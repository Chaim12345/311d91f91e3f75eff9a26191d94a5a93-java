package androidx.biometric;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayout;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class FingerprintDialogFragment extends DialogFragment {
    private static final int MESSAGE_DISPLAY_TIME_MS = 2000;
    private static final String TAG = "FingerprintFragment";

    /* renamed from: a  reason: collision with root package name */
    final Handler f648a = new Handler(Looper.getMainLooper());

    /* renamed from: b  reason: collision with root package name */
    final Runnable f649b = new Runnable() { // from class: androidx.biometric.FingerprintDialogFragment.1
        @Override // java.lang.Runnable
        public void run() {
            FingerprintDialogFragment.this.g();
        }
    };

    /* renamed from: c  reason: collision with root package name */
    BiometricViewModel f650c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    TextView f651d;
    private int mErrorTextColor;
    @Nullable
    private ImageView mFingerprintIcon;
    private int mNormalTextColor;

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(21)
    /* loaded from: classes.dex */
    public static class Api21Impl {
        private Api21Impl() {
        }

        static void a(@NonNull Drawable drawable) {
            if (drawable instanceof AnimatedVectorDrawable) {
                ((AnimatedVectorDrawable) drawable).start();
            }
        }
    }

    @RequiresApi(26)
    /* loaded from: classes.dex */
    private static class Api26Impl {
        private Api26Impl() {
        }

        static int a() {
            return R.attr.colorError;
        }
    }

    private void connectViewModel() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        BiometricViewModel biometricViewModel = (BiometricViewModel) new ViewModelProvider(activity).get(BiometricViewModel.class);
        this.f650c = biometricViewModel;
        biometricViewModel.n().observe(this, new Observer<Integer>() { // from class: androidx.biometric.FingerprintDialogFragment.3
            @Override // androidx.lifecycle.Observer
            public void onChanged(Integer num) {
                FingerprintDialogFragment fingerprintDialogFragment = FingerprintDialogFragment.this;
                fingerprintDialogFragment.f648a.removeCallbacks(fingerprintDialogFragment.f649b);
                FingerprintDialogFragment.this.h(num.intValue());
                FingerprintDialogFragment.this.i(num.intValue());
                FingerprintDialogFragment fingerprintDialogFragment2 = FingerprintDialogFragment.this;
                fingerprintDialogFragment2.f648a.postDelayed(fingerprintDialogFragment2.f649b, SkeletonLayout.DEFAULT_SHIMMER_DURATION_IN_MILLIS);
            }
        });
        this.f650c.l().observe(this, new Observer<CharSequence>() { // from class: androidx.biometric.FingerprintDialogFragment.4
            @Override // androidx.lifecycle.Observer
            public void onChanged(CharSequence charSequence) {
                FingerprintDialogFragment fingerprintDialogFragment = FingerprintDialogFragment.this;
                fingerprintDialogFragment.f648a.removeCallbacks(fingerprintDialogFragment.f649b);
                FingerprintDialogFragment.this.j(charSequence);
                FingerprintDialogFragment fingerprintDialogFragment2 = FingerprintDialogFragment.this;
                fingerprintDialogFragment2.f648a.postDelayed(fingerprintDialogFragment2.f649b, SkeletonLayout.DEFAULT_SHIMMER_DURATION_IN_MILLIS);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static FingerprintDialogFragment f() {
        return new FingerprintDialogFragment();
    }

    private Drawable getAssetForTransition(int i2, int i3) {
        int i4;
        Context context = getContext();
        if (context == null) {
            return null;
        }
        if (i2 != 0 || i3 != 1) {
            if (i2 == 1 && i3 == 2) {
                i4 = R.drawable.fingerprint_dialog_error;
                return ContextCompat.getDrawable(context, i4);
            } else if ((i2 != 2 || i3 != 1) && (i2 != 1 || i3 != 3)) {
                return null;
            }
        }
        i4 = R.drawable.fingerprint_dialog_fp_icon;
        return ContextCompat.getDrawable(context, i4);
    }

    private int getThemedColorFor(int i2) {
        Context context = getContext();
        FragmentActivity activity = getActivity();
        if (context == null || activity == null) {
            return 0;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i2, typedValue, true);
        TypedArray obtainStyledAttributes = activity.obtainStyledAttributes(typedValue.data, new int[]{i2});
        int color = obtainStyledAttributes.getColor(0, 0);
        obtainStyledAttributes.recycle();
        return color;
    }

    private boolean shouldAnimateForTransition(int i2, int i3) {
        if (i2 == 0 && i3 == 1) {
            return false;
        }
        if (i2 == 1 && i3 == 2) {
            return true;
        }
        return i2 == 2 && i3 == 1;
    }

    void g() {
        Context context = getContext();
        if (context == null) {
            return;
        }
        this.f650c.T(1);
        this.f650c.R(context.getString(R.string.fingerprint_dialog_touch_sensor));
    }

    void h(int i2) {
        int m2;
        Drawable assetForTransition;
        if (this.mFingerprintIcon == null || Build.VERSION.SDK_INT < 23 || (assetForTransition = getAssetForTransition((m2 = this.f650c.m()), i2)) == null) {
            return;
        }
        this.mFingerprintIcon.setImageDrawable(assetForTransition);
        if (shouldAnimateForTransition(m2, i2)) {
            Api21Impl.a(assetForTransition);
        }
        this.f650c.S(i2);
    }

    void i(int i2) {
        TextView textView = this.f651d;
        if (textView != null) {
            textView.setTextColor(i2 == 2 ? this.mErrorTextColor : this.mNormalTextColor);
        }
    }

    void j(@Nullable CharSequence charSequence) {
        TextView textView = this.f651d;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(@NonNull DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        this.f650c.P(true);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        int color;
        super.onCreate(bundle);
        connectViewModel();
        if (Build.VERSION.SDK_INT >= 26) {
            color = getThemedColorFor(Api26Impl.a());
        } else {
            Context context = getContext();
            color = context != null ? ContextCompat.getColor(context, R.color.biometric_error_color) : 0;
        }
        this.mErrorTextColor = color;
        this.mNormalTextColor = getThemedColorFor(16842808);
    }

    @Override // androidx.fragment.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(@Nullable Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(this.f650c.s());
        View inflate = LayoutInflater.from(builder.getContext()).inflate(R.layout.fingerprint_dialog_layout, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.fingerprint_subtitle);
        if (textView != null) {
            CharSequence r2 = this.f650c.r();
            if (TextUtils.isEmpty(r2)) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
                textView.setText(r2);
            }
        }
        TextView textView2 = (TextView) inflate.findViewById(R.id.fingerprint_description);
        if (textView2 != null) {
            CharSequence k2 = this.f650c.k();
            if (TextUtils.isEmpty(k2)) {
                textView2.setVisibility(8);
            } else {
                textView2.setVisibility(0);
                textView2.setText(k2);
            }
        }
        this.mFingerprintIcon = (ImageView) inflate.findViewById(R.id.fingerprint_icon);
        this.f651d = (TextView) inflate.findViewById(R.id.fingerprint_error);
        builder.setNegativeButton(AuthenticatorUtils.c(this.f650c.a()) ? getString(R.string.confirm_device_credential_password) : this.f650c.q(), new DialogInterface.OnClickListener() { // from class: androidx.biometric.FingerprintDialogFragment.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                FingerprintDialogFragment.this.f650c.V(true);
            }
        });
        builder.setView(inflate);
        AlertDialog create = builder.create();
        create.setCanceledOnTouchOutside(false);
        return create;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.f648a.removeCallbacksAndMessages(null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.f650c.S(0);
        this.f650c.T(1);
        this.f650c.R(getString(R.string.fingerprint_dialog_touch_sensor));
    }
}
