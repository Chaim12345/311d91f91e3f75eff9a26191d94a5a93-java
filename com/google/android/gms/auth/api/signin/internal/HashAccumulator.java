package com.google.android.gms.auth.api.signin.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
@KeepForSdk
/* loaded from: classes.dex */
public class HashAccumulator {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    static int f5601a = 31;
    private int zab = 1;

    @NonNull
    @KeepForSdk
    public HashAccumulator addObject(@Nullable Object obj) {
        this.zab = (f5601a * this.zab) + (obj == null ? 0 : obj.hashCode());
        return this;
    }

    @KeepForSdk
    public int hash() {
        return this.zab;
    }

    @NonNull
    public final HashAccumulator zaa(boolean z) {
        this.zab = (f5601a * this.zab) + (z ? 1 : 0);
        return this;
    }
}
