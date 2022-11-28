package androidx.car.app.navigation.model;

import android.annotation.SuppressLint;
import android.os.RemoteException;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.navigation.model.IPanModeListener;
import androidx.car.app.navigation.model.PanModeDelegateImpl;
import androidx.car.app.utils.RemoteUtils;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class PanModeDelegateImpl implements PanModeDelegate {
    @Nullable
    @Keep
    private final IPanModeListener mStub;

    /* JADX INFO: Access modifiers changed from: private */
    @Keep
    /* loaded from: classes.dex */
    public static class PanModeListenerStub extends IPanModeListener.Stub {
        private final PanModeListener mListener;

        PanModeListenerStub(PanModeListener panModeListener) {
            this.mListener = panModeListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object lambda$onPanModeChanged$0(boolean z) {
            this.mListener.onPanModeChanged(z);
            return null;
        }

        @Override // androidx.car.app.navigation.model.IPanModeListener
        public void onPanModeChanged(final boolean z, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(iOnDoneCallback, "onPanModeChanged", new RemoteUtils.HostCall() { // from class: androidx.car.app.navigation.model.b
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    Object lambda$onPanModeChanged$0;
                    lambda$onPanModeChanged$0 = PanModeDelegateImpl.PanModeListenerStub.this.lambda$onPanModeChanged$0(z);
                    return lambda$onPanModeChanged$0;
                }
            });
        }
    }

    private PanModeDelegateImpl() {
        this.mStub = null;
    }

    private PanModeDelegateImpl(@NonNull PanModeListener panModeListener) {
        this.mStub = new PanModeListenerStub(panModeListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @SuppressLint({"ExecutorRegistration"})
    public static PanModeDelegate create(@NonNull PanModeListener panModeListener) {
        return new PanModeDelegateImpl(panModeListener);
    }

    @Override // androidx.car.app.navigation.model.PanModeDelegate
    public void sendPanModeChanged(boolean z, @NonNull @NotNull OnDoneCallback onDoneCallback) {
        try {
            IPanModeListener iPanModeListener = this.mStub;
            Objects.requireNonNull(iPanModeListener);
            iPanModeListener.onPanModeChanged(z, RemoteUtils.createOnDoneCallbackStub(onDoneCallback));
        } catch (RemoteException e2) {
            throw new RuntimeException(e2);
        }
    }
}
