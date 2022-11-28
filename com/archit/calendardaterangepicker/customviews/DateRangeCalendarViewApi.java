package com.archit.calendardaterangepicker.customviews;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Calendar;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\b\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H&J\b\u0010\t\u001a\u00020\u0004H&J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH&J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH&J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\rH&J\u0018\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H&J\u0010\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0012H&J\u0018\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0012H&J\u0018\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0012H&J\u0010\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\nH&R\u001c\u0010\u001f\u001a\u00020\u001e8&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00128&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00128&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b%\u0010$¨\u0006&"}, d2 = {"Lcom/archit/calendardaterangepicker/customviews/DateRangeCalendarViewApi;", "", "Lcom/archit/calendardaterangepicker/customviews/CalendarListener;", "calendarListener", "", "setCalendarListener", "Landroid/graphics/Typeface;", "fonts", "setFonts", "resetAllSelectedViews", "", TypedValues.Cycle.S_WAVE_OFFSET, "setWeekOffset", "Landroid/graphics/drawable/Drawable;", "leftDrawable", "setNavLeftImage", "rightDrawable", "setNavRightImage", "Ljava/util/Calendar;", "startMonth", "endMonth", "setVisibleMonthRange", "calendar", "setCurrentMonth", "startDate", "endDate", "setSelectableDateRange", "setSelectedDateRange", "numberOfDaysSelection", "setFixedDaysSelection", "", "isEditable", "()Z", "setEditable", "(Z)V", "getStartDate", "()Ljava/util/Calendar;", "getEndDate", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public interface DateRangeCalendarViewApi {
    @Nullable
    Calendar getEndDate();

    @Nullable
    Calendar getStartDate();

    boolean isEditable();

    void resetAllSelectedViews();

    void setCalendarListener(@NotNull CalendarListener calendarListener);

    void setCurrentMonth(@NotNull Calendar calendar);

    void setEditable(boolean z);

    void setFixedDaysSelection(int i2);

    void setFonts(@NotNull Typeface typeface);

    void setNavLeftImage(@NotNull Drawable drawable);

    void setNavRightImage(@NotNull Drawable drawable);

    void setSelectableDateRange(@NotNull Calendar calendar, @NotNull Calendar calendar2);

    void setSelectedDateRange(@NotNull Calendar calendar, @NotNull Calendar calendar2);

    void setVisibleMonthRange(@NotNull Calendar calendar, @NotNull Calendar calendar2);

    void setWeekOffset(int i2);
}
