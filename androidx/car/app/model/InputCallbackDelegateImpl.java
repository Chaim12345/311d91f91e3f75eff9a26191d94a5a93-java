package androidx.car.app.model;

import android.annotation.SuppressLint;
import android.os.RemoteException;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.model.IInputCallback;
import androidx.car.app.model.InputCallbackDelegateImpl;
import androidx.car.app.utils.RemoteUtils;
import java.util.Objects;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class InputCallbackDelegateImpl implements InputCallbackDelegate {
    @Nullable
    @Keep
    private final IInputCallback mCallback;

    /* JADX INFO: Access modifiers changed from: private */
    @Keep
    /* loaded from: classes.dex */
    public static class OnInputCallbackStub extends IInputCallback.Stub {
        private final InputCallback mCallback;

        OnInputCallbackStub(InputCallback inputCallback) {
            this.mCallback = inputCallback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object lambda$onInputSubmitted$0(String str) {
            this.mCallback.onInputSubmitted(str);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object lambda$onInputTextChanged$1(String str) {
            this.mCallback.onInputTextChanged(str);
            return null;
        }

        @Override // androidx.car.app.model.IInputCallback
        public void onInputSubmitted(final String str, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(iOnDoneCallback, "onInputSubmitted", new RemoteUtils.HostCall() { // from class: androidx.car.app.model.b
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    Object lambda$onInputSubmitted$0;
                    lambda$onInputSubmitted$0 = InputCallbackDelegateImpl.OnInputCallbackStub.this.lambda$onInputSubmitted$0(str);
                    return lambda$onInputSubmitted$0;
                }
            });
        }

        @Override // androidx.car.app.model.IInputCallback
        public void onInputTextChanged(final String str, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(iOnDoneCallback, "onInputTextChanged", new RemoteUtils.HostCall() { // from class: androidx.car.app.model.a
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    Object lambda$onInputTextChanged$1;
                    lambda$onInputTextChanged$1 = InputCallbackDelegateImpl.OnInputCallbackStub.this.lambda$onInputTextChanged$1(str);
                    return lambda$onInputTextChanged$1;
                }
            });
        }
    }

    private InputCallbackDelegateImpl() {
        this.mCallback = null;
    }

    private InputCallbackDelegateImpl(@NonNull InputCallback inputCallback) {
        this.mCallback = new OnInputCallbackStub(inputCallback);
    }

    @NonNull
    @SuppressLint({"ExecutorRegistration"})
    public static InputCallbackDelegate create(@NonNull InputCallback inputCallback) {
        Objects.requireNonNull(inputCallback);
        return new InputCallbackDelegateImpl(inputCallback);
    }

    @Override // androidx.car.app.model.InputCallbackDelegate
    public void sendInputSubmitted(@NonNull String str, @NonNull OnDoneCallback onDoneCallback) {
        try {
            IInputCallback iInputCallback = this.mCallback;
            Objects.requireNonNull(iInputCallback);
            iInputCallback.onInputSubmitted(str, RemoteUtils.createOnDoneCallbackStub(onDoneCallback));
        } catch (RemoteException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // androidx.car.app.model.InputCallbackDelegate
    public void sendInputTextChanged(@NonNull String str, @NonNull OnDoneCallback onDoneCallback) {
        try {
            IInputCallback iInputCallback = this.mCallback;
            Objects.requireNonNull(iInputCallback);
            iInputCallback.onInputTextChanged(str, RemoteUtils.createOnDoneCallbackStub(onDoneCallback));
        } catch (RemoteException e2) {
            throw new RuntimeException(e2);
        }
    }
}
