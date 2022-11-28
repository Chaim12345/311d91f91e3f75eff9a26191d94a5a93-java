package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
/* loaded from: classes.dex */
public final class zam extends com.google.android.gms.internal.base.zaa {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zam(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.ISignInButtonCreator");
    }

    public final IObjectWrapper zae(IObjectWrapper iObjectWrapper, zax zaxVar) {
        Parcel a2 = a();
        com.google.android.gms.internal.base.zac.zad(a2, iObjectWrapper);
        com.google.android.gms.internal.base.zac.zac(a2, zaxVar);
        Parcel b2 = b(2, a2);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(b2.readStrongBinder());
        b2.recycle();
        return asInterface;
    }
}
