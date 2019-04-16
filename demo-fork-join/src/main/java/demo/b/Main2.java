package demo.b;

public class Main2 {
    MyRecursiveTask myRecursiveTask = new MyRecursiveTask(128);

    long mergedResult = forkJoinPool.invoke(myRecursiveTask);

System.out.println("mergedResult = " + mergedResult);
}
