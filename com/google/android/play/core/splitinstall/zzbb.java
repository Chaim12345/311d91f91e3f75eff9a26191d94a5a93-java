package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import com.google.android.play.core.internal.zzcb;
import java.util.List;
/* loaded from: classes2.dex */
class zzbb extends zzcb {

    /* renamed from: a  reason: collision with root package name */
    final com.google.android.play.core.tasks.zzi f7923a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzbc f7924b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbb(zzbc zzbcVar, com.google.android.play.core.tasks.zzi zziVar) {
        this.f7924b = zzbcVar;
        this.f7923a = zziVar;
    }

    public void zzb(int i2, Bundle bundle) {
        com.google.android.play.core.internal.zzag zzagVar;
        this.f7924b.f7925a.zzs(this.f7923a);
        zzagVar = zzbc.zzb;
        zzagVar.zzd("onCancelInstall(%d)", Integer.valueOf(i2));
    }

    public void zzc(Bundle bundle) {
        com.google.android.play.core.internal.zzag zzagVar;
        this.f7924b.f7925a.zzs(this.f7923a);
        zzagVar = zzbc.zzb;
        zzagVar.zzd("onDeferredInstall", new Object[0]);
    }

    public void zzd(Bundle bundle) {
        com.google.android.play.core.internal.zzag zzagVar;
        this.f7924b.f7925a.zzs(this.f7923a);
        zzagVar = zzbc.zzb;
        zzagVar.zzd("onDeferredLanguageInstall", new Object[0]);
    }

    public void zze(Bundle bundle) {
        com.google.android.play.core.internal.zzag zzagVar;
        this.f7924b.f7925a.zzs(this.f7923a);
        zzagVar = zzbc.zzb;
        zzagVar.zzd("onDeferredLanguageUninstall", new Object[0]);
    }

    public void zzf(Bundle bundle) {
        com.google.android.play.core.internal.zzag zzagVar;
        this.f7924b.f7925a.zzs(this.f7923a);
        zzagVar = zzbc.zzb;
        zzagVar.zzd("onDeferredUninstall", new Object[0]);
    }

    public void zzg(int i2, Bundle bundle) {
        com.google.android.play.core.internal.zzag zzagVar;
        this.f7924b.f7925a.zzs(this.f7923a);
        zzagVar = zzbc.zzb;
        zzagVar.zzd("onGetSession(%d)", Integer.valueOf(i2));
    }

    public void zzh(List list) {
        com.google.android.play.core.internal.zzag zzagVar;
        this.f7924b.f7925a.zzs(this.f7923a);
        zzagVar = zzbc.zzb;
        zzagVar.zzd("onGetSessionStates", new Object[0]);
    }

    public void zzi(int i2, Bundle bundle) {
        com.google.android.play.core.internal.zzag zzagVar;
        this.f7924b.f7925a.zzs(this.f7923a);
        zzagVar = zzbc.zzb;
        zzagVar.zzd("onStartInstall(%d)", Integer.valueOf(i2));
    }

    @Override // com.google.android.play.core.internal.zzcc
    public final void zzj(int i2, Bundle bundle) {
        com.google.android.play.core.internal.zzag zzagVar;
        this.f7924b.f7925a.zzs(this.f7923a);
        zzagVar = zzbc.zzb;
        zzagVar.zzd("onCompleteInstall(%d)", Integer.valueOf(i2));
    }

    @Override // com.google.android.play.core.internal.zzcc
    public final void zzk(Bundle bundle) {
        com.google.android.play.core.internal.zzag zzagVar;
        this.f7924b.f7925a.zzs(this.f7923a);
        zzagVar = zzbc.zzb;
        zzagVar.zzd("onCompleteInstallForAppUpdate", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.zzcc
    public final void zzl(Bundle bundle) {
        com.google.android.play.core.internal.zzag zzagVar;
        this.f7924b.f7925a.zzs(this.f7923a);
        int i2 = bundle.getInt("error_code");
        zzagVar = zzbc.zzb;
        zzagVar.zzb("onError(%d)", Integer.valueOf(i2));
        this.f7923a.zzd(new SplitInstallException(i2));
    }

    @Override // com.google.android.play.core.internal.zzcc
    public final void zzm(Bundle bundle) {
        com.google.android.play.core.internal.zzag zzagVar;
        this.f7924b.f7925a.zzs(this.f7923a);
        zzagVar = zzbc.zzb;
        zzagVar.zzd("onGetSplitsForAppUpdate", new Object[0]);
    }
}
