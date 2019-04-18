package demo.b;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class DemoRecursiveAction extends RecursiveAction {

    private long workload = 0;

    public DemoRecursiveAction(long workload) {
        this.workload = workload;
    }

    @Override
    protected void compute() {
        if (this.workload > 16) {
            System.out.println("Splitting workload : " + this.workload);

            List<DemoRecursiveAction> subtasks = createSubtasks();
            for (RecursiveAction subtask : subtasks) {
                subtask.fork();
            }
        } else {
            System.out.println("Doing workload myself: " + this.workload);
        }
    }

    private List<DemoRecursiveAction> createSubtasks() {
        List<DemoRecursiveAction> subtasks = new ArrayList<DemoRecursiveAction>();

        DemoRecursiveAction subtask1 = new DemoRecursiveAction(this.workload / 2);
        DemoRecursiveAction subtask2 = new DemoRecursiveAction(this.workload / 2);

        subtasks.add(subtask1);
        subtasks.add(subtask2);

        return subtasks;
    }

}