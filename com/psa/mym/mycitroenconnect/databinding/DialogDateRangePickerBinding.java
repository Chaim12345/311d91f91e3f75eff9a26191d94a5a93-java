package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class DialogDateRangePickerBinding implements ViewBinding {
    @NonNull
    public final DateRangeCalendarView dateRangeCalendar;
    @NonNull
    public final AppCompatImageView ivClose;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final AppCompatTextView tvEndDate;
    @NonNull
    public final AppCompatTextView tvSave;
    @NonNull
    public final AppCompatTextView tvStartDate;
    @NonNull
    public final AppCompatTextView tvTitle;

    private DialogDateRangePickerBinding(@NonNull FrameLayout frameLayout, @NonNull DateRangeCalendarView dateRangeCalendarView, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4) {
        this.rootView = frameLayout;
        this.dateRangeCalendar = dateRangeCalendarView;
        this.ivClose = appCompatImageView;
        this.tvEndDate = appCompatTextView;
        this.tvSave = appCompatTextView2;
        this.tvStartDate = appCompatTextView3;
        this.tvTitle = appCompatTextView4;
    }

    @NonNull
    public static DialogDateRangePickerBinding bind(@NonNull View view) {
        int i2 = R.id.dateRangeCalendar;
        DateRangeCalendarView dateRangeCalendarView = (DateRangeCalendarView) ViewBindings.findChildViewById(view, R.id.dateRangeCalendar);
        if (dateRangeCalendarView != null) {
            i2 = R.id.ivClose;
            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivClose);
            if (appCompatImageView != null) {
                i2 = R.id.tvEndDate;
                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvEndDate);
                if (appCompatTextView != null) {
                    i2 = R.id.tvSave;
                    AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSave);
                    if (appCompatTextView2 != null) {
                        i2 = R.id.tvStartDate;
                        AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvStartDate);
                        if (appCompatTextView3 != null) {
                            i2 = R.id.tvTitle;
                            AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTitle);
                            if (appCompatTextView4 != null) {
                                return new DialogDateRangePickerBinding((FrameLayout) view, dateRangeCalendarView, appCompatImageView, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DialogDateRangePickerBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DialogDateRangePickerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_date_range_picker, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public FrameLayout getRoot() {
        return this.rootView;
    }
}
