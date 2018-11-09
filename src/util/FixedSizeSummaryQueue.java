package util;

import java.util.Optional;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FixedSizeSummaryQueue<T, A extends BiFunction<T, T, T>, B extends BiFunction<T, T, T>> {
    private BiFunction<T, T, T> in;
    private BiFunction<T, T, T> out;
    private Function<T, Optional<T>> _push;
    private T summary;
    private LinkedBlockingQueue<T> queue;
    private Long limit;

    private Optional<T> defaultPush(T a)  {
        queue.add(a);
        summary = in.apply(summary, a);

        if (queue.size() == limit) {
            this._push = (b -> {
                queue.add(b);
                summary = in.apply(summary, b);

                var removed = queue.remove();

                summary = out.apply(summary, removed);

                return Optional.of(removed);
            });
        }

        return Optional.empty();
    }

    public Optional<T> push(T t) {
        return _push.apply(t);
    }

    public FixedSizeSummaryQueue(T defaultValue, A a, B b, Long l) {
        summary = defaultValue;
        in = a;
        out = b;

        queue = new LinkedBlockingQueue<>();

        limit = l;

        _push = this::defaultPush;
    }

    public void flush(T t) {
        queue.clear();
        summary = t;

        _push = this::defaultPush;
    }

    public T getSummary() {
        return summary;
    }
}
