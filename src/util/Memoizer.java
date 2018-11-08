package util;

import java.util.HashMap;
import java.util.function.Function;

public class Memoizer<T, E> {
     private HashMap<T, E> memos;
     private Function<T,E> function;

     public Memoizer(Function<T, E> function) {
         this.memos = new HashMap<>();
         this.function = function;
    }

    public E call(T argument) {
         if (memos.containsKey(argument)) {
             return memos.get(argument);
         } else {
             final E result = function.apply(argument);

             memos.put(argument, result);

             return result;
         }

    }
}
