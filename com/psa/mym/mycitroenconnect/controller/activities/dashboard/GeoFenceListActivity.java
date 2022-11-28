package com.psa.mym.mycitroenconnect.controller.activities.dashboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.maps.model.LatLng;
import com.psa.mym.mycitroenconnect.BusBaseActivity;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.adapters.GeoFenceAdapter;
import com.psa.mym.mycitroenconnect.controller.adapters.GeoFenceClickListener;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.DeleteConfirmationFragment;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OnDeleteDialogDismiss;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.PostCommonResponse;
import com.psa.mym.mycitroenconnect.model.geo_fence.GetGeoFenceResponse;
import com.psa.mym.mycitroenconnect.model.geo_fence.GetGeoFenceResponseItem;
import com.psa.mym.mycitroenconnect.services.GeoFenceService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.Skeleton;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayoutUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class GeoFenceListActivity extends BusBaseActivity implements GeoFenceClickListener, OnDeleteDialogDismiss {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int MAX_GEO_FENCE_LIST_SIZE = 5;
    @Nullable
    private LatLng carLatLng;
    @Nullable
    private Location currentLocation;
    @Nullable
    private GeoFenceAdapter geoFenceAdapter;
    private boolean isFirstTime;
    private Skeleton skeleton;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private ArrayList<GetGeoFenceResponseItem> geoFenceList = new ArrayList<>();

    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final void activeDeActiveFence(int i2) {
        AppUtil.Companion.showDialog(this);
        GeoFenceService geoFenceService = new GeoFenceService();
        String vinNumber = SharedPref.Companion.getVinNumber(this);
        String fenceId = this.geoFenceList.get(i2).getFenceId();
        Intrinsics.checkNotNull(fenceId);
        String fenceStatus = this.geoFenceList.get(i2).getFenceStatus();
        Intrinsics.checkNotNull(fenceStatus);
        geoFenceService.activeDeactiveGeoFence(this, vinNumber, fenceId, fenceStatus);
    }

    private final void disableAllGeoFence() {
        AppUtil.Companion.showDialog(this);
        new GeoFenceService().disableAllGeoFence(this, SharedPref.Companion.getVinNumber(this));
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
        ConstraintLayout clParent = (ConstraintLayout) _$_findCachedViewById(R.id.clParent);
        Intrinsics.checkNotNullExpressionValue(clParent, "clParent");
        Skeleton createSkeleton = SkeletonLayoutUtils.createSkeleton(clParent, ExtensionsKt.skeletonConfig(this));
        createSkeleton.showSkeleton();
        this.skeleton = createSkeleton;
    }

    private final void getGeoFenceList() {
        displayLoading();
        new GeoFenceService().getGeoFence(this, SharedPref.Companion.getVinNumber(this), "location");
    }

    private final void getIntentData() {
        if (getIntent() != null) {
            if (getIntent().hasExtra(AppConstants.CURRENT_LOCATION)) {
                this.currentLocation = (Location) getIntent().getParcelableExtra(AppConstants.CURRENT_LOCATION);
            }
            if (getIntent().hasExtra(AppConstants.CAR_LOCATION)) {
                this.carLatLng = (LatLng) getIntent().getParcelableExtra(AppConstants.CAR_LOCATION);
            }
        }
    }

    private final void init() {
        this.isFirstTime = true;
        if (SharedPref.Companion.isGuestUser(this)) {
            int i2 = R.id.layoutGeoHeader;
            View _$_findCachedViewById = _$_findCachedViewById(i2);
            int i3 = R.id.switchDashHeader;
            SwitchCompat switchCompat = (SwitchCompat) _$_findCachedViewById.findViewById(i3);
            Intrinsics.checkNotNullExpressionValue(switchCompat, "layoutGeoHeader.switchDashHeader");
            ExtensionsKt.hide(switchCompat);
            ((SwitchCompat) _$_findCachedViewById(i2).findViewById(i3)).setClickable(false);
            AppCompatButton btnAddNew = (AppCompatButton) _$_findCachedViewById(R.id.btnAddNew);
            Intrinsics.checkNotNullExpressionValue(btnAddNew, "btnAddNew");
            ExtensionsKt.hide(btnAddNew);
        } else {
            int i4 = R.id.layoutGeoHeader;
            View _$_findCachedViewById2 = _$_findCachedViewById(i4);
            int i5 = R.id.switchDashHeader;
            SwitchCompat switchCompat2 = (SwitchCompat) _$_findCachedViewById2.findViewById(i5);
            Intrinsics.checkNotNullExpressionValue(switchCompat2, "layoutGeoHeader.switchDashHeader");
            ExtensionsKt.show(switchCompat2);
            ((SwitchCompat) _$_findCachedViewById(i4).findViewById(i5)).setClickable(true);
            ((SwitchCompat) _$_findCachedViewById(i4).findViewById(i5)).setOnClickListener(this);
            AppCompatButton btnAddNew2 = (AppCompatButton) _$_findCachedViewById(R.id.btnAddNew);
            Intrinsics.checkNotNullExpressionValue(btnAddNew2, "btnAddNew");
            ExtensionsKt.show(btnAddNew2);
        }
        int i6 = R.id.layoutGeoHeader;
        ((AppCompatTextView) _$_findCachedViewById(i6).findViewById(R.id.tvDbHeaderTitle)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_geo_fence));
        AppCompatTextView appCompatTextView = (AppCompatTextView) _$_findCachedViewById(i6).findViewById(R.id.tvSkip);
        Intrinsics.checkNotNullExpressionValue(appCompatTextView, "layoutGeoHeader.tvSkip");
        ExtensionsKt.hide(appCompatTextView);
        AppCompatTextView appCompatTextView2 = (AppCompatTextView) _$_findCachedViewById(i6).findViewById(R.id.tvDbSubTitle);
        Intrinsics.checkNotNullExpressionValue(appCompatTextView2, "layoutGeoHeader.tvDbSubTitle");
        ExtensionsKt.show(appCompatTextView2);
        GeoFenceAdapter geoFenceAdapter = new GeoFenceAdapter(this.geoFenceList);
        this.geoFenceAdapter = geoFenceAdapter;
        geoFenceAdapter.setOnGeoFenceClickListener(this);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvGeoFenceList);
        recyclerView.setAdapter(this.geoFenceAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setSubTitle();
        ((AppCompatButton) _$_findCachedViewById(R.id.btnAddNew)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(i6).findViewById(R.id.ivBack)).setOnClickListener(this);
    }

    private final void redirectToCreateGeoFence() {
        Intent intent = new Intent(this, GeoFenceActivity.class);
        intent.putExtra(AppConstants.ARG_PAGE_MODE, 5);
        intent.putExtra(AppConstants.CURRENT_LOCATION, this.currentLocation);
        intent.putExtra(AppConstants.CAR_LOCATION, this.carLatLng);
        intent.putExtra(AppConstants.IS_FROM_DASHBOARD, false);
        startActivity(intent);
    }

    private final void redirectToEditGeoFence(GetGeoFenceResponseItem getGeoFenceResponseItem) {
        Intent intent = new Intent(this, GeoFenceActivity.class);
        intent.putExtra(AppConstants.ARG_PAGE_MODE, 6);
        intent.putExtra(AppConstants.GEO_FENCE, getGeoFenceResponseItem);
        intent.putExtra(AppConstants.CURRENT_LOCATION, this.currentLocation);
        intent.putExtra(AppConstants.CAR_LOCATION, this.carLatLng);
        intent.putExtra(AppConstants.IS_FROM_DASHBOARD, false);
        startActivity(intent);
    }

    private final void setSubTitle() {
        Logger logger = Logger.INSTANCE;
        StringBuilder sb = new StringBuilder();
        sb.append("Enable Count: ");
        GeoFenceAdapter geoFenceAdapter = this.geoFenceAdapter;
        sb.append(geoFenceAdapter != null ? Integer.valueOf(geoFenceAdapter.getEnableFenceCount()) : null);
        logger.e(sb.toString());
        ArrayList<GetGeoFenceResponseItem> arrayList = this.geoFenceList;
        if ((arrayList == null || arrayList.isEmpty()) && this.geoFenceList.size() == 0) {
            int i2 = R.id.layoutGeoHeader;
            AppCompatTextView appCompatTextView = (AppCompatTextView) _$_findCachedViewById(i2).findViewById(R.id.tvDbSubTitle);
            Intrinsics.checkNotNullExpressionValue(appCompatTextView, "layoutGeoHeader.tvDbSubTitle");
            ExtensionsKt.hide(appCompatTextView);
            SwitchCompat switchCompat = (SwitchCompat) _$_findCachedViewById(i2).findViewById(R.id.switchDashHeader);
            Intrinsics.checkNotNullExpressionValue(switchCompat, "layoutGeoHeader.switchDashHeader");
            ExtensionsKt.hide(switchCompat);
        } else {
            int i3 = R.id.layoutGeoHeader;
            AppCompatTextView appCompatTextView2 = (AppCompatTextView) _$_findCachedViewById(i3).findViewById(R.id.tvDbSubTitle);
            Intrinsics.checkNotNullExpressionValue(appCompatTextView2, "layoutGeoHeader.tvDbSubTitle");
            ExtensionsKt.show(appCompatTextView2);
            SwitchCompat switchCompat2 = (SwitchCompat) _$_findCachedViewById(i3).findViewById(R.id.switchDashHeader);
            Intrinsics.checkNotNullExpressionValue(switchCompat2, "layoutGeoHeader.switchDashHeader");
            ExtensionsKt.show(switchCompat2);
        }
        int i4 = R.id.layoutGeoHeader;
        AppCompatTextView appCompatTextView3 = (AppCompatTextView) _$_findCachedViewById(i4).findViewById(R.id.tvDbSubTitle);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(uat.psa.mym.mycitroenconnect.R.string.enable_of);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.enable_of)");
        Object[] objArr = new Object[2];
        GeoFenceAdapter geoFenceAdapter2 = this.geoFenceAdapter;
        objArr[0] = geoFenceAdapter2 != null ? Integer.valueOf(geoFenceAdapter2.getEnableFenceCount()) : null;
        objArr[1] = Integer.valueOf(this.geoFenceList.size());
        String format = String.format(string, Arrays.copyOf(objArr, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        appCompatTextView3.setText(format);
        SwitchCompat switchCompat3 = (SwitchCompat) _$_findCachedViewById(i4).findViewById(R.id.switchDashHeader);
        GeoFenceAdapter geoFenceAdapter3 = this.geoFenceAdapter;
        switchCompat3.setChecked(!(geoFenceAdapter3 != null && geoFenceAdapter3.getEnableFenceCount() == 0));
        ((AppCompatButton) _$_findCachedViewById(R.id.btnAddNew)).setEnabled(this.geoFenceList.size() < 5);
    }

    private final void showDeleteFenceDialog(int i2, GetGeoFenceResponseItem getGeoFenceResponseItem) {
        DeleteConfirmationFragment.Companion companion = DeleteConfirmationFragment.Companion;
        String fenceName = getGeoFenceResponseItem.getFenceName();
        Intrinsics.checkNotNull(fenceName);
        DeleteConfirmationFragment newInstance$default = DeleteConfirmationFragment.Companion.newInstance$default(companion, fenceName, "", 4, null, getGeoFenceResponseItem, i2, 8, null);
        newInstance$default.setOnDeleteDialogDismiss(this);
        newInstance$default.show(getSupportFragmentManager(), DeleteConfirmationFragment.TAG);
        newInstance$default.setCancelable(false);
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseActivity, com.psa.mym.mycitroenconnect.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseActivity, com.psa.mym.mycitroenconnect.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i2) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            if (findViewById != null) {
                map.put(Integer.valueOf(i2), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @Subscribe
    public final void getErrorResponse(@NotNull ErrorResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        String apiName = response.getApiName();
        if (Intrinsics.areEqual(apiName, "location")) {
            displayData();
            if (this.isFirstTime) {
                this.isFirstTime = false;
                redirectToCreateGeoFence();
            }
        } else if (Intrinsics.areEqual(apiName, GeoFenceService.activeDeactiveGeoFence)) {
            AppUtil.Companion.dismissDialog();
        }
        ExtensionsKt.showToast(this, response.getMsg());
    }

    @Subscribe
    public final void getResponse(@NotNull PostCommonResponse response) {
        GeoFenceAdapter geoFenceAdapter;
        Intrinsics.checkNotNullParameter(response, "response");
        ExtensionsKt.showToast(this, response.getMessage());
        String apiName = response.getApiName();
        if (Intrinsics.areEqual(apiName, GeoFenceService.activeDeactiveGeoFence)) {
            AppUtil.Companion.dismissDialog();
        } else if (Intrinsics.areEqual(apiName, GeoFenceService.disableAllGeoFence) && (geoFenceAdapter = this.geoFenceAdapter) != null) {
            geoFenceAdapter.deActiveAllFence();
        }
        setSubTitle();
    }

    @Subscribe
    @SuppressLint({"NotifyDataSetChanged"})
    public final void getResponse(@NotNull GetGeoFenceResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        displayData();
        if (!(!response.isEmpty()) || response.size() <= 0) {
            Logger.INSTANCE.e("No GeoFence Available.");
            return;
        }
        this.geoFenceList.clear();
        this.geoFenceList.addAll(response);
        GeoFenceAdapter geoFenceAdapter = this.geoFenceAdapter;
        if (geoFenceAdapter != null) {
            geoFenceAdapter.notifyDataSetChanged();
        }
        setSubTitle();
    }

    @Override // com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OnDeleteDialogDismiss
    public void onCancel() {
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        int i2 = R.id.layoutGeoHeader;
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(i2).findViewById(R.id.ivBack))) {
            finish();
            return;
        }
        View _$_findCachedViewById = _$_findCachedViewById(i2);
        int i3 = R.id.switchDashHeader;
        if (!Intrinsics.areEqual(view, (SwitchCompat) _$_findCachedViewById.findViewById(i3))) {
            if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnAddNew))) {
                redirectToCreateGeoFence();
            }
        } else if (!((SwitchCompat) _$_findCachedViewById(i2).findViewById(i3)).isChecked()) {
            disableAllGeoFence();
        } else {
            ((SwitchCompat) _$_findCachedViewById(i2).findViewById(i3)).setChecked(false);
            String string = getString(uat.psa.mym.mycitroenconnect.R.string.no_all_geo_fence_enable);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.no_all_geo_fence_enable)");
            ExtensionsKt.showToast(this, string);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(uat.psa.mym.mycitroenconnect.R.layout.activity_geo_fence_list);
        getIntentData();
        init();
    }

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.GeoFenceClickListener
    public void onDelete(int i2, @NotNull GetGeoFenceResponseItem geoFence) {
        Intrinsics.checkNotNullParameter(geoFence, "geoFence");
        showDeleteFenceDialog(i2, geoFence);
    }

    @Override // com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OnDeleteDialogDismiss
    public void onDeleteSuccess(int i2) {
        this.geoFenceList.remove(i2);
        GeoFenceAdapter geoFenceAdapter = this.geoFenceAdapter;
        if (geoFenceAdapter != null) {
            geoFenceAdapter.notifyItemRemoved(i2);
        }
        setSubTitle();
    }

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.GeoFenceClickListener
    public void onEditClick(@NotNull GetGeoFenceResponseItem geoFence) {
        Intrinsics.checkNotNullParameter(geoFence, "geoFence");
        redirectToEditGeoFence(geoFence);
    }

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.GeoFenceClickListener
    public void onGeoFenceSwitchChange(boolean z, int i2) {
        GetGeoFenceResponseItem getGeoFenceResponseItem;
        String str;
        if (z) {
            getGeoFenceResponseItem = this.geoFenceList.get(i2);
            str = ExifInterface.GPS_MEASUREMENT_IN_PROGRESS;
        } else {
            getGeoFenceResponseItem = this.geoFenceList.get(i2);
            str = "D";
        }
        getGeoFenceResponseItem.setFenceStatus(str);
        activeDeActiveFence(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        getGeoFenceList();
    }
}
