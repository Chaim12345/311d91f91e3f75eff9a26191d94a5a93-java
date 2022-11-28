package androidx.car.app.model;

import android.annotation.SuppressLint;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.annotations.RequiresCarApi;
import java.util.Objects;
@RequiresCarApi(2)
/* loaded from: classes.dex */
public final class ClickableSpan extends CarSpan {
    @Nullable
    @Keep
    private final OnClickDelegate mOnClickDelegate;

    private ClickableSpan() {
        this.mOnClickDelegate = null;
    }

    private ClickableSpan(OnClickListener onClickListener) {
        this.mOnClickDelegate = OnClickDelegateImpl.create(onClickListener);
    }

    @NonNull
    @SuppressLint({"ExecutorRegistration"})
    public static ClickableSpan create(@NonNull OnClickListener onClickListener) {
        Objects.requireNonNull(onClickListener);
        return new ClickableSpan(onClickListener);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ClickableSpan) {
            return Objects.equals(Boolean.valueOf(this.mOnClickDelegate == null), Boolean.valueOf(((ClickableSpan) obj).mOnClickDelegate == null));
        }
        return false;
    }

    @NonNull
    public OnClickDelegate getOnClickDelegate() {
        OnClickDelegate onClickDelegate = this.mOnClickDelegate;
        Objects.requireNonNull(onClickDelegate);
        return onClickDelegate;
    }

    public int hashCode() {
        Object[] objArr = new Object[1];
        objArr[0] = Boolean.valueOf(this.mOnClickDelegate == null);
        return Objects.hash(objArr);
    }

    @NonNull
    public String toString() {
        return "[clickable]";
    }
}
