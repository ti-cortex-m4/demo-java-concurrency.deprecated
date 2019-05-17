package demo.completable_future.part3;

import demo.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// run Runnable after finishing future
public class ThenRun extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture.supplyAsync(() -> delayed("value"))
                .thenRun(() -> log("run in Runnable"))
                .get();
    }
}
