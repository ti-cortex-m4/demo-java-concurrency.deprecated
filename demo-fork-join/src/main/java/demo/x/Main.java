package demo.x;

import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        ForkJoinPool fjp = ForkJoinPool.commonPool();
        StringUpperCaseAction task = new StringUpperCaseAction(2, "abcdefghijklmnopqrstuvwxyz");
        fjp.invoke(task);
    }
}
