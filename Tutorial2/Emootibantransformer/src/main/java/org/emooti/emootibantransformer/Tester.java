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
		
		// Tester
		System.out.println("Hello Tester!");
		//1
		String att="Team A";
		String hashtag="#code";
		String emootiId="4.0.0.0";

		HashMap <String, String> aMap = new HashMap();
		aMap.put(emootiID_f, emootiId);
		//aMap.put(vote_f, "2");

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
        Thread.sleep(1000);
        
        } catch (Exception e) {
		   System.out.println(e);
		   }
        
        //2
		emootiId="5.0.0.0";  //Orange
		aMap = new HashMap();
		aMap.put("record", "1");
		aMap.put(att_f, att);
		aMap.put(hashtag_f, hashtag);
		aMap.put(emootiID_f, emootiId);

		aMap.put("record", "2");
		//aMap.put(vote_f, "2");

		json=js.maptoJson(aMap);
        System.out.println(json);

        eb.transformBan(json);  //2
        try
        {
        Thread.sleep(2000);
        
        } catch (Exception e) {
		   System.out.println(e);
		   }
        
        //3
		emootiId="7.0.0.0"; // Green
		aMap = new HashMap();
		aMap.put(emootiID_f, emootiId);
		aMap.put(att_f, att);
		aMap.put(hashtag_f, hashtag);
		aMap.put("record", "3");
		//aMap.put(vote_f, "2");

		json=js.maptoJson(aMap);
        System.out.println(json);


        eb.transformBan(json); //3

        try
        {
        Thread.sleep(2000);
        
        } catch (Exception e) {
		   System.out.println(e);
		   }
        //4
		emootiId="7.0.0.0"; //Green
		aMap.put(vote_f, "2");

		aMap = new HashMap();
		//aMap.put(emootiID_f, emootiId);
		aMap.put(att_f, att);
		aMap.put(hashtag_f, hashtag);
		aMap.put("record", "4");
		aMap.put(vote_f, "2");

		json=js.maptoJson(aMap);
        eb.transformBan(json);  //4
        System.out.println(json);


        try
        {
        Thread.sleep(2000);
        
        } catch (Exception e) {
		   System.out.println(e);
		   }
        
        //5

		emootiId="5.0.0.0"; //Orange
		aMap = new HashMap();
		//aMap.put(emootiID_f, emootiId);
		aMap.put(att_f, att);
		aMap.put(hashtag_f, hashtag);
		aMap.put("record", "5");
		aMap.put(vote_f, "2");

		json=js.maptoJson(aMap);
        System.out.println(json);

        eb.transformBan(json); //5

        try
        {
        Thread.sleep(2000);
        
        } catch (Exception e) {
		   System.out.println(e);
		   }
        //6

		//emootiId="5.0.0.0"; //Orange
        
		aMap = new HashMap();
		//aMap.put(emootiID_f, emootiId);
		aMap.put(vote_f, "1");
		aMap.put(att_f, "Team B");
		aMap.put(hashtag_f, hashtag);
		aMap.put("record", "6");
		aMap.put(vote_f, "2");

		json=js.maptoJson(aMap);
        System.out.println(json);

        eb.transformBan(json); //6

        try
        {
        Thread.sleep(2000);
        
        } catch (Exception e) {
		   System.out.println(e);
		   }

        //7
 		aMap = new HashMap();
		//aMap.put(emootiID_f, emootiId);

		aMap.put(att_f, att);
		hashtag="#fun";
		aMap.put(emootiID_f, emootiId);

		aMap.put(hashtag_f, hashtag);
		aMap.put("record", "7");
		aMap.put(vote_f, "2");

		json=js.maptoJson(aMap);
        System.out.println(json);

        eb.transformBan(json); //7
 

        aMap = new HashMap();
   		aMap.put(att_f, att);
		aMap.put(hashtag_f, "#code");



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
