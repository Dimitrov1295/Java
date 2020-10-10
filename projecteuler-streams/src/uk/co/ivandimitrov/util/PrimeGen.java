package uk.co.ivandimitrov.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeGen {

    /**
     * Generates a list of prime numbers up to n.
     * 
     * @param n the limit up to which primes should be generated.
     * @return Returns a list containing prime numbers only.
     */
    public static List<Integer> getPrimes(int n) {
        // I don't like this approach, but I couldn't figure out another solution given
        // the restriction of not using loops.
        boolean prime[] = new boolean[n + 1];
        Arrays.fill(prime, true);
        IntStream.range(2, (int) Math.sqrt(n)).filter(i -> prime[i]).forEach(
                i -> IntStream.iterate(i * 2, num -> num < n, num -> num + i).forEach(num -> prime[num] = false));
        return IntStream.range(2, n).filter(i -> prime[i]).boxed().collect(Collectors.toList());
    }
}