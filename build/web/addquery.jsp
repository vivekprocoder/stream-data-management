<%-- 
    Document   : addquery
    Created on : 21 Mar, 2013, 9:20:14 AM
    Author     : _
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.datastreammanager.utility.Addquery"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>	
<script language="JavaScript">
    function fillSelect(control) {
        var select = document.getElementById(control);//alert("hello");
        resetSelect(control);
        var arr = new Array();
    <%
            Addquery a = new Addquery();
            ArrayList<String> result = a.getstreams();
            for (int i = 0; i < result.size(); i++) {
    %>      arr.push("<%= result.get(i)%>");
    <%
            }
    %>//alert("hello");
        for (var i = 0; i < arr.length; i++) {
            var option = document.createElement("option");
            option.text = arr[i];
            select.add(option, null);
        }
    }
    function resetSelect(control) {
        var select = document.getElementById(control);//alert(select.options.length);
        var i;
        for (i = select.options.length - 1; i >= 0; i--)
        {
            select.remove(i);
        }
    }
    function addSelectedValues(control1, control2)
    {
        
        var control = document.getElementById(control2);
        control.disabled = false;
        var arr = new Array();
        arr = getSelectValues(control1);
        for (var i = 0; i < arr.length; i++) {
            var option = document.createElement("option");
            option.text = arr[i];
            control.add(option, null);
        }
    }
    function getSelectValues(control) {

        var selectedArray = new Array();
        var selObj = document.getElementById(control);
        var i;
        var count = 0;
        for (i = 0; i < selObj.options.length; i++) {
            if (selObj.options[i].selected) {
                selectedArray[count] = selObj.options[i].value;
                count++;
            }
        }
        return selectedArray;
    }
    function enableWindow(control)
    {
        var control = document.getElementById(control);
        control.disabled = false;
    }
    function updateStreamData(control1, control2)
    {
        resetSelect(control2);
        var control1 = document.getElementById(control1);
        var control2 = document.getElementById(control2);
        var control3 = document.getElementById("select6");
        var control4 = document.getElementById("select8");

        control2.disabled = false;
        control3.disabled = false;
        control4.disabled = false;
        var arr = new Array();
    <%
            Addquery b = new Addquery();
            ArrayList<String> result2 = b.getstreamsdata();
            for (int i = 1; i < result2.size(); i++) {
    %>
    arr.push("<%= result2.get(i)%>");
    <%
            }
    %>
         
        for (var x = 0; x < control1.length; x++)
        {
            if (control1[x].selected)
            {
                var v = control1[x].text;
                for (var i = 0; i < arr.length; i++) {
                    if (arr[i].indexOf(v) !== -1) {
                        var option = document.createElement("option");
                        option.text = arr[i];
                        control2.add(option, null);
                        var option1 = document.createElement("option");
                        option1.text = arr[i];
                        control3.add(option1, null);
                        var option2 = document.createElement("option");
                        option2.text = arr[i];
                        control4.add(option2, null);
                    }
                }
            }

        }

    }
</script>
<!DOCTYPE html>
<html><%@ include file="masterpage.html" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <section id="main" class="column">
            <article class="module width_full">
                <header><h3>Add New Query</h3></header>
                <div class="module_content">
                    <form id="form1" name="form1" action="Servletaddquery" method="post" >
                        <table border="0" cellpadding="10" cellspacing="10">
                            <tr>
                                <td>
                                    <font size="2.5" >Query Name</font>
                                </td>
                                <td>
                                    <input id="Text2" style="width: 150px" name="Text2" type="text" placeholder="Enter name of query" required/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <font size="2.5" required>Query Type</font>
                                </td>
                                <td>
                                    <select id="select3" onChange="enableWindow('select1');
        enableWindow('Text1');enableWindow('text01');
        fillSelect('select1')" style="width: 150px" name="select3">
                                        <option value=""></option>
                                        <option value="Continuous">Continuous</option>
                                        <option value="Adhoc">Adhoc</option>
                                    </select>
                                </td>                                   
                                <td>
                                </td>                                              
                            </tr>
                            <tr>
                                <td>
                                    <font size="2.5" >Window Range</font>
                                </td>
                                <td>
                                    <input id="Text1"  title="No of tuples on which query will operate" style="width: 150px" name="Text1" type="text" placeholder="No of tuples" required/>
                                </td>
                                <td>
                                    <font size="2.5" >Interval</font>
                                </td>
                                <td>
                                    <input id="text01"  style="width: 150px" title="Time interval between query execution" name="text01" type="text" placeholder="Enter in Seconds" required/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <font size="2.5" >Choose Streams</font>
                                </td>
                                <td>
                                    <select id="select1" disabled="disabled" name="select1" onChange="updateStreamData('select1', 'select2')" style="width: 150px" size="3" multiple="multiple" tabindex="5" required>
                                    </select>
                                </td>                                   
                                <td>
                                </td> 
                            </tr>
                            <tr>
                                <td>
                                    <font size="2.5" >Select Stream Data</font>
                                </td>
                                <td>
                                    <select id="select2" disabled="disabled" onChange="enableWindow('Text1');
        enableWindow('select4');
        enableWindow('select4');
        enableWindow('displayValue1');
        enableWindow('idValue1')" name="select2" style="width: 150px"  size="3" multiple="multiple" tabindex="5">
                                    </select>
                                </td>                                   
                                <td>
                                </td> 
                            </tr>
                            <tr>
                                <td>
                                    <font size="2.5" >Apply Aggregation</font>
                                </td>
                                <td>
                                    <select id="select4" disabled="disabled" onChange="addSelectedValues('select2', 'select5')" name="select4" style="width: 150px"  size="1" tabindex="1">
                                        <option value=""></option>
                                        <option value="AVG">AVG</option>
                                        <option value="SUM">SUM</option>
                                        <option value="MAX">MAX</option>
                                        <option value="MIN">MIN</option>
                                    </select>
                                </td>
                                <td>
                                    <select id="select5" disabled="disabled" name="select5" style="width: 150px"  size="1" tabindex="1">
                                    </select>
                                </td> 
                            </tr>
                            <td>
                                <font size="2.5" >Where</font>
                            </td>                                                
                            <td>
                                <div style="position:relative;width:150px;height:25px;border:0;padding:0;margin:0;">
                                    <select id ="select6" name="select6" style="position:absolute;top:3px;left:0px;width:150px; height:20px;line-height:20px;margin:0;padding:0;" onchange="enableWindow('select7');
        document.getElementById('displayValue1').value = this.options[this.selectedIndex].text;
        document.getElementById('idValue1').value = this.options[this.selectedIndex].value;">
                                        <option></option>
                                    </select>
                                    <input disabled="disabled" type="text" name="displayValue1" placeholder="add/select a value" id="displayValue1" style="position:absolute;top:3px;left:0px;width:130px;width:150px\9;#width:180px;height:20px; height:21px\9;#height:18px;border:1px solid #556;" onfocus="this.select()" onclick="enableWindow('select7')">
                                    <input disabled="disabled" type="hidden" name="idValue1" id="idValue1">
                                </div>
                            </td>                                              
                            <td>                                               
                                <select id="select7" name = "select7" onchange="enableWindow('select8');
        enableWindow('displayValue');
        enableWindow('idValue')" disabled="disabled" name="select7" style="width: 150px"  size="1" tabindex="1">
                                    <option value=""></option>
                                    <option value="=">=</option>
                                    <option value="<>"><></option>
                                    <option value="<"><</option>
                                    <option value=">">></option>
                                    <option value=">=">>=</option>
                                    <option value="<="><=</option>
                                    <option value="LIKE">LIKE</option>
                                    <option value=">">></option> 
                                </select>
                            </td>
                            <td>
                                <div style="position:relative;width:150px;height:25px;border:0;padding:0;margin:0;">
                                    <select id ="select8" name="select8" style="position:absolute;top:3px;left:0px;width:150px; height:20px;line-height:20px;margin:0;padding:0;" onchange="document.getElementById('displayValue').value = this.options[this.selectedIndex].text;
        document.getElementById('idValue').value = this.options[this.selectedIndex].value;">
                                        <option></option>
                                    </select>
                                    <input disabled="disabled" type="text" name="displayValue" placeholder="add/select a value" id="displayValue" style="position:absolute;top:3px;left:0px;width:130px;width:150px\9;#width:180px;height:20px; height:21px\9;#height:18px;border:1px solid #556;" onfocus="this.select()">
                                    <input disabled="disabled" type="hidden" name="idValue" id="idValue">
                                </div>
                            </td>                                              
                            <td>
                                <input type="button" value="+"  title="Add Column" onclick="addRow('columns');"/>
                                <input type="button" value="-"  title="Delete Column" onclick="deleteRow('columns');"/>
                            </td>                                            
                            </tr>
                            <tr>
                                <td>                                                
                                </td>
                                <td>
                                    <input type="submit" name="Submit" value="Submit" tabindex="2" /><input type="reset" onclick="document.getElementById('form1').reset();
        resetSelect('select2');
        resetSelect('select5');
        resetSelect('select6');
        ;
        resetSelect('select8')" name="Reset" value="Reset" tabindex="2" />
                                </td>                                   
                                <td>
                                </td>                                                                                            
                            </tr>
                        </table>
                    </form>         				
                </div>
            </article><!-- end of styles article -->
        </section>
    </body>
</html>