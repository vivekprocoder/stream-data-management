/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datastreammanager.Handler;

import com.datastreammanager.model.ColumnPair;
import com.datastreammanager.model.Constants;
import com.datastreammanager.model.QueryModel;
import com.datastreammanager.model.TupleModel;
import com.datastreammanager.model.TupleQueueItem;
import com.datastreammanager.utility.CSVParser;
import com.datastreammanager.utility.HTTPConnection;
import com.datastreammanager.utility.JsonParser;
import com.datastreammanager.utility.MonitorTimeWindow;
import com.datastreammanager.utility.MonitorTupleWindow;
import com.datastreammanager.utility.MonitorWindow;
import com.datastreammanager.utility.Parser;
import com.datastreammanager.utility.RSSParser;
import com.datastreammanager.utility.XMLParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/*
 * @author PprrATeekK
 */


public class StreamHandler implements Runnable{

    private InputStream inStrm;
    private BufferedReader reader;
    private boolean status = true;
    private Parser parser;
    private ArrayList<MonitorWindow> windowMonitors = new ArrayList<MonitorWindow>();
    private MonitorWindow windowMonitor;
    private ArrayList<TupleModel> tuples;
    private DBHandler dbhandler = new DBHandler();
    private String dataTableId;
    private String dataTableName;
    private String dataType;
    private String argument;
    private String filterClause;
    private long increment;
    private String URL;
    private String socketAddress;
    private String method;//streamMetadata.get(3).getValue();
    private int slackness = 0;
    private String slacknessType;
    private int interval = 10;
    private ArrayList<TupleModel> dataMatrix;
    private ArrayList<TupleModel> dataTuples;
    //private String[] queries=null;
    private ArrayList<QueryModel> queries = new ArrayList<QueryModel>();
    private LinkedBlockingQueue<TupleQueueItem> dataQueue = new LinkedBlockingQueue<TupleQueueItem>();
    private Thread th = new Thread(this, "TupleHandler");
    public void initVars(ArrayList<ColumnPair> streamMetadata){
    
        dataTableId = streamMetadata.get(0).getValue();
        dataTableName = streamMetadata.get(2).getValue();
        dataType = streamMetadata.get(3).getValue();
        socketAddress = streamMetadata.get(4).getValue();
        if(socketAddress.equalsIgnoreCase(":"))
        URL = streamMetadata.get(5).getValue();
        else
            URL=socketAddress;
        try {
            slackness = Integer.parseInt(streamMetadata.get(6).getValue().trim());
        } catch (NumberFormatException nfex) {
            nfex.printStackTrace();
        }
        slacknessType = streamMetadata.get(7).getValue();
        increment = Long.parseLong(streamMetadata.get(8).getValue());
        argument = streamMetadata.get(9).getValue();
        filterClause = streamMetadata.get(10).getValue();
        
        
        dataMatrix = dbhandler.fetchRowsFromTable(Constants.NameOfQueryInfoRepo,"streamid="+dataTableId);
        
        
        if(dataMatrix!=null)
        for(TupleModel queryTuple : dataMatrix){
        queries.add(new QueryModel(queryTuple.getDataTuple()));
        }
//        System.out.println("Query : "+queries.get(0).getQuerystring());
        
        if (filterClause != null && increment != 0) {
            URL += "?$" + filterClause + "=";
        }
        if (argument != null && increment != 0) {
            URL += argument + "=";
        }

        method = "POST";//streamMetadata.get(3).getValue();
        


        if (dataType.equalsIgnoreCase("CSV")) {
            parser = new CSVParser();
        } else if (dataType.equalsIgnoreCase("JSON")) {
            parser = new JsonParser();
        } else if (dataType.equalsIgnoreCase("RSS")) {
            parser = new RSSParser();
        } else {
            parser = new XMLParser();
        }

        for(QueryModel query : queries){
        if (slacknessType.equalsIgnoreCase("Time"))
            windowMonitor=new MonitorTimeWindow();
        else
            windowMonitor=new MonitorTupleWindow();
        
        windowMonitor.init("strm"+dataTableId,slackness, MonitorWindow.windowCategory.Cascade);
        windowMonitors.add(windowMonitor);
        
        windowMonitor.getQueryThread().start();
        
        }
        th.start();
    }
    
    
    public void startFetchingStream(ArrayList<ColumnPair> streamMetadata) {

        
        initVars(streamMetadata);
        int dQSize;
       
        while (status) {
            try {

                if (increment != 0) {
                    inStrm = HTTPConnection.sendHttpRequest(URL + "'" + increment + "'", method, null);
                } else {
                    inStrm = HTTPConnection.sendHttpRequest(URL, method, null);
                }

                if (inStrm == null) {
                    Thread.sleep(interval * 1000);
                    continue;
                }
                
                if(dataType.equalsIgnoreCase("XML"))
                    dataTuples = parser.parse(inStrm);
                else{
                reader = new BufferedReader(new InputStreamReader(inStrm));

                String line = "";
                String data = "";

                while ((line = reader.readLine()) != null) {
                    data += line;
                }
                    dataTuples = parser.parse(data);
                }
                
                synchronized(dataQueue){
                dataQueue.add(new TupleQueueItem(dataTuples));
                dQSize = dataQueue.size();
                }
                
                if(dQSize==1){
                    synchronized(this){
                    this.notify();
                    }
            //        System.out.println("data added and notified");
                }
            
          //  Thread.sleep(interval * 1000);

            incr(1);
            
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }
    }
    
    
    public void handleTuples(){
        
       // tuples = dataQueue.poll().getQueueItem();
        
        if (tuples == null) {
                  return;
                }
                if ( tuples.size() > 0) {
                          
                            int count = dbhandler.insertRowsIntoTable("strm"+dataTableId, tuples);
                            for(MonitorWindow monitor : windowMonitors){
                            monitor.updateWindowFilled(count);
                            }
                            
                   // System.out.println("WindowSize is="+slackness+" and rows in Table ="+windowFilled);
                  
                } else {
                    System.out.println("Tuple size is 0 for " + URL);
                }

        
    }
    

    public void incr(long value) {

        if (dataTableName.equals("FireDept")) {
            increment+=value;
  //          System.out.println("Incremented value:" + increment);
            dbhandler.updateIncrement(Constants.NameOfStreamMetadataRepo, "increment", increment, "STREAMID", dataTableId);
        }
    }

    @Override
    public void run() {
        
     
            while (status) {
                while (dataQueue.size() > 0) {
                    synchronized (dataQueue) {
                    tuples = dataQueue.poll().getQueueItem();
                    }
                    handleTuples();
                }
                try {
              //      System.out.println("Waiting for data");
                     synchronized (this) {
                     this.wait();
                     }
              //      System.out.println("running");
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
