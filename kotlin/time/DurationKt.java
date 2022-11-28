package kotlin.time;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.collections.IntIterator;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.math.MathKt__MathJVMKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.StringsKt___StringsKt;
import kotlin.time.Duration;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Marker;
/* loaded from: classes3.dex */
public final class DurationKt {
    public static final long MAX_MILLIS = 4611686018427387903L;
    public static final long MAX_NANOS = 4611686018426999999L;
    private static final long MAX_NANOS_IN_MILLIS = 4611686018426L;
    public static final int NANOS_IN_MILLIS = 1000000;

    public static final long durationOf(long j2, int i2) {
        return Duration.m1478constructorimpl((j2 << 1) + i2);
    }

    public static final long durationOfMillis(long j2) {
        return Duration.m1478constructorimpl((j2 << 1) + 1);
    }

    public static final long durationOfMillisNormalized(long j2) {
        long coerceIn;
        boolean z = false;
        if (-4611686018426L <= j2 && j2 < 4611686018427L) {
            z = true;
        }
        if (z) {
            return durationOfNanos(millisToNanos(j2));
        }
        coerceIn = RangesKt___RangesKt.coerceIn(j2, -4611686018427387903L, (long) MAX_MILLIS);
        return durationOfMillis(coerceIn);
    }

    public static final long durationOfNanos(long j2) {
        return Duration.m1478constructorimpl(j2 << 1);
    }

    public static final long durationOfNanosNormalized(long j2) {
        boolean z = false;
        if (-4611686018426999999L <= j2 && j2 < 4611686018427000000L) {
            z = true;
        }
        return z ? durationOfNanos(j2) : durationOfMillis(nanosToMillis(j2));
    }

    public static final long getDays(double d2) {
        return toDuration(d2, DurationUnit.DAYS);
    }

    public static final long getDays(int i2) {
        return toDuration(i2, DurationUnit.DAYS);
    }

    public static final long getDays(long j2) {
        return toDuration(j2, DurationUnit.DAYS);
    }

    @SinceKotlin(version = "1.3")
    @Deprecated(message = "Use 'Double.days' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.days", imports = {"kotlin.time.Duration.Companion.days"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getDays$annotations(double d2) {
    }

    @SinceKotlin(version = "1.3")
    @Deprecated(message = "Use 'Int.days' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.days", imports = {"kotlin.time.Duration.Companion.days"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getDays$annotations(int i2) {
    }

    @SinceKotlin(version = "1.3")
    @Deprecated(message = "Use 'Long.days' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.days", imports = {"kotlin.time.Duration.Companion.days"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getDays$annotations(long j2) {
    }

    public static final long getHours(double d2) {
        return toDuration(d2, DurationUnit.HOURS);
    }

    public static final long getHours(int i2) {
        return toDuration(i2, DurationUnit.HOURS);
    }

    public static final long getHours(long j2) {
        return toDuration(j2, DurationUnit.HOURS);
    }

    @SinceKotlin(version = "1.3")
    @Deprecated(message = "Use 'Double.hours' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getHours$annotations(double d2) {
    }

    @SinceKotlin(version = "1.3")
    @Deprecated(message = "Use 'Int.hours' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getHours$annotations(int i2) {
    }

    @SinceKotlin(version = "1.3")
    @Deprecated(message = "Use 'Long.hours' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getHours$annotations(long j2) {
    }

    public static final long getMicroseconds(double d2) {
        return toDuration(d2, DurationUnit.MICROSECONDS);
    }

    public static final long getMicroseconds(int i2) {
        return toDuration(i2, DurationUnit.MICROSECONDS);
    }

    public static final long getMicroseconds(long j2) {
        return toDuration(j2, DurationUnit.MICROSECONDS);
    }

    @SinceKotlin(version = "1.3")
    @Deprecated(message = "Use 'Double.microseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getMicroseconds$annotations(double d2) {
    }

    @SinceKotlin(version = "1.3")
    @Deprecated(message = "Use 'Int.microseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getMicroseconds$annotations(int i2) {
    }

    @SinceKotlin(version = "1.3")
    @Deprecated(message = "Use 'Long.microseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getMicroseconds$annotations(long j2) {
    }

    public static final long getMilliseconds(double d2) {
        return toDuration(d2, DurationUnit.MILLISECONDS);
    }

    public static final long getMilliseconds(int i2) {
        return toDuration(i2, DurationUnit.MILLISECONDS);
    }

    public static final long getMilliseconds(long j2) {
        return toDuration(j2, DurationUnit.MILLISECONDS);
    }

    @SinceKotlin(version = "1.3")
    @Deprecated(message = "Use 'Double.milliseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getMilliseconds$annotations(double d2) {
    }

    @SinceKotlin(version = "1.3")
    @Deprecated(message = "Use 'Int.milliseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getMilliseconds$annotations(int i2) {
    }

    @SinceKotlin(version = "1.3")
    @Deprecated(message = "Use 'Long.milliseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getMilliseconds$annotations(long j2) {
    }

    public static final long getMinutes(double d2) {
        return toDuration(d2, DurationUnit.MINUTES);
    }

    public static final long getMinutes(int i2) {
        return toDuration(i2, DurationUnit.MINUTES);
    }

    public static final long getMinutes(long j2) {
        return toDuration(j2, DurationUnit.MINUTES);
    }

    @SinceKotlin(version = "1.3")
    @Deprecated(message = "Use 'Double.minutes' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getMinutes$annotations(double d2) {
    }

    @SinceKotlin(version = "1.3")
    @Deprecated(message = "Use 'Int.minutes' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getMinutes$annotations(int i2) {
    }

    @SinceKotlin(version = "1.3")
    @Deprecated(message = "Use 'Long.minutes' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getMinutes$annotations(long j2) {
    }

    public static final long getNanoseconds(double d2) {
        return toDuration(d2, DurationUnit.NANOSECONDS);
    }

    public static final long getNanoseconds(int i2) {
        return toDuration(i2, DurationUnit.NANOSECONDS);
    }

    public static final long getNanoseconds(long j2) {
        return toDuration(j2, DurationUnit.NANOSECONDS);
    }

    @SinceKotlin(version = "1.3")
    @Deprecated(message = "Use 'Double.nanoseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getNanoseconds$annotations(double d2) {
    }

    @SinceKotlin(version = "1.3")
    @Deprecated(message = "Use 'Int.nanoseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getNanoseconds$annotations(int i2) {
    }

    @SinceKotlin(version = "1.3")
    @Deprecated(message = "Use 'Long.nanoseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getNanoseconds$annotations(long j2) {
    }

    public static final long getSeconds(double d2) {
        return toDuration(d2, DurationUnit.SECONDS);
    }

    public static final long getSeconds(int i2) {
        return toDuration(i2, DurationUnit.SECONDS);
    }

    public static final long getSeconds(long j2) {
        return toDuration(j2, DurationUnit.SECONDS);
    }

    @SinceKotlin(version = "1.3")
    @Deprecated(message = "Use 'Double.seconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getSeconds$annotations(double d2) {
    }

    @SinceKotlin(version = "1.3")
    @Deprecated(message = "Use 'Int.seconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getSeconds$annotations(int i2) {
    }

    @SinceKotlin(version = "1.3")
    @Deprecated(message = "Use 'Long.seconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getSeconds$annotations(long j2) {
    }

    public static final long millisToNanos(long j2) {
        return j2 * ((long) NANOS_IN_MILLIS);
    }

    public static final long nanosToMillis(long j2) {
        return j2 / ((long) NANOS_IN_MILLIS);
    }

    /* JADX WARN: Removed duplicated region for block: B:216:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0090 A[LOOP:1: B:231:0x006a->B:246:0x0090, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:359:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x0099 A[EDGE_INSN: B:375:0x0099->B:248:0x0099 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final long parseDuration(String str, boolean z) {
        boolean z2;
        boolean regionMatches;
        int indexOf$default;
        char last;
        int lastIndex;
        int indexOf$default2;
        boolean z3;
        boolean contains$default;
        boolean startsWith$default;
        String str2 = str;
        int length = str.length();
        if (length == 0) {
            throw new IllegalArgumentException("The string is empty");
        }
        Duration.Companion companion = Duration.Companion;
        long m1580getZEROUwyO8pc = companion.m1580getZEROUwyO8pc();
        char charAt = str2.charAt(0);
        boolean z4 = true;
        int i2 = (charAt == '+' || charAt == '-') ? 1 : 0;
        boolean z5 = i2 > 0;
        if (z5) {
            startsWith$default = StringsKt__StringsKt.startsWith$default((CharSequence) str2, (char) Soundex.SILENT_MARKER, false, 2, (Object) null);
            if (startsWith$default) {
                z2 = true;
                if (length <= i2) {
                    char charAt2 = str2.charAt(i2);
                    char c2 = AbstractJsonLexerKt.COLON;
                    char c3 = '0';
                    if (charAt2 == 'P') {
                        int i3 = i2 + 1;
                        if (i3 == length) {
                            throw new IllegalArgumentException();
                        }
                        DurationUnit durationUnit = null;
                        boolean z6 = false;
                        while (i3 < length) {
                            if (str2.charAt(i3) != 'T') {
                                int i4 = i3;
                                while (i4 < str.length()) {
                                    char charAt3 = str2.charAt(i4);
                                    if (!((c3 > charAt3 || charAt3 >= c2) ? false : z4)) {
                                        contains$default = StringsKt__StringsKt.contains$default((CharSequence) "+-.", charAt3, false, 2, (Object) null);
                                        if (!contains$default) {
                                            z3 = false;
                                            if (z3) {
                                                break;
                                            }
                                            i4++;
                                            c3 = '0';
                                            c2 = AbstractJsonLexerKt.COLON;
                                            z4 = true;
                                        }
                                    }
                                    z3 = true;
                                    if (z3) {
                                    }
                                }
                                String substring = str2.substring(i3, i4);
                                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                                if (substring.length() == 0) {
                                    throw new IllegalArgumentException();
                                }
                                int length2 = i3 + substring.length();
                                if (length2 >= 0) {
                                    lastIndex = StringsKt__StringsKt.getLastIndex(str);
                                    if (length2 <= lastIndex) {
                                        char charAt4 = str2.charAt(length2);
                                        i3 = length2 + 1;
                                        DurationUnit durationUnitByIsoChar = DurationUnitKt__DurationUnitKt.durationUnitByIsoChar(charAt4, z6);
                                        if (durationUnit != null && durationUnit.compareTo(durationUnitByIsoChar) <= 0) {
                                            throw new IllegalArgumentException("Unexpected order of duration components");
                                        }
                                        indexOf$default2 = StringsKt__StringsKt.indexOf$default((CharSequence) substring, '.', 0, false, 6, (Object) null);
                                        if (durationUnitByIsoChar != DurationUnit.SECONDS || indexOf$default2 <= 0) {
                                            m1580getZEROUwyO8pc = Duration.m1514plusLRDsOJo(m1580getZEROUwyO8pc, toDuration(parseOverLongIsoComponent(substring), durationUnitByIsoChar));
                                        } else {
                                            String substring2 = substring.substring(0, indexOf$default2);
                                            Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
                                            long m1514plusLRDsOJo = Duration.m1514plusLRDsOJo(m1580getZEROUwyO8pc, toDuration(parseOverLongIsoComponent(substring2), durationUnitByIsoChar));
                                            String substring3 = substring.substring(indexOf$default2);
                                            Intrinsics.checkNotNullExpressionValue(substring3, "this as java.lang.String).substring(startIndex)");
                                            m1580getZEROUwyO8pc = Duration.m1514plusLRDsOJo(m1514plusLRDsOJo, toDuration(Double.parseDouble(substring3), durationUnitByIsoChar));
                                        }
                                        durationUnit = durationUnitByIsoChar;
                                        c3 = '0';
                                        c2 = AbstractJsonLexerKt.COLON;
                                        z4 = true;
                                        str2 = str;
                                    }
                                }
                                throw new IllegalArgumentException("Missing unit for value " + substring);
                            } else if (z6 || (i3 = i3 + 1) == length) {
                                throw new IllegalArgumentException();
                            } else {
                                z6 = z4;
                            }
                        }
                    } else if (z) {
                        throw new IllegalArgumentException();
                    } else {
                        String str3 = "Unexpected order of duration components";
                        regionMatches = StringsKt__StringsJVMKt.regionMatches(str, i2, "Infinity", 0, Math.max(length - i2, 8), true);
                        if (regionMatches) {
                            m1580getZEROUwyO8pc = companion.m1578getINFINITEUwyO8pc();
                        } else {
                            boolean z7 = !z5;
                            if (z5 && str.charAt(i2) == '(') {
                                last = StringsKt___StringsKt.last(str);
                                if (last == ')') {
                                    i2++;
                                    length--;
                                    if (i2 == length) {
                                        throw new IllegalArgumentException("No components");
                                    }
                                    z7 = true;
                                }
                            }
                            boolean z8 = false;
                            DurationUnit durationUnit2 = null;
                            while (i2 < length) {
                                if (z8 && z7) {
                                    while (i2 < str.length()) {
                                        if (!(str.charAt(i2) == ' ')) {
                                            break;
                                        }
                                        i2++;
                                    }
                                }
                                int i5 = i2;
                                while (i5 < str.length()) {
                                    char charAt5 = str.charAt(i5);
                                    if (!(('0' <= charAt5 && charAt5 < ':') || charAt5 == '.')) {
                                        break;
                                    }
                                    i5++;
                                }
                                String substring4 = str.substring(i2, i5);
                                Intrinsics.checkNotNullExpressionValue(substring4, "this as java.lang.String…ing(startIndex, endIndex)");
                                if (substring4.length() == 0) {
                                    throw new IllegalArgumentException();
                                }
                                int length3 = i2 + substring4.length();
                                int i6 = length3;
                                while (i6 < str.length()) {
                                    char charAt6 = str.charAt(i6);
                                    if (!('a' <= charAt6 && charAt6 < '{')) {
                                        break;
                                    }
                                    i6++;
                                }
                                String substring5 = str.substring(length3, i6);
                                Intrinsics.checkNotNullExpressionValue(substring5, "this as java.lang.String…ing(startIndex, endIndex)");
                                i2 = length3 + substring5.length();
                                DurationUnit durationUnitByShortName = DurationUnitKt__DurationUnitKt.durationUnitByShortName(substring5);
                                if (durationUnit2 != null && durationUnit2.compareTo(durationUnitByShortName) <= 0) {
                                    throw new IllegalArgumentException(str3);
                                }
                                String str4 = str3;
                                indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) substring4, '.', 0, false, 6, (Object) null);
                                if (indexOf$default > 0) {
                                    String substring6 = substring4.substring(0, indexOf$default);
                                    Intrinsics.checkNotNullExpressionValue(substring6, "this as java.lang.String…ing(startIndex, endIndex)");
                                    long m1514plusLRDsOJo2 = Duration.m1514plusLRDsOJo(m1580getZEROUwyO8pc, toDuration(Long.parseLong(substring6), durationUnitByShortName));
                                    String substring7 = substring4.substring(indexOf$default);
                                    Intrinsics.checkNotNullExpressionValue(substring7, "this as java.lang.String).substring(startIndex)");
                                    m1580getZEROUwyO8pc = Duration.m1514plusLRDsOJo(m1514plusLRDsOJo2, toDuration(Double.parseDouble(substring7), durationUnitByShortName));
                                    if (i2 < length) {
                                        throw new IllegalArgumentException("Fractional component must be last");
                                    }
                                } else {
                                    m1580getZEROUwyO8pc = Duration.m1514plusLRDsOJo(m1580getZEROUwyO8pc, toDuration(Long.parseLong(substring4), durationUnitByShortName));
                                }
                                durationUnit2 = durationUnitByShortName;
                                str3 = str4;
                                z8 = true;
                            }
                        }
                    }
                    return z2 ? Duration.m1530unaryMinusUwyO8pc(m1580getZEROUwyO8pc) : m1580getZEROUwyO8pc;
                }
                throw new IllegalArgumentException("No components");
            }
        }
        z2 = false;
        if (length <= i2) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x001e  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0074  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final long parseOverLongIsoComponent(String str) {
        int i2;
        boolean startsWith$default;
        int lastIndex;
        boolean z;
        boolean z2;
        boolean contains$default;
        int length = str.length();
        if (length > 0) {
            contains$default = StringsKt__StringsKt.contains$default((CharSequence) "+-", str.charAt(0), false, 2, (Object) null);
            if (contains$default) {
                i2 = 1;
                if (length - i2 > 16) {
                    lastIndex = StringsKt__StringsKt.getLastIndex(str);
                    IntRange intRange = new IntRange(i2, lastIndex);
                    if (!(intRange instanceof Collection) || !((Collection) intRange).isEmpty()) {
                        Iterator<Integer> it = intRange.iterator();
                        while (it.hasNext()) {
                            char charAt = str.charAt(((IntIterator) it).nextInt());
                            if ('0' > charAt || charAt >= ':') {
                                z = false;
                                continue;
                            } else {
                                z = true;
                                continue;
                            }
                            if (!z) {
                                z2 = false;
                                break;
                            }
                        }
                    }
                    z2 = true;
                    if (z2) {
                        if (str.charAt(0) == '-') {
                            return Long.MIN_VALUE;
                        }
                        return LongCompanionObject.MAX_VALUE;
                    }
                }
                startsWith$default = StringsKt__StringsJVMKt.startsWith$default(str, Marker.ANY_NON_NULL_MARKER, false, 2, null);
                if (startsWith$default) {
                    str = StringsKt___StringsKt.drop(str, 1);
                }
                return Long.parseLong(str);
            }
        }
        i2 = 0;
        if (length - i2 > 16) {
        }
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(str, Marker.ANY_NON_NULL_MARKER, false, 2, null);
        if (startsWith$default) {
        }
        return Long.parseLong(str);
    }

    private static final int skipWhile(String str, int i2, Function1<? super Character, Boolean> function1) {
        while (i2 < str.length() && function1.invoke(Character.valueOf(str.charAt(i2))).booleanValue()) {
            i2++;
        }
        return i2;
    }

    private static final String substringWhile(String str, int i2, Function1<? super Character, Boolean> function1) {
        int i3 = i2;
        while (i3 < str.length() && function1.invoke(Character.valueOf(str.charAt(i3))).booleanValue()) {
            i3++;
        }
        String substring = str.substring(i2, i3);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return substring;
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalTime.class})
    @InlineOnly
    /* renamed from: times-kIfJnKk */
    private static final long m1603timeskIfJnKk(double d2, long j2) {
        return Duration.m1515timesUwyO8pc(j2, d2);
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalTime.class})
    @InlineOnly
    /* renamed from: times-mvk6XK0 */
    private static final long m1604timesmvk6XK0(int i2, long j2) {
        return Duration.m1516timesUwyO8pc(j2, i2);
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalTime.class})
    public static final long toDuration(double d2, @NotNull DurationUnit unit) {
        long roundToLong;
        long roundToLong2;
        Intrinsics.checkNotNullParameter(unit, "unit");
        double convertDurationUnit = DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(d2, unit, DurationUnit.NANOSECONDS);
        boolean z = true;
        if (!Double.isNaN(convertDurationUnit)) {
            roundToLong = MathKt__MathJVMKt.roundToLong(convertDurationUnit);
            if (-4611686018426999999L > roundToLong || roundToLong >= 4611686018427000000L) {
                z = false;
            }
            if (z) {
                return durationOfNanos(roundToLong);
            }
            roundToLong2 = MathKt__MathJVMKt.roundToLong(DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(d2, unit, DurationUnit.MILLISECONDS));
            return durationOfMillisNormalized(roundToLong2);
        }
        throw new IllegalArgumentException("Duration value cannot be NaN.".toString());
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalTime.class})
    public static final long toDuration(int i2, @NotNull DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return unit.compareTo(DurationUnit.SECONDS) <= 0 ? durationOfNanos(DurationUnitKt__DurationUnitJvmKt.convertDurationUnitOverflow(i2, unit, DurationUnit.NANOSECONDS)) : toDuration(i2, unit);
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalTime.class})
    public static final long toDuration(long j2, @NotNull DurationUnit unit) {
        long coerceIn;
        Intrinsics.checkNotNullParameter(unit, "unit");
        DurationUnit durationUnit = DurationUnit.NANOSECONDS;
        long convertDurationUnitOverflow = DurationUnitKt__DurationUnitJvmKt.convertDurationUnitOverflow(MAX_NANOS, durationUnit, unit);
        boolean z = false;
        if ((-convertDurationUnitOverflow) <= j2 && j2 <= convertDurationUnitOverflow) {
            z = true;
        }
        if (z) {
            return durationOfNanos(DurationUnitKt__DurationUnitJvmKt.convertDurationUnitOverflow(j2, unit, durationUnit));
        }
        coerceIn = RangesKt___RangesKt.coerceIn(DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(j2, unit, DurationUnit.MILLISECONDS), -4611686018427387903L, (long) MAX_MILLIS);
        return durationOfMillis(coerceIn);
    }
}
