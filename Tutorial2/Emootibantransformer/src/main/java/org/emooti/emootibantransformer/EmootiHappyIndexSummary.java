package org.emooti.emootibantransformer;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author utakapp
 * Create summary for EmootiBan selection
 */
public class EmootiHappyIndexSummary implements EmootiBanFields{
	HashMap<String, HashMap> resultMap = new <String, HashMap> HashMap();
	
		
private boolean timespan(Map item)
	{
		return true;
	}

protected Object summary(ArrayList <HashMap <String,String>> foundList, Map <String, String> map)
	{	
		ArrayList<HashMap <String, String>> result=new ArrayList <HashMap <String, String>>();
		result=generateSummary(foundList);		
		return result;
	}

private ArrayList <HashMap <String, String>> generateSummary(ArrayList <HashMap <String,String>> cache)
	{
	/**create resultMap from sorted ArrayList*/
	//Aggregate
	int i=0;
	int cachesize=cache.size();
	int item1vote=1;

    while (i<cachesize)
        	{
			Map <String, String> item1 = cache.get(i);
			Map <String, String> item2 = null;		

    		if (i==cachesize-1) //last
    			{
       		    item1.put(item1.get(emootiID_f), new Integer(item1vote).toString());
       		    i++; 
    			item1vote++;
    			}
    		else
    			{
	    		item2 = cache.get(i+1);
	    			  		
	    		if (item1!=null && item1.containsKey(vote_f))
	    			{
	    			item1vote=new Integer (item1.get(vote_f));
	     			}
	    		else
	    			{
	    			item1.put(vote_f, "1"); // initialize
	    		    item1vote=1;
	        		}
	  		
	    		String date1 = item1.get(datetime_f).substring(0,8);
	    		String date2 = item2.get(datetime_f).substring(0,8);
				String color1 = null;
				String color2 = null;
	
	    		if (item1.containsKey(emootiID_f) && item2.containsKey(emootiID_f))
	    			{
	    			color1 = item1.get(emootiID_f).substring(0,1);
	    			color2 = item2.get(emootiID_f).substring(0,1);
	    			}
	    		
	    		if (date1.equals(date2) && (color1!=null && color2!=null && color1.equals(color2))
	    					&& item1.get(att_f).equals(item2.get(att_f))
	    					&& item1.get(hashtag_f).equals(item2.get(hashtag_f)) )
		    			{
		       		    item1.put(item1.get(emootiID_f), new Integer(item1vote).toString());
		    			cache.remove(i+1);
		    			cachesize--;
		       			item1vote++;
		    			}
		    	else
	    			{
	       		    item1.put(item1.get(emootiID_f), new Integer(item1vote).toString());
	       		    i++;
		    		item1vote=0;
		    		}
	    			// Add Vote
	
	    		item1.put(vote_f, new Integer(item1vote).toString());
    			}
    		}
 
	return cache;
		
	}

private ArrayList getDays(String start, int nrof)
	{
	// Generate a String for Date and Time
	ArrayList<String> days = new ArrayList<String>();
	
	Calendar myCal = new GregorianCalendar();
	
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

}