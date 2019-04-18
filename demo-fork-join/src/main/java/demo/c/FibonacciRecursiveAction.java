package demo.c;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class FibonacciRecursiveAction extends RecursiveAction {

    private static final long THRESHOLD = 10;

    private volatile long number;

    public FibonacciRecursiveAction(long number) {
        this.number = number;
    }

    public long getNumber() {
        return number;
    }

    @Override
    protected void compute() {
        long n = number;
        if (n <= THRESHOLD) {
            number = fib(n);
        } else {
            FibonacciRecursiveAction task1 = new FibonacciRecursiveAction(n - 1);
            FibonacciRecursiveAction task2 = new FibonacciRecursiveAction(n - 2);
            ForkJoinTask.invokeAll(task1, task2);
            number = task1.number + task2.number;
        }
    }

    private static long fib(long n) {
        if (n <= 1) return n;
        else return fib(n - 1) + fib(n - 2);
    }

}
