package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.MainThread;
import kotlinx.coroutines.DebugKt;
/* JADX INFO: Access modifiers changed from: package-private */
@TargetApi(14)
@MainThread
/* loaded from: classes2.dex */
public final class zzio implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzip f6891a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzio(zzip zzipVar, zzin zzinVar) {
        this.f6891a = zzipVar;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        Uri data;
        try {
            try {
                this.f6891a.f6809a.zzay().zzj().zza("onActivityCreated");
                Intent intent = activity.getIntent();
                if (intent != null && (data = intent.getData()) != null && data.isHierarchical()) {
                    this.f6891a.f6809a.zzv();
                    String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
                    boolean z = true;
                    String str = true != ("android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) || "https://www.google.com".equals(stringExtra) || "android-app://com.google.appcrawler".equals(stringExtra)) ? DebugKt.DEBUG_PROPERTY_VALUE_AUTO : "gs";
                    String queryParameter = data.getQueryParameter("referrer");
                    if (bundle != null) {
                        z = false;
                    }
                    this.f6891a.f6809a.zzaz().zzp(new zzim(this, z, data, str, queryParameter));
                }
            } catch (RuntimeException e2) {
                this.f6891a.f6809a.zzay().zzd().zzb("Throwable caught in onActivityCreated", e2);
            }
        } finally {
            this.f6891a.f6809a.zzs().zzr(activity, bundle);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        this.f6891a.f6809a.zzs().zzs(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    @MainThread
    public final void onActivityPaused(Activity activity) {
        this.f6891a.f6809a.zzs().zzt(activity);
        zzku zzu = this.f6891a.f6809a.zzu();
        zzu.f6809a.zzaz().zzp(new zzkn(zzu, zzu.f6809a.zzav().elapsedRealtime()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    @MainThread
    public final void onActivityResumed(Activity activity) {
        zzku zzu = this.f6891a.f6809a.zzu();
        zzu.f6809a.zzaz().zzp(new zzkm(zzu, zzu.f6809a.zzav().elapsedRealtime()));
        this.f6891a.f6809a.zzs().zzu(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.f6891a.f6809a.zzs().zzv(activity, bundle);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }
}
