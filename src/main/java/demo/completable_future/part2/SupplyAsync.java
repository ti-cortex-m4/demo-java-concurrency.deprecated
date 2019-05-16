package demo.completable_future.part2;

import demo.completable_future.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// complete future after returning value from Supplier asynchronously
public class SupplyAsync extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> delayed("value"));
        log("result: " + future.get());
    }
}
