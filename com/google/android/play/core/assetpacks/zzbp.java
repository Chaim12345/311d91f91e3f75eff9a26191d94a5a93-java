package com.google.android.play.core.assetpacks;

import androidx.annotation.Nullable;
import org.bouncycastle.tls.CipherSuite;
/* loaded from: classes2.dex */
final class zzbp extends zzem {
    private final int zza;
    private final String zzb;
    private final long zzc;
    private final long zzd;
    private final int zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbp(int i2, @Nullable String str, long j2, long j3, int i3) {
        this.zza = i2;
        this.zzb = str;
        this.zzc = j2;
        this.zzd = j3;
        this.zze = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.zzem
    public final int a() {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.zzem
    public final int b() {
        return this.zze;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.zzem
    public final long c() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.zzem
    public final long d() {
        return this.zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.zzem
    @Nullable
    public final String e() {
        return this.zzb;
    }

    public final boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzem) {
            zzem zzemVar = (zzem) obj;
            if (this.zza == zzemVar.a() && ((str = this.zzb) != null ? str.equals(zzemVar.e()) : zzemVar.e() == null) && this.zzc == zzemVar.c() && this.zzd == zzemVar.d() && this.zze == zzemVar.b()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i2 = (this.zza ^ 1000003) * 1000003;
        String str = this.zzb;
        int hashCode = str == null ? 0 : str.hashCode();
        long j2 = this.zzc;
        long j3 = this.zzd;
        return ((((((i2 ^ hashCode) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ ((int) ((j3 >>> 32) ^ j3))) * 1000003) ^ this.zze;
    }

    public final String toString() {
        int i2 = this.zza;
        String str = this.zzb;
        long j2 = this.zzc;
        long j3 = this.zzd;
        int i3 = this.zze;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384);
        sb.append("SliceCheckpoint{fileExtractionStatus=");
        sb.append(i2);
        sb.append(", filePath=");
        sb.append(str);
        sb.append(", fileOffset=");
        sb.append(j2);
        sb.append(", remainingBytes=");
        sb.append(j3);
        sb.append(", previousChunk=");
        sb.append(i3);
        sb.append("}");
        return sb.toString();
    }
}
