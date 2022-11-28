package com.google.android.libraries.places.internal;

import java.io.IOException;
import java.util.Iterator;
import javax.annotation.CheckForNull;
/* loaded from: classes2.dex */
public class zzgv {
    private final String zza;

    private zzgv(String str) {
        this.zza = str;
    }

    public static zzgv zzc(String str) {
        return new zzgv(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharSequence zza(@CheckForNull Object obj) {
        obj.getClass();
        return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
    }

    public Appendable zzb(Appendable appendable, Iterator it) {
        if (it.hasNext()) {
            while (true) {
                appendable.append(zza(it.next()));
                if (!it.hasNext()) {
                    break;
                }
                appendable.append(this.zza);
            }
        }
        return appendable;
    }

    public final zzgv zzd() {
        return new zzgt(this, this);
    }

    public final String zzf(Iterable iterable) {
        Iterator it = iterable.iterator();
        StringBuilder sb = new StringBuilder();
        try {
            zzb(sb, it);
            return sb.toString();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }
}
