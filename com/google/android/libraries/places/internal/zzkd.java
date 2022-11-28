package com.google.android.libraries.places.internal;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
/* loaded from: classes2.dex */
public final class zzkd extends zzjs {
    private static final Set zza;
    private static final zzjk zzb;
    private final String zzc;
    private final Level zzd;

    static {
        Set unmodifiableSet = Collections.unmodifiableSet(new HashSet(Arrays.asList(zzit.zza, zziz.zza)));
        zza = unmodifiableSet;
        zzb = zzjn.zza(unmodifiableSet).zzd();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzkd(String str, String str2, boolean z, boolean z2, Level level, zzkc zzkcVar) {
        super(str2);
        if (str2.length() > 23) {
            int i2 = -1;
            for (int length = str2.length() - 1; length >= 0; length--) {
                char charAt = str2.charAt(length);
                if (charAt == '.' || charAt == '$') {
                    i2 = length;
                    break;
                }
            }
            str2 = str2.substring(i2 + 1);
        }
        String valueOf = String.valueOf(str2);
        String concat = valueOf.length() != 0 ? "".concat(valueOf) : new String("");
        this.zzc = concat.substring(0, Math.min(concat.length(), 23));
        this.zzd = level;
    }
}
