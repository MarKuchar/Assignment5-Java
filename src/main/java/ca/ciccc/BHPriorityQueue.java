package ca.ciccc;

import java.util.ArrayList;

public class BHPriorityQueue<E> implements VCPriorityQueue{
    private static final int DEFAULT_INITIAL_CAPACITY = 11;
    private int size;
    private Entry[] data;

    public BHPriorityQueue() {
        this.data = new Entry[11];
    }

    public BHPriorityQueue(int initialCapacity) {
        data = new Entry[initialCapacity];
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
        if (key == null) {
            throw new IllegalArgumentException("Invalid key.");
        } else if (size == 0) {
            data[size] = newEntry;
            size++;
            return newEntry;
        } else {
            int i = size - 1;
            while (data[(i - 1) / 2].getKey().compareTo(key) < 0) {
                i = (i - 1) / 2;
            }
            Entry temp = data[(i - 1) / 2];
            data[(i - 1) / 2] = newEntry;
            data[size - 1] = temp;
            size += 1;
            return newEntry;
        }
    }

    @Override
    public Entry peek() {
        return null;
    }

    @Override
    public Entry dequeueMin() {
        return null;
    }

    @Override
    public VCPriorityQueue merge(VCPriorityQueue other) {
        return null;
    }

    @Override
    public String toString() {
        String BHPriorityQueue = "";
        for (Entry entry : data) {
            BHPriorityQueue += entry.toString() + "\n";
        }
        return BHPriorityQueue;
    }

    public static void main(String[] args) {
        BHPriorityQueue<Entry> test = new BHPriorityQueue<Entry>();
//        ALPriorityQueue<Entry> test2 = new ALPriorityQueue<Entry>();
        Entry slovensko = new Entry("sk", "Slovakia");
        Entry polsko = new Entry("pl","Poland");
        Entry cesko = new Entry("cz", "Czech Republic");
        Entry kanada = new Entry("ca", "Canada");
        Entry norsko = new Entry("no", "Norway");

        System.out.println(test.enqueue("sk", "Slovakia"));
        System.out.println(test.enqueue("pl","Poland"));
        test.enqueue("cz", "Czech Republic");
        test.enqueue("ca", "Canada");
        test.enqueue("no", "Norway");

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
