package com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.model.MessageEvent;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.event_bus.GlobalBusUtil;
import com.psa.mym.mycitroenconnect.views.custom_seek_bar.CustomSeekBar;
import com.psa.mym.mycitroenconnect.views.custom_seek_bar.SimpleCustomSeekBarListener;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SpeedSettingFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "SpeedSettingFragment";
    private double mDifferenceVal;
    @Nullable
    private String mSelectedVal;
    private int minimumVal;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private String mPageMode = AppConstants.PAGE_MODE_RADIUS_SETTINGS;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final SpeedSettingFragment newInstance() {
            SpeedSettingFragment speedSettingFragment = new SpeedSettingFragment();
            speedSettingFragment.setArguments(new Bundle());
            return speedSettingFragment;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x01c0, code lost:
        if (r1 != false) goto L49;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void initViews() {
        String str;
        CharSequence trim;
        boolean isBlank;
        AppCompatTextView appCompatTextView;
        int i2;
        int i3 = R.id.csbSeekBar;
        boolean z = false;
        ((CustomSeekBar) _$_findCachedViewById(i3)).setEnableStep(false);
        String str2 = this.mPageMode;
        switch (str2.hashCode()) {
            case -1880941117:
                if (str2.equals(AppConstants.SPEED_LIMIT_MODE)) {
                    appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvSpeedLimitTitle);
                    i2 = uat.psa.mym.mycitroenconnect.R.string.speed_limit_title;
                    appCompatTextView.setText(getString(i2));
                    ((AppCompatTextView) _$_findCachedViewById(R.id.tvSpeedSet)).setVisibility(4);
                    ((CustomSeekBar) _$_findCachedViewById(i3)).setMin(1);
                    ((CustomSeekBar) _$_findCachedViewById(i3)).setMax(60);
                    break;
                }
                break;
            case -1269621170:
                if (str2.equals(AppConstants.AC_IDLING_MODE)) {
                    ((AppCompatTextView) _$_findCachedViewById(R.id.tvSpeedLimitTitle)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.ac_idling_threshold_time));
                    ((AppCompatTextView) _$_findCachedViewById(R.id.tvSpeedSet)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.ac_idling_message));
                    ((CustomSeekBar) _$_findCachedViewById(i3)).setMin(1);
                    ((CustomSeekBar) _$_findCachedViewById(i3)).setMax(60);
                    break;
                }
                break;
            case -1239146154:
                if (str2.equals(AppConstants.PAGE_MODE_MAX_SPEED_SETTING)) {
                    ((AppCompatTextView) _$_findCachedViewById(R.id.tvSpeedLimitTitle)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_set_max_speed));
                    ((AppCompatTextView) _$_findCachedViewById(R.id.tvSpeedSet)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_set_speed_limit));
                    ((CustomSeekBar) _$_findCachedViewById(i3)).setMin(0);
                    ((CustomSeekBar) _$_findCachedViewById(i3)).setMax(60);
                    break;
                }
                break;
            case 271942960:
                if (str2.equals(AppConstants.PAGE_MODE_RADIUS_SETTINGS)) {
                    this.minimumVal = 0;
                    this.mDifferenceVal = 1.0d;
                    ((AppCompatTextView) _$_findCachedViewById(R.id.tvSpeedLimitTitle)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_set_radius));
                    ((AppCompatTextView) _$_findCachedViewById(R.id.tvSpeedSet)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_set_geo_fence_rad));
                    ((CustomSeekBar) _$_findCachedViewById(i3)).setMin(1);
                    ((CustomSeekBar) _$_findCachedViewById(i3)).setMax(100);
                    break;
                }
                break;
            case 915556993:
                if (str2.equals(AppConstants.PAGE_MODE_VALET_RADIUS)) {
                    this.minimumVal = 0;
                    this.mDifferenceVal = 1.0d;
                    ((AppCompatTextView) _$_findCachedViewById(R.id.tvSpeedLimitTitle)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_set_radius));
                    ((AppCompatTextView) _$_findCachedViewById(R.id.tvSpeedSet)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_set_valet_rad));
                    ((CustomSeekBar) _$_findCachedViewById(i3)).setMin(1);
                    ((CustomSeekBar) _$_findCachedViewById(i3)).setMax(5);
                    break;
                }
                break;
            case 1320937407:
                if (str2.equals(AppConstants.PAGE_MODE_VALET_MIN_SETTINGS)) {
                    appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvSpeedLimitTitle);
                    i2 = uat.psa.mym.mycitroenconnect.R.string.label_set_idling_time;
                    appCompatTextView.setText(getString(i2));
                    ((AppCompatTextView) _$_findCachedViewById(R.id.tvSpeedSet)).setVisibility(4);
                    ((CustomSeekBar) _$_findCachedViewById(i3)).setMin(1);
                    ((CustomSeekBar) _$_findCachedViewById(i3)).setMax(60);
                    break;
                }
                break;
            case 1591561499:
                if (str2.equals(AppConstants.PAGE_MODE_SPEED_SETTINGS)) {
                    ((AppCompatTextView) _$_findCachedViewById(R.id.tvSpeedLimitTitle)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_speed_title));
                    ((AppCompatTextView) _$_findCachedViewById(R.id.tvSpeedSet)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_set_speed_limit));
                    ((CustomSeekBar) _$_findCachedViewById(i3)).setMin(0);
                    ((CustomSeekBar) _$_findCachedViewById(i3)).setMax(100);
                    break;
                }
                break;
        }
        String str3 = this.mSelectedVal;
        if (str3 == null || str3.length() == 0) {
            return;
        }
        String str4 = this.mSelectedVal;
        if (str4 != null) {
            isBlank = StringsKt__StringsJVMKt.isBlank(str4);
        }
        z = true;
        if (z || (str = this.mSelectedVal) == null) {
            return;
        }
        trim = StringsKt__StringsKt.trim((CharSequence) str);
        String obj = trim.toString();
        if (obj != null) {
            ((CustomSeekBar) _$_findCachedViewById(i3)).setProgress(Integer.valueOf(Integer.parseInt(obj)));
        }
    }

    @JvmStatic
    @NotNull
    public static final SpeedSettingFragment newInstance() {
        return Companion.newInstance();
    }

    private final void setListener() {
        ((AppCompatButton) _$_findCachedViewById(R.id.btnSpeedCancel)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnSpeedDone)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivCloseSpeed)).setOnClickListener(this);
        ((CustomSeekBar) _$_findCachedViewById(R.id.csbSeekBar)).setOnSeekBarChangeListener(new SimpleCustomSeekBarListener() { // from class: com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.SpeedSettingFragment$setListener$1
            @Override // com.psa.mym.mycitroenconnect.views.custom_seek_bar.SimpleCustomSeekBarListener, com.psa.mym.mycitroenconnect.views.custom_seek_bar.CustomSeekBarListener
            public void onProgressChanged(@Nullable SeekBar seekBar, double d2, @NotNull String formattedValue, boolean z) {
                Intrinsics.checkNotNullParameter(formattedValue, "formattedValue");
                super.onProgressChanged(seekBar, d2, formattedValue, z);
                SpeedSettingFragment.this.updateTvWithProgress(String.valueOf((int) d2));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002e, code lost:
        if (r1.equals(com.psa.mym.mycitroenconnect.common.AppConstants.PAGE_MODE_MAX_SPEED_SETTING) == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0031, code lost:
        r1 = (androidx.appcompat.widget.AppCompatTextView) _$_findCachedViewById(r0);
        r3 = new java.lang.StringBuilder();
        r3.append(r7);
        r7 = " kmph";
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0048, code lost:
        if (r1.equals(com.psa.mym.mycitroenconnect.common.AppConstants.AC_IDLING_MODE) == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0051, code lost:
        if (r1.equals(com.psa.mym.mycitroenconnect.common.AppConstants.SPEED_LIMIT_MODE) == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0054, code lost:
        r1 = (androidx.appcompat.widget.AppCompatTextView) _$_findCachedViewById(r0);
        r3 = new java.lang.StringBuilder();
        r3.append(r7);
        r7 = " Mins";
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x001c, code lost:
        if (r1.equals(com.psa.mym.mycitroenconnect.common.AppConstants.PAGE_MODE_SPEED_SETTINGS) == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0025, code lost:
        if (r1.equals(com.psa.mym.mycitroenconnect.common.AppConstants.PAGE_MODE_VALET_MIN_SETTINGS) == false) goto L24;
     */
    @SuppressLint({"SetTextI18n"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void updateTvWithProgress(String str) {
        AppCompatTextView appCompatTextView;
        StringBuilder sb;
        String str2;
        boolean contains$default;
        int i2 = R.id.tvSliderToolTip;
        ((AppCompatTextView) _$_findCachedViewById(i2)).setVisibility(0);
        String str3 = this.mPageMode;
        switch (str3.hashCode()) {
            case -1880941117:
                break;
            case -1269621170:
                break;
            case -1239146154:
                break;
            case 1320937407:
                break;
            case 1591561499:
                break;
            default:
                appCompatTextView = (AppCompatTextView) _$_findCachedViewById(i2);
                sb = new StringBuilder();
                sb.append(str);
                str2 = " km";
                break;
        }
        sb.append(str2);
        appCompatTextView.setText(sb.toString());
        int i3 = R.id.csbSeekBar;
        int leftBound = ((CustomSeekBar) _$_findCachedViewById(i3)).getLeftBound();
        if (leftBound == 0) {
            leftBound += ((CustomSeekBar) _$_findCachedViewById(i3)).getSeekBarLeftBound();
        }
        ((AppCompatTextView) _$_findCachedViewById(i2)).setX(leftBound);
        CharSequence text = ((AppCompatTextView) _$_findCachedViewById(i2)).getText();
        Intrinsics.checkNotNullExpressionValue(text, "tvSliderToolTip.text");
        contains$default = StringsKt__StringsKt.contains$default(text, (CharSequence) "kmph", false, 2, (Object) null);
        if (contains$default) {
            ((AppCompatTextView) _$_findCachedViewById(i2)).setX(leftBound - 30);
        }
        Logger logger = Logger.INSTANCE;
        logger.e("tvSliderToolTip x co-ordinate:" + leftBound);
        logger.e("tvSliderToolTip x co-ordinate Updated :" + ((AppCompatTextView) _$_findCachedViewById(i2)).getX());
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
        EventBus optBus;
        MessageEvent messageEvent;
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnSpeedDone))) {
            optBus = GlobalBusUtil.Companion.optBus();
            messageEvent = new MessageEvent(((AppCompatTextView) _$_findCachedViewById(R.id.tvSliderToolTip)).getText().toString());
        } else {
            if (!(Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnSpeedCancel)) ? true : Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivCloseSpeed)))) {
                return;
            }
            optBus = GlobalBusUtil.Companion.optBus();
            messageEvent = new MessageEvent(AppConstants.DEFAULT_VAL_SPEED_DLG);
        }
        optBus.post(messageEvent);
        dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getArguments();
        setStyle(0, uat.psa.mym.mycitroenconnect.R.style.DialogStyle);
    }

    @Override // com.google.android.material.bottomsheet.BottomSheetDialogFragment, androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    @NotNull
    public Dialog onCreateDialog(@Nullable Bundle bundle) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext(), getTheme());
        bottomSheetDialog.getBehavior().setState(3);
        bottomSheetDialog.getBehavior().setSkipCollapsed(true);
        return bottomSheetDialog;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_speed_setting, viewGroup, false);
        Bundle requireArguments = requireArguments();
        Intrinsics.checkNotNullExpressionValue(requireArguments, "requireArguments()");
        String string = requireArguments.getString("login");
        Intrinsics.checkNotNull(string);
        this.mPageMode = string;
        if (requireArguments.containsKey(AppConstants.SPEED_SELECTED_VAL)) {
            this.mSelectedVal = requireArguments.getString(AppConstants.SPEED_SELECTED_VAL);
        }
        return inflate;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        setListener();
        initViews();
    }
}
