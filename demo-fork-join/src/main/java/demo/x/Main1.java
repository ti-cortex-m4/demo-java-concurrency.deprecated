package demo.x;

import java.util.concurrent.ForkJoinPool;

public class Main1 {

    public static void main(String[] args) {
        StringUpperAction task = new StringUpperAction(2, "abcdefghIjklmnopqrstuvwxyz");
        new ForkJoinPool().invoke(task);
        System.out.println(task.getResult());
    }
}
