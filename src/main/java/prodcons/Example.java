package prodcons;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Example {
  public static void main(String[] args) {
    BlockingQueue<int[]> queue = new ArrayBlockingQueue<>(10);
    new Thread(() -> {
      System.out.println("producer starting");
      for (int i = 0; i < 10_000; i++) {
        try {
          // self contained, new object, every time!!!
          int[] data = {i, 0}; // transactionally incomplete
          if (i < 100) {
            Thread.sleep(10);
          }
          data[1] = i;
          if (i == 5_000) {
            data[1] = -1; // test the test
          }
          // write data and delete our reference to it
          queue.put(data);
          data = null; // MUST NOT touch this object again, it would be shared!!!
        }catch (InterruptedException ie) {
          System.out.println("Didn't expect that!");
        }
      }
      System.out.println("producer ending");
    }).start();
    new Thread(() -> {
      System.out.println("consumer starting...");
      for (int i = 0; i < 10_000; i++) {
        try {
          int[] data = queue.take();
          if (i > 9_900) {
            Thread.sleep(10);
          }
          if (data[0] != i || data[0] != data[1]) {
            System.out.println("****** Error at index " + i);
          }
          // could pass data, or something to a third thread...
        } catch (InterruptedException ie) {
          System.out.println("unexpected!!!");
        }
      }
      System.out.println("consumer ending...");
    }).start();
    System.out.println("started both threads...");
  }
}
