package org.emooti.emootibantransformer;
import java.util.ArrayList;
import java.util.*;

public class EmootiBanCache
{
	public static ArrayList <Map <String, String>> cache = null;
	
    public EmootiBanCache() {
    	if (cache==null)
    		cache = new ArrayList <Map<String, String>>();
    	}
    	
    
		public void add(Map <String, String> map) 
			{
			cache.add(map);
			}
		
		public ArrayList <Map <String, String>> get() 
			{
			return cache;
			}
		public ArrayList <Map <String, String>> getCopy() 
			{
	
			ArrayList <Map <String, String>> cacheCopy = new ArrayList <Map<String, String>>();
			cacheCopy=(ArrayList <Map <String, String>>)(cache.clone());
			return cache;
			}
		public int size()
			{
			
			if (cache== null)
				return 0;
			return cache.size();
			}
}