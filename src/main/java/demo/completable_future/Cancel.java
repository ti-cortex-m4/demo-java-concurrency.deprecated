package demo.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Cancel {

    static Future<String> calculateAsyncWithCancellation()  {
        CompletableFuture<String> completableFuture  = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);
            completableFuture.cancel(false);;
            return null;
        });

        return completableFuture;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException  {
        Future<String> completableFuture = calculateAsyncWithCancellation();
        String result = completableFuture.get();
        System.out.println("Result: " + result);
    }
}