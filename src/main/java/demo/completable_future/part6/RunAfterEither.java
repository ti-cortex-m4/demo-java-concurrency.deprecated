package demo.completable_future.part6;

import demo.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// run Runnable after finishing any future
public class RunAfterEither extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture.supplyAsync(() -> delayed("parallel1", 1))
                .runAfterEither(CompletableFuture.supplyAsync(() -> delayed("parallel2", 2)),
                        () -> log("finished one"))
                .get();
    }
}
