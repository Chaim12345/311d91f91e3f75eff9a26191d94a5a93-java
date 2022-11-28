package kotlin.ranges;

import java.util.Iterator;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.WasExperimental;
import kotlin.internal.UProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SinceKotlin(version = "1.5")
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
/* loaded from: classes3.dex */
public class ULongProgression implements Iterable<ULong>, KMappedMarker {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final long first;
    private final long last;
    private final long step;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        /* renamed from: fromClosedRange-7ftBX0g  reason: not valid java name */
        public final ULongProgression m1408fromClosedRange7ftBX0g(long j2, long j3, long j4) {
            return new ULongProgression(j2, j3, j4, null);
        }
    }

    private ULongProgression(long j2, long j3, long j4) {
        if (j4 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (j4 == Long.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Long.MIN_VALUE to avoid overflow on negation.");
        }
        this.first = j2;
        this.last = UProgressionUtilKt.m1385getProgressionLastElement7ftBX0g(j2, j3, j4);
        this.step = j4;
    }

    public /* synthetic */ ULongProgression(long j2, long j3, long j4, DefaultConstructorMarker defaultConstructorMarker) {
        this(j2, j3, j4);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof ULongProgression) {
            if (!isEmpty() || !((ULongProgression) obj).isEmpty()) {
                ULongProgression uLongProgression = (ULongProgression) obj;
                if (m1406getFirstsVKNKU() != uLongProgression.m1406getFirstsVKNKU() || m1407getLastsVKNKU() != uLongProgression.m1407getLastsVKNKU() || this.step != uLongProgression.step) {
                }
            }
            return true;
        }
        return false;
    }

    /* renamed from: getFirst-s-VKNKU  reason: not valid java name */
    public final long m1406getFirstsVKNKU() {
        return this.first;
    }

    /* renamed from: getLast-s-VKNKU  reason: not valid java name */
    public final long m1407getLastsVKNKU() {
        return this.last;
    }

    public final long getStep() {
        return this.step;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        long j2 = this.step;
        return (((((int) ULong.m359constructorimpl(m1406getFirstsVKNKU() ^ ULong.m359constructorimpl(m1406getFirstsVKNKU() >>> 32))) * 31) + ((int) ULong.m359constructorimpl(m1407getLastsVKNKU() ^ ULong.m359constructorimpl(m1407getLastsVKNKU() >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)));
    }

    public boolean isEmpty() {
        int i2 = (this.step > 0L ? 1 : (this.step == 0L ? 0 : -1));
        long m1406getFirstsVKNKU = m1406getFirstsVKNKU();
        long m1407getLastsVKNKU = m1407getLastsVKNKU();
        if (i2 > 0) {
            if (UnsignedKt.ulongCompare(m1406getFirstsVKNKU, m1407getLastsVKNKU) > 0) {
                return true;
            }
        } else if (UnsignedKt.ulongCompare(m1406getFirstsVKNKU, m1407getLastsVKNKU) < 0) {
            return true;
        }
        return false;
    }

    @Override // java.lang.Iterable
    @NotNull
    public final Iterator<ULong> iterator() {
        return new ULongProgressionIterator(m1406getFirstsVKNKU(), m1407getLastsVKNKU(), this.step, null);
    }

    @NotNull
    public String toString() {
        StringBuilder sb;
        long j2;
        if (this.step > 0) {
            sb = new StringBuilder();
            sb.append((Object) ULong.m404toStringimpl(m1406getFirstsVKNKU()));
            sb.append("..");
            sb.append((Object) ULong.m404toStringimpl(m1407getLastsVKNKU()));
            sb.append(" step ");
            j2 = this.step;
        } else {
            sb = new StringBuilder();
            sb.append((Object) ULong.m404toStringimpl(m1406getFirstsVKNKU()));
            sb.append(" downTo ");
            sb.append((Object) ULong.m404toStringimpl(m1407getLastsVKNKU()));
            sb.append(" step ");
            j2 = -this.step;
        }
        sb.append(j2);
        return sb.toString();
    }
}
