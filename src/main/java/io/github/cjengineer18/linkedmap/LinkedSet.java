package io.github.cjengineer18.linkedmap;

import java.util.AbstractSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Collections;

public class LinkedSet<E> extends AbstractSet<E> {

    private Set<E> realSetImpl;

    public LinkedSet() {
        realSetImpl = Collections.newSetFromMap(new LinkedMap<E, Boolean>());
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedSetIterator();
    }

    @Override
    public int size() {
        return realSetImpl.size();
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
