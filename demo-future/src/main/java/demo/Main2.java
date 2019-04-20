package demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<Integer> future = new SquareCalculator(executorService).calculate(10);
        future.cancel(true);

        if(future.isCancelled()) {
            System.out.println("Is cancelled...");
        }

        future.get();
    }
}
