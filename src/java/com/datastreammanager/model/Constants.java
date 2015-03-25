/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 *@author PprrATeekK
 */

package com.datastreammanager.model;


public class Constants {

    
    public static String NameOfStreamMetadataRepo = "STREAMMETADATA";
    public static String NameOfQueryInfoRepo = "QUERYINFO";
    
    static{
        System.setProperty("http.proxyHost", "192.16.3.254");
        System.setProperty("http.proxyPort", "8080");
        System.setProperty("https.proxyHost", "192.16.3.254");
        System.setProperty("https.proxyPort", "8080");
    }
    
    
}
