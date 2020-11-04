package ca.ciccc;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ALPriorityQueueTest {

    private static Practice.PracticePriorityQueue<String, String> alPriorityQueue;
    private static Practice.PracticePriorityQueue<String, String> alPriorityQueue2;

    @Before
    public void setup() {
        alPriorityQueue = new Practice.PracticePriorityQueue<>();
        alPriorityQueue2 = new Practice.PracticePriorityQueue<>();
    }

    @Test
    public void size() {
        assertEquals("Invalid", 0, alPriorityQueue.size());
        alPriorityQueue.enqueue("sk", "Slovakia");
        assertEquals("Invalid", 1, alPriorityQueue.size());
    }

    @Test
    public void isEmpty() {
        assertTrue("Invalid", alPriorityQueue.isEmpty());
    }

    @Test
    public void enqueue() {
        assertEquals("Invalid!", "Country code{code=es, country=Spain}", alPriorityQueue.enqueue("es", "Spain").toString());
        Entry<String, String> Denmark = alPriorityQueue.enqueue("dk", "Denmark");
        assertEquals("Invalid!", "Denmark", Denmark.value);
        assertEquals("Invalid", 2, alPriorityQueue.size());
    }

    @Test
    public void peek() {
        alPriorityQueue.enqueue("cz", "Czech Republic");
        alPriorityQueue.enqueue("ca", "Canada");
        alPriorityQueue.enqueue("no", "Norway");
        assertEquals("Invalid", "Country code{code=ca, country=Canada}", alPriorityQueue.peek().toString());
        alPriorityQueue.enqueue("be", "Belgium");
        assertEquals("Invalid!", "Country code{code=be, country=Belgium}", alPriorityQueue.peek().toString());
    }

    @Test
    public void dequeueMin() {
        alPriorityQueue.enqueue("cz", "Czech Republic");
        alPriorityQueue.enqueue("ca", "Canada");
        alPriorityQueue.enqueue("no", "Norway");
        assertEquals("Invalid!", "Country code{code=ca, country=Canada}", alPriorityQueue.dequeueMin().toString());
        assertEquals("Invalid!", "Country code{code=cz, country=Czech Republic}", alPriorityQueue.peek().toString());
        assertEquals("Invalid!", "Country code{code=cz, country=Czech Republic}", alPriorityQueue.dequeueMin().toString());
        assertEquals("Invalid!", "Country code{code=no, country=Norway}", alPriorityQueue.dequeueMin().toString());
    }

    @Test
    public void merge() {
        alPriorityQueue.enqueue("cz", "Czech Republic");
        alPriorityQueue.enqueue("ca", "Canada");
        alPriorityQueue.enqueue("no", "Norway");

        alPriorityQueue2.enqueue("gb", "Great Britain");
        alPriorityQueue2.enqueue("au", "Australia");
        alPriorityQueue2.enqueue("fr", "France");
        alPriorityQueue.merge(alPriorityQueue2);
        assertEquals("Invalid", 6, alPriorityQueue.size());
        assertEquals("Invalid", "Country code{code=au, country=Australia}", alPriorityQueue.peek().toString());
    }
}