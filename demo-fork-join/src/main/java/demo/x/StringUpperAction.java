package demo.x;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class StringUpperAction extends RecursiveAction {

    private final int threshold;
    private final String value;

    private String result = "";

    public StringUpperAction(int threshold, String value) {
        System.out.println("Create - (" + value + ") - was processed by " + Thread.currentThread().getName());
        this.threshold = threshold;
        this.value = value;
    }

    public String getResult() {
        return result;
    }

    @Override
    protected void compute() {
        if (value.length() > threshold) {
            StringUpperAction task1 = new StringUpperAction(threshold, value.substring(0, value.length() / 2));
            StringUpperAction task2 = new StringUpperAction(threshold,value.substring(value.length() / 2));
            ForkJoinTask.invokeAll(task1, task2);
            result = task1.getResult() + task2.getResult();
            System.out.println("Result - (" + result + ") - was processed by " + Thread.currentThread().getName());
        } else {
            result = value.toUpperCase();
            System.out.println("Direct - (" + result + ") - was processed by " + Thread.currentThread().getName());
        }
    }
}
