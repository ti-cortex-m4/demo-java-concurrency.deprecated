package demo.fork_join.mkyong;

public class Main2 {

    public static void main(String[] args) {

        System.out.println(ForkJoinAdd.startForkJoinSum(1_000_000));

    }

}