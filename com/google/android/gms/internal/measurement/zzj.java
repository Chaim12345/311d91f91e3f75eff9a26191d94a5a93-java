package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
/* loaded from: classes.dex */
public final class zzj {

    /* renamed from: a  reason: collision with root package name */
    final Map f6094a = new HashMap();

    public final void zza(String str, Callable callable) {
        this.f6094a.put(str, callable);
    }
}
