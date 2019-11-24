package ua.edu.ucu.tries;

import ua.edu.ucu.iterators.TrieIterator;


public class RWayTrie implements Trie {

    private TrieNode new_node;
    private final TrieNode root;
    private int size;

    public RWayTrie() {
        root = new TrieNode(' ');
        size = 0;
    }


    @Override
    public void add(Tuple t) {
        int index;
        TrieNode cur_node = root;
        for (int i = 0; i < t.weight; i++) {
            index = t.term.charAt(i) - 'a';
            if (cur_node.children[index] == null) {
                cur_node.children[index] = new TrieNode(t.term.charAt(i));
            }
            cur_node = cur_node.children[index];
        }
        cur_node.end = true;
        cur_node.isWord = true;
        size++;
    }

    @Override
    public boolean contains(String word) {
        int index;
        TrieNode cur_node = root;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if (cur_node.children[index] == null) {
                return false;
            } else {
                cur_node = cur_node.children[index];
            }
        }

        if (cur_node.end) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String word) {
        int index;
        TrieNode cur_node = root;
        if (!(contains(word))) return false;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if (cur_node.children[index] != null) {
                cur_node = cur_node.children[index];
            }
            if (cur_node.end) {
                cur_node.end = false;
                cur_node.isWord = false;
                size--;
            }
        }
        return true;
    }

    public TrieNode getNode(String word) {
        int index;
        int flag = 0;
        TrieNode cur_node = root;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if (cur_node.children[index] != null) {
                cur_node = cur_node.children[index];
                flag++;
            }
        }
        if (flag == word.length()) {
            return cur_node;
        }
        return null;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        TrieNode node = getNode(s);
        return TrieIterator.words(node, s);
    }

    @Override
    public int size() {
        return size;
    }
}
