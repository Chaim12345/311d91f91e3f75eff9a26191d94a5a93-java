package androidx.car.app.model;

import android.annotation.SuppressLint;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;
/* loaded from: classes.dex */
public final class Toggle {
    @Keep
    private final boolean mIsChecked;
    @Nullable
    @Keep
    private final OnCheckedChangeDelegate mOnCheckedChangeDelegate;

    /* loaded from: classes.dex */
    public static final class Builder {
        boolean mIsChecked;
        OnCheckedChangeDelegate mOnCheckedChangeDelegate;

        @SuppressLint({"ExecutorRegistration"})
        public Builder(@NonNull OnCheckedChangeListener onCheckedChangeListener) {
            this.mOnCheckedChangeDelegate = OnCheckedChangeDelegateImpl.create(onCheckedChangeListener);
        }

        @NonNull
        public Toggle build() {
            return new Toggle(this);
        }

        @NonNull
        public Builder setChecked(boolean z) {
            this.mIsChecked = z;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public interface OnCheckedChangeListener {
        void onCheckedChange(boolean z);
    }

    private Toggle() {
        this.mOnCheckedChangeDelegate = null;
        this.mIsChecked = false;
    }

    Toggle(Builder builder) {
        this.mIsChecked = builder.mIsChecked;
        this.mOnCheckedChangeDelegate = builder.mOnCheckedChangeDelegate;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Toggle) && this.mIsChecked == ((Toggle) obj).mIsChecked;
    }

    @NonNull
    public OnCheckedChangeDelegate getOnCheckedChangeDelegate() {
        OnCheckedChangeDelegate onCheckedChangeDelegate = this.mOnCheckedChangeDelegate;
        Objects.requireNonNull(onCheckedChangeDelegate);
        return onCheckedChangeDelegate;
    }

    public int hashCode() {
        return Boolean.valueOf(this.mIsChecked).hashCode();
    }

    public boolean isChecked() {
        return this.mIsChecked;
    }

    @NonNull
    public String toString() {
        return "[ isChecked: " + this.mIsChecked + "]";
    }
}
