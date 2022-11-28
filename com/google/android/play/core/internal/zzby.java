package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import java.util.List;
/* loaded from: classes2.dex */
public final class zzby extends zzk implements zzca {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzby(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.splitinstall.protocol.ISplitInstallService");
    }

    @Override // com.google.android.play.core.internal.zzca
    public final void zzc(String str, int i2, Bundle bundle, zzcc zzccVar) {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeInt(i2);
        zzm.zzb(a2, bundle);
        zzm.zzc(a2, zzccVar);
        b(4, a2);
    }

    @Override // com.google.android.play.core.internal.zzca
    public final void zzd(String str, List list, Bundle bundle, zzcc zzccVar) {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeTypedList(list);
        zzm.zzb(a2, bundle);
        zzm.zzc(a2, zzccVar);
        b(8, a2);
    }

    @Override // com.google.android.play.core.internal.zzca
    public final void zze(String str, List list, Bundle bundle, zzcc zzccVar) {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeTypedList(list);
        zzm.zzb(a2, bundle);
        zzm.zzc(a2, zzccVar);
        b(13, a2);
    }

    @Override // com.google.android.play.core.internal.zzca
    public final void zzf(String str, List list, Bundle bundle, zzcc zzccVar) {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeTypedList(list);
        zzm.zzb(a2, bundle);
        zzm.zzc(a2, zzccVar);
        b(14, a2);
    }

    @Override // com.google.android.play.core.internal.zzca
    public final void zzg(String str, List list, Bundle bundle, zzcc zzccVar) {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeTypedList(list);
        zzm.zzb(a2, bundle);
        zzm.zzc(a2, zzccVar);
        b(7, a2);
    }

    @Override // com.google.android.play.core.internal.zzca
    public final void zzh(String str, int i2, zzcc zzccVar) {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeInt(i2);
        zzm.zzc(a2, zzccVar);
        b(5, a2);
    }

    @Override // com.google.android.play.core.internal.zzca
    public final void zzi(String str, zzcc zzccVar) {
        Parcel a2 = a();
        a2.writeString(str);
        zzm.zzc(a2, zzccVar);
        b(6, a2);
    }

    @Override // com.google.android.play.core.internal.zzca
    public final void zzj(String str, List list, Bundle bundle, zzcc zzccVar) {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeTypedList(list);
        zzm.zzb(a2, bundle);
        zzm.zzc(a2, zzccVar);
        b(2, a2);
    }
}
