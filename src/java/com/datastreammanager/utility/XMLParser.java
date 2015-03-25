/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//http://207.251.86.229/nyc-links-cams/LinkSpeedQuery.php


package com.datastreammanager.utility;

import com.datastreammanager.model.TupleModel;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @author PprrATeekK
 */
public class XMLParser extends Parser{

    @Override
    public String RemoveHeader(String dataString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String RemoveTrailer(String dataString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<TupleModel> parse(String dataString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<TupleModel> parse(InputStream dataStream) {
        
        MySAXParser saxParser = new MySAXParser(dataStream);
        return saxParser.getTuples();
        
    }
    
    @Override
    public void setParameters() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
}
