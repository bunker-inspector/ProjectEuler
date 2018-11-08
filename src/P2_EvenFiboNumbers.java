import util.Memoizer;

import javax.sound.midi.SysexMessage;

public class P2_EvenFiboNumbers {
    public static void main(String[] args) {
        int[] memos = new int[5000000];
        memos[1] = 1;
        memos[2] = 2;

        long evenTotal = 2;

        int lastValue = 0;
        for (int term = 3; lastValue < 4000000; term++) {
            memos[term] = memos[term-1] + memos[term-2];

            if (memos[term] % 2 == 0) {
                evenTotal += memos[term];
            }
            lastValue = memos[term];
        }

        System.out.println(evenTotal);
    }
}
