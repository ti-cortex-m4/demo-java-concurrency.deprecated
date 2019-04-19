package demo.x;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class StringUpperCaseAction extends RecursiveAction {

    private final int threshold;
    private final String value;

    private String result = "";

    public StringUpperCaseAction(int threshold, String value) {
        log("Create", value);
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
            StringUpperCaseAction task2 = new StringUpperCaseAction(threshold, value.substring(value.length() / 2));
            ForkJoinTask.invokeAll(task1, task2);
            result = task1.getResult() + task2.getResult();
            log("Result", result);
        } else {
            result = value.toUpperCase();
            log("Direct", result);
        }
    }

    private void log(String s, String result) {
        System.out.println(String.format("%35s %10s %s", Thread.currentThread().getName(), s , result ));
    }
}
