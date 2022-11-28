package com.archit.calendardaterangepicker.customviews;

import androidx.room.RoomDatabase;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0016\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002\u001a\u0010\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0000\u001a\u0016\u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000\u001a\u0016\u0010\r\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000¨\u0006\u000e"}, d2 = {"Ljava/util/Calendar;", "date", "Lcom/archit/calendardaterangepicker/customviews/DateTiming;", "dateTiming", "", "resetTime", "calendar", "", "printDate", "one", "second", "", "isMonthSame", "isDateSame", "awesome-calendar_release"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class CalendarRangeUtilsKt {

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DateTiming.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[DateTiming.START.ordinal()] = 1;
            iArr[DateTiming.END.ordinal()] = 2;
        }
    }

    public static final boolean isDateSame(@NotNull Calendar one, @NotNull Calendar second) {
        Intrinsics.checkParameterIsNotNull(one, "one");
        Intrinsics.checkParameterIsNotNull(second, "second");
        return isMonthSame(one, second) && one.get(5) == second.get(5);
    }

    public static final boolean isMonthSame(@NotNull Calendar one, @NotNull Calendar second) {
        Intrinsics.checkParameterIsNotNull(one, "one");
        Intrinsics.checkParameterIsNotNull(second, "second");
        return one.get(1) == second.get(1) && one.get(2) == second.get(2);
    }

    @NotNull
    public static final String printDate(@Nullable Calendar calendar) {
        if (calendar != null) {
            String format = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(calendar.getTime());
            Intrinsics.checkExpressionValueIsNotNull(format, "simpleDateFormat.format(calendar.time)");
            return format;
        }
        return "null";
    }

    public static final void resetTime(@NotNull Calendar date, @NotNull DateTiming dateTiming) {
        Intrinsics.checkParameterIsNotNull(date, "date");
        Intrinsics.checkParameterIsNotNull(dateTiming, "dateTiming");
        int i2 = WhenMappings.$EnumSwitchMapping$0[dateTiming.ordinal()];
        if (i2 == 1 || i2 != 2) {
            date.set(11, 0);
            date.set(12, 0);
            date.set(13, 0);
            date.set(14, 0);
            return;
        }
        date.set(11, 23);
        date.set(12, 59);
        date.set(13, 59);
        date.set(14, RoomDatabase.MAX_BIND_PARAMETER_CNT);
    }
}
