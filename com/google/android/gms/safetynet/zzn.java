package com.google.android.gms.safetynet;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.safetynet.zzaf;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes2.dex */
public final /* synthetic */ class zzn implements RemoteCall {
    public static final /* synthetic */ zzn zza = new zzn();

    private /* synthetic */ zzn() {
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object obj, Object obj2) {
        TaskCompletionSource taskCompletionSource = (TaskCompletionSource) obj2;
        ((com.google.android.gms.internal.safetynet.zzh) ((zzaf) obj).getService()).zzi();
    }
}
