package demo.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCancel {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<String> future = new Futures(executorService).toUpperCase("value");
        System.out.println("Cancel result: " + future.cancel(true));

        if (future.isCancelled()) {
            System.out.println("Is cancelled...");
        }

        System.out.println("Cancel result: " + future.get());
        executorService.shutdown();
    }
}
