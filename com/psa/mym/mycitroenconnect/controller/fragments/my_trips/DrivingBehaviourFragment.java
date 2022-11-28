package com.psa.mym.mycitroenconnect.controller.fragments.my_trips;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.BusBaseFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.adapters.TipsCardAdapter;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.TipsCardData;
import com.psa.mym.mycitroenconnect.model.trip.DrivingBehaviourResponse;
import com.psa.mym.mycitroenconnect.services.TripService;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.Skeleton;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayoutUtils;
import com.rd.PageIndicatorView;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class DrivingBehaviourFragment extends BusBaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private DrivingBehaviourResponse drivingBehaviourResponse;
    private Skeleton skeleton;
    @Nullable
    private TipsCardAdapter tipsCardAdapter;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final DrivingBehaviourFragment newInstance() {
            DrivingBehaviourFragment drivingBehaviourFragment = new DrivingBehaviourFragment();
            drivingBehaviourFragment.setArguments(new Bundle());
            return drivingBehaviourFragment;
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
        LinearLayout llDrivingBehaviour = (LinearLayout) _$_findCachedViewById(R.id.llDrivingBehaviour);
        Intrinsics.checkNotNullExpressionValue(llDrivingBehaviour, "llDrivingBehaviour");
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Skeleton createSkeleton = SkeletonLayoutUtils.createSkeleton(llDrivingBehaviour, ExtensionsKt.skeletonConfig(requireContext));
        createSkeleton.showSkeleton();
        this.skeleton = createSkeleton;
    }

    private final void initTipsCards() {
        List mutableListOf;
        String string = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.harsh_braking);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.harsh_braking)");
        String string2 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.harsh_breaking_desc);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.harsh_breaking_desc)");
        String string3 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.harsh_acceleration);
        Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(R.string.harsh_acceleration)");
        String string4 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.harsh_acceleration_desc);
        Intrinsics.checkNotNullExpressionValue(string4, "resources.getString(R.st….harsh_acceleration_desc)");
        String string5 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.over_speed);
        Intrinsics.checkNotNullExpressionValue(string5, "resources.getString(R.string.over_speed)");
        String string6 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.over_speed_desc);
        Intrinsics.checkNotNullExpressionValue(string6, "resources.getString(R.string.over_speed_desc)");
        mutableListOf = CollectionsKt__CollectionsKt.mutableListOf(new TipsCardData(string, string2), new TipsCardData(string3, string4), new TipsCardData(string5, string6));
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.tipsCardAdapter = new TipsCardAdapter(requireContext, mutableListOf);
        int i2 = R.id.tipsRV;
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(i2);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
        }
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(false);
        }
        if (recyclerView != null) {
            recyclerView.setAdapter(this.tipsCardAdapter);
        }
        final Ref.IntRef intRef = new Ref.IntRef();
        final Ref.IntRef intRef2 = new Ref.IntRef();
        PageIndicatorView pageIndicatorView = (PageIndicatorView) _$_findCachedViewById(R.id.pageIndicatorViewD);
        if (pageIndicatorView != null) {
            pageIndicatorView.setCount(mutableListOf.size());
        }
        ((RecyclerView) _$_findCachedViewById(i2)).addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.my_trips.DrivingBehaviourFragment$initTipsCards$2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NotNull RecyclerView recyclerView2, int i3, int i4) {
                Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                super.onScrolled(recyclerView2, i3, i4);
                Ref.IntRef.this.element += i3;
                View childAt = recyclerView2.getChildAt(0);
                if (childAt != null) {
                    int width = childAt.getWidth();
                    Ref.IntRef intRef3 = Ref.IntRef.this;
                    Ref.IntRef intRef4 = intRef;
                    DrivingBehaviourFragment drivingBehaviourFragment = this;
                    float f2 = width;
                    int floor = (int) Math.floor((intRef3.element + (f2 / 2.0f)) / f2);
                    int i5 = intRef4.element;
                    if (i5 != floor) {
                        drivingBehaviourFragment.setCurrentItem(i5 < floor ? i5 + 1 : i5 - 1, true);
                    }
                    intRef4.element = floor;
                }
            }
        });
    }

    private final void initTripCards() {
        AppCompatTextView appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvDbCardValueHarshBraking);
        DrivingBehaviourResponse drivingBehaviourResponse = this.drivingBehaviourResponse;
        DrivingBehaviourResponse drivingBehaviourResponse2 = null;
        if (drivingBehaviourResponse == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drivingBehaviourResponse");
            drivingBehaviourResponse = null;
        }
        appCompatTextView.setText(String.valueOf((int) drivingBehaviourResponse.getHardBrake()));
        AppCompatTextView appCompatTextView2 = (AppCompatTextView) _$_findCachedViewById(R.id.tvDbCardValueHarshAcc);
        DrivingBehaviourResponse drivingBehaviourResponse3 = this.drivingBehaviourResponse;
        if (drivingBehaviourResponse3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drivingBehaviourResponse");
            drivingBehaviourResponse3 = null;
        }
        appCompatTextView2.setText(String.valueOf((int) drivingBehaviourResponse3.getAggressiveAcceleration()));
        AppCompatTextView appCompatTextView3 = (AppCompatTextView) _$_findCachedViewById(R.id.tvDbCardValue);
        DrivingBehaviourResponse drivingBehaviourResponse4 = this.drivingBehaviourResponse;
        if (drivingBehaviourResponse4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drivingBehaviourResponse");
        } else {
            drivingBehaviourResponse2 = drivingBehaviourResponse4;
        }
        appCompatTextView3.setText(String.valueOf((int) drivingBehaviourResponse2.getHighSpeed()));
    }

    @JvmStatic
    @NotNull
    public static final DrivingBehaviourFragment newInstance() {
        return Companion.newInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCurrentItem(int i2, boolean z) {
        ((PageIndicatorView) _$_findCachedViewById(R.id.pageIndicatorViewD)).setSelection(Math.abs(i2));
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

    public final void getDrivingBehaviourData() {
        AppConstants.Companion companion;
        AppConstants.Companion.setDateChanged(false);
        SharedPref.Companion companion2 = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String vinNumber = companion2.getVinNumber(requireContext);
        displayLoading();
        TripService tripService = new TripService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        tripService.callGetTripSummaryAPI(requireActivity, vinNumber, companion.getStartDate() + " 00:00:01", companion.getEndDate() + " 23:59:59");
    }

    @Subscribe
    public final void getMessage(@NotNull ErrorResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getStatusCode() == 204) {
            ((ConstraintLayout) _$_findCachedViewById(R.id.conLayout)).setVisibility(8);
            ((TextView) _$_findCachedViewById(R.id.tvNoData)).setVisibility(0);
        }
        displayData();
    }

    @Subscribe
    public final void getMessage(@NotNull DrivingBehaviourResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        ((ConstraintLayout) _$_findCachedViewById(R.id.conLayout)).setVisibility(0);
        ((TextView) _$_findCachedViewById(R.id.tvNoData)).setVisibility(8);
        this.drivingBehaviourResponse = event;
        initTripCards();
        displayData();
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
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_driving_behaviour, viewGroup, false);
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        ((ScrollView) _$_findCachedViewById(R.id.scrollViewDrivingBehavior)).requestLayout();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        getDrivingBehaviourData();
        initTipsCards();
    }
}
