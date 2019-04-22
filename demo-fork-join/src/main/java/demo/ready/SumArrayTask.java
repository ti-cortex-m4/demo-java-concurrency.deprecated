package demo.ready;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

class SumArrayTask extends RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long threshold = 10_000;

    SumArrayTask(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private SumArrayTask(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        int length = end - start;
        if (length <= threshold) {
            return add();
        }

        SumArrayTask firstTask = new SumArrayTask(numbers, start, start + length / 2);
        firstTask.fork(); //start asynchronously

        SumArrayTask secondTask = new SumArrayTask(numbers, start + length / 2, end);

        Long secondTaskResult = secondTask.compute();
        Long firstTaskResult = firstTask.join();

        return firstTaskResult + secondTaskResult;

    }

    private long add() {
        long result = 0;
        for (int i = start; i < end; i++) {
            result += numbers[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 100;
        long[] numbers = LongStream.rangeClosed(1, n).toArray();

        ForkJoinPool fjp = ForkJoinPool.commonPool();
        SumArrayTask task = new SumArrayTask(numbers);
        System.out.println(fjp.invoke(task));
    }
}
