package demo.completable_future.part4;

import demo.completable_future.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenCombine extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> delayed("Hello "))
                .thenCombine(CompletableFuture.supplyAsync(() -> delayed("World")), (s1, s2) -> s1 + s2);
        log("result: " + future.get());
    }
}
