package com.google.android.libraries.places.internal;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes2.dex */
public final class zzgu {
    public static final Appendable zza(Appendable appendable, Iterator it, zzgv zzgvVar, String str) {
        String str2;
        if (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            appendable.append(zzgvVar.zza(entry.getKey()));
            while (true) {
                appendable.append("=");
                appendable.append(zzgvVar.zza(entry.getValue()));
                if (!it.hasNext()) {
                    break;
                }
                str2 = zzgvVar.zza;
                appendable.append(str2);
                entry = (Map.Entry) it.next();
                appendable.append(zzgvVar.zza(entry.getKey()));
            }
        }
        return appendable;
    }
}
