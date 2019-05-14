package demo.completable_future.part5;

import demo.completable_future.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RunAfterBoth extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture.supplyAsync(() -> delayed("Hello "))
                .runAfterBoth(CompletableFuture.supplyAsync(() -> delayed("World ")), () -> log("Finish"))
                .get();
    }
}

