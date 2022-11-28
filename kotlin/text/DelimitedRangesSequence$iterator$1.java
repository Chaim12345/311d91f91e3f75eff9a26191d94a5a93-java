package kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class DelimitedRangesSequence$iterator$1 implements Iterator<IntRange>, KMappedMarker {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ DelimitedRangesSequence f11235a;
    private int counter;
    private int currentStartIndex;
    @Nullable
    private IntRange nextItem;
    private int nextSearchIndex;
    private int nextState = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DelimitedRangesSequence$iterator$1(DelimitedRangesSequence delimitedRangesSequence) {
        int i2;
        CharSequence charSequence;
        int coerceIn;
        this.f11235a = delimitedRangesSequence;
        i2 = delimitedRangesSequence.startIndex;
        charSequence = delimitedRangesSequence.input;
        coerceIn = RangesKt___RangesKt.coerceIn(i2, 0, charSequence.length());
        this.currentStartIndex = coerceIn;
        this.nextSearchIndex = coerceIn;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0021, code lost:
        if (r0 < r4) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void calcNext() {
        int i2;
        CharSequence charSequence;
        Function2 function2;
        CharSequence charSequence2;
        IntRange until;
        IntRange intRange;
        CharSequence charSequence3;
        int lastIndex;
        CharSequence charSequence4;
        int lastIndex2;
        int i3;
        if (this.nextSearchIndex < 0) {
            this.nextState = 0;
            this.nextItem = null;
            return;
        }
        i2 = this.f11235a.limit;
        int i4 = -1;
        if (i2 > 0) {
            int i5 = this.counter + 1;
            this.counter = i5;
            i3 = this.f11235a.limit;
        }
        int i6 = this.nextSearchIndex;
        charSequence = this.f11235a.input;
        if (i6 <= charSequence.length()) {
            function2 = this.f11235a.getNextMatch;
            charSequence2 = this.f11235a.input;
            Pair pair = (Pair) function2.invoke(charSequence2, Integer.valueOf(this.nextSearchIndex));
            if (pair == null) {
                int i7 = this.currentStartIndex;
                charSequence3 = this.f11235a.input;
                lastIndex = StringsKt__StringsKt.getLastIndex(charSequence3);
                intRange = new IntRange(i7, lastIndex);
                this.nextItem = intRange;
                this.nextSearchIndex = i4;
                this.nextState = 1;
            }
            int intValue = ((Number) pair.component1()).intValue();
            int intValue2 = ((Number) pair.component2()).intValue();
            until = RangesKt___RangesKt.until(this.currentStartIndex, intValue);
            this.nextItem = until;
            int i8 = intValue + intValue2;
            this.currentStartIndex = i8;
            i4 = i8 + (intValue2 == 0 ? 1 : 0);
            this.nextSearchIndex = i4;
            this.nextState = 1;
        }
        int i9 = this.currentStartIndex;
        charSequence4 = this.f11235a.input;
        lastIndex2 = StringsKt__StringsKt.getLastIndex(charSequence4);
        intRange = new IntRange(i9, lastIndex2);
        this.nextItem = intRange;
        this.nextSearchIndex = i4;
        this.nextState = 1;
    }

    public final int getCounter() {
        return this.counter;
    }

    public final int getCurrentStartIndex() {
        return this.currentStartIndex;
    }

    @Nullable
    public final IntRange getNextItem() {
        return this.nextItem;
    }

    public final int getNextSearchIndex() {
        return this.nextSearchIndex;
    }

    public final int getNextState() {
        return this.nextState;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.nextState == -1) {
            calcNext();
        }
        return this.nextState == 1;
    }

    @Override // java.util.Iterator
    @NotNull
    public IntRange next() {
        if (this.nextState == -1) {
            calcNext();
        }
        if (this.nextState != 0) {
            IntRange intRange = this.nextItem;
            Objects.requireNonNull(intRange, "null cannot be cast to non-null type kotlin.ranges.IntRange");
            this.nextItem = null;
            this.nextState = -1;
            return intRange;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setCounter(int i2) {
        this.counter = i2;
    }

    public final void setCurrentStartIndex(int i2) {
        this.currentStartIndex = i2;
    }

    public final void setNextItem(@Nullable IntRange intRange) {
        this.nextItem = intRange;
    }

    public final void setNextSearchIndex(int i2) {
        this.nextSearchIndex = i2;
    }

    public final void setNextState(int i2) {
        this.nextState = i2;
    }
}
