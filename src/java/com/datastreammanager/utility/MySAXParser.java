/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 *@author PprrATeekK
 */

package com.datastreammanager.utility;

import com.datastreammanager.model.ColumnPair;
import com.datastreammanager.model.TupleModel;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class MySAXParser extends DefaultHandler {
    
   ArrayList<TupleModel> tuples = new ArrayList<TupleModel>();
   int count = 25;
   TupleModel tuple;
    
    
    public MySAXParser(InputStream XmlStream) {
        
        parseDocument(XmlStream);
      
    }
    private void parseDocument(InputStream XmlStream) {
        // parse
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(XmlStream, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }
   
    @Override
    public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException {
        // if current element is book , create new book
        // clear tmpValue on start of element

        if (elementName.equalsIgnoreCase("Speed")) {
            
            if(count--<=0)
                return;
            
            tuple = new TupleModel();
            for(int i =0 ; i<attributes.getLength();i++){
                if(attributes.getLocalName(i).equalsIgnoreCase("linkPoints"))
                   continue; 
                ColumnPair col = new ColumnPair();                
                col.setValue(attributes.getValue(i));
           //     System.out.print(col.getValue()+"\t");
                tuple.getDataTuple().add(col);
            }
            
              
                  DateFormat df = new SimpleDateFormat("d/M/yyyy HH:mm:ss");
                   try {
                       Date dt = df.parse(tuple.getDataTuple().get(4).getValue());
                       df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                       tuple.getDataTuple().get(4).setValue(df.format(dt));
                //       System.out.println("DATE:"+col.getValue());
                   } catch (ParseException ex) {
                       Logger.getLogger(CSVParser.class.getName()).log(Level.SEVERE, null, ex);
                   }
             
            tuples.add(tuple);
         //   System.out.println("\n"+tuple.getDataTuple().size());
        }
        
    }

    public ArrayList<TupleModel> getTuples() {
        return tuples;
    }

    public void setTuples(ArrayList<TupleModel> tuples) {
        this.tuples = tuples;
    }
    
    
    
    @Override
    public void endElement(String s, String s1, String element) throws SAXException {
        // if end of book element add to list
       
        
    }
    @Override
    public void characters(char[] ac, int i, int j) throws SAXException {
      
    }
    public static void main(String[] args) {
      //  new MySAXParser("TrafficSpeed.xml");
    }
}
