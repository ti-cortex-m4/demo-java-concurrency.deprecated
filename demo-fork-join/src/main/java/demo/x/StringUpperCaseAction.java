package demo.x;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class StringUpperCaseAction extends RecursiveAction {

    private final int threshold;
    private final String value;

    private String result = "";

    public StringUpperCaseAction(int threshold, String value) {
        System.out.println("Create - (" + value + ") - " + Thread.currentThread().getName());
        this.threshold = threshold;
        this.value = value;
    }

    public String getResult() {
        return result;
    }

    @Override
    protected void compute() {
        if (value.length() > threshold) {
            StringUpperCaseAction task1 = new StringUpperCaseAction(threshold, value.substring(0, value.length() / 2));
            StringUpperCaseAction task2 = new StringUpperCaseAction(threshold,value.substring(value.length() / 2));
            ForkJoinTask.invokeAll(task1, task2);
            result = task1.getResult() + task2.getResult();
            System.out.println("Result - (" + result + ") - " + Thread.currentThread().getName());
        } else {
            result = value.toUpperCase();
            System.out.println("Direct - (" + result + ") - " + Thread.currentThread().getName());
        }
    }
}
