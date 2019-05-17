package demo.completable_future.part6;

import demo.completable_future.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// apply result in Function after finishing any future
public class ApplyToEither extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> delayed("parallel1", 1))
                .applyToEither(CompletableFuture.supplyAsync(() -> delayed("parallel2", 2)),
                        String::toUpperCase);
        log("result: " + future.get());
    }
}
