import java.util.stream.IntStream;

public class P6_SumSqareDifference {
    private static int getSumOfSqares(int[] input) {
        int result = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = i; j < input.length; j++) {
                // Square of sums includes all sum of squares terms
                // If i = j, that is a sum of squares term, skip
                if (i != j) {
                    //For each pair i,j, square of sums will include i * j and j * i
                    // Double it and only consider of of pair
                    result += (2 * i * j);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getSumOfSqares(IntStream.rangeClosed(0, 100).toArray()));
    }
}
