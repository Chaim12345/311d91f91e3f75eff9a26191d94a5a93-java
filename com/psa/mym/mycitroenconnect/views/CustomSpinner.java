package com.psa.mym.mycitroenconnect.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.widget.AppCompatSpinner;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class CustomSpinner extends AppCompatSpinner {
    @NotNull
    public Map<Integer, View> _$_findViewCache;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomSpinner(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomSpinner(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomSpinner(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    @Override // android.widget.AbsSpinner, android.widget.AdapterView
    public void setSelection(int i2) {
        AdapterView.OnItemSelectedListener onItemSelectedListener;
        boolean z = i2 == getSelectedItemPosition();
        super.setSelection(i2);
        if (!z || (onItemSelectedListener = getOnItemSelectedListener()) == null) {
            return;
        }
        onItemSelectedListener.onItemSelected(this, getSelectedView(), i2, getSelectedItemId());
    }

    @Override // android.widget.AbsSpinner
    public void setSelection(int i2, boolean z) {
        AdapterView.OnItemSelectedListener onItemSelectedListener;
        boolean z2 = i2 == getSelectedItemPosition();
        super.setSelection(i2, z);
        if (!z2 || (onItemSelectedListener = getOnItemSelectedListener()) == null) {
            return;
        }
        onItemSelectedListener.onItemSelected(this, getSelectedView(), i2, getSelectedItemId());
    }
}
