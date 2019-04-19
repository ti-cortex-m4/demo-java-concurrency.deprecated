package demo.ready;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class StringUpperCaseAction extends RecursiveAction {

    private final int threshold;
    private final String argument;

    private String target = "";

    public StringUpperCaseAction(int threshold, String argument) {
        this.threshold = threshold;
        this.argument = argument;
        log("create task", argument);
    }

    public String getTarget() {
        return target;
    }

    @Override
    protected void compute() {
        if (argument.length() > threshold) {
            StringUpperCaseAction task1 = new StringUpperCaseAction(threshold, argument.substring(0, argument.length() / 2));
            StringUpperCaseAction task2 = new StringUpperCaseAction(threshold, argument.substring(argument.length() / 2));
            ForkJoinTask.invokeAll(task1, task2);
            target = task1.getTarget() + task2.getTarget();
            log("join results", target);
        } else {
            target = argument.toUpperCase();
            log("get target", target);
        }
    }

    private void log(String s, String result) {
        System.out.println(String.format("%35s %15s %s", Thread.currentThread().getName(), s, result));
    }
}
