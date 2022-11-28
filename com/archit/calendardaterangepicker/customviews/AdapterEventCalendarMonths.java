package com.archit.calendardaterangepicker.customviews;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import com.archit.calendardaterangepicker.R;
import com.archit.calendardaterangepicker.models.CalendarStyleAttributes;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010(\u001a\u00020'\u0012\u0006\u0010/\u001a\u00020.\u0012\u0006\u00100\u001a\u00020\u001b¢\u0006\u0004\b1\u00102J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016J\u0018\u0010\u0010\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J \u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\tH\u0016J\u0010\u0010\u0018\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016J\u0006\u0010\u0019\u001a\u00020\u0012J\u0006\u0010\u001a\u001a\u00020\u0012R\u0016\u0010\u001c\u001a\u00020\u001b8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010!\u001a\u00020 8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u0016\u0010$\u001a\u00020#8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010&\u001a\u00020\u00168\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010\u001fR\u0016\u0010(\u001a\u00020'8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R$\u0010*\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-¨\u00063"}, d2 = {"Lcom/archit/calendardaterangepicker/customviews/AdapterEventCalendarMonths;", "Landroidx/viewpager/widget/PagerAdapter;", "Ljava/util/Calendar;", "calendar", "getCurrentMonth", "", "getCount", "Landroid/view/View;", "view", "", "obj", "", "isViewFromObject", "Landroid/view/ViewGroup;", "container", AppConstants.ARG_POSITION, "instantiateItem", "collection", "", "destroyItem", "object", "getItemPosition", "Lcom/archit/calendardaterangepicker/customviews/CalendarListener;", "calendarListener", "setCalendarListener", "invalidateCalendar", "resetAllSelectedViews", "Lcom/archit/calendardaterangepicker/models/CalendarStyleAttributes;", "mCalendarStyleAttr", "Lcom/archit/calendardaterangepicker/models/CalendarStyleAttributes;", "mCalendarListener", "Lcom/archit/calendardaterangepicker/customviews/CalendarListener;", "Lcom/archit/calendardaterangepicker/customviews/CalendarDateRangeManager;", "mDateRangeCalendarManager", "Lcom/archit/calendardaterangepicker/customviews/CalendarDateRangeManager;", "Landroid/os/Handler;", "mHandler", "Landroid/os/Handler;", "calendarAdapterListener", "Landroid/content/Context;", "mContext", "Landroid/content/Context;", "isEditable", "()Z", "setEditable", "(Z)V", "Lcom/archit/calendardaterangepicker/customviews/CalendarDateRangeManagerImpl;", "calendarDateRangeManager", "calendarStyleAttr", "<init>", "(Landroid/content/Context;Lcom/archit/calendardaterangepicker/customviews/CalendarDateRangeManagerImpl;Lcom/archit/calendardaterangepicker/models/CalendarStyleAttributes;)V", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class AdapterEventCalendarMonths extends PagerAdapter {
    private final CalendarListener calendarAdapterListener;
    private CalendarListener mCalendarListener;
    private final CalendarStyleAttributes mCalendarStyleAttr;
    private final Context mContext;
    private final CalendarDateRangeManager mDateRangeCalendarManager;
    private final Handler mHandler;

    public AdapterEventCalendarMonths(@NotNull Context mContext, @NotNull CalendarDateRangeManagerImpl calendarDateRangeManager, @NotNull CalendarStyleAttributes calendarStyleAttr) {
        Intrinsics.checkParameterIsNotNull(mContext, "mContext");
        Intrinsics.checkParameterIsNotNull(calendarDateRangeManager, "calendarDateRangeManager");
        Intrinsics.checkParameterIsNotNull(calendarStyleAttr, "calendarStyleAttr");
        this.mContext = mContext;
        this.mHandler = new Handler();
        this.calendarAdapterListener = new AdapterEventCalendarMonths$calendarAdapterListener$1(this);
        this.mDateRangeCalendarManager = calendarDateRangeManager;
        this.mCalendarStyleAttr = calendarStyleAttr;
    }

    private final Calendar getCurrentMonth(Calendar calendar) {
        Object clone = calendar.clone();
        if (clone != null) {
            Calendar calendar2 = (Calendar) clone;
            calendar2.set(5, 1);
            return calendar2;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(@NotNull ViewGroup collection, int i2, @NotNull Object view) {
        Intrinsics.checkParameterIsNotNull(collection, "collection");
        Intrinsics.checkParameterIsNotNull(view, "view");
        collection.removeView((View) view);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.mDateRangeCalendarManager.getVisibleMonthDataList().size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(@NotNull Object object) {
        Intrinsics.checkParameterIsNotNull(object, "object");
        return -2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @NotNull
    public Object instantiateItem(@NotNull ViewGroup container, int i2) {
        Intrinsics.checkParameterIsNotNull(container, "container");
        Calendar calendar = this.mDateRangeCalendarManager.getVisibleMonthDataList().get(i2);
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.layout_pager_month, container, false);
        if (inflate != null) {
            ViewGroup viewGroup = (ViewGroup) inflate;
            View findViewById = viewGroup.findViewById(R.id.cvEventCalendarView);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "layout.findViewById(id.cvEventCalendarView)");
            DateRangeMonthView dateRangeMonthView = (DateRangeMonthView) findViewById;
            dateRangeMonthView.drawCalendarForMonth(this.mCalendarStyleAttr, getCurrentMonth(calendar), this.mDateRangeCalendarManager);
            dateRangeMonthView.setCalendarListener(this.calendarAdapterListener);
            container.addView(viewGroup);
            return viewGroup;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
    }

    public final void invalidateCalendar() {
        this.mHandler.postDelayed(new Runnable() { // from class: com.archit.calendardaterangepicker.customviews.AdapterEventCalendarMonths$invalidateCalendar$1
            @Override // java.lang.Runnable
            public final void run() {
                AdapterEventCalendarMonths.this.notifyDataSetChanged();
            }
        }, 50L);
    }

    public final boolean isEditable() {
        return this.mCalendarStyleAttr.isEditable();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(@NotNull View view, @NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(obj, "obj");
        return view == obj;
    }

    public final void resetAllSelectedViews() {
        notifyDataSetChanged();
    }

    public final void setCalendarListener(@Nullable CalendarListener calendarListener) {
        this.mCalendarListener = calendarListener;
    }

    public final void setEditable(boolean z) {
        this.mCalendarStyleAttr.setEditable(z);
        notifyDataSetChanged();
    }
}
