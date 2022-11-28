package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zaab implements PendingResult.StatusListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ BasePendingResult f5632a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zaad f5633b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zaab(zaad zaadVar, BasePendingResult basePendingResult) {
        this.f5633b = zaadVar;
        this.f5632a = basePendingResult;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status status) {
        Map map;
        map = this.f5633b.zaa;
        map.remove(this.f5632a);
    }
}
