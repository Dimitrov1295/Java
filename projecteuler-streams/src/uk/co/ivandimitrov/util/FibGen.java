package uk.co.ivandimitrov.util;

public class FibGen {
    private static int prevFib = 1;

    /**
     * This method can be used in a loop to get the next Fibonacci number when the
     * loop starts from 1.
     * 
     * @param n Current Fibonacci number
     * @return Returns the next Fibonacci number if this method has been used in a
     *         loop starting from 1. Otherwise, returns n + (1 or some other number
     *         previously specified).
     */
    public static int nextFib(int n) {
        int temp = prevFib;
        prevFib = n;
        return temp + n;
    }
}