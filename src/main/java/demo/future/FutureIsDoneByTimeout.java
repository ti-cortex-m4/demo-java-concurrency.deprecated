package demo.future;

import java.util.concurrent.*;

public class FutureIsDoneByTimeout {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<Integer> future = new SquareCalculator(executorService).calculate(10);

        Integer result = future.get(1500, TimeUnit.MILLISECONDS); // 500
        System.out.println(result);

        executorService.shutdown();
    }
}
