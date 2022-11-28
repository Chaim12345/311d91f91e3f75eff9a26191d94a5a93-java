package com.google.android.play.core.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
/* loaded from: classes2.dex */
public class zzl extends Binder implements IInterface {
    /* JADX INFO: Access modifiers changed from: protected */
    public zzl(String str) {
        attachInterface(this, str);
    }

    protected boolean a(int i2, Parcel parcel, Parcel parcel2, int i3) {
        throw null;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 <= 16777215) {
            parcel.enforceInterface(getInterfaceDescriptor());
        } else if (super.onTransact(i2, parcel, parcel2, i3)) {
            return true;
        }
        return a(i2, parcel, parcel2, i3);
    }
}
