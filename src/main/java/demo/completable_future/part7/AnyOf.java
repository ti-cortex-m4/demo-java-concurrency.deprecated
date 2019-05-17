package demo.completable_future.part7;

import demo.completable_future.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// wait for any of futures to finish
public class AnyOf extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> delayed("parallel1", 1));
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> delayed("parallel2", 2));
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> delayed("parallel3", 3));

        CompletableFuture<Object> future = CompletableFuture.anyOf(future2, future1, future3);
        log("result: " + future.get());
    }
}
