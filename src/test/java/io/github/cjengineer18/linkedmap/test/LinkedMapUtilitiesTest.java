package io.github.cjengineer18.linkedmap.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;

import io.github.cjengineer18.linkedmap.LinkedMap;

public class LinkedMapUtilitiesTest {

	private LinkedMap<String, Object> testMap;
	private Map<String, Integer> sampleMap;

	@Before
	public void setUp() throws Exception {
		sampleMap = new HashMap<String, Integer>(4);

		sampleMap.put("key1", 20);
		sampleMap.put("key2", 36);
		sampleMap.put("key3", 48);
		sampleMap.put("key4", -100);

		sampleMap = Collections.unmodifiableMap(sampleMap);
	}

}
