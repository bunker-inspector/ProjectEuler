import util.FixedSizeSummaryQueue;
import util.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class P11_LargestGridProduct {
    private static final Long N = 4L;

    public static void main(String[] args) throws IOException {
        final var grid = Resource.stream("P11_Grid.txt")
                .lines()
                .map(l -> l.trim())
                .map(l -> l.split(" "))
                .map(l -> {
                    var curr = new ArrayList<Long>();
                    curr.addAll(Arrays
                            .asList(l)
                            .stream()
                            .map(x -> Long.valueOf(x))
                            .collect(Collectors.toList()));
                    return curr;
                })
                .collect(Collectors.toList());

        var queue = FixedSizeSummaryQueue.product(N);

        var maxProduct = 0L;
        var width = grid.get(0).size();
        var height = grid.size();

        // Rows
        for (ArrayList<Long> row: grid) {
            for (int i = 0; i < width; i++) {
                if (row.get(i) == 0L) {
                    queue.flush(1L);
                } else {
                    queue.push(row.get(i));
                    if (queue.getSummary() > maxProduct) maxProduct = queue.getSummary();
                }
            }
            queue.flush(1L);
        }

        queue.flush(1L);

        // Columns
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                var curr = grid.get(j).get(i);
                if (curr == 0) {
                    queue.flush(1L);
                } else {
                    queue.push(curr);
                    if (queue.getSummary() > maxProduct) maxProduct = queue.getSummary();
                }
            }
            queue.flush(1L);
        }

        queue.flush(1L);

        // Diagonal down-right, bottom half
        for (int i = 0; i + N <= height; i++) {
            for (int _i = i, _j = 0; _i < height; _i++, _j++) {
                var curr = grid.get(_i).get(_j);

                if (curr == 0) {
                    queue.flush(1L);
                } else {
                    queue.push(curr);
                    if (queue.getSummary() > maxProduct) maxProduct = queue.getSummary();
                }
            }
            queue.flush(1L);
        }

        // Diagonal down-right top half
        for (int i = 1; i + N <= width; i++) {
            for (int _i = 0, _j = i; _j < width; _i++, _j++) {
                var curr = grid.get(_i).get(_j);

                if (curr == 0) {
                    queue.flush(1L);
                } else {
                    queue.push(curr);
                    if (queue.getSummary() > maxProduct) maxProduct = queue.getSummary();
                }
            }
            queue.flush(1L);
        }

        // Diagonal down-left bottom half
        for (int i = 0; i + N <= height; i++) {
            for (int _i = i, _j = width - 1; _i < height; _i++, _j--) {
                var curr = grid.get(_i).get(_j);

                if (curr == 0) {
                    queue.flush(1L);
                } else {
                    queue.push(curr);
                    if (queue.getSummary() > maxProduct) maxProduct = queue.getSummary();
                }
            }
            queue.flush(1L);
        }

        // Diagonal down-left top half
        for (int i = width - 2; i - N  >= -1; i--) {
            for (int _i = i, _j = 0; _i >= 0 ; _i--, _j++) {
                var curr = grid.get(_j).get(_i);

                if (curr == 0) {
                    queue.flush(1L);
                } else {
                    queue.push(curr);
                    if (queue.getSummary() > maxProduct) maxProduct = queue.getSummary();
                }
            }
            queue.flush(1L);
        }

        System.out.println(maxProduct);
    }
}
