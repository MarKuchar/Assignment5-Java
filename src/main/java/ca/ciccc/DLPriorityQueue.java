package ca.ciccc;

import java.util.LinkedList;

public class DLPriorityQueue<E> implements VCPriorityQueue {
    private int size;
    private Node head, tail;

    private static class Node<E> {
        Entry data;
        Node next;
        Node prev;

        Node(Entry data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    public DLPriorityQueue() {
    }

    public DLPriorityQueue(int initialCapacity) {
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public Entry enqueue(Comparable key, Object value) {
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

        /*
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
    */

    @Override
    public Entry peek() {
        if (size == 0) {
            return null;
        } else {
            return null;
        }
    }

    @Override
    public Entry dequeueMin() {
        return null;
    }

    /*
        @Override
        public Entry dequeueMin() {
            if (size == 0) {
                return null;
            } else {
                Entry pollEntry = data.poll();
                size -= 1;
                return null;
            }
        }


     */
    @Override
    public VCPriorityQueue merge(VCPriorityQueue other) {
        return null;
    }

    @Override
    public String toString() {
        Node temp = head;
        String result = "";
        while (temp.next != null) {
            result += temp.data + "\n";
            temp = temp.next;
        }
        result += temp.data + "";
        return result;
    }

    public static void main(String[] args) {
        DLPriorityQueue<Entry> test = new DLPriorityQueue<Entry>();
        DLPriorityQueue<Entry> test2 = new DLPriorityQueue<Entry>();
        Entry slovensko = new Entry("sk", "Slovakia");
        Entry polsko = new Entry("pl","Poland");
        Entry cesko = new Entry("cz", "Czech Republic");
        Entry kanada = new Entry("ca", "Canada");
        Entry norsko = new Entry("no", "Norway");

        System.out.println(test.enqueue("sk", "Slovakia"));
        System.out.println(test.enqueue("pl","Poland"));
        test.enqueue("no", "Norway");
        test.enqueue("cz", "Czech Republic");
        test.enqueue("ca", "Canada");

//        test2.enqueue("gb", "Great Britain");
//        test2.enqueue("au", "Australia");
//        test2.enqueue("fr", "France");

        System.out.println(test.isEmpty());
        System.out.println(test.size());
//        System.out.println(test.peek());
//        System.out.println(test.dequeueMin());
//        System.out.println(test.size());
//        System.out.println(test.peek());

//        System.out.println(test.merge(test2));
        System.out.println(test);
//        System.out.println(test.size());
    }
}
