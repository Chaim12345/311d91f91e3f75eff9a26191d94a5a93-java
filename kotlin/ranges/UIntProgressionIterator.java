package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.UnsignedKt;
import kotlin.collections.UIntIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
@SinceKotlin(version = "1.3")
/* loaded from: classes3.dex */
final class UIntProgressionIterator extends UIntIterator {
    private final int finalElement;
    private boolean hasNext;
    private int next;
    private final int step;

    private UIntProgressionIterator(int i2, int i3, int i4) {
        this.finalElement = i3;
        boolean z = true;
        int uintCompare = UnsignedKt.uintCompare(i2, i3);
        if (i4 <= 0 ? uintCompare < 0 : uintCompare > 0) {
            z = false;
        }
        this.hasNext = z;
        this.step = UInt.m281constructorimpl(i4);
        this.next = this.hasNext ? i2 : i3;
    }

    public /* synthetic */ UIntProgressionIterator(int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2, i3, i4);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override // kotlin.collections.UIntIterator
    /* renamed from: nextUInt-pVg5ArA */
    public int mo350nextUIntpVg5ArA() {
        int i2 = this.next;
        if (i2 != this.finalElement) {
            this.next = UInt.m281constructorimpl(this.step + i2);
        } else if (!this.hasNext) {
            throw new NoSuchElementException();
        } else {
            this.hasNext = false;
        }
        return i2;
    }
}
