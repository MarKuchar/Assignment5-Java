package ca.ciccc;

public class TestALPriorityQueue {
    public static void main(String[] args) {
        ALPriorityQueue<Entry> test = new ALPriorityQueue<Entry>();
        ALPriorityQueue<Entry> test2 = new ALPriorityQueue<Entry>();
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

        test2.enqueue("gb", "Great Britain");
        test2.enqueue("au", "Australia");
        test2.enqueue("fr", "France");

        System.out.println(test.isEmpty());
        System.out.println(test.size());
        System.out.println(test.peek());
        System.out.println(test.dequeueMin());
        System.out.println(test.size());
        System.out.println(test.peek());

        System.out.println(test.merge(test2));
        System.out.println(test);
        System.out.println(test.size());
    }
}
