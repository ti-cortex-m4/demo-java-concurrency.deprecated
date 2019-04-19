package demo.ready;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class StringUpperCaseTask extends RecursiveTask<String> {

    private final int threshold;
    private final String argument;

    public StringUpperCaseTask(int threshold, String argument) {
        this.threshold = threshold;
        this.argument = argument;
        log("create task", argument);
    }

    @Override
    protected String compute() {
        if (argument.length() > threshold) {
            StringUpperCaseTask task1 = new StringUpperCaseTask(threshold, argument.substring(0, argument.length() / 2));
            StringUpperCaseTask task2 = new StringUpperCaseTask(threshold, argument.substring(argument.length() / 2));
            ForkJoinTask.invokeAll(task1, task2);
            String target = task1.compute() + task2.compute();
            log("join results", target);
            return target;
        } else {
            String target = argument.toUpperCase();
            log("get target", target);
            return target;
        }
    }

    private void log(String s, String result) {
        System.out.println(String.format("%-35s %-15s %s", Thread.currentThread().getName(), s, result));
    }
}
