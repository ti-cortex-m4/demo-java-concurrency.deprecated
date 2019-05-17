package demo.completable_future.part5;

import demo.completable_future.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// accept results in BiConsumer after finishing both futures
public class ThenAcceptBoth extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture.supplyAsync(() -> delayed("parallel1"))
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> delayed("parallel2")),
                        (s1, s2) -> log("consumed: " + s1 + " " + s2))
                .get();
    }
}
