package kotlin.time;

import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;
@SinceKotlin(version = "1.3")
@ExperimentalTime
/* loaded from: classes3.dex */
public abstract class TimeMark {
    /* renamed from: elapsedNow-UwyO8pc */
    public abstract long mo1471elapsedNowUwyO8pc();

    public final boolean hasNotPassedNow() {
        return Duration.m1511isNegativeimpl(mo1471elapsedNowUwyO8pc());
    }

    public final boolean hasPassedNow() {
        return !Duration.m1511isNegativeimpl(mo1471elapsedNowUwyO8pc());
    }

    @NotNull
    /* renamed from: minus-LRDsOJo  reason: not valid java name */
    public TimeMark m1607minusLRDsOJo(long j2) {
        return mo1472plusLRDsOJo(Duration.m1530unaryMinusUwyO8pc(j2));
    }

    @NotNull
    /* renamed from: plus-LRDsOJo */
    public TimeMark mo1472plusLRDsOJo(long j2) {
        return new AdjustedTimeMark(this, j2, null);
    }
}
