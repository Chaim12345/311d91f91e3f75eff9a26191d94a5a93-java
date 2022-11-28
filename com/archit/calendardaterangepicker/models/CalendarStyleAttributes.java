package com.archit.calendardaterangepicker.models;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import androidx.annotation.ColorInt;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\bf\u0018\u0000 ;2\u00020\u0001:\u0002;<R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00028&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u0003\u0010\u0004\"\u0004\b\u0005\u0010\u0006R\u0016\u0010\u000b\u001a\u00020\b8g@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001e\u0010\u0011\u001a\u0004\u0018\u00010\f8&@&X¦\u000e¢\u0006\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0013\u001a\u00020\b8g@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\nR\u0016\u0010\u0015\u001a\u00020\b8g@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\nR\u0016\u0010\u0017\u001a\u00020\b8g@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\nR\u0016\u0010\u0019\u001a\u00020\b8g@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\nR\u0016\u0010\u001b\u001a\u00020\b8g@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\nR\u0016\u0010\u001d\u001a\u00020\b8g@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\nR\u0016\u0010\u001f\u001a\u00020\b8g@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\nR\u0016\u0010#\u001a\u00020 8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0016\u0010%\u001a\u00020 8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\"R\u0016\u0010'\u001a\u00020 8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\"R\u0016\u0010)\u001a\u00020(8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*R\u001c\u0010.\u001a\u00020\b8&@&X¦\u000e¢\u0006\f\u001a\u0004\b+\u0010\n\"\u0004\b,\u0010-R\u001c\u0010/\u001a\u00020(8&@&X¦\u000e¢\u0006\f\u001a\u0004\b/\u0010*\"\u0004\b0\u00101R\u001c\u00107\u001a\u0002028&@&X¦\u000e¢\u0006\f\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u001c\u0010:\u001a\u00020\b8&@&X¦\u000e¢\u0006\f\u001a\u0004\b8\u0010\n\"\u0004\b9\u0010-¨\u0006="}, d2 = {"Lcom/archit/calendardaterangepicker/models/CalendarStyleAttributes;", "", "Landroid/graphics/Typeface;", "getFonts", "()Landroid/graphics/Typeface;", "setFonts", "(Landroid/graphics/Typeface;)V", "fonts", "", "getTitleColor", "()I", "titleColor", "Landroid/graphics/drawable/Drawable;", "getHeaderBg", "()Landroid/graphics/drawable/Drawable;", "setHeaderBg", "(Landroid/graphics/drawable/Drawable;)V", "headerBg", "getWeekColor", "weekColor", "getRangeStripColor", "rangeStripColor", "getSelectedDateCircleColor", "selectedDateCircleColor", "getSelectedDateColor", "selectedDateColor", "getDefaultDateColor", "defaultDateColor", "getDisableDateColor", "disableDateColor", "getRangeDateColor", "rangeDateColor", "", "getTextSizeTitle", "()F", "textSizeTitle", "getTextSizeWeek", "textSizeWeek", "getTextSizeDate", "textSizeDate", "", "isShouldEnabledTime", "()Z", "getWeekOffset", "setWeekOffset", "(I)V", "weekOffset", "isEditable", "setEditable", "(Z)V", "Lcom/archit/calendardaterangepicker/models/CalendarStyleAttributes$DateSelectionMode;", "getDateSelectionMode", "()Lcom/archit/calendardaterangepicker/models/CalendarStyleAttributes$DateSelectionMode;", "setDateSelectionMode", "(Lcom/archit/calendardaterangepicker/models/CalendarStyleAttributes$DateSelectionMode;)V", "dateSelectionMode", "getFixedDaysSelectionNumber", "setFixedDaysSelectionNumber", "fixedDaysSelectionNumber", "Companion", "DateSelectionMode", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public interface CalendarStyleAttributes {
    public static final Companion Companion = Companion.f4654a;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bR\u001c\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086D¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/archit/calendardaterangepicker/models/CalendarStyleAttributes$Companion;", "", "", "DEFAULT_FIXED_DAYS_SELECTION", "I", "getDEFAULT_FIXED_DAYS_SELECTION", "()I", "<init>", "()V", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Companion {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ Companion f4654a = new Companion();
        private static final int DEFAULT_FIXED_DAYS_SELECTION = 7;

        private Companion() {
        }

        public final int getDEFAULT_FIXED_DAYS_SELECTION() {
            return DEFAULT_FIXED_DAYS_SELECTION;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/archit/calendardaterangepicker/models/CalendarStyleAttributes$DateSelectionMode;", "", "<init>", "(Ljava/lang/String;I)V", "FREE_RANGE", "SINGLE", "FIXED_RANGE", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public enum DateSelectionMode {
        FREE_RANGE,
        SINGLE,
        FIXED_RANGE
    }

    @NotNull
    DateSelectionMode getDateSelectionMode();

    @ColorInt
    int getDefaultDateColor();

    @ColorInt
    int getDisableDateColor();

    int getFixedDaysSelectionNumber();

    @Nullable
    Typeface getFonts();

    @Nullable
    Drawable getHeaderBg();

    @ColorInt
    int getRangeDateColor();

    @ColorInt
    int getRangeStripColor();

    @ColorInt
    int getSelectedDateCircleColor();

    @ColorInt
    int getSelectedDateColor();

    float getTextSizeDate();

    float getTextSizeTitle();

    float getTextSizeWeek();

    @ColorInt
    int getTitleColor();

    @ColorInt
    int getWeekColor();

    int getWeekOffset();

    boolean isEditable();

    boolean isShouldEnabledTime();

    void setDateSelectionMode(@NotNull DateSelectionMode dateSelectionMode);

    void setEditable(boolean z);

    void setFixedDaysSelectionNumber(int i2);

    void setFonts(@Nullable Typeface typeface);

    void setHeaderBg(@Nullable Drawable drawable);

    void setWeekOffset(int i2);
}
