package demo.completable_future.part7;

import demo.completable_future.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// waited for all of the futures
public class AllOf extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> delayed("Car", 2));
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> delayed("Train", 1));
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> delayed("Bike", 3));

        CompletableFuture<Void> future = CompletableFuture.allOf(future1, future2, future3);
        future.get();

        String result = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(", "));

        log("All vehicles finished: " + result);
    }
}
