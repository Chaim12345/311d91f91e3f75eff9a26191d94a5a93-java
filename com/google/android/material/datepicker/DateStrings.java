package com.google.android.material.datepicker;

import android.content.Context;
import android.os.Build;
import android.text.format.DateUtils;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
/* loaded from: classes2.dex */
class DateStrings {
    private DateStrings() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Pair a(@Nullable Long l2, @Nullable Long l3) {
        return b(l2, l3, null);
    }

    static Pair b(@Nullable Long l2, @Nullable Long l3, @Nullable SimpleDateFormat simpleDateFormat) {
        if (l2 == null && l3 == null) {
            return Pair.create(null, null);
        }
        if (l2 == null) {
            return Pair.create(null, d(l3.longValue(), simpleDateFormat));
        }
        if (l3 == null) {
            return Pair.create(d(l2.longValue(), simpleDateFormat), null);
        }
        Calendar k2 = UtcDates.k();
        Calendar l4 = UtcDates.l();
        l4.setTimeInMillis(l2.longValue());
        Calendar l5 = UtcDates.l();
        l5.setTimeInMillis(l3.longValue());
        if (simpleDateFormat != null) {
            return Pair.create(simpleDateFormat.format(new Date(l2.longValue())), simpleDateFormat.format(new Date(l3.longValue())));
        }
        return l4.get(1) == l5.get(1) ? l4.get(1) == k2.get(1) ? Pair.create(f(l2.longValue(), Locale.getDefault()), f(l3.longValue(), Locale.getDefault())) : Pair.create(f(l2.longValue(), Locale.getDefault()), k(l3.longValue(), Locale.getDefault())) : Pair.create(k(l2.longValue(), Locale.getDefault()), k(l3.longValue(), Locale.getDefault()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String c(long j2) {
        return d(j2, null);
    }

    static String d(long j2, @Nullable SimpleDateFormat simpleDateFormat) {
        Calendar k2 = UtcDates.k();
        Calendar l2 = UtcDates.l();
        l2.setTimeInMillis(j2);
        return simpleDateFormat != null ? simpleDateFormat.format(new Date(j2)) : k2.get(1) == l2.get(1) ? e(j2) : j(j2);
    }

    static String e(long j2) {
        return f(j2, Locale.getDefault());
    }

    static String f(long j2, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? UtcDates.b(locale).format(new Date(j2)) : UtcDates.g(locale).format(new Date(j2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String g(long j2) {
        return h(j2, Locale.getDefault());
    }

    static String h(long j2, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? UtcDates.c(locale).format(new Date(j2)) : UtcDates.e(locale).format(new Date(j2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String i(Context context, long j2) {
        return DateUtils.formatDateTime(context, j2 - TimeZone.getDefault().getOffset(j2), 36);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String j(long j2) {
        return k(j2, Locale.getDefault());
    }

    static String k(long j2, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? UtcDates.n(locale).format(new Date(j2)) : UtcDates.f(locale).format(new Date(j2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String l(long j2) {
        return m(j2, Locale.getDefault());
    }

    static String m(long j2, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? UtcDates.o(locale).format(new Date(j2)) : UtcDates.e(locale).format(new Date(j2));
    }
}
