package demo.fork_join.portola;

import java.util.concurrent.*;
/*
@OutputTimeUnit(TimeUnit.MINUTES)
@State(Scope.Benchmark)
*/
public class ForkJoinPoolForking {

    /**
     * Implementation notes:
     *
     * This test harnesses forking infrastructure within FJP.
     * As such, no slack is given for allocating any humble number of tasks: the goal is to fork a lot.
     * The approximate number of tasks is (SIZE / THRESHOLD).
     *
     * Raw baseline gives the idea for compute bound for this benchmark.
     * FJP could be faster than baseline, because the baseline is single-threaded.
     */
/*
    @Param("0")
    private int workers;

    @Param("10000000")
    private int size;

    @Param("10")
    private int threshold;
*/
    private Problem problem;
    private ForkJoinPool fjpSync;
    private ForkJoinPool fjpAsync;
/*
    @Setup
    public void setup() {
        problem = new Problem(size);
        if (workers == 0) {
            workers = Runtime.getRuntime().availableProcessors();
        }
        fjpSync = new ForkJoinPool(workers, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, false);
        fjpAsync = new ForkJoinPool(workers, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);
    }

    @TearDown
    public void teardown() {
        fjpSync.shutdownNow();
        fjpAsync.shutdownNow();
    }

    @Benchmark
    public long baselineRaw() {
        return problem.solve();
    }

    @Benchmark
    public Long testExplicit_Sync() throws ExecutionException, InterruptedException {
        return fjpSync.invoke(new ExplicitTask(problem, 0, problem.size(), threshold));
    }

    @Benchmark
    public Long testExplicit_Async() throws ExecutionException, InterruptedException {
        return fjpAsync.invoke(new ExplicitTask(problem, 0, problem.size(), threshold));
    }

    @Benchmark
    public Long testStandard_Sync() throws ExecutionException, InterruptedException {
        return fjpSync.invoke(new StandardTask(problem, 0, problem.size(), threshold));
    }

    @Benchmark
    public Long testStandard_Async() throws ExecutionException, InterruptedException {
        return fjpAsync.invoke(new StandardTask(problem, 0, problem.size(), threshold));
    }
*/
    private static class ExplicitTask extends RecursiveTask<Long> {
        private final Problem problem;
        private final int l;
        private final int r;
        private final int thresh;

        public ExplicitTask(Problem p, int l, int r, int thresh) {
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
            ForkJoinTask<Long> t1 = new ExplicitTask(problem, l, mid, thresh);
            ForkJoinTask<Long> t2 = new ExplicitTask(problem, mid, r, thresh);

            t1.fork();
            t2.fork();

            long res = 0;
            res += t2.join();
            res += t1.join();
            return res;
        }
    }

    private static class StandardTask extends RecursiveTask<Long> {
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


}
