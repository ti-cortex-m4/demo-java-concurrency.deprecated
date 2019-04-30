package demo.fork_join._jenkov;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;


public class DemoRecursiveTask extends RecursiveTask<Long> {

    private long workload = 0;

    public DemoRecursiveTask(long workload) {
        this.workload = workload;
    }

    @Override
    protected Long compute() {
        if (this.workload > 16) {
            System.out.println("Splitting workload : " + this.workload);

            List<DemoRecursiveTask> subtasks = createSubtasks();
            for (DemoRecursiveTask subtask : subtasks) {
                subtask.fork();
            }

            long result = 0;
            for (DemoRecursiveTask subtask : subtasks) {
                result += subtask.join();
            }
            return result;
        } else {
            System.out.println("Doing workload myself: " + this.workload);
            return workload * 3;
        }
    }

    private List<DemoRecursiveTask> createSubtasks() {
        List<DemoRecursiveTask> subtasks = new ArrayList<DemoRecursiveTask>();

        DemoRecursiveTask subtask1 = new DemoRecursiveTask(this.workload / 2);
        DemoRecursiveTask subtask2 = new DemoRecursiveTask(this.workload / 2);

        subtasks.add(subtask1);
        subtasks.add(subtask2);

        return subtasks;
    }
}