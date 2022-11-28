package com.google.android.play.core.missingsplits;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import com.google.android.play.core.internal.zzag;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes2.dex */
final class zzb implements MissingSplitsManager {
    private static final zzag zza = new zzag("MissingSplitsManagerImpl");
    private final Context zzb;
    private final Runtime zzc;
    private final zza zzd;
    private final AtomicReference zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzb(Context context, Runtime runtime, zza zzaVar, AtomicReference atomicReference) {
        this.zzb = context;
        this.zzc = runtime;
        this.zzd = zzaVar;
        this.zze = atomicReference;
    }

    @TargetApi(21)
    private final List zza() {
        List<ActivityManager.AppTask> appTasks = ((ActivityManager) this.zzb.getSystemService("activity")).getAppTasks();
        return appTasks != null ? appTasks : Collections.emptyList();
    }

    @Override // com.google.android.play.core.missingsplits.MissingSplitsManager
    public final boolean disableAppIfMissingRequiredSplits() {
        boolean booleanValue;
        boolean z;
        Intent intent;
        Class<?> cls;
        ApplicationInfo applicationInfo;
        Bundle bundle;
        Set set;
        String[] strArr;
        boolean z2;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            synchronized (this.zze) {
                if (((Boolean) this.zze.get()) == null) {
                    AtomicReference atomicReference = this.zze;
                    if (i2 >= 21) {
                        try {
                            applicationInfo = this.zzb.getPackageManager().getApplicationInfo(this.zzb.getPackageName(), 128);
                        } catch (PackageManager.NameNotFoundException unused) {
                            zza.zze("App '%s' is not found in the PackageManager", this.zzb.getPackageName());
                        }
                        if (applicationInfo != null && (bundle = applicationInfo.metaData) != null) {
                            if (Boolean.TRUE.equals(bundle.get("com.android.vending.splits.required"))) {
                                if (i2 >= 21) {
                                    try {
                                        PackageInfo packageInfo = this.zzb.getPackageManager().getPackageInfo(this.zzb.getPackageName(), 0);
                                        HashSet hashSet = new HashSet();
                                        if (packageInfo != null && (strArr = packageInfo.splitNames) != null) {
                                            Collections.addAll(hashSet, strArr);
                                        }
                                        set = hashSet;
                                    } catch (PackageManager.NameNotFoundException unused2) {
                                        zza.zze("App '%s' is not found in PackageManager", this.zzb.getPackageName());
                                    }
                                    if (!set.isEmpty() || (set.size() == 1 && set.contains(""))) {
                                        z2 = true;
                                        atomicReference.set(Boolean.valueOf(z2));
                                    }
                                }
                                set = Collections.emptySet();
                                if (!set.isEmpty()) {
                                }
                                z2 = true;
                                atomicReference.set(Boolean.valueOf(z2));
                            }
                        }
                    }
                    z2 = false;
                    atomicReference.set(Boolean.valueOf(z2));
                }
                booleanValue = ((Boolean) this.zze.get()).booleanValue();
            }
            if (!booleanValue) {
                if (this.zzd.c()) {
                    this.zzd.b();
                    this.zzc.exit(0);
                }
                return false;
            }
            Iterator it = zza().iterator();
            while (true) {
                if (it.hasNext()) {
                    ActivityManager.AppTask appTask = (ActivityManager.AppTask) it.next();
                    if (appTask.getTaskInfo() != null && appTask.getTaskInfo().baseIntent != null && appTask.getTaskInfo().baseIntent.getComponent() != null && PlayCoreMissingSplitsActivity.class.getName().equals(appTask.getTaskInfo().baseIntent.getComponent().getClassName())) {
                        break;
                    }
                } else {
                    loop1: for (ActivityManager.AppTask appTask2 : zza()) {
                        ActivityManager.RecentTaskInfo taskInfo = appTask2.getTaskInfo();
                        if (taskInfo != null && (intent = taskInfo.baseIntent) != null && intent.getComponent() != null) {
                            ComponentName component = taskInfo.baseIntent.getComponent();
                            String className = component.getClassName();
                            try {
                                cls = Class.forName(className);
                            } catch (ClassNotFoundException unused3) {
                                zza.zze("ClassNotFoundException when scanning class hierarchy of '%s'", className);
                                try {
                                    if (this.zzb.getPackageManager().getActivityInfo(component, 0) != null) {
                                    }
                                } catch (PackageManager.NameNotFoundException unused4) {
                                    continue;
                                }
                            }
                            while (cls != null) {
                                if (cls.equals(Activity.class)) {
                                    z = true;
                                    break;
                                }
                                Class<? super Object> superclass = cls.getSuperclass();
                                cls = superclass != cls ? superclass : null;
                            }
                            continue;
                        }
                    }
                    z = false;
                    this.zzd.a();
                    for (ActivityManager.AppTask appTask3 : zza()) {
                        appTask3.finishAndRemoveTask();
                    }
                    if (z) {
                        this.zzb.getPackageManager().setComponentEnabledSetting(new ComponentName(this.zzb, PlayCoreMissingSplitsActivity.class), 1, 1);
                        this.zzb.startActivity(new Intent(this.zzb, PlayCoreMissingSplitsActivity.class).addFlags(884998144));
                    }
                    this.zzc.exit(0);
                }
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x007f A[Catch: all -> 0x00b6, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x000d, B:34:0x00a1, B:9:0x001a, B:10:0x0020, B:12:0x002e, B:14:0x0032, B:17:0x0042, B:26:0x0079, B:28:0x007f, B:30:0x0085, B:19:0x0047, B:21:0x005e, B:23:0x0062, B:25:0x0067, B:33:0x008e, B:35:0x00a8, B:36:0x00b4), top: B:43:0x0003, inners: #0, #2 }] */
    @Override // com.google.android.play.core.missingsplits.MissingSplitsManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean isMissingRequiredSplits() {
        boolean booleanValue;
        ApplicationInfo applicationInfo;
        Bundle bundle;
        Set set;
        String[] strArr;
        synchronized (this.zze) {
            if (((Boolean) this.zze.get()) == null) {
                AtomicReference atomicReference = this.zze;
                int i2 = Build.VERSION.SDK_INT;
                boolean z = true;
                if (i2 >= 21) {
                    try {
                        applicationInfo = this.zzb.getPackageManager().getApplicationInfo(this.zzb.getPackageName(), 128);
                    } catch (PackageManager.NameNotFoundException unused) {
                        zza.zze("App '%s' is not found in the PackageManager", this.zzb.getPackageName());
                    }
                    if (applicationInfo != null && (bundle = applicationInfo.metaData) != null) {
                        if (Boolean.TRUE.equals(bundle.get("com.android.vending.splits.required"))) {
                            if (i2 >= 21) {
                                try {
                                    PackageInfo packageInfo = this.zzb.getPackageManager().getPackageInfo(this.zzb.getPackageName(), 0);
                                    HashSet hashSet = new HashSet();
                                    if (packageInfo != null && (strArr = packageInfo.splitNames) != null) {
                                        Collections.addAll(hashSet, strArr);
                                    }
                                    set = hashSet;
                                } catch (PackageManager.NameNotFoundException unused2) {
                                    zza.zze("App '%s' is not found in PackageManager", this.zzb.getPackageName());
                                }
                                if (!set.isEmpty()) {
                                    if (set.size() == 1 && set.contains("")) {
                                    }
                                }
                                atomicReference.set(Boolean.valueOf(z));
                            }
                            set = Collections.emptySet();
                            if (!set.isEmpty()) {
                            }
                            atomicReference.set(Boolean.valueOf(z));
                        }
                    }
                }
                z = false;
                atomicReference.set(Boolean.valueOf(z));
            }
            booleanValue = ((Boolean) this.zze.get()).booleanValue();
        }
        return booleanValue;
    }
}
