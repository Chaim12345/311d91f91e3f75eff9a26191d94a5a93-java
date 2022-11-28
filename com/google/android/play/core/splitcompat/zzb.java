package com.google.android.play.core.splitcompat;

import androidx.annotation.NonNull;
import java.io.File;
import java.util.Objects;
/* loaded from: classes2.dex */
final class zzb extends zzs {
    private final File zza;
    private final String zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzb(File file, String str) {
        Objects.requireNonNull(file, "Null splitFile");
        this.zza = file;
        Objects.requireNonNull(str, "Null splitId");
        this.zzb = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.splitcompat.zzs
    @NonNull
    public final File a() {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.splitcompat.zzs
    @NonNull
    public final String b() {
        return this.zzb;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzs) {
            zzs zzsVar = (zzs) obj;
            if (this.zza.equals(zzsVar.a()) && this.zzb.equals(zzsVar.b())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode();
    }

    public final String toString() {
        String obj = this.zza.toString();
        String str = this.zzb;
        StringBuilder sb = new StringBuilder(obj.length() + 35 + str.length());
        sb.append("SplitFileInfo{splitFile=");
        sb.append(obj);
        sb.append(", splitId=");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }
}
