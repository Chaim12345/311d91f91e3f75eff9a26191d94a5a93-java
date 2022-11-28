package androidx.databinding;

import androidx.annotation.Nullable;
import java.io.Serializable;
/* loaded from: classes.dex */
public class ObservableField<T> extends BaseObservableField implements Serializable {
    private T mValue;

    public ObservableField() {
    }

    public ObservableField(T t2) {
        this.mValue = t2;
    }

    public ObservableField(Observable... observableArr) {
        super(observableArr);
    }

    @Nullable
    public T get() {
        return this.mValue;
    }

    public void set(T t2) {
        if (t2 != this.mValue) {
            this.mValue = t2;
            notifyChange();
        }
    }
}
