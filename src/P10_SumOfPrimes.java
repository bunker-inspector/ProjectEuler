import util.PrimeSieve;

import java.math.BigInteger;

public class P10_SumOfPrimes {
    private static final int N = 2000000;

    public static void main(String[] args) {
        var sieve = new PrimeSieve(N);

        var sum = sieve.stream()
                .map(i -> new BigInteger(i+ ""))
                .reduce(new BigInteger("0"), (a, b) -> a.add(b));

        System.out.println(sum);
    }
}
