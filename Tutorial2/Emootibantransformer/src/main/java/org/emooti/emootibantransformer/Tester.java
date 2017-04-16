package org.emooti.emootibantransformer;

import java.util.HashMap;
import java.io.IOException;
import java.lang.Thread;

public class Tester implements EmootiBanFields{

	public static void main(String[] args) {
		testdata();
	}
	protected static void testdata ()
		{
		HashMap <String, String> aMap = new HashMap();

		// Tester
		System.out.println("Hello Tester!");
		//1. Record
		String att="Team A";
		String hashtag="#code";
		String emootiId="4.0.0.0"; //Red

		aMap.put(emootiID_f, emootiId);
		aMap.put(att_f, att);
		aMap.put(hashtag_f, hashtag);
		aMap.put("record", "1");
		JsonConverter js = new JsonConverter();
		String json=js.maptoJson(aMap);
		EmootiBanTransformer eb = new EmootiBanTransformer();
        eb.transformBan(json); //1
        System.out.println(json);
        try
	        {
	        Thread.sleep(500);
	        
	        } catch (Exception e) {
			   System.out.println(e);
			   }
        
        //2. Record
		emootiId="5.0.0.0";  //Orange
		aMap = new HashMap();
		aMap.put("record", "2");
		aMap.put(att_f, att);
		aMap.put(hashtag_f, hashtag);
		aMap.put(emootiID_f, emootiId);
		aMap.put(vote_f, "2");

		json=js.maptoJson(aMap);
        System.out.println(json);

        eb.transformBan(json);  //2
        try
        {
        Thread.sleep(2000);
        
        } catch (Exception e) {
		   System.out.println(e);
		   }
        //3. Record
 		emootiId="5.0.0.0";  //Orange
 		aMap = new HashMap();
 		aMap.put("record", "3");
 		aMap.put(att_f, att);
 		aMap.put(hashtag_f, hashtag);
 		aMap.put(emootiID_f, emootiId);
 		aMap.put(vote_f, "2");

 		json=js.maptoJson(aMap);
        System.out.println(json);

        eb.transformBan(json);  //2
	         try
	         {
	         Thread.sleep(2000);
	         
	         } catch (Exception e) {
	 		   System.out.println(e);
	 		   }
       
        //4
		emootiId="7.0.0.0"; // Green
		aMap = new HashMap();
		aMap.put(emootiID_f, emootiId);
		aMap.put(att_f, att);
		aMap.put(hashtag_f, hashtag);
		aMap.put("record", "4");
		aMap.put(vote_f, "3");

		json=js.maptoJson(aMap);
        System.out.println(json);


        eb.transformBan(json);

        try
	        {
	        Thread.sleep(2000);
	        
	        } catch (Exception e) {
			   System.out.println(e);
			   }
        
		emootiId="7.0.0.0"; // Green
		aMap = new HashMap();
		aMap.put(emootiID_f, emootiId);
		aMap.put(att_f, att);
		aMap.put(hashtag_f, hashtag);
		aMap.put("record", "5");
		aMap.put(vote_f, "3");

		json=js.maptoJson(aMap);
        System.out.println(json);


        eb.transformBan(json);

        try
	        {
	        Thread.sleep(2000);
	        
	        } catch (Exception e) {
			   System.out.println(e);
			   }
 
 
        //Request
		aMap = new HashMap();
		aMap.put(att_f, att);
		aMap.put(hashtag_f, hashtag);

        EmootiHappyIndexAppData hi = new EmootiHappyIndexAppData();
        String request=js.maptoJson(aMap);
        System.out.println("Request:"+request);
        try
	        {
	        System.out.println(" Result:"+hi.find(request));
	        }
        catch (IOException ie)
        	{
        	System.out.println(ie);
        	}
	}

}
