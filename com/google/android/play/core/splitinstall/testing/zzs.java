package com.google.android.play.core.splitinstall.testing;

import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode;
import java.util.Collections;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class zzs {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract zzs a(@SplitInstallErrorCode int i2);

    abstract zzs b(Map map);

    abstract zzt c();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Map d();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzt e() {
        b(Collections.unmodifiableMap(d()));
        return c();
    }
}
