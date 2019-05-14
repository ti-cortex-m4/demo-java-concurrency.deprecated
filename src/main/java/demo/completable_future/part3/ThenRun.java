package demo.completable_future.part3;

import demo.completable_future.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// execute Runnable after future normal completion
public class ThenRun extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture.supplyAsync(() -> "Hello")
                .thenRun(() -> log("run in Runnable"))
                .get();

        CompletableFuture.supplyAsync(() -> "Hello")
                .thenRunAsync(() -> log("run in Runnable asynchronously"))
                .get();
    }
}
