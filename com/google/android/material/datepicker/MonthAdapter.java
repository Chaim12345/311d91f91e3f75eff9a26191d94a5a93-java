package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.R;
import com.google.android.material.timepicker.TimeModel;
import java.util.Collection;
import java.util.Iterator;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class MonthAdapter extends BaseAdapter {

    /* renamed from: e  reason: collision with root package name */
    static final int f7274e = UtcDates.l().getMaximum(4);

    /* renamed from: a  reason: collision with root package name */
    final Month f7275a;

    /* renamed from: b  reason: collision with root package name */
    final DateSelector f7276b;

    /* renamed from: c  reason: collision with root package name */
    CalendarStyle f7277c;

    /* renamed from: d  reason: collision with root package name */
    final CalendarConstraints f7278d;
    private Collection<Long> previouslySelectedDates;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MonthAdapter(Month month, DateSelector dateSelector, CalendarConstraints calendarConstraints) {
        this.f7275a = month;
        this.f7276b = dateSelector;
        this.f7278d = calendarConstraints;
        this.previouslySelectedDates = dateSelector.getSelectedDays();
    }

    private void initializeStyles(Context context) {
        if (this.f7277c == null) {
            this.f7277c = new CalendarStyle(context);
        }
    }

    private boolean isSelected(long j2) {
        Iterator<Long> it = this.f7276b.getSelectedDays().iterator();
        while (it.hasNext()) {
            if (UtcDates.a(j2) == UtcDates.a(it.next().longValue())) {
                return true;
            }
        }
        return false;
    }

    private void updateSelectedState(@Nullable TextView textView, long j2) {
        CalendarItemStyle calendarItemStyle;
        if (textView == null) {
            return;
        }
        if (this.f7278d.getDateValidator().isValid(j2)) {
            textView.setEnabled(true);
            calendarItemStyle = isSelected(j2) ? this.f7277c.f7224b : UtcDates.k().getTimeInMillis() == j2 ? this.f7277c.f7225c : this.f7277c.f7223a;
        } else {
            textView.setEnabled(false);
            calendarItemStyle = this.f7277c.f7229g;
        }
        calendarItemStyle.d(textView);
    }

    private void updateSelectedStateForDate(MaterialCalendarGridView materialCalendarGridView, long j2) {
        if (Month.b(j2).equals(this.f7275a)) {
            updateSelectedState((TextView) materialCalendarGridView.getChildAt(materialCalendarGridView.getAdapter2().a(this.f7275a.f(j2)) - materialCalendarGridView.getFirstVisiblePosition()), j2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a(int i2) {
        return b() + (i2 - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b() {
        return this.f7275a.d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c(int i2) {
        return i2 % this.f7275a.f7271c == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d(int i2) {
        return (i2 + 1) % this.f7275a.f7271c == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int e() {
        return (this.f7275a.d() + this.f7275a.f7272d) - 1;
    }

    int f(int i2) {
        return (i2 - this.f7275a.d()) + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean g(int i2) {
        return i2 >= b() && i2 <= e();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f7275a.f7272d + b();
    }

    @Override // android.widget.Adapter
    @Nullable
    public Long getItem(int i2) {
        if (i2 < this.f7275a.d() || i2 > e()) {
            return null;
        }
        return Long.valueOf(this.f7275a.e(f(i2)));
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2 / this.f7275a.f7271c;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0080 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0081  */
    @Override // android.widget.Adapter
    @NonNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public TextView getView(int i2, @Nullable View view, @NonNull ViewGroup viewGroup) {
        Long item;
        initializeStyles(viewGroup.getContext());
        TextView textView = (TextView) view;
        if (view == null) {
            textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mtrl_calendar_day, viewGroup, false);
        }
        int b2 = i2 - b();
        if (b2 >= 0) {
            Month month = this.f7275a;
            if (b2 < month.f7272d) {
                int i3 = b2 + 1;
                textView.setTag(month);
                textView.setText(String.format(textView.getResources().getConfiguration().locale, TimeModel.NUMBER_FORMAT, Integer.valueOf(i3)));
                long e2 = this.f7275a.e(i3);
                textView.setContentDescription(this.f7275a.f7270b == Month.c().f7270b ? DateStrings.g(e2) : DateStrings.l(e2));
                textView.setVisibility(0);
                textView.setEnabled(true);
                item = getItem(i2);
                if (item != null) {
                    return textView;
                }
                updateSelectedState(textView, item.longValue());
                return textView;
            }
        }
        textView.setVisibility(8);
        textView.setEnabled(false);
        item = getItem(i2);
        if (item != null) {
        }
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    public void updateSelectedStates(MaterialCalendarGridView materialCalendarGridView) {
        for (Long l2 : this.previouslySelectedDates) {
            updateSelectedStateForDate(materialCalendarGridView, l2.longValue());
        }
        DateSelector dateSelector = this.f7276b;
        if (dateSelector != null) {
            for (Long l3 : dateSelector.getSelectedDays()) {
                updateSelectedStateForDate(materialCalendarGridView, l3.longValue());
            }
            this.previouslySelectedDates = this.f7276b.getSelectedDays();
        }
    }
}
