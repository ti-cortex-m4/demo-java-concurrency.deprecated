package demo.completable_future.part3;

import demo.completable_future.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenAccept extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture.supplyAsync(() -> delayed("value"))
                .thenAccept(s -> log("accept in Consumer: " + s))
                .get();

        CompletableFuture.supplyAsync(() -> delayed("value"))
                .thenAcceptAsync(s -> log("accept in Consumer: " + s))
                .get();
    }
}
