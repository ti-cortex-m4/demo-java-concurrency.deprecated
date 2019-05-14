package demo.fork_join.demo.part2;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class FibonacciTask extends RecursiveTask<Long> {

    private final int n;
    private final int threshold;

    FibonacciTask(int n, int threshold) {
        this.n = n;
        this.threshold = threshold;
    }

    @Override
    protected Long compute() {
        if (n <= threshold) {
            return fibonacciRecursive(n);
        }
        FibonacciTask f1 = new FibonacciTask(n - 1, threshold);
        f1.fork();
        FibonacciTask f2 = new FibonacciTask(n - 2, threshold);
        return f2.compute() + f1.join();
    }

    private static long fibonacciRecursive(long n) {
        if (n <= 1) return n;
        else return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static void main(String[] args) {
        int n = 40;

        System.out.println(fibonacciRecursive(n));

        ForkJoinPool fjp = ForkJoinPool.commonPool();
        FibonacciTask task = new FibonacciTask(n, 1);
        System.out.println(fjp.invoke(task));
    }
}
