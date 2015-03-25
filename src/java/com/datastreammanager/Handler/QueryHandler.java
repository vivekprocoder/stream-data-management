/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 *@author PprrATeekK
 */

package com.datastreammanager.Handler;

import com.datastreammanager.model.ColumnPair;
import com.datastreammanager.model.QueryModel;
import com.datastreammanager.model.QueryStatus;
import com.datastreammanager.model.StreamStatus;
import java.util.ArrayList;


public class QueryHandler {

    
    private boolean status=true;
    private QueryModel query;
    DBHandler dbhandler = new DBHandler();
            
            
    public QueryHandler(ArrayList<ColumnPair> queryMetadata){
    
        query = new QueryModel(queryMetadata);
        
    }

   
    
    public void startExecutingQuery(){
    
        if(StreamStatus.checkStream(query.getStreamId()))
        QueryStatus.add(query.getQueryid());
        else
        return;
        
            while(status){
            
            String result = dbhandler.executeQuery(query.getQuerystring(), "");
            
            QueryStatus.getStatus().put(query.getQueryid(), result);
           
            
            try {
                Thread.sleep(query.getInterval()*1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
    }
    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
