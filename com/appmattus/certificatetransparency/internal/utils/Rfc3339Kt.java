package com.appmattus.certificatetransparency.internal.utils;

import com.psa.mym.mycitroenconnect.car_console.utils.AutoConstants;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class Rfc3339Kt {
    private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    @NotNull
    private static final Regex Rfc3339Pattern = new Regex("^(\\d{4})-(\\d{2})-(\\d{2})([Tt](\\d{2}):(\\d{2}):(\\d{2})(\\.\\d+)?)?([Zz]|([+-])(\\d{2}):(\\d{2}))?");

    public static final long toRfc3339Long(@NotNull String str) {
        int i2;
        int i3;
        int i4;
        int i5;
        String substring;
        Intrinsics.checkNotNullParameter(str, "<this>");
        MatchResult matchEntire = Rfc3339Pattern.matchEntire(str);
        if (matchEntire == null) {
            throw new NumberFormatException("Invalid RFC3339 date/time format: " + str);
        }
        int parseInt = Integer.parseInt(matchEntire.getGroupValues().get(1));
        int parseInt2 = Integer.parseInt(matchEntire.getGroupValues().get(2)) - 1;
        int parseInt3 = Integer.parseInt(matchEntire.getGroupValues().get(3));
        boolean z = matchEntire.getGroupValues().get(4).length() > 0;
        String str2 = matchEntire.getGroupValues().get(9);
        boolean z2 = str2.length() > 0;
        if (z2 && !z) {
            throw new NumberFormatException("Invalid RFC33339 date/time format, cannot specify time zone shift without specifying time: " + str);
        }
        if (z) {
            int parseInt4 = Integer.parseInt(matchEntire.getGroupValues().get(5));
            int parseInt5 = Integer.parseInt(matchEntire.getGroupValues().get(6));
            int parseInt6 = Integer.parseInt(matchEntire.getGroupValues().get(7));
            if (matchEntire.getGroupValues().get(8).length() > 0) {
                String substring2 = matchEntire.getGroupValues().get(8).substring(1);
                Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
                int parseInt7 = Integer.parseInt(substring2);
                Intrinsics.checkNotNullExpressionValue(matchEntire.getGroupValues().get(8).substring(1), "this as java.lang.String).substring(startIndex)");
                i2 = (int) (parseInt7 / Math.pow(10.0d, substring.length() - 3));
                i5 = parseInt6;
            } else {
                i5 = parseInt6;
                i2 = 0;
            }
            i4 = parseInt5;
            i3 = parseInt4;
        } else {
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(GMT);
        gregorianCalendar.set(parseInt, parseInt2, parseInt3, i3, i4, i5);
        gregorianCalendar.set(14, i2);
        long timeInMillis = gregorianCalendar.getTimeInMillis();
        if (z && z2 && Character.toUpperCase(str2.charAt(0)) != 'Z') {
            int parseInt8 = (Integer.parseInt(matchEntire.getGroupValues().get(11)) * 60) + Integer.parseInt(matchEntire.getGroupValues().get(12));
            if (matchEntire.getGroupValues().get(10).charAt(0) == '-') {
                parseInt8 = -parseInt8;
            }
            return timeInMillis - (parseInt8 * AutoConstants.UPDATE_INTERVAL_IN_MILLISECONDS);
        }
        return timeInMillis;
    }
}
