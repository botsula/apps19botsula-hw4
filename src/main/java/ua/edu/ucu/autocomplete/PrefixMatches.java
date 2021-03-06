package ua.edu.ucu.autocomplete;

import ua.edu.ucu.iterators.TrieIteratorK;
import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.Collections;


public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int counter = 0;
        for (String s : strings) {
            if (s.length() > 1) {
                trie.add(new Tuple(s, s.length()));
                counter++;
            }
        }
        return counter;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        if (pref.length() < 2) return (Collections::emptyIterator);
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        if (pref.length() < 2) return (Collections::emptyIterator);
        return TrieIteratorK.words(trie.wordsWithPrefix(pref), k);
    }

    public int size() {
        return trie.size();
    }
}
