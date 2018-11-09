import util.FixedSizeSummaryQueue;
import util.Resource;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.LinkedBlockingQueue;

public class P8_LargestProductInSeries {
    private static final long N = 13L;

    public static void main(String[] args) throws IOException  {
        var digits = new FixedSizeSummaryQueue<>(new BigInteger("1"),
                (a, b) -> a.multiply(b),
                (a, b) -> a.divide(b),
                N
        );

        var nums = Resource.stream("P8_1000-digit-number.txt")
                .lines()
                .map(l -> l.trim())
                .reduce("", (acc, line) -> acc + line)
                .chars()
                .map(c -> c - 48)
                .toArray();

        var max = new BigInteger("0");

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                // 0 makes it impossible for max until it is flushed completely. Skip until 0 is gone
                i += (3 * N) + 1;
                digits.flush(new BigInteger("1"));
            } else {
                digits.push(new BigInteger(nums[i] + ""));

                if (digits.getSummary().compareTo(max) > 0) max = digits.getSummary();
            }
        }

        System.out.println(max);
    }
}
