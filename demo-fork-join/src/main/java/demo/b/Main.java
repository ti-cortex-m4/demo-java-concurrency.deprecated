package demo.b;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        MyRecursiveAction myRecursiveAction = new MyRecursiveAction(24);
        forkJoinPool.invoke(myRecursiveAction);
    }
}
