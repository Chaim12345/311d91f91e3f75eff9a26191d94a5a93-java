package com.google.android.play.core.assetpacks;

import java.util.HashMap;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzco {
    private final Map zza = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized double a(String str) {
        Double d2 = (Double) this.zza.get(str);
        if (d2 == null) {
            return 0.0d;
        }
        return d2.doubleValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized double b(String str, zzdg zzdgVar) {
        double d2;
        d2 = (((zzce) zzdgVar).f7809h + 1.0d) / ((zzce) zzdgVar).f7810i;
        this.zza.put(str, Double.valueOf(d2));
        return d2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void c(String str) {
        this.zza.put(str, Double.valueOf(0.0d));
    }
}
