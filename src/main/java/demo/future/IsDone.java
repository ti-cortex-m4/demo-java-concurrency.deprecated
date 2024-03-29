package demo.future;

import demo.Demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class IsDone extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future = executorService.submit(() -> {
            delay(3);
            return "value";
        });
        while (!future.isDone()) {
            log("waiting...");
            delay(1);
        }
        log("result: " + future.get());

        executorService.shutdown();
    }
}
