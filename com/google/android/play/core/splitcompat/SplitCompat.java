package com.google.android.play.core.splitcompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.google.android.play.core.internal.zzau;
import com.google.android.play.core.internal.zzaw;
import com.google.android.play.core.internal.zzay;
import com.google.android.play.core.internal.zzaz;
import com.google.android.play.core.internal.zzba;
import com.google.android.play.core.internal.zzbt;
import com.google.android.play.core.splitinstall.zzbe;
import com.google.android.play.core.splitinstall.zzx;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
/* loaded from: classes2.dex */
public class SplitCompat {
    public static final /* synthetic */ int zza = 0;
    private static final AtomicReference zzb = new AtomicReference(null);
    private final zze zzc;
    private final zzbe zzd;
    @GuardedBy("emulatedSplits")
    private final Set zze = new HashSet();
    private final zza zzf;

    private SplitCompat(Context context) {
        try {
            zze zzeVar = new zze(context);
            this.zzc = zzeVar;
            this.zzf = new zza(zzeVar);
            this.zzd = new zzbe(context);
        } catch (PackageManager.NameNotFoundException e2) {
            throw new zzbt("Failed to initialize FileStorage", e2);
        }
    }

    public static boolean install(@NonNull Context context) {
        return zzi(context, false);
    }

    public static boolean installActivity(@NonNull Context context) {
        if (zzj()) {
            return false;
        }
        SplitCompat splitCompat = (SplitCompat) zzb.get();
        if (splitCompat == null) {
            if (context.getApplicationContext() != null) {
                install(context.getApplicationContext());
            }
            return install(context);
        }
        return splitCompat.zzf.a(context, splitCompat.zzf());
    }

    public static boolean zzd(Context context) {
        return zzi(context, true);
    }

    public static boolean zze() {
        return zzb.get() != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set zzf() {
        HashSet hashSet;
        synchronized (this.zze) {
            hashSet = new HashSet(this.zze);
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzg(Set set) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            zze.zzl(this.zzc.zzg((String) it.next()));
        }
        this.zzd.zzb();
    }

    @RequiresApi(21)
    private final synchronized void zzh(Context context, boolean z) {
        ZipFile zipFile;
        if (z) {
            this.zzc.zzk();
        } else {
            zzd.zza().execute(new zzp(this));
        }
        String packageName = context.getPackageName();
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(packageName, 0).splitNames;
            List<String> arrayList = strArr == null ? new ArrayList() : Arrays.asList(strArr);
            Set<zzs> c2 = this.zzc.c();
            Set zza2 = this.zzd.zza();
            HashSet hashSet = new HashSet();
            Iterator it = c2.iterator();
            while (it.hasNext()) {
                String b2 = ((zzs) it.next()).b();
                if (arrayList.contains(b2) || zza2.contains(com.google.android.play.core.splitinstall.zzs.zzb(b2))) {
                    hashSet.add(b2);
                    it.remove();
                }
            }
            if (z) {
                zzg(hashSet);
            } else if (!hashSet.isEmpty()) {
                zzd.zza().execute(new zzq(this, hashSet));
            }
            HashSet hashSet2 = new HashSet();
            for (zzs zzsVar : c2) {
                String b3 = zzsVar.b();
                if (!com.google.android.play.core.splitinstall.zzs.zzf(b3)) {
                    hashSet2.add(b3);
                }
            }
            for (String str : arrayList) {
                if (!com.google.android.play.core.splitinstall.zzs.zzf(str)) {
                    hashSet2.add(str);
                }
            }
            HashSet<zzs> hashSet3 = new HashSet(c2.size());
            for (zzs zzsVar2 : c2) {
                if (com.google.android.play.core.splitinstall.zzs.zze(zzsVar2.b()) || hashSet2.contains(com.google.android.play.core.splitinstall.zzs.zzb(zzsVar2.b()))) {
                    hashSet3.add(zzsVar2);
                }
            }
            zzm zzmVar = new zzm(this.zzc);
            zzaz zza3 = zzba.zza();
            ClassLoader classLoader = context.getClassLoader();
            if (z) {
                zza3.zza(classLoader, zzmVar.c());
            } else {
                Iterator it2 = hashSet3.iterator();
                while (it2.hasNext()) {
                    Set b4 = zzmVar.b((zzs) it2.next());
                    if (b4 == null) {
                        it2.remove();
                    } else {
                        zza3.zza(classLoader, b4);
                    }
                }
            }
            HashSet hashSet4 = new HashSet();
            for (zzs zzsVar3 : hashSet3) {
                try {
                    zipFile = new ZipFile(zzsVar3.a());
                } catch (IOException e2) {
                    e = e2;
                    zipFile = null;
                }
                try {
                    ZipEntry entry = zipFile.getEntry("classes.dex");
                    zipFile.close();
                    if (entry != null && !zza3.zzb(classLoader, this.zzc.zza(zzsVar3.b()), zzsVar3.a(), z)) {
                        "split was not installed ".concat(zzsVar3.a().toString());
                    }
                    hashSet4.add(zzsVar3.a());
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
            this.zzf.zza(context, hashSet4);
            HashSet hashSet5 = new HashSet();
            for (zzs zzsVar4 : hashSet3) {
                if (hashSet4.contains(zzsVar4.a())) {
                    String b5 = zzsVar4.b();
                    StringBuilder sb = new StringBuilder(b5.length() + 30);
                    sb.append("Split '");
                    sb.append(b5);
                    sb.append("' installation emulated");
                    hashSet5.add(zzsVar4.b());
                } else {
                    String b6 = zzsVar4.b();
                    StringBuilder sb2 = new StringBuilder(b6.length() + 35);
                    sb2.append("Split '");
                    sb2.append(b6);
                    sb2.append("' installation not emulated.");
                }
            }
            synchronized (this.zze) {
                this.zze.addAll(hashSet5);
            }
        } catch (PackageManager.NameNotFoundException e4) {
            throw new IOException(String.format("Cannot load data for application '%s'", packageName), e4);
        }
    }

    private static boolean zzi(final Context context, boolean z) {
        if (zzj()) {
            return false;
        }
        AtomicReference atomicReference = zzb;
        boolean compareAndSet = atomicReference.compareAndSet(null, new SplitCompat(context));
        SplitCompat splitCompat = (SplitCompat) atomicReference.get();
        if (compareAndSet) {
            com.google.android.play.core.splitinstall.zzo.INSTANCE.zzb(new zzaw(context, zzd.zza(), new zzay(context, splitCompat.zzc, new zzau()), splitCompat.zzc, new zzr(), null));
            com.google.android.play.core.splitinstall.zzr.zzb(new zzo(splitCompat));
            zzd.zza().execute(new Runnable() { // from class: com.google.android.play.core.splitcompat.zzn
                @Override // java.lang.Runnable
                public final void run() {
                    Context context2 = context;
                    int i2 = SplitCompat.zza;
                    try {
                        zzx.zzc(context2).zzg(true);
                    } catch (SecurityException unused) {
                        Log.e("SplitCompat", "Failed to set broadcast receiver to always on.");
                    }
                }
            });
        }
        try {
            splitCompat.zzh(context, z);
            return true;
        } catch (Exception e2) {
            Log.e("SplitCompat", "Error installing additional splits", e2);
            return false;
        }
    }

    private static boolean zzj() {
        return Build.VERSION.SDK_INT < 21;
    }
}
