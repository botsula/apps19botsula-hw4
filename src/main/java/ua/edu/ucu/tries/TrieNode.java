package ua.edu.ucu.tries;

public class TrieNode {
    static final int ALPHABET_SIZE = 26;
    public char val;
    public boolean end;
    public boolean isWord;
    public ua.edu.ucu.tries.TrieNode[] children;

    TrieNode(char term) {
        this.val = term;
        this.children = new ua.edu.ucu.tries.TrieNode[ALPHABET_SIZE];
        this.isWord = false;
        this.end = false;
    }
}
