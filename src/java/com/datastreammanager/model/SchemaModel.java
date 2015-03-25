/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datastreammanager.model;

import java.util.ArrayList;

/**
 *
 * @author PprrATeekK
 */
public class SchemaModel {
    
    private ArrayList<ColumnPair> schema=new ArrayList<ColumnPair>();

    public ArrayList<ColumnPair> getSchema() {
        return schema;
    }

    public void setSchema(ArrayList<ColumnPair> schema) {
        this.schema = schema;
    }
    
}
