package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.Parcel;
/* loaded from: classes2.dex */
public abstract class zzad extends zzl implements zzae {
    public zzad() {
        super("com.google.android.play.core.inappreview.protocol.IInAppReviewServiceCallback");
    }

    @Override // com.google.android.play.core.internal.zzl
    protected final boolean a(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 == 2) {
            zzb((Bundle) zzm.zza(parcel, Bundle.CREATOR));
            return true;
        }
        return false;
    }
}
