package demo.completable_future.part4;

import demo.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// run two futures parallelly and then combine them
public class ThenCombine extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> delayed("parallel1"))
                .thenCombine(CompletableFuture.supplyAsync(() -> delayed("parallel2")),
                        (s1, s2) -> s1 + " " + s2);
        log("result: " + future.get());
    }
}
