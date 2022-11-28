package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.collections.LongIterator;
/* loaded from: classes3.dex */
public final class LongProgressionIterator extends LongIterator {
    private final long finalElement;
    private boolean hasNext;
    private long next;
    private final long step;

    public LongProgressionIterator(long j2, long j3, long j4) {
        this.step = j4;
        this.finalElement = j3;
        boolean z = true;
        if (j4 <= 0 ? j2 < j3 : j2 > j3) {
            z = false;
        }
        this.hasNext = z;
        this.next = z ? j2 : j3;
    }

    public final long getStep() {
        return this.step;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override // kotlin.collections.LongIterator
    public long nextLong() {
        long j2 = this.next;
        if (j2 != this.finalElement) {
            this.next = this.step + j2;
        } else if (!this.hasNext) {
            throw new NoSuchElementException();
        } else {
            this.hasNext = false;
        }
        return j2;
    }
}
