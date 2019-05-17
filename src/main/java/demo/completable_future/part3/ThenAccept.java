package demo.completable_future.part3;

import demo.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// accept result in Consumer after finishing future
public class ThenAccept extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture.supplyAsync(() -> delayed("value"))
                .thenAccept(s -> log("consumed: " + s))
                .get();
    }
}
