package com.psa.mym.mycitroenconnect.controller.fragments.geo_fence;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.card.MaterialCardView;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.api.body.geo_fence.Coordinates;
import com.psa.mym.mycitroenconnect.api.body.geo_fence.GeoFenceBody;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.dashboard.GeoFenceActivity;
import com.psa.mym.mycitroenconnect.controller.activities.dashboard.SearchLocationActivity;
import com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.SetLocationFragment;
import com.psa.mym.mycitroenconnect.controller.viewmodel.GeoFenceViewModel;
import com.psa.mym.mycitroenconnect.model.GeoFenceCommonModel;
import com.psa.mym.mycitroenconnect.model.geo_fence.GetGeoFenceResponseItem;
import com.psa.mym.mycitroenconnect.model.geo_fence.LocationData;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SetLocationFragment extends Fragment implements View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private double destinationLatitude;
    private double destinationLongitude;
    @NotNull
    private ActivityResultLauncher<Intent> destinationResultLauncher;
    private boolean isLocationChange;
    @Nullable
    private GeoFenceActivity parentActivity;
    private double sourceLatitude;
    private double sourceLongitude;
    @NotNull
    private ActivityResultLauncher<Intent> sourceResultLauncher;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private String fenceType = "";
    @NotNull
    private String sourceAddress = "";
    @NotNull
    private String destinationAddress = "";
    private boolean isFirstTime = true;
    @NotNull
    private final Lazy viewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(GeoFenceViewModel.class), new SetLocationFragment$special$$inlined$activityViewModels$default$1(this), new SetLocationFragment$special$$inlined$activityViewModels$default$2(this));

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final SetLocationFragment newInstance() {
            SetLocationFragment setLocationFragment = new SetLocationFragment();
            setLocationFragment.setArguments(new Bundle());
            return setLocationFragment;
        }
    }

    public SetLocationFragment() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: l.h
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                SetLocationFragment.m144_init_$lambda1(SetLocationFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…          }\n            }");
        this.sourceResultLauncher = registerForActivityResult;
        ActivityResultLauncher<Intent> registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: l.g
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                SetLocationFragment.m145_init_$lambda3(SetLocationFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResul…          }\n            }");
        this.destinationResultLauncher = registerForActivityResult2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-1  reason: not valid java name */
    public static final void m144_init_$lambda1(SetLocationFragment this$0, ActivityResult activityResult) {
        Intent data;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (activityResult == null || activityResult.getData() == null || activityResult.getResultCode() != -1 || (data = activityResult.getData()) == null) {
            return;
        }
        this$0.sourceLatitude = data.getDoubleExtra("latitude", 0.0d);
        this$0.sourceLongitude = data.getDoubleExtra("longitude", 0.0d);
        Logger logger = Logger.INSTANCE;
        logger.e("sourceLatitude = " + this$0.sourceLatitude + " & sourceLongitude = " + this$0.sourceLongitude);
        this$0.setSourceAddressText();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-3  reason: not valid java name */
    public static final void m145_init_$lambda3(SetLocationFragment this$0, ActivityResult activityResult) {
        Intent data;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (activityResult == null || activityResult.getData() == null || activityResult.getResultCode() != -1 || (data = activityResult.getData()) == null) {
            return;
        }
        this$0.destinationLatitude = data.getDoubleExtra("latitude", 0.0d);
        this$0.destinationLongitude = data.getDoubleExtra("longitude", 0.0d);
        Logger logger = Logger.INSTANCE;
        logger.e("destinationLatitude = " + this$0.destinationLatitude + " & destinationLongitude = " + this$0.destinationLongitude);
        this$0.setDestinationAddressText();
    }

    private final GeoFenceViewModel getViewModel() {
        return (GeoFenceViewModel) this.viewModel$delegate.getValue();
    }

    private final void initView() {
        GeoFenceActivity geoFenceActivity;
        if (getActivity() == null || !(getActivity() instanceof GeoFenceActivity)) {
            geoFenceActivity = null;
        } else {
            FragmentActivity activity = getActivity();
            Objects.requireNonNull(activity, "null cannot be cast to non-null type com.psa.mym.mycitroenconnect.controller.activities.dashboard.GeoFenceActivity");
            geoFenceActivity = (GeoFenceActivity) activity;
        }
        this.parentActivity = geoFenceActivity;
        setConfirmLocationButton();
        setListeners();
    }

    private final void navigateToGeoFenceMapFragment() {
        GeoFenceActivity geoFenceActivity = this.parentActivity;
        if (geoFenceActivity != null) {
            geoFenceActivity.navigateToGeoFenceMapFragment();
        }
    }

    @JvmStatic
    @NotNull
    public static final SetLocationFragment newInstance() {
        return Companion.newInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00a1, code lost:
        if ((java.lang.String.valueOf(((androidx.appcompat.widget.AppCompatEditText) _$_findCachedViewById(r1)).getText()).length() > 0) != false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00aa, code lost:
        if (r1.equals(com.psa.mym.mycitroenconnect.common.AppConstants.GEO_FENCE_MODE_RADIUS) != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00b3, code lost:
        if (r1.equals(com.psa.mym.mycitroenconnect.common.AppConstants.GEO_FENCE_MODE_CUSTOM) == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b6, code lost:
        r1 = com.psa.mym.mycitroenconnect.R.id.edtDestination;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00c2, code lost:
        if (((androidx.appcompat.widget.AppCompatEditText) _$_findCachedViewById(r1)).getText() == null) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00c4, code lost:
        r2 = kotlin.text.StringsKt__StringsJVMKt.isBlank(java.lang.String.valueOf(((androidx.appcompat.widget.AppCompatEditText) _$_findCachedViewById(r1)).getText()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00d7, code lost:
        if ((!r2) == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00eb, code lost:
        if (java.lang.String.valueOf(((androidx.appcompat.widget.AppCompatEditText) _$_findCachedViewById(r1)).getText()).length() <= 0) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00ed, code lost:
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00ef, code lost:
        r1 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00f0, code lost:
        if (r1 == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00f2, code lost:
        r4 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void setConfirmLocationButton() {
        boolean isBlank;
        boolean isBlank2;
        AppCompatButton appCompatButton = (AppCompatButton) _$_findCachedViewById(R.id.btnConfirmLocation);
        String str = this.fenceType;
        int hashCode = str.hashCode();
        boolean z = false;
        if (hashCode != -1349088399) {
            if (hashCode != -938578798) {
                if (hashCode == 108704329 && str.equals(AppConstants.GEO_FENCE_MODE_ROUTE)) {
                    int i2 = R.id.edtSource;
                    if (((AppCompatEditText) _$_findCachedViewById(i2)).getText() != null) {
                        isBlank = StringsKt__StringsJVMKt.isBlank(String.valueOf(((AppCompatEditText) _$_findCachedViewById(i2)).getText()));
                        if (!isBlank) {
                            if (String.valueOf(((AppCompatEditText) _$_findCachedViewById(i2)).getText()).length() > 0) {
                                int i3 = R.id.edtDestination;
                                if (((AppCompatEditText) _$_findCachedViewById(i3)).getText() != null) {
                                    isBlank2 = StringsKt__StringsJVMKt.isBlank(String.valueOf(((AppCompatEditText) _$_findCachedViewById(i3)).getText()));
                                    if (!isBlank2) {
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        appCompatButton.setEnabled(z);
    }

    private final void setDestinationAddressText() {
        AppUtil.Companion companion = AppUtil.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.destinationAddress = companion.getAddressNameString(requireContext, this.destinationLatitude, this.destinationLongitude);
        ((AppCompatEditText) _$_findCachedViewById(R.id.edtDestination)).setText(this.destinationAddress);
    }

    private final void setEditBox(boolean z) {
        if (z) {
            AppCompatImageView ivSource = (AppCompatImageView) _$_findCachedViewById(R.id.ivSource);
            Intrinsics.checkNotNullExpressionValue(ivSource, "ivSource");
            ExtensionsKt.show(ivSource);
            View dottedLine = _$_findCachedViewById(R.id.dottedLine);
            Intrinsics.checkNotNullExpressionValue(dottedLine, "dottedLine");
            ExtensionsKt.show(dottedLine);
            MaterialCardView cvSource = (MaterialCardView) _$_findCachedViewById(R.id.cvSource);
            Intrinsics.checkNotNullExpressionValue(cvSource, "cvSource");
            ExtensionsKt.show(cvSource);
            return;
        }
        AppCompatImageView ivSource2 = (AppCompatImageView) _$_findCachedViewById(R.id.ivSource);
        Intrinsics.checkNotNullExpressionValue(ivSource2, "ivSource");
        ExtensionsKt.hide(ivSource2);
        View dottedLine2 = _$_findCachedViewById(R.id.dottedLine);
        Intrinsics.checkNotNullExpressionValue(dottedLine2, "dottedLine");
        ExtensionsKt.hide(dottedLine2);
        MaterialCardView cvSource2 = (MaterialCardView) _$_findCachedViewById(R.id.cvSource);
        Intrinsics.checkNotNullExpressionValue(cvSource2, "cvSource");
        ExtensionsKt.hide(cvSource2);
    }

    private final void setListeners() {
        int i2 = R.id.edtSource;
        ((AppCompatEditText) _$_findCachedViewById(i2)).addTextChangedListener(new TextWatcher() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.SetLocationFragment$setListeners$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i3, int i4, int i5) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i3, int i4, int i5) {
                SetLocationFragment.this.setConfirmLocationButton();
            }
        });
        int i3 = R.id.edtDestination;
        ((AppCompatEditText) _$_findCachedViewById(i3)).addTextChangedListener(new TextWatcher() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.SetLocationFragment$setListeners$2
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i4, int i5, int i6) {
                SetLocationFragment.this.setConfirmLocationButton();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(R.id.btnConfirmLocation)).setOnClickListener(this);
        ((AppCompatEditText) _$_findCachedViewById(i2)).setOnClickListener(this);
        ((AppCompatEditText) _$_findCachedViewById(i3)).setOnClickListener(this);
    }

    private final void setLocationInModel() {
        List<LocationData> emptyList;
        String str = this.fenceType;
        int hashCode = str.hashCode();
        if (hashCode == -1349088399) {
            if (str.equals(AppConstants.GEO_FENCE_MODE_CUSTOM)) {
                GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
                GeoFenceBody geoFenceBody = geoFenceCommonModel != null ? geoFenceCommonModel.getGeoFenceBody() : null;
                if (geoFenceBody != null) {
                    geoFenceBody.setSourceLocation(new Coordinates(Double.valueOf(this.destinationLatitude), Double.valueOf(this.destinationLongitude)));
                }
                GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
                GeoFenceBody geoFenceBody2 = geoFenceCommonModel2 != null ? geoFenceCommonModel2.getGeoFenceBody() : null;
                if (geoFenceBody2 == null) {
                    return;
                }
                geoFenceBody2.setDestinationLocation(new Coordinates(Double.valueOf(this.destinationLatitude), Double.valueOf(this.destinationLongitude)));
            }
        } else if (hashCode == -938578798) {
            if (str.equals(AppConstants.GEO_FENCE_MODE_RADIUS)) {
                GeoFenceCommonModel geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
                GeoFenceBody geoFenceBody3 = geoFenceCommonModel3 != null ? geoFenceCommonModel3.getGeoFenceBody() : null;
                if (geoFenceBody3 == null) {
                    return;
                }
                geoFenceBody3.setCentre(new Coordinates(Double.valueOf(this.destinationLatitude), Double.valueOf(this.destinationLongitude)));
            }
        } else if (hashCode == 108704329 && str.equals(AppConstants.GEO_FENCE_MODE_ROUTE)) {
            GeoFenceCommonModel geoFenceCommonModel4 = getViewModel().getGeoFenceCommonModel();
            GeoFenceBody geoFenceBody4 = geoFenceCommonModel4 != null ? geoFenceCommonModel4.getGeoFenceBody() : null;
            if (geoFenceBody4 != null) {
                geoFenceBody4.setSourceLocation(new Coordinates(Double.valueOf(this.sourceLatitude), Double.valueOf(this.sourceLongitude)));
            }
            GeoFenceCommonModel geoFenceCommonModel5 = getViewModel().getGeoFenceCommonModel();
            GeoFenceBody geoFenceBody5 = geoFenceCommonModel5 != null ? geoFenceCommonModel5.getGeoFenceBody() : null;
            if (geoFenceBody5 != null) {
                geoFenceBody5.setDestinationLocation(new Coordinates(Double.valueOf(this.destinationLatitude), Double.valueOf(this.destinationLongitude)));
            }
            GeoFenceCommonModel geoFenceCommonModel6 = getViewModel().getGeoFenceCommonModel();
            boolean z = false;
            if (geoFenceCommonModel6 != null && geoFenceCommonModel6.getFenceCreationMode() == 6) {
                z = true;
            }
            GeoFenceCommonModel geoFenceCommonModel7 = getViewModel().getGeoFenceCommonModel();
            if (!z) {
                GeoFenceBody geoFenceBody6 = geoFenceCommonModel7 != null ? geoFenceCommonModel7.getGeoFenceBody() : null;
                if (geoFenceBody6 == null) {
                    return;
                }
                geoFenceBody6.setCoordinates(new ArrayList<>());
                return;
            }
            GetGeoFenceResponseItem geoFenceGetResponse = geoFenceCommonModel7 != null ? geoFenceCommonModel7.getGeoFenceGetResponse() : null;
            if (geoFenceGetResponse != null) {
                emptyList = CollectionsKt__CollectionsKt.emptyList();
                geoFenceGetResponse.setCoordinates(emptyList);
            }
            GeoFenceCommonModel geoFenceCommonModel8 = getViewModel().getGeoFenceCommonModel();
            GetGeoFenceResponseItem geoFenceGetResponse2 = geoFenceCommonModel8 != null ? geoFenceCommonModel8.getGeoFenceGetResponse() : null;
            if (geoFenceGetResponse2 != null) {
                geoFenceGetResponse2.setSourceLocation(new LocationData(Double.valueOf(this.sourceLatitude), Double.valueOf(this.sourceLongitude)));
            }
            GeoFenceCommonModel geoFenceCommonModel9 = getViewModel().getGeoFenceCommonModel();
            GetGeoFenceResponseItem geoFenceGetResponse3 = geoFenceCommonModel9 != null ? geoFenceCommonModel9.getGeoFenceGetResponse() : null;
            if (geoFenceGetResponse3 == null) {
                return;
            }
            geoFenceGetResponse3.setDestinationLocation(new LocationData(Double.valueOf(this.destinationLatitude), Double.valueOf(this.destinationLongitude)));
        }
    }

    private final void setSourceAddressText() {
        AppUtil.Companion companion = AppUtil.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.sourceAddress = companion.getAddressNameString(requireContext, this.sourceLatitude, this.sourceLongitude);
        ((AppCompatEditText) _$_findCachedViewById(R.id.edtSource)).setText(this.sourceAddress);
    }

    /* JADX WARN: Removed duplicated region for block: B:310:0x0472  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x0474  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void setUIData() {
        GetGeoFenceResponseItem geoFenceGetResponse;
        LocationData centre;
        GetGeoFenceResponseItem geoFenceGetResponse2;
        LocationData centre2;
        GetGeoFenceResponseItem geoFenceGetResponse3;
        LocationData centre3;
        GetGeoFenceResponseItem geoFenceGetResponse4;
        LocationData centre4;
        GetGeoFenceResponseItem geoFenceGetResponse5;
        GetGeoFenceResponseItem geoFenceGetResponse6;
        LocationData sourceLocation;
        GetGeoFenceResponseItem geoFenceGetResponse7;
        LocationData sourceLocation2;
        GetGeoFenceResponseItem geoFenceGetResponse8;
        LocationData sourceLocation3;
        GetGeoFenceResponseItem geoFenceGetResponse9;
        LocationData sourceLocation4;
        GetGeoFenceResponseItem geoFenceGetResponse10;
        GetGeoFenceResponseItem geoFenceGetResponse11;
        LocationData destinationLocation;
        GetGeoFenceResponseItem geoFenceGetResponse12;
        LocationData destinationLocation2;
        GetGeoFenceResponseItem geoFenceGetResponse13;
        LocationData destinationLocation3;
        GetGeoFenceResponseItem geoFenceGetResponse14;
        LocationData destinationLocation4;
        GetGeoFenceResponseItem geoFenceGetResponse15;
        GetGeoFenceResponseItem geoFenceGetResponse16;
        double doubleValue;
        GeoFenceBody geoFenceBody;
        Coordinates centre5;
        GeoFenceBody geoFenceBody2;
        Coordinates centre6;
        GeoFenceBody geoFenceBody3;
        Coordinates centre7;
        GeoFenceBody geoFenceBody4;
        Coordinates centre8;
        GeoFenceBody geoFenceBody5;
        GeoFenceBody geoFenceBody6;
        Coordinates sourceLocation5;
        GeoFenceBody geoFenceBody7;
        Coordinates sourceLocation6;
        GeoFenceBody geoFenceBody8;
        Coordinates sourceLocation7;
        GeoFenceBody geoFenceBody9;
        Coordinates sourceLocation8;
        GeoFenceBody geoFenceBody10;
        GeoFenceBody geoFenceBody11;
        Coordinates destinationLocation5;
        GeoFenceBody geoFenceBody12;
        Coordinates destinationLocation6;
        GeoFenceBody geoFenceBody13;
        Coordinates destinationLocation7;
        GeoFenceBody geoFenceBody14;
        Coordinates destinationLocation8;
        GeoFenceBody geoFenceBody15;
        LatLng carLatLng;
        LatLng carLatLng2;
        GeoFenceBody geoFenceBody16;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        Double d2 = null;
        this.fenceType = String.valueOf((geoFenceCommonModel == null || (geoFenceBody16 = geoFenceCommonModel.getGeoFenceBody()) == null) ? null : geoFenceBody16.getFenceMode());
        GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
        Integer valueOf = geoFenceCommonModel2 != null ? Integer.valueOf(geoFenceCommonModel2.getFenceCreationMode()) : null;
        if (valueOf != null && valueOf.intValue() == 5) {
            if (this.isFirstTime) {
                if (Intrinsics.areEqual(this.fenceType, AppConstants.GEO_FENCE_MODE_ROUTE)) {
                    GeoFenceCommonModel geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel3 != null && (carLatLng = geoFenceCommonModel3.getCarLatLng()) != null) {
                        this.sourceLatitude = carLatLng.latitude;
                        this.sourceLongitude = carLatLng.longitude;
                        setSourceAddressText();
                    }
                } else {
                    GeoFenceCommonModel geoFenceCommonModel4 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel4 != null && (carLatLng2 = geoFenceCommonModel4.getCarLatLng()) != null) {
                        this.destinationLatitude = carLatLng2.latitude;
                        doubleValue = carLatLng2.longitude;
                        this.destinationLongitude = doubleValue;
                    }
                }
            }
            setEditBox(!Intrinsics.areEqual(this.fenceType, AppConstants.GEO_FENCE_MODE_ROUTE));
        } else if (valueOf != null && valueOf.intValue() == 7) {
            GeoFenceCommonModel geoFenceCommonModel5 = getViewModel().getGeoFenceCommonModel();
            if (((geoFenceCommonModel5 == null || (geoFenceBody15 = geoFenceCommonModel5.getGeoFenceBody()) == null) ? null : geoFenceBody15.getDestinationLocation()) != null) {
                GeoFenceCommonModel geoFenceCommonModel6 = getViewModel().getGeoFenceCommonModel();
                if (((geoFenceCommonModel6 == null || (geoFenceBody14 = geoFenceCommonModel6.getGeoFenceBody()) == null || (destinationLocation8 = geoFenceBody14.getDestinationLocation()) == null) ? null : destinationLocation8.getGpsLat()) != null) {
                    GeoFenceCommonModel geoFenceCommonModel7 = getViewModel().getGeoFenceCommonModel();
                    Double gpsLat = (geoFenceCommonModel7 == null || (geoFenceBody13 = geoFenceCommonModel7.getGeoFenceBody()) == null || (destinationLocation7 = geoFenceBody13.getDestinationLocation()) == null) ? null : destinationLocation7.getGpsLat();
                    Intrinsics.checkNotNull(gpsLat);
                    this.destinationLatitude = gpsLat.doubleValue();
                }
                GeoFenceCommonModel geoFenceCommonModel8 = getViewModel().getGeoFenceCommonModel();
                if (((geoFenceCommonModel8 == null || (geoFenceBody12 = geoFenceCommonModel8.getGeoFenceBody()) == null || (destinationLocation6 = geoFenceBody12.getDestinationLocation()) == null) ? null : destinationLocation6.getGpsLong()) != null) {
                    GeoFenceCommonModel geoFenceCommonModel9 = getViewModel().getGeoFenceCommonModel();
                    Double gpsLong = (geoFenceCommonModel9 == null || (geoFenceBody11 = geoFenceCommonModel9.getGeoFenceBody()) == null || (destinationLocation5 = geoFenceBody11.getDestinationLocation()) == null) ? null : destinationLocation5.getGpsLong();
                    Intrinsics.checkNotNull(gpsLong);
                    this.destinationLongitude = gpsLong.doubleValue();
                }
                setDestinationAddressText();
            }
            GeoFenceCommonModel geoFenceCommonModel10 = getViewModel().getGeoFenceCommonModel();
            if (((geoFenceCommonModel10 == null || (geoFenceBody10 = geoFenceCommonModel10.getGeoFenceBody()) == null) ? null : geoFenceBody10.getSourceLocation()) != null) {
                GeoFenceCommonModel geoFenceCommonModel11 = getViewModel().getGeoFenceCommonModel();
                if (((geoFenceCommonModel11 == null || (geoFenceBody9 = geoFenceCommonModel11.getGeoFenceBody()) == null || (sourceLocation8 = geoFenceBody9.getSourceLocation()) == null) ? null : sourceLocation8.getGpsLat()) != null) {
                    GeoFenceCommonModel geoFenceCommonModel12 = getViewModel().getGeoFenceCommonModel();
                    Double gpsLat2 = (geoFenceCommonModel12 == null || (geoFenceBody8 = geoFenceCommonModel12.getGeoFenceBody()) == null || (sourceLocation7 = geoFenceBody8.getSourceLocation()) == null) ? null : sourceLocation7.getGpsLat();
                    Intrinsics.checkNotNull(gpsLat2);
                    this.sourceLatitude = gpsLat2.doubleValue();
                }
                GeoFenceCommonModel geoFenceCommonModel13 = getViewModel().getGeoFenceCommonModel();
                if (((geoFenceCommonModel13 == null || (geoFenceBody7 = geoFenceCommonModel13.getGeoFenceBody()) == null || (sourceLocation6 = geoFenceBody7.getSourceLocation()) == null) ? null : sourceLocation6.getGpsLong()) != null) {
                    GeoFenceCommonModel geoFenceCommonModel14 = getViewModel().getGeoFenceCommonModel();
                    Double gpsLong2 = (geoFenceCommonModel14 == null || (geoFenceBody6 = geoFenceCommonModel14.getGeoFenceBody()) == null || (sourceLocation5 = geoFenceBody6.getSourceLocation()) == null) ? null : sourceLocation5.getGpsLong();
                    Intrinsics.checkNotNull(gpsLong2);
                    this.sourceLongitude = gpsLong2.doubleValue();
                }
                setSourceAddressText();
            }
            GeoFenceCommonModel geoFenceCommonModel15 = getViewModel().getGeoFenceCommonModel();
            if (((geoFenceCommonModel15 == null || (geoFenceBody5 = geoFenceCommonModel15.getGeoFenceBody()) == null) ? null : geoFenceBody5.getCentre()) != null) {
                GeoFenceCommonModel geoFenceCommonModel16 = getViewModel().getGeoFenceCommonModel();
                if (((geoFenceCommonModel16 == null || (geoFenceBody4 = geoFenceCommonModel16.getGeoFenceBody()) == null || (centre8 = geoFenceBody4.getCentre()) == null) ? null : centre8.getGpsLat()) != null) {
                    GeoFenceCommonModel geoFenceCommonModel17 = getViewModel().getGeoFenceCommonModel();
                    Double gpsLat3 = (geoFenceCommonModel17 == null || (geoFenceBody3 = geoFenceCommonModel17.getGeoFenceBody()) == null || (centre7 = geoFenceBody3.getCentre()) == null) ? null : centre7.getGpsLat();
                    Intrinsics.checkNotNull(gpsLat3);
                    this.destinationLatitude = gpsLat3.doubleValue();
                }
                GeoFenceCommonModel geoFenceCommonModel18 = getViewModel().getGeoFenceCommonModel();
                if (((geoFenceCommonModel18 == null || (geoFenceBody2 = geoFenceCommonModel18.getGeoFenceBody()) == null || (centre6 = geoFenceBody2.getCentre()) == null) ? null : centre6.getGpsLong()) != null) {
                    GeoFenceCommonModel geoFenceCommonModel19 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel19 != null && (geoFenceBody = geoFenceCommonModel19.getGeoFenceBody()) != null && (centre5 = geoFenceBody.getCentre()) != null) {
                        d2 = centre5.getGpsLong();
                    }
                    Intrinsics.checkNotNull(d2);
                    doubleValue = d2.doubleValue();
                    this.destinationLongitude = doubleValue;
                }
            }
            setEditBox(!Intrinsics.areEqual(this.fenceType, AppConstants.GEO_FENCE_MODE_ROUTE));
        } else {
            if (valueOf != null && valueOf.intValue() == 6) {
                GeoFenceCommonModel geoFenceCommonModel20 = getViewModel().getGeoFenceCommonModel();
                this.fenceType = String.valueOf((geoFenceCommonModel20 == null || (geoFenceGetResponse16 = geoFenceCommonModel20.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse16.getFenceMode());
                GeoFenceCommonModel geoFenceCommonModel21 = getViewModel().getGeoFenceCommonModel();
                if (((geoFenceCommonModel21 == null || (geoFenceGetResponse15 = geoFenceCommonModel21.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse15.getDestinationLocation()) != null) {
                    GeoFenceCommonModel geoFenceCommonModel22 = getViewModel().getGeoFenceCommonModel();
                    if (((geoFenceCommonModel22 == null || (geoFenceGetResponse14 = geoFenceCommonModel22.getGeoFenceGetResponse()) == null || (destinationLocation4 = geoFenceGetResponse14.getDestinationLocation()) == null) ? null : destinationLocation4.getGpsLat()) != null) {
                        GeoFenceCommonModel geoFenceCommonModel23 = getViewModel().getGeoFenceCommonModel();
                        Double gpsLat4 = (geoFenceCommonModel23 == null || (geoFenceGetResponse13 = geoFenceCommonModel23.getGeoFenceGetResponse()) == null || (destinationLocation3 = geoFenceGetResponse13.getDestinationLocation()) == null) ? null : destinationLocation3.getGpsLat();
                        Intrinsics.checkNotNull(gpsLat4);
                        this.destinationLatitude = gpsLat4.doubleValue();
                    }
                    GeoFenceCommonModel geoFenceCommonModel24 = getViewModel().getGeoFenceCommonModel();
                    if (((geoFenceCommonModel24 == null || (geoFenceGetResponse12 = geoFenceCommonModel24.getGeoFenceGetResponse()) == null || (destinationLocation2 = geoFenceGetResponse12.getDestinationLocation()) == null) ? null : destinationLocation2.getGpsLong()) != null) {
                        GeoFenceCommonModel geoFenceCommonModel25 = getViewModel().getGeoFenceCommonModel();
                        Double gpsLong3 = (geoFenceCommonModel25 == null || (geoFenceGetResponse11 = geoFenceCommonModel25.getGeoFenceGetResponse()) == null || (destinationLocation = geoFenceGetResponse11.getDestinationLocation()) == null) ? null : destinationLocation.getGpsLong();
                        Intrinsics.checkNotNull(gpsLong3);
                        this.destinationLongitude = gpsLong3.doubleValue();
                    }
                    setDestinationAddressText();
                }
                GeoFenceCommonModel geoFenceCommonModel26 = getViewModel().getGeoFenceCommonModel();
                if (((geoFenceCommonModel26 == null || (geoFenceGetResponse10 = geoFenceCommonModel26.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse10.getSourceLocation()) != null) {
                    GeoFenceCommonModel geoFenceCommonModel27 = getViewModel().getGeoFenceCommonModel();
                    if (((geoFenceCommonModel27 == null || (geoFenceGetResponse9 = geoFenceCommonModel27.getGeoFenceGetResponse()) == null || (sourceLocation4 = geoFenceGetResponse9.getSourceLocation()) == null) ? null : sourceLocation4.getGpsLat()) != null) {
                        GeoFenceCommonModel geoFenceCommonModel28 = getViewModel().getGeoFenceCommonModel();
                        Double gpsLat5 = (geoFenceCommonModel28 == null || (geoFenceGetResponse8 = geoFenceCommonModel28.getGeoFenceGetResponse()) == null || (sourceLocation3 = geoFenceGetResponse8.getSourceLocation()) == null) ? null : sourceLocation3.getGpsLat();
                        Intrinsics.checkNotNull(gpsLat5);
                        this.sourceLatitude = gpsLat5.doubleValue();
                    }
                    GeoFenceCommonModel geoFenceCommonModel29 = getViewModel().getGeoFenceCommonModel();
                    if (((geoFenceCommonModel29 == null || (geoFenceGetResponse7 = geoFenceCommonModel29.getGeoFenceGetResponse()) == null || (sourceLocation2 = geoFenceGetResponse7.getSourceLocation()) == null) ? null : sourceLocation2.getGpsLong()) != null) {
                        GeoFenceCommonModel geoFenceCommonModel30 = getViewModel().getGeoFenceCommonModel();
                        Double gpsLong4 = (geoFenceCommonModel30 == null || (geoFenceGetResponse6 = geoFenceCommonModel30.getGeoFenceGetResponse()) == null || (sourceLocation = geoFenceGetResponse6.getSourceLocation()) == null) ? null : sourceLocation.getGpsLong();
                        Intrinsics.checkNotNull(gpsLong4);
                        this.sourceLongitude = gpsLong4.doubleValue();
                    }
                    setSourceAddressText();
                }
                GeoFenceCommonModel geoFenceCommonModel31 = getViewModel().getGeoFenceCommonModel();
                if (((geoFenceCommonModel31 == null || (geoFenceGetResponse5 = geoFenceCommonModel31.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse5.getCentre()) != null) {
                    GeoFenceCommonModel geoFenceCommonModel32 = getViewModel().getGeoFenceCommonModel();
                    if (((geoFenceCommonModel32 == null || (geoFenceGetResponse4 = geoFenceCommonModel32.getGeoFenceGetResponse()) == null || (centre4 = geoFenceGetResponse4.getCentre()) == null) ? null : centre4.getGpsLat()) != null) {
                        GeoFenceCommonModel geoFenceCommonModel33 = getViewModel().getGeoFenceCommonModel();
                        Double gpsLat6 = (geoFenceCommonModel33 == null || (geoFenceGetResponse3 = geoFenceCommonModel33.getGeoFenceGetResponse()) == null || (centre3 = geoFenceGetResponse3.getCentre()) == null) ? null : centre3.getGpsLat();
                        Intrinsics.checkNotNull(gpsLat6);
                        this.destinationLatitude = gpsLat6.doubleValue();
                    }
                    GeoFenceCommonModel geoFenceCommonModel34 = getViewModel().getGeoFenceCommonModel();
                    if (((geoFenceCommonModel34 == null || (geoFenceGetResponse2 = geoFenceCommonModel34.getGeoFenceGetResponse()) == null || (centre2 = geoFenceGetResponse2.getCentre()) == null) ? null : centre2.getGpsLong()) != null) {
                        GeoFenceCommonModel geoFenceCommonModel35 = getViewModel().getGeoFenceCommonModel();
                        if (geoFenceCommonModel35 != null && (geoFenceGetResponse = geoFenceCommonModel35.getGeoFenceGetResponse()) != null && (centre = geoFenceGetResponse.getCentre()) != null) {
                            d2 = centre.getGpsLong();
                        }
                        Intrinsics.checkNotNull(d2);
                        doubleValue = d2.doubleValue();
                        this.destinationLongitude = doubleValue;
                    }
                }
            }
            setEditBox(!Intrinsics.areEqual(this.fenceType, AppConstants.GEO_FENCE_MODE_ROUTE));
        }
        setDestinationAddressText();
        setEditBox(!Intrinsics.areEqual(this.fenceType, AppConstants.GEO_FENCE_MODE_ROUTE));
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x00d7, code lost:
        if (r0.equals(com.psa.mym.mycitroenconnect.common.AppConstants.GEO_FENCE_MODE_RADIUS) != false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00e0, code lost:
        if (r0.equals(com.psa.mym.mycitroenconnect.common.AppConstants.GEO_FENCE_MODE_CUSTOM) == false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00e4, code lost:
        r0 = com.psa.mym.mycitroenconnect.R.id.edtDestination;
        r1 = ((androidx.appcompat.widget.AppCompatEditText) _$_findCachedViewById(r0)).getText();
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00f0, code lost:
        if (r1 == null) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00f6, code lost:
        if (r1.length() != 0) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00f9, code lost:
        r1 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00fb, code lost:
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00fc, code lost:
        if (r1 != false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00fe, code lost:
        r0 = ((androidx.appcompat.widget.AppCompatEditText) _$_findCachedViewById(r0)).getText();
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0108, code lost:
        if (r0 == null) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x010a, code lost:
        r0 = kotlin.text.StringsKt__StringsJVMKt.isBlank(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x010e, code lost:
        if (r0 == false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0111, code lost:
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0113, code lost:
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0114, code lost:
        if (r0 == false) goto L45;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0092  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean validateSelection() {
        Context requireContext;
        String string;
        String str;
        boolean z;
        boolean z2;
        boolean isBlank;
        boolean isBlank2;
        String str2 = this.fenceType;
        int hashCode = str2.hashCode();
        if (hashCode != -1349088399) {
            if (hashCode != -938578798) {
                if (hashCode == 108704329 && str2.equals(AppConstants.GEO_FENCE_MODE_ROUTE)) {
                    int i2 = R.id.edtSource;
                    Editable text = ((AppCompatEditText) _$_findCachedViewById(i2)).getText();
                    if (!(text == null || text.length() == 0)) {
                        Editable text2 = ((AppCompatEditText) _$_findCachedViewById(i2)).getText();
                        if (text2 != null) {
                            isBlank2 = StringsKt__StringsJVMKt.isBlank(text2);
                            if (!isBlank2) {
                                z = false;
                                if (!z) {
                                    int i3 = R.id.edtDestination;
                                    Editable text3 = ((AppCompatEditText) _$_findCachedViewById(i3)).getText();
                                    if (!(text3 == null || text3.length() == 0)) {
                                        Editable text4 = ((AppCompatEditText) _$_findCachedViewById(i3)).getText();
                                        if (text4 != null) {
                                            isBlank = StringsKt__StringsJVMKt.isBlank(text4);
                                            if (!isBlank) {
                                                z2 = false;
                                                if (!z2) {
                                                    if (this.sourceLatitude == this.destinationLatitude) {
                                                        if (this.sourceLongitude == this.destinationLongitude) {
                                                            requireContext = requireContext();
                                                            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                                                            string = getString(uat.psa.mym.mycitroenconnect.R.string.source_destination_should_not_same);
                                                            str = "getString(R.string.sourc…tination_should_not_same)";
                                                            Intrinsics.checkNotNullExpressionValue(string, str);
                                                        }
                                                    }
                                                    setLocationInModel();
                                                    return true;
                                                }
                                            }
                                        }
                                        z2 = true;
                                        if (!z2) {
                                        }
                                    }
                                    requireContext = requireContext();
                                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                                    string = getString(uat.psa.mym.mycitroenconnect.R.string.select_destination_location);
                                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.select_destination_location)");
                                }
                            }
                        }
                        z = true;
                        if (!z) {
                        }
                    }
                    requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    string = getString(uat.psa.mym.mycitroenconnect.R.string.select_source_location);
                    str = "getString(R.string.select_source_location)";
                    Intrinsics.checkNotNullExpressionValue(string, str);
                }
            }
            return false;
        }
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
        Intent intent;
        ActivityResultLauncher<Intent> activityResultLauncher;
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnConfirmLocation))) {
            if (validateSelection()) {
                this.isLocationChange = false;
                navigateToGeoFenceMapFragment();
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(view, (AppCompatEditText) _$_findCachedViewById(R.id.edtSource))) {
            this.isFirstTime = false;
            this.isLocationChange = true;
            intent = new Intent(requireContext(), SearchLocationActivity.class);
            activityResultLauncher = this.sourceResultLauncher;
        } else if (!Intrinsics.areEqual(view, (AppCompatEditText) _$_findCachedViewById(R.id.edtDestination))) {
            return;
        } else {
            this.isFirstTime = false;
            this.isLocationChange = true;
            intent = new Intent(requireContext(), SearchLocationActivity.class);
            activityResultLauncher = this.destinationResultLauncher;
        }
        activityResultLauncher.launch(intent);
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
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_set_location, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.isLocationChange) {
            return;
        }
        setUIData();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initView();
    }
}
