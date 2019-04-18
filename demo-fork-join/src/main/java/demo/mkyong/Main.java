package demo.mkyong;

import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        FibonacciRecursiveAction task = new FibonacciRecursiveAction(50);
        new ForkJoinPool().invoke(task);
        System.out.println(task.getNumber());
    }
}
