package androidx.biometric;

import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.biometric.BiometricPrompt;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class AuthenticationCallbackProvider {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    final Listener f627a;
    @Nullable
    private BiometricPrompt.AuthenticationCallback mBiometricCallback;
    @Nullable
    private FingerprintManagerCompat.AuthenticationCallback mFingerprintCallback;

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(28)
    /* loaded from: classes.dex */
    public static class Api28Impl {
        private Api28Impl() {
        }

        @NonNull
        static BiometricPrompt.AuthenticationCallback a(@NonNull final Listener listener) {
            return new BiometricPrompt.AuthenticationCallback() { // from class: androidx.biometric.AuthenticationCallbackProvider.Api28Impl.1
                @Override // android.hardware.biometrics.BiometricPrompt.AuthenticationCallback
                public void onAuthenticationError(int i2, CharSequence charSequence) {
                    Listener.this.a(i2, charSequence);
                }

                @Override // android.hardware.biometrics.BiometricPrompt.AuthenticationCallback
                public void onAuthenticationFailed() {
                    Listener.this.b();
                }

                @Override // android.hardware.biometrics.BiometricPrompt.AuthenticationCallback
                public void onAuthenticationHelp(int i2, CharSequence charSequence) {
                }

                @Override // android.hardware.biometrics.BiometricPrompt.AuthenticationCallback
                public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult authenticationResult) {
                    BiometricPrompt.CryptoObject b2 = authenticationResult != null ? CryptoObjectUtils.b(authenticationResult.getCryptoObject()) : null;
                    int i2 = Build.VERSION.SDK_INT;
                    int i3 = -1;
                    if (i2 >= 30) {
                        if (authenticationResult != null) {
                            i3 = Api30Impl.a(authenticationResult);
                        }
                    } else if (i2 != 29) {
                        i3 = 2;
                    }
                    Listener.this.d(new BiometricPrompt.AuthenticationResult(b2, i3));
                }
            };
        }
    }

    @RequiresApi(30)
    /* loaded from: classes.dex */
    private static class Api30Impl {
        private Api30Impl() {
        }

        static int a(@NonNull BiometricPrompt.AuthenticationResult authenticationResult) {
            return authenticationResult.getAuthenticationType();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Listener {
        void a(int i2, @Nullable CharSequence charSequence) {
        }

        void b() {
        }

        void c(@Nullable CharSequence charSequence) {
        }

        void d(@NonNull BiometricPrompt.AuthenticationResult authenticationResult) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AuthenticationCallbackProvider(@NonNull Listener listener) {
        this.f627a = listener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @RequiresApi(28)
    public BiometricPrompt.AuthenticationCallback a() {
        if (this.mBiometricCallback == null) {
            this.mBiometricCallback = Api28Impl.a(this.f627a);
        }
        return this.mBiometricCallback;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public FingerprintManagerCompat.AuthenticationCallback b() {
        if (this.mFingerprintCallback == null) {
            this.mFingerprintCallback = new FingerprintManagerCompat.AuthenticationCallback() { // from class: androidx.biometric.AuthenticationCallbackProvider.1
                @Override // androidx.core.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback
                public void onAuthenticationError(int i2, CharSequence charSequence) {
                    AuthenticationCallbackProvider.this.f627a.a(i2, charSequence);
                }

                @Override // androidx.core.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback
                public void onAuthenticationFailed() {
                    AuthenticationCallbackProvider.this.f627a.b();
                }

                @Override // androidx.core.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback
                public void onAuthenticationHelp(int i2, CharSequence charSequence) {
                    AuthenticationCallbackProvider.this.f627a.c(charSequence);
                }

                @Override // androidx.core.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback
                public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult authenticationResult) {
                    AuthenticationCallbackProvider.this.f627a.d(new BiometricPrompt.AuthenticationResult(authenticationResult != null ? CryptoObjectUtils.c(authenticationResult.getCryptoObject()) : null, 2));
                }
            };
        }
        return this.mFingerprintCallback;
    }
}
