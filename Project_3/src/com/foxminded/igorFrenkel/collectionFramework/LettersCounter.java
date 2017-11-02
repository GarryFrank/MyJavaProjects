package com.foxminded.igorFrenkel.collectionFramework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class LettersCounter {
    
	private static final int CACHE_SIZE = 5;
	
	private Map<String, CacheHolder> cache = new LinkedHashMap<String, CacheHolder>();
	
	private class CacheHolder {

		private Map<Character, Integer> cacheData = new LinkedHashMap<Character, Integer>();
		private int priority;

		public void setCacheData(Map<Character, Integer> lettersAmount) {
			cacheData.putAll(lettersAmount);
		}

		public Map<Character, Integer> getCacheData() {
			return cacheData;
		}

		public void setPriority(int priority) {
			this.priority = priority;
		}

		public int getPriority() {
			return priority;
		}

	}
	
	public static void main(String[] args) {

		List <String> inputData = new ArrayList <String>();
		inputData.add("hello world!");
		inputData.add("1234562");
		inputData.add("hello world!");
		inputData.add("collection");
		inputData.add("framework");
		inputData.add("array");
		inputData.add("work");
		LettersCounter lettersCounter = new LettersCounter();
		for (String string : inputData) {
			Map<Character, Integer> lettersAmount = lettersCounter.findLettersAmount(string);
			System.out.println(string);
			for (Map.Entry<Character, Integer> pair : lettersAmount.entrySet()) {
				System.out.println("\"" + pair.getKey() + "\"" + " - " + pair.getValue());
			}
			System.out.println();
		}
	}
		
	Map<Character, Integer> findLettersAmount(String inputData) {

		if (cache.containsKey(inputData)) {
		    CacheHolder cacheHolder = cache.get(inputData);
		    int priority = cacheHolder.getPriority();
		    cacheHolder.setPriority(priority + 1);
			return cache.get(inputData).getCacheData();
		}
		Map<Character, Integer> lettersAmount = new HashMap<Character, Integer>();
		char[] letters = inputData.toCharArray();
		lettersAmount = countLettersAmount(letters);
		CacheHolder cacheHolder = new CacheHolder();
		cacheHolder.setCacheData(lettersAmount);
		cacheHolder.setPriority(1);
		cache.put(inputData, cacheHolder);
		if (cache.size() > CACHE_SIZE) {
			String minKey = cache.keySet().iterator().next();
			CacheHolder minValue = cache.get(minKey);
			for (Map.Entry<String, CacheHolder> pair : cache.entrySet()) {
	               if (pair.getValue().getPriority() < minValue.getPriority()) {
					minValue = pair.getValue();
					minKey = pair.getKey();
				}
			}
			cache.remove(minKey);
		}
		return lettersAmount;
	}

	private Map<Character, Integer> countLettersAmount(char[] letters) {

		Map<Character, Integer> result = new HashMap<>();
		for (int j = 0; j < letters.length; j++) {
			if (result.containsKey(letters[j])){
				result.put(letters[j], result.get(letters[j]) + 1);
			} else {
				result.put(letters[j], 1);
			}
		}
		return result;
	}
}
