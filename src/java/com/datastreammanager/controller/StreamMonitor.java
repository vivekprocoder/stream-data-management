
package com.datastreammanager.controller;

import com.datastreammanager.Handler.DBHandler;
import com.datastreammanager.Handler.StreamHandler;
import com.datastreammanager.model.ColumnPair;
import com.datastreammanager.model.Constants;
import com.datastreammanager.model.TupleModel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author PprrATeekK
 */
public class StreamMonitor implements Runnable{

    
    private DBHandler dbhandler = new DBHandler();
    private ArrayList<TupleModel> dataMatrix;
    private Thread[] ths ;
    private StreamHandler[] strmHandlers;
    private StreamMonitor[] sMs;  
    private int x=0;
    public StreamMonitor(int z){
    this.x=z;
    }
    
    public StreamMonitor(){
    
    
        dataMatrix = dbhandler.fetchRowsFromTable(Constants.NameOfStreamMetadataRepo);
        
         if (dataMatrix == null) {
            return; // false ;
        }
        
        ths = new Thread[dataMatrix.size()];
        sMs= new StreamMonitor[dataMatrix.size()];
        strmHandlers =  new StreamHandler[dataMatrix.size()];
       
        
        
        
   for(int z = 0; z < dataMatrix.size(); z++){
            sMs[z] = new StreamMonitor(z);
            sMs[z].setDataMatrix(dataMatrix);
            sMs[z].setStrmHandlers(strmHandlers);
  //          sMs[z].setsMs(sMs);
        //    ths[z] = new Thread(sMs[z], "" +dataMatrix.get(z).getDataTuple().get(0).getValue());
        }
        
    }
    
    
     
  
    public static void main(String[] args) {

        DBHandler dbhandler = new DBHandler();
       // StreamMonitor strmMtr = new StreamMonitor();
        ArrayList<TupleModel> dataMatrix = dbhandler.fetchRowsFromTable("StreamMetadata");
        
         if (dataMatrix == null) {
            return; // false ;
        }
         
        Thread[] ths = new Thread[dataMatrix.size()];
        StreamMonitor[] sMs= new StreamMonitor[dataMatrix.size()];
        StreamHandler[] strmHandlers =  new StreamHandler[dataMatrix.size()];
       
        int z;        
        
       // strmMtr.monitorStream();
        
        
        
        
   for (z = 0; z < dataMatrix.size(); z++){
            sMs[z] = new StreamMonitor(z);
            sMs[z].setDataMatrix(dataMatrix);
            sMs[z].setStrmHandlers(strmHandlers);
            ths[z] = new Thread(sMs[z], "" +dataMatrix.get(z).getDataTuple().get(0).getValue());
//            if(!dataMatrix.get(z).getDataTuple().get(2).getValue().equalsIgnoreCase("FireDept"))
            ths[z].start();
        }
        
       
    }

    
    public void startStream(String strmId){
    
        for (int z = 0; z < dataMatrix.size(); z++){
            if(dataMatrix.get(z).getDataTuple().get(0).getValue().equalsIgnoreCase(strmId)){

            ths[z] = new Thread(sMs[z], "" +dataMatrix.get(z).getDataTuple().get(0).getValue());
            ths[z].start();
            }
        }
        
    }
   
     public void stopStream(String strmId){
    
         for (int z = 0; z < dataMatrix.size(); z++){
            if(dataMatrix.get(z).getDataTuple().get(0).getValue().equalsIgnoreCase(strmId))
             strmHandlers[z].setStatus(false);
        }
    }
    
    @Override
    public void run() {
                    System.out.println("Thread running : "+Thread.currentThread().getName());
                    strmHandlers[x] = new StreamHandler();
                    ArrayList<ColumnPair> streamMetadata = dataMatrix.get(x).getDataTuple();
                    strmHandlers[x].startFetchingStream(streamMetadata);
    }

    
    
    public DBHandler getDbhandler() {
        return dbhandler;
    }

    public void setDbhandler(DBHandler dbhandler) {
        this.dbhandler = dbhandler;
    }

    public ArrayList<TupleModel> getDataMatrix() {
        return dataMatrix;
    }

    public void setDataMatrix(ArrayList<TupleModel> dataMatrix) {
        this.dataMatrix = dataMatrix;
    }

    public StreamHandler[] getStrmHandlers() {
        return strmHandlers;
    }

    public void setStrmHandlers(StreamHandler[] strmHandlers) {
        this.strmHandlers = strmHandlers;
    }

    public StreamMonitor[] getsMs() {
        return sMs;
    }

    public void setsMs(StreamMonitor[] sMs) {
        this.sMs = sMs;
    }

    
}
