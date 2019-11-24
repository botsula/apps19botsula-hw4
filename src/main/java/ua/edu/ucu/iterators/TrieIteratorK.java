package ua.edu.ucu.iterators;

import java.util.Iterator;

public class TrieIteratorK implements Iterator {

    private Iterator<String> iterator;
    private int k;
    private String next;
    private int alreadyIterSizes = 0;
    private int maxLength = -1;

    TrieIteratorK(Iterator iterator, int k) {
        this.iterator = iterator;
        this.k = k;
        findNext();
    }

    public void findNext() {
        if (iterator.hasNext()) {
            next = iterator.next();
            if (alreadyIterSizes == k && next.length() > maxLength) {
                next = null;
                return;
            }
            if (next.length() > maxLength) {
                maxLength = next.length();
                alreadyIterSizes++;
            }
        } else {
            next = null;
        }
    }


    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public String next() {
        String str = next;
        findNext();
        return str;
    }

    public static Iterable<String> words(Iterable<String> iterator, int k) {
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return new TrieIteratorK(iterator.iterator(), k);
            }
        };
    }
}
