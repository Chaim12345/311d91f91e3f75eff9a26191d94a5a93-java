package com.psa.mym.mycitroenconnect.controller.fragments.profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.BusBaseFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.AddCarActivity;
import com.psa.mym.mycitroenconnect.controller.activities.MainActivity;
import com.psa.mym.mycitroenconnect.controller.activities.MyCarDetailsActivity;
import com.psa.mym.mycitroenconnect.controller.adapters.CarInterface;
import com.psa.mym.mycitroenconnect.controller.adapters.MyCarAdapter;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OnCarSwitchListener;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.SwitchCarConfirmationFragment;
import com.psa.mym.mycitroenconnect.controller.dialog.CustomProgressDialog;
import com.psa.mym.mycitroenconnect.controller.fragments.profile.MyCarsFragment;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.Token;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCar;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCarResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCars;
import com.psa.mym.mycitroenconnect.services.SecondaryUserService;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.Skeleton;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayoutUtils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class MyCarsFragment extends BusBaseFragment implements View.OnClickListener, CarInterface, OnCarSwitchListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int REQ_DELETE_CAR = 234;
    @Nullable
    private MyCarAdapter carAdapter;
    private boolean isSelectedVin;
    private long lastClickTime;
    @Nullable
    private String mobileNumber;
    @Nullable
    private MainActivity parentActivity;
    private Skeleton skeleton;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private ArrayList<Object> vehicleList = new ArrayList<>();
    private int carCount = -1;
    private boolean isAPICalled = true;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final MyCarsFragment newInstance() {
            MyCarsFragment myCarsFragment = new MyCarsFragment();
            myCarsFragment.setArguments(new Bundle());
            return myCarsFragment;
        }
    }

    private final void displayData() {
        Skeleton skeleton = this.skeleton;
        if (skeleton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skeleton");
            skeleton = null;
        }
        ExtensionsKt.showData$default(skeleton, 0L, 1, null);
    }

    private final void displayLoading() {
        FrameLayout flParent = (FrameLayout) _$_findCachedViewById(R.id.flParent);
        Intrinsics.checkNotNullExpressionValue(flParent, "flParent");
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Skeleton createSkeleton = SkeletonLayoutUtils.createSkeleton(flParent, ExtensionsKt.skeletonConfig(requireContext));
        createSkeleton.showSkeleton();
        this.skeleton = createSkeleton;
    }

    private final void getMyCarsList() {
        String str = this.mobileNumber;
        if (str != null) {
            displayLoading();
            SecondaryUserService secondaryUserService = new SecondaryUserService();
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            StringBuilder sb = new StringBuilder();
            sb.append("91");
            String substring = str.substring(3, str.length());
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            sb.append(substring);
            secondaryUserService.getMyCarList(requireContext, sb.toString(), AppConstants.SCREEN_MY_CARS);
        }
    }

    private final void initViews() {
        MainActivity mainActivity;
        if (getActivity() == null || !(getActivity() instanceof MainActivity)) {
            mainActivity = null;
        } else {
            FragmentActivity activity = getActivity();
            Objects.requireNonNull(activity, "null cannot be cast to non-null type com.psa.mym.mycitroenconnect.controller.activities.MainActivity");
            mainActivity = (MainActivity) activity;
        }
        this.parentActivity = mainActivity;
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.mobileNumber = companion.getMobileNumber(requireContext);
        ArrayList<Object> arrayList = this.vehicleList;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        this.carAdapter = new MyCarAdapter(requireContext2, arrayList, this);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvMyCar);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(this.carAdapter);
        Intrinsics.checkNotNullExpressionValue(recyclerView, "this");
        ExtensionsKt.show(recyclerView);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnAddAnotherCar)).setOnClickListener(this);
    }

    @JvmStatic
    @NotNull
    public static final MyCarsFragment newInstance() {
        return Companion.newInstance();
    }

    private final void setVehicleDetails(MyCar myCar) {
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String vinNum = myCar.getVinNum();
        if (vinNum == null) {
            vinNum = "";
        }
        companion.setVinNumber(requireContext, vinNum);
        String vehicleType = myCar.getVehicleType();
        if (vehicleType != null) {
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            companion.setVehicleType(requireContext2, vehicleType);
        }
        Token token = myCar.getToken();
        if (token != null) {
            Context requireContext3 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
            companion.setPrimaryUserTokenDetails(requireContext3, token);
        }
    }

    private final void showSwitchingProgressDialog() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(requireContext, "Configuring Selected Car");
        customProgressDialog.show();
        final Handler handler = new Handler(Looper.getMainLooper());
        final Runnable runnable = new Runnable() { // from class: o.b
            @Override // java.lang.Runnable
            public final void run() {
                MyCarsFragment.m157showSwitchingProgressDialog$lambda7(CustomProgressDialog.this);
            }
        };
        handler.postDelayed(runnable, 5000L);
        customProgressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: o.a
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                MyCarsFragment.m158showSwitchingProgressDialog$lambda8(handler, runnable, dialogInterface);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showSwitchingProgressDialog$lambda-7  reason: not valid java name */
    public static final void m157showSwitchingProgressDialog$lambda7(CustomProgressDialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showSwitchingProgressDialog$lambda-8  reason: not valid java name */
    public static final void m158showSwitchingProgressDialog$lambda8(Handler handler, Runnable runnable, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(handler, "$handler");
        Intrinsics.checkNotNullParameter(runnable, "$runnable");
        handler.removeCallbacks(runnable);
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

    @Subscribe
    public final void getMessage(@NotNull ErrorResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        displayData();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        ExtensionsKt.showToast(requireActivity, event.getMsg());
    }

    @Subscribe
    public final void getResponse(@NotNull MyCarResponse myCarsResponse) {
        String str;
        String str2;
        boolean isBlank;
        String str3;
        boolean isBlank2;
        String str4;
        Intrinsics.checkNotNullParameter(myCarsResponse, "myCarsResponse");
        if (Intrinsics.areEqual(myCarsResponse.getScreenName(), AppConstants.SCREEN_MY_CARS)) {
            MyCars myCars = myCarsResponse.getMyCars();
            if (!(myCars == null || myCars.isEmpty())) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                if (myCars.size() > 0) {
                    String userType = myCars.get(0).getUserType();
                    if (userType != null) {
                        str4 = userType.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    } else {
                        str4 = null;
                    }
                    if (Intrinsics.areEqual(str4, "g")) {
                        Token token = myCars.get(0).getToken();
                        if (token != null) {
                            SharedPref.Companion companion = SharedPref.Companion;
                            Context requireContext = requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                            companion.setSecondaryUserTokenDetails(requireContext, token);
                        }
                        myCars.remove(0);
                    }
                }
                if (myCars.size() > 1) {
                    CollectionsKt__MutableCollectionsJVMKt.sortWith(myCars, new Comparator() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.profile.MyCarsFragment$getResponse$$inlined$sortBy$1
                        @Override // java.util.Comparator
                        public final int compare(T t2, T t3) {
                            int compareValues;
                            compareValues = ComparisonsKt__ComparisonsKt.compareValues(((MyCar) t2).getUserType(), ((MyCar) t3).getUserType());
                            return compareValues;
                        }
                    });
                }
                SharedPref.Companion companion2 = SharedPref.Companion;
                Context requireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                companion2.setVinTokenDetails(requireContext2, myCars);
                Context requireContext3 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                Context requireContext4 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                companion2.setTokenDetails(requireContext3, companion2.getVinNumber(requireContext4));
                for (MyCar myCar : myCars) {
                    myCar.setVehicleImage(Integer.valueOf((int) uat.psa.mym.mycitroenconnect.R.drawable.citroen_nav_bar_car));
                    String vinNum = myCar.getVinNum();
                    SharedPref.Companion companion3 = SharedPref.Companion;
                    Context requireContext5 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext()");
                    myCar.setVehicleSelected(Intrinsics.areEqual(vinNum, companion3.getVinNumber(requireContext5)));
                    String userType2 = myCar.getUserType();
                    if (userType2 != null) {
                        str = userType2.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    } else {
                        str = null;
                    }
                    if (!Intrinsics.areEqual(str, "g")) {
                        String userType3 = myCar.getUserType();
                        if (userType3 != null) {
                            str2 = userType3.toLowerCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                        } else {
                            str2 = null;
                        }
                        if (Intrinsics.areEqual(str2, "p")) {
                            arrayList.add(myCar);
                            Context requireContext6 = requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext6, "requireContext()");
                            String vinNumber = companion3.getVinNumber(requireContext6);
                            if (this.isSelectedVin && vinNumber != null) {
                                isBlank = StringsKt__StringsJVMKt.isBlank(vinNumber);
                                if (isBlank) {
                                    if (vinNumber.length() == 0) {
                                        myCar.setVehicleSelected(true);
                                        Context requireContext7 = requireContext();
                                        Intrinsics.checkNotNullExpressionValue(requireContext7, "requireContext()");
                                        companion3.setIsPrimaryUser(requireContext7, "true");
                                        Context requireContext8 = requireContext();
                                        Intrinsics.checkNotNullExpressionValue(requireContext8, "requireContext()");
                                        companion3.setIsVerifiedUser(requireContext8, "true");
                                        setVehicleDetails(myCar);
                                    }
                                }
                            }
                        } else if (Intrinsics.areEqual(str2, "s")) {
                            String inviteStatus = myCar.getInviteStatus();
                            if (inviteStatus != null) {
                                str3 = inviteStatus.toLowerCase(Locale.ROOT);
                                Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                            } else {
                                str3 = null;
                            }
                            if (Intrinsics.areEqual(str3, "accepted") ? true : Intrinsics.areEqual(str3, AppConstants.SECONDARY_USER_STATE_VERIFIED)) {
                                myCar.setAccessible(true);
                                myCar.setViewOnly(true);
                                arrayList2.add(myCar);
                                Context requireContext9 = requireContext();
                                Intrinsics.checkNotNullExpressionValue(requireContext9, "requireContext()");
                                String vinNumber2 = companion3.getVinNumber(requireContext9);
                                if (this.isSelectedVin && vinNumber2 != null) {
                                    isBlank2 = StringsKt__StringsJVMKt.isBlank(vinNumber2);
                                    if (isBlank2) {
                                        if (vinNumber2.length() == 0) {
                                            myCar.setVehicleSelected(true);
                                            Context requireContext10 = requireContext();
                                            Intrinsics.checkNotNullExpressionValue(requireContext10, "requireContext()");
                                            companion3.setIsGuestUser(requireContext10, "true");
                                            Context requireContext82 = requireContext();
                                            Intrinsics.checkNotNullExpressionValue(requireContext82, "requireContext()");
                                            companion3.setIsVerifiedUser(requireContext82, "true");
                                            setVehicleDetails(myCar);
                                        }
                                    }
                                }
                            } else {
                                Logger.INSTANCE.e("This car invite status is: " + myCar.getInviteStatus());
                            }
                        }
                    }
                }
                ArrayList<Object> arrayList3 = this.vehicleList;
                arrayList3.clear();
                this.carCount = arrayList.size() + arrayList2.size();
                if (!arrayList.isEmpty()) {
                    arrayList3.add(getString(uat.psa.mym.mycitroenconnect.R.string.label_my_cars));
                    arrayList3.addAll(arrayList);
                }
                if (!arrayList2.isEmpty()) {
                    arrayList3.add(getString(uat.psa.mym.mycitroenconnect.R.string.child_account_cars));
                    arrayList3.addAll(arrayList2);
                }
                MyCarAdapter myCarAdapter = this.carAdapter;
                if (myCarAdapter != null) {
                    myCarAdapter.updateCarList(arrayList3);
                }
            }
        }
        displayData();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 234 && i3 == -1 && intent != null) {
            boolean booleanExtra = intent.getBooleanExtra("is_selected_vin", false);
            this.isSelectedVin = booleanExtra;
            if (booleanExtra) {
                SharedPref.Companion companion = SharedPref.Companion;
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                companion.setVinNumber(requireContext, "");
            }
            int intExtra = intent.getIntExtra(AppConstants.ARG_POSITION, -1);
            Logger logger = Logger.INSTANCE;
            logger.e("Deleted Car Position: " + intExtra + " & is selected vin number: " + this.isSelectedVin);
            getMyCarsList();
        }
    }

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.CarInterface
    public void onCarChange(@NotNull MyCar car, int i2) {
        Intrinsics.checkNotNullParameter(car, "car");
        SwitchCarConfirmationFragment newInstance = SwitchCarConfirmationFragment.Companion.newInstance(car, i2);
        newInstance.setOnCarSwitchListener(this);
        newInstance.show(getChildFragmentManager(), SwitchCarConfirmationFragment.TAG);
        newInstance.setCancelable(false);
    }

    @Override // com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OnCarSwitchListener
    @SuppressLint({"NotifyDataSetChanged"})
    public void onCarSwitch(@NotNull MyCar car, int i2) {
        String str;
        Intrinsics.checkNotNullParameter(car, "car");
        showSwitchingProgressDialog();
        if (car.getVinNum() != null) {
            SharedPref.Companion companion = SharedPref.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            companion.setVinNumber(requireContext, car.getVinNum());
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            companion.setTokenDetails(requireContext2, car.getVinNum());
            Context requireContext3 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
            companion.setIgnitionState(requireContext3, AppConstants.IGNITION_STATUS_STOP);
            String vehicleType = car.getVehicleType();
            if (vehicleType != null) {
                Context requireContext4 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                companion.setVehicleType(requireContext4, vehicleType);
            }
            if (car.getUserType() != null) {
                String userType = car.getUserType();
                if (userType != null) {
                    str = userType.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                } else {
                    str = null;
                }
                if (Intrinsics.areEqual(str, "s")) {
                    Context requireContext5 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext()");
                    companion.setIsGuestUser(requireContext5, "true");
                    Context requireContext6 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext6, "requireContext()");
                    companion.setIsPrimaryUser(requireContext6, "false");
                }
            }
            Context requireContext7 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext7, "requireContext()");
            companion.setIsPrimaryUser(requireContext7, "true");
            Context requireContext8 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext8, "requireContext()");
            companion.setIsGuestUser(requireContext8, "false");
        }
        int i3 = 0;
        for (Object obj : this.vehicleList) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            if (obj instanceof MyCar) {
                ((MyCar) obj).setVehicleSelected(i3 == i2);
            }
            i3 = i4;
        }
        MyCarAdapter myCarAdapter = this.carAdapter;
        if (myCarAdapter != null) {
            myCarAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.CarInterface
    public void onCarViewDetails(@NotNull MyCar objCar, int i2) {
        Intrinsics.checkNotNullParameter(objCar, "objCar");
        Intent intent = new Intent(requireContext(), MyCarDetailsActivity.class);
        intent.putExtra(AppConstants.ARG_POSITION, i2);
        intent.putExtra(AppConstants.ARG_CAR_DETAILS, objCar);
        intent.putExtra("car_size", this.carCount);
        startActivityForResult(intent, 234);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (SystemClock.elapsedRealtime() - this.lastClickTime < 1500) {
            return;
        }
        this.lastClickTime = SystemClock.elapsedRealtime();
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnAddAnotherCar))) {
            Intent intent = new Intent(requireActivity(), AddCarActivity.class);
            intent.putExtra("login", AppConstants.PAGE_MODE_ADD_ANOTHER_CAR);
            startActivity(intent);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getArguments();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_my_cars, viewGroup, false);
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.isAPICalled) {
            this.isAPICalled = false;
            getMyCarsList();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initViews();
    }
}
