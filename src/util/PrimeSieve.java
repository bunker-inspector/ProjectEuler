package util;

import java.util.ArrayList;
import java.util.Arrays;

public class PrimeSieve {
    private ArrayList<Integer> primes = new ArrayList<>();

    private void fillSieve(int count) {
        boolean[] sieve = new boolean[count];

        Arrays.fill(sieve,true);        // assume all integers are prime.
        sieve[0] = sieve[1] = false;       // we know 0 and 1 are not prime.
        for (int i=2; i < sieve.length; i++) {
            //if the number is prime,
            //then go through all its multiples and make their values false.
            if(sieve[i]) {
                for (int j = 2; i * j < sieve.length; j++) {
                    sieve[i * j] = false;
                }
            }
        }

        for (int i = 0; i < sieve.length; i++) {
            if (sieve[i]) {
                primes.add(i);
            }
        }
    }

    public PrimeSieve(int count) {
        fillSieve(count);
    }

    public int getNthPrime(int nThPrime) {
        if (nThPrime < 1) {
            return -1;
        }

        if (nThPrime > primes.size()) {
            return -1;
        }

        return primes.get(nThPrime - 1);
    }
}
