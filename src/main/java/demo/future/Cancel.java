package demo.future;

import demo.Demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Cancel extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future = executorService.submit(() -> {
            delay(1);
            return "value";
        });
        log("cancel: " + future.cancel(true));
        log("is cancelled: " + future.isCancelled());
        log("result: " + future.get()); // CancellationException

        executorService.shutdown(); // never happens
    }
}
