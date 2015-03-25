/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datastreammanager.utility;

/**
 *
 * @author PprrATeekK
 */
import com.datastreammanager.model.ColumnPair;
import com.datastreammanager.model.TupleModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser extends Parser {

    @Override
    public ArrayList<TupleModel> parse(String dataString) {

        JSONParser parser = new JSONParser();
        tuples = new ArrayList<TupleModel>();
        ArrayList<ColumnPair> colList;
        ColumnPair col;
        TupleModel tpl;
        JSONArray data=null;
        
        try {
            data = (JSONArray) parser.parse(dataString);
        } catch (ParseException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        if(data==null)
        return null;
        
            for (Object obj : data) {

              JSONObject jsonObject = (JSONObject) obj;

              tpl = new TupleModel();
              colList = new ArrayList<ColumnPair>();
                Collection<Object> values =jsonObject.values();
                Iterator it = values.iterator();
                   while(it.hasNext()) {
                        col = new ColumnPair();
                        col.setValue(it.next().toString());
                        System.out.print(col.getValue()+"\t");
                        colList.add(col);
                    }
                    tpl.setDataRow(colList);
                    tuples.add(tpl);
                     System.out.println("");
                }

        return tuples;

    }

    public static void main(String[] args) {

       JsonParser jr = new JsonParser();
       jr.parse("[ {\n" +
"  \"address\" : \"10049 College Way N\",\n" +
"  \"longitude\" : \"-122.335022\",\n" +
"  \"latitude\" : \"47.701756\",\n" +
"  \"incident_number\" : \"F110104009\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.335022\",\n" +
"    \"latitude\" : \"47.701756\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"5929 Beach Dr Sw\",\n" +
"  \"longitude\" : \"-122.397816\",\n" +
"  \"latitude\" : \"47.550431\",\n" +
"  \"incident_number\" : \"F110104008\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.397816\",\n" +
"    \"latitude\" : \"47.550431\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"12035 Aurora Av N\",\n" +
"  \"longitude\" : \"-122.344888\",\n" +
"  \"latitude\" : \"47.716784\",\n" +
"  \"incident_number\" : \"F110104006\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.344888\",\n" +
"    \"latitude\" : \"47.716784\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"2720 E Madison St\",\n" +
"  \"longitude\" : \"-122.296667\",\n" +
"  \"latitude\" : \"47.623153\",\n" +
"  \"incident_number\" : \"F110104004\",\n" +
"  \"type\" : \"Medic Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.296667\",\n" +
"    \"latitude\" : \"47.623153\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"2260 1st Av S\",\n" +
"  \"longitude\" : \"-122.334199\",\n" +
"  \"latitude\" : \"47.583347\",\n" +
"  \"incident_number\" : \"F110103709\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.334199\",\n" +
"    \"latitude\" : \"47.583347\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"1930 Boren Av\",\n" +
"  \"longitude\" : \"-122.333103\",\n" +
"  \"latitude\" : \"47.617173\",\n" +
"  \"incident_number\" : \"F110103707\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.333103\",\n" +
"    \"latitude\" : \"47.617173\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"4015 25th Av S\",\n" +
"  \"longitude\" : \"-122.30014\",\n" +
"  \"latitude\" : \"47.567998\",\n" +
"  \"incident_number\" : \"F110103708\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.30014\",\n" +
"    \"latitude\" : \"47.567998\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"913 24th Av\",\n" +
"  \"longitude\" : \"-122.301455\",\n" +
"  \"latitude\" : \"47.610116\",\n" +
"  \"incident_number\" : \"F110103706\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.301455\",\n" +
"    \"latitude\" : \"47.610116\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"725 N 109th St\",\n" +
"  \"longitude\" : \"-122.349784\",\n" +
"  \"latitude\" : \"47.708005\",\n" +
"  \"incident_number\" : \"F110103704\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.349784\",\n" +
"    \"latitude\" : \"47.708005\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"2315 Ne 65th St\",\n" +
"  \"longitude\" : \"-122.302579\",\n" +
"  \"latitude\" : \"47.675775\",\n" +
"  \"incident_number\" : \"F110103703\",\n" +
"  \"type\" : \"Medic Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.302579\",\n" +
"    \"latitude\" : \"47.675775\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"13333 Greenwood Av N\",\n" +
"  \"longitude\" : \"-122.355552\",\n" +
"  \"latitude\" : \"47.725503\",\n" +
"  \"incident_number\" : \"F110103169\",\n" +
"  \"type\" : \"Medic Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.355552\",\n" +
"    \"latitude\" : \"47.725503\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"2326 California Av Sw\",\n" +
"  \"longitude\" : \"-122.386485\",\n" +
"  \"latitude\" : \"47.582838\",\n" +
"  \"incident_number\" : \"F110102795\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.386485\",\n" +
"    \"latitude\" : \"47.582838\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"11522 3rd Av Ne\",\n" +
"  \"longitude\" : \"-122.326055\",\n" +
"  \"latitude\" : \"47.712441\",\n" +
"  \"incident_number\" : \"F110102791\",\n" +
"  \"type\" : \"Aid Response Yellow\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.326055\",\n" +
"    \"latitude\" : \"47.712441\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"2811 E Pike St\",\n" +
"  \"longitude\" : \"-122.296035\",\n" +
"  \"latitude\" : \"47.614118\",\n" +
"  \"incident_number\" : \"F110102789\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.296035\",\n" +
"    \"latitude\" : \"47.614118\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"1859 Boyer Av E\",\n" +
"  \"longitude\" : \"-122.302212\",\n" +
"  \"latitude\" : \"47.635548\",\n" +
"  \"incident_number\" : \"F110102430\",\n" +
"  \"type\" : \"Medic Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.302212\",\n" +
"    \"latitude\" : \"47.635548\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"Ashworth Av N / N 36th St\",\n" +
"  \"longitude\" : \"-122.340239\",\n" +
"  \"latitude\" : \"47.650513\",\n" +
"  \"incident_number\" : \"F110102429\",\n" +
"  \"type\" : \"Unk Odor\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.340239\",\n" +
"    \"latitude\" : \"47.650513\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"8507 17th Av Nw\",\n" +
"  \"longitude\" : \"-122.378957\",\n" +
"  \"latitude\" : \"47.690657\",\n" +
"  \"incident_number\" : \"F110102428\",\n" +
"  \"type\" : \"Medic Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.378957\",\n" +
"    \"latitude\" : \"47.690657\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"Queen Anne Dr / 1st Av N\",\n" +
"  \"longitude\" : \"-122.355558\",\n" +
"  \"latitude\" : \"47.641855\",\n" +
"  \"incident_number\" : \"F110102427\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.355558\",\n" +
"    \"latitude\" : \"47.641855\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"1005 Terrace St\",\n" +
"  \"longitude\" : \"-122.32228\",\n" +
"  \"latitude\" : \"47.604615\",\n" +
"  \"incident_number\" : \"F110102122\",\n" +
"  \"type\" : \"Aid Response Yellow\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.32228\",\n" +
"    \"latitude\" : \"47.604615\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"1700 17th Av\",\n" +
"  \"longitude\" : \"-122.310151\",\n" +
"  \"latitude\" : \"47.616443\",\n" +
"  \"incident_number\" : \"F110102120\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.310151\",\n" +
"    \"latitude\" : \"47.616443\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"3025 Ne 140th St\",\n" +
"  \"longitude\" : \"-122.296165\",\n" +
"  \"latitude\" : \"47.730148\",\n" +
"  \"incident_number\" : \"F110101737\",\n" +
"  \"type\" : \"Medic Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.296165\",\n" +
"    \"latitude\" : \"47.730148\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"6708 1st Av Nw\",\n" +
"  \"longitude\" : \"-122.357432\",\n" +
"  \"latitude\" : \"47.677823\",\n" +
"  \"incident_number\" : \"F110101736\",\n" +
"  \"type\" : \"Medic Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.357432\",\n" +
"    \"latitude\" : \"47.677823\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"3114 Mount Rainier Dr S\",\n" +
"  \"longitude\" : \"-122.285251\",\n" +
"  \"latitude\" : \"47.575911\",\n" +
"  \"incident_number\" : \"F110101735\",\n" +
"  \"type\" : \"Medic Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.285251\",\n" +
"    \"latitude\" : \"47.575911\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"2615 25th Av S\",\n" +
"  \"longitude\" : \"-122.300279\",\n" +
"  \"latitude\" : \"47.579598\",\n" +
"  \"incident_number\" : \"F110101417\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.300279\",\n" +
"    \"latitude\" : \"47.579598\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"2611 45th Av Sw\",\n" +
"  \"longitude\" : \"-122.389247\",\n" +
"  \"latitude\" : \"47.580983\",\n" +
"  \"incident_number\" : \"F110101416\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.389247\",\n" +
"    \"latitude\" : \"47.580983\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"4032 8th Av Ne\",\n" +
"  \"longitude\" : \"-122.319795\",\n" +
"  \"latitude\" : \"47.656118\",\n" +
"  \"incident_number\" : \"F110101123\",\n" +
"  \"type\" : \"Medic Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.319795\",\n" +
"    \"latitude\" : \"47.656118\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"7700 - 8398 Nb Sr99 At Kenyon\",\n" +
"  \"longitude\" : \"-122.332133\",\n" +
"  \"latitude\" : \"47.534148\",\n" +
"  \"incident_number\" : \"F110101120\",\n" +
"  \"type\" : \"Rescue Automobile\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.332133\",\n" +
"    \"latitude\" : \"47.534148\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"12505 Greenwood Av N\",\n" +
"  \"longitude\" : \"-122.355539\",\n" +
"  \"latitude\" : \"47.719638\",\n" +
"  \"incident_number\" : \"F110101117\",\n" +
"  \"type\" : \"Auto Fire Alarm\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.355539\",\n" +
"    \"latitude\" : \"47.719638\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"530 4th Av W\",\n" +
"  \"longitude\" : \"-122.361941\",\n" +
"  \"latitude\" : \"47.623683\",\n" +
"  \"incident_number\" : \"F110101114\",\n" +
"  \"type\" : \"Trans to AMR\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.361941\",\n" +
"    \"latitude\" : \"47.623683\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"1902 2nd Av\",\n" +
"  \"longitude\" : \"-122.340465\",\n" +
"  \"latitude\" : \"47.610972\",\n" +
"  \"incident_number\" : \"F110101113\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.340465\",\n" +
"    \"latitude\" : \"47.610972\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"Aurora Av N / N 135th St\",\n" +
"  \"longitude\" : \"-122.345017\",\n" +
"  \"latitude\" : \"47.72686\",\n" +
"  \"incident_number\" : \"F110100813\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.345017\",\n" +
"    \"latitude\" : \"47.72686\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"747 N 135th St\",\n" +
"  \"longitude\" : \"-122.348012\",\n" +
"  \"latitude\" : \"47.726864\",\n" +
"  \"incident_number\" : \"F110100812\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.348012\",\n" +
"    \"latitude\" : \"47.726864\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"600 4th Av\",\n" +
"  \"longitude\" : \"-122.330278\",\n" +
"  \"latitude\" : \"47.603333\",\n" +
"  \"incident_number\" : \"F110100417\",\n" +
"  \"type\" : \"Medic Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.330278\",\n" +
"    \"latitude\" : \"47.603333\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"6512 4th Av Nw\",\n" +
"  \"longitude\" : \"-122.361498\",\n" +
"  \"latitude\" : \"47.676093\",\n" +
"  \"incident_number\" : \"F110100416\",\n" +
"  \"type\" : \"Automatic Medical Alarm\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.361498\",\n" +
"    \"latitude\" : \"47.676093\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"6606 43rd Av S\",\n" +
"  \"longitude\" : \"-122.279762\",\n" +
"  \"latitude\" : \"47.543277\",\n" +
"  \"incident_number\" : \"F110100415\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.279762\",\n" +
"    \"latitude\" : \"47.543277\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"3101 Ne 145th St\",\n" +
"  \"longitude\" : \"-122.295148\",\n" +
"  \"latitude\" : \"47.733775\",\n" +
"  \"incident_number\" : \"F110100039\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.295148\",\n" +
"    \"latitude\" : \"47.733775\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"1907 N 80th St\",\n" +
"  \"longitude\" : \"-122.335484\",\n" +
"  \"latitude\" : \"47.686916\",\n" +
"  \"incident_number\" : \"F110100038\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.335484\",\n" +
"    \"latitude\" : \"47.686916\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"12333 33rd Av Ne\",\n" +
"  \"longitude\" : \"-122.292773\",\n" +
"  \"latitude\" : \"47.717654\",\n" +
"  \"incident_number\" : \"F110100037\",\n" +
"  \"type\" : \"Medic Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.292773\",\n" +
"    \"latitude\" : \"47.717654\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"6505 Delridge Way Sw\",\n" +
"  \"longitude\" : \"-122.361199\",\n" +
"  \"latitude\" : \"47.543663\",\n" +
"  \"incident_number\" : \"F110100036\",\n" +
"  \"type\" : \"Medic Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.361199\",\n" +
"    \"latitude\" : \"47.543663\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"5000 California Av Sw\",\n" +
"  \"longitude\" : \"-122.386883\",\n" +
"  \"latitude\" : \"47.557498\",\n" +
"  \"incident_number\" : \"F110100035\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.386883\",\n" +
"    \"latitude\" : \"47.557498\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"5270 Rainier Av S\",\n" +
"  \"longitude\" : \"-122.282393\",\n" +
"  \"latitude\" : \"47.554937\",\n" +
"  \"incident_number\" : \"F110100033\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.282393\",\n" +
"    \"latitude\" : \"47.554937\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"2100 Queen Anne Av N\",\n" +
"  \"longitude\" : \"-122.356941\",\n" +
"  \"latitude\" : \"47.63717\",\n" +
"  \"incident_number\" : \"F110100032\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.356941\",\n" +
"    \"latitude\" : \"47.63717\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"1620 43rd Av E\",\n" +
"  \"longitude\" : \"-122.277157\",\n" +
"  \"latitude\" : \"47.633463\",\n" +
"  \"incident_number\" : \"F110099751\",\n" +
"  \"type\" : \"Trans to AMR\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.277157\",\n" +
"    \"latitude\" : \"47.633463\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"11712 38th Av Ne\",\n" +
"  \"longitude\" : \"-122.287246\",\n" +
"  \"latitude\" : \"47.71379\",\n" +
"  \"incident_number\" : \"F110099750\",\n" +
"  \"type\" : \"Investigate Out Of Service\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.287246\",\n" +
"    \"latitude\" : \"47.71379\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"9201 Rainier Av S\",\n" +
"  \"longitude\" : \"-122.269997\",\n" +
"  \"latitude\" : \"47.52101\",\n" +
"  \"incident_number\" : \"F110099749\",\n" +
"  \"type\" : \"Rescue Elevator\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.269997\",\n" +
"    \"latitude\" : \"47.52101\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"3204 Sw Morgan St\",\n" +
"  \"longitude\" : \"-122.373777\",\n" +
"  \"latitude\" : \"47.544669\",\n" +
"  \"incident_number\" : \"F110099747\",\n" +
"  \"type\" : \"Trans to AMR\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.373777\",\n" +
"    \"latitude\" : \"47.544669\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"620 N 130th St\",\n" +
"  \"longitude\" : \"-122.351366\",\n" +
"  \"latitude\" : \"47.723223\",\n" +
"  \"incident_number\" : \"F110099441\",\n" +
"  \"type\" : \"Medic Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.351366\",\n" +
"    \"latitude\" : \"47.723223\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"2821 S Walden St\",\n" +
"  \"longitude\" : \"-122.296173\",\n" +
"  \"latitude\" : \"47.572163\",\n" +
"  \"incident_number\" : \"F110099440\",\n" +
"  \"type\" : \"Medic Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.296173\",\n" +
"    \"latitude\" : \"47.572163\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"601 Union St\",\n" +
"  \"longitude\" : \"-122.333545\",\n" +
"  \"latitude\" : \"47.610155\",\n" +
"  \"incident_number\" : \"F110099438\",\n" +
"  \"type\" : \"Auto Fire Alarm\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.333545\",\n" +
"    \"latitude\" : \"47.610155\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"337 Ne 103rd St\",\n" +
"  \"longitude\" : \"-122.325371\",\n" +
"  \"latitude\" : \"47.703157\",\n" +
"  \"incident_number\" : \"F110099436\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.325371\",\n" +
"    \"latitude\" : \"47.703157\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"4515 41st Av S\",\n" +
"  \"longitude\" : \"-122.281699\",\n" +
"  \"latitude\" : \"47.562767\",\n" +
"  \"incident_number\" : \"F110099146\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.281699\",\n" +
"    \"latitude\" : \"47.562767\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"1615 15th Av\",\n" +
"  \"longitude\" : \"-122.312765\",\n" +
"  \"latitude\" : \"47.615453\",\n" +
"  \"incident_number\" : \"F110099148\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.312765\",\n" +
"    \"latitude\" : \"47.615453\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"751 N 135th St\",\n" +
"  \"longitude\" : \"-122.348004\",\n" +
"  \"latitude\" : \"47.726864\",\n" +
"  \"incident_number\" : \"F110099145\",\n" +
"  \"type\" : \"Medic Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.348004\",\n" +
"    \"latitude\" : \"47.726864\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"318 2nd Av Et  S\",\n" +
"  \"longitude\" : \"-122.330541\",\n" +
"  \"latitude\" : \"47.600194\",\n" +
"  \"incident_number\" : \"F110099144\",\n" +
"  \"type\" : \"Medic Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.330541\",\n" +
"    \"latitude\" : \"47.600194\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"6545 Fauntleroy Way Sw\",\n" +
"  \"longitude\" : \"-122.389038\",\n" +
"  \"latitude\" : \"47.544562\",\n" +
"  \"incident_number\" : \"F110098820\",\n" +
"  \"type\" : \"Aid Response\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"13519 30th Av Ne\",\n" +
"  \"longitude\" : \"-122.296407\",\n" +
"  \"latitude\" : \"47.726638\",\n" +
"  \"incident_number\" : \"F110098818\",\n" +
"  \"type\" : \"Aid Response\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"Nb I5 At Mercer\",\n" +
"  \"longitude\" : \"-122.328213\",\n" +
"  \"latitude\" : \"47.620184\",\n" +
"  \"incident_number\" : \"F110098817\",\n" +
"  \"type\" : \"Motor Vehicle Accident Freeway\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"East Marginal Way S / S Spokane St\",\n" +
"  \"longitude\" : \"-122.339668\",\n" +
"  \"latitude\" : \"47.571328\",\n" +
"  \"incident_number\" : \"F110098816\",\n" +
"  \"type\" : \"Medic Response\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"1415 E Olive St\",\n" +
"  \"longitude\" : \"-122.314031\",\n" +
"  \"latitude\" : \"47.616451\",\n" +
"  \"incident_number\" : \"F110098814\",\n" +
"  \"type\" : \"Alarm Bell\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"2555 43rd Av W\",\n" +
"  \"longitude\" : \"-122.412202\",\n" +
"  \"latitude\" : \"47.642676\",\n" +
"  \"incident_number\" : \"F110098564\",\n" +
"  \"type\" : \"Aid Response\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"15th Av Ne / Ne 50th St\",\n" +
"  \"longitude\" : \"-122.311937\",\n" +
"  \"latitude\" : \"47.664893\",\n" +
"  \"incident_number\" : \"F110098543\",\n" +
"  \"type\" : \"Aid Response\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"2345 Boylston Av E\",\n" +
"  \"longitude\" : \"-122.32346\",\n" +
"  \"latitude\" : \"47.640239\",\n" +
"  \"incident_number\" : \"F110098540\",\n" +
"  \"type\" : \"Aid Response Yellow\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"500 5th Av\",\n" +
"  \"longitude\" : \"-122.328535\",\n" +
"  \"latitude\" : \"47.602985\",\n" +
"  \"incident_number\" : \"F110098180\",\n" +
"  \"type\" : \"Aid Response\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"6420 California Av Sw\",\n" +
"  \"longitude\" : \"-122.387209\",\n" +
"  \"latitude\" : \"47.545436\",\n" +
"  \"incident_number\" : \"F110098181\",\n" +
"  \"type\" : \"1RED 1 Unit\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"2048 41st Av E\",\n" +
"  \"longitude\" : \"-122.279625\",\n" +
"  \"latitude\" : \"47.637301\",\n" +
"  \"incident_number\" : \"F110098179\",\n" +
"  \"type\" : \"Aid Response\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"310 15th Av E\",\n" +
"  \"longitude\" : \"-122.31269\",\n" +
"  \"latitude\" : \"47.620859\",\n" +
"  \"incident_number\" : \"F110098178\",\n" +
"  \"type\" : \"Medic Response\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"7538 43rd Av S\",\n" +
"  \"longitude\" : \"-122.27916\",\n" +
"  \"latitude\" : \"47.534502\",\n" +
"  \"incident_number\" : \"F110098175\",\n" +
"  \"type\" : \"Medic Response\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"3652 S Andover St\",\n" +
"  \"longitude\" : \"-122.287192\",\n" +
"  \"latitude\" : \"47.567936\",\n" +
"  \"incident_number\" : \"F110098176\",\n" +
"  \"type\" : \"Medic Response\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"2650 Alki Av Sw\",\n" +
"  \"longitude\" : \"-122.408009\",\n" +
"  \"latitude\" : \"47.58006\",\n" +
"  \"incident_number\" : \"F110098177\",\n" +
"  \"type\" : \"1RED 1 Unit\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"700 6th Av S\",\n" +
"  \"longitude\" : \"-122.32637\",\n" +
"  \"latitude\" : \"47.596676\",\n" +
"  \"incident_number\" : \"F110097864\",\n" +
"  \"type\" : \"Aid Response\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"2614 4th Av\",\n" +
"  \"longitude\" : \"-122.347229\",\n" +
"  \"latitude\" : \"47.617316\",\n" +
"  \"incident_number\" : \"F110097863\",\n" +
"  \"type\" : \"Aid Response\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"101 N 104th St\",\n" +
"  \"longitude\" : \"-122.358359\",\n" +
"  \"latitude\" : \"47.704379\",\n" +
"  \"incident_number\" : \"F110097861\",\n" +
"  \"type\" : \"Aid Response\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"303 S Jackson St\",\n" +
"  \"longitude\" : \"-122.33005\",\n" +
"  \"latitude\" : \"47.599199\",\n" +
"  \"incident_number\" : \"F110097550\",\n" +
"  \"type\" : \"Aid Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.33005\",\n" +
"    \"latitude\" : \"47.599199\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"1802 17th Av\",\n" +
"  \"longitude\" : \"-122.310139\",\n" +
"  \"latitude\" : \"47.61762\",\n" +
"  \"incident_number\" : \"F110097548\",\n" +
"  \"type\" : \"Aid Response Yellow\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.310139\",\n" +
"    \"latitude\" : \"47.61762\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"7400 West Marginal Way S\",\n" +
"  \"longitude\" : \"-122.333553\",\n" +
"  \"latitude\" : \"47.536229\",\n" +
"  \"incident_number\" : \"F110097547\",\n" +
"  \"type\" : \"Medic Response\",\n" +
"  \"report_location\" : {\n" +
"    \"needs_recoding\" : false,\n" +
"    \"longitude\" : \"-122.333553\",\n" +
"    \"latitude\" : \"47.536229\"\n" +
"  }\n" +
"}\n" +
", {\n" +
"  \"address\" : \"15th Av Ne / Ne 91st St\",\n" +
"  \"longitude\" : \"-122.312135\",\n" +
"  \"latitude\" : \"47.694888\",\n" +
"  \"incident_number\" : \"F110097210\",\n" +
"  \"type\" : \"Medic Response\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"2309 Ne 48th St\",\n" +
"  \"longitude\" : \"-122.302569\",\n" +
"  \"latitude\" : \"47.664024\",\n" +
"  \"incident_number\" : \"F110097209\",\n" +
"  \"type\" : \"Water Job Minor\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"817 Ne 75th St\",\n" +
"  \"longitude\" : \"-122.319917\",\n" +
"  \"latitude\" : \"47.683142\",\n" +
"  \"incident_number\" : \"F110096928\",\n" +
"  \"type\" : \"Aid Response\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"3215 Sw Andover St\",\n" +
"  \"longitude\" : \"-122.372444\",\n" +
"  \"latitude\" : \"47.568162\",\n" +
"  \"incident_number\" : \"F110096927\",\n" +
"  \"type\" : \"Aid Response\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"815 Alaskan Way\",\n" +
"  \"longitude\" : \"-122.337241\",\n" +
"  \"latitude\" : \"47.602452\",\n" +
"  \"incident_number\" : \"F110096926\",\n" +
"  \"type\" : \"Aid Response\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"4616 M L King Jr Way S\",\n" +
"  \"longitude\" : \"-122.294242\",\n" +
"  \"latitude\" : \"47.562011\",\n" +
"  \"incident_number\" : \"F110096625\",\n" +
"  \"type\" : \"Medic Response\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"1421 Minor Av\",\n" +
"  \"longitude\" : \"-122.326948\",\n" +
"  \"latitude\" : \"47.613011\",\n" +
"  \"incident_number\" : \"F110096351\",\n" +
"  \"type\" : \"Aid Response\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"1012 Queen Anne Av N\",\n" +
"  \"longitude\" : \"-122.356681\",\n" +
"  \"latitude\" : \"47.628379\",\n" +
"  \"incident_number\" : \"F110096350\",\n" +
"  \"type\" : \"Trans to AMR\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"3rd Av / Pike St\",\n" +
"  \"longitude\" : \"-122.337793\",\n" +
"  \"latitude\" : \"47.60975\",\n" +
"  \"incident_number\" : \"F110096348\",\n" +
"  \"type\" : \"Aid Response\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"1403 E Olive Way\",\n" +
"  \"longitude\" : \"-122.326803\",\n" +
"  \"latitude\" : \"47.617097\",\n" +
"  \"incident_number\" : \"F110096347\",\n" +
"  \"type\" : \"Auto Fire Alarm\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"6233 37th Av Ne\",\n" +
"  \"longitude\" : \"-122.288032\",\n" +
"  \"latitude\" : \"47.674172\",\n" +
"  \"incident_number\" : \"F110096346\",\n" +
"  \"type\" : \"Activated CO Detector\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"Broadway E / E Thomas St\",\n" +
"  \"longitude\" : \"-122.320892\",\n" +
"  \"latitude\" : \"47.621076\",\n" +
"  \"incident_number\" : \"F110096068\",\n" +
"  \"type\" : \"Aid Response\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"4th Av / Battery St\",\n" +
"  \"longitude\" : \"-122.345235\",\n" +
"  \"latitude\" : \"47.616138\",\n" +
"  \"incident_number\" : \"F110096067\",\n" +
"  \"type\" : \"Assault w/Weap 7 per Rule\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"410 9th Av\",\n" +
"  \"longitude\" : \"-122.32351\",\n" +
"  \"latitude\" : \"47.6042\",\n" +
"  \"incident_number\" : \"F110096064\",\n" +
"  \"type\" : \"Auto Fire Alarm\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"1st Av / Pike St\",\n" +
"  \"longitude\" : \"-122.340013\",\n" +
"  \"latitude\" : \"47.608821\",\n" +
"  \"incident_number\" : \"F110096063\",\n" +
"  \"type\" : \"Aid Response\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"1530 Nw 46th St\",\n" +
"  \"longitude\" : \"-122.376914\",\n" +
"  \"latitude\" : \"47.662206\",\n" +
"  \"incident_number\" : \"F110096062\",\n" +
"  \"type\" : \"Auto Fire Alarm\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"2024 3rd Av\",\n" +
"  \"longitude\" : \"-122.341197\",\n" +
"  \"latitude\" : \"47.612578\",\n" +
"  \"incident_number\" : \"F110095703\",\n" +
"  \"type\" : \"Aid Response\"\n" +
"}\n" +
", {\n" +
"  \"address\" : \"Shilshole Av Nw / 22nd Av Nw\",\n" +
"  \"longitude\" : \"-122.38475\",\n" +
"  \"latitude\" : \"47.666467\",\n" +
"  \"incident_number\" : \"F110095702\",\n" +
"  \"type\" : \"Aid Response\"\n" +
"} ]");

    }

    @Override
    public void setParameters() {
    }
}