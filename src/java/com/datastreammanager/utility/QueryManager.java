/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datastreammanager.utility;
import com.datastreammanager.Handler.DBHandler;
import com.datastreammanager.model.Constants;
import com.datastreammanager.model.TupleModel;
import java.util.ArrayList;

/**
 *
 * @author _
 */
public class QueryManager {  
    
   public ArrayList<TupleModel> getquerynames(){
   ArrayList<TupleModel> result;
        DBHandler dbhandler = new DBHandler();
        result = dbhandler.fetchRowsFromTable(Constants.NameOfQueryInfoRepo);        
        return result;   
   }
}
