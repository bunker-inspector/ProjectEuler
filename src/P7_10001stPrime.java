import util.PrimeSieve;

public class P7_10001stPrime {
    public static void main(String[] args) {
        var sieve = new PrimeSieve(10000000);

        System.out.println(sieve.getNthPrime(10001));
    }
}
