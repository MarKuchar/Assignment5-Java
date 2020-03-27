package ca.ciccc;

import java.util.ArrayList;

public class BHPriorityQueue<K extends Comparable, V> implements VCPriorityQueue<K, V>{
    private static final int DEFAULT_INITIAL_CAPACITY = 100;
    private int size;
    private Entry<K, V>[] data;

    public BHPriorityQueue() {
        this.data = new Entry[DEFAULT_INITIAL_CAPACITY];
    }


    /*
     Add extend grow size method.
     */

    /**
     * Returns the number of items in the priority queue.
     * @return number of items
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Tests whether the priority queue is empty.
     * @return true if the priority queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    private boolean isLeft(int i) {
        return (size % 2 == 1);
    }

    /**
     * Inserts a key-value pair and returns the entry created.
     * @param key     the key of the new entry
     * @param value   the associated value of the new entry
     * @return the entry storing the new key-value pair
     * @throws IllegalArgumentException if the key is unacceptable for this queue
     */
    @Override
    public Entry<K, V> enqueue(K key, V value) {
        Entry newEntry = new Entry(key, value);
        if (key == null) {
            throw new IllegalArgumentException("Invalid key.");
        }

        if (size == 0) {
            data[size] = newEntry;
            size++;
            return newEntry;
        }

        int i = (size - 1) / 2;
        boolean isLeft = false;
        if (size % 2 == 1) {
            isLeft = true;
        }

        if (data[i].getKey().compareTo(key) < 0) {
            data[size] = newEntry;
        }

        while (data[i].getKey().compareTo(key) > 0) {
            Entry temp = data[i];
            data[i] = newEntry;
            if (!isLeft) {
                data[(2 * i) + 2] = temp;
                if (i % 2 == 1) {
                    isLeft = true;
                }
                i = (i - 1) / 2;
            } else {
                data[(2 * i) + 1] = temp;
                if (i % 2 == 0) {
                    isLeft = false;
                }
                i = (i - 1) / 2;
            }
        }
        size++;
        return newEntry;
    }

    /**
     * Returns (but does not remove) an entry with minimal key.
     * @return entry having a minimal key (or null if empty)
     */
    @Override
    public Entry peek() {
        if (size == 0) {
            return null;
        }
        return data[0];
    }

    /**
     * Removes and returns an entry with minimal key.
     * @return the removed entry (or null if empty)
     */
    @Override
    public Entry dequeueMin() {
        if (size == 0) {
            return null;
        }
        Entry<K, V> highestPriority = data[0];
        Entry<K, V> lastEntry = data[size - 1];
        data[0] = lastEntry;
        if (size == 1) {
            size--;
            return highestPriority;
        }
        System.arraycopy(data, 0, data, 0,size - 2);
        size--;

        int i = 0;
        while (data[(2 * i) + 1] != null) {
            if (data[(2 * i) + 2].getKey().compareTo(data[(2 * i) + 1].getKey()) >= 0 || data[(2 * i) + 2] == null) {
                if (lastEntry.getKey().compareTo(data[(2 * i) + 1].getKey()) > 0) {
                    Entry<K, V> temp = data[(2 * i) + 1];
                    data[(2 * i) + 1] = lastEntry;
                    data[i] = temp;
                    i = (2 * i) + 1;
                } else {
                    return highestPriority;
                }
            } else {
                if (lastEntry.getKey().compareTo(data[(2 * i) + 2].getKey()) > 0 && size != 1) {
                    Entry<K, V> temp = data[(2 * i) + 2];
                    data[(2 * i) + 2] = lastEntry;
                    data[i] = temp;
                    i = (2 * i) + 1;
                } else {
                    return highestPriority;
                }
            }
        }
        return highestPriority;
    }

    /**
     * Merges another priority queue of the same type.
     * @return the merged priority queue
     */
    @Override
    public VCPriorityQueue<K, V> merge(VCPriorityQueue<K, V> other) {
        int a = this.size;
        int b = other.size();
        Entry<K, V>[] mergedQueue = new Entry[DEFAULT_INITIAL_CAPACITY];
        for (int i = 0; i < a; i++) {
            mergedQueue[i] = data[i];
        }
        for (int i = 0; i < b; i++) {
            // Entry<K, V> entryAdd = other.dequeueMin();
            mergedQueue[a + i] = other.dequeueMin();
        }
        size = a + b;
        int i = size - 1;

        while (i != 0) {
            if (mergedQueue[(i - 1) / 2].getKey().compareTo(mergedQueue[i].getKey()) > 0 && isLeft(i)) {
                Entry<K, V> tempP = mergedQueue[(i - 1) / 2];
                Entry<K, V> tempC = mergedQueue[i];
                mergedQueue[i] = tempP;
                mergedQueue[(i - 1) / 2] = tempC;
            } else if (mergedQueue[(i - 1) / 2].getKey().compareTo(mergedQueue[i].getKey()) > 0 && !isLeft(i)) {
                Entry<K, V> tempP = mergedQueue[(i - 1) / 2];
                Entry<K, V> tempC = mergedQueue[i];
                mergedQueue[i] = tempP;
                mergedQueue[(i - 1) / 2] = tempC;
            }
            i -= 1;
        }
        this.data = mergedQueue;
        return this;
    }

    @Override
    public String toString() {
        String BHPriorityQueue = "";
        for (int i = 0; i < size; i++) {
            BHPriorityQueue += data[i] + "\n";
        }
        return BHPriorityQueue;
    }
}
