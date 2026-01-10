/* 
 * Copyright (c) 2026 Cristian José Jiménez Diazgranados
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.github.cjengineer18.linkedmap.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
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

	@After
	public void restore() {
		testMap = null;
		sampleMap = null;
	}

}
