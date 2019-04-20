package demo.briangoetz1;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class MaxWithFJ extends RecursiveAction {

    private final int threshold;
    private final SelectMaxProblem problem;
    public int result;

    public MaxWithFJ(SelectMaxProblem problem, int threshold) {
        this.problem = problem;
        this.threshold = threshold;
    }

    protected void compute() {
        if (problem.size < threshold)
            result = problem.solveSequentially();
        else {
            int midpoint = problem.size / 2;
            MaxWithFJ left = new MaxWithFJ(problem.subproblem(0, midpoint), threshold);
            MaxWithFJ right = new MaxWithFJ(problem.subproblem(midpoint + 1, problem.size), threshold);
            ForkJoinTask.invokeAll(left, right);
            result = Math.max(left.result, right.result);
        }
    }

    public static void main(String[] args) {
        int[] numbers = new int[500_000];
        Random generator = new Random();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = generator.nextInt();
        }
        SelectMaxProblem problem = new SelectMaxProblem(numbers, 0, numbers.length - 1);
        int threshold = 1000;
        int nThreads = 4;
        MaxWithFJ mfj = new MaxWithFJ(problem, threshold);
        ForkJoinPool fjPool = new ForkJoinPool(nThreads);
        fjPool.invoke(mfj);
        int result = mfj.result;
        System.out.println(result);
    }
}
