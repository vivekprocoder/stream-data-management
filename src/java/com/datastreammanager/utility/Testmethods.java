/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datastreammanager.utility;

import java.util.ArrayList;

/**
 *
 * @author _
 */
public class Testmethods {
    public static void main(String[] args) {
        Addquery a = new Addquery();
        //a.getstreams();
        
        ArrayList<String> temp = a.getstreamsdata();
        for(int i=0;i<temp.size();i++){
            System.out.println(temp.get(i));
        }
    }
    
}
