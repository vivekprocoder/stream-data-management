/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 *@author PprrATeekK
 */
package com.datastreammanager.utility;

/*
 Monitors cache where data is accumulated, on time basis.
 Keeps track of how many historical data tuples are to be kept in DataTable
 * but in terms of time.
 * ex.: keep data for two days, remove data prior to timestamp t1.
 * 
 */
public class MonitorTimeWindow extends MonitorWindow {

    @Override
    public boolean monitor() {




        return false;
    }

    @Override
    public boolean triggerOutputGenerator() {

        if (query != null) {
            outHandle.generateTempOutput("strm" + dataRepo, query.getQuerystring());
        }
        return true;
    }

    @Override
    public boolean flushCache() {
        if (getWindowType() == windowCategory.Sliding) {
            dbhandler.emptyCache("delete from app." + dataRepo + " where S_ID=(select min(S_ID) from app." + dataRepo + ")");
            windowFilled--;
        } else if (getWindowType() == windowCategory.Cascade) {
            dbhandler.emptyCache("delete from app." + dataRepo + " where S_ID<(select max(S_ID) from app." + dataRepo + ")");
            windowFilled = 1;
        }
        return true;
    }

    @Override
    public void run() {

        while (true) {
            try {
                queryThread.sleep(windowSize);
           //     triggerOutputGenerator();
                flushCache();
            } catch (InterruptedException ex) {
            }
        }

    }
}
