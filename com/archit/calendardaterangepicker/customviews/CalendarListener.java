package com.archit.calendardaterangepicker.customviews;

import java.util.Calendar;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H&¨\u0006\b"}, d2 = {"Lcom/archit/calendardaterangepicker/customviews/CalendarListener;", "", "Ljava/util/Calendar;", "startDate", "", "onFirstDateSelected", "endDate", "onDateRangeSelected", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public interface CalendarListener {
    void onDateRangeSelected(@NotNull Calendar calendar, @NotNull Calendar calendar2);

    void onFirstDateSelected(@NotNull Calendar calendar);
}
