package demo.ready;

import java.util.concurrent.RecursiveAction;

class IncrementArrayTask extends RecursiveAction {

    private static final int THRESHOLD = 1000;

    final long[] array;
    final int lo, hi;

    IncrementArrayTask(long[] array, int lo, int hi) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
    }

    @Override
    protected void compute() {
        if (hi - lo < THRESHOLD) {
            for (int i = lo; i < hi; ++i)
                array[i]++;
        } else {
            int mid = (lo + hi) >>> 1;
            invokeAll(new IncrementArrayTask(array, lo, mid),
                    new IncrementArrayTask(array, mid, hi));
        }
    }
}
