package com.google.android.odml.image;
/* loaded from: classes2.dex */
final class zzb extends zzh {
    private Integer zza;
    private Integer zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.odml.image.zzh
    public final zzh a(int i2) {
        this.zza = Integer.valueOf(i2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.odml.image.zzh
    public final zzh b(int i2) {
        this.zzb = Integer.valueOf(i2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.odml.image.zzh
    public final ImageProperties c() {
        Integer num = this.zza;
        if (num == null || this.zzb == null) {
            StringBuilder sb = new StringBuilder();
            if (this.zza == null) {
                sb.append(" imageFormat");
            }
            if (this.zzb == null) {
                sb.append(" storageType");
            }
            String valueOf = String.valueOf(sb);
            StringBuilder sb2 = new StringBuilder(valueOf.length() + 28);
            sb2.append("Missing required properties:");
            sb2.append(valueOf);
            throw new IllegalStateException(sb2.toString());
        }
        return new zzc(num.intValue(), this.zzb.intValue(), null);
    }
}
