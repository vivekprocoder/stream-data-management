<%-- 
    Document   : index
    Created on : Feb 6, 2013, 6:22:00 PM
    Author     : PprrATeekK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Home Page</title>
    </head>
    <body><center>
        <h1>Configure Stream</h1>
        <form action="RegisterStream" method="post" >
            <table border="0">
                <tr>
                    <td><h3>Stream Name</h3></td> <td colspan="2"><input name="StreamName" type="text" value="" placeholder="Enter Name of Stream"></td>
                </tr>
                <tr>
                    <td><h3>Socket Address</h3></td> <td><input type="text" name="SocketAddress" value="" placeholder="IPaddress"></td> <td><input type="text" value="" placeholder="Port"></td>
                </tr>
                <tr>
                    <td><h3>Stream Type</h3></td>
                    <td colspan="2"><select name="StreamType" >
                            <option value="string">String</option>
                            <option value="bitstream">Bitstream</option>
                        </select></td>
                </tr>
                <tr>
                    <td>
                        <h3>Schema</h3>
                    </td>

                    <td>
                        
                    </td>
                </tr>    
                <tr><td></td>
                    <td> <input name="columnName1" type="text" value="" placeholder="Column Name" title="Column Name"/></td> 
                    <td><select title="Data Type">
                            <option value="String">String</option>
                            <option value="Number">Number</option>
                        </select>
                    </td>
                </tr>
                <tr><td></td>
                    <td> <input name="columnName2" type="text" value="" placeholder="Column Name" title="Column Name"/></td> 
                    <td><select title="Data Type">
                            <option value="String">String</option>
                            <option value="Number">Number</option>
                        </select>
                    </td>
                </tr>
                <tr><td></td>
                    <td> <input name="columnName3" type="text" value="" placeholder="Column Name"  title="Column Name"/></td> 
                    <td><select title="Data Type">
                            <option value="String">String</option>
                            <option value="Number">Number</option>
                        </select>
                    </td>
                </tr>
                <tr><td colspan="3"><center><input type="submit" value="Save"  title="Save Stream"/></center></td></tr>
            </table>
        </form>
    </center>
</body>
</html>
