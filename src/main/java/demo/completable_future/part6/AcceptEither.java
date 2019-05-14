package demo.completable_future.part6;

import demo.completable_future.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AcceptEither extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture.supplyAsync(() -> delayed("Car"))
                .acceptEither(CompletableFuture.supplyAsync(() -> delayed("Train")), s -> log("Consumed only: " + s))
                .get();
    }
}
