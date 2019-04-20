package demo.briangoetz2;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MergeSort extends RecursiveAction {

    private static final int SEQUENTIAL_THRESHOLD = 3;

    final int[] numbers;
    final int startPos, endPos;
    final int[] result;

    public MergeSort(int[] numbers, int startPos, int endPos) {
        this.numbers = numbers;
        this.startPos = startPos;
        this.endPos = endPos;
        this.result = new int[size()];
    }

    private void merge(MergeSort left, MergeSort right) {
        int i = 0, leftPos = 0, rightPos = 0, leftSize = left.size(), rightSize = right.size();
        while (leftPos < leftSize && rightPos < rightSize)
            result[i++] = (left.result[leftPos] <= right.result[rightPos])
                    ? left.result[leftPos++]
                    : right.result[rightPos++];
        while (leftPos < leftSize)
            result[i++] = left.result[leftPos++];
        while (rightPos < rightSize)
            result[i++] = right.result[rightPos++];
    }

    public int size() {
        return endPos - startPos;
    }

    @Override
    protected void compute() {
        if (size() < SEQUENTIAL_THRESHOLD) {
            System.arraycopy(numbers, startPos, result, 0, size());
            Arrays.sort(result, 0, size());
        } else {
            int midpoint = size() / 2;
            MergeSort left = new MergeSort(numbers, startPos, startPos + midpoint);
            MergeSort right = new MergeSort(numbers, startPos + midpoint, endPos);
            invokeAll(left, right);
            merge(left, right);
        }
    }

    public static void main(String[] args) {
        test2();
    }

    private static void test1() {
        int[] numbers = {12, 23, 100, 1, 2, 9};
        int nThreads = 4;
        MergeSort mfj = new MergeSort(numbers, 0, numbers.length);
        ForkJoinPool pool = new ForkJoinPool(nThreads);
        pool.invoke(mfj);
        printArray(mfj.result);
    }

    private static void test2() {
        int[] numbers = new int[1_000_000];
        Random generator = new Random();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = generator.nextInt();
        }
        int nThreads = 4;
        MergeSort mfj = new MergeSort(numbers, 0, numbers.length);
        ForkJoinPool pool = new ForkJoinPool(nThreads);
        long startTime = System.currentTimeMillis();
        pool.invoke(mfj);
        System.out.println("Spent time: " + (System.currentTimeMillis() - startTime));
    }

    private static void printArray(int[] arr) {
        for (int n : arr)
            System.out.printf("%d ", n);
        System.out.println();
    }
}
