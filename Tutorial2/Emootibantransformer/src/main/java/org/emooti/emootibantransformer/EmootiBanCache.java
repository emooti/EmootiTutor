package org.emooti.emootibantransformer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author utakapp
 * Cache for EmootiBan
 */
public class EmootiBanCache implements EmootiBanFields
{
	// Static Variable creates memory leak!!
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
		
		public ArrayList <Map <String, String>> getCopybyAttributes(Map <String, String> map) 
			{
			/* return Cache ordered and selected by Attributes*/
			ArrayList <Map <String, String>> cacheCopy = new ArrayList <Map<String, String>>();
			System.out.println("Attributes:"+map);

			
			for (int i = 0; i < cache.size(); i++)
		        	{
	        		Map <String, String> item = cache.get(i);
	        			        		
		    		Set set1 = map.keySet();
		    	
		    	    // Iterate over the Set to find match
		    	    Iterator iter1 = set1.iterator();
		    	    
//Add rest
		    	    boolean found=true;
		    	    while (iter1.hasNext())
		    	    	{
		    	        String key = iter1.next().toString();
		    	        if (!item.containsKey(key) || !item.get(key).equals(map.get(key)))
		    	        	{
		    	        	found=false;
		    	        	}
		    			}
		    	    
	        		if (found)
	        			cacheCopy.add(item);
		        	}
			System.out.println("Cache byAttributes:"+cacheCopy);
			return cacheCopy;
			
			}

		public int size()
			{
			/* Size of Cache*/
			if (cache== null)
				return 0;
			return cache.size();
			}
}