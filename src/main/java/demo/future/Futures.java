package demo.future;

import demo.Demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Futures extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        run(1);
        run(2);
    }

    private static void run(int numberOfThreads) throws InterruptedException, ExecutionException {
        log("number of threads: " + numberOfThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        Future<String> future1 = executorService.submit(() -> {
            log("task1 started");
            delay(2);
            log("task1 finished");
            return "value 1";
        });
        Future<String> future2 = executorService.submit(() -> {
            log("task2 started");
            delay(4);
            log("task2 finished");
            return "value 2";
        });

        while (!(future1.isDone() && future2.isDone())) {
            log("waiting...");
            delay(1);
        }

        String result1 = future1.get();
        String result2 = future2.get();
        log("result: " + result1 + " " + result2);

        executorService.shutdown();
    }
}
