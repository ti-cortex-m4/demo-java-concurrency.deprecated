package demo.ready;

import demo.portola.ForkJoinPoolForking;
import demo.portola.Problem;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

class StandardTask extends RecursiveTask<Long> {
    private final Problem problem;
    private final int l;
    private final int r;
    private final int thresh;

    public StandardTask(Problem p, int l, int r, int thresh) {
        this.problem = p;
        this.l = l;
        this.r = r;
        this.thresh = thresh;
    }

    @Override
    protected Long compute() {
        if (r - l <= thresh) {
            return problem.solve(l, r);
        }

        int mid = (l + r) >>> 1;
        ForkJoinTask<Long> t1 = new StandardTask(problem, l, mid, thresh);
        ForkJoinTask<Long> t2 = new StandardTask(problem, mid, r, thresh);

        ForkJoinTask.invokeAll(t1, t2);
        long res = 0;
        res += t1.join();
        res += t2.join();
        return res;
    }
}
