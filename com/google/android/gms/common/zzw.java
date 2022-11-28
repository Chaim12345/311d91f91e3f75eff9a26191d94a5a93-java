package com.google.android.gms.common;

import android.util.Log;
import androidx.annotation.NonNull;
import javax.annotation.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class zzw {
    private static final zzw zzd = new zzw(true, null, null);

    /* renamed from: a  reason: collision with root package name */
    final boolean f5825a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    final String f5826b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    final Throwable f5827c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzw(boolean z, @Nullable String str, @Nullable Throwable th) {
        this.f5825a = z;
        this.f5826b = str;
        this.f5827c = th;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzw b() {
        return zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzw c(@NonNull String str) {
        return new zzw(false, str, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzw d(@NonNull String str, @NonNull Throwable th) {
        return new zzw(false, str, th);
    }

    @Nullable
    String a() {
        return this.f5826b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void e() {
        if (this.f5825a || !Log.isLoggable("GoogleCertificatesRslt", 3)) {
            return;
        }
        Throwable th = this.f5827c;
        a();
    }
}
