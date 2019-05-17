package demo.completable_future.part1;

import demo.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// complete future exceptionally, cause the Throwable
public class CompleteExceptionally extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.completeExceptionally(new RuntimeException());
        log("is completed exceptionally: " + future.isCompletedExceptionally());
        future.get();
    }
}
