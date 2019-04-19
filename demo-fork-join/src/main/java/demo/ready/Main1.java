package demo.ready;

import java.util.concurrent.ForkJoinPool;

public class Main1 {

    public static void main(String[] args) {
        ForkJoinPool fjp = ForkJoinPool.commonPool();
        StringUpperCaseAction task = new StringUpperCaseAction(2, "abcdefghijklmnopqrstuvwxyz");
        fjp.invoke(task);
    }
}
