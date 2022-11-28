package com.google.mlkit.common.sdkinternal;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.HashMap;
import java.util.Map;
@KeepForSdk
/* loaded from: classes2.dex */
public abstract class LazyInstanceMap<K, V> {
    @GuardedBy("instances")
    private final Map zza = new HashMap();

    @NonNull
    @KeepForSdk
    protected abstract Object a(@NonNull Object obj);

    @NonNull
    @KeepForSdk
    public V get(@NonNull K k2) {
        synchronized (this.zza) {
            if (this.zza.containsKey(k2)) {
                return (V) this.zza.get(k2);
            }
            V v = (V) a(k2);
            this.zza.put(k2, v);
            return v;
        }
    }
}
