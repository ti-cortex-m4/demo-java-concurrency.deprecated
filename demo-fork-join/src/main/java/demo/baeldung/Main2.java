package demo.baeldung;

import java.util.concurrent.ForkJoinPool;

public class Main2 {

    public static void main(String[] args) {
        ForkJoinPool fjp = ForkJoinPool.commonPool();
        CustomRecursiveAction task = new CustomRecursiveAction("abcdefghIjklmnopqrstuvwxyz");
        fjp.invoke(task);
    }
}
