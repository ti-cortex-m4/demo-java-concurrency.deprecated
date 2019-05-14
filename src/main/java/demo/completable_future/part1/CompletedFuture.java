package demo.completable_future.part1;

import demo.completable_future.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// create already completed future
public class CompletedFuture extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture.completedFuture("value");
        log("is done: " + future.isDone());
        log("result: " + future.get());
    }
}
