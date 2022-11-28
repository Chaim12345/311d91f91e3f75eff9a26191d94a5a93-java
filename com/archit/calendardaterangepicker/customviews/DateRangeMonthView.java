package com.archit.calendardaterangepicker.customviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.archit.calendardaterangepicker.R;
import com.archit.calendardaterangepicker.customviews.CalendarDateRangeManager;
import com.archit.calendardaterangepicker.customviews.DateView;
import com.archit.calendardaterangepicker.models.CalendarStyleAttributes;
import java.util.Calendar;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\b\u0000\u0018\u0000 /2\u00020\u0001:\u0001/B\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b&\u0010'B\u001b\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010(\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b&\u0010)B#\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010(\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010+\u001a\u00020*¢\u0006\u0004\b&\u0010,B+\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010(\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010+\u001a\u00020*\u0012\u0006\u0010-\u001a\u00020*¢\u0006\u0004\b&\u0010.J\u001a\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002J\b\u0010\b\u001a\u00020\u0006H\u0002J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0002J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\tH\u0002J\u0018\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\tH\u0002J\b\u0010\u0012\u001a\u00020\u0006H\u0002J\u0010\u0010\u0015\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013J\u001e\u0010\r\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u0018J\u0006\u0010\u001a\u001a\u00020\u0006R\u0016\u0010\u001b\u001a\u00020\u00018\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0016\u0010\u001d\u001a\u00020\u00018\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u001d\u0010\u001cR\u0016\u0010\u001e\u001a\u00020\t8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010\u0017\u001a\u00020\u00168\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u0017\u0010 R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010!R\u0016\u0010\u0019\u001a\u00020\u00188\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u0019\u0010\"R\u0016\u0010$\u001a\u00020#8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%¨\u00060"}, d2 = {"Lcom/archit/calendardaterangepicker/customviews/DateRangeMonthView;", "Landroid/widget/LinearLayout;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attributeSet", "", "initView", "setListeners", "Ljava/util/Calendar;", "selectedDate", "setSelectedDate", "month", "drawCalendarForMonth", "Lcom/archit/calendardaterangepicker/customviews/CustomDateView;", "customDateView", "date", "drawDayContainer", "setWeekTextAttributes", "Lcom/archit/calendardaterangepicker/customviews/CalendarListener;", "calendarListener", "setCalendarListener", "Lcom/archit/calendardaterangepicker/models/CalendarStyleAttributes;", "calendarStyleAttr", "Lcom/archit/calendardaterangepicker/customviews/CalendarDateRangeManager;", "dateRangeCalendarManager", "resetAllSelectedViews", "llDaysContainer", "Landroid/widget/LinearLayout;", "llTitleWeekContainer", "currentCalendarMonth", "Ljava/util/Calendar;", "Lcom/archit/calendardaterangepicker/models/CalendarStyleAttributes;", "Lcom/archit/calendardaterangepicker/customviews/CalendarListener;", "Lcom/archit/calendardaterangepicker/customviews/CalendarDateRangeManager;", "Lcom/archit/calendardaterangepicker/customviews/DateView$OnDateClickListener;", "mOnDateClickListener", "Lcom/archit/calendardaterangepicker/customviews/DateView$OnDateClickListener;", "<init>", "(Landroid/content/Context;)V", "attrs", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "Companion", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class DateRangeMonthView extends LinearLayout {
    public static final Companion Companion = new Companion(null);
    private static final String LOG_TAG = DateRangeMonthView.class.getSimpleName();
    private HashMap _$_findViewCache;
    private CalendarListener calendarListener;
    private CalendarStyleAttributes calendarStyleAttr;
    private Calendar currentCalendarMonth;
    private CalendarDateRangeManager dateRangeCalendarManager;
    private LinearLayout llDaysContainer;
    private LinearLayout llTitleWeekContainer;
    private final DateView.OnDateClickListener mOnDateClickListener;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007R\u001e\u0010\u0004\u001a\n \u0003*\u0004\u0018\u00010\u00020\u00028\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0005¨\u0006\b"}, d2 = {"Lcom/archit/calendardaterangepicker/customviews/DateRangeMonthView$Companion;", "", "", "kotlin.jvm.PlatformType", "LOG_TAG", "Ljava/lang/String;", "<init>", "()V", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
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
            iArr[CalendarStyleAttributes.DateSelectionMode.FREE_RANGE.ordinal()] = 1;
            iArr[CalendarStyleAttributes.DateSelectionMode.SINGLE.ordinal()] = 2;
            iArr[CalendarStyleAttributes.DateSelectionMode.FIXED_RANGE.ordinal()] = 3;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateRangeMonthView(@NotNull Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mOnDateClickListener = new DateRangeMonthView$mOnDateClickListener$1(this);
        initView(context, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateRangeMonthView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mOnDateClickListener = new DateRangeMonthView$mOnDateClickListener$1(this);
        initView(context, attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateRangeMonthView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mOnDateClickListener = new DateRangeMonthView$mOnDateClickListener$1(this);
        initView(context, attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @TargetApi(21)
    public DateRangeMonthView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mOnDateClickListener = new DateRangeMonthView$mOnDateClickListener$1(this);
        initView(context, attributeSet);
    }

    public static final /* synthetic */ CalendarStyleAttributes access$getCalendarStyleAttr$p(DateRangeMonthView dateRangeMonthView) {
        CalendarStyleAttributes calendarStyleAttributes = dateRangeMonthView.calendarStyleAttr;
        if (calendarStyleAttributes == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
        }
        return calendarStyleAttributes;
    }

    private final void drawCalendarForMonth(Calendar calendar) {
        setWeekTextAttributes();
        Object clone = calendar.clone();
        if (clone == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
        }
        Calendar calendar2 = (Calendar) clone;
        this.currentCalendarMonth = calendar2;
        calendar2.set(5, 1);
        Calendar calendar3 = this.currentCalendarMonth;
        if (calendar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentCalendarMonth");
        }
        CalendarRangeUtilsKt.resetTime(calendar3, DateTiming.NONE);
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        String[] stringArray = context.getResources().getStringArray(R.array.week_sun_sat);
        for (int i2 = 0; i2 <= 6; i2++) {
            LinearLayout linearLayout = this.llTitleWeekContainer;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("llTitleWeekContainer");
            }
            View childAt = linearLayout.getChildAt(i2);
            if (childAt == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.archit.calendardaterangepicker.customviews.CustomTextView");
            }
            CustomTextView customTextView = (CustomTextView) childAt;
            CalendarStyleAttributes calendarStyleAttributes = this.calendarStyleAttr;
            if (calendarStyleAttributes == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
            }
            customTextView.setText(stringArray[(calendarStyleAttributes.getWeekOffset() + i2) % 7]);
        }
        int i3 = calendar.get(7);
        CalendarStyleAttributes calendarStyleAttributes2 = this.calendarStyleAttr;
        if (calendarStyleAttributes2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
        }
        int weekOffset = i3 - calendarStyleAttributes2.getWeekOffset();
        if (weekOffset < 1) {
            weekOffset += 7;
        }
        calendar.add(5, (-weekOffset) + 1);
        LinearLayout linearLayout2 = this.llDaysContainer;
        if (linearLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("llDaysContainer");
        }
        int childCount = linearLayout2.getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            LinearLayout linearLayout3 = this.llDaysContainer;
            if (linearLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("llDaysContainer");
            }
            View childAt2 = linearLayout3.getChildAt(i4);
            if (childAt2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout");
            }
            LinearLayout linearLayout4 = (LinearLayout) childAt2;
            for (int i5 = 0; i5 <= 6; i5++) {
                View childAt3 = linearLayout4.getChildAt(i5);
                if (childAt3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.archit.calendardaterangepicker.customviews.CustomDateView");
                }
                drawDayContainer((CustomDateView) childAt3, calendar);
                calendar.add(5, 1);
            }
        }
    }

    private final void drawDayContainer(CustomDateView customDateView, Calendar calendar) {
        DateView.DateState dateState;
        customDateView.setDateText(String.valueOf(calendar.get(5)));
        CalendarStyleAttributes calendarStyleAttributes = this.calendarStyleAttr;
        if (calendarStyleAttributes == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
        }
        customDateView.setDateStyleAttributes(calendarStyleAttributes);
        customDateView.setDateClickListener(this.mOnDateClickListener);
        CalendarStyleAttributes calendarStyleAttributes2 = this.calendarStyleAttr;
        if (calendarStyleAttributes2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
        }
        Typeface fonts = calendarStyleAttributes2.getFonts();
        if (fonts != null) {
            customDateView.setTypeface(fonts);
        }
        Calendar calendar2 = this.currentCalendarMonth;
        if (calendar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentCalendarMonth");
        }
        if (calendar2.get(2) != calendar.get(2)) {
            dateState = DateView.DateState.HIDDEN;
        } else {
            CalendarDateRangeManager calendarDateRangeManager = this.dateRangeCalendarManager;
            if (calendarDateRangeManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dateRangeCalendarManager");
            }
            CalendarDateRangeManager.DateSelectionState checkDateRange = calendarDateRangeManager.checkDateRange(calendar);
            if (checkDateRange == CalendarDateRangeManager.DateSelectionState.START_DATE) {
                dateState = DateView.DateState.START;
            } else if (checkDateRange == CalendarDateRangeManager.DateSelectionState.LAST_DATE) {
                dateState = DateView.DateState.END;
            } else if (checkDateRange == CalendarDateRangeManager.DateSelectionState.START_END_SAME) {
                dateState = DateView.DateState.START_END_SAME;
            } else if (checkDateRange == CalendarDateRangeManager.DateSelectionState.IN_SELECTED_RANGE) {
                dateState = DateView.DateState.MIDDLE;
            } else {
                CalendarDateRangeManager calendarDateRangeManager2 = this.dateRangeCalendarManager;
                if (calendarDateRangeManager2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("dateRangeCalendarManager");
                }
                dateState = calendarDateRangeManager2.isSelectableDate(calendar) ? DateView.DateState.SELECTABLE : DateView.DateState.DISABLE;
            }
        }
        customDateView.updateDateBackground(dateState);
        customDateView.setTag(Long.valueOf(DateView.Companion.getContainerKey(calendar)));
    }

    private final void initView(Context context, AttributeSet attributeSet) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_calendar_month, (ViewGroup) this, true);
        if (inflate == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout");
        }
        LinearLayout linearLayout = (LinearLayout) inflate;
        View findViewById = linearLayout.findViewById(R.id.llDaysContainer);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "mainView.findViewById(R.id.llDaysContainer)");
        this.llDaysContainer = (LinearLayout) findViewById;
        View findViewById2 = linearLayout.findViewById(R.id.llTitleWeekContainer);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "mainView.findViewById(R.id.llTitleWeekContainer)");
        this.llTitleWeekContainer = (LinearLayout) findViewById2;
        setListeners();
    }

    private final void setListeners() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSelectedDate(Calendar calendar) {
        CalendarStyleAttributes calendarStyleAttributes = this.calendarStyleAttr;
        if (calendarStyleAttributes == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
        }
        CalendarStyleAttributes.DateSelectionMode dateSelectionMode = calendarStyleAttributes.getDateSelectionMode();
        CalendarDateRangeManager calendarDateRangeManager = this.dateRangeCalendarManager;
        if (calendarDateRangeManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dateRangeCalendarManager");
        }
        Calendar minSelectedDate = calendarDateRangeManager.getMinSelectedDate();
        CalendarDateRangeManager calendarDateRangeManager2 = this.dateRangeCalendarManager;
        if (calendarDateRangeManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dateRangeCalendarManager");
        }
        Calendar maxSelectedDate = calendarDateRangeManager2.getMaxSelectedDate();
        int i2 = WhenMappings.$EnumSwitchMapping$0[dateSelectionMode.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    Object clone = calendar.clone();
                    if (clone == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
                    }
                    maxSelectedDate = (Calendar) clone;
                    CalendarStyleAttributes calendarStyleAttributes2 = this.calendarStyleAttr;
                    if (calendarStyleAttributes2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
                    }
                    maxSelectedDate.add(5, calendarStyleAttributes2.getFixedDaysSelectionNumber());
                    minSelectedDate = calendar;
                }
            }
            minSelectedDate = calendar;
            maxSelectedDate = minSelectedDate;
        } else if (minSelectedDate == null || maxSelectedDate != null) {
            if (maxSelectedDate != null) {
                maxSelectedDate = null;
            }
            minSelectedDate = calendar;
        } else {
            DateView.Companion companion = DateView.Companion;
            int i3 = (companion.getContainerKey(minSelectedDate) > companion.getContainerKey(calendar) ? 1 : (companion.getContainerKey(minSelectedDate) == companion.getContainerKey(calendar) ? 0 : -1));
            if (i3 != 0) {
                if (i3 > 0) {
                    Object clone2 = minSelectedDate.clone();
                    if (clone2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
                    }
                    minSelectedDate = calendar;
                    maxSelectedDate = (Calendar) clone2;
                } else {
                    maxSelectedDate = calendar;
                }
            }
            minSelectedDate = calendar;
            maxSelectedDate = minSelectedDate;
        }
        CalendarDateRangeManager calendarDateRangeManager3 = this.dateRangeCalendarManager;
        if (calendarDateRangeManager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dateRangeCalendarManager");
        }
        calendarDateRangeManager3.setSelectedDateRange(minSelectedDate, maxSelectedDate);
        Calendar calendar2 = this.currentCalendarMonth;
        if (calendar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentCalendarMonth");
        }
        drawCalendarForMonth(calendar2);
        StringBuilder sb = new StringBuilder();
        sb.append("Time: ");
        sb.append(calendar.getTime().toString());
        CalendarListener calendarListener = this.calendarListener;
        if (maxSelectedDate != null) {
            if (calendarListener == null) {
                Intrinsics.throwNpe();
            }
            calendarListener.onDateRangeSelected(minSelectedDate, maxSelectedDate);
            return;
        }
        if (calendarListener == null) {
            Intrinsics.throwNpe();
        }
        calendarListener.onFirstDateSelected(minSelectedDate);
    }

    private final void setWeekTextAttributes() {
        LinearLayout linearLayout = this.llTitleWeekContainer;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("llTitleWeekContainer");
        }
        int childCount = linearLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            LinearLayout linearLayout2 = this.llTitleWeekContainer;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("llTitleWeekContainer");
            }
            View childAt = linearLayout2.getChildAt(i2);
            if (childAt == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.archit.calendardaterangepicker.customviews.CustomTextView");
            }
            CustomTextView customTextView = (CustomTextView) childAt;
            CalendarStyleAttributes calendarStyleAttributes = this.calendarStyleAttr;
            if (calendarStyleAttributes == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
            }
            customTextView.setTypeface(calendarStyleAttributes.getFonts());
            CalendarStyleAttributes calendarStyleAttributes2 = this.calendarStyleAttr;
            if (calendarStyleAttributes2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
            }
            customTextView.setTextSize(0, calendarStyleAttributes2.getTextSizeWeek());
            CalendarStyleAttributes calendarStyleAttributes3 = this.calendarStyleAttr;
            if (calendarStyleAttributes3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
            }
            customTextView.setTextColor(calendarStyleAttributes3.getWeekColor());
        }
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i2) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            this._$_findViewCache.put(Integer.valueOf(i2), findViewById);
            return findViewById;
        }
        return view;
    }

    public final void drawCalendarForMonth(@NotNull CalendarStyleAttributes calendarStyleAttr, @NotNull Calendar month, @NotNull CalendarDateRangeManager dateRangeCalendarManager) {
        Intrinsics.checkParameterIsNotNull(calendarStyleAttr, "calendarStyleAttr");
        Intrinsics.checkParameterIsNotNull(month, "month");
        Intrinsics.checkParameterIsNotNull(dateRangeCalendarManager, "dateRangeCalendarManager");
        this.calendarStyleAttr = calendarStyleAttr;
        Object clone = month.clone();
        if (clone == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
        }
        Calendar calendar = (Calendar) clone;
        this.currentCalendarMonth = calendar;
        this.dateRangeCalendarManager = dateRangeCalendarManager;
        drawCalendarForMonth(calendar);
    }

    public final void resetAllSelectedViews() {
        CalendarDateRangeManager calendarDateRangeManager = this.dateRangeCalendarManager;
        if (calendarDateRangeManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dateRangeCalendarManager");
        }
        calendarDateRangeManager.resetSelectedDateRange();
        Calendar calendar = this.currentCalendarMonth;
        if (calendar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentCalendarMonth");
        }
        drawCalendarForMonth(calendar);
    }

    public final void setCalendarListener(@Nullable CalendarListener calendarListener) {
        this.calendarListener = calendarListener;
    }
}
