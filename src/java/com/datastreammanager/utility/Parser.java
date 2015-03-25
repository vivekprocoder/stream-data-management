/*
 * @author PprrATeekK
 */

package com.datastreammanager.utility;

import com.datastreammanager.model.SchemaModel;
import com.datastreammanager.model.TupleModel;
import java.io.InputStream;
import java.util.ArrayList;


public abstract class Parser {
    
    SchemaModel tblData;
    String dlimNewLine;
    String dlimNewItem;
    String dlimKeyValue;
    String dlimTuple;
    String header;
    String trailer;
    ArrayList<TupleModel> tuples = new ArrayList<TupleModel>();

    public ArrayList<TupleModel> getTuples() {
        return tuples;
    }

    public void setTuples(ArrayList<TupleModel> tuples) {
        this.tuples = tuples;
    }
            
   public String RemoveHeader(String dataString){
   return dataString.replaceFirst(getHeader(), "");
   }
   public String RemoveTrailer(String dataString){
   return dataString.replaceFirst(getTrailer(), "");
   }
   
   public String RemoveNewLine(String dataString){
   return dataString.replaceAll(getDlimNewLine(), "");
   }
   
   public String RemoveDlimKeyValue(String dataString){
   return dataString.replaceAll(getDlimKeyValue(), "▓");
   }
   
   public abstract ArrayList<TupleModel> parse(String dataString);
   public ArrayList<TupleModel> parse(InputStream dataStream){
   
       return null;
   }
   public abstract void setParameters();
  
    public SchemaModel getTblData() {
        return tblData;
    }

    public void setTblData(SchemaModel tblData) {
        this.tblData = tblData;
    }

    public String getDlimNewLine() {
        return dlimNewLine;
    }

    public void setDlimNewLine(String dlimNewLine) {
        this.dlimNewLine = dlimNewLine;
    }

    public String getDlimNewItem() {
        return dlimNewItem;
    }

    public void setDlimNewItem(String dlimNewItem) {
        this.dlimNewItem = dlimNewItem;
    }

    public String getDlimKeyValue() {
        return dlimKeyValue;
    }

    public void setDlimKeyValue(String dlimKeyValue) {
        this.dlimKeyValue = dlimKeyValue;
    }

    public String getDlimTuple() {
        return dlimTuple;
    }

    public void setDlimTuple(String dlimTuple) {
        this.dlimTuple = dlimTuple;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }
    
    
}
