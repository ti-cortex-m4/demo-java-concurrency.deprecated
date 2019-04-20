package demo.briangoetz;

public class SelectMaxProblem {

    private final int[] numbers;
    private final int start;
    private final int end;
    public final int size;

    public SelectMaxProblem(int[] numbers, int start, int end, int size) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.size = size;
    }

    public int solveSequentially() {
        int max = Integer.MIN_VALUE;
        for (int i = start; i < end; i++) {
            int n = numbers[i];
            if (n > max)
                max = n;
        }
        return max;
    }

    public SelectMaxProblem subproblem(int subStart, int subEnd) {
        return new SelectMaxProblem(numbers, start + subStart, start + subEnd);
    }
}
