public class P1_SumOfMultiplesOf3Or5 {
    private static final int LIMIT = 1000;

    public static void main(String[] args) {
        int sum = 0;

        for (int i = 3; i < LIMIT; i+=3) {
            sum += i;
        }

        for (int i = 5; i < LIMIT; i+=5) {
            if (i % 15 != 0) sum += i;
        }

        System.out.println(sum);
    }
}
