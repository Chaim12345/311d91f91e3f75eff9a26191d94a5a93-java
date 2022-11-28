package com.google.android.gms.common.wrappers;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Binder;
import android.os.Process;
import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.PlatformVersion;
@KeepForSdk
/* loaded from: classes.dex */
public class PackageManagerWrapper {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    protected final Context f5818a;

    public PackageManagerWrapper(@NonNull Context context) {
        this.f5818a = context;
    }

    @KeepForSdk
    public int checkCallingOrSelfPermission(@NonNull String str) {
        return this.f5818a.checkCallingOrSelfPermission(str);
    }

    @KeepForSdk
    public int checkPermission(@NonNull String str, @NonNull String str2) {
        return this.f5818a.getPackageManager().checkPermission(str, str2);
    }

    @NonNull
    @KeepForSdk
    public ApplicationInfo getApplicationInfo(@NonNull String str, int i2) {
        return this.f5818a.getPackageManager().getApplicationInfo(str, i2);
    }

    @NonNull
    @KeepForSdk
    public CharSequence getApplicationLabel(@NonNull String str) {
        return this.f5818a.getPackageManager().getApplicationLabel(this.f5818a.getPackageManager().getApplicationInfo(str, 0));
    }

    @NonNull
    @KeepForSdk
    public Pair<CharSequence, Drawable> getApplicationLabelAndIcon(@NonNull String str) {
        ApplicationInfo applicationInfo = this.f5818a.getPackageManager().getApplicationInfo(str, 0);
        return Pair.create(this.f5818a.getPackageManager().getApplicationLabel(applicationInfo), this.f5818a.getPackageManager().getApplicationIcon(applicationInfo));
    }

    @NonNull
    @KeepForSdk
    public PackageInfo getPackageInfo(@NonNull String str, int i2) {
        return this.f5818a.getPackageManager().getPackageInfo(str, i2);
    }

    @KeepForSdk
    public boolean isCallerInstantApp() {
        String nameForUid;
        if (Binder.getCallingUid() == Process.myUid()) {
            return InstantApps.isInstantApp(this.f5818a);
        }
        if (!PlatformVersion.isAtLeastO() || (nameForUid = this.f5818a.getPackageManager().getNameForUid(Binder.getCallingUid())) == null) {
            return false;
        }
        return this.f5818a.getPackageManager().isInstantApp(nameForUid);
    }

    @TargetApi(19)
    public final boolean zza(int i2, @NonNull String str) {
        if (PlatformVersion.isAtLeastKitKat()) {
            try {
                AppOpsManager appOpsManager = (AppOpsManager) this.f5818a.getSystemService("appops");
                if (appOpsManager != null) {
                    appOpsManager.checkPackage(i2, str);
                    return true;
                }
                throw new NullPointerException("context.getSystemService(Context.APP_OPS_SERVICE) is null");
            } catch (SecurityException unused) {
                return false;
            }
        }
        String[] packagesForUid = this.f5818a.getPackageManager().getPackagesForUid(i2);
        if (str != null && packagesForUid != null) {
            for (String str2 : packagesForUid) {
                if (str.equals(str2)) {
                    return true;
                }
            }
        }
        return false;
    }
}