<%-- 
    Document   : streammanager
    Created on : 21 Mar, 2013, 9:29:19 AM
    Author     : _
--%>

<%@page import="com.datastreammanager.model.TupleModel"%>
<%@page import="com.datastreammanager.model.StreamStatus"%>
<%@page import="com.datastreammanager.controller.StreamMonitor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.datastreammanager.utility.StreamManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
        StreamManager sm = new StreamManager();
        ArrayList<TupleModel> result = sm.getStreamNames();
        ArrayList<String> streamstatus = new ArrayList<String>();
        try{
            streamstatus = StreamStatus.getStatus();        
        }
        catch(Exception e){
        
        }        
%>
<!DOCTYPE html>``
<html><%@ include file="masterpage.html" %>
    <head><script language="JavaScript"> 
   function start(control){
        var control = document.getElementById(control);var test = control.id;
       // alert(test);   
        document.location.href="/StreamDataManager/ServletStreamMonitor?param1="+test+"&param2=start";       
   }
    function stop(control){
        var control = document.getElementById(control);var test = control.id;
       // alert(control.id);
        document.location.href="/StreamDataManager/ServletStreamMonitor?param1="+test+"&param2=stop";  
   }
</script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stream Manager</title>
    </head>
    <body>
       <section id="main" class="column">
		
		<h4 class="alert_info">Stream Management</h4>
		
		<article class="module width_3_quarter">
		<header><h3 class="tabs_involved">Streams</h3>
		</header>

		<div class="tab_container">
			<div id="tab1" class="tab_content">
			<table class="tablesorter" cellspacing="0"> 
			<thead> 
				
    				<th>Stream Name</th> 
    				<th>Status</th>
				<th>Action</th> 
				</tr> 
			</thead> 
			<tbody> 
				<% for(int i = 0; i < result.size(); i++){  
				  String streamname = result.get(i).getDataTuple().get(2).getValue(); 
				  String streamid = result.get(i).getDataTuple().get(0).getValue(); %>
				<tr>   					
    				<td><%=streamname%></td>   					
    				<% String st="stop";   					
    				if(streamstatus != null)if(streamstatus.contains(streamid))					
                                    st="running";   					
    				%>
    				<td><%=st%></td> 
    				<td><input type="button" id="<%=streamid%>" onClick="start('<%=streamid%>')" value="Start" title="Start"><input type="button" id="<%=streamid%>" onClick="stop('<%=streamid%>')" value="Stop" title="Start"></td> 
				</tr> 
				 <%} %>
			</tbody> 
			</table>
			</div><!-- end of #tab1 -->	
	</section>
    </body>
</html>
