package androidx.biometric;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.biometric.AuthenticationCallbackProvider;
import androidx.biometric.BiometricPrompt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class BiometricViewModel extends ViewModel {
    @Nullable
    private AuthenticationCallbackProvider mAuthenticationCallbackProvider;
    @Nullable
    private MutableLiveData<BiometricErrorData> mAuthenticationError;
    @Nullable
    private MutableLiveData<CharSequence> mAuthenticationHelpMessage;
    @Nullable
    private MutableLiveData<BiometricPrompt.AuthenticationResult> mAuthenticationResult;
    @Nullable
    private CancellationSignalProvider mCancellationSignalProvider;
    @Nullable
    private BiometricPrompt.AuthenticationCallback mClientCallback;
    @Nullable
    private Executor mClientExecutor;
    @Nullable
    private BiometricPrompt.CryptoObject mCryptoObject;
    @Nullable
    private MutableLiveData<CharSequence> mFingerprintDialogHelpMessage;
    @Nullable
    private MutableLiveData<Integer> mFingerprintDialogState;
    @Nullable
    private MutableLiveData<Boolean> mIsAuthenticationFailurePending;
    private boolean mIsAwaitingResult;
    private boolean mIsConfirmingDeviceCredential;
    private boolean mIsDelayingPrompt;
    @Nullable
    private MutableLiveData<Boolean> mIsFingerprintDialogCancelPending;
    private boolean mIsIgnoringCancel;
    @Nullable
    private MutableLiveData<Boolean> mIsNegativeButtonPressPending;
    private boolean mIsPromptShowing;
    @Nullable
    private DialogInterface.OnClickListener mNegativeButtonListener;
    @Nullable
    private CharSequence mNegativeButtonTextOverride;
    @Nullable
    private BiometricPrompt.PromptInfo mPromptInfo;
    private int mCanceledFrom = 0;
    private boolean mIsFingerprintDialogDismissedInstantly = true;
    private int mFingerprintDialogPreviousState = 0;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class CallbackListener extends AuthenticationCallbackProvider.Listener {
        @NonNull
        private final WeakReference<BiometricViewModel> mViewModelRef;

        CallbackListener(@Nullable BiometricViewModel biometricViewModel) {
            this.mViewModelRef = new WeakReference<>(biometricViewModel);
        }

        @Override // androidx.biometric.AuthenticationCallbackProvider.Listener
        void a(int i2, @Nullable CharSequence charSequence) {
            if (this.mViewModelRef.get() == null || this.mViewModelRef.get().w() || !this.mViewModelRef.get().u()) {
                return;
            }
            this.mViewModelRef.get().E(new BiometricErrorData(i2, charSequence));
        }

        @Override // androidx.biometric.AuthenticationCallbackProvider.Listener
        void b() {
            if (this.mViewModelRef.get() == null || !this.mViewModelRef.get().u()) {
                return;
            }
            this.mViewModelRef.get().F(true);
        }

        @Override // androidx.biometric.AuthenticationCallbackProvider.Listener
        void c(@Nullable CharSequence charSequence) {
            if (this.mViewModelRef.get() != null) {
                this.mViewModelRef.get().G(charSequence);
            }
        }

        @Override // androidx.biometric.AuthenticationCallbackProvider.Listener
        void d(@NonNull BiometricPrompt.AuthenticationResult authenticationResult) {
            if (this.mViewModelRef.get() == null || !this.mViewModelRef.get().u()) {
                return;
            }
            if (authenticationResult.getAuthenticationType() == -1) {
                authenticationResult = new BiometricPrompt.AuthenticationResult(authenticationResult.getCryptoObject(), this.mViewModelRef.get().o());
            }
            this.mViewModelRef.get().H(authenticationResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class DefaultExecutor implements Executor {
        private final Handler mHandler = new Handler(Looper.getMainLooper());

        DefaultExecutor() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.mHandler.post(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class NegativeButtonListener implements DialogInterface.OnClickListener {
        @NonNull
        private final WeakReference<BiometricViewModel> mViewModelRef;

        NegativeButtonListener(@Nullable BiometricViewModel biometricViewModel) {
            this.mViewModelRef = new WeakReference<>(biometricViewModel);
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            if (this.mViewModelRef.get() != null) {
                this.mViewModelRef.get().V(true);
            }
        }
    }

    private static <T> void updateValue(MutableLiveData<T> mutableLiveData, T t2) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            mutableLiveData.setValue(t2);
        } else {
            mutableLiveData.postValue(t2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean A() {
        return this.mIsIgnoringCancel;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public LiveData B() {
        if (this.mIsNegativeButtonPressPending == null) {
            this.mIsNegativeButtonPressPending = new MutableLiveData<>();
        }
        return this.mIsNegativeButtonPressPending;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean C() {
        return this.mIsPromptShowing;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void D() {
        this.mClientCallback = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void E(@Nullable BiometricErrorData biometricErrorData) {
        if (this.mAuthenticationError == null) {
            this.mAuthenticationError = new MutableLiveData<>();
        }
        updateValue(this.mAuthenticationError, biometricErrorData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void F(boolean z) {
        if (this.mIsAuthenticationFailurePending == null) {
            this.mIsAuthenticationFailurePending = new MutableLiveData<>();
        }
        updateValue(this.mIsAuthenticationFailurePending, Boolean.valueOf(z));
    }

    void G(@Nullable CharSequence charSequence) {
        if (this.mAuthenticationHelpMessage == null) {
            this.mAuthenticationHelpMessage = new MutableLiveData<>();
        }
        updateValue(this.mAuthenticationHelpMessage, charSequence);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void H(@Nullable BiometricPrompt.AuthenticationResult authenticationResult) {
        if (this.mAuthenticationResult == null) {
            this.mAuthenticationResult = new MutableLiveData<>();
        }
        updateValue(this.mAuthenticationResult, authenticationResult);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void I(boolean z) {
        this.mIsAwaitingResult = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void J(int i2) {
        this.mCanceledFrom = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void K(@NonNull BiometricPrompt.AuthenticationCallback authenticationCallback) {
        this.mClientCallback = authenticationCallback;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void L(@NonNull Executor executor) {
        this.mClientExecutor = executor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void M(boolean z) {
        this.mIsConfirmingDeviceCredential = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void N(@Nullable BiometricPrompt.CryptoObject cryptoObject) {
        this.mCryptoObject = cryptoObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void O(boolean z) {
        this.mIsDelayingPrompt = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void P(boolean z) {
        if (this.mIsFingerprintDialogCancelPending == null) {
            this.mIsFingerprintDialogCancelPending = new MutableLiveData<>();
        }
        updateValue(this.mIsFingerprintDialogCancelPending, Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void Q(boolean z) {
        this.mIsFingerprintDialogDismissedInstantly = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void R(@NonNull CharSequence charSequence) {
        if (this.mFingerprintDialogHelpMessage == null) {
            this.mFingerprintDialogHelpMessage = new MutableLiveData<>();
        }
        updateValue(this.mFingerprintDialogHelpMessage, charSequence);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void S(int i2) {
        this.mFingerprintDialogPreviousState = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void T(int i2) {
        if (this.mFingerprintDialogState == null) {
            this.mFingerprintDialogState = new MutableLiveData<>();
        }
        updateValue(this.mFingerprintDialogState, Integer.valueOf(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void U(boolean z) {
        this.mIsIgnoringCancel = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void V(boolean z) {
        if (this.mIsNegativeButtonPressPending == null) {
            this.mIsNegativeButtonPressPending = new MutableLiveData<>();
        }
        updateValue(this.mIsNegativeButtonPressPending, Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void W(@Nullable CharSequence charSequence) {
        this.mNegativeButtonTextOverride = charSequence;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void X(@Nullable BiometricPrompt.PromptInfo promptInfo) {
        this.mPromptInfo = promptInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void Y(boolean z) {
        this.mIsPromptShowing = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        BiometricPrompt.PromptInfo promptInfo = this.mPromptInfo;
        if (promptInfo != null) {
            return AuthenticatorUtils.b(promptInfo, this.mCryptoObject);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public AuthenticationCallbackProvider b() {
        if (this.mAuthenticationCallbackProvider == null) {
            this.mAuthenticationCallbackProvider = new AuthenticationCallbackProvider(new CallbackListener(this));
        }
        return this.mAuthenticationCallbackProvider;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public MutableLiveData c() {
        if (this.mAuthenticationError == null) {
            this.mAuthenticationError = new MutableLiveData<>();
        }
        return this.mAuthenticationError;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public LiveData d() {
        if (this.mAuthenticationHelpMessage == null) {
            this.mAuthenticationHelpMessage = new MutableLiveData<>();
        }
        return this.mAuthenticationHelpMessage;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public LiveData e() {
        if (this.mAuthenticationResult == null) {
            this.mAuthenticationResult = new MutableLiveData<>();
        }
        return this.mAuthenticationResult;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f() {
        return this.mCanceledFrom;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public CancellationSignalProvider g() {
        if (this.mCancellationSignalProvider == null) {
            this.mCancellationSignalProvider = new CancellationSignalProvider();
        }
        return this.mCancellationSignalProvider;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public BiometricPrompt.AuthenticationCallback h() {
        if (this.mClientCallback == null) {
            this.mClientCallback = new BiometricPrompt.AuthenticationCallback(this) { // from class: androidx.biometric.BiometricViewModel.1
            };
        }
        return this.mClientCallback;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public Executor i() {
        Executor executor = this.mClientExecutor;
        return executor != null ? executor : new DefaultExecutor();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public BiometricPrompt.CryptoObject j() {
        return this.mCryptoObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public CharSequence k() {
        BiometricPrompt.PromptInfo promptInfo = this.mPromptInfo;
        if (promptInfo != null) {
            return promptInfo.getDescription();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public LiveData l() {
        if (this.mFingerprintDialogHelpMessage == null) {
            this.mFingerprintDialogHelpMessage = new MutableLiveData<>();
        }
        return this.mFingerprintDialogHelpMessage;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int m() {
        return this.mFingerprintDialogPreviousState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public LiveData n() {
        if (this.mFingerprintDialogState == null) {
            this.mFingerprintDialogState = new MutableLiveData<>();
        }
        return this.mFingerprintDialogState;
    }

    int o() {
        int a2 = a();
        return (!AuthenticatorUtils.d(a2) || AuthenticatorUtils.c(a2)) ? -1 : 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public DialogInterface.OnClickListener p() {
        if (this.mNegativeButtonListener == null) {
            this.mNegativeButtonListener = new NegativeButtonListener(this);
        }
        return this.mNegativeButtonListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public CharSequence q() {
        CharSequence charSequence = this.mNegativeButtonTextOverride;
        if (charSequence != null) {
            return charSequence;
        }
        BiometricPrompt.PromptInfo promptInfo = this.mPromptInfo;
        if (promptInfo != null) {
            return promptInfo.getNegativeButtonText();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public CharSequence r() {
        BiometricPrompt.PromptInfo promptInfo = this.mPromptInfo;
        if (promptInfo != null) {
            return promptInfo.getSubtitle();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public CharSequence s() {
        BiometricPrompt.PromptInfo promptInfo = this.mPromptInfo;
        if (promptInfo != null) {
            return promptInfo.getTitle();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public LiveData t() {
        if (this.mIsAuthenticationFailurePending == null) {
            this.mIsAuthenticationFailurePending = new MutableLiveData<>();
        }
        return this.mIsAuthenticationFailurePending;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean u() {
        return this.mIsAwaitingResult;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean v() {
        BiometricPrompt.PromptInfo promptInfo = this.mPromptInfo;
        return promptInfo == null || promptInfo.isConfirmationRequired();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean w() {
        return this.mIsConfirmingDeviceCredential;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean x() {
        return this.mIsDelayingPrompt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public LiveData y() {
        if (this.mIsFingerprintDialogCancelPending == null) {
            this.mIsFingerprintDialogCancelPending = new MutableLiveData<>();
        }
        return this.mIsFingerprintDialogCancelPending;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean z() {
        return this.mIsFingerprintDialogDismissedInstantly;
    }
}
