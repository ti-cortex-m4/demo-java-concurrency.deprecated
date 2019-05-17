package demo.completable_future.part1;

import demo.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// create already failed future
public class FailedFuture extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture.failedFuture(new RuntimeException());
        log("is completed exceptionally: " + future.isCompletedExceptionally());
        log("result: " + future.get());
    }
}
