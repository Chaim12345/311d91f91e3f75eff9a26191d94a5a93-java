package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.datepicker.MaterialCalendar;
/* loaded from: classes2.dex */
class MonthsPagerAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NonNull
    private final CalendarConstraints calendarConstraints;
    private final Context context;
    private final DateSelector<?> dateSelector;
    private final int itemHeight;
    private final MaterialCalendar.OnDayClickListener onDayClickListener;

    /* loaded from: classes2.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        final TextView f7281a;

        /* renamed from: b  reason: collision with root package name */
        final MaterialCalendarGridView f7282b;

        ViewHolder(@NonNull LinearLayout linearLayout, boolean z) {
            super(linearLayout);
            TextView textView = (TextView) linearLayout.findViewById(R.id.month_title);
            this.f7281a = textView;
            ViewCompat.setAccessibilityHeading(textView, true);
            this.f7282b = (MaterialCalendarGridView) linearLayout.findViewById(R.id.month_grid);
            if (z) {
                return;
            }
            textView.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MonthsPagerAdapter(@NonNull Context context, DateSelector dateSelector, @NonNull CalendarConstraints calendarConstraints, MaterialCalendar.OnDayClickListener onDayClickListener) {
        Month i2 = calendarConstraints.i();
        Month f2 = calendarConstraints.f();
        Month h2 = calendarConstraints.h();
        if (i2.compareTo(h2) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        }
        if (h2.compareTo(f2) > 0) {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
        int l2 = MonthAdapter.f7274e * MaterialCalendar.l(context);
        int l3 = MaterialDatePicker.n(context) ? MaterialCalendar.l(context) : 0;
        this.context = context;
        this.itemHeight = l2 + l3;
        this.calendarConstraints = calendarConstraints;
        this.dateSelector = dateSelector;
        this.onDayClickListener = onDayClickListener;
        setHasStableIds(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public Month b(int i2) {
        return this.calendarConstraints.i().i(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public CharSequence c(int i2) {
        return b(i2).g(this.context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int d(@NonNull Month month) {
        return this.calendarConstraints.i().j(month);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.calendarConstraints.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i2) {
        return this.calendarConstraints.i().i(i2).h();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i2) {
        Month i3 = this.calendarConstraints.i().i(i2);
        viewHolder.f7281a.setText(i3.g(viewHolder.itemView.getContext()));
        final MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) viewHolder.f7282b.findViewById(R.id.month_grid);
        if (materialCalendarGridView.getAdapter2() == null || !i3.equals(materialCalendarGridView.getAdapter2().f7275a)) {
            MonthAdapter monthAdapter = new MonthAdapter(i3, this.dateSelector, this.calendarConstraints);
            materialCalendarGridView.setNumColumns(i3.f7271c);
            materialCalendarGridView.setAdapter((ListAdapter) monthAdapter);
        } else {
            materialCalendarGridView.invalidate();
            materialCalendarGridView.getAdapter2().updateSelectedStates(materialCalendarGridView);
        }
        materialCalendarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.google.android.material.datepicker.MonthsPagerAdapter.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i4, long j2) {
                if (materialCalendarGridView.getAdapter2().g(i4)) {
                    MonthsPagerAdapter.this.onDayClickListener.onDayClick(materialCalendarGridView.getAdapter2().getItem(i4).longValue());
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mtrl_calendar_month_labeled, viewGroup, false);
        if (MaterialDatePicker.n(viewGroup.getContext())) {
            linearLayout.setLayoutParams(new RecyclerView.LayoutParams(-1, this.itemHeight));
            return new ViewHolder(linearLayout, true);
        }
        return new ViewHolder(linearLayout, false);
    }
}
