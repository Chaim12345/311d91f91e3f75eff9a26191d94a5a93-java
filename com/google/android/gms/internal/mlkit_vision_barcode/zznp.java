package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.io.UnsupportedEncodingException;
/* loaded from: classes2.dex */
public final class zznp {
    private final zzkl zza;
    private zzmc zzb = new zzmc();
    private final int zzc;

    private zznp(zzkl zzklVar, int i2) {
        this.zza = zzklVar;
        zzny.zza();
        this.zzc = i2;
    }

    public static zznp zzd(zzkl zzklVar) {
        return new zznp(zzklVar, 0);
    }

    public static zznp zze(zzkl zzklVar, int i2) {
        return new zznp(zzklVar, 1);
    }

    public final int zza() {
        return this.zzc;
    }

    public final String zzb() {
        zzme zzf = this.zza.zzj().zzf();
        return (zzf == null || zzar.zzb(zzf.zzk())) ? AppConstants.SECONDARY_USER_STATE_NA : (String) Preconditions.checkNotNull(zzf.zzk());
    }

    public final byte[] zzc(int i2, boolean z) {
        this.zzb.zzf(Boolean.valueOf(1 == (i2 ^ 1)));
        this.zzb.zze(Boolean.FALSE);
        this.zza.zzi(this.zzb.zzm());
        try {
            zzny.zza();
            if (i2 == 0) {
                return new JsonDataEncoderBuilder().configureWith(zziu.zza).ignoreNullValues(true).build().encode(this.zza.zzj()).getBytes("utf-8");
            }
            zzkn zzj = this.zza.zzj();
            zzdn zzdnVar = new zzdn();
            zziu.zza.configure(zzdnVar);
            return zzdnVar.zza().zza(zzj);
        } catch (UnsupportedEncodingException e2) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e2);
        }
    }

    public final zznp zzf(zzkk zzkkVar) {
        this.zza.zzf(zzkkVar);
        return this;
    }

    public final zznp zzg(zzmc zzmcVar) {
        this.zzb = zzmcVar;
        return this;
    }
}
