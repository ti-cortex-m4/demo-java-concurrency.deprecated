package demo.completable_future.part3;

import demo.completable_future.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// apply Function after finishing future
public class ThenApply extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        log("result: " + CompletableFuture.supplyAsync(() -> delayed("value"))
                .thenApply(String::toUpperCase)
                .get());
    }
}
