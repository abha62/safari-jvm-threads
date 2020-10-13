package compfut;

import java.util.concurrent.CompletableFuture;

public class TimeConsuming {
  static CompletableFuture<String> readFile(String filename) {
    CompletableFuture<String> rv = new CompletableFuture<>();
    new Thread(()->{ // pretending to be OS kernel
      System.out.println("about to read file " + filename);
      try {
        Thread.sleep(2_000);
      } catch (InterruptedException ie) {
        System.out.println("Unexpected!");
      }
      // return, kinda
      rv.complete("This is the data from the file called " + filename);
//      rv.completeExceptionally()
    }).start();
    return rv;
  }

  public static void main(String[] args) {
    CompletableFuture.supplyAsync(() -> "filename.txt")
        .thenCompose(n -> readFile(n))
        .thenAccept(s -> System.out.println("File contains:\n" + s))
        .join();
  }
}
