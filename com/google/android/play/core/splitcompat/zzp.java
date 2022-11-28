package com.google.android.play.core.splitcompat;

import android.util.Log;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzp implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SplitCompat f7890a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzp(SplitCompat splitCompat) {
        this.f7890a = splitCompat;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zze zzeVar;
        try {
            zzeVar = this.f7890a.zzc;
            zzeVar.zzk();
        } catch (Exception e2) {
            Log.e("SplitCompat", "Failed to cleanup splitcompat storage", e2);
        }
    }
}
