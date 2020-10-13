package runnables;

public class Counter {
  private static long count = 0; // DOesn't have to be volatile

  public static void main(String[] args) throws Throwable {
    Runnable r = () -> {
      for (int i = 0; i < 10_000_000; i++) {
        synchronized (Counter.class) {
          count++;
        }
      }
      System.out.println("Worker finished");
    };

    Thread t1 = new Thread(r);
    Thread t2 = new Thread(r);
    t1.start();
    t2.start();
//    Thread.sleep(1_000);
    t1.join(); // block until t1 has completed
    t2.join();
    System.out.println("main exiting, count is " + count);
  }
}
