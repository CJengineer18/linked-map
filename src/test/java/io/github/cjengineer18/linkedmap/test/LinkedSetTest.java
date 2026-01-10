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
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.github.cjengineer18.linkedmap.LinkedSet;

/**
 * 
 *
 * @author cjengineer18
 */
public class LinkedSetTest {

	private LinkedSet<Object> testSet;
	private Set<Object> sampleSet;

	@Before
	public void setUp() throws Exception {
		sampleSet = new HashSet<Object>();

		sampleSet.add("bar0");
		sampleSet.add(123);
		sampleSet.add(12L);

		sampleSet = Collections.unmodifiableSet(sampleSet);
	}

	@Test
	public void testLinkedSetCollectionQ() throws Exception {
		Object theSampleSecondObject;
		Object theTestSecondObject;

		testSet = new LinkedSet<Object>(sampleSet);

		theSampleSecondObject = sampleSet.toArray()[1];
		theTestSecondObject = testSet.toArray()[1];

		Assert.assertEquals("New set size must be the same as sample", sampleSet.size(), testSet.size());
		Assert.assertEquals("The objects must be the same", theSampleSecondObject, theTestSecondObject);
	}

	@Test
	public void testAddObject() throws Exception {
		testSet = new LinkedSet<Object>();

		Assert.assertTrue("The method 'add' must return true", testSet.add(12));
		Assert.assertEquals("New set size must be 1", 1, testSet.size());
	}

	@Test
	public void testRemoveObject() throws Exception {
		testSet = new LinkedSet<Object>(sampleSet);

		Assert.assertTrue("The method 'remove' must return true", testSet.remove(123));
		Assert.assertEquals("New set size must be 2", 2, testSet.size());
	}

	@Test
	public void testRemoveIfPredicateObject() throws Exception {
		testSet = new LinkedSet<Object>(sampleSet);

		Assert.assertTrue("The method 'removeIf' must return true", testSet.removeIf(o -> o.equals(12L)));
		Assert.assertEquals("New set size must be 2", 2, testSet.size());
	}

	@After
	public void restore() {
		testSet = null;
		sampleSet = null;
	}

}
