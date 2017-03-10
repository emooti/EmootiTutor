package org.emooti.emootibantransformer;


import java.util.ArrayList;

public class EmootiDesign {
	String design = "ampel";
	String num = "3";
	
	private ArrayList<Emooti> emootis=new ArrayList<Emooti>();
	
    public String getDesign()
    {
        return design;
    }
 
    public void setDesign(String design)
    {
        this.design = design;
    }
    public String getNum()
    {
        return num;
    }
 
    public void setNum(String num)
    {
        this.num = num;
    }
    
    public ArrayList<Emooti> getEmooti()
    {
        return emootis;
    }
    public void setEmooti(ArrayList<Emooti> emootis)
    {
        this.emootis = emootis;
    }
 
}
