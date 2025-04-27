package com.lotashinski;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Hello world!
 */
public class App {
	
	static private Object placebo = new Object();
	
	
    public static void main(String[] args) {
    	Map<BadObject, Object> map = new HashMap<>();
    	
    	System.out.println("Start experiment");
    	map.put(new BadObject(), placebo);
    	
    	String firstType = getNameOfFirstNode(map);
    	System.out.println("First type (simpleName): " + firstType);
    	
    	int counter = 1;
    	String currentType = null;
    	
    	while(firstType == (currentType = getNameOfFirstNode(map))) {
    		map.put(new BadObject(), placebo);
    		counter++;
    	}
    	
    	System.out.println(String.format("Map changed node type to %s. Bucket size: %d", currentType, counter));
    }
    
    private static <K, V> String getNameOfFirstNode(Map<K, V> map) {
    	return extractFirstNode(map)
    			.map(e -> e.getClass().getSimpleName())
    			.orElse("null");
    }
    
    private static <K, V> Optional<Map.Entry<K, V>> extractFirstNode(Map<K, V> map) {
    	return map.entrySet().stream().findFirst();
    }
}
