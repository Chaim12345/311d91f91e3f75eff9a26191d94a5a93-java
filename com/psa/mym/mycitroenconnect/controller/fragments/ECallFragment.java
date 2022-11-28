package com.psa.mym.mycitroenconnect.controller.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import com.psa.mym.mycitroenconnect.R;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ECallFragment extends Fragment implements View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final ECallFragment newInstance() {
            ECallFragment eCallFragment = new ECallFragment();
            eCallFragment.setArguments(new Bundle());
            return eCallFragment;
        }
    }

    @JvmStatic
    @NotNull
    public static final ECallFragment newInstance() {
        return Companion.newInstance();
    }

    private final void setListeners() {
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivEcallClose)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnECallCancel)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnCallSupport)).setOnClickListener(this);
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvECallCustomerCareNo)).setOnClickListener(this);
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
        CharSequence trim;
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivEcallClose)) ? true : Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnECallCancel))) {
            requireActivity().onBackPressed();
            return;
        }
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnCallSupport)) ? true : Intrinsics.areEqual(view, (AppCompatTextView) _$_findCachedViewById(R.id.tvECallCustomerCareNo))) {
            try {
                String string = getString(uat.psa.mym.mycitroenconnect.R.string.label_customer_care_number);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.label_customer_care_number)");
                trim = StringsKt__StringsKt.trim((CharSequence) string);
                String obj = trim.toString();
                requireActivity().startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + obj)));
            } catch (Exception e2) {
                e2.printStackTrace();
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
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_e_call, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setListeners();
    }
}
