package com.psa.mym.mycitroenconnect.controller.fragments.my_trips;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.BusBaseFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.trips.TripDetailsActivity;
import com.psa.mym.mycitroenconnect.controller.adapters.MyTripsSummaryAdapter;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.trip.OnGoingResponse;
import com.psa.mym.mycitroenconnect.model.trip.TripListResponse;
import com.psa.mym.mycitroenconnect.model.trip.TripResponseDTO;
import com.psa.mym.mycitroenconnect.services.TripService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.Skeleton;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayoutUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SuppressLint({"NotifyDataSetChanged"})
/* loaded from: classes3.dex */
public final class TripSummaryFragment extends BusBaseFragment implements MyTripsSummaryAdapter.OnItemClickListener, View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private ActivityResultLauncher<Intent> activityResultLauncher;
    @Nullable
    private MyTripsSummaryAdapter cardAdapter;
    @NotNull
    private final CompletableJob geoCoderJob;
    private Skeleton skeleton;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private List<TripResponseDTO> tripSummaryDataList = new ArrayList();
    @NotNull
    private List<OnGoingResponse> onGoingTripList = new ArrayList();

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final TripSummaryFragment newInstance() {
            TripSummaryFragment tripSummaryFragment = new TripSummaryFragment();
            tripSummaryFragment.setArguments(new Bundle());
            return tripSummaryFragment;
        }
    }

    public TripSummaryFragment() {
        CompletableJob Job$default;
        Job$default = JobKt__JobKt.Job$default((Job) null, 1, (Object) null);
        this.geoCoderJob = Job$default;
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.my_trips.f
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                TripSummaryFragment.m156_init_$lambda0(TripSummaryFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…          }\n            }");
        this.activityResultLauncher = registerForActivityResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-0  reason: not valid java name */
    public static final void m156_init_$lambda0(TripSummaryFragment this$0, ActivityResult activityResult) {
        ArrayList arrayListOf;
        boolean isBlank;
        boolean isBlank2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (activityResult == null || activityResult.getResultCode() != -1 || activityResult.getData() == null) {
            return;
        }
        Intent data = activityResult.getData();
        if (data != null && data.hasExtra("tripName")) {
            Intent data2 = activityResult.getData();
            if (data2 != null && data2.hasExtra(AppConstants.ARG_POSITION)) {
                Intent data3 = activityResult.getData();
                String stringExtra = data3 != null ? data3.getStringExtra("tripName") : null;
                Intent data4 = activityResult.getData();
                Integer valueOf = data4 != null ? Integer.valueOf(data4.getIntExtra(AppConstants.ARG_POSITION, -1)) : null;
                if (valueOf != null) {
                    try {
                        if (valueOf.intValue() != -1 && valueOf.intValue() < this$0.tripSummaryDataList.size() && stringExtra != null) {
                            if (stringExtra.length() > 0) {
                                isBlank2 = StringsKt__StringsJVMKt.isBlank(stringExtra);
                                if (!isBlank2) {
                                    this$0.tripSummaryDataList.get(valueOf.intValue()).setTripName(stringExtra);
                                    MyTripsSummaryAdapter myTripsSummaryAdapter = this$0.cardAdapter;
                                    if (myTripsSummaryAdapter != null) {
                                        myTripsSummaryAdapter.notifyDataSetChanged();
                                    }
                                }
                            }
                        }
                    } catch (Exception e2) {
                        Logger.INSTANCE.e(e2.getLocalizedMessage());
                    }
                }
            }
        }
        Intent data5 = activityResult.getData();
        if (data5 != null && data5.hasExtra(AppConstants.BUNDLE_KEY_ONGOING_TRIP_OBJ)) {
            Intent data6 = activityResult.getData();
            OnGoingResponse onGoingResponse = data6 != null ? (OnGoingResponse) data6.getParcelableExtra(AppConstants.BUNDLE_KEY_ONGOING_TRIP_OBJ) : null;
            Intent data7 = activityResult.getData();
            if (data7 != null && data7.hasExtra("tripName")) {
                Intent data8 = activityResult.getData();
                if (data8 != null && data8.hasExtra(AppConstants.ARG_POSITION)) {
                    Intent data9 = activityResult.getData();
                    String stringExtra2 = data9 != null ? data9.getStringExtra("tripName") : null;
                    if (stringExtra2 != null) {
                        try {
                            if (stringExtra2.length() > 0) {
                                isBlank = StringsKt__StringsJVMKt.isBlank(stringExtra2);
                                if ((!isBlank) && (!this$0.onGoingTripList.isEmpty())) {
                                    this$0.onGoingTripList.get(0).setTripName(stringExtra2);
                                    MyTripsSummaryAdapter myTripsSummaryAdapter2 = this$0.cardAdapter;
                                    if (myTripsSummaryAdapter2 != null) {
                                        myTripsSummaryAdapter2.updateOngoingTripList(this$0.onGoingTripList);
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            return;
                        } catch (Exception e3) {
                            Logger.INSTANCE.e(e3.getLocalizedMessage());
                            return;
                        }
                    }
                    return;
                }
            }
            if (onGoingResponse != null) {
                arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(onGoingResponse);
                this$0.onGoingTripList = arrayListOf;
                MyTripsSummaryAdapter myTripsSummaryAdapter3 = this$0.cardAdapter;
                if (myTripsSummaryAdapter3 != null) {
                    myTripsSummaryAdapter3.updateOngoingTripList(arrayListOf);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void displayData() {
        Skeleton skeleton = this.skeleton;
        if (skeleton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skeleton");
            skeleton = null;
        }
        ExtensionsKt.showData(skeleton, 50L);
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

    private final void getOngoingTrip() {
        boolean isBlank;
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String vinNumber = companion.getVinNumber(requireContext);
        if (vinNumber.length() > 0) {
            isBlank = StringsKt__StringsJVMKt.isBlank(vinNumber);
            if (!isBlank) {
                TripService tripService = new TripService();
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                tripService.callOnGoingAPI(requireActivity, vinNumber);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initTripCards() {
        setTripCount();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.cardAdapter = new MyTripsSummaryAdapter(requireContext, this.tripSummaryDataList, this.onGoingTripList);
        int i2 = R.id.tripSumRV;
        ((RecyclerView) _$_findCachedViewById(i2)).setAdapter(this.cardAdapter);
        MyTripsSummaryAdapter myTripsSummaryAdapter = this.cardAdapter;
        Intrinsics.checkNotNull(myTripsSummaryAdapter);
        myTripsSummaryAdapter.setOnItemClickListener(this);
        ((RecyclerView) _$_findCachedViewById(i2)).setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @JvmStatic
    @NotNull
    public static final TripSummaryFragment newInstance() {
        return Companion.newInstance();
    }

    private final void setTripCount() {
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvTripCount)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.trip_count, Integer.valueOf(this.tripSummaryDataList.size() + this.onGoingTripList.size())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showTripDetails(boolean z) {
        if (z) {
            TextView tvNoTrip = (TextView) _$_findCachedViewById(R.id.tvNoTrip);
            Intrinsics.checkNotNullExpressionValue(tvNoTrip, "tvNoTrip");
            ExtensionsKt.hide(tvNoTrip);
            RecyclerView tripSumRV = (RecyclerView) _$_findCachedViewById(R.id.tripSumRV);
            Intrinsics.checkNotNullExpressionValue(tripSumRV, "tripSumRV");
            ExtensionsKt.show(tripSumRV);
            AppCompatTextView tvTripCount = (AppCompatTextView) _$_findCachedViewById(R.id.tvTripCount);
            Intrinsics.checkNotNullExpressionValue(tvTripCount, "tvTripCount");
            ExtensionsKt.show(tvTripCount);
            AppCompatImageView ivRefresh = (AppCompatImageView) _$_findCachedViewById(R.id.ivRefresh);
            Intrinsics.checkNotNullExpressionValue(ivRefresh, "ivRefresh");
            ExtensionsKt.show(ivRefresh);
            return;
        }
        TextView tvNoTrip2 = (TextView) _$_findCachedViewById(R.id.tvNoTrip);
        Intrinsics.checkNotNullExpressionValue(tvNoTrip2, "tvNoTrip");
        ExtensionsKt.show(tvNoTrip2);
        RecyclerView tripSumRV2 = (RecyclerView) _$_findCachedViewById(R.id.tripSumRV);
        Intrinsics.checkNotNullExpressionValue(tripSumRV2, "tripSumRV");
        ExtensionsKt.hide(tripSumRV2);
        AppCompatTextView tvTripCount2 = (AppCompatTextView) _$_findCachedViewById(R.id.tvTripCount);
        Intrinsics.checkNotNullExpressionValue(tvTripCount2, "tvTripCount");
        ExtensionsKt.hide(tvTripCount2);
        AppCompatImageView ivRefresh2 = (AppCompatImageView) _$_findCachedViewById(R.id.ivRefresh);
        Intrinsics.checkNotNullExpressionValue(ivRefresh2, "ivRefresh");
        ExtensionsKt.hide(ivRefresh2);
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

    public final void animateRefreshButton() {
        AppCompatImageView ivRefresh = (AppCompatImageView) _$_findCachedViewById(R.id.ivRefresh);
        Intrinsics.checkNotNullExpressionValue(ivRefresh, "ivRefresh");
        ExtensionsKt.rotateAnimation$default(ivRefresh, 0L, 0, new TripSummaryFragment$animateRefreshButton$1(this), TripSummaryFragment$animateRefreshButton$2.INSTANCE, TripSummaryFragment$animateRefreshButton$3.INSTANCE, 3, null);
    }

    @Subscribe
    public final void getMessage(@NotNull ErrorResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getStatusCode() == 204) {
            String apiName = event.getApiName();
            if (Intrinsics.areEqual(apiName, AppConstants.API_NAME_ALL_TRIP_LIST)) {
                this.tripSummaryDataList = new ArrayList();
                getOngoingTrip();
            } else if (Intrinsics.areEqual(apiName, AppConstants.API_NAME_ONGOING)) {
                displayData();
                ArrayList arrayList = new ArrayList();
                this.onGoingTripList = arrayList;
                MyTripsSummaryAdapter myTripsSummaryAdapter = this.cardAdapter;
                if (myTripsSummaryAdapter != null) {
                    myTripsSummaryAdapter.updateOngoingTripList(arrayList);
                }
                MyTripsSummaryAdapter myTripsSummaryAdapter2 = this.cardAdapter;
                if (myTripsSummaryAdapter2 != null) {
                    myTripsSummaryAdapter2.updateTripList(this.tripSummaryDataList);
                }
                setTripCount();
                showTripDetails(this.tripSummaryDataList.size() + this.onGoingTripList.size() > 0);
            }
        }
    }

    @Subscribe
    public final void getMessage(@NotNull OnGoingResponse event) {
        ArrayList arrayListOf;
        Intrinsics.checkNotNullParameter(event, "event");
        if (Intrinsics.areEqual(event.getTripType(), "Active Trip")) {
            arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(event);
            this.onGoingTripList = arrayListOf;
            if (event.getLocation().length() > 0) {
                String[] split = TextUtils.split(event.getLocation(), ",");
                AppUtil.Companion companion = AppUtil.Companion;
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                String str = split[0];
                Intrinsics.checkNotNullExpressionValue(str, "startAllIdsArray[0]");
                double parseDouble = Double.parseDouble(str);
                String str2 = split[1];
                Intrinsics.checkNotNullExpressionValue(str2, "startAllIdsArray[1]");
                event.setTripSourceName(companion.getAddressNameString(requireContext, parseDouble, Double.parseDouble(str2)));
            }
            showTripDetails(true);
            if (this.cardAdapter == null) {
                initTripCards();
            }
            MyTripsSummaryAdapter myTripsSummaryAdapter = this.cardAdapter;
            if (myTripsSummaryAdapter != null) {
                myTripsSummaryAdapter.updateTripList(this.tripSummaryDataList);
            }
            MyTripsSummaryAdapter myTripsSummaryAdapter2 = this.cardAdapter;
            if (myTripsSummaryAdapter2 != null) {
                myTripsSummaryAdapter2.updateOngoingTripList(this.onGoingTripList);
            }
            setTripCount();
        }
        displayData();
    }

    @Subscribe
    public final void getMessage(@NotNull TripListResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        this.tripSummaryDataList = event.getTripListResponseDTO();
        try {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(this.geoCoderJob)), null, null, new TripSummaryFragment$getMessage$1(this, null), 3, null);
        } catch (Exception e2) {
            Logger.INSTANCE.e(e2.getLocalizedMessage());
        }
    }

    public final void getTripList() {
        AppConstants.Companion companion;
        boolean isBlank;
        AppConstants.Companion.setDateChanged(false);
        this.tripSummaryDataList = new ArrayList();
        SharedPref.Companion companion2 = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String vinNumber = companion2.getVinNumber(requireContext);
        isBlank = StringsKt__StringsJVMKt.isBlank(vinNumber);
        if (!isBlank) {
            if (vinNumber.length() > 0) {
                displayLoading();
                TripService tripService = new TripService();
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                tripService.callGetAllTripDetailsAPI(requireActivity, vinNumber, companion.getStartDate() + " 00:00:01", companion.getEndDate() + " 23:59:59");
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivRefresh))) {
            getOngoingTrip();
            getTripList();
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
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_trip_summary, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        Job.DefaultImpls.cancel$default((Job) this.geoCoderJob, (CancellationException) null, 1, (Object) null);
        super.onDestroy();
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.MyTripsSummaryAdapter.OnItemClickListener
    public void onItemClick(int i2, int i3) {
        try {
            Intent intent = new Intent(requireActivity(), TripDetailsActivity.class);
            if (i3 == 1) {
                int size = i2 - this.onGoingTripList.size();
                intent.putExtra("tripId", this.tripSummaryDataList.get(size).getTripId());
                intent.putExtra(AppConstants.ARG_POSITION, size);
            } else {
                intent.putExtra("tripId", this.onGoingTripList.get(i2).getTripId());
                intent.putExtra(AppConstants.BUNDLE_KEY_ONGOING_TRIP_OBJ, this.onGoingTripList.get(i2));
                intent.putExtra(AppConstants.BUNDLE_KEY_IS_ONGOING_TRIP, true);
            }
            this.activityResultLauncher.launch(intent);
        } catch (Exception e2) {
            Logger.INSTANCE.e(e2.getLocalizedMessage());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        ((ConstraintLayout) _$_findCachedViewById(R.id.clParent)).requestLayout();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivRefresh)).setOnClickListener(this);
        getTripList();
        getOngoingTrip();
    }
}
