package demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCancelLate {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<Integer> future = new SquareCalculator(executorService).calculate(10);
        Thread.sleep(2000);
        System.out.println("Cancel result: " + future.cancel(true));

        if(future.isCancelled()) {
            System.out.println("Is cancelled...");
        }

        System.out.println("Cancel result: " + future.get());
        executorService.shutdown();
    }
}
