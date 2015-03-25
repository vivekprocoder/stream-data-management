/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 *@author PprrATeekK
 */

package com.datastreammanager.utility;

import com.datastreammanager.Handler.DBHandler;
import com.datastreammanager.model.QueryModel;


public abstract class MonitorWindow implements Runnable{

    protected int windowSize;   // No. of tuples to be kept in cache
    protected int windowFilled=0;
    protected windowCategory windowType;
    protected OutputGenerator outHandle;
    protected QueryModel query;
    protected String dataRepo;
    DBHandler dbhandler = new DBHandler();
    
    protected Thread queryThread = new Thread(this, "queryThread");
    
    public enum windowCategory{
        Cascade,
        Sliding
    };
    
    abstract public boolean monitor();
    abstract public boolean triggerOutputGenerator();
    abstract public boolean flushCache();
    
    
    public void init( String dataRepo, QueryModel query,int slackness,windowCategory type){
    
        setWindowSize(slackness);
        setWindowType(type);
      //  System.out.println("Query Output Type: "+query.getOutputType());
        if(query.getOutputType().equalsIgnoreCase(QueryModel.OutputTypes.Value.name()))
        outHandle = new OutputValueGenerator();
        else if(query.getOutputType().equalsIgnoreCase(QueryModel.OutputTypes.Graph.name()))
        outHandle = new OutputGraphGenerator();
        else if(query.getOutputType().equalsIgnoreCase(QueryModel.OutputTypes.Doc.name()))
        outHandle = new OutputDocGenerator();
        else
        outHandle =  new OutputGenerator();
        
        setDataRepo(dataRepo);
        setQuery(query);
        
    }
    
    
     public void init( String dataRepo,int slackness,windowCategory type){
    
        setWindowSize(slackness);
        setWindowType(type);
      //  System.out.println("Query Output Type: "+query.getOutputType());
       
        setDataRepo(dataRepo);
        
    }
    
    public void updateWindowFilled(int amount){
      windowFilled=windowFilled+amount;
    }
    
    public QueryModel getQuery() {
        return query;
    }

    public void setQuery(QueryModel query) {
        this.query = query;
    }
    public int getWindowSize() {
        return windowSize;
    }

    public void setWindowSize(int windowSize) {
        this.windowSize = windowSize;
    }

    public windowCategory getWindowType() {
        return windowType;
    }

    public void setWindowType(windowCategory type) {
        this.windowType = type;
    }
    
    public OutputGenerator getOutHandle() {
        return outHandle;
    }

    public void setOutHandle(OutputValueGenerator outHandle) {
        this.outHandle = outHandle;
    }

    public String getDataRepo() {
        return dataRepo;
    }

    public void setDataRepo(String dataRepo) {
        this.dataRepo = dataRepo;
    }

    public DBHandler getDbhandler() {
        return dbhandler;
    }

    public void setDbhandler(DBHandler dbhandler) {
        this.dbhandler = dbhandler;
    }

    public int getWindowFilled() {
        return windowFilled;
    }

    public void setWindowFilled(int windowFilled) {
        this.windowFilled = windowFilled;
    }

    public Thread getQueryThread() {
        return queryThread;
    }

    public void setQueryThread(Thread queryThread) {
        this.queryThread = queryThread;
    }
   
}
