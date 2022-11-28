package com.archit.calendardaterangepicker.customviews;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import com.archit.calendardaterangepicker.R;
import com.archit.calendardaterangepicker.customviews.DateView;
import com.archit.calendardaterangepicker.models.CalendarStyleAttrImpl;
import com.archit.calendardaterangepicker.models.CalendarStyleAttributes;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B'\b\u0007\u0012\u0006\u0010U\u001a\u00020T\u0012\n\b\u0002\u0010W\u001a\u0004\u0018\u00010V\u0012\b\b\u0002\u0010X\u001a\u00020;¢\u0006\u0004\bY\u0010ZJ\b\u0010\u0004\u001a\u00020\u0003H\u0002J\b\u0010\u0005\u001a\u00020\u0003H\u0002J\b\u0010\u0006\u001a\u00020\u0003H\u0002J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0007H\u0002J\b\u0010\n\u001a\u00020\u0003H\u0002J\b\u0010\u000b\u001a\u00020\u0003H\u0002J\b\u0010\f\u001a\u00020\u0003H\u0002J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0016H\u0016J\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0007H\u0016J\b\u0010\u001a\u001a\u00020\u0003H\u0016J\u0010\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001bH\u0016R\u0016\u0010\u001f\u001a\u00020\u001e8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010\"\u001a\u00020!8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u0016\u0010%\u001a\u00020$8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&R\u0016\u0010(\u001a\u00020'8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u0018\u0010*\u001a\u0004\u0018\u00010\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010+R\u0016\u0010,\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010/\u001a\u00020.8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100R\u0016\u00102\u001a\u0002018\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103R\"\u00105\u001a\u0002048\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\"\u0010<\u001a\u00020;8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\"\u0010B\u001a\u00020;8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bB\u0010=\u001a\u0004\bC\u0010?\"\u0004\bD\u0010AR\"\u0010E\u001a\u00020;8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bE\u0010=\u001a\u0004\bF\u0010?\"\u0004\bG\u0010AR\"\u0010H\u001a\u00020;8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bH\u0010=\u001a\u0004\bI\u0010?\"\u0004\bJ\u0010AR\"\u0010K\u001a\u00020;8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bK\u0010=\u001a\u0004\bL\u0010?\"\u0004\bM\u0010AR\"\u0010N\u001a\u00020;8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bN\u0010=\u001a\u0004\bO\u0010?\"\u0004\bP\u0010AR\u0016\u0010R\u001a\u00020Q8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bR\u0010S¨\u0006["}, d2 = {"Lcom/archit/calendardaterangepicker/customviews/CustomDateView;", "Landroid/widget/FrameLayout;", "Lcom/archit/calendardaterangepicker/customviews/DateView;", "", "hideDayContainer", "disableDayContainer", "enabledDayContainer", "Lcom/archit/calendardaterangepicker/customviews/DateView$DateState;", "state", "makeAsSelectedDate", "setLeftFacedSelectedDate", "setRightFacedSelectedDate", "makeAsRangeDate", "", "date", "setDateText", "Lcom/archit/calendardaterangepicker/models/CalendarStyleAttributes;", "attr", "setDateStyleAttributes", "Landroid/graphics/Typeface;", "typeface", "setTypeface", "Ljava/util/Calendar;", "setDateTag", "dateState", "updateDateBackground", "refreshLayout", "Lcom/archit/calendardaterangepicker/customviews/DateView$OnDateClickListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setDateClickListener", "Lcom/archit/calendardaterangepicker/customviews/CustomTextView;", "tvDate", "Lcom/archit/calendardaterangepicker/customviews/CustomTextView;", "Landroid/view/View;", "strip", "Landroid/view/View;", "Ljava/text/SimpleDateFormat;", "simpleDateFormat", "Ljava/text/SimpleDateFormat;", "Landroid/graphics/PorterDuff$Mode;", "filterMode", "Landroid/graphics/PorterDuff$Mode;", "onDateClickListener", "Lcom/archit/calendardaterangepicker/customviews/DateView$OnDateClickListener;", "mDateState", "Lcom/archit/calendardaterangepicker/customviews/DateView$DateState;", "", "isRightToLeft", "Z", "Lcom/archit/calendardaterangepicker/models/CalendarStyleAttrImpl;", "defCalendarStyleAttr", "Lcom/archit/calendardaterangepicker/models/CalendarStyleAttrImpl;", "", "dateTextSize", "F", "getDateTextSize", "()F", "setDateTextSize", "(F)V", "", "defaultDateColor", "I", "getDefaultDateColor", "()I", "setDefaultDateColor", "(I)V", "disableDateColor", "getDisableDateColor", "setDisableDateColor", "selectedDateCircleColor", "getSelectedDateCircleColor", "setSelectedDateCircleColor", "selectedDateColor", "getSelectedDateColor", "setSelectedDateColor", "rangeDateColor", "getRangeDateColor", "setRangeDateColor", "stripColor", "getStripColor", "setStripColor", "Landroid/view/View$OnClickListener;", "mViewClickListener", "Landroid/view/View$OnClickListener;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class CustomDateView extends FrameLayout implements DateView {
    private HashMap _$_findViewCache;
    private float dateTextSize;
    private final CalendarStyleAttrImpl defCalendarStyleAttr;
    private int defaultDateColor;
    private int disableDateColor;
    private final PorterDuff.Mode filterMode;
    private final boolean isRightToLeft;
    private DateView.DateState mDateState;
    private final View.OnClickListener mViewClickListener;
    private DateView.OnDateClickListener onDateClickListener;
    private int rangeDateColor;
    private int selectedDateCircleColor;
    private int selectedDateColor;
    private final SimpleDateFormat simpleDateFormat;
    private final View strip;
    private int stripColor;
    private final CustomTextView tvDate;

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[DateView.DateState.values().length];
            $EnumSwitchMapping$0 = iArr;
            DateView.DateState dateState = DateView.DateState.START;
            iArr[dateState.ordinal()] = 1;
            DateView.DateState dateState2 = DateView.DateState.END;
            iArr[dateState2.ordinal()] = 2;
            DateView.DateState dateState3 = DateView.DateState.START_END_SAME;
            iArr[dateState3.ordinal()] = 3;
            iArr[DateView.DateState.HIDDEN.ordinal()] = 4;
            iArr[DateView.DateState.SELECTABLE.ordinal()] = 5;
            iArr[DateView.DateState.DISABLE.ordinal()] = 6;
            iArr[DateView.DateState.MIDDLE.ordinal()] = 7;
            int[] iArr2 = new int[DateView.DateState.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[dateState3.ordinal()] = 1;
            iArr2[dateState.ordinal()] = 2;
            iArr2[dateState2.ordinal()] = 3;
        }
    }

    @JvmOverloads
    public CustomDateView(@NotNull Context context) {
        this(context, null, 0, 6, null);
    }

    @JvmOverloads
    public CustomDateView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public CustomDateView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault());
        this.filterMode = PorterDuff.Mode.SRC_IN;
        this.isRightToLeft = getResources().getBoolean(R.bool.cdr_is_right_to_left);
        Object systemService = context.getSystemService("layout_inflater");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
        }
        ((LayoutInflater) systemService).inflate(R.layout.layout_calendar_day, (ViewGroup) this, true);
        View findViewById = findViewById(R.id.dayOfMonthText);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.dayOfMonthText)");
        this.tvDate = (CustomTextView) findViewById;
        View findViewById2 = findViewById(R.id.viewStrip);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "findViewById(R.id.viewStrip)");
        this.strip = findViewById2;
        this.mDateState = DateView.DateState.SELECTABLE;
        if (!isInEditMode()) {
            setDateStyleAttributes(CalendarStyleAttrImpl.Companion.getDefAttributes(context));
            updateDateBackground(this.mDateState);
        }
        CalendarStyleAttrImpl defAttributes = CalendarStyleAttrImpl.Companion.getDefAttributes(context);
        this.defCalendarStyleAttr = defAttributes;
        this.dateTextSize = defAttributes.getTextSizeDate();
        this.defaultDateColor = defAttributes.getDefaultDateColor();
        this.disableDateColor = defAttributes.getDisableDateColor();
        this.selectedDateCircleColor = defAttributes.getSelectedDateCircleColor();
        this.selectedDateColor = defAttributes.getSelectedDateColor();
        this.rangeDateColor = defAttributes.getRangeDateColor();
        this.stripColor = defAttributes.getRangeStripColor();
        this.mViewClickListener = new View.OnClickListener() { // from class: com.archit.calendardaterangepicker.customviews.CustomDateView$mViewClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                DateView.OnDateClickListener onDateClickListener;
                DateView.OnDateClickListener onDateClickListener2;
                SimpleDateFormat simpleDateFormat;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Object tag = it.getTag();
                if (tag == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Long");
                }
                long longValue = ((Long) tag).longValue();
                onDateClickListener = CustomDateView.this.onDateClickListener;
                if (onDateClickListener != null) {
                    Calendar selectedCal = Calendar.getInstance();
                    Date date = new Date();
                    try {
                        simpleDateFormat = CustomDateView.this.simpleDateFormat;
                        Date parse = simpleDateFormat.parse(String.valueOf(longValue));
                        Intrinsics.checkExpressionValueIsNotNull(parse, "simpleDateFormat.parse(key.toString())");
                        date = parse;
                    } catch (ParseException e2) {
                        e2.printStackTrace();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(selectedCal, "selectedCal");
                    selectedCal.setTime(date);
                    onDateClickListener2 = CustomDateView.this.onDateClickListener;
                    if (onDateClickListener2 != null) {
                        onDateClickListener2.onDateClicked(it, selectedCal);
                    }
                }
            }
        };
    }

    public /* synthetic */ CustomDateView(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    private final void disableDayContainer() {
        this.tvDate.setBackgroundColor(0);
        this.strip.setBackgroundColor(0);
        setBackgroundColor(0);
        this.tvDate.setTextColor(getDisableDateColor());
        setVisibility(0);
        setOnClickListener(null);
    }

    private final void enabledDayContainer() {
        this.tvDate.setBackgroundColor(0);
        this.strip.setBackgroundColor(0);
        setBackgroundColor(0);
        this.tvDate.setTextColor(getDefaultDateColor());
        setVisibility(0);
        setOnClickListener(this.mViewClickListener);
    }

    private final void hideDayContainer() {
        this.tvDate.setText("");
        this.tvDate.setBackgroundColor(0);
        this.strip.setBackgroundColor(0);
        setBackgroundColor(0);
        setVisibility(4);
        setOnClickListener(null);
    }

    private final void makeAsRangeDate() {
        this.tvDate.setBackgroundColor(0);
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.range_bg);
        if (drawable == null) {
            Intrinsics.throwNpe();
        }
        drawable.setColorFilter(new PorterDuffColorFilter(getStripColor(), this.filterMode));
        this.strip.setBackground(drawable);
        setBackgroundColor(0);
        this.tvDate.setTextColor(getRangeDateColor());
        setVisibility(0);
        ViewGroup.LayoutParams layoutParams = this.strip.getLayoutParams();
        if (layoutParams == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
        }
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
        layoutParams2.setMargins(0, 0, 0, 0);
        this.strip.setLayoutParams(layoutParams2);
        setOnClickListener(this.mViewClickListener);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0030, code lost:
        if (r4.isRightToLeft != false) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0032, code lost:
        setRightFacedSelectedDate();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0036, code lost:
        setLeftFacedSelectedDate();
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0014, code lost:
        if (r4.isRightToLeft != false) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void makeAsSelectedDate(DateView.DateState dateState) {
        int i2 = WhenMappings.$EnumSwitchMapping$1[dateState.ordinal()];
        if (i2 == 1) {
            ViewGroup.LayoutParams layoutParams = this.strip.getLayoutParams();
            if (layoutParams == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
            }
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            this.strip.setBackgroundColor(0);
            layoutParams2.setMargins(0, 0, 0, 0);
            this.strip.setLayoutParams(layoutParams2);
        } else if (i2 != 2) {
            if (i2 != 3) {
                throw new IllegalArgumentException(dateState + " is an invalid state.");
            }
        }
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.green_circle);
        if (drawable == null) {
            Intrinsics.throwNpe();
        }
        drawable.setColorFilter(new PorterDuffColorFilter(getSelectedDateCircleColor(), this.filterMode));
        this.tvDate.setBackground(drawable);
        setBackgroundColor(0);
        this.tvDate.setTextColor(getSelectedDateColor());
        setVisibility(0);
        setOnClickListener(this.mViewClickListener);
    }

    private final void setLeftFacedSelectedDate() {
        ViewGroup.LayoutParams layoutParams = this.strip.getLayoutParams();
        if (layoutParams == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
        }
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.range_bg_left);
        if (drawable == null) {
            Intrinsics.throwNpe();
        }
        drawable.setColorFilter(new PorterDuffColorFilter(getStripColor(), this.filterMode));
        this.strip.setBackground(drawable);
        layoutParams2.setMargins(20, 0, 0, 0);
        this.strip.setLayoutParams(layoutParams2);
    }

    private final void setRightFacedSelectedDate() {
        ViewGroup.LayoutParams layoutParams = this.strip.getLayoutParams();
        if (layoutParams == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
        }
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.range_bg_right);
        if (drawable == null) {
            Intrinsics.throwNpe();
        }
        drawable.setColorFilter(new PorterDuffColorFilter(getStripColor(), this.filterMode));
        this.strip.setBackground(drawable);
        layoutParams2.setMargins(0, 0, 20, 0);
        this.strip.setLayoutParams(layoutParams2);
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

    @Override // com.archit.calendardaterangepicker.customviews.DateView
    public float getDateTextSize() {
        return this.dateTextSize;
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateView
    public int getDefaultDateColor() {
        return this.defaultDateColor;
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateView
    public int getDisableDateColor() {
        return this.disableDateColor;
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateView
    public int getRangeDateColor() {
        return this.rangeDateColor;
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateView
    public int getSelectedDateCircleColor() {
        return this.selectedDateCircleColor;
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateView
    public int getSelectedDateColor() {
        return this.selectedDateColor;
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateView
    public int getStripColor() {
        return this.stripColor;
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateView
    public void refreshLayout() {
        this.tvDate.setTextSize(0, getDateTextSize());
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateView
    public void setDateClickListener(@NotNull DateView.OnDateClickListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.onDateClickListener = listener;
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateView
    public void setDateStyleAttributes(@NotNull CalendarStyleAttributes attr) {
        Intrinsics.checkParameterIsNotNull(attr, "attr");
        setDisableDateColor(attr.getDisableDateColor());
        setDefaultDateColor(attr.getDefaultDateColor());
        setSelectedDateCircleColor(attr.getSelectedDateCircleColor());
        setSelectedDateColor(attr.getSelectedDateColor());
        setStripColor(attr.getRangeStripColor());
        setRangeDateColor(attr.getRangeDateColor());
        this.tvDate.setTextSize(attr.getTextSizeDate());
        refreshLayout();
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateView
    public void setDateTag(@NotNull Calendar date) {
        Intrinsics.checkParameterIsNotNull(date, "date");
        setTag(Long.valueOf(DateView.Companion.getContainerKey(date)));
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateView
    public void setDateText(@NotNull String date) {
        Intrinsics.checkParameterIsNotNull(date, "date");
        this.tvDate.setText(date);
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateView
    public void setDateTextSize(float f2) {
        this.dateTextSize = f2;
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateView
    public void setDefaultDateColor(int i2) {
        this.defaultDateColor = i2;
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateView
    public void setDisableDateColor(int i2) {
        this.disableDateColor = i2;
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateView
    public void setRangeDateColor(int i2) {
        this.rangeDateColor = i2;
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateView
    public void setSelectedDateCircleColor(int i2) {
        this.selectedDateCircleColor = i2;
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateView
    public void setSelectedDateColor(int i2) {
        this.selectedDateColor = i2;
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateView
    public void setStripColor(int i2) {
        this.stripColor = i2;
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateView
    public void setTypeface(@NotNull Typeface typeface) {
        Intrinsics.checkParameterIsNotNull(typeface, "typeface");
        this.tvDate.setTypeface(typeface);
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateView
    public void updateDateBackground(@NotNull DateView.DateState dateState) {
        Intrinsics.checkParameterIsNotNull(dateState, "dateState");
        this.mDateState = dateState;
        switch (WhenMappings.$EnumSwitchMapping$0[dateState.ordinal()]) {
            case 1:
            case 2:
            case 3:
                makeAsSelectedDate(dateState);
                return;
            case 4:
                hideDayContainer();
                return;
            case 5:
                enabledDayContainer();
                return;
            case 6:
                disableDayContainer();
                return;
            case 7:
                makeAsRangeDate();
                return;
            default:
                throw new IllegalArgumentException(dateState + " is an invalid state.");
        }
    }
}
