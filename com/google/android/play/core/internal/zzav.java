package com.google.android.play.core.internal;

import android.util.Log;
import java.util.List;
/* loaded from: classes2.dex */
final class zzav implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ List f7865a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ com.google.android.play.core.splitinstall.zzf f7866b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzaw f7867c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzav(zzaw zzawVar, List list, com.google.android.play.core.splitinstall.zzf zzfVar) {
        this.f7867c = zzawVar;
        this.f7865a = list;
        this.f7866b = zzfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzay zzayVar;
        try {
            zzayVar = this.f7867c.zzc;
            if (zzayVar.zzb(this.f7865a)) {
                zzaw.c(this.f7867c, this.f7866b);
            } else {
                zzaw.b(this.f7867c, this.f7865a, this.f7866b);
            }
        } catch (Exception e2) {
            Log.e("SplitCompat", "Error checking verified files.", e2);
            this.f7866b.zzb(-11);
        }
    }
}
