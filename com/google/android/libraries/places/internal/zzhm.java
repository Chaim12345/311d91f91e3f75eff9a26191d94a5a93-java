package com.google.android.libraries.places.internal;

import java.util.Objects;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzhm extends zzhn {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzhm(Comparable comparable) {
        super(comparable);
        Objects.requireNonNull(comparable);
    }

    @Override // com.google.android.libraries.places.internal.zzhn
    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final String toString() {
        String obj = this.zza.toString();
        StringBuilder sb = new StringBuilder(obj.length() + 2);
        sb.append("\\");
        sb.append(obj);
        sb.append("/");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzhn
    public final void zzc(StringBuilder sb) {
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        sb.append(this.zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzhn
    public final void zzd(StringBuilder sb) {
        sb.append(this.zza);
        sb.append(')');
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzhn
    public final boolean zze(Comparable comparable) {
        return zzie.zza(this.zza, comparable) <= 0;
    }
}
