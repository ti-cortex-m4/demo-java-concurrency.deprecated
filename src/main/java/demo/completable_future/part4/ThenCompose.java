package demo.completable_future.part4;

import demo.completable_future.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenCompose extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> delayed("Hello "))
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> delayed(s + "World")));
        log("result: " + future.get());
    }
}