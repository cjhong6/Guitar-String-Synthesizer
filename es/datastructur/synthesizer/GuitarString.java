package es.datastructur.synthesizer;
import java.util.*;
import edu.princeton.cs.algs4.StdAudio;
//Note: This file will not compile until you complete task 1 (BoundedQueue).
public class GuitarString{
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // TODO: Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this division operation into an int. For
        //       better accuracy, use the Math.round() function before casting.
        //       Your buffer should be initially filled with zeros.
        buffer = new ArrayRingBuffer((int)Math.round(SR/frequency));
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // TODO: Dequeue everything in buffer, and replace with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each
        //       other.
        while(!buffer.isEmpty()) buffer.dequeue();
        Set<Double> set = new HashSet<Double>(buffer.capacity());
        while(set.size()<buffer.capacity()) set.add(Math.random() - 0.5);
        for(double newDouble:set) buffer.enqueue(newDouble);

    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play().
        double front = buffer.dequeue();
        double next = buffer.peek();
        buffer.enqueue(((front+next)/2)*DECAY);

    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }

    public static void main(String[] args){
      GuitarString s = new GuitarString(11025);
      s.pluck();

      // Record the front four values, ticcing as we go.
      double s1 = s.sample();
      s.tic();
      double s2 = s.sample();
      s.tic();
      double s3 = s.sample();
      s.tic();
      double s4 = s.sample();

      // If we tic once more, it should be equal to 0.996*0.5*(s1 + s2)
      s.tic();
      double s5 = s.sample();
      double expected = 0.996 * 0.5 * (s1 + s2);
      System.out.println(s5);
      System.out.println(expected);
    }
}
    // TODO: Remove all comments that say TODO when you're done.
