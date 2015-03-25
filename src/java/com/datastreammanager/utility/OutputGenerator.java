/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 *@author PprrATeekK
 */

package com.datastreammanager.utility;

import com.datastreammanager.Handler.DBHandler;


public class OutputGenerator {

    private DBHandler dbHandler;
    public void generateTempOutput(String tableName, String query){
        dbHandler = new DBHandler();
   //     System.out.println("OutputGenerator");
        String result = dbHandler.executeQuery(query, tableName);
    }
    
    
}
