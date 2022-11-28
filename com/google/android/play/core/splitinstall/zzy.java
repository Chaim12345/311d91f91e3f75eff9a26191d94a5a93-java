package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
final class zzy implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SplitInstallRequest f7934a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzaa f7935b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzy(zzaa zzaaVar, SplitInstallRequest splitInstallRequest) {
        this.f7935b = zzaaVar;
        this.f7934a = splitInstallRequest;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzx zzxVar;
        List zze;
        zzxVar = this.f7935b.zzb;
        List<String> moduleNames = this.f7934a.getModuleNames();
        zze = zzaa.zze(this.f7934a.getLanguages());
        Bundle bundle = new Bundle();
        bundle.putInt("session_id", 0);
        bundle.putInt(NotificationCompat.CATEGORY_STATUS, 5);
        bundle.putInt("error_code", 0);
        if (!moduleNames.isEmpty()) {
            bundle.putStringArrayList("module_names", new ArrayList<>(moduleNames));
        }
        if (!zze.isEmpty()) {
            bundle.putStringArrayList("languages", new ArrayList<>(zze));
        }
        bundle.putLong("total_bytes_to_download", 0L);
        bundle.putLong("bytes_downloaded", 0L);
        zzxVar.zzm(SplitInstallSessionState.zzd(bundle));
    }
}
