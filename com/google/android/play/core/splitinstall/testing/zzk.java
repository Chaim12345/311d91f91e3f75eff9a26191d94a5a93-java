package com.google.android.play.core.splitinstall.testing;

import java.io.File;
import java.io.FileFilter;
/* loaded from: classes2.dex */
public final /* synthetic */ class zzk implements FileFilter {
    public static final /* synthetic */ zzk zza = new zzk();

    private /* synthetic */ zzk() {
    }

    @Override // java.io.FileFilter
    public final boolean accept(File file) {
        int i2 = FakeSplitInstallManager.zza;
        return file.getName().endsWith(".apk");
    }
}
