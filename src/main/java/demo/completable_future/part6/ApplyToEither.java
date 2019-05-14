package demo.completable_future.part6;

import demo.completable_future.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ApplyToEither extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> delayed("Car "))
                .applyToEither(CompletableFuture.supplyAsync(() -> delayed("Train ")), s -> s + "Finished");
        System.out.println("Result: " + future.get());
    }
}
