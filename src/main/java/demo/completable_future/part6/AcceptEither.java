package demo.completable_future.part6;

import demo.completable_future.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// accept result in Consumer after finishing any future
public class AcceptEither extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture.supplyAsync(() -> delayed("parallel1", 1))
                .acceptEither(CompletableFuture.supplyAsync(() -> delayed("parallel2", 2)),
                        s -> log("consumed one: " + s))
                .get();
    }
}
