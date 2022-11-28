package com.google.android.play.core.splitcompat;

import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
/* loaded from: classes2.dex */
public final class zzm {
    private static final Pattern zza = Pattern.compile("lib/([^/]+)/(.*\\.so)$");
    private final zze zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzm(zze zzeVar) {
        this.zzb = zzeVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ Set a(zzm zzmVar, Set set, zzs zzsVar, ZipFile zipFile) {
        HashSet hashSet = new HashSet();
        zzmVar.zzf(zzsVar, set, new zzi(zzmVar, hashSet, zzsVar, zipFile));
        return hashSet;
    }

    @RequiresApi(21)
    private static void zze(zzs zzsVar, zzj zzjVar) {
        ZipFile zipFile;
        String[] strArr;
        try {
            zipFile = new ZipFile(zzsVar.a());
        } catch (IOException e2) {
            e = e2;
            zipFile = null;
        }
        try {
            String b2 = zzsVar.b();
            HashMap hashMap = new HashMap();
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry nextElement = entries.nextElement();
                Matcher matcher = zza.matcher(nextElement.getName());
                if (matcher.matches()) {
                    String group = matcher.group(1);
                    String group2 = matcher.group(2);
                    String.format("NativeLibraryExtractor: split '%s' has native library '%s' for ABI '%s'", b2, group2, group);
                    Set set = (Set) hashMap.get(group);
                    if (set == null) {
                        set = new HashSet();
                        hashMap.put(group, set);
                    }
                    set.add(new zzl(nextElement, group2));
                }
            }
            HashMap hashMap2 = new HashMap();
            for (String str : Build.SUPPORTED_ABIS) {
                if (hashMap.containsKey(str)) {
                    String.format("NativeLibraryExtractor: there are native libraries for supported ABI %s; will use this ABI", str);
                    for (zzl zzlVar : (Set) hashMap.get(str)) {
                        if (hashMap2.containsKey(zzlVar.zza)) {
                            String.format("NativeLibraryExtractor: skipping library %s for ABI %s; already present for a better ABI", zzlVar.zza, str);
                        } else {
                            hashMap2.put(zzlVar.zza, zzlVar);
                            String.format("NativeLibraryExtractor: using library %s for ABI %s", zzlVar.zza, str);
                        }
                    }
                } else {
                    String.format("NativeLibraryExtractor: there are no native libraries for supported ABI %s", str);
                }
            }
            zzjVar.zza(zipFile, new HashSet(hashMap2.values()));
            zipFile.close();
        } catch (IOException e3) {
            e = e3;
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException unused) {
                }
            }
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzf(zzs zzsVar, Set set, zzk zzkVar) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            zzl zzlVar = (zzl) it.next();
            File zzc = this.zzb.zzc(zzsVar.b(), zzlVar.zza);
            boolean z = false;
            if (zzc.exists() && zzc.length() == zzlVar.zzb.getSize() && zze.zzp(zzc)) {
                z = true;
            }
            zzkVar.zza(zzlVar, zzc, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    @RequiresApi(21)
    public final Set b(zzs zzsVar) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        HashSet hashSet = new HashSet();
        zze(zzsVar, new zzg(this, zzsVar, hashSet, atomicBoolean));
        if (atomicBoolean.get()) {
            return hashSet;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(21)
    public final Set c() {
        Set<zzs> c2 = this.zzb.c();
        for (String str : this.zzb.a()) {
            Iterator it = c2.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((zzs) it.next()).b().equals(str)) {
                        break;
                    }
                } else {
                    String.format("NativeLibraryExtractor: extracted split '%s' has no corresponding split; deleting", str);
                    this.zzb.d(str);
                    break;
                }
            }
        }
        HashSet hashSet = new HashSet();
        for (zzs zzsVar : c2) {
            HashSet hashSet2 = new HashSet();
            zze(zzsVar, new zzh(this, hashSet2, zzsVar));
            for (File file : this.zzb.b(zzsVar.b())) {
                if (!hashSet2.contains(file)) {
                    String.format("NativeLibraryExtractor: file '%s' found in split '%s' that is not in the split file '%s'; removing", file.getAbsolutePath(), zzsVar.b(), zzsVar.a().getAbsolutePath());
                    this.zzb.e(file);
                }
            }
            hashSet.addAll(hashSet2);
        }
        return hashSet;
    }
}
