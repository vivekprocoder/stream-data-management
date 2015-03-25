


function addRow(tableID) {
    var table = document.getElementById(tableID);

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);

    var colCount = table.rows[0].cells.length;

     
    for (var i = 0; i < colCount; i++) {

       var newcell = row.insertCell(i);

        newcell.innerHTML = table.rows[0].cells[i].innerHTML;
        
         alert("Type:"+newcell.childNodes[0].type.value);
        switch (newcell.childNodes[0].type.value) {
            case "text":
                newcell.childNodes[0].value = "";
                break;
            case "checkbox":
                newcell.childNodes[0].checked = true;
                break;
            case "select-one":
                newcell.childNodes[0].selectedIndex = 0;
                break;
        }
         newcell.childNodes[0].name = "ColumnId"+rowCount;
    }
    alert("Type:"+newcell.childNodes[0].type+" tableID:" +tableID+",no. of rows:"+rowCount+",no. of cols:"+colCount+" rowID:"+ newcell.childNodes[0].name);
}

function deleteRow(tableID) {
    try {
        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;

        for (var i = 0; i < rowCount; i++) {
            var row = table.rows[i];
            var chkbox = row.cells[0].childNodes[0];
            if (null != chkbox && true == chkbox.checked) {
                if (rowCount <= 1) {
                    alert("Cannot delete all the rows.");
                    break;
                }
                table.deleteRow(i);
                rowCount--;
                i--;
            }


        }
    } catch (e) {
        alert(e);
    }
}

