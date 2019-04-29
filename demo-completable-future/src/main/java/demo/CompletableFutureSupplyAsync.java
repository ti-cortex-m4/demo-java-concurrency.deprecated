package demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CompletableFutureSupplyAsync {

    public static void main(String[] args) throws InterruptedException, ExecutionException  {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
        String result = future.get();
        System.out.println("Result: " + result);
    }
}
