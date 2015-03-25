/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 *@author PprrATeekK
 */

package com.datastreammanager.model;

import java.util.ArrayList;


public class TupleQueueItem {

    ArrayList<TupleModel> queueItem;

    public TupleQueueItem( ArrayList<TupleModel> queueItem){
    this.queueItem =  queueItem;
    }
    
    public ArrayList<TupleModel> getQueueItem() {
        return queueItem;
    }

    public void setQueueItem(ArrayList<TupleModel> queueItem) {
        this.queueItem = queueItem;
    }
    
    
}
