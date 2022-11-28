package com.google.android.play.core.splitinstall.testing;

import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzo implements com.google.android.play.core.splitinstall.zzf {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ List f7893a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ List f7894b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ long f7895c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ boolean f7896d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ List f7897e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ FakeSplitInstallManager f7898f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(FakeSplitInstallManager fakeSplitInstallManager, List list, List list2, long j2, boolean z, List list3) {
        this.f7898f = fakeSplitInstallManager;
        this.f7893a = list;
        this.f7894b = list2;
        this.f7895c = j2;
        this.f7896d = z;
        this.f7897e = list3;
    }

    @Override // com.google.android.play.core.splitinstall.zzf
    public final void zza() {
        this.f7898f.zzr(this.f7893a, this.f7894b, this.f7895c);
    }

    @Override // com.google.android.play.core.splitinstall.zzf
    public final void zzb(int i2) {
        this.f7898f.zzs(6, i2, null, null, null, null, null);
    }

    @Override // com.google.android.play.core.splitinstall.zzf
    public final void zzc() {
        if (this.f7896d) {
            return;
        }
        this.f7898f.zzp(this.f7897e, this.f7893a, this.f7894b, this.f7895c, true);
    }
}
