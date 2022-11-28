package com.archit.calendardaterangepicker.customviews;

import android.content.Context;
import android.view.View;
import com.archit.calendardaterangepicker.R;
import com.archit.calendardaterangepicker.customviews.DateView;
import com.archit.calendardaterangepicker.timepicker.AwesomeTimePickerDialog;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\b"}, d2 = {"com/archit/calendardaterangepicker/customviews/DateRangeMonthView$mOnDateClickListener$1", "Lcom/archit/calendardaterangepicker/customviews/DateView$OnDateClickListener;", "Landroid/view/View;", "view", "Ljava/util/Calendar;", "selectedDate", "", "onDateClicked", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class DateRangeMonthView$mOnDateClickListener$1 implements DateView.OnDateClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ DateRangeMonthView f4650a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DateRangeMonthView$mOnDateClickListener$1(DateRangeMonthView dateRangeMonthView) {
        this.f4650a = dateRangeMonthView;
    }

    @Override // com.archit.calendardaterangepicker.customviews.DateView.OnDateClickListener
    public void onDateClicked(@NotNull View view, @NotNull final Calendar selectedDate) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(selectedDate, "selectedDate");
        if (DateRangeMonthView.access$getCalendarStyleAttr$p(this.f4650a).isEditable()) {
            if (!DateRangeMonthView.access$getCalendarStyleAttr$p(this.f4650a).isShouldEnabledTime()) {
                this.f4650a.setSelectedDate(selectedDate);
                return;
            }
            Context context = this.f4650a.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            String string = this.f4650a.getContext().getString(R.string.select_time);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(string.select_time)");
            new AwesomeTimePickerDialog(context, string, new AwesomeTimePickerDialog.TimePickerCallback() { // from class: com.archit.calendardaterangepicker.customviews.DateRangeMonthView$mOnDateClickListener$1$onDateClicked$awesomeTimePickerDialog$1
                @Override // com.archit.calendardaterangepicker.timepicker.AwesomeTimePickerDialog.TimePickerCallback
                public void onCancel() {
                    DateRangeMonthView$mOnDateClickListener$1.this.f4650a.resetAllSelectedViews();
                }

                @Override // com.archit.calendardaterangepicker.timepicker.AwesomeTimePickerDialog.TimePickerCallback
                public void onTimeSelected(int i2, int i3) {
                    selectedDate.set(10, i2);
                    selectedDate.set(12, i3);
                    DateRangeMonthView$mOnDateClickListener$1.this.f4650a.setSelectedDate(selectedDate);
                }
            }).showDialog();
        }
    }
}
