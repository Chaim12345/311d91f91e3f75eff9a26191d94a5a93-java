package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.Parcel;
/* loaded from: classes2.dex */
public abstract class zzq extends zzl implements zzr {
    public zzq() {
        super("com.google.android.play.core.appupdate.protocol.IAppUpdateServiceCallback");
    }

    @Override // com.google.android.play.core.internal.zzl
    protected final boolean a(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 == 2) {
            zzc((Bundle) zzm.zza(parcel, Bundle.CREATOR));
            return true;
        } else if (i2 != 3) {
            return false;
        } else {
            zzb((Bundle) zzm.zza(parcel, Bundle.CREATOR));
            return true;
        }
    }
}
