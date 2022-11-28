package androidx.car.app.model;

import android.annotation.SuppressLint;
import android.os.RemoteException;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.model.IOnCheckedChangeListener;
import androidx.car.app.model.OnCheckedChangeDelegateImpl;
import androidx.car.app.model.Toggle;
import androidx.car.app.utils.RemoteUtils;
import java.util.Objects;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class OnCheckedChangeDelegateImpl implements OnCheckedChangeDelegate {
    @Nullable
    @Keep
    private final IOnCheckedChangeListener mStub;

    /* JADX INFO: Access modifiers changed from: private */
    @Keep
    /* loaded from: classes.dex */
    public static class OnCheckedChangeListenerStub extends IOnCheckedChangeListener.Stub {
        private final Toggle.OnCheckedChangeListener mListener;

        OnCheckedChangeListenerStub(Toggle.OnCheckedChangeListener onCheckedChangeListener) {
            this.mListener = onCheckedChangeListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object lambda$onCheckedChange$0(boolean z) {
            this.mListener.onCheckedChange(z);
            return null;
        }

        @Override // androidx.car.app.model.IOnCheckedChangeListener
        public void onCheckedChange(final boolean z, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(iOnDoneCallback, "onCheckedChange", new RemoteUtils.HostCall() { // from class: androidx.car.app.model.c
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    Object lambda$onCheckedChange$0;
                    lambda$onCheckedChange$0 = OnCheckedChangeDelegateImpl.OnCheckedChangeListenerStub.this.lambda$onCheckedChange$0(z);
                    return lambda$onCheckedChange$0;
                }
            });
        }
    }

    private OnCheckedChangeDelegateImpl() {
        this.mStub = null;
    }

    private OnCheckedChangeDelegateImpl(@NonNull Toggle.OnCheckedChangeListener onCheckedChangeListener) {
        this.mStub = new OnCheckedChangeListenerStub(onCheckedChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @SuppressLint({"ExecutorRegistration"})
    public static OnCheckedChangeDelegate create(@NonNull Toggle.OnCheckedChangeListener onCheckedChangeListener) {
        return new OnCheckedChangeDelegateImpl(onCheckedChangeListener);
    }

    @Override // androidx.car.app.model.OnCheckedChangeDelegate
    public void sendCheckedChange(boolean z, @NonNull OnDoneCallback onDoneCallback) {
        try {
            IOnCheckedChangeListener iOnCheckedChangeListener = this.mStub;
            Objects.requireNonNull(iOnCheckedChangeListener);
            iOnCheckedChangeListener.onCheckedChange(z, RemoteUtils.createOnDoneCallbackStub(onDoneCallback));
        } catch (RemoteException e2) {
            throw new RuntimeException(e2);
        }
    }
}
