package com.google.firebase.analytics;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.measurement.zzee;
import java.util.concurrent.Callable;
/* loaded from: classes2.dex */
final class zzb implements Callable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ FirebaseAnalytics f9882a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzb(FirebaseAnalytics firebaseAnalytics) {
        this.f9882a = firebaseAnalytics;
    }

    @Override // java.util.concurrent.Callable
    @Nullable
    public final /* bridge */ /* synthetic */ Object call() {
        zzee zzeeVar;
        zzeeVar = this.f9882a.zzb;
        return zzeeVar.zzk();
    }
}
