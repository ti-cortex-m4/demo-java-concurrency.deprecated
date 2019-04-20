package demo.ready;

import demo.portola.Problem;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

class StandardTask extends RecursiveTask<Long> {

    private final Problem problem;
    private final int l;
    private final int r;
    private final int threshold;

    public StandardTask(Problem p, int l, int r, int threshold) {
        this.problem = p;
        this.l = l;
        this.r = r;
        this.threshold = threshold;
    }

    @Override
    protected Long compute() {
        if (r - l <= threshold) {
            return problem.solve(l, r);
        }

        int mid = (l + r) >>> 1;
        ForkJoinTask<Long> t1 = new StandardTask(problem, l, mid, threshold);
        ForkJoinTask<Long> t2 = new StandardTask(problem, mid, r, threshold);

        ForkJoinTask.invokeAll(t1, t2);
        long res = 0;
        res += t1.join();
        res += t2.join();
        return res;
    }
}
