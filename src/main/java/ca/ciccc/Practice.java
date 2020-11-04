package ca.ciccc;

import java.util.ArrayList;

class Practice {


    public static class PracticePriorityQueue<K extends Comparable, V> implements VCPriorityQueue<K, V>{
        private static final int DEFAULT_INITIAL_CAPACITY = 11;
        private ArrayList<Entry<K, V>> data;

        public PracticePriorityQueue() {
            this.data = new ArrayList<>(DEFAULT_INITIAL_CAPACITY);
        }

        public PracticePriorityQueue(int initialCapacity) {
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
            if (data.size() == 0) {
                data.add(newEntry);
                return newEntry;
            }
            for (Entry<K, V> d : data) {
                if (key.compareTo(d.key) < 0) {
                    data.add(data.indexOf(d), newEntry);
                    return newEntry;
                }
            }
            data.add(data.size(), newEntry);
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
            return data.get(0);
        }

        /**
         * Removes and returns an entry with minimal key.
         * @return the removed entry (or null if empty)
         */
        @Override
        public Entry<K, V> dequeueMin() {
            return data.remove(0);
        }

        /**
         * Merges another priority queue of the same type.
         * @return the merged priority queue
         */
        @Override
        public VCPriorityQueue<K, V> merge(VCPriorityQueue<K, V> other) {
            while (!other.isEmpty()) {
                Entry<K, V> toAdd = other.dequeueMin();
                this.enqueue(toAdd.key, toAdd.value);
            }
            return this;
        }

        @Override
        public String toString() {
            return "";
        }
    }
}
