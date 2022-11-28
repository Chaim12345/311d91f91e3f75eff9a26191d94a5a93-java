package com.google.android.gms.common.internal.service;

import android.os.IBinder;
import android.os.Parcel;
/* loaded from: classes.dex */
public final class zal extends com.google.android.gms.internal.base.zaa {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zal(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.service.ICommonService");
    }

    public final void zae(zak zakVar) {
        Parcel a2 = a();
        com.google.android.gms.internal.base.zac.zad(a2, zakVar);
        d(1, a2);
    }
}
