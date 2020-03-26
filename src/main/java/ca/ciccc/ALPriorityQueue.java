package ca.ciccc;

import java.util.*;

public class ALPriorityQueue<K extends Comparable, V> implements VCPriorityQueue<K, V>{
    private static final int DEFAULT_INITIAL_CAPACITY = 11;
    private ArrayList<Entry<K, V>> data;

    public ALPriorityQueue() {
        this.data = new ArrayList<>(DEFAULT_INITIAL_CAPACITY);
    }

    public ALPriorityQueue(int initialCapacity) {
        if (initialCapacity >= 0) {
            this.data = new ArrayList<>(initialCapacity);
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
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
        Entry<K, V> newEntry = new Entry<>(key, value);
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
        if (data.size() == 0) {
            return null;
        }
        Entry<K, V> highestPriority = data.get(0);
        for (Entry<K, V> datum : data) {
            if (datum.getKey().compareTo(highestPriority.getKey()) < 0) {
                highestPriority = datum;
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
        if (data.size() == 0) {
            return null;
        }
        Entry<K, V> highestPriority = data.get(0);
        for (Entry<K, V> datum : data) {
            if (datum.getKey().compareTo(highestPriority.getKey()) < 0) {
                highestPriority = datum;
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
        }
        while (!other.isEmpty()) {
            Entry<K, V> newEntry = other.dequeueMin();
            data.add(newEntry);
        }
        return this;
    }

    @Override
    public String toString() {
        String ALPriorityQueue = "";
        for (Entry<K, V> entry : data) {
            ALPriorityQueue += entry.toString() + "\n";
        }
        return ALPriorityQueue;
    }
    public static void main(String[] args) {
        ALPriorityQueue<String, String> test = new ALPriorityQueue<>();
        ALPriorityQueue<String, String> test2 = new ALPriorityQueue<>();

        System.out.println(test.enqueue("sk", "Slovakia"));
        System.out.println(test.enqueue("pl","Poland"));
        test.enqueue("cz", "Czech Republic");
        test.enqueue("ca", "Canada");
        test.enqueue("no", "Norway");

        test2.enqueue("gb", "Great Britain");
        test2.enqueue("au", "Australia");
        test2.enqueue("fr", "France");

        System.out.println(test.isEmpty());
        System.out.println(test.size()); // Is there size?
        System.out.println(test.dequeueMin());
        System.out.println(test);
        System.out.println(test.size());
        System.out.println(test.peek());
        System.out.println(test.size());
        System.out.println(test.merge(test2));
        System.out.println(test);
        System.out.println(test.size());
    }

}

