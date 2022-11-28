package com.google.android.play.core.listener;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.Nullable;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.internal.zzce;
import com.google.android.play.core.internal.zzci;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/* loaded from: classes2.dex */
public abstract class zzc {

    /* renamed from: a  reason: collision with root package name */
    protected final zzag f7869a;
    private final IntentFilter zzc;
    private final Context zzd;

    /* renamed from: b  reason: collision with root package name */
    protected final Set f7870b = new HashSet();
    @Nullable
    private zzb zze = null;
    private volatile boolean zzf = false;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzc(zzag zzagVar, IntentFilter intentFilter, Context context) {
        this.f7869a = zzagVar;
        this.zzc = intentFilter;
        this.zzd = zzce.zza(context);
    }

    private final void zzb() {
        zzb zzbVar;
        if ((this.zzf || !this.f7870b.isEmpty()) && this.zze == null) {
            zzb zzbVar2 = new zzb(this, null);
            this.zze = zzbVar2;
            this.zzd.registerReceiver(zzbVar2, this.zzc);
        }
        if (this.zzf || !this.f7870b.isEmpty() || (zzbVar = this.zze) == null) {
            return;
        }
        this.zzd.unregisterReceiver(zzbVar);
        this.zze = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void a(Context context, Intent intent);

    public final synchronized void zze() {
        this.f7869a.zzd("clearListeners", new Object[0]);
        this.f7870b.clear();
        zzb();
    }

    public final synchronized void zzf(StateUpdatedListener stateUpdatedListener) {
        this.f7869a.zzd("registerListener", new Object[0]);
        zzci.zza(stateUpdatedListener, "Registered Play Core listener should not be null.");
        this.f7870b.add(stateUpdatedListener);
        zzb();
    }

    public final synchronized void zzg(boolean z) {
        this.zzf = z;
        zzb();
    }

    public final synchronized void zzh(StateUpdatedListener stateUpdatedListener) {
        this.f7869a.zzd("unregisterListener", new Object[0]);
        zzci.zza(stateUpdatedListener, "Unregistered Play Core listener should not be null.");
        this.f7870b.remove(stateUpdatedListener);
        zzb();
    }

    public final synchronized void zzi(Object obj) {
        Iterator it = new HashSet(this.f7870b).iterator();
        while (it.hasNext()) {
            ((StateUpdatedListener) it.next()).onStateUpdate(obj);
        }
    }

    public final synchronized boolean zzj() {
        return this.zze != null;
    }
}
