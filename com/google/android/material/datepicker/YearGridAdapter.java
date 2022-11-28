package com.google.android.material.datepicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.datepicker.MaterialCalendar;
import com.google.android.material.timepicker.TimeModel;
import java.util.Calendar;
import java.util.Locale;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class YearGridAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final MaterialCalendar<?> materialCalendar;

    /* loaded from: classes2.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        final TextView f7297a;

        ViewHolder(TextView textView) {
            super(textView);
            this.f7297a = textView;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public YearGridAdapter(MaterialCalendar materialCalendar) {
        this.materialCalendar = materialCalendar;
    }

    @NonNull
    private View.OnClickListener createYearClickListener(final int i2) {
        return new View.OnClickListener() { // from class: com.google.android.material.datepicker.YearGridAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                YearGridAdapter.this.materialCalendar.n(YearGridAdapter.this.materialCalendar.i().e(Month.a(i2, YearGridAdapter.this.materialCalendar.k().f7269a)));
                YearGridAdapter.this.materialCalendar.o(MaterialCalendar.CalendarSelector.DAY);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b(int i2) {
        return i2 - this.materialCalendar.i().i().f7270b;
    }

    int c(int i2) {
        return this.materialCalendar.i().i().f7270b + i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.materialCalendar.i().j();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i2) {
        int c2 = c(i2);
        String string = viewHolder.f7297a.getContext().getString(R.string.mtrl_picker_navigate_to_year_description);
        viewHolder.f7297a.setText(String.format(Locale.getDefault(), TimeModel.NUMBER_FORMAT, Integer.valueOf(c2)));
        viewHolder.f7297a.setContentDescription(String.format(string, Integer.valueOf(c2)));
        CalendarStyle j2 = this.materialCalendar.j();
        Calendar k2 = UtcDates.k();
        CalendarItemStyle calendarItemStyle = k2.get(1) == c2 ? j2.f7228f : j2.f7226d;
        for (Long l2 : this.materialCalendar.getDateSelector().getSelectedDays()) {
            k2.setTimeInMillis(l2.longValue());
            if (k2.get(1) == c2) {
                calendarItemStyle = j2.f7227e;
            }
        }
        calendarItemStyle.d(viewHolder.f7297a);
        viewHolder.f7297a.setOnClickListener(createYearClickListener(c2));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return new ViewHolder((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mtrl_calendar_year, viewGroup, false));
    }
}
