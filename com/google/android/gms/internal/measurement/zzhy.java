package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;
/* loaded from: classes.dex */
public abstract class zzhy {
    public static final /* synthetic */ int zzc = 0;
    @Nullable
    private static volatile zzhw zze = null;
    private static volatile boolean zzf = false;

    /* renamed from: a  reason: collision with root package name */
    final zzhv f6082a;

    /* renamed from: b  reason: collision with root package name */
    final String f6083b;
    private final Object zzj;
    private volatile int zzk = -1;
    private volatile Object zzl;
    private final boolean zzm;
    private static final Object zzd = new Object();
    private static final AtomicReference zzg = new AtomicReference();
    private static final zzia zzh = new zzia(zzhq.zza, null);
    private static final AtomicInteger zzi = new AtomicInteger();

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzhy(zzhv zzhvVar, String str, Object obj, boolean z, zzhx zzhxVar) {
        if (zzhvVar.f6074b == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        this.f6082a = zzhvVar;
        this.f6083b = str;
        this.zzj = obj;
        this.zzm = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b() {
        zzi.incrementAndGet();
    }

    public static void zze(final Context context) {
        if (zze == null) {
            Object obj = zzd;
            synchronized (obj) {
                if (zze == null) {
                    synchronized (obj) {
                        zzhw zzhwVar = zze;
                        Context applicationContext = context.getApplicationContext();
                        if (applicationContext != null) {
                            context = applicationContext;
                        }
                        if (zzhwVar == null || zzhwVar.a() != context) {
                            zzhe.b();
                            zzhz.b();
                            zzhm.c();
                            zze = new zzhb(context, zzij.zza(new zzif() { // from class: com.google.android.gms.internal.measurement.zzhp
                                /* JADX WARN: Removed duplicated region for block: B:30:0x0074 A[Catch: all -> 0x014f, TRY_LEAVE, TryCatch #5 {all -> 0x014f, blocks: (B:19:0x0046, B:21:0x004a, B:22:0x0057, B:24:0x005d, B:28:0x006e, B:30:0x0074, B:31:0x007a, B:50:0x011c, B:51:0x011f, B:58:0x013f, B:57:0x012a, B:60:0x0141, B:61:0x0146, B:62:0x0147, B:25:0x0062, B:27:0x0068), top: B:78:0x0046, inners: #1, #4 }] */
                                /* JADX WARN: Removed duplicated region for block: B:62:0x0147 A[Catch: all -> 0x014f, TRY_LEAVE, TryCatch #5 {all -> 0x014f, blocks: (B:19:0x0046, B:21:0x004a, B:22:0x0057, B:24:0x005d, B:28:0x006e, B:30:0x0074, B:31:0x007a, B:50:0x011c, B:51:0x011f, B:58:0x013f, B:57:0x012a, B:60:0x0141, B:61:0x0146, B:62:0x0147, B:25:0x0062, B:27:0x0068), top: B:78:0x0046, inners: #1, #4 }] */
                                @Override // com.google.android.gms.internal.measurement.zzif
                                /*
                                    Code decompiled incorrectly, please refer to instructions dump.
                                */
                                public final Object zza() {
                                    zzid zzc2;
                                    zzid zzc3;
                                    File file;
                                    Context context2 = context;
                                    int i2 = zzhy.zzc;
                                    String str = Build.TYPE;
                                    String str2 = Build.TAGS;
                                    if ((str.equals("eng") || str.equals("userdebug")) && (str2.contains("dev-keys") || str2.contains("test-keys"))) {
                                        if (zzha.zza() && !context2.isDeviceProtectedStorage()) {
                                            context2 = context2.createDeviceProtectedStorageContext();
                                        }
                                        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                                        try {
                                            StrictMode.allowThreadDiskWrites();
                                            try {
                                                file = new File(context2.getDir("phenotype_hermetic", 0), "overrides.txt");
                                            } catch (RuntimeException e2) {
                                                Log.e("HermeticFileOverrides", "no data dir", e2);
                                            }
                                            if (file.exists()) {
                                                zzc2 = zzid.zzd(file);
                                                if (zzc2.zzb()) {
                                                    zzc3 = zzid.zzc();
                                                } else {
                                                    File file2 = (File) zzc2.zza();
                                                    try {
                                                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
                                                        try {
                                                            HashMap hashMap = new HashMap();
                                                            HashMap hashMap2 = new HashMap();
                                                            while (true) {
                                                                String readLine = bufferedReader.readLine();
                                                                if (readLine == null) {
                                                                    break;
                                                                }
                                                                String[] split = readLine.split(" ", 3);
                                                                if (split.length != 3) {
                                                                    Log.e("HermeticFileOverrides", "Invalid: " + readLine);
                                                                } else {
                                                                    String str3 = new String(split[0]);
                                                                    String decode = Uri.decode(new String(split[1]));
                                                                    String str4 = (String) hashMap2.get(split[2]);
                                                                    if (str4 == null) {
                                                                        String str5 = new String(split[2]);
                                                                        str4 = Uri.decode(str5);
                                                                        if (str4.length() < 1024 || str4 == str5) {
                                                                            hashMap2.put(str5, str4);
                                                                        }
                                                                    }
                                                                    if (!hashMap.containsKey(str3)) {
                                                                        hashMap.put(str3, new HashMap());
                                                                    }
                                                                    ((Map) hashMap.get(str3)).put(decode, str4);
                                                                }
                                                            }
                                                            String obj2 = file2.toString();
                                                            StringBuilder sb = new StringBuilder();
                                                            sb.append("Parsed ");
                                                            sb.append(obj2);
                                                            zzhg zzhgVar = new zzhg(hashMap);
                                                            bufferedReader.close();
                                                            zzc3 = zzid.zzd(zzhgVar);
                                                        } catch (Throwable th) {
                                                            try {
                                                                bufferedReader.close();
                                                            } catch (Throwable th2) {
                                                                try {
                                                                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                                                                } catch (Exception unused) {
                                                                }
                                                            }
                                                            throw th;
                                                        }
                                                    } catch (IOException e3) {
                                                        throw new RuntimeException(e3);
                                                    }
                                                }
                                                return zzc3;
                                            }
                                            zzc2 = zzid.zzc();
                                            if (zzc2.zzb()) {
                                            }
                                            return zzc3;
                                        } finally {
                                            StrictMode.setThreadPolicy(allowThreadDiskReads);
                                        }
                                    }
                                    return zzid.zzc();
                                }
                            }));
                            zzi.incrementAndGet();
                        }
                    }
                }
            }
        }
    }

    abstract Object a(Object obj);

    /* JADX WARN: Removed duplicated region for block: B:37:0x0095 A[Catch: all -> 0x00cf, TryCatch #0 {, blocks: (B:8:0x0016, B:10:0x001a, B:12:0x0020, B:14:0x0029, B:16:0x0037, B:20:0x005c, B:22:0x0066, B:38:0x0097, B:40:0x00a7, B:42:0x00bb, B:43:0x00be, B:44:0x00c2, B:26:0x006f, B:28:0x0075, B:32:0x0087, B:34:0x008d, B:37:0x0095, B:31:0x0085, B:18:0x004e, B:45:0x00c7, B:46:0x00cc, B:47:0x00cd), top: B:54:0x0016 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object zzb() {
        zzhj a2;
        Object zzb;
        if (!this.zzm) {
            Objects.requireNonNull(this.f6083b, "flagName must not be null");
        }
        int i2 = zzi.get();
        if (this.zzk < i2) {
            synchronized (this) {
                if (this.zzk < i2) {
                    zzhw zzhwVar = zze;
                    if (zzhwVar == null) {
                        throw new IllegalStateException("Must call PhenotypeFlag.init() first");
                    }
                    zzhv zzhvVar = this.f6082a;
                    boolean z = zzhvVar.f6078f;
                    if (zzhvVar.f6074b == null) {
                        Context a3 = zzhwVar.a();
                        String str = this.f6082a.f6073a;
                        a2 = zzhz.a(a3, null);
                    } else if (zzhn.zza(zzhwVar.a(), this.f6082a.f6074b)) {
                        boolean z2 = this.f6082a.f6080h;
                        a2 = zzhe.zza(zzhwVar.a().getContentResolver(), this.f6082a.f6074b);
                    } else {
                        a2 = null;
                    }
                    Object a4 = (a2 == null || (zzb = a2.zzb(zzc())) == null) ? null : a(zzb);
                    if (a4 == null) {
                        if (!this.f6082a.f6077e) {
                            String zzb2 = zzhm.a(zzhwVar.a()).zzb(this.f6082a.f6077e ? null : this.f6083b);
                            if (zzb2 != null) {
                                a4 = a(zzb2);
                                if (a4 == null) {
                                    a4 = this.zzj;
                                }
                            }
                        }
                        a4 = null;
                        if (a4 == null) {
                        }
                    }
                    zzid zzidVar = (zzid) zzhwVar.b().zza();
                    if (zzidVar.zzb()) {
                        zzhv zzhvVar2 = this.f6082a;
                        String zza = ((zzhg) zzidVar.zza()).zza(zzhvVar2.f6074b, null, zzhvVar2.f6076d, this.f6083b);
                        a4 = zza == null ? this.zzj : a(zza);
                    }
                    this.zzl = a4;
                    this.zzk = i2;
                }
            }
        }
        return this.zzl;
    }

    public final String zzc() {
        String str = this.f6082a.f6076d;
        return this.f6083b;
    }
}
