package ua.edu.ucu.tries;

import ua.edu.ucu.iterators.TrieIterator;

import java.util.Iterator;


public class RWayTrie implements Trie {


    //
//    private TrieNode cur_node;
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

    // чи є слово в тріе
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


    public static void main(String[] args) {
        RWayTrie trie = new RWayTrie();
        Tuple tuple = new Tuple(new String("kar"), 3);
        Tuple tuple2 = new Tuple(new String("ar"), 2);
        Tuple tuple3 = new Tuple(new String("antidote"), 8);
        Tuple tuple4 = new Tuple(new String("antro"), 5);


        trie.add(tuple);
        trie.add(tuple2);
        trie.add(tuple3);
        trie.add(tuple4);

        System.out.println(trie.contains("ar"));
        trie.delete("ar");
        System.out.println(trie.contains("ar"));


        System.out.println(trie.contains("antidote"));
        trie.delete("antidote");
        System.out.println(trie.contains("antidote"));


        trie.delete("antro");
        System.out.println(trie.contains("antro"));

        Iterator<String> it = trie.wordsWithPrefix("").iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
