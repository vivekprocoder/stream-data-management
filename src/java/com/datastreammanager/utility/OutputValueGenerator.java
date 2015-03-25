/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.datastreammanager.utility;

import com.datastreammanager.Handler.DBHandler;

/*
 *@author PprrATeekK
 */

public class OutputValueGenerator  extends OutputGenerator{

        
    private DBHandler dbHandler;
    public void generateTempOutput(String tableName, String query){
        dbHandler = new DBHandler();
     //    System.out.println("OutputValueGenerator");
        String result = dbHandler.executeQuery(query, tableName);
    }
    
}
