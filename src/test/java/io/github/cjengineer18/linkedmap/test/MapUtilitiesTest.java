package io.github.cjengineer18.linkedmap.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;

import io.github.cjengineer18.linkedmap.LinkedMap;

public class MapUtilitiesTest {

	private LinkedMap<String, Object> testMap;
	private Map<String, Object> sampleMap;

	@Before
	public void setUp() throws Exception {
		sampleMap = new HashMap<String, Object>(4);

		sampleMap.put("key1", "string");
		sampleMap.put("key2", 20);
		sampleMap.put("key3", 36L);
		sampleMap.put("key4", true);

		sampleMap = Collections.unmodifiableMap(sampleMap);
	}

}
