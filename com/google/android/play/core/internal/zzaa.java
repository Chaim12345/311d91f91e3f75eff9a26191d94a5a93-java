package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
/* loaded from: classes2.dex */
public final class zzaa extends zzk implements zzac {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaa(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.inappreview.protocol.IInAppReviewService");
    }

    @Override // com.google.android.play.core.internal.zzac
    public final void zzc(String str, Bundle bundle, zzae zzaeVar) {
        Parcel a2 = a();
        a2.writeString(str);
        zzm.zzb(a2, bundle);
        zzm.zzc(a2, zzaeVar);
        b(2, a2);
    }
}
