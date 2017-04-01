package org.emooti.emootibantransformer;

import java.util.Map;

import java.util.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class JsonConverter {
	
		public static Map <String,String> jsonToMap (String json)
			{
					
			Map<String,String> map = new HashMap<String,String>();
			ObjectMapper mapper = new ObjectMapper();
				
			try {
				
				//convert JSON string to Map
				map = mapper.readValue(json, 
				new TypeReference<HashMap<String,String>>(){});
					 
				} catch (Exception e) {
				e.printStackTrace();
				}
			return map;
			}

		
		public static String maptoJson(Map<String, String> map)
			{
			String json="";
            try {
                ObjectMapper mapper = new ObjectMapper();
 
                //convert map to JSON string
                json = mapper.writeValueAsString(map);
            	} catch (Exception e) {
            		e.printStackTrace();
            	}
			return json;
			}
		
		
		public static String maptoJson(ArrayList<HashMap<String, String>> aList)
			{
			String json="";
			for (int i=0; i<aList.size() &&aList!=null; i++)
				{
				HashMap <String,String> aMap = aList.get(i);
		        try {
		            ObjectMapper mapper = new ObjectMapper();
		
		            //convert map to JSON string
		            json = json+ mapper.writeValueAsString(aMap);
		            if (i<aList.size()-1)
		            	json=json+", ";
		        	} catch (Exception e) {
		        		e.printStackTrace();
		        	}
				}
			return json;
			}

		
		public static String maptoJsonMap(HashMap<String, HashMap> map)
			{
			String json="";
			
			for (Map.Entry me : map.entrySet()) 
				{
				String key=me.getKey().toString();				
				Object nextMap=(Object)(me.getValue());
				if (nextMap instanceof HashMap)
					{
					json=json+"\""+key+":";
				     json=json+maptoJsonMap((HashMap)nextMap);
					
					}
				else
					{
				        try {
				            ObjectMapper mapper = new ObjectMapper();
				
				            //convert map to JSON string
				            json = mapper.writeValueAsString(map);
				            System.out.println("mapper json...:"+json);
				        	} catch (Exception e) {
				        		e.printStackTrace();
				        		}
					}
				}
			return json;
	
			}
}

