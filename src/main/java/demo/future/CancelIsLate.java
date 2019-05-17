package demo.future;

import demo.Demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CancelIsLate extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future = executorService.submit(() -> {
            delay(1);
            return "value";
        });
        delay(2);
        log("cancel: " + future.cancel(true));
        log("is cancelled: " + future.isCancelled());
        log("result: " + future.get());

        executorService.shutdown();
    }
}
