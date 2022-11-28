package com.google.android.gms.internal.mlkit_vision_common;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.io.UnsupportedEncodingException;
/* loaded from: classes2.dex */
public final class zzju {
    private final zzha zza;
    private zzil zzb = new zzil();

    private zzju(zzha zzhaVar, int i2) {
        this.zza = zzhaVar;
        zzkg.zza();
    }

    public static zzju zzc(zzha zzhaVar) {
        return new zzju(zzhaVar, 0);
    }

    public final String zza() {
        zzin zzc = this.zza.zzf().zzc();
        return (zzc == null || zzg.zzb(zzc.zzk())) ? AppConstants.SECONDARY_USER_STATE_NA : (String) Preconditions.checkNotNull(zzc.zzk());
    }

    public final byte[] zzb(int i2, boolean z) {
        this.zzb.zzf(Boolean.valueOf(1 == (i2 ^ 1)));
        this.zzb.zze(Boolean.FALSE);
        this.zza.zze(this.zzb.zzm());
        try {
            zzkg.zza();
            if (i2 == 0) {
                return new JsonDataEncoderBuilder().configureWith(zzfo.zza).ignoreNullValues(true).build().encode(this.zza.zzf()).getBytes("utf-8");
            }
            zzhc zzf = this.zza.zzf();
            zzal zzalVar = new zzal();
            zzfo.zza.configure(zzalVar);
            return zzalVar.zza().zza(zzf);
        } catch (UnsupportedEncodingException e2) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e2);
        }
    }

    public final zzju zzd(zzgz zzgzVar) {
        this.zza.zzc(zzgzVar);
        return this;
    }

    public final zzju zze(zzil zzilVar) {
        this.zzb = zzilVar;
        return this;
    }
}
