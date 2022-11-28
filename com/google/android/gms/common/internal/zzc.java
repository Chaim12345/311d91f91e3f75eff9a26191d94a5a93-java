package com.google.android.gms.common.internal;

import androidx.annotation.Nullable;
import java.util.ArrayList;
/* loaded from: classes.dex */
public abstract class zzc<TListener> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ BaseGmsClient f5786a;
    @Nullable
    private TListener zza;
    private boolean zzb = false;

    public zzc(BaseGmsClient baseGmsClient, TListener tlistener) {
        this.f5786a = baseGmsClient;
        this.zza = tlistener;
    }

    protected abstract void a(Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void b();

    public final void zze() {
        TListener tlistener;
        synchronized (this) {
            tlistener = this.zza;
            if (this.zzb) {
                String obj = toString();
                StringBuilder sb = new StringBuilder(obj.length() + 47);
                sb.append("Callback proxy ");
                sb.append(obj);
                sb.append(" being reused. This is not safe.");
            }
        }
        if (tlistener != null) {
            try {
                a(tlistener);
            } catch (RuntimeException e2) {
                throw e2;
            }
        }
        synchronized (this) {
            this.zzb = true;
        }
        zzg();
    }

    public final void zzf() {
        synchronized (this) {
            this.zza = null;
        }
    }

    public final void zzg() {
        ArrayList arrayList;
        ArrayList arrayList2;
        zzf();
        arrayList = this.f5786a.zzt;
        synchronized (arrayList) {
            arrayList2 = this.f5786a.zzt;
            arrayList2.remove(this);
        }
    }
}
