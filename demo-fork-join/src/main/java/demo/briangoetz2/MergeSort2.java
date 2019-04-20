package demo.briangoetz2;

import java.util.concurrent.RecursiveAction;

public class MergeSort2 extends RecursiveAction {

    final int[] numbers;
    final int startPos, endPos;
    final int[] result;

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

    protected void compute() {
        if (size() < SEQUENTIAL_THRESHOLD) {
            System.arraycopy(numbers, startPos, result, 0, size());
            Arrays.sort(result, 0, size());
        } else {
            int midpoint = size() / 2;
            MergeSort left = new MergeSort(numbers, startPos, startPos + midpoint);
            MergeSort right = new MergeSort(numbers, startPos + midpoint, endPos);
            coInvoke(left, right);
            merge(left, right);
        }
    }
}
