<%-- 
    Document   : querymanager
    Created on : 21 Mar, 2013, 9:29:32 AM
    Author     : _
--%>

<%@page import="com.datastreammanager.model.TupleModel"%>
<%@page import="com.datastreammanager.model.QueryStatus"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.datastreammanager.utility.QueryManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
        QueryManager qm = new QueryManager();
        ArrayList<TupleModel> result = qm.getquerynames();
        HashMap<String, String> querystatus = new HashMap<String, String>();
        try{
             querystatus = QueryStatus.getStatus();           
        }
        catch(Exception e){
}
%>

<!DOCTYPE html>
<html><%@ include file="masterpage.html" %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript"> 
   function start(control){
        var control = document.getElementById(control);var test = control.id;
       // alert(test);   
        document.location.href="/StreamDataManager/ServletQueryMonitor?param1="+test+"&param2=start";       
   }
   function stop(control){
        var control = document.getElementById(control);var test = control.id;
       // alert(control.id);
        document.location.href="/StreamDataManager/ServletQueryMonitor?param1="+test+"&param2=stop";  
   }
</script>
        <title>Stream Manager</title>
    </head>
    <body>
       <section id="main" class="column">
		
		<h4 class="alert_info">Query Management</h4>
		
		<article class="module width_3_quarter">
		<header><h3 class="tabs_involved">Query</h3>
		</header>

		<div class="tab_container">
			<div id="tab1" class="tab_content">
			<table class="tablesorter" cellspacing="0"> 
			<thead> 
				
    				<th>Query Name</th>     				
    				<th>Status</th>
				<th>Action</th> 
				</tr> 
			</thead> 
			<tbody> 
   				<% for(int i = 0; i < result.size(); i++){ 
				String queryname = result.get(i).getDataTuple().get(2).getValue(); 
				String queryid = result.get(i).getDataTuple().get(0).getValue(); %>
				<tr>   					
    				<td><%=queryname%></td> 
    				<% String st="stop";   					
    				if(querystatus != null)if(querystatus.containsKey(queryid))					
                                    st="running";   					
    				%>
    				<td><%=st%></td> 
    				<td><input type="button" id="<%=queryid%>" onClick="start('<%=queryid%>')" value="Start" title="Start"><input type="button" id="<%=queryid%>" onClick="stop('<%=queryid%>')" value="Stop" title="Start"></td> 
				</tr> 
				 <%} %>
			</tbody> 
			</table>
			</div><!-- end of #tab1 -->	
	</section>
    </body>
</html>