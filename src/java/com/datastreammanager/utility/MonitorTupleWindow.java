/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 *@author PprrATeekK
 */
package com.datastreammanager.utility;

public class MonitorTupleWindow extends MonitorWindow {

    @Override
    public boolean monitor() {

        if (windowFilled >= windowSize) {
            System.out.println("WindowFull WindowSize" + windowSize + " and data in Table " + windowFilled);

       //    triggerOutputGenerator();
            flushCache();
            return true;
        }

        return false;
    }

    @Override
    public boolean triggerOutputGenerator() {

        if (query != null) {
            System.out.println(query.getQuerystring());
            outHandle.generateTempOutput("strm" + dataRepo, query.getQuerystring());
        }
        else
        System.out.println("Query is null");
        return true;
    }

    @Override
    public boolean flushCache() {
        if (getWindowType() == windowCategory.Sliding) {
            dbhandler.emptyCache("delete from app." + dataRepo + " where S_ID=(select min(S_ID) from app." + dataRepo + ")");
            windowFilled--;
        } else if (getWindowType() == windowCategory.Cascade) {
            dbhandler.emptyCache("delete from app." + dataRepo + " where S_ID<(select max(S_ID) from app." + dataRepo + ")");
            windowFilled = Integer.parseInt(dbhandler.executeQuery("select count(*) from app."+dataRepo, ""));
            System.out.println("tuples deleted");
        }
        return true;
    }

    @Override
    public void run() {
        flushCache();
        while (true) {
            monitor();
        }

    }
}
