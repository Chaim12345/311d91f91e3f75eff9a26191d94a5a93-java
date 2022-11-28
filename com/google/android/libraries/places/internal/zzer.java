package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
final class zzer extends zzet {
    private final String zza;
    private final int zzb;
    private final int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzer(String str, int i2, int i3, zzeq zzeqVar) {
        this.zza = str;
        this.zzb = i2;
        this.zzc = i3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzet) {
            zzet zzetVar = (zzet) obj;
            if (this.zza.equals(zzetVar.zzb()) && this.zzb == zzetVar.zza() && this.zzc == zzetVar.zzc()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb) * 1000003) ^ this.zzc;
    }

    public final String toString() {
        String str = this.zza;
        int i2 = this.zzb;
        String str2 = this.zzc != 1 ? "AUTOCOMPLETE_WIDGET" : "PROGRAMMATIC_API";
        StringBuilder sb = new StringBuilder(str.length() + 68 + str2.length());
        sb.append("ClientProfile{packageName=");
        sb.append(str);
        sb.append(", versionCode=");
        sb.append(i2);
        sb.append(", requestSource=");
        sb.append(str2);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.libraries.places.internal.zzet
    public final int zza() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.internal.zzet
    public final String zzb() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.internal.zzet
    public final int zzc() {
        return this.zzc;
    }
}
