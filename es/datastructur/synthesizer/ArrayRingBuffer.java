package es.datastructur.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    @SuppressWarnings("unchecked")
    public ArrayRingBuffer(int capacity) {
      first = 0;
      last = 0;
      rb = (T[])new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if(isFull()) throw new RuntimeException("Ring buffer overflow");
        rb[last]=x;
        last=(last+1)%rb.length;
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if(isEmpty()) throw new RuntimeException("Ring buffer underflow");
        T front = rb[first];
        first=(first+1)%rb.length;
        fillCount--;
        return front;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if(isEmpty()) throw new RuntimeException("Ring buffer underflow");
        return rb[first];
    }

    @Override
    public int fillCount(){
      return fillCount;
    }

    @Override
    public int capacity(){
      return rb.length;
    }

    public void printArray(){
      for(int i=first; i<first+fillCount; i++){
        System.out.println(rb[i%rb.length]);
      }
    }

    public static void main(String [] args){
      ArrayRingBuffer<Integer> list = new ArrayRingBuffer<Integer>(4);
      list.enqueue(1);
      list.enqueue(2);
      list.enqueue(3);
      list.enqueue(4);
      list.printArray();
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}
    // TODO: Remove all comments that say TODO when you're done.
