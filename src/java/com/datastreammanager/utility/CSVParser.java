/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datastreammanager.utility;

import com.datastreammanager.model.ColumnPair;
import com.datastreammanager.model.TupleModel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PprrATeekK
 */
public class CSVParser extends Parser{

    
    @Override
    public void setParameters() {
        
        setDlimNewItem("\"\t\"");
        setDlimNewLine("\r\n");
        setDlimTuple("▓");
        setHeader("");
        setTrailer("");
    }
    
    
   
    @Override
        public ArrayList<TupleModel> parse(String dataString) {

         //   System.out.println("Parsing csv file");
        
           ArrayList<ColumnPair> colList = new ArrayList<ColumnPair>();
          
           ColumnPair col;
           
           tuples.clear();
        
           
           dataString  = dataString.replaceAll("\n", "");
           dataString = dataString.replaceAll("\"\t\"", ";");
           
        //   dataString  = dataString.replaceAll("\t", ";");
          
           dataString = dataString.replaceAll("\"\"", "▓"); //alt+1458
           dataString = dataString.replaceAll("\"", "");
          // System.out.println(">>>>"+dataString);
           String metadata = dataString.subSequence(0, dataString.indexOf("▓")).toString();
           String[] columns = metadata.split(";");
          int m=0,n=0;
           for(String str : columns){
              
              // if(!(str=str.trim()).equals(""))
               if(str.equals("linkPoints")){
                   n=m;
                   continue;
               }
               m++;
            //   System.out.println(str);
//               col = new ColumnPair();
//               col.setColumnName(str);
//               col.setColumnDatatype("varchar");
//               col.setSize("100");
//               colList.add(col);
           }
           
           TupleModel tpl; 
           String[] dataRows=dataString.split("▓");
           String[] dataItems;
           for(int i=1;i<25;i++){
           dataItems = dataRows[i].split(";");
           tpl = new TupleModel();
           
           for(int j=0;j<dataItems.length;j++){
               if(j==n)
                   continue;
              col = new ColumnPair();
              col.setValue(dataItems[j]);
        //    System.out.print(col.getValue()+"\t");
              
              tpl.getDataTuple().add(col);
              
           }
     //       System.out.println();
           
               
                  DateFormat df = new SimpleDateFormat("d/M/yyyy HH:mm:ss");
                   try {
                       Date dt = df.parse(tpl.getDataTuple().get(4).getValue());
                       df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                       tpl.getDataTuple().get(4).setValue(df.format(dt));
                //       System.out.println("DATE:"+col.getValue());
                   } catch (ParseException ex) {
                       Logger.getLogger(CSVParser.class.getName()).log(Level.SEVERE, null, ex);
                   }
              
                tuples.add(tpl);
           }
         
        
        
        
        return tuples;
    }
    

    
    
}
