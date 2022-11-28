package com.psa.mym.mycitroenconnect.controller.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.DialogFragment;
import com.archit.calendardaterangepicker.customviews.CalendarListener;
import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class DateRangePickerDialog extends DialogFragment implements View.OnClickListener, CalendarListener {
    @NotNull
    public static final String ARG_END_DATE = "end_date";
    @NotNull
    public static final String ARG_START_DATE = "start_date";
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    private static final String TAG = Reflection.getOrCreateKotlinClass(DateRangePickerDialog.class).getSimpleName();
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    private Calendar endDate;
    @Nullable
    private OnDateSelectListener onDateSelectListener;
    @Nullable
    private Calendar startDate;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final String getTAG() {
            return DateRangePickerDialog.TAG;
        }

        @JvmStatic
        @NotNull
        public final DateRangePickerDialog newInstance(@NotNull Calendar startDate, @NotNull Calendar endDate) {
            Intrinsics.checkNotNullParameter(startDate, "startDate");
            Intrinsics.checkNotNullParameter(endDate, "endDate");
            DateRangePickerDialog dateRangePickerDialog = new DateRangePickerDialog();
            Bundle bundle = new Bundle();
            bundle.putSerializable("start_date", startDate);
            bundle.putSerializable("end_date", endDate);
            dateRangePickerDialog.setArguments(bundle);
            return dateRangePickerDialog;
        }
    }

    private final void initCalenderView() {
        DateRangeCalendarView dateRangeCalendarView;
        Calendar calendar;
        Typeface font = ResourcesCompat.getFont(requireContext(), R.font.ubuntu_regular);
        if (font != null) {
            ((DateRangeCalendarView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.dateRangeCalendar)).setFonts(font);
        }
        Drawable drawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_red_arrow_left);
        if (drawable != null) {
            ((DateRangeCalendarView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.dateRangeCalendar)).setNavLeftImage(drawable);
        }
        Drawable drawable2 = ContextCompat.getDrawable(requireContext(), R.drawable.ic_red_arrow_right);
        if (drawable2 != null) {
            ((DateRangeCalendarView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.dateRangeCalendar)).setNavRightImage(drawable2);
        }
        Calendar startDateRange = Calendar.getInstance();
        startDateRange.set(5, 1);
        startDateRange.set(2, 0);
        startDateRange.set(1, 2001);
        int i2 = com.psa.mym.mycitroenconnect.R.id.dateRangeCalendar;
        Intrinsics.checkNotNullExpressionValue(startDateRange, "startDateRange");
        Calendar calendar2 = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
        ((DateRangeCalendarView) _$_findCachedViewById(i2)).setVisibleMonthRange(startDateRange, calendar2);
        Calendar calendar3 = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar3, "getInstance()");
        ((DateRangeCalendarView) _$_findCachedViewById(i2)).setSelectableDateRange(startDateRange, calendar3);
        if (this.startDate == null || this.endDate == null) {
            Calendar calendar4 = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar4, "getInstance()");
            Calendar calendar5 = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar5, "getInstance()");
            ((DateRangeCalendarView) _$_findCachedViewById(i2)).setSelectedDateRange(calendar4, calendar5);
            dateRangeCalendarView = (DateRangeCalendarView) _$_findCachedViewById(i2);
            calendar = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        } else {
            Calendar calendar6 = this.startDate;
            Intrinsics.checkNotNull(calendar6);
            Calendar calendar7 = this.endDate;
            Intrinsics.checkNotNull(calendar7);
            ((DateRangeCalendarView) _$_findCachedViewById(i2)).setSelectedDateRange(calendar6, calendar7);
            dateRangeCalendarView = (DateRangeCalendarView) _$_findCachedViewById(i2);
            calendar = this.startDate;
            Intrinsics.checkNotNull(calendar);
        }
        dateRangeCalendarView.setCurrentMonth(calendar);
    }

    private final void initView() {
        String string;
        String string2;
        AppCompatTextView appCompatTextView = (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvStartDate);
        Calendar calendar = this.startDate;
        if (calendar != null) {
            AppUtil.Companion companion = AppUtil.Companion;
            Date time = calendar != null ? calendar.getTime() : null;
            Intrinsics.checkNotNull(time);
            string = companion.getDateString(time, AppConstants.MM_DD_YY_FORMAT);
        } else {
            string = getString(R.string.start_date);
        }
        appCompatTextView.setText(string);
        AppCompatTextView appCompatTextView2 = (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvEndDate);
        Calendar calendar2 = this.endDate;
        if (calendar2 != null) {
            AppUtil.Companion companion2 = AppUtil.Companion;
            Date time2 = calendar2 != null ? calendar2.getTime() : null;
            Intrinsics.checkNotNull(time2);
            string2 = companion2.getDateString(time2, AppConstants.MM_DD_YY_FORMAT);
        } else {
            string2 = getString(R.string.end_date);
        }
        appCompatTextView2.setText(string2);
        initCalenderView();
        ((DateRangeCalendarView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.dateRangeCalendar)).setCalendarListener(this);
        ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivClose)).setOnClickListener(this);
        ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvSave)).setOnClickListener(this);
    }

    @JvmStatic
    @NotNull
    public static final DateRangePickerDialog newInstance(@NotNull Calendar calendar, @NotNull Calendar calendar2) {
        return Companion.newInstance(calendar, calendar2);
    }

    private final boolean validate() {
        Context requireContext;
        String string;
        String str;
        Calendar calendar;
        if (this.startDate == null || (calendar = this.endDate) == null) {
            requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            string = getString(R.string.select_date_range);
            str = "getString(R.string.select_date_range)";
        } else {
            Long valueOf = calendar != null ? Long.valueOf(calendar.getTimeInMillis()) : null;
            Intrinsics.checkNotNull(valueOf);
            long longValue = valueOf.longValue();
            Calendar calendar2 = this.startDate;
            Long valueOf2 = calendar2 != null ? Long.valueOf(calendar2.getTimeInMillis()) : null;
            Intrinsics.checkNotNull(valueOf2);
            if (TimeUnit.MILLISECONDS.toDays(longValue - valueOf2.longValue()) <= 30) {
                return true;
            }
            requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            string = getString(R.string.select_date_range_between_30_days);
            str = "getString(R.string.selec…te_range_between_30_days)";
        }
        Intrinsics.checkNotNullExpressionValue(string, str);
        ExtensionsKt.showToast(requireContext, string);
        return false;
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i2) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i2)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i2), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        Calendar calendar;
        if (!Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivClose))) {
            if (!Intrinsics.areEqual(view, (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvSave)) || !validate()) {
                return;
            }
            OnDateSelectListener onDateSelectListener = this.onDateSelectListener;
            if (onDateSelectListener != null && (calendar = this.startDate) != null && this.endDate != null && onDateSelectListener != null) {
                Long valueOf = calendar != null ? Long.valueOf(calendar.getTimeInMillis()) : null;
                Calendar calendar2 = this.endDate;
                onDateSelectListener.onDateSelect(new Pair<>(valueOf, calendar2 != null ? Long.valueOf(calendar2.getTimeInMillis()) : null));
            }
        }
        dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.startDate = (Calendar) arguments.getSerializable("start_date");
            this.endDate = (Calendar) arguments.getSerializable("end_date");
        }
        setStyle(0, R.style.FullScreenDialog);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.dialog_date_range_picker, viewGroup, false);
    }

    @Override // com.archit.calendardaterangepicker.customviews.CalendarListener
    public void onDateRangeSelected(@NotNull Calendar startDate, @NotNull Calendar endDate) {
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        this.startDate = startDate;
        this.endDate = endDate;
        try {
            AppUtil.Companion companion = AppUtil.Companion;
            Date time = startDate.getTime();
            Intrinsics.checkNotNullExpressionValue(time, "startDate.time");
            ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvStartDate)).setText(companion.getDateString(time, AppConstants.MM_DD_YY_FORMAT));
        } catch (Exception e2) {
            Logger logger = Logger.INSTANCE;
            e2.printStackTrace();
            logger.e(String.valueOf(Unit.INSTANCE));
        }
        try {
            AppUtil.Companion companion2 = AppUtil.Companion;
            Date time2 = endDate.getTime();
            Intrinsics.checkNotNullExpressionValue(time2, "endDate.time");
            ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvEndDate)).setText(companion2.getDateString(time2, AppConstants.MM_DD_YY_FORMAT));
        } catch (Exception e3) {
            Logger logger2 = Logger.INSTANCE;
            e3.printStackTrace();
            logger2.e(String.valueOf(Unit.INSTANCE));
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.archit.calendardaterangepicker.customviews.CalendarListener
    public void onFirstDateSelected(@NotNull Calendar startDate) {
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        this.startDate = startDate;
        try {
            AppUtil.Companion companion = AppUtil.Companion;
            Date time = startDate.getTime();
            Intrinsics.checkNotNullExpressionValue(time, "startDate.time");
            ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvStartDate)).setText(companion.getDateString(time, AppConstants.MM_DD_YY_FORMAT));
        } catch (Exception e2) {
            Logger logger = Logger.INSTANCE;
            e2.printStackTrace();
            logger.e(String.valueOf(Unit.INSTANCE));
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Dialog dialog;
        Window window;
        super.onStart();
        if (getDialog() == null || (dialog = getDialog()) == null || (window = dialog.getWindow()) == null) {
            return;
        }
        window.setLayout(-1, -1);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initView();
    }

    public final void setOnDateSelectListener(@NotNull OnDateSelectListener onDateSelectListener) {
        Intrinsics.checkNotNullParameter(onDateSelectListener, "onDateSelectListener");
        this.onDateSelectListener = onDateSelectListener;
    }
}
