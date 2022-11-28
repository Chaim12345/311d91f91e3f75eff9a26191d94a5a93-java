package kotlinx.serialization.internal;

import kotlin.PublishedApi;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;
import org.jetbrains.annotations.NotNull;
@PublishedApi
/* loaded from: classes3.dex */
public final class ElementMarker {
    @NotNull
    private static final Companion Companion = new Companion(null);
    @Deprecated
    @NotNull
    private static final long[] EMPTY_HIGH_MARKS = new long[0];
    @NotNull
    private final SerialDescriptor descriptor;
    @NotNull
    private final long[] highMarksArray;
    private long lowerMarks;
    @NotNull
    private final Function2<SerialDescriptor, Integer, Boolean> readIfAbsent;

    /* loaded from: classes3.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ElementMarker(@NotNull SerialDescriptor descriptor, @NotNull Function2<? super SerialDescriptor, ? super Integer, Boolean> readIfAbsent) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(readIfAbsent, "readIfAbsent");
        this.descriptor = descriptor;
        this.readIfAbsent = readIfAbsent;
        int elementsCount = descriptor.getElementsCount();
        if (elementsCount <= 64) {
            this.lowerMarks = elementsCount != 64 ? (-1) << elementsCount : 0L;
            this.highMarksArray = EMPTY_HIGH_MARKS;
            return;
        }
        this.lowerMarks = 0L;
        this.highMarksArray = prepareHighMarksArray(elementsCount);
    }

    private final void markHigh(int i2) {
        int i3 = (i2 >>> 6) - 1;
        long[] jArr = this.highMarksArray;
        jArr[i3] = jArr[i3] | (1 << (i2 & 63));
    }

    private final int nextUnmarkedHighIndex() {
        int length = this.highMarksArray.length;
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 + 1;
            int i4 = i3 * 64;
            long j2 = this.highMarksArray[i2];
            while (j2 != -1) {
                int numberOfTrailingZeros = Long.numberOfTrailingZeros(~j2);
                j2 |= 1 << numberOfTrailingZeros;
                int i5 = numberOfTrailingZeros + i4;
                if (this.readIfAbsent.invoke(this.descriptor, Integer.valueOf(i5)).booleanValue()) {
                    this.highMarksArray[i2] = j2;
                    return i5;
                }
            }
            this.highMarksArray[i2] = j2;
            i2 = i3;
        }
        return -1;
    }

    private final long[] prepareHighMarksArray(int i2) {
        int lastIndex;
        long[] jArr = new long[(i2 - 1) >>> 6];
        if ((i2 & 63) != 0) {
            lastIndex = ArraysKt___ArraysKt.getLastIndex(jArr);
            jArr[lastIndex] = (-1) << i2;
        }
        return jArr;
    }

    public final void mark(int i2) {
        if (i2 < 64) {
            this.lowerMarks |= 1 << i2;
        } else {
            markHigh(i2);
        }
    }

    public final int nextUnmarkedIndex() {
        int numberOfTrailingZeros;
        int elementsCount = this.descriptor.getElementsCount();
        do {
            long j2 = this.lowerMarks;
            if (j2 == -1) {
                if (elementsCount > 64) {
                    return nextUnmarkedHighIndex();
                }
                return -1;
            }
            numberOfTrailingZeros = Long.numberOfTrailingZeros(~j2);
            this.lowerMarks |= 1 << numberOfTrailingZeros;
        } while (!this.readIfAbsent.invoke(this.descriptor, Integer.valueOf(numberOfTrailingZeros)).booleanValue());
        return numberOfTrailingZeros;
    }
}
