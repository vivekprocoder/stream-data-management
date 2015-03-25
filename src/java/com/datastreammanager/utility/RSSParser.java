
package com.datastreammanager.utility;

import com.datastreammanager.model.TupleModel;
import java.io.InputStream;
import java.util.ArrayList;

/*
 * @author PprrATeekK
 */
public class RSSParser extends Parser{

    @Override
    public String RemoveHeader(String dataString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String RemoveTrailer(String dataString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<TupleModel> parse(String dataString) {
      
        
           tuples.clear();
           
           
        return null;
        
    }

    @Override
    public void setParameters() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
