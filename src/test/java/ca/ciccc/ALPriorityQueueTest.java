package ca.ciccc;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ALPriorityQueueTest {

    private static ALPriorityQueue<String, String> alPriorityQueue;

    @Before
    public void setup() {
        alPriorityQueue = new ALPriorityQueue<>();
    }

    @Test
    public void size() {
        assertEquals("Invalid", 0, alPriorityQueue.size());
        alPriorityQueue.enqueue("cz", "Czech Republic");
        alPriorityQueue.enqueue("ca", "Canada");
        assertEquals("Invalid", 2, alPriorityQueue.size());

    }

    @Test
    public void isEmpty() {
        assertTrue("Invalid", alPriorityQueue.isEmpty());
        Entry<String, String> slovensko = alPriorityQueue.enqueue("sk", "Slovakia");
        assertFalse("Invalid", alPriorityQueue.isEmpty());
    }

    @Test
    public void enqueue() {
        alPriorityQueue.enqueue("sk", "Slovakia");
        assertEquals("Invalid!", "Country code{code=sk, country=Slovakia}", alPriorityQueue.enqueue("sk", "Slovakia").toString());
        Entry<String, String> slovensko = alPriorityQueue.enqueue("sk", "Slovakia");
        assertEquals("Invalid!", "Slovakia", slovensko.value);
        assertFalse("Invalid!", alPriorityQueue.isEmpty());
    }

    @Test
    public void peek() {
        assertNull("Invalid!", alPriorityQueue.peek());
        alPriorityQueue.enqueue("sk", "Slovakia");
        assertEquals("Ivalid!", "Country code{code=sk, country=Slovakia}", alPriorityQueue.peek().toString());
        alPriorityQueue.enqueue("cz", "Czech Republic");
        alPriorityQueue.enqueue("ca", "Canada");
        alPriorityQueue.enqueue("no", "Norway");
        assertEquals("Invalid", "Country code{code=ca, country=Canada}", alPriorityQueue.peek().toString());
    }

    @Test
    public void dequeueMin() {
        alPriorityQueue.enqueue("cz", "Czech Republic");
        alPriorityQueue.enqueue("ca", "Canada");
        alPriorityQueue.enqueue("no", "Norway");
//        assertNull("Invalid!", alPriorityQueue.peek());
        alPriorityQueue.enqueue("cz", "Czech Republic");

    }

    @Test
    public void merge() {
    }

    @Test
    public void testToString() {
    }
}