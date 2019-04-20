package demo._jenkov;

import java.util.concurrent.ForkJoinPool;

public class Main1 {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        DemoRecursiveAction recursiveAction = new DemoRecursiveAction(24);
        forkJoinPool.invoke(recursiveAction);
    }
}
