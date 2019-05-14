package demo.completable_future.part1;

import demo.completable_future.Demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// cancel future, cause CancellationException
public class Cancel extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.cancel(false);
        log("is cancelled: " + future.isCancelled());
        future.get();
    }
}
