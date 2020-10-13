package compfut;

import java.util.concurrent.CompletableFuture;

public class Example {
  public static void main(String[] args) {
    CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> {
      System.out.println("supplier running in thread "
          + Thread.currentThread().getName());
      return "Data is " + Math.random();
    })
        .thenApplyAsync(s -> s.toUpperCase())
        .thenAcceptAsync(s -> System.out.println("data is " + s + " in thread "
            + Thread.currentThread().getName()));
    System.out.println("work submitted...");
    CompletableFuture<Void> cf2 = cf.thenRunAsync(() -> System.out.println("CompletableFuture seems to have finished"));
//    cf.join();
    cf2.join();
    System.out.println("work completed...");
  }
}
