<%-- 
    Document   : index
    Created on : 21 Mar, 2013, 9:22:13 AM
    Author     : _
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><%@ include file="masterpage.html" %>
    <head>
     <script type="text/javascript" src="js/smoothie.js">
    </script><script type="text/javascript">
            var result1 ;
            
            function query1()
            {//query2();
                
                var smoothie = new SmoothieChart({grid:{fillStyle:'#dff6f4'},labels:{fillStyle:'#000000'},timestampFormatter:SmoothieChart.timeFormatter});
                smoothie.streamTo(document.getElementById("mycanvas"));            
                // Data
                var line1 = new TimeSeries();
                var line2 = new TimeSeries();

                // Add a random value to each line every second
                setInterval(function() { show1();
                  line1.append(new Date().getTime(), result1[0]);
                  line2.append(new Date().getTime(), result1[1]);
               //   document.getElementById('label1').innerHTML = result1;
                }, 1000);

                // Add to SmoothieChart
                smoothie.addTimeSeries(line1,{lineWidth:4,strokeStyle:'#171cdc'});
                smoothie.addTimeSeries(line2,{lineWidth:4,strokeStyle:'#ff0c00'});
                // Add a random value to each line every second    
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
               result1 = eval(AJAX.responseText );
               
              //alert('Success. Result: name => ' + json);
          }else if (AJAX.readyState == 4 && AJAX.status != 200) {
            alert('Something went wrong...');
          }
        }

        function show1(){
          AJAX.onreadystatechange = handler1();
          AJAX.open("GET",'streamstats.jsp');
          AJAX.send("");
        };      
 
</script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stream Data Management</title>
    </head>
    <body onload="query1()">
       	<section id="main" class="column">
		
		<h4 class="alert_info">Welcome to the Data Stream Management System</h4>
		
		<article class="module width_full">
			<header><h3>Stream Stats</h3></header>
			<div class="module_content">
				<article class="stats_graph">
				   <canvas id="mycanvas" width="900" height="200"></canvas>
				</article>
                            
				<div class="clear"></div>
                                <br>
                                Traffic Stream <hr size = 3 width=8% align="left" color="red">
                                Fire Department Stream <hr size = 3 width=8% align="left" color="blue">
			</div>
		</article><!-- end of stats article -->			
	</section>
    </body>
</html>
