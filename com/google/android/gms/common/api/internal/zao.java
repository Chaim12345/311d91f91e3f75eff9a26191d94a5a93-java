package com.google.android.gms.common.api.internal;

import android.app.Dialog;
import android.app.PendingIntent;
import androidx.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zao implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zap f5707a;
    private final zam zab;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zao(zap zapVar, zam zamVar) {
        this.f5707a = zapVar;
        this.zab = zamVar;
    }

    @Override // java.lang.Runnable
    @MainThread
    public final void run() {
        if (this.f5707a.f5708b) {
            ConnectionResult b2 = this.zab.b();
            if (b2.hasResolution()) {
                zap zapVar = this.f5707a;
                zapVar.f5631a.startActivityForResult(GoogleApiActivity.zaa(zapVar.getActivity(), (PendingIntent) Preconditions.checkNotNull(b2.getResolution()), this.zab.a(), false), 1);
                return;
            }
            zap zapVar2 = this.f5707a;
            if (zapVar2.f5710d.getErrorResolutionIntent(zapVar2.getActivity(), b2.getErrorCode(), null) != null) {
                zap zapVar3 = this.f5707a;
                zapVar3.f5710d.zag(zapVar3.getActivity(), this.f5707a.f5631a, b2.getErrorCode(), 2, this.f5707a);
            } else if (b2.getErrorCode() != 18) {
                this.f5707a.zaa(b2, this.zab.a());
            } else {
                zap zapVar4 = this.f5707a;
                Dialog zab = zapVar4.f5710d.zab(zapVar4.getActivity(), this.f5707a);
                zap zapVar5 = this.f5707a;
                zapVar5.f5710d.zac(zapVar5.getActivity().getApplicationContext(), new zan(this, zab));
            }
        }
    }
}
