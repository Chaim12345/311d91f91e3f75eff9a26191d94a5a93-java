package com.archit.calendardaterangepicker.customviews;

import com.archit.calendardaterangepicker.customviews.CalendarDateRangeManager;
import com.archit.calendardaterangepicker.customviews.DateView;
import com.archit.calendardaterangepicker.models.CalendarStyleAttributes;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.message.TokenParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 /2\u00020\u0001:\u0001/B\u001f\u0012\u0006\u0010+\u001a\u00020\u0002\u0012\u0006\u0010,\u001a\u00020\u0002\u0012\u0006\u0010)\u001a\u00020(¢\u0006\u0004\b-\u0010.J\u001a\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0002J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0016J\n\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0016J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\tH\u0016J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\u0018\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0002H\u0016J\b\u0010\u0011\u001a\u00020\u0002H\u0016J\b\u0010\u0012\u001a\u00020\u0002H\u0016J\u0018\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0002H\u0016J\b\u0010\u0016\u001a\u00020\u0005H\u0016J\u001a\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00022\b\u0010\u0014\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0002H\u0016J\u0010\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u0002H\u0016R\u0016\u0010\u001e\u001a\u00020\u00028\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\u00020\u00028\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b \u0010\u001fR\u0016\u0010!\u001a\u00020\u00028\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b!\u0010\u001fR\u0016\u0010\"\u001a\u00020\u00028\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\"\u0010\u001fR\u0018\u0010#\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010\u001fR\u0018\u0010$\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010\u001fR\u001c\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00020%8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010'R\u0016\u0010)\u001a\u00020(8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*¨\u00060"}, d2 = {"Lcom/archit/calendardaterangepicker/customviews/CalendarDateRangeManagerImpl;", "Lcom/archit/calendardaterangepicker/customviews/CalendarDateRangeManager;", "Ljava/util/Calendar;", "start", "end", "", "validateDatesOrder", "getMaxSelectedDate", "getMinSelectedDate", "", "getVisibleMonthDataList", "month", "", "getMonthIndex", "startMonth", "endMonth", "setVisibleMonths", "getStartVisibleMonth", "getEndVisibleMonth", "startDate", "endDate", "setSelectableDateRange", "resetSelectedDateRange", "setSelectedDateRange", "selectedDate", "Lcom/archit/calendardaterangepicker/customviews/CalendarDateRangeManager$DateSelectionState;", "checkDateRange", "date", "", "isSelectableDate", "mStartVisibleMonth", "Ljava/util/Calendar;", "mEndVisibleMonth", "mStartSelectableDate", "mEndSelectableDate", "mMinSelectedDate", "mMaxSelectedDate", "", "mVisibleMonths", "Ljava/util/List;", "Lcom/archit/calendardaterangepicker/models/CalendarStyleAttributes;", "calendarStyleAttributes", "Lcom/archit/calendardaterangepicker/models/CalendarStyleAttributes;", "startMonthDate", "endMonthDate", "<init>", "(Ljava/util/Calendar;Ljava/util/Calendar;Lcom/archit/calendardaterangepicker/models/CalendarStyleAttributes;)V", "Companion", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class CalendarDateRangeManagerImpl implements CalendarDateRangeManager {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "CDRManagerImpl";
    private final CalendarStyleAttributes calendarStyleAttributes;
    private Calendar mEndSelectableDate;
    private Calendar mEndVisibleMonth;
    private Calendar mMaxSelectedDate;
    private Calendar mMinSelectedDate;
    private Calendar mStartSelectableDate;
    private Calendar mStartVisibleMonth;
    private final List<Calendar> mVisibleMonths;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004¨\u0006\u0007"}, d2 = {"Lcom/archit/calendardaterangepicker/customviews/CalendarDateRangeManagerImpl$Companion;", "", "", "TAG", "Ljava/lang/String;", "<init>", "()V", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CalendarStyleAttributes.DateSelectionMode.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[CalendarStyleAttributes.DateSelectionMode.SINGLE.ordinal()] = 1;
            iArr[CalendarStyleAttributes.DateSelectionMode.FIXED_RANGE.ordinal()] = 2;
            iArr[CalendarStyleAttributes.DateSelectionMode.FREE_RANGE.ordinal()] = 3;
        }
    }

    public CalendarDateRangeManagerImpl(@NotNull Calendar startMonthDate, @NotNull Calendar endMonthDate, @NotNull CalendarStyleAttributes calendarStyleAttributes) {
        Intrinsics.checkParameterIsNotNull(startMonthDate, "startMonthDate");
        Intrinsics.checkParameterIsNotNull(endMonthDate, "endMonthDate");
        Intrinsics.checkParameterIsNotNull(calendarStyleAttributes, "calendarStyleAttributes");
        this.calendarStyleAttributes = calendarStyleAttributes;
        this.mVisibleMonths = new ArrayList();
        setVisibleMonths(startMonthDate, endMonthDate);
    }

    private final void validateDatesOrder(Calendar calendar, Calendar calendar2) {
        if (calendar.after(calendar2)) {
            throw new InvalidDateException("Start date(" + CalendarRangeUtilsKt.printDate(calendar) + ") can not be after end date(" + CalendarRangeUtilsKt.printDate(calendar2) + ").");
        }
    }

    @Override // com.archit.calendardaterangepicker.customviews.CalendarDateRangeManager
    @NotNull
    public CalendarDateRangeManager.DateSelectionState checkDateRange(@NotNull Calendar selectedDate) {
        Intrinsics.checkParameterIsNotNull(selectedDate, "selectedDate");
        Calendar calendar = this.mMinSelectedDate;
        if (calendar != null && this.mMaxSelectedDate != null) {
            DateView.Companion companion = DateView.Companion;
            long containerKey = companion.getContainerKey(selectedDate);
            Calendar calendar2 = this.mMinSelectedDate;
            if (calendar2 == null) {
                Intrinsics.throwNpe();
            }
            long containerKey2 = companion.getContainerKey(calendar2);
            Calendar calendar3 = this.mMaxSelectedDate;
            if (calendar3 == null) {
                Intrinsics.throwNpe();
            }
            long containerKey3 = companion.getContainerKey(calendar3);
            Calendar calendar4 = this.mMinSelectedDate;
            if (calendar4 == null) {
                Intrinsics.throwNpe();
            }
            if (CalendarRangeUtilsKt.isDateSame(selectedDate, calendar4)) {
                Calendar calendar5 = this.mMaxSelectedDate;
                if (calendar5 == null) {
                    Intrinsics.throwNpe();
                }
                if (CalendarRangeUtilsKt.isDateSame(selectedDate, calendar5)) {
                    return CalendarDateRangeManager.DateSelectionState.START_END_SAME;
                }
            }
            Calendar calendar6 = this.mMinSelectedDate;
            if (calendar6 == null) {
                Intrinsics.throwNpe();
            }
            if (CalendarRangeUtilsKt.isDateSame(selectedDate, calendar6)) {
                return CalendarDateRangeManager.DateSelectionState.START_DATE;
            }
            Calendar calendar7 = this.mMaxSelectedDate;
            if (calendar7 == null) {
                Intrinsics.throwNpe();
            }
            if (CalendarRangeUtilsKt.isDateSame(selectedDate, calendar7)) {
                return CalendarDateRangeManager.DateSelectionState.LAST_DATE;
            }
            if (containerKey2 <= containerKey && containerKey3 > containerKey) {
                return CalendarDateRangeManager.DateSelectionState.IN_SELECTED_RANGE;
            }
        } else if (calendar != null) {
            if (calendar == null) {
                Intrinsics.throwNpe();
            }
            if (CalendarRangeUtilsKt.isDateSame(selectedDate, calendar)) {
                return CalendarDateRangeManager.DateSelectionState.START_END_SAME;
            }
        }
        return CalendarDateRangeManager.DateSelectionState.UNKNOWN;
    }

    @Override // com.archit.calendardaterangepicker.customviews.CalendarDateRangeManager
    @NotNull
    public Calendar getEndVisibleMonth() {
        Calendar calendar = this.mEndVisibleMonth;
        if (calendar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mEndVisibleMonth");
        }
        return calendar;
    }

    @Override // com.archit.calendardaterangepicker.customviews.CalendarDateRangeManager
    @Nullable
    public Calendar getMaxSelectedDate() {
        return this.mMaxSelectedDate;
    }

    @Override // com.archit.calendardaterangepicker.customviews.CalendarDateRangeManager
    @Nullable
    public Calendar getMinSelectedDate() {
        return this.mMinSelectedDate;
    }

    @Override // com.archit.calendardaterangepicker.customviews.CalendarDateRangeManager
    public int getMonthIndex(@NotNull Calendar month) {
        Intrinsics.checkParameterIsNotNull(month, "month");
        int size = this.mVisibleMonths.size();
        for (int i2 = 0; i2 < size; i2++) {
            Calendar calendar = this.mVisibleMonths.get(i2);
            if (month.get(1) == calendar.get(1) && month.get(2) == calendar.get(2)) {
                return i2;
            }
        }
        throw new RuntimeException("Month(" + month.getTime().toString() + ") is not available in the given month range.");
    }

    @Override // com.archit.calendardaterangepicker.customviews.CalendarDateRangeManager
    @NotNull
    public Calendar getStartVisibleMonth() {
        Calendar calendar = this.mStartVisibleMonth;
        if (calendar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mStartVisibleMonth");
        }
        return calendar;
    }

    @Override // com.archit.calendardaterangepicker.customviews.CalendarDateRangeManager
    @NotNull
    public List<Calendar> getVisibleMonthDataList() {
        return this.mVisibleMonths;
    }

    @Override // com.archit.calendardaterangepicker.customviews.CalendarDateRangeManager
    public boolean isSelectableDate(@NotNull Calendar date) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(date, "date");
        Calendar calendar = this.mStartSelectableDate;
        if (calendar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mStartSelectableDate");
        }
        if (!date.before(calendar)) {
            Calendar calendar2 = this.mEndSelectableDate;
            if (calendar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEndSelectableDate");
            }
            if (!date.after(calendar2)) {
                z = true;
                if (!z || checkDateRange(date) == CalendarDateRangeManager.DateSelectionState.UNKNOWN) {
                    CalendarRangeUtilsKt.printDate(date);
                    CalendarRangeUtilsKt.printDate(this.mMinSelectedDate);
                    CalendarRangeUtilsKt.printDate(this.mMaxSelectedDate);
                }
                return z;
            }
        }
        z = false;
        if (!z) {
        }
        CalendarRangeUtilsKt.printDate(date);
        CalendarRangeUtilsKt.printDate(this.mMinSelectedDate);
        CalendarRangeUtilsKt.printDate(this.mMaxSelectedDate);
        return z;
    }

    @Override // com.archit.calendardaterangepicker.customviews.CalendarDateRangeManager
    public void resetSelectedDateRange() {
        this.mMinSelectedDate = null;
        this.mMaxSelectedDate = null;
    }

    @Override // com.archit.calendardaterangepicker.customviews.CalendarDateRangeManager
    public void setSelectableDateRange(@NotNull Calendar startDate, @NotNull Calendar endDate) {
        Intrinsics.checkParameterIsNotNull(startDate, "startDate");
        Intrinsics.checkParameterIsNotNull(endDate, "endDate");
        validateDatesOrder(startDate, endDate);
        Object clone = startDate.clone();
        if (clone == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
        }
        Calendar calendar = (Calendar) clone;
        this.mStartSelectableDate = calendar;
        CalendarRangeUtilsKt.resetTime(calendar, DateTiming.START);
        Object clone2 = endDate.clone();
        if (clone2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
        }
        Calendar calendar2 = (Calendar) clone2;
        this.mEndSelectableDate = calendar2;
        CalendarRangeUtilsKt.resetTime(calendar2, DateTiming.END);
        Calendar calendar3 = this.mStartSelectableDate;
        if (calendar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mStartSelectableDate");
        }
        Calendar calendar4 = this.mStartVisibleMonth;
        if (calendar4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mStartVisibleMonth");
        }
        if (calendar3.before(calendar4)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Selectable start date ");
            sb.append(CalendarRangeUtilsKt.printDate(startDate));
            sb.append(" is out of visible months");
            sb.append('(');
            Calendar calendar5 = this.mStartVisibleMonth;
            if (calendar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mStartVisibleMonth");
            }
            sb.append(CalendarRangeUtilsKt.printDate(calendar5));
            sb.append(TokenParser.SP);
            sb.append("- ");
            Calendar calendar6 = this.mEndVisibleMonth;
            if (calendar6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEndVisibleMonth");
            }
            sb.append(CalendarRangeUtilsKt.printDate(calendar6));
            sb.append(").");
            throw new InvalidDateException(sb.toString());
        }
        Calendar calendar7 = this.mEndSelectableDate;
        if (calendar7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mEndSelectableDate");
        }
        Calendar calendar8 = this.mEndVisibleMonth;
        if (calendar8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mEndVisibleMonth");
        }
        if (!calendar7.after(calendar8)) {
            resetSelectedDateRange();
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Selectable end date ");
        sb2.append(CalendarRangeUtilsKt.printDate(endDate));
        sb2.append(" is out of visible months");
        sb2.append('(');
        Calendar calendar9 = this.mStartVisibleMonth;
        if (calendar9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mStartVisibleMonth");
        }
        sb2.append(CalendarRangeUtilsKt.printDate(calendar9));
        sb2.append(TokenParser.SP);
        sb2.append("- ");
        Calendar calendar10 = this.mEndVisibleMonth;
        if (calendar10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mEndVisibleMonth");
        }
        sb2.append(CalendarRangeUtilsKt.printDate(calendar10));
        sb2.append(").");
        throw new InvalidDateException(sb2.toString());
    }

    @Override // com.archit.calendardaterangepicker.customviews.CalendarDateRangeManager
    public void setSelectedDateRange(@NotNull Calendar startDate, @Nullable Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(startDate, "startDate");
        validateDatesOrder(startDate, calendar);
        Calendar calendar2 = this.mStartSelectableDate;
        if (calendar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mStartSelectableDate");
        }
        if (startDate.before(calendar2)) {
            throw new InvalidDateException("Start date(" + CalendarRangeUtilsKt.printDate(startDate) + ") is out of selectable date range.");
        }
        if (calendar != null) {
            Calendar calendar3 = this.mEndSelectableDate;
            if (calendar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEndSelectableDate");
            }
            if (calendar.after(calendar3)) {
                throw new InvalidDateException("End date(" + CalendarRangeUtilsKt.printDate(calendar) + ") is out of selectable date range.");
            }
        }
        CalendarStyleAttributes.DateSelectionMode dateSelectionMode = this.calendarStyleAttributes.getDateSelectionMode();
        int i2 = WhenMappings.$EnumSwitchMapping$0[dateSelectionMode.ordinal()];
        if (i2 == 1) {
            Object clone = startDate.clone();
            if (clone == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
            }
            calendar = (Calendar) clone;
            StringBuilder sb = new StringBuilder();
            sb.append("End date is ignored due date selection mode: ");
            sb.append(dateSelectionMode);
        } else if (i2 == 2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("End date is ignored due date selection mode: ");
            sb2.append(dateSelectionMode);
            Object clone2 = startDate.clone();
            if (clone2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
            }
            calendar = (Calendar) clone2;
            calendar.add(5, this.calendarStyleAttributes.getFixedDaysSelectionNumber());
        } else if (i2 != 3) {
            throw new IllegalArgumentException("Unsupported selectionMode: " + dateSelectionMode);
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Selected dates: Start(");
        sb3.append(CalendarRangeUtilsKt.printDate(startDate));
        sb3.append(")-End(");
        sb3.append(CalendarRangeUtilsKt.printDate(calendar));
        sb3.append(") for mode:");
        sb3.append(dateSelectionMode);
        Object clone3 = startDate.clone();
        if (clone3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
        }
        this.mMinSelectedDate = (Calendar) clone3;
        this.mMaxSelectedDate = (Calendar) (calendar != null ? calendar.clone() : null);
    }

    @Override // com.archit.calendardaterangepicker.customviews.CalendarDateRangeManager
    public void setVisibleMonths(@NotNull Calendar startMonth, @NotNull Calendar endMonth) {
        Intrinsics.checkParameterIsNotNull(startMonth, "startMonth");
        Intrinsics.checkParameterIsNotNull(endMonth, "endMonth");
        validateDatesOrder(startMonth, endMonth);
        Object clone = startMonth.clone();
        if (clone == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
        }
        Calendar calendar = (Calendar) clone;
        Object clone2 = endMonth.clone();
        if (clone2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
        }
        Calendar calendar2 = (Calendar) clone2;
        calendar.set(5, 1);
        DateTiming dateTiming = DateTiming.START;
        CalendarRangeUtilsKt.resetTime(calendar, dateTiming);
        calendar2.set(5, calendar2.getActualMaximum(5));
        DateTiming dateTiming2 = DateTiming.END;
        CalendarRangeUtilsKt.resetTime(calendar2, dateTiming2);
        Object clone3 = calendar.clone();
        if (clone3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
        }
        Calendar calendar3 = (Calendar) clone3;
        this.mStartVisibleMonth = calendar3;
        CalendarRangeUtilsKt.resetTime(calendar3, dateTiming);
        Object clone4 = calendar2.clone();
        if (clone4 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
        }
        Calendar calendar4 = (Calendar) clone4;
        this.mEndVisibleMonth = calendar4;
        CalendarRangeUtilsKt.resetTime(calendar4, dateTiming2);
        this.mVisibleMonths.clear();
        Calendar calendar5 = this.mStartVisibleMonth;
        if (calendar5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mStartVisibleMonth");
        }
        Object clone5 = calendar5.clone();
        if (clone5 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
        }
        Calendar calendar6 = (Calendar) clone5;
        while (true) {
            Calendar calendar7 = this.mEndVisibleMonth;
            if (calendar7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEndVisibleMonth");
            }
            if (CalendarRangeUtilsKt.isMonthSame(calendar6, calendar7)) {
                List<Calendar> list = this.mVisibleMonths;
                Object clone6 = calendar6.clone();
                if (clone6 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
                }
                list.add((Calendar) clone6);
                Calendar calendar8 = this.mStartVisibleMonth;
                if (calendar8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mStartVisibleMonth");
                }
                Calendar calendar9 = this.mEndVisibleMonth;
                if (calendar9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mEndVisibleMonth");
                }
                setSelectableDateRange(calendar8, calendar9);
                return;
            }
            List<Calendar> list2 = this.mVisibleMonths;
            Object clone7 = calendar6.clone();
            if (clone7 == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
            }
            list2.add((Calendar) clone7);
            calendar6.add(2, 1);
        }
    }
}
