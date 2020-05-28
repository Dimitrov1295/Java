package uk.co.ivandimitrov;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;

public class App {
    public static void main(String[] args) {
        Instant start = Instant.now(); // An instant in time before the computation starts.
        App m = new App();
        m.main();
        Instant finish = Instant.now(); // An instant in time after the computation has finished.
        long timeElapsed = Duration.between(start, finish).toMillis();// Time between start and finish.
        System.out.println();
        System.out.println(timeElapsed / 1000.0 + " seconds!");// Divide milliseconds by 1_000 to get seconds.
    }

    /**
     * Submits the factorial task to a ForkJoinPool
     */
    public void main() {
        new ForkJoinPool().invoke(new Factorial(1_000_000)); // takes 2 to 3 seconds to execute on my i7-8750H and about
                                                             // 12 seconds
        // total to
        // write the result to a .txt file using a BufferedWriter. For reference,
        // it takes
        // about 8 minutes to do the same thing without the ForkJoinPool.
    }

}
