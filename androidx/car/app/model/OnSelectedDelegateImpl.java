package androidx.car.app.model;

import android.annotation.SuppressLint;
import android.os.RemoteException;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.model.IOnSelectedListener;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.OnSelectedDelegateImpl;
import androidx.car.app.utils.RemoteUtils;
import java.util.Objects;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class OnSelectedDelegateImpl implements OnSelectedDelegate {
    @Nullable
    @Keep
    private final IOnSelectedListener mStub;

    /* JADX INFO: Access modifiers changed from: private */
    @Keep
    /* loaded from: classes.dex */
    public static class OnSelectedListenerStub extends IOnSelectedListener.Stub {
        private final ItemList.OnSelectedListener mListener;

        OnSelectedListenerStub(ItemList.OnSelectedListener onSelectedListener) {
            this.mListener = onSelectedListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object lambda$onSelected$0(int i2) {
            this.mListener.onSelected(i2);
            return null;
        }

        @Override // androidx.car.app.model.IOnSelectedListener
        public void onSelected(final int i2, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(iOnDoneCallback, "onSelectedListener", new RemoteUtils.HostCall() { // from class: androidx.car.app.model.f
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    Object lambda$onSelected$0;
                    lambda$onSelected$0 = OnSelectedDelegateImpl.OnSelectedListenerStub.this.lambda$onSelected$0(i2);
                    return lambda$onSelected$0;
                }
            });
        }
    }

    private OnSelectedDelegateImpl() {
        this.mStub = null;
    }

    private OnSelectedDelegateImpl(@NonNull ItemList.OnSelectedListener onSelectedListener) {
        this.mStub = new OnSelectedListenerStub(onSelectedListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @SuppressLint({"ExecutorRegistration"})
    public static OnSelectedDelegate create(@NonNull ItemList.OnSelectedListener onSelectedListener) {
        return new OnSelectedDelegateImpl(onSelectedListener);
    }

    @Override // androidx.car.app.model.OnSelectedDelegate
    public void sendSelected(int i2, @NonNull OnDoneCallback onDoneCallback) {
        try {
            IOnSelectedListener iOnSelectedListener = this.mStub;
            Objects.requireNonNull(iOnSelectedListener);
            iOnSelectedListener.onSelected(i2, RemoteUtils.createOnDoneCallbackStub(onDoneCallback));
        } catch (RemoteException e2) {
            throw new RuntimeException(e2);
        }
    }
}
