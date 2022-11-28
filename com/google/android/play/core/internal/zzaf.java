package com.google.android.play.core.internal;

import androidx.annotation.GuardedBy;
import com.google.android.play.core.listener.StateUpdatedListener;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes2.dex */
public final class zzaf {
    @GuardedBy("this")

    /* renamed from: a  reason: collision with root package name */
    protected final Set f7857a = new HashSet();

    public final synchronized void zza(StateUpdatedListener stateUpdatedListener) {
        this.f7857a.add(stateUpdatedListener);
    }

    public final synchronized void zzb(StateUpdatedListener stateUpdatedListener) {
        this.f7857a.remove(stateUpdatedListener);
    }

    public final synchronized void zzc(Object obj) {
        for (StateUpdatedListener stateUpdatedListener : this.f7857a) {
            stateUpdatedListener.onStateUpdate(obj);
        }
    }
}
