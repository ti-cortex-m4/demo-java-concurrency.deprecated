package demo.completable_future;

import java.util.concurrent.TimeUnit;

public class Demo {

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tH:%1$tM:%1$tS.%1$tL %5$s %n");
    }

    private static java.util.logging.Logger LOGGER = java.util.logging.Logger.getAnonymousLogger();

    protected static void log(String s) {
        LOGGER.info(String.format("%-35s %s", Thread.currentThread().getName(), s));
    }

    protected static void delay(int delay) {
        try {
            TimeUnit.SECONDS.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected static String delayed(String s, int delay) {
        log(s + " started");
        delay(delay);
        log(s + " finished");
        return s;
    }

    protected static String delayed(String s) {
        return delayed(s, 1);
    }
}
