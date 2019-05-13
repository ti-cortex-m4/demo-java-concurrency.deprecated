package demo.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompleteExceptionally {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.completeExceptionally(new RuntimeException("Calculation failed!"));
        future.get();
    }
}
