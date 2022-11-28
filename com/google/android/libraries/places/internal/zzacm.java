package com.google.android.libraries.places.internal;

import java.nio.charset.Charset;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class zzacm extends zzacl {
    protected final byte[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzacm(byte[] bArr) {
        Objects.requireNonNull(bArr);
        this.zza = bArr;
    }

    @Override // com.google.android.libraries.places.internal.zzacp
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzacp) && zzd() == ((zzacp) obj).zzd()) {
            if (zzd() == 0) {
                return true;
            }
            if (obj instanceof zzacm) {
                zzacm zzacmVar = (zzacm) obj;
                int zzk = zzk();
                int zzk2 = zzacmVar.zzk();
                if (zzk == 0 || zzk2 == 0 || zzk == zzk2) {
                    int zzd = zzd();
                    if (zzd > zzacmVar.zzd()) {
                        int zzd2 = zzd();
                        StringBuilder sb = new StringBuilder(40);
                        sb.append("Length too large: ");
                        sb.append(zzd);
                        sb.append(zzd2);
                        throw new IllegalArgumentException(sb.toString());
                    } else if (zzd > zzacmVar.zzd()) {
                        int zzd3 = zzacmVar.zzd();
                        StringBuilder sb2 = new StringBuilder(59);
                        sb2.append("Ran off end of other: 0, ");
                        sb2.append(zzd);
                        sb2.append(", ");
                        sb2.append(zzd3);
                        throw new IllegalArgumentException(sb2.toString());
                    } else {
                        byte[] bArr = this.zza;
                        byte[] bArr2 = zzacmVar.zza;
                        zzacmVar.zzc();
                        int i2 = 0;
                        int i3 = 0;
                        while (i2 < zzd) {
                            if (bArr[i2] != bArr2[i3]) {
                                return false;
                            }
                            i2++;
                            i3++;
                        }
                        return true;
                    }
                }
                return false;
            }
            return obj.equals(this);
        }
        return false;
    }

    @Override // com.google.android.libraries.places.internal.zzacp
    public byte zza(int i2) {
        return this.zza[i2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzacp
    public byte zzb(int i2) {
        return this.zza[i2];
    }

    protected int zzc() {
        return 0;
    }

    @Override // com.google.android.libraries.places.internal.zzacp
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.libraries.places.internal.zzacp
    protected final int zze(int i2, int i3, int i4) {
        return zzads.zzd(i2, this.zza, 0, i4);
    }

    @Override // com.google.android.libraries.places.internal.zzacp
    public final zzacp zzf(int i2, int i3) {
        zzacp.zzj(0, i3, zzd());
        return i3 == 0 ? zzacp.zzb : new zzacj(this.zza, 0, i3);
    }

    @Override // com.google.android.libraries.places.internal.zzacp
    protected final String zzg(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzacp
    public final void zzh(zzacf zzacfVar) {
        ((zzacu) zzacfVar).zzc(this.zza, 0, zzd());
    }

    @Override // com.google.android.libraries.places.internal.zzacp
    public final boolean zzi() {
        return zzagh.zze(this.zza, 0, zzd());
    }
}
