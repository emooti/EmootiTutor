package org.emooti.emootibantransformer;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 
 * @author utakapp
 *
 */
public class EmootiBanTransformer {
	
	private ArrayList <String> al = new ArrayList <String>();

		public String transform(InputStream object) throws IOException
			{
	        // get received JSON data from request
	        BufferedReader br = new BufferedReader(new InputStreamReader(object));
	        String json = "";
	        
	        if(br != null){
	            json = br.readLine();
	        	}
	        
	        if (json !=null && !json.equals(""))
	        	al.add(json);
	        
	        EmootiBanCache cache = new EmootiBanCache();
	        
	        Map <String,String> map = JsonConverter.jsonToMap(json);
	        
	        if (cache.size()==0) //Testdata for new setuo
	        	{
		        map.put("datetime", addDateTime(-2));
		        cache.add(map);
		        
		        map = JsonConverter.jsonToMap(json);
		        map.put("datetime", addDateTime(-3));
		        cache.add(map);
	        	}
		        
		        map = JsonConverter.jsonToMap(json);
		        map.put("datetime", addDateTime(0));
		        cache.add(map);
	        
	        ArrayList <Map <String, String>>al = cache.get();
	        for (int i = 0; i < al.size(); i++)
	        	{
	        	Map <String, String> item = null; 
	        	item = al.get(i);
	        	}
	        
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
	    
}
