package com.google.android.play.core.splitcompat;

import android.content.Context;
import com.google.android.play.core.internal.zzci;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes2.dex */
public final class zze {
    private final long zza;
    private final Context zzb;
    private File zzc;

    public zze(Context context) {
        this.zzb = context;
        this.zza = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
    }

    public static void zzl(File file) {
        File[] listFiles;
        if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                zzl(file2);
            }
        }
        if (file.exists() && !file.delete()) {
            throw new IOException(String.format("Failed to delete '%s'", file.getAbsolutePath()));
        }
    }

    public static void zzm(File file) {
        file.setWritable(false, true);
        file.setWritable(false, false);
    }

    public static boolean zzp(File file) {
        return !file.canWrite();
    }

    private static File zzq(File file, String str) {
        File file2 = new File(file, str);
        if (file2.getCanonicalPath().startsWith(file.getCanonicalPath())) {
            return file2;
        }
        throw new IllegalArgumentException("split ID cannot be placed in target directory");
    }

    private final File zzr() {
        File file = new File(zzw(), "native-libraries");
        zzu(file);
        return file;
    }

    private final File zzs(String str) {
        File zzq = zzq(zzr(), str);
        zzu(zzq);
        return zzq;
    }

    private final File zzt() {
        File file = new File(zzw(), "verified-splits");
        zzu(file);
        return file;
    }

    private static File zzu(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                return file;
            }
            throw new IllegalArgumentException("File input must be directory when it exists.");
        }
        file.mkdirs();
        if (file.isDirectory()) {
            return file;
        }
        String valueOf = String.valueOf(file.getAbsolutePath());
        throw new IOException(valueOf.length() != 0 ? "Unable to create directory: ".concat(valueOf) : new String("Unable to create directory: "));
    }

    private final File zzv() {
        if (this.zzc == null) {
            Context context = this.zzb;
            if (context == null) {
                throw new IllegalStateException("context must be non-null to populate null filesDir");
            }
            this.zzc = context.getFilesDir();
        }
        File file = new File(this.zzc, "splitcompat");
        zzu(file);
        return file;
    }

    private final File zzw() {
        File file = new File(zzv(), Long.toString(this.zza));
        zzu(file);
        return file;
    }

    private static String zzx(String str) {
        return String.valueOf(str).concat(".apk");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List a() {
        ArrayList arrayList = new ArrayList();
        File[] listFiles = zzr().listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    arrayList.add(file.getName());
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Set b(String str) {
        HashSet hashSet = new HashSet();
        File[] listFiles = zzs(str).listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isFile()) {
                    hashSet.add(file);
                }
            }
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Set c() {
        String name;
        File zzt = zzt();
        HashSet hashSet = new HashSet();
        File[] listFiles = zzt.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isFile() && file.getName().endsWith(".apk") && zzp(file)) {
                    hashSet.add(new zzb(file, file.getName().substring(0, name.length() - 4)));
                }
            }
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void d(String str) {
        zzl(zzs(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void e(File file) {
        zzci.zzb(file.getParentFile().getParentFile().equals(zzr()), "File to remove is not a native library");
        zzl(file);
    }

    public final File zza(String str) {
        File file = new File(zzw(), "dex");
        zzu(file);
        File zzq = zzq(file, str);
        zzu(zzq);
        return zzq;
    }

    public final File zzb() {
        File file = new File(zzw(), "unverified-splits");
        zzu(file);
        return file;
    }

    public final File zzc(String str, String str2) {
        return zzq(zzs(str), str2);
    }

    public final File zzd() {
        return new File(zzw(), "lock.tmp");
    }

    public final File zze(String str) {
        return zzq(zzb(), zzx(str));
    }

    public final File zzf(File file) {
        return zzq(zzt(), file.getName());
    }

    public final File zzg(String str) {
        return zzq(zzt(), zzx(str));
    }

    public final void zzk() {
        File zzv = zzv();
        String[] list = zzv.list();
        if (list != null) {
            for (String str : list) {
                if (!str.equals(Long.toString(this.zza))) {
                    File file = new File(zzv, str);
                    String obj = file.toString();
                    long j2 = this.zza;
                    StringBuilder sb = new StringBuilder(obj.length() + 118);
                    sb.append("FileStorage: removing directory for different version code (directory = ");
                    sb.append(obj);
                    sb.append(", current version code = ");
                    sb.append(j2);
                    sb.append(")");
                    zzl(file);
                }
            }
        }
    }
}
