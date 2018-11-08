public class P3_LargestPrimeFactor {
    private static final Long N = 600851475143L;

    public static void main(String[] args) {
        Long maxDivisor = -1L;
        Long n = N;
        for (Long i = 3L; i < Math.sqrt(n); i+=2) {
            while (n % i == 0) {
                n /= i ;
                maxDivisor = i;
            }
        }

        if (n > 2) maxDivisor = n;

        System.out.println(maxDivisor);
    }
}
