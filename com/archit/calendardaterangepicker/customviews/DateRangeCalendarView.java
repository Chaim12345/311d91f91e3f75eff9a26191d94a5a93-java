package com.archit.calendardaterangepicker.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.viewpager.widget.ViewPager;
import com.archit.calendardaterangepicker.R;
import com.archit.calendardaterangepicker.models.CalendarStyleAttrImpl;
import com.archit.calendardaterangepicker.models.CalendarStyleAttributes;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u0000 K2\u00020\u00012\u00020\u0002:\u0001KB\u0011\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\bF\u0010GB\u001b\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\bF\u0010HB#\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010I\u001a\u00020\n¢\u0006\u0004\bF\u0010JJ\u001a\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0002J\b\u0010\t\u001a\u00020\u0007H\u0002J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0010\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\u0010\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J\b\u0010\u0014\u001a\u00020\u0007H\u0016J\u0010\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\nH\u0016J\u0010\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0017H\u0016J\u0010\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0017H\u0016J\u0018\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001cH\u0016J\u0018\u0010\"\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u001cH\u0016J\u0010\u0010$\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u001cH\u0016J\u0018\u0010%\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001cH\u0016J\u0010\u0010'\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\nH\u0016R\u0016\u0010)\u001a\u00020(8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010,\u001a\u00020+8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010.\u001a\u00020+8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b.\u0010-R\u0016\u00100\u001a\u00020/8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b0\u00101R\u0016\u00103\u001a\u0002028\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b3\u00104R\u0016\u00106\u001a\u0002058\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b6\u00107R\u0016\u00109\u001a\u0002088\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b9\u0010:R\u0016\u0010<\u001a\u00020;8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b<\u0010=R\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u001c8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b>\u0010?R\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u001c8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b@\u0010?R$\u0010B\u001a\u00020A2\u0006\u0010B\u001a\u00020A8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bB\u0010C\"\u0004\bD\u0010E¨\u0006L"}, d2 = {"Lcom/archit/calendardaterangepicker/customviews/DateRangeCalendarView;", "Landroid/widget/LinearLayout;", "Lcom/archit/calendardaterangepicker/customviews/DateRangeCalendarViewApi;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "", "initViews", "setListeners", "", AppConstants.ARG_POSITION, "setNavigationHeader", "setCalendarYearTitle", "Lcom/archit/calendardaterangepicker/customviews/CalendarListener;", "calendarListener", "setCalendarListener", "Landroid/graphics/Typeface;", "fonts", "setFonts", "resetAllSelectedViews", TypedValues.Cycle.S_WAVE_OFFSET, "setWeekOffset", "Landroid/graphics/drawable/Drawable;", "leftDrawable", "setNavLeftImage", "rightDrawable", "setNavRightImage", "Ljava/util/Calendar;", "startDate", "endDate", "setSelectedDateRange", "startMonth", "endMonth", "setVisibleMonthRange", "calendar", "setCurrentMonth", "setSelectableDateRange", "numberOfDaysSelection", "setFixedDaysSelection", "Lcom/archit/calendardaterangepicker/customviews/CustomTextView;", "tvYearTitle", "Lcom/archit/calendardaterangepicker/customviews/CustomTextView;", "Landroidx/appcompat/widget/AppCompatImageView;", "imgVNavLeft", "Landroidx/appcompat/widget/AppCompatImageView;", "imgVNavRight", "Lcom/archit/calendardaterangepicker/customviews/AdapterEventCalendarMonths;", "adapterEventCalendarMonths", "Lcom/archit/calendardaterangepicker/customviews/AdapterEventCalendarMonths;", "Ljava/util/Locale;", "locale", "Ljava/util/Locale;", "Landroidx/viewpager/widget/ViewPager;", "vpCalendar", "Landroidx/viewpager/widget/ViewPager;", "Lcom/archit/calendardaterangepicker/models/CalendarStyleAttributes;", "calendarStyleAttr", "Lcom/archit/calendardaterangepicker/models/CalendarStyleAttributes;", "Lcom/archit/calendardaterangepicker/customviews/CalendarDateRangeManagerImpl;", "mDateRangeCalendarManager", "Lcom/archit/calendardaterangepicker/customviews/CalendarDateRangeManagerImpl;", "getStartDate", "()Ljava/util/Calendar;", "getEndDate", "", "isEditable", "()Z", "setEditable", "(Z)V", "<init>", "(Landroid/content/Context;)V", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "Companion", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class DateRangeCalendarView extends LinearLayout implements DateRangeCalendarViewApi {
    public static final Companion Companion = new Companion(null);
    private static final int TOTAL_ALLOWED_MONTHS = 30;
    private HashMap _$_findViewCache;
    private AdapterEventCalendarMonths adapterEventCalendarMonths;
    private CalendarStyleAttributes calendarStyleAttr;
    private AppCompatImageView imgVNavLeft;
    private AppCompatImageView imgVNavRight;
    private Locale locale;
    private CalendarDateRangeManagerImpl mDateRangeCalendarManager;
    private CustomTextView tvYearTitle;
    private ViewPager vpCalendar;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004¨\u0006\u0007"}, d2 = {"Lcom/archit/calendardaterangepicker/customviews/DateRangeCalendarView$Companion;", "", "", "TOTAL_ALLOWED_MONTHS", "I", "<init>", "()V", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateRangeCalendarView(@NotNull Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        initViews(context, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateRangeCalendarView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        initViews(context, attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateRangeCalendarView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Intrinsics.checkParameterIsNotNull(context, "context");
        initViews(context, attributeSet);
    }

    public static final /* synthetic */ CalendarDateRangeManagerImpl access$getMDateRangeCalendarManager$p(DateRangeCalendarView dateRangeCalendarView) {
        CalendarDateRangeManagerImpl calendarDateRangeManagerImpl = dateRangeCalendarView.mDateRangeCalendarManager;
        if (calendarDateRangeManagerImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDateRangeCalendarManager");
        }
        return calendarDateRangeManagerImpl;
    }

    public static final /* synthetic */ ViewPager access$getVpCalendar$p(DateRangeCalendarView dateRangeCalendarView) {
        ViewPager viewPager = dateRangeCalendarView.vpCalendar;
        if (viewPager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vpCalendar");
        }
        return viewPager;
    }

    private final void initViews(Context context, AttributeSet attributeSet) {
        Resources resources = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        Locale locale = resources.getConfiguration().locale;
        Intrinsics.checkExpressionValueIsNotNull(locale, "context.resources.configuration.locale");
        this.locale = locale;
        this.calendarStyleAttr = new CalendarStyleAttrImpl(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.layout_calendar_container, (ViewGroup) this, true);
        RelativeLayout rlHeaderCalendar = (RelativeLayout) findViewById(R.id.rlHeaderCalendar);
        Intrinsics.checkExpressionValueIsNotNull(rlHeaderCalendar, "rlHeaderCalendar");
        CalendarStyleAttributes calendarStyleAttributes = this.calendarStyleAttr;
        if (calendarStyleAttributes == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
        }
        rlHeaderCalendar.setBackground(calendarStyleAttributes.getHeaderBg());
        View findViewById = findViewById(R.id.tvYearTitle);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.tvYearTitle)");
        CustomTextView customTextView = (CustomTextView) findViewById;
        this.tvYearTitle = customTextView;
        if (customTextView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvYearTitle");
        }
        CalendarStyleAttributes calendarStyleAttributes2 = this.calendarStyleAttr;
        if (calendarStyleAttributes2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
        }
        customTextView.setTextSize(0, calendarStyleAttributes2.getTextSizeTitle());
        View findViewById2 = findViewById(R.id.imgVNavLeft);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "findViewById(R.id.imgVNavLeft)");
        this.imgVNavLeft = (AppCompatImageView) findViewById2;
        View findViewById3 = findViewById(R.id.imgVNavRight);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "findViewById(R.id.imgVNavRight)");
        this.imgVNavRight = (AppCompatImageView) findViewById3;
        View findViewById4 = findViewById(R.id.vpCalendar);
        Intrinsics.checkExpressionValueIsNotNull(findViewById4, "findViewById(R.id.vpCalendar)");
        this.vpCalendar = (ViewPager) findViewById4;
        Object clone = Calendar.getInstance().clone();
        if (clone == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
        }
        Calendar calendar = (Calendar) clone;
        calendar.add(2, -30);
        Object clone2 = Calendar.getInstance().clone();
        if (clone2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
        }
        Calendar calendar2 = (Calendar) clone2;
        calendar2.add(2, 30);
        CalendarStyleAttributes calendarStyleAttributes3 = this.calendarStyleAttr;
        if (calendarStyleAttributes3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
        }
        CalendarDateRangeManagerImpl calendarDateRangeManagerImpl = new CalendarDateRangeManagerImpl(calendar, calendar2, calendarStyleAttributes3);
        this.mDateRangeCalendarManager = calendarDateRangeManagerImpl;
        CalendarStyleAttributes calendarStyleAttributes4 = this.calendarStyleAttr;
        if (calendarStyleAttributes4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
        }
        this.adapterEventCalendarMonths = new AdapterEventCalendarMonths(context, calendarDateRangeManagerImpl, calendarStyleAttributes4);
        ViewPager viewPager = this.vpCalendar;
        if (viewPager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vpCalendar");
        }
        AdapterEventCalendarMonths adapterEventCalendarMonths = this.adapterEventCalendarMonths;
        if (adapterEventCalendarMonths == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterEventCalendarMonths");
        }
        viewPager.setAdapter(adapterEventCalendarMonths);
        ViewPager viewPager2 = this.vpCalendar;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vpCalendar");
        }
        viewPager2.setOffscreenPageLimit(0);
        ViewPager viewPager3 = this.vpCalendar;
        if (viewPager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vpCalendar");
        }
        viewPager3.setCurrentItem(30);
        setCalendarYearTitle(30);
        setListeners();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCalendarYearTitle(int i2) {
        CalendarDateRangeManagerImpl calendarDateRangeManagerImpl = this.mDateRangeCalendarManager;
        if (calendarDateRangeManagerImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDateRangeCalendarManager");
        }
        Calendar calendar = calendarDateRangeManagerImpl.getVisibleMonthDataList().get(i2);
        Locale locale = this.locale;
        if (locale == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locale");
        }
        String dateText = new DateFormatSymbols(locale).getMonths()[calendar.get(2)];
        StringBuilder sb = new StringBuilder();
        Intrinsics.checkExpressionValueIsNotNull(dateText, "dateText");
        if (dateText == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String substring = dateText.substring(0, 1);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if (substring == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String upperCase = substring.toUpperCase();
        Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
        sb.append(upperCase);
        sb.append(dateText.subSequence(1, dateText.length()));
        String str = sb.toString() + " " + calendar.get(1);
        CustomTextView customTextView = this.tvYearTitle;
        if (customTextView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvYearTitle");
        }
        customTextView.setText(str);
        CustomTextView customTextView2 = this.tvYearTitle;
        if (customTextView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvYearTitle");
        }
        CalendarStyleAttributes calendarStyleAttributes = this.calendarStyleAttr;
        if (calendarStyleAttributes == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
        }
        customTextView2.setTextColor(calendarStyleAttributes.getTitleColor());
    }

    private final void setListeners() {
        ViewPager viewPager = this.vpCalendar;
        if (viewPager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vpCalendar");
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.archit.calendardaterangepicker.customviews.DateRangeCalendarView$setListeners$1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i2, float f2, int i3) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i2) {
                DateRangeCalendarView.this.setCalendarYearTitle(i2);
                DateRangeCalendarView.this.setNavigationHeader(i2);
            }
        });
        AppCompatImageView appCompatImageView = this.imgVNavLeft;
        if (appCompatImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imgVNavLeft");
        }
        appCompatImageView.setOnClickListener(new View.OnClickListener() { // from class: com.archit.calendardaterangepicker.customviews.DateRangeCalendarView$setListeners$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int currentItem = DateRangeCalendarView.access$getVpCalendar$p(DateRangeCalendarView.this).getCurrentItem() - 1;
                if (currentItem > -1) {
                    DateRangeCalendarView.access$getVpCalendar$p(DateRangeCalendarView.this).setCurrentItem(currentItem);
                }
            }
        });
        AppCompatImageView appCompatImageView2 = this.imgVNavRight;
        if (appCompatImageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imgVNavRight");
        }
        appCompatImageView2.setOnClickListener(new View.OnClickListener() { // from class: com.archit.calendardaterangepicker.customviews.DateRangeCalendarView$setListeners$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int currentItem = DateRangeCalendarView.access$getVpCalendar$p(DateRangeCalendarView.this).getCurrentItem() + 1;
                if (currentItem < DateRangeCalendarView.access$getMDateRangeCalendarManager$p(DateRangeCalendarView.this).getVisibleMonthDataList().size()) {
                    DateRangeCalendarView.access$getVpCalendar$p(DateRangeCalendarView.this).setCurrentItem(currentItem);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003a, code lost:
        if (r7 == null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003c, code lost:
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("imgVNavRight");
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0061, code lost:
        if (r7 == null) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void setNavigationHeader(int i2) {
        AppCompatImageView appCompatImageView;
        AppCompatImageView appCompatImageView2 = this.imgVNavRight;
        if (appCompatImageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imgVNavRight");
        }
        appCompatImageView2.setVisibility(0);
        AppCompatImageView appCompatImageView3 = this.imgVNavLeft;
        if (appCompatImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imgVNavLeft");
        }
        appCompatImageView3.setVisibility(0);
        CalendarDateRangeManagerImpl calendarDateRangeManagerImpl = this.mDateRangeCalendarManager;
        if (calendarDateRangeManagerImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDateRangeCalendarManager");
        }
        if (calendarDateRangeManagerImpl.getVisibleMonthDataList().size() != 1) {
            if (i2 == 0) {
                appCompatImageView = this.imgVNavLeft;
                if (appCompatImageView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("imgVNavLeft");
                }
            } else {
                CalendarDateRangeManagerImpl calendarDateRangeManagerImpl2 = this.mDateRangeCalendarManager;
                if (calendarDateRangeManagerImpl2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mDateRangeCalendarManager");
                }
                if (i2 != calendarDateRangeManagerImpl2.getVisibleMonthDataList().size() - 1) {
                    return;
                }
                appCompatImageView = this.imgVNavRight;
            }
            appCompatImageView.setVisibility(4);
            return;
        }
        AppCompatImageView appCompatImageView4 = this.imgVNavLeft;
        if (appCompatImageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imgVNavLeft");
        }
        appCompatImageView4.setVisibility(4);
        appCompatImageView = this.imgVNavRight;
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

    @Override // com.archit.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    @Nullable
    public Calendar getEndDate() {
        CalendarDateRangeManagerImpl calendarDateRangeManagerImpl = this.mDateRangeCalendarManager;
        if (calendarDateRangeManagerImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDateRangeCalendarManager");
        }
        return calendarDateRangeManagerImpl.getMaxSelectedDate();
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    @Nullable
    public Calendar getStartDate() {
        CalendarDateRangeManagerImpl calendarDateRangeManagerImpl = this.mDateRangeCalendarManager;
        if (calendarDateRangeManagerImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDateRangeCalendarManager");
        }
        return calendarDateRangeManagerImpl.getMinSelectedDate();
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public boolean isEditable() {
        AdapterEventCalendarMonths adapterEventCalendarMonths = this.adapterEventCalendarMonths;
        if (adapterEventCalendarMonths == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterEventCalendarMonths");
        }
        return adapterEventCalendarMonths.isEditable();
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void resetAllSelectedViews() {
        CalendarDateRangeManagerImpl calendarDateRangeManagerImpl = this.mDateRangeCalendarManager;
        if (calendarDateRangeManagerImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDateRangeCalendarManager");
        }
        calendarDateRangeManagerImpl.resetSelectedDateRange();
        AdapterEventCalendarMonths adapterEventCalendarMonths = this.adapterEventCalendarMonths;
        if (adapterEventCalendarMonths == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterEventCalendarMonths");
        }
        adapterEventCalendarMonths.resetAllSelectedViews();
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void setCalendarListener(@NotNull CalendarListener calendarListener) {
        Intrinsics.checkParameterIsNotNull(calendarListener, "calendarListener");
        AdapterEventCalendarMonths adapterEventCalendarMonths = this.adapterEventCalendarMonths;
        if (adapterEventCalendarMonths == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterEventCalendarMonths");
        }
        adapterEventCalendarMonths.setCalendarListener(calendarListener);
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void setCurrentMonth(@NotNull Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, "calendar");
        ViewPager viewPager = this.vpCalendar;
        if (viewPager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vpCalendar");
        }
        CalendarDateRangeManagerImpl calendarDateRangeManagerImpl = this.mDateRangeCalendarManager;
        if (calendarDateRangeManagerImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDateRangeCalendarManager");
        }
        viewPager.setCurrentItem(calendarDateRangeManagerImpl.getMonthIndex(calendar));
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void setEditable(boolean z) {
        AdapterEventCalendarMonths adapterEventCalendarMonths = this.adapterEventCalendarMonths;
        if (adapterEventCalendarMonths == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterEventCalendarMonths");
        }
        adapterEventCalendarMonths.setEditable(z);
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void setFixedDaysSelection(int i2) {
        CalendarStyleAttributes calendarStyleAttributes = this.calendarStyleAttr;
        if (calendarStyleAttributes == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
        }
        calendarStyleAttributes.setFixedDaysSelectionNumber(i2);
        AdapterEventCalendarMonths adapterEventCalendarMonths = this.adapterEventCalendarMonths;
        if (adapterEventCalendarMonths == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterEventCalendarMonths");
        }
        adapterEventCalendarMonths.invalidateCalendar();
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void setFonts(@NotNull Typeface fonts) {
        Intrinsics.checkParameterIsNotNull(fonts, "fonts");
        CustomTextView customTextView = this.tvYearTitle;
        if (customTextView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvYearTitle");
        }
        customTextView.setTypeface(fonts);
        CalendarStyleAttributes calendarStyleAttributes = this.calendarStyleAttr;
        if (calendarStyleAttributes == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
        }
        calendarStyleAttributes.setFonts(fonts);
        AdapterEventCalendarMonths adapterEventCalendarMonths = this.adapterEventCalendarMonths;
        if (adapterEventCalendarMonths == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterEventCalendarMonths");
        }
        adapterEventCalendarMonths.invalidateCalendar();
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void setNavLeftImage(@NotNull Drawable leftDrawable) {
        Intrinsics.checkParameterIsNotNull(leftDrawable, "leftDrawable");
        AppCompatImageView appCompatImageView = this.imgVNavLeft;
        if (appCompatImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imgVNavLeft");
        }
        appCompatImageView.setImageDrawable(leftDrawable);
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void setNavRightImage(@NotNull Drawable rightDrawable) {
        Intrinsics.checkParameterIsNotNull(rightDrawable, "rightDrawable");
        AppCompatImageView appCompatImageView = this.imgVNavRight;
        if (appCompatImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imgVNavRight");
        }
        appCompatImageView.setImageDrawable(rightDrawable);
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void setSelectableDateRange(@NotNull Calendar startDate, @NotNull Calendar endDate) {
        Intrinsics.checkParameterIsNotNull(startDate, "startDate");
        Intrinsics.checkParameterIsNotNull(endDate, "endDate");
        CalendarDateRangeManagerImpl calendarDateRangeManagerImpl = this.mDateRangeCalendarManager;
        if (calendarDateRangeManagerImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDateRangeCalendarManager");
        }
        calendarDateRangeManagerImpl.setSelectableDateRange(startDate, endDate);
        AdapterEventCalendarMonths adapterEventCalendarMonths = this.adapterEventCalendarMonths;
        if (adapterEventCalendarMonths == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterEventCalendarMonths");
        }
        adapterEventCalendarMonths.notifyDataSetChanged();
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void setSelectedDateRange(@NotNull Calendar startDate, @NotNull Calendar endDate) {
        Intrinsics.checkParameterIsNotNull(startDate, "startDate");
        Intrinsics.checkParameterIsNotNull(endDate, "endDate");
        CalendarDateRangeManagerImpl calendarDateRangeManagerImpl = this.mDateRangeCalendarManager;
        if (calendarDateRangeManagerImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDateRangeCalendarManager");
        }
        calendarDateRangeManagerImpl.setSelectedDateRange(startDate, endDate);
        AdapterEventCalendarMonths adapterEventCalendarMonths = this.adapterEventCalendarMonths;
        if (adapterEventCalendarMonths == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterEventCalendarMonths");
        }
        adapterEventCalendarMonths.notifyDataSetChanged();
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void setVisibleMonthRange(@NotNull Calendar startMonth, @NotNull Calendar endMonth) {
        Intrinsics.checkParameterIsNotNull(startMonth, "startMonth");
        Intrinsics.checkParameterIsNotNull(endMonth, "endMonth");
        CalendarDateRangeManagerImpl calendarDateRangeManagerImpl = this.mDateRangeCalendarManager;
        if (calendarDateRangeManagerImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDateRangeCalendarManager");
        }
        calendarDateRangeManagerImpl.setVisibleMonths(startMonth, endMonth);
        AdapterEventCalendarMonths adapterEventCalendarMonths = this.adapterEventCalendarMonths;
        if (adapterEventCalendarMonths == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterEventCalendarMonths");
        }
        adapterEventCalendarMonths.notifyDataSetChanged();
        ViewPager viewPager = this.vpCalendar;
        if (viewPager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vpCalendar");
        }
        viewPager.setCurrentItem(0);
        setCalendarYearTitle(0);
        setNavigationHeader(0);
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void setWeekOffset(int i2) {
        CalendarStyleAttributes calendarStyleAttributes = this.calendarStyleAttr;
        if (calendarStyleAttributes == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
        }
        calendarStyleAttributes.setWeekOffset(i2);
        AdapterEventCalendarMonths adapterEventCalendarMonths = this.adapterEventCalendarMonths;
        if (adapterEventCalendarMonths == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterEventCalendarMonths");
        }
        adapterEventCalendarMonths.invalidateCalendar();
    }
}
