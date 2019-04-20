package demo.y;

public class MaxWithFJ extends RecursiveAction {
    private final int threshold;
    private final SelectMaxProblem problem;
    public int result;

    public MaxWithFJ(SelectMaxProblem problem, int threshold) {
        this.problem = problem;
        this.threshold = threshold;
    }

    @Override
    protected void compute() {
        if (problem.getSize() < threshold)
            result = problem.solveSequentially();
        else {
            int midpoint = problem.getSize() / 2;
            MaxWithFJ left = new MaxWithFJ(problem.subproblem(0, midpoint + 1), threshold);
            MaxWithFJ right = new MaxWithFJ(problem.subproblem(midpoint + 1,
                    problem.getSize()),
                    threshold);
            invokeAll(new ForkJoinTask[]{left, right});
            result = Math.max(left.result, right.result);
        }
    }

    public static void main(String[] args) {
        int[] data = {1, 200, 7, 800, 90, 19};
        SelectMaxProblem problem = new SelectMaxProblem(data, 0, data.length);
        int threshold = 3;
        int nThreads = 4;
        MaxWithFJ mfj = new MaxWithFJ(problem, threshold);
        ForkJoinPool pool = new ForkJoinPool(nThreads);
        pool.invoke(mfj);
        int result = mfj.result;
        System.out.println(result);
    }

    private static final long serialVersionUID = 1L;
}
