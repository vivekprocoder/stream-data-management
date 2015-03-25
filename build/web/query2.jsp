  <%@page import="com.datastreammanager.model.QueryStatus"%>
<%@page contentType="text/html; charset=UTF-8"%>
  
  <%
    /*JSONObject obj=new JSONObject();
    obj.put("name","foo");
    obj.put("num",new Integer(100));
    obj.put("balance",new Double(1000.21));
    obj.put("is_vip",new Boolean(true));
    obj.put("nickname",null);*/
    try{
        out.print(QueryStatus.getStatus().get("2"));
    }catch(Exception e){
        out.print("Unavailable");
    }
    out.flush();
  %>