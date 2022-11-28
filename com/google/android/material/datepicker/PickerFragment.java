package com.google.android.material.datepicker;

import androidx.fragment.app.Fragment;
import java.util.LinkedHashSet;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class PickerFragment<S> extends Fragment {

    /* renamed from: a  reason: collision with root package name */
    protected final LinkedHashSet f7283a = new LinkedHashSet();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        this.f7283a.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean addOnSelectionChangedListener(OnSelectionChangedListener onSelectionChangedListener) {
        return this.f7283a.add(onSelectionChangedListener);
    }
}
