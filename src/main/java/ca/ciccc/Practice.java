package ca.ciccc;

import java.util.ArrayList;
import java.util.LinkedList;

class Practice {


    public static class PracticePriorityQueue<K extends Comparable, V> implements VCPriorityQueue<K, V>{
        class Node {
            Entry<K, V> entry;
            Node next;
            Node previous;

            public Node(Entry<K, V> entry, Node next, Node previous) {
                this.entry = entry;
                this.next = next;
                this.previous = previous;
            }
        }

        private Node head;
        public PracticePriorityQueue() { }

        /**
         * Returns the number of items in the priority queue.
         * @return number of items
         */
        @Override
        public int size() {
            if (head == null) {
                return 0;
            } else {
                Node actual = head;
                int count = 1;
                while (actual.next != null) {
                    actual = actual.next;
                    count += 1;
                }
                return count;
            }
        }

        /**
         * Tests whether the priority queue is empty.
         * @return true if the priority queue is empty, false otherwise
         */
        @Override
        public boolean isEmpty() {
            return head == null;
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
            Node newNode = new Node(newEntry, null, null);
            Node actual = head;

            if (this.isEmpty()) {
                head = newNode;
            } else if (key.compareTo(actual.entry.key) < 0) {
                head = newNode;
                newNode.next = actual;
            } else {
                while (actual.next != null) {
                    if (key.compareTo(actual.entry.key) > 0) {
                        actual = actual.next;
                    } else {
                        newNode.next = actual.next;
                        actual.next = newNode;
                        return newEntry;
                    }
                }
                actual.next = newNode;
            }
            return newEntry;
        }

        /**
         * Returns (but does not remove) an entry with minimal key.
         * @return entry having a minimal key (or null if empty)
         */
        @Override
        public Entry<K, V> peek() {
            return head.entry;
        }

        /**
         * Removes and returns an entry with minimal key.
         * @return the removed entry (or null if empty)
         */
        @Override
        public Entry<K, V> dequeueMin() {
            if (head == null) {
                return null;
            }
            Node actual = head;
            head = head.next;
            return actual.entry;
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
