/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.datastreammanager.model;

import java.util.ArrayList;

/*
 *@author PprrATeekK
 */
public class TupleModel {

    ArrayList<ColumnPair> dataTuple= new ArrayList<ColumnPair>();

    public ArrayList<ColumnPair> getDataTuple() {
        return dataTuple;
    }

    public void setDataRow(ArrayList<ColumnPair> dataTuple) {
        this.dataTuple = dataTuple;
    }
    
    
}
