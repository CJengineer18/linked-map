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
package io.github.cjengineer18.linkedmap;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A map that uses a {@code LinkedList} as it's core.
 * 
 * @author cjengineer18
 * 
 * @version 1.0.1
 *
 * @param <Key>
 *            The key.
 * @param <Value>
 *            The value.
 * 
 * @see LinkedList
 * @see AbstractMap
 */
public class LinkedMap<Key, Value> extends AbstractMap<Key, Value> {

	// Fields

	// The map's core.
	private LinkedList<LinkedMapEntry> core;

	// The map's set.
	private LinkedMapSet set;

	// Constructors

	/**
	 * Creates a empty map.
	 */
	public LinkedMap() {
		core = new LinkedList<LinkedMapEntry>();
		set = new LinkedMapSet();
	}

	/**
	 * Creates a map and transfers all elements from {@code map} to this map.
	 * 
	 * @param map
	 *            The map.
	 */
	public LinkedMap(Map<? extends Key, ? extends Value> map) {
		this();
		putAll(map);
	}

	// Override methods

	/**
	 * Returns a set reflecting the map's content.
	 * 
	 * @return A set reflecting the map's content.
	 * 
	 * @see AbstractMap#entrySet()
	 */
	@Override
	public Set<Entry<Key, Value>> entrySet() {
		return set;
	}

	/**
	 * Adds or changes a {@code value} associated by {@code key}.
	 * 
	 * @param key
	 *            The key.
	 * @param value
	 *            The new value.
	 * 
	 * @return The old value of {@code key} or {@code null} if not exist else if
	 *         the key was associated with {@code null}.
	 * 
	 * @see AbstractMap#put(Object, Object)
	 */
	@Override
	public Value put(Key key, Value value) {
		int index = findEntry(key);
		LinkedMapEntry entry = index == -1 ? new LinkedMapEntry(key) : core.get(index);
		Value oldValue = entry.setValue(value);

		if (index == -1) {
			core.add(entry);
		}

		return oldValue;
	}

	/**
	 * Empty this map.
	 * 
	 * @see AbstractMap#clear()
	 */
	@Override
	public void clear() {
		core = new LinkedList<LinkedMapEntry>();
	}

	// Private methods

	/**
	 * Finds the index of the entry based on the key.
	 * 
	 * @param key
	 *            The key.
	 * 
	 * @return The index of the key, or -1 if not found.
	 */
	private int findEntry(Key key) {
		List<Object> keys = Arrays.asList(keySet().toArray());
		int index = -1;

		if (keys.contains(key)) {
			index = keys.indexOf(key);
		}

		return index;
	}

	// Private sub-classes

	/**
	 * LinkedMap's entry class.
	 * 
	 * @author cjengineer18
	 *
	 */
	private class LinkedMapEntry implements Entry<Key, Value> {

		private Key key;
		private Value value;

		public LinkedMapEntry(Key key) {
			this.key = key;
		}

		@Override
		public Key getKey() {
			return key;
		}

		@Override
		public Value getValue() {
			return value;
		}

		@Override
		public Value setValue(Value value) {
			Value oldValue = this.value;

			this.value = value;

			return oldValue;
		}

		@Override
		public String toString() {
			return "LinkedMapEntry [key=" + key + ", value=" + value + "]";
		}

	}

	/**
	 * LinkedMap's iterator class.
	 * 
	 * @author cjengineer18
	 *
	 */
	private class LinkedMapIterator implements Iterator<Entry<Key, Value>> {

		private Iterator<LinkedMapEntry> iterator = core.iterator();

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public Entry<Key, Value> next() {
			return iterator.next();
		}

		@Override
		public void remove() {
			iterator.remove();
		}

	}

	/**
	 * LinkedMap's set class.
	 * 
	 * @author cjengineer18
	 *
	 */
	private class LinkedMapSet extends AbstractSet<Entry<Key, Value>> {

		@Override
		public Iterator<Entry<Key, Value>> iterator() {
			return new LinkedMapIterator();
		}

		@Override
		public int size() {
			return core.size();
		}

	}

}
