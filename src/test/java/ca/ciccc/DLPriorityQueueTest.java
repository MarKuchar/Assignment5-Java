package ca.ciccc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DLPriorityQueueTest {

    private static DLPriorityQueue<String, String> dlPriorityQueue;
    private static DLPriorityQueue<String, String> dlPriorityQueue2;

    @Before
    public void setup() {
        dlPriorityQueue = new DLPriorityQueue<>();
        dlPriorityQueue2 = new DLPriorityQueue<>();
    }

    @Test
    public void size() {
        assertEquals("Invalid", 0, dlPriorityQueue.size());
        dlPriorityQueue.enqueue("sk", "Slovakia");
        assertEquals("Invalid", 1, dlPriorityQueue.size());
    }

    @Test
    public void isEmpty() {
        assertTrue("Invalid", dlPriorityQueue.isEmpty());
    }

    @Test
    public void enqueue() {
        assertEquals("Invalid!", "Country code{code=es, country=Spain}", dlPriorityQueue.enqueue("es", "Spain").toString());
        Entry<String, String> Denmark = dlPriorityQueue.enqueue("dk", "Denmark");
        assertEquals("Invalid!", "Denmark", Denmark.value);
        assertEquals("Invalid", 2, dlPriorityQueue.size());
    }

    @Test
    public void peek() {
        dlPriorityQueue.enqueue("cz", "Czech Republic");
        dlPriorityQueue.enqueue("ca", "Canada");
        dlPriorityQueue.enqueue("no", "Norway");
        assertEquals("Invalid", "Country code{code=ca, country=Canada}", dlPriorityQueue.peek().toString());
        dlPriorityQueue.enqueue("be", "Belgium");
        assertEquals("Invalid!", "Country code{code=be, country=Belgium}", dlPriorityQueue.peek().toString());
    }

    @Test
    public void dequeueMin() {
        dlPriorityQueue.enqueue("cz", "Czech Republic");
        dlPriorityQueue.enqueue("ca", "Canada");
        dlPriorityQueue.enqueue("no", "Norway");
        assertEquals("Invalid!", "Country code{code=ca, country=Canada}", dlPriorityQueue.dequeueMin().toString());
        assertEquals("Invalid!", "Country code{code=cz, country=Czech Republic}", dlPriorityQueue.peek().toString());
        assertEquals("Invalid!", "Country code{code=cz, country=Czech Republic}", dlPriorityQueue.dequeueMin().toString());
        assertEquals("Invalid!", "Country code{code=no, country=Norway}", dlPriorityQueue.dequeueMin().toString());
    }

    @Test
    public void merge() {
        dlPriorityQueue.enqueue("cz", "Czech Republic");
        dlPriorityQueue.enqueue("ca", "Canada");
        dlPriorityQueue.enqueue("no", "Norway");

        dlPriorityQueue2.enqueue("gb", "Great Britain");
        dlPriorityQueue2.enqueue("au", "Australia");
        dlPriorityQueue2.enqueue("fr", "France");
        dlPriorityQueue.merge(dlPriorityQueue2);
        assertEquals("Invalid", 6, dlPriorityQueue.size());
        assertEquals("Invalid", "Country code{code=au, country=Australia}", dlPriorityQueue.peek().toString());
    }
}