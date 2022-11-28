package com.psa.mym.mycitroenconnect.controller.fragments.geo_fence;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;
import com.psa.mym.mycitroenconnect.BusBaseFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.api.body.geo_fence.Coordinates;
import com.psa.mym.mycitroenconnect.api.body.geo_fence.GeoFenceBody;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.dashboard.GeoFenceActivity;
import com.psa.mym.mycitroenconnect.controller.viewmodel.GeoFenceViewModel;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.FailResponse;
import com.psa.mym.mycitroenconnect.model.GeoFenceCommonModel;
import com.psa.mym.mycitroenconnect.model.geo_fence.GetGeoFenceResponseItem;
import com.psa.mym.mycitroenconnect.model.geo_fence.LocationData;
import com.psa.mym.mycitroenconnect.model.geo_fence.PostGeoFenceResponse;
import com.psa.mym.mycitroenconnect.services.GeoFenceService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.CharsKt__CharJVMKt;
import kotlin.text.StringsKt__StringsJVMKt;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class GeoFenceSummaryFragment extends BusBaseFragment implements View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private long lastClickTime;
    @Nullable
    private GeoFenceActivity parentActivity;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private final Lazy viewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(GeoFenceViewModel.class), new GeoFenceSummaryFragment$special$$inlined$activityViewModels$default$1(this), new GeoFenceSummaryFragment$special$$inlined$activityViewModels$default$2(this));

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final GeoFenceSummaryFragment newInstance() {
            return new GeoFenceSummaryFragment();
        }
    }

    static /* synthetic */ void a(GeoFenceSummaryFragment geoFenceSummaryFragment, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = false;
        }
        geoFenceSummaryFragment.hideEditNameUI(z);
    }

    private final void createFence(boolean z) {
        GeoFenceBody geoFenceBody;
        String str;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        if (geoFenceCommonModel != null) {
            if (z) {
                geoFenceBody = geoFenceCommonModel.getGeoFenceBody();
                str = ExifInterface.GPS_MEASUREMENT_IN_PROGRESS;
            } else {
                geoFenceBody = geoFenceCommonModel.getGeoFenceBody();
                str = "D";
            }
            geoFenceBody.setFenceStatus(str);
            Logger logger = Logger.INSTANCE;
            logger.e("GeoFenceBody " + new Gson().toJson(geoFenceCommonModel.getGeoFenceBody()));
            AppUtil.Companion companion = AppUtil.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            companion.showDialog(requireContext);
            GeoFenceService geoFenceService = new GeoFenceService();
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            SharedPref.Companion companion2 = SharedPref.Companion;
            Context requireContext3 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
            geoFenceService.createGeoFence(requireContext2, companion2.getVinNumber(requireContext3), geoFenceCommonModel.getGeoFenceBody());
        }
    }

    private final String getDestinationAddressText() {
        GeoFenceBody geoFenceBody;
        Coordinates destinationLocation;
        Double gpsLat;
        double doubleValue;
        GeoFenceBody geoFenceBody2;
        Coordinates destinationLocation2;
        Double gpsLong;
        GeoFenceBody geoFenceBody3;
        Coordinates centre;
        Double gpsLat2;
        GeoFenceBody geoFenceBody4;
        Coordinates centre2;
        GeoFenceBody geoFenceBody5;
        Coordinates destinationLocation3;
        Double gpsLat3;
        GeoFenceBody geoFenceBody6;
        Coordinates destinationLocation4;
        GeoFenceBody geoFenceBody7;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        String fenceMode = (geoFenceCommonModel == null || (geoFenceBody7 = geoFenceCommonModel.getGeoFenceBody()) == null) ? null : geoFenceBody7.getFenceMode();
        if (fenceMode != null) {
            int hashCode = fenceMode.hashCode();
            if (hashCode != -1349088399) {
                if (hashCode != -938578798) {
                    if (hashCode == 108704329 && fenceMode.equals(AppConstants.GEO_FENCE_MODE_ROUTE)) {
                        GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
                        if (geoFenceCommonModel2 == null || (geoFenceBody5 = geoFenceCommonModel2.getGeoFenceBody()) == null || (destinationLocation3 = geoFenceBody5.getDestinationLocation()) == null || (gpsLat3 = destinationLocation3.getGpsLat()) == null) {
                            return null;
                        }
                        doubleValue = gpsLat3.doubleValue();
                        GeoFenceCommonModel geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
                        if (geoFenceCommonModel3 == null || (geoFenceBody6 = geoFenceCommonModel3.getGeoFenceBody()) == null || (destinationLocation4 = geoFenceBody6.getDestinationLocation()) == null || (gpsLong = destinationLocation4.getGpsLong()) == null) {
                            return null;
                        }
                        double doubleValue2 = gpsLong.doubleValue();
                        AppUtil.Companion companion = AppUtil.Companion;
                        Context requireContext = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                        return companion.getAddressNameString(requireContext, doubleValue, doubleValue2);
                    }
                } else if (fenceMode.equals(AppConstants.GEO_FENCE_MODE_RADIUS)) {
                    GeoFenceCommonModel geoFenceCommonModel4 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel4 == null || (geoFenceBody3 = geoFenceCommonModel4.getGeoFenceBody()) == null || (centre = geoFenceBody3.getCentre()) == null || (gpsLat2 = centre.getGpsLat()) == null) {
                        return null;
                    }
                    doubleValue = gpsLat2.doubleValue();
                    GeoFenceCommonModel geoFenceCommonModel5 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel5 == null || (geoFenceBody4 = geoFenceCommonModel5.getGeoFenceBody()) == null || (centre2 = geoFenceBody4.getCentre()) == null || (gpsLong = centre2.getGpsLong()) == null) {
                        return null;
                    }
                    double doubleValue22 = gpsLong.doubleValue();
                    AppUtil.Companion companion2 = AppUtil.Companion;
                    Context requireContext2 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    return companion2.getAddressNameString(requireContext2, doubleValue, doubleValue22);
                }
            } else if (fenceMode.equals(AppConstants.GEO_FENCE_MODE_CUSTOM)) {
                GeoFenceCommonModel geoFenceCommonModel6 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel6 == null || (geoFenceBody = geoFenceCommonModel6.getGeoFenceBody()) == null || (destinationLocation = geoFenceBody.getDestinationLocation()) == null || (gpsLat = destinationLocation.getGpsLat()) == null) {
                    return null;
                }
                doubleValue = gpsLat.doubleValue();
                GeoFenceCommonModel geoFenceCommonModel7 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel7 == null || (geoFenceBody2 = geoFenceCommonModel7.getGeoFenceBody()) == null || (destinationLocation2 = geoFenceBody2.getDestinationLocation()) == null || (gpsLong = destinationLocation2.getGpsLong()) == null) {
                    return null;
                }
                double doubleValue222 = gpsLong.doubleValue();
                AppUtil.Companion companion22 = AppUtil.Companion;
                Context requireContext22 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext22, "requireContext()");
                return companion22.getAddressNameString(requireContext22, doubleValue, doubleValue222);
            }
        }
        return "";
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00af, code lost:
        if (r0.equals(com.psa.mym.mycitroenconnect.common.AppConstants.GEO_FENCE_MODE_RADIUS) != false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00b8, code lost:
        if (r0.equals(com.psa.mym.mycitroenconnect.common.AppConstants.GEO_FENCE_MODE_CUSTOM) == false) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00bb, code lost:
        r0 = (androidx.appcompat.widget.LinearLayoutCompat) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.llSource);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, "llSource");
        com.psa.mym.mycitroenconnect.utils.ExtensionsKt.hide(r0);
        r0 = _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.viewSeperatorLine);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, "viewSeperatorLine");
        com.psa.mym.mycitroenconnect.utils.ExtensionsKt.hide(r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final String getSourceAddressText() {
        GeoFenceBody geoFenceBody;
        Coordinates sourceLocation;
        Double gpsLat;
        GeoFenceBody geoFenceBody2;
        Coordinates sourceLocation2;
        Double gpsLong;
        GeoFenceBody geoFenceBody3;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        String fenceMode = (geoFenceCommonModel == null || (geoFenceBody3 = geoFenceCommonModel.getGeoFenceBody()) == null) ? null : geoFenceBody3.getFenceMode();
        if (fenceMode != null) {
            int hashCode = fenceMode.hashCode();
            if (hashCode != -1349088399) {
                if (hashCode != -938578798) {
                    if (hashCode == 108704329 && fenceMode.equals(AppConstants.GEO_FENCE_MODE_ROUTE)) {
                        LinearLayoutCompat llSource = (LinearLayoutCompat) _$_findCachedViewById(R.id.llSource);
                        Intrinsics.checkNotNullExpressionValue(llSource, "llSource");
                        ExtensionsKt.show(llSource);
                        View viewSeperatorLine = _$_findCachedViewById(R.id.viewSeperatorLine);
                        Intrinsics.checkNotNullExpressionValue(viewSeperatorLine, "viewSeperatorLine");
                        ExtensionsKt.show(viewSeperatorLine);
                        GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
                        if (geoFenceCommonModel2 == null || (geoFenceBody = geoFenceCommonModel2.getGeoFenceBody()) == null || (sourceLocation = geoFenceBody.getSourceLocation()) == null || (gpsLat = sourceLocation.getGpsLat()) == null) {
                            return null;
                        }
                        double doubleValue = gpsLat.doubleValue();
                        GeoFenceCommonModel geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
                        if (geoFenceCommonModel3 == null || (geoFenceBody2 = geoFenceCommonModel3.getGeoFenceBody()) == null || (sourceLocation2 = geoFenceBody2.getSourceLocation()) == null || (gpsLong = sourceLocation2.getGpsLong()) == null) {
                            return null;
                        }
                        double doubleValue2 = gpsLong.doubleValue();
                        AppUtil.Companion companion = AppUtil.Companion;
                        Context requireContext = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                        return companion.getAddressNameString(requireContext, doubleValue, doubleValue2);
                    }
                }
            }
        }
        return "";
    }

    private final String getTransitionType() {
        String string;
        GeoFenceBody geoFenceBody;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        String transitionType = (geoFenceCommonModel == null || (geoFenceBody = geoFenceCommonModel.getGeoFenceBody()) == null) ? null : geoFenceBody.getTransitionType();
        String str = "{\n                getStr…tring.both)\n            }";
        if (transitionType != null) {
            int hashCode = transitionType.hashCode();
            if (hashCode != 2341) {
                if (hashCode != 78638) {
                    if (hashCode == 69819369) {
                        transitionType.equals(AppConstants.GEO_FENCE_TRANSITION_BOTH);
                    }
                } else if (transitionType.equals(AppConstants.GEO_FENCE_TRANSITION_OUT)) {
                    string = getString(uat.psa.mym.mycitroenconnect.R.string.exit);
                    str = "{\n                getStr…tring.exit)\n            }";
                }
            } else if (transitionType.equals(AppConstants.GEO_FENCE_TRANSITION_IN)) {
                string = getString(uat.psa.mym.mycitroenconnect.R.string.entry);
                str = "{\n                getStr…ring.entry)\n            }";
            }
            Intrinsics.checkNotNullExpressionValue(string, str);
            return string;
        }
        string = getString(uat.psa.mym.mycitroenconnect.R.string.both);
        Intrinsics.checkNotNullExpressionValue(string, str);
        return string;
    }

    private final GeoFenceViewModel getViewModel() {
        return (GeoFenceViewModel) this.viewModel$delegate.getValue();
    }

    private final void hideEditNameUI(boolean z) {
        ((TextInputEditText) _$_findCachedViewById(R.id.edtFenceName)).setEnabled(false);
        if (z) {
            setFenceName();
        }
        setFenceNameInModel();
        AppCompatImageView ivFenceNameEdit = (AppCompatImageView) _$_findCachedViewById(R.id.ivFenceNameEdit);
        Intrinsics.checkNotNullExpressionValue(ivFenceNameEdit, "ivFenceNameEdit");
        ExtensionsKt.show(ivFenceNameEdit);
        AppCompatImageView ivFenceNameClose = (AppCompatImageView) _$_findCachedViewById(R.id.ivFenceNameClose);
        Intrinsics.checkNotNullExpressionValue(ivFenceNameClose, "ivFenceNameClose");
        ExtensionsKt.hide(ivFenceNameClose);
        AppCompatImageView ivFenceNameUpdateCheck = (AppCompatImageView) _$_findCachedViewById(R.id.ivFenceNameUpdateCheck);
        Intrinsics.checkNotNullExpressionValue(ivFenceNameUpdateCheck, "ivFenceNameUpdateCheck");
        ExtensionsKt.hide(ivFenceNameUpdateCheck);
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
        TextInputEditText edtFenceName = (TextInputEditText) _$_findCachedViewById(R.id.edtFenceName);
        Intrinsics.checkNotNullExpressionValue(edtFenceName, "edtFenceName");
        ExtensionsKt.disableEmoji(edtFenceName);
        setListeners();
    }

    private final void navigateToListActivity() {
        requireActivity().finish();
    }

    @JvmStatic
    @NotNull
    public static final GeoFenceSummaryFragment newInstance() {
        return Companion.newInstance();
    }

    @SuppressLint({"SetTextI18n"})
    private final void setDateRangeValue() {
        GeoFenceBody geoFenceBody;
        String endTime;
        GeoFenceBody geoFenceBody2;
        String startTime;
        GeoFenceBody geoFenceBody3;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        String str = null;
        if (Intrinsics.areEqual((geoFenceCommonModel == null || (geoFenceBody3 = geoFenceCommonModel.getGeoFenceBody()) == null) ? null : geoFenceBody3.getTimeMode(), AppConstants.GEO_FENCE_TIME_MODE_SKIP)) {
            MaterialTextView tvDate = (MaterialTextView) _$_findCachedViewById(R.id.tvDate);
            Intrinsics.checkNotNullExpressionValue(tvDate, "tvDate");
            ExtensionsKt.hide(tvDate);
            return;
        }
        int i2 = R.id.tvDate;
        MaterialTextView tvDate2 = (MaterialTextView) _$_findCachedViewById(i2);
        Intrinsics.checkNotNullExpressionValue(tvDate2, "tvDate");
        ExtensionsKt.show(tvDate2);
        MaterialTextView materialTextView = (MaterialTextView) _$_findCachedViewById(i2);
        StringBuilder sb = new StringBuilder();
        GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
        sb.append((geoFenceCommonModel2 == null || (geoFenceBody2 = geoFenceCommonModel2.getGeoFenceBody()) == null || (startTime = geoFenceBody2.getStartTime()) == null) ? null : AppUtil.Companion.convertToDate(startTime));
        sb.append(" - ");
        GeoFenceCommonModel geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
        if (geoFenceCommonModel3 != null && (geoFenceBody = geoFenceCommonModel3.getGeoFenceBody()) != null && (endTime = geoFenceBody.getEndTime()) != null) {
            str = AppUtil.Companion.convertToDate(endTime);
        }
        sb.append(str);
        materialTextView.setText(sb.toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002a, code lost:
        if (r0.equals(com.psa.mym.mycitroenconnect.common.AppConstants.GEO_FENCE_TIME_MODE_FULL_DAY_ALL_DAYS) == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0034, code lost:
        if (r0.equals(com.psa.mym.mycitroenconnect.common.AppConstants.GEO_FENCE_TIME_MODE_SET_TIME_ALL_DAYS) == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0038, code lost:
        r0 = com.psa.mym.mycitroenconnect.R.id.tvDate;
        r1 = (com.google.android.material.textview.MaterialTextView) _$_findCachedViewById(r0);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, "tvDate");
        com.psa.mym.mycitroenconnect.utils.ExtensionsKt.show(r1);
        r0 = (com.google.android.material.textview.MaterialTextView) _$_findCachedViewById(r0);
        r1 = uat.psa.mym.mycitroenconnect.R.string.lbl_all_days;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0057, code lost:
        if (r0.equals(com.psa.mym.mycitroenconnect.common.AppConstants.GEO_FENCE_TIME_MODE_SET_TIME_WEEK_DAYS) == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0061, code lost:
        if (r0.equals(com.psa.mym.mycitroenconnect.common.AppConstants.GEO_FENCE_TIME_MODE_SET_TIME_CUSTOM_DAYS) == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006b, code lost:
        if (r0.equals(com.psa.mym.mycitroenconnect.common.AppConstants.GEO_FENCE_TIME_MODE_FULL_DAY_CUSTOM_DAYS) == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x006f, code lost:
        r0 = getViewModel().getGeoFenceCommonModel();
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0079, code lost:
        if (r0 == null) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x007b, code lost:
        r0 = r0.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x007f, code lost:
        if (r0 == null) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0085, code lost:
        if (r0.getDaysOfWeek() == null) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x008c, code lost:
        if ((!r0.isEmpty()) != true) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x008e, code lost:
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x008f, code lost:
        if (r2 == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0091, code lost:
        r0 = com.psa.mym.mycitroenconnect.R.id.tvDate;
        r2 = (com.google.android.material.textview.MaterialTextView) _$_findCachedViewById(r0);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, "tvDate");
        com.psa.mym.mycitroenconnect.utils.ExtensionsKt.show(r2);
        r0 = (com.google.android.material.textview.MaterialTextView) _$_findCachedViewById(r0);
        r2 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00ad, code lost:
        if (r2 == null) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00af, code lost:
        r2 = r2.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00b3, code lost:
        if (r2 == null) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00b5, code lost:
        r1 = r2.getDaysOfWeek();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b9, code lost:
        kotlin.jvm.internal.Intrinsics.checkNotNull(r1);
        r1 = android.text.TextUtils.join(", ", r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00c9, code lost:
        if (r0.equals(com.psa.mym.mycitroenconnect.common.AppConstants.GEO_FENCE_TIME_MODE_FULL_DAY_WEEK_DAYS) == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00cc, code lost:
        r0 = com.psa.mym.mycitroenconnect.R.id.tvDate;
        r1 = (com.google.android.material.textview.MaterialTextView) _$_findCachedViewById(r0);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, "tvDate");
        com.psa.mym.mycitroenconnect.utils.ExtensionsKt.show(r1);
        r0 = (com.google.android.material.textview.MaterialTextView) _$_findCachedViewById(r0);
        r1 = uat.psa.mym.mycitroenconnect.R.string.lbl_all_week_days;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00e3, code lost:
        r1 = getString(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00e7, code lost:
        r0.setText(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:?, code lost:
        return;
     */
    @SuppressLint({"SetTextI18n"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void setDaysValue() {
        String str;
        GeoFenceBody geoFenceBody;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        ArrayList<String> arrayList = null;
        String timeMode = (geoFenceCommonModel == null || (geoFenceBody = geoFenceCommonModel.getGeoFenceBody()) == null) ? null : geoFenceBody.getTimeMode();
        if (timeMode != null) {
            switch (timeMode.hashCode()) {
                case -1385810073:
                    str = AppConstants.GEO_FENCE_TIME_MODE_FULL_DAY_CUSTOM_DATE_RANGE;
                    timeMode.equals(str);
                    break;
                case -1339174267:
                    str = AppConstants.GEO_FENCE_TIME_MODE_SET_TIME_CUSTOM_DATE_RANGE;
                    timeMode.equals(str);
                    break;
                case -1300341969:
                    break;
                case -816877934:
                    break;
                case -96913360:
                    break;
                case 166327373:
                    break;
                case 901250122:
                    break;
                case 1131032872:
                    break;
            }
        }
        setDateRangeValue();
    }

    private final void setFenceName() {
        TextInputEditText textInputEditText;
        GeoFenceBody geoFenceBody;
        GetGeoFenceResponseItem geoFenceResponse;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        String str = null;
        Integer valueOf = geoFenceCommonModel != null ? Integer.valueOf(geoFenceCommonModel.getFenceCreationMode()) : null;
        if (valueOf != null && valueOf.intValue() == 6) {
            textInputEditText = (TextInputEditText) _$_findCachedViewById(R.id.edtFenceName);
            GeoFenceActivity geoFenceActivity = this.parentActivity;
            if (geoFenceActivity != null && (geoFenceResponse = geoFenceActivity.getGeoFenceResponse()) != null) {
                str = geoFenceResponse.getFenceName();
            }
        } else {
            textInputEditText = (TextInputEditText) _$_findCachedViewById(R.id.edtFenceName);
            GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel2 != null && (geoFenceBody = geoFenceCommonModel2.getGeoFenceBody()) != null) {
                str = geoFenceBody.getFenceName();
            }
        }
        textInputEditText.setText(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void setFenceNameInModel() {
        int i2;
        boolean z;
        GeoFenceBody geoFenceBody;
        boolean isBlank;
        boolean z2;
        boolean isBlank2;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        Integer valueOf = geoFenceCommonModel != null ? Integer.valueOf(geoFenceCommonModel.getFenceCreationMode()) : null;
        boolean z3 = false;
        if (valueOf != null && valueOf.intValue() == 6) {
            i2 = R.id.edtFenceName;
            Editable text = ((TextInputEditText) _$_findCachedViewById(i2)).getText();
            if (text != null) {
                if (text.length() > 0) {
                    z2 = true;
                    if (z2) {
                        return;
                    }
                    Editable text2 = ((TextInputEditText) _$_findCachedViewById(i2)).getText();
                    if (text2 != null) {
                        isBlank2 = StringsKt__StringsJVMKt.isBlank(text2);
                        if (!isBlank2) {
                            z3 = true;
                        }
                    }
                    if (!z3) {
                        return;
                    }
                    GeoFenceActivity geoFenceActivity = this.parentActivity;
                    GetGeoFenceResponseItem geoFenceResponse = geoFenceActivity != null ? geoFenceActivity.getGeoFenceResponse() : null;
                    if (geoFenceResponse != null) {
                        geoFenceResponse.setFenceName(String.valueOf(((TextInputEditText) _$_findCachedViewById(i2)).getText()));
                    }
                    GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
                    geoFenceBody = geoFenceCommonModel2 != null ? geoFenceCommonModel2.getGeoFenceBody() : null;
                    if (geoFenceBody == null) {
                        return;
                    }
                }
            }
            z2 = false;
            if (z2) {
            }
        } else {
            i2 = R.id.edtFenceName;
            Editable text3 = ((TextInputEditText) _$_findCachedViewById(i2)).getText();
            if (text3 != null) {
                if (text3.length() > 0) {
                    z = true;
                    if (z) {
                        return;
                    }
                    Editable text4 = ((TextInputEditText) _$_findCachedViewById(i2)).getText();
                    if (text4 != null) {
                        isBlank = StringsKt__StringsJVMKt.isBlank(text4);
                        if (!isBlank) {
                            z3 = true;
                        }
                    }
                    if (!z3) {
                        return;
                    }
                    GeoFenceCommonModel geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
                    geoFenceBody = geoFenceCommonModel3 != null ? geoFenceCommonModel3.getGeoFenceBody() : null;
                    if (geoFenceBody == null) {
                        return;
                    }
                }
            }
            z = false;
            if (z) {
            }
        }
        geoFenceBody.setFenceName(String.valueOf(((TextInputEditText) _$_findCachedViewById(i2)).getText()));
    }

    private final void setListeners() {
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivFenceNameEdit)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivFenceNameClose)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivFenceNameUpdateCheck)).setOnClickListener(this);
        ((RelativeLayout) _$_findCachedViewById(R.id.rlFenceType)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivFenceTypeNav)).setOnClickListener(this);
        ((RelativeLayout) _$_findCachedViewById(R.id.fenceMode)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivFenceModeNav)).setOnClickListener(this);
        ((RelativeLayout) _$_findCachedViewById(R.id.rlFenceRoute)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivRouteNav)).setOnClickListener(this);
        ((RelativeLayout) _$_findCachedViewById(R.id.rlFenceTime)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivSetTimeNav)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnActivate)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnActivateLater)).setOnClickListener(this);
    }

    /* JADX WARN: Code restructure failed: missing block: B:116:0x0195, code lost:
        if (((r1 == null || (r1 = r1.getGeoFenceBody()) == null) ? null : r1.getFenceStatus()) == null) goto L668;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x01e4, code lost:
        if (((r1 == null || (r1 = r1.getGeoFenceBody()) == null) ? null : r1.getTransitionType()) == null) goto L658;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0258, code lost:
        if (((r1 == null || (r1 = r1.getGeoFenceBody()) == null) ? null : r1.getFenceMode()) == null) goto L623;
     */
    /* JADX WARN: Code restructure failed: missing block: B:238:0x034f, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r1, (r11 == null || (r11 = r11.getGeoFenceGetResponse()) == null || (r11 = r11.getDestinationLocation()) == null) ? null : r11.getGpsLong()) == false) goto L613;
     */
    /* JADX WARN: Code restructure failed: missing block: B:245:0x0367, code lost:
        if (((r1 == null || (r1 = r1.getGeoFenceBody()) == null) ? null : r1.getDestinationLocation()) == null) goto L145;
     */
    /* JADX WARN: Code restructure failed: missing block: B:246:0x0369, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:247:0x0371, code lost:
        if (r1 == null) goto L610;
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x0373, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:249:0x0378, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:250:0x0379, code lost:
        if (r1 != null) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:252:0x037c, code lost:
        r12 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:253:0x0386, code lost:
        if (r12 == null) goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:254:0x0388, code lost:
        r12 = r12.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:255:0x038c, code lost:
        if (r12 == null) goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:256:0x038e, code lost:
        r12 = r12.getDestinationLocation();
     */
    /* JADX WARN: Code restructure failed: missing block: B:257:0x0392, code lost:
        if (r12 == null) goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:258:0x0394, code lost:
        r12 = r12.getGpsLat();
     */
    /* JADX WARN: Code restructure failed: missing block: B:259:0x0399, code lost:
        r12 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:260:0x039a, code lost:
        r13 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:261:0x03a2, code lost:
        if (r13 == null) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:262:0x03a4, code lost:
        r13 = r13.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:263:0x03a8, code lost:
        if (r13 == null) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:264:0x03aa, code lost:
        r13 = r13.getDestinationLocation();
     */
    /* JADX WARN: Code restructure failed: missing block: B:265:0x03ae, code lost:
        if (r13 == null) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:266:0x03b0, code lost:
        r13 = r13.getGpsLong();
     */
    /* JADX WARN: Code restructure failed: missing block: B:267:0x03b5, code lost:
        r13 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:268:0x03b6, code lost:
        r1.setDestinationLocation(new com.psa.mym.mycitroenconnect.api.body.geo_fence.Coordinates(r12, r13));
     */
    /* JADX WARN: Code restructure failed: missing block: B:269:0x03bc, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0059, code lost:
        if (((r1 == null || (r1 = r1.getGeoFenceBody()) == null) ? null : r1.getTransitionType()) == null) goto L692;
     */
    /* JADX WARN: Code restructure failed: missing block: B:270:0x03c4, code lost:
        if (r1 == null) goto L609;
     */
    /* JADX WARN: Code restructure failed: missing block: B:271:0x03c6, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:272:0x03ca, code lost:
        if (r1 == null) goto L609;
     */
    /* JADX WARN: Code restructure failed: missing block: B:273:0x03cc, code lost:
        r1 = r1.getSourceLocation();
     */
    /* JADX WARN: Code restructure failed: missing block: B:274:0x03d0, code lost:
        if (r1 == null) goto L609;
     */
    /* JADX WARN: Code restructure failed: missing block: B:275:0x03d2, code lost:
        r1 = r1.getGpsLat();
     */
    /* JADX WARN: Code restructure failed: missing block: B:276:0x03d7, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:277:0x03d8, code lost:
        r11 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:278:0x03e0, code lost:
        if (r11 == null) goto L608;
     */
    /* JADX WARN: Code restructure failed: missing block: B:279:0x03e2, code lost:
        r11 = r11.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:280:0x03e6, code lost:
        if (r11 == null) goto L608;
     */
    /* JADX WARN: Code restructure failed: missing block: B:281:0x03e8, code lost:
        r11 = r11.getSourceLocation();
     */
    /* JADX WARN: Code restructure failed: missing block: B:282:0x03ec, code lost:
        if (r11 == null) goto L608;
     */
    /* JADX WARN: Code restructure failed: missing block: B:283:0x03ee, code lost:
        r11 = r11.getGpsLat();
     */
    /* JADX WARN: Code restructure failed: missing block: B:284:0x03f3, code lost:
        r11 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:286:0x03f8, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r1, r11) == false) goto L601;
     */
    /* JADX WARN: Code restructure failed: missing block: B:287:0x03fa, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:288:0x0402, code lost:
        if (r1 == null) goto L600;
     */
    /* JADX WARN: Code restructure failed: missing block: B:289:0x0404, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:290:0x0408, code lost:
        if (r1 == null) goto L600;
     */
    /* JADX WARN: Code restructure failed: missing block: B:291:0x040a, code lost:
        r1 = r1.getSourceLocation();
     */
    /* JADX WARN: Code restructure failed: missing block: B:292:0x040e, code lost:
        if (r1 == null) goto L600;
     */
    /* JADX WARN: Code restructure failed: missing block: B:293:0x0410, code lost:
        r1 = r1.getGpsLong();
     */
    /* JADX WARN: Code restructure failed: missing block: B:294:0x0415, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:295:0x0416, code lost:
        r11 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:296:0x041e, code lost:
        if (r11 == null) goto L599;
     */
    /* JADX WARN: Code restructure failed: missing block: B:297:0x0420, code lost:
        r11 = r11.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:298:0x0424, code lost:
        if (r11 == null) goto L599;
     */
    /* JADX WARN: Code restructure failed: missing block: B:299:0x0426, code lost:
        r11 = r11.getSourceLocation();
     */
    /* JADX WARN: Code restructure failed: missing block: B:300:0x042a, code lost:
        if (r11 == null) goto L599;
     */
    /* JADX WARN: Code restructure failed: missing block: B:301:0x042c, code lost:
        r11 = r11.getGpsLong();
     */
    /* JADX WARN: Code restructure failed: missing block: B:302:0x0431, code lost:
        r11 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:304:0x0436, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r1, r11) != false) goto L198;
     */
    /* JADX WARN: Code restructure failed: missing block: B:305:0x0438, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:306:0x0440, code lost:
        if (r1 == null) goto L607;
     */
    /* JADX WARN: Code restructure failed: missing block: B:307:0x0442, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:308:0x0446, code lost:
        if (r1 == null) goto L607;
     */
    /* JADX WARN: Code restructure failed: missing block: B:309:0x0448, code lost:
        r1 = r1.getSourceLocation();
     */
    /* JADX WARN: Code restructure failed: missing block: B:310:0x044d, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:311:0x044e, code lost:
        if (r1 != null) goto L219;
     */
    /* JADX WARN: Code restructure failed: missing block: B:312:0x0450, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:313:0x0458, code lost:
        if (r1 == null) goto L598;
     */
    /* JADX WARN: Code restructure failed: missing block: B:314:0x045a, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:315:0x045f, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:316:0x0460, code lost:
        if (r1 != null) goto L202;
     */
    /* JADX WARN: Code restructure failed: missing block: B:318:0x0463, code lost:
        r12 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:319:0x046d, code lost:
        if (r12 == null) goto L218;
     */
    /* JADX WARN: Code restructure failed: missing block: B:320:0x046f, code lost:
        r12 = r12.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:321:0x0473, code lost:
        if (r12 == null) goto L218;
     */
    /* JADX WARN: Code restructure failed: missing block: B:322:0x0475, code lost:
        r12 = r12.getSourceLocation();
     */
    /* JADX WARN: Code restructure failed: missing block: B:323:0x0479, code lost:
        if (r12 == null) goto L218;
     */
    /* JADX WARN: Code restructure failed: missing block: B:324:0x047b, code lost:
        r12 = r12.getGpsLat();
     */
    /* JADX WARN: Code restructure failed: missing block: B:325:0x0480, code lost:
        r12 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:326:0x0481, code lost:
        r13 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:327:0x0489, code lost:
        if (r13 == null) goto L217;
     */
    /* JADX WARN: Code restructure failed: missing block: B:328:0x048b, code lost:
        r13 = r13.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:329:0x048f, code lost:
        if (r13 == null) goto L217;
     */
    /* JADX WARN: Code restructure failed: missing block: B:330:0x0491, code lost:
        r13 = r13.getSourceLocation();
     */
    /* JADX WARN: Code restructure failed: missing block: B:331:0x0495, code lost:
        if (r13 == null) goto L217;
     */
    /* JADX WARN: Code restructure failed: missing block: B:332:0x0497, code lost:
        r13 = r13.getGpsLong();
     */
    /* JADX WARN: Code restructure failed: missing block: B:333:0x049c, code lost:
        r13 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:334:0x049d, code lost:
        r1.setSourceLocation(new com.psa.mym.mycitroenconnect.api.body.geo_fence.Coordinates(r12, r13));
     */
    /* JADX WARN: Code restructure failed: missing block: B:335:0x04a3, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:336:0x04ab, code lost:
        if (r1 == null) goto L597;
     */
    /* JADX WARN: Code restructure failed: missing block: B:337:0x04ad, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:338:0x04b1, code lost:
        if (r1 == null) goto L597;
     */
    /* JADX WARN: Code restructure failed: missing block: B:339:0x04b3, code lost:
        r1 = r1.getTimeMode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:340:0x04b8, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:341:0x04b9, code lost:
        r11 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:342:0x04c1, code lost:
        if (r11 == null) goto L596;
     */
    /* JADX WARN: Code restructure failed: missing block: B:343:0x04c3, code lost:
        r11 = r11.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:344:0x04c7, code lost:
        if (r11 == null) goto L596;
     */
    /* JADX WARN: Code restructure failed: missing block: B:345:0x04c9, code lost:
        r11 = r11.getTimeMode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:346:0x04ce, code lost:
        r11 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:348:0x04d3, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r1, r11) != false) goto L584;
     */
    /* JADX WARN: Code restructure failed: missing block: B:349:0x04d5, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:350:0x04dd, code lost:
        if (r1 == null) goto L583;
     */
    /* JADX WARN: Code restructure failed: missing block: B:351:0x04df, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:352:0x04e3, code lost:
        if (r1 == null) goto L583;
     */
    /* JADX WARN: Code restructure failed: missing block: B:353:0x04e5, code lost:
        r1 = r1.getTimeMode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:354:0x04ea, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:355:0x04eb, code lost:
        if (r1 != null) goto L237;
     */
    /* JADX WARN: Code restructure failed: missing block: B:356:0x04ed, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:357:0x04f5, code lost:
        if (r1 == null) goto L595;
     */
    /* JADX WARN: Code restructure failed: missing block: B:358:0x04f7, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:359:0x04fc, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:360:0x04fd, code lost:
        if (r1 != null) goto L588;
     */
    /* JADX WARN: Code restructure failed: missing block: B:362:0x0500, code lost:
        r11 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:363:0x0508, code lost:
        if (r11 == null) goto L594;
     */
    /* JADX WARN: Code restructure failed: missing block: B:364:0x050a, code lost:
        r11 = r11.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:365:0x050e, code lost:
        if (r11 == null) goto L594;
     */
    /* JADX WARN: Code restructure failed: missing block: B:366:0x0510, code lost:
        r11 = r11.getTimeMode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:367:0x0515, code lost:
        r11 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:368:0x0516, code lost:
        r1.setTimeMode(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:369:0x0519, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:370:0x0521, code lost:
        if (r1 == null) goto L582;
     */
    /* JADX WARN: Code restructure failed: missing block: B:371:0x0523, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:372:0x0527, code lost:
        if (r1 == null) goto L582;
     */
    /* JADX WARN: Code restructure failed: missing block: B:373:0x0529, code lost:
        r1 = r1.isInfinite();
     */
    /* JADX WARN: Code restructure failed: missing block: B:374:0x052e, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:375:0x052f, code lost:
        r11 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:376:0x0537, code lost:
        if (r11 == null) goto L581;
     */
    /* JADX WARN: Code restructure failed: missing block: B:377:0x0539, code lost:
        r11 = r11.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:378:0x053d, code lost:
        if (r11 == null) goto L581;
     */
    /* JADX WARN: Code restructure failed: missing block: B:379:0x053f, code lost:
        r11 = r11.isInfinite();
     */
    /* JADX WARN: Code restructure failed: missing block: B:380:0x0544, code lost:
        r11 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:382:0x0549, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r1, r11) != false) goto L569;
     */
    /* JADX WARN: Code restructure failed: missing block: B:383:0x054b, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:384:0x0553, code lost:
        if (r1 == null) goto L568;
     */
    /* JADX WARN: Code restructure failed: missing block: B:385:0x0555, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:386:0x0559, code lost:
        if (r1 == null) goto L568;
     */
    /* JADX WARN: Code restructure failed: missing block: B:387:0x055b, code lost:
        r1 = r1.isInfinite();
     */
    /* JADX WARN: Code restructure failed: missing block: B:388:0x0560, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:390:0x0567, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r1, "false") == false) goto L256;
     */
    /* JADX WARN: Code restructure failed: missing block: B:391:0x0569, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:392:0x0571, code lost:
        if (r1 == null) goto L580;
     */
    /* JADX WARN: Code restructure failed: missing block: B:393:0x0573, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:394:0x0578, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:395:0x0579, code lost:
        if (r1 != null) goto L573;
     */
    /* JADX WARN: Code restructure failed: missing block: B:397:0x057c, code lost:
        r11 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:398:0x0584, code lost:
        if (r11 == null) goto L579;
     */
    /* JADX WARN: Code restructure failed: missing block: B:399:0x0586, code lost:
        r11 = r11.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:400:0x058a, code lost:
        if (r11 == null) goto L579;
     */
    /* JADX WARN: Code restructure failed: missing block: B:401:0x058c, code lost:
        r11 = r11.isInfinite();
     */
    /* JADX WARN: Code restructure failed: missing block: B:402:0x0591, code lost:
        r11 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:403:0x0592, code lost:
        r1.setInfinite(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:404:0x0595, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:405:0x059f, code lost:
        if (r1 == null) goto L567;
     */
    /* JADX WARN: Code restructure failed: missing block: B:406:0x05a1, code lost:
        r1 = r1.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:407:0x05a5, code lost:
        if (r1 == null) goto L567;
     */
    /* JADX WARN: Code restructure failed: missing block: B:408:0x05a7, code lost:
        r1 = r1.getStartTime();
     */
    /* JADX WARN: Code restructure failed: missing block: B:409:0x05ab, code lost:
        if (r1 == null) goto L567;
     */
    /* JADX WARN: Code restructure failed: missing block: B:411:0x05b1, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r1, "") != false) goto L567;
     */
    /* JADX WARN: Code restructure failed: missing block: B:412:0x05b3, code lost:
        r1 = com.psa.mym.mycitroenconnect.utils.AppUtil.Companion.getTimeInDateFormat(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:413:0x05ba, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:414:0x05bb, code lost:
        r12 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:415:0x05c3, code lost:
        if (r12 == null) goto L566;
     */
    /* JADX WARN: Code restructure failed: missing block: B:416:0x05c5, code lost:
        r12 = r12.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:417:0x05c9, code lost:
        if (r12 == null) goto L566;
     */
    /* JADX WARN: Code restructure failed: missing block: B:418:0x05cb, code lost:
        r12 = r12.getStartTime();
     */
    /* JADX WARN: Code restructure failed: missing block: B:419:0x05d0, code lost:
        r12 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:421:0x05d3, code lost:
        if (r1 == null) goto L565;
     */
    /* JADX WARN: Code restructure failed: missing block: B:422:0x05d5, code lost:
        r14 = com.psa.mym.mycitroenconnect.utils.AppUtil.Companion.getDateString(r1, com.psa.mym.mycitroenconnect.common.AppConstants.UTC_DATE_FORMAT);
     */
    /* JADX WARN: Code restructure failed: missing block: B:423:0x05dc, code lost:
        r14 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:425:0x05e1, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r12, r14) != false) goto L556;
     */
    /* JADX WARN: Code restructure failed: missing block: B:426:0x05e3, code lost:
        r12 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:427:0x05eb, code lost:
        if (r12 == null) goto L555;
     */
    /* JADX WARN: Code restructure failed: missing block: B:428:0x05ed, code lost:
        r12 = r12.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:429:0x05f1, code lost:
        if (r12 == null) goto L555;
     */
    /* JADX WARN: Code restructure failed: missing block: B:430:0x05f3, code lost:
        r12 = r12.getStartTime();
     */
    /* JADX WARN: Code restructure failed: missing block: B:431:0x05f8, code lost:
        r12 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:432:0x05f9, code lost:
        if (r12 != null) goto L281;
     */
    /* JADX WARN: Code restructure failed: missing block: B:433:0x05fb, code lost:
        r12 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:434:0x0603, code lost:
        if (r12 == null) goto L564;
     */
    /* JADX WARN: Code restructure failed: missing block: B:435:0x0605, code lost:
        r12 = r12.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:436:0x060a, code lost:
        r12 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:437:0x060b, code lost:
        if (r12 != null) goto L560;
     */
    /* JADX WARN: Code restructure failed: missing block: B:439:0x060e, code lost:
        if (r1 == null) goto L563;
     */
    /* JADX WARN: Code restructure failed: missing block: B:440:0x0610, code lost:
        r1 = com.psa.mym.mycitroenconnect.utils.AppUtil.Companion.getDateString(r1, com.psa.mym.mycitroenconnect.common.AppConstants.UTC_DATE_FORMAT);
     */
    /* JADX WARN: Code restructure failed: missing block: B:441:0x0617, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:442:0x0618, code lost:
        r12.setStartTime(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:443:0x061b, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:444:0x0623, code lost:
        if (r1 == null) goto L554;
     */
    /* JADX WARN: Code restructure failed: missing block: B:445:0x0625, code lost:
        r1 = r1.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:446:0x0629, code lost:
        if (r1 == null) goto L554;
     */
    /* JADX WARN: Code restructure failed: missing block: B:447:0x062b, code lost:
        r1 = r1.getEndTime();
     */
    /* JADX WARN: Code restructure failed: missing block: B:448:0x062f, code lost:
        if (r1 == null) goto L554;
     */
    /* JADX WARN: Code restructure failed: missing block: B:450:0x0635, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r1, "") != false) goto L554;
     */
    /* JADX WARN: Code restructure failed: missing block: B:451:0x0637, code lost:
        r1 = com.psa.mym.mycitroenconnect.utils.AppUtil.Companion.getTimeInDateFormat(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:452:0x063e, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:453:0x063f, code lost:
        r11 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:454:0x0647, code lost:
        if (r11 == null) goto L553;
     */
    /* JADX WARN: Code restructure failed: missing block: B:455:0x0649, code lost:
        r11 = r11.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:456:0x064d, code lost:
        if (r11 == null) goto L553;
     */
    /* JADX WARN: Code restructure failed: missing block: B:457:0x064f, code lost:
        r11 = r11.getEndTime();
     */
    /* JADX WARN: Code restructure failed: missing block: B:458:0x0654, code lost:
        r11 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:459:0x0655, code lost:
        if (r1 == null) goto L552;
     */
    /* JADX WARN: Code restructure failed: missing block: B:460:0x0657, code lost:
        r12 = com.psa.mym.mycitroenconnect.utils.AppUtil.Companion.getDateString(r1, com.psa.mym.mycitroenconnect.common.AppConstants.UTC_DATE_FORMAT);
     */
    /* JADX WARN: Code restructure failed: missing block: B:461:0x065e, code lost:
        r12 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:463:0x0663, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r11, r12) != false) goto L543;
     */
    /* JADX WARN: Code restructure failed: missing block: B:464:0x0665, code lost:
        r11 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:465:0x066d, code lost:
        if (r11 == null) goto L542;
     */
    /* JADX WARN: Code restructure failed: missing block: B:466:0x066f, code lost:
        r11 = r11.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:467:0x0673, code lost:
        if (r11 == null) goto L542;
     */
    /* JADX WARN: Code restructure failed: missing block: B:468:0x0675, code lost:
        r11 = r11.getEndTime();
     */
    /* JADX WARN: Code restructure failed: missing block: B:469:0x067a, code lost:
        r11 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:470:0x067b, code lost:
        if (r11 != null) goto L305;
     */
    /* JADX WARN: Code restructure failed: missing block: B:471:0x067d, code lost:
        r11 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:472:0x0685, code lost:
        if (r11 == null) goto L551;
     */
    /* JADX WARN: Code restructure failed: missing block: B:473:0x0687, code lost:
        r11 = r11.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:474:0x068c, code lost:
        r11 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:475:0x068d, code lost:
        if (r11 != null) goto L547;
     */
    /* JADX WARN: Code restructure failed: missing block: B:477:0x0690, code lost:
        if (r1 == null) goto L550;
     */
    /* JADX WARN: Code restructure failed: missing block: B:478:0x0692, code lost:
        r1 = com.psa.mym.mycitroenconnect.utils.AppUtil.Companion.getDateString(r1, com.psa.mym.mycitroenconnect.common.AppConstants.UTC_DATE_FORMAT);
     */
    /* JADX WARN: Code restructure failed: missing block: B:479:0x0699, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:480:0x069a, code lost:
        r11.setEndTime(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:481:0x069d, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:482:0x06a5, code lost:
        if (r1 == null) goto L541;
     */
    /* JADX WARN: Code restructure failed: missing block: B:483:0x06a7, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:484:0x06ab, code lost:
        if (r1 == null) goto L541;
     */
    /* JADX WARN: Code restructure failed: missing block: B:485:0x06ad, code lost:
        r1 = r1.getDaysOfWeek();
     */
    /* JADX WARN: Code restructure failed: missing block: B:486:0x06b2, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:487:0x06b3, code lost:
        r11 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:488:0x06bb, code lost:
        if (r11 == null) goto L540;
     */
    /* JADX WARN: Code restructure failed: missing block: B:489:0x06bd, code lost:
        r11 = r11.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:490:0x06c1, code lost:
        if (r11 == null) goto L540;
     */
    /* JADX WARN: Code restructure failed: missing block: B:491:0x06c3, code lost:
        r11 = r11.getDaysOfWeek();
     */
    /* JADX WARN: Code restructure failed: missing block: B:492:0x06c8, code lost:
        r11 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:494:0x06cf, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r1, (java.util.ArrayList) r11) != false) goto L528;
     */
    /* JADX WARN: Code restructure failed: missing block: B:495:0x06d1, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:496:0x06d9, code lost:
        if (r1 == null) goto L527;
     */
    /* JADX WARN: Code restructure failed: missing block: B:497:0x06db, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:498:0x06df, code lost:
        if (r1 == null) goto L527;
     */
    /* JADX WARN: Code restructure failed: missing block: B:499:0x06e1, code lost:
        r1 = r1.getDaysOfWeek();
     */
    /* JADX WARN: Code restructure failed: missing block: B:500:0x06e6, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:501:0x06e7, code lost:
        if (r1 != null) goto L323;
     */
    /* JADX WARN: Code restructure failed: missing block: B:502:0x06e9, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:503:0x06f1, code lost:
        if (r1 == null) goto L539;
     */
    /* JADX WARN: Code restructure failed: missing block: B:504:0x06f3, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:505:0x06f8, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:506:0x06f9, code lost:
        if (r1 != null) goto L532;
     */
    /* JADX WARN: Code restructure failed: missing block: B:508:0x06fc, code lost:
        r11 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:509:0x0704, code lost:
        if (r11 == null) goto L538;
     */
    /* JADX WARN: Code restructure failed: missing block: B:510:0x0706, code lost:
        r11 = r11.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:511:0x070a, code lost:
        if (r11 == null) goto L538;
     */
    /* JADX WARN: Code restructure failed: missing block: B:512:0x070c, code lost:
        r11 = r11.getDaysOfWeek();
     */
    /* JADX WARN: Code restructure failed: missing block: B:513:0x0711, code lost:
        r11 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:514:0x0712, code lost:
        r1.setDaysOfWeek((java.util.ArrayList) r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:515:0x0717, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:516:0x071f, code lost:
        if (r1 == null) goto L526;
     */
    /* JADX WARN: Code restructure failed: missing block: B:517:0x0721, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:518:0x0725, code lost:
        if (r1 == null) goto L526;
     */
    /* JADX WARN: Code restructure failed: missing block: B:519:0x0727, code lost:
        r1 = r1.getFenceMode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:520:0x072c, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:521:0x072d, code lost:
        if (r1 == null) goto L496;
     */
    /* JADX WARN: Code restructure failed: missing block: B:522:0x072f, code lost:
        r11 = r1.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:523:0x0733, code lost:
        if (r11 == (-1349088399)) goto L494;
     */
    /* JADX WARN: Code restructure failed: missing block: B:524:0x0735, code lost:
        if (r11 == (-938578798)) goto L384;
     */
    /* JADX WARN: Code restructure failed: missing block: B:525:0x0737, code lost:
        if (r11 == 108704329) goto L333;
     */
    /* JADX WARN: Code restructure failed: missing block: B:528:0x073f, code lost:
        if (r1.equals(com.psa.mym.mycitroenconnect.common.AppConstants.GEO_FENCE_MODE_ROUTE) != false) goto L335;
     */
    /* JADX WARN: Code restructure failed: missing block: B:531:0x0747, code lost:
        if (r1.equals(com.psa.mym.mycitroenconnect.common.AppConstants.GEO_FENCE_MODE_RADIUS) == false) goto L496;
     */
    /* JADX WARN: Code restructure failed: missing block: B:532:0x0749, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:533:0x0751, code lost:
        if (r1 == null) goto L493;
     */
    /* JADX WARN: Code restructure failed: missing block: B:534:0x0753, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:535:0x0758, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:536:0x0759, code lost:
        if (r1 != null) goto L390;
     */
    /* JADX WARN: Code restructure failed: missing block: B:538:0x075c, code lost:
        r1.setCoordinates(null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:539:0x075f, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:540:0x0767, code lost:
        if (r1 == null) goto L492;
     */
    /* JADX WARN: Code restructure failed: missing block: B:541:0x0769, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:542:0x076d, code lost:
        if (r1 == null) goto L492;
     */
    /* JADX WARN: Code restructure failed: missing block: B:543:0x076f, code lost:
        r1 = r1.getRadius();
     */
    /* JADX WARN: Code restructure failed: missing block: B:544:0x0773, code lost:
        if (r1 == null) goto L492;
     */
    /* JADX WARN: Code restructure failed: missing block: B:545:0x0775, code lost:
        r1 = java.lang.Integer.valueOf((int) r1.doubleValue());
     */
    /* JADX WARN: Code restructure failed: missing block: B:546:0x077f, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:547:0x0780, code lost:
        r3 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:548:0x0788, code lost:
        if (r3 == null) goto L491;
     */
    /* JADX WARN: Code restructure failed: missing block: B:549:0x078a, code lost:
        r3 = r3.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:550:0x078e, code lost:
        if (r3 == null) goto L491;
     */
    /* JADX WARN: Code restructure failed: missing block: B:551:0x0790, code lost:
        r3 = r3.getRadius();
     */
    /* JADX WARN: Code restructure failed: missing block: B:552:0x0795, code lost:
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:554:0x079a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r1, r3) != false) goto L477;
     */
    /* JADX WARN: Code restructure failed: missing block: B:555:0x079c, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:556:0x07a4, code lost:
        if (r1 == null) goto L476;
     */
    /* JADX WARN: Code restructure failed: missing block: B:557:0x07a6, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:558:0x07aa, code lost:
        if (r1 == null) goto L476;
     */
    /* JADX WARN: Code restructure failed: missing block: B:559:0x07ac, code lost:
        r1 = r1.getRadius();
     */
    /* JADX WARN: Code restructure failed: missing block: B:560:0x07b1, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:561:0x07b2, code lost:
        if (r1 != null) goto L411;
     */
    /* JADX WARN: Code restructure failed: missing block: B:562:0x07b4, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:563:0x07bc, code lost:
        if (r1 == null) goto L490;
     */
    /* JADX WARN: Code restructure failed: missing block: B:564:0x07be, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:565:0x07c3, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:566:0x07c4, code lost:
        if (r1 != null) goto L481;
     */
    /* JADX WARN: Code restructure failed: missing block: B:568:0x07c7, code lost:
        r3 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:569:0x07cf, code lost:
        if (r3 == null) goto L489;
     */
    /* JADX WARN: Code restructure failed: missing block: B:570:0x07d1, code lost:
        r3 = r3.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:571:0x07d5, code lost:
        if (r3 == null) goto L489;
     */
    /* JADX WARN: Code restructure failed: missing block: B:573:0x07db, code lost:
        if (r3.getRadius() == null) goto L489;
     */
    /* JADX WARN: Code restructure failed: missing block: B:574:0x07dd, code lost:
        r3 = java.lang.Double.valueOf(r3.intValue());
     */
    /* JADX WARN: Code restructure failed: missing block: B:575:0x07e7, code lost:
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:576:0x07e8, code lost:
        r1.setRadius(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:577:0x07eb, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:578:0x07f3, code lost:
        if (r1 == null) goto L475;
     */
    /* JADX WARN: Code restructure failed: missing block: B:579:0x07f5, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:580:0x07f9, code lost:
        if (r1 == null) goto L475;
     */
    /* JADX WARN: Code restructure failed: missing block: B:581:0x07fb, code lost:
        r1 = r1.getCentre();
     */
    /* JADX WARN: Code restructure failed: missing block: B:582:0x07ff, code lost:
        if (r1 == null) goto L475;
     */
    /* JADX WARN: Code restructure failed: missing block: B:583:0x0801, code lost:
        r1 = r1.getGpsLat();
     */
    /* JADX WARN: Code restructure failed: missing block: B:584:0x0806, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:585:0x0807, code lost:
        r3 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:586:0x080f, code lost:
        if (r3 == null) goto L474;
     */
    /* JADX WARN: Code restructure failed: missing block: B:587:0x0811, code lost:
        r3 = r3.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:588:0x0815, code lost:
        if (r3 == null) goto L474;
     */
    /* JADX WARN: Code restructure failed: missing block: B:589:0x0817, code lost:
        r3 = r3.getCentre();
     */
    /* JADX WARN: Code restructure failed: missing block: B:590:0x081b, code lost:
        if (r3 == null) goto L474;
     */
    /* JADX WARN: Code restructure failed: missing block: B:591:0x081d, code lost:
        r3 = r3.getGpsLat();
     */
    /* JADX WARN: Code restructure failed: missing block: B:592:0x0822, code lost:
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:594:0x0827, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r1, r3) == false) goto L467;
     */
    /* JADX WARN: Code restructure failed: missing block: B:595:0x0829, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:596:0x0831, code lost:
        if (r1 == null) goto L466;
     */
    /* JADX WARN: Code restructure failed: missing block: B:597:0x0833, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:598:0x0837, code lost:
        if (r1 == null) goto L466;
     */
    /* JADX WARN: Code restructure failed: missing block: B:599:0x0839, code lost:
        r1 = r1.getCentre();
     */
    /* JADX WARN: Code restructure failed: missing block: B:600:0x083d, code lost:
        if (r1 == null) goto L466;
     */
    /* JADX WARN: Code restructure failed: missing block: B:601:0x083f, code lost:
        r1 = r1.getGpsLong();
     */
    /* JADX WARN: Code restructure failed: missing block: B:602:0x0844, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:603:0x0845, code lost:
        r3 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:604:0x084d, code lost:
        if (r3 == null) goto L465;
     */
    /* JADX WARN: Code restructure failed: missing block: B:605:0x084f, code lost:
        r3 = r3.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:606:0x0853, code lost:
        if (r3 == null) goto L465;
     */
    /* JADX WARN: Code restructure failed: missing block: B:607:0x0855, code lost:
        r3 = r3.getCentre();
     */
    /* JADX WARN: Code restructure failed: missing block: B:608:0x0859, code lost:
        if (r3 == null) goto L465;
     */
    /* JADX WARN: Code restructure failed: missing block: B:609:0x085b, code lost:
        r3 = r3.getGpsLong();
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00cf, code lost:
        if (((r1 == null || (r1 = r1.getGeoFenceBody()) == null) ? null : r1.getFenceType()) == null) goto L682;
     */
    /* JADX WARN: Code restructure failed: missing block: B:610:0x0860, code lost:
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:612:0x0865, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r1, r3) != false) goto L443;
     */
    /* JADX WARN: Code restructure failed: missing block: B:613:0x0867, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:614:0x086f, code lost:
        if (r1 == null) goto L473;
     */
    /* JADX WARN: Code restructure failed: missing block: B:615:0x0871, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:616:0x0875, code lost:
        if (r1 == null) goto L473;
     */
    /* JADX WARN: Code restructure failed: missing block: B:617:0x0877, code lost:
        r1 = r1.getCentre();
     */
    /* JADX WARN: Code restructure failed: missing block: B:618:0x087c, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:619:0x087d, code lost:
        if (r1 != null) goto L496;
     */
    /* JADX WARN: Code restructure failed: missing block: B:620:0x087f, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:621:0x0887, code lost:
        if (r1 == null) goto L464;
     */
    /* JADX WARN: Code restructure failed: missing block: B:622:0x0889, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:623:0x088e, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:624:0x088f, code lost:
        if (r1 != null) goto L447;
     */
    /* JADX WARN: Code restructure failed: missing block: B:626:0x0893, code lost:
        r4 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:627:0x089d, code lost:
        if (r4 == null) goto L463;
     */
    /* JADX WARN: Code restructure failed: missing block: B:628:0x089f, code lost:
        r4 = r4.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:629:0x08a3, code lost:
        if (r4 == null) goto L463;
     */
    /* JADX WARN: Code restructure failed: missing block: B:630:0x08a5, code lost:
        r4 = r4.getCentre();
     */
    /* JADX WARN: Code restructure failed: missing block: B:631:0x08a9, code lost:
        if (r4 == null) goto L463;
     */
    /* JADX WARN: Code restructure failed: missing block: B:632:0x08ab, code lost:
        r4 = r4.getGpsLat();
     */
    /* JADX WARN: Code restructure failed: missing block: B:633:0x08b0, code lost:
        r4 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:634:0x08b1, code lost:
        r5 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:635:0x08b9, code lost:
        if (r5 == null) goto L462;
     */
    /* JADX WARN: Code restructure failed: missing block: B:636:0x08bb, code lost:
        r5 = r5.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:637:0x08bf, code lost:
        if (r5 == null) goto L462;
     */
    /* JADX WARN: Code restructure failed: missing block: B:638:0x08c1, code lost:
        r5 = r5.getCentre();
     */
    /* JADX WARN: Code restructure failed: missing block: B:639:0x08c5, code lost:
        if (r5 == null) goto L462;
     */
    /* JADX WARN: Code restructure failed: missing block: B:640:0x08c7, code lost:
        r5 = r5.getGpsLong();
     */
    /* JADX WARN: Code restructure failed: missing block: B:641:0x08cc, code lost:
        r5 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:642:0x08cd, code lost:
        r1.setCentre(new com.psa.mym.mycitroenconnect.api.body.geo_fence.Coordinates(r4, r5));
     */
    /* JADX WARN: Code restructure failed: missing block: B:644:0x08d9, code lost:
        if (r1.equals(com.psa.mym.mycitroenconnect.common.AppConstants.GEO_FENCE_MODE_CUSTOM) != false) goto L335;
     */
    /* JADX WARN: Code restructure failed: missing block: B:646:0x08dd, code lost:
        r0.setCoordinates(new java.util.ArrayList<>());
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:647:0x08ed, code lost:
        if (r1 == null) goto L383;
     */
    /* JADX WARN: Code restructure failed: missing block: B:648:0x08ef, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:649:0x08f3, code lost:
        if (r1 == null) goto L383;
     */
    /* JADX WARN: Code restructure failed: missing block: B:650:0x08f5, code lost:
        r1 = r1.getCoordinates();
     */
    /* JADX WARN: Code restructure failed: missing block: B:651:0x08fa, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:652:0x08fb, code lost:
        r4 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:653:0x0903, code lost:
        if (r4 == null) goto L382;
     */
    /* JADX WARN: Code restructure failed: missing block: B:654:0x0905, code lost:
        r4 = r4.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:655:0x0909, code lost:
        if (r4 == null) goto L382;
     */
    /* JADX WARN: Code restructure failed: missing block: B:656:0x090b, code lost:
        r4 = r4.getCoordinates();
     */
    /* JADX WARN: Code restructure failed: missing block: B:657:0x0910, code lost:
        r4 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:659:0x0915, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r1, r4) != false) goto L364;
     */
    /* JADX WARN: Code restructure failed: missing block: B:660:0x0917, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:661:0x091f, code lost:
        if (r1 == null) goto L363;
     */
    /* JADX WARN: Code restructure failed: missing block: B:662:0x0921, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:663:0x0925, code lost:
        if (r1 == null) goto L363;
     */
    /* JADX WARN: Code restructure failed: missing block: B:664:0x0927, code lost:
        r1 = r1.getCoordinates();
     */
    /* JADX WARN: Code restructure failed: missing block: B:665:0x092c, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:666:0x092d, code lost:
        if (r1 == null) goto L364;
     */
    /* JADX WARN: Code restructure failed: missing block: B:667:0x092f, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:668:0x0937, code lost:
        if (r1 == null) goto L362;
     */
    /* JADX WARN: Code restructure failed: missing block: B:669:0x0939, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:670:0x093d, code lost:
        if (r1 == null) goto L362;
     */
    /* JADX WARN: Code restructure failed: missing block: B:671:0x093f, code lost:
        r1 = r1.getCoordinates();
     */
    /* JADX WARN: Code restructure failed: missing block: B:672:0x0943, code lost:
        if (r1 == null) goto L362;
     */
    /* JADX WARN: Code restructure failed: missing block: B:674:0x0949, code lost:
        if (r1.size() != 0) goto L362;
     */
    /* JADX WARN: Code restructure failed: missing block: B:676:0x094c, code lost:
        r3 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:677:0x094d, code lost:
        if (r3 == false) goto L496;
     */
    /* JADX WARN: Code restructure failed: missing block: B:678:0x094f, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:679:0x0957, code lost:
        if (r1 == null) goto L496;
     */
    /* JADX WARN: Code restructure failed: missing block: B:680:0x0959, code lost:
        r1 = r1.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:681:0x095d, code lost:
        if (r1 == null) goto L496;
     */
    /* JADX WARN: Code restructure failed: missing block: B:682:0x095f, code lost:
        r1 = r1.getCoordinates();
     */
    /* JADX WARN: Code restructure failed: missing block: B:683:0x0963, code lost:
        if (r1 == null) goto L496;
     */
    /* JADX WARN: Code restructure failed: missing block: B:684:0x0965, code lost:
        r1 = r1.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:686:0x096d, code lost:
        if (r1.hasNext() == false) goto L380;
     */
    /* JADX WARN: Code restructure failed: missing block: B:687:0x096f, code lost:
        r3 = (com.psa.mym.mycitroenconnect.model.geo_fence.LocationData) r1.next();
        r4 = r0.getCoordinates();
     */
    /* JADX WARN: Code restructure failed: missing block: B:688:0x0979, code lost:
        if (r4 == null) goto L379;
     */
    /* JADX WARN: Code restructure failed: missing block: B:689:0x097b, code lost:
        r4.add(new com.psa.mym.mycitroenconnect.api.body.geo_fence.Coordinates(r3.getGpsLat(), r3.getGpsLong()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:690:0x098c, code lost:
        r1 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:691:0x098e, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:692:0x0996, code lost:
        if (r1 == null) goto L525;
     */
    /* JADX WARN: Code restructure failed: missing block: B:693:0x0998, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:694:0x099c, code lost:
        if (r1 == null) goto L525;
     */
    /* JADX WARN: Code restructure failed: missing block: B:695:0x099e, code lost:
        r1 = r1.getFenceName();
     */
    /* JADX WARN: Code restructure failed: missing block: B:696:0x09a3, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:697:0x09a4, code lost:
        r3 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:698:0x09ac, code lost:
        if (r3 == null) goto L524;
     */
    /* JADX WARN: Code restructure failed: missing block: B:699:0x09ae, code lost:
        r3 = r3.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:700:0x09b2, code lost:
        if (r3 == null) goto L524;
     */
    /* JADX WARN: Code restructure failed: missing block: B:701:0x09b4, code lost:
        r3 = r3.getFenceName();
     */
    /* JADX WARN: Code restructure failed: missing block: B:702:0x09b9, code lost:
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:704:0x09be, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r1, r3) != false) goto L518;
     */
    /* JADX WARN: Code restructure failed: missing block: B:705:0x09c0, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:706:0x09c8, code lost:
        if (r1 == null) goto L517;
     */
    /* JADX WARN: Code restructure failed: missing block: B:707:0x09ca, code lost:
        r1 = r1.getGeoFenceBody();
     */
    /* JADX WARN: Code restructure failed: missing block: B:708:0x09ce, code lost:
        if (r1 == null) goto L517;
     */
    /* JADX WARN: Code restructure failed: missing block: B:709:0x09d0, code lost:
        r1 = r1.getFenceName();
     */
    /* JADX WARN: Code restructure failed: missing block: B:710:0x09d5, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:711:0x09d6, code lost:
        if (r1 != null) goto L514;
     */
    /* JADX WARN: Code restructure failed: missing block: B:712:0x09d8, code lost:
        r1 = getViewModel().getGeoFenceCommonModel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:713:0x09e0, code lost:
        if (r1 == null) goto L523;
     */
    /* JADX WARN: Code restructure failed: missing block: B:714:0x09e2, code lost:
        r1 = r1.getGeoFenceGetResponse();
     */
    /* JADX WARN: Code restructure failed: missing block: B:715:0x09e6, code lost:
        if (r1 == null) goto L523;
     */
    /* JADX WARN: Code restructure failed: missing block: B:716:0x09e8, code lost:
        r2 = r1.getFenceName();
     */
    /* JADX WARN: Code restructure failed: missing block: B:717:0x09ec, code lost:
        r0.setFenceName(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:718:0x09ef, code lost:
        com.psa.mym.mycitroenconnect.utils.Logger.INSTANCE.e("GeoFenceBody Edit Mode : " + new com.google.gson.Gson().toJson(r0));
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:719:0x0a10, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0132, code lost:
        if (((r1 == null || (r1 = r1.getGeoFenceBody()) == null) ? null : r1.getFenceGeometry()) == null) goto L672;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void setModelDataForEdit() {
        GeoFenceBody geoFenceBody;
        GetGeoFenceResponseItem geoFenceGetResponse;
        GetGeoFenceResponseItem geoFenceGetResponse2;
        GetGeoFenceResponseItem geoFenceGetResponse3;
        GetGeoFenceResponseItem geoFenceGetResponse4;
        GeoFenceBody geoFenceBody2;
        GetGeoFenceResponseItem geoFenceGetResponse5;
        GeoFenceBody geoFenceBody3;
        Coordinates destinationLocation;
        GetGeoFenceResponseItem geoFenceGetResponse6;
        LocationData destinationLocation2;
        GeoFenceBody geoFenceBody4;
        Coordinates destinationLocation3;
        GetGeoFenceResponseItem geoFenceGetResponse7;
        GeoFenceBody geoFenceBody5;
        GetGeoFenceResponseItem geoFenceGetResponse8;
        GeoFenceBody geoFenceBody6;
        GetGeoFenceResponseItem geoFenceGetResponse9;
        GeoFenceBody geoFenceBody7;
        GetGeoFenceResponseItem geoFenceGetResponse10;
        GeoFenceBody geoFenceBody8;
        GetGeoFenceResponseItem geoFenceGetResponse11;
        GeoFenceBody geoFenceBody9;
        GetGeoFenceResponseItem geoFenceGetResponse12;
        GeoFenceBody geoFenceBody10;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        if (geoFenceCommonModel == null || (geoFenceBody = geoFenceCommonModel.getGeoFenceBody()) == null) {
            return;
        }
        GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
        String str = null;
        String transitionType = (geoFenceCommonModel2 == null || (geoFenceBody10 = geoFenceCommonModel2.getGeoFenceBody()) == null) ? null : geoFenceBody10.getTransitionType();
        GeoFenceCommonModel geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
        if (!Intrinsics.areEqual(transitionType, (geoFenceCommonModel3 == null || (geoFenceGetResponse12 = geoFenceCommonModel3.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse12.getTransitionType())) {
            GeoFenceCommonModel geoFenceCommonModel4 = getViewModel().getGeoFenceCommonModel();
        }
        GeoFenceCommonModel geoFenceCommonModel5 = getViewModel().getGeoFenceCommonModel();
        GeoFenceBody geoFenceBody11 = geoFenceCommonModel5 != null ? geoFenceCommonModel5.getGeoFenceBody() : null;
        if (geoFenceBody11 != null) {
            GeoFenceCommonModel geoFenceCommonModel6 = getViewModel().getGeoFenceCommonModel();
            geoFenceBody11.setTransitionType((geoFenceCommonModel6 == null || (geoFenceGetResponse = geoFenceCommonModel6.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse.getTransitionType());
        }
        GeoFenceCommonModel geoFenceCommonModel7 = getViewModel().getGeoFenceCommonModel();
        String fenceType = (geoFenceCommonModel7 == null || (geoFenceBody9 = geoFenceCommonModel7.getGeoFenceBody()) == null) ? null : geoFenceBody9.getFenceType();
        GeoFenceCommonModel geoFenceCommonModel8 = getViewModel().getGeoFenceCommonModel();
        if (!Intrinsics.areEqual(fenceType, (geoFenceCommonModel8 == null || (geoFenceGetResponse11 = geoFenceCommonModel8.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse11.getFenceType())) {
            GeoFenceCommonModel geoFenceCommonModel9 = getViewModel().getGeoFenceCommonModel();
        }
        GeoFenceCommonModel geoFenceCommonModel10 = getViewModel().getGeoFenceCommonModel();
        geoFenceBody.setFenceType((geoFenceCommonModel10 == null || (geoFenceGetResponse2 = geoFenceCommonModel10.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse2.getFenceType());
        GeoFenceCommonModel geoFenceCommonModel11 = getViewModel().getGeoFenceCommonModel();
        String fenceGeometry = (geoFenceCommonModel11 == null || (geoFenceBody8 = geoFenceCommonModel11.getGeoFenceBody()) == null) ? null : geoFenceBody8.getFenceGeometry();
        GeoFenceCommonModel geoFenceCommonModel12 = getViewModel().getGeoFenceCommonModel();
        if (!Intrinsics.areEqual(fenceGeometry, (geoFenceCommonModel12 == null || (geoFenceGetResponse10 = geoFenceCommonModel12.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse10.getFenceGeometry())) {
            GeoFenceCommonModel geoFenceCommonModel13 = getViewModel().getGeoFenceCommonModel();
        }
        GeoFenceCommonModel geoFenceCommonModel14 = getViewModel().getGeoFenceCommonModel();
        geoFenceBody.setFenceGeometry((geoFenceCommonModel14 == null || (geoFenceGetResponse3 = geoFenceCommonModel14.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse3.getFenceGeometry());
        GeoFenceCommonModel geoFenceCommonModel15 = getViewModel().getGeoFenceCommonModel();
        String fenceStatus = (geoFenceCommonModel15 == null || (geoFenceBody7 = geoFenceCommonModel15.getGeoFenceBody()) == null) ? null : geoFenceBody7.getFenceStatus();
        GeoFenceCommonModel geoFenceCommonModel16 = getViewModel().getGeoFenceCommonModel();
        if (!Intrinsics.areEqual(fenceStatus, (geoFenceCommonModel16 == null || (geoFenceGetResponse9 = geoFenceCommonModel16.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse9.getFenceStatus())) {
            GeoFenceCommonModel geoFenceCommonModel17 = getViewModel().getGeoFenceCommonModel();
        }
        geoFenceBody.setFenceStatus("D");
        GeoFenceCommonModel geoFenceCommonModel18 = getViewModel().getGeoFenceCommonModel();
        String transitionType2 = (geoFenceCommonModel18 == null || (geoFenceBody6 = geoFenceCommonModel18.getGeoFenceBody()) == null) ? null : geoFenceBody6.getTransitionType();
        GeoFenceCommonModel geoFenceCommonModel19 = getViewModel().getGeoFenceCommonModel();
        if (!Intrinsics.areEqual(transitionType2, (geoFenceCommonModel19 == null || (geoFenceGetResponse8 = geoFenceCommonModel19.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse8.getTransitionType())) {
            GeoFenceCommonModel geoFenceCommonModel20 = getViewModel().getGeoFenceCommonModel();
        }
        GeoFenceCommonModel geoFenceCommonModel21 = getViewModel().getGeoFenceCommonModel();
        geoFenceBody.setTransitionType((geoFenceCommonModel21 == null || (geoFenceGetResponse4 = geoFenceCommonModel21.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse4.getTransitionType());
        GeoFenceCommonModel geoFenceCommonModel22 = getViewModel().getGeoFenceCommonModel();
        String fenceMode = (geoFenceCommonModel22 == null || (geoFenceBody5 = geoFenceCommonModel22.getGeoFenceBody()) == null) ? null : geoFenceBody5.getFenceMode();
        GeoFenceCommonModel geoFenceCommonModel23 = getViewModel().getGeoFenceCommonModel();
        boolean areEqual = Intrinsics.areEqual(fenceMode, (geoFenceCommonModel23 == null || (geoFenceGetResponse7 = geoFenceCommonModel23.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse7.getFenceMode());
        boolean z = true;
        if (!areEqual) {
            GeoFenceCommonModel geoFenceCommonModel24 = getViewModel().getGeoFenceCommonModel();
        }
        GeoFenceCommonModel geoFenceCommonModel25 = getViewModel().getGeoFenceCommonModel();
        GeoFenceBody geoFenceBody12 = geoFenceCommonModel25 != null ? geoFenceCommonModel25.getGeoFenceBody() : null;
        if (geoFenceBody12 != null) {
            GeoFenceCommonModel geoFenceCommonModel26 = getViewModel().getGeoFenceCommonModel();
            geoFenceBody12.setFenceMode((geoFenceCommonModel26 == null || (geoFenceGetResponse5 = geoFenceCommonModel26.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse5.getFenceMode());
        }
        GeoFenceCommonModel geoFenceCommonModel27 = getViewModel().getGeoFenceCommonModel();
        String fenceMode2 = (geoFenceCommonModel27 == null || (geoFenceBody2 = geoFenceCommonModel27.getGeoFenceBody()) == null) ? null : geoFenceBody2.getFenceMode();
        if (fenceMode2 != null) {
            int hashCode = fenceMode2.hashCode();
            if (hashCode != -1349088399) {
                if (hashCode != -938578798) {
                    if (hashCode == 108704329 && fenceMode2.equals(AppConstants.GEO_FENCE_MODE_ROUTE)) {
                        getViewModel().setSelectedFenceId(2);
                    }
                } else if (fenceMode2.equals(AppConstants.GEO_FENCE_MODE_RADIUS)) {
                    getViewModel().setSelectedFenceId(0);
                }
            } else if (fenceMode2.equals(AppConstants.GEO_FENCE_MODE_CUSTOM)) {
                getViewModel().setSelectedFenceId(1);
            }
        }
        GeoFenceCommonModel geoFenceCommonModel28 = getViewModel().getGeoFenceCommonModel();
        Double gpsLat = (geoFenceCommonModel28 == null || (geoFenceBody4 = geoFenceCommonModel28.getGeoFenceBody()) == null || (destinationLocation3 = geoFenceBody4.getDestinationLocation()) == null) ? null : destinationLocation3.getGpsLat();
        GeoFenceCommonModel geoFenceCommonModel29 = getViewModel().getGeoFenceCommonModel();
        if (Intrinsics.areEqual(gpsLat, (geoFenceCommonModel29 == null || (geoFenceGetResponse6 = geoFenceCommonModel29.getGeoFenceGetResponse()) == null || (destinationLocation2 = geoFenceGetResponse6.getDestinationLocation()) == null) ? null : destinationLocation2.getGpsLat())) {
            GeoFenceCommonModel geoFenceCommonModel30 = getViewModel().getGeoFenceCommonModel();
            Double gpsLong = (geoFenceCommonModel30 == null || (geoFenceBody3 = geoFenceCommonModel30.getGeoFenceBody()) == null || (destinationLocation = geoFenceBody3.getDestinationLocation()) == null) ? null : destinationLocation.getGpsLong();
            GeoFenceCommonModel geoFenceCommonModel31 = getViewModel().getGeoFenceCommonModel();
        }
        GeoFenceCommonModel geoFenceCommonModel32 = getViewModel().getGeoFenceCommonModel();
    }

    @SuppressLint({"SetTextI18n"})
    private final void setTime() {
        GeoFenceBody geoFenceBody;
        String endTime;
        GeoFenceBody geoFenceBody2;
        String startTime;
        GeoFenceBody geoFenceBody3;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        String str = null;
        if (Intrinsics.areEqual((geoFenceCommonModel == null || (geoFenceBody3 = geoFenceCommonModel.getGeoFenceBody()) == null) ? null : geoFenceBody3.getTimeMode(), AppConstants.GEO_FENCE_TIME_MODE_SKIP)) {
            MaterialTextView tvTime = (MaterialTextView) _$_findCachedViewById(R.id.tvTime);
            Intrinsics.checkNotNullExpressionValue(tvTime, "tvTime");
            ExtensionsKt.hide(tvTime);
            return;
        }
        int i2 = R.id.tvTime;
        MaterialTextView tvTime2 = (MaterialTextView) _$_findCachedViewById(i2);
        Intrinsics.checkNotNullExpressionValue(tvTime2, "tvTime");
        ExtensionsKt.show(tvTime2);
        MaterialTextView materialTextView = (MaterialTextView) _$_findCachedViewById(i2);
        StringBuilder sb = new StringBuilder();
        GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
        sb.append((geoFenceCommonModel2 == null || (geoFenceBody2 = geoFenceCommonModel2.getGeoFenceBody()) == null || (startTime = geoFenceBody2.getStartTime()) == null) ? null : AppUtil.Companion.convertToTime(startTime));
        sb.append(" - ");
        GeoFenceCommonModel geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
        if (geoFenceCommonModel3 != null && (geoFenceBody = geoFenceCommonModel3.getGeoFenceBody()) != null && (endTime = geoFenceBody.getEndTime()) != null) {
            str = AppUtil.Companion.convertToTime(endTime);
        }
        sb.append(str);
        materialTextView.setText(sb.toString());
    }

    @SuppressLint({"SetTextI18n"})
    private final void setTimeValue() {
        String str;
        GeoFenceBody geoFenceBody;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        String timeMode = (geoFenceCommonModel == null || (geoFenceBody = geoFenceCommonModel.getGeoFenceBody()) == null) ? null : geoFenceBody.getTimeMode();
        if (timeMode != null) {
            switch (timeMode.hashCode()) {
                case -1339174267:
                    str = AppConstants.GEO_FENCE_TIME_MODE_SET_TIME_CUSTOM_DATE_RANGE;
                    timeMode.equals(str);
                    break;
                case -96913360:
                    str = AppConstants.GEO_FENCE_TIME_MODE_SET_TIME_CUSTOM_DAYS;
                    timeMode.equals(str);
                    break;
                case 166327373:
                    str = AppConstants.GEO_FENCE_TIME_MODE_SET_TIME_WEEK_DAYS;
                    timeMode.equals(str);
                    break;
                case 901250122:
                    str = AppConstants.GEO_FENCE_TIME_MODE_SET_TIME_ALL_DAYS;
                    timeMode.equals(str);
                    break;
                case 1415560330:
                    str = AppConstants.GEO_FENCE_TIME_MODE_SET_TIME;
                    timeMode.equals(str);
                    break;
            }
        }
        setTime();
    }

    @SuppressLint({"SetTextI18n"})
    private final void setUIData() {
        AppCompatTextView appCompatTextView;
        StringBuilder sb;
        GeoFenceBody geoFenceBody;
        String fenceMode;
        StringBuilder sb2;
        String valueOf;
        GetGeoFenceResponseItem geoFenceResponse;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        String str = null;
        if (geoFenceCommonModel != null && geoFenceCommonModel.getFenceCreationMode() == 6) {
            GeoFenceActivity geoFenceActivity = this.parentActivity;
            if ((geoFenceActivity != null ? geoFenceActivity.getGeoFenceResponse() : null) != null) {
                setModelDataForEdit();
                int i2 = R.id.ivFenceTypeNav;
                ((AppCompatImageView) _$_findCachedViewById(i2)).setEnabled(false);
                AppCompatImageView ivFenceTypeNav = (AppCompatImageView) _$_findCachedViewById(i2);
                Intrinsics.checkNotNullExpressionValue(ivFenceTypeNav, "ivFenceTypeNav");
                ExtensionsKt.hide(ivFenceTypeNav);
                ((AppCompatButton) _$_findCachedViewById(R.id.btnActivate)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_save));
                AppCompatButton btnActivateLater = (AppCompatButton) _$_findCachedViewById(R.id.btnActivateLater);
                Intrinsics.checkNotNullExpressionValue(btnActivateLater, "btnActivateLater");
                ExtensionsKt.hide(btnActivateLater);
                setFenceName();
                appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvFenceTypeVal);
                sb = new StringBuilder();
                GeoFenceActivity geoFenceActivity2 = this.parentActivity;
                if (geoFenceActivity2 != null && (geoFenceResponse = geoFenceActivity2.getGeoFenceResponse()) != null && (fenceMode = geoFenceResponse.getFenceMode()) != null) {
                    if (fenceMode.length() > 0) {
                        sb2 = new StringBuilder();
                        char charAt = fenceMode.charAt(0);
                        if (Character.isLowerCase(charAt)) {
                            Locale ROOT = Locale.ROOT;
                            Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
                            valueOf = CharsKt__CharJVMKt.titlecase(charAt, ROOT);
                        } else {
                            valueOf = String.valueOf(charAt);
                        }
                        sb2.append((Object) valueOf);
                        String substring = fenceMode.substring(1);
                        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                        sb2.append(substring);
                        str = sb2.toString();
                    }
                    str = fenceMode;
                }
                sb.append(str);
                sb.append(" Fence");
                appCompatTextView.setText(sb.toString());
                ((AppCompatTextView) _$_findCachedViewById(R.id.tvFenceModeVal)).setText(getTransitionType());
                ((AppCompatTextView) _$_findCachedViewById(R.id.tvFenceSummarySource)).setText(getSourceAddressText());
                ((AppCompatTextView) _$_findCachedViewById(R.id.tvFenceSummaryDest)).setText(getDestinationAddressText());
                setTimeValue();
                setDaysValue();
            }
        }
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivFenceTypeNav)).setEnabled(true);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnActivate)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.lbl_confirm_n_activate));
        AppCompatButton btnActivateLater2 = (AppCompatButton) _$_findCachedViewById(R.id.btnActivateLater);
        Intrinsics.checkNotNullExpressionValue(btnActivateLater2, "btnActivateLater");
        ExtensionsKt.show(btnActivateLater2);
        setFenceName();
        appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvFenceTypeVal);
        sb = new StringBuilder();
        GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
        if (geoFenceCommonModel2 != null && (geoFenceBody = geoFenceCommonModel2.getGeoFenceBody()) != null && (fenceMode = geoFenceBody.getFenceMode()) != null) {
            if (fenceMode.length() > 0) {
                sb2 = new StringBuilder();
                char charAt2 = fenceMode.charAt(0);
                if (Character.isLowerCase(charAt2)) {
                    Locale ROOT2 = Locale.ROOT;
                    Intrinsics.checkNotNullExpressionValue(ROOT2, "ROOT");
                    valueOf = CharsKt__CharJVMKt.titlecase(charAt2, ROOT2);
                } else {
                    valueOf = String.valueOf(charAt2);
                }
                sb2.append((Object) valueOf);
                String substring2 = fenceMode.substring(1);
                Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
                sb2.append(substring2);
                str = sb2.toString();
            }
            str = fenceMode;
        }
        sb.append(str);
        sb.append(" Fence");
        appCompatTextView.setText(sb.toString());
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvFenceModeVal)).setText(getTransitionType());
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvFenceSummarySource)).setText(getSourceAddressText());
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvFenceSummaryDest)).setText(getDestinationAddressText());
        setTimeValue();
        setDaysValue();
    }

    private final void showEditNameUI() {
        ((TextInputEditText) _$_findCachedViewById(R.id.edtFenceName)).setEnabled(true);
        AppCompatImageView ivFenceNameClose = (AppCompatImageView) _$_findCachedViewById(R.id.ivFenceNameClose);
        Intrinsics.checkNotNullExpressionValue(ivFenceNameClose, "ivFenceNameClose");
        ExtensionsKt.show(ivFenceNameClose);
        AppCompatImageView ivFenceNameUpdateCheck = (AppCompatImageView) _$_findCachedViewById(R.id.ivFenceNameUpdateCheck);
        Intrinsics.checkNotNullExpressionValue(ivFenceNameUpdateCheck, "ivFenceNameUpdateCheck");
        ExtensionsKt.show(ivFenceNameUpdateCheck);
        AppCompatImageView ivFenceNameEdit = (AppCompatImageView) _$_findCachedViewById(R.id.ivFenceNameEdit);
        Intrinsics.checkNotNullExpressionValue(ivFenceNameEdit, "ivFenceNameEdit");
        ExtensionsKt.hide(ivFenceNameEdit);
    }

    private final void updateFence() {
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        if (geoFenceCommonModel != null) {
            GeoFenceBody geoFenceBody = geoFenceCommonModel.getGeoFenceBody();
            GetGeoFenceResponseItem geoFenceGetResponse = geoFenceCommonModel.getGeoFenceGetResponse();
            geoFenceBody.setFenceStatus(geoFenceGetResponse != null ? geoFenceGetResponse.getFenceStatus() : null);
            Logger logger = Logger.INSTANCE;
            logger.e("GeoFenceBody " + new Gson().toJson(geoFenceCommonModel.getGeoFenceBody()));
            AppUtil.Companion companion = AppUtil.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            companion.showDialog(requireContext);
            GeoFenceService geoFenceService = new GeoFenceService();
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            SharedPref.Companion companion2 = SharedPref.Companion;
            Context requireContext3 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
            String vinNumber = companion2.getVinNumber(requireContext3);
            GetGeoFenceResponseItem geoFenceGetResponse2 = geoFenceCommonModel.getGeoFenceGetResponse();
            String fenceId = geoFenceGetResponse2 != null ? geoFenceGetResponse2.getFenceId() : null;
            Intrinsics.checkNotNull(fenceId);
            geoFenceService.updateGeoFence(requireContext2, vinNumber, fenceId, geoFenceCommonModel.getGeoFenceBody());
        }
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
    public final void getResponse(@NotNull ErrorResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        AppUtil.Companion.dismissDialog();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        ExtensionsKt.showToast(requireActivity, response.getMsg());
    }

    @Subscribe
    public final void getResponse(@NotNull FailResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        Logger logger = Logger.INSTANCE;
        logger.e("API Name: " + response.getApiName());
        AppUtil.Companion.dismissDialog();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ExtensionsKt.showToast(requireContext, response.getMessage());
    }

    @Subscribe
    public final void getResponse(@NotNull PostGeoFenceResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        AppUtil.Companion.dismissDialog();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String message = response.getMessage();
        Intrinsics.checkNotNull(message);
        ExtensionsKt.showToast(requireActivity, message);
        navigateToListActivity();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        GeoFenceCommonModel geoFenceCommonModel;
        GeoFenceActivity geoFenceActivity;
        GeoFenceCommonModel geoFenceCommonModel2;
        GeoFenceCommonModel geoFenceCommonModel3;
        if (SystemClock.elapsedRealtime() - this.lastClickTime < 1500) {
            return;
        }
        this.lastClickTime = SystemClock.elapsedRealtime();
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivFenceNameEdit))) {
            showEditNameUI();
            return;
        }
        boolean z = false;
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivFenceNameUpdateCheck))) {
            a(this, false, 1, null);
        } else if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivFenceNameClose))) {
            hideEditNameUI(true);
        } else {
            if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivFenceTypeNav)) ? true : Intrinsics.areEqual(view, (RelativeLayout) _$_findCachedViewById(R.id.rlFenceType))) {
                GeoFenceCommonModel geoFenceCommonModel4 = getViewModel().getGeoFenceCommonModel();
                if (!(geoFenceCommonModel4 != null && geoFenceCommonModel4.getFenceCreationMode() == 5)) {
                    GeoFenceCommonModel geoFenceCommonModel5 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel5 != null && geoFenceCommonModel5.getFenceCreationMode() == 7) {
                        z = true;
                    }
                    if (!z) {
                        return;
                    }
                }
                GeoFenceCommonModel geoFenceCommonModel6 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel6 != null) {
                    geoFenceCommonModel6.setFenceCreationMode(7);
                }
                GeoFenceActivity geoFenceActivity2 = this.parentActivity;
                if (geoFenceActivity2 != null) {
                    geoFenceActivity2.navigateToChooseFenceTypeFragment();
                    return;
                }
                return;
            }
            if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivFenceModeNav)) ? true : Intrinsics.areEqual(view, (RelativeLayout) _$_findCachedViewById(R.id.fenceMode))) {
                GeoFenceCommonModel geoFenceCommonModel7 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel7 != null && geoFenceCommonModel7.getFenceCreationMode() == 5) {
                    z = true;
                }
                if (z && (geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel()) != null) {
                    geoFenceCommonModel3.setFenceCreationMode(7);
                }
                GeoFenceActivity geoFenceActivity3 = this.parentActivity;
                if (geoFenceActivity3 != null) {
                    geoFenceActivity3.navigateToSetFenceFragment();
                    return;
                }
                return;
            }
            if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivRouteNav)) ? true : Intrinsics.areEqual(view, (RelativeLayout) _$_findCachedViewById(R.id.rlFenceRoute))) {
                GeoFenceCommonModel geoFenceCommonModel8 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel8 != null && geoFenceCommonModel8.getFenceCreationMode() == 5) {
                    z = true;
                }
                if (z && (geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel()) != null) {
                    geoFenceCommonModel2.setFenceCreationMode(7);
                }
                int selectedFenceId = getViewModel().getSelectedFenceId();
                if (selectedFenceId == 0 || selectedFenceId == 1) {
                    GeoFenceActivity geoFenceActivity4 = this.parentActivity;
                    if (geoFenceActivity4 != null) {
                        geoFenceActivity4.navigateToGeoFenceMapFragment();
                        return;
                    }
                    return;
                } else if (selectedFenceId == 2 && (geoFenceActivity = this.parentActivity) != null) {
                    geoFenceActivity.navigateToSetLocationFragment();
                    return;
                } else {
                    return;
                }
            }
            if (!(Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivSetTimeNav)) ? true : Intrinsics.areEqual(view, (RelativeLayout) _$_findCachedViewById(R.id.rlFenceTime)))) {
                int i2 = R.id.btnActivate;
                if (!Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(i2))) {
                    if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnActivateLater))) {
                        createFence(false);
                        return;
                    }
                    return;
                } else if (Intrinsics.areEqual(((AppCompatButton) _$_findCachedViewById(i2)).getText(), getString(uat.psa.mym.mycitroenconnect.R.string.label_save))) {
                    updateFence();
                    return;
                } else {
                    createFence(true);
                    return;
                }
            }
            GeoFenceCommonModel geoFenceCommonModel9 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel9 != null && geoFenceCommonModel9.getFenceCreationMode() == 5) {
                z = true;
            }
            if (z && (geoFenceCommonModel = getViewModel().getGeoFenceCommonModel()) != null) {
                geoFenceCommonModel.setFenceCreationMode(7);
            }
            GeoFenceActivity geoFenceActivity5 = this.parentActivity;
            if (geoFenceActivity5 != null) {
                geoFenceActivity5.navigateToSetTimeFragment();
            }
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
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_geo_fence_summary, viewGroup, false);
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment, androidx.fragment.app.Fragment
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
        setUIData();
    }
}
