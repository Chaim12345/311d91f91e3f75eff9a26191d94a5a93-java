package com.psa.mym.mycitroenconnect.controller.fragments.my_trips;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class TripFragment$initSpinner$2 implements AdapterView.OnItemSelectedListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TripFragment f10570a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TripFragment$initSpinner$2(TripFragment tripFragment) {
        this.f10570a = tripFragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemSelected$lambda-0  reason: not valid java name */
    public static final void m155onItemSelected$lambda0(TripFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppConstants.Companion companion = AppConstants.Companion;
        AppUtil.Companion companion2 = AppUtil.Companion;
        companion.setStartDate(companion2.getCurrentDateString());
        companion.setEndDate(companion2.getCurrentDateString());
        this$0.getFuelReport();
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(@Nullable AdapterView<?> adapterView, @Nullable View view, int i2, long j2) {
        Pair<String, String> currentWeekDates;
        if (i2 != 0) {
            if (i2 == 1) {
                currentWeekDates = AppUtil.Companion.getCurrentWeekDates();
            } else if (i2 == 2) {
                currentWeekDates = AppUtil.Companion.getLastWeekDate();
            } else if (i2 == 3) {
                currentWeekDates = AppUtil.Companion.getCurrentMonthDates();
            } else if (i2 != 4) {
                if (i2 == 5) {
                    this.f10570a.showDateRangePickerDialog();
                }
                this.f10570a.spinnerSelectionPos = i2;
            } else {
                currentWeekDates = AppUtil.Companion.getLastMonthDates();
            }
            AppConstants.Companion companion = AppConstants.Companion;
            companion.setStartDate(currentWeekDates.getFirst());
            companion.setEndDate(currentWeekDates.getSecond());
            this.f10570a.getFuelReport();
            companion.setDateChanged(true);
        } else {
            Handler handler = new Handler(Looper.getMainLooper());
            final TripFragment tripFragment = this.f10570a;
            handler.postDelayed(new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.my_trips.e
                @Override // java.lang.Runnable
                public final void run() {
                    TripFragment$initSpinner$2.m155onItemSelected$lambda0(TripFragment.this);
                }
            }, 300L);
            AppConstants.Companion.setDateChanged(true);
        }
        TripFragment tripFragment2 = this.f10570a;
        String string = tripFragment2.getString(R.string.custom_dates);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.custom_dates)");
        tripFragment2.updateCustomDateValue(string);
        this.f10570a.spinnerSelectionPos = i2;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(@Nullable AdapterView<?> adapterView) {
    }
}
