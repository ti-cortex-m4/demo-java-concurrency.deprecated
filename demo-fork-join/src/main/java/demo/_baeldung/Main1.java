package demo._baeldung;

import java.util.concurrent.ForkJoinPool;

public class Main1 {

    public static void main(String[] args) {
        ForkJoinPool fjp = ForkJoinPool.commonPool();

        // submit() or execute()
        CustomRecursiveTask task1 = new CustomRecursiveTask(new int[]{1, 2, 3, 4, 5, 6});
        fjp.execute(task1);
        System.out.println("execute/join " + task1.join());

        CustomRecursiveTask task2 = new CustomRecursiveTask(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println("submit " + fjp.submit(task2));

        CustomRecursiveTask task3 = new CustomRecursiveTask(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println("invoke " + fjp.invoke(task3));
    }
}
