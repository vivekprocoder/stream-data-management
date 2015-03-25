/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datastreammanager.Handler;

import com.datastreammanager.model.ColumnPair;
import com.datastreammanager.model.Constants;
import com.datastreammanager.model.StreamModel;
import com.datastreammanager.model.TupleModel;
import java.util.ArrayList;

/*
 * @author PprrATeekK
 */
public class ManageStream  {

    private DBHandler dbhandler = new DBHandler();
    
    
    public boolean registerStream(StreamModel strmMetadata){
    
        boolean status=false;
       
        TupleModel tuple = new TupleModel();
        ArrayList<TupleModel> tuples = new ArrayList<TupleModel>();
        
        tuple.setDataRow(strmMetadata.generateListOfAttrib());
        tuples.add(tuple);
        
        int streamId = dbhandler.insertRowsIntoTable(Constants.NameOfStreamMetadataRepo,tuples );
        createSchema(streamId,strmMetadata.getSchema());
        
        return status;  
    
    }
    
    
    public boolean createSchema(int streamId,ArrayList<ColumnPair> headers){
    
        boolean status=false;
        
        DbConn dbconn = new DbConn();
        
        dbconn.creatTable("strm"+streamId, headers);
        
        dbconn.closeConnection();
        
        return status;        
    }
    
    
    }


