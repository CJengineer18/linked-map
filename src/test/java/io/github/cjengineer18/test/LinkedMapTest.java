/*
 * Copyright 2023 Cristian José Jiménez Diazgranados
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the “Software”), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.cjengineer18.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.github.cjengineer18.utils.LinkedMap;

public class LinkedMapTest {

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

	@Test
	public void testClear() {
		testMap = new LinkedMap<String, Object>(sampleMap);

		testMap.clear();

		Assert.assertEquals("Map must be empty", 0, testMap.size());
		Assert.assertTrue("Map's isEmpty must be true", testMap.isEmpty());
	}

	@Test
	public void testLinkedMap() {
		testMap = new LinkedMap<String, Object>();

		Assert.assertEquals("Map must be empty", 0, testMap.size());
		Assert.assertTrue("Map's isEmpty must be true", testMap.isEmpty());
	}

	@Test
	public void testLinkedMapMapOfQextendsKeyQextendsValue() {
		testMap = new LinkedMap<String, Object>(sampleMap);

		Assert.assertEquals("Both maps have same size", sampleMap.size(), testMap.size());
		Assert.assertTrue("Key 'key1' must be present", testMap.containsKey("key1"));
		Assert.assertTrue("Value '(long) 36' must be present", testMap.containsValue((long) 36));
	}

	@Test
	public void testEntrySet() {
		testMap = new LinkedMap<String, Object>(sampleMap);

		Assert.assertNotNull("Set must exist", testMap.entrySet());
		Assert.assertEquals("Both sets have same size", sampleMap.entrySet().size(), testMap.entrySet().size());
		Assert.assertArrayEquals("Both sets has similar elements", sampleMap.entrySet().toArray(),
				testMap.entrySet().toArray());
	}

	@Test
	public void testPutKeyValue() {
		// Part 1: new key/value pair

		testMap = new LinkedMap<String, Object>();

		Assert.assertNull("New key returns null as oldValue", testMap.put("dummy", 200));
		Assert.assertEquals("Key 'dummy' has value 200", 200, testMap.get("dummy"));

		// Part 2: modify key/value

		testMap = new LinkedMap<String, Object>(sampleMap);

		Assert.assertEquals("Change value for 'key2' returns original value", sampleMap.get("key2"),
				testMap.put("key2", 30));
		Assert.assertEquals("Key 'key2' has new value 30", 30, testMap.get("key2"));
	}

}
