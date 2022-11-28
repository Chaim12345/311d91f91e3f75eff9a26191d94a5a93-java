package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.collections.ULongIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
@SinceKotlin(version = "1.3")
/* loaded from: classes3.dex */
final class ULongProgressionIterator extends ULongIterator {
    private final long finalElement;
    private boolean hasNext;
    private long next;
    private final long step;

    private ULongProgressionIterator(long j2, long j3, long j4) {
        this.finalElement = j3;
        boolean z = true;
        if (j4 <= 0 ? UnsignedKt.ulongCompare(j2, j3) < 0 : UnsignedKt.ulongCompare(j2, j3) > 0) {
            z = false;
        }
        this.hasNext = z;
        this.step = ULong.m359constructorimpl(j4);
        this.next = this.hasNext ? j2 : j3;
    }

    public /* synthetic */ ULongProgressionIterator(long j2, long j3, long j4, DefaultConstructorMarker defaultConstructorMarker) {
        this(j2, j3, j4);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override // kotlin.collections.ULongIterator
    /* renamed from: nextULong-s-VKNKU */
    public long mo428nextULongsVKNKU() {
        long j2 = this.next;
        if (j2 != this.finalElement) {
            this.next = ULong.m359constructorimpl(this.step + j2);
        } else if (!this.hasNext) {
            throw new NoSuchElementException();
        } else {
            this.hasNext = false;
        }
        return j2;
    }
}
