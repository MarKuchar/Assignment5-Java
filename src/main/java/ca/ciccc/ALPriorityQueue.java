package ca.ciccc;

import java.util.*;
import java.util.function.Predicate;

public class ALPriorityQueue<E> implements VCPriorityQueue{
    private static final int DEFAULT_INITIAL_CAPACITY = 11;
    private int size;
    private ArrayList<Entry> data;

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


    public ALPriorityQueue(Collection<? extends E> c) {  // What does it mean?
        // this.data = new ArrayList<Entry>(Arrays.asList(c));
    }


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

    /**
     * Inserts a key-value pair and returns the entry created.
     * @param key     the key of the new entry
     * @param value   the associated value of the new entry
     * @return the entry storing the new key-value pair
     * @throws IllegalArgumentException if the key is unacceptable for this queue
     */
    @Override
    public Entry enqueue(Comparable key, Object value) {
        Entry newEntry = new Entry(key, value);
        if (key == null) {
            throw new IllegalArgumentException("Invalid key.");
        } else if (size == 0) {
            data.add(newEntry);
            size += 1;
            return newEntry;
        } else {
            for (int i = 0; i < size; i++) {
                if (data.get(i).getKey().compareTo(key) > 0) {
                    data.add(i, newEntry);
                    size += 1;
                    return newEntry;
                }
            }
        }
        return null;
    }

    /**
     * Returns (but does not remove) an entry with minimal key.
     * @return entry having a minimal key (or null if empty)
     */
    @Override
    public Entry peek() {
        if (size == 0) {
            return null;
        } else {
            return data.get(0);
        }
    }

    /**
     * Removes and returns an entry with minimal key.
     * @return the removed entry (or null if empty)
     */
    @Override
    public Entry dequeueMin() {
        if (size == 0) {
            return null;
        } else {
            Entry pollEntry = data.get(0);
            data.remove(0);
            size -= 1;
            return pollEntry;
        }
    }

    /**
     * Merges another priority queue of the same type.
     * @return the merged priority queue
     */
    @Override
    public VCPriorityQueue merge(VCPriorityQueue other) {
        ALPriorityQueue mergedPriorityQueue = new ALPriorityQueue();
        if (other.size() == 0 && data.size() == 0) {
            return null;
        } else if (other.size() == 0) {
            for (int i = 0; i < data.size(); i++) {
                mergedPriorityQueue.enqueue(data.get(i).getKey(), data.get(i).getValue());
            }
            return mergedPriorityQueue;
        } else if (data.size() == 0) {
            for (int i = 0; i < other.size(); i++) {
                Entry newEntry = new Entry(other.peek().getKey(), other.peek().getValue());
                mergedPriorityQueue.enqueue(other.peek().getKey(), other.peek().getValue());
                data.add(newEntry);
                other.dequeueMin();
            }
            return mergedPriorityQueue;
        } else {
            while (!other.isEmpty()) {
                Entry newEntry = new Entry(other.peek().getKey(), other.peek().getValue());
                for (int j = 0; j < size; j++) {
                    if (data.get(j).getKey().compareTo(newEntry.getKey()) > 0) {
                        data.add(j, newEntry);
                        other.dequeueMin();
                        break;
                    }
                }
                size += 1;
            }
            for (int i = 0; i < data.size(); i++) {
                mergedPriorityQueue.enqueue(data.get(i).getKey(), data.get(i).getValue());
            }
            return mergedPriorityQueue;
        }
    }

    @Override
    public String toString() {
        String ALPriorityQueue = "";
        for (Entry entry : data) {
            ALPriorityQueue += entry.toString() + "\n";
        }
        return ALPriorityQueue;
    }

}

