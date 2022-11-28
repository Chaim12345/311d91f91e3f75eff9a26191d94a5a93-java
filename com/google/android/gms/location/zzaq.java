package com.google.android.gms.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzaq extends zzaw {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ListenerHolder f6622a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ FusedLocationProviderClient f6623b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaq(FusedLocationProviderClient fusedLocationProviderClient, ListenerHolder listenerHolder) {
        this.f6623b = fusedLocationProviderClient;
        this.f6622a = listenerHolder;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* bridge */ /* synthetic */ void accept(Object obj, Object obj2) {
        com.google.android.gms.internal.location.zzbe zzbeVar = (com.google.android.gms.internal.location.zzbe) obj;
        TaskCompletionSource taskCompletionSource = (TaskCompletionSource) obj2;
        if (b()) {
            zzar zzarVar = new zzar(this.f6623b, taskCompletionSource);
            ListenerHolder.ListenerKey listenerKey = this.f6622a.getListenerKey();
            if (listenerKey != null) {
                zzbeVar.zzy(listenerKey, zzarVar);
            }
        }
    }
}
