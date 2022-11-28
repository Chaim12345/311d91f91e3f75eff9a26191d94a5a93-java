package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.collections.CharIterator;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes3.dex */
public final class CharProgressionIterator extends CharIterator {
    private final int finalElement;
    private boolean hasNext;
    private int next;
    private final int step;

    public CharProgressionIterator(char c2, char c3, int i2) {
        this.step = i2;
        this.finalElement = c3;
        boolean z = true;
        if (i2 <= 0 ? Intrinsics.compare((int) c2, (int) c3) < 0 : Intrinsics.compare((int) c2, (int) c3) > 0) {
            z = false;
        }
        this.hasNext = z;
        this.next = z ? c2 : c3;
    }

    public final int getStep() {
        return this.step;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override // kotlin.collections.CharIterator
    public char nextChar() {
        int i2 = this.next;
        if (i2 != this.finalElement) {
            this.next = this.step + i2;
        } else if (!this.hasNext) {
            throw new NoSuchElementException();
        } else {
            this.hasNext = false;
        }
        return (char) i2;
    }
}
