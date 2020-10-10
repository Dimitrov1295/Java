package uk.co.ivandimitrov.util;

import java.util.stream.IntStream;

public class EulerUtils {

    /**
     * Checks if a number is a palindrome using streams.
     * 
     * @param n the number to check
     * @return Returns wether or not n is a palindrome.
     */
    public static boolean isPalindrome(int n) {
        return (IntStream.iterate(n, i -> i > 0, i -> i / 10).reduce(0, (a, b) -> (a += b % 10) * 10) / 10) == n;
    }

}