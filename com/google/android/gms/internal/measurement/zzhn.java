package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
/* loaded from: classes.dex */
public final class zzhn {

    /* renamed from: a  reason: collision with root package name */
    static volatile zzid f6072a = zzid.zzc();
    private static final Object zzb = new Object();

    /* JADX WARN: Can't wrap try/catch for region: R(10:18|(1:20)(7:32|(1:34)(1:40)|35|(2:37|(1:39))|27|28|29)|21|22|23|24|(1:26)|27|28|29) */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean zza(Context context, Uri uri) {
        String authority = uri.getAuthority();
        boolean z = false;
        if (!"com.google.android.gms.phenotype".equals(authority)) {
            Log.e("PhenotypeClientHelper", String.valueOf(authority).concat(" is an unsupported authority. Only com.google.android.gms.phenotype authority is supported."));
            return false;
        }
        if (!f6072a.zzb()) {
            synchronized (zzb) {
                if (f6072a.zzb()) {
                    return ((Boolean) f6072a.zza()).booleanValue();
                }
                if (!"com.google.android.gms".equals(context.getPackageName())) {
                    ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.phenotype", Build.VERSION.SDK_INT < 29 ? 0 : 268435456);
                    if (resolveContentProvider != null) {
                        if (!"com.google.android.gms".equals(resolveContentProvider.packageName)) {
                        }
                    }
                    f6072a = zzid.zzd(Boolean.valueOf(z));
                }
                if ((context.getPackageManager().getApplicationInfo("com.google.android.gms", 0).flags & 129) != 0) {
                    z = true;
                }
                f6072a = zzid.zzd(Boolean.valueOf(z));
            }
        }
        return ((Boolean) f6072a.zza()).booleanValue();
    }
}
