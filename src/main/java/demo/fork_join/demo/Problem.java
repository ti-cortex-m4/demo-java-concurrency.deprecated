package demo.fork_join.demo;

abstract class Problem {

    protected final int[] data;
    protected final int size;

    public Problem(int[] data, int size) {
        this.data = data;
        this.size = size;
    }

    public Problem(int[] data) {
        this.data = data;
        this.size = data.length;
    }

    public long solve() {
        return solve(0, size);
    }

    abstract long solve(int l, int r);

    public int size() {
        return size;
    }
}
