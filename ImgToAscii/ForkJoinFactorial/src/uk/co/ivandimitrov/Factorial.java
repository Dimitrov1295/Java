package uk.co.ivandimitrov;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

public class Factorial extends RecursiveTask<BigInteger> {

    /**
     *
     */
    private static final long serialVersionUID = -6326166104577511367L;
    private int from, to;

    static final int THRESHOLD = 300;

    /**
     * Takes the number of which the factorial is to be calculated.
     * 
     * @param to The number we want to get the factorial of.
     */
    public Factorial(int to) {
        this.from = 1;
        this.to = to;
    }

    // Private constructor that works with the FJ framework to compute the
    // factorial by subdividing the tasks until they're small enough to compute.
    private Factorial(int from, int to) {
        this.from = from;
        this.to = to;
    }

    // Multiplies the integers from this.from to this.to variables and returns the
    // result.
    private BigInteger factorial() {
        BigInteger ans = BigInteger.ONE;
        for (int i = from; i <= to; i++)
            ans = ans.multiply(BigInteger.valueOf(i));
        return ans;
    }

    // A standard RecursiveTask compute method that does the hard work. Reference:
    // https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/RecursiveTask.html
    @Override
    protected BigInteger compute() {
        BigInteger result = BigInteger.ONE;
        if (from + THRESHOLD > to) {
            result = factorial();
        } else {
            int mid = (from + to) / 2;
            Factorial fact1 = new Factorial(from, mid);
            Factorial fact2 = new Factorial(mid + 1, to);
            fact2.fork();
            BigInteger f1 = fact1.compute();
            BigInteger f2 = fact2.join();
            result = f1.multiply(f2);
        }
        return result;
    }

}