package demo.future;

import demo.Demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class IsDone extends Demo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future = new Futures(executorService).toUpperCase("value");
        while(!future.isDone()) {
            log("calculating...");
            Thread.sleep(300);
        }
        log("result: " + future.get());

        executorService.shutdown();
    }
}
