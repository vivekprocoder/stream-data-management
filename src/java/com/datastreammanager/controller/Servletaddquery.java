/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datastreammanager.controller;

import com.datastreammanager.Handler.DBHandler;
import com.datastreammanager.Handler.DbConn;
import com.datastreammanager.model.ColumnPair;
import com.datastreammanager.model.TupleModel;
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
@WebServlet(name = "addquery", urlPatterns = {"/addquery"})
public class Servletaddquery extends HttpServlet {

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
        String[] table = request.getParameterValues("select1");
        String[] selectdata = request.getParameterValues("select2");
        String where1 = request.getParameter("select6");
        String where1tx = request.getParameter("displayValue1");
        String where2 = request.getParameter("select7");
        String where3 = request.getParameter("select8");
        String where3tx = request.getParameter("displayValue");
        String windowSize = request.getParameter("Text1");
        String interval = request.getParameter("text01");
        String queryName = request.getParameter("Text2");
        String aggregation = request.getParameter("select4");
        String aggregationfield = request.getParameter("select5");
        //String tablename = request.getParameter("texthidden");
        DBHandler dbhandler = new DBHandler();
        ArrayList<TupleModel> tuple = dbhandler.fetchRowsFromTable("streammetadata");
       
        String tablename[]= new String[table.length];
        int count=0;
        for(int i = 0; i < tuple.size() ;i++)
        {
            for(int j = 0; j < table.length ;j++)
            {
                if( tuple.get(i).getDataTuple().get(2).getValue().equals(table[j]))
                    tablename[j] = tuple.get(i).getDataTuple().get(0).getValue();
            }
        }
        
        String query="SELECT ";
        if(aggregation.compareTo("")==0){
            for(int i = 0; i < selectdata.length; i++){
                if(i != selectdata.length - 1)
                    query = query + selectdata[i] + " , ";                
                
                    else
                        query = query + selectdata[i] + " FROM ";
                
            }
        }else{
            query = query + aggregation + " ( " + aggregationfield + " ) FROM ";
        }
        for(int i = 0;  i < table.length; i++){
            if(i != table.length - 1){
                query = query + table[i] + " , ";
            }
            else
                query = query + table[i];
        }
        if(where1tx.compareTo("")!= 0){
            query = query + "  WHERE ";
            if(where1.compareTo("") != 0)
                query = query + where1;
            else
                query = query + where1tx;
            query = query + " "+ where2 + " " ;
            if(where3.compareTo("") !=0 )
                query = query + where3;
            else 
                query = query + where3tx;
        }
        for(int i = 0; i < table.length; i++){
            query = query.replaceAll(table[i], "App.STRM"+tablename[i]);
        }
        ArrayList<ColumnPair> cList = new ArrayList<ColumnPair>();
        ColumnPair cp = new ColumnPair();
        
        cp.setValue(queryName);
        cList.add(cp);
        
        cp = new ColumnPair();
        cp.setValue(query);
        cList.add(cp);
        
        cp = new ColumnPair();
        cp.setValue("Value");
        cList.add(cp);
        
        cp = new ColumnPair();
        cp.setValue(windowSize);
        cList.add(cp);
        
        cp = new ColumnPair();
        cp.setValue(interval);
        cList.add(cp);
        
        cp = new ColumnPair();
        cp.setValue(tablename[0]);
        cList.add(cp);
        
        DbConn dbconn = new DbConn();
        dbconn.insert("queryInfo", cList);
        
        //Addquery qry =new Addquery();
        //qry.saveQuery(queryName, query, windowSize);
        try {
            /* TODO output your page here*/
            
            out.println("<html>");
            request.getRequestDispatcher("masterpage.html").include(request, response);
            out.println("<head>");
            out.println("<title>Servlet addquery</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addquery at " + request.getContextPath () + "</h1>");
            out.println(query);
            //out.println(two);out.println(one[1]);out.println(one[2]);
            out.println("</body>");
            out.println("</html>");
             
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