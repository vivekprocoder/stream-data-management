/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datastreammanager.utility;
import com.datastreammanager.Handler.*;
import com.datastreammanager.model.ColumnPair;
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
public class StreamManager {
   public ArrayList<TupleModel> getStreamNames(){
        ArrayList<TupleModel> result;
        DBHandler dbhandler = new DBHandler();
        result = dbhandler.fetchRowsFromTable(Constants.NameOfStreamMetadataRepo);        
        return result;
    }   
    
}
