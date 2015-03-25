<%-- 
    Document   : continuousquery
    Created on : 21 Mar, 2013, 9:30:48 AM
    Author     : _
--%>

<%@page import="com.datastreammanager.model.TupleModel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.datastreammanager.utility.QueryManager"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.datastreammanager.model.QueryStatus"%>
<%@page import="java.util.HashMap"%>
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
   <link rel="stylesheet" href="css/hideshow.css" type="text/css"  /> <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.js" type="text/javascript"></script>
    <script type="text/javascript">

    $(document).ready(function(){

            $(".slidingDiv").hide();
            $(".show_hide").show();

        $('.show_hide').click(function(){
        $(".slidingDiv").slideToggle();
        });

    });
 
</script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <section id="main" class="column">
		
		<h4 class="alert_info">Running Query Result</h4>
		
		<article class="module width_3_quarter">
		<header><h3 class="tabs_involved">Query</h3>
		</header>

		<div class="tab_container">
			<div id="tab1" class="tab_content">
			<table class="tablesorter" cellspacing="0"> 
			<thead> 
				
    				<th>Query Name</th> 
    				<th><a href="#" class="show_hide">Show/hide</a></th> 
			
			</thead> 
			<tbody> 
				<%for (Iterator it = querystatus.keySet().iterator(); it.hasNext();) {                                
				String output = it.next().toString(); 
                                String queryname=""; String queryStr="";
                                for(int i=0;i<result.size();i++){
                                    if(result.get(i).getDataTuple().get(0).getValue().equalsIgnoreCase(output)){
                                       queryname = result.get(i).getDataTuple().get(2).getValue();
                                       queryStr =  result.get(i).getDataTuple().get(3).getValue();
                                       break;
                                    }
                                }
				
				String queryout = querystatus.get(output); %>
				<tr>   					
    				<td ><%=queryname%></td>    				
    				<td></td> </td>
                                <tr><td colspan="3">
                                <div class="slidingDiv">
                                    Query = <%=queryStr%> &nbsp;&nbsp;&nbsp; Result = <%=queryout%> </div>
				</td></tr>                          
                                </tr>
				 <%} %>
			</tbody> 
			</table>
			</div><!-- end of #tab1 -->	
	</section>
    </body>
</html>
