package com.google.android.gms.internal.mlkit_vision_common;
/* loaded from: classes2.dex */
final class zzjj extends zzjm {
    private String zza;
    private boolean zzb;
    private int zzc;
    private byte zzd;

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzjm
    public final zzjm zza(boolean z) {
        this.zzb = true;
        this.zzd = (byte) (1 | this.zzd);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzjm
    public final zzjm zzb(int i2) {
        this.zzc = 1;
        this.zzd = (byte) (this.zzd | 2);
        return this;
    }

    public final zzjm zzc(String str) {
        this.zza = "vision-common";
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzjm
    public final zzjn zzd() {
        String str;
        if (this.zzd != 3 || (str = this.zza) == null) {
            StringBuilder sb = new StringBuilder();
            if (this.zza == null) {
                sb.append(" libraryName");
            }
            if ((this.zzd & 1) == 0) {
                sb.append(" enableFirelog");
            }
            if ((this.zzd & 2) == 0) {
                sb.append(" firelogEventType");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new zzjl(str, this.zzb, this.zzc, null);
    }
}
