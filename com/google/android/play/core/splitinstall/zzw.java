package com.google.android.play.core.splitinstall;
/* loaded from: classes2.dex */
final class zzw implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SplitInstallSessionState f7930a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ int f7931b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ int f7932c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ zzx f7933d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzw(zzx zzxVar, SplitInstallSessionState splitInstallSessionState, int i2, int i3) {
        this.f7933d = zzxVar;
        this.f7930a = splitInstallSessionState;
        this.f7931b = i2;
        this.f7932c = i3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzx zzxVar = this.f7933d;
        SplitInstallSessionState splitInstallSessionState = this.f7930a;
        zzxVar.zzm(new zza(splitInstallSessionState.sessionId(), this.f7931b, this.f7932c, splitInstallSessionState.bytesDownloaded(), splitInstallSessionState.totalBytesToDownload(), splitInstallSessionState.b(), splitInstallSessionState.a(), splitInstallSessionState.resolutionIntent(), splitInstallSessionState.c()));
    }
}
