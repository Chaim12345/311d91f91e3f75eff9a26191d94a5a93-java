package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;
/* loaded from: classes.dex */
final class zzhd extends ContentObserver {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzhe f6071a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzhd(zzhe zzheVar, Handler handler) {
        super(null);
        this.f6071a = zzheVar;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        this.f6071a.zzf();
    }
}
