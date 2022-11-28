package com.google.android.play.core.internal;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/* loaded from: classes2.dex */
final class zzbf implements zzaz {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object a(ClassLoader classLoader) {
        return zzbw.zzb(classLoader, "pathList", Object.class).zzc();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(ClassLoader classLoader, Set set) {
        if (set.isEmpty()) {
            return;
        }
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            File file = (File) it.next();
            String valueOf = String.valueOf(file.getParentFile().getAbsolutePath());
            if (valueOf.length() != 0) {
                "Adding native library parent directory: ".concat(valueOf);
            }
            hashSet.add(file.getParentFile());
        }
        zzbu zza = zzbw.zza(a(classLoader), "nativeLibraryDirectories", File.class);
        hashSet.removeAll(Arrays.asList((File[]) zza.zzc()));
        synchronized (com.google.android.play.core.splitinstall.zzn.class) {
            int size = hashSet.size();
            StringBuilder sb = new StringBuilder(30);
            sb.append("Adding directories ");
            sb.append(size);
            zza.zzb(hashSet);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean c(ClassLoader classLoader, File file, File file2, boolean z, zzbe zzbeVar, String str, zzbd zzbdVar) {
        ArrayList arrayList = new ArrayList();
        Object a2 = a(classLoader);
        zzbu zza = zzbw.zza(a2, "dexElements", Object.class);
        List<Object> asList = Arrays.asList((Object[]) zza.zzc());
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : asList) {
            arrayList2.add((File) zzbw.zzb(obj, str, File.class).zzc());
        }
        if (arrayList2.contains(file2)) {
            return true;
        }
        if (!z && !zzbdVar.zza(a2, file2, file)) {
            String valueOf = String.valueOf(file2.getPath());
            if (valueOf.length() != 0) {
                "Should be optimized ".concat(valueOf);
            }
            return false;
        }
        zza.zza(Arrays.asList(zzbeVar.zza(a2, new ArrayList(Collections.singleton(file2)), file, arrayList)));
        if (arrayList.isEmpty()) {
            return true;
        }
        zzbt zzbtVar = new zzbt("DexPathList.makeDexElement failed");
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            Log.e("SplitCompat", "DexPathList.makeDexElement failed", (IOException) arrayList.get(i2));
        }
        zzbw.zza(a2, "dexElementsSuppressedExceptions", IOException.class).zza(arrayList);
        throw zzbtVar;
    }

    @Override // com.google.android.play.core.internal.zzaz
    public final void zza(ClassLoader classLoader, Set set) {
        b(classLoader, set);
    }

    @Override // com.google.android.play.core.internal.zzaz
    public final boolean zzb(ClassLoader classLoader, File file, File file2, boolean z) {
        return c(classLoader, file, file2, z, new zzbb(), "zip", new zzbc());
    }
}
