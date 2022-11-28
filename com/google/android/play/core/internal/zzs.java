package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import java.util.List;
/* loaded from: classes2.dex */
public final class zzs extends zzk implements zzu {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzs(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.assetpacks.protocol.IAssetModuleService");
    }

    @Override // com.google.android.play.core.internal.zzu
    public final void zzc(String str, List list, Bundle bundle, zzw zzwVar) {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeTypedList(list);
        zzm.zzb(a2, bundle);
        zzm.zzc(a2, zzwVar);
        b(14, a2);
    }

    @Override // com.google.android.play.core.internal.zzu
    public final void zzd(String str, Bundle bundle, Bundle bundle2, zzw zzwVar) {
        Parcel a2 = a();
        a2.writeString(str);
        zzm.zzb(a2, bundle);
        zzm.zzb(a2, bundle2);
        zzm.zzc(a2, zzwVar);
        b(11, a2);
    }

    @Override // com.google.android.play.core.internal.zzu
    public final void zze(String str, Bundle bundle, zzw zzwVar) {
        Parcel a2 = a();
        a2.writeString(str);
        zzm.zzb(a2, bundle);
        zzm.zzc(a2, zzwVar);
        b(5, a2);
    }

    @Override // com.google.android.play.core.internal.zzu
    public final void zzf(String str, Bundle bundle, zzw zzwVar) {
        Parcel a2 = a();
        a2.writeString(str);
        zzm.zzb(a2, bundle);
        zzm.zzc(a2, zzwVar);
        b(10, a2);
    }

    @Override // com.google.android.play.core.internal.zzu
    public final void zzg(String str, Bundle bundle, Bundle bundle2, zzw zzwVar) {
        Parcel a2 = a();
        a2.writeString(str);
        zzm.zzb(a2, bundle);
        zzm.zzb(a2, bundle2);
        zzm.zzc(a2, zzwVar);
        b(6, a2);
    }

    @Override // com.google.android.play.core.internal.zzu
    public final void zzh(String str, Bundle bundle, Bundle bundle2, zzw zzwVar) {
        Parcel a2 = a();
        a2.writeString(str);
        zzm.zzb(a2, bundle);
        zzm.zzb(a2, bundle2);
        zzm.zzc(a2, zzwVar);
        b(7, a2);
    }

    @Override // com.google.android.play.core.internal.zzu
    public final void zzi(String str, Bundle bundle, Bundle bundle2, zzw zzwVar) {
        Parcel a2 = a();
        a2.writeString(str);
        zzm.zzb(a2, bundle);
        zzm.zzb(a2, bundle2);
        zzm.zzc(a2, zzwVar);
        b(9, a2);
    }

    @Override // com.google.android.play.core.internal.zzu
    public final void zzj(String str, Bundle bundle, Bundle bundle2, zzw zzwVar) {
        Parcel a2 = a();
        a2.writeString(str);
        zzm.zzb(a2, bundle);
        zzm.zzb(a2, bundle2);
        zzm.zzc(a2, zzwVar);
        b(13, a2);
    }

    @Override // com.google.android.play.core.internal.zzu
    public final void zzk(String str, List list, Bundle bundle, zzw zzwVar) {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeTypedList(list);
        zzm.zzb(a2, bundle);
        zzm.zzc(a2, zzwVar);
        b(12, a2);
    }

    @Override // com.google.android.play.core.internal.zzu
    public final void zzl(String str, List list, Bundle bundle, zzw zzwVar) {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeTypedList(list);
        zzm.zzb(a2, bundle);
        zzm.zzc(a2, zzwVar);
        b(2, a2);
    }
}
