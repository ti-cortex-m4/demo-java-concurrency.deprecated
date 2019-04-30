package demo.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureThenApply {

    public static void main(String[] args) throws InterruptedException, ExecutionException  {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = future1.thenApply(s -> s + " World");
        String result = future2.get();
        System.out.println("Result: " + result);
    }
}
