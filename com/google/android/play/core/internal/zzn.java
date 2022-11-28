package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
/* loaded from: classes2.dex */
public final class zzn extends zzk implements zzp {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzn(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.appupdate.protocol.IAppUpdateService");
    }

    @Override // com.google.android.play.core.internal.zzp
    public final void zzc(String str, Bundle bundle, zzr zzrVar) {
        Parcel a2 = a();
        a2.writeString(str);
        zzm.zzb(a2, bundle);
        zzm.zzc(a2, zzrVar);
        b(3, a2);
    }

    @Override // com.google.android.play.core.internal.zzp
    public final void zzd(String str, Bundle bundle, zzr zzrVar) {
        Parcel a2 = a();
        a2.writeString(str);
        zzm.zzb(a2, bundle);
        zzm.zzc(a2, zzrVar);
        b(2, a2);
    }
}
