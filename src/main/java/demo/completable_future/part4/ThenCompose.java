package demo.completable_future.part4;

import demo.completable_future.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenCompose extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> delayed("sequential1"))
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> delayed(s + " sequential2")));
        log("result: " + future.get());
    }
}
