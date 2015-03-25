/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datastreammanager.Handler;

import com.datastreammanager.model.ColumnPair;
import com.datastreammanager.model.Constants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * @author PprrATeekK
 */
public class DbConn {

    private static final String database = "StreamData";
    private static final String schema = "App.";
    private static final String user = "root";
    private static final String password = "tiger";
    private static final String host = "localhost";
    private static final String driverClass = "org.apache.derby.jdbc.ClientDriver";
    private static final String dbURL = "jdbc:derby://" + host + ":1527/" + database + ";create=true;user=" + user + ";password=" + password;

    private Connection conn = null;
    private Statement stmt = null;

    private void createConnection() {
        try {
            Class.forName(driverClass).newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL);
            stmt= conn.createStatement();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public boolean creatTable(String tableName, ArrayList<ColumnPair> colList) {

        
        boolean status=false;
        
        try {
            if(conn==null)
            createConnection();
            
            String str = "S_ID INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) not null primary key, CQTIME TIMESTAMP Default CURRENT_TIMESTAMP,";
            
            for (int i = 0; i < colList.size(); i++) {
                str = str + " " + colList.get(i).getColumnName() + " " + colList.get(i).getColumnDatatype();
                if(!"".equals(colList.get(i).getSize()))
                    str+="(" + colList.get(i).getSize() + "),";
                else
                    str+=",";
                            
            }
            if (str.endsWith(",")) {
                str = str.substring(0, str.length() - 1);
            }

            str = "Create table "+schema + tableName + " (" + str + ")";
   //         System.out.println(str);
            status=stmt.execute(str);
            
           
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }

        return status;
    }

    public int insert(String tableName, ArrayList<ColumnPair>  columnPair) {
        
        int numbs=0;
        try {
            
            if(conn==null)
            createConnection();
            
            ResultSet results = stmt.executeQuery("select * from "+schema+tableName);
            ResultSetMetaData rsmd = results.getMetaData();
           
            String str = "";
            String colStr="";
            String val="";
            
        //    System.out.println("Column Pair Size: "+columnPair.size());
            for (int i = 0; i < columnPair.size(); i++) {
                
                if(!rsmd.getColumnTypeName(i+3).equalsIgnoreCase("Double"))
                   val="'"+ columnPair.get(i).getValue().trim()+"'";
                else
                    val=columnPair.get(i).getValue().trim();
                
                str = str + " CAST(" + val + " as "+rsmd.getColumnTypeName(i+3);
                
                if(rsmd.getColumnTypeName(i+3).equalsIgnoreCase("VARCHAR"))
                str+="("+rsmd.getPrecision(i+3)+")),";
                else if(rsmd.getColumnTypeName(i+3).equalsIgnoreCase("NUMERIC") )
                str+="("+rsmd.getPrecision(i+3)+","+rsmd.getScale(i+3)+")),";
                else
                str+="),";
                
                colStr += rsmd.getColumnName(i+3)+",";
            }
//            for (int i = 2; i <= rsmd.getColumnCount(); i++) {
//                
//            }
            
            
            if (colStr.endsWith(","))
            colStr = colStr.substring(0, colStr.length() - 1);
            
            if (str.endsWith(","))
            str = str.substring(0, str.length() - 1);
            
    
            numbs += stmt.executeUpdate("insert into "+schema + tableName +" ("+colStr+ ") values (" + str + ")");
            
            if(tableName.equalsIgnoreCase(Constants.NameOfStreamMetadataRepo)){
                ResultSet rs =stmt.executeQuery("select max(streamid) from "+schema+Constants.NameOfStreamMetadataRepo);
                rs.next();
                numbs=rs.getInt(1);
            }
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        return numbs;
    }

    public ResultSet selectAll(String tableName) {
       
        ResultSet results=null; 
        
        try {
           if(conn==null)
           createConnection();
           
           results = stmt.executeQuery("select * from "+schema+ tableName);
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        
        return results;
    }

    
     public ResultSet selectAll(String tableName, String condition) {
       
        ResultSet results=null; 
        
        try {
           if(conn==null)
           createConnection();
           if(condition!=null)
           results = stmt.executeQuery("select * from "+schema+ tableName+" where "+condition);
           else
           results = stmt.executeQuery("select * from "+schema+ tableName);
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        
        return results;
    }
     
    public boolean deleteRecords(String query){
    
        boolean status=false;
        if(conn==null)
            createConnection();
        try {
          int i =  stmt.executeUpdate(query);
            
            if(i!=0)
                status=true;
            
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        
        
        return status;
    }
    
    public boolean updateIncrement(String tableName,String colName, double newValue,String primaryKeyCol,String primaryKeyValue){
    
        boolean status=false;
        if(conn==null)
            createConnection();
        try {
            status=stmt.execute("update "+schema+ tableName+" SET "+colName+"="+newValue+" where "+primaryKeyCol+"="+primaryKeyValue);
            
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        
        
        return status;
    }
    
     public String executeQuery(String query, String outputTable){
    
        String result="NA";
        
        ResultSet results=null; 
        
        try {
           if(conn==null)
           createConnection();
         
           results = stmt.executeQuery(query) ;
       
          results.next();
          
          result = ""+results.getObject(1).toString();
          System.out.println("################# Output : "+result+" #################");
           
          
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        
        return result;
    }
    
    public void closeConnection() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sqlExcep) {
            sqlExcep.printStackTrace();
        }

    }
}
