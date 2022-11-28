package com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.car.app.CarContext;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCar;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SwitchCarConfirmationFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "SwitchCarConfirmationFragment";
    @Nullable
    private MyCar car;
    @Nullable
    private OnCarSwitchListener onCarSwitchListener;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private int position = -1;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final SwitchCarConfirmationFragment newInstance(@NotNull MyCar car, int i2) {
            Intrinsics.checkNotNullParameter(car, "car");
            SwitchCarConfirmationFragment switchCarConfirmationFragment = new SwitchCarConfirmationFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(CarContext.CAR_SERVICE, car);
            bundle.putInt(AppConstants.ARG_POSITION, i2);
            switchCarConfirmationFragment.setArguments(bundle);
            return switchCarConfirmationFragment;
        }
    }

    private final void initView() {
        MyCar myCar = this.car;
        if (myCar != null) {
            ((AppCompatTextView) _$_findCachedViewById(R.id.tvSwitchConfDesc)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.switch_car_confirmation, myCar.getCarModelName()));
        }
        ((AppCompatButton) _$_findCachedViewById(R.id.btnYes)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnNo)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivClose)).setOnClickListener(this);
    }

    @JvmStatic
    @NotNull
    public static final SwitchCarConfirmationFragment newInstance(@NotNull MyCar myCar, int i2) {
        return Companion.newInstance(myCar, i2);
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
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnYes))) {
            OnCarSwitchListener onCarSwitchListener = this.onCarSwitchListener;
            if (onCarSwitchListener != null && onCarSwitchListener != null) {
                MyCar myCar = this.car;
                Intrinsics.checkNotNull(myCar);
                onCarSwitchListener.onCarSwitch(myCar, this.position);
            }
        } else {
            if (!(Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnNo)) ? true : Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivClose)))) {
                return;
            }
        }
        dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.position = arguments.getInt(AppConstants.ARG_POSITION);
            this.car = (MyCar) arguments.getParcelable(CarContext.CAR_SERVICE);
        }
        setStyle(0, uat.psa.mym.mycitroenconnect.R.style.DialogStyle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.bottom_sheet_switch_car_confirmation, viewGroup, false);
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
        initView();
    }

    public final void setOnCarSwitchListener(@NotNull OnCarSwitchListener onCarSwitchListener) {
        Intrinsics.checkNotNullParameter(onCarSwitchListener, "onCarSwitchListener");
        this.onCarSwitchListener = onCarSwitchListener;
    }
}
