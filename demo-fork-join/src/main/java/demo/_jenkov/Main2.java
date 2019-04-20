package demo._jenkov;

import java.util.concurrent.ForkJoinPool;

public class Main2 {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        DemoRecursiveTask recursiveTask = new DemoRecursiveTask(128);
        long mergedResult = forkJoinPool.invoke(recursiveTask);
        System.out.println("mergedResult = " + mergedResult);
    }
}
