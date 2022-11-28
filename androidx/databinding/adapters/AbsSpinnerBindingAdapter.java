package androidx.databinding.adapters;

import android.widget.AbsSpinner;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import androidx.annotation.RestrictTo;
import androidx.databinding.BindingAdapter;
import java.util.List;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class AbsSpinnerBindingAdapter {
    @BindingAdapter({"android:entries"})
    public static <T> void setEntries(AbsSpinner absSpinner, List<T> list) {
        if (list == null) {
            absSpinner.setAdapter((SpinnerAdapter) null);
            return;
        }
        SpinnerAdapter adapter = absSpinner.getAdapter();
        if (adapter instanceof ObservableListAdapter) {
            ((ObservableListAdapter) adapter).setList(list);
        } else {
            absSpinner.setAdapter((SpinnerAdapter) new ObservableListAdapter(absSpinner.getContext(), list, 17367048, 17367049, 0));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    @BindingAdapter({"android:entries"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static <T extends CharSequence> void setEntries(AbsSpinner absSpinner, T[] tArr) {
        if (tArr == null) {
            absSpinner.setAdapter((SpinnerAdapter) null);
            return;
        }
        SpinnerAdapter adapter = absSpinner.getAdapter();
        boolean z = false;
        if (adapter != null && adapter.getCount() == tArr.length) {
            for (int i2 = 0; i2 < tArr.length; i2++) {
                if (tArr[i2].equals(adapter.getItem(i2))) {
                }
            }
            if (z) {
                return;
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(absSpinner.getContext(), 17367048, tArr);
            arrayAdapter.setDropDownViewResource(17367049);
            absSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
            return;
        }
        z = true;
        if (z) {
        }
    }
}
