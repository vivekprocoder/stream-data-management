/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datastreammanager.model;

/*
 * @author PprrATeekK
 */
public class ColumnPair {
    
   private String columnName;
   private String columnDatatype;
   private String value;
   private String size = "";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
   
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnDatatype() {
        return columnDatatype;
    }

    public void setColumnDatatype(String columnDatatype) {
        this.columnDatatype = columnDatatype;
    }
    
    
}
