package demo.completable_future.part5;

import demo.completable_future.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenAcceptBoth extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture.supplyAsync(() -> delayed("Hello "))
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> delayed("World ")), (s1, s2) -> log(s1 + s2))
                .get();
    }
}
