package org.emooti.emootibantransformer;

import java.util.ArrayList;
import java.util.HashMap;


public class EmootiHappyIndexSummaryFillResultMap {

	
	public HashMap addToResultMap(HashMap<String, HashMap> resultMap, String day, String att, String hash, String num)
	
		{
		// add Group/Team to Result
		HashMap attMap = null;
		if (resultMap==null || !resultMap.containsKey(att))
			{
			// First Entry
			if (resultMap==null)
				{
				resultMap = new HashMap<String, HashMap>();
				}
			
			// add to Map
			if (!resultMap.containsKey(att))
				{
				attMap = new HashMap<HashMap, HashMap>();
				attMap.put(att, attMap);
				}
			}
		else
			{
			attMap = (HashMap)(resultMap.get(att));
					
			addToResultMap_att(attMap, day, hash, num);
				
			}
		return resultMap;
		}
	
	private HashMap addToResultMap_att(HashMap <String,HashMap> attMap, String day, String hash, String color)
		{
		// Add HashTag to Group
		HashMap <String, HashMap> hashTagMap = null;
		if (attMap==null || !attMap.containsKey(hash))
			{
			if (attMap==null)
				{
				attMap = new HashMap<String, HashMap>();
				}
		
		// add to Map
			if (!attMap.containsKey(hash))
				{
				hashTagMap = new HashMap<String, HashMap>();
				attMap.put(hash, hashTagMap);
				}
			}
		hashTagMap = (HashMap)(attMap.get(hash));					
		addToHashTagMap_day(hashTagMap, day, color);
				
			
		return hashTagMap;
		}
	private HashMap addToHashTagMap_day(HashMap <String, HashMap> hashTagMap, String day, String color)
		{
		HashMap <String, HashMap>dayMap= new HashMap();
		if (hashTagMap==null || !hashTagMap.containsKey(day))
			{
			// First Entry
			// add to Map
			if (dayMap==null)
				{
				dayMap = new HashMap<String, HashMap>();
				}
	
			// add to Map
			if (!dayMap.containsKey(day))
				{
				dayMap = new HashMap<String, HashMap>();
				hashTagMap.put(day, dayMap);
				}

			}
		
		dayMap = (HashMap)(hashTagMap.get(day));					
		addToDaytMap_color(dayMap, color);
			
		return dayMap;
		}

	
	private HashMap<String, String> addToDaytMap_color(HashMap <String, HashMap> dayMap, String color)
		{
		HashMap <String, String>colorMap = new HashMap();
		if (dayMap==null || !dayMap.containsKey(color))
			{
			// First Entry
			// add to Map
			if (dayMap==null)
				{
				dayMap = new HashMap<String, HashMap>();
				}

			// add to Map
			if (!dayMap.containsKey(color))
				{
				colorMap = new HashMap<String, String>();
				dayMap.put(color, colorMap);
				}
			}
			
		colorMap = (HashMap)(dayMap.get(color));					
		addToColortMap_count(colorMap, color);
			
		return colorMap;
		}

	private HashMap addToColortMap_count(HashMap <String, String> colorMap, String color)
		{
		if (colorMap==null || !colorMap.containsKey(color))
			{
			// First Entry
			// add to Map
			if (colorMap==null)
				colorMap=new HashMap <String,String>();
			colorMap.put(color, "1");
			}
		else
			{
			String colentry = (String)colorMap.get(color).toString();
			int icol = (new Integer(colentry)).intValue();
			icol++;
			colorMap.put(color, (new Integer(colentry)).toString());
			}
		return colorMap;
		}
}
