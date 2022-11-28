package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzbh {
    private static final com.google.android.play.core.internal.zzag zza = new com.google.android.play.core.internal.zzag("AssetPackStorage");
    private static final long zzb;
    private static final long zzc;
    private final Context zzd;
    private final zzed zze;

    static {
        TimeUnit timeUnit = TimeUnit.DAYS;
        zzb = timeUnit.toMillis(14L);
        zzc = timeUnit.toMillis(28L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbh(Context context, zzed zzedVar) {
        this.zzd = context;
        this.zze = zzedVar;
    }

    private static long zzH(File file, boolean z) {
        File[] listFiles;
        if (file.exists()) {
            ArrayList arrayList = new ArrayList();
            if (z && file.listFiles().length > 1) {
                zza.zze("Multiple pack versions found, using highest version code.", new Object[0]);
            }
            try {
                for (File file2 : file.listFiles()) {
                    if (!file2.getName().equals("stale.tmp")) {
                        arrayList.add(Long.valueOf(file2.getName()));
                    }
                }
            } catch (NumberFormatException e2) {
                zza.zzc(e2, "Corrupt asset pack directories.", new Object[0]);
            }
            if (arrayList.isEmpty()) {
                return -1L;
            }
            Collections.sort(arrayList);
            return ((Long) arrayList.get(arrayList.size() - 1)).longValue();
        }
        return -1L;
    }

    private final File zzI(String str) {
        return new File(zzL(), str);
    }

    private final File zzJ(String str, int i2, long j2) {
        return new File(q(str, i2, j2), "merge.tmp");
    }

    private final File zzK(String str, int i2, long j2) {
        return new File(new File(new File(zzM(), str), String.valueOf(i2)), String.valueOf(j2));
    }

    private final File zzL() {
        return new File(this.zzd.getFilesDir(), "assetpacks");
    }

    private final File zzM() {
        return new File(zzL(), "_tmp");
    }

    @RequiresApi(21)
    private static List zzN(PackageInfo packageInfo, String str) {
        ArrayList arrayList = new ArrayList();
        String[] strArr = packageInfo.splitNames;
        if (strArr == null) {
            return arrayList;
        }
        int i2 = (-Arrays.binarySearch(strArr, str)) - 1;
        while (true) {
            String[] strArr2 = packageInfo.splitNames;
            if (i2 >= strArr2.length || !strArr2[i2].startsWith(str)) {
                break;
            }
            arrayList.add(packageInfo.applicationInfo.splitSourceDirs[i2]);
            i2++;
        }
        return arrayList;
    }

    private final List zzO() {
        File[] listFiles;
        ArrayList arrayList = new ArrayList();
        try {
        } catch (IOException e2) {
            zza.zzb("Could not process directory while scanning installed packs. %s", e2);
        }
        if (zzL().exists() && zzL().listFiles() != null) {
            for (File file : zzL().listFiles()) {
                if (!file.getCanonicalPath().equals(zzM().getCanonicalPath())) {
                    arrayList.add(file);
                }
            }
            return arrayList;
        }
        return arrayList;
    }

    private static void zzP(File file) {
        File[] listFiles;
        if (file.listFiles() == null || file.listFiles().length <= 1) {
            return;
        }
        long zzH = zzH(file, false);
        for (File file2 : file.listFiles()) {
            if (!file2.getName().equals(String.valueOf(zzH)) && !file2.getName().equals("stale.tmp")) {
                zzQ(file2);
            }
        }
    }

    private static boolean zzQ(File file) {
        File[] listFiles = file.listFiles();
        boolean z = true;
        if (listFiles != null) {
            for (File file2 : listFiles) {
                z &= zzQ(file2);
            }
        }
        if (file.delete()) {
            return z;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Map A() {
        HashMap hashMap = new HashMap();
        for (File file : zzO()) {
            String name = file.getName();
            int zzH = (int) zzH(zzI(name), true);
            long zzH2 = zzH(n(name, zzH), true);
            if (o(name, zzH, zzH2).exists()) {
                hashMap.put(name, Long.valueOf(zzH2));
            }
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Map B() {
        HashMap hashMap = new HashMap();
        for (String str : C().keySet()) {
            hashMap.put(str, Long.valueOf(j(str)));
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Map C() {
        HashMap hashMap = new HashMap();
        try {
            for (File file : zzO()) {
                AssetPackLocation m2 = m(file.getName());
                if (m2 != null) {
                    hashMap.put(file.getName(), m2);
                }
            }
        } catch (IOException e2) {
            zza.zzb("Could not process directory while scanning installed packs: %s", e2);
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void D() {
        for (File file : zzO()) {
            if (file.listFiles() != null) {
                zzP(file);
                long zzH = zzH(file, false);
                if (this.zze.zza() != zzH) {
                    try {
                        new File(new File(file, String.valueOf(zzH)), "stale.tmp").createNewFile();
                    } catch (IOException unused) {
                        zza.zzb("Could not write staleness marker.", new Object[0]);
                    }
                }
                for (File file2 : file.listFiles()) {
                    zzP(file2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void E() {
        File[] listFiles;
        if (zzM().exists()) {
            for (File file : zzM().listFiles()) {
                if (System.currentTimeMillis() - file.lastModified() > zzb) {
                    zzQ(file);
                } else {
                    zzP(file);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void F() {
        File[] listFiles;
        for (File file : zzO()) {
            if (file.listFiles() != null) {
                for (File file2 : file.listFiles()) {
                    File file3 = new File(file2, "stale.tmp");
                    if (file3.exists() && System.currentTimeMillis() - file3.lastModified() > zzc) {
                        zzQ(file2);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void G() {
        zzQ(zzL());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(String str, int i2, long j2, int i3) {
        File zzJ = zzJ(str, i2, j2);
        Properties properties = new Properties();
        properties.put("numberOfMerges", String.valueOf(i3));
        zzJ.getParentFile().mkdirs();
        zzJ.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(zzJ);
        properties.store(fileOutputStream, (String) null);
        fileOutputStream.close();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(String str, int i2, long j2) {
        File[] listFiles;
        File[] listFiles2;
        File zzI = zzI(str);
        if (zzI.exists()) {
            for (File file : zzI.listFiles()) {
                if (!file.getName().equals(String.valueOf(i2)) && !file.getName().equals("stale.tmp")) {
                    zzQ(file);
                } else if (file.getName().equals(String.valueOf(i2))) {
                    for (File file2 : file.listFiles()) {
                        if (!file2.getName().equals(String.valueOf(j2))) {
                            zzQ(file2);
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c(List list) {
        int zza2 = this.zze.zza();
        for (File file : zzO()) {
            if (!list.contains(file.getName()) && zzH(file, true) != zza2) {
                zzQ(file);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean d(String str) {
        if (zzI(str).exists()) {
            return zzQ(zzI(str));
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean e(String str, int i2, long j2) {
        if (zzK(str, i2, j2).exists()) {
            return zzQ(zzK(str, i2, j2));
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean f(String str, int i2, long j2) {
        if (o(str, i2, j2).exists()) {
            return zzQ(o(str, i2, j2));
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean g(String str) {
        return y(str) != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int h(String str) {
        return (int) zzH(zzI(str), true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int i(String str, int i2, long j2) {
        File zzJ = zzJ(str, i2, j2);
        if (zzJ.exists()) {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(zzJ);
            try {
                properties.load(fileInputStream);
                fileInputStream.close();
                if (properties.getProperty("numberOfMerges") != null) {
                    try {
                        return Integer.parseInt(properties.getProperty("numberOfMerges"));
                    } catch (NumberFormatException e2) {
                        throw new zzck("Merge checkpoint file corrupt.", e2);
                    }
                }
                throw new zzck("Merge checkpoint file corrupt.");
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long j(String str) {
        return zzH(n(str, (int) zzH(zzI(str), true)), true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    @VisibleForTesting
    public final AssetLocation k(String str, String str2, List list) {
        if (list == null) {
            return null;
        }
        String path = new File("assets", str2).getPath();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str3 = (String) it.next();
            try {
                AssetLocation a2 = zzbt.a(str3, path);
                if (a2 != null) {
                    return a2;
                }
            } catch (IOException e2) {
                zza.zzc(e2, "Failed to parse APK file '%s' looking for asset '%s'.", str3, str2);
                return null;
            }
        }
        zza.zza("The asset %s is not present in Asset Pack %s. Searched in APKs: %s", str2, str, list);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final AssetLocation l(String str, String str2, AssetPackLocation assetPackLocation) {
        File file = new File(assetPackLocation.assetsPath(), str2);
        if (file.exists()) {
            return new zzbl(file.getPath(), 0L, file.length());
        }
        zza.zza("The asset %s is not present in Asset Pack %s. Searched in folder: %s", str2, str, assetPackLocation.assetsPath());
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final AssetPackLocation m(String str) {
        String y = y(str);
        if (y == null) {
            return null;
        }
        File file = new File(y, "assets");
        if (file.isDirectory()) {
            return new zzbm(0, y, file.getCanonicalPath());
        }
        zza.zzb("Failed to find assets directory: %s", file);
        return null;
    }

    final File n(String str, int i2) {
        return new File(zzI(str), String.valueOf(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File o(String str, int i2, long j2) {
        return new File(n(str, i2), String.valueOf(j2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File p(String str, int i2, long j2) {
        return new File(o(str, i2, j2), "_metadata");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File q(String str, int i2, long j2) {
        return new File(zzK(str, i2, j2), "_packs");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File r(String str, int i2, long j2) {
        return new File(p(str, i2, j2), "properties.dat");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File s(String str, int i2, long j2) {
        return new File(new File(zzK(str, i2, j2), "_slices"), "_metadata");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File t(String str, int i2, long j2, String str2) {
        return new File(v(str, i2, j2, str2), "checkpoint_ext.dat");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File u(String str, int i2, long j2, String str2) {
        return new File(v(str, i2, j2, str2), "checkpoint.dat");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File v(String str, int i2, long j2, String str2) {
        return new File(s(str, i2, j2), str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File w(String str, int i2, long j2, String str2) {
        return new File(new File(new File(zzK(str, i2, j2), "_slices"), "_unverified"), str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File x(String str, int i2, long j2, String str2) {
        return new File(new File(new File(zzK(str, i2, j2), "_slices"), "_verified"), str2);
    }

    @Nullable
    final String y(String str) {
        int length;
        File file = new File(zzL(), str);
        if (!file.exists()) {
            zza.zza("Pack not found with pack name: %s", str);
            return null;
        }
        File file2 = new File(file, String.valueOf(this.zze.zza()));
        if (!file2.exists()) {
            zza.zza("Pack not found with pack name: %s app version: %s", str, Integer.valueOf(this.zze.zza()));
            return null;
        }
        File[] listFiles = file2.listFiles();
        if (listFiles == null || (length = listFiles.length) == 0) {
            zza.zza("No pack version found for pack name: %s app version: %s", str, Integer.valueOf(this.zze.zza()));
            return null;
        } else if (length > 1) {
            zza.zzb("Multiple pack versions found for pack name: %s app version: %s", str, Integer.valueOf(this.zze.zza()));
            return null;
        } else {
            return listFiles[0].getCanonicalPath();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final List z(String str) {
        PackageInfo packageInfo;
        String str2 = null;
        try {
            packageInfo = this.zzd.getPackageManager().getPackageInfo(this.zzd.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException unused) {
            zza.zzb("Could not find PackageInfo.", new Object[0]);
            packageInfo = null;
        }
        if (packageInfo == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT < 21) {
            arrayList.add(packageInfo.applicationInfo.sourceDir);
            return arrayList;
        }
        String[] strArr = packageInfo.splitNames;
        if (strArr == null || packageInfo.applicationInfo.splitSourceDirs == null) {
            zza.zza("No splits present for package %s.", str);
        } else {
            int binarySearch = Arrays.binarySearch(strArr, str);
            if (binarySearch < 0) {
                zza.zza("Asset Pack '%s' is not installed.", str);
            } else {
                str2 = packageInfo.applicationInfo.splitSourceDirs[binarySearch];
            }
        }
        if (str2 == null) {
            arrayList.add(packageInfo.applicationInfo.sourceDir);
            arrayList.addAll(zzN(packageInfo, "config."));
            return arrayList;
        }
        arrayList.add(str2);
        arrayList.addAll(zzN(packageInfo, String.valueOf(str).concat(".config.")));
        return arrayList;
    }
}
