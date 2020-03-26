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
     Add extend size method.
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

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

    @Override
    public Entry peek() {
        if (size == 0) {
            return null;
        }
        return data[0];
    }

    @Override
    public Entry dequeueMin() {
        if (size == 0) {
            return null;
        }
        Entry highestPriority = data[0];
        Entry lastEntry = data[size - 1];
        data[0] = lastEntry;
        System.arraycopy(data, 0, data, 0,size - 2);
        size--;

        int i = 0;
        while (data[(2 * i) + 1] != null) {
            if (data[(2 * i) + 2].getKey().compareTo(data[(2 * i) + 1].getKey()) >= 0 || data[(2 * i) + 2] == null) {
                if (lastEntry.getKey().compareTo(data[(2 * i) + 1].getKey()) > 0) {
                    Entry temp = data[(2 * i) + 1];
                    data[(2 * i) + 1] = lastEntry;
                    data[i] = temp;
                    i = (2 * i) + 1;
                } else {
                    return highestPriority;
                }
            } else {
                if (lastEntry.getKey().compareTo(data[(2 * i) + 2].getKey()) > 0) {
                    Entry temp = data[(2 * i) + 2];
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

    @Override
    public VCPriorityQueue merge(VCPriorityQueue other) {
        return null;
    }

    @Override
    public String toString() {
        String BHPriorityQueue = "";
        for (int i = 0; i < size; i++) {
            BHPriorityQueue += data[i] + "\n";
        }
        return BHPriorityQueue;
    }

    public static void main(String[] args) {
        BHPriorityQueue<String, String> test = new BHPriorityQueue<>();
//        ALPriorityQueue<Entry> test2 = new ALPriorityQueue<Entry>();
        Entry polsko = new Entry("pl","Poland");
        Entry cesko = new Entry("cz", "Czech Republic");
        Entry kanada = new Entry("ca", "Canada");
        Entry norsko = new Entry("no", "Norway");
        Entry slovensko = new Entry("sk", "Slovakia");

        test.enqueue("ca", "Canada");
        test.enqueue("no", "Norway");
        System.out.println(test.enqueue("pl","Poland"));
        test.enqueue("cz", "Czech Republic");
        System.out.println(test.enqueue("sk", "Slovakia"));

//        test2.enqueue("gb", "Great Britain");
//        test2.enqueue("au", "Australia");
//        test2.enqueue("fr", "France");

        System.out.println(test.isEmpty());
        System.out.println(test.size());
        System.out.println(test.peek());
        System.out.println(test.dequeueMin());
        System.out.println(test.size());
//        System.out.println(test.peek());

//        System.out.println(test.merge(test2));
        System.out.println(test);
//        System.out.println(test.size());
    }
}
