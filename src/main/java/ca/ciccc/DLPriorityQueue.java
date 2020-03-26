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
        Entry newEntry = new Entry(key, value);
        if (key == null) {
            throw new IllegalArgumentException("Invalid key.");
        }
        data.add(newEntry);
        return newEntry;
    }

        /*
  for (int i = 0; i < size; i++) {
                if (data.get(i).getKey().compareTo(key) > 0) {
                    data.add(i, newEntry);
                    size += 1;
                    return newEntry;

        Entry newEntry = new Entry(key, value);
        Node tempHead = head;
        Node tempTail = tail;
        Node newNode = new Node(newEntry, null, null);
        if (key == null) {
            throw new IllegalArgumentException("Invalid key.");
        }
        if (size == 0) {
            tail = newNode;
            head = newNode;
            size++;
            return newEntry;
        } else if (size == 1) {
            if (tempTail.data.getKey().compareTo(key) > 0) {
                head.prev = newNode;
                newNode.next = head;
                head = newNode;
            } else {
                Node nextNode = tempHead.next;
                nextNode.prev = newNode;
                newNode.next = nextNode;
                newNode.prev = tempHead;
                head.next = newNode;
            }
        } else if (size > 1) {
            while (tempHead.data.getKey().compareTo(key) < 0) {
                if (tempHead.next == null) {
                    newNode.prev = tail;
                    tail.next = newNode;
                    newNode = tail;
                    size++;
                    return newEntry;
                } else {
                    tempHead = tempHead.next;
                }
            }
            if (tempHead == head) {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            } else if (tempHead == tail) {
                Node previousNode = tempTail.prev;
                previousNode.next = newNode;
                newNode.next = tail;
                newNode.prev = previousNode;
                tail.prev = newNode;
            } else {
                Node previousNode = tempTail.prev;
                previousNode.prev = tempTail.prev.prev;
                previousNode.next = newNode;
                newNode.next = tempTail;
                newNode.prev = previousNode;
                tempTail.prev = newNode;
            }
        }
        size++;
        return newEntry;
        }

         */

    /**
     * Returns (but does not remove) an entry with minimal key.
     * @return entry having a minimal key (or null if empty)
     */
    @Override
    public Entry peek() {
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
    public Entry dequeueMin() {
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
    public VCPriorityQueue merge(VCPriorityQueue other) {
        if (other.size() == 0 && data.size() == 0) {
            return null;
        } else {
            while (!other.isEmpty()) {
                Entry newEntry = other.dequeueMin();
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

    public static void main(String[] args) {
        DLPriorityQueue<String, String> test = new DLPriorityQueue<>();
        DLPriorityQueue<String, String> test2 = new DLPriorityQueue<>();

        System.out.println(test.enqueue("sk", "Slovakia"));
        System.out.println(test.enqueue("pl","Poland"));
        test.enqueue("cz", "Czech Republic");
        test.enqueue("ca", "Canada");
        test.enqueue("no", "Norway");

        test2.enqueue("gb", "Great Britain");
        test2.enqueue("au", "Australia");
        test2.enqueue("fr", "France");

        System.out.println(test.isEmpty());
        System.out.println(test.size());
        System.out.println(test.peek());
        System.out.println(test.dequeueMin());
        System.out.println(test);
        System.out.println(test.size());
        System.out.println(test.peek());
        System.out.println(test.size());
        System.out.println(test.merge(test2));
        System.out.println(test);
        System.out.println(test.peek());
        System.out.println(test.dequeueMin());
        System.out.println(test.size());
    }
}
