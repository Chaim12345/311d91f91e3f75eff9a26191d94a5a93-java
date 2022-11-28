package kotlin.time;

import androidx.exifinterface.media.ExifInterface;
import com.google.common.primitives.Longs;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.math.MathKt__MathJVMKt;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringsKt;
import org.apache.commons.codec.language.Soundex;
import org.apache.http.message.TokenParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SinceKotlin(version = "1.6")
@JvmInline
@WasExperimental(markerClass = {ExperimentalTime.class})
/* loaded from: classes3.dex */
public final class Duration implements Comparable<Duration> {
    private final long rawValue;
    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final long ZERO = m1478constructorimpl(0);
    private static final long INFINITE = DurationKt.access$durationOfMillis(DurationKt.MAX_MILLIS);
    private static final long NEG_INFINITE = DurationKt.access$durationOfMillis(-4611686018427387903L);

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* renamed from: getDays-UwyO8pc  reason: not valid java name */
        private final long m1533getDaysUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.DAYS);
        }

        /* renamed from: getDays-UwyO8pc  reason: not valid java name */
        private final long m1534getDaysUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.DAYS);
        }

        /* renamed from: getDays-UwyO8pc  reason: not valid java name */
        private final long m1535getDaysUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.DAYS);
        }

        @InlineOnly
        /* renamed from: getDays-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m1536getDaysUwyO8pc$annotations(double d2) {
        }

        @InlineOnly
        /* renamed from: getDays-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m1537getDaysUwyO8pc$annotations(int i2) {
        }

        @InlineOnly
        /* renamed from: getDays-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m1538getDaysUwyO8pc$annotations(long j2) {
        }

        /* renamed from: getHours-UwyO8pc  reason: not valid java name */
        private final long m1539getHoursUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.HOURS);
        }

        /* renamed from: getHours-UwyO8pc  reason: not valid java name */
        private final long m1540getHoursUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.HOURS);
        }

        /* renamed from: getHours-UwyO8pc  reason: not valid java name */
        private final long m1541getHoursUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.HOURS);
        }

        @InlineOnly
        /* renamed from: getHours-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m1542getHoursUwyO8pc$annotations(double d2) {
        }

        @InlineOnly
        /* renamed from: getHours-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m1543getHoursUwyO8pc$annotations(int i2) {
        }

        @InlineOnly
        /* renamed from: getHours-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m1544getHoursUwyO8pc$annotations(long j2) {
        }

        /* renamed from: getMicroseconds-UwyO8pc  reason: not valid java name */
        private final long m1545getMicrosecondsUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.MICROSECONDS);
        }

        /* renamed from: getMicroseconds-UwyO8pc  reason: not valid java name */
        private final long m1546getMicrosecondsUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.MICROSECONDS);
        }

        /* renamed from: getMicroseconds-UwyO8pc  reason: not valid java name */
        private final long m1547getMicrosecondsUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.MICROSECONDS);
        }

        @InlineOnly
        /* renamed from: getMicroseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m1548getMicrosecondsUwyO8pc$annotations(double d2) {
        }

        @InlineOnly
        /* renamed from: getMicroseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m1549getMicrosecondsUwyO8pc$annotations(int i2) {
        }

        @InlineOnly
        /* renamed from: getMicroseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m1550getMicrosecondsUwyO8pc$annotations(long j2) {
        }

        /* renamed from: getMilliseconds-UwyO8pc  reason: not valid java name */
        private final long m1551getMillisecondsUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.MILLISECONDS);
        }

        /* renamed from: getMilliseconds-UwyO8pc  reason: not valid java name */
        private final long m1552getMillisecondsUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.MILLISECONDS);
        }

        /* renamed from: getMilliseconds-UwyO8pc  reason: not valid java name */
        private final long m1553getMillisecondsUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.MILLISECONDS);
        }

        @InlineOnly
        /* renamed from: getMilliseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m1554getMillisecondsUwyO8pc$annotations(double d2) {
        }

        @InlineOnly
        /* renamed from: getMilliseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m1555getMillisecondsUwyO8pc$annotations(int i2) {
        }

        @InlineOnly
        /* renamed from: getMilliseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m1556getMillisecondsUwyO8pc$annotations(long j2) {
        }

        /* renamed from: getMinutes-UwyO8pc  reason: not valid java name */
        private final long m1557getMinutesUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.MINUTES);
        }

        /* renamed from: getMinutes-UwyO8pc  reason: not valid java name */
        private final long m1558getMinutesUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.MINUTES);
        }

        /* renamed from: getMinutes-UwyO8pc  reason: not valid java name */
        private final long m1559getMinutesUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.MINUTES);
        }

        @InlineOnly
        /* renamed from: getMinutes-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m1560getMinutesUwyO8pc$annotations(double d2) {
        }

        @InlineOnly
        /* renamed from: getMinutes-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m1561getMinutesUwyO8pc$annotations(int i2) {
        }

        @InlineOnly
        /* renamed from: getMinutes-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m1562getMinutesUwyO8pc$annotations(long j2) {
        }

        /* renamed from: getNanoseconds-UwyO8pc  reason: not valid java name */
        private final long m1563getNanosecondsUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.NANOSECONDS);
        }

        /* renamed from: getNanoseconds-UwyO8pc  reason: not valid java name */
        private final long m1564getNanosecondsUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.NANOSECONDS);
        }

        /* renamed from: getNanoseconds-UwyO8pc  reason: not valid java name */
        private final long m1565getNanosecondsUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.NANOSECONDS);
        }

        @InlineOnly
        /* renamed from: getNanoseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m1566getNanosecondsUwyO8pc$annotations(double d2) {
        }

        @InlineOnly
        /* renamed from: getNanoseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m1567getNanosecondsUwyO8pc$annotations(int i2) {
        }

        @InlineOnly
        /* renamed from: getNanoseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m1568getNanosecondsUwyO8pc$annotations(long j2) {
        }

        /* renamed from: getSeconds-UwyO8pc  reason: not valid java name */
        private final long m1569getSecondsUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.SECONDS);
        }

        /* renamed from: getSeconds-UwyO8pc  reason: not valid java name */
        private final long m1570getSecondsUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.SECONDS);
        }

        /* renamed from: getSeconds-UwyO8pc  reason: not valid java name */
        private final long m1571getSecondsUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.SECONDS);
        }

        @InlineOnly
        /* renamed from: getSeconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m1572getSecondsUwyO8pc$annotations(double d2) {
        }

        @InlineOnly
        /* renamed from: getSeconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m1573getSecondsUwyO8pc$annotations(int i2) {
        }

        @InlineOnly
        /* renamed from: getSeconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m1574getSecondsUwyO8pc$annotations(long j2) {
        }

        @ExperimentalTime
        public final double convert(double d2, @NotNull DurationUnit sourceUnit, @NotNull DurationUnit targetUnit) {
            Intrinsics.checkNotNullParameter(sourceUnit, "sourceUnit");
            Intrinsics.checkNotNullParameter(targetUnit, "targetUnit");
            return DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(d2, sourceUnit, targetUnit);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Double.days' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.days", imports = {"kotlin.time.Duration.Companion.days"}))
        @DeprecatedSinceKotlin(warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: days-UwyO8pc  reason: not valid java name */
        public final long m1575daysUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.DAYS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Int.days' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.days", imports = {"kotlin.time.Duration.Companion.days"}))
        @DeprecatedSinceKotlin(warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: days-UwyO8pc  reason: not valid java name */
        public final long m1576daysUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.DAYS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Long.days' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.days", imports = {"kotlin.time.Duration.Companion.days"}))
        @DeprecatedSinceKotlin(warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: days-UwyO8pc  reason: not valid java name */
        public final long m1577daysUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.DAYS);
        }

        /* renamed from: getINFINITE-UwyO8pc  reason: not valid java name */
        public final long m1578getINFINITEUwyO8pc() {
            return Duration.INFINITE;
        }

        /* renamed from: getNEG_INFINITE-UwyO8pc$kotlin_stdlib  reason: not valid java name */
        public final long m1579getNEG_INFINITEUwyO8pc$kotlin_stdlib() {
            return Duration.NEG_INFINITE;
        }

        /* renamed from: getZERO-UwyO8pc  reason: not valid java name */
        public final long m1580getZEROUwyO8pc() {
            return Duration.ZERO;
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Double.hours' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
        @DeprecatedSinceKotlin(warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: hours-UwyO8pc  reason: not valid java name */
        public final long m1581hoursUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.HOURS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Int.hours' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
        @DeprecatedSinceKotlin(warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: hours-UwyO8pc  reason: not valid java name */
        public final long m1582hoursUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.HOURS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Long.hours' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
        @DeprecatedSinceKotlin(warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: hours-UwyO8pc  reason: not valid java name */
        public final long m1583hoursUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.HOURS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Double.microseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
        @DeprecatedSinceKotlin(warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: microseconds-UwyO8pc  reason: not valid java name */
        public final long m1584microsecondsUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.MICROSECONDS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Int.microseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
        @DeprecatedSinceKotlin(warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: microseconds-UwyO8pc  reason: not valid java name */
        public final long m1585microsecondsUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.MICROSECONDS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Long.microseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
        @DeprecatedSinceKotlin(warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: microseconds-UwyO8pc  reason: not valid java name */
        public final long m1586microsecondsUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.MICROSECONDS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Double.milliseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
        @DeprecatedSinceKotlin(warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: milliseconds-UwyO8pc  reason: not valid java name */
        public final long m1587millisecondsUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.MILLISECONDS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Int.milliseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
        @DeprecatedSinceKotlin(warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: milliseconds-UwyO8pc  reason: not valid java name */
        public final long m1588millisecondsUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.MILLISECONDS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Long.milliseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
        @DeprecatedSinceKotlin(warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: milliseconds-UwyO8pc  reason: not valid java name */
        public final long m1589millisecondsUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.MILLISECONDS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Double.minutes' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
        @DeprecatedSinceKotlin(warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: minutes-UwyO8pc  reason: not valid java name */
        public final long m1590minutesUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.MINUTES);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Int.minutes' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
        @DeprecatedSinceKotlin(warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: minutes-UwyO8pc  reason: not valid java name */
        public final long m1591minutesUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.MINUTES);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Long.minutes' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
        @DeprecatedSinceKotlin(warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: minutes-UwyO8pc  reason: not valid java name */
        public final long m1592minutesUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.MINUTES);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Double.nanoseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
        @DeprecatedSinceKotlin(warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: nanoseconds-UwyO8pc  reason: not valid java name */
        public final long m1593nanosecondsUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.NANOSECONDS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Int.nanoseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
        @DeprecatedSinceKotlin(warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: nanoseconds-UwyO8pc  reason: not valid java name */
        public final long m1594nanosecondsUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.NANOSECONDS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Long.nanoseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
        @DeprecatedSinceKotlin(warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: nanoseconds-UwyO8pc  reason: not valid java name */
        public final long m1595nanosecondsUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.NANOSECONDS);
        }

        /* renamed from: parse-UwyO8pc  reason: not valid java name */
        public final long m1596parseUwyO8pc(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            try {
                return DurationKt.access$parseDuration(value, false);
            } catch (IllegalArgumentException e2) {
                throw new IllegalArgumentException("Invalid duration string format: '" + value + "'.", e2);
            }
        }

        /* renamed from: parseIsoString-UwyO8pc  reason: not valid java name */
        public final long m1597parseIsoStringUwyO8pc(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            try {
                return DurationKt.access$parseDuration(value, true);
            } catch (IllegalArgumentException e2) {
                throw new IllegalArgumentException("Invalid ISO duration string format: '" + value + "'.", e2);
            }
        }

        @Nullable
        /* renamed from: parseIsoStringOrNull-FghU774  reason: not valid java name */
        public final Duration m1598parseIsoStringOrNullFghU774(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            try {
                return Duration.m1476boximpl(DurationKt.access$parseDuration(value, true));
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }

        @Nullable
        /* renamed from: parseOrNull-FghU774  reason: not valid java name */
        public final Duration m1599parseOrNullFghU774(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            try {
                return Duration.m1476boximpl(DurationKt.access$parseDuration(value, false));
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Double.seconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
        @DeprecatedSinceKotlin(warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: seconds-UwyO8pc  reason: not valid java name */
        public final long m1600secondsUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.SECONDS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Int.seconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
        @DeprecatedSinceKotlin(warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: seconds-UwyO8pc  reason: not valid java name */
        public final long m1601secondsUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.SECONDS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Long.seconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
        @DeprecatedSinceKotlin(warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: seconds-UwyO8pc  reason: not valid java name */
        public final long m1602secondsUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.SECONDS);
        }
    }

    private /* synthetic */ Duration(long j2) {
        this.rawValue = j2;
    }

    /* renamed from: addValuesMixedRanges-UwyO8pc  reason: not valid java name */
    private static final long m1474addValuesMixedRangesUwyO8pc(long j2, long j3, long j4) {
        long coerceIn;
        long access$nanosToMillis = DurationKt.access$nanosToMillis(j4);
        long j5 = j3 + access$nanosToMillis;
        boolean z = false;
        if (-4611686018426L <= j5 && j5 < 4611686018427L) {
            z = true;
        }
        if (z) {
            return DurationKt.access$durationOfNanos(DurationKt.access$millisToNanos(j5) + (j4 - DurationKt.access$millisToNanos(access$nanosToMillis)));
        }
        coerceIn = RangesKt___RangesKt.coerceIn(j5, -4611686018427387903L, (long) DurationKt.MAX_MILLIS);
        return DurationKt.access$durationOfMillis(coerceIn);
    }

    /* renamed from: appendFractional-impl  reason: not valid java name */
    private static final void m1475appendFractionalimpl(long j2, StringBuilder sb, int i2, int i3, int i4, String str, boolean z) {
        String padStart;
        sb.append(i2);
        if (i3 != 0) {
            sb.append('.');
            padStart = StringsKt__StringsKt.padStart(String.valueOf(i3), i4, '0');
            int i5 = -1;
            int length = padStart.length() - 1;
            if (length >= 0) {
                while (true) {
                    int i6 = length - 1;
                    if (padStart.charAt(length) != '0') {
                        i5 = length;
                        break;
                    } else if (i6 < 0) {
                        break;
                    } else {
                        length = i6;
                    }
                }
            }
            int i7 = i5 + 1;
            if (z || i7 >= 3) {
                i7 = ((i7 + 2) / 3) * 3;
            }
            sb.append((CharSequence) padStart, 0, i7);
            Intrinsics.checkNotNullExpressionValue(sb, "this.append(value, startIndex, endIndex)");
        }
        sb.append(str);
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ Duration m1476boximpl(long j2) {
        return new Duration(j2);
    }

    /* renamed from: compareTo-LRDsOJo  reason: not valid java name */
    public static int m1477compareToLRDsOJo(long j2, long j3) {
        long j4 = j2 ^ j3;
        if (j4 < 0 || (((int) j4) & 1) == 0) {
            return Intrinsics.compare(j2, j3);
        }
        int i2 = (((int) j2) & 1) - (((int) j3) & 1);
        return m1511isNegativeimpl(j2) ? -i2 : i2;
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static long m1478constructorimpl(long j2) {
        if (DurationJvmKt.getDurationAssertionsEnabled()) {
            boolean m1509isInNanosimpl = m1509isInNanosimpl(j2);
            boolean z = true;
            long m1505getValueimpl = m1505getValueimpl(j2);
            if (m1509isInNanosimpl) {
                if (-4611686018426999999L > m1505getValueimpl || m1505getValueimpl >= 4611686018427000000L) {
                    z = false;
                }
                if (!z) {
                    throw new AssertionError(m1505getValueimpl(j2) + " ns is out of nanoseconds range");
                }
            } else {
                if (!(-4611686018427387903L <= m1505getValueimpl && m1505getValueimpl < Longs.MAX_POWER_OF_TWO)) {
                    throw new AssertionError(m1505getValueimpl(j2) + " ms is out of milliseconds range");
                }
                long m1505getValueimpl2 = m1505getValueimpl(j2);
                if (-4611686018426L > m1505getValueimpl2 || m1505getValueimpl2 >= 4611686018427L) {
                    z = false;
                }
                if (z) {
                    throw new AssertionError(m1505getValueimpl(j2) + " ms is denormalized");
                }
            }
        }
        return j2;
    }

    /* renamed from: div-LRDsOJo  reason: not valid java name */
    public static final double m1479divLRDsOJo(long j2, long j3) {
        Comparable maxOf;
        maxOf = ComparisonsKt___ComparisonsJvmKt.maxOf(m1503getStorageUnitimpl(j2), m1503getStorageUnitimpl(j3));
        DurationUnit durationUnit = (DurationUnit) maxOf;
        return m1521toDoubleimpl(j2, durationUnit) / m1521toDoubleimpl(j3, durationUnit);
    }

    /* renamed from: div-UwyO8pc  reason: not valid java name */
    public static final long m1480divUwyO8pc(long j2, double d2) {
        int roundToInt;
        roundToInt = MathKt__MathJVMKt.roundToInt(d2);
        if (!(((double) roundToInt) == d2) || roundToInt == 0) {
            DurationUnit m1503getStorageUnitimpl = m1503getStorageUnitimpl(j2);
            return DurationKt.toDuration(m1521toDoubleimpl(j2, m1503getStorageUnitimpl) / d2, m1503getStorageUnitimpl);
        }
        return m1481divUwyO8pc(j2, roundToInt);
    }

    /* renamed from: div-UwyO8pc  reason: not valid java name */
    public static final long m1481divUwyO8pc(long j2, int i2) {
        int sign;
        if (i2 == 0) {
            if (m1512isPositiveimpl(j2)) {
                return INFINITE;
            }
            if (m1511isNegativeimpl(j2)) {
                return NEG_INFINITE;
            }
            throw new IllegalArgumentException("Dividing zero duration by zero yields an undefined result.");
        } else if (m1509isInNanosimpl(j2)) {
            return DurationKt.access$durationOfNanos(m1505getValueimpl(j2) / i2);
        } else {
            if (m1510isInfiniteimpl(j2)) {
                sign = MathKt__MathJVMKt.getSign(i2);
                return m1516timesUwyO8pc(j2, sign);
            }
            long j3 = i2;
            long m1505getValueimpl = m1505getValueimpl(j2) / j3;
            boolean z = false;
            if (-4611686018426L <= m1505getValueimpl && m1505getValueimpl < 4611686018427L) {
                z = true;
            }
            if (z) {
                return DurationKt.access$durationOfNanos(DurationKt.access$millisToNanos(m1505getValueimpl) + (DurationKt.access$millisToNanos(m1505getValueimpl(j2) - (m1505getValueimpl * j3)) / j3));
            }
            return DurationKt.access$durationOfMillis(m1505getValueimpl);
        }
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m1482equalsimpl(long j2, Object obj) {
        return (obj instanceof Duration) && j2 == ((Duration) obj).m1532unboximpl();
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m1483equalsimpl0(long j2, long j3) {
        return j2 == j3;
    }

    /* renamed from: getAbsoluteValue-UwyO8pc  reason: not valid java name */
    public static final long m1484getAbsoluteValueUwyO8pc(long j2) {
        return m1511isNegativeimpl(j2) ? m1530unaryMinusUwyO8pc(j2) : j2;
    }

    @PublishedApi
    public static /* synthetic */ void getHoursComponent$annotations() {
    }

    /* renamed from: getHoursComponent-impl  reason: not valid java name */
    public static final int m1485getHoursComponentimpl(long j2) {
        if (m1510isInfiniteimpl(j2)) {
            return 0;
        }
        return (int) (m1494getInWholeHoursimpl(j2) % 24);
    }

    @Deprecated(message = "Use inWholeDays property instead or convert toDouble(DAYS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.DAYS)", imports = {}))
    @ExperimentalTime
    public static /* synthetic */ void getInDays$annotations() {
    }

    /* renamed from: getInDays-impl  reason: not valid java name */
    public static final double m1486getInDaysimpl(long j2) {
        return m1521toDoubleimpl(j2, DurationUnit.DAYS);
    }

    @Deprecated(message = "Use inWholeHours property instead or convert toDouble(HOURS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.HOURS)", imports = {}))
    @ExperimentalTime
    public static /* synthetic */ void getInHours$annotations() {
    }

    /* renamed from: getInHours-impl  reason: not valid java name */
    public static final double m1487getInHoursimpl(long j2) {
        return m1521toDoubleimpl(j2, DurationUnit.HOURS);
    }

    @Deprecated(message = "Use inWholeMicroseconds property instead or convert toDouble(MICROSECONDS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.MICROSECONDS)", imports = {}))
    @ExperimentalTime
    public static /* synthetic */ void getInMicroseconds$annotations() {
    }

    /* renamed from: getInMicroseconds-impl  reason: not valid java name */
    public static final double m1488getInMicrosecondsimpl(long j2) {
        return m1521toDoubleimpl(j2, DurationUnit.MICROSECONDS);
    }

    @Deprecated(message = "Use inWholeMilliseconds property instead or convert toDouble(MILLISECONDS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.MILLISECONDS)", imports = {}))
    @ExperimentalTime
    public static /* synthetic */ void getInMilliseconds$annotations() {
    }

    /* renamed from: getInMilliseconds-impl  reason: not valid java name */
    public static final double m1489getInMillisecondsimpl(long j2) {
        return m1521toDoubleimpl(j2, DurationUnit.MILLISECONDS);
    }

    @Deprecated(message = "Use inWholeMinutes property instead or convert toDouble(MINUTES) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.MINUTES)", imports = {}))
    @ExperimentalTime
    public static /* synthetic */ void getInMinutes$annotations() {
    }

    /* renamed from: getInMinutes-impl  reason: not valid java name */
    public static final double m1490getInMinutesimpl(long j2) {
        return m1521toDoubleimpl(j2, DurationUnit.MINUTES);
    }

    @Deprecated(message = "Use inWholeNanoseconds property instead or convert toDouble(NANOSECONDS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.NANOSECONDS)", imports = {}))
    @ExperimentalTime
    public static /* synthetic */ void getInNanoseconds$annotations() {
    }

    /* renamed from: getInNanoseconds-impl  reason: not valid java name */
    public static final double m1491getInNanosecondsimpl(long j2) {
        return m1521toDoubleimpl(j2, DurationUnit.NANOSECONDS);
    }

    @Deprecated(message = "Use inWholeSeconds property instead or convert toDouble(SECONDS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.SECONDS)", imports = {}))
    @ExperimentalTime
    public static /* synthetic */ void getInSeconds$annotations() {
    }

    /* renamed from: getInSeconds-impl  reason: not valid java name */
    public static final double m1492getInSecondsimpl(long j2) {
        return m1521toDoubleimpl(j2, DurationUnit.SECONDS);
    }

    /* renamed from: getInWholeDays-impl  reason: not valid java name */
    public static final long m1493getInWholeDaysimpl(long j2) {
        return m1524toLongimpl(j2, DurationUnit.DAYS);
    }

    /* renamed from: getInWholeHours-impl  reason: not valid java name */
    public static final long m1494getInWholeHoursimpl(long j2) {
        return m1524toLongimpl(j2, DurationUnit.HOURS);
    }

    /* renamed from: getInWholeMicroseconds-impl  reason: not valid java name */
    public static final long m1495getInWholeMicrosecondsimpl(long j2) {
        return m1524toLongimpl(j2, DurationUnit.MICROSECONDS);
    }

    /* renamed from: getInWholeMilliseconds-impl  reason: not valid java name */
    public static final long m1496getInWholeMillisecondsimpl(long j2) {
        return (m1508isInMillisimpl(j2) && m1507isFiniteimpl(j2)) ? m1505getValueimpl(j2) : m1524toLongimpl(j2, DurationUnit.MILLISECONDS);
    }

    /* renamed from: getInWholeMinutes-impl  reason: not valid java name */
    public static final long m1497getInWholeMinutesimpl(long j2) {
        return m1524toLongimpl(j2, DurationUnit.MINUTES);
    }

    /* renamed from: getInWholeNanoseconds-impl  reason: not valid java name */
    public static final long m1498getInWholeNanosecondsimpl(long j2) {
        long m1505getValueimpl = m1505getValueimpl(j2);
        if (m1509isInNanosimpl(j2)) {
            return m1505getValueimpl;
        }
        if (m1505getValueimpl > 9223372036854L) {
            return LongCompanionObject.MAX_VALUE;
        }
        if (m1505getValueimpl < -9223372036854L) {
            return Long.MIN_VALUE;
        }
        return DurationKt.access$millisToNanos(m1505getValueimpl);
    }

    /* renamed from: getInWholeSeconds-impl  reason: not valid java name */
    public static final long m1499getInWholeSecondsimpl(long j2) {
        return m1524toLongimpl(j2, DurationUnit.SECONDS);
    }

    @PublishedApi
    public static /* synthetic */ void getMinutesComponent$annotations() {
    }

    /* renamed from: getMinutesComponent-impl  reason: not valid java name */
    public static final int m1500getMinutesComponentimpl(long j2) {
        if (m1510isInfiniteimpl(j2)) {
            return 0;
        }
        return (int) (m1497getInWholeMinutesimpl(j2) % 60);
    }

    @PublishedApi
    public static /* synthetic */ void getNanosecondsComponent$annotations() {
    }

    /* renamed from: getNanosecondsComponent-impl  reason: not valid java name */
    public static final int m1501getNanosecondsComponentimpl(long j2) {
        if (m1510isInfiniteimpl(j2)) {
            return 0;
        }
        boolean m1508isInMillisimpl = m1508isInMillisimpl(j2);
        long m1505getValueimpl = m1505getValueimpl(j2);
        return (int) (m1508isInMillisimpl ? DurationKt.access$millisToNanos(m1505getValueimpl % 1000) : m1505getValueimpl % 1000000000);
    }

    @PublishedApi
    public static /* synthetic */ void getSecondsComponent$annotations() {
    }

    /* renamed from: getSecondsComponent-impl  reason: not valid java name */
    public static final int m1502getSecondsComponentimpl(long j2) {
        if (m1510isInfiniteimpl(j2)) {
            return 0;
        }
        return (int) (m1499getInWholeSecondsimpl(j2) % 60);
    }

    /* renamed from: getStorageUnit-impl  reason: not valid java name */
    private static final DurationUnit m1503getStorageUnitimpl(long j2) {
        return m1509isInNanosimpl(j2) ? DurationUnit.NANOSECONDS : DurationUnit.MILLISECONDS;
    }

    /* renamed from: getUnitDiscriminator-impl  reason: not valid java name */
    private static final int m1504getUnitDiscriminatorimpl(long j2) {
        return ((int) j2) & 1;
    }

    /* renamed from: getValue-impl  reason: not valid java name */
    private static final long m1505getValueimpl(long j2) {
        return j2 >> 1;
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m1506hashCodeimpl(long j2) {
        return (int) (j2 ^ (j2 >>> 32));
    }

    /* renamed from: isFinite-impl  reason: not valid java name */
    public static final boolean m1507isFiniteimpl(long j2) {
        return !m1510isInfiniteimpl(j2);
    }

    /* renamed from: isInMillis-impl  reason: not valid java name */
    private static final boolean m1508isInMillisimpl(long j2) {
        return (((int) j2) & 1) == 1;
    }

    /* renamed from: isInNanos-impl  reason: not valid java name */
    private static final boolean m1509isInNanosimpl(long j2) {
        return (((int) j2) & 1) == 0;
    }

    /* renamed from: isInfinite-impl  reason: not valid java name */
    public static final boolean m1510isInfiniteimpl(long j2) {
        return j2 == INFINITE || j2 == NEG_INFINITE;
    }

    /* renamed from: isNegative-impl  reason: not valid java name */
    public static final boolean m1511isNegativeimpl(long j2) {
        return j2 < 0;
    }

    /* renamed from: isPositive-impl  reason: not valid java name */
    public static final boolean m1512isPositiveimpl(long j2) {
        return j2 > 0;
    }

    /* renamed from: minus-LRDsOJo  reason: not valid java name */
    public static final long m1513minusLRDsOJo(long j2, long j3) {
        return m1514plusLRDsOJo(j2, m1530unaryMinusUwyO8pc(j3));
    }

    /* renamed from: plus-LRDsOJo  reason: not valid java name */
    public static final long m1514plusLRDsOJo(long j2, long j3) {
        if (m1510isInfiniteimpl(j2)) {
            if (m1507isFiniteimpl(j3) || (j3 ^ j2) >= 0) {
                return j2;
            }
            throw new IllegalArgumentException("Summing infinite durations of different signs yields an undefined result.");
        } else if (m1510isInfiniteimpl(j3)) {
            return j3;
        } else {
            if ((((int) j2) & 1) != (((int) j3) & 1)) {
                return m1508isInMillisimpl(j2) ? m1474addValuesMixedRangesUwyO8pc(j2, m1505getValueimpl(j2), m1505getValueimpl(j3)) : m1474addValuesMixedRangesUwyO8pc(j2, m1505getValueimpl(j3), m1505getValueimpl(j2));
            }
            long m1505getValueimpl = m1505getValueimpl(j2) + m1505getValueimpl(j3);
            return m1509isInNanosimpl(j2) ? DurationKt.access$durationOfNanosNormalized(m1505getValueimpl) : DurationKt.access$durationOfMillisNormalized(m1505getValueimpl);
        }
    }

    /* renamed from: times-UwyO8pc  reason: not valid java name */
    public static final long m1515timesUwyO8pc(long j2, double d2) {
        int roundToInt;
        roundToInt = MathKt__MathJVMKt.roundToInt(d2);
        if (((double) roundToInt) == d2) {
            return m1516timesUwyO8pc(j2, roundToInt);
        }
        DurationUnit m1503getStorageUnitimpl = m1503getStorageUnitimpl(j2);
        return DurationKt.toDuration(m1521toDoubleimpl(j2, m1503getStorageUnitimpl) * d2, m1503getStorageUnitimpl);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0085, code lost:
        if ((r12 * r13) > 0) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00a5, code lost:
        if ((r12 * r13) > 0) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00ac, code lost:
        return kotlin.time.Duration.NEG_INFINITE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:?, code lost:
        return kotlin.time.Duration.INFINITE;
     */
    /* renamed from: times-UwyO8pc  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final long m1516timesUwyO8pc(long j2, int i2) {
        int sign;
        int sign2;
        LongRange longRange;
        long coerceIn;
        int sign3;
        int sign4;
        if (m1510isInfiniteimpl(j2)) {
            if (i2 != 0) {
                return i2 > 0 ? j2 : m1530unaryMinusUwyO8pc(j2);
            }
            throw new IllegalArgumentException("Multiplying infinite duration by zero yields an undefined result.");
        } else if (i2 == 0) {
            return ZERO;
        } else {
            long m1505getValueimpl = m1505getValueimpl(j2);
            long j3 = i2;
            long j4 = m1505getValueimpl * j3;
            if (m1509isInNanosimpl(j2)) {
                boolean z = false;
                if (m1505getValueimpl <= 2147483647L && -2147483647L <= m1505getValueimpl) {
                    z = true;
                }
                if (z) {
                    return DurationKt.access$durationOfNanos(j4);
                }
                if (j4 / j3 == m1505getValueimpl) {
                    return DurationKt.access$durationOfNanosNormalized(j4);
                }
                long access$nanosToMillis = DurationKt.access$nanosToMillis(m1505getValueimpl);
                long j5 = access$nanosToMillis * j3;
                j4 = DurationKt.access$nanosToMillis((m1505getValueimpl - DurationKt.access$millisToNanos(access$nanosToMillis)) * j3) + j5;
                if (j5 / j3 == access$nanosToMillis && (j4 ^ j5) >= 0) {
                    longRange = new LongRange(-4611686018427387903L, DurationKt.MAX_MILLIS);
                    coerceIn = RangesKt___RangesKt.coerceIn(j4, longRange);
                    return DurationKt.access$durationOfMillis(coerceIn);
                }
                sign3 = MathKt__MathJVMKt.getSign(m1505getValueimpl);
                sign4 = MathKt__MathJVMKt.getSign(i2);
            } else if (j4 / j3 == m1505getValueimpl) {
                longRange = new LongRange(-4611686018427387903L, DurationKt.MAX_MILLIS);
                coerceIn = RangesKt___RangesKt.coerceIn(j4, longRange);
                return DurationKt.access$durationOfMillis(coerceIn);
            } else {
                sign = MathKt__MathJVMKt.getSign(m1505getValueimpl);
                sign2 = MathKt__MathJVMKt.getSign(i2);
            }
        }
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m1517toComponentsimpl(long j2, @NotNull Function2<? super Long, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Long.valueOf(m1499getInWholeSecondsimpl(j2)), Integer.valueOf(m1501getNanosecondsComponentimpl(j2)));
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m1518toComponentsimpl(long j2, @NotNull Function3<? super Long, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Long.valueOf(m1497getInWholeMinutesimpl(j2)), Integer.valueOf(m1502getSecondsComponentimpl(j2)), Integer.valueOf(m1501getNanosecondsComponentimpl(j2)));
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m1519toComponentsimpl(long j2, @NotNull Function4<? super Long, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Long.valueOf(m1494getInWholeHoursimpl(j2)), Integer.valueOf(m1500getMinutesComponentimpl(j2)), Integer.valueOf(m1502getSecondsComponentimpl(j2)), Integer.valueOf(m1501getNanosecondsComponentimpl(j2)));
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m1520toComponentsimpl(long j2, @NotNull Function5<? super Long, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Long.valueOf(m1493getInWholeDaysimpl(j2)), Integer.valueOf(m1485getHoursComponentimpl(j2)), Integer.valueOf(m1500getMinutesComponentimpl(j2)), Integer.valueOf(m1502getSecondsComponentimpl(j2)), Integer.valueOf(m1501getNanosecondsComponentimpl(j2)));
    }

    /* renamed from: toDouble-impl  reason: not valid java name */
    public static final double m1521toDoubleimpl(long j2, @NotNull DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (j2 == INFINITE) {
            return Double.POSITIVE_INFINITY;
        }
        if (j2 == NEG_INFINITE) {
            return Double.NEGATIVE_INFINITY;
        }
        return DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(m1505getValueimpl(j2), m1503getStorageUnitimpl(j2), unit);
    }

    /* renamed from: toInt-impl  reason: not valid java name */
    public static final int m1522toIntimpl(long j2, @NotNull DurationUnit unit) {
        long coerceIn;
        Intrinsics.checkNotNullParameter(unit, "unit");
        coerceIn = RangesKt___RangesKt.coerceIn(m1524toLongimpl(j2, unit), -2147483648L, 2147483647L);
        return (int) coerceIn;
    }

    @NotNull
    /* renamed from: toIsoString-impl  reason: not valid java name */
    public static final String m1523toIsoStringimpl(long j2) {
        StringBuilder sb = new StringBuilder();
        if (m1511isNegativeimpl(j2)) {
            sb.append(Soundex.SILENT_MARKER);
        }
        sb.append("PT");
        long m1484getAbsoluteValueUwyO8pc = m1484getAbsoluteValueUwyO8pc(j2);
        long m1494getInWholeHoursimpl = m1494getInWholeHoursimpl(m1484getAbsoluteValueUwyO8pc);
        int m1500getMinutesComponentimpl = m1500getMinutesComponentimpl(m1484getAbsoluteValueUwyO8pc);
        int m1502getSecondsComponentimpl = m1502getSecondsComponentimpl(m1484getAbsoluteValueUwyO8pc);
        int m1501getNanosecondsComponentimpl = m1501getNanosecondsComponentimpl(m1484getAbsoluteValueUwyO8pc);
        if (m1510isInfiniteimpl(j2)) {
            m1494getInWholeHoursimpl = 9999999999999L;
        }
        boolean z = true;
        boolean z2 = m1494getInWholeHoursimpl != 0;
        boolean z3 = (m1502getSecondsComponentimpl == 0 && m1501getNanosecondsComponentimpl == 0) ? false : true;
        if (m1500getMinutesComponentimpl == 0 && (!z3 || !z2)) {
            z = false;
        }
        if (z2) {
            sb.append(m1494getInWholeHoursimpl);
            sb.append('H');
        }
        if (z) {
            sb.append(m1500getMinutesComponentimpl);
            sb.append('M');
        }
        if (z3 || (!z2 && !z)) {
            m1475appendFractionalimpl(j2, sb, m1502getSecondsComponentimpl, m1501getNanosecondsComponentimpl, 9, ExifInterface.LATITUDE_SOUTH, true);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    /* renamed from: toLong-impl  reason: not valid java name */
    public static final long m1524toLongimpl(long j2, @NotNull DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (j2 == INFINITE) {
            return LongCompanionObject.MAX_VALUE;
        }
        if (j2 == NEG_INFINITE) {
            return Long.MIN_VALUE;
        }
        return DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(m1505getValueimpl(j2), m1503getStorageUnitimpl(j2), unit);
    }

    @Deprecated(message = "Use inWholeMilliseconds property instead.", replaceWith = @ReplaceWith(expression = "this.inWholeMilliseconds", imports = {}))
    @ExperimentalTime
    /* renamed from: toLongMilliseconds-impl  reason: not valid java name */
    public static final long m1525toLongMillisecondsimpl(long j2) {
        return m1496getInWholeMillisecondsimpl(j2);
    }

    @Deprecated(message = "Use inWholeNanoseconds property instead.", replaceWith = @ReplaceWith(expression = "this.inWholeNanoseconds", imports = {}))
    @ExperimentalTime
    /* renamed from: toLongNanoseconds-impl  reason: not valid java name */
    public static final long m1526toLongNanosecondsimpl(long j2) {
        return m1498getInWholeNanosecondsimpl(j2);
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static String m1527toStringimpl(long j2) {
        int i2;
        long j3;
        StringBuilder sb;
        int i3;
        int i4;
        String str;
        boolean z;
        if (j2 == 0) {
            return "0s";
        }
        if (j2 == INFINITE) {
            return "Infinity";
        }
        if (j2 == NEG_INFINITE) {
            return "-Infinity";
        }
        boolean m1511isNegativeimpl = m1511isNegativeimpl(j2);
        StringBuilder sb2 = new StringBuilder();
        if (m1511isNegativeimpl) {
            sb2.append(Soundex.SILENT_MARKER);
        }
        long m1484getAbsoluteValueUwyO8pc = m1484getAbsoluteValueUwyO8pc(j2);
        long m1493getInWholeDaysimpl = m1493getInWholeDaysimpl(m1484getAbsoluteValueUwyO8pc);
        int m1485getHoursComponentimpl = m1485getHoursComponentimpl(m1484getAbsoluteValueUwyO8pc);
        int m1500getMinutesComponentimpl = m1500getMinutesComponentimpl(m1484getAbsoluteValueUwyO8pc);
        int m1502getSecondsComponentimpl = m1502getSecondsComponentimpl(m1484getAbsoluteValueUwyO8pc);
        int m1501getNanosecondsComponentimpl = m1501getNanosecondsComponentimpl(m1484getAbsoluteValueUwyO8pc);
        int i5 = 0;
        boolean z2 = m1493getInWholeDaysimpl != 0;
        boolean z3 = m1485getHoursComponentimpl != 0;
        boolean z4 = m1500getMinutesComponentimpl != 0;
        boolean z5 = (m1502getSecondsComponentimpl == 0 && m1501getNanosecondsComponentimpl == 0) ? false : true;
        if (z2) {
            sb2.append(m1493getInWholeDaysimpl);
            sb2.append('d');
            i5 = 1;
        }
        if (z3 || (z2 && (z4 || z5))) {
            int i6 = i5 + 1;
            if (i5 > 0) {
                sb2.append(TokenParser.SP);
            }
            sb2.append(m1485getHoursComponentimpl);
            sb2.append('h');
            i5 = i6;
        }
        if (z4 || (z5 && (z3 || z2))) {
            int i7 = i5 + 1;
            if (i5 > 0) {
                sb2.append(TokenParser.SP);
            }
            sb2.append(m1500getMinutesComponentimpl);
            sb2.append('m');
            i5 = i7;
        }
        if (z5) {
            int i8 = i5 + 1;
            if (i5 > 0) {
                sb2.append(TokenParser.SP);
            }
            if (m1502getSecondsComponentimpl != 0 || z2 || z3 || z4) {
                i2 = 9;
                j3 = j2;
                sb = sb2;
                i3 = m1502getSecondsComponentimpl;
                i4 = m1501getNanosecondsComponentimpl;
                str = "s";
                z = false;
            } else {
                if (m1501getNanosecondsComponentimpl >= 1000000) {
                    i3 = m1501getNanosecondsComponentimpl / DurationKt.NANOS_IN_MILLIS;
                    i4 = m1501getNanosecondsComponentimpl % DurationKt.NANOS_IN_MILLIS;
                    i2 = 6;
                    z = false;
                    str = "ms";
                } else if (m1501getNanosecondsComponentimpl >= 1000) {
                    i3 = m1501getNanosecondsComponentimpl / 1000;
                    i4 = m1501getNanosecondsComponentimpl % 1000;
                    i2 = 3;
                    z = false;
                    str = "us";
                } else {
                    sb2.append(m1501getNanosecondsComponentimpl);
                    sb2.append("ns");
                    i5 = i8;
                }
                j3 = j2;
                sb = sb2;
            }
            m1475appendFractionalimpl(j3, sb, i3, i4, i2, str, z);
            i5 = i8;
        }
        if (m1511isNegativeimpl && i5 > 1) {
            sb2.insert(1, '(').append(')');
        }
        String sb3 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(sb3, "StringBuilder().apply(builderAction).toString()");
        return sb3;
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static final String m1528toStringimpl(long j2, @NotNull DurationUnit unit, int i2) {
        int coerceAtMost;
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("decimals must be not negative, but was " + i2).toString());
        }
        double m1521toDoubleimpl = m1521toDoubleimpl(j2, unit);
        if (Double.isInfinite(m1521toDoubleimpl)) {
            return String.valueOf(m1521toDoubleimpl);
        }
        StringBuilder sb = new StringBuilder();
        coerceAtMost = RangesKt___RangesKt.coerceAtMost(i2, 12);
        sb.append(DurationJvmKt.formatToExactDecimals(m1521toDoubleimpl, coerceAtMost));
        sb.append(DurationUnitKt__DurationUnitKt.shortName(unit));
        return sb.toString();
    }

    /* renamed from: toString-impl$default  reason: not valid java name */
    public static /* synthetic */ String m1529toStringimpl$default(long j2, DurationUnit durationUnit, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        return m1528toStringimpl(j2, durationUnit, i2);
    }

    /* renamed from: unaryMinus-UwyO8pc  reason: not valid java name */
    public static final long m1530unaryMinusUwyO8pc(long j2) {
        return DurationKt.access$durationOf(-m1505getValueimpl(j2), ((int) j2) & 1);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Duration duration) {
        return m1531compareToLRDsOJo(duration.m1532unboximpl());
    }

    /* renamed from: compareTo-LRDsOJo  reason: not valid java name */
    public int m1531compareToLRDsOJo(long j2) {
        return m1477compareToLRDsOJo(this.rawValue, j2);
    }

    public boolean equals(Object obj) {
        return m1482equalsimpl(this.rawValue, obj);
    }

    public int hashCode() {
        return m1506hashCodeimpl(this.rawValue);
    }

    @NotNull
    public String toString() {
        return m1527toStringimpl(this.rawValue);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ long m1532unboximpl() {
        return this.rawValue;
    }
}
