package com.archit.calendardaterangepicker.customviews;

import android.graphics.Typeface;
import android.view.View;
import com.archit.calendardaterangepicker.models.CalendarStyleAttributes;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0018\bf\u0018\u0000 02\u00020\u0001:\u0003012J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0006H&J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\bH&J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH&J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u000eH&J\b\u0010\u0011\u001a\u00020\u0004H&J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0012H&R\u001c\u0010\u001a\u001a\u00020\u00158&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010 \u001a\u00020\u001b8&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010#\u001a\u00020\u001b8&@&X¦\u000e¢\u0006\f\u001a\u0004\b!\u0010\u001d\"\u0004\b\"\u0010\u001fR\u001c\u0010&\u001a\u00020\u001b8&@&X¦\u000e¢\u0006\f\u001a\u0004\b$\u0010\u001d\"\u0004\b%\u0010\u001fR\u001c\u0010)\u001a\u00020\u001b8&@&X¦\u000e¢\u0006\f\u001a\u0004\b'\u0010\u001d\"\u0004\b(\u0010\u001fR\u001c\u0010,\u001a\u00020\u001b8&@&X¦\u000e¢\u0006\f\u001a\u0004\b*\u0010\u001d\"\u0004\b+\u0010\u001fR\u001c\u0010/\u001a\u00020\u001b8&@&X¦\u000e¢\u0006\f\u001a\u0004\b-\u0010\u001d\"\u0004\b.\u0010\u001f¨\u00063"}, d2 = {"Lcom/archit/calendardaterangepicker/customviews/DateView;", "", "Ljava/util/Calendar;", "date", "", "setDateTag", "", "setDateText", "Lcom/archit/calendardaterangepicker/models/CalendarStyleAttributes;", "attr", "setDateStyleAttributes", "Landroid/graphics/Typeface;", "typeface", "setTypeface", "Lcom/archit/calendardaterangepicker/customviews/DateView$DateState;", "dateState", "updateDateBackground", "refreshLayout", "Lcom/archit/calendardaterangepicker/customviews/DateView$OnDateClickListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setDateClickListener", "", "getDateTextSize", "()F", "setDateTextSize", "(F)V", "dateTextSize", "", "getStripColor", "()I", "setStripColor", "(I)V", "stripColor", "getSelectedDateCircleColor", "setSelectedDateCircleColor", "selectedDateCircleColor", "getSelectedDateColor", "setSelectedDateColor", "selectedDateColor", "getDefaultDateColor", "setDefaultDateColor", "defaultDateColor", "getDisableDateColor", "setDisableDateColor", "disableDateColor", "getRangeDateColor", "setRangeDateColor", "rangeDateColor", "Companion", "DateState", "OnDateClickListener", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public interface DateView {
    public static final Companion Companion = Companion.f4653a;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lcom/archit/calendardaterangepicker/customviews/DateView$Companion;", "", "Ljava/util/Calendar;", "cal", "", "getContainerKey", "<init>", "()V", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Companion {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ Companion f4653a = new Companion();

        private Companion() {
        }

        public final long getContainerKey(@NotNull Calendar cal) {
            Intrinsics.checkParameterIsNotNull(cal, "cal");
            String str = new SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault()).format(cal.getTime());
            Intrinsics.checkExpressionValueIsNotNull(str, "str");
            return Long.parseLong(str);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/archit/calendardaterangepicker/customviews/DateView$DateState;", "", "<init>", "(Ljava/lang/String;I)V", "HIDDEN", "DISABLE", "SELECTABLE", "START", "END", "MIDDLE", "START_END_SAME", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public enum DateState {
        HIDDEN,
        DISABLE,
        SELECTABLE,
        START,
        END,
        MIDDLE,
        START_END_SAME
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&¨\u0006\b"}, d2 = {"Lcom/archit/calendardaterangepicker/customviews/DateView$OnDateClickListener;", "", "Landroid/view/View;", "view", "Ljava/util/Calendar;", "selectedDate", "", "onDateClicked", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public interface OnDateClickListener {
        void onDateClicked(@NotNull View view, @NotNull Calendar calendar);
    }

    float getDateTextSize();

    int getDefaultDateColor();

    int getDisableDateColor();

    int getRangeDateColor();

    int getSelectedDateCircleColor();

    int getSelectedDateColor();

    int getStripColor();

    void refreshLayout();

    void setDateClickListener(@NotNull OnDateClickListener onDateClickListener);

    void setDateStyleAttributes(@NotNull CalendarStyleAttributes calendarStyleAttributes);

    void setDateTag(@NotNull Calendar calendar);

    void setDateText(@NotNull String str);

    void setDateTextSize(float f2);

    void setDefaultDateColor(int i2);

    void setDisableDateColor(int i2);

    void setRangeDateColor(int i2);

    void setSelectedDateCircleColor(int i2);

    void setSelectedDateColor(int i2);

    void setStripColor(int i2);

    void setTypeface(@NotNull Typeface typeface);

    void updateDateBackground(@NotNull DateState dateState);
}
