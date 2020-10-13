package runnables;

class MyJob implements Runnable {
  int i = 0;
  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + " Starting...");
    for (; i < 10; i++) {
      System.out.println(Thread.currentThread().getName() + " i is " + i);
    }
    System.out.println(Thread.currentThread().getName() + " ending");
  }
}

public class SimpleRunnable {
  public static void main(String[] args) {
    MyJob mj = new MyJob();
    Thread t1 = new Thread(mj);
    Thread t2 = new Thread(mj);
//    t1.setDaemon(true); // "this thread isn't important" -- not often true
    System.out.println(Thread.currentThread().getName() + " about to start...");
//    mj.run();
    t1.start(); // NOT run!!!!
    t2.start();
    System.out.println(Thread.currentThread().getName() + " job started...");
  }
}
