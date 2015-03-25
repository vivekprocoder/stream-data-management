/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datastreammanager.controller;

import com.datastreammanager.Handler.DBHandler;
import com.datastreammanager.Handler.QueryHandler;
import com.datastreammanager.model.TupleModel;
import java.util.ArrayList;

/**
 *
 * @author _
 */
class QueryMonitor implements Runnable{

    
    private DBHandler dbhandler = new DBHandler();
    private ArrayList<TupleModel> dataMatrix;
    private Thread[] ths ;
    private QueryHandler[] queryHandlers;
    private QueryMonitor[] qMs;  
    private int z=0;
    
    
    public QueryMonitor(int z){
    this.z=z;
    }
    
    public QueryMonitor(){
    
    dataMatrix = dbhandler.fetchRowsFromTable("QueryInfo");
        ths = new Thread[dataMatrix.size()];
        qMs= new QueryMonitor[dataMatrix.size()];
        queryHandlers =  new QueryHandler[dataMatrix.size()];
       
        
         if (dataMatrix == null) {
            return; 
        }
        
   for(int z = 0; z < dataMatrix.size(); z++){
            qMs[z] = new QueryMonitor(z);
            qMs[z].setDataMatrix(dataMatrix);
            qMs[z].setQueryHandlers(queryHandlers);
        }
    }
    
    public void startQuery(String queryName) {
       
        for (int z = 0; z < dataMatrix.size(); z++){
            if(dataMatrix.get(z).getDataTuple().get(0).getValue().equalsIgnoreCase(queryName)){

            ths[z] = new Thread(qMs[z], "" +dataMatrix.get(z).getDataTuple().get(0).getValue());
            ths[z].start();
            }
        }
        
    }

    public void stopQuery(String queryName) {
      
         for (int z = 0; z < dataMatrix.size(); z++){
            if(dataMatrix.get(z).getDataTuple().get(0).getValue().equalsIgnoreCase(queryName))
             queryHandlers[z].setStatus(false);
        }
    }

    public ArrayList<TupleModel> getDataMatrix() {
        return dataMatrix;
    }

    public void setDataMatrix(ArrayList<TupleModel> dataMatrix) {
        this.dataMatrix = dataMatrix;
    }

    public QueryHandler[] getQueryHandlers() {
        return queryHandlers;
    }

    public void setQueryHandlers(QueryHandler[] queryHandlers) {
        this.queryHandlers = queryHandlers;
    }

    @Override
    public void run() {
       System.out.println("Query Thread running : "+Thread.currentThread().getName());
        
       queryHandlers[z] = new QueryHandler(dataMatrix.get(z).getDataTuple());
       queryHandlers[z].startExecutingQuery();
   
    }
    
}
