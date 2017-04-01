package org.emooti.emootibantransformer;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 
 * @author utakapp
 *
 */
public class EmootiBanTransformer implements EmootiBanFields{
	
		public String transform(InputStream object) throws IOException
			{
	        // get received JSON data from request
	        BufferedReader br = new BufferedReader(new InputStreamReader(object));
	        String json = "";
		    EmootiBanCache ebc = new EmootiBanCache();	
		    if (ebc.size()==0)
		    	Tester.testdata();

	        
	        if(br != null){
	            json = br.readLine();
	        	}
	        return (transformBan(json));
			}
		
		public String transformBan (String json)
			{	        	        
	        Map <String,String> map = JsonConverter.jsonToMap(json);	        
		    map=verifyMap((HashMap)map);
		    EmootiBanCache ebc = new EmootiBanCache();	
		    
	        String dt = addDateTime(0);
	        map.put(datetime_f, dt);
		    ebc.cache.add(map);

	        //Convert to Jso String
	        String json_new=JsonConverter.maptoJson(map);
			return json_new;
			}
		
		private String addDateTime(int diff)
			{
			// Generate a String for Date and Time
			
			Calendar myCal = Calendar.getInstance();
			// Einzelne Felder extrahieren:
			int date=0;
			if (diff != 0)
				{
				myCal.add(Calendar.DATE, diff);
				}
			int year = myCal.get( Calendar.YEAR  );
			int mnth = myCal.get( Calendar.MONTH ) + 1;  
					
			date = myCal.get( Calendar.DATE);
			
			int hour = myCal.get(Calendar.HOUR);
			int minute = myCal.get(Calendar.MINUTE);
			int second = myCal.get(Calendar.SECOND);
			int milli = myCal.get(Calendar.MILLISECOND);
			long dt=((year)*10000L)+(mnth*100L)+date;
			long time=(hour*10000L)+(minute*100L)+second;
			
			
			return ((new Long(dt).toString()).concat(new Long(time).toString())).concat( ((new Long(milli+10000)).toString()).substring(1, 5));
			}
	
		
		private Map verifyMap (HashMap <String, String> map)
			{
			Map new_map = new HashMap<String, String>();
		// add to Map
			if (map!= null)	
				{
				for (Map.Entry me : map.entrySet()) 
					{
					if (me.getKey().toString().toLowerCase().equals(vote_f))
						{
						String vote=((String)me.getValue());
						if (vote.equals("1")) //Green
							{
							new_map.put(emootiID_f, "7.0.0.0");
							}
						if (vote.equals("2")) //Yellow
							new_map.put(emootiID_f, "5.0.0.0");
	
						if (vote.equals("3")) //red
							new_map.put(emootiID_f, "4.0.0.0");

						}
					else
						{
						new_map.put(me.getKey(), me.getValue());
						}
					}
				}
			return new_map;
		
			}
	    
}
