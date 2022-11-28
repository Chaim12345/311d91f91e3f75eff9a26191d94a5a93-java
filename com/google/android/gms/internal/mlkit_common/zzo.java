package com.google.android.gms.internal.mlkit_common;

import android.os.Build;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructStat;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.concurrent.Callable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzo {

    /* renamed from: a  reason: collision with root package name */
    final long f6239a;

    /* renamed from: b  reason: collision with root package name */
    final long f6240b;

    /* renamed from: c  reason: collision with root package name */
    final boolean f6241c;

    private zzo(long j2, long j3, boolean z) {
        this.f6239a = j2;
        this.f6240b = j3;
        this.f6241c = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzo a(final FileDescriptor fileDescriptor) {
        if (Build.VERSION.SDK_INT >= 21) {
            StructStat structStat = (StructStat) zzc(new Callable() { // from class: com.google.android.gms.internal.mlkit_common.zzi
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return Os.fstat(fileDescriptor);
                }
            });
            return new zzo(structStat.st_dev, structStat.st_ino, OsConstants.S_ISLNK(structStat.st_mode));
        }
        return zzm.a(fileDescriptor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzo b(final String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            StructStat structStat = (StructStat) zzc(new Callable() { // from class: com.google.android.gms.internal.mlkit_common.zzj
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return Os.lstat(str);
                }
            });
            return new zzo(structStat.st_dev, structStat.st_ino, OsConstants.S_ISLNK(structStat.st_mode));
        }
        return zzm.d(str);
    }

    private static Object zzc(Callable callable) {
        try {
            return callable.call();
        } catch (Throwable th) {
            throw new IOException(th);
        }
    }
}
