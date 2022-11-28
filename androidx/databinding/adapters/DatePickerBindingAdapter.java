package androidx.databinding.adapters;

import android.widget.DatePicker;
import androidx.annotation.RestrictTo;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.InverseBindingMethod;
import androidx.databinding.InverseBindingMethods;
import androidx.databinding.library.baseAdapters.R;
@InverseBindingMethods({@InverseBindingMethod(attribute = "android:year", type = DatePicker.class), @InverseBindingMethod(attribute = "android:month", type = DatePicker.class), @InverseBindingMethod(attribute = "android:day", method = "getDayOfMonth", type = DatePicker.class)})
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class DatePickerBindingAdapter {

    /* loaded from: classes.dex */
    private static class DateChangedListener implements DatePicker.OnDateChangedListener {

        /* renamed from: a  reason: collision with root package name */
        DatePicker.OnDateChangedListener f2795a;

        /* renamed from: b  reason: collision with root package name */
        InverseBindingListener f2796b;

        /* renamed from: c  reason: collision with root package name */
        InverseBindingListener f2797c;

        /* renamed from: d  reason: collision with root package name */
        InverseBindingListener f2798d;

        private DateChangedListener() {
        }

        @Override // android.widget.DatePicker.OnDateChangedListener
        public void onDateChanged(DatePicker datePicker, int i2, int i3, int i4) {
            DatePicker.OnDateChangedListener onDateChangedListener = this.f2795a;
            if (onDateChangedListener != null) {
                onDateChangedListener.onDateChanged(datePicker, i2, i3, i4);
            }
            InverseBindingListener inverseBindingListener = this.f2796b;
            if (inverseBindingListener != null) {
                inverseBindingListener.onChange();
            }
            InverseBindingListener inverseBindingListener2 = this.f2797c;
            if (inverseBindingListener2 != null) {
                inverseBindingListener2.onChange();
            }
            InverseBindingListener inverseBindingListener3 = this.f2798d;
            if (inverseBindingListener3 != null) {
                inverseBindingListener3.onChange();
            }
        }

        public void setListeners(DatePicker.OnDateChangedListener onDateChangedListener, InverseBindingListener inverseBindingListener, InverseBindingListener inverseBindingListener2, InverseBindingListener inverseBindingListener3) {
            this.f2795a = onDateChangedListener;
            this.f2796b = inverseBindingListener;
            this.f2797c = inverseBindingListener2;
            this.f2798d = inverseBindingListener3;
        }
    }

    @BindingAdapter(requireAll = false, value = {"android:year", "android:month", "android:day", "android:onDateChanged", "android:yearAttrChanged", "android:monthAttrChanged", "android:dayAttrChanged"})
    public static void setListeners(DatePicker datePicker, int i2, int i3, int i4, DatePicker.OnDateChangedListener onDateChangedListener, InverseBindingListener inverseBindingListener, InverseBindingListener inverseBindingListener2, InverseBindingListener inverseBindingListener3) {
        if (i2 == 0) {
            i2 = datePicker.getYear();
        }
        if (i4 == 0) {
            i4 = datePicker.getDayOfMonth();
        }
        if (inverseBindingListener == null && inverseBindingListener2 == null && inverseBindingListener3 == null) {
            datePicker.init(i2, i3, i4, onDateChangedListener);
            return;
        }
        int i5 = R.id.onDateChanged;
        DateChangedListener dateChangedListener = (DateChangedListener) ListenerUtil.getListener(datePicker, i5);
        if (dateChangedListener == null) {
            dateChangedListener = new DateChangedListener();
            ListenerUtil.trackListener(datePicker, dateChangedListener, i5);
        }
        dateChangedListener.setListeners(onDateChangedListener, inverseBindingListener, inverseBindingListener2, inverseBindingListener3);
        datePicker.init(i2, i3, i4, dateChangedListener);
    }
}
