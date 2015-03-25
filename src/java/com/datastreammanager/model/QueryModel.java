/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datastreammanager.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author _
 */
public class QueryModel {
    private String queryid;
    private String querystring;
    private String outputType;
    private String queryName;
    private String streamId;
    private int windowSize; //No. of tuples over which query is to be fired
    private int interval;   // Interval (time/tuples) after which query is to be fired
    private Date dateCreated;
    private boolean status=true;

    public enum OutputTypes{Graph, Value, Doc};
    
    public QueryModel(ArrayList<ColumnPair> queryMetadata){
    
        init(queryMetadata);
       
    }
    
    private void init(ArrayList<ColumnPair> queryMetadata){
    
         setQueryid(queryMetadata.get(0).getValue());
  //      setDateCreated(DateFormat.getInstance().parse(queryMetadata.get(1).getValue()));
        setQueryName(queryMetadata.get(2).getValue());
        setQuerystring(queryMetadata.get(3).getValue());
        setOutputType(queryMetadata.get(4).getValue());
        setWindowSize(Integer.parseInt(queryMetadata.get(5).getValue()));
        setInterval(Integer.parseInt(queryMetadata.get(6).getValue()));
        setStreamId(queryMetadata.get(7).getValue());
    
    }
    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
    
    public String getOutputType() {
        return outputType;
    }

    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    public String getQueryid() {
        return queryid;
    }

    public void setQueryid(String queryid) {
        this.queryid = queryid;
    }

    public String getQuerystring() {
        return querystring;
    }

    public void setQuerystring(String querystring) {
        this.querystring = querystring;
    }
    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getWindowSize() {
        return windowSize;
    }

    public void setWindowSize(int windowSize) {
        this.windowSize = windowSize;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    public static void main(String[] args) {
        
        System.out.println( QueryModel.OutputTypes.Value.name());
       System.out.println( QueryModel.OutputTypes.Value.toString());
       
    }
}
