package demo.completable_future.part2;

import demo.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// complete future after running Runnable asynchronously
public class RunAsync extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> log(delayed("value")));
        log("result: " + future.get());
    }
}
