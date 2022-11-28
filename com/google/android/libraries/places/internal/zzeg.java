package com.google.android.libraries.places.internal;

import android.content.Context;
import java.util.Objects;
/* loaded from: classes2.dex */
final class zzeg implements zzej {
    private Context zza;
    private zzem zzb;
    private zzet zzc;

    private zzeg() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzeg(zzef zzefVar) {
    }

    @Override // com.google.android.libraries.places.internal.zzej
    public final /* synthetic */ zzej zza(zzem zzemVar) {
        this.zzb = zzemVar;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzej
    public final /* synthetic */ zzej zzb(zzet zzetVar) {
        this.zzc = zzetVar;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzej
    public final /* synthetic */ zzej zzc(Context context) {
        Objects.requireNonNull(context);
        this.zza = context;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzej
    public final zzek zzd() {
        zzagk.zzb(this.zza, Context.class);
        zzagk.zzb(this.zzb, zzem.class);
        zzagk.zzb(this.zzc, zzet.class);
        return new zzei(this.zza, this.zzb, this.zzc, null);
    }
}
