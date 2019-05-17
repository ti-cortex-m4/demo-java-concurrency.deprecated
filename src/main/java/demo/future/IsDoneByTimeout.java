package demo.future;

import demo.Demo;

import java.util.concurrent.*;

public class IsDoneByTimeout extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future1 = executorService.submit(() -> {
            delay(2);
            return "value";
        });
        log("result: " + future1.get(3, TimeUnit.SECONDS));

        Future<String> future2 = executorService.submit(() -> {
            delay(2);
            return "value";
        });
        log("result: " + future2.get(1, TimeUnit.SECONDS)); // TimeoutException

        executorService.shutdown(); // never happens
    }
}
