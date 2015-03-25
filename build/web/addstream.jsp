<%-- 
    Document   : addstream
    Created on : 21 Mar, 2013, 9:28:33 AM
    Author     : _
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="js/schemaTable.js"></script>
<!DOCTYPE html>
<html><%@ include file="masterpage.html" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>

        <title>Register Stream</title>


        <script type="text/javascript">
            var counter = 1;
            $(document).ready(function() {
                
                 $("#addColumn").click(function() {

                    var newColumnDiv = $(document.createElement('div')).attr("id", 'colMeta' + counter);
                    newColumnDiv.after().html('<tr >' + '<td>' +
                            '<input type="checkbox" id="chkbox'+counter+'" name="chkbox'+counter+'" tabindex="-1" title="Select to delete this column"/>' + '</td>'
                            + '<td>' + '<input type="text" name="colName'+counter+'" placeholder="Column Name" title="Column Name" required/>' + '</td>'
                            + '<td>' + '<select name="colDataType'+counter+'" title="Data Type" required>'
                            + '<option value="varchar">String</option>'
                            + '<option value="Numeric">Number</option>'
                            + '<option value="Integer">Integer</option>'
                            + '<option value="BIGINT">Big Integer</option>'
                            + '<option value="Double">Decimal</option>'
                            + '<option value="Date">Date</option>'
                            + '<option value="Time">Time</option>'
                            + '<option value="Timestamp">Timestamp</option>'
                            + '</select>'
                            + '</td>'
                            + '<td>' + '<input type="text" name="size'+counter+'"  placeholder="Size" title="Size" />'
                            + '</td>'
                            + '</tr>');
                    newColumnDiv.appendTo("#row");
                    counter++;
                });
                
                
                
                
                $("#delColumn").click(function() {
                   if (counter == 1) {
                        alert("No more attributes to remove");
                        return false;
                    }
                  
                    for (var i = 0; i < counter; i++) {
                        if ($('#chkbox'+i).is(':checked')){
                            $('div').remove('#colMeta'+i);
                        }
                    }

                });
                
                $('#form1').submit(function() {
            $('#counter').val(counter);
            return true;
        }); 
            });
            
             $(document).onload(function() {
                
                if($('#saveStatus').val()=='saved')
                alert("Stream Saved !");
        });
            $(document).reload(function() {
                
                if($('#saveStatus').val()=='saved')
                alert("Stream Saved !");
        });
            
            
        </script>


       


    </head>
    <body>
        <section id="main" class="column">
            <article class="module width_full">
                <header><center><h3>Add Schema</h3></center></header>
                <div class="module_content">
                    <center>			
                        <form id="form1" action="ServletRegisterStream" method="post" >
                            <table id="metadata" border="0" >
                                <tr>
                                    <td><h3>Stream Name</h3></td> 
                                    <td colspan="2"><input name="StreamName" id="StreamName" type="text" value="" placeholder="Enter Name of Stream" required></td>
                                </tr>
                                <tr>
                                    <td><h3>Socket Address</h3></td> <td><input type="text" name="SocketAddress" id="SocketAddress" value="" placeholder="IPaddress" pattern="((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)"></td> <td><input type="number" name="port" id="port" value=""  placeholder="Port"></td>
                                </tr>
                                <tr>
                                    <td><h3>URL</h3></td><td colspan="2"><input type="url" name="URL" id="URL" value="" size="47" placeholder="URL">
                                </tr>
                                <tr>
                                    <td><h3>Stream Type</h3></td>
                                    <td colspan="2"><select id="StreamType" name="StreamType"  >
                                            <option value="CSV">CSV</option>
                                            <option value="XML">XML</option>
                                            <option value="JSON">JSON</option>
                                            <option value="RSS">RSS</option>
                                        </select></td>
                                </tr>
                                <tr>
                                    <td><h3>Slackness</h3></td> <td><h3>Slackness Type</h3></td>                                    
                                </tr>
                                <tr>
                                    <td><input type="number" id ="slackness" name="slackness" title="Remove historic data after.." value="" placeholder="Slackness" required></td>
                                    <td><select name="slacknesstype" title="..after specified time or tuples">
                                                <option value="Tuple">Tuple</option>
                                                <option value="Time">Time</option>
                                        </select>
                                    </td>
                                </tr>

                                <tr>
                                    <td><h3>Starting Value</h3></td> <td><h3>of ?</h3></td>
                                </tr>
                                <tr>
                                    <td><input type="number" id="startingvalue" name="startingvalue" value="" placeholder="Starting value" ></td> <td><input type="text" id="columnname" name="columnname" value="" placeholder="Column Name"></td> 
                                </tr>

                                <tr>
                                    <td>
                                        <h3>Schema</h3>
                                    </td>
                                    <td>
                                        <input type="button" value="+" id="addColumn" title="Add Column" />
                                        <input type="button" value="-" id="delColumn" title="Delete Column" />

                                    </td>
                                </tr>     

                            </table>

                            <table id="schemaDetails" border="0" >  
                                <tbody >
                                <div id='colMeta0'>
                                    <tr id='row'>
                                        <td>
                                            <input type="checkbox" id="chkbox0" name="chkbox0" tabindex="-1" title="Select to delete this column"/>
                                        </td>
                                        <td>
                                            <input type="text" name="colName0"  placeholder="Column Name" title="Column Name" required/>
                                        </td> 
                                        <td>
                                            <select name="colDataType0" title="Data Type">
                                                <option value="varchar">String</option>
                                                <option value="Numeric">Number</option>
                                                <option value="Integer">Integer</option>
                                                <option value="BIGINT">Big Integer</option>
                                                <option value="Double">Decimal</option>
                                                <option value="Date">Date</option>
                                                <option value="Time">Time</option>
                                                <option value="Timestamp">Timestamp</option>
                                            </select>
                                        </td>
                                        <td>
                                            <input type="text" name="size0"  placeholder="Size" title="Size" />
                                        </td>
                                    </tr>
                             </div>
                            </tbody>
                            </table>
                            
                            <input  type="hidden" name="counter" id="counter">
                            <input  type="hidden" name="saveStatus" value='saved' id="saveStatus">                            
                            
                            <center><input type="submit" value="Save"  title="Save Stream"/></center>
                        </form></center>
                </div>
            </article><!-- end of styles article -->

        </section>
    </body>
</html>
