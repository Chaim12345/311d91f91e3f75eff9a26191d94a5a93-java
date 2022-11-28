package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;
/* loaded from: classes2.dex */
public final class zzfs {

    /* renamed from: a  reason: collision with root package name */
    final zzgk f6742a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfs(zzll zzllVar) {
        this.f6742a = zzllVar.E();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public final boolean a() {
        try {
            PackageManagerWrapper packageManager = Wrappers.packageManager(this.f6742a.zzau());
            if (packageManager != null) {
                return packageManager.getPackageInfo("com.android.vending", 128).versionCode >= 80837300;
            }
            this.f6742a.zzay().zzj().zza("Failed to get PackageManager for Install Referrer Play Store compatibility check");
            return false;
        } catch (Exception e2) {
            this.f6742a.zzay().zzj().zzb("Failed to retrieve Play Store version for Install Referrer", e2);
            return false;
        }
    }
}
