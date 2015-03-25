/**
 *
 * @author PprrATeekK
 */

package com.datastreammanager.utility;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
 
public class HTTPConnection {
    
    int i;
    /**
     * Makes a HTTP request to a URL and receive response
     * @param requestUrl the URL address
     * @param method Indicates the request method, "POST" or "GET"
     * @param params a map of parameters send along with the request
     * @return An array of String containing text lines in response
     * @throws IOException
     */
    
    
    public static InputStream sendHttpRequest(String requestUrl, String method, Map<String, String> params) throws IOException {
         
        
        System.out.println("Fetching data from "+requestUrl);
       StringBuilder requestParams = new StringBuilder();
        
       if (params != null && params.size() > 0) {
           Iterator<String> paramIterator = params.keySet().iterator();
           while (paramIterator.hasNext()) {
               String key = paramIterator.next();
               String value = params.get(key);
               requestParams.append(URLEncoder.encode(key, "UTF-8"));
               requestParams.append("=").append(URLEncoder.encode(value, "UTF-8"));
               requestParams.append("&");
           }
       }
        
       URL url = new URL(requestUrl);
       URLConnection urlConn = url.openConnection();
       urlConn.setUseCaches(false);
        
       // the request will return a response
       urlConn.setDoInput(true);
        
       if ("POST".equals(method)) {
           // set request method to POST
           urlConn.setDoOutput(true);
       } else {
           // set request method to GET
           urlConn.setDoOutput(false);
       }
        
       if ("POST".equals(method) && params != null && params.size() > 0) {
           OutputStreamWriter writer = new OutputStreamWriter(urlConn.getOutputStream());
           writer.write(requestParams.toString());
           writer.flush();   
           writer.close();
       }
        
        return urlConn.getInputStream();
        
    }
    
}
