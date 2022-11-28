package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.radiobutton.MaterialRadioButton;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class LayoutAmPmBinding implements ViewBinding {
    @NonNull
    public final MaterialRadioButton rbAM;
    @NonNull
    public final MaterialRadioButton rbPM;
    @NonNull
    private final RadioGroup rootView;

    private LayoutAmPmBinding(@NonNull RadioGroup radioGroup, @NonNull MaterialRadioButton materialRadioButton, @NonNull MaterialRadioButton materialRadioButton2) {
        this.rootView = radioGroup;
        this.rbAM = materialRadioButton;
        this.rbPM = materialRadioButton2;
    }

    @NonNull
    public static LayoutAmPmBinding bind(@NonNull View view) {
        int i2 = R.id.rbAM;
        MaterialRadioButton materialRadioButton = (MaterialRadioButton) ViewBindings.findChildViewById(view, R.id.rbAM);
        if (materialRadioButton != null) {
            i2 = R.id.rbPM;
            MaterialRadioButton materialRadioButton2 = (MaterialRadioButton) ViewBindings.findChildViewById(view, R.id.rbPM);
            if (materialRadioButton2 != null) {
                return new LayoutAmPmBinding((RadioGroup) view, materialRadioButton, materialRadioButton2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static LayoutAmPmBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static LayoutAmPmBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_am_pm, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public RadioGroup getRoot() {
        return this.rootView;
    }
}
