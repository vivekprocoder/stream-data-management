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
            e.printStackTrace();
}
%>
<!DOCTYPE html>
<html><%@ include file="masterpage.html" %>
    <head>
   <link rel="stylesheet" href="css/hideshow.css" type="text/css"  /> <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/smoothie.js">
    </script><script type="text/javascript">
            var result1="retrieving..."; var result2="retrieving...";
            
            function query1()
            {//query2();
                
                var chart1 = new SmoothieChart({grid:{fillStyle:'#dff6f4'},labels:{fillStyle:'#000000'},timestampFormatter:SmoothieChart.timeFormatter}),
                canvas1 = document.getElementById('smoothie-chart1'),
                series1 = new TimeSeries();
                chart1.addTimeSeries(series1, {lineWidth:4,strokeStyle:'#171cdc'});
                chart1.streamTo(canvas1, 580);               
                // alert("TEST");
                // Add a random value to each line every second
                setInterval(function() {show1();
                  series1.append(new Date().getTime(), result1);
                  document.getElementById('label1').innerHTML = result1;
                }, 1000);               
               
            }              
            function query2()
            {
                //alert("test");
                var chart = new SmoothieChart({grid:{fillStyle:'#dff6f4'},labels:{fillStyle:'#000000'},timestampFormatter:SmoothieChart.timeFormatter}),
                canvas = document.getElementById('smoothie-chart2'),
                series = new TimeSeries();
                chart.addTimeSeries(series, {lineWidth:4,strokeStyle:'#171cdc'});
                chart.streamTo(canvas, 580);               

                // Add a random value to each line every second
                setInterval(function() {show2();
                  series.append(new Date().getTime(), result2);
                  document.getElementById('label2').innerHTML = result2;
                }, 1000); 
            }
        function createXMLHttpRequest1(){
          // See http://en.wikipedia.org/wiki/XMLHttpRequest
          // Provide the XMLHttpRequest class for IE 5.x-6.x:
          if( typeof XMLHttpRequest == "undefined" ) XMLHttpRequest = function() {
            try { return new ActiveXObject("Msxml2.XMLHTTP.6.0") } catch(e) {}
            try { return new ActiveXObject("Msxml2.XMLHTTP.3.0") } catch(e) {}
            try { return new ActiveXObject("Msxml2.XMLHTTP") } catch(e) {}
            try { return new ActiveXObject("Microsoft.XMLHTTP") } catch(e) {}
            throw new Error( "This browser does not support XMLHttpRequest." )
          };
          return new XMLHttpRequest();
        }

        var AJAX = createXMLHttpRequest1();

        function handler1() {
          if(AJAX.readyState == 4 && AJAX.status == 200) {
               result1 = AJAX.responseText ;
               
              //alert('Success. Result: name => ' + json);
          }else if (AJAX.readyState == 4 && AJAX.status != 200) {
            alert('Something went wrong...');
          }
        }

        function show1(){
          AJAX.onreadystatechange = handler1();
          AJAX.open("GET",'query1.jsp');
          AJAX.send("");
        };
        function createXMLHttpRequest2(){
          // See http://en.wikipedia.org/wiki/XMLHttpRequest
          // Provide the XMLHttpRequest class for IE 5.x-6.x:
          if( typeof XMLHttpRequest == "undefined" ) XMLHttpRequest = function() {
            try { return new ActiveXObject("Msxml2.XMLHTTP.6.0") } catch(e) {}
            try { return new ActiveXObject("Msxml2.XMLHTTP.3.0") } catch(e) {}
            try { return new ActiveXObject("Msxml2.XMLHTTP") } catch(e) {}
            try { return new ActiveXObject("Microsoft.XMLHTTP") } catch(e) {}
            throw new Error( "This browser does not support XMLHttpRequest." )
          };
          return new XMLHttpRequest();
        }

        var AJAX = createXMLHttpRequest2();

        function handler2() {
          if(AJAX.readyState == 4 && AJAX.status == 200) {
               result2 = AJAX.responseText ;
               
              //alert('Success. Result: name => ' + json);
          }else if (AJAX.readyState == 4 && AJAX.status != 200) {
            alert('Something went wrong...');
          }
        }

        function show2(){
          AJAX.onreadystatechange = handler2();
          AJAX.open("GET",'query2.jsp');
          AJAX.send("");
        };
    $(document).ready(function(){

            $(".slidingDiv1").hide();$(".slidingDiv2").hide();
            $(".show_hide").show();

        $('.show_hide').click(function(){
        $(".slidingDiv1").slideToggle();$(".slidingDiv2").slideToggle();
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
    				<th>Result</th> 
			        <th><a href="#" class="show_hide"><div style="width:100%; font-size:10pt; color:#0000FF; filter:Glow(color=#ff0000, strength=12)">Show/Hide Graph</div<</a></th>
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
                        <div><tr>	
                                    <td ><%=queryname%></td> 				
    				<td><label id="label<%=output%>"><%=queryout%></label> </td><td></td>
                                <tr><td colspan="3">
                                <div class="slidingDiv<%=output%>">
                                    Query = <%=queryStr%>
                                <br></br>
                                <canvas id="smoothie-chart<%=output%>" width="580" height="120"></canvas>
                                </div>
				</td></tr>   
                                
                                </tr><script type="text/javascript">query<%=output%>();</script>
				 <%} %></div>
			</tbody> 
			</table>
			</div><!-- end of #tab1 -->	
	</section>
    </body>
</html>
