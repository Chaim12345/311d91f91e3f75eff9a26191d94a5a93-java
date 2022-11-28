package com.google.api.client.util;

import com.psa.mym.mycitroenconnect.car_console.utils.AutoConstants;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.codec.language.Soundex;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes2.dex */
public final class DateTime implements Serializable {
    private static final long serialVersionUID = 1;
    private final boolean dateOnly;
    private final int tzShift;
    private final long value;
    private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    private static final String RFC3339_REGEX = "(\\d{4})-(\\d{2})-(\\d{2})([Tt](\\d{2}):(\\d{2}):(\\d{2})(\\.\\d{1,9})?)?([Zz]|([+-])(\\d{2}):(\\d{2}))?";
    private static final Pattern RFC3339_PATTERN = Pattern.compile(RFC3339_REGEX);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class Rfc3339ParseResult implements Serializable {
        private final int nanos;
        private final long seconds;
        private final boolean timeGiven;
        private final Integer tzShift;

        private Rfc3339ParseResult(long j2, int i2, boolean z, Integer num) {
            this.seconds = j2;
            this.nanos = i2;
            this.timeGiven = z;
            this.tzShift = num;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public DateTime toDateTime() {
            return new DateTime(!this.timeGiven, TimeUnit.SECONDS.toMillis(this.seconds) + TimeUnit.NANOSECONDS.toMillis(this.nanos), this.tzShift);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public SecondsAndNanos toSecondsAndNanos() {
            return new SecondsAndNanos(this.seconds, this.nanos);
        }
    }

    /* loaded from: classes2.dex */
    public static final class SecondsAndNanos implements Serializable {
        private final int nanos;
        private final long seconds;

        private SecondsAndNanos(long j2, int i2) {
            this.seconds = j2;
            this.nanos = i2;
        }

        public static SecondsAndNanos ofSecondsAndNanos(long j2, int i2) {
            return new SecondsAndNanos(j2, i2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || SecondsAndNanos.class != obj.getClass()) {
                return false;
            }
            SecondsAndNanos secondsAndNanos = (SecondsAndNanos) obj;
            return this.seconds == secondsAndNanos.seconds && this.nanos == secondsAndNanos.nanos;
        }

        public int getNanos() {
            return this.nanos;
        }

        public long getSeconds() {
            return this.seconds;
        }

        public int hashCode() {
            return java.util.Objects.hash(Long.valueOf(this.seconds), Integer.valueOf(this.nanos));
        }

        public String toString() {
            return String.format("Seconds: %d, Nanos: %d", Long.valueOf(this.seconds), Integer.valueOf(this.nanos));
        }
    }

    public DateTime(long j2) {
        this(false, j2, null);
    }

    public DateTime(long j2, int i2) {
        this(false, j2, Integer.valueOf(i2));
    }

    public DateTime(String str) {
        DateTime parseRfc3339 = parseRfc3339(str);
        this.dateOnly = parseRfc3339.dateOnly;
        this.value = parseRfc3339.value;
        this.tzShift = parseRfc3339.tzShift;
    }

    public DateTime(Date date) {
        this(date.getTime());
    }

    public DateTime(Date date, TimeZone timeZone) {
        this(false, date.getTime(), timeZone == null ? null : Integer.valueOf(timeZone.getOffset(date.getTime()) / 60000));
    }

    public DateTime(boolean z, long j2, Integer num) {
        this.dateOnly = z;
        this.value = j2;
        this.tzShift = z ? 0 : num == null ? TimeZone.getDefault().getOffset(j2) / 60000 : num.intValue();
    }

    private static void appendInt(StringBuilder sb, int i2, int i3) {
        if (i2 < 0) {
            sb.append(Soundex.SILENT_MARKER);
            i2 = -i2;
        }
        int i4 = i2;
        while (i4 > 0) {
            i4 /= 10;
            i3--;
        }
        for (int i5 = 0; i5 < i3; i5++) {
            sb.append('0');
        }
        if (i2 != 0) {
            sb.append(i2);
        }
    }

    public static DateTime parseRfc3339(String str) {
        return parseRfc3339WithNanoSeconds(str).toDateTime();
    }

    public static SecondsAndNanos parseRfc3339ToSecondsAndNanos(String str) {
        return parseRfc3339WithNanoSeconds(str).toSecondsAndNanos();
    }

    private static Rfc3339ParseResult parseRfc3339WithNanoSeconds(String str) {
        int i2;
        int i3;
        int i4;
        int i5;
        Integer num;
        int i6;
        Matcher matcher = RFC3339_PATTERN.matcher(str);
        if (!matcher.matches()) {
            throw new NumberFormatException("Invalid date/time format: " + str);
        }
        int parseInt = Integer.parseInt(matcher.group(1));
        int parseInt2 = Integer.parseInt(matcher.group(2)) - 1;
        int parseInt3 = Integer.parseInt(matcher.group(3));
        boolean z = matcher.group(4) != null;
        String group = matcher.group(9);
        boolean z2 = group != null;
        if (z2 && !z) {
            throw new NumberFormatException("Invalid date/time format, cannot specify time zone shift without specifying time: " + str);
        }
        if (z) {
            int parseInt4 = Integer.parseInt(matcher.group(5));
            int parseInt5 = Integer.parseInt(matcher.group(6));
            int parseInt6 = Integer.parseInt(matcher.group(7));
            if (matcher.group(8) != null) {
                i5 = Integer.parseInt(com.google.common.base.Strings.padEnd(matcher.group(8).substring(1), 9, '0'));
                i3 = parseInt5;
                i4 = parseInt6;
            } else {
                i3 = parseInt5;
                i4 = parseInt6;
                i5 = 0;
            }
            i2 = parseInt4;
        } else {
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(GMT);
        gregorianCalendar.clear();
        gregorianCalendar.set(parseInt, parseInt2, parseInt3, i2, i3, i4);
        long timeInMillis = gregorianCalendar.getTimeInMillis();
        if (z && z2) {
            if (Character.toUpperCase(group.charAt(0)) != 'Z') {
                int parseInt7 = (Integer.parseInt(matcher.group(11)) * 60) + Integer.parseInt(matcher.group(12));
                if (matcher.group(10).charAt(0) == '-') {
                    parseInt7 = -parseInt7;
                }
                timeInMillis -= parseInt7 * AutoConstants.UPDATE_INTERVAL_IN_MILLISECONDS;
                i6 = Integer.valueOf(parseInt7);
            } else {
                i6 = 0;
            }
            num = i6;
        } else {
            num = null;
        }
        return new Rfc3339ParseResult(timeInMillis / 1000, i5, z, num);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DateTime) {
            DateTime dateTime = (DateTime) obj;
            return this.dateOnly == dateTime.dateOnly && this.value == dateTime.value && this.tzShift == dateTime.tzShift;
        }
        return false;
    }

    public int getTimeZoneShift() {
        return this.tzShift;
    }

    public long getValue() {
        return this.value;
    }

    public int hashCode() {
        long[] jArr = new long[3];
        jArr[0] = this.value;
        jArr[1] = this.dateOnly ? 1L : 0L;
        jArr[2] = this.tzShift;
        return Arrays.hashCode(jArr);
    }

    public boolean isDateOnly() {
        return this.dateOnly;
    }

    public String toString() {
        return toStringRfc3339();
    }

    public String toStringRfc3339() {
        StringBuilder sb = new StringBuilder();
        GregorianCalendar gregorianCalendar = new GregorianCalendar(GMT);
        gregorianCalendar.setTimeInMillis(this.value + (this.tzShift * AutoConstants.UPDATE_INTERVAL_IN_MILLISECONDS));
        appendInt(sb, gregorianCalendar.get(1), 4);
        sb.append(Soundex.SILENT_MARKER);
        appendInt(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append(Soundex.SILENT_MARKER);
        appendInt(sb, gregorianCalendar.get(5), 2);
        if (!this.dateOnly) {
            sb.append('T');
            appendInt(sb, gregorianCalendar.get(11), 2);
            sb.append(AbstractJsonLexerKt.COLON);
            appendInt(sb, gregorianCalendar.get(12), 2);
            sb.append(AbstractJsonLexerKt.COLON);
            appendInt(sb, gregorianCalendar.get(13), 2);
            if (gregorianCalendar.isSet(14)) {
                sb.append('.');
                appendInt(sb, gregorianCalendar.get(14), 3);
            }
            int i2 = this.tzShift;
            if (i2 == 0) {
                sb.append(Matrix.MATRIX_TYPE_ZERO);
            } else {
                if (i2 > 0) {
                    sb.append('+');
                } else {
                    sb.append(Soundex.SILENT_MARKER);
                    i2 = -i2;
                }
                appendInt(sb, i2 / 60, 2);
                sb.append(AbstractJsonLexerKt.COLON);
                appendInt(sb, i2 % 60, 2);
            }
        }
        return sb.toString();
    }
}
