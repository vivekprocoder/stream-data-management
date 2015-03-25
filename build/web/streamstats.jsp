 <%@page import="com.datastreammanager.Handler.DBHandler"%>
<%@page import="com.datastreammanager.model.TupleModel"%>
<%@page import="com.datastreammanager.utility.StreamManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.datastreammanager.model.QueryStatus"%>
<%@page contentType="text/html; charset=UTF-8"%>
  
<% 
    String rs="new Array (\"";
    StreamManager sm = new StreamManager();
    ArrayList<TupleModel> result = sm.getStreamNames();
    for(int i = 0; i < result.size(); i++){
        String str = result.get(i).getDataTuple().get(0).getValue();
        DBHandler dbhandler = new DBHandler();
        String count = dbhandler.executeQuery("SELECT count(*) FROM APP.STRM"+str, "");
        if(i < result.size() -1)
            rs = rs + count + "\",\"";
        else
            rs = rs + count + "\")";
        System.out.println(str+"\n"+count);
    }
    try{
        out.print(rs);
    }catch(Exception e){
        out.print("Unavailable");
    }
     out.flush();
  %>
