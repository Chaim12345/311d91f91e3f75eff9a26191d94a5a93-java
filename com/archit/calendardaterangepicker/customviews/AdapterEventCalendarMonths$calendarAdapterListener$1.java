package com.archit.calendardaterangepicker.customviews;

import android.os.Handler;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016¨\u0006\b"}, d2 = {"com/archit/calendardaterangepicker/customviews/AdapterEventCalendarMonths$calendarAdapterListener$1", "Lcom/archit/calendardaterangepicker/customviews/CalendarListener;", "Ljava/util/Calendar;", "startDate", "", "onFirstDateSelected", "endDate", "onDateRangeSelected", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class AdapterEventCalendarMonths$calendarAdapterListener$1 implements CalendarListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AdapterEventCalendarMonths f4641a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdapterEventCalendarMonths$calendarAdapterListener$1(AdapterEventCalendarMonths adapterEventCalendarMonths) {
        this.f4641a = adapterEventCalendarMonths;
    }

    @Override // com.archit.calendardaterangepicker.customviews.CalendarListener
    public void onDateRangeSelected(@NotNull Calendar startDate, @NotNull Calendar endDate) {
        Handler handler;
        CalendarListener calendarListener;
        CalendarListener calendarListener2;
        Intrinsics.checkParameterIsNotNull(startDate, "startDate");
        Intrinsics.checkParameterIsNotNull(endDate, "endDate");
        handler = this.f4641a.mHandler;
        handler.postDelayed(new Runnable() { // from class: com.archit.calendardaterangepicker.customviews.AdapterEventCalendarMonths$calendarAdapterListener$1$onDateRangeSelected$1
            @Override // java.lang.Runnable
            public final void run() {
                AdapterEventCalendarMonths$calendarAdapterListener$1.this.f4641a.notifyDataSetChanged();
            }
        }, 50L);
        calendarListener = this.f4641a.mCalendarListener;
        if (calendarListener != null) {
            calendarListener2 = this.f4641a.mCalendarListener;
            if (calendarListener2 == null) {
                Intrinsics.throwNpe();
            }
            calendarListener2.onDateRangeSelected(startDate, endDate);
        }
    }

    @Override // com.archit.calendardaterangepicker.customviews.CalendarListener
    public void onFirstDateSelected(@NotNull Calendar startDate) {
        Handler handler;
        CalendarListener calendarListener;
        CalendarListener calendarListener2;
        Intrinsics.checkParameterIsNotNull(startDate, "startDate");
        handler = this.f4641a.mHandler;
        handler.postDelayed(new Runnable() { // from class: com.archit.calendardaterangepicker.customviews.AdapterEventCalendarMonths$calendarAdapterListener$1$onFirstDateSelected$1
            @Override // java.lang.Runnable
            public final void run() {
                AdapterEventCalendarMonths$calendarAdapterListener$1.this.f4641a.notifyDataSetChanged();
            }
        }, 50L);
        calendarListener = this.f4641a.mCalendarListener;
        if (calendarListener != null) {
            calendarListener2 = this.f4641a.mCalendarListener;
            if (calendarListener2 == null) {
                Intrinsics.throwNpe();
            }
            calendarListener2.onFirstDateSelected(startDate);
        }
    }
}
