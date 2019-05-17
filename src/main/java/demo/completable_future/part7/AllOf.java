package demo.completable_future.part7;

import demo.completable_future.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// wait for all of futures to finish
public class AllOf extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> delayed("parallel1", 1));
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> delayed("parallel2", 2));
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> delayed("parallel3", 3));

        CompletableFuture<Void> future = CompletableFuture.allOf(future2, future1, future3);
        future.get();

        String result = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));

        log("result: " + result);
    }
}
