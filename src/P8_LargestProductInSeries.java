import util.FixedSizeSummaryQueue;
import util.Resource;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.LinkedBlockingQueue;

public class P8_LargestProductInSeries {
    private static final long N = 13L;

    public static void main(String[] args) throws IOException  {
        var queue = new LinkedBlockingQueue<BigInteger>();

        var nums = Resource.stream("P8_1000-digit-number.txt")
                .lines()
                .map(l -> l.trim())
                .reduce("", (acc, line) -> acc + line)
                .chars()
                .map(c -> c - 48)
                .toArray();

        var max = new BigInteger("0");

        for (int i = 0; i < nums.length; i++) {
            if (queue.size() >= N) queue.remove();

            queue.add(new BigInteger("" + nums[i]));

            var product = new BigInteger("1");

            for (BigInteger b: queue) {
                product = product.multiply(b);
            }

            if (product.compareTo(max) > 0) max = product;
        }


        System.out.println(max);
    }
}
