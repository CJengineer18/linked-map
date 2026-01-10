/*
 * Copyright 2026 Cristian José Jiménez Diazgranados
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

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

/**
 * A set backed up by a LinkedMap
 * 
 * @param <E> The element class
 *
 * @author cjengineer18
 */
public class LinkedSet<E> extends AbstractSet<E> {

	private Set<E> realSetImpl;

	public LinkedSet() {
		realSetImpl = Collections.newSetFromMap(new LinkedMap<E, Boolean>());
	}

	public LinkedSet(Collection<? extends E> collection) {
		this();
		addAll(collection);
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkedSetIterator();
	}

	@Override
	public int size() {
		return realSetImpl.size();
	}

	@Override
	public boolean add(E e) {
		return realSetImpl.add(e);
	}

	private class LinkedSetIterator implements Iterator<E> {

		private Iterator<E> mainIterator = realSetImpl.iterator();

		@Override
		public boolean hasNext() {
			return mainIterator.hasNext();
		}

		@Override
		public E next() {
			return mainIterator.next();
		}

		@Override
		public void remove() {
			mainIterator.remove();
		}

	}

}
