/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datastreammanager.utility;

import com.datastreammanager.Handler.DBHandler;
import com.datastreammanager.model.Constants;
import com.datastreammanager.model.TupleModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

/**
 *
 * @author _
 */
public class Addquery {

    private static String database = "StreamData";
    private static String user = "root";
    private static String password = "tiger";
    private static String dbURL = "jdbc:derby://localhost:1527/" + database + ";create=true;user=" + user + ";password=" + password;
    private static String tableName;
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
    private DBHandler dbHandler = new DBHandler();

    private static Connection createConnection() {
        Connection conn1 = null;
        try {

            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            conn1 = DriverManager.getConnection(dbURL);
        } catch (Exception except) {
            except.printStackTrace();
        }
        return conn1;
    }

    public ArrayList<String> getstreams() {
        ArrayList<String> result = new ArrayList<String>();

        ArrayList<TupleModel> tuples = dbHandler.fetchRowsFromTable(Constants.NameOfStreamMetadataRepo);
        int count = 0;
        for (TupleModel tpl : tuples) {
            result.add(tpl.getDataTuple().get(2).getValue());
        }

        return result;
    }

    public ArrayList<String> getstreamsdata() {
        ArrayList<String> result = new ArrayList<String>();
        try {

            conn = createConnection();
            tableName = "app.streammetadata";
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select STREAMID,NAME from " + tableName);
            /*ResultSetMetaData rsmd = results.getMetaData();
             int numberCols = rsmd.getColumnCount();
             for (int i=1; i<=numberCols; i++)
             {
             //print Column Names
             System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
             }*/

            //    System.out.println("\n-------------------------------------------------");
            int count = 0;
            while (results.next()) {
                int id = results.getInt(1);//adding id to arraylist 
                String name = results.getString(2);
                Connection conn2 = null;
                Statement stmt2 = null;
                conn2 = createConnection();
                stmt2 = conn2.createStatement();
                ResultSet results2 = stmt2.executeQuery("select * from " + "app.STRM" + id);
                ResultSetMetaData rsmd = results2.getMetaData();
                int numberCols = rsmd.getColumnCount();
                for (int i = 1; i <= numberCols; i++) {
                    //print Column Name
                    String columnname = rsmd.getColumnLabel(i);
                    //     System.out.print(columnname+"\t\t");
                    result.add(name + "." + columnname);
                }
                //result.add(name);
                //        System.out.println(id + "   " + name);
            }
            results.close();
            stmt.close();
            conn.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        return result;
    }

    public boolean saveQuery(String queryName, String queryString, String windowSize) {
        try {

//            ColumnPair cp = new ColumnPair();
//            ArrayList<>
//            
//            
//            String[] str={"Que123",queryString,"graph","3",queryName};
//            DbConn.insert("app.queryinfo", str);
//            

            conn = createConnection();
            tableName = "app.queryinfo";
            System.out.println(conn.getMetaData().toString());
            stmt = conn.createStatement();
            String id = "3";
            String queryOutput = "dgdh";
            int win = 10;
            stmt.execute("insert into " + tableName + " values ("
                    + id + "," + queryString + "," + queryOutput + "," + win + "," + queryName + ")");
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        return true;
    }
}
