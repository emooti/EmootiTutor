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
		
		public static String maptoJsonMap(HashMap<String, HashMap> map)
			{
			String json="";
			
			for (Map.Entry me : map.entrySet()) 
				{
				String key=me.getKey().toString();
				
				Object nextMap=(Object)(me.getValue());
				if (nextMap instanceof HashMap)
					{
					
				        try {
				            ObjectMapper mapper = new ObjectMapper();
				
				            //convert map to JSON string
				            json = mapper.writeValueAsString(map);
				        	} catch (Exception e) {
				        		e.printStackTrace();
				        		}
				     json=json+"{“+key+“:"+maptoJsonMap((HashMap)nextMap)+"}";
					}
				}
			System.out.println("{“+key+“:"+json+"}");
			return "{“+key+“:"+json+"}";
	
			}
}

