package demo.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

class Futures {

    private final ExecutorService executor;

    Futures(ExecutorService executor) {
        this.executor = executor;
    }

    Future<String> toUpperCase(String s) {
        return executor.submit(() -> {
            Thread.sleep(1000);
            return s.toUpperCase();
        });
    }
}
