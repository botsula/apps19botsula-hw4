package ua.edu.ucu.iterators;
import ua.edu.ucu.collections.Queue;
import ua.edu.ucu.tries.TrieNode;

import java.util.Iterator;

public class TrieIterator implements Iterator<String>{

    private Queue q;
    private String next;

    public TrieIterator(TrieNode node, String s) {
        q = new Queue();
        q.enqueue(new Object[]{node, s});
        findNext();
    }

    private void findNext() {
        while (!q.queue.isEmpty()) {
            TrieNode cur_node = (TrieNode) ((Object[]) q.peek())[0];
            String pref = (String) ((Object[]) q.dequeue())[1];
            Object n;
            for (int i = 0; i < cur_node.children.length; i++) {
                if (cur_node.children[i] != null){
                    q.enqueue(new Object[]{cur_node.children[i], pref + cur_node.children[i].val});
                }
            }
            if ((cur_node.isWord)) {
                next = pref;
                return;
            }
        }
        next = null;
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

    public static Iterable<String> words(TrieNode node, String s) {
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return new TrieIterator(node, s);
            }
        };
    }
}
