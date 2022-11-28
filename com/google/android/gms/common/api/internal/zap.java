package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes.dex */
public abstract class zap extends LifecycleCallback implements DialogInterface.OnCancelListener {

    /* renamed from: b  reason: collision with root package name */
    protected volatile boolean f5708b;

    /* renamed from: c  reason: collision with root package name */
    protected final AtomicReference f5709c;

    /* renamed from: d  reason: collision with root package name */
    protected final GoogleApiAvailability f5710d;
    private final Handler zad;

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public zap(LifecycleFragment lifecycleFragment, GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment);
        this.f5709c = new AtomicReference(null);
        this.zad = new com.google.android.gms.internal.base.zaq(Looper.getMainLooper());
        this.f5710d = googleApiAvailability;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zaa(ConnectionResult connectionResult, int i2) {
        this.f5709c.set(null);
        b(connectionResult, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zad() {
        this.f5709c.set(null);
        c();
    }

    private static final int zae(@Nullable zam zamVar) {
        if (zamVar == null) {
            return -1;
        }
        return zamVar.a();
    }

    protected abstract void b(ConnectionResult connectionResult, int i2);

    protected abstract void c();

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onActivityResult(int i2, int i3, Intent intent) {
        zam zamVar = (zam) this.f5709c.get();
        if (i2 != 1) {
            if (i2 == 2) {
                int isGooglePlayServicesAvailable = this.f5710d.isGooglePlayServicesAvailable(getActivity());
                if (isGooglePlayServicesAvailable == 0) {
                    zad();
                    return;
                } else if (zamVar == null) {
                    return;
                } else {
                    if (zamVar.b().getErrorCode() == 18 && isGooglePlayServicesAvailable == 18) {
                        return;
                    }
                }
            }
        } else if (i3 == -1) {
            zad();
            return;
        } else if (i3 == 0) {
            if (zamVar == null) {
                return;
            }
            zaa(new ConnectionResult(intent != null ? intent.getIntExtra("<<ResolutionFailureErrorDetail>>", 13) : 13, null, zamVar.b().toString()), zae(zamVar));
            return;
        }
        if (zamVar != null) {
            zaa(zamVar.b(), zamVar.a());
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        zaa(new ConnectionResult(13, null), zae((zam) this.f5709c.get()));
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.f5709c.set(bundle.getBoolean("resolving_error", false) ? new zam(new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1)) : null);
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        zam zamVar = (zam) this.f5709c.get();
        if (zamVar == null) {
            return;
        }
        bundle.putBoolean("resolving_error", true);
        bundle.putInt("failed_client_id", zamVar.a());
        bundle.putInt("failed_status", zamVar.b().getErrorCode());
        bundle.putParcelable("failed_resolution", zamVar.b().getResolution());
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStart() {
        super.onStart();
        this.f5708b = true;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        super.onStop();
        this.f5708b = false;
    }

    public final void zah(ConnectionResult connectionResult, int i2) {
        zam zamVar = new zam(connectionResult, i2);
        if (this.f5709c.compareAndSet(null, zamVar)) {
            this.zad.post(new zao(this, zamVar));
        }
    }
}
