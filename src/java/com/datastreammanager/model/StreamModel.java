/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.datastreammanager.model;

import java.util.ArrayList;

/*
 * @author PprrATeekK
 */
public class StreamModel {

    private String streamid;
    private String streamname;
    private String socketaddress;
    private String url;
    private String StreamDataType;
    private int slackness = 0;
    private String slacknessType;
    private long increment = 0;
    private String filterClause;
    private String argColumn;
    private SchemaModel schema;
    private ArrayList<ColumnPair> cols = new ArrayList<ColumnPair>();
     
    public String getFilterClause() {
        return filterClause;
    }

    public void setFilterClause(String filterClause) {
        this.filterClause = filterClause;
    }

    public long getIncrement() {
        return increment;
    }

    public void setIncrement(long increment) {
        this.increment = increment;
    }
    
    public ArrayList<ColumnPair> getSchema() {
        return schema.getSchema();
    }

    public void setSchema(SchemaModel schema) {
        this.schema = schema;
    }
    

    public String getArgColumn() {
        return argColumn;
    }

    public void setArgColumn(String argColumn) {
        this.argColumn = argColumn;
    }

    public String getDatatype() {
        return StreamDataType;
    }

    public void setDatatype(String datatype) {
        this.StreamDataType = datatype;
    }

    public int getSlackness() {
        return slackness;
    }

    public void setSlackness(int slackness) {
        this.slackness = slackness;
    }

    public String getSlacknessType() {
        return slacknessType;
    }

    public void setSlacknessType(String slacknessType) {
        this.slacknessType = slacknessType;
    }

    public String getSocketaddress() {
        return socketaddress;
    }

    public void setSocketaddress(String socketaddress) {
        this.socketaddress = socketaddress;
    }

    public String getStreamid() {
        return streamid;
    }

    public void setStreamid(String streamid) {
        this.streamid = streamid;
    }

    public String getStreamname() {
        return streamname;
    }

    public void setStreamname(String streamname) {
        this.streamname = streamname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    
    public  ArrayList<ColumnPair> generateListOfAttrib(){
    
         
//        ColumnPair cpStreamId = new ColumnPair();
//        cpStreamId.setValue(getStreamid());
//        cols.add(cpStreamId);
//        
        ColumnPair cpStreamName = new ColumnPair();
        cpStreamName.setValue(getStreamname());
        cols.add(cpStreamName);
        
        ColumnPair cpStreamDataType = new ColumnPair();
        cpStreamDataType.setValue(getDatatype());
        cols.add(cpStreamDataType);
        
        ColumnPair cpStreamSocketAdd = new ColumnPair();
        cpStreamSocketAdd.setValue(getSocketaddress());
        cols.add(cpStreamSocketAdd);
        
        ColumnPair cpStreamURL = new ColumnPair();
        cpStreamURL.setValue(getUrl());
        cols.add(cpStreamURL);
        
        ColumnPair cpStreamSlackness = new ColumnPair();
        cpStreamSlackness.setValue(""+getSlackness());
        cols.add(cpStreamSlackness);
        
        ColumnPair cpStreamSlacknessType = new ColumnPair();
        cpStreamSlacknessType.setValue(""+getSlacknessType());
        cols.add(cpStreamSlacknessType);
        
        ColumnPair cpStreamIncrement = new ColumnPair();
        cpStreamIncrement.setValue(""+getIncrement());
        cols.add(cpStreamIncrement);
        
        ColumnPair cpStreamArgument = new ColumnPair();
        cpStreamArgument.setValue(getArgColumn());
        cols.add(cpStreamArgument);
        
        ColumnPair cpStreamFilterClause = new ColumnPair();
        cpStreamFilterClause.setValue(getFilterClause());
        cols.add(cpStreamFilterClause);
        
        
        return cols;
    
    }
    
}
