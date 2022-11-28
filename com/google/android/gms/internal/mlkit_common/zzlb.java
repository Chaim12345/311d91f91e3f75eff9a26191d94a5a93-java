package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.ModelType;
import org.bouncycastle.tls.CipherSuite;
/* loaded from: classes.dex */
final class zzlb extends zzln {
    private final zzid zza;
    private final String zzb;
    private final boolean zzc;
    private final boolean zzd;
    private final ModelType zze;
    private final zzij zzf;
    private final int zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzlb(zzid zzidVar, String str, boolean z, boolean z2, ModelType modelType, zzij zzijVar, int i2, zzla zzlaVar) {
        this.zza = zzidVar;
        this.zzb = str;
        this.zzc = z;
        this.zzd = z2;
        this.zze = modelType;
        this.zzf = zzijVar;
        this.zzg = i2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzln) {
            zzln zzlnVar = (zzln) obj;
            if (this.zza.equals(zzlnVar.zzc()) && this.zzb.equals(zzlnVar.zze()) && this.zzc == zzlnVar.zzg() && this.zzd == zzlnVar.zzf() && this.zze.equals(zzlnVar.zzb()) && this.zzf.equals(zzlnVar.zzd()) && this.zzg == zzlnVar.zza()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003) ^ (true != this.zzc ? 1237 : 1231)) * 1000003) ^ (true == this.zzd ? 1231 : 1237)) * 1000003) ^ this.zze.hashCode()) * 1000003) ^ this.zzf.hashCode()) * 1000003) ^ this.zzg;
    }

    public final String toString() {
        String obj = this.zza.toString();
        String str = this.zzb;
        boolean z = this.zzc;
        boolean z2 = this.zzd;
        String obj2 = this.zze.toString();
        String obj3 = this.zzf.toString();
        int i2 = this.zzg;
        StringBuilder sb = new StringBuilder(obj.length() + CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256 + str.length() + obj2.length() + obj3.length());
        sb.append("RemoteModelLoggingOptions{errorCode=");
        sb.append(obj);
        sb.append(", tfliteSchemaVersion=");
        sb.append(str);
        sb.append(", shouldLogRoughDownloadTime=");
        sb.append(z);
        sb.append(", shouldLogExactDownloadTime=");
        sb.append(z2);
        sb.append(", modelType=");
        sb.append(obj2);
        sb.append(", downloadStatus=");
        sb.append(obj3);
        sb.append(", failureStatusCode=");
        sb.append(i2);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzln
    public final int zza() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzln
    public final ModelType zzb() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzln
    public final zzid zzc() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzln
    public final zzij zzd() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzln
    public final String zze() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzln
    public final boolean zzf() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzln
    public final boolean zzg() {
        return this.zzc;
    }
}
