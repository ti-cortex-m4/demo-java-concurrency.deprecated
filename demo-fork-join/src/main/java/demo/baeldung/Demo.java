package demo.baeldung;

import java.util.concurrent.ForkJoinPool;

public class Demo {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        // submit() or execute()
        CustomRecursiveTask task1 = new CustomRecursiveTask(new int[]{1, 2, 3, 4, 5, 6});
        forkJoinPool.execute(task1);
        System.out.println("execute/join " + task1.join());

        CustomRecursiveTask task2 = new CustomRecursiveTask(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println("submit " + forkJoinPool.submit(task2));

        CustomRecursiveTask task3 = new CustomRecursiveTask(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println("invoke " + forkJoinPool.invoke(task3));
    }
}
