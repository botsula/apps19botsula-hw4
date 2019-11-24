package ua.edu.ucu.collections.immutable;

import java.util.Arrays;


public class ImmutableLinkedList implements ImmutableList {


    private Node head;
    private Node tail;
    private int len;

    public static class Node {
        public Object val;
        public Node next;

        Node(Object val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public ImmutableLinkedList() {
        head = new Node(null, null);
        tail = new Node(null, null);
        len = 0;
    }

    public ImmutableLinkedList addFirst(Object e) {
        ImmutableLinkedList temp = new ImmutableLinkedList();
        if (len == 0) {
            temp.head.val = e;
            temp.tail.val = temp.head.val;
        } else {
            temp.tail = tail;
            temp.len = len;
            temp.head = new Node(e, head);
        }
        temp.len++;
        return temp;
    }


    public ImmutableLinkedList addLast(Object e) {
        ImmutableLinkedList temp = new ImmutableLinkedList();
        if (len == 0) {
            temp.head.val = e;
            temp.tail.val = e;
        } else {
            temp.head = head;
            temp.tail = head;
            while (temp.tail.next != null) {
                temp.tail = temp.tail.next;
            }
            temp.tail.next = new Node(e, null);
            temp.tail = temp.tail.next;
        }
        temp.len = len + 1;
        return temp;
    }

    public Object getFirst() {
        if (len == 0) {
            throw new IndexOutOfBoundsException();
        }
        return head.val;
    }

    public Object getLast() {
        if (len == 0) {
            throw new IndexOutOfBoundsException();
        }
        return tail.val;
    }

    public ImmutableLinkedList removeFirst() {
        if (len == 0) {
            throw new IndexOutOfBoundsException();
        }
        ImmutableLinkedList temp = new ImmutableLinkedList();
        temp.head = head.next;
        temp.len = len - 1;
        temp.tail = tail;
        return temp;
    }

    public ImmutableLinkedList removeLast() {
        if (len == 0) {
            throw new IndexOutOfBoundsException();
        }
        ImmutableLinkedList temp = new ImmutableLinkedList();
        temp.head = head;
        temp.tail = head;
        for (int i = 0; i < len - 2; i++) {
            temp.tail = temp.tail.next;
        }
        temp.tail.next = null;
        temp.len = len - 1;
        return temp;
    }


    public ImmutableList add(Object e) {
        ImmutableLinkedList temp = new ImmutableLinkedList();
        if (len == 0) {
            temp.head.val = e;
            temp.tail.val = e;
        } else {
            temp.head = head;
            temp.tail = head;
            while (temp.tail.next != null) {
                temp.tail = temp.tail.next;
            }
            temp.tail.next = new Node(e, null);
            temp.tail = temp.tail.next;
        }
        temp.len = len + 1;
        return temp;
    }

    @Override
    public ImmutableList add(int index, Object e) {
        ImmutableLinkedList temp = new ImmutableLinkedList();
        if (index >= len) {
            throw new IndexOutOfBoundsException();
        }
        temp.head = head;
        temp.tail = tail;
        temp.len = len;
        if (index == 0) {
            Node other = new Node(e, temp.head);
            temp.head = other;
        } else {
            Node cur = new Node(temp.head.val, temp.head.next);
            cur = temp.head;
            for (int i = 0; i < index - 1; i++) {
                cur = cur.next;
            }
            Node other = cur.next;
            cur.next = new Node(e, other);
        }
        temp.len++;
        return temp;
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        ImmutableLinkedList temp = new ImmutableLinkedList();
        ImmutableList fin;
        temp.head = head;
        temp.tail = tail;
        fin = temp;
        for (Object i : c) {
            fin = fin.add(i);
        }
        return fin;
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        ImmutableLinkedList temp = new ImmutableLinkedList();

        if (index >= len) {
            throw new IndexOutOfBoundsException();
        }


        ImmutableList fin;
        temp.head = head;
        temp.tail = tail;
        temp.len = len;
        fin = temp;
        for (int i = 0; i < c.length; i++) {
            fin = fin.add(index, c[i]);
            index++;
        }
        return fin;
    }

    @Override
    public Object get(int index) {
        if (index >= len) {
            throw new IndexOutOfBoundsException();
        }
        Node cur = new Node(head.val, head.next);
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    @Override
    public ImmutableList remove(int index) {
        ImmutableLinkedList temp = new ImmutableLinkedList();
        System.out.println(this.size());
        if (index >= len) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        temp.head = temp.tail = head;
        temp.len = len;
        if (index == 0) {
            temp.head = temp.head.next;
        } else {
            for (int i = 0; i < index - 1; i++) {
                temp.tail = temp.tail.next;
            }
            temp.tail.next = temp.tail.next.next;
            while (temp.tail.next != null) {
                temp.tail = temp.tail.next;
            }
        }
        temp.len = len - 1;
        return temp;
    }

    @Override
    public ImmutableList set(int index, Object e) {
        ImmutableLinkedList temp = new ImmutableLinkedList();
        if (index >= len) {
            throw new IndexOutOfBoundsException();
        }
        temp.head = head;
        temp.tail = tail;
        temp.len = len;
        return temp.remove(index).add(index, e);
    }

    @Override
    public int indexOf(Object e) {
        ImmutableLinkedList temp = new ImmutableLinkedList();
        temp.tail = head;
        int counter = 0;
        while (temp.tail != null) {
            if (temp.tail.val == e) {
                return counter;
            }
            counter++;
            temp.tail = temp.tail.next;
        }
        throw new IndexOutOfBoundsException("No such element.");
    }

    @Override
    public int size() {
        return len;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return len == 0;
    }

    @Override
    public Object[] toArray() {
        ImmutableLinkedList temp = new ImmutableLinkedList();
        if (len == 0) {
            return new Object[0];
        }
        Object[] array = new Object[len];
        int counter = 0;
        temp.head = temp.tail = head;
        while (temp.tail != null) {
            array[counter] = temp.tail.val;
            temp.tail = temp.tail.next;
            counter++;
        }
        return array;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
