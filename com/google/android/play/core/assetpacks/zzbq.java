package com.google.android.play.core.assetpacks;

import androidx.annotation.Nullable;
import java.util.Arrays;
/* loaded from: classes2.dex */
final class zzbq extends zzet {
    private final String zza;
    private final long zzb;
    private final int zzc;
    private final boolean zzd;
    private final boolean zze;
    private final byte[] zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbq(@Nullable String str, long j2, int i2, boolean z, boolean z2, @Nullable byte[] bArr) {
        this.zza = str;
        this.zzb = j2;
        this.zzc = i2;
        this.zzd = z;
        this.zze = z2;
        this.zzf = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.zzet
    public final int a() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.zzet
    public final long b() {
        return this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.zzet
    @Nullable
    public final String c() {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.zzet
    public final boolean d() {
        return this.zze;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.zzet
    public final boolean e() {
        return this.zzd;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzet) {
            zzet zzetVar = (zzet) obj;
            String str = this.zza;
            if (str != null ? str.equals(zzetVar.c()) : zzetVar.c() == null) {
                if (this.zzb == zzetVar.b() && this.zzc == zzetVar.a() && this.zzd == zzetVar.e() && this.zze == zzetVar.d()) {
                    if (Arrays.equals(this.zzf, zzetVar instanceof zzbq ? ((zzbq) zzetVar).zzf : zzetVar.f())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.zzet
    @Nullable
    public final byte[] f() {
        return this.zzf;
    }

    public final int hashCode() {
        String str = this.zza;
        int hashCode = str == null ? 0 : str.hashCode();
        long j2 = this.zzb;
        return ((((((((((hashCode ^ 1000003) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ this.zzc) * 1000003) ^ (true != this.zzd ? 1237 : 1231)) * 1000003) ^ (true == this.zze ? 1231 : 1237)) * 1000003) ^ Arrays.hashCode(this.zzf);
    }

    public final String toString() {
        String str = this.zza;
        long j2 = this.zzb;
        int i2 = this.zzc;
        boolean z = this.zzd;
        boolean z2 = this.zze;
        String arrays = Arrays.toString(this.zzf);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 126 + String.valueOf(arrays).length());
        sb.append("ZipEntry{name=");
        sb.append(str);
        sb.append(", size=");
        sb.append(j2);
        sb.append(", compressionMethod=");
        sb.append(i2);
        sb.append(", isPartial=");
        sb.append(z);
        sb.append(", isEndOfArchive=");
        sb.append(z2);
        sb.append(", headerBytes=");
        sb.append(arrays);
        sb.append("}");
        return sb.toString();
    }
}
