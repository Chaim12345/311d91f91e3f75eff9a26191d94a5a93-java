package com.google.android.gms.internal.mlkit_common;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.io.UnsupportedEncodingException;
/* loaded from: classes.dex */
public final class zzlo implements zzlc {
    private final zzif zza;
    private zzjz zzb = new zzjz();

    private zzlo(zzif zzifVar, int i2) {
        this.zza = zzifVar;
        zzlz.zza();
    }

    public static zzlc zzf(zzif zzifVar) {
        return new zzlo(zzifVar, 0);
    }

    public static zzlc zzg() {
        return new zzlo(new zzif(), 0);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzlc
    public final zzlc zza(zzie zzieVar) {
        this.zza.zzf(zzieVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzlc
    public final zzlc zzb(zzil zzilVar) {
        this.zza.zzi(zzilVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzlc
    public final zzlc zzc(zzjz zzjzVar) {
        this.zzb = zzjzVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzlc
    public final String zzd() {
        zzkb zzf = this.zza.zzk().zzf();
        return (zzf == null || zzac.zzc(zzf.zzk())) ? AppConstants.SECONDARY_USER_STATE_NA : (String) Preconditions.checkNotNull(zzf.zzk());
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzlc
    public final byte[] zze(int i2, boolean z) {
        this.zzb.zzf(Boolean.valueOf(1 == (i2 ^ 1)));
        this.zzb.zze(Boolean.FALSE);
        this.zza.zzj(this.zzb.zzm());
        try {
            zzlz.zza();
            if (i2 == 0) {
                return new JsonDataEncoderBuilder().configureWith(zzgp.zza).ignoreNullValues(true).build().encode(this.zza.zzk()).getBytes("utf-8");
            }
            zzih zzk = this.zza.zzk();
            zzbm zzbmVar = new zzbm();
            zzgp.zza.configure(zzbmVar);
            return zzbmVar.zza().zza(zzk);
        } catch (UnsupportedEncodingException e2) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e2);
        }
    }
}
