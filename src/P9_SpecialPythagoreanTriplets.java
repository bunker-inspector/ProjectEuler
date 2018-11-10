import java.util.stream.IntStream;

public class P9_SpecialPythagoreanTriplets {
    private static final int SUM_TARGET = 1000;

    public static void main(String[] args) {
        final var CHECK_MAX = 1000;

        final var product = IntStream.rangeClosed(0, CHECK_MAX)
                .map(a -> {
                    final var candidates = IntStream.range(a, CHECK_MAX)
                            .map(b -> {
                                final var c = Math.sqrt((a * a) + (b * b));

                                return (int) (((a + b + c) == 1000.0)
                                        ? a * b * c : -1);
                            })
                            .filter(x -> x > 0)
                            .findFirst();
                    return candidates.isPresent()
                            ? candidates.getAsInt()
                            : -1;
                })
                .filter(x -> x > 0)
                .findFirst();

        System.out.println(product.isPresent() ? product.getAsInt() : "You fucked up");
    }
}
