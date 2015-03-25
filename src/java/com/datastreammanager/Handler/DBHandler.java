/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.datastreammanager.Handler;

import com.datastreammanager.model.ColumnPair;
import com.datastreammanager.model.TupleModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * @author PprrATeekK
 */
public class DBHandler {

    DbConn dbconn = new DbConn();
    public ArrayList<TupleModel> fetchRowsFromTable(String dataRepo, String condition) {

        ArrayList<TupleModel> dataMatrix = new ArrayList<TupleModel>();

        try {
            ColumnPair col;
            ArrayList<ColumnPair> dataRow;
            TupleModel row;
            ResultSet results = dbconn.selectAll(dataRepo,condition);
            
            if(results==null)
                return null;
            
            ResultSetMetaData rsmd = results.getMetaData();
            System.out.println(rsmd.getTableName(1));
            while (results.next()) {
                dataRow = new ArrayList<ColumnPair>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    col = new ColumnPair();
                    col.setColumnDatatype(rsmd.getColumnClassName(i));
                    col.setValue(results.getString(i));
                    dataRow.add(col);
                }
                row = new TupleModel();
                row.setDataRow(dataRow);
                dataMatrix.add(row);
            }
            results.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return dataMatrix;
    }
    
    
    public ArrayList<TupleModel> fetchRowsFromTable(String dataRepo) {

        ArrayList<TupleModel> dataMatrix = new ArrayList<TupleModel>();

        try {
            ColumnPair col;
            ArrayList<ColumnPair> dataRow;
            TupleModel row;
            ResultSet results = dbconn.selectAll(dataRepo);
            
            if(results==null)
                return null;
            
            ResultSetMetaData rsmd = results.getMetaData();
         //   System.out.println(rsmd.getTableName(1));
            while (results.next()) {
                dataRow = new ArrayList<ColumnPair>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    col = new ColumnPair();
                    col.setColumnDatatype(rsmd.getColumnClassName(i));
                    col.setValue(results.getString(i));
                    dataRow.add(col);
                }
                row = new TupleModel();
                row.setDataRow(dataRow);
                dataMatrix.add(row);
            }
            results.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return dataMatrix;
    }
    
    
    public int insertRowsIntoTable(String dataRepo, ArrayList<TupleModel> tuples){
        
        int num=0;
    //    System.out.println(tuples.size());
        for(int i=0; i<tuples.size();i++){
       num += dbconn.insert(dataRepo, tuples.get(i).getDataTuple());
        }
        System.out.println(num + " rows inserted in "+dataRepo);
        return num;
    }
    
    public boolean emptyCache(String query){
        
        return dbconn.deleteRecords(query);
    }
    
    
    public boolean updateIncrement(String tableName,String colName, double newValue,String primaryKeyCol,String primaryKeyValue){
    
        return dbconn.updateIncrement(tableName,colName,newValue,primaryKeyCol,primaryKeyValue);
    }
            
    public String executeQuery(String query, String outputTable){
    
        return dbconn.executeQuery(query, outputTable);
    }
    
}
