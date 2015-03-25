/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datastreammanager.model;

import java.util.HashMap;

/**
 *
 * @author _
 */
public class QueryStatus {
    
    static HashMap<String, String> status = new HashMap<String, String>();

    public static HashMap<String, String> getStatus() {
        return status;
    }

    public static void setStatus(HashMap<String, String> status) {
        QueryStatus.status = status;
    }

    public static void add(String name){
        status.put(name, "");
    }
    
    public static void remove(String name){
        status.remove(name);
    }
    
    public static String getVal(){
        return status.toString();
    }
}
