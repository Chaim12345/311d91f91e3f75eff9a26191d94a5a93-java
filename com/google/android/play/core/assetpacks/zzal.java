package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import java.util.List;
/* loaded from: classes2.dex */
class zzal extends com.google.android.play.core.internal.zzv {

    /* renamed from: a  reason: collision with root package name */
    final com.google.android.play.core.tasks.zzi f7793a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzaw f7794b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzal(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar) {
        this.f7794b = zzawVar;
        this.f7793a = zziVar;
    }

    @Override // com.google.android.play.core.internal.zzw
    public final void zzb(int i2, Bundle bundle) {
        zzaw.i(this.f7794b).zzs(this.f7793a);
        zzaw.h().zzd("onCancelDownload(%d)", Integer.valueOf(i2));
    }

    @Override // com.google.android.play.core.internal.zzw
    public final void zzc(Bundle bundle) {
        zzaw.i(this.f7794b).zzs(this.f7793a);
        zzaw.h().zzd("onCancelDownloads()", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.zzw
    public void zzd(Bundle bundle) {
        zzaw.i(this.f7794b).zzs(this.f7793a);
        int i2 = bundle.getInt("error_code");
        zzaw.h().zzb("onError(%d)", Integer.valueOf(i2));
        this.f7793a.zzd(new AssetPackException(i2));
    }

    @Override // com.google.android.play.core.internal.zzw
    public void zze(Bundle bundle, Bundle bundle2) {
        zzaw.i(this.f7794b).zzs(this.f7793a);
        zzaw.h().zzd("onGetChunkFileDescriptor", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.zzw
    public final void zzf(int i2, Bundle bundle) {
        zzaw.i(this.f7794b).zzs(this.f7793a);
        zzaw.h().zzd("onGetSession(%d)", Integer.valueOf(i2));
    }

    @Override // com.google.android.play.core.internal.zzw
    public void zzg(List list) {
        zzaw.i(this.f7794b).zzs(this.f7793a);
        zzaw.h().zzd("onGetSessionStates", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.zzw
    public void zzh(Bundle bundle, Bundle bundle2) {
        zzaw.j(this.f7794b).zzs(this.f7793a);
        zzaw.h().zzd("onKeepAlive(%b)", Boolean.valueOf(bundle.getBoolean("keep_alive")));
    }

    @Override // com.google.android.play.core.internal.zzw
    public final void zzi(Bundle bundle, Bundle bundle2) {
        zzaw.i(this.f7794b).zzs(this.f7793a);
        zzaw.h().zzd("onNotifyChunkTransferred(%s, %s, %d, session=%d)", bundle.getString("module_name"), bundle.getString("slice_id"), Integer.valueOf(bundle.getInt("chunk_number")), Integer.valueOf(bundle.getInt("session_id")));
    }

    @Override // com.google.android.play.core.internal.zzw
    public final void zzj(Bundle bundle, Bundle bundle2) {
        zzaw.i(this.f7794b).zzs(this.f7793a);
        zzaw.h().zzd("onNotifyModuleCompleted(%s, sessionId=%d)", bundle.getString("module_name"), Integer.valueOf(bundle.getInt("session_id")));
    }

    @Override // com.google.android.play.core.internal.zzw
    public final void zzk(Bundle bundle, Bundle bundle2) {
        zzaw.i(this.f7794b).zzs(this.f7793a);
        zzaw.h().zzd("onNotifySessionFailed(%d)", Integer.valueOf(bundle.getInt("session_id")));
    }

    @Override // com.google.android.play.core.internal.zzw
    public final void zzl(Bundle bundle, Bundle bundle2) {
        zzaw.i(this.f7794b).zzs(this.f7793a);
        zzaw.h().zzd("onRemoveModule()", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.zzw
    public void zzm(Bundle bundle, Bundle bundle2) {
        zzaw.i(this.f7794b).zzs(this.f7793a);
        zzaw.h().zzd("onRequestDownloadInfo()", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.zzw
    public void zzn(int i2, Bundle bundle) {
        zzaw.i(this.f7794b).zzs(this.f7793a);
        zzaw.h().zzd("onStartDownload(%d)", Integer.valueOf(i2));
    }
}
