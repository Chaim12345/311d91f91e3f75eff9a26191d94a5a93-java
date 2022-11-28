package androidx.biometric;

import android.os.Build;
import android.os.CancellationSignal;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes.dex */
class CancellationSignalProvider {
    private static final String TAG = "CancelSignalProvider";
    @Nullable
    private CancellationSignal mBiometricCancellationSignal;
    @Nullable
    private androidx.core.os.CancellationSignal mFingerprintCancellationSignal;
    private final Injector mInjector = new Injector(this) { // from class: androidx.biometric.CancellationSignalProvider.1
        @Override // androidx.biometric.CancellationSignalProvider.Injector
        @NonNull
        @RequiresApi(16)
        public CancellationSignal getBiometricCancellationSignal() {
            return Api16Impl.b();
        }

        @Override // androidx.biometric.CancellationSignalProvider.Injector
        @NonNull
        public androidx.core.os.CancellationSignal getFingerprintCancellationSignal() {
            return new androidx.core.os.CancellationSignal();
        }
    };

    @RequiresApi(16)
    /* loaded from: classes.dex */
    private static class Api16Impl {
        private Api16Impl() {
        }

        static void a(CancellationSignal cancellationSignal) {
            cancellationSignal.cancel();
        }

        static CancellationSignal b() {
            return new CancellationSignal();
        }
    }

    @VisibleForTesting
    /* loaded from: classes.dex */
    interface Injector {
        @NonNull
        @RequiresApi(16)
        CancellationSignal getBiometricCancellationSignal();

        @NonNull
        androidx.core.os.CancellationSignal getFingerprintCancellationSignal();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        CancellationSignal cancellationSignal;
        if (Build.VERSION.SDK_INT >= 16 && (cancellationSignal = this.mBiometricCancellationSignal) != null) {
            try {
                Api16Impl.a(cancellationSignal);
            } catch (NullPointerException e2) {
                Log.e(TAG, "Got NPE while canceling biometric authentication.", e2);
            }
            this.mBiometricCancellationSignal = null;
        }
        androidx.core.os.CancellationSignal cancellationSignal2 = this.mFingerprintCancellationSignal;
        if (cancellationSignal2 != null) {
            try {
                cancellationSignal2.cancel();
            } catch (NullPointerException e3) {
                Log.e(TAG, "Got NPE while canceling fingerprint authentication.", e3);
            }
            this.mFingerprintCancellationSignal = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @RequiresApi(16)
    public CancellationSignal b() {
        if (this.mBiometricCancellationSignal == null) {
            this.mBiometricCancellationSignal = this.mInjector.getBiometricCancellationSignal();
        }
        return this.mBiometricCancellationSignal;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public androidx.core.os.CancellationSignal c() {
        if (this.mFingerprintCancellationSignal == null) {
            this.mFingerprintCancellationSignal = this.mInjector.getFingerprintCancellationSignal();
        }
        return this.mFingerprintCancellationSignal;
    }
}
