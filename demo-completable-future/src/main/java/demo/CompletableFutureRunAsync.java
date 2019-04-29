package demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureRunAsync {

    public static void main(String[] args) throws InterruptedException, ExecutionException  {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> System.out.println("Hello"));
        Void result = completableFuture.get();
        System.out.println("Result: " + result);
    }
}
