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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import io.github.cjengineer18.linkedmap.interfaces.functional.IIndexedBiConsumer;

/**
 * A map that uses a {@code LinkedList} as it's core.
 * 
 * @author cjengineer18
 * 
 * @version 1.0.2
 *
 * @param <Key>   The key.
 * @param <Value> The value.
 * 
 * @see LinkedList
 * @see AbstractMap
 */
public class LinkedMap<Key, Value> extends AbstractMap<Key, Value> {

	// Fields

	// The map's core.
	private LinkedList<SimpleEntry<Key, Value>> core;

	// The map's set.
	private LinkedMapSet set;

	// Constructors

	/**
	 * Creates a empty map.
	 */
	public LinkedMap() {
		core = new LinkedList<SimpleEntry<Key, Value>>();
		set = new LinkedMapSet();
	}

	/**
	 * Creates a map and transfers all elements from {@code map} to this map.
	 * 
	 * @param map The map.
	 */
	public LinkedMap(Map<? extends Key, ? extends Value> map) {
		this();
		putAll(map);
	}

	// Public methods

	public void forEach(IIndexedBiConsumer<Key, Value> consumer) throws Exception {
		LinkedList<Key> keys = new LinkedList<Key>(keySet());

		Key key;

		for (int i = 0; i < size(); i++) {
			key = keys.get(i);

			consumer.accept(key, get(key), i);
		}
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
	 * @param key   The key.
	 * @param value The new value.
	 * 
	 * @return The old value of {@code key} or {@code null} if not exist else if the
	 *         key was associated with {@code null}.
	 * 
	 * @see AbstractMap#put(Object, Object)
	 */
	@Override
	public Value put(Key key, Value value) {
		int index = findEntry(key);
		boolean found = index > -1;
		Value oldValue = null;
		SimpleEntry<Key, Value> entry = found ? core.get(index) : new SimpleEntry<Key, Value>(key, value);

		if (found) {
			oldValue = entry.setValue(value);
		} else {
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
		core = new LinkedList<SimpleEntry<Key, Value>>();
	}

	// Private methods

	/**
	 * Finds the index of the entry based on the key.
	 * 
	 * @param key The key.
	 * 
	 * @return The index of the key, or -1 if not found.
	 */
	private int findEntry(Key key) {
		LinkedList<Key> keys = new LinkedList<Key>(keySet());

		return keys.stream().filter(key::equals).mapToInt(keys::indexOf).findFirst().orElse(-1);
	}

	// Private sub-classes

	/**
	 * LinkedMap's iterator class.
	 * 
	 * @author cjengineer18
	 *
	 */
	private class LinkedMapIterator implements Iterator<Entry<Key, Value>> {

		private Iterator<SimpleEntry<Key, Value>> iterator = core.iterator();

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
