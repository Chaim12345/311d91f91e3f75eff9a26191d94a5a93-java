package com.psa.mym.mycitroenconnect.controller.fragments.geo_fence;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SpinnerAdapter;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.psa.mym.mycitroenconnect.api.body.geo_fence.GeoFenceBody;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.dashboard.GeoFenceActivity;
import com.psa.mym.mycitroenconnect.controller.adapters.GeoFenceRepeatAdapter;
import com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.GeoFenceSetTimeFragment;
import com.psa.mym.mycitroenconnect.controller.viewmodel.GeoFenceViewModel;
import com.psa.mym.mycitroenconnect.model.GeoFenceCommonModel;
import com.psa.mym.mycitroenconnect.model.geo_fence.GeoFenceRepeat;
import com.psa.mym.mycitroenconnect.model.geo_fence.GetGeoFenceResponseItem;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.views.CustomSpinner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.Triple;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import l.b;
import l.c;
import org.apache.http.message.TokenParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class GeoFenceSetTimeFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    private GeoFenceRepeatAdapter dayRepeatAdapter;
    @NotNull
    private Calendar endDateCal;
    private long lastClickTime;
    @Nullable
    private GeoFenceActivity parentActivity;
    @Nullable
    private ArrayAdapter<String> periodAdapter;
    @NotNull
    private ArrayList<String> periods;
    private AdapterView.OnItemSelectedListener spinnerListener;
    private int spinnerSelectionPos;
    @NotNull
    private Calendar startDateCal;
    @NotNull
    private ArrayList<String> timeHrList;
    @NotNull
    private ArrayList<String> timeMinList;
    @NotNull
    private final Lazy viewModel$delegate;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private String timeMode = AppConstants.GEO_FENCE_TIME_MODE_SKIP;
    @NotNull
    private String startTime = "";
    @NotNull
    private String endTime = "";
    @NotNull
    private String startDate = "";
    @NotNull
    private String endDate = "";

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final GeoFenceSetTimeFragment newInstance() {
            GeoFenceSetTimeFragment geoFenceSetTimeFragment = new GeoFenceSetTimeFragment();
            geoFenceSetTimeFragment.setArguments(new Bundle());
            return geoFenceSetTimeFragment;
        }
    }

    public GeoFenceSetTimeFragment() {
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        this.startDateCal = calendar;
        Calendar calendar2 = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
        this.endDateCal = calendar2;
        this.timeHrList = new ArrayList<>();
        this.timeMinList = new ArrayList<>();
        this.periods = new ArrayList<>();
        this.viewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(GeoFenceViewModel.class), new GeoFenceSetTimeFragment$special$$inlined$activityViewModels$default$1(this), new GeoFenceSetTimeFragment$special$$inlined$activityViewModels$default$2(this));
    }

    private final void getBundleData() {
        getArguments();
    }

    private final String getCurrentStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(13, 60);
        AppUtil.Companion companion = AppUtil.Companion;
        Date time = calendar.getTime();
        Intrinsics.checkNotNullExpressionValue(time, "calendar.time");
        return companion.getDateString(time, AppConstants.UTC_DATE_FORMAT);
    }

    private final int getSelectedHourPosition(String str) {
        int i2 = 0;
        for (Object obj : this.timeHrList) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            String str2 = (String) obj;
            if (Intrinsics.areEqual(str, "00") || Intrinsics.areEqual(str, "12")) {
                return 11;
            }
            if (Intrinsics.areEqual(str, str2)) {
                return i2;
            }
            i2 = i3;
        }
        return -1;
    }

    private final int getSelectedMinPosition(String str) {
        int i2 = 0;
        try {
            int i3 = 0;
            for (Object obj : this.timeMinList) {
                int i4 = i3 + 1;
                if (i3 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                if (Intrinsics.areEqual((String) obj, String.valueOf(((Integer.parseInt(str) + 4) / 5) * 5))) {
                    return i3;
                }
                i3 = i4;
            }
            return -1;
        } catch (Exception unused) {
            for (Object obj2 : this.timeMinList) {
                int i5 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                if (Intrinsics.areEqual((String) obj2, str)) {
                    return i2;
                }
                i2 = i5;
            }
            return -1;
        }
    }

    private final Date getTimeInDateFormat(String str) {
        try {
            Date parse = new SimpleDateFormat(AppConstants.UTC_DATE_FORMAT, AppUtil.Companion.getDefaultLocale()).parse(str);
            Intrinsics.checkNotNull(parse);
            Calendar.getInstance().setTime(parse);
            return parse;
        } catch (Exception unused) {
            Date parse2 = new SimpleDateFormat(AppConstants.RESPONSE_UTC_DATE_FORMAT, AppUtil.Companion.getDefaultLocale()).parse(str);
            Intrinsics.checkNotNull(parse2);
            Calendar.getInstance().setTime(parse2);
            return parse2;
        }
    }

    private final Triple<String, String, String> getTimeValue(String str) {
        String str2;
        int i2;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getTimeInDateFormat(str));
        int i3 = calendar.get(10);
        int i4 = calendar.get(12);
        if (calendar.get(9) == 0) {
            i2 = R.string.label_am;
        } else if (calendar.get(9) != 1) {
            str2 = "";
            Intrinsics.checkNotNullExpressionValue(str2, "when {\n            calen…\"\n            }\n        }");
            return new Triple<>(String.valueOf(i3), String.valueOf(i4), str2);
        } else {
            i2 = R.string.label_pm;
        }
        str2 = getString(i2);
        Intrinsics.checkNotNullExpressionValue(str2, "when {\n            calen…\"\n            }\n        }");
        return new Triple<>(String.valueOf(i3), String.valueOf(i4), str2);
    }

    private final GeoFenceViewModel getViewModel() {
        return (GeoFenceViewModel) this.viewModel$delegate.getValue();
    }

    private final void hideRepeatView() {
        AppCompatTextView tvSelectDays = (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvSelectDays);
        Intrinsics.checkNotNullExpressionValue(tvSelectDays, "tvSelectDays");
        ExtensionsKt.hide(tvSelectDays);
        MaterialCardView cvDays = (MaterialCardView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.cvDays);
        Intrinsics.checkNotNullExpressionValue(cvDays, "cvDays");
        ExtensionsKt.hide(cvDays);
        RecyclerView rvDays = (RecyclerView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rvDays);
        Intrinsics.checkNotNullExpressionValue(rvDays, "rvDays");
        ExtensionsKt.hide(rvDays);
    }

    private final void hideSetTimeHomeScreen() {
        ConstraintLayout clSetTimeHome = (ConstraintLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.clSetTimeHome);
        Intrinsics.checkNotNullExpressionValue(clSetTimeHome, "clSetTimeHome");
        ExtensionsKt.hide(clSetTimeHome);
        ConstraintLayout layoutSetTime = (ConstraintLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.layoutSetTime);
        Intrinsics.checkNotNullExpressionValue(layoutSetTime, "layoutSetTime");
        ExtensionsKt.show(layoutSetTime);
    }

    private final void hideTimeSelectionLayout() {
        RelativeLayout rlTime = (RelativeLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rlTime);
        Intrinsics.checkNotNullExpressionValue(rlTime, "rlTime");
        ExtensionsKt.hide(rlTime);
    }

    private final void initView() {
        GeoFenceActivity geoFenceActivity;
        List list;
        List list2;
        List list3;
        ArrayList arrayListOf;
        if (getActivity() == null || !(getActivity() instanceof GeoFenceActivity)) {
            geoFenceActivity = null;
        } else {
            FragmentActivity activity = getActivity();
            Objects.requireNonNull(activity, "null cannot be cast to non-null type com.psa.mym.mycitroenconnect.controller.activities.dashboard.GeoFenceActivity");
            geoFenceActivity = (GeoFenceActivity) activity;
        }
        this.parentActivity = geoFenceActivity;
        showSetTimeHomeScreen();
        String[] stringArray = getResources().getStringArray(R.array.time_selection_hr);
        Intrinsics.checkNotNullExpressionValue(stringArray, "resources.getStringArray….array.time_selection_hr)");
        list = ArraysKt___ArraysKt.toList(stringArray);
        this.timeHrList = (ArrayList) list;
        String[] stringArray2 = getResources().getStringArray(R.array.time_selection_min);
        Intrinsics.checkNotNullExpressionValue(stringArray2, "resources.getStringArray…array.time_selection_min)");
        list2 = ArraysKt___ArraysKt.toList(stringArray2);
        this.timeMinList = (ArrayList) list2;
        ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext(), (int) R.layout.layout_curom_spinner_item, this.timeHrList);
        arrayAdapter.setDropDownViewResource(R.layout.layout_curom_spinner_item);
        View _$_findCachedViewById = _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.spinStartTimeHr);
        int i2 = com.psa.mym.mycitroenconnect.R.id.spinner;
        ((CustomSpinner) _$_findCachedViewById.findViewById(i2)).setAdapter((SpinnerAdapter) arrayAdapter);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(requireActivity(), (int) R.layout.layout_curom_spinner_item, this.timeMinList);
        arrayAdapter2.setDropDownViewResource(R.layout.layout_curom_spinner_item);
        ((CustomSpinner) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.spinStartTimeMin).findViewById(i2)).setAdapter((SpinnerAdapter) arrayAdapter2);
        ArrayAdapter arrayAdapter3 = new ArrayAdapter(requireActivity(), (int) R.layout.layout_curom_spinner_item, this.timeHrList);
        arrayAdapter3.setDropDownViewResource(R.layout.layout_curom_spinner_item);
        ((CustomSpinner) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.spinEndTimeHr).findViewById(i2)).setAdapter((SpinnerAdapter) arrayAdapter3);
        ArrayAdapter arrayAdapter4 = new ArrayAdapter(requireActivity(), (int) R.layout.layout_curom_spinner_item, this.timeMinList);
        arrayAdapter4.setDropDownViewResource(R.layout.layout_curom_spinner_item);
        ((CustomSpinner) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.spinEndTimeMin).findViewById(i2)).setAdapter((SpinnerAdapter) arrayAdapter4);
        String[] stringArray3 = getResources().getStringArray(R.array.geofence_day_selection);
        Intrinsics.checkNotNullExpressionValue(stringArray3, "resources.getStringArray…y.geofence_day_selection)");
        list3 = ArraysKt___ArraysKt.toList(stringArray3);
        this.periods = (ArrayList) list3;
        ArrayAdapter<String> arrayAdapter5 = new ArrayAdapter<>(requireActivity(), (int) R.layout.layout_curom_spinner_item, this.periods);
        arrayAdapter5.setDropDownViewResource(R.layout.layout_curom_spinner_item);
        ((CustomSpinner) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.spDays)).setAdapter((SpinnerAdapter) arrayAdapter5);
        this.periodAdapter = arrayAdapter5;
        String string = getString(R.string.mon);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.mon)");
        String string2 = getString(R.string.tue);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.tue)");
        String string3 = getString(R.string.wed);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.wed)");
        String string4 = getString(R.string.thu);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.thu)");
        String string5 = getString(R.string.fri);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.fri)");
        String string6 = getString(R.string.sat);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.sat)");
        String string7 = getString(R.string.sun);
        Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.sun)");
        arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(new GeoFenceRepeat(string, false, false, null, 8, null), new GeoFenceRepeat(string2, false, false, null, 8, null), new GeoFenceRepeat(string3, false, false, null, 8, null), new GeoFenceRepeat(string4, false, false, null, 8, null), new GeoFenceRepeat(string5, false, false, null, 8, null), new GeoFenceRepeat(string6, false, false, null, 8, null), new GeoFenceRepeat(string7, false, false, null, 8, null));
        this.dayRepeatAdapter = new GeoFenceRepeatAdapter(arrayListOf, true);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rvDays);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 4));
        recyclerView.setAdapter(this.dayRepeatAdapter);
        setListener();
    }

    @JvmStatic
    @NotNull
    public static final GeoFenceSetTimeFragment newInstance() {
        return Companion.newInstance();
    }

    private final String parseDate(String str) {
        AppUtil.Companion companion = AppUtil.Companion;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.TIME_FORMAT_HH_mm_a, companion.getDefaultLocale());
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(AppConstants.TIME_FORMAT_HH_mm, companion.getDefaultLocale());
        Date parse = simpleDateFormat.parse(str);
        if (parse != null) {
            String format = simpleDateFormat2.format(parse);
            Intrinsics.checkNotNullExpressionValue(format, "{\n            parseFormat.format(date)\n        }");
            return format;
        }
        return "";
    }

    private final boolean repeatDaysValidate() {
        Context requireContext;
        String string;
        String str;
        int i2 = com.psa.mym.mycitroenconnect.R.id.spDays;
        if (((CustomSpinner) _$_findCachedViewById(i2)).getSelectedItemPosition() == 2) {
            GeoFenceRepeatAdapter geoFenceRepeatAdapter = this.dayRepeatAdapter;
            if ((geoFenceRepeatAdapter == null || geoFenceRepeatAdapter.isSelected()) ? false : true) {
                requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                string = getString(R.string.select_at_least_one_day);
                str = "getString(R.string.select_at_least_one_day)";
                Intrinsics.checkNotNullExpressionValue(string, str);
                ExtensionsKt.showToast(requireContext, string);
                return false;
            }
        }
        if (((CustomSpinner) _$_findCachedViewById(i2)).getSelectedItemPosition() == 3 && Intrinsics.areEqual(this.periods.get(3), getString(R.string.lbl_custom_date_range))) {
            requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            string = getString(R.string.select_custom_date_range);
            str = "getString(R.string.select_custom_date_range)";
            Intrinsics.checkNotNullExpressionValue(string, str);
            ExtensionsKt.showToast(requireContext, string);
            return false;
        }
        return true;
    }

    private final void setCurrentTime() {
        String str;
        int i2;
        Calendar calendar = Calendar.getInstance();
        int i3 = calendar.get(10);
        int i4 = calendar.get(12);
        if (calendar.get(9) == 0) {
            i2 = R.string.label_am;
        } else if (calendar.get(9) != 1) {
            str = "";
            Intrinsics.checkNotNullExpressionValue(str, "when {\n            calen…\"\n            }\n        }");
            Triple<String, String, String> triple = new Triple<>(String.valueOf(i3), String.valueOf(i4), str);
            setStartTimeInSpinner(triple);
            setEndTimeInSpinner(triple);
        } else {
            i2 = R.string.label_pm;
        }
        str = getString(i2);
        Intrinsics.checkNotNullExpressionValue(str, "when {\n            calen…\"\n            }\n        }");
        Triple<String, String, String> triple2 = new Triple<>(String.valueOf(i3), String.valueOf(i4), str);
        setStartTimeInSpinner(triple2);
        setEndTimeInSpinner(triple2);
    }

    private final void setCustomPickerSelection(Pair<Long, Long> pair) {
        Long l2 = pair.first;
        Long l3 = pair.second;
        AppUtil.Companion companion = AppUtil.Companion;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.ONLY_DATE_FORMAT, companion.getDefaultLocale());
        AppConstants.Companion companion2 = AppConstants.Companion;
        companion2.setStartDate(simpleDateFormat.format(l2).toString());
        companion2.setEndDate(simpleDateFormat.format(l3).toString());
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(AppConstants.MM_DD_YY_FORMAT, companion.getDefaultLocale());
        String str = simpleDateFormat2.format(l2).toString();
        String str2 = simpleDateFormat2.format(l3).toString();
        updateCustomDateValue(str + " - " + str2);
    }

    private final void setCustomSpinnerSelection() {
        int i2 = com.psa.mym.mycitroenconnect.R.id.spDays;
        ((CustomSpinner) _$_findCachedViewById(i2)).setOnItemSelectedListener(null);
        ((CustomSpinner) _$_findCachedViewById(i2)).post(new Runnable() { // from class: l.e
            @Override // java.lang.Runnable
            public final void run() {
                GeoFenceSetTimeFragment.m139setCustomSpinnerSelection$lambda13(GeoFenceSetTimeFragment.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setCustomSpinnerSelection$lambda-13  reason: not valid java name */
    public static final void m139setCustomSpinnerSelection$lambda13(final GeoFenceSetTimeFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i2 = com.psa.mym.mycitroenconnect.R.id.spDays;
        ((CustomSpinner) this$0._$_findCachedViewById(i2)).setSelection(this$0.spinnerSelectionPos);
        ((CustomSpinner) this$0._$_findCachedViewById(i2)).post(new Runnable() { // from class: l.f
            @Override // java.lang.Runnable
            public final void run() {
                GeoFenceSetTimeFragment.m140setCustomSpinnerSelection$lambda13$lambda12(GeoFenceSetTimeFragment.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setCustomSpinnerSelection$lambda-13$lambda-12  reason: not valid java name */
    public static final void m140setCustomSpinnerSelection$lambda13$lambda12(GeoFenceSetTimeFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CustomSpinner customSpinner = (CustomSpinner) this$0._$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.spDays);
        AdapterView.OnItemSelectedListener onItemSelectedListener = this$0.spinnerListener;
        if (onItemSelectedListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("spinnerListener");
            onItemSelectedListener = null;
        }
        customSpinner.setOnItemSelectedListener(onItemSelectedListener);
    }

    private final void setDaysOfWeek(List<String> list) {
        GeoFenceRepeatAdapter geoFenceRepeatAdapter;
        int i2;
        if (!list.isEmpty()) {
            for (String str : list) {
                String string = getString(R.string.mon);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.mon)");
                Locale locale = Locale.ROOT;
                String upperCase = string.toUpperCase(locale);
                Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                if (Intrinsics.areEqual(str, upperCase)) {
                    geoFenceRepeatAdapter = this.dayRepeatAdapter;
                    if (geoFenceRepeatAdapter != null) {
                        i2 = 0;
                        geoFenceRepeatAdapter.selectDayTime(i2);
                    }
                } else {
                    String string2 = getString(R.string.tue);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.tue)");
                    String upperCase2 = string2.toUpperCase(locale);
                    Intrinsics.checkNotNullExpressionValue(upperCase2, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                    if (Intrinsics.areEqual(str, upperCase2)) {
                        GeoFenceRepeatAdapter geoFenceRepeatAdapter2 = this.dayRepeatAdapter;
                        if (geoFenceRepeatAdapter2 != null) {
                            geoFenceRepeatAdapter2.selectDayTime(1);
                        }
                    } else {
                        String string3 = getString(R.string.wed);
                        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.wed)");
                        String upperCase3 = string3.toUpperCase(locale);
                        Intrinsics.checkNotNullExpressionValue(upperCase3, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                        if (Intrinsics.areEqual(str, upperCase3)) {
                            geoFenceRepeatAdapter = this.dayRepeatAdapter;
                            if (geoFenceRepeatAdapter != null) {
                                i2 = 2;
                                geoFenceRepeatAdapter.selectDayTime(i2);
                            }
                        } else {
                            String string4 = getString(R.string.thu);
                            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.thu)");
                            String upperCase4 = string4.toUpperCase(locale);
                            Intrinsics.checkNotNullExpressionValue(upperCase4, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                            if (Intrinsics.areEqual(str, upperCase4)) {
                                geoFenceRepeatAdapter = this.dayRepeatAdapter;
                                if (geoFenceRepeatAdapter != null) {
                                    i2 = 3;
                                    geoFenceRepeatAdapter.selectDayTime(i2);
                                }
                            } else {
                                String string5 = getString(R.string.fri);
                                Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.fri)");
                                String upperCase5 = string5.toUpperCase(locale);
                                Intrinsics.checkNotNullExpressionValue(upperCase5, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                                if (Intrinsics.areEqual(str, upperCase5)) {
                                    geoFenceRepeatAdapter = this.dayRepeatAdapter;
                                    if (geoFenceRepeatAdapter != null) {
                                        i2 = 4;
                                        geoFenceRepeatAdapter.selectDayTime(i2);
                                    }
                                } else {
                                    String string6 = getString(R.string.sat);
                                    Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.sat)");
                                    String upperCase6 = string6.toUpperCase(locale);
                                    Intrinsics.checkNotNullExpressionValue(upperCase6, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                                    if (Intrinsics.areEqual(str, upperCase6)) {
                                        geoFenceRepeatAdapter = this.dayRepeatAdapter;
                                        if (geoFenceRepeatAdapter != null) {
                                            i2 = 5;
                                            geoFenceRepeatAdapter.selectDayTime(i2);
                                        }
                                    } else {
                                        String string7 = getString(R.string.sun);
                                        Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.sun)");
                                        String upperCase7 = string7.toUpperCase(locale);
                                        Intrinsics.checkNotNullExpressionValue(upperCase7, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                                        if (Intrinsics.areEqual(str, upperCase7) && (geoFenceRepeatAdapter = this.dayRepeatAdapter) != null) {
                                            i2 = 6;
                                            geoFenceRepeatAdapter.selectDayTime(i2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setEndTime() {
        StringBuilder sb = new StringBuilder();
        View _$_findCachedViewById = _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.spinEndTimeHr);
        int i2 = com.psa.mym.mycitroenconnect.R.id.spinner;
        sb.append(((CustomSpinner) _$_findCachedViewById.findViewById(i2)).getSelectedItem());
        sb.append(AbstractJsonLexerKt.COLON);
        sb.append(((CustomSpinner) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.spinEndTimeMin).findViewById(i2)).getSelectedItem());
        this.endTime = sb.toString();
    }

    private final String setEndTimeAMPM() {
        String string;
        String str;
        if (((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.endAMPM).findViewById(com.psa.mym.mycitroenconnect.R.id.rbAM)).isChecked()) {
            string = getString(R.string.label_am);
            str = "getString(R.string.label_am)";
        } else {
            string = getString(R.string.label_pm);
            str = "getString(R.string.label_pm)";
        }
        Intrinsics.checkNotNullExpressionValue(string, str);
        String upperCase = string.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        return upperCase;
    }

    private final void setEndTimeInSpinner(Triple<String, String, String> triple) {
        Integer num;
        String third;
        String second;
        int i2;
        boolean isBlank;
        String first;
        int i3;
        boolean isBlank2;
        Integer num2 = null;
        if (triple == null || (first = triple.getFirst()) == null) {
            num = null;
        } else {
            if (first.length() > 0) {
                isBlank2 = StringsKt__StringsJVMKt.isBlank(first);
                if ((!isBlank2) && !Intrinsics.areEqual(first, "")) {
                    if (Integer.parseInt(first) < 10) {
                        first = '0' + first;
                    }
                    i3 = getSelectedHourPosition(first);
                    num = Integer.valueOf(i3);
                }
            }
            i3 = -1;
            num = Integer.valueOf(i3);
        }
        if (num != null && num.intValue() != -1) {
            ((CustomSpinner) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.spinEndTimeHr).findViewById(com.psa.mym.mycitroenconnect.R.id.spinner)).setSelection(num.intValue());
        }
        if (triple != null && (second = triple.getSecond()) != null) {
            if (second.length() > 0) {
                isBlank = StringsKt__StringsJVMKt.isBlank(second);
                if ((!isBlank) && !Intrinsics.areEqual(second, "")) {
                    if (Integer.parseInt(second) < 10) {
                        i2 = getSelectedMinPosition('0' + second);
                    } else {
                        i2 = getSelectedMinPosition(second);
                    }
                    num2 = Integer.valueOf(i2);
                }
            }
            i2 = -1;
            num2 = Integer.valueOf(i2);
        }
        if (num2 != null && num2.intValue() != -1) {
            ((CustomSpinner) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.spinEndTimeMin).findViewById(com.psa.mym.mycitroenconnect.R.id.spinner)).setSelection(num2.intValue());
        }
        if (triple == null || (third = triple.getThird()) == null) {
            return;
        }
        int i4 = com.psa.mym.mycitroenconnect.R.id.endAMPM;
        ((MaterialRadioButton) _$_findCachedViewById(i4).findViewById(com.psa.mym.mycitroenconnect.R.id.rbAM)).setChecked(Intrinsics.areEqual(third, getString(R.string.label_am)));
        ((MaterialRadioButton) _$_findCachedViewById(i4).findViewById(com.psa.mym.mycitroenconnect.R.id.rbPM)).setChecked(Intrinsics.areEqual(third, getString(R.string.label_pm)));
    }

    private final void setListener() {
        ((RadioGroup) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rgSetTime)).setOnCheckedChangeListener(this);
        ((RadioGroup) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rgSetDayTime)).setOnCheckedChangeListener(this);
        int i2 = com.psa.mym.mycitroenconnect.R.id.switchSetTime;
        ((SwitchMaterial) _$_findCachedViewById(i2)).setClickable(true);
        ((SwitchMaterial) _$_findCachedViewById(i2)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSaveContinue)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivDown)).setOnClickListener(this);
        int i3 = com.psa.mym.mycitroenconnect.R.id.spinStartTimeHr;
        View _$_findCachedViewById = _$_findCachedViewById(i3);
        int i4 = com.psa.mym.mycitroenconnect.R.id.ivSpinner;
        ((AppCompatImageView) _$_findCachedViewById.findViewById(i4)).setOnClickListener(this);
        int i5 = com.psa.mym.mycitroenconnect.R.id.spinEndTimeHr;
        ((AppCompatImageView) _$_findCachedViewById(i5).findViewById(i4)).setOnClickListener(this);
        int i6 = com.psa.mym.mycitroenconnect.R.id.spinStartTimeMin;
        ((AppCompatImageView) _$_findCachedViewById(i6).findViewById(i4)).setOnClickListener(this);
        int i7 = com.psa.mym.mycitroenconnect.R.id.spinEndTimeMin;
        ((AppCompatImageView) _$_findCachedViewById(i7).findViewById(i4)).setOnClickListener(this);
        ((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchRepeatTime)).setOnCheckedChangeListener(this);
        View _$_findCachedViewById2 = _$_findCachedViewById(i3);
        int i8 = com.psa.mym.mycitroenconnect.R.id.spinner;
        ((CustomSpinner) _$_findCachedViewById2.findViewById(i8)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.GeoFenceSetTimeFragment$setListener$1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(@Nullable AdapterView<?> adapterView, @Nullable View view, int i9, long j2) {
                Logger logger = Logger.INSTANCE;
                logger.e("Start Time Hour Position: " + i9 + " & id: " + j2);
                GeoFenceSetTimeFragment.this.setStartTime();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(@Nullable AdapterView<?> adapterView) {
            }
        });
        ((CustomSpinner) _$_findCachedViewById(i6).findViewById(i8)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.GeoFenceSetTimeFragment$setListener$2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(@Nullable AdapterView<?> adapterView, @Nullable View view, int i9, long j2) {
                Logger logger = Logger.INSTANCE;
                logger.e("Start Minute Hour Position: " + i9 + " & id: " + j2);
                GeoFenceSetTimeFragment.this.setStartTime();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(@Nullable AdapterView<?> adapterView) {
            }
        });
        ((CustomSpinner) _$_findCachedViewById(i5).findViewById(i8)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.GeoFenceSetTimeFragment$setListener$3
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(@Nullable AdapterView<?> adapterView, @Nullable View view, int i9, long j2) {
                Logger logger = Logger.INSTANCE;
                logger.e("End Time Hour Position: " + i9 + " & id: " + j2);
                GeoFenceSetTimeFragment.this.setEndTime();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(@Nullable AdapterView<?> adapterView) {
            }
        });
        ((CustomSpinner) _$_findCachedViewById(i7).findViewById(i8)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.GeoFenceSetTimeFragment$setListener$4
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(@Nullable AdapterView<?> adapterView, @Nullable View view, int i9, long j2) {
                Logger logger = Logger.INSTANCE;
                logger.e("End Minute Hour Position: " + i9 + " & id: " + j2);
                GeoFenceSetTimeFragment.this.setEndTime();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(@Nullable AdapterView<?> adapterView) {
            }
        });
        this.spinnerListener = new AdapterView.OnItemSelectedListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.GeoFenceSetTimeFragment$setListener$5
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(@Nullable AdapterView<?> adapterView, @Nullable View view, int i9, long j2) {
                if (i9 == 0 || i9 == 1) {
                    RecyclerView rvDays = (RecyclerView) GeoFenceSetTimeFragment.this._$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rvDays);
                    Intrinsics.checkNotNullExpressionValue(rvDays, "rvDays");
                    ExtensionsKt.hide(rvDays);
                } else if (i9 != 2) {
                    if (i9 != 3) {
                        return;
                    }
                    RecyclerView rvDays2 = (RecyclerView) GeoFenceSetTimeFragment.this._$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rvDays);
                    Intrinsics.checkNotNullExpressionValue(rvDays2, "rvDays");
                    ExtensionsKt.hide(rvDays2);
                    AppCompatTextView tvSelectedDateRange = (AppCompatTextView) GeoFenceSetTimeFragment.this._$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvSelectedDateRange);
                    Intrinsics.checkNotNullExpressionValue(tvSelectedDateRange, "tvSelectedDateRange");
                    ExtensionsKt.hide(tvSelectedDateRange);
                    GeoFenceSetTimeFragment.this.showDatePicker();
                    return;
                } else {
                    RecyclerView rvDays3 = (RecyclerView) GeoFenceSetTimeFragment.this._$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rvDays);
                    Intrinsics.checkNotNullExpressionValue(rvDays3, "rvDays");
                    ExtensionsKt.show(rvDays3);
                }
                AppCompatTextView tvSelectedDateRange2 = (AppCompatTextView) GeoFenceSetTimeFragment.this._$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvSelectedDateRange);
                Intrinsics.checkNotNullExpressionValue(tvSelectedDateRange2, "tvSelectedDateRange");
                ExtensionsKt.hide(tvSelectedDateRange2);
                GeoFenceSetTimeFragment geoFenceSetTimeFragment = GeoFenceSetTimeFragment.this;
                String string = geoFenceSetTimeFragment.getString(R.string.lbl_custom_date_range);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.lbl_custom_date_range)");
                geoFenceSetTimeFragment.updateCustomDateValue(string);
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(@Nullable AdapterView<?> adapterView) {
            }
        };
        CustomSpinner customSpinner = (CustomSpinner) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.spDays);
        AdapterView.OnItemSelectedListener onItemSelectedListener = this.spinnerListener;
        if (onItemSelectedListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("spinnerListener");
            onItemSelectedListener = null;
        }
        customSpinner.setOnItemSelectedListener(onItemSelectedListener);
    }

    private final void setSkipTimeData() {
        GeoFenceBody geoFenceBody;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        if (geoFenceCommonModel == null || (geoFenceBody = geoFenceCommonModel.getGeoFenceBody()) == null) {
            return;
        }
        geoFenceBody.setInfinite("true");
        Calendar calendar = Calendar.getInstance();
        calendar.add(13, 60);
        AppUtil.Companion companion = AppUtil.Companion;
        Date time = calendar.getTime();
        Intrinsics.checkNotNullExpressionValue(time, "calendar.time");
        geoFenceBody.setStartTime(companion.getDateString(time, AppConstants.UTC_DATE_FORMAT));
        geoFenceBody.setEndTime(null);
        geoFenceBody.setTimeMode(AppConstants.GEO_FENCE_TIME_MODE_SKIP);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setStartTime() {
        StringBuilder sb = new StringBuilder();
        View _$_findCachedViewById = _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.spinStartTimeHr);
        int i2 = com.psa.mym.mycitroenconnect.R.id.spinner;
        sb.append(((CustomSpinner) _$_findCachedViewById.findViewById(i2)).getSelectedItem());
        sb.append(AbstractJsonLexerKt.COLON);
        sb.append(((CustomSpinner) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.spinStartTimeMin).findViewById(i2)).getSelectedItem());
        this.startTime = sb.toString();
    }

    private final String setStartTimeAMPM() {
        String string;
        String str;
        if (((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.startAMPM).findViewById(com.psa.mym.mycitroenconnect.R.id.rbAM)).isChecked()) {
            string = getString(R.string.label_am);
            str = "{\n            getString(…tring.label_am)\n        }";
        } else {
            string = getString(R.string.label_pm);
            str = "{\n            getString(…tring.label_pm)\n        }";
        }
        Intrinsics.checkNotNullExpressionValue(string, str);
        return string;
    }

    private final void setStartTimeInSpinner(Triple<String, String, String> triple) {
        Integer num;
        String third;
        String second;
        int i2;
        boolean isBlank;
        String first;
        int i3;
        boolean isBlank2;
        Integer num2 = null;
        if (triple == null || (first = triple.getFirst()) == null) {
            num = null;
        } else {
            if (first.length() > 0) {
                isBlank2 = StringsKt__StringsJVMKt.isBlank(first);
                if ((!isBlank2) && !Intrinsics.areEqual(first, "")) {
                    if (Integer.parseInt(first) < 10) {
                        first = '0' + first;
                    }
                    i3 = getSelectedHourPosition(first);
                    num = Integer.valueOf(i3);
                }
            }
            i3 = -1;
            num = Integer.valueOf(i3);
        }
        if (num != null && num.intValue() != -1) {
            ((CustomSpinner) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.spinStartTimeHr).findViewById(com.psa.mym.mycitroenconnect.R.id.spinner)).setSelection(num.intValue());
        }
        if (triple != null && (second = triple.getSecond()) != null) {
            if (second.length() > 0) {
                isBlank = StringsKt__StringsJVMKt.isBlank(second);
                if ((!isBlank) && !Intrinsics.areEqual(second, "")) {
                    if (Integer.parseInt(second) < 10) {
                        i2 = getSelectedMinPosition('0' + second);
                    } else {
                        i2 = getSelectedMinPosition(second);
                    }
                    num2 = Integer.valueOf(i2);
                }
            }
            i2 = -1;
            num2 = Integer.valueOf(i2);
        }
        if (num2 != null && num2.intValue() != -1) {
            ((CustomSpinner) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.spinStartTimeMin).findViewById(com.psa.mym.mycitroenconnect.R.id.spinner)).setSelection(num2.intValue());
        }
        if (triple == null || (third = triple.getThird()) == null) {
            return;
        }
        int i4 = com.psa.mym.mycitroenconnect.R.id.startAMPM;
        ((MaterialRadioButton) _$_findCachedViewById(i4).findViewById(com.psa.mym.mycitroenconnect.R.id.rbAM)).setChecked(Intrinsics.areEqual(third, getString(R.string.label_am)));
        ((MaterialRadioButton) _$_findCachedViewById(i4).findViewById(com.psa.mym.mycitroenconnect.R.id.rbPM)).setChecked(Intrinsics.areEqual(third, getString(R.string.label_pm)));
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0108, code lost:
        if (r2 != null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x010a, code lost:
        r12 = r2.getSelectedDays();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x010f, code lost:
        r12 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0110, code lost:
        r1.setDaysOfWeek(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0233, code lost:
        if (r2 != null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0273, code lost:
        if (((com.google.android.material.radiobutton.MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbNo)).isChecked() != false) goto L50;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void setTimeInModel(boolean z) {
        GeoFenceCommonModel geoFenceCommonModel;
        GeoFenceBody geoFenceBody;
        GeoFenceCommonModel geoFenceCommonModel2;
        GetGeoFenceResponseItem geoFenceGetResponse;
        GeoFenceBody geoFenceBody2;
        AppUtil.Companion companion;
        String str;
        ArrayList<String> arrayListOf;
        GeoFenceRepeatAdapter geoFenceRepeatAdapter;
        Calendar calendar;
        String dateString;
        GeoFenceCommonModel geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
        if (geoFenceCommonModel3 != null && (geoFenceBody2 = geoFenceCommonModel3.getGeoFenceBody()) != null) {
            if (((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbYes)).isChecked()) {
                geoFenceBody2.setInfinite("false");
                if (((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchSetTime)).isChecked()) {
                    if (((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbFullDay)).isChecked()) {
                        Calendar calendar2 = Calendar.getInstance();
                        calendar2.add(13, 60);
                        companion = AppUtil.Companion;
                        Date time = calendar2.getTime();
                        Intrinsics.checkNotNullExpressionValue(time, "calendar.time");
                        geoFenceBody2.setStartTime(companion.getDateString(time, AppConstants.UTC_DATE_FORMAT));
                        Intrinsics.checkNotNullExpressionValue(calendar2, "calendar");
                        companion.setTimeToEndOfDay(calendar2);
                        Date time2 = calendar2.getTime();
                        Intrinsics.checkNotNullExpressionValue(time2, "calendar.time");
                        geoFenceBody2.setEndTime(companion.getDateString(time2, AppConstants.UTC_DATE_FORMAT));
                        if (((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchRepeatTime)).isChecked()) {
                            Calendar cal = Calendar.getInstance();
                            Intrinsics.checkNotNullExpressionValue(cal, "cal");
                            companion.setTimeToBeginningOfDay(cal);
                            Date time3 = cal.getTime();
                            Intrinsics.checkNotNullExpressionValue(time3, "cal.time");
                            geoFenceBody2.setStartTime(companion.getDateString(time3, AppConstants.UTC_DATE_FORMAT));
                            int selectedItemPosition = ((CustomSpinner) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.spDays)).getSelectedItemPosition();
                            if (selectedItemPosition == 0) {
                                geoFenceBody2.setTimeMode(AppConstants.GEO_FENCE_TIME_MODE_FULL_DAY_ALL_DAYS);
                                arrayListOf = CollectionsKt__CollectionsKt.arrayListOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN");
                            } else if (selectedItemPosition == 1) {
                                geoFenceBody2.setTimeMode(AppConstants.GEO_FENCE_TIME_MODE_FULL_DAY_WEEK_DAYS);
                                arrayListOf = CollectionsKt__CollectionsKt.arrayListOf("MON", "TUE", "WED", "THU", "FRI");
                            } else if (selectedItemPosition == 2) {
                                geoFenceBody2.setTimeMode(AppConstants.GEO_FENCE_TIME_MODE_FULL_DAY_CUSTOM_DAYS);
                                geoFenceRepeatAdapter = this.dayRepeatAdapter;
                            } else if (selectedItemPosition == 3) {
                                geoFenceBody2.setTimeMode(AppConstants.GEO_FENCE_TIME_MODE_FULL_DAY_CUSTOM_DATE_RANGE);
                                geoFenceBody2.setDaysOfWeek(null);
                                Calendar calendar3 = this.startDateCal;
                                if (ExtensionsKt.isToday(calendar3)) {
                                    dateString = getCurrentStartTime();
                                } else {
                                    companion.setTimeToBeginningOfDay(calendar3);
                                    Date time4 = calendar3.getTime();
                                    Intrinsics.checkNotNullExpressionValue(time4, "it.time");
                                    dateString = companion.getDateString(time4, AppConstants.UTC_DATE_FORMAT);
                                }
                                geoFenceBody2.setStartTime(dateString);
                                calendar = this.endDateCal;
                                companion.setTimeToEndOfDay(calendar);
                                Date time5 = calendar.getTime();
                                Intrinsics.checkNotNullExpressionValue(time5, "it.time");
                                geoFenceBody2.setEndTime(companion.getDateString(time5, AppConstants.UTC_DATE_FORMAT));
                            }
                            geoFenceBody2.setDaysOfWeek(arrayListOf);
                        } else {
                            str = AppConstants.GEO_FENCE_TIME_MODE_TODAY;
                            geoFenceBody2.setTimeMode(str);
                            arrayListOf = null;
                            geoFenceBody2.setDaysOfWeek(arrayListOf);
                        }
                    } else if (((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbSetTiming)).isChecked()) {
                        setStartTime();
                        String parseDate = parseDate(this.startTime + TokenParser.SP + setStartTimeAMPM());
                        companion = AppUtil.Companion;
                        Date time6 = companion.timeCalendar24Hour(parseDate).getTime();
                        Intrinsics.checkNotNullExpressionValue(time6, "AppUtil.timeCalendar24Hour(sTime).time");
                        geoFenceBody2.setStartTime(companion.getDateString(time6, AppConstants.UTC_DATE_FORMAT));
                        setEndTime();
                        String parseDate2 = parseDate(this.endTime + TokenParser.SP + setEndTimeAMPM());
                        Date time7 = companion.timeCalendar24Hour(parseDate2).getTime();
                        Intrinsics.checkNotNullExpressionValue(time7, "AppUtil.timeCalendar24Hour(eTime).time");
                        geoFenceBody2.setEndTime(companion.getDateString(time7, AppConstants.UTC_DATE_FORMAT));
                        if (((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchRepeatTime)).isChecked()) {
                            int selectedItemPosition2 = ((CustomSpinner) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.spDays)).getSelectedItemPosition();
                            if (selectedItemPosition2 == 0) {
                                geoFenceBody2.setTimeMode(AppConstants.GEO_FENCE_TIME_MODE_SET_TIME_ALL_DAYS);
                                arrayListOf = CollectionsKt__CollectionsKt.arrayListOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN");
                            } else if (selectedItemPosition2 == 1) {
                                geoFenceBody2.setTimeMode(AppConstants.GEO_FENCE_TIME_MODE_SET_TIME_WEEK_DAYS);
                                arrayListOf = CollectionsKt__CollectionsKt.arrayListOf("MON", "TUE", "WED", "THU", "FRI");
                            } else if (selectedItemPosition2 == 2) {
                                geoFenceBody2.setTimeMode(AppConstants.GEO_FENCE_TIME_MODE_SET_TIME_CUSTOM_DAYS);
                                geoFenceRepeatAdapter = this.dayRepeatAdapter;
                            } else if (selectedItemPosition2 == 3) {
                                geoFenceBody2.setTimeMode(AppConstants.GEO_FENCE_TIME_MODE_SET_TIME_CUSTOM_DATE_RANGE);
                                geoFenceBody2.setDaysOfWeek(null);
                                Calendar calendar4 = this.startDateCal;
                                Calendar timeCalendar24Hour = companion.timeCalendar24Hour(parseDate);
                                calendar4.set(11, timeCalendar24Hour.get(11));
                                calendar4.set(12, timeCalendar24Hour.get(12));
                                Date time8 = calendar4.getTime();
                                Intrinsics.checkNotNullExpressionValue(time8, "it.time");
                                geoFenceBody2.setStartTime(companion.getDateString(time8, AppConstants.UTC_DATE_FORMAT));
                                calendar = this.endDateCal;
                                Calendar timeCalendar24Hour2 = companion.timeCalendar24Hour(parseDate2);
                                calendar.set(11, timeCalendar24Hour2.get(11));
                                calendar.set(12, timeCalendar24Hour2.get(12));
                                Date time52 = calendar.getTime();
                                Intrinsics.checkNotNullExpressionValue(time52, "it.time");
                                geoFenceBody2.setEndTime(companion.getDateString(time52, AppConstants.UTC_DATE_FORMAT));
                            }
                            geoFenceBody2.setDaysOfWeek(arrayListOf);
                        } else {
                            str = AppConstants.GEO_FENCE_TIME_MODE_SET_TIME;
                            geoFenceBody2.setTimeMode(str);
                            arrayListOf = null;
                            geoFenceBody2.setDaysOfWeek(arrayListOf);
                        }
                    }
                }
                setSkipTimeData();
            }
        }
        if (!z || (geoFenceCommonModel = getViewModel().getGeoFenceCommonModel()) == null || (geoFenceBody = geoFenceCommonModel.getGeoFenceBody()) == null || (geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel()) == null || (geoFenceGetResponse = geoFenceCommonModel2.getGeoFenceGetResponse()) == null) {
            return;
        }
        geoFenceGetResponse.setInfinite(geoFenceBody.isInfinite());
        geoFenceGetResponse.setStartTime(geoFenceBody.getStartTime());
        geoFenceGetResponse.setEndTime(geoFenceBody.getEndTime());
        geoFenceGetResponse.setTimeMode(geoFenceBody.getTimeMode());
        geoFenceGetResponse.setDaysOfWeek(geoFenceBody.getDaysOfWeek());
    }

    /* JADX WARN: Code restructure failed: missing block: B:113:0x0208, code lost:
        if (r2.intValue() != 5) goto L51;
     */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0353  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x036d  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x03ad  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x03f0  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0430  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x048e  */
    /* JADX WARN: Removed duplicated region for block: B:173:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void setUIData() {
        boolean isBlank;
        boolean isBlank2;
        GeoFenceBody geoFenceBody;
        ArrayList<String> daysOfWeek;
        GeoFenceBody geoFenceBody2;
        GeoFenceBody geoFenceBody3;
        GeoFenceBody geoFenceBody4;
        String str;
        Pair<Long, Long> pair;
        boolean isBlank3;
        boolean isBlank4;
        GetGeoFenceResponseItem geoFenceGetResponse;
        List<String> daysOfWeek2;
        GetGeoFenceResponseItem geoFenceGetResponse2;
        GetGeoFenceResponseItem geoFenceGetResponse3;
        GetGeoFenceResponseItem geoFenceGetResponse4;
        ArrayList<String> arrayList = new ArrayList<>();
        Triple<String, String, String> triple = new Triple<>("", "", "");
        Triple<String, String, String> triple2 = new Triple<>("", "", "");
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        String str2 = null;
        Integer valueOf = geoFenceCommonModel != null ? Integer.valueOf(geoFenceCommonModel.getFenceCreationMode()) : null;
        if (valueOf == null || valueOf.intValue() != 6) {
            if (valueOf != null && valueOf.intValue() == 7) {
                hideSetTimeHomeScreen();
                GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
                this.timeMode = String.valueOf((geoFenceCommonModel2 == null || (geoFenceBody4 = geoFenceCommonModel2.getGeoFenceBody()) == null) ? null : geoFenceBody4.getTimeMode());
                GeoFenceCommonModel geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
                this.startTime = String.valueOf((geoFenceCommonModel3 == null || (geoFenceBody3 = geoFenceCommonModel3.getGeoFenceBody()) == null) ? null : geoFenceBody3.getStartTime());
                GeoFenceCommonModel geoFenceCommonModel4 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel4 != null && (geoFenceBody2 = geoFenceCommonModel4.getGeoFenceBody()) != null) {
                    str2 = geoFenceBody2.getEndTime();
                }
                this.endTime = String.valueOf(str2);
                GeoFenceCommonModel geoFenceCommonModel5 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel5 != null && (geoFenceBody = geoFenceCommonModel5.getGeoFenceBody()) != null && (daysOfWeek = geoFenceBody.getDaysOfWeek()) != null) {
                    arrayList = daysOfWeek;
                }
                String str3 = this.startTime;
                if (str3 != null) {
                    if (str3.length() > 0) {
                        isBlank2 = StringsKt__StringsJVMKt.isBlank(this.startTime);
                        if ((!isBlank2) && !Intrinsics.areEqual(this.startTime, "null")) {
                            triple = getTimeValue(this.startTime);
                            Calendar calendar = Calendar.getInstance();
                            Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
                            this.startDateCal = calendar;
                            calendar.setTimeInMillis(getTimeInDateFormat(this.startTime).getTime());
                        }
                    }
                }
                String str4 = this.endTime;
                if (str4 != null) {
                    if (str4.length() > 0) {
                        isBlank = StringsKt__StringsJVMKt.isBlank(this.endTime);
                        if ((!isBlank) && !Intrinsics.areEqual(this.endTime, "null")) {
                            triple2 = getTimeValue(this.endTime);
                            Calendar calendar2 = Calendar.getInstance();
                            Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
                            this.endDateCal = calendar2;
                            calendar2.setTimeInMillis(getTimeInDateFormat(this.endTime).getTime());
                        }
                    }
                }
            } else if (valueOf != null) {
            }
            str = this.timeMode;
            switch (str.hashCode()) {
                case -1385810073:
                    if (str.equals(AppConstants.GEO_FENCE_TIME_MODE_FULL_DAY_CUSTOM_DATE_RANGE)) {
                        ((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbYes)).setChecked(true);
                        ((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchSetTime)).setChecked(true);
                        ((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbFullDay)).setChecked(true);
                        ((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchRepeatTime)).setChecked(true);
                        showRepeatView();
                        this.spinnerSelectionPos = 3;
                        setCustomSpinnerSelection();
                        pair = new Pair<>(Long.valueOf(this.startDateCal.getTimeInMillis()), Long.valueOf(this.endDateCal.getTimeInMillis()));
                        setCustomPickerSelection(pair);
                        return;
                    }
                    return;
                case -1339174267:
                    if (str.equals(AppConstants.GEO_FENCE_TIME_MODE_SET_TIME_CUSTOM_DATE_RANGE)) {
                        ((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbYes)).setChecked(true);
                        ((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchSetTime)).setChecked(true);
                        ((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbSetTiming)).setChecked(true);
                        ((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchRepeatTime)).setChecked(true);
                        showRepeatView();
                        setStartTimeInSpinner(triple);
                        setEndTimeInSpinner(triple2);
                        this.spinnerSelectionPos = 3;
                        setCustomSpinnerSelection();
                        pair = new Pair<>(Long.valueOf(this.startDateCal.getTimeInMillis()), Long.valueOf(this.endDateCal.getTimeInMillis()));
                        setCustomPickerSelection(pair);
                        return;
                    }
                    return;
                case -1300341969:
                    if (str.equals(AppConstants.GEO_FENCE_TIME_MODE_FULL_DAY_WEEK_DAYS)) {
                        ((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbYes)).setChecked(true);
                        ((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchSetTime)).setChecked(true);
                        ((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbFullDay)).setChecked(true);
                        ((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchRepeatTime)).setChecked(true);
                        showRepeatView();
                        this.spinnerSelectionPos = 1;
                        setCustomSpinnerSelection();
                        return;
                    }
                    return;
                case -816877934:
                    if (str.equals(AppConstants.GEO_FENCE_TIME_MODE_FULL_DAY_CUSTOM_DAYS)) {
                        ((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbYes)).setChecked(true);
                        ((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchSetTime)).setChecked(true);
                        ((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbFullDay)).setChecked(true);
                        ((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchRepeatTime)).setChecked(true);
                        showRepeatView();
                        this.spinnerSelectionPos = 2;
                        setCustomSpinnerSelection();
                        setDaysOfWeek(arrayList);
                        return;
                    }
                    return;
                case -96913360:
                    if (str.equals(AppConstants.GEO_FENCE_TIME_MODE_SET_TIME_CUSTOM_DAYS)) {
                        ((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbYes)).setChecked(true);
                        ((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchSetTime)).setChecked(true);
                        ((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbSetTiming)).setChecked(true);
                        ((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchRepeatTime)).setChecked(true);
                        showRepeatView();
                        setStartTimeInSpinner(triple);
                        setEndTimeInSpinner(triple2);
                        this.spinnerSelectionPos = 2;
                        setCustomSpinnerSelection();
                        setDaysOfWeek(arrayList);
                        return;
                    }
                    return;
                case 3532159:
                    if (str.equals(AppConstants.GEO_FENCE_TIME_MODE_SKIP)) {
                        ((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbNo)).setChecked(true);
                        showSetTimeHomeScreen();
                        return;
                    }
                    return;
                case 110534465:
                    if (str.equals(AppConstants.GEO_FENCE_TIME_MODE_TODAY)) {
                        ((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbYes)).setChecked(true);
                        ((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchSetTime)).setChecked(true);
                        ((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbFullDay)).setChecked(true);
                        ((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchRepeatTime)).setChecked(false);
                        hideRepeatView();
                        return;
                    }
                    return;
                case 166327373:
                    if (str.equals(AppConstants.GEO_FENCE_TIME_MODE_SET_TIME_WEEK_DAYS)) {
                        ((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbYes)).setChecked(true);
                        ((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchSetTime)).setChecked(true);
                        ((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbSetTiming)).setChecked(true);
                        ((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchRepeatTime)).setChecked(true);
                        showRepeatView();
                        setStartTimeInSpinner(triple);
                        setEndTimeInSpinner(triple2);
                        this.spinnerSelectionPos = 1;
                        setCustomSpinnerSelection();
                        return;
                    }
                    return;
                case 901250122:
                    if (str.equals(AppConstants.GEO_FENCE_TIME_MODE_SET_TIME_ALL_DAYS)) {
                        ((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbYes)).setChecked(true);
                        ((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchSetTime)).setChecked(true);
                        ((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbSetTiming)).setChecked(true);
                        ((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchRepeatTime)).setChecked(true);
                        showRepeatView();
                        setStartTimeInSpinner(triple);
                        setEndTimeInSpinner(triple2);
                        this.spinnerSelectionPos = 0;
                        setCustomSpinnerSelection();
                        return;
                    }
                    return;
                case 1131032872:
                    if (str.equals(AppConstants.GEO_FENCE_TIME_MODE_FULL_DAY_ALL_DAYS)) {
                        ((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbYes)).setChecked(true);
                        ((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchSetTime)).setChecked(true);
                        ((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbFullDay)).setChecked(true);
                        ((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchRepeatTime)).setChecked(true);
                        showRepeatView();
                        this.spinnerSelectionPos = 0;
                        setCustomSpinnerSelection();
                        return;
                    }
                    return;
                case 1415560330:
                    if (str.equals(AppConstants.GEO_FENCE_TIME_MODE_SET_TIME)) {
                        ((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbYes)).setChecked(true);
                        ((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchSetTime)).setChecked(true);
                        ((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbSetTiming)).setChecked(true);
                        ((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchRepeatTime)).setChecked(false);
                        hideRepeatView();
                        setStartTimeInSpinner(triple);
                        setEndTimeInSpinner(triple2);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
        hideSetTimeHomeScreen();
        GeoFenceCommonModel geoFenceCommonModel6 = getViewModel().getGeoFenceCommonModel();
        this.timeMode = String.valueOf((geoFenceCommonModel6 == null || (geoFenceGetResponse4 = geoFenceCommonModel6.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse4.getTimeMode());
        GeoFenceCommonModel geoFenceCommonModel7 = getViewModel().getGeoFenceCommonModel();
        this.startTime = String.valueOf((geoFenceCommonModel7 == null || (geoFenceGetResponse3 = geoFenceCommonModel7.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse3.getStartTime());
        GeoFenceCommonModel geoFenceCommonModel8 = getViewModel().getGeoFenceCommonModel();
        if (geoFenceCommonModel8 != null && (geoFenceGetResponse2 = geoFenceCommonModel8.getGeoFenceGetResponse()) != null) {
            str2 = geoFenceGetResponse2.getEndTime();
        }
        this.endTime = String.valueOf(str2);
        GeoFenceCommonModel geoFenceCommonModel9 = getViewModel().getGeoFenceCommonModel();
        if (geoFenceCommonModel9 != null && (geoFenceGetResponse = geoFenceCommonModel9.getGeoFenceGetResponse()) != null && (daysOfWeek2 = geoFenceGetResponse.getDaysOfWeek()) != null) {
            arrayList = daysOfWeek2;
        }
        String str5 = this.startTime;
        if (str5 != null) {
            if (str5.length() > 0) {
                isBlank4 = StringsKt__StringsJVMKt.isBlank(this.startTime);
                if (!isBlank4) {
                    triple = getTimeValue(this.startTime);
                    Calendar calendar3 = Calendar.getInstance();
                    Intrinsics.checkNotNullExpressionValue(calendar3, "getInstance()");
                    this.startDateCal = calendar3;
                    calendar3.setTimeInMillis(getTimeInDateFormat(this.startTime).getTime());
                }
            }
        }
        String str6 = this.endTime;
        if (str6 != null) {
            if (str6.length() > 0) {
                isBlank3 = StringsKt__StringsJVMKt.isBlank(this.endTime);
                if (!isBlank3) {
                    triple2 = getTimeValue(this.endTime);
                    Calendar calendar4 = Calendar.getInstance();
                    Intrinsics.checkNotNullExpressionValue(calendar4, "getInstance()");
                    this.endDateCal = calendar4;
                    calendar4.setTimeInMillis(getTimeInDateFormat(this.endTime).getTime());
                }
            }
        }
        setCurrentTime();
        str = this.timeMode;
        switch (str.hashCode()) {
            case -1385810073:
                break;
            case -1339174267:
                break;
            case -1300341969:
                break;
            case -816877934:
                break;
            case -96913360:
                break;
            case 3532159:
                break;
            case 110534465:
                break;
            case 166327373:
                break;
            case 901250122:
                break;
            case 1131032872:
                break;
            case 1415560330:
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDatePicker() {
        Calendar calendar;
        CalendarConstraints build = new CalendarConstraints.Builder().setValidator(DateValidatorPointForward.now()).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…w())\n            .build()");
        MaterialDatePicker.Builder<Pair<Long, Long>> titleText = MaterialDatePicker.Builder.dateRangePicker().setTitleText("Select date");
        Intrinsics.checkNotNullExpressionValue(titleText, "dateRangePicker()\n      …tTitleText(\"Select date\")");
        titleText.setCalendarConstraints(build);
        if (((CustomSpinner) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.spDays)).getSelectedItemPosition() == 3 && (calendar = this.startDateCal) != null && this.endDateCal != null) {
            titleText.setSelection(new Pair<>(Long.valueOf(calendar.getTimeInMillis()), Long.valueOf(this.endDateCal.getTimeInMillis())));
        }
        final MaterialDatePicker<Pair<Long, Long>> build2 = titleText.build();
        Intrinsics.checkNotNullExpressionValue(build2, "datePickerBuilder.build()");
        build2.show(getChildFragmentManager(), getTag());
        build2.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() { // from class: l.d
            @Override // com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
            public final void onPositiveButtonClick(Object obj) {
                GeoFenceSetTimeFragment.m143showDatePicker$lambda9(MaterialDatePicker.this, this, (Pair) obj);
            }
        });
        build2.addOnNegativeButtonClickListener(c.f12472a);
        build2.addOnCancelListener(b.f12471a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showDatePicker$lambda-10  reason: not valid java name */
    public static final void m141showDatePicker$lambda10(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showDatePicker$lambda-11  reason: not valid java name */
    public static final void m142showDatePicker$lambda11(DialogInterface dialogInterface) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showDatePicker$lambda-9  reason: not valid java name */
    public static final void m143showDatePicker$lambda9(MaterialDatePicker datePicker, GeoFenceSetTimeFragment this$0, Pair it) {
        Intrinsics.checkNotNullParameter(datePicker, "$datePicker");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Pair pair = (Pair) datePicker.getSelection();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.ONLY_DATE_FORMAT, AppUtil.Companion.getDefaultLocale());
        String format = simpleDateFormat.format(pair != null ? (Long) pair.first : null);
        Intrinsics.checkNotNullExpressionValue(format, "dateFormat.format(selectedDates?.first)");
        this$0.startDate = format;
        String format2 = simpleDateFormat.format(pair != null ? (Long) pair.second : null);
        Intrinsics.checkNotNullExpressionValue(format2, "dateFormat.format(selectedDates?.second)");
        this$0.endDate = format2;
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        this$0.startDateCal = calendar;
        Long l2 = pair != null ? (Long) pair.first : null;
        Intrinsics.checkNotNull(l2);
        calendar.setTimeInMillis(l2.longValue());
        Calendar calendar2 = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
        this$0.endDateCal = calendar2;
        S s2 = pair.second;
        Intrinsics.checkNotNull(s2);
        calendar2.setTimeInMillis(((Number) s2).longValue());
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.setCustomPickerSelection(it);
    }

    private final void showRepeatView() {
        AppCompatTextView tvSelectDays = (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvSelectDays);
        Intrinsics.checkNotNullExpressionValue(tvSelectDays, "tvSelectDays");
        ExtensionsKt.show(tvSelectDays);
        MaterialCardView cvDays = (MaterialCardView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.cvDays);
        Intrinsics.checkNotNullExpressionValue(cvDays, "cvDays");
        ExtensionsKt.show(cvDays);
        int selectedItemPosition = ((CustomSpinner) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.spDays)).getSelectedItemPosition();
        if (selectedItemPosition == 2) {
            RecyclerView rvDays = (RecyclerView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rvDays);
            Intrinsics.checkNotNullExpressionValue(rvDays, "rvDays");
            ExtensionsKt.show(rvDays);
        } else if (selectedItemPosition == 3) {
            RecyclerView rvDays2 = (RecyclerView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rvDays);
            Intrinsics.checkNotNullExpressionValue(rvDays2, "rvDays");
            ExtensionsKt.hide(rvDays2);
            AppCompatTextView tvSelectedDateRange = (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvSelectedDateRange);
            Intrinsics.checkNotNullExpressionValue(tvSelectedDateRange, "tvSelectedDateRange");
            ExtensionsKt.show(tvSelectedDateRange);
            return;
        } else {
            RecyclerView rvDays3 = (RecyclerView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rvDays);
            Intrinsics.checkNotNullExpressionValue(rvDays3, "rvDays");
            ExtensionsKt.hide(rvDays3);
        }
        AppCompatTextView tvSelectedDateRange2 = (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvSelectedDateRange);
        Intrinsics.checkNotNullExpressionValue(tvSelectedDateRange2, "tvSelectedDateRange");
        ExtensionsKt.hide(tvSelectedDateRange2);
    }

    private final void showSetTimeHomeScreen() {
        ConstraintLayout clSetTimeHome = (ConstraintLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.clSetTimeHome);
        Intrinsics.checkNotNullExpressionValue(clSetTimeHome, "clSetTimeHome");
        ExtensionsKt.show(clSetTimeHome);
        ConstraintLayout layoutSetTime = (ConstraintLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.layoutSetTime);
        Intrinsics.checkNotNullExpressionValue(layoutSetTime, "layoutSetTime");
        ExtensionsKt.hide(layoutSetTime);
    }

    private final void showTimeSelectionLayout() {
        RelativeLayout rlTime = (RelativeLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rlTime);
        Intrinsics.checkNotNullExpressionValue(rlTime, "rlTime");
        ExtensionsKt.show(rlTime);
    }

    private final boolean timeValidate() {
        boolean isBlank;
        boolean isBlank2;
        Context requireContext;
        String string;
        String str;
        setStartTime();
        setEndTime();
        isBlank = StringsKt__StringsJVMKt.isBlank(this.startTime);
        if (isBlank) {
            if (this.startTime.length() == 0) {
                requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                string = getString(R.string.select_start_time);
                str = "getString(R.string.select_start_time)";
                Intrinsics.checkNotNullExpressionValue(string, str);
                ExtensionsKt.showToast(requireContext, string);
                return false;
            }
        }
        isBlank2 = StringsKt__StringsJVMKt.isBlank(this.endTime);
        if (isBlank2) {
            if (this.endTime.length() == 0) {
                requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                string = getString(R.string.select_end_time);
                str = "getString(R.string.select_end_time)";
                Intrinsics.checkNotNullExpressionValue(string, str);
                ExtensionsKt.showToast(requireContext, string);
                return false;
            }
        }
        AppUtil.Companion companion = AppUtil.Companion;
        if (companion.isPastTime(this.startTime + TokenParser.SP + setStartTimeAMPM())) {
            if (!companion.isSameTime(this.startTime + TokenParser.SP + setStartTimeAMPM(), this.endTime + TokenParser.SP + setEndTimeAMPM())) {
                if (!Intrinsics.areEqual(this.startTime + TokenParser.SP + setStartTimeAMPM(), this.endTime + TokenParser.SP + setEndTimeAMPM())) {
                    if (!companion.isTimeGreaterThanStartTime(this.startTime + TokenParser.SP + setStartTimeAMPM(), this.endTime + TokenParser.SP + setEndTimeAMPM())) {
                        return true;
                    }
                    requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    string = getString(R.string.end_time_should_after_end_time);
                    str = "getString(R.string.end_time_should_after_end_time)";
                }
            }
            requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            string = getString(R.string.start_end_time_can_not_be_same);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.start_end_time_can_not_be_same)");
            ExtensionsKt.showToast(requireContext, string);
            return false;
        }
        requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        string = getString(R.string.start_time_can_not_past_time);
        str = "getString(R.string.start_time_can_not_past_time)";
        Intrinsics.checkNotNullExpressionValue(string, str);
        ExtensionsKt.showToast(requireContext, string);
        return false;
    }

    private final boolean timeValidate1() {
        boolean isBlank;
        boolean isBlank2;
        Context requireContext;
        String string;
        String str;
        setStartTime();
        setEndTime();
        isBlank = StringsKt__StringsJVMKt.isBlank(this.startTime);
        if (isBlank) {
            if (this.startTime.length() == 0) {
                requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                string = getString(R.string.select_start_time);
                str = "getString(R.string.select_start_time)";
                Intrinsics.checkNotNullExpressionValue(string, str);
                ExtensionsKt.showToast(requireContext, string);
                return false;
            }
        }
        isBlank2 = StringsKt__StringsJVMKt.isBlank(this.endTime);
        if (isBlank2) {
            if (this.endTime.length() == 0) {
                requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                string = getString(R.string.select_end_time);
                str = "getString(R.string.select_end_time)";
                Intrinsics.checkNotNullExpressionValue(string, str);
                ExtensionsKt.showToast(requireContext, string);
                return false;
            }
        }
        AppUtil.Companion companion = AppUtil.Companion;
        if (!companion.isSameTime(this.startTime + TokenParser.SP + setStartTimeAMPM(), this.endTime + TokenParser.SP + setEndTimeAMPM())) {
            if (!Intrinsics.areEqual(this.startTime + TokenParser.SP + setStartTimeAMPM(), this.endTime + TokenParser.SP + setEndTimeAMPM())) {
                if (companion.isTimeGreaterThanStartTime(this.startTime + TokenParser.SP + setStartTimeAMPM(), this.endTime + TokenParser.SP + setEndTimeAMPM())) {
                    requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    string = getString(R.string.end_time_should_after_end_time);
                    str = "getString(R.string.end_time_should_after_end_time)";
                    Intrinsics.checkNotNullExpressionValue(string, str);
                    ExtensionsKt.showToast(requireContext, string);
                    return false;
                }
                return true;
            }
        }
        requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        string = getString(R.string.start_end_time_can_not_be_same);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.start_end_time_can_not_be_same)");
        ExtensionsKt.showToast(requireContext, string);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateCustomDateValue(String str) {
        if (Intrinsics.areEqual(str, getString(R.string.lbl_custom_date_range))) {
            Calendar calendar = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
            this.startDateCal = calendar;
            Calendar calendar2 = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
            this.endDateCal = calendar2;
        }
        ArrayList<String> arrayList = this.periods;
        arrayList.set(arrayList.size() - 1, str);
        ArrayAdapter<String> arrayAdapter = this.periodAdapter;
        if (arrayAdapter != null) {
            arrayAdapter.notifyDataSetChanged();
        }
    }

    private final boolean validate() {
        if (!((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbNo)).isChecked()) {
            if (!((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbYes)).isChecked()) {
                return false;
            }
            if (((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchSetTime)).isChecked()) {
                if (((RadioGroup) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rgSetDayTime)).getCheckedRadioButtonId() != -1) {
                    if (((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbFullDay)).isChecked()) {
                        if (((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchRepeatTime)).isChecked()) {
                            return repeatDaysValidate();
                        }
                    } else if (((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbSetTiming)).isChecked()) {
                        return ((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchRepeatTime)).isChecked() ? timeValidate1() : timeValidate();
                    }
                }
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                String string = getString(R.string.select_full_day_set_timing);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.select_full_day_set_timing)");
                ExtensionsKt.showToast(requireContext, string);
                return false;
            }
        }
        return true;
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

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(@Nullable CompoundButton compoundButton, boolean z) {
        int i2 = com.psa.mym.mycitroenconnect.R.id.switchRepeatTime;
        if (Intrinsics.areEqual(compoundButton, (SwitchMaterial) _$_findCachedViewById(i2))) {
            if (((SwitchMaterial) _$_findCachedViewById(i2)).isChecked()) {
                showRepeatView();
            } else {
                hideRepeatView();
            }
        }
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(@Nullable RadioGroup radioGroup, int i2) {
        if (!Intrinsics.areEqual(radioGroup, (RadioGroup) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rgSetTime))) {
            if (Intrinsics.areEqual(radioGroup, (RadioGroup) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rgSetDayTime))) {
                if (i2 == R.id.rbFullDay) {
                    Logger.INSTANCE.e("Full Day Time Set");
                    hideTimeSelectionLayout();
                    return;
                } else if (i2 != R.id.rbSetTiming) {
                    return;
                } else {
                    Logger.INSTANCE.e("Custom Timing is set");
                    showTimeSelectionLayout();
                    return;
                }
            }
            return;
        }
        if (i2 == R.id.rbNo) {
            Logger.INSTANCE.e("Set Time: No is checked");
            if (!((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbNo)).isChecked()) {
                hideSetTimeHomeScreen();
                return;
            }
        } else if (i2 != R.id.rbYes) {
            return;
        } else {
            Logger.INSTANCE.e("Set Time: Yes is checked");
            if (((MaterialRadioButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rbYes)).isChecked()) {
                hideSetTimeHomeScreen();
                ((SwitchMaterial) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.switchSetTime)).setChecked(true);
                return;
            }
        }
        showSetTimeHomeScreen();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        View findViewById;
        if (SystemClock.elapsedRealtime() - this.lastClickTime < 1500) {
            return;
        }
        this.lastClickTime = SystemClock.elapsedRealtime();
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivDown))) {
            findViewById = _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.spDays);
        } else {
            int i2 = com.psa.mym.mycitroenconnect.R.id.spinStartTimeHr;
            View _$_findCachedViewById = _$_findCachedViewById(i2);
            int i3 = com.psa.mym.mycitroenconnect.R.id.ivSpinner;
            if (!Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById.findViewById(i3))) {
                i2 = com.psa.mym.mycitroenconnect.R.id.spinEndTimeHr;
                if (!Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(i2).findViewById(i3))) {
                    i2 = com.psa.mym.mycitroenconnect.R.id.spinStartTimeMin;
                    if (!Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(i2).findViewById(i3))) {
                        i2 = com.psa.mym.mycitroenconnect.R.id.spinEndTimeMin;
                        if (!Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(i2).findViewById(i3))) {
                            int i4 = com.psa.mym.mycitroenconnect.R.id.switchSetTime;
                            boolean z = true;
                            if (Intrinsics.areEqual(view, (SwitchMaterial) _$_findCachedViewById(i4))) {
                                ((MaterialRadioButton) _$_findCachedViewById(((SwitchMaterial) _$_findCachedViewById(i4)).isChecked() ? com.psa.mym.mycitroenconnect.R.id.rbYes : com.psa.mym.mycitroenconnect.R.id.rbNo)).setChecked(true);
                                return;
                            }
                            int i5 = com.psa.mym.mycitroenconnect.R.id.switchRepeatTime;
                            if (Intrinsics.areEqual(view, (SwitchMaterial) _$_findCachedViewById(i5))) {
                                Logger.INSTANCE.e("Switch Repeat Time :" + ((SwitchMaterial) _$_findCachedViewById(i5)).isChecked());
                                if (((SwitchMaterial) _$_findCachedViewById(i5)).isChecked()) {
                                    showRepeatView();
                                    return;
                                } else {
                                    hideRepeatView();
                                    return;
                                }
                            } else if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSaveContinue)) && validate()) {
                                GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
                                if (geoFenceCommonModel == null || geoFenceCommonModel.getFenceCreationMode() != 6) {
                                    z = false;
                                }
                                setTimeInModel(z);
                                GeoFenceActivity geoFenceActivity = this.parentActivity;
                                if (geoFenceActivity != null) {
                                    geoFenceActivity.navigateToSummaryFragment();
                                    return;
                                }
                                return;
                            } else {
                                return;
                            }
                        }
                    }
                }
            }
            findViewById = _$_findCachedViewById(i2).findViewById(com.psa.mym.mycitroenconnect.R.id.spinner);
        }
        ((CustomSpinner) findViewById).performClick();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getBundleData();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_geo_fence_set_time, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setUIData();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initView();
    }
}
