package kotlin.time;

import kotlin.SinceKotlin;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@SinceKotlin(version = "1.3")
@ExperimentalTime
/* loaded from: classes3.dex */
public abstract class AbstractDoubleTimeSource implements TimeSource {
    @NotNull
    private final DurationUnit unit;

    /* loaded from: classes3.dex */
    private static final class DoubleTimeMark extends TimeMark {
        private final long offset;
        private final double startedAt;
        @NotNull
        private final AbstractDoubleTimeSource timeSource;

        private DoubleTimeMark(double d2, AbstractDoubleTimeSource abstractDoubleTimeSource, long j2) {
            this.startedAt = d2;
            this.timeSource = abstractDoubleTimeSource;
            this.offset = j2;
        }

        public /* synthetic */ DoubleTimeMark(double d2, AbstractDoubleTimeSource abstractDoubleTimeSource, long j2, DefaultConstructorMarker defaultConstructorMarker) {
            this(d2, abstractDoubleTimeSource, j2);
        }

        @Override // kotlin.time.TimeMark
        /* renamed from: elapsedNow-UwyO8pc  reason: not valid java name */
        public long mo1471elapsedNowUwyO8pc() {
            return Duration.m1513minusLRDsOJo(DurationKt.toDuration(this.timeSource.b() - this.startedAt, this.timeSource.a()), this.offset);
        }

        @Override // kotlin.time.TimeMark
        @NotNull
        /* renamed from: plus-LRDsOJo  reason: not valid java name */
        public TimeMark mo1472plusLRDsOJo(long j2) {
            return new DoubleTimeMark(this.startedAt, this.timeSource, Duration.m1514plusLRDsOJo(this.offset, j2), null);
        }
    }

    public AbstractDoubleTimeSource(@NotNull DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        this.unit = unit;
    }

    @NotNull
    protected final DurationUnit a() {
        return this.unit;
    }

    protected abstract double b();

    @Override // kotlin.time.TimeSource
    @NotNull
    public TimeMark markNow() {
        return new DoubleTimeMark(b(), this, Duration.Companion.m1580getZEROUwyO8pc(), null);
    }
}
