/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datastreammanager.controller;

import com.datastreammanager.Handler.ManageStream;
import com.datastreammanager.model.ColumnPair;
import com.datastreammanager.model.SchemaModel;
import com.datastreammanager.model.StreamModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author _
 */
@WebServlet(name = "addstream", urlPatterns = {"/addstream"})
public class ServletRegisterStream extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            StreamModel strmMetadata = new StreamModel();
            
            strmMetadata.setStreamname(request.getParameter("StreamName"));
            strmMetadata.setDatatype(request.getParameter("StreamType"));
            strmMetadata.setSocketaddress(request.getParameter("SocketAddress")+":"+request.getParameter("port"));
            strmMetadata.setUrl(request.getParameter("URL"));
            
            try{
            strmMetadata.setSlackness(Integer.parseInt(request.getParameter("slackness")));
            }catch(NumberFormatException nfex){
                System.out.println(nfex.getMessage());
            }
            strmMetadata.setSlacknessType(request.getParameter("slacknesstype"));
            
            try{
            strmMetadata.setIncrement(Long.parseLong(request.getParameter("startingvalue")));
            }catch(NumberFormatException nfex){
                System.out.println(nfex.getMessage());
            }
            strmMetadata.setArgColumn(request.getParameter("columnname"));
            strmMetadata.setFilterClause("where");
            
            SchemaModel schema = new SchemaModel();
            ArrayList<ColumnPair> cols = new ArrayList<ColumnPair>();
            int counter = Integer.parseInt(request.getParameter("counter"));
            
            ColumnPair col;
            
            for(int i=0;i<counter;i++){
            col = new ColumnPair();
            col.setColumnName(request.getParameter("colName"+i));
            col.setColumnDatatype(request.getParameter("colDataType"+i));
            col.setSize(request.getParameter("size"+i));
            System.out.println("Col Name:"+col.getColumnName()+" : Col. Datatype:"+col.getColumnDatatype());
            if(col.getColumnName()!=null)
            cols.add(col);
            }
            
            schema.setSchema(cols);
            
            strmMetadata.setSchema(schema);
            
            ManageStream addStream = new ManageStream();
            addStream.registerStream(strmMetadata);
            
            request.setAttribute("saveStatus", "saved");
            response.sendRedirect("addstream.jsp");
            
        } finally {             
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
