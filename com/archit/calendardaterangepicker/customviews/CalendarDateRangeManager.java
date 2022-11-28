package com.archit.calendardaterangepicker.customviews;

import java.util.Calendar;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001b\u001cJ\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H&J\b\u0010\u0007\u001a\u00020\u0002H&J\b\u0010\b\u001a\u00020\u0002H&J\u0018\u0010\u000b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H&J\u001a\u0010\f\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00022\b\u0010\n\u001a\u0004\u0018\u00010\u0002H&J\b\u0010\r\u001a\u00020\u0005H&J\n\u0010\u000e\u001a\u0004\u0018\u00010\u0002H&J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0002H&J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010H&J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0002H&J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0002H&J\u0010\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0002H&¨\u0006\u001d"}, d2 = {"Lcom/archit/calendardaterangepicker/customviews/CalendarDateRangeManager;", "", "Ljava/util/Calendar;", "startMonth", "endMonth", "", "setVisibleMonths", "getStartVisibleMonth", "getEndVisibleMonth", "startDate", "endDate", "setSelectableDateRange", "setSelectedDateRange", "resetSelectedDateRange", "getMaxSelectedDate", "getMinSelectedDate", "", "getVisibleMonthDataList", "month", "", "getMonthIndex", "date", "", "isSelectableDate", "selectedDate", "Lcom/archit/calendardaterangepicker/customviews/CalendarDateRangeManager$DateSelectionState;", "checkDateRange", "Companion", "DateSelectionState", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public interface CalendarDateRangeManager {
    public static final Companion Companion = Companion.f4645a;
    @NotNull
    public static final String DATE_FORMAT = "yyyyMMddHHmm";

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004¨\u0006\u0007"}, d2 = {"Lcom/archit/calendardaterangepicker/customviews/CalendarDateRangeManager$Companion;", "", "", "DATE_FORMAT", "Ljava/lang/String;", "<init>", "()V", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Companion {
        @NotNull
        public static final String DATE_FORMAT = "yyyyMMddHHmm";

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ Companion f4645a = new Companion();

        private Companion() {
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/archit/calendardaterangepicker/customviews/CalendarDateRangeManager$DateSelectionState;", "", "<init>", "(Ljava/lang/String;I)V", "UNKNOWN", "START_DATE", "LAST_DATE", "START_END_SAME", "IN_SELECTED_RANGE", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public enum DateSelectionState {
        UNKNOWN,
        START_DATE,
        LAST_DATE,
        START_END_SAME,
        IN_SELECTED_RANGE
    }

    @NotNull
    DateSelectionState checkDateRange(@NotNull Calendar calendar);

    @NotNull
    Calendar getEndVisibleMonth();

    @Nullable
    Calendar getMaxSelectedDate();

    @Nullable
    Calendar getMinSelectedDate();

    int getMonthIndex(@NotNull Calendar calendar);

    @NotNull
    Calendar getStartVisibleMonth();

    @NotNull
    List<Calendar> getVisibleMonthDataList();

    boolean isSelectableDate(@NotNull Calendar calendar);

    void resetSelectedDateRange();

    void setSelectableDateRange(@NotNull Calendar calendar, @NotNull Calendar calendar2);

    void setSelectedDateRange(@NotNull Calendar calendar, @Nullable Calendar calendar2);

    void setVisibleMonths(@NotNull Calendar calendar, @NotNull Calendar calendar2);
}
