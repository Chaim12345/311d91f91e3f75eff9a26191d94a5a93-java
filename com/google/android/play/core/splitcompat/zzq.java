package com.google.android.play.core.splitcompat;

import android.util.Log;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzq implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Set f7891a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ SplitCompat f7892b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzq(SplitCompat splitCompat, Set set) {
        this.f7892b = splitCompat;
        this.f7891a = set;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.f7892b.zzg(this.f7891a);
        } catch (Exception e2) {
            Log.e("SplitCompat", "Failed to remove from splitcompat storage split that is already installed", e2);
        }
    }
}
