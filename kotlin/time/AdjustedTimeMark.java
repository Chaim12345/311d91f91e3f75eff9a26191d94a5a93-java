package kotlin.time;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
@ExperimentalTime
/* loaded from: classes3.dex */
final class AdjustedTimeMark extends TimeMark {
    private final long adjustment;
    @NotNull
    private final TimeMark mark;

    private AdjustedTimeMark(TimeMark timeMark, long j2) {
        this.mark = timeMark;
        this.adjustment = j2;
    }

    public /* synthetic */ AdjustedTimeMark(TimeMark timeMark, long j2, DefaultConstructorMarker defaultConstructorMarker) {
        this(timeMark, j2);
    }

    @Override // kotlin.time.TimeMark
    /* renamed from: elapsedNow-UwyO8pc */
    public long mo1471elapsedNowUwyO8pc() {
        return Duration.m1513minusLRDsOJo(this.mark.mo1471elapsedNowUwyO8pc(), m1473getAdjustmentUwyO8pc());
    }

    /* renamed from: getAdjustment-UwyO8pc  reason: not valid java name */
    public final long m1473getAdjustmentUwyO8pc() {
        return this.adjustment;
    }

    @NotNull
    public final TimeMark getMark() {
        return this.mark;
    }

    @Override // kotlin.time.TimeMark
    @NotNull
    /* renamed from: plus-LRDsOJo */
    public TimeMark mo1472plusLRDsOJo(long j2) {
        return new AdjustedTimeMark(this.mark, Duration.m1514plusLRDsOJo(m1473getAdjustmentUwyO8pc(), j2), null);
    }
}
