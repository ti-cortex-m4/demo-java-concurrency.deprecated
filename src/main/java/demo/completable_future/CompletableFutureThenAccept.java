package demo.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureThenAccept {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<Void> future2 = future1.thenAccept(s -> System.out.println("Computation returned: " + s));
        Void result = future2.get();
        System.out.println("Result: " + result);
    }
}
