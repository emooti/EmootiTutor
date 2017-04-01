package org.emooti.emootibantransformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author utakapp
 * Answer Request from App
 *
 */
public class EmootiHappyIndexAppData implements EmootiBanFields{
	public Object find(String input) throws IOException
		{
		String query="";
		if (input!=null && !input.equals(""))
			{
			Map<String, String> map = null;
			input="{\"att\": \"Team A\"}";
	
			if (input.substring(0,1).equals("{"))
				// json
				
				{
				JsonConverter jc = new JsonConverter();
				map=jc.jsonToMap(input);
				}
			else
				{
				query=input.replaceFirst("\\?", "&");
				
				map=getQueryMap(query);
				}
			
	    	if (map!=null)
	    		{
	    		Set<String> keys = map.keySet();
	    		ArrayList<HashMap <String, String>> foundList = new ArrayList<HashMap <String, String>>();
	        
	    		EmootiBanCache cache = new EmootiBanCache();
	    		//ArrayList <Map <String, String>>al = cache.getCopy();
	    		// Ordered
	    		ArrayList <Map <String, String>>al=cache.getCopybyAttributes(map);
	    		ArrayList<String> sortList = new ArrayList<String>();
	    		sortList.add(att_f);
	    		sortList.add(hashtag_f);
	    		sortList.add(datetime_f);
	    		sortList.add(emootiID_f);
	    		
	    			        		
			    SortArrayList sl = new SortArrayList();
			    
			    al=sl.sort(al, sortList);
	
	    		for (int i = 0; i < al.size(); i++)
	    			{
	    			HashMap <String, String> item = null; 
	    			item = (HashMap<String, String>)al.get(i);
	    			boolean found=false;
	    			for (Map.Entry me : item.entrySet()) 
	    				{
		        		for (String key : keys)
							{
	    					if ( me.getKey().equals(key) && me.getValue().equals(map.get(key)) )
		        				{
	    						found=true;
		        				}
							}
	    				}
	    			
	        		//if (found && timespan(item)==true)
		        	if (found)
						foundList.add(item);
	
	    			}
	    		
	    		if (foundList!=null && foundList.size()>0)
	        		/* Prepare for HappyIndex */	
	    			{
	    			EmootiHappyIndexSummary ehp = new EmootiHappyIndexSummary();
	    			ArrayList <HashMap <String,String>> summary= (ArrayList <HashMap <String,String>>)ehp.summary(foundList, map);
	        			        		
	        		String json_new=craft_result_for_app(summary);
	        		System.out.println("....."+json_new);
	        	  	
	        		return json_new;

	    			}
	    		else
	    			return "";
	    		}
	    	else
	    		return "";
	
	    }
	return "";
	}

private String craft_result_for_app(ArrayList <HashMap <String,String>> foundList)
	{
		String craft = "{\"results\":[{";
		int i=0;
		HashMap aList = new HashMap();
		HashMap tags = new HashMap();
		int end=foundList.size();
		while (i<end)
			{
				
			HashMap<String, String> item = foundList.get(i);
			String key = att_f;
			String value=item.get(key);
			
			HashMap<String, String> item2=null;
			boolean last=false;
			if (i<foundList.size()-1)
				{
				last=false;
				item2=foundList.get(i+1);
				}
			else
				{
					//take care of last entry
				last=true;
				}
			String green="0";
			String orange="0";
			String red="0";
			//Prepare
			while (last || item.get(key).equals(item2.get(key))) // Aggregate
				{
				aList.put("hashTag", item.get(hashtag_f));
				if (item.get("7.0.0.0")!=null)
					{
					green=item.get(vote_f);
					aList.put("green", green);

					}
				
				if (item.get("5.0.0.0")!=null)
					{
					orange=item.get(vote_f);
					aList.put("orange", orange);

					}
				
				if (item.get("4.0.0.0")!=null)
					{
					red=item.get(vote_f);
					aList.put("red", red);

					}
				
				i++;
				if (i<end-1)
					item = foundList.get(i);
				else
					break; //last item
				}
			
			aList.put("red", red);
			aList.put("orange", orange);
			aList.put("green", green);

			aList.put("date", item.get(datetime_f));


			int totalvotes=0;
			if ((String)aList.get("red")!=null)
				totalvotes=totalvotes+(new Integer(((String)aList.get("red")))).intValue();
	
			if ((String)aList.get("orange")!=null)
				totalvotes=totalvotes+(new Integer(((String)aList.get("orange")))).intValue();
	
			if ((String)aList.get("green")!=null)
				totalvotes=totalvotes+(new Integer(((String)aList.get("green")))).intValue();
	
			aList.put("total_votes", (new Integer(totalvotes)).toString());
			tags.put(value, aList); // Tags for Team
			// Go to next Team
			}
		// add Teams to json
		HashMap <String, String> item = null; 
		Set<String> keys = tags.keySet();
		for (String key : keys)
			{
			craft = craft + "\"team\":\""+key+"\", \"tags\": [";
			craft=craft+= JsonConverter.maptoJson((Map<String, String>)tags.get(key));
			craft=craft+"]}],";
		}
	craft=craft+"\"total_team_amount\":"+new Integer(tags.size()).toString()+"}";
	
	// Shortcut for Test
	craft="{\"results\": [ {\"team\": \"Team A\",\"tags\": [{\"hashTag\": \"#Code\",\"green\": 28,\"orange\": 21,\"red\": 45,\"total_votes\": 94,\"date\": 1488957464291                       }]}],\"total_team_amount\": 2}";

	return craft;
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