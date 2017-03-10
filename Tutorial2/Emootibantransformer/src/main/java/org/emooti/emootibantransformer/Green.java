package org.emooti.emootibantransformer;

import java.util.*;
public class Green {
		public Object sayHello(String Name)
			{
			org.emooti.emootibantransformer.EmootiDesign ed = new org.emooti.emootibantransformer.EmootiDesign();
			ArrayList<Emooti> al = new ArrayList<Emooti>();
			Emooti em = new Emooti();
			al.add(em);
			em=new Emooti();
			em.setEmootiID("01.01.01");
			al.add(em);
			em=new Emooti();
			em.setEmootiID("02.01.01");
			al.add(em);
			ed.setEmooti(al);
			return (Object)ed;
			}
		
}
