package demo.completable_future.part1;

import demo.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// create future and then complete it
public class Complete extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = new CompletableFuture<>();
        log("is done: " + future.isDone());
        future.complete("value");
        log("is done: " + future.isDone());
        log("result: " + future.get());
    }
}
