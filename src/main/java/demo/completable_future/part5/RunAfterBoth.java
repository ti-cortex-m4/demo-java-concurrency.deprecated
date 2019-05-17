package demo.completable_future.part5;

import demo.completable_future.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// run Runnable after finishing both futures
public class RunAfterBoth extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture.supplyAsync(() -> delayed("parallel1", 1))
                .runAfterBoth(CompletableFuture.supplyAsync(() -> delayed("parallel2", 2)),
                        () -> log("finished both"))
                .get();
    }
}

