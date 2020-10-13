package runnables;

public class Stopper {
  static volatile boolean stop = false;
  public static void main(String[] args) throws Throwable {
    new Thread(() -> {
      System.out.println("worker starting...");
      while (! stop)
        ;
      System.out.println("worker stopping...");
    }).start();

    System.out.println("worker kicked off...");
    // pause...
    Thread.sleep(1_000);
    stop = true;
    System.out.println("main exiting, stop is " + stop);
  }
}
