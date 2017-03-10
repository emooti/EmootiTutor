package org.emooti.emootibantransformer;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EmootiHappyIndexSummary {
	HashMap<String, HashMap> resultMap = new <String, HashMap> HashMap();
	
		public Object find(String input) throws IOException
			{
			String query="";
			if (input!=null && !input.equals(""))
				{

				query=input.replaceFirst("\\?", "&");

				Map<String, String> map = null;
				map=getQueryMap(query);
	        	if (map!=null)
	        		{
	        		Set<String> keys = map.keySet();
	        		for (String key : keys)
						{
	        			System.out.println("Name=" + key);
	        			System.out.println("Value=" + map.get(key));
						}
				
	        		ArrayList<Map <String, String>> foundList = new ArrayList<Map <String, String>>();
		        
	        		EmootiBanCache cache = new EmootiBanCache();
	        		ArrayList <Map <String, String>>al = cache.getCopy();
	        		for (int i = 0; i < al.size(); i++)
	        			{
	        			Map <String, String> item = null; 
	        			item = al.get(i);
	        			for (Map.Entry me : item.entrySet()) 
	        				{
	    	        		for (String key : keys)
	    						{
	        					if ( me.getKey().equals(key) && me.getValue().equals(map.get(key)) )
			        				{
	        						foundList.add(item);
			        				}
	    						}

	        				}
	        			}
	        		/* Prepare for HappyIndex */
		        
	        
	        		ArrayList <String> days = getDays("", 30);
	        		
	        
	        		return generateLineChartData(foundList, days);
	        		}
	        	else
	        		return null;

	        }
		
			
		return "";
		}
		
private Object generateLineChartData(ArrayList <Map <String,String>> al, ArrayList <String> days)
	{

	String start=days.get(0);
	String end=days.get(days.size()-1);
	HashMap <String, int []> map = new HashMap <> ();
	
    for (int i = 0; i < al.size(); i++) // for each entry in Map
		{
		Map <String, String> item = null; 
		item = al.get(i);
		boolean found=false;
		String day="";
		//Step 1 Is in right datespectrum?
		for (Map.Entry me : item.entrySet()) 
			{
			found=false;
			if (me.getKey().equals("datetime") )
				{
				String aDate=((String)me.getValue()).substring(0, 8);
				
				if (start.compareTo(aDate)>=0 && end.compareTo(aDate)<=0)
					{
					found=true;
					day=me.getValue().toString().substring(0, 8);
					break;
					}
				}
			}
		
		int inum=0;
		String att="";
		String hash="";
		if (found)
		   {			
			//2. Find @ - group of user map
			for (Map.Entry me : item.entrySet()) 
				{
				if (me.getKey().equals("att") )
					{
					att=me.getValue().toString();
					}
				}
			
			//3. Find # - add to map
			
			for (Map.Entry me : item.entrySet()) 
				{
				if (me.getKey().equals("hash") )
					{
					hash=me.getValue().toString();
					}
				}

		
			//4. Find Color Number
			String num="00";
			for (Map.Entry me : item.entrySet()) 
				{
				if (me.getKey().equals("emootiID") )
					{
					String emootiid=me.getValue().toString()+".";
					num=emootiid.substring(0, emootiid.indexOf("."));
					inum = (new Integer(num)).intValue();
					
					break;
					}
				}
			EmootiHappyIndexSummaryFillResultMap ehirm = new EmootiHappyIndexSummaryFillResultMap();
			HashMap <String, HashMap> resultMap = new HashMap();
			resultMap= ehirm.addToResultMap(resultMap, day, att, hash, num);

			}
        	String json_new=JsonConverter.maptoJsonMap(resultMap);
        	return json_new;

		   }
				
	HappyIndexData hid = new HappyIndexData();
	//hid.setData(Data);
	//hid.setLabel(Label);
	return (Object)resultMap;
	}


private ArrayList getDays(String start, int nrof)
	{
	// Generate a String fo Date and Time
	ArrayList<String> days = new ArrayList<String>();
	
	Calendar myCal = new GregorianCalendar();
	// Einzelne Felder extrahieren:
	int year = myCal.get( Calendar.YEAR  );
	int month = myCal.get( Calendar.MONTH );     
	int day = myCal.get( Calendar.DATE  );
	
		
		if (!(start.equals("today") || start.equals("") || start == null)) //Still to be implemented
			{
			//long dt=((year)*10000L)+(month*100L)+day;
			//start=(new Long(dt).toString());

			return days;
			}

    Calendar aCal = Calendar.getInstance();
    
    aCal.set(Calendar.DATE, day);
    
	
	for (int i=1; i<nrof; i++)
		{
		year = aCal.get( Calendar.YEAR  );
		month = aCal.get( Calendar.MONTH ) + 1;           
		day = aCal.get( Calendar.DATE  );

		long dt=((year)*10000L)+(month*100L)+day;
		days.add((new Long(dt).toString()));
		aCal.add(Calendar.DATE, -1);
		}
	
	return days;
	}	

public static Map<String, String> getQueryMap(String query)
	{
    String[] params = query.split("&");
    Map<String, String> map = new HashMap<String, String>();
    for (String param : params)
    	{
    	if (param!=null && param.contains("="))
    		{
    		String name = param.split("=")[0];
    		String value = param.split("=")[1];
    		map.put(name, value);
    		}
    	}
    return map;
	}
}