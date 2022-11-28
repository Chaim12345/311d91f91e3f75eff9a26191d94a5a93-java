package com.archit.calendardaterangepicker.models;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.core.content.ContextCompat;
import com.archit.calendardaterangepicker.R;
import com.archit.calendardaterangepicker.models.CalendarStyleAttributes;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 L2\u00020\u0001:\u0001LB\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\bJ\u0010KJ\u001a\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002R$\u0010\t\u001a\u0004\u0018\u00010\b8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f8\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R$\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f8\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b\u001c\u0010\u0012\u001a\u0004\b\u001d\u0010\u0014R$\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f8\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b\u001e\u0010\u0012\u001a\u0004\b\u001f\u0010\u0014R$\u0010 \u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f8\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b \u0010\u0012\u001a\u0004\b!\u0010\u0014R$\u0010\"\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f8\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b\"\u0010\u0012\u001a\u0004\b#\u0010\u0014R$\u0010$\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f8\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b$\u0010\u0012\u001a\u0004\b%\u0010\u0014R$\u0010&\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f8\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b&\u0010\u0012\u001a\u0004\b'\u0010\u0014R$\u0010(\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f8\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b(\u0010\u0012\u001a\u0004\b)\u0010\u0014R$\u0010+\u001a\u00020*2\u0006\u0010\u0010\u001a\u00020*8\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.R$\u0010/\u001a\u00020*2\u0006\u0010\u0010\u001a\u00020*8\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b/\u0010,\u001a\u0004\b0\u0010.R$\u00101\u001a\u00020*2\u0006\u0010\u0010\u001a\u00020*8\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b1\u0010,\u001a\u0004\b2\u0010.R\"\u00104\u001a\u0002038\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b4\u00106\"\u0004\b7\u00108R*\u00109\u001a\u00020\u000f2\u0006\u00109\u001a\u00020\u000f8\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\b9\u0010\u0012\u001a\u0004\b:\u0010\u0014\"\u0004\b;\u0010<R\"\u0010=\u001a\u0002038\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b=\u00105\u001a\u0004\b=\u00106\"\u0004\b>\u00108R\"\u0010@\u001a\u00020?8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b@\u0010A\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER*\u0010G\u001a\u00020\u000f2\u0006\u0010F\u001a\u00020\u000f8\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\bG\u0010\u0012\u001a\u0004\bH\u0010\u0014\"\u0004\bI\u0010<¨\u0006M"}, d2 = {"Lcom/archit/calendardaterangepicker/models/CalendarStyleAttrImpl;", "Lcom/archit/calendardaterangepicker/models/CalendarStyleAttributes;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attributeSet", "", "setAttributes", "Landroid/graphics/Typeface;", "fonts", "Landroid/graphics/Typeface;", "getFonts", "()Landroid/graphics/Typeface;", "setFonts", "(Landroid/graphics/Typeface;)V", "", "<set-?>", "titleColor", "I", "getTitleColor", "()I", "Landroid/graphics/drawable/Drawable;", "headerBg", "Landroid/graphics/drawable/Drawable;", "getHeaderBg", "()Landroid/graphics/drawable/Drawable;", "setHeaderBg", "(Landroid/graphics/drawable/Drawable;)V", "weekColor", "getWeekColor", "rangeStripColor", "getRangeStripColor", "selectedDateCircleColor", "getSelectedDateCircleColor", "selectedDateColor", "getSelectedDateColor", "defaultDateColor", "getDefaultDateColor", "disableDateColor", "getDisableDateColor", "rangeDateColor", "getRangeDateColor", "", "textSizeTitle", "F", "getTextSizeTitle", "()F", "textSizeWeek", "getTextSizeWeek", "textSizeDate", "getTextSizeDate", "", "isShouldEnabledTime", "Z", "()Z", "setShouldEnabledTime", "(Z)V", "weekOffset", "getWeekOffset", "setWeekOffset", "(I)V", "isEditable", "setEditable", "Lcom/archit/calendardaterangepicker/models/CalendarStyleAttributes$DateSelectionMode;", "dateSelectionMode", "Lcom/archit/calendardaterangepicker/models/CalendarStyleAttributes$DateSelectionMode;", "getDateSelectionMode", "()Lcom/archit/calendardaterangepicker/models/CalendarStyleAttributes$DateSelectionMode;", "setDateSelectionMode", "(Lcom/archit/calendardaterangepicker/models/CalendarStyleAttributes$DateSelectionMode;)V", "value", "fixedDaysSelectionNumber", "getFixedDaysSelectionNumber", "setFixedDaysSelectionNumber", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Companion", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class CalendarStyleAttrImpl implements CalendarStyleAttributes {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private CalendarStyleAttributes.DateSelectionMode dateSelectionMode;
    private int defaultDateColor;
    private int disableDateColor;
    private int fixedDaysSelectionNumber;
    @Nullable
    private Typeface fonts;
    @Nullable
    private Drawable headerBg;
    private boolean isEditable;
    private boolean isShouldEnabledTime;
    private int rangeDateColor;
    private int rangeStripColor;
    private int selectedDateCircleColor;
    private int selectedDateColor;
    private float textSizeDate;
    private float textSizeTitle;
    private float textSizeWeek;
    private int titleColor;
    private int weekColor;
    private int weekOffset;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lcom/archit/calendardaterangepicker/models/CalendarStyleAttrImpl$Companion;", "", "Landroid/content/Context;", "context", "Lcom/archit/calendardaterangepicker/models/CalendarStyleAttrImpl;", "getDefAttributes", "<init>", "()V", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final CalendarStyleAttrImpl getDefAttributes(@NotNull Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            CalendarStyleAttrImpl calendarStyleAttrImpl = new CalendarStyleAttrImpl(context, null, 2, null);
            calendarStyleAttrImpl.textSizeTitle = context.getResources().getDimension(R.dimen.text_size_title);
            calendarStyleAttrImpl.textSizeWeek = context.getResources().getDimension(R.dimen.text_size_week);
            calendarStyleAttrImpl.textSizeDate = context.getResources().getDimension(R.dimen.text_size_date);
            calendarStyleAttrImpl.weekColor = ContextCompat.getColor(context, R.color.week_color);
            calendarStyleAttrImpl.rangeStripColor = ContextCompat.getColor(context, R.color.range_bg_color);
            calendarStyleAttrImpl.selectedDateCircleColor = ContextCompat.getColor(context, R.color.selected_date_circle_color);
            calendarStyleAttrImpl.selectedDateColor = ContextCompat.getColor(context, R.color.selected_date_color);
            calendarStyleAttrImpl.defaultDateColor = ContextCompat.getColor(context, R.color.default_date_color);
            calendarStyleAttrImpl.rangeDateColor = ContextCompat.getColor(context, R.color.range_date_color);
            calendarStyleAttrImpl.disableDateColor = ContextCompat.getColor(context, R.color.disable_date_color);
            return calendarStyleAttrImpl;
        }
    }

    public CalendarStyleAttrImpl(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.titleColor = ContextCompat.getColor(context, R.color.title_color);
        this.weekColor = ContextCompat.getColor(context, R.color.week_color);
        this.rangeStripColor = ContextCompat.getColor(context, R.color.range_bg_color);
        this.selectedDateCircleColor = ContextCompat.getColor(context, R.color.selected_date_circle_color);
        this.selectedDateColor = ContextCompat.getColor(context, R.color.selected_date_color);
        this.defaultDateColor = ContextCompat.getColor(context, R.color.default_date_color);
        this.disableDateColor = ContextCompat.getColor(context, R.color.disable_date_color);
        this.rangeDateColor = ContextCompat.getColor(context, R.color.range_date_color);
        this.textSizeTitle = context.getResources().getDimension(R.dimen.text_size_title);
        this.textSizeWeek = context.getResources().getDimension(R.dimen.text_size_week);
        this.textSizeDate = context.getResources().getDimension(R.dimen.text_size_date);
        this.isEditable = true;
        this.dateSelectionMode = CalendarStyleAttributes.DateSelectionMode.FREE_RANGE;
        this.fixedDaysSelectionNumber = CalendarStyleAttributes.Companion.getDEFAULT_FIXED_DAYS_SELECTION();
        if (attributeSet != null) {
            setAttributes(context, attributeSet);
        }
    }

    public /* synthetic */ CalendarStyleAttrImpl(Context context, AttributeSet attributeSet, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }

    private final void setAttributes(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DateRangeMonthView, 0, 0);
            try {
                this.titleColor = obtainStyledAttributes.getColor(R.styleable.DateRangeMonthView_title_color, getTitleColor());
                setHeaderBg(obtainStyledAttributes.getDrawable(R.styleable.DateRangeMonthView_header_bg));
                this.weekColor = obtainStyledAttributes.getColor(R.styleable.DateRangeMonthView_week_color, getWeekColor());
                this.rangeStripColor = obtainStyledAttributes.getColor(R.styleable.DateRangeMonthView_range_color, getRangeStripColor());
                this.selectedDateCircleColor = obtainStyledAttributes.getColor(R.styleable.DateRangeMonthView_selected_date_circle_color, getSelectedDateCircleColor());
                setShouldEnabledTime(obtainStyledAttributes.getBoolean(R.styleable.DateRangeMonthView_enable_time_selection, false));
                setEditable(obtainStyledAttributes.getBoolean(R.styleable.DateRangeMonthView_editable, true));
                this.textSizeTitle = obtainStyledAttributes.getDimension(R.styleable.DateRangeMonthView_text_size_title, getTextSizeTitle());
                this.textSizeWeek = obtainStyledAttributes.getDimension(R.styleable.DateRangeMonthView_text_size_week, getTextSizeWeek());
                this.textSizeDate = obtainStyledAttributes.getDimension(R.styleable.DateRangeMonthView_text_size_date, getTextSizeDate());
                this.selectedDateColor = obtainStyledAttributes.getColor(R.styleable.DateRangeMonthView_selected_date_color, getSelectedDateColor());
                this.defaultDateColor = obtainStyledAttributes.getColor(R.styleable.DateRangeMonthView_default_date_color, getDefaultDateColor());
                this.rangeDateColor = obtainStyledAttributes.getColor(R.styleable.DateRangeMonthView_range_date_color, getRangeDateColor());
                this.disableDateColor = obtainStyledAttributes.getColor(R.styleable.DateRangeMonthView_disable_date_color, getDisableDateColor());
                setWeekOffset(obtainStyledAttributes.getColor(R.styleable.DateRangeMonthView_week_offset, 0));
                setDateSelectionMode(CalendarStyleAttributes.DateSelectionMode.values()[obtainStyledAttributes.getInt(R.styleable.DateRangeMonthView_date_selection_mode, 0)]);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    @NotNull
    public CalendarStyleAttributes.DateSelectionMode getDateSelectionMode() {
        return this.dateSelectionMode;
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    public int getDefaultDateColor() {
        return this.defaultDateColor;
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    public int getDisableDateColor() {
        return this.disableDateColor;
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    public int getFixedDaysSelectionNumber() {
        return this.fixedDaysSelectionNumber;
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    @Nullable
    public Typeface getFonts() {
        return this.fonts;
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    @Nullable
    public Drawable getHeaderBg() {
        return this.headerBg;
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    public int getRangeDateColor() {
        return this.rangeDateColor;
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    public int getRangeStripColor() {
        return this.rangeStripColor;
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    public int getSelectedDateCircleColor() {
        return this.selectedDateCircleColor;
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    public int getSelectedDateColor() {
        return this.selectedDateColor;
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    public float getTextSizeDate() {
        return this.textSizeDate;
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    public float getTextSizeTitle() {
        return this.textSizeTitle;
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    public float getTextSizeWeek() {
        return this.textSizeWeek;
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    public int getTitleColor() {
        return this.titleColor;
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    public int getWeekColor() {
        return this.weekColor;
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    public int getWeekOffset() {
        return this.weekOffset;
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    public boolean isEditable() {
        return this.isEditable;
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    public boolean isShouldEnabledTime() {
        return this.isShouldEnabledTime;
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    public void setDateSelectionMode(@NotNull CalendarStyleAttributes.DateSelectionMode dateSelectionMode) {
        Intrinsics.checkParameterIsNotNull(dateSelectionMode, "<set-?>");
        this.dateSelectionMode = dateSelectionMode;
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    public void setEditable(boolean z) {
        this.isEditable = z;
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    public void setFixedDaysSelectionNumber(int i2) {
        if (getDateSelectionMode() != CalendarStyleAttributes.DateSelectionMode.FIXED_RANGE) {
            throw new InvalidCalendarAttributeException("Selected date selection mode is not `fixed_range` for `date_selection_mode` attribute in layout.");
        }
        if (i2 < 0 || i2 > 365) {
            throw new InvalidCalendarAttributeException("Fixed days can be between 0 to 365.");
        }
        this.fixedDaysSelectionNumber = i2;
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    public void setFonts(@Nullable Typeface typeface) {
        this.fonts = typeface;
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    public void setHeaderBg(@Nullable Drawable drawable) {
        this.headerBg = drawable;
    }

    public void setShouldEnabledTime(boolean z) {
        this.isShouldEnabledTime = z;
    }

    @Override // com.archit.calendardaterangepicker.models.CalendarStyleAttributes
    public void setWeekOffset(int i2) {
        if (i2 < 0 || i2 > 6) {
            throw new InvalidCalendarAttributeException("Week offset can only be between 0 to 6. 0->Sun, 1->Mon, 2->Tue, 3->Wed, 4->Thu, 5->Fri, 6->Sat");
        }
        this.weekOffset = i2;
    }
}
