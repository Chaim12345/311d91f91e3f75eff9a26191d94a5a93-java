package com.psa.mym.mycitroenconnect.controller.activities.onboarding;

import androidx.biometric.BiometricPrompt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.views.CustomToast;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class AppLockFingerprintActivity$initFingerPrint$biometricPrompt$1 extends BiometricPrompt.AuthenticationCallback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AppLockFingerprintActivity f10397a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppLockFingerprintActivity$initFingerPrint$biometricPrompt$1(AppLockFingerprintActivity appLockFingerprintActivity) {
        this.f10397a = appLockFingerprintActivity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onAuthenticationFailed$lambda-1  reason: not valid java name */
    public static final void m94onAuthenticationFailed$lambda1(AppLockFingerprintActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CustomToast.showErrorToast(this$0, this$0.getString(R.string.authentication_failed));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onAuthenticationSucceeded$lambda-0  reason: not valid java name */
    public static final void m95onAuthenticationSucceeded$lambda0(AppLockFingerprintActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runExitAnimation();
    }

    @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
    public void onAuthenticationError(int i2, @NotNull CharSequence errString) {
        Intrinsics.checkNotNullParameter(errString, "errString");
        super.onAuthenticationError(i2, errString);
        if (i2 != 13) {
            Logger logger = Logger.INSTANCE;
            logger.d("Unrecoverable error :" + ((Object) errString));
        }
    }

    @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        final AppLockFingerprintActivity appLockFingerprintActivity = this.f10397a;
        appLockFingerprintActivity.runOnUiThread(new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.b
            @Override // java.lang.Runnable
            public final void run() {
                AppLockFingerprintActivity$initFingerPrint$biometricPrompt$1.m94onAuthenticationFailed$lambda1(AppLockFingerprintActivity.this);
            }
        });
    }

    @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
    public void onAuthenticationSucceeded(@NotNull BiometricPrompt.AuthenticationResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        super.onAuthenticationSucceeded(result);
        final AppLockFingerprintActivity appLockFingerprintActivity = this.f10397a;
        appLockFingerprintActivity.runOnUiThread(new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.c
            @Override // java.lang.Runnable
            public final void run() {
                AppLockFingerprintActivity$initFingerPrint$biometricPrompt$1.m95onAuthenticationSucceeded$lambda0(AppLockFingerprintActivity.this);
            }
        });
    }
}
