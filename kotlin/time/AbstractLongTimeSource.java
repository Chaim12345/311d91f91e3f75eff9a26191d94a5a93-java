package kotlin.time;

import kotlin.SinceKotlin;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@SinceKotlin(version = "1.3")
@ExperimentalTime
/* loaded from: classes3.dex */
public abstract class AbstractLongTimeSource implements TimeSource {
    @NotNull
    private final DurationUnit unit;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class LongTimeMark extends TimeMark {
        private final long offset;
        private final long startedAt;
        @NotNull
        private final AbstractLongTimeSource timeSource;

        private LongTimeMark(long j2, AbstractLongTimeSource abstractLongTimeSource, long j3) {
            this.startedAt = j2;
            this.timeSource = abstractLongTimeSource;
            this.offset = j3;
        }

        public /* synthetic */ LongTimeMark(long j2, AbstractLongTimeSource abstractLongTimeSource, long j3, DefaultConstructorMarker defaultConstructorMarker) {
            this(j2, abstractLongTimeSource, j3);
        }

        @Override // kotlin.time.TimeMark
        /* renamed from: elapsedNow-UwyO8pc */
        public long mo1471elapsedNowUwyO8pc() {
            return Duration.m1513minusLRDsOJo(DurationKt.toDuration(this.timeSource.b() - this.startedAt, this.timeSource.a()), this.offset);
        }

        @Override // kotlin.time.TimeMark
        @NotNull
        /* renamed from: plus-LRDsOJo */
        public TimeMark mo1472plusLRDsOJo(long j2) {
            return new LongTimeMark(this.startedAt, this.timeSource, Duration.m1514plusLRDsOJo(this.offset, j2), null);
        }
    }

    public AbstractLongTimeSource(@NotNull DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        this.unit = unit;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final DurationUnit a() {
        return this.unit;
    }

    protected abstract long b();

    @Override // kotlin.time.TimeSource
    @NotNull
    public TimeMark markNow() {
        return new LongTimeMark(b(), this, Duration.Companion.m1580getZEROUwyO8pc(), null);
    }
}
