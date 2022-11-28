package androidx.car.app.model;

import android.annotation.SuppressLint;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import java.util.Objects;
@SuppressLint({"ListenerInterface"})
/* loaded from: classes.dex */
public final class ParkedOnlyOnClickListener implements OnClickListener {
    @Keep
    private final OnClickListener mListener;

    private ParkedOnlyOnClickListener(OnClickListener onClickListener) {
        this.mListener = onClickListener;
    }

    @NonNull
    @SuppressLint({"ExecutorRegistration"})
    public static ParkedOnlyOnClickListener create(@NonNull OnClickListener onClickListener) {
        Objects.requireNonNull(onClickListener);
        return new ParkedOnlyOnClickListener(onClickListener);
    }

    @Override // androidx.car.app.model.OnClickListener
    public void onClick() {
        this.mListener.onClick();
    }
}
