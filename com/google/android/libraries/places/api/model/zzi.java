package com.google.android.libraries.places.api.model;
/* loaded from: classes2.dex */
final class zzi extends zzbd {
    private int zza;
    private int zzb;
    private byte zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzbd zza(int i2) {
        this.zza = i2;
        this.zzc = (byte) (this.zzc | 1);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.api.model.zzbd
    public final zzbd zzb(int i2) {
        this.zzb = i2;
        this.zzc = (byte) (this.zzc | 2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.api.model.zzbd
    public final LocalTime zzc() {
        if (this.zzc != 3) {
            StringBuilder sb = new StringBuilder();
            if ((this.zzc & 1) == 0) {
                sb.append(" hours");
            }
            if ((this.zzc & 2) == 0) {
                sb.append(" minutes");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new zzaj(this.zza, this.zzb);
    }
}
