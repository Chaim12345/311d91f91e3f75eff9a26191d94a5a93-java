package com.psa.mym.mycitroenconnect.controller.fragments.my_car;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.BusBaseFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.adapters.MyCarStatusAdapter;
import com.psa.mym.mycitroenconnect.controller.adapters.MyCarStatusAlertHeaderAdapter;
import com.psa.mym.mycitroenconnect.controller.adapters.MyVehicleStatusAdapter;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.VehicleStatus;
import com.psa.mym.mycitroenconnect.model.dashboard.DashboardResponse;
import com.psa.mym.mycitroenconnect.model.my_car.MyCarStatus;
import com.psa.mym.mycitroenconnect.model.my_car.MyVehicleStatus;
import com.psa.mym.mycitroenconnect.model.my_car.VehicleAlerts;
import com.psa.mym.mycitroenconnect.model.notification.Notification;
import com.psa.mym.mycitroenconnect.model.notification.NotificationResponse;
import com.psa.mym.mycitroenconnect.services.DashboardService;
import com.psa.mym.mycitroenconnect.services.FCMService;
import com.psa.mym.mycitroenconnect.services.SnapShotService;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.Skeleton;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayoutUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class MyCarStatusFragment extends BusBaseFragment implements View.OnClickListener {
    @Nullable
    private CarFragment mCarFragment;
    @Nullable
    private MyCarStatusAdapter myCarStatusAdapter;
    @Nullable
    private MyCarStatusAlertHeaderAdapter myCarStatusAlertHeaderAdapter;
    @Nullable
    private MyVehicleStatusAdapter myVehicleStatusAdapter;
    @Nullable
    private View rootView;
    @Nullable
    private RecyclerView rvAlert;
    @Nullable
    private RecyclerView rvCarStatus;
    @Nullable
    private RecyclerView rvVehicleStatus;
    private Skeleton skeleton;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private List<MyCarStatus> mCarStatusList = new ArrayList();
    @NotNull
    private final List<MyVehicleStatus> myVehicleStatus = new ArrayList();
    @NotNull
    private List<VehicleAlerts> mVehicleAlerts = new ArrayList();
    @NotNull
    private String vehicleType = "";

    private final void displayData() {
        Skeleton skeleton = this.skeleton;
        if (skeleton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skeleton");
            skeleton = null;
        }
        ExtensionsKt.showData$default(skeleton, 0L, 1, null);
        CarFragment carFragment = this.mCarFragment;
        if (carFragment != null) {
            carFragment.displayData();
        }
    }

    private final void displayLoading() {
        ConstraintLayout clParent = (ConstraintLayout) _$_findCachedViewById(R.id.clParent);
        Intrinsics.checkNotNullExpressionValue(clParent, "clParent");
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Skeleton createSkeleton = SkeletonLayoutUtils.createSkeleton(clParent, ExtensionsKt.skeletonConfig(requireContext));
        createSkeleton.showSkeleton();
        this.skeleton = createSkeleton;
    }

    private final void getNotificationList() {
        FCMService fCMService = new FCMService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        FCMService.getNotificationList$default(fCMService, requireActivity, companion.getVinNumber(requireContext), 0, 0, 0, 28, null);
    }

    private final void getSnapShotVehicleStatus() {
        SnapShotService snapShotService = new SnapShotService();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        snapShotService.getVehicleStatus(requireContext, companion.getVinNumber(requireContext2));
    }

    private final void initCarStatus() {
        this.myCarStatusAdapter = new MyCarStatusAdapter(this.mCarStatusList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.my_car.MyCarStatusFragment$initCarStatus$1
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i2) {
                return (i2 == 1 || i2 == 2) ? 1 : 2;
            }
        });
        View view = this.rootView;
        RecyclerView recyclerView = view != null ? (RecyclerView) view.findViewById(uat.psa.mym.mycitroenconnect.R.id.rvCarStatus) : null;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(gridLayoutManager);
        }
        if (recyclerView != null) {
            recyclerView.setAdapter(this.myCarStatusAdapter);
        }
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
        }
        this.rvCarStatus = recyclerView;
    }

    private final void initVehicleAlert() {
        AppCompatTextView tvAlertLbl = (AppCompatTextView) _$_findCachedViewById(R.id.tvAlertLbl);
        Intrinsics.checkNotNullExpressionValue(tvAlertLbl, "tvAlertLbl");
        ExtensionsKt.show(tvAlertLbl);
        RecyclerView recyclerView = this.rvAlert;
        if (recyclerView != null) {
            ExtensionsKt.show(recyclerView);
        }
        MyCarStatusAlertHeaderAdapter myCarStatusAlertHeaderAdapter = this.myCarStatusAlertHeaderAdapter;
        if (myCarStatusAlertHeaderAdapter != null) {
            if (myCarStatusAlertHeaderAdapter != null) {
                myCarStatusAlertHeaderAdapter.updateVehicleAlertList(this.mVehicleAlerts);
                return;
            }
            return;
        }
        this.myCarStatusAlertHeaderAdapter = new MyCarStatusAlertHeaderAdapter(this.mVehicleAlerts);
        View view = this.rootView;
        RecyclerView recyclerView2 = view != null ? (RecyclerView) view.findViewById(uat.psa.mym.mycitroenconnect.R.id.rvAlert) : null;
        if (recyclerView2 != null) {
            recyclerView2.setLayoutManager(new LinearLayoutManager(requireContext()));
        }
        if (recyclerView2 != null) {
            recyclerView2.setAdapter(this.myCarStatusAlertHeaderAdapter);
        }
        if (recyclerView2 != null) {
            recyclerView2.setHasFixedSize(true);
        }
        this.rvAlert = recyclerView2;
    }

    private final void initVehicleStatus() {
        AppCompatTextView tvVehicleStatusLbl = (AppCompatTextView) _$_findCachedViewById(R.id.tvVehicleStatusLbl);
        Intrinsics.checkNotNullExpressionValue(tvVehicleStatusLbl, "tvVehicleStatusLbl");
        ExtensionsKt.show(tvVehicleStatusLbl);
        MyVehicleStatusAdapter myVehicleStatusAdapter = this.myVehicleStatusAdapter;
        if (myVehicleStatusAdapter != null) {
            if (myVehicleStatusAdapter != null) {
                myVehicleStatusAdapter.updateList(this.myVehicleStatus);
                return;
            }
            return;
        }
        this.myVehicleStatusAdapter = new MyVehicleStatusAdapter(this.myVehicleStatus);
        View view = this.rootView;
        RecyclerView recyclerView = view != null ? (RecyclerView) view.findViewById(uat.psa.mym.mycitroenconnect.R.id.rvVehicleStatus) : null;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        }
        if (recyclerView != null) {
            recyclerView.setAdapter(this.myVehicleStatusAdapter);
        }
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
        }
        if (recyclerView != null) {
            ExtensionsKt.show(recyclerView);
        }
        this.rvVehicleStatus = recyclerView;
    }

    private final void initViews() {
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.vehicleType = companion.getVehicleType(requireContext);
        initCarStatus();
    }

    private final int isCarStatusExist(String str) {
        if (!(!this.mCarStatusList.isEmpty()) || this.mCarStatusList.size() <= 1) {
            return -1;
        }
        int i2 = 0;
        for (Object obj : this.mCarStatusList) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            if (Intrinsics.areEqual(((MyCarStatus) obj).getTitle(), str)) {
                return i2;
            }
            i2 = i3;
        }
        return -1;
    }

    private final int isVehicleAlertExist(String str) {
        if (!(!this.mVehicleAlerts.isEmpty()) || this.mVehicleAlerts.size() <= 0) {
            return -1;
        }
        int i2 = 0;
        for (Object obj : this.mVehicleAlerts) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            if (Intrinsics.areEqual(((VehicleAlerts) obj).getNotifPriority(), str)) {
                return i2;
            }
            i2 = i3;
        }
        return -1;
    }

    private final void prepareVehicleAlertList(List<Notification> list) {
        List mutableListOf;
        VehicleAlerts vehicleAlerts;
        List mutableListOf2;
        List mutableListOf3;
        List mutableListOf4;
        this.mVehicleAlerts.clear();
        for (Notification notification : list) {
            int isVehicleAlertExist = isVehicleAlertExist(notification.getPriority());
            String priority = notification.getPriority();
            switch (priority.hashCode()) {
                case 49:
                    if (priority.equals("1")) {
                        if (isVehicleAlertExist == -1) {
                            String priority2 = notification.getPriority();
                            mutableListOf = CollectionsKt__CollectionsKt.mutableListOf(notification);
                            vehicleAlerts = new VehicleAlerts(1, uat.psa.mym.mycitroenconnect.R.color.dark_red, uat.psa.mym.mycitroenconnect.R.color.white, "Critical Alerts", mutableListOf, false, priority2);
                            this.mVehicleAlerts.add(vehicleAlerts);
                            break;
                        } else {
                            this.mVehicleAlerts.get(isVehicleAlertExist).getAlerts().add(notification);
                            this.mVehicleAlerts.get(isVehicleAlertExist).setAlertCounts(this.mVehicleAlerts.get(isVehicleAlertExist).getAlerts().size());
                            break;
                        }
                    } else {
                        break;
                    }
                case 50:
                    if (priority.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                        if (isVehicleAlertExist == -1) {
                            String priority3 = notification.getPriority();
                            mutableListOf2 = CollectionsKt__CollectionsKt.mutableListOf(notification);
                            vehicleAlerts = new VehicleAlerts(1, uat.psa.mym.mycitroenconnect.R.color.dark_yellow, uat.psa.mym.mycitroenconnect.R.color.dark_grey, "General Alerts", mutableListOf2, false, priority3);
                            this.mVehicleAlerts.add(vehicleAlerts);
                            break;
                        } else {
                            this.mVehicleAlerts.get(isVehicleAlertExist).getAlerts().add(notification);
                            this.mVehicleAlerts.get(isVehicleAlertExist).setAlertCounts(this.mVehicleAlerts.get(isVehicleAlertExist).getAlerts().size());
                            break;
                        }
                    } else {
                        break;
                    }
                case 51:
                    if (priority.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                        if (isVehicleAlertExist == -1) {
                            String priority4 = notification.getPriority();
                            mutableListOf3 = CollectionsKt__CollectionsKt.mutableListOf(notification);
                            vehicleAlerts = new VehicleAlerts(1, uat.psa.mym.mycitroenconnect.R.color.dark_yellow, uat.psa.mym.mycitroenconnect.R.color.dark_grey, "Maintenance Alerts", mutableListOf3, false, priority4);
                            this.mVehicleAlerts.add(vehicleAlerts);
                            break;
                        } else {
                            this.mVehicleAlerts.get(isVehicleAlertExist).getAlerts().add(notification);
                            this.mVehicleAlerts.get(isVehicleAlertExist).setAlertCounts(this.mVehicleAlerts.get(isVehicleAlertExist).getAlerts().size());
                            break;
                        }
                    } else {
                        break;
                    }
                case 52:
                    if (priority.equals("4")) {
                        if (isVehicleAlertExist == -1) {
                            String priority5 = notification.getPriority();
                            mutableListOf4 = CollectionsKt__CollectionsKt.mutableListOf(notification);
                            vehicleAlerts = new VehicleAlerts(1, uat.psa.mym.mycitroenconnect.R.color.dark_yellow, uat.psa.mym.mycitroenconnect.R.color.dark_grey, "Other Alerts", mutableListOf4, false, priority5);
                            this.mVehicleAlerts.add(vehicleAlerts);
                            break;
                        } else {
                            this.mVehicleAlerts.get(isVehicleAlertExist).getAlerts().add(notification);
                            this.mVehicleAlerts.get(isVehicleAlertExist).setAlertCounts(this.mVehicleAlerts.get(isVehicleAlertExist).getAlerts().size());
                            break;
                        }
                    } else {
                        break;
                    }
            }
        }
    }

    private final void setMileageValue(String str) {
        updateMileageValue(str);
        updateCarStatusList();
        getNotificationList();
    }

    private final void updateBatteryValue(String str) {
        String string;
        String str2;
        if (Intrinsics.areEqual(this.vehicleType, AppConstants.ICE)) {
            string = getString(uat.psa.mym.mycitroenconnect.R.string.current_fuel);
            str2 = "{\n            getString(…g.current_fuel)\n        }";
        } else {
            string = getString(uat.psa.mym.mycitroenconnect.R.string.battery);
            str2 = "{\n            getString(…string.battery)\n        }";
        }
        Intrinsics.checkNotNullExpressionValue(string, str2);
        int i2 = Intrinsics.areEqual(this.vehicleType, AppConstants.ICE) ? uat.psa.mym.mycitroenconnect.R.drawable.ic_current_fuel_level : uat.psa.mym.mycitroenconnect.R.drawable.ic_battery_red;
        int isCarStatusExist = isCarStatusExist(string);
        if (isCarStatusExist == -1) {
            this.mCarStatusList.add(new MyCarStatus(string, str, "%", i2));
        } else {
            this.mCarStatusList.get(isCarStatusExist).setValue(str);
        }
    }

    private final void updateCarStatusList() {
        MyCarStatusAdapter myCarStatusAdapter = this.myCarStatusAdapter;
        if (myCarStatusAdapter != null) {
            myCarStatusAdapter.updateList(this.mCarStatusList);
        }
    }

    private final void updateDteValue(String str) {
        String string;
        String str2;
        if (Intrinsics.areEqual(this.vehicleType, AppConstants.ICE)) {
            string = getString(uat.psa.mym.mycitroenconnect.R.string.dte);
            str2 = "{\n            getString(R.string.dte)\n        }";
        } else {
            string = getString(uat.psa.mym.mycitroenconnect.R.string.txt_km_range);
            str2 = "{\n            getString(…g.txt_km_range)\n        }";
        }
        Intrinsics.checkNotNullExpressionValue(string, str2);
        String string2 = getString(uat.psa.mym.mycitroenconnect.R.string.dte);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.dte)");
        int isCarStatusExist = isCarStatusExist(string2);
        if (isCarStatusExist != -1) {
            this.mCarStatusList.get(isCarStatusExist).setValue(str);
            return;
        }
        List<MyCarStatus> list = this.mCarStatusList;
        String string3 = getString(uat.psa.mym.mycitroenconnect.R.string.lbl_km_value);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.lbl_km_value)");
        list.add(new MyCarStatus(string, str, string3, uat.psa.mym.mycitroenconnect.R.drawable.ic_range));
    }

    private final void updateMileageValue(String str) {
        MyCarStatus myCarStatus;
        List<MyCarStatus> list;
        MyCarStatus myCarStatus2;
        if (Intrinsics.areEqual(this.vehicleType, AppConstants.ICE)) {
            String string = getString(uat.psa.mym.mycitroenconnect.R.string.average_fuel_economy);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.average_fuel_economy)");
            int isCarStatusExist = isCarStatusExist(string);
            if (isCarStatusExist != -1) {
                myCarStatus = this.mCarStatusList.get(isCarStatusExist);
                myCarStatus.setValue(str);
                return;
            }
            list = this.mCarStatusList;
            String string2 = getString(uat.psa.mym.mycitroenconnect.R.string.average_fuel_economy);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.average_fuel_economy)");
            String string3 = getString(uat.psa.mym.mycitroenconnect.R.string.kmpl);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.kmpl)");
            myCarStatus2 = new MyCarStatus(string2, str, string3, uat.psa.mym.mycitroenconnect.R.drawable.ic_range);
            list.add(myCarStatus2);
        }
        String string4 = getString(uat.psa.mym.mycitroenconnect.R.string.average_power_consumption);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.average_power_consumption)");
        int isCarStatusExist2 = isCarStatusExist(string4);
        if (isCarStatusExist2 != -1) {
            myCarStatus = this.mCarStatusList.get(isCarStatusExist2);
            str = ExtensionsKt.toTwoDecimal(Double.parseDouble(str) / 1000);
            myCarStatus.setValue(str);
            return;
        }
        list = this.mCarStatusList;
        String string5 = getString(uat.psa.mym.mycitroenconnect.R.string.average_power_consumption);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.average_power_consumption)");
        String twoDecimal = ExtensionsKt.toTwoDecimal(Double.parseDouble(str) / 1000);
        String string6 = getString(uat.psa.mym.mycitroenconnect.R.string.kwh_km);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.kwh_km)");
        myCarStatus2 = new MyCarStatus(string5, twoDecimal, string6, uat.psa.mym.mycitroenconnect.R.drawable.ic_range);
        list.add(myCarStatus2);
    }

    private final void updateOdoMeterValue(String str) {
        String string = getString(uat.psa.mym.mycitroenconnect.R.string.odometer_reading);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.odometer_reading)");
        int isCarStatusExist = isCarStatusExist(string);
        if (isCarStatusExist == -1) {
            List<MyCarStatus> list = this.mCarStatusList;
            String string2 = getString(uat.psa.mym.mycitroenconnect.R.string.odometer_reading);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.odometer_reading)");
            String string3 = getString(uat.psa.mym.mycitroenconnect.R.string.lbl_kms_value);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.lbl_kms_value)");
            list.add(0, new MyCarStatus(string2, str, string3, uat.psa.mym.mycitroenconnect.R.drawable.ic_avg_speed));
        } else {
            this.mCarStatusList.get(isCarStatusExist).setValue(str);
        }
        updateCarStatusList();
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x01d8, code lost:
        r4 = r17.getRearLeftDoorEvent();
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x01dc, code lost:
        if (r4 == null) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x01de, code lost:
        r4 = r4.toLowerCase(java.util.Locale.ROOT);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, "this as java.lang.String).toLowerCase(Locale.ROOT)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x01e8, code lost:
        r4 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x01ed, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r4, "open") == false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x01f0, code lost:
        r4 = r17.getRearRightDoorEvent();
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x01f4, code lost:
        if (r4 == null) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x01f6, code lost:
        r12 = r4.toLowerCase(java.util.Locale.ROOT);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, "this as java.lang.String).toLowerCase(Locale.ROOT)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0203, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r12, "open") == false) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x013b, code lost:
        if (r4 == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x015f, code lost:
        if (r4 == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0183, code lost:
        if (r4 == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01a1, code lost:
        if (r4 != false) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x01a4, code lost:
        if (r5 == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x01a6, code lost:
        r4 = r17.getDriverDoorEvent();
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x01aa, code lost:
        if (r4 == null) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x01ac, code lost:
        r4 = r4.toLowerCase(java.util.Locale.ROOT);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, "this as java.lang.String).toLowerCase(Locale.ROOT)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01b6, code lost:
        r4 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x01bd, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r4, "open") == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x01c0, code lost:
        r4 = r17.getCoDriverDoorEvent();
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01c4, code lost:
        if (r4 == null) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01c6, code lost:
        r4 = r4.toLowerCase(java.util.Locale.ROOT);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, "this as java.lang.String).toLowerCase(Locale.ROOT)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01d0, code lost:
        r4 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x01d5, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r4, "open") == false) goto L42;
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0197  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void updateVehicleStatus(VehicleStatus vehicleStatus) {
        MyVehicleStatus myVehicleStatus;
        MyVehicleStatus myVehicleStatus2;
        String driverDoorEvent;
        String coDriverDoorEvent;
        String rearLeftDoorEvent;
        String rearRightDoorEvent;
        boolean isBlank;
        boolean z;
        boolean isBlank2;
        boolean z2;
        boolean isBlank3;
        boolean z3;
        boolean isBlank4;
        String str;
        String str2;
        this.myVehicleStatus.clear();
        CarFragment carFragment = this.mCarFragment;
        if (carFragment != null) {
            carFragment.updateChargingStatus(vehicleStatus.getChargingStatus());
        }
        List<MyVehicleStatus> list = this.myVehicleStatus;
        String str3 = null;
        if (vehicleStatus.getIgnitionStatus() != null) {
            String ignitionStatus = vehicleStatus.getIgnitionStatus();
            if (ignitionStatus != null) {
                str2 = ignitionStatus.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            } else {
                str2 = null;
            }
            String lowerCase = AppConstants.IGNITION_STATUS_CONTACT.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            if (Intrinsics.areEqual(str2, lowerCase)) {
                String string = getString(uat.psa.mym.mycitroenconnect.R.string.vehicle);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.vehicle)");
                String string2 = getString(uat.psa.mym.mycitroenconnect.R.string.lbl_on);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.lbl_on)");
                myVehicleStatus = new MyVehicleStatus(string, string2, uat.psa.mym.mycitroenconnect.R.color.special_color_1, uat.psa.mym.mycitroenconnect.R.drawable.ic_car_red);
                list.add(myVehicleStatus);
                List<MyVehicleStatus> list2 = this.myVehicleStatus;
                if (vehicleStatus.getAcSwitchStatus() != null) {
                    String acSwitchStatus = vehicleStatus.getAcSwitchStatus();
                    if (acSwitchStatus != null) {
                        str = acSwitchStatus.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    } else {
                        str = null;
                    }
                    String lowerCase2 = AppConstants.AC_STATUS_ON.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    if (Intrinsics.areEqual(str, lowerCase2)) {
                        String string3 = getString(uat.psa.mym.mycitroenconnect.R.string.ac);
                        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ac)");
                        String string4 = getString(uat.psa.mym.mycitroenconnect.R.string.lbl_on);
                        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.lbl_on)");
                        myVehicleStatus2 = new MyVehicleStatus(string3, string4, uat.psa.mym.mycitroenconnect.R.color.special_color_1, uat.psa.mym.mycitroenconnect.R.drawable.ic_ac_flow);
                        list2.add(myVehicleStatus2);
                        String string5 = getString(uat.psa.mym.mycitroenconnect.R.string.doors);
                        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.doors)");
                        String string6 = getString(uat.psa.mym.mycitroenconnect.R.string.open);
                        Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.open)");
                        MyVehicleStatus myVehicleStatus3 = new MyVehicleStatus(string5, string6, uat.psa.mym.mycitroenconnect.R.color.special_color_1, uat.psa.mym.mycitroenconnect.R.drawable.ic_car_door);
                        String string7 = getString(uat.psa.mym.mycitroenconnect.R.string.doors);
                        Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.doors)");
                        String string8 = getString(uat.psa.mym.mycitroenconnect.R.string.lbl_auto_close);
                        Intrinsics.checkNotNullExpressionValue(string8, "getString(R.string.lbl_auto_close)");
                        MyVehicleStatus myVehicleStatus4 = new MyVehicleStatus(string7, string8, uat.psa.mym.mycitroenconnect.R.color.hot_grey_20, uat.psa.mym.mycitroenconnect.R.drawable.ic_car_door);
                        List<MyVehicleStatus> list3 = this.myVehicleStatus;
                        driverDoorEvent = vehicleStatus.getDriverDoorEvent();
                        boolean z4 = false;
                        if (!(driverDoorEvent != null || driverDoorEvent.length() == 0)) {
                            String driverDoorEvent2 = vehicleStatus.getDriverDoorEvent();
                            if (driverDoorEvent2 != null) {
                                isBlank4 = StringsKt__StringsJVMKt.isBlank(driverDoorEvent2);
                                if (!isBlank4) {
                                    z3 = false;
                                }
                            }
                            z3 = true;
                        }
                        coDriverDoorEvent = vehicleStatus.getCoDriverDoorEvent();
                        if (!(coDriverDoorEvent != null || coDriverDoorEvent.length() == 0)) {
                            String coDriverDoorEvent2 = vehicleStatus.getCoDriverDoorEvent();
                            if (coDriverDoorEvent2 != null) {
                                isBlank3 = StringsKt__StringsJVMKt.isBlank(coDriverDoorEvent2);
                                if (!isBlank3) {
                                    z2 = false;
                                }
                            }
                            z2 = true;
                        }
                        rearLeftDoorEvent = vehicleStatus.getRearLeftDoorEvent();
                        if (!(rearLeftDoorEvent != null || rearLeftDoorEvent.length() == 0)) {
                            String rearLeftDoorEvent2 = vehicleStatus.getRearLeftDoorEvent();
                            if (rearLeftDoorEvent2 != null) {
                                isBlank2 = StringsKt__StringsJVMKt.isBlank(rearLeftDoorEvent2);
                                if (!isBlank2) {
                                    z = false;
                                }
                            }
                            z = true;
                        }
                        rearRightDoorEvent = vehicleStatus.getRearRightDoorEvent();
                        if (!(rearRightDoorEvent != null || rearRightDoorEvent.length() == 0)) {
                            String rearRightDoorEvent2 = vehicleStatus.getRearRightDoorEvent();
                            if (rearRightDoorEvent2 != null) {
                                isBlank = StringsKt__StringsJVMKt.isBlank(rearRightDoorEvent2);
                            }
                            z4 = true;
                        }
                        myVehicleStatus3 = myVehicleStatus4;
                        list3.add(myVehicleStatus3);
                        initVehicleStatus();
                    }
                }
                String string9 = getString(uat.psa.mym.mycitroenconnect.R.string.ac);
                Intrinsics.checkNotNullExpressionValue(string9, "getString(R.string.ac)");
                String string10 = getString(uat.psa.mym.mycitroenconnect.R.string.lbl_off);
                Intrinsics.checkNotNullExpressionValue(string10, "getString(R.string.lbl_off)");
                myVehicleStatus2 = new MyVehicleStatus(string9, string10, uat.psa.mym.mycitroenconnect.R.color.hot_grey_20, uat.psa.mym.mycitroenconnect.R.drawable.ic_ac_flow);
                list2.add(myVehicleStatus2);
                String string52 = getString(uat.psa.mym.mycitroenconnect.R.string.doors);
                Intrinsics.checkNotNullExpressionValue(string52, "getString(R.string.doors)");
                String string62 = getString(uat.psa.mym.mycitroenconnect.R.string.open);
                Intrinsics.checkNotNullExpressionValue(string62, "getString(R.string.open)");
                MyVehicleStatus myVehicleStatus32 = new MyVehicleStatus(string52, string62, uat.psa.mym.mycitroenconnect.R.color.special_color_1, uat.psa.mym.mycitroenconnect.R.drawable.ic_car_door);
                String string72 = getString(uat.psa.mym.mycitroenconnect.R.string.doors);
                Intrinsics.checkNotNullExpressionValue(string72, "getString(R.string.doors)");
                String string82 = getString(uat.psa.mym.mycitroenconnect.R.string.lbl_auto_close);
                Intrinsics.checkNotNullExpressionValue(string82, "getString(R.string.lbl_auto_close)");
                MyVehicleStatus myVehicleStatus42 = new MyVehicleStatus(string72, string82, uat.psa.mym.mycitroenconnect.R.color.hot_grey_20, uat.psa.mym.mycitroenconnect.R.drawable.ic_car_door);
                List<MyVehicleStatus> list32 = this.myVehicleStatus;
                driverDoorEvent = vehicleStatus.getDriverDoorEvent();
                boolean z42 = false;
                if (!(driverDoorEvent != null || driverDoorEvent.length() == 0)) {
                }
                coDriverDoorEvent = vehicleStatus.getCoDriverDoorEvent();
                if (!(coDriverDoorEvent != null || coDriverDoorEvent.length() == 0)) {
                }
                rearLeftDoorEvent = vehicleStatus.getRearLeftDoorEvent();
                if (!(rearLeftDoorEvent != null || rearLeftDoorEvent.length() == 0)) {
                }
                rearRightDoorEvent = vehicleStatus.getRearRightDoorEvent();
                if (!(rearRightDoorEvent != null || rearRightDoorEvent.length() == 0)) {
                }
                myVehicleStatus32 = myVehicleStatus42;
                list32.add(myVehicleStatus32);
                initVehicleStatus();
            }
        }
        String string11 = getString(uat.psa.mym.mycitroenconnect.R.string.vehicle);
        Intrinsics.checkNotNullExpressionValue(string11, "getString(R.string.vehicle)");
        String string12 = getString(uat.psa.mym.mycitroenconnect.R.string.lbl_off);
        Intrinsics.checkNotNullExpressionValue(string12, "getString(R.string.lbl_off)");
        myVehicleStatus = new MyVehicleStatus(string11, string12, uat.psa.mym.mycitroenconnect.R.color.hot_grey_20, uat.psa.mym.mycitroenconnect.R.drawable.ic_car_red);
        list.add(myVehicleStatus);
        List<MyVehicleStatus> list22 = this.myVehicleStatus;
        if (vehicleStatus.getAcSwitchStatus() != null) {
        }
        String string92 = getString(uat.psa.mym.mycitroenconnect.R.string.ac);
        Intrinsics.checkNotNullExpressionValue(string92, "getString(R.string.ac)");
        String string102 = getString(uat.psa.mym.mycitroenconnect.R.string.lbl_off);
        Intrinsics.checkNotNullExpressionValue(string102, "getString(R.string.lbl_off)");
        myVehicleStatus2 = new MyVehicleStatus(string92, string102, uat.psa.mym.mycitroenconnect.R.color.hot_grey_20, uat.psa.mym.mycitroenconnect.R.drawable.ic_ac_flow);
        list22.add(myVehicleStatus2);
        String string522 = getString(uat.psa.mym.mycitroenconnect.R.string.doors);
        Intrinsics.checkNotNullExpressionValue(string522, "getString(R.string.doors)");
        String string622 = getString(uat.psa.mym.mycitroenconnect.R.string.open);
        Intrinsics.checkNotNullExpressionValue(string622, "getString(R.string.open)");
        MyVehicleStatus myVehicleStatus322 = new MyVehicleStatus(string522, string622, uat.psa.mym.mycitroenconnect.R.color.special_color_1, uat.psa.mym.mycitroenconnect.R.drawable.ic_car_door);
        String string722 = getString(uat.psa.mym.mycitroenconnect.R.string.doors);
        Intrinsics.checkNotNullExpressionValue(string722, "getString(R.string.doors)");
        String string822 = getString(uat.psa.mym.mycitroenconnect.R.string.lbl_auto_close);
        Intrinsics.checkNotNullExpressionValue(string822, "getString(R.string.lbl_auto_close)");
        MyVehicleStatus myVehicleStatus422 = new MyVehicleStatus(string722, string822, uat.psa.mym.mycitroenconnect.R.color.hot_grey_20, uat.psa.mym.mycitroenconnect.R.drawable.ic_car_door);
        List<MyVehicleStatus> list322 = this.myVehicleStatus;
        driverDoorEvent = vehicleStatus.getDriverDoorEvent();
        boolean z422 = false;
        if (!(driverDoorEvent != null || driverDoorEvent.length() == 0)) {
        }
        coDriverDoorEvent = vehicleStatus.getCoDriverDoorEvent();
        if (!(coDriverDoorEvent != null || coDriverDoorEvent.length() == 0)) {
        }
        rearLeftDoorEvent = vehicleStatus.getRearLeftDoorEvent();
        if (!(rearLeftDoorEvent != null || rearLeftDoorEvent.length() == 0)) {
        }
        rearRightDoorEvent = vehicleStatus.getRearRightDoorEvent();
        if (!(rearRightDoorEvent != null || rearRightDoorEvent.length() == 0)) {
        }
        myVehicleStatus322 = myVehicleStatus422;
        list322.add(myVehicleStatus322);
        initVehicleStatus();
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment
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

    public final void getDashboardInfo() {
        displayLoading();
        DashboardService dashboardService = new DashboardService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        dashboardService.callDashboardAPI(requireActivity, companion.getVinNumber(requireContext));
    }

    @Subscribe
    public final void getMessage(@NotNull ErrorResponse event) {
        boolean isBlank;
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getApiName().length() > 0) {
            isBlank = StringsKt__StringsJVMKt.isBlank(event.getApiName());
            if (!isBlank) {
                String apiName = event.getApiName();
                switch (apiName.hashCode()) {
                    case -1857640538:
                        if (apiName.equals("summary")) {
                            setMileageValue("0");
                            return;
                        }
                        return;
                    case -1047860588:
                        if (apiName.equals(AppConstants.API_NAME_DASHBOARD)) {
                            CarFragment carFragment = this.mCarFragment;
                            if (carFragment != null) {
                                carFragment.hideUpdatedTime();
                            }
                            CarFragment carFragment2 = this.mCarFragment;
                            if (carFragment2 != null) {
                                carFragment2.setUpdateTime("");
                            }
                            updateDteValue("0");
                            updateBatteryValue("0");
                            break;
                        } else {
                            return;
                        }
                    case -740353638:
                        if (apiName.equals(AppConstants.API_NAME_SNAP_VEHICLE_STATUS)) {
                            displayData();
                            updateOdoMeterValue("0");
                            return;
                        }
                        return;
                    case 768509001:
                        if (!apiName.equals(AppConstants.API_NAME_NOTIF_LIST)) {
                            return;
                        }
                        displayData();
                        AppCompatTextView tvAlertLbl = (AppCompatTextView) _$_findCachedViewById(R.id.tvAlertLbl);
                        Intrinsics.checkNotNullExpressionValue(tvAlertLbl, "tvAlertLbl");
                        ExtensionsKt.hide(tvAlertLbl);
                        RecyclerView recyclerView = this.rvAlert;
                        if (recyclerView != null) {
                            ExtensionsKt.hide(recyclerView);
                            break;
                        }
                        break;
                    default:
                        return;
                }
                getSnapShotVehicleStatus();
                return;
            }
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        ExtensionsKt.showToast(requireActivity, event.getMsg());
    }

    @Subscribe
    public final void getMessage(@NotNull DashboardResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        CarFragment carFragment = this.mCarFragment;
        if (carFragment != null) {
            carFragment.setUpdateTime(event.getUpdatedTimeStamp());
        }
        updateDteValue(String.valueOf(event.getDistanceToEmpty()));
        updateBatteryValue(String.valueOf(Intrinsics.areEqual(this.vehicleType, AppConstants.ICE) ? (int) Math.ceil(event.getFuelLevel() / 0.08d) : event.getStateOfCharge()));
        updateCarStatusList();
        getSnapShotVehicleStatus();
    }

    @Subscribe
    public final void getMessage(@NotNull NotificationResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        prepareVehicleAlertList(event.getNotifications());
        initVehicleAlert();
    }

    @Subscribe
    public final void getResponse(@NotNull VehicleStatus response) {
        Intrinsics.checkNotNullParameter(response, "response");
        displayData();
        Double odometer = response.getOdometer();
        updateOdoMeterValue(String.valueOf(odometer != null ? Integer.valueOf((int) odometer.doubleValue()) : null));
        Double avgFuelEfficiency = response.getAvgFuelEfficiency();
        setMileageValue(String.valueOf(avgFuelEfficiency != null ? ExtensionsKt.format(avgFuelEfficiency.doubleValue(), 2) : null));
        updateVehicleStatus(response);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_my_car_status, viewGroup, false);
        this.rootView = inflate;
        return inflate;
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getDashboardInfo();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        CarFragment carFragment;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        if (getParentFragment() == null || !(getParentFragment() instanceof CarFragment)) {
            carFragment = null;
        } else {
            Fragment parentFragment = getParentFragment();
            Objects.requireNonNull(parentFragment, "null cannot be cast to non-null type com.psa.mym.mycitroenconnect.controller.fragments.my_car.CarFragment");
            carFragment = (CarFragment) parentFragment;
        }
        this.mCarFragment = carFragment;
        initViews();
    }
}
