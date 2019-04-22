package demo.ready;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class FibonacciTask extends RecursiveTask<Integer> {

    final int n;

    FibonacciTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1)
            return n;
        FibonacciTask f1 = new FibonacciTask(n - 1);
        f1.fork();
        FibonacciTask f2 = new FibonacciTask(n - 2);
        return f2.compute() + f1.join();
    }

    private static long fibonacciRecursive(long n) {
        if (n <= 1) return n;
        else return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static void main(String[] args) {
        int n = 20;

        System.out.println(fibonacciRecursive(n));

        ForkJoinPool fjp = ForkJoinPool.commonPool();
        FibonacciTask task = new FibonacciTask(n);
        System.out.println(fjp.invoke(task));
    }
}
