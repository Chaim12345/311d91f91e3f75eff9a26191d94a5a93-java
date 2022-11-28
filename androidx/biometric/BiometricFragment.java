package androidx.biometric;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.biometric.BiometricPrompt;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class BiometricFragment extends Fragment {
    private static final int DISMISS_INSTANTLY_DELAY_MS = 500;
    private static final String FINGERPRINT_DIALOG_FRAGMENT_TAG = "androidx.biometric.FingerprintDialogFragment";
    private static final int HIDE_DIALOG_DELAY_MS = 2000;
    private static final int REQUEST_CONFIRM_CREDENTIAL = 1;
    private static final int SHOW_PROMPT_DELAY_MS = 600;
    private static final String TAG = "BiometricFragment";
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    Handler f630a = new Handler(Looper.getMainLooper());
    @VisibleForTesting

    /* renamed from: b  reason: collision with root package name */
    BiometricViewModel f631b;

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(21)
    /* loaded from: classes.dex */
    public static class Api21Impl {
        private Api21Impl() {
        }

        @Nullable
        static Intent a(@NonNull KeyguardManager keyguardManager, @Nullable CharSequence charSequence, @Nullable CharSequence charSequence2) {
            return keyguardManager.createConfirmDeviceCredentialIntent(charSequence, charSequence2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(28)
    /* loaded from: classes.dex */
    public static class Api28Impl {
        private Api28Impl() {
        }

        static void a(@NonNull android.hardware.biometrics.BiometricPrompt biometricPrompt, @NonNull BiometricPrompt.CryptoObject cryptoObject, @NonNull CancellationSignal cancellationSignal, @NonNull Executor executor, @NonNull BiometricPrompt.AuthenticationCallback authenticationCallback) {
            biometricPrompt.authenticate(cryptoObject, cancellationSignal, executor, authenticationCallback);
        }

        static void b(@NonNull android.hardware.biometrics.BiometricPrompt biometricPrompt, @NonNull CancellationSignal cancellationSignal, @NonNull Executor executor, @NonNull BiometricPrompt.AuthenticationCallback authenticationCallback) {
            biometricPrompt.authenticate(cancellationSignal, executor, authenticationCallback);
        }

        @NonNull
        static android.hardware.biometrics.BiometricPrompt c(@NonNull BiometricPrompt.Builder builder) {
            return builder.build();
        }

        @NonNull
        static BiometricPrompt.Builder d(@NonNull Context context) {
            return new BiometricPrompt.Builder(context);
        }

        static void e(@NonNull BiometricPrompt.Builder builder, @NonNull CharSequence charSequence) {
            builder.setDescription(charSequence);
        }

        static void f(@NonNull BiometricPrompt.Builder builder, @NonNull CharSequence charSequence, @NonNull Executor executor, @NonNull DialogInterface.OnClickListener onClickListener) {
            builder.setNegativeButton(charSequence, executor, onClickListener);
        }

        static void g(@NonNull BiometricPrompt.Builder builder, @NonNull CharSequence charSequence) {
            builder.setSubtitle(charSequence);
        }

        static void h(@NonNull BiometricPrompt.Builder builder, @NonNull CharSequence charSequence) {
            builder.setTitle(charSequence);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(29)
    /* loaded from: classes.dex */
    public static class Api29Impl {
        private Api29Impl() {
        }

        static void a(@NonNull BiometricPrompt.Builder builder, boolean z) {
            builder.setConfirmationRequired(z);
        }

        static void b(@NonNull BiometricPrompt.Builder builder, boolean z) {
            builder.setDeviceCredentialAllowed(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(30)
    /* loaded from: classes.dex */
    public static class Api30Impl {
        private Api30Impl() {
        }

        static void a(@NonNull BiometricPrompt.Builder builder, int i2) {
            builder.setAllowedAuthenticators(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class PromptExecutor implements Executor {
        private final Handler mPromptHandler = new Handler(Looper.getMainLooper());

        PromptExecutor() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable runnable) {
            this.mPromptHandler.post(runnable);
        }
    }

    /* loaded from: classes.dex */
    private static class ShowPromptForAuthenticationRunnable implements Runnable {
        @NonNull
        private final WeakReference<BiometricFragment> mFragmentRef;

        ShowPromptForAuthenticationRunnable(@Nullable BiometricFragment biometricFragment) {
            this.mFragmentRef = new WeakReference<>(biometricFragment);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mFragmentRef.get() != null) {
                this.mFragmentRef.get().n();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class StopDelayingPromptRunnable implements Runnable {
        @NonNull
        private final WeakReference<BiometricViewModel> mViewModelRef;

        StopDelayingPromptRunnable(@Nullable BiometricViewModel biometricViewModel) {
            this.mViewModelRef = new WeakReference<>(biometricViewModel);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mViewModelRef.get() != null) {
                this.mViewModelRef.get().O(false);
            }
        }
    }

    /* loaded from: classes.dex */
    private static class StopIgnoringCancelRunnable implements Runnable {
        @NonNull
        private final WeakReference<BiometricViewModel> mViewModelRef;

        StopIgnoringCancelRunnable(@Nullable BiometricViewModel biometricViewModel) {
            this.mViewModelRef = new WeakReference<>(biometricViewModel);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mViewModelRef.get() != null) {
                this.mViewModelRef.get().U(false);
            }
        }
    }

    private static int checkForFingerprintPreAuthenticationErrors(FingerprintManagerCompat fingerprintManagerCompat) {
        if (fingerprintManagerCompat.isHardwareDetected()) {
            return !fingerprintManagerCompat.hasEnrolledFingerprints() ? 11 : 0;
        }
        return 12;
    }

    private void connectViewModel() {
        if (getActivity() == null) {
            return;
        }
        BiometricViewModel biometricViewModel = (BiometricViewModel) new ViewModelProvider(getActivity()).get(BiometricViewModel.class);
        this.f631b = biometricViewModel;
        biometricViewModel.e().observe(this, new Observer<BiometricPrompt.AuthenticationResult>() { // from class: androidx.biometric.BiometricFragment.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(BiometricPrompt.AuthenticationResult authenticationResult) {
                if (authenticationResult != null) {
                    BiometricFragment.this.j(authenticationResult);
                    BiometricFragment.this.f631b.H(null);
                }
            }
        });
        this.f631b.c().observe(this, new Observer<BiometricErrorData>() { // from class: androidx.biometric.BiometricFragment.2
            @Override // androidx.lifecycle.Observer
            public void onChanged(BiometricErrorData biometricErrorData) {
                if (biometricErrorData != null) {
                    BiometricFragment.this.g(biometricErrorData.a(), biometricErrorData.b());
                    BiometricFragment.this.f631b.E(null);
                }
            }
        });
        this.f631b.d().observe(this, new Observer<CharSequence>() { // from class: androidx.biometric.BiometricFragment.3
            @Override // androidx.lifecycle.Observer
            public void onChanged(CharSequence charSequence) {
                if (charSequence != null) {
                    BiometricFragment.this.i(charSequence);
                    BiometricFragment.this.f631b.E(null);
                }
            }
        });
        this.f631b.t().observe(this, new Observer<Boolean>() { // from class: androidx.biometric.BiometricFragment.4
            @Override // androidx.lifecycle.Observer
            public void onChanged(Boolean bool) {
                if (bool.booleanValue()) {
                    BiometricFragment.this.h();
                    BiometricFragment.this.f631b.F(false);
                }
            }
        });
        this.f631b.B().observe(this, new Observer<Boolean>() { // from class: androidx.biometric.BiometricFragment.5
            @Override // androidx.lifecycle.Observer
            public void onChanged(Boolean bool) {
                if (bool.booleanValue()) {
                    if (BiometricFragment.this.e()) {
                        BiometricFragment.this.l();
                    } else {
                        BiometricFragment.this.k();
                    }
                    BiometricFragment.this.f631b.V(false);
                }
            }
        });
        this.f631b.y().observe(this, new Observer<Boolean>() { // from class: androidx.biometric.BiometricFragment.6
            @Override // androidx.lifecycle.Observer
            public void onChanged(Boolean bool) {
                if (bool.booleanValue()) {
                    BiometricFragment.this.d(1);
                    BiometricFragment.this.dismiss();
                    BiometricFragment.this.f631b.P(false);
                }
            }
        });
    }

    private void dismissFingerprintDialog() {
        this.f631b.Y(false);
        if (isAdded()) {
            FragmentManager parentFragmentManager = getParentFragmentManager();
            FingerprintDialogFragment fingerprintDialogFragment = (FingerprintDialogFragment) parentFragmentManager.findFragmentByTag(FINGERPRINT_DIALOG_FRAGMENT_TAG);
            if (fingerprintDialogFragment != null) {
                if (fingerprintDialogFragment.isAdded()) {
                    fingerprintDialogFragment.dismissAllowingStateLoss();
                } else {
                    parentFragmentManager.beginTransaction().remove(fingerprintDialogFragment).commitAllowingStateLoss();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BiometricFragment f() {
        return new BiometricFragment();
    }

    private int getDismissDialogDelay() {
        Context context = getContext();
        return (context == null || !DeviceUtils.c(context, Build.MODEL)) ? 2000 : 0;
    }

    private void handleConfirmCredentialResult(int i2) {
        if (i2 == -1) {
            sendSuccessAndDismiss(new BiometricPrompt.AuthenticationResult(null, 1));
        } else {
            m(10, getString(R.string.generic_error_user_canceled));
        }
    }

    private boolean isChangingConfigurations() {
        FragmentActivity activity = getActivity();
        return activity != null && activity.isChangingConfigurations();
    }

    private boolean isFingerprintDialogNeededForCrypto() {
        FragmentActivity activity = getActivity();
        return (activity == null || this.f631b.j() == null || !DeviceUtils.d(activity, Build.MANUFACTURER, Build.MODEL)) ? false : true;
    }

    private boolean isFingerprintDialogNeededForErrorHandling() {
        return Build.VERSION.SDK_INT == 28 && !PackageUtils.a(getContext());
    }

    private boolean isUsingFingerprintDialog() {
        return Build.VERSION.SDK_INT < 28 || isFingerprintDialogNeededForCrypto() || isFingerprintDialogNeededForErrorHandling();
    }

    @RequiresApi(21)
    private void launchConfirmCredentialActivity() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Log.e(TAG, "Failed to check device credential. Client FragmentActivity not found.");
            return;
        }
        KeyguardManager a2 = KeyguardUtils.a(activity);
        if (a2 == null) {
            m(12, getString(R.string.generic_error_no_keyguard));
            return;
        }
        CharSequence s2 = this.f631b.s();
        CharSequence r2 = this.f631b.r();
        CharSequence k2 = this.f631b.k();
        if (r2 == null) {
            r2 = k2;
        }
        Intent a3 = Api21Impl.a(a2, s2, r2);
        if (a3 == null) {
            m(14, getString(R.string.generic_error_no_device_credential));
            return;
        }
        this.f631b.M(true);
        if (isUsingFingerprintDialog()) {
            dismissFingerprintDialog();
        }
        a3.setFlags(134742016);
        startActivityForResult(a3, 1);
    }

    private void sendErrorToClient(final int i2, @NonNull final CharSequence charSequence) {
        if (!this.f631b.w() && this.f631b.u()) {
            this.f631b.I(false);
            this.f631b.i().execute(new Runnable() { // from class: androidx.biometric.BiometricFragment.10
                @Override // java.lang.Runnable
                public void run() {
                    BiometricFragment.this.f631b.h().onAuthenticationError(i2, charSequence);
                }
            });
        }
    }

    private void sendFailureToClient() {
        if (this.f631b.u()) {
            this.f631b.i().execute(new Runnable() { // from class: androidx.biometric.BiometricFragment.11
                @Override // java.lang.Runnable
                public void run() {
                    BiometricFragment.this.f631b.h().onAuthenticationFailed();
                }
            });
        }
    }

    private void sendSuccessAndDismiss(@NonNull BiometricPrompt.AuthenticationResult authenticationResult) {
        sendSuccessToClient(authenticationResult);
        dismiss();
    }

    private void sendSuccessToClient(@NonNull final BiometricPrompt.AuthenticationResult authenticationResult) {
        if (this.f631b.u()) {
            this.f631b.I(false);
            this.f631b.i().execute(new Runnable() { // from class: androidx.biometric.BiometricFragment.9
                @Override // java.lang.Runnable
                public void run() {
                    BiometricFragment.this.f631b.h().onAuthenticationSucceeded(authenticationResult);
                }
            });
        }
    }

    @RequiresApi(28)
    private void showBiometricPromptForAuthentication() {
        BiometricPrompt.Builder d2 = Api28Impl.d(requireContext().getApplicationContext());
        CharSequence s2 = this.f631b.s();
        CharSequence r2 = this.f631b.r();
        CharSequence k2 = this.f631b.k();
        if (s2 != null) {
            Api28Impl.h(d2, s2);
        }
        if (r2 != null) {
            Api28Impl.g(d2, r2);
        }
        if (k2 != null) {
            Api28Impl.e(d2, k2);
        }
        CharSequence q2 = this.f631b.q();
        if (!TextUtils.isEmpty(q2)) {
            Api28Impl.f(d2, q2, this.f631b.i(), this.f631b.p());
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            Api29Impl.a(d2, this.f631b.v());
        }
        int a2 = this.f631b.a();
        if (i2 >= 30) {
            Api30Impl.a(d2, a2);
        } else if (i2 >= 29) {
            Api29Impl.b(d2, AuthenticatorUtils.c(a2));
        }
        b(Api28Impl.c(d2), getContext());
    }

    private void showFingerprintDialogForAuthentication() {
        Context applicationContext = requireContext().getApplicationContext();
        FingerprintManagerCompat from = FingerprintManagerCompat.from(applicationContext);
        int checkForFingerprintPreAuthenticationErrors = checkForFingerprintPreAuthenticationErrors(from);
        if (checkForFingerprintPreAuthenticationErrors != 0) {
            m(checkForFingerprintPreAuthenticationErrors, ErrorUtils.a(applicationContext, checkForFingerprintPreAuthenticationErrors));
        } else if (isAdded()) {
            this.f631b.Q(true);
            if (!DeviceUtils.c(applicationContext, Build.MODEL)) {
                this.f630a.postDelayed(new Runnable() { // from class: androidx.biometric.BiometricFragment.7
                    @Override // java.lang.Runnable
                    public void run() {
                        BiometricFragment.this.f631b.Q(false);
                    }
                }, 500L);
                FingerprintDialogFragment.f().show(getParentFragmentManager(), FINGERPRINT_DIALOG_FRAGMENT_TAG);
            }
            this.f631b.J(0);
            c(from, applicationContext);
        }
    }

    private void showFingerprintErrorMessage(@Nullable CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = getString(R.string.default_error_msg);
        }
        this.f631b.T(2);
        this.f631b.R(charSequence);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(@NonNull BiometricPrompt.PromptInfo promptInfo, @Nullable BiometricPrompt.CryptoObject cryptoObject) {
        BiometricViewModel biometricViewModel;
        BiometricViewModel biometricViewModel2;
        String str;
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Log.e(TAG, "Not launching prompt. Client activity was null.");
            return;
        }
        this.f631b.X(promptInfo);
        int b2 = AuthenticatorUtils.b(promptInfo, cryptoObject);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 23 || i2 >= 30 || b2 != 15 || cryptoObject != null) {
            biometricViewModel = this.f631b;
        } else {
            biometricViewModel = this.f631b;
            cryptoObject = CryptoObjectUtils.a();
        }
        biometricViewModel.N(cryptoObject);
        if (e()) {
            biometricViewModel2 = this.f631b;
            str = getString(R.string.confirm_device_credential_password);
        } else {
            biometricViewModel2 = this.f631b;
            str = null;
        }
        biometricViewModel2.W(str);
        if (i2 >= 21 && e() && BiometricManager.from(activity).canAuthenticate(255) != 0) {
            this.f631b.I(true);
            launchConfirmCredentialActivity();
        } else if (this.f631b.x()) {
            this.f630a.postDelayed(new ShowPromptForAuthenticationRunnable(this), 600L);
        } else {
            n();
        }
    }

    @RequiresApi(28)
    @VisibleForTesting
    void b(@NonNull android.hardware.biometrics.BiometricPrompt biometricPrompt, @Nullable Context context) {
        BiometricPrompt.CryptoObject d2 = CryptoObjectUtils.d(this.f631b.j());
        CancellationSignal b2 = this.f631b.g().b();
        PromptExecutor promptExecutor = new PromptExecutor();
        BiometricPrompt.AuthenticationCallback a2 = this.f631b.b().a();
        try {
            if (d2 == null) {
                Api28Impl.b(biometricPrompt, b2, promptExecutor, a2);
            } else {
                Api28Impl.a(biometricPrompt, d2, b2, promptExecutor, a2);
            }
        } catch (NullPointerException e2) {
            Log.e(TAG, "Got NPE while authenticating with biometric prompt.", e2);
            m(1, context != null ? context.getString(R.string.default_error_msg) : "");
        }
    }

    @VisibleForTesting
    void c(@NonNull FingerprintManagerCompat fingerprintManagerCompat, @NonNull Context context) {
        try {
            fingerprintManagerCompat.authenticate(CryptoObjectUtils.e(this.f631b.j()), 0, this.f631b.g().c(), this.f631b.b().b(), null);
        } catch (NullPointerException e2) {
            Log.e(TAG, "Got NPE while authenticating with fingerprint.", e2);
            m(1, ErrorUtils.a(context, 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(int i2) {
        if (i2 == 3 || !this.f631b.A()) {
            if (isUsingFingerprintDialog()) {
                this.f631b.J(i2);
                if (i2 == 1) {
                    sendErrorToClient(10, ErrorUtils.a(getContext(), 10));
                }
            }
            this.f631b.g().a();
        }
    }

    void dismiss() {
        this.f631b.Y(false);
        dismissFingerprintDialog();
        if (!this.f631b.w() && isAdded()) {
            getParentFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
        }
        Context context = getContext();
        if (context == null || !DeviceUtils.b(context, Build.MODEL)) {
            return;
        }
        this.f631b.O(true);
        this.f630a.postDelayed(new StopDelayingPromptRunnable(this.f631b), 600L);
    }

    boolean e() {
        return Build.VERSION.SDK_INT <= 28 && AuthenticatorUtils.c(this.f631b.a());
    }

    @VisibleForTesting
    void g(final int i2, @Nullable final CharSequence charSequence) {
        if (!ErrorUtils.b(i2)) {
            i2 = 8;
        }
        Context context = getContext();
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 21 && i3 < 29 && ErrorUtils.c(i2) && context != null && KeyguardUtils.b(context) && AuthenticatorUtils.c(this.f631b.a())) {
            launchConfirmCredentialActivity();
        } else if (!isUsingFingerprintDialog()) {
            if (charSequence == null) {
                charSequence = getString(R.string.default_error_msg) + " " + i2;
            }
            m(i2, charSequence);
        } else {
            if (charSequence == null) {
                charSequence = ErrorUtils.a(getContext(), i2);
            }
            if (i2 == 5) {
                int f2 = this.f631b.f();
                if (f2 == 0 || f2 == 3) {
                    sendErrorToClient(i2, charSequence);
                }
                dismiss();
                return;
            }
            if (this.f631b.z()) {
                m(i2, charSequence);
            } else {
                showFingerprintErrorMessage(charSequence);
                this.f630a.postDelayed(new Runnable() { // from class: androidx.biometric.BiometricFragment.8
                    @Override // java.lang.Runnable
                    public void run() {
                        BiometricFragment.this.m(i2, charSequence);
                    }
                }, getDismissDialogDelay());
            }
            this.f631b.Q(true);
        }
    }

    void h() {
        if (isUsingFingerprintDialog()) {
            showFingerprintErrorMessage(getString(R.string.fingerprint_not_recognized));
        }
        sendFailureToClient();
    }

    void i(@NonNull CharSequence charSequence) {
        if (isUsingFingerprintDialog()) {
            showFingerprintErrorMessage(charSequence);
        }
    }

    @VisibleForTesting
    void j(@NonNull BiometricPrompt.AuthenticationResult authenticationResult) {
        sendSuccessAndDismiss(authenticationResult);
    }

    void k() {
        CharSequence q2 = this.f631b.q();
        if (q2 == null) {
            q2 = getString(R.string.default_error_msg);
        }
        m(13, q2);
        d(2);
    }

    void l() {
        if (Build.VERSION.SDK_INT < 21) {
            Log.e(TAG, "Failed to check device credential. Not supported prior to API 21.");
        } else {
            launchConfirmCredentialActivity();
        }
    }

    void m(int i2, @NonNull CharSequence charSequence) {
        sendErrorToClient(i2, charSequence);
        dismiss();
    }

    void n() {
        if (this.f631b.C() || getContext() == null) {
            return;
        }
        this.f631b.Y(true);
        this.f631b.I(true);
        if (isUsingFingerprintDialog()) {
            showFingerprintDialogForAuthentication();
        } else {
            showBiometricPromptForAuthentication();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 1) {
            this.f631b.M(false);
            handleConfirmCredentialResult(i3);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        connectViewModel();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT == 29 && AuthenticatorUtils.c(this.f631b.a())) {
            this.f631b.U(true);
            this.f630a.postDelayed(new StopIgnoringCancelRunnable(this.f631b), 250L);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        if (Build.VERSION.SDK_INT >= 29 || this.f631b.w() || isChangingConfigurations()) {
            return;
        }
        d(0);
    }
}
