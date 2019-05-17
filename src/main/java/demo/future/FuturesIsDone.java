package demo.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FuturesIsDone {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);//1

        Futures futures = new Futures(executorService);

        Future<String> future1 = futures.toUpperCase("a");
        Future<String> future2 = futures.toUpperCase("bcd");

        while (!(future1.isDone() && future2.isDone())) {
            System.out.println(
                    String.format(
                            "future1 is %s and future2 is %s",
                            future1.isDone() ? "done" : "not done",
                            future2.isDone() ? "done" : "not done"
                    )
            );
            Thread.sleep(300);
        }

        String result1 = future1.get();
        String result2 = future2.get();

        System.out.println(result1 + " and " + result2);

        executorService.shutdown();
    }
}
