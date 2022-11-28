package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.radiobutton.MaterialRadioButton;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ItemViewSelectCarAccessBinding implements ViewBinding {
    @NonNull
    public final MaterialCheckBox cbCarAccess;
    @NonNull
    public final MaterialRadioButton rbSelect;
    @NonNull
    private final RelativeLayout rootView;
    @NonNull
    public final AppCompatTextView tvCarName;
    @NonNull
    public final AppCompatTextView tvCarNumber;

    private ItemViewSelectCarAccessBinding(@NonNull RelativeLayout relativeLayout, @NonNull MaterialCheckBox materialCheckBox, @NonNull MaterialRadioButton materialRadioButton, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2) {
        this.rootView = relativeLayout;
        this.cbCarAccess = materialCheckBox;
        this.rbSelect = materialRadioButton;
        this.tvCarName = appCompatTextView;
        this.tvCarNumber = appCompatTextView2;
    }

    @NonNull
    public static ItemViewSelectCarAccessBinding bind(@NonNull View view) {
        int i2 = R.id.cbCarAccess;
        MaterialCheckBox materialCheckBox = (MaterialCheckBox) ViewBindings.findChildViewById(view, R.id.cbCarAccess);
        if (materialCheckBox != null) {
            i2 = R.id.rbSelect;
            MaterialRadioButton materialRadioButton = (MaterialRadioButton) ViewBindings.findChildViewById(view, R.id.rbSelect);
            if (materialRadioButton != null) {
                i2 = R.id.tvCarName;
                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvCarName);
                if (appCompatTextView != null) {
                    i2 = R.id.tvCarNumber;
                    AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvCarNumber);
                    if (appCompatTextView2 != null) {
                        return new ItemViewSelectCarAccessBinding((RelativeLayout) view, materialCheckBox, materialRadioButton, appCompatTextView, appCompatTextView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ItemViewSelectCarAccessBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ItemViewSelectCarAccessBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_view_select_car_access, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public RelativeLayout getRoot() {
        return this.rootView;
    }
}
