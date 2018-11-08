import java.util.List;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class P4_LargestPalindromeProduct {
    private static boolean isPalindrome(Integer input) {
        var check = input.toString();

        for (int front = 0, back = check.length() - 1; front < back; front++, back--) {
            if (check.charAt(front) != check.charAt(back)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(List.of(8008, 2005, 909, 45)
                .stream()
                .map(P4_LargestPalindromeProduct::isPalindrome)
                .collect(Collectors.toList()));

        Function<Predicate<Integer>, Stream<Integer>> f = fn -> IntStream.range(100, 999).boxed().filter(fn);

        Predicate<Integer> even = x -> x % 2 == 0;
        Predicate<Integer> odd  = x -> x % 2 != 0;

        var A = f.apply(even);
        var B = f.apply(odd);

        var aMax= A.parallel()
                .map(x -> f.apply(even)
                            .skip((x / 2) - 50)
                            .map(y -> x * y)
                            .filter(P4_LargestPalindromeProduct::isPalindrome)
                            .mapToInt(v -> v)
                            .max())
                .filter(OptionalInt::isPresent)
                .mapToInt(OptionalInt::getAsInt)
                .max()
                .getAsInt();

        var bMax= B.parallel()
                .map(x -> f.apply(odd)
                        .skip((x - 101) / 50)
                        .map(y -> x * y)
                        .filter(P4_LargestPalindromeProduct::isPalindrome)
                        .mapToInt(v -> v)
                        .max())
                .filter(OptionalInt::isPresent)
                .mapToInt(OptionalInt::getAsInt)
                .max()
                .getAsInt();

        System.out.println(aMax > bMax ? aMax : bMax);
    }
}
