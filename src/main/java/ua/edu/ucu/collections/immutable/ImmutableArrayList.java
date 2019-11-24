package ua.edu.ucu.collections.immutable;

import java.util.Arrays;


public class ImmutableArrayList implements ImmutableList {

    private static ImmutableArrayList temp = new ImmutableArrayList();
    private static ImmutableList fin;
    private Object[] arr;
    private int len;

    public ImmutableArrayList() {
        arr = new Object[0];
        len = 0;
    }

    @Override
    public ImmutableList add(Object e) {
        ImmutableArrayList fin = new ImmutableArrayList();
        fin.arr = Arrays.copyOf(arr, len + 1);
        fin.arr[len] = e;
        fin.len = len + 1;
        return fin;
    }


    @Override
    public ImmutableList add(int index, Object e) {
        if (index >= len) {
            throw new IndexOutOfBoundsException("Index out of range.");
        }
        ImmutableList fin;
        ImmutableArrayList temp = new ImmutableArrayList();

        temp.arr = Arrays.copyOf(arr, index);
        temp.len = index;
        fin = temp.add(e);
        for (int i = index; i < len; i++) {
            fin = fin.add(arr[i]);
        }
        return fin;
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        ImmutableList fin;

        temp.arr = Arrays.copyOf(arr, len);
        temp.len = len;
        fin = temp;
        for (Object i : c) {
            fin = fin.add(i);
        }
        return fin;
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        if (index >= len) {
            throw new IndexOutOfBoundsException("Index out of range.");
        }
        ImmutableList fin;

        temp.arr = Arrays.copyOf(arr, len);
        temp.len = len;
        fin = temp;
        for (Object i : c) {
            fin = fin.add(index, i);
            index++;
        }
        return fin;
    }

    @Override
    public Object get(int index) {
        if (index >= len) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        return arr[index];
    }

    @Override
    public ImmutableList remove(int index) {
        if (index >= len) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        ImmutableList fin;
        temp.arr = Arrays.copyOf(arr, index);
        temp.len = index;
        fin = temp;
        for (int i = index + 1; i < len; i++) {
            fin = fin.add(arr[i]);
        }
        return fin;
    }

    @Override
    public ImmutableList set(int index, Object e) {
        if (index >= len) {
            throw new IndexOutOfBoundsException();
        }
        temp.arr = Arrays.copyOf(arr, index);
        temp.len = index;
        fin = temp.add(e);
        for (int i = index + 1; i < len; i++) {
            fin = fin.add(arr[i]);
        }
        return fin;
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < len; i++) {
            if (arr[i] == e) {
                return i;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return len;
    }

    @Override
    public ImmutableList clear() {
        ImmutableArrayList ret = new ImmutableArrayList();
        return ret;
    }

    @Override
    public boolean isEmpty() {
        if (len == 0) {
            return true;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        return arr;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }


}

