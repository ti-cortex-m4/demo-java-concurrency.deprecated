package demo.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RunAsync {

    public static void main(String[] args) throws InterruptedException, ExecutionException  {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> System.out.println("Hello"));
        Void result = future.get();
        System.out.println("Result: " + result);
    }
}
