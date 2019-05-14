package demo.completable_future.part7;

import demo.completable_future.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// waited for any of the futures
public class AnyOf extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> delayed("Car", 2));
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> delayed("Train", 1));
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> delayed("Bike", 3));

        CompletableFuture<Object> future = CompletableFuture.anyOf(future1, future2, future3);
        log("The fastest vehicle finished: " + future.get());
    }
}
