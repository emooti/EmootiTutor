package org.emooti.emootibantransformer;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SortMap {

    private static final String Collections = null;

	public static void main(String[] args) {
        HashMap<String, HashMap> map = new HashMap<String, HashMap>();
        
        HashMap<String, String> map1 = new HashMap<String, String>();
        HashMap<String, String> map2 = new HashMap<String, String>();
        HashMap<String, String> map3 = new HashMap<String, String>();

        
        ValueComparator bvc = new ValueComparator(map);
        
        TreeMap<String, HashMap> sorted_map = new TreeMap<String, HashMap>(bvc);
        
        map1.put("Team","myteam");
        map1.put("Hash","fun");
        map2.put("Team","ateam");
        map2.put("Hash","fun");
        map3.put("Team","ateam");
        map3.put("Hash","code");

        map.put("myteam  fun", map1);
        map.put("ateam   fun", map3);
        map.put("ateam   code", map2);

        System.out.println("unsorted map: " + map);
        sorted_map.putAll(map);
        System.out.println("results: " + sorted_map);
    }
}

class ValueComparator implements Comparator<String> {
    Map<String, HashMap> base;

    public ValueComparator(Map<String, HashMap> base) {
        this.base = base;
    }

    public int compare(String a, String b) {
    	
        if (a.compareTo (b)>0) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}
