package ca.ciccc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BHPriorityQueueTest {

    private static BHPriorityQueue<String, String> bhPriorityQueue;
    private static BHPriorityQueue<String, String> bhPriorityQueue2;

    @Before
    public void setup() {
        bhPriorityQueue = new BHPriorityQueue<>();
        bhPriorityQueue2 = new BHPriorityQueue<>();
    }

    @Test
    public void size() {
        assertEquals("Invalid", 0, bhPriorityQueue.size());
        bhPriorityQueue.enqueue("sk", "Slovakia");
        assertEquals("Invalid", 1, bhPriorityQueue.size());
    }

    @Test
    public void isEmpty() {
        assertTrue("Invalid", bhPriorityQueue.isEmpty());
    }

    @Test
    public void enqueue() {
        assertEquals("Invalid!", "Country code{code=es, country=Spain}", bhPriorityQueue.enqueue("es", "Spain").toString());
        Entry<String, String> Denmark = bhPriorityQueue.enqueue("dk", "Denmark");
        assertEquals("Invalid!", "Denmark", Denmark.value);
        assertEquals("Invalid", 2, bhPriorityQueue.size());
    }

    @Test
    public void peek() {
        bhPriorityQueue.enqueue("cz", "Czech Republic");
        bhPriorityQueue.enqueue("ca", "Canada");
        bhPriorityQueue.enqueue("no", "Norway");
        assertEquals("Invalid", "Country code{code=ca, country=Canada}", bhPriorityQueue.peek().toString());
        bhPriorityQueue.enqueue("be", "Belgium");
        assertEquals("Invalid!", "Country code{code=be, country=Belgium}", bhPriorityQueue.peek().toString());
    }

    @Test
    public void dequeueMin() {
        bhPriorityQueue.enqueue("cz", "Czech Republic");
        bhPriorityQueue.enqueue("ca", "Canada");
        bhPriorityQueue.enqueue("no", "Norway");
        assertEquals("Invalid!", "Country code{code=ca, country=Canada}", bhPriorityQueue.dequeueMin().toString());
        assertEquals("Invalid!", "Country code{code=cz, country=Czech Republic}", bhPriorityQueue.peek().toString());
        assertEquals("Invalid!", "Country code{code=cz, country=Czech Republic}", bhPriorityQueue.dequeueMin().toString());
        assertEquals("Invalid!", "Country code{code=no, country=Norway}", bhPriorityQueue.dequeueMin().toString());
    }

    @Test
    public void merge() {
        bhPriorityQueue.enqueue("cz", "Czech Republic");
        bhPriorityQueue.enqueue("ca", "Canada");
        bhPriorityQueue.enqueue("no", "Norway");

        bhPriorityQueue2.enqueue("gb", "Great Britain");
        bhPriorityQueue2.enqueue("au", "Australia");
        bhPriorityQueue2.enqueue("fr", "France");
        bhPriorityQueue.merge(bhPriorityQueue2);
        assertEquals("Invalid", 6, bhPriorityQueue.size());
        assertEquals("Invalid", "Country code{code=au, country=Australia}", bhPriorityQueue.peek().toString());
    }
}