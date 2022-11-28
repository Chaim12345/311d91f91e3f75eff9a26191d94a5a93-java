package com.google.android.gms.signin.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.IAccountAccessor;
/* loaded from: classes2.dex */
public final class zaf extends com.google.android.gms.internal.base.zaa {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zaf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.signin.internal.ISignInService");
    }

    public final void zae(int i2) {
        Parcel a2 = a();
        a2.writeInt(i2);
        c(7, a2);
    }

    public final void zaf(IAccountAccessor iAccountAccessor, int i2, boolean z) {
        Parcel a2 = a();
        com.google.android.gms.internal.base.zac.zad(a2, iAccountAccessor);
        a2.writeInt(i2);
        com.google.android.gms.internal.base.zac.zab(a2, z);
        c(9, a2);
    }

    public final void zag(zai zaiVar, zae zaeVar) {
        Parcel a2 = a();
        com.google.android.gms.internal.base.zac.zac(a2, zaiVar);
        com.google.android.gms.internal.base.zac.zad(a2, zaeVar);
        c(12, a2);
    }
}
