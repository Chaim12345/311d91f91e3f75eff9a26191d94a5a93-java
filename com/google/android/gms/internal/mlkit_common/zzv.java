package com.google.android.gms.internal.mlkit_common;

import java.util.Arrays;
import java.util.Objects;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes.dex */
public final class zzv {
    private final String zza;
    private final zzu zzb;
    private zzu zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzv(String str, zzs zzsVar) {
        zzu zzuVar = new zzu(null);
        this.zzb = zzuVar;
        this.zzc = zzuVar;
        Objects.requireNonNull(str);
        this.zza = str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.zza);
        sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
        zzu zzuVar = this.zzb.f6244c;
        String str = "";
        while (zzuVar != null) {
            Object obj = zzuVar.f6243b;
            sb.append(str);
            String str2 = zzuVar.f6242a;
            if (str2 != null) {
                sb.append(str2);
                sb.append('=');
            }
            if (obj == null || !obj.getClass().isArray()) {
                sb.append(obj);
            } else {
                String deepToString = Arrays.deepToString(new Object[]{obj});
                sb.append((CharSequence) deepToString, 1, deepToString.length() - 1);
            }
            zzuVar = zzuVar.f6244c;
            str = ", ";
        }
        sb.append(AbstractJsonLexerKt.END_OBJ);
        return sb.toString();
    }

    public final zzv zza(String str, @CheckForNull Object obj) {
        zzu zzuVar = new zzu(null);
        this.zzc.f6244c = zzuVar;
        this.zzc = zzuVar;
        zzuVar.f6243b = obj;
        zzuVar.f6242a = str;
        return this;
    }

    public final zzv zzb(String str, boolean z) {
        String valueOf = String.valueOf(z);
        zzt zztVar = new zzt(null);
        this.zzc.f6244c = zztVar;
        this.zzc = zztVar;
        zztVar.f6243b = valueOf;
        zztVar.f6242a = "isManifestFile";
        return this;
    }
}
