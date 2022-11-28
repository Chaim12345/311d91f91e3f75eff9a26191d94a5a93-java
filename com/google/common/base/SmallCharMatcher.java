package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.CharMatcher;
import java.util.BitSet;
@GwtIncompatible
/* loaded from: classes2.dex */
final class SmallCharMatcher extends CharMatcher.NamedFastMatcher {
    private static final int C1 = -862048943;
    private static final int C2 = 461845907;
    private static final double DESIRED_LOAD_FACTOR = 0.5d;
    private final boolean containsZero;
    private final long filter;
    private final char[] table;

    private SmallCharMatcher(char[] cArr, long j2, boolean z, String str) {
        super(str);
        this.table = cArr;
        this.filter = j2;
        this.containsZero = z;
    }

    private boolean checkFilter(int i2) {
        return 1 == ((this.filter >> i2) & 1);
    }

    @VisibleForTesting
    static int d(int i2) {
        if (i2 == 1) {
            return 2;
        }
        int highestOneBit = Integer.highestOneBit(i2 - 1) << 1;
        while (highestOneBit * 0.5d < i2) {
            highestOneBit <<= 1;
        }
        return highestOneBit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CharMatcher e(BitSet bitSet, String str) {
        int i2;
        int cardinality = bitSet.cardinality();
        boolean z = bitSet.get(0);
        int d2 = d(cardinality);
        char[] cArr = new char[d2];
        int i3 = d2 - 1;
        int nextSetBit = bitSet.nextSetBit(0);
        long j2 = 0;
        while (nextSetBit != -1) {
            long j3 = (1 << nextSetBit) | j2;
            int f2 = f(nextSetBit);
            while (true) {
                i2 = f2 & i3;
                if (cArr[i2] == 0) {
                    break;
                }
                f2 = i2 + 1;
            }
            cArr[i2] = (char) nextSetBit;
            nextSetBit = bitSet.nextSetBit(nextSetBit + 1);
            j2 = j3;
        }
        return new SmallCharMatcher(cArr, j2, z, str);
    }

    static int f(int i2) {
        return Integer.rotateLeft(i2 * C1, 15) * C2;
    }

    @Override // com.google.common.base.CharMatcher
    void c(BitSet bitSet) {
        char[] cArr;
        if (this.containsZero) {
            bitSet.set(0);
        }
        for (char c2 : this.table) {
            if (c2 != 0) {
                bitSet.set(c2);
            }
        }
    }

    @Override // com.google.common.base.CharMatcher
    public boolean matches(char c2) {
        if (c2 == 0) {
            return this.containsZero;
        }
        if (checkFilter(c2)) {
            int length = this.table.length - 1;
            int f2 = f(c2) & length;
            int i2 = f2;
            do {
                char[] cArr = this.table;
                if (cArr[i2] == 0) {
                    return false;
                }
                if (cArr[i2] == c2) {
                    return true;
                }
                i2 = (i2 + 1) & length;
            } while (i2 != f2);
            return false;
        }
        return false;
    }
}
