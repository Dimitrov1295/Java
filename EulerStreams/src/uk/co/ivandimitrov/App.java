package uk.co.ivandimitrov;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This program doesn't use any loops and calculates the first 5 questions from
 * project euler.
 */
public final class App {
    private App() {
    }

    public static void main(String[] args) {
        System.out.println("PE1 " + projectEuler1());
        System.out.println("PE2 " + projectEuler2());
        System.out.println("PE3 " + projectEuler3());
        System.out.println("PE4 " + projectEuler4());
        System.out.println("PE5 " + projectEuler5());
    }

    /**
     * If we list all the natural numbers below 10 that are multiples of 3 or 5, we
     * get 3, 5, 6 and 9. The sum of these multiples is 23.
     * 
     * Find the sum of all the multiples of 3 or 5 below 1000.
     * 
     * @return Answer: 233168
     */
    private static int projectEuler1() {
        return IntStream.range(0, 1000).filter(n -> n % 3 == 0 || n % 5 == 0).sum();
    }

    /**
     * 
     * Each new term in the Fibonacci sequence is generated by adding the previous
     * two terms. By starting with 1 and 2, the first 10 terms will be:
     * 
     * 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
     * 
     * By considering the terms in the Fibonacci sequence whose values do not exceed
     * four million, find the sum of the even-valued terms.
     * 
     * @return Answer: 4613732
     */
    private static int projectEuler2() {
        return Stream.iterate(1, n -> n <= 4000000, FibGen::nextFib).filter(n -> n % 2 == 0).mapToInt(n -> n).sum();
    }

    /**
     * The prime factors of 13195 are 5, 7, 13 and 29.
     * 
     * What is the largest prime factor of the number 600851475143 ?
     * 
     * @return Answer: 6857
     */
    private static int projectEuler3() {
        // It's nice that this problem gives us a number that doesn't have repeating
        // prime factors, otherwise this wouldn't work.
        return PrimeGen.getPrimes((int) Math.sqrt(600851475143.0)).stream().mapToInt(i -> i)
                .filter(i -> (600851475143.0 % i == 0)).max().getAsInt();
    }

    /**
     * A palindromic number reads the same both ways. The largest palindrome made
     * from the product of two 2-digit numbers is 9009 = 91 × 99.
     * 
     * Find the largest palindrome made from the product of two 3-digit numbers.
     * 
     * @return Answer: 906609
     */
    private static int projectEuler4() {
        // It is starting to get real ugly real fast.
        return IntStream
                .iterate(999, i -> i > 100, i -> i -= 1).map(i -> IntStream.iterate(999, n -> n > 100, n -> n -= 1)
                        .filter(n -> EulerUtils.isPalindrome(i * n)).map(n -> i * n).findFirst().orElse(0))
                .max().getAsInt();
    }

    /**
     * 2520 is the smallest number that can be divided by each of the numbers from 1
     * to 10 without any remainder.
     * 
     * What is the smallest positive number that is evenly divisible by all of the
     * numbers from 1 to 20?
     * 
     * @return Answer: 232792560
     */
    private static int projectEuler5() {
        // Gets the correct result, but needs optimization.
        return IntStream.iterate(20, i -> i < Integer.MAX_VALUE, i -> i += 20)
                .filter(i -> IntStream.range(1, 21).allMatch(n -> i % n == 0)).findFirst().getAsInt();
    }
}
