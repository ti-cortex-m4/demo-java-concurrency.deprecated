package demo;

import java.util.concurrent.ForkJoinPool;

public class Demo {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        // submit() or execute()
        CustomRecursiveTask customRecursiveTask =new CustomRecursiveTask(new int[] {1,2,3,4,5,6});
        forkJoinPool.execute(customRecursiveTask);
        int result = customRecursiveTask.join();

        // int result = forkJoinPool.invoke(customRecursiveTask);
    }
}
