/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datastreammanager.model;

import java.lang.String;
import java.util.ArrayList;

/**
 *
 * @author _
 */
public class StreamStatus {
    
   static ArrayList<String> status = new ArrayList<String>();;
    
    public static boolean checkStream(String streamId){
    
       return  status.contains(streamId);
    }

    public static ArrayList<String> getStatus() {
        return status;
    }

    public static void setStatus(ArrayList<String> status) {
        StreamStatus.status = status;
    }  
    
    public static void addStream(String name){
        status.add(name);
    }
    
    public static void removeStream(String name){
        status.remove(name);
    }
    
}
