package demo.completable_future.part3;

import demo.completable_future.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenApply extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        log("result: " + CompletableFuture.supplyAsync(() -> delayed("Hello"))
                .thenApply(s1 -> s1 + " World")
                .get());

        log("result: " + CompletableFuture.supplyAsync(() -> delayed("Hello"))
                .thenApplyAsync(s -> s + " World")
                .get());
    }
}
