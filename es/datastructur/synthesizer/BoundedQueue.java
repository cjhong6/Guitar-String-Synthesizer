package es.datastructur.synthesizer;

public interface BoundedQueue<T>{

  //return size of buffer
  public int capacity();

  //retunr number of item in the buffer
  public int fillCount();

  //add item to the end
  public void enqueue(T x);

  //delete and return item from the front
  public T dequeue();

  //return but not delete item from the front
  public T peek();

  default boolean isEmpty(){
    return fillCount()==0;
  }

  default boolean isFull(){
    return fillCount()==capacity();
  }


}
