package demo.future;

import demo.Demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class IsDoneByTimeout extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future1 = executorService.submit(() -> {
            delay(2);
            return "on-time value";
        });
        log("result: " + future1.get(3, TimeUnit.SECONDS));

        Future<String> future2 = executorService.submit(() -> {
            delay(2);
            return "late value";
        });
        log("result: " + future2.get(1, TimeUnit.SECONDS)); // TimeoutException

        executorService.shutdown(); // never happens
    }
}
