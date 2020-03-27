package ca.ciccc;

import java.util.LinkedList;

public class DLPriorityQueue<K extends Comparable, V> implements VCPriorityQueue<K, V> {
    private LinkedList<Entry<K, V>> data;


    public DLPriorityQueue() {
        this.data = new LinkedList<Entry<K, V>>();
    }

    /**
     * Returns the number of items in the priority queue.
     * @return number of items
     */
    @Override
    public int size() {
        return data.size();
    }

    /**
     * Tests whether the priority queue is empty.
     * @return true if the priority queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return data.isEmpty();
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
        Entry<K, V> newEntry = new Entry<K, V>(key, value);
        if (key == null) {
            throw new IllegalArgumentException("Invalid key.");
        }
        data.add(newEntry);
        return newEntry;
    }

    /**
     * Returns (but does not remove) an entry with minimal key.
     * @return entry having a minimal key (or null if empty)
     */
    @Override
    public Entry<K, V> peek() {
        if (data.isEmpty()) {
            return null;
        }
        Entry<K, V> highestPriority = data.get(0);
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getKey().compareTo(highestPriority.getKey()) < 0) {
                highestPriority = data.get(i);
            }
        }
        return highestPriority;
        }

    /**
     * Removes and returns an entry with minimal key.
     * @return the removed entry (or null if empty)
     */
    @Override
    public Entry<K, V> dequeueMin() {
     if (data.isEmpty()) {
        return null;
    }
    Entry<K, V> highestPriority = data.get(0);
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getKey().compareTo(highestPriority.getKey()) < 0) {
            highestPriority = data.get(i);
            }
        }
        data.remove(highestPriority);
        return highestPriority;
        }

    /**
     * Merges another priority queue of the same type.
     * @return the merged priority queue
     */
    @Override
    public VCPriorityQueue<K, V> merge(VCPriorityQueue<K, V> other) {
        if (other.size() == 0 && data.size() == 0) {
            return null;
        } else {
            while (!other.isEmpty()) {
                Entry<K, V> newEntry = other.dequeueMin();
                data.add(newEntry);
            }
            return this;
        }
    }

    @Override
    public String toString() {
        return "DLPriorityQueue{" +
                "data=" + data +
                '}';
    }
}
