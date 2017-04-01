package org.emooti.emootibantransformer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

import java.util.Iterator;

public class SortArrayList {
ArrayList <String>aSortList=null;
ArrayList <Integer>aSortListLen=new ArrayList<Integer>();

public ArrayList<Map<String, String>> sort (ArrayList<Map<String, String>> list) 
	{
	System.out.println(list);
	Collections.sort(list, new Comparator<Map>() {
	    @Override
	    public int compare(Map o1, Map o2) {
	    	return (compareMap(o1, o2));
	    }
	});

	return list;

	}

public ArrayList<Map<String, String>> sort (ArrayList<Map<String, String>> list, ArrayList <String>sortList) 
	{
	aSortList= sortList;
	ArrayList <Integer>aSortLength=new ArrayList <Integer>(); // max length of each key
	
	for (int i=0; i<list.size(); i++)
		{
		Map m = list.get(i);
		Set set1 = m.keySet();
	    Iterator iter1 = set1.iterator();
	    while (iter1.hasNext())
	    	{
	        String key = iter1.next().toString();
	        String cont=m.get(key).toString();
	        
	        if (sortList !=null && sortList.indexOf(key) >= 0 )
	        	{
	        	int index = sortList.indexOf(key);
	        	if (key.equals(sortList.get(index)))
		        	{
		        	// Prepare a list with max size of Attributes
		        	String attribute=key;
		        	int len=((m.get(key)).toString()).length();
	
		        	int maxlen=len;
		        	if (aSortListLen.contains(index) && aSortListLen.get(index)!=null) //already there
		        		maxlen=(aSortListLen.get(index)).intValue();
		        		
		        	if (maxlen < len)
		        		{
		        		aSortListLen.add(index, new Integer(maxlen));
		        		}
		        			        	
		        	}
	        	}
	    	}
		}
	    
	for (int i=0; i<list.size(); i++)
	System.out.println(list);
	Collections.sort(list, new Comparator<Map>() {
	    @Override
	    public int compare(Map o1, Map o2) {
	    	return (compareMap(o1, o2));
	    }
	});
	
	System.out.println(list);
	return list;
	
	}

	
protected static int compareMap(Map o1, Map o2)
	{
	if (o1!=null && o2 !=null)
		{
		Set set1 = o1.keySet();
		Set set2 = o2.keySet();
	
	    // Iterate over the Set to see what it contains.
	    Iterator iter1 = set1.iterator();
	    Iterator iter2 = set2.iterator();
	    
	    while (iter1.hasNext() && iter2.hasNext())
	    	{
	        Object o11 = iter1.next();
	        Object o22 = iter2.next();
			if ((o1.get(o11).toString()).compareTo(o2.get(o22).toString())<0)
					{
					return -1;
					}
			}
		return 1;
		}
	return 0;
	}
}
