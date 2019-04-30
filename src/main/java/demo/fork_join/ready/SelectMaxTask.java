package demo.fork_join.ready;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class SelectMaxTask extends RecursiveAction {

    private final int threshold;
    private final SelectMaxProblem problem;
    public int result;

    public SelectMaxTask(SelectMaxProblem problem, int threshold) {
        this.problem = problem;
        this.threshold = threshold;
    }

    @Override
    protected void compute() {
        if (problem.getSize() < threshold)
            result = problem.solveSequentially();
        else {
            int midpoint = problem.getSize() / 2;
            SelectMaxTask left = new SelectMaxTask(problem.subproblem(0, midpoint + 1), threshold);
            SelectMaxTask right = new SelectMaxTask(problem.subproblem(midpoint + 1, problem.getSize()), threshold);
            ForkJoinTask.invokeAll(new ForkJoinTask[]{left, right});
            result = Math.max(left.result, right.result);
        }
    }

    public static void main(String[] args) {
        int[] data = {1, 200, 7, 800, 90, 19};
        SelectMaxProblem problem = new SelectMaxProblem(data, 0, data.length);
        int threshold = 3;
        int nThreads = 4;
        SelectMaxTask mfj = new SelectMaxTask(problem, threshold);
        ForkJoinPool pool = new ForkJoinPool(nThreads);
        pool.invoke(mfj);
        int result = mfj.result;
        System.out.println(result);
    }

    private static final long serialVersionUID = 1L;
}
