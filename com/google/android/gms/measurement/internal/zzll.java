package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzne;
import com.google.android.gms.internal.measurement.zznz;
import com.google.android.gms.internal.measurement.zzpj;
import com.google.android.gms.internal.measurement.zzpp;
import com.google.android.gms.internal.measurement.zzps;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.psa.mym.mycitroenconnect.car_console.utils.AutoConstants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.zip.GZIPInputStream;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.time.DurationKt;
import kotlinx.coroutines.DebugKt;
/* loaded from: classes2.dex */
public final class zzll implements zzhf {
    private static volatile zzll zzb;
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    long f7040a;
    private long zzA;
    private final Map zzB;
    private final Map zzC;
    private zziw zzD;
    private String zzE;
    private final zzgb zzc;
    private final zzfg zzd;
    private zzam zze;
    private zzfi zzf;
    private zzkx zzg;
    private zzaa zzh;
    private final zzln zzi;
    private zziu zzj;
    private zzkg zzk;
    private final zzla zzl;
    private zzfs zzm;
    private final zzgk zzn;
    private boolean zzp;
    private List zzq;
    private int zzr;
    private int zzs;
    private boolean zzt;
    private boolean zzu;
    private boolean zzv;
    private FileLock zzw;
    private FileChannel zzx;
    private List zzy;
    private List zzz;
    private boolean zzo = false;
    private final zzls zzF = new zzlg(this);

    zzll(zzlm zzlmVar, zzgk zzgkVar) {
        Preconditions.checkNotNull(zzlmVar);
        this.zzn = zzgk.zzp(zzlmVar.f7041a, null, null);
        this.zzA = -1L;
        this.zzl = new zzla(this);
        zzln zzlnVar = new zzln(this);
        zzlnVar.zzX();
        this.zzi = zzlnVar;
        zzfg zzfgVar = new zzfg(this);
        zzfgVar.zzX();
        this.zzd = zzfgVar;
        zzgb zzgbVar = new zzgb(this);
        zzgbVar.zzX();
        this.zzc = zzgbVar;
        this.zzB = new HashMap();
        this.zzC = new HashMap();
        zzaz().zzp(new zzlb(this, zzlmVar));
    }

    @VisibleForTesting
    static final void A(com.google.android.gms.internal.measurement.zzfr zzfrVar, @NonNull String str) {
        List zzp = zzfrVar.zzp();
        for (int i2 = 0; i2 < zzp.size(); i2++) {
            if (str.equals(((com.google.android.gms.internal.measurement.zzfw) zzp.get(i2)).zzg())) {
                zzfrVar.zzh(i2);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void H(zzll zzllVar, zzlm zzlmVar) {
        zzllVar.zzaz().zzg();
        zzllVar.zzm = new zzfs(zzllVar);
        zzam zzamVar = new zzam(zzllVar);
        zzamVar.zzX();
        zzllVar.zze = zzamVar;
        zzllVar.zzg().f((zzaf) Preconditions.checkNotNull(zzllVar.zzc));
        zzkg zzkgVar = new zzkg(zzllVar);
        zzkgVar.zzX();
        zzllVar.zzk = zzkgVar;
        zzaa zzaaVar = new zzaa(zzllVar);
        zzaaVar.zzX();
        zzllVar.zzh = zzaaVar;
        zziu zziuVar = new zziu(zzllVar);
        zziuVar.zzX();
        zzllVar.zzj = zziuVar;
        zzkx zzkxVar = new zzkx(zzllVar);
        zzkxVar.zzX();
        zzllVar.zzg = zzkxVar;
        zzllVar.zzf = new zzfi(zzllVar);
        if (zzllVar.zzr != zzllVar.zzs) {
            zzllVar.zzay().zzd().zzc("Not all upload components initialized", Integer.valueOf(zzllVar.zzr), Integer.valueOf(zzllVar.zzs));
        }
        zzllVar.zzo = true;
    }

    @VisibleForTesting
    static final void y(com.google.android.gms.internal.measurement.zzfr zzfrVar, int i2, String str) {
        List zzp = zzfrVar.zzp();
        for (int i3 = 0; i3 < zzp.size(); i3++) {
            if ("_err".equals(((com.google.android.gms.internal.measurement.zzfw) zzp.get(i3)).zzg())) {
                return;
            }
        }
        com.google.android.gms.internal.measurement.zzfv zze = com.google.android.gms.internal.measurement.zzfw.zze();
        zze.zzj("_err");
        zze.zzi(Long.valueOf(i2).longValue());
        com.google.android.gms.internal.measurement.zzfv zze2 = com.google.android.gms.internal.measurement.zzfw.zze();
        zze2.zzj("_ev");
        zze2.zzk(str);
        zzfrVar.zzf((com.google.android.gms.internal.measurement.zzfw) zze.zzaE());
        zzfrVar.zzf((com.google.android.gms.internal.measurement.zzfw) zze2.zzaE());
    }

    @WorkerThread
    private final zzq zzab(String str) {
        zzey zzc;
        String str2;
        String str3;
        String str4 = str;
        zzam zzamVar = this.zze;
        zzak(zzamVar);
        zzh zzj = zzamVar.zzj(str4);
        if (zzj == null || TextUtils.isEmpty(zzj.zzw())) {
            zzc = zzay().zzc();
            str2 = "No app data available; dropping";
            str3 = str4;
        } else {
            Boolean zzac = zzac(zzj);
            if (zzac == null || zzac.booleanValue()) {
                return new zzq(str, zzj.zzy(), zzj.zzw(), zzj.zzb(), zzj.zzv(), zzj.zzm(), zzj.zzj(), (String) null, zzj.zzaj(), false, zzj.zzx(), zzj.zza(), 0L, 0, zzj.zzai(), false, zzj.zzr(), zzj.zzq(), zzj.zzk(), zzj.zzC(), (String) null, C(str).zzh(), "", (String) null);
            }
            zzc = zzay().zzd();
            str2 = "App version does not match; dropping. appId";
            str3 = zzfa.g(str);
        }
        zzc.zzb(str2, str3);
        return null;
    }

    @WorkerThread
    private final Boolean zzac(zzh zzhVar) {
        try {
            if (zzhVar.zzb() != -2147483648L) {
                if (zzhVar.zzb() == Wrappers.packageManager(this.zzn.zzau()).getPackageInfo(zzhVar.zzt(), 0).versionCode) {
                    return Boolean.TRUE;
                }
            } else {
                String str = Wrappers.packageManager(this.zzn.zzau()).getPackageInfo(zzhVar.zzt(), 0).versionName;
                String zzw = zzhVar.zzw();
                if (zzw != null && zzw.equals(str)) {
                    return Boolean.TRUE;
                }
            }
            return Boolean.FALSE;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    @WorkerThread
    private final void zzad() {
        zzaz().zzg();
        if (this.zzt || this.zzu || this.zzv) {
            zzay().zzj().zzd("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzt), Boolean.valueOf(this.zzu), Boolean.valueOf(this.zzv));
            return;
        }
        zzay().zzj().zza("Stopping uploading service(s)");
        List<Runnable> list = this.zzq;
        if (list == null) {
            return;
        }
        for (Runnable runnable : list) {
            runnable.run();
        }
        ((List) Preconditions.checkNotNull(this.zzq)).clear();
    }

    @VisibleForTesting
    private final void zzae(com.google.android.gms.internal.measurement.zzgb zzgbVar, long j2, boolean z) {
        String str = true != z ? "_lte" : "_se";
        zzam zzamVar = this.zze;
        zzak(zzamVar);
        zzlq zzp = zzamVar.zzp(zzgbVar.zzaq(), str);
        zzlq zzlqVar = (zzp == null || zzp.f7046e == null) ? new zzlq(zzgbVar.zzaq(), DebugKt.DEBUG_PROPERTY_VALUE_AUTO, str, zzav().currentTimeMillis(), Long.valueOf(j2)) : new zzlq(zzgbVar.zzaq(), DebugKt.DEBUG_PROPERTY_VALUE_AUTO, str, zzav().currentTimeMillis(), Long.valueOf(((Long) zzp.f7046e).longValue() + j2));
        com.google.android.gms.internal.measurement.zzgk zzd = com.google.android.gms.internal.measurement.zzgl.zzd();
        zzd.zzf(str);
        zzd.zzg(zzav().currentTimeMillis());
        zzd.zze(((Long) zzlqVar.f7046e).longValue());
        com.google.android.gms.internal.measurement.zzgl zzglVar = (com.google.android.gms.internal.measurement.zzgl) zzd.zzaE();
        int g2 = zzln.g(zzgbVar, str);
        if (g2 >= 0) {
            zzgbVar.zzan(g2, zzglVar);
        } else {
            zzgbVar.zzm(zzglVar);
        }
        if (j2 > 0) {
            zzam zzamVar2 = this.zze;
            zzak(zzamVar2);
            zzamVar2.zzL(zzlqVar);
            zzay().zzj().zzc("Updated engagement user property. scope, value", true != z ? "lifetime" : "session-scoped", zzlqVar.f7046e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x021b  */
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void zzaf() {
        zzem zzemVar;
        long j2;
        zzaz().zzg();
        b();
        if (this.f7040a > 0) {
            long abs = 3600000 - Math.abs(zzav().elapsedRealtime() - this.f7040a);
            if (abs > 0) {
                zzay().zzj().zzb("Upload has been suspended. Will update scheduling later in approximately ms", Long.valueOf(abs));
                zzm().zzc();
                zzkx zzkxVar = this.zzg;
                zzak(zzkxVar);
                zzkxVar.zza();
                return;
            }
            this.f7040a = 0L;
        }
        if (!this.zzn.g() || !zzah()) {
            zzay().zzj().zza("Nothing to upload or uploading impossible");
            zzm().zzc();
            zzkx zzkxVar2 = this.zzg;
            zzak(zzkxVar2);
            zzkxVar2.zza();
            return;
        }
        long currentTimeMillis = zzav().currentTimeMillis();
        zzg();
        long max = Math.max(0L, ((Long) zzen.zzz.zza(null)).longValue());
        zzam zzamVar = this.zze;
        zzak(zzamVar);
        boolean z = true;
        if (!zzamVar.zzH()) {
            zzam zzamVar2 = this.zze;
            zzak(zzamVar2);
            if (!zzamVar2.zzG()) {
                z = false;
            }
        }
        if (z) {
            String zzl = zzg().zzl();
            if (TextUtils.isEmpty(zzl) || ".none.".equals(zzl)) {
                zzg();
                zzemVar = zzen.zzt;
            } else {
                zzg();
                zzemVar = zzen.zzu;
            }
        } else {
            zzg();
            zzemVar = zzen.zzs;
        }
        long max2 = Math.max(0L, ((Long) zzemVar.zza(null)).longValue());
        long zza = this.zzk.zzc.zza();
        long zza2 = this.zzk.zzd.zza();
        zzam zzamVar3 = this.zze;
        zzak(zzamVar3);
        boolean z2 = z;
        long zzd = zzamVar3.zzd();
        zzam zzamVar4 = this.zze;
        zzak(zzamVar4);
        long max3 = Math.max(zzd, zzamVar4.zze());
        if (max3 != 0) {
            long abs2 = currentTimeMillis - Math.abs(max3 - currentTimeMillis);
            long abs3 = Math.abs(zza - currentTimeMillis);
            long abs4 = currentTimeMillis - Math.abs(zza2 - currentTimeMillis);
            long max4 = Math.max(currentTimeMillis - abs3, abs4);
            j2 = abs2 + max;
            if (z2 && max4 > 0) {
                j2 = Math.min(abs2, max4) + max2;
            }
            zzln zzlnVar = this.zzi;
            zzak(zzlnVar);
            if (!zzlnVar.w(max4, max2)) {
                j2 = max4 + max2;
            }
            if (abs4 != 0 && abs4 >= abs2) {
                int i2 = 0;
                while (true) {
                    zzg();
                    if (i2 >= Math.min(20, Math.max(0, ((Integer) zzen.zzB.zza(null)).intValue()))) {
                        break;
                    }
                    zzg();
                    j2 += Math.max(0L, ((Long) zzen.zzA.zza(null)).longValue()) * (1 << i2);
                    if (j2 > abs4) {
                        break;
                    }
                    i2++;
                }
            }
            if (j2 != 0) {
                zzay().zzj().zza("Next upload time is 0");
                zzm().zzc();
                zzkx zzkxVar3 = this.zzg;
                zzak(zzkxVar3);
                zzkxVar3.zza();
                return;
            }
            zzfg zzfgVar = this.zzd;
            zzak(zzfgVar);
            if (!zzfgVar.zza()) {
                zzay().zzj().zza("No network");
                zzm().zzb();
                zzkx zzkxVar4 = this.zzg;
                zzak(zzkxVar4);
                zzkxVar4.zza();
                return;
            }
            long zza3 = this.zzk.zzb.zza();
            zzg();
            long max5 = Math.max(0L, ((Long) zzen.zzq.zza(null)).longValue());
            zzln zzlnVar2 = this.zzi;
            zzak(zzlnVar2);
            if (!zzlnVar2.w(zza3, max5)) {
                j2 = Math.max(j2, zza3 + max5);
            }
            zzm().zzc();
            long currentTimeMillis2 = j2 - zzav().currentTimeMillis();
            if (currentTimeMillis2 <= 0) {
                zzg();
                currentTimeMillis2 = Math.max(0L, ((Long) zzen.zzv.zza(null)).longValue());
                this.zzk.zzc.zzb(zzav().currentTimeMillis());
            }
            zzay().zzj().zzb("Upload scheduled in approximately ms", Long.valueOf(currentTimeMillis2));
            zzkx zzkxVar5 = this.zzg;
            zzak(zzkxVar5);
            zzkxVar5.zzd(currentTimeMillis2);
            return;
        }
        j2 = 0;
        if (j2 != 0) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:372:0x0b76, code lost:
        if (r10 > (com.google.android.gms.measurement.internal.zzag.zzA() + r8)) goto L404;
     */
    /* JADX WARN: Removed duplicated region for block: B:111:0x03a7 A[Catch: all -> 0x0d0d, TryCatch #3 {all -> 0x0d0d, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:173:0x0538, B:24:0x00f3, B:26:0x0101, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:111:0x03a7, B:112:0x03b3, B:115:0x03bd, B:121:0x03e0, B:118:0x03cf, B:143:0x045f, B:145:0x046b, B:148:0x047e, B:150:0x048f, B:152:0x049b, B:172:0x0524, B:157:0x04c5, B:159:0x04d5, B:162:0x04ea, B:164:0x04fb, B:166:0x0507, B:125:0x03e8, B:127:0x03f4, B:129:0x0400, B:141:0x0445, B:133:0x041d, B:136:0x042f, B:138:0x0435, B:140:0x043f, B:68:0x01e4, B:71:0x01ee, B:73:0x01fc, B:77:0x0243, B:74:0x0219, B:76:0x022a, B:81:0x0252, B:83:0x027e, B:84:0x02a8, B:86:0x02de, B:88:0x02e4, B:91:0x02f0, B:93:0x0326, B:94:0x0341, B:96:0x0347, B:98:0x0355, B:102:0x0368, B:99:0x035d, B:105:0x036f, B:108:0x0376, B:109:0x038e, B:176:0x054d, B:178:0x055b, B:180:0x0566, B:191:0x0598, B:181:0x056e, B:183:0x0579, B:185:0x057f, B:188:0x058b, B:190:0x0593, B:192:0x059b, B:193:0x05a7, B:196:0x05af, B:198:0x05c1, B:199:0x05cd, B:201:0x05d5, B:205:0x05fa, B:207:0x061f, B:209:0x0630, B:211:0x0636, B:213:0x0642, B:214:0x0673, B:216:0x0679, B:218:0x0687, B:219:0x068b, B:220:0x068e, B:221:0x0691, B:222:0x069f, B:224:0x06a5, B:226:0x06b5, B:227:0x06bc, B:229:0x06c8, B:230:0x06cf, B:231:0x06d2, B:233:0x0712, B:234:0x0725, B:236:0x072b, B:239:0x0745, B:241:0x0760, B:243:0x0779, B:245:0x077e, B:247:0x0782, B:249:0x0786, B:251:0x0790, B:252:0x079a, B:254:0x079e, B:256:0x07a4, B:257:0x07b2, B:258:0x07bb, B:325:0x0a03, B:259:0x07c8, B:261:0x07df, B:267:0x07fb, B:269:0x081f, B:270:0x0827, B:272:0x082d, B:274:0x083f, B:281:0x0868, B:282:0x0888, B:284:0x0894, B:286:0x08a9, B:288:0x08ea, B:292:0x0902, B:294:0x0909, B:296:0x0918, B:298:0x091c, B:300:0x0920, B:302:0x0924, B:303:0x0932, B:305:0x0938, B:307:0x0954, B:308:0x0959, B:324:0x0a00, B:309:0x0974, B:311:0x097c, B:315:0x09a3, B:317:0x09cf, B:318:0x09d6, B:319:0x09e4, B:320:0x09e8, B:322:0x09f2, B:312:0x0989, B:279:0x0853, B:265:0x07e6, B:326:0x0a0f, B:328:0x0a1d, B:329:0x0a23, B:330:0x0a2b, B:332:0x0a31, B:335:0x0a4b, B:337:0x0a5c, B:357:0x0ad0, B:359:0x0ad6, B:361:0x0aee, B:364:0x0af5, B:369:0x0b24, B:371:0x0b66, B:374:0x0b9b, B:375:0x0b9f, B:376:0x0baa, B:378:0x0bed, B:379:0x0bfa, B:381:0x0c09, B:385:0x0c23, B:386:0x0c37, B:388:0x0c3c, B:373:0x0b78, B:365:0x0afd, B:367:0x0b09, B:368:0x0b0d, B:389:0x0c52, B:390:0x0c6a, B:393:0x0c72, B:394:0x0c77, B:395:0x0c87, B:397:0x0ca1, B:398:0x0cbc, B:400:0x0cc6, B:405:0x0ce9, B:404:0x0cd6, B:338:0x0a74, B:340:0x0a7a, B:342:0x0a84, B:344:0x0a8b, B:350:0x0a9b, B:352:0x0aa2, B:354:0x0ac1, B:356:0x0ac8, B:355:0x0ac5, B:351:0x0a9f, B:343:0x0a88, B:202:0x05da, B:204:0x05e0, B:408:0x0cfb), top: B:420:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:145:0x046b A[Catch: all -> 0x0d0d, TryCatch #3 {all -> 0x0d0d, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:173:0x0538, B:24:0x00f3, B:26:0x0101, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:111:0x03a7, B:112:0x03b3, B:115:0x03bd, B:121:0x03e0, B:118:0x03cf, B:143:0x045f, B:145:0x046b, B:148:0x047e, B:150:0x048f, B:152:0x049b, B:172:0x0524, B:157:0x04c5, B:159:0x04d5, B:162:0x04ea, B:164:0x04fb, B:166:0x0507, B:125:0x03e8, B:127:0x03f4, B:129:0x0400, B:141:0x0445, B:133:0x041d, B:136:0x042f, B:138:0x0435, B:140:0x043f, B:68:0x01e4, B:71:0x01ee, B:73:0x01fc, B:77:0x0243, B:74:0x0219, B:76:0x022a, B:81:0x0252, B:83:0x027e, B:84:0x02a8, B:86:0x02de, B:88:0x02e4, B:91:0x02f0, B:93:0x0326, B:94:0x0341, B:96:0x0347, B:98:0x0355, B:102:0x0368, B:99:0x035d, B:105:0x036f, B:108:0x0376, B:109:0x038e, B:176:0x054d, B:178:0x055b, B:180:0x0566, B:191:0x0598, B:181:0x056e, B:183:0x0579, B:185:0x057f, B:188:0x058b, B:190:0x0593, B:192:0x059b, B:193:0x05a7, B:196:0x05af, B:198:0x05c1, B:199:0x05cd, B:201:0x05d5, B:205:0x05fa, B:207:0x061f, B:209:0x0630, B:211:0x0636, B:213:0x0642, B:214:0x0673, B:216:0x0679, B:218:0x0687, B:219:0x068b, B:220:0x068e, B:221:0x0691, B:222:0x069f, B:224:0x06a5, B:226:0x06b5, B:227:0x06bc, B:229:0x06c8, B:230:0x06cf, B:231:0x06d2, B:233:0x0712, B:234:0x0725, B:236:0x072b, B:239:0x0745, B:241:0x0760, B:243:0x0779, B:245:0x077e, B:247:0x0782, B:249:0x0786, B:251:0x0790, B:252:0x079a, B:254:0x079e, B:256:0x07a4, B:257:0x07b2, B:258:0x07bb, B:325:0x0a03, B:259:0x07c8, B:261:0x07df, B:267:0x07fb, B:269:0x081f, B:270:0x0827, B:272:0x082d, B:274:0x083f, B:281:0x0868, B:282:0x0888, B:284:0x0894, B:286:0x08a9, B:288:0x08ea, B:292:0x0902, B:294:0x0909, B:296:0x0918, B:298:0x091c, B:300:0x0920, B:302:0x0924, B:303:0x0932, B:305:0x0938, B:307:0x0954, B:308:0x0959, B:324:0x0a00, B:309:0x0974, B:311:0x097c, B:315:0x09a3, B:317:0x09cf, B:318:0x09d6, B:319:0x09e4, B:320:0x09e8, B:322:0x09f2, B:312:0x0989, B:279:0x0853, B:265:0x07e6, B:326:0x0a0f, B:328:0x0a1d, B:329:0x0a23, B:330:0x0a2b, B:332:0x0a31, B:335:0x0a4b, B:337:0x0a5c, B:357:0x0ad0, B:359:0x0ad6, B:361:0x0aee, B:364:0x0af5, B:369:0x0b24, B:371:0x0b66, B:374:0x0b9b, B:375:0x0b9f, B:376:0x0baa, B:378:0x0bed, B:379:0x0bfa, B:381:0x0c09, B:385:0x0c23, B:386:0x0c37, B:388:0x0c3c, B:373:0x0b78, B:365:0x0afd, B:367:0x0b09, B:368:0x0b0d, B:389:0x0c52, B:390:0x0c6a, B:393:0x0c72, B:394:0x0c77, B:395:0x0c87, B:397:0x0ca1, B:398:0x0cbc, B:400:0x0cc6, B:405:0x0ce9, B:404:0x0cd6, B:338:0x0a74, B:340:0x0a7a, B:342:0x0a84, B:344:0x0a8b, B:350:0x0a9b, B:352:0x0aa2, B:354:0x0ac1, B:356:0x0ac8, B:355:0x0ac5, B:351:0x0a9f, B:343:0x0a88, B:202:0x05da, B:204:0x05e0, B:408:0x0cfb), top: B:420:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:157:0x04c5 A[Catch: all -> 0x0d0d, TryCatch #3 {all -> 0x0d0d, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:173:0x0538, B:24:0x00f3, B:26:0x0101, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:111:0x03a7, B:112:0x03b3, B:115:0x03bd, B:121:0x03e0, B:118:0x03cf, B:143:0x045f, B:145:0x046b, B:148:0x047e, B:150:0x048f, B:152:0x049b, B:172:0x0524, B:157:0x04c5, B:159:0x04d5, B:162:0x04ea, B:164:0x04fb, B:166:0x0507, B:125:0x03e8, B:127:0x03f4, B:129:0x0400, B:141:0x0445, B:133:0x041d, B:136:0x042f, B:138:0x0435, B:140:0x043f, B:68:0x01e4, B:71:0x01ee, B:73:0x01fc, B:77:0x0243, B:74:0x0219, B:76:0x022a, B:81:0x0252, B:83:0x027e, B:84:0x02a8, B:86:0x02de, B:88:0x02e4, B:91:0x02f0, B:93:0x0326, B:94:0x0341, B:96:0x0347, B:98:0x0355, B:102:0x0368, B:99:0x035d, B:105:0x036f, B:108:0x0376, B:109:0x038e, B:176:0x054d, B:178:0x055b, B:180:0x0566, B:191:0x0598, B:181:0x056e, B:183:0x0579, B:185:0x057f, B:188:0x058b, B:190:0x0593, B:192:0x059b, B:193:0x05a7, B:196:0x05af, B:198:0x05c1, B:199:0x05cd, B:201:0x05d5, B:205:0x05fa, B:207:0x061f, B:209:0x0630, B:211:0x0636, B:213:0x0642, B:214:0x0673, B:216:0x0679, B:218:0x0687, B:219:0x068b, B:220:0x068e, B:221:0x0691, B:222:0x069f, B:224:0x06a5, B:226:0x06b5, B:227:0x06bc, B:229:0x06c8, B:230:0x06cf, B:231:0x06d2, B:233:0x0712, B:234:0x0725, B:236:0x072b, B:239:0x0745, B:241:0x0760, B:243:0x0779, B:245:0x077e, B:247:0x0782, B:249:0x0786, B:251:0x0790, B:252:0x079a, B:254:0x079e, B:256:0x07a4, B:257:0x07b2, B:258:0x07bb, B:325:0x0a03, B:259:0x07c8, B:261:0x07df, B:267:0x07fb, B:269:0x081f, B:270:0x0827, B:272:0x082d, B:274:0x083f, B:281:0x0868, B:282:0x0888, B:284:0x0894, B:286:0x08a9, B:288:0x08ea, B:292:0x0902, B:294:0x0909, B:296:0x0918, B:298:0x091c, B:300:0x0920, B:302:0x0924, B:303:0x0932, B:305:0x0938, B:307:0x0954, B:308:0x0959, B:324:0x0a00, B:309:0x0974, B:311:0x097c, B:315:0x09a3, B:317:0x09cf, B:318:0x09d6, B:319:0x09e4, B:320:0x09e8, B:322:0x09f2, B:312:0x0989, B:279:0x0853, B:265:0x07e6, B:326:0x0a0f, B:328:0x0a1d, B:329:0x0a23, B:330:0x0a2b, B:332:0x0a31, B:335:0x0a4b, B:337:0x0a5c, B:357:0x0ad0, B:359:0x0ad6, B:361:0x0aee, B:364:0x0af5, B:369:0x0b24, B:371:0x0b66, B:374:0x0b9b, B:375:0x0b9f, B:376:0x0baa, B:378:0x0bed, B:379:0x0bfa, B:381:0x0c09, B:385:0x0c23, B:386:0x0c37, B:388:0x0c3c, B:373:0x0b78, B:365:0x0afd, B:367:0x0b09, B:368:0x0b0d, B:389:0x0c52, B:390:0x0c6a, B:393:0x0c72, B:394:0x0c77, B:395:0x0c87, B:397:0x0ca1, B:398:0x0cbc, B:400:0x0cc6, B:405:0x0ce9, B:404:0x0cd6, B:338:0x0a74, B:340:0x0a7a, B:342:0x0a84, B:344:0x0a8b, B:350:0x0a9b, B:352:0x0aa2, B:354:0x0ac1, B:356:0x0ac8, B:355:0x0ac5, B:351:0x0a9f, B:343:0x0a88, B:202:0x05da, B:204:0x05e0, B:408:0x0cfb), top: B:420:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:269:0x081f A[Catch: all -> 0x0d0d, TryCatch #3 {all -> 0x0d0d, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:173:0x0538, B:24:0x00f3, B:26:0x0101, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:111:0x03a7, B:112:0x03b3, B:115:0x03bd, B:121:0x03e0, B:118:0x03cf, B:143:0x045f, B:145:0x046b, B:148:0x047e, B:150:0x048f, B:152:0x049b, B:172:0x0524, B:157:0x04c5, B:159:0x04d5, B:162:0x04ea, B:164:0x04fb, B:166:0x0507, B:125:0x03e8, B:127:0x03f4, B:129:0x0400, B:141:0x0445, B:133:0x041d, B:136:0x042f, B:138:0x0435, B:140:0x043f, B:68:0x01e4, B:71:0x01ee, B:73:0x01fc, B:77:0x0243, B:74:0x0219, B:76:0x022a, B:81:0x0252, B:83:0x027e, B:84:0x02a8, B:86:0x02de, B:88:0x02e4, B:91:0x02f0, B:93:0x0326, B:94:0x0341, B:96:0x0347, B:98:0x0355, B:102:0x0368, B:99:0x035d, B:105:0x036f, B:108:0x0376, B:109:0x038e, B:176:0x054d, B:178:0x055b, B:180:0x0566, B:191:0x0598, B:181:0x056e, B:183:0x0579, B:185:0x057f, B:188:0x058b, B:190:0x0593, B:192:0x059b, B:193:0x05a7, B:196:0x05af, B:198:0x05c1, B:199:0x05cd, B:201:0x05d5, B:205:0x05fa, B:207:0x061f, B:209:0x0630, B:211:0x0636, B:213:0x0642, B:214:0x0673, B:216:0x0679, B:218:0x0687, B:219:0x068b, B:220:0x068e, B:221:0x0691, B:222:0x069f, B:224:0x06a5, B:226:0x06b5, B:227:0x06bc, B:229:0x06c8, B:230:0x06cf, B:231:0x06d2, B:233:0x0712, B:234:0x0725, B:236:0x072b, B:239:0x0745, B:241:0x0760, B:243:0x0779, B:245:0x077e, B:247:0x0782, B:249:0x0786, B:251:0x0790, B:252:0x079a, B:254:0x079e, B:256:0x07a4, B:257:0x07b2, B:258:0x07bb, B:325:0x0a03, B:259:0x07c8, B:261:0x07df, B:267:0x07fb, B:269:0x081f, B:270:0x0827, B:272:0x082d, B:274:0x083f, B:281:0x0868, B:282:0x0888, B:284:0x0894, B:286:0x08a9, B:288:0x08ea, B:292:0x0902, B:294:0x0909, B:296:0x0918, B:298:0x091c, B:300:0x0920, B:302:0x0924, B:303:0x0932, B:305:0x0938, B:307:0x0954, B:308:0x0959, B:324:0x0a00, B:309:0x0974, B:311:0x097c, B:315:0x09a3, B:317:0x09cf, B:318:0x09d6, B:319:0x09e4, B:320:0x09e8, B:322:0x09f2, B:312:0x0989, B:279:0x0853, B:265:0x07e6, B:326:0x0a0f, B:328:0x0a1d, B:329:0x0a23, B:330:0x0a2b, B:332:0x0a31, B:335:0x0a4b, B:337:0x0a5c, B:357:0x0ad0, B:359:0x0ad6, B:361:0x0aee, B:364:0x0af5, B:369:0x0b24, B:371:0x0b66, B:374:0x0b9b, B:375:0x0b9f, B:376:0x0baa, B:378:0x0bed, B:379:0x0bfa, B:381:0x0c09, B:385:0x0c23, B:386:0x0c37, B:388:0x0c3c, B:373:0x0b78, B:365:0x0afd, B:367:0x0b09, B:368:0x0b0d, B:389:0x0c52, B:390:0x0c6a, B:393:0x0c72, B:394:0x0c77, B:395:0x0c87, B:397:0x0ca1, B:398:0x0cbc, B:400:0x0cc6, B:405:0x0ce9, B:404:0x0cd6, B:338:0x0a74, B:340:0x0a7a, B:342:0x0a84, B:344:0x0a8b, B:350:0x0a9b, B:352:0x0aa2, B:354:0x0ac1, B:356:0x0ac8, B:355:0x0ac5, B:351:0x0a9f, B:343:0x0a88, B:202:0x05da, B:204:0x05e0, B:408:0x0cfb), top: B:420:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:281:0x0868 A[Catch: all -> 0x0d0d, TryCatch #3 {all -> 0x0d0d, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:173:0x0538, B:24:0x00f3, B:26:0x0101, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:111:0x03a7, B:112:0x03b3, B:115:0x03bd, B:121:0x03e0, B:118:0x03cf, B:143:0x045f, B:145:0x046b, B:148:0x047e, B:150:0x048f, B:152:0x049b, B:172:0x0524, B:157:0x04c5, B:159:0x04d5, B:162:0x04ea, B:164:0x04fb, B:166:0x0507, B:125:0x03e8, B:127:0x03f4, B:129:0x0400, B:141:0x0445, B:133:0x041d, B:136:0x042f, B:138:0x0435, B:140:0x043f, B:68:0x01e4, B:71:0x01ee, B:73:0x01fc, B:77:0x0243, B:74:0x0219, B:76:0x022a, B:81:0x0252, B:83:0x027e, B:84:0x02a8, B:86:0x02de, B:88:0x02e4, B:91:0x02f0, B:93:0x0326, B:94:0x0341, B:96:0x0347, B:98:0x0355, B:102:0x0368, B:99:0x035d, B:105:0x036f, B:108:0x0376, B:109:0x038e, B:176:0x054d, B:178:0x055b, B:180:0x0566, B:191:0x0598, B:181:0x056e, B:183:0x0579, B:185:0x057f, B:188:0x058b, B:190:0x0593, B:192:0x059b, B:193:0x05a7, B:196:0x05af, B:198:0x05c1, B:199:0x05cd, B:201:0x05d5, B:205:0x05fa, B:207:0x061f, B:209:0x0630, B:211:0x0636, B:213:0x0642, B:214:0x0673, B:216:0x0679, B:218:0x0687, B:219:0x068b, B:220:0x068e, B:221:0x0691, B:222:0x069f, B:224:0x06a5, B:226:0x06b5, B:227:0x06bc, B:229:0x06c8, B:230:0x06cf, B:231:0x06d2, B:233:0x0712, B:234:0x0725, B:236:0x072b, B:239:0x0745, B:241:0x0760, B:243:0x0779, B:245:0x077e, B:247:0x0782, B:249:0x0786, B:251:0x0790, B:252:0x079a, B:254:0x079e, B:256:0x07a4, B:257:0x07b2, B:258:0x07bb, B:325:0x0a03, B:259:0x07c8, B:261:0x07df, B:267:0x07fb, B:269:0x081f, B:270:0x0827, B:272:0x082d, B:274:0x083f, B:281:0x0868, B:282:0x0888, B:284:0x0894, B:286:0x08a9, B:288:0x08ea, B:292:0x0902, B:294:0x0909, B:296:0x0918, B:298:0x091c, B:300:0x0920, B:302:0x0924, B:303:0x0932, B:305:0x0938, B:307:0x0954, B:308:0x0959, B:324:0x0a00, B:309:0x0974, B:311:0x097c, B:315:0x09a3, B:317:0x09cf, B:318:0x09d6, B:319:0x09e4, B:320:0x09e8, B:322:0x09f2, B:312:0x0989, B:279:0x0853, B:265:0x07e6, B:326:0x0a0f, B:328:0x0a1d, B:329:0x0a23, B:330:0x0a2b, B:332:0x0a31, B:335:0x0a4b, B:337:0x0a5c, B:357:0x0ad0, B:359:0x0ad6, B:361:0x0aee, B:364:0x0af5, B:369:0x0b24, B:371:0x0b66, B:374:0x0b9b, B:375:0x0b9f, B:376:0x0baa, B:378:0x0bed, B:379:0x0bfa, B:381:0x0c09, B:385:0x0c23, B:386:0x0c37, B:388:0x0c3c, B:373:0x0b78, B:365:0x0afd, B:367:0x0b09, B:368:0x0b0d, B:389:0x0c52, B:390:0x0c6a, B:393:0x0c72, B:394:0x0c77, B:395:0x0c87, B:397:0x0ca1, B:398:0x0cbc, B:400:0x0cc6, B:405:0x0ce9, B:404:0x0cd6, B:338:0x0a74, B:340:0x0a7a, B:342:0x0a84, B:344:0x0a8b, B:350:0x0a9b, B:352:0x0aa2, B:354:0x0ac1, B:356:0x0ac8, B:355:0x0ac5, B:351:0x0a9f, B:343:0x0a88, B:202:0x05da, B:204:0x05e0, B:408:0x0cfb), top: B:420:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:282:0x0888 A[Catch: all -> 0x0d0d, TryCatch #3 {all -> 0x0d0d, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:173:0x0538, B:24:0x00f3, B:26:0x0101, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:111:0x03a7, B:112:0x03b3, B:115:0x03bd, B:121:0x03e0, B:118:0x03cf, B:143:0x045f, B:145:0x046b, B:148:0x047e, B:150:0x048f, B:152:0x049b, B:172:0x0524, B:157:0x04c5, B:159:0x04d5, B:162:0x04ea, B:164:0x04fb, B:166:0x0507, B:125:0x03e8, B:127:0x03f4, B:129:0x0400, B:141:0x0445, B:133:0x041d, B:136:0x042f, B:138:0x0435, B:140:0x043f, B:68:0x01e4, B:71:0x01ee, B:73:0x01fc, B:77:0x0243, B:74:0x0219, B:76:0x022a, B:81:0x0252, B:83:0x027e, B:84:0x02a8, B:86:0x02de, B:88:0x02e4, B:91:0x02f0, B:93:0x0326, B:94:0x0341, B:96:0x0347, B:98:0x0355, B:102:0x0368, B:99:0x035d, B:105:0x036f, B:108:0x0376, B:109:0x038e, B:176:0x054d, B:178:0x055b, B:180:0x0566, B:191:0x0598, B:181:0x056e, B:183:0x0579, B:185:0x057f, B:188:0x058b, B:190:0x0593, B:192:0x059b, B:193:0x05a7, B:196:0x05af, B:198:0x05c1, B:199:0x05cd, B:201:0x05d5, B:205:0x05fa, B:207:0x061f, B:209:0x0630, B:211:0x0636, B:213:0x0642, B:214:0x0673, B:216:0x0679, B:218:0x0687, B:219:0x068b, B:220:0x068e, B:221:0x0691, B:222:0x069f, B:224:0x06a5, B:226:0x06b5, B:227:0x06bc, B:229:0x06c8, B:230:0x06cf, B:231:0x06d2, B:233:0x0712, B:234:0x0725, B:236:0x072b, B:239:0x0745, B:241:0x0760, B:243:0x0779, B:245:0x077e, B:247:0x0782, B:249:0x0786, B:251:0x0790, B:252:0x079a, B:254:0x079e, B:256:0x07a4, B:257:0x07b2, B:258:0x07bb, B:325:0x0a03, B:259:0x07c8, B:261:0x07df, B:267:0x07fb, B:269:0x081f, B:270:0x0827, B:272:0x082d, B:274:0x083f, B:281:0x0868, B:282:0x0888, B:284:0x0894, B:286:0x08a9, B:288:0x08ea, B:292:0x0902, B:294:0x0909, B:296:0x0918, B:298:0x091c, B:300:0x0920, B:302:0x0924, B:303:0x0932, B:305:0x0938, B:307:0x0954, B:308:0x0959, B:324:0x0a00, B:309:0x0974, B:311:0x097c, B:315:0x09a3, B:317:0x09cf, B:318:0x09d6, B:319:0x09e4, B:320:0x09e8, B:322:0x09f2, B:312:0x0989, B:279:0x0853, B:265:0x07e6, B:326:0x0a0f, B:328:0x0a1d, B:329:0x0a23, B:330:0x0a2b, B:332:0x0a31, B:335:0x0a4b, B:337:0x0a5c, B:357:0x0ad0, B:359:0x0ad6, B:361:0x0aee, B:364:0x0af5, B:369:0x0b24, B:371:0x0b66, B:374:0x0b9b, B:375:0x0b9f, B:376:0x0baa, B:378:0x0bed, B:379:0x0bfa, B:381:0x0c09, B:385:0x0c23, B:386:0x0c37, B:388:0x0c3c, B:373:0x0b78, B:365:0x0afd, B:367:0x0b09, B:368:0x0b0d, B:389:0x0c52, B:390:0x0c6a, B:393:0x0c72, B:394:0x0c77, B:395:0x0c87, B:397:0x0ca1, B:398:0x0cbc, B:400:0x0cc6, B:405:0x0ce9, B:404:0x0cd6, B:338:0x0a74, B:340:0x0a7a, B:342:0x0a84, B:344:0x0a8b, B:350:0x0a9b, B:352:0x0aa2, B:354:0x0ac1, B:356:0x0ac8, B:355:0x0ac5, B:351:0x0a9f, B:343:0x0a88, B:202:0x05da, B:204:0x05e0, B:408:0x0cfb), top: B:420:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:290:0x08ff  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0901  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x0909 A[Catch: all -> 0x0d0d, TryCatch #3 {all -> 0x0d0d, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:173:0x0538, B:24:0x00f3, B:26:0x0101, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:111:0x03a7, B:112:0x03b3, B:115:0x03bd, B:121:0x03e0, B:118:0x03cf, B:143:0x045f, B:145:0x046b, B:148:0x047e, B:150:0x048f, B:152:0x049b, B:172:0x0524, B:157:0x04c5, B:159:0x04d5, B:162:0x04ea, B:164:0x04fb, B:166:0x0507, B:125:0x03e8, B:127:0x03f4, B:129:0x0400, B:141:0x0445, B:133:0x041d, B:136:0x042f, B:138:0x0435, B:140:0x043f, B:68:0x01e4, B:71:0x01ee, B:73:0x01fc, B:77:0x0243, B:74:0x0219, B:76:0x022a, B:81:0x0252, B:83:0x027e, B:84:0x02a8, B:86:0x02de, B:88:0x02e4, B:91:0x02f0, B:93:0x0326, B:94:0x0341, B:96:0x0347, B:98:0x0355, B:102:0x0368, B:99:0x035d, B:105:0x036f, B:108:0x0376, B:109:0x038e, B:176:0x054d, B:178:0x055b, B:180:0x0566, B:191:0x0598, B:181:0x056e, B:183:0x0579, B:185:0x057f, B:188:0x058b, B:190:0x0593, B:192:0x059b, B:193:0x05a7, B:196:0x05af, B:198:0x05c1, B:199:0x05cd, B:201:0x05d5, B:205:0x05fa, B:207:0x061f, B:209:0x0630, B:211:0x0636, B:213:0x0642, B:214:0x0673, B:216:0x0679, B:218:0x0687, B:219:0x068b, B:220:0x068e, B:221:0x0691, B:222:0x069f, B:224:0x06a5, B:226:0x06b5, B:227:0x06bc, B:229:0x06c8, B:230:0x06cf, B:231:0x06d2, B:233:0x0712, B:234:0x0725, B:236:0x072b, B:239:0x0745, B:241:0x0760, B:243:0x0779, B:245:0x077e, B:247:0x0782, B:249:0x0786, B:251:0x0790, B:252:0x079a, B:254:0x079e, B:256:0x07a4, B:257:0x07b2, B:258:0x07bb, B:325:0x0a03, B:259:0x07c8, B:261:0x07df, B:267:0x07fb, B:269:0x081f, B:270:0x0827, B:272:0x082d, B:274:0x083f, B:281:0x0868, B:282:0x0888, B:284:0x0894, B:286:0x08a9, B:288:0x08ea, B:292:0x0902, B:294:0x0909, B:296:0x0918, B:298:0x091c, B:300:0x0920, B:302:0x0924, B:303:0x0932, B:305:0x0938, B:307:0x0954, B:308:0x0959, B:324:0x0a00, B:309:0x0974, B:311:0x097c, B:315:0x09a3, B:317:0x09cf, B:318:0x09d6, B:319:0x09e4, B:320:0x09e8, B:322:0x09f2, B:312:0x0989, B:279:0x0853, B:265:0x07e6, B:326:0x0a0f, B:328:0x0a1d, B:329:0x0a23, B:330:0x0a2b, B:332:0x0a31, B:335:0x0a4b, B:337:0x0a5c, B:357:0x0ad0, B:359:0x0ad6, B:361:0x0aee, B:364:0x0af5, B:369:0x0b24, B:371:0x0b66, B:374:0x0b9b, B:375:0x0b9f, B:376:0x0baa, B:378:0x0bed, B:379:0x0bfa, B:381:0x0c09, B:385:0x0c23, B:386:0x0c37, B:388:0x0c3c, B:373:0x0b78, B:365:0x0afd, B:367:0x0b09, B:368:0x0b0d, B:389:0x0c52, B:390:0x0c6a, B:393:0x0c72, B:394:0x0c77, B:395:0x0c87, B:397:0x0ca1, B:398:0x0cbc, B:400:0x0cc6, B:405:0x0ce9, B:404:0x0cd6, B:338:0x0a74, B:340:0x0a7a, B:342:0x0a84, B:344:0x0a8b, B:350:0x0a9b, B:352:0x0aa2, B:354:0x0ac1, B:356:0x0ac8, B:355:0x0ac5, B:351:0x0a9f, B:343:0x0a88, B:202:0x05da, B:204:0x05e0, B:408:0x0cfb), top: B:420:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:303:0x0932 A[Catch: all -> 0x0d0d, TryCatch #3 {all -> 0x0d0d, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:173:0x0538, B:24:0x00f3, B:26:0x0101, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:111:0x03a7, B:112:0x03b3, B:115:0x03bd, B:121:0x03e0, B:118:0x03cf, B:143:0x045f, B:145:0x046b, B:148:0x047e, B:150:0x048f, B:152:0x049b, B:172:0x0524, B:157:0x04c5, B:159:0x04d5, B:162:0x04ea, B:164:0x04fb, B:166:0x0507, B:125:0x03e8, B:127:0x03f4, B:129:0x0400, B:141:0x0445, B:133:0x041d, B:136:0x042f, B:138:0x0435, B:140:0x043f, B:68:0x01e4, B:71:0x01ee, B:73:0x01fc, B:77:0x0243, B:74:0x0219, B:76:0x022a, B:81:0x0252, B:83:0x027e, B:84:0x02a8, B:86:0x02de, B:88:0x02e4, B:91:0x02f0, B:93:0x0326, B:94:0x0341, B:96:0x0347, B:98:0x0355, B:102:0x0368, B:99:0x035d, B:105:0x036f, B:108:0x0376, B:109:0x038e, B:176:0x054d, B:178:0x055b, B:180:0x0566, B:191:0x0598, B:181:0x056e, B:183:0x0579, B:185:0x057f, B:188:0x058b, B:190:0x0593, B:192:0x059b, B:193:0x05a7, B:196:0x05af, B:198:0x05c1, B:199:0x05cd, B:201:0x05d5, B:205:0x05fa, B:207:0x061f, B:209:0x0630, B:211:0x0636, B:213:0x0642, B:214:0x0673, B:216:0x0679, B:218:0x0687, B:219:0x068b, B:220:0x068e, B:221:0x0691, B:222:0x069f, B:224:0x06a5, B:226:0x06b5, B:227:0x06bc, B:229:0x06c8, B:230:0x06cf, B:231:0x06d2, B:233:0x0712, B:234:0x0725, B:236:0x072b, B:239:0x0745, B:241:0x0760, B:243:0x0779, B:245:0x077e, B:247:0x0782, B:249:0x0786, B:251:0x0790, B:252:0x079a, B:254:0x079e, B:256:0x07a4, B:257:0x07b2, B:258:0x07bb, B:325:0x0a03, B:259:0x07c8, B:261:0x07df, B:267:0x07fb, B:269:0x081f, B:270:0x0827, B:272:0x082d, B:274:0x083f, B:281:0x0868, B:282:0x0888, B:284:0x0894, B:286:0x08a9, B:288:0x08ea, B:292:0x0902, B:294:0x0909, B:296:0x0918, B:298:0x091c, B:300:0x0920, B:302:0x0924, B:303:0x0932, B:305:0x0938, B:307:0x0954, B:308:0x0959, B:324:0x0a00, B:309:0x0974, B:311:0x097c, B:315:0x09a3, B:317:0x09cf, B:318:0x09d6, B:319:0x09e4, B:320:0x09e8, B:322:0x09f2, B:312:0x0989, B:279:0x0853, B:265:0x07e6, B:326:0x0a0f, B:328:0x0a1d, B:329:0x0a23, B:330:0x0a2b, B:332:0x0a31, B:335:0x0a4b, B:337:0x0a5c, B:357:0x0ad0, B:359:0x0ad6, B:361:0x0aee, B:364:0x0af5, B:369:0x0b24, B:371:0x0b66, B:374:0x0b9b, B:375:0x0b9f, B:376:0x0baa, B:378:0x0bed, B:379:0x0bfa, B:381:0x0c09, B:385:0x0c23, B:386:0x0c37, B:388:0x0c3c, B:373:0x0b78, B:365:0x0afd, B:367:0x0b09, B:368:0x0b0d, B:389:0x0c52, B:390:0x0c6a, B:393:0x0c72, B:394:0x0c77, B:395:0x0c87, B:397:0x0ca1, B:398:0x0cbc, B:400:0x0cc6, B:405:0x0ce9, B:404:0x0cd6, B:338:0x0a74, B:340:0x0a7a, B:342:0x0a84, B:344:0x0a8b, B:350:0x0a9b, B:352:0x0aa2, B:354:0x0ac1, B:356:0x0ac8, B:355:0x0ac5, B:351:0x0a9f, B:343:0x0a88, B:202:0x05da, B:204:0x05e0, B:408:0x0cfb), top: B:420:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:371:0x0b66 A[Catch: all -> 0x0d0d, TryCatch #3 {all -> 0x0d0d, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:173:0x0538, B:24:0x00f3, B:26:0x0101, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:111:0x03a7, B:112:0x03b3, B:115:0x03bd, B:121:0x03e0, B:118:0x03cf, B:143:0x045f, B:145:0x046b, B:148:0x047e, B:150:0x048f, B:152:0x049b, B:172:0x0524, B:157:0x04c5, B:159:0x04d5, B:162:0x04ea, B:164:0x04fb, B:166:0x0507, B:125:0x03e8, B:127:0x03f4, B:129:0x0400, B:141:0x0445, B:133:0x041d, B:136:0x042f, B:138:0x0435, B:140:0x043f, B:68:0x01e4, B:71:0x01ee, B:73:0x01fc, B:77:0x0243, B:74:0x0219, B:76:0x022a, B:81:0x0252, B:83:0x027e, B:84:0x02a8, B:86:0x02de, B:88:0x02e4, B:91:0x02f0, B:93:0x0326, B:94:0x0341, B:96:0x0347, B:98:0x0355, B:102:0x0368, B:99:0x035d, B:105:0x036f, B:108:0x0376, B:109:0x038e, B:176:0x054d, B:178:0x055b, B:180:0x0566, B:191:0x0598, B:181:0x056e, B:183:0x0579, B:185:0x057f, B:188:0x058b, B:190:0x0593, B:192:0x059b, B:193:0x05a7, B:196:0x05af, B:198:0x05c1, B:199:0x05cd, B:201:0x05d5, B:205:0x05fa, B:207:0x061f, B:209:0x0630, B:211:0x0636, B:213:0x0642, B:214:0x0673, B:216:0x0679, B:218:0x0687, B:219:0x068b, B:220:0x068e, B:221:0x0691, B:222:0x069f, B:224:0x06a5, B:226:0x06b5, B:227:0x06bc, B:229:0x06c8, B:230:0x06cf, B:231:0x06d2, B:233:0x0712, B:234:0x0725, B:236:0x072b, B:239:0x0745, B:241:0x0760, B:243:0x0779, B:245:0x077e, B:247:0x0782, B:249:0x0786, B:251:0x0790, B:252:0x079a, B:254:0x079e, B:256:0x07a4, B:257:0x07b2, B:258:0x07bb, B:325:0x0a03, B:259:0x07c8, B:261:0x07df, B:267:0x07fb, B:269:0x081f, B:270:0x0827, B:272:0x082d, B:274:0x083f, B:281:0x0868, B:282:0x0888, B:284:0x0894, B:286:0x08a9, B:288:0x08ea, B:292:0x0902, B:294:0x0909, B:296:0x0918, B:298:0x091c, B:300:0x0920, B:302:0x0924, B:303:0x0932, B:305:0x0938, B:307:0x0954, B:308:0x0959, B:324:0x0a00, B:309:0x0974, B:311:0x097c, B:315:0x09a3, B:317:0x09cf, B:318:0x09d6, B:319:0x09e4, B:320:0x09e8, B:322:0x09f2, B:312:0x0989, B:279:0x0853, B:265:0x07e6, B:326:0x0a0f, B:328:0x0a1d, B:329:0x0a23, B:330:0x0a2b, B:332:0x0a31, B:335:0x0a4b, B:337:0x0a5c, B:357:0x0ad0, B:359:0x0ad6, B:361:0x0aee, B:364:0x0af5, B:369:0x0b24, B:371:0x0b66, B:374:0x0b9b, B:375:0x0b9f, B:376:0x0baa, B:378:0x0bed, B:379:0x0bfa, B:381:0x0c09, B:385:0x0c23, B:386:0x0c37, B:388:0x0c3c, B:373:0x0b78, B:365:0x0afd, B:367:0x0b09, B:368:0x0b0d, B:389:0x0c52, B:390:0x0c6a, B:393:0x0c72, B:394:0x0c77, B:395:0x0c87, B:397:0x0ca1, B:398:0x0cbc, B:400:0x0cc6, B:405:0x0ce9, B:404:0x0cd6, B:338:0x0a74, B:340:0x0a7a, B:342:0x0a84, B:344:0x0a8b, B:350:0x0a9b, B:352:0x0aa2, B:354:0x0ac1, B:356:0x0ac8, B:355:0x0ac5, B:351:0x0a9f, B:343:0x0a88, B:202:0x05da, B:204:0x05e0, B:408:0x0cfb), top: B:420:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:378:0x0bed A[Catch: all -> 0x0d0d, TRY_LEAVE, TryCatch #3 {all -> 0x0d0d, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:173:0x0538, B:24:0x00f3, B:26:0x0101, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:111:0x03a7, B:112:0x03b3, B:115:0x03bd, B:121:0x03e0, B:118:0x03cf, B:143:0x045f, B:145:0x046b, B:148:0x047e, B:150:0x048f, B:152:0x049b, B:172:0x0524, B:157:0x04c5, B:159:0x04d5, B:162:0x04ea, B:164:0x04fb, B:166:0x0507, B:125:0x03e8, B:127:0x03f4, B:129:0x0400, B:141:0x0445, B:133:0x041d, B:136:0x042f, B:138:0x0435, B:140:0x043f, B:68:0x01e4, B:71:0x01ee, B:73:0x01fc, B:77:0x0243, B:74:0x0219, B:76:0x022a, B:81:0x0252, B:83:0x027e, B:84:0x02a8, B:86:0x02de, B:88:0x02e4, B:91:0x02f0, B:93:0x0326, B:94:0x0341, B:96:0x0347, B:98:0x0355, B:102:0x0368, B:99:0x035d, B:105:0x036f, B:108:0x0376, B:109:0x038e, B:176:0x054d, B:178:0x055b, B:180:0x0566, B:191:0x0598, B:181:0x056e, B:183:0x0579, B:185:0x057f, B:188:0x058b, B:190:0x0593, B:192:0x059b, B:193:0x05a7, B:196:0x05af, B:198:0x05c1, B:199:0x05cd, B:201:0x05d5, B:205:0x05fa, B:207:0x061f, B:209:0x0630, B:211:0x0636, B:213:0x0642, B:214:0x0673, B:216:0x0679, B:218:0x0687, B:219:0x068b, B:220:0x068e, B:221:0x0691, B:222:0x069f, B:224:0x06a5, B:226:0x06b5, B:227:0x06bc, B:229:0x06c8, B:230:0x06cf, B:231:0x06d2, B:233:0x0712, B:234:0x0725, B:236:0x072b, B:239:0x0745, B:241:0x0760, B:243:0x0779, B:245:0x077e, B:247:0x0782, B:249:0x0786, B:251:0x0790, B:252:0x079a, B:254:0x079e, B:256:0x07a4, B:257:0x07b2, B:258:0x07bb, B:325:0x0a03, B:259:0x07c8, B:261:0x07df, B:267:0x07fb, B:269:0x081f, B:270:0x0827, B:272:0x082d, B:274:0x083f, B:281:0x0868, B:282:0x0888, B:284:0x0894, B:286:0x08a9, B:288:0x08ea, B:292:0x0902, B:294:0x0909, B:296:0x0918, B:298:0x091c, B:300:0x0920, B:302:0x0924, B:303:0x0932, B:305:0x0938, B:307:0x0954, B:308:0x0959, B:324:0x0a00, B:309:0x0974, B:311:0x097c, B:315:0x09a3, B:317:0x09cf, B:318:0x09d6, B:319:0x09e4, B:320:0x09e8, B:322:0x09f2, B:312:0x0989, B:279:0x0853, B:265:0x07e6, B:326:0x0a0f, B:328:0x0a1d, B:329:0x0a23, B:330:0x0a2b, B:332:0x0a31, B:335:0x0a4b, B:337:0x0a5c, B:357:0x0ad0, B:359:0x0ad6, B:361:0x0aee, B:364:0x0af5, B:369:0x0b24, B:371:0x0b66, B:374:0x0b9b, B:375:0x0b9f, B:376:0x0baa, B:378:0x0bed, B:379:0x0bfa, B:381:0x0c09, B:385:0x0c23, B:386:0x0c37, B:388:0x0c3c, B:373:0x0b78, B:365:0x0afd, B:367:0x0b09, B:368:0x0b0d, B:389:0x0c52, B:390:0x0c6a, B:393:0x0c72, B:394:0x0c77, B:395:0x0c87, B:397:0x0ca1, B:398:0x0cbc, B:400:0x0cc6, B:405:0x0ce9, B:404:0x0cd6, B:338:0x0a74, B:340:0x0a7a, B:342:0x0a84, B:344:0x0a8b, B:350:0x0a9b, B:352:0x0aa2, B:354:0x0ac1, B:356:0x0ac8, B:355:0x0ac5, B:351:0x0a9f, B:343:0x0a88, B:202:0x05da, B:204:0x05e0, B:408:0x0cfb), top: B:420:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:381:0x0c09 A[Catch: SQLiteException -> 0x0c21, all -> 0x0d0d, TRY_LEAVE, TryCatch #2 {SQLiteException -> 0x0c21, blocks: (B:379:0x0bfa, B:381:0x0c09), top: B:418:0x0bfa, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:392:0x0c70  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x0ca1 A[Catch: all -> 0x0d0d, TryCatch #3 {all -> 0x0d0d, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:173:0x0538, B:24:0x00f3, B:26:0x0101, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:111:0x03a7, B:112:0x03b3, B:115:0x03bd, B:121:0x03e0, B:118:0x03cf, B:143:0x045f, B:145:0x046b, B:148:0x047e, B:150:0x048f, B:152:0x049b, B:172:0x0524, B:157:0x04c5, B:159:0x04d5, B:162:0x04ea, B:164:0x04fb, B:166:0x0507, B:125:0x03e8, B:127:0x03f4, B:129:0x0400, B:141:0x0445, B:133:0x041d, B:136:0x042f, B:138:0x0435, B:140:0x043f, B:68:0x01e4, B:71:0x01ee, B:73:0x01fc, B:77:0x0243, B:74:0x0219, B:76:0x022a, B:81:0x0252, B:83:0x027e, B:84:0x02a8, B:86:0x02de, B:88:0x02e4, B:91:0x02f0, B:93:0x0326, B:94:0x0341, B:96:0x0347, B:98:0x0355, B:102:0x0368, B:99:0x035d, B:105:0x036f, B:108:0x0376, B:109:0x038e, B:176:0x054d, B:178:0x055b, B:180:0x0566, B:191:0x0598, B:181:0x056e, B:183:0x0579, B:185:0x057f, B:188:0x058b, B:190:0x0593, B:192:0x059b, B:193:0x05a7, B:196:0x05af, B:198:0x05c1, B:199:0x05cd, B:201:0x05d5, B:205:0x05fa, B:207:0x061f, B:209:0x0630, B:211:0x0636, B:213:0x0642, B:214:0x0673, B:216:0x0679, B:218:0x0687, B:219:0x068b, B:220:0x068e, B:221:0x0691, B:222:0x069f, B:224:0x06a5, B:226:0x06b5, B:227:0x06bc, B:229:0x06c8, B:230:0x06cf, B:231:0x06d2, B:233:0x0712, B:234:0x0725, B:236:0x072b, B:239:0x0745, B:241:0x0760, B:243:0x0779, B:245:0x077e, B:247:0x0782, B:249:0x0786, B:251:0x0790, B:252:0x079a, B:254:0x079e, B:256:0x07a4, B:257:0x07b2, B:258:0x07bb, B:325:0x0a03, B:259:0x07c8, B:261:0x07df, B:267:0x07fb, B:269:0x081f, B:270:0x0827, B:272:0x082d, B:274:0x083f, B:281:0x0868, B:282:0x0888, B:284:0x0894, B:286:0x08a9, B:288:0x08ea, B:292:0x0902, B:294:0x0909, B:296:0x0918, B:298:0x091c, B:300:0x0920, B:302:0x0924, B:303:0x0932, B:305:0x0938, B:307:0x0954, B:308:0x0959, B:324:0x0a00, B:309:0x0974, B:311:0x097c, B:315:0x09a3, B:317:0x09cf, B:318:0x09d6, B:319:0x09e4, B:320:0x09e8, B:322:0x09f2, B:312:0x0989, B:279:0x0853, B:265:0x07e6, B:326:0x0a0f, B:328:0x0a1d, B:329:0x0a23, B:330:0x0a2b, B:332:0x0a31, B:335:0x0a4b, B:337:0x0a5c, B:357:0x0ad0, B:359:0x0ad6, B:361:0x0aee, B:364:0x0af5, B:369:0x0b24, B:371:0x0b66, B:374:0x0b9b, B:375:0x0b9f, B:376:0x0baa, B:378:0x0bed, B:379:0x0bfa, B:381:0x0c09, B:385:0x0c23, B:386:0x0c37, B:388:0x0c3c, B:373:0x0b78, B:365:0x0afd, B:367:0x0b09, B:368:0x0b0d, B:389:0x0c52, B:390:0x0c6a, B:393:0x0c72, B:394:0x0c77, B:395:0x0c87, B:397:0x0ca1, B:398:0x0cbc, B:400:0x0cc6, B:405:0x0ce9, B:404:0x0cd6, B:338:0x0a74, B:340:0x0a7a, B:342:0x0a84, B:344:0x0a8b, B:350:0x0a9b, B:352:0x0aa2, B:354:0x0ac1, B:356:0x0ac8, B:355:0x0ac5, B:351:0x0a9f, B:343:0x0a88, B:202:0x05da, B:204:0x05e0, B:408:0x0cfb), top: B:420:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01cb  */
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean zzag(String str, long j2) {
        int i2;
        String str2;
        com.google.android.gms.internal.measurement.zzgb zzgbVar;
        zzli zzliVar;
        List list;
        int i3;
        int delete;
        zzam zzamVar;
        zzam zzamVar2;
        com.google.android.gms.internal.measurement.zzgc zzgcVar;
        long currentTimeMillis;
        long zzm;
        Throwable th;
        zzey zzd;
        String str3;
        Object g2;
        ContentValues contentValues;
        long parseLong;
        int d2;
        long j3;
        SecureRandom secureRandom;
        com.google.android.gms.internal.measurement.zzgb zzgbVar2;
        Long l2;
        zzli zzliVar2;
        HashMap hashMap;
        long zzr;
        HashMap hashMap2;
        String zzo;
        zzas a2;
        com.google.android.gms.internal.measurement.zzgb zzgbVar3;
        com.google.android.gms.internal.measurement.zzfr zzfrVar;
        int i4;
        String str4;
        com.google.android.gms.internal.measurement.zzgb zzgbVar4;
        com.google.android.gms.internal.measurement.zzfr zzfrVar2;
        int i5;
        com.google.android.gms.internal.measurement.zzfr zzfrVar3;
        int i6;
        int i7;
        com.google.android.gms.internal.measurement.zzgb zzgbVar5;
        int i8;
        com.google.android.gms.internal.measurement.zzfr zzfrVar4;
        int i9;
        int i10;
        com.google.android.gms.internal.measurement.zzfr zzfrVar5;
        char c2;
        String str5 = "_npa";
        String str6 = "_ai";
        zzam zzamVar3 = this.zze;
        zzak(zzamVar3);
        zzamVar3.zzw();
        try {
            zzli zzliVar3 = new zzli(this, null);
            zzam zzamVar4 = this.zze;
            zzak(zzamVar4);
            zzamVar4.zzU(null, j2, this.zzA, zzliVar3);
            List list2 = zzliVar3.f7035c;
            if (list2 != null && !list2.isEmpty()) {
                com.google.android.gms.internal.measurement.zzgb zzgbVar6 = (com.google.android.gms.internal.measurement.zzgb) zzliVar3.f7033a.zzbB();
                zzgbVar6.zzr();
                com.google.android.gms.internal.measurement.zzfr zzfrVar6 = null;
                com.google.android.gms.internal.measurement.zzfr zzfrVar7 = null;
                int i11 = 0;
                int i12 = 0;
                int i13 = -1;
                int i14 = -1;
                int i15 = 0;
                while (true) {
                    i2 = i15;
                    str2 = str5;
                    String str7 = str6;
                    if (i11 >= zzliVar3.f7035c.size()) {
                        break;
                    }
                    com.google.android.gms.internal.measurement.zzfr zzfrVar8 = (com.google.android.gms.internal.measurement.zzfr) ((com.google.android.gms.internal.measurement.zzfs) zzliVar3.f7035c.get(i11)).zzbB();
                    zzgb zzgbVar7 = this.zzc;
                    zzak(zzgbVar7);
                    int i16 = i12;
                    if (zzgbVar7.q(zzliVar3.f7033a.zzy(), zzfrVar8.zzo())) {
                        zzay().zzk().zzc("Dropping blocked raw event. appId", zzfa.g(zzliVar3.f7033a.zzy()), this.zzn.zzj().d(zzfrVar8.zzo()));
                        zzgb zzgbVar8 = this.zzc;
                        zzak(zzgbVar8);
                        if (!zzgbVar8.o(zzliVar3.f7033a.zzy())) {
                            zzgb zzgbVar9 = this.zzc;
                            zzak(zzgbVar9);
                            if (!zzgbVar9.r(zzliVar3.f7033a.zzy()) && !"_err".equals(zzfrVar8.zzo())) {
                                zzv().n(this.zzF, zzliVar3.f7033a.zzy(), 11, "_ev", zzfrVar8.zzo(), 0);
                            }
                        }
                        i9 = i11;
                        zzfrVar = zzfrVar6;
                        i15 = i2;
                        i12 = i16;
                        zzgbVar5 = zzgbVar6;
                    } else {
                        if (zzfrVar8.zzo().equals(zzhh.zza(str7))) {
                            zzfrVar8.zzi(str7);
                            str7 = str7;
                            zzay().zzj().zza("Renaming ad_impression to _ai");
                            if (Log.isLoggable(zzay().zzq(), 5)) {
                                int i17 = 0;
                                while (i17 < zzfrVar8.zza()) {
                                    int i18 = i11;
                                    if (FirebaseAnalytics.Param.AD_PLATFORM.equals(zzfrVar8.zzn(i17).zzg()) && !zzfrVar8.zzn(i17).zzh().isEmpty() && "admob".equalsIgnoreCase(zzfrVar8.zzn(i17).zzh())) {
                                        zzay().zzl().zza("AdMob ad impression logged from app. Potentially duplicative.");
                                    }
                                    i17++;
                                    i11 = i18;
                                }
                            }
                        }
                        int i19 = i11;
                        zzgb zzgbVar10 = this.zzc;
                        zzak(zzgbVar10);
                        boolean p2 = zzgbVar10.p(zzliVar3.f7033a.zzy(), zzfrVar8.zzo());
                        if (p2) {
                            zzfrVar = zzfrVar6;
                            i4 = i13;
                        } else {
                            zzak(this.zzi);
                            String zzo2 = zzfrVar8.zzo();
                            Preconditions.checkNotEmpty(zzo2);
                            i4 = i13;
                            int hashCode = zzo2.hashCode();
                            zzfrVar = zzfrVar6;
                            if (hashCode == 94660) {
                                if (zzo2.equals("_in")) {
                                    c2 = 0;
                                    if (c2 != 0) {
                                    }
                                }
                                c2 = 65535;
                                if (c2 != 0) {
                                }
                            } else if (hashCode != 95025) {
                                if (hashCode == 95027 && zzo2.equals("_ui")) {
                                    c2 = 1;
                                    if (c2 != 0 && c2 != 1 && c2 != 2) {
                                        zzgbVar4 = zzgbVar6;
                                        str4 = "_et";
                                        zzfrVar2 = zzfrVar7;
                                        i5 = i14;
                                        p2 = false;
                                        if (p2) {
                                            ArrayList arrayList = new ArrayList(zzfrVar8.zzp());
                                            int i20 = -1;
                                            int i21 = -1;
                                            for (int i22 = 0; i22 < arrayList.size(); i22++) {
                                                if ("value".equals(((com.google.android.gms.internal.measurement.zzfw) arrayList.get(i22)).zzg())) {
                                                    i20 = i22;
                                                } else if (FirebaseAnalytics.Param.CURRENCY.equals(((com.google.android.gms.internal.measurement.zzfw) arrayList.get(i22)).zzg())) {
                                                    i21 = i22;
                                                }
                                            }
                                            if (i20 != -1) {
                                                if (((com.google.android.gms.internal.measurement.zzfw) arrayList.get(i20)).zzw() || ((com.google.android.gms.internal.measurement.zzfw) arrayList.get(i20)).zzu()) {
                                                    if (i21 != -1) {
                                                        String zzh = ((com.google.android.gms.internal.measurement.zzfw) arrayList.get(i21)).zzh();
                                                        if (zzh.length() == 3) {
                                                            int i23 = 0;
                                                            while (i23 < zzh.length()) {
                                                                int codePointAt = zzh.codePointAt(i23);
                                                                if (Character.isLetter(codePointAt)) {
                                                                    i23 += Character.charCount(codePointAt);
                                                                }
                                                            }
                                                        }
                                                    }
                                                    zzay().zzl().zza("Value parameter discarded. You must also supply a 3-letter ISO_4217 currency code in the currency parameter.");
                                                    zzfrVar8.zzh(i20);
                                                    A(zzfrVar8, "_c");
                                                    y(zzfrVar8, 19, FirebaseAnalytics.Param.CURRENCY);
                                                    break;
                                                }
                                                zzay().zzl().zza("Value must be specified with a numeric type.");
                                                zzfrVar8.zzh(i20);
                                                A(zzfrVar8, "_c");
                                                y(zzfrVar8, 18, "value");
                                            }
                                            if ("_e".equals(zzfrVar8.zzo())) {
                                                i7 = i5;
                                                zzgbVar5 = zzgbVar4;
                                                if ("_vs".equals(zzfrVar8.zzo())) {
                                                    zzak(this.zzi);
                                                    if (zzln.e((com.google.android.gms.internal.measurement.zzfs) zzfrVar8.zzaE(), str4) == null) {
                                                        if (zzfrVar == null || Math.abs(zzfrVar.zzc() - zzfrVar8.zzc()) > 1000) {
                                                            zzfrVar7 = zzfrVar8;
                                                            i13 = i4;
                                                            i14 = i16;
                                                        } else {
                                                            com.google.android.gms.internal.measurement.zzfr zzfrVar9 = (com.google.android.gms.internal.measurement.zzfr) zzfrVar.zzau();
                                                            if (zzai(zzfrVar9, zzfrVar8)) {
                                                                i8 = i4;
                                                                zzgbVar5.zzT(i8, zzfrVar9);
                                                                i14 = i7;
                                                                zzfrVar4 = null;
                                                                zzfrVar = null;
                                                            } else {
                                                                i8 = i4;
                                                                zzfrVar4 = zzfrVar8;
                                                                i14 = i16;
                                                            }
                                                            zzfrVar7 = zzfrVar4;
                                                            i13 = i8;
                                                        }
                                                    }
                                                }
                                                i8 = i4;
                                                i14 = i7;
                                                zzfrVar7 = zzfrVar2;
                                                i13 = i8;
                                            } else {
                                                zzak(this.zzi);
                                                if (zzln.e((com.google.android.gms.internal.measurement.zzfs) zzfrVar8.zzaE(), "_fr") != null) {
                                                    i7 = i5;
                                                    zzgbVar5 = zzgbVar4;
                                                    i8 = i4;
                                                    i14 = i7;
                                                    zzfrVar7 = zzfrVar2;
                                                    i13 = i8;
                                                } else if (zzfrVar2 == null || Math.abs(zzfrVar2.zzc() - zzfrVar8.zzc()) > 1000) {
                                                    zzgbVar5 = zzgbVar4;
                                                    zzfrVar = zzfrVar8;
                                                    i14 = i5;
                                                    zzfrVar7 = zzfrVar2;
                                                    i13 = i16;
                                                } else {
                                                    com.google.android.gms.internal.measurement.zzfr zzfrVar10 = (com.google.android.gms.internal.measurement.zzfr) zzfrVar2.zzau();
                                                    if (zzai(zzfrVar8, zzfrVar10)) {
                                                        i10 = i5;
                                                        zzgbVar5 = zzgbVar4;
                                                        zzgbVar5.zzT(i10, zzfrVar10);
                                                        i13 = i4;
                                                        zzfrVar5 = null;
                                                        zzfrVar7 = null;
                                                    } else {
                                                        i10 = i5;
                                                        zzgbVar5 = zzgbVar4;
                                                        zzfrVar5 = zzfrVar8;
                                                        zzfrVar7 = zzfrVar2;
                                                        i13 = i16;
                                                    }
                                                    zzfrVar = zzfrVar5;
                                                    i14 = i10;
                                                }
                                            }
                                            i9 = i19;
                                            zzliVar3.f7035c.set(i9, (com.google.android.gms.internal.measurement.zzfs) zzfrVar8.zzaE());
                                            i12 = i16 + 1;
                                            zzgbVar5.zzk(zzfrVar8);
                                            i15 = i2;
                                        }
                                        if ("_e".equals(zzfrVar8.zzo())) {
                                        }
                                        i9 = i19;
                                        zzliVar3.f7035c.set(i9, (com.google.android.gms.internal.measurement.zzfs) zzfrVar8.zzaE());
                                        i12 = i16 + 1;
                                        zzgbVar5.zzk(zzfrVar8);
                                        i15 = i2;
                                    }
                                }
                                c2 = 65535;
                                if (c2 != 0) {
                                    zzgbVar4 = zzgbVar6;
                                    str4 = "_et";
                                    zzfrVar2 = zzfrVar7;
                                    i5 = i14;
                                    p2 = false;
                                    if (p2) {
                                    }
                                    if ("_e".equals(zzfrVar8.zzo())) {
                                    }
                                    i9 = i19;
                                    zzliVar3.f7035c.set(i9, (com.google.android.gms.internal.measurement.zzfs) zzfrVar8.zzaE());
                                    i12 = i16 + 1;
                                    zzgbVar5.zzk(zzfrVar8);
                                    i15 = i2;
                                }
                            } else {
                                if (zzo2.equals("_ug")) {
                                    c2 = 2;
                                    if (c2 != 0) {
                                    }
                                }
                                c2 = 65535;
                                if (c2 != 0) {
                                }
                            }
                        }
                        str4 = "_et";
                        int i24 = 0;
                        boolean z = false;
                        boolean z2 = false;
                        while (true) {
                            zzgbVar4 = zzgbVar6;
                            if (i24 >= zzfrVar8.zza()) {
                                break;
                            }
                            if ("_c".equals(zzfrVar8.zzn(i24).zzg())) {
                                com.google.android.gms.internal.measurement.zzfv zzfvVar = (com.google.android.gms.internal.measurement.zzfv) zzfrVar8.zzn(i24).zzbB();
                                zzfrVar3 = zzfrVar7;
                                i6 = i14;
                                zzfvVar.zzi(1L);
                                zzfrVar8.zzk(i24, (com.google.android.gms.internal.measurement.zzfw) zzfvVar.zzaE());
                                z = true;
                            } else {
                                zzfrVar3 = zzfrVar7;
                                i6 = i14;
                                if ("_r".equals(zzfrVar8.zzn(i24).zzg())) {
                                    com.google.android.gms.internal.measurement.zzfv zzfvVar2 = (com.google.android.gms.internal.measurement.zzfv) zzfrVar8.zzn(i24).zzbB();
                                    zzfvVar2.zzi(1L);
                                    zzfrVar8.zzk(i24, (com.google.android.gms.internal.measurement.zzfw) zzfvVar2.zzaE());
                                    z2 = true;
                                }
                            }
                            i24++;
                            zzfrVar7 = zzfrVar3;
                            i14 = i6;
                            zzgbVar6 = zzgbVar4;
                        }
                        zzfrVar2 = zzfrVar7;
                        i5 = i14;
                        if (!z && p2) {
                            zzay().zzj().zzb("Marking event as conversion", this.zzn.zzj().d(zzfrVar8.zzo()));
                            com.google.android.gms.internal.measurement.zzfv zze = com.google.android.gms.internal.measurement.zzfw.zze();
                            zze.zzj("_c");
                            zze.zzi(1L);
                            zzfrVar8.zze(zze);
                        }
                        if (!z2) {
                            zzay().zzj().zzb("Marking event as real-time", this.zzn.zzj().d(zzfrVar8.zzo()));
                            com.google.android.gms.internal.measurement.zzfv zze2 = com.google.android.gms.internal.measurement.zzfw.zze();
                            zze2.zzj("_r");
                            zze2.zzi(1L);
                            zzfrVar8.zze(zze2);
                        }
                        zzam zzamVar5 = this.zze;
                        zzak(zzamVar5);
                        if (zzamVar5.zzl(z(), zzliVar3.f7033a.zzy(), false, false, false, false, true).f6688e > zzg().zze(zzliVar3.f7033a.zzy(), zzen.zzn)) {
                            A(zzfrVar8, "_r");
                        } else {
                            i2 = 1;
                        }
                        if (zzlt.C(zzfrVar8.zzo()) && p2) {
                            zzam zzamVar6 = this.zze;
                            zzak(zzamVar6);
                            if (zzamVar6.zzl(z(), zzliVar3.f7033a.zzy(), false, false, true, false, false).f6686c > zzg().zze(zzliVar3.f7033a.zzy(), zzen.zzm)) {
                                zzay().zzk().zzb("Too many conversions. Not logging as conversion. appId", zzfa.g(zzliVar3.f7033a.zzy()));
                                com.google.android.gms.internal.measurement.zzfv zzfvVar3 = null;
                                boolean z3 = false;
                                int i25 = -1;
                                for (int i26 = 0; i26 < zzfrVar8.zza(); i26++) {
                                    com.google.android.gms.internal.measurement.zzfw zzn = zzfrVar8.zzn(i26);
                                    if ("_c".equals(zzn.zzg())) {
                                        zzfvVar3 = (com.google.android.gms.internal.measurement.zzfv) zzn.zzbB();
                                        i25 = i26;
                                    } else if ("_err".equals(zzn.zzg())) {
                                        z3 = true;
                                    }
                                }
                                if (z3) {
                                    if (zzfvVar3 != null) {
                                        zzfrVar8.zzh(i25);
                                    } else {
                                        zzfvVar3 = null;
                                    }
                                }
                                if (zzfvVar3 != null) {
                                    com.google.android.gms.internal.measurement.zzfv zzfvVar4 = (com.google.android.gms.internal.measurement.zzfv) zzfvVar3.zzau();
                                    zzfvVar4.zzj("_err");
                                    zzfvVar4.zzi(10L);
                                    zzfrVar8.zzk(i25, (com.google.android.gms.internal.measurement.zzfw) zzfvVar4.zzaE());
                                } else {
                                    zzay().zzd().zzb("Did not find conversion parameter. appId", zzfa.g(zzliVar3.f7033a.zzy()));
                                }
                            }
                        }
                        if (p2) {
                        }
                        if ("_e".equals(zzfrVar8.zzo())) {
                        }
                        i9 = i19;
                        zzliVar3.f7035c.set(i9, (com.google.android.gms.internal.measurement.zzfs) zzfrVar8.zzaE());
                        i12 = i16 + 1;
                        zzgbVar5.zzk(zzfrVar8);
                        i15 = i2;
                    }
                    i11 = i9 + 1;
                    zzgbVar6 = zzgbVar5;
                    str5 = str2;
                    str6 = str7;
                    zzfrVar6 = zzfrVar;
                }
                com.google.android.gms.internal.measurement.zzgb zzgbVar11 = zzgbVar6;
                long j4 = 0;
                int i27 = 0;
                while (i27 < i12) {
                    com.google.android.gms.internal.measurement.zzfs zze3 = zzgbVar11.zze(i27);
                    if ("_e".equals(zze3.zzh())) {
                        zzak(this.zzi);
                        if (zzln.e(zze3, "_fr") != null) {
                            zzgbVar11.zzA(i27);
                            i12--;
                            i27--;
                            i27++;
                        }
                    }
                    zzak(this.zzi);
                    com.google.android.gms.internal.measurement.zzfw e2 = zzln.e(zze3, "_et");
                    if (e2 != null) {
                        Long valueOf = e2.zzw() ? Long.valueOf(e2.zzd()) : null;
                        if (valueOf != null && valueOf.longValue() > 0) {
                            j4 += valueOf.longValue();
                        }
                    }
                    i27++;
                }
                zzae(zzgbVar11, j4, false);
                Iterator it = zzgbVar11.zzas().iterator();
                while (true) {
                    if (it.hasNext()) {
                        if ("_s".equals(((com.google.android.gms.internal.measurement.zzfs) it.next()).zzh())) {
                            zzam zzamVar7 = this.zze;
                            zzak(zzamVar7);
                            zzamVar7.zzA(zzgbVar11.zzaq(), "_se");
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (zzln.g(zzgbVar11, "_sid") >= 0) {
                    zzae(zzgbVar11, j4, true);
                } else {
                    int g3 = zzln.g(zzgbVar11, "_se");
                    if (g3 >= 0) {
                        zzgbVar11.zzB(g3);
                        zzay().zzd().zzb("Session engagement user property is in the bundle without session ID. appId", zzfa.g(zzliVar3.f7033a.zzy()));
                    }
                }
                zzln zzlnVar = this.zzi;
                zzak(zzlnVar);
                zzlnVar.f6809a.zzay().zzj().zza("Checking account type status for ad personalization signals");
                zzgb zzgbVar12 = zzlnVar.f7018b.zzc;
                zzak(zzgbVar12);
                if (zzgbVar12.n(zzgbVar11.zzaq())) {
                    zzam zzamVar8 = zzlnVar.f7018b.zze;
                    zzak(zzamVar8);
                    zzh zzj = zzamVar8.zzj(zzgbVar11.zzaq());
                    if (zzj != null && zzj.zzai() && zzlnVar.f6809a.zzg().g()) {
                        zzlnVar.f6809a.zzay().zzc().zza("Turning off ad personalization due to account type");
                        com.google.android.gms.internal.measurement.zzgk zzd2 = com.google.android.gms.internal.measurement.zzgl.zzd();
                        zzd2.zzf(str2);
                        zzd2.zzg(zzlnVar.f6809a.zzg().e());
                        zzd2.zze(1L);
                        com.google.android.gms.internal.measurement.zzgl zzglVar = (com.google.android.gms.internal.measurement.zzgl) zzd2.zzaE();
                        int i28 = 0;
                        while (true) {
                            if (i28 >= zzgbVar11.zzb()) {
                                zzgbVar11.zzm(zzglVar);
                                break;
                            } else if (str2.equals(zzgbVar11.zzap(i28).zzf())) {
                                zzgbVar11.zzan(i28, zzglVar);
                                break;
                            } else {
                                i28++;
                            }
                        }
                    }
                }
                zzgbVar11.zzaj(LongCompanionObject.MAX_VALUE);
                zzgbVar11.zzR(Long.MIN_VALUE);
                for (int i29 = 0; i29 < zzgbVar11.zza(); i29++) {
                    com.google.android.gms.internal.measurement.zzfs zze4 = zzgbVar11.zze(i29);
                    if (zze4.zzd() < zzgbVar11.zzd()) {
                        zzgbVar11.zzaj(zze4.zzd());
                    }
                    if (zze4.zzd() > zzgbVar11.zzc()) {
                        zzgbVar11.zzR(zze4.zzd());
                    }
                }
                zzgbVar11.zzz();
                zzgbVar11.zzo();
                zzaa zzaaVar = this.zzh;
                zzak(zzaaVar);
                zzgbVar11.zzf(zzaaVar.d(zzgbVar11.zzaq(), zzgbVar11.zzas(), zzgbVar11.zzat(), Long.valueOf(zzgbVar11.zzd()), Long.valueOf(zzgbVar11.zzc())));
                if (zzg().zzw(zzliVar3.f7033a.zzy())) {
                    HashMap hashMap3 = new HashMap();
                    ArrayList arrayList2 = new ArrayList();
                    SecureRandom i30 = zzv().i();
                    int i31 = 0;
                    while (i31 < zzgbVar11.zza()) {
                        com.google.android.gms.internal.measurement.zzfr zzfrVar11 = (com.google.android.gms.internal.measurement.zzfr) zzgbVar11.zze(i31).zzbB();
                        if (zzfrVar11.zzo().equals("_ep")) {
                            zzak(this.zzi);
                            String str8 = (String) zzln.f((com.google.android.gms.internal.measurement.zzfs) zzfrVar11.zzaE(), "_en");
                            zzas zzasVar = (zzas) hashMap3.get(str8);
                            if (zzasVar == null) {
                                zzam zzamVar9 = this.zze;
                                zzak(zzamVar9);
                                zzasVar = zzamVar9.zzn(zzliVar3.f7033a.zzy(), (String) Preconditions.checkNotNull(str8));
                                if (zzasVar != null) {
                                    hashMap3.put(str8, zzasVar);
                                }
                            }
                            if (zzasVar != null && zzasVar.f6706i == null) {
                                Long l3 = zzasVar.f6707j;
                                if (l3 != null && l3.longValue() > 1) {
                                    zzak(this.zzi);
                                    zzln.z(zzfrVar11, "_sr", zzasVar.f6707j);
                                }
                                Boolean bool = zzasVar.f6708k;
                                if (bool != null && bool.booleanValue()) {
                                    zzak(this.zzi);
                                    zzln.z(zzfrVar11, "_efs", 1L);
                                }
                                arrayList2.add((com.google.android.gms.internal.measurement.zzfs) zzfrVar11.zzaE());
                            }
                        } else {
                            zzgb zzgbVar13 = this.zzc;
                            zzak(zzgbVar13);
                            String zzy = zzliVar3.f7033a.zzy();
                            String zza = zzgbVar13.zza(zzy, "measurement.account.time_zone_offset_minutes");
                            if (!TextUtils.isEmpty(zza)) {
                                try {
                                    parseLong = Long.parseLong(zza);
                                } catch (NumberFormatException e3) {
                                    zzgbVar13.f6809a.zzay().zzk().zzc("Unable to parse timezone offset. appId", zzfa.g(zzy), e3);
                                }
                                long zzr22 = zzv().zzr(zzfrVar11.zzc(), parseLong);
                                com.google.android.gms.internal.measurement.zzfs zzfsVar2 = (com.google.android.gms.internal.measurement.zzfs) zzfrVar11.zzaE();
                                Long l42 = 1L;
                                long j52 = parseLong;
                                if (!TextUtils.isEmpty("_dbg")) {
                                    Iterator it2 = zzfsVar2.zzi().iterator();
                                    while (true) {
                                        if (!it2.hasNext()) {
                                            break;
                                        }
                                        com.google.android.gms.internal.measurement.zzfw zzfwVar = (com.google.android.gms.internal.measurement.zzfw) it2.next();
                                        Iterator it3 = it2;
                                        if (!"_dbg".equals(zzfwVar.zzg())) {
                                            it2 = it3;
                                        } else if (l42.equals(Long.valueOf(zzfwVar.zzd()))) {
                                            d2 = 1;
                                        }
                                    }
                                }
                                zzgb zzgbVar142 = this.zzc;
                                zzak(zzgbVar142);
                                d2 = zzgbVar142.d(zzliVar3.f7033a.zzy(), zzfrVar11.zzo());
                                if (d2 > 0) {
                                    zzay().zzk().zzc("Sample rate must be positive. event, rate", zzfrVar11.zzo(), Integer.valueOf(d2));
                                    arrayList2.add((com.google.android.gms.internal.measurement.zzfs) zzfrVar11.zzaE());
                                } else {
                                    zzas zzasVar2 = (zzas) hashMap3.get(zzfrVar11.zzo());
                                    if (zzasVar2 == null) {
                                        zzam zzamVar10 = this.zze;
                                        zzak(zzamVar10);
                                        zzasVar2 = zzamVar10.zzn(zzliVar3.f7033a.zzy(), zzfrVar11.zzo());
                                        if (zzasVar2 == null) {
                                            j3 = zzr22;
                                            zzay().zzk().zzc("Event being bundled has no eventAggregate. appId, eventName", zzliVar3.f7033a.zzy(), zzfrVar11.zzo());
                                            zzasVar2 = new zzas(zzliVar3.f7033a.zzy(), zzfrVar11.zzo(), 1L, 1L, 1L, zzfrVar11.zzc(), 0L, null, null, null, null);
                                            zzak(this.zzi);
                                            Long l52 = (Long) zzln.f((com.google.android.gms.internal.measurement.zzfs) zzfrVar11.zzaE(), "_eid");
                                            Boolean valueOf22 = Boolean.valueOf(l52 == null);
                                            if (d2 != 1) {
                                                arrayList2.add((com.google.android.gms.internal.measurement.zzfs) zzfrVar11.zzaE());
                                                if (valueOf22.booleanValue() && (zzasVar2.f6706i != null || zzasVar2.f6707j != null || zzasVar2.f6708k != null)) {
                                                    hashMap3.put(zzfrVar11.zzo(), zzasVar2.a(null, null, null));
                                                }
                                            } else {
                                                if (i30.nextInt(d2) == 0) {
                                                    zzak(this.zzi);
                                                    Long valueOf3 = Long.valueOf(d2);
                                                    zzln.z(zzfrVar11, "_sr", valueOf3);
                                                    arrayList2.add((com.google.android.gms.internal.measurement.zzfs) zzfrVar11.zzaE());
                                                    if (valueOf22.booleanValue()) {
                                                        zzasVar2 = zzasVar2.a(null, valueOf3, null);
                                                    }
                                                    hashMap3.put(zzfrVar11.zzo(), zzasVar2.b(zzfrVar11.zzc(), j3));
                                                    zzliVar2 = zzliVar3;
                                                    secureRandom = i30;
                                                    zzgbVar3 = zzgbVar11;
                                                    hashMap2 = hashMap3;
                                                } else {
                                                    long j6 = j3;
                                                    secureRandom = i30;
                                                    Long l6 = zzasVar2.f6705h;
                                                    if (l6 != null) {
                                                        zzr = l6.longValue();
                                                        zzliVar2 = zzliVar3;
                                                        hashMap = hashMap3;
                                                        zzgbVar2 = zzgbVar11;
                                                        l2 = l52;
                                                    } else {
                                                        zzgbVar2 = zzgbVar11;
                                                        l2 = l52;
                                                        zzliVar2 = zzliVar3;
                                                        hashMap = hashMap3;
                                                        zzr = zzv().zzr(zzfrVar11.zzb(), j52);
                                                    }
                                                    if (zzr != j6) {
                                                        zzak(this.zzi);
                                                        zzln.z(zzfrVar11, "_efs", 1L);
                                                        zzak(this.zzi);
                                                        Long valueOf4 = Long.valueOf(d2);
                                                        zzln.z(zzfrVar11, "_sr", valueOf4);
                                                        arrayList2.add((com.google.android.gms.internal.measurement.zzfs) zzfrVar11.zzaE());
                                                        if (valueOf22.booleanValue()) {
                                                            zzasVar2 = zzasVar2.a(null, valueOf4, Boolean.TRUE);
                                                        }
                                                        zzo = zzfrVar11.zzo();
                                                        a2 = zzasVar2.b(zzfrVar11.zzc(), j6);
                                                        hashMap2 = hashMap;
                                                    } else {
                                                        hashMap2 = hashMap;
                                                        if (valueOf22.booleanValue()) {
                                                            zzo = zzfrVar11.zzo();
                                                            a2 = zzasVar2.a(l2, null, null);
                                                        }
                                                        zzgbVar3 = zzgbVar2;
                                                    }
                                                    hashMap2.put(zzo, a2);
                                                    zzgbVar3 = zzgbVar2;
                                                }
                                                zzgbVar3.zzT(i31, zzfrVar11);
                                                i31++;
                                                zzgbVar11 = zzgbVar3;
                                                hashMap3 = hashMap2;
                                                i30 = secureRandom;
                                                zzliVar3 = zzliVar2;
                                            }
                                        }
                                    }
                                    j3 = zzr22;
                                    zzak(this.zzi);
                                    Long l522 = (Long) zzln.f((com.google.android.gms.internal.measurement.zzfs) zzfrVar11.zzaE(), "_eid");
                                    Boolean valueOf222 = Boolean.valueOf(l522 == null);
                                    if (d2 != 1) {
                                    }
                                }
                            }
                            parseLong = 0;
                            long zzr222 = zzv().zzr(zzfrVar11.zzc(), parseLong);
                            com.google.android.gms.internal.measurement.zzfs zzfsVar22 = (com.google.android.gms.internal.measurement.zzfs) zzfrVar11.zzaE();
                            Long l422 = 1L;
                            long j522 = parseLong;
                            if (!TextUtils.isEmpty("_dbg")) {
                            }
                            zzgb zzgbVar1422 = this.zzc;
                            zzak(zzgbVar1422);
                            d2 = zzgbVar1422.d(zzliVar3.f7033a.zzy(), zzfrVar11.zzo());
                            if (d2 > 0) {
                            }
                        }
                        zzgbVar11.zzT(i31, zzfrVar11);
                        zzliVar2 = zzliVar3;
                        secureRandom = i30;
                        zzgbVar3 = zzgbVar11;
                        hashMap2 = hashMap3;
                        i31++;
                        zzgbVar11 = zzgbVar3;
                        hashMap3 = hashMap2;
                        i30 = secureRandom;
                        zzliVar3 = zzliVar2;
                    }
                    zzli zzliVar4 = zzliVar3;
                    HashMap hashMap4 = hashMap3;
                    zzgbVar = zzgbVar11;
                    if (arrayList2.size() < zzgbVar.zza()) {
                        zzgbVar.zzr();
                        zzgbVar.zzg(arrayList2);
                    }
                    for (Map.Entry entry : hashMap4.entrySet()) {
                        zzam zzamVar11 = this.zze;
                        zzak(zzamVar11);
                        zzamVar11.zzE((zzas) entry.getValue());
                    }
                    zzliVar = zzliVar4;
                } else {
                    zzgbVar = zzgbVar11;
                    zzliVar = zzliVar3;
                }
                String zzy2 = zzliVar.f7033a.zzy();
                zzam zzamVar12 = this.zze;
                zzak(zzamVar12);
                zzh zzj2 = zzamVar12.zzj(zzy2);
                if (zzj2 == null) {
                    zzay().zzd().zzb("Bundling raw events w/o app info. appId", zzfa.g(zzliVar.f7033a.zzy()));
                } else if (zzgbVar.zza() > 0) {
                    long zzn2 = zzj2.zzn();
                    if (zzn2 != 0) {
                        zzgbVar.zzac(zzn2);
                    } else {
                        zzgbVar.zzv();
                    }
                    long zzp = zzj2.zzp();
                    if (zzp != 0) {
                        zzn2 = zzp;
                    }
                    if (zzn2 != 0) {
                        zzgbVar.zzad(zzn2);
                    } else {
                        zzgbVar.zzw();
                    }
                    zzj2.zzE();
                    zzgbVar.zzJ((int) zzj2.zzo());
                    zzj2.zzac(zzgbVar.zzd());
                    zzj2.zzaa(zzgbVar.zzc());
                    String zzs = zzj2.zzs();
                    if (zzs != null) {
                        zzgbVar.zzX(zzs);
                    } else {
                        zzgbVar.zzs();
                    }
                    zzam zzamVar13 = this.zze;
                    zzak(zzamVar13);
                    zzamVar13.zzD(zzj2);
                }
                if (zzgbVar.zza() > 0) {
                    this.zzn.zzaw();
                    zzgb zzgbVar15 = this.zzc;
                    zzak(zzgbVar15);
                    com.google.android.gms.internal.measurement.zzfe f2 = zzgbVar15.f(zzliVar.f7033a.zzy());
                    try {
                        try {
                            if (f2 != null && f2.zzs()) {
                                zzgbVar.zzL(f2.zzc());
                                zzamVar2 = this.zze;
                                zzak(zzamVar2);
                                zzgcVar = (com.google.android.gms.internal.measurement.zzgc) zzgbVar.zzaE();
                                zzamVar2.zzg();
                                zzamVar2.a();
                                Preconditions.checkNotNull(zzgcVar);
                                Preconditions.checkNotEmpty(zzgcVar.zzy());
                                Preconditions.checkState(zzgcVar.zzbh());
                                zzamVar2.zzz();
                                currentTimeMillis = zzamVar2.f6809a.zzav().currentTimeMillis();
                                zzm = zzgcVar.zzm();
                                zzamVar2.f6809a.zzf();
                                if (zzm >= currentTimeMillis - zzag.zzA()) {
                                    long zzm2 = zzgcVar.zzm();
                                    zzamVar2.f6809a.zzf();
                                }
                                zzamVar2.f6809a.zzay().zzk().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzfa.g(zzgcVar.zzy()), Long.valueOf(currentTimeMillis), Long.valueOf(zzgcVar.zzm()));
                                byte[] zzby2 = zzgcVar.zzby();
                                zzln zzlnVar22 = zzamVar2.f7018b.zzi;
                                zzak(zzlnVar22);
                                byte[] y2 = zzlnVar22.y(zzby2);
                                zzamVar2.f6809a.zzay().zzj().zzb("Saving bundle, size", Integer.valueOf(y2.length));
                                contentValues = new ContentValues();
                                contentValues.put("app_id", zzgcVar.zzy());
                                contentValues.put("bundle_end_timestamp", Long.valueOf(zzgcVar.zzm()));
                                contentValues.put(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, y2);
                                contentValues.put("has_realtime", Integer.valueOf(i2));
                                if (zzgcVar.zzbn()) {
                                    contentValues.put("retry_count", Integer.valueOf(zzgcVar.zze()));
                                }
                                if (zzamVar2.p().insert("queue", null, contentValues) == -1) {
                                    zzamVar2.f6809a.zzay().zzd().zzb("Failed to insert bundle (got -1). appId", zzfa.g(zzgcVar.zzy()));
                                }
                            }
                            if (zzamVar2.p().insert("queue", null, contentValues) == -1) {
                            }
                        } catch (SQLiteException e4) {
                            th = e4;
                            zzd = zzamVar2.f6809a.zzay().zzd();
                            str3 = "Error storing bundle. appId";
                            g2 = zzfa.g(zzgcVar.zzy());
                            zzd.zzc(str3, g2, th);
                            zzam zzamVar142 = this.zze;
                            zzak(zzamVar142);
                            list = zzliVar.f7034b;
                            Preconditions.checkNotNull(list);
                            zzamVar142.zzg();
                            zzamVar142.a();
                            StringBuilder sb2 = new StringBuilder("rowid in (");
                            while (i3 < list.size()) {
                            }
                            sb2.append(")");
                            delete = zzamVar142.p().delete("raw_events", sb2.toString(), null);
                            if (delete != list.size()) {
                            }
                            zzamVar = this.zze;
                            zzak(zzamVar);
                            zzamVar.p().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{zzy2, zzy2});
                            zzam zzamVar152 = this.zze;
                            zzak(zzamVar152);
                            zzamVar152.zzC();
                            zzam zzamVar162 = this.zze;
                            zzak(zzamVar162);
                            zzamVar162.zzx();
                            return true;
                        }
                        zzln zzlnVar222 = zzamVar2.f7018b.zzi;
                        zzak(zzlnVar222);
                        byte[] y22 = zzlnVar222.y(zzby2);
                        zzamVar2.f6809a.zzay().zzj().zzb("Saving bundle, size", Integer.valueOf(y22.length));
                        contentValues = new ContentValues();
                        contentValues.put("app_id", zzgcVar.zzy());
                        contentValues.put("bundle_end_timestamp", Long.valueOf(zzgcVar.zzm()));
                        contentValues.put(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, y22);
                        contentValues.put("has_realtime", Integer.valueOf(i2));
                        if (zzgcVar.zzbn()) {
                        }
                    } catch (IOException e5) {
                        th = e5;
                        zzd = zzamVar2.f6809a.zzay().zzd();
                        str3 = "Data loss. Failed to serialize bundle. appId";
                        g2 = zzfa.g(zzgcVar.zzy());
                    }
                    if (zzliVar.f7033a.zzG().isEmpty()) {
                        zzgbVar.zzL(-1L);
                    } else {
                        zzay().zzk().zzb("Did not find measurement config or missing version info. appId", zzfa.g(zzliVar.f7033a.zzy()));
                    }
                    zzamVar2 = this.zze;
                    zzak(zzamVar2);
                    zzgcVar = (com.google.android.gms.internal.measurement.zzgc) zzgbVar.zzaE();
                    zzamVar2.zzg();
                    zzamVar2.a();
                    Preconditions.checkNotNull(zzgcVar);
                    Preconditions.checkNotEmpty(zzgcVar.zzy());
                    Preconditions.checkState(zzgcVar.zzbh());
                    zzamVar2.zzz();
                    currentTimeMillis = zzamVar2.f6809a.zzav().currentTimeMillis();
                    zzm = zzgcVar.zzm();
                    zzamVar2.f6809a.zzf();
                    if (zzm >= currentTimeMillis - zzag.zzA()) {
                    }
                    zzamVar2.f6809a.zzay().zzk().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzfa.g(zzgcVar.zzy()), Long.valueOf(currentTimeMillis), Long.valueOf(zzgcVar.zzm()));
                    byte[] zzby22 = zzgcVar.zzby();
                }
                zzam zzamVar1422 = this.zze;
                zzak(zzamVar1422);
                list = zzliVar.f7034b;
                Preconditions.checkNotNull(list);
                zzamVar1422.zzg();
                zzamVar1422.a();
                StringBuilder sb22 = new StringBuilder("rowid in (");
                for (i3 = 0; i3 < list.size(); i3++) {
                    if (i3 != 0) {
                        sb22.append(",");
                    }
                    sb22.append(((Long) list.get(i3)).longValue());
                }
                sb22.append(")");
                delete = zzamVar1422.p().delete("raw_events", sb22.toString(), null);
                if (delete != list.size()) {
                    zzamVar1422.f6809a.zzay().zzd().zzc("Deleted fewer rows from raw events table than expected", Integer.valueOf(delete), Integer.valueOf(list.size()));
                }
                zzamVar = this.zze;
                zzak(zzamVar);
                try {
                    zzamVar.p().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{zzy2, zzy2});
                } catch (SQLiteException e6) {
                    zzamVar.f6809a.zzay().zzd().zzc("Failed to remove unused event metadata. appId", zzfa.g(zzy2), e6);
                }
                zzam zzamVar1522 = this.zze;
                zzak(zzamVar1522);
                zzamVar1522.zzC();
                zzam zzamVar1622 = this.zze;
                zzak(zzamVar1622);
                zzamVar1622.zzx();
                return true;
            }
            zzam zzamVar17 = this.zze;
            zzak(zzamVar17);
            zzamVar17.zzC();
            zzam zzamVar18 = this.zze;
            zzak(zzamVar18);
            zzamVar18.zzx();
            return false;
        } catch (Throwable th2) {
            zzam zzamVar19 = this.zze;
            zzak(zzamVar19);
            zzamVar19.zzx();
            throw th2;
        }
    }

    private final boolean zzah() {
        zzaz().zzg();
        b();
        zzam zzamVar = this.zze;
        zzak(zzamVar);
        if (zzamVar.zzF()) {
            return true;
        }
        zzam zzamVar2 = this.zze;
        zzak(zzamVar2);
        return !TextUtils.isEmpty(zzamVar2.zzr());
    }

    private final boolean zzai(com.google.android.gms.internal.measurement.zzfr zzfrVar, com.google.android.gms.internal.measurement.zzfr zzfrVar2) {
        Preconditions.checkArgument("_e".equals(zzfrVar.zzo()));
        zzak(this.zzi);
        com.google.android.gms.internal.measurement.zzfw e2 = zzln.e((com.google.android.gms.internal.measurement.zzfs) zzfrVar.zzaE(), "_sc");
        String zzh = e2 == null ? null : e2.zzh();
        zzak(this.zzi);
        com.google.android.gms.internal.measurement.zzfw e3 = zzln.e((com.google.android.gms.internal.measurement.zzfs) zzfrVar2.zzaE(), "_pc");
        String zzh2 = e3 != null ? e3.zzh() : null;
        if (zzh2 == null || !zzh2.equals(zzh)) {
            return false;
        }
        Preconditions.checkArgument("_e".equals(zzfrVar.zzo()));
        zzak(this.zzi);
        com.google.android.gms.internal.measurement.zzfw e4 = zzln.e((com.google.android.gms.internal.measurement.zzfs) zzfrVar.zzaE(), "_et");
        if (e4 == null || !e4.zzw() || e4.zzd() <= 0) {
            return true;
        }
        long zzd = e4.zzd();
        zzak(this.zzi);
        com.google.android.gms.internal.measurement.zzfw e5 = zzln.e((com.google.android.gms.internal.measurement.zzfs) zzfrVar2.zzaE(), "_et");
        if (e5 != null && e5.zzd() > 0) {
            zzd += e5.zzd();
        }
        zzak(this.zzi);
        zzln.z(zzfrVar2, "_et", Long.valueOf(zzd));
        zzak(this.zzi);
        zzln.z(zzfrVar, "_fr", 1L);
        return true;
    }

    private static final boolean zzaj(zzq zzqVar) {
        return (TextUtils.isEmpty(zzqVar.zzb) && TextUtils.isEmpty(zzqVar.zzq)) ? false : true;
    }

    private static final zzkz zzak(zzkz zzkzVar) {
        if (zzkzVar != null) {
            if (zzkzVar.b()) {
                return zzkzVar;
            }
            throw new IllegalStateException("Component not initialized: ".concat(String.valueOf(zzkzVar.getClass())));
        }
        throw new IllegalStateException("Upload Component not created");
    }

    public static zzll zzt(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzb == null) {
            synchronized (zzll.class) {
                if (zzb == null) {
                    zzb = new zzll((zzlm) Preconditions.checkNotNull(new zzlm(context)), null);
                }
            }
        }
        return zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final zzh B(zzq zzqVar) {
        zzaz().zzg();
        b();
        Preconditions.checkNotNull(zzqVar);
        Preconditions.checkNotEmpty(zzqVar.zza);
        zzpj.zzc();
        if (zzg().zzs(zzqVar.zza, zzen.zzaJ) && !zzqVar.zzw.isEmpty()) {
            this.zzC.put(zzqVar.zza, new zzlk(this, zzqVar.zzw));
        }
        zzam zzamVar = this.zze;
        zzak(zzamVar);
        zzh zzj = zzamVar.zzj(zzqVar.zza);
        zzai zzc = C(zzqVar.zza).zzc(zzai.zzb(zzqVar.zzv));
        zzah zzahVar = zzah.AD_STORAGE;
        String f2 = zzc.zzi(zzahVar) ? this.zzk.f(zzqVar.zza) : "";
        if (zzj == null) {
            zzj = new zzh(this.zzn, zzqVar.zza);
            if (zzc.zzi(zzah.ANALYTICS_STORAGE)) {
                zzj.zzI(F(zzc));
            }
            if (zzc.zzi(zzahVar)) {
                zzj.zzaf(f2);
            }
        } else if (zzc.zzi(zzahVar) && f2 != null && !f2.equals(zzj.zzA())) {
            zzj.zzaf(f2);
            zzne.zzc();
            zzag zzg = zzg();
            zzem zzemVar = zzen.zzan;
            if (!zzg.zzs(null, zzemVar) || !zzg().zzs(null, zzen.zzas) || !"00000000-0000-0000-0000-000000000000".equals(this.zzk.e(zzqVar.zza, zzc).first)) {
                zzj.zzI(F(zzc));
            }
            zzne.zzc();
            if (zzg().zzs(null, zzemVar) && !"00000000-0000-0000-0000-000000000000".equals(this.zzk.e(zzqVar.zza, zzc).first)) {
                zzam zzamVar2 = this.zze;
                zzak(zzamVar2);
                if (zzamVar2.zzp(zzqVar.zza, "_id") != null) {
                    zzam zzamVar3 = this.zze;
                    zzak(zzamVar3);
                    if (zzamVar3.zzp(zzqVar.zza, "_lair") == null) {
                        zzlq zzlqVar = new zzlq(zzqVar.zza, DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_lair", zzav().currentTimeMillis(), 1L);
                        zzam zzamVar4 = this.zze;
                        zzak(zzamVar4);
                        zzamVar4.zzL(zzlqVar);
                    }
                }
            }
        } else if (TextUtils.isEmpty(zzj.zzu()) && zzc.zzi(zzah.ANALYTICS_STORAGE)) {
            zzj.zzI(F(zzc));
        }
        zzj.zzX(zzqVar.zzb);
        zzj.zzF(zzqVar.zzq);
        if (!TextUtils.isEmpty(zzqVar.zzk)) {
            zzj.zzW(zzqVar.zzk);
        }
        long j2 = zzqVar.zze;
        if (j2 != 0) {
            zzj.zzY(j2);
        }
        if (!TextUtils.isEmpty(zzqVar.zzc)) {
            zzj.zzK(zzqVar.zzc);
        }
        zzj.zzL(zzqVar.zzj);
        String str = zzqVar.zzd;
        if (str != null) {
            zzj.zzJ(str);
        }
        zzj.zzT(zzqVar.zzf);
        zzj.zzad(zzqVar.zzh);
        if (!TextUtils.isEmpty(zzqVar.zzg)) {
            zzj.zzZ(zzqVar.zzg);
        }
        if (!zzg().zzs(null, zzen.zzah)) {
            zzj.zzH(zzqVar.zzl);
        }
        zzj.zzG(zzqVar.zzo);
        zzj.zzae(zzqVar.zzr);
        zzj.zzU(zzqVar.zzs);
        zzps.zzc();
        if (zzg().zzs(null, zzen.zzaH)) {
            zzj.zzah(zzqVar.zzx);
        }
        zznz.zzc();
        if (zzg().zzs(null, zzen.zzaz)) {
            zzj.zzag(zzqVar.zzt);
        } else {
            zznz.zzc();
            if (zzg().zzs(null, zzen.zzay)) {
                zzj.zzag(null);
            }
        }
        if (zzj.zzak()) {
            zzam zzamVar5 = this.zze;
            zzak(zzamVar5);
            zzamVar5.zzD(zzj);
        }
        return zzj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final zzai C(String str) {
        String str2;
        zzai zzaiVar = zzai.zza;
        zzaz().zzg();
        b();
        zzai zzaiVar2 = (zzai) this.zzB.get(str);
        if (zzaiVar2 == null) {
            zzam zzamVar = this.zze;
            zzak(zzamVar);
            Preconditions.checkNotNull(str);
            zzamVar.zzg();
            zzamVar.a();
            Cursor cursor = null;
            try {
                try {
                    cursor = zzamVar.p().rawQuery("select consent_state from consent_settings where app_id=? limit 1;", new String[]{str});
                    if (cursor.moveToFirst()) {
                        str2 = cursor.getString(0);
                        cursor.close();
                    } else {
                        cursor.close();
                        str2 = "G1";
                    }
                    zzai zzb2 = zzai.zzb(str2);
                    t(str, zzb2);
                    return zzb2;
                } catch (SQLiteException e2) {
                    zzamVar.f6809a.zzay().zzd().zzc("Database error", "select consent_state from consent_settings where app_id=? limit 1;", e2);
                    throw e2;
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        return zzaiVar2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzgk E() {
        return this.zzn;
    }

    @WorkerThread
    final String F(zzai zzaiVar) {
        if (zzaiVar.zzi(zzah.ANALYTICS_STORAGE)) {
            byte[] bArr = new byte[16];
            zzv().i().nextBytes(bArr);
            return String.format(Locale.US, "%032x", new BigInteger(1, bArr));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String G(zzq zzqVar) {
        try {
            return (String) zzaz().zzh(new zzle(this, zzqVar)).get(AutoConstants.FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e2) {
            zzay().zzd().zzc("Failed to get app instance id. appId", zzfa.g(zzqVar.zza), e2);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void I(Runnable runnable) {
        zzaz().zzg();
        if (this.zzq == null) {
            this.zzq = new ArrayList();
        }
        this.zzq.add(runnable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    @WorkerThread
    public final void a() {
        zzey zzd;
        Integer valueOf;
        Integer valueOf2;
        String str;
        zzaz().zzg();
        b();
        if (this.zzp) {
            return;
        }
        this.zzp = true;
        if (x()) {
            FileChannel fileChannel = this.zzx;
            zzaz().zzg();
            int i2 = 0;
            if (fileChannel == null || !fileChannel.isOpen()) {
                zzay().zzd().zza("Bad channel to read from");
            } else {
                ByteBuffer allocate = ByteBuffer.allocate(4);
                try {
                    fileChannel.position(0L);
                    int read = fileChannel.read(allocate);
                    if (read == 4) {
                        allocate.flip();
                        i2 = allocate.getInt();
                    } else if (read != -1) {
                        zzay().zzk().zzb("Unexpected data length. Bytes read", Integer.valueOf(read));
                    }
                } catch (IOException e2) {
                    zzay().zzd().zzb("Failed to read from channel", e2);
                }
            }
            int e3 = this.zzn.zzh().e();
            zzaz().zzg();
            if (i2 > e3) {
                zzd = zzay().zzd();
                valueOf = Integer.valueOf(i2);
                valueOf2 = Integer.valueOf(e3);
                str = "Panic: can't downgrade version. Previous, current version";
            } else if (i2 >= e3) {
                return;
            } else {
                FileChannel fileChannel2 = this.zzx;
                zzaz().zzg();
                if (fileChannel2 == null || !fileChannel2.isOpen()) {
                    zzay().zzd().zza("Bad channel to read from");
                } else {
                    ByteBuffer allocate2 = ByteBuffer.allocate(4);
                    allocate2.putInt(e3);
                    allocate2.flip();
                    try {
                        fileChannel2.truncate(0L);
                        fileChannel2.write(allocate2);
                        fileChannel2.force(true);
                        if (fileChannel2.size() != 4) {
                            zzay().zzd().zzb("Error writing to channel. Bytes written", Long.valueOf(fileChannel2.size()));
                        }
                        zzd = zzay().zzj();
                        valueOf = Integer.valueOf(i2);
                        valueOf2 = Integer.valueOf(e3);
                        str = "Storage version upgraded. Previous, current version";
                    } catch (IOException e4) {
                        zzay().zzd().zzb("Failed to write to channel", e4);
                    }
                }
                zzd = zzay().zzd();
                valueOf = Integer.valueOf(i2);
                valueOf2 = Integer.valueOf(e3);
                str = "Storage version upgrade failed. Previous, current version";
            }
            zzd.zzc(str, valueOf, valueOf2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b() {
        if (!this.zzo) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    @WorkerThread
    final void c(zzh zzhVar) {
        ArrayMap arrayMap;
        ArrayMap arrayMap2;
        zzaz().zzg();
        if (TextUtils.isEmpty(zzhVar.zzy()) && TextUtils.isEmpty(zzhVar.zzr())) {
            h((String) Preconditions.checkNotNull(zzhVar.zzt()), 204, null, null, null);
            return;
        }
        zzla zzlaVar = this.zzl;
        Uri.Builder builder = new Uri.Builder();
        String zzy = zzhVar.zzy();
        if (TextUtils.isEmpty(zzy)) {
            zzy = zzhVar.zzr();
        }
        ArrayMap arrayMap3 = null;
        Uri.Builder appendQueryParameter = builder.scheme((String) zzen.zzd.zza(null)).encodedAuthority((String) zzen.zze.zza(null)).path("config/app/".concat(String.valueOf(zzy))).appendQueryParameter("platform", "android");
        zzlaVar.f6809a.zzf().zzh();
        appendQueryParameter.appendQueryParameter("gmp_version", String.valueOf(64000L)).appendQueryParameter("runtime_version", "0");
        zzpj.zzc();
        if (!zzlaVar.f6809a.zzf().zzs(zzhVar.zzt(), zzen.zzaA)) {
            builder.appendQueryParameter("app_instance_id", zzhVar.zzu());
        }
        String uri = builder.build().toString();
        try {
            String str = (String) Preconditions.checkNotNull(zzhVar.zzt());
            URL url = new URL(uri);
            zzay().zzj().zzb("Fetching remote configuration", str);
            zzgb zzgbVar = this.zzc;
            zzak(zzgbVar);
            com.google.android.gms.internal.measurement.zzfe f2 = zzgbVar.f(str);
            zzgb zzgbVar2 = this.zzc;
            zzak(zzgbVar2);
            String h2 = zzgbVar2.h(str);
            if (f2 != null) {
                if (TextUtils.isEmpty(h2)) {
                    arrayMap2 = null;
                } else {
                    arrayMap2 = new ArrayMap();
                    arrayMap2.put("If-Modified-Since", h2);
                }
                zzpj.zzc();
                if (zzg().zzs(null, zzen.zzaM)) {
                    zzgb zzgbVar3 = this.zzc;
                    zzak(zzgbVar3);
                    String g2 = zzgbVar3.g(str);
                    if (!TextUtils.isEmpty(g2)) {
                        if (arrayMap2 == null) {
                            arrayMap2 = new ArrayMap();
                        }
                        arrayMap3 = arrayMap2;
                        arrayMap3.put("If-None-Match", g2);
                    }
                }
                arrayMap = arrayMap2;
                this.zzt = true;
                zzfg zzfgVar2 = this.zzd;
                zzak(zzfgVar2);
                zzld zzldVar2 = new zzld(this);
                zzfgVar2.zzg();
                zzfgVar2.a();
                Preconditions.checkNotNull(url);
                Preconditions.checkNotNull(zzldVar2);
                zzfgVar2.f6809a.zzaz().zzo(new zzff(zzfgVar2, str, url, null, arrayMap, zzldVar2));
            }
            arrayMap = arrayMap3;
            this.zzt = true;
            zzfg zzfgVar22 = this.zzd;
            zzak(zzfgVar22);
            zzld zzldVar22 = new zzld(this);
            zzfgVar22.zzg();
            zzfgVar22.a();
            Preconditions.checkNotNull(url);
            Preconditions.checkNotNull(zzldVar22);
            zzfgVar22.f6809a.zzaz().zzo(new zzff(zzfgVar22, str, url, null, arrayMap, zzldVar22));
        } catch (MalformedURLException unused) {
            zzay().zzd().zzc("Failed to parse config URL. Not fetching. appId", zzfa.g(zzhVar.zzt()), uri);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void d(zzaw zzawVar, zzq zzqVar) {
        zzaw zzawVar2;
        List<zzac> zzt;
        List<zzac> zzt2;
        List<zzac> zzt3;
        zzey zzd;
        String str;
        Object g2;
        String f2;
        Object obj;
        String str2;
        Preconditions.checkNotNull(zzqVar);
        Preconditions.checkNotEmpty(zzqVar.zza);
        zzaz().zzg();
        b();
        String str3 = zzqVar.zza;
        zzaw zzawVar3 = zzawVar;
        long j2 = zzawVar3.zzd;
        zzpp.zzc();
        zziw zziwVar = null;
        if (zzg().zzs(null, zzen.zzat)) {
            zzfb zzb2 = zzfb.zzb(zzawVar);
            zzaz().zzg();
            if (this.zzD != null && (str2 = this.zzE) != null && str2.equals(str3)) {
                zziwVar = this.zzD;
            }
            zzlt.zzK(zziwVar, zzb2.zzd, false);
            zzawVar3 = zzb2.zza();
        }
        zzak(this.zzi);
        if (zzln.d(zzawVar3, zzqVar)) {
            if (!zzqVar.zzh) {
                B(zzqVar);
                return;
            }
            List list = zzqVar.zzt;
            if (list == null) {
                zzawVar2 = zzawVar3;
            } else if (!list.contains(zzawVar3.zza)) {
                zzay().zzc().zzd("Dropping non-safelisted event. appId, event name, origin", str3, zzawVar3.zza, zzawVar3.zzc);
                return;
            } else {
                Bundle zzc = zzawVar3.zzb.zzc();
                zzc.putLong("ga_safelisted", 1L);
                zzawVar2 = new zzaw(zzawVar3.zza, new zzau(zzc), zzawVar3.zzc, zzawVar3.zzd);
            }
            zzam zzamVar = this.zze;
            zzak(zzamVar);
            zzamVar.zzw();
            try {
                zzam zzamVar2 = this.zze;
                zzak(zzamVar2);
                Preconditions.checkNotEmpty(str3);
                zzamVar2.zzg();
                zzamVar2.a();
                int i2 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
                if (i2 < 0) {
                    zzamVar2.f6809a.zzay().zzk().zzc("Invalid time querying timed out conditional properties", zzfa.g(str3), Long.valueOf(j2));
                    zzt = Collections.emptyList();
                } else {
                    zzt = zzamVar2.zzt("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str3, String.valueOf(j2)});
                }
                for (zzac zzacVar : zzt) {
                    if (zzacVar != null) {
                        zzay().zzj().zzd("User property timed out", zzacVar.zza, this.zzn.zzj().f(zzacVar.zzc.zzb), zzacVar.zzc.zza());
                        zzaw zzawVar4 = zzacVar.zzg;
                        if (zzawVar4 != null) {
                            w(new zzaw(zzawVar4, j2), zzqVar);
                        }
                        zzam zzamVar3 = this.zze;
                        zzak(zzamVar3);
                        zzamVar3.zza(str3, zzacVar.zzc.zzb);
                    }
                }
                zzam zzamVar4 = this.zze;
                zzak(zzamVar4);
                Preconditions.checkNotEmpty(str3);
                zzamVar4.zzg();
                zzamVar4.a();
                if (i2 < 0) {
                    zzamVar4.f6809a.zzay().zzk().zzc("Invalid time querying expired conditional properties", zzfa.g(str3), Long.valueOf(j2));
                    zzt2 = Collections.emptyList();
                } else {
                    zzt2 = zzamVar4.zzt("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str3, String.valueOf(j2)});
                }
                ArrayList<zzaw> arrayList = new ArrayList(zzt2.size());
                for (zzac zzacVar2 : zzt2) {
                    if (zzacVar2 != null) {
                        zzay().zzj().zzd("User property expired", zzacVar2.zza, this.zzn.zzj().f(zzacVar2.zzc.zzb), zzacVar2.zzc.zza());
                        zzam zzamVar5 = this.zze;
                        zzak(zzamVar5);
                        zzamVar5.zzA(str3, zzacVar2.zzc.zzb);
                        zzaw zzawVar5 = zzacVar2.zzk;
                        if (zzawVar5 != null) {
                            arrayList.add(zzawVar5);
                        }
                        zzam zzamVar6 = this.zze;
                        zzak(zzamVar6);
                        zzamVar6.zza(str3, zzacVar2.zzc.zzb);
                    }
                }
                for (zzaw zzawVar6 : arrayList) {
                    w(new zzaw(zzawVar6, j2), zzqVar);
                }
                zzam zzamVar7 = this.zze;
                zzak(zzamVar7);
                String str4 = zzawVar2.zza;
                Preconditions.checkNotEmpty(str3);
                Preconditions.checkNotEmpty(str4);
                zzamVar7.zzg();
                zzamVar7.a();
                if (i2 < 0) {
                    zzamVar7.f6809a.zzay().zzk().zzd("Invalid time querying triggered conditional properties", zzfa.g(str3), zzamVar7.f6809a.zzj().d(str4), Long.valueOf(j2));
                    zzt3 = Collections.emptyList();
                } else {
                    zzt3 = zzamVar7.zzt("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str3, str4, String.valueOf(j2)});
                }
                ArrayList<zzaw> arrayList2 = new ArrayList(zzt3.size());
                for (zzac zzacVar3 : zzt3) {
                    if (zzacVar3 != null) {
                        zzlo zzloVar = zzacVar3.zzc;
                        zzlq zzlqVar = new zzlq((String) Preconditions.checkNotNull(zzacVar3.zza), zzacVar3.zzb, zzloVar.zzb, j2, Preconditions.checkNotNull(zzloVar.zza()));
                        zzam zzamVar8 = this.zze;
                        zzak(zzamVar8);
                        if (zzamVar8.zzL(zzlqVar)) {
                            zzd = zzay().zzj();
                            str = "User property triggered";
                            g2 = zzacVar3.zza;
                            f2 = this.zzn.zzj().f(zzlqVar.f7044c);
                            obj = zzlqVar.f7046e;
                        } else {
                            zzd = zzay().zzd();
                            str = "Too many active user properties, ignoring";
                            g2 = zzfa.g(zzacVar3.zza);
                            f2 = this.zzn.zzj().f(zzlqVar.f7044c);
                            obj = zzlqVar.f7046e;
                        }
                        zzd.zzd(str, g2, f2, obj);
                        zzaw zzawVar7 = zzacVar3.zzi;
                        if (zzawVar7 != null) {
                            arrayList2.add(zzawVar7);
                        }
                        zzacVar3.zzc = new zzlo(zzlqVar);
                        zzacVar3.zze = true;
                        zzam zzamVar9 = this.zze;
                        zzak(zzamVar9);
                        zzamVar9.zzK(zzacVar3);
                    }
                }
                w(zzawVar2, zzqVar);
                for (zzaw zzawVar8 : arrayList2) {
                    w(new zzaw(zzawVar8, j2), zzqVar);
                }
                zzam zzamVar10 = this.zze;
                zzak(zzamVar10);
                zzamVar10.zzC();
            } finally {
                zzam zzamVar11 = this.zze;
                zzak(zzamVar11);
                zzamVar11.zzx();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void e(zzaw zzawVar, String str) {
        zzam zzamVar = this.zze;
        zzak(zzamVar);
        zzh zzj = zzamVar.zzj(str);
        if (zzj == null || TextUtils.isEmpty(zzj.zzw())) {
            zzay().zzc().zzb("No app data available; dropping event", str);
            return;
        }
        Boolean zzac = zzac(zzj);
        if (zzac == null) {
            if (!"_ui".equals(zzawVar.zza)) {
                zzay().zzk().zzb("Could not find package. appId", zzfa.g(str));
            }
        } else if (!zzac.booleanValue()) {
            zzay().zzd().zzb("App version does not match; dropping event. appId", zzfa.g(str));
            return;
        }
        f(zzawVar, new zzq(str, zzj.zzy(), zzj.zzw(), zzj.zzb(), zzj.zzv(), zzj.zzm(), zzj.zzj(), (String) null, zzj.zzaj(), false, zzj.zzx(), zzj.zza(), 0L, 0, zzj.zzai(), false, zzj.zzr(), zzj.zzq(), zzj.zzk(), zzj.zzC(), (String) null, C(str).zzh(), "", (String) null));
    }

    @WorkerThread
    final void f(zzaw zzawVar, zzq zzqVar) {
        Preconditions.checkNotEmpty(zzqVar.zza);
        zzfb zzb2 = zzfb.zzb(zzawVar);
        zzlt zzv = zzv();
        Bundle bundle = zzb2.zzd;
        zzam zzamVar = this.zze;
        zzak(zzamVar);
        zzv.l(bundle, zzamVar.zzi(zzqVar.zza));
        zzv().m(zzb2, zzg().zzd(zzqVar.zza));
        zzaw zza = zzb2.zza();
        if (Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN.equals(zza.zza) && "referrer API v2".equals(zza.zzb.e("_cis"))) {
            String e2 = zza.zzb.e("gclid");
            if (!TextUtils.isEmpty(e2)) {
                u(new zzlo("_lgclid", zza.zzd, e2, DebugKt.DEBUG_PROPERTY_VALUE_AUTO), zzqVar);
            }
        }
        d(zza, zzqVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void g() {
        this.zzs++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0049 A[Catch: all -> 0x0192, TryCatch #1 {all -> 0x019c, blocks: (B:4:0x0010, B:5:0x0012, B:71:0x018c, B:52:0x0117, B:51:0x0112, B:59:0x0136, B:6:0x002c, B:16:0x0049, B:70:0x0184, B:21:0x0063, B:26:0x00b5, B:25:0x00a6, B:29:0x00bd, B:32:0x00c9, B:34:0x00cf, B:36:0x00d7, B:39:0x00e8, B:42:0x00f4, B:44:0x00fa, B:49:0x0107, B:61:0x013c, B:63:0x0151, B:65:0x0170, B:67:0x017b, B:69:0x0181, B:64:0x015f, B:55:0x0120, B:57:0x012b), top: B:78:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0112 A[Catch: all -> 0x019c, TRY_ENTER, TryCatch #1 {all -> 0x019c, blocks: (B:4:0x0010, B:5:0x0012, B:71:0x018c, B:52:0x0117, B:51:0x0112, B:59:0x0136, B:6:0x002c, B:16:0x0049, B:70:0x0184, B:21:0x0063, B:26:0x00b5, B:25:0x00a6, B:29:0x00bd, B:32:0x00c9, B:34:0x00cf, B:36:0x00d7, B:39:0x00e8, B:42:0x00f4, B:44:0x00fa, B:49:0x0107, B:61:0x013c, B:63:0x0151, B:65:0x0170, B:67:0x017b, B:69:0x0181, B:64:0x015f, B:55:0x0120, B:57:0x012b), top: B:78:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x012b A[Catch: all -> 0x0192, TRY_LEAVE, TryCatch #1 {all -> 0x019c, blocks: (B:4:0x0010, B:5:0x0012, B:71:0x018c, B:52:0x0117, B:51:0x0112, B:59:0x0136, B:6:0x002c, B:16:0x0049, B:70:0x0184, B:21:0x0063, B:26:0x00b5, B:25:0x00a6, B:29:0x00bd, B:32:0x00c9, B:34:0x00cf, B:36:0x00d7, B:39:0x00e8, B:42:0x00f4, B:44:0x00fa, B:49:0x0107, B:61:0x013c, B:63:0x0151, B:65:0x0170, B:67:0x017b, B:69:0x0181, B:64:0x015f, B:55:0x0120, B:57:0x012b), top: B:78:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0151 A[Catch: all -> 0x0192, TryCatch #1 {all -> 0x019c, blocks: (B:4:0x0010, B:5:0x0012, B:71:0x018c, B:52:0x0117, B:51:0x0112, B:59:0x0136, B:6:0x002c, B:16:0x0049, B:70:0x0184, B:21:0x0063, B:26:0x00b5, B:25:0x00a6, B:29:0x00bd, B:32:0x00c9, B:34:0x00cf, B:36:0x00d7, B:39:0x00e8, B:42:0x00f4, B:44:0x00fa, B:49:0x0107, B:61:0x013c, B:63:0x0151, B:65:0x0170, B:67:0x017b, B:69:0x0181, B:64:0x015f, B:55:0x0120, B:57:0x012b), top: B:78:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x015f A[Catch: all -> 0x0192, TryCatch #1 {all -> 0x019c, blocks: (B:4:0x0010, B:5:0x0012, B:71:0x018c, B:52:0x0117, B:51:0x0112, B:59:0x0136, B:6:0x002c, B:16:0x0049, B:70:0x0184, B:21:0x0063, B:26:0x00b5, B:25:0x00a6, B:29:0x00bd, B:32:0x00c9, B:34:0x00cf, B:36:0x00d7, B:39:0x00e8, B:42:0x00f4, B:44:0x00fa, B:49:0x0107, B:61:0x013c, B:63:0x0151, B:65:0x0170, B:67:0x017b, B:69:0x0181, B:64:0x015f, B:55:0x0120, B:57:0x012b), top: B:78:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x017b A[Catch: all -> 0x0192, TryCatch #1 {all -> 0x019c, blocks: (B:4:0x0010, B:5:0x0012, B:71:0x018c, B:52:0x0117, B:51:0x0112, B:59:0x0136, B:6:0x002c, B:16:0x0049, B:70:0x0184, B:21:0x0063, B:26:0x00b5, B:25:0x00a6, B:29:0x00bd, B:32:0x00c9, B:34:0x00cf, B:36:0x00d7, B:39:0x00e8, B:42:0x00f4, B:44:0x00fa, B:49:0x0107, B:61:0x013c, B:63:0x0151, B:65:0x0170, B:67:0x017b, B:69:0x0181, B:64:0x015f, B:55:0x0120, B:57:0x012b), top: B:78:0x0010 }] */
    @VisibleForTesting
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void h(String str, int i2, Throwable th, byte[] bArr, Map map) {
        boolean z;
        String str2;
        zzgb zzgbVar;
        zzam zzamVar;
        zzfg zzfgVar;
        zzgb zzgbVar2;
        zzaz().zzg();
        b();
        Preconditions.checkNotEmpty(str);
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } finally {
                this.zzt = false;
                zzad();
            }
        }
        zzey zzj = zzay().zzj();
        Integer valueOf = Integer.valueOf(bArr.length);
        zzj.zzb("onConfigFetched. Response size", valueOf);
        zzam zzamVar2 = this.zze;
        zzak(zzamVar2);
        zzamVar2.zzw();
        zzam zzamVar3 = this.zze;
        zzak(zzamVar3);
        zzh zzj2 = zzamVar3.zzj(str);
        if (i2 != 200 && i2 != 204) {
            if (i2 == 304) {
                i2 = 304;
            }
            z = false;
            if (zzj2 == null) {
                zzay().zzk().zzb("App does not exist in onConfigFetched. appId", zzfa.g(str));
            } else {
                if (!z && i2 != 404) {
                    zzj2.zzV(zzav().currentTimeMillis());
                    zzam zzamVar4 = this.zze;
                    zzak(zzamVar4);
                    zzamVar4.zzD(zzj2);
                    zzay().zzj().zzc("Fetching config failed. code, error", Integer.valueOf(i2), th);
                    zzgb zzgbVar3 = this.zzc;
                    zzak(zzgbVar3);
                    zzgbVar3.l(str);
                    this.zzk.zzd.zzb(zzav().currentTimeMillis());
                    if (i2 == 503 || i2 == 429) {
                        this.zzk.zzb.zzb(zzav().currentTimeMillis());
                    }
                    zzaf();
                }
                List list = map != null ? (List) map.get("Last-Modified") : null;
                String str3 = (list == null || list.isEmpty()) ? null : (String) list.get(0);
                zzpj.zzc();
                if (zzg().zzs(null, zzen.zzaM)) {
                    List list2 = map != null ? (List) map.get("ETag") : null;
                    if (list2 != null && !list2.isEmpty()) {
                        str2 = (String) list2.get(0);
                        if (i2 != 404 && i2 != 304) {
                            zzgbVar2 = this.zzc;
                            zzak(zzgbVar2);
                            if (!zzgbVar2.s(str, bArr, str3, str2)) {
                                zzamVar = this.zze;
                                zzak(zzamVar);
                                zzamVar.zzx();
                            }
                            zzj2.zzM(zzav().currentTimeMillis());
                            zzam zzamVar5222 = this.zze;
                            zzak(zzamVar5222);
                            zzamVar5222.zzD(zzj2);
                            if (i2 != 404) {
                                zzay().zzl().zzb("Config not found. Using empty config. appId", str);
                            } else {
                                zzay().zzj().zzc("Successfully fetched config. Got network response. code, size", Integer.valueOf(i2), valueOf);
                            }
                            zzfgVar = this.zzd;
                            zzak(zzfgVar);
                            if (zzfgVar.zza() && zzah()) {
                                v();
                            }
                            zzaf();
                        }
                        zzgbVar = this.zzc;
                        zzak(zzgbVar);
                        if (zzgbVar.f(str) == null) {
                            zzgb zzgbVar4 = this.zzc;
                            zzak(zzgbVar4);
                            if (!zzgbVar4.s(str, null, null, null)) {
                                zzamVar = this.zze;
                                zzak(zzamVar);
                                zzamVar.zzx();
                            }
                        }
                        zzj2.zzM(zzav().currentTimeMillis());
                        zzam zzamVar52222 = this.zze;
                        zzak(zzamVar52222);
                        zzamVar52222.zzD(zzj2);
                        if (i2 != 404) {
                        }
                        zzfgVar = this.zzd;
                        zzak(zzfgVar);
                        if (zzfgVar.zza()) {
                            v();
                        }
                        zzaf();
                    }
                }
                str2 = null;
                if (i2 != 404) {
                    zzgbVar2 = this.zzc;
                    zzak(zzgbVar2);
                    if (!zzgbVar2.s(str, bArr, str3, str2)) {
                    }
                    zzj2.zzM(zzav().currentTimeMillis());
                    zzam zzamVar522222 = this.zze;
                    zzak(zzamVar522222);
                    zzamVar522222.zzD(zzj2);
                    if (i2 != 404) {
                    }
                    zzfgVar = this.zzd;
                    zzak(zzfgVar);
                    if (zzfgVar.zza()) {
                    }
                    zzaf();
                }
                zzgbVar = this.zzc;
                zzak(zzgbVar);
                if (zzgbVar.f(str) == null) {
                }
                zzj2.zzM(zzav().currentTimeMillis());
                zzam zzamVar5222222 = this.zze;
                zzak(zzamVar5222222);
                zzamVar5222222.zzD(zzj2);
                if (i2 != 404) {
                }
                zzfgVar = this.zzd;
                zzak(zzfgVar);
                if (zzfgVar.zza()) {
                }
                zzaf();
            }
            zzam zzamVar622 = this.zze;
            zzak(zzamVar622);
            zzamVar622.zzC();
            zzamVar = this.zze;
            zzak(zzamVar);
            zzamVar.zzx();
        }
        if (th == null) {
            z = true;
            if (zzj2 == null) {
            }
            zzam zzamVar6222 = this.zze;
            zzak(zzamVar6222);
            zzamVar6222.zzC();
            zzamVar = this.zze;
            zzak(zzamVar);
            zzamVar.zzx();
        }
        z = false;
        if (zzj2 == null) {
        }
        zzam zzamVar62222 = this.zze;
        zzak(zzamVar62222);
        zzamVar62222.zzC();
        zzamVar = this.zze;
        zzak(zzamVar);
        zzamVar.zzx();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void i(boolean z) {
        zzaf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    @WorkerThread
    public final void j(int i2, Throwable th, byte[] bArr, String str) {
        zzam zzamVar;
        long longValue;
        zzaz().zzg();
        b();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } finally {
                this.zzu = false;
                zzad();
            }
        }
        List<Long> list = (List) Preconditions.checkNotNull(this.zzy);
        this.zzy = null;
        if (i2 != 200) {
            if (i2 == 204) {
                i2 = 204;
            }
            zzay().zzj().zzc("Network upload failed. Will retry later. code, error", Integer.valueOf(i2), th);
            this.zzk.zzd.zzb(zzav().currentTimeMillis());
            if (i2 != 503 || i2 == 429) {
                this.zzk.zzb.zzb(zzav().currentTimeMillis());
            }
            zzam zzamVar22 = this.zze;
            zzak(zzamVar22);
            zzamVar22.s(list);
            zzaf();
        }
        if (th == null) {
            try {
                this.zzk.zzc.zzb(zzav().currentTimeMillis());
                this.zzk.zzd.zzb(0L);
                zzaf();
                zzay().zzj().zzc("Successful upload. Got network response. code, size", Integer.valueOf(i2), Integer.valueOf(bArr.length));
                zzam zzamVar3 = this.zze;
                zzak(zzamVar3);
                zzamVar3.zzw();
            } catch (SQLiteException e2) {
                zzay().zzd().zzb("Database error while trying to delete uploaded bundles", e2);
                this.f7040a = zzav().elapsedRealtime();
                zzay().zzj().zzb("Disable upload, time", Long.valueOf(this.f7040a));
            }
            try {
                for (Long l2 : list) {
                    try {
                        zzamVar = this.zze;
                        zzak(zzamVar);
                        longValue = l2.longValue();
                        zzamVar.zzg();
                        zzamVar.a();
                    } catch (SQLiteException e3) {
                        List list2 = this.zzz;
                        if (list2 == null || !list2.contains(l2)) {
                            throw e3;
                        }
                    }
                    try {
                        if (zzamVar.p().delete("queue", "rowid=?", new String[]{String.valueOf(longValue)}) != 1) {
                            throw new SQLiteException("Deleted fewer rows from queue than expected");
                            break;
                        }
                    } catch (SQLiteException e4) {
                        zzamVar.f6809a.zzay().zzd().zzb("Failed to delete a bundle in a queue table", e4);
                        throw e4;
                        break;
                    }
                }
                zzam zzamVar4 = this.zze;
                zzak(zzamVar4);
                zzamVar4.zzC();
                zzam zzamVar5 = this.zze;
                zzak(zzamVar5);
                zzamVar5.zzx();
                this.zzz = null;
                zzfg zzfgVar = this.zzd;
                zzak(zzfgVar);
                if (zzfgVar.zza() && zzah()) {
                    v();
                } else {
                    this.zzA = -1L;
                    zzaf();
                }
                this.f7040a = 0L;
            } catch (Throwable th2) {
                zzam zzamVar6 = this.zze;
                zzak(zzamVar6);
                zzamVar6.zzx();
                throw th2;
            }
        }
        zzay().zzj().zzc("Network upload failed. Will retry later. code, error", Integer.valueOf(i2), th);
        this.zzk.zzd.zzb(zzav().currentTimeMillis());
        if (i2 != 503) {
        }
        this.zzk.zzb.zzb(zzav().currentTimeMillis());
        zzam zzamVar222 = this.zze;
        zzak(zzamVar222);
        zzamVar222.s(list);
        zzaf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:121:0x03c1 A[Catch: all -> 0x0558, TryCatch #2 {all -> 0x0558, blocks: (B:23:0x00a4, B:25:0x00b3, B:43:0x0119, B:45:0x012c, B:47:0x0142, B:48:0x0169, B:50:0x01b9, B:53:0x01ce, B:56:0x01e4, B:58:0x01ef, B:63:0x01fe, B:66:0x020c, B:70:0x0217, B:72:0x021a, B:74:0x023a, B:76:0x023f, B:79:0x025e, B:82:0x0271, B:84:0x029a, B:87:0x02a2, B:89:0x02b1, B:90:0x02bd, B:119:0x038f, B:121:0x03c1, B:122:0x03c4, B:124:0x03ed, B:165:0x04c8, B:166:0x04cd, B:175:0x0547, B:127:0x0404, B:132:0x0429, B:134:0x0435, B:136:0x043b, B:140:0x044e, B:144:0x0461, B:148:0x046d, B:152:0x0487, B:155:0x0498, B:157:0x04ac, B:159:0x04b2, B:160:0x04b7, B:162:0x04bd, B:142:0x0459, B:130:0x0415, B:91:0x02c2, B:93:0x02ed, B:94:0x02fa, B:96:0x0301, B:98:0x0307, B:100:0x0311, B:102:0x0317, B:104:0x031d, B:106:0x0323, B:107:0x0328, B:112:0x034c, B:115:0x0351, B:116:0x0365, B:117:0x0373, B:118:0x0381, B:167:0x04e2, B:169:0x0514, B:170:0x0517, B:171:0x0528, B:172:0x052c, B:174:0x0530, B:77:0x024e, B:29:0x00c5, B:31:0x00c9, B:35:0x00da, B:37:0x00f3, B:39:0x00fd, B:42:0x0109), top: B:186:0x00a4, inners: #0, #1, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x03ed A[Catch: all -> 0x0558, TRY_LEAVE, TryCatch #2 {all -> 0x0558, blocks: (B:23:0x00a4, B:25:0x00b3, B:43:0x0119, B:45:0x012c, B:47:0x0142, B:48:0x0169, B:50:0x01b9, B:53:0x01ce, B:56:0x01e4, B:58:0x01ef, B:63:0x01fe, B:66:0x020c, B:70:0x0217, B:72:0x021a, B:74:0x023a, B:76:0x023f, B:79:0x025e, B:82:0x0271, B:84:0x029a, B:87:0x02a2, B:89:0x02b1, B:90:0x02bd, B:119:0x038f, B:121:0x03c1, B:122:0x03c4, B:124:0x03ed, B:165:0x04c8, B:166:0x04cd, B:175:0x0547, B:127:0x0404, B:132:0x0429, B:134:0x0435, B:136:0x043b, B:140:0x044e, B:144:0x0461, B:148:0x046d, B:152:0x0487, B:155:0x0498, B:157:0x04ac, B:159:0x04b2, B:160:0x04b7, B:162:0x04bd, B:142:0x0459, B:130:0x0415, B:91:0x02c2, B:93:0x02ed, B:94:0x02fa, B:96:0x0301, B:98:0x0307, B:100:0x0311, B:102:0x0317, B:104:0x031d, B:106:0x0323, B:107:0x0328, B:112:0x034c, B:115:0x0351, B:116:0x0365, B:117:0x0373, B:118:0x0381, B:167:0x04e2, B:169:0x0514, B:170:0x0517, B:171:0x0528, B:172:0x052c, B:174:0x0530, B:77:0x024e, B:29:0x00c5, B:31:0x00c9, B:35:0x00da, B:37:0x00f3, B:39:0x00fd, B:42:0x0109), top: B:186:0x00a4, inners: #0, #1, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:165:0x04c8 A[Catch: all -> 0x0558, TryCatch #2 {all -> 0x0558, blocks: (B:23:0x00a4, B:25:0x00b3, B:43:0x0119, B:45:0x012c, B:47:0x0142, B:48:0x0169, B:50:0x01b9, B:53:0x01ce, B:56:0x01e4, B:58:0x01ef, B:63:0x01fe, B:66:0x020c, B:70:0x0217, B:72:0x021a, B:74:0x023a, B:76:0x023f, B:79:0x025e, B:82:0x0271, B:84:0x029a, B:87:0x02a2, B:89:0x02b1, B:90:0x02bd, B:119:0x038f, B:121:0x03c1, B:122:0x03c4, B:124:0x03ed, B:165:0x04c8, B:166:0x04cd, B:175:0x0547, B:127:0x0404, B:132:0x0429, B:134:0x0435, B:136:0x043b, B:140:0x044e, B:144:0x0461, B:148:0x046d, B:152:0x0487, B:155:0x0498, B:157:0x04ac, B:159:0x04b2, B:160:0x04b7, B:162:0x04bd, B:142:0x0459, B:130:0x0415, B:91:0x02c2, B:93:0x02ed, B:94:0x02fa, B:96:0x0301, B:98:0x0307, B:100:0x0311, B:102:0x0317, B:104:0x031d, B:106:0x0323, B:107:0x0328, B:112:0x034c, B:115:0x0351, B:116:0x0365, B:117:0x0373, B:118:0x0381, B:167:0x04e2, B:169:0x0514, B:170:0x0517, B:171:0x0528, B:172:0x052c, B:174:0x0530, B:77:0x024e, B:29:0x00c5, B:31:0x00c9, B:35:0x00da, B:37:0x00f3, B:39:0x00fd, B:42:0x0109), top: B:186:0x00a4, inners: #0, #1, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:172:0x052c A[Catch: all -> 0x0558, TryCatch #2 {all -> 0x0558, blocks: (B:23:0x00a4, B:25:0x00b3, B:43:0x0119, B:45:0x012c, B:47:0x0142, B:48:0x0169, B:50:0x01b9, B:53:0x01ce, B:56:0x01e4, B:58:0x01ef, B:63:0x01fe, B:66:0x020c, B:70:0x0217, B:72:0x021a, B:74:0x023a, B:76:0x023f, B:79:0x025e, B:82:0x0271, B:84:0x029a, B:87:0x02a2, B:89:0x02b1, B:90:0x02bd, B:119:0x038f, B:121:0x03c1, B:122:0x03c4, B:124:0x03ed, B:165:0x04c8, B:166:0x04cd, B:175:0x0547, B:127:0x0404, B:132:0x0429, B:134:0x0435, B:136:0x043b, B:140:0x044e, B:144:0x0461, B:148:0x046d, B:152:0x0487, B:155:0x0498, B:157:0x04ac, B:159:0x04b2, B:160:0x04b7, B:162:0x04bd, B:142:0x0459, B:130:0x0415, B:91:0x02c2, B:93:0x02ed, B:94:0x02fa, B:96:0x0301, B:98:0x0307, B:100:0x0311, B:102:0x0317, B:104:0x031d, B:106:0x0323, B:107:0x0328, B:112:0x034c, B:115:0x0351, B:116:0x0365, B:117:0x0373, B:118:0x0381, B:167:0x04e2, B:169:0x0514, B:170:0x0517, B:171:0x0528, B:172:0x052c, B:174:0x0530, B:77:0x024e, B:29:0x00c5, B:31:0x00c9, B:35:0x00da, B:37:0x00f3, B:39:0x00fd, B:42:0x0109), top: B:186:0x00a4, inners: #0, #1, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0404 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01b9 A[Catch: SQLiteException -> 0x01cd, all -> 0x0558, TRY_LEAVE, TryCatch #4 {SQLiteException -> 0x01cd, blocks: (B:48:0x0169, B:50:0x01b9), top: B:190:0x0169, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01e4 A[Catch: all -> 0x0558, TryCatch #2 {all -> 0x0558, blocks: (B:23:0x00a4, B:25:0x00b3, B:43:0x0119, B:45:0x012c, B:47:0x0142, B:48:0x0169, B:50:0x01b9, B:53:0x01ce, B:56:0x01e4, B:58:0x01ef, B:63:0x01fe, B:66:0x020c, B:70:0x0217, B:72:0x021a, B:74:0x023a, B:76:0x023f, B:79:0x025e, B:82:0x0271, B:84:0x029a, B:87:0x02a2, B:89:0x02b1, B:90:0x02bd, B:119:0x038f, B:121:0x03c1, B:122:0x03c4, B:124:0x03ed, B:165:0x04c8, B:166:0x04cd, B:175:0x0547, B:127:0x0404, B:132:0x0429, B:134:0x0435, B:136:0x043b, B:140:0x044e, B:144:0x0461, B:148:0x046d, B:152:0x0487, B:155:0x0498, B:157:0x04ac, B:159:0x04b2, B:160:0x04b7, B:162:0x04bd, B:142:0x0459, B:130:0x0415, B:91:0x02c2, B:93:0x02ed, B:94:0x02fa, B:96:0x0301, B:98:0x0307, B:100:0x0311, B:102:0x0317, B:104:0x031d, B:106:0x0323, B:107:0x0328, B:112:0x034c, B:115:0x0351, B:116:0x0365, B:117:0x0373, B:118:0x0381, B:167:0x04e2, B:169:0x0514, B:170:0x0517, B:171:0x0528, B:172:0x052c, B:174:0x0530, B:77:0x024e, B:29:0x00c5, B:31:0x00c9, B:35:0x00da, B:37:0x00f3, B:39:0x00fd, B:42:0x0109), top: B:186:0x00a4, inners: #0, #1, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x021a A[Catch: all -> 0x0558, TryCatch #2 {all -> 0x0558, blocks: (B:23:0x00a4, B:25:0x00b3, B:43:0x0119, B:45:0x012c, B:47:0x0142, B:48:0x0169, B:50:0x01b9, B:53:0x01ce, B:56:0x01e4, B:58:0x01ef, B:63:0x01fe, B:66:0x020c, B:70:0x0217, B:72:0x021a, B:74:0x023a, B:76:0x023f, B:79:0x025e, B:82:0x0271, B:84:0x029a, B:87:0x02a2, B:89:0x02b1, B:90:0x02bd, B:119:0x038f, B:121:0x03c1, B:122:0x03c4, B:124:0x03ed, B:165:0x04c8, B:166:0x04cd, B:175:0x0547, B:127:0x0404, B:132:0x0429, B:134:0x0435, B:136:0x043b, B:140:0x044e, B:144:0x0461, B:148:0x046d, B:152:0x0487, B:155:0x0498, B:157:0x04ac, B:159:0x04b2, B:160:0x04b7, B:162:0x04bd, B:142:0x0459, B:130:0x0415, B:91:0x02c2, B:93:0x02ed, B:94:0x02fa, B:96:0x0301, B:98:0x0307, B:100:0x0311, B:102:0x0317, B:104:0x031d, B:106:0x0323, B:107:0x0328, B:112:0x034c, B:115:0x0351, B:116:0x0365, B:117:0x0373, B:118:0x0381, B:167:0x04e2, B:169:0x0514, B:170:0x0517, B:171:0x0528, B:172:0x052c, B:174:0x0530, B:77:0x024e, B:29:0x00c5, B:31:0x00c9, B:35:0x00da, B:37:0x00f3, B:39:0x00fd, B:42:0x0109), top: B:186:0x00a4, inners: #0, #1, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x023f A[Catch: all -> 0x0558, TryCatch #2 {all -> 0x0558, blocks: (B:23:0x00a4, B:25:0x00b3, B:43:0x0119, B:45:0x012c, B:47:0x0142, B:48:0x0169, B:50:0x01b9, B:53:0x01ce, B:56:0x01e4, B:58:0x01ef, B:63:0x01fe, B:66:0x020c, B:70:0x0217, B:72:0x021a, B:74:0x023a, B:76:0x023f, B:79:0x025e, B:82:0x0271, B:84:0x029a, B:87:0x02a2, B:89:0x02b1, B:90:0x02bd, B:119:0x038f, B:121:0x03c1, B:122:0x03c4, B:124:0x03ed, B:165:0x04c8, B:166:0x04cd, B:175:0x0547, B:127:0x0404, B:132:0x0429, B:134:0x0435, B:136:0x043b, B:140:0x044e, B:144:0x0461, B:148:0x046d, B:152:0x0487, B:155:0x0498, B:157:0x04ac, B:159:0x04b2, B:160:0x04b7, B:162:0x04bd, B:142:0x0459, B:130:0x0415, B:91:0x02c2, B:93:0x02ed, B:94:0x02fa, B:96:0x0301, B:98:0x0307, B:100:0x0311, B:102:0x0317, B:104:0x031d, B:106:0x0323, B:107:0x0328, B:112:0x034c, B:115:0x0351, B:116:0x0365, B:117:0x0373, B:118:0x0381, B:167:0x04e2, B:169:0x0514, B:170:0x0517, B:171:0x0528, B:172:0x052c, B:174:0x0530, B:77:0x024e, B:29:0x00c5, B:31:0x00c9, B:35:0x00da, B:37:0x00f3, B:39:0x00fd, B:42:0x0109), top: B:186:0x00a4, inners: #0, #1, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x024e A[Catch: all -> 0x0558, TryCatch #2 {all -> 0x0558, blocks: (B:23:0x00a4, B:25:0x00b3, B:43:0x0119, B:45:0x012c, B:47:0x0142, B:48:0x0169, B:50:0x01b9, B:53:0x01ce, B:56:0x01e4, B:58:0x01ef, B:63:0x01fe, B:66:0x020c, B:70:0x0217, B:72:0x021a, B:74:0x023a, B:76:0x023f, B:79:0x025e, B:82:0x0271, B:84:0x029a, B:87:0x02a2, B:89:0x02b1, B:90:0x02bd, B:119:0x038f, B:121:0x03c1, B:122:0x03c4, B:124:0x03ed, B:165:0x04c8, B:166:0x04cd, B:175:0x0547, B:127:0x0404, B:132:0x0429, B:134:0x0435, B:136:0x043b, B:140:0x044e, B:144:0x0461, B:148:0x046d, B:152:0x0487, B:155:0x0498, B:157:0x04ac, B:159:0x04b2, B:160:0x04b7, B:162:0x04bd, B:142:0x0459, B:130:0x0415, B:91:0x02c2, B:93:0x02ed, B:94:0x02fa, B:96:0x0301, B:98:0x0307, B:100:0x0311, B:102:0x0317, B:104:0x031d, B:106:0x0323, B:107:0x0328, B:112:0x034c, B:115:0x0351, B:116:0x0365, B:117:0x0373, B:118:0x0381, B:167:0x04e2, B:169:0x0514, B:170:0x0517, B:171:0x0528, B:172:0x052c, B:174:0x0530, B:77:0x024e, B:29:0x00c5, B:31:0x00c9, B:35:0x00da, B:37:0x00f3, B:39:0x00fd, B:42:0x0109), top: B:186:0x00a4, inners: #0, #1, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x025e A[Catch: all -> 0x0558, TRY_LEAVE, TryCatch #2 {all -> 0x0558, blocks: (B:23:0x00a4, B:25:0x00b3, B:43:0x0119, B:45:0x012c, B:47:0x0142, B:48:0x0169, B:50:0x01b9, B:53:0x01ce, B:56:0x01e4, B:58:0x01ef, B:63:0x01fe, B:66:0x020c, B:70:0x0217, B:72:0x021a, B:74:0x023a, B:76:0x023f, B:79:0x025e, B:82:0x0271, B:84:0x029a, B:87:0x02a2, B:89:0x02b1, B:90:0x02bd, B:119:0x038f, B:121:0x03c1, B:122:0x03c4, B:124:0x03ed, B:165:0x04c8, B:166:0x04cd, B:175:0x0547, B:127:0x0404, B:132:0x0429, B:134:0x0435, B:136:0x043b, B:140:0x044e, B:144:0x0461, B:148:0x046d, B:152:0x0487, B:155:0x0498, B:157:0x04ac, B:159:0x04b2, B:160:0x04b7, B:162:0x04bd, B:142:0x0459, B:130:0x0415, B:91:0x02c2, B:93:0x02ed, B:94:0x02fa, B:96:0x0301, B:98:0x0307, B:100:0x0311, B:102:0x0317, B:104:0x031d, B:106:0x0323, B:107:0x0328, B:112:0x034c, B:115:0x0351, B:116:0x0365, B:117:0x0373, B:118:0x0381, B:167:0x04e2, B:169:0x0514, B:170:0x0517, B:171:0x0528, B:172:0x052c, B:174:0x0530, B:77:0x024e, B:29:0x00c5, B:31:0x00c9, B:35:0x00da, B:37:0x00f3, B:39:0x00fd, B:42:0x0109), top: B:186:0x00a4, inners: #0, #1, #3, #4 }] */
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void k(zzq zzqVar) {
        String str;
        String str2;
        int i2;
        zzh zzj;
        String str3;
        zzas zzn;
        boolean z;
        zzaw zzawVar;
        zzey zzm;
        String str4;
        long o2;
        PackageInfo packageInfo;
        String str5;
        String str6;
        String str7;
        ApplicationInfo applicationInfo;
        boolean z2;
        boolean z3;
        String zzw;
        zzam zzamVar;
        String zzt;
        int delete;
        zzaz().zzg();
        b();
        Preconditions.checkNotNull(zzqVar);
        Preconditions.checkNotEmpty(zzqVar.zza);
        if (!zzaj(zzqVar)) {
            return;
        }
        zzam zzamVar2 = this.zze;
        zzak(zzamVar2);
        zzh zzj2 = zzamVar2.zzj(zzqVar.zza);
        if (zzj2 != null && TextUtils.isEmpty(zzj2.zzy()) && !TextUtils.isEmpty(zzqVar.zzb)) {
            zzj2.zzM(0L);
            zzam zzamVar3 = this.zze;
            zzak(zzamVar3);
            zzamVar3.zzD(zzj2);
            zzgb zzgbVar = this.zzc;
            zzak(zzgbVar);
            zzgbVar.m(zzqVar.zza);
        }
        if (!zzqVar.zzh) {
            B(zzqVar);
            return;
        }
        long j2 = zzqVar.zzm;
        if (j2 == 0) {
            j2 = zzav().currentTimeMillis();
        }
        this.zzn.zzg().f();
        int i3 = zzqVar.zzn;
        if (i3 != 0 && i3 != 1) {
            zzay().zzk().zzc("Incorrect app type, assuming installed app. appId, appType", zzfa.g(zzqVar.zza), Integer.valueOf(i3));
            i3 = 0;
        }
        zzam zzamVar4 = this.zze;
        zzak(zzamVar4);
        zzamVar4.zzw();
        try {
            zzam zzamVar5 = this.zze;
            zzak(zzamVar5);
            zzlq zzp = zzamVar5.zzp(zzqVar.zza, "_npa");
            if (zzp != null && !DebugKt.DEBUG_PROPERTY_VALUE_AUTO.equals(zzp.f7043b)) {
                str = "_sysu";
                str2 = "_sys";
                i2 = 1;
                zzam zzamVar62 = this.zze;
                zzak(zzamVar62);
                zzj = zzamVar62.zzj((String) Preconditions.checkNotNull(zzqVar.zza));
                if (zzj != null && zzv().F(zzqVar.zzb, zzj.zzy(), zzqVar.zzq, zzj.zzr())) {
                    zzay().zzk().zzb("New GMP App Id passed in. Removing cached database data. appId", zzfa.g(zzj.zzt()));
                    zzamVar = this.zze;
                    zzak(zzamVar);
                    zzt = zzj.zzt();
                    zzamVar.a();
                    zzamVar.zzg();
                    Preconditions.checkNotEmpty(zzt);
                    try {
                        SQLiteDatabase p22 = zzamVar.p();
                        String[] strArr2 = new String[i2];
                        strArr2[0] = zzt;
                        delete = p22.delete("events", "app_id=?", strArr2) + p22.delete("user_attributes", "app_id=?", strArr2) + p22.delete("conditional_properties", "app_id=?", strArr2) + p22.delete("apps", "app_id=?", strArr2) + p22.delete("raw_events", "app_id=?", strArr2) + p22.delete("raw_events_metadata", "app_id=?", strArr2) + p22.delete("event_filters", "app_id=?", strArr2) + p22.delete("property_filters", "app_id=?", strArr2) + p22.delete("audience_filter_values", "app_id=?", strArr2) + p22.delete("consent_settings", "app_id=?", strArr2);
                        if (delete > 0) {
                            zzamVar.f6809a.zzay().zzj().zzc("Deleted application data. app, records", zzt, Integer.valueOf(delete));
                        }
                    } catch (SQLiteException e2) {
                        zzamVar.f6809a.zzay().zzd().zzc("Error deleting application data. appId, error", zzfa.g(zzt), e2);
                    }
                    zzj = null;
                }
                if (zzj == null) {
                    if (zzj.zzb() != -2147483648L) {
                        str3 = "com.android.vending";
                        if (zzj.zzb() != zzqVar.zzj) {
                            z3 = true;
                            zzw = zzj.zzw();
                            if (z3 | ((zzj.zzb() == -2147483648L || zzw == null || zzw.equals(zzqVar.zzc)) ? false : true)) {
                                Bundle bundle = new Bundle();
                                bundle.putString("_pv", zzw);
                                d(new zzaw("_au", new zzau(bundle), DebugKt.DEBUG_PROPERTY_VALUE_AUTO, j2), zzqVar);
                            }
                        }
                    } else {
                        str3 = "com.android.vending";
                    }
                    z3 = false;
                    zzw = zzj.zzw();
                    if (z3 | ((zzj.zzb() == -2147483648L || zzw == null || zzw.equals(zzqVar.zzc)) ? false : true)) {
                    }
                } else {
                    str3 = "com.android.vending";
                }
                B(zzqVar);
                if (i3 != 0) {
                    zzam zzamVar7 = this.zze;
                    zzak(zzamVar7);
                    zzn = zzamVar7.zzn(zzqVar.zza, "_f");
                    z = false;
                } else {
                    zzam zzamVar8 = this.zze;
                    zzak(zzamVar8);
                    zzn = zzamVar8.zzn(zzqVar.zza, "_v");
                    z = true;
                }
                if (zzn == null) {
                    if (zzqVar.zzi) {
                        zzawVar = new zzaw("_cd", new zzau(new Bundle()), DebugKt.DEBUG_PROPERTY_VALUE_AUTO, j2);
                        f(zzawVar, zzqVar);
                    }
                    zzam zzamVar92222 = this.zze;
                    zzak(zzamVar92222);
                    zzamVar92222.zzC();
                    return;
                }
                long j3 = ((j2 / 3600000) + 1) * 3600000;
                if (z) {
                    u(new zzlo("_fvt", j2, Long.valueOf(j3), DebugKt.DEBUG_PROPERTY_VALUE_AUTO), zzqVar);
                    zzaz().zzg();
                    b();
                    Bundle bundle2 = new Bundle();
                    bundle2.putLong("_c", 1L);
                    bundle2.putLong("_r", 1L);
                    bundle2.putLong("_et", 1L);
                    if (zzqVar.zzp) {
                        bundle2.putLong("_dac", 1L);
                    }
                    zzawVar = new zzaw("_v", new zzau(bundle2), DebugKt.DEBUG_PROPERTY_VALUE_AUTO, j2);
                    f(zzawVar, zzqVar);
                    zzam zzamVar922222 = this.zze;
                    zzak(zzamVar922222);
                    zzamVar922222.zzC();
                    return;
                }
                u(new zzlo("_fot", j2, Long.valueOf(j3), DebugKt.DEBUG_PROPERTY_VALUE_AUTO), zzqVar);
                zzaz().zzg();
                zzfs zzfsVar = (zzfs) Preconditions.checkNotNull(this.zzm);
                String str8 = zzqVar.zza;
                if (str8 != null && !str8.isEmpty()) {
                    zzfsVar.f6742a.zzaz().zzg();
                    if (zzfsVar.a()) {
                        zzfr zzfrVar = new zzfr(zzfsVar, str8);
                        zzfsVar.f6742a.zzaz().zzg();
                        Intent intent = new Intent("com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE");
                        intent.setComponent(new ComponentName(str3, "com.google.android.finsky.externalreferrer.GetInstallReferrerService"));
                        PackageManager packageManager = zzfsVar.f6742a.zzau().getPackageManager();
                        if (packageManager == null) {
                            zzm = zzfsVar.f6742a.zzay().zzm();
                            str4 = "Failed to obtain Package Manager to verify binding conditions for Install Referrer";
                        } else {
                            List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
                            if (queryIntentServices != null && !queryIntentServices.isEmpty()) {
                                ServiceInfo serviceInfo = queryIntentServices.get(0).serviceInfo;
                                if (serviceInfo != null) {
                                    String str9 = serviceInfo.packageName;
                                    if (serviceInfo.name != null && str3.equals(str9) && zzfsVar.a()) {
                                        try {
                                            zzfsVar.f6742a.zzay().zzj().zzb("Install Referrer Service is", true != ConnectionTracker.getInstance().bindService(zzfsVar.f6742a.zzau(), new Intent(intent), zzfrVar, 1) ? "not available" : "available");
                                        } catch (RuntimeException e3) {
                                            zzfsVar.f6742a.zzay().zzd().zzb("Exception occurred while binding to Install Referrer Service", e3.getMessage());
                                        }
                                    } else {
                                        zzm = zzfsVar.f6742a.zzay().zzk();
                                        str4 = "Play Store version 8.3.73 or higher required for Install Referrer";
                                    }
                                }
                                zzaz().zzg();
                                b();
                                Bundle bundle322 = new Bundle();
                                bundle322.putLong("_c", 1L);
                                bundle322.putLong("_r", 1L);
                                bundle322.putLong("_uwa", 0L);
                                bundle322.putLong("_pfo", 0L);
                                String str1022 = str2;
                                bundle322.putLong(str1022, 0L);
                                String str1122 = str;
                                bundle322.putLong(str1122, 0L);
                                bundle322.putLong("_et", 1L);
                                if (zzqVar.zzp) {
                                    bundle322.putLong("_dac", 1L);
                                }
                                String str1222 = (String) Preconditions.checkNotNull(zzqVar.zza);
                                zzam zzamVar1022 = this.zze;
                                zzak(zzamVar1022);
                                Preconditions.checkNotEmpty(str1222);
                                zzamVar1022.zzg();
                                zzamVar1022.a();
                                o2 = zzamVar1022.o(str1222, "first_open_count");
                                if (this.zzn.zzau().getPackageManager() == null) {
                                    zzay().zzd().zzb("PackageManager is null, first open report might be inaccurate. appId", zzfa.g(str1222));
                                    str5 = "_pfo";
                                } else {
                                    try {
                                        packageInfo = Wrappers.packageManager(this.zzn.zzau()).getPackageInfo(str1222, 0);
                                    } catch (PackageManager.NameNotFoundException e4) {
                                        zzay().zzd().zzc("Package info is null, first open report might be inaccurate. appId", zzfa.g(str1222), e4);
                                        packageInfo = null;
                                    }
                                    if (packageInfo != null) {
                                        long j4 = packageInfo.firstInstallTime;
                                        str5 = "_pfo";
                                        if (j4 != 0) {
                                            if (j4 != packageInfo.lastUpdateTime) {
                                                if (!zzg().zzs(null, zzen.zzac)) {
                                                    bundle322.putLong("_uwa", 1L);
                                                } else if (o2 == 0) {
                                                    bundle322.putLong("_uwa", 1L);
                                                    z2 = false;
                                                    o2 = 0;
                                                }
                                                z2 = false;
                                            } else {
                                                z2 = true;
                                            }
                                            str6 = str1122;
                                            str7 = str1022;
                                            u(new zzlo("_fi", j2, Long.valueOf(true != z2 ? 0L : 1L), DebugKt.DEBUG_PROPERTY_VALUE_AUTO), zzqVar);
                                        } else {
                                            str7 = str1022;
                                            str6 = str1122;
                                        }
                                    } else {
                                        str5 = "_pfo";
                                        str6 = str1122;
                                        str7 = str1022;
                                    }
                                    try {
                                        applicationInfo = Wrappers.packageManager(this.zzn.zzau()).getApplicationInfo(str1222, 0);
                                    } catch (PackageManager.NameNotFoundException e5) {
                                        zzay().zzd().zzc("Application info is null, first open report might be inaccurate. appId", zzfa.g(str1222), e5);
                                        applicationInfo = null;
                                    }
                                    if (applicationInfo != null) {
                                        if ((applicationInfo.flags & 1) != 0) {
                                            bundle322.putLong(str7, 1L);
                                        }
                                        if ((applicationInfo.flags & 128) != 0) {
                                            bundle322.putLong(str6, 1L);
                                        }
                                    }
                                }
                                if (o2 >= 0) {
                                    bundle322.putLong(str5, o2);
                                }
                                f(new zzaw("_f", new zzau(bundle322), DebugKt.DEBUG_PROPERTY_VALUE_AUTO, j2), zzqVar);
                                zzam zzamVar9222222 = this.zze;
                                zzak(zzamVar9222222);
                                zzamVar9222222.zzC();
                                return;
                            }
                            zzm = zzfsVar.f6742a.zzay().zzi();
                            str4 = "Play Service for fetching Install Referrer is unavailable on device";
                        }
                    } else {
                        zzm = zzfsVar.f6742a.zzay().zzi();
                        str4 = "Install Referrer Reporter is not available";
                    }
                    zzm.zza(str4);
                    zzaz().zzg();
                    b();
                    Bundle bundle3222 = new Bundle();
                    bundle3222.putLong("_c", 1L);
                    bundle3222.putLong("_r", 1L);
                    bundle3222.putLong("_uwa", 0L);
                    bundle3222.putLong("_pfo", 0L);
                    String str10222 = str2;
                    bundle3222.putLong(str10222, 0L);
                    String str11222 = str;
                    bundle3222.putLong(str11222, 0L);
                    bundle3222.putLong("_et", 1L);
                    if (zzqVar.zzp) {
                    }
                    String str12222 = (String) Preconditions.checkNotNull(zzqVar.zza);
                    zzam zzamVar10222 = this.zze;
                    zzak(zzamVar10222);
                    Preconditions.checkNotEmpty(str12222);
                    zzamVar10222.zzg();
                    zzamVar10222.a();
                    o2 = zzamVar10222.o(str12222, "first_open_count");
                    if (this.zzn.zzau().getPackageManager() == null) {
                    }
                    if (o2 >= 0) {
                    }
                    f(new zzaw("_f", new zzau(bundle3222), DebugKt.DEBUG_PROPERTY_VALUE_AUTO, j2), zzqVar);
                    zzam zzamVar92222222 = this.zze;
                    zzak(zzamVar92222222);
                    zzamVar92222222.zzC();
                    return;
                }
                zzm = zzfsVar.f6742a.zzay().zzm();
                str4 = "Install Referrer Reporter was called with invalid app package name";
                zzm.zza(str4);
                zzaz().zzg();
                b();
                Bundle bundle32222 = new Bundle();
                bundle32222.putLong("_c", 1L);
                bundle32222.putLong("_r", 1L);
                bundle32222.putLong("_uwa", 0L);
                bundle32222.putLong("_pfo", 0L);
                String str102222 = str2;
                bundle32222.putLong(str102222, 0L);
                String str112222 = str;
                bundle32222.putLong(str112222, 0L);
                bundle32222.putLong("_et", 1L);
                if (zzqVar.zzp) {
                }
                String str122222 = (String) Preconditions.checkNotNull(zzqVar.zza);
                zzam zzamVar102222 = this.zze;
                zzak(zzamVar102222);
                Preconditions.checkNotEmpty(str122222);
                zzamVar102222.zzg();
                zzamVar102222.a();
                o2 = zzamVar102222.o(str122222, "first_open_count");
                if (this.zzn.zzau().getPackageManager() == null) {
                }
                if (o2 >= 0) {
                }
                f(new zzaw("_f", new zzau(bundle32222), DebugKt.DEBUG_PROPERTY_VALUE_AUTO, j2), zzqVar);
                zzam zzamVar922222222 = this.zze;
                zzak(zzamVar922222222);
                zzamVar922222222.zzC();
                return;
            }
            if (zzqVar.zzr != null) {
                str = "_sysu";
                str2 = "_sys";
                i2 = 1;
                zzlo zzloVar = new zzlo("_npa", j2, Long.valueOf(true != zzqVar.zzr.booleanValue() ? 0L : 1L), DebugKt.DEBUG_PROPERTY_VALUE_AUTO);
                if (zzp == null || !zzp.f7046e.equals(zzloVar.zzd)) {
                    u(zzloVar, zzqVar);
                }
            } else {
                str = "_sysu";
                str2 = "_sys";
                i2 = 1;
                if (zzp != null) {
                    o(new zzlo("_npa", j2, null, DebugKt.DEBUG_PROPERTY_VALUE_AUTO), zzqVar);
                }
            }
            zzam zzamVar622 = this.zze;
            zzak(zzamVar622);
            zzj = zzamVar622.zzj((String) Preconditions.checkNotNull(zzqVar.zza));
            if (zzj != null) {
                zzay().zzk().zzb("New GMP App Id passed in. Removing cached database data. appId", zzfa.g(zzj.zzt()));
                zzamVar = this.zze;
                zzak(zzamVar);
                zzt = zzj.zzt();
                zzamVar.a();
                zzamVar.zzg();
                Preconditions.checkNotEmpty(zzt);
                SQLiteDatabase p222 = zzamVar.p();
                String[] strArr22 = new String[i2];
                strArr22[0] = zzt;
                delete = p222.delete("events", "app_id=?", strArr22) + p222.delete("user_attributes", "app_id=?", strArr22) + p222.delete("conditional_properties", "app_id=?", strArr22) + p222.delete("apps", "app_id=?", strArr22) + p222.delete("raw_events", "app_id=?", strArr22) + p222.delete("raw_events_metadata", "app_id=?", strArr22) + p222.delete("event_filters", "app_id=?", strArr22) + p222.delete("property_filters", "app_id=?", strArr22) + p222.delete("audience_filter_values", "app_id=?", strArr22) + p222.delete("consent_settings", "app_id=?", strArr22);
                if (delete > 0) {
                }
                zzj = null;
            }
            if (zzj == null) {
            }
            B(zzqVar);
            if (i3 != 0) {
            }
            if (zzn == null) {
            }
        } finally {
            zzam zzamVar11 = this.zze;
            zzak(zzamVar11);
            zzamVar11.zzx();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void l() {
        this.zzr++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void m(zzac zzacVar) {
        zzq zzab = zzab((String) Preconditions.checkNotNull(zzacVar.zza));
        if (zzab != null) {
            n(zzacVar, zzab);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void n(zzac zzacVar, zzq zzqVar) {
        Preconditions.checkNotNull(zzacVar);
        Preconditions.checkNotEmpty(zzacVar.zza);
        Preconditions.checkNotNull(zzacVar.zzc);
        Preconditions.checkNotEmpty(zzacVar.zzc.zzb);
        zzaz().zzg();
        b();
        if (zzaj(zzqVar)) {
            if (!zzqVar.zzh) {
                B(zzqVar);
                return;
            }
            zzam zzamVar = this.zze;
            zzak(zzamVar);
            zzamVar.zzw();
            try {
                B(zzqVar);
                String str = (String) Preconditions.checkNotNull(zzacVar.zza);
                zzam zzamVar2 = this.zze;
                zzak(zzamVar2);
                zzac zzk = zzamVar2.zzk(str, zzacVar.zzc.zzb);
                if (zzk != null) {
                    zzay().zzc().zzc("Removing conditional user property", zzacVar.zza, this.zzn.zzj().f(zzacVar.zzc.zzb));
                    zzam zzamVar3 = this.zze;
                    zzak(zzamVar3);
                    zzamVar3.zza(str, zzacVar.zzc.zzb);
                    if (zzk.zze) {
                        zzam zzamVar4 = this.zze;
                        zzak(zzamVar4);
                        zzamVar4.zzA(str, zzacVar.zzc.zzb);
                    }
                    zzaw zzawVar = zzacVar.zzk;
                    if (zzawVar != null) {
                        zzau zzauVar = zzawVar.zzb;
                        w((zzaw) Preconditions.checkNotNull(zzv().R(str, ((zzaw) Preconditions.checkNotNull(zzacVar.zzk)).zza, zzauVar != null ? zzauVar.zzc() : null, zzk.zzb, zzacVar.zzk.zzd, true, true)), zzqVar);
                    }
                } else {
                    zzay().zzk().zzc("Conditional user property doesn't exist", zzfa.g(zzacVar.zza), this.zzn.zzj().f(zzacVar.zzc.zzb));
                }
                zzam zzamVar5 = this.zze;
                zzak(zzamVar5);
                zzamVar5.zzC();
            } finally {
                zzam zzamVar6 = this.zze;
                zzak(zzamVar6);
                zzamVar6.zzx();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void o(zzlo zzloVar, zzq zzqVar) {
        zzaz().zzg();
        b();
        if (zzaj(zzqVar)) {
            if (!zzqVar.zzh) {
                B(zzqVar);
            } else if ("_npa".equals(zzloVar.zzb) && zzqVar.zzr != null) {
                zzay().zzc().zza("Falling back to manifest metadata value for ad personalization");
                u(new zzlo("_npa", zzav().currentTimeMillis(), Long.valueOf(true != zzqVar.zzr.booleanValue() ? 0L : 1L), DebugKt.DEBUG_PROPERTY_VALUE_AUTO), zzqVar);
            } else {
                zzay().zzc().zzb("Removing user property", this.zzn.zzj().f(zzloVar.zzb));
                zzam zzamVar = this.zze;
                zzak(zzamVar);
                zzamVar.zzw();
                try {
                    B(zzqVar);
                    zzne.zzc();
                    if (this.zzn.zzf().zzs(null, zzen.zzan) && this.zzn.zzf().zzs(null, zzen.zzap) && "_id".equals(zzloVar.zzb)) {
                        zzam zzamVar2 = this.zze;
                        zzak(zzamVar2);
                        zzamVar2.zzA((String) Preconditions.checkNotNull(zzqVar.zza), "_lair");
                    }
                    zzam zzamVar3 = this.zze;
                    zzak(zzamVar3);
                    zzamVar3.zzA((String) Preconditions.checkNotNull(zzqVar.zza), zzloVar.zzb);
                    zzam zzamVar4 = this.zze;
                    zzak(zzamVar4);
                    zzamVar4.zzC();
                    zzay().zzc().zzb("User property removed", this.zzn.zzj().f(zzloVar.zzb));
                } finally {
                    zzam zzamVar5 = this.zze;
                    zzak(zzamVar5);
                    zzamVar5.zzx();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    @WorkerThread
    public final void p(zzq zzqVar) {
        if (this.zzy != null) {
            ArrayList arrayList = new ArrayList();
            this.zzz = arrayList;
            arrayList.addAll(this.zzy);
        }
        zzam zzamVar = this.zze;
        zzak(zzamVar);
        String str = (String) Preconditions.checkNotNull(zzqVar.zza);
        Preconditions.checkNotEmpty(str);
        zzamVar.zzg();
        zzamVar.a();
        try {
            SQLiteDatabase p2 = zzamVar.p();
            String[] strArr = {str};
            int delete = p2.delete("apps", "app_id=?", strArr) + p2.delete("events", "app_id=?", strArr) + p2.delete("user_attributes", "app_id=?", strArr) + p2.delete("conditional_properties", "app_id=?", strArr) + p2.delete("raw_events", "app_id=?", strArr) + p2.delete("raw_events_metadata", "app_id=?", strArr) + p2.delete("queue", "app_id=?", strArr) + p2.delete("audience_filter_values", "app_id=?", strArr) + p2.delete("main_event_params", "app_id=?", strArr) + p2.delete("default_event_params", "app_id=?", strArr);
            if (delete > 0) {
                zzamVar.f6809a.zzay().zzj().zzc("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e2) {
            zzamVar.f6809a.zzay().zzd().zzc("Error resetting analytics data. appId, error", zzfa.g(str), e2);
        }
        if (zzqVar.zzh) {
            k(zzqVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void q() {
        zzaz().zzg();
        zzam zzamVar = this.zze;
        zzak(zzamVar);
        zzamVar.zzz();
        if (this.zzk.zzc.zza() == 0) {
            this.zzk.zzc.zzb(zzav().currentTimeMillis());
        }
        zzaf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void r(zzac zzacVar) {
        zzq zzab = zzab((String) Preconditions.checkNotNull(zzacVar.zza));
        if (zzab != null) {
            s(zzacVar, zzab);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void s(zzac zzacVar, zzq zzqVar) {
        zzey zzd;
        String str;
        Object g2;
        String f2;
        Object zza;
        zzey zzd2;
        String str2;
        Object g3;
        String f3;
        Object obj;
        Preconditions.checkNotNull(zzacVar);
        Preconditions.checkNotEmpty(zzacVar.zza);
        Preconditions.checkNotNull(zzacVar.zzb);
        Preconditions.checkNotNull(zzacVar.zzc);
        Preconditions.checkNotEmpty(zzacVar.zzc.zzb);
        zzaz().zzg();
        b();
        if (zzaj(zzqVar)) {
            if (!zzqVar.zzh) {
                B(zzqVar);
                return;
            }
            zzac zzacVar2 = new zzac(zzacVar);
            boolean z = false;
            zzacVar2.zze = false;
            zzam zzamVar = this.zze;
            zzak(zzamVar);
            zzamVar.zzw();
            try {
                zzam zzamVar2 = this.zze;
                zzak(zzamVar2);
                zzac zzk = zzamVar2.zzk((String) Preconditions.checkNotNull(zzacVar2.zza), zzacVar2.zzc.zzb);
                if (zzk != null && !zzk.zzb.equals(zzacVar2.zzb)) {
                    zzay().zzk().zzd("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzn.zzj().f(zzacVar2.zzc.zzb), zzacVar2.zzb, zzk.zzb);
                }
                if (zzk != null && zzk.zze) {
                    zzacVar2.zzb = zzk.zzb;
                    zzacVar2.zzd = zzk.zzd;
                    zzacVar2.zzh = zzk.zzh;
                    zzacVar2.zzf = zzk.zzf;
                    zzacVar2.zzi = zzk.zzi;
                    zzacVar2.zze = true;
                    zzlo zzloVar = zzacVar2.zzc;
                    zzacVar2.zzc = new zzlo(zzloVar.zzb, zzk.zzc.zzc, zzloVar.zza(), zzk.zzc.zzf);
                } else if (TextUtils.isEmpty(zzacVar2.zzf)) {
                    zzlo zzloVar2 = zzacVar2.zzc;
                    zzacVar2.zzc = new zzlo(zzloVar2.zzb, zzacVar2.zzd, zzloVar2.zza(), zzacVar2.zzc.zzf);
                    zzacVar2.zze = true;
                    z = true;
                }
                if (zzacVar2.zze) {
                    zzlo zzloVar3 = zzacVar2.zzc;
                    zzlq zzlqVar = new zzlq((String) Preconditions.checkNotNull(zzacVar2.zza), zzacVar2.zzb, zzloVar3.zzb, zzloVar3.zzc, Preconditions.checkNotNull(zzloVar3.zza()));
                    zzam zzamVar3 = this.zze;
                    zzak(zzamVar3);
                    if (zzamVar3.zzL(zzlqVar)) {
                        zzd2 = zzay().zzc();
                        str2 = "User property updated immediately";
                        g3 = zzacVar2.zza;
                        f3 = this.zzn.zzj().f(zzlqVar.f7044c);
                        obj = zzlqVar.f7046e;
                    } else {
                        zzd2 = zzay().zzd();
                        str2 = "(2)Too many active user properties, ignoring";
                        g3 = zzfa.g(zzacVar2.zza);
                        f3 = this.zzn.zzj().f(zzlqVar.f7044c);
                        obj = zzlqVar.f7046e;
                    }
                    zzd2.zzd(str2, g3, f3, obj);
                    if (z && zzacVar2.zzi != null) {
                        w(new zzaw(zzacVar2.zzi, zzacVar2.zzd), zzqVar);
                    }
                }
                zzam zzamVar4 = this.zze;
                zzak(zzamVar4);
                if (zzamVar4.zzK(zzacVar2)) {
                    zzd = zzay().zzc();
                    str = "Conditional property added";
                    g2 = zzacVar2.zza;
                    f2 = this.zzn.zzj().f(zzacVar2.zzc.zzb);
                    zza = zzacVar2.zzc.zza();
                } else {
                    zzd = zzay().zzd();
                    str = "Too many conditional properties, ignoring";
                    g2 = zzfa.g(zzacVar2.zza);
                    f2 = this.zzn.zzj().f(zzacVar2.zzc.zzb);
                    zza = zzacVar2.zzc.zza();
                }
                zzd.zzd(str, g2, f2, zza);
                zzam zzamVar5 = this.zze;
                zzak(zzamVar5);
                zzamVar5.zzC();
            } finally {
                zzam zzamVar6 = this.zze;
                zzak(zzamVar6);
                zzamVar6.zzx();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void t(String str, zzai zzaiVar) {
        zzaz().zzg();
        b();
        this.zzB.put(str, zzaiVar);
        zzam zzamVar = this.zze;
        zzak(zzamVar);
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzaiVar);
        zzamVar.zzg();
        zzamVar.a();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("consent_state", zzaiVar.zzh());
        try {
            if (zzamVar.p().insertWithOnConflict("consent_settings", null, contentValues, 5) == -1) {
                zzamVar.f6809a.zzay().zzd().zzb("Failed to insert/update consent setting (got -1). appId", zzfa.g(str));
            }
        } catch (SQLiteException e2) {
            zzamVar.f6809a.zzay().zzd().zzc("Error storing consent setting. appId, error", zzfa.g(str), e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void u(zzlo zzloVar, zzq zzqVar) {
        zzam zzamVar;
        String str;
        long j2;
        zzaz().zzg();
        b();
        if (zzaj(zzqVar)) {
            if (!zzqVar.zzh) {
                B(zzqVar);
                return;
            }
            int M = zzv().M(zzloVar.zzb);
            int i2 = 0;
            if (M != 0) {
                zzlt zzv = zzv();
                String str2 = zzloVar.zzb;
                zzg();
                String zzD = zzv.zzD(str2, 24, true);
                String str3 = zzloVar.zzb;
                zzv().n(this.zzF, zzqVar.zza, M, "_ev", zzD, str3 != null ? str3.length() : 0);
                return;
            }
            int J = zzv().J(zzloVar.zzb, zzloVar.zza());
            if (J != 0) {
                zzlt zzv2 = zzv();
                String str4 = zzloVar.zzb;
                zzg();
                String zzD2 = zzv2.zzD(str4, 24, true);
                Object zza = zzloVar.zza();
                if (zza != null && ((zza instanceof String) || (zza instanceof CharSequence))) {
                    i2 = zza.toString().length();
                }
                zzv().n(this.zzF, zzqVar.zza, J, "_ev", zzD2, i2);
                return;
            }
            Object f2 = zzv().f(zzloVar.zzb, zzloVar.zza());
            if (f2 == null) {
                return;
            }
            if ("_sid".equals(zzloVar.zzb)) {
                long j3 = zzloVar.zzc;
                String str5 = zzloVar.zzf;
                String str6 = (String) Preconditions.checkNotNull(zzqVar.zza);
                zzam zzamVar2 = this.zze;
                zzak(zzamVar2);
                zzlq zzp = zzamVar2.zzp(str6, "_sno");
                if (zzp != null) {
                    Object obj = zzp.f7046e;
                    if (obj instanceof Long) {
                        j2 = ((Long) obj).longValue();
                        u(new zzlo("_sno", j3, Long.valueOf(j2 + 1), str5), zzqVar);
                    }
                }
                if (zzp != null) {
                    zzay().zzk().zzb("Retrieved last session number from database does not contain a valid (long) value", zzp.f7046e);
                }
                zzam zzamVar3 = this.zze;
                zzak(zzamVar3);
                zzas zzn = zzamVar3.zzn(str6, "_s");
                if (zzn != null) {
                    j2 = zzn.f6700c;
                    zzay().zzj().zzb("Backfill the session number. Last used session number", Long.valueOf(j2));
                } else {
                    j2 = 0;
                }
                u(new zzlo("_sno", j3, Long.valueOf(j2 + 1), str5), zzqVar);
            }
            zzlq zzlqVar = new zzlq((String) Preconditions.checkNotNull(zzqVar.zza), (String) Preconditions.checkNotNull(zzloVar.zzf), zzloVar.zzb, zzloVar.zzc, f2);
            zzay().zzj().zzc("Setting user property", this.zzn.zzj().f(zzlqVar.f7044c), f2);
            zzam zzamVar4 = this.zze;
            zzak(zzamVar4);
            zzamVar4.zzw();
            try {
                zzne.zzc();
                if (this.zzn.zzf().zzs(null, zzen.zzan) && "_id".equals(zzlqVar.f7044c)) {
                    if (this.zzn.zzf().zzs(null, zzen.zzaq)) {
                        zzam zzamVar5 = this.zze;
                        zzak(zzamVar5);
                        zzlq zzp2 = zzamVar5.zzp(zzqVar.zza, "_id");
                        if (zzp2 != null && !zzlqVar.f7046e.equals(zzp2.f7046e)) {
                            zzamVar = this.zze;
                            zzak(zzamVar);
                            str = zzqVar.zza;
                        }
                    } else {
                        zzamVar = this.zze;
                        zzak(zzamVar);
                        str = zzqVar.zza;
                    }
                    zzamVar.zzA(str, "_lair");
                }
                B(zzqVar);
                zzam zzamVar6 = this.zze;
                zzak(zzamVar6);
                boolean zzL = zzamVar6.zzL(zzlqVar);
                zzam zzamVar7 = this.zze;
                zzak(zzamVar7);
                zzamVar7.zzC();
                if (!zzL) {
                    zzay().zzd().zzc("Too many unique user properties are set. Ignoring user property", this.zzn.zzj().f(zzlqVar.f7044c), zzlqVar.f7046e);
                    zzv().n(this.zzF, zzqVar.zza, 9, null, null, 0);
                }
            } finally {
                zzam zzamVar8 = this.zze;
                zzak(zzamVar8);
                zzamVar8.zzx();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x02fc, code lost:
        r8 = r8.subList(0, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x0301, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0302, code lost:
        r2 = r0;
        r3 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:279:0x068c, code lost:
        if (r12 == null) goto L306;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0118, code lost:
        if (r11 == null) goto L44;
     */
    /* JADX WARN: Not initialized variable reg: 12, insn: 0x06ab: MOVE  (r10 I:??[OBJECT, ARRAY]) = (r12 I:??[OBJECT, ARRAY]), block:B:289:0x06aa */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0261 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:133:0x029a A[Catch: all -> 0x06b2, TRY_ENTER, TryCatch #13 {all -> 0x06b2, blocks: (B:134:0x029d, B:136:0x02a3, B:138:0x02af, B:139:0x02b3, B:141:0x02b9, B:143:0x02cd, B:147:0x02d6, B:149:0x02dc, B:152:0x02f1, B:160:0x0309, B:162:0x0324, B:166:0x0333, B:168:0x0358, B:172:0x0392, B:174:0x0397, B:176:0x039f, B:177:0x03a2, B:179:0x03b3, B:181:0x03be, B:182:0x03c1, B:184:0x03cd, B:186:0x03d8, B:187:0x03db, B:189:0x03e6, B:190:0x03e9, B:192:0x03f5, B:194:0x0400, B:196:0x0409, B:197:0x040c, B:199:0x0418, B:201:0x0423, B:202:0x0426, B:204:0x0432, B:206:0x043d, B:208:0x044c, B:210:0x0456, B:215:0x0480, B:216:0x048b, B:218:0x0496, B:220:0x04a2, B:222:0x04ad, B:224:0x04b2, B:225:0x04b5, B:227:0x04c1, B:228:0x04d7, B:231:0x04ec, B:233:0x04fd, B:235:0x050f, B:237:0x0531, B:239:0x0542, B:244:0x0589, B:246:0x059b, B:248:0x05b0, B:252:0x05c0, B:253:0x05c4, B:247:0x05a9, B:255:0x0609, B:240:0x0577, B:241:0x057e, B:242:0x0581, B:120:0x026b, B:133:0x029a, B:259:0x0621, B:260:0x0624, B:261:0x0625, B:266:0x0666, B:282:0x0690, B:284:0x0696, B:286:0x06a1, B:270:0x066f, B:291:0x06ae, B:292:0x06b1, B:251:0x05bc), top: B:317:0x00da, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:136:0x02a3 A[Catch: all -> 0x06b2, TryCatch #13 {all -> 0x06b2, blocks: (B:134:0x029d, B:136:0x02a3, B:138:0x02af, B:139:0x02b3, B:141:0x02b9, B:143:0x02cd, B:147:0x02d6, B:149:0x02dc, B:152:0x02f1, B:160:0x0309, B:162:0x0324, B:166:0x0333, B:168:0x0358, B:172:0x0392, B:174:0x0397, B:176:0x039f, B:177:0x03a2, B:179:0x03b3, B:181:0x03be, B:182:0x03c1, B:184:0x03cd, B:186:0x03d8, B:187:0x03db, B:189:0x03e6, B:190:0x03e9, B:192:0x03f5, B:194:0x0400, B:196:0x0409, B:197:0x040c, B:199:0x0418, B:201:0x0423, B:202:0x0426, B:204:0x0432, B:206:0x043d, B:208:0x044c, B:210:0x0456, B:215:0x0480, B:216:0x048b, B:218:0x0496, B:220:0x04a2, B:222:0x04ad, B:224:0x04b2, B:225:0x04b5, B:227:0x04c1, B:228:0x04d7, B:231:0x04ec, B:233:0x04fd, B:235:0x050f, B:237:0x0531, B:239:0x0542, B:244:0x0589, B:246:0x059b, B:248:0x05b0, B:252:0x05c0, B:253:0x05c4, B:247:0x05a9, B:255:0x0609, B:240:0x0577, B:241:0x057e, B:242:0x0581, B:120:0x026b, B:133:0x029a, B:259:0x0621, B:260:0x0624, B:261:0x0625, B:266:0x0666, B:282:0x0690, B:284:0x0696, B:286:0x06a1, B:270:0x066f, B:291:0x06ae, B:292:0x06b1, B:251:0x05bc), top: B:317:0x00da, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:220:0x04a2 A[Catch: all -> 0x06b2, TryCatch #13 {all -> 0x06b2, blocks: (B:134:0x029d, B:136:0x02a3, B:138:0x02af, B:139:0x02b3, B:141:0x02b9, B:143:0x02cd, B:147:0x02d6, B:149:0x02dc, B:152:0x02f1, B:160:0x0309, B:162:0x0324, B:166:0x0333, B:168:0x0358, B:172:0x0392, B:174:0x0397, B:176:0x039f, B:177:0x03a2, B:179:0x03b3, B:181:0x03be, B:182:0x03c1, B:184:0x03cd, B:186:0x03d8, B:187:0x03db, B:189:0x03e6, B:190:0x03e9, B:192:0x03f5, B:194:0x0400, B:196:0x0409, B:197:0x040c, B:199:0x0418, B:201:0x0423, B:202:0x0426, B:204:0x0432, B:206:0x043d, B:208:0x044c, B:210:0x0456, B:215:0x0480, B:216:0x048b, B:218:0x0496, B:220:0x04a2, B:222:0x04ad, B:224:0x04b2, B:225:0x04b5, B:227:0x04c1, B:228:0x04d7, B:231:0x04ec, B:233:0x04fd, B:235:0x050f, B:237:0x0531, B:239:0x0542, B:244:0x0589, B:246:0x059b, B:248:0x05b0, B:252:0x05c0, B:253:0x05c4, B:247:0x05a9, B:255:0x0609, B:240:0x0577, B:241:0x057e, B:242:0x0581, B:120:0x026b, B:133:0x029a, B:259:0x0621, B:260:0x0624, B:261:0x0625, B:266:0x0666, B:282:0x0690, B:284:0x0696, B:286:0x06a1, B:270:0x066f, B:291:0x06ae, B:292:0x06b1, B:251:0x05bc), top: B:317:0x00da, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:224:0x04b2 A[Catch: all -> 0x06b2, TryCatch #13 {all -> 0x06b2, blocks: (B:134:0x029d, B:136:0x02a3, B:138:0x02af, B:139:0x02b3, B:141:0x02b9, B:143:0x02cd, B:147:0x02d6, B:149:0x02dc, B:152:0x02f1, B:160:0x0309, B:162:0x0324, B:166:0x0333, B:168:0x0358, B:172:0x0392, B:174:0x0397, B:176:0x039f, B:177:0x03a2, B:179:0x03b3, B:181:0x03be, B:182:0x03c1, B:184:0x03cd, B:186:0x03d8, B:187:0x03db, B:189:0x03e6, B:190:0x03e9, B:192:0x03f5, B:194:0x0400, B:196:0x0409, B:197:0x040c, B:199:0x0418, B:201:0x0423, B:202:0x0426, B:204:0x0432, B:206:0x043d, B:208:0x044c, B:210:0x0456, B:215:0x0480, B:216:0x048b, B:218:0x0496, B:220:0x04a2, B:222:0x04ad, B:224:0x04b2, B:225:0x04b5, B:227:0x04c1, B:228:0x04d7, B:231:0x04ec, B:233:0x04fd, B:235:0x050f, B:237:0x0531, B:239:0x0542, B:244:0x0589, B:246:0x059b, B:248:0x05b0, B:252:0x05c0, B:253:0x05c4, B:247:0x05a9, B:255:0x0609, B:240:0x0577, B:241:0x057e, B:242:0x0581, B:120:0x026b, B:133:0x029a, B:259:0x0621, B:260:0x0624, B:261:0x0625, B:266:0x0666, B:282:0x0690, B:284:0x0696, B:286:0x06a1, B:270:0x066f, B:291:0x06ae, B:292:0x06b1, B:251:0x05bc), top: B:317:0x00da, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:227:0x04c1 A[Catch: all -> 0x06b2, TryCatch #13 {all -> 0x06b2, blocks: (B:134:0x029d, B:136:0x02a3, B:138:0x02af, B:139:0x02b3, B:141:0x02b9, B:143:0x02cd, B:147:0x02d6, B:149:0x02dc, B:152:0x02f1, B:160:0x0309, B:162:0x0324, B:166:0x0333, B:168:0x0358, B:172:0x0392, B:174:0x0397, B:176:0x039f, B:177:0x03a2, B:179:0x03b3, B:181:0x03be, B:182:0x03c1, B:184:0x03cd, B:186:0x03d8, B:187:0x03db, B:189:0x03e6, B:190:0x03e9, B:192:0x03f5, B:194:0x0400, B:196:0x0409, B:197:0x040c, B:199:0x0418, B:201:0x0423, B:202:0x0426, B:204:0x0432, B:206:0x043d, B:208:0x044c, B:210:0x0456, B:215:0x0480, B:216:0x048b, B:218:0x0496, B:220:0x04a2, B:222:0x04ad, B:224:0x04b2, B:225:0x04b5, B:227:0x04c1, B:228:0x04d7, B:231:0x04ec, B:233:0x04fd, B:235:0x050f, B:237:0x0531, B:239:0x0542, B:244:0x0589, B:246:0x059b, B:248:0x05b0, B:252:0x05c0, B:253:0x05c4, B:247:0x05a9, B:255:0x0609, B:240:0x0577, B:241:0x057e, B:242:0x0581, B:120:0x026b, B:133:0x029a, B:259:0x0621, B:260:0x0624, B:261:0x0625, B:266:0x0666, B:282:0x0690, B:284:0x0696, B:286:0x06a1, B:270:0x066f, B:291:0x06ae, B:292:0x06b1, B:251:0x05bc), top: B:317:0x00da, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:246:0x059b A[Catch: MalformedURLException -> 0x0609, all -> 0x06b2, TryCatch #0 {MalformedURLException -> 0x0609, blocks: (B:244:0x0589, B:246:0x059b, B:248:0x05b0, B:251:0x05bc, B:252:0x05c0, B:253:0x05c4, B:247:0x05a9), top: B:299:0x0589, outer: #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:247:0x05a9 A[Catch: MalformedURLException -> 0x0609, all -> 0x06b2, TryCatch #0 {MalformedURLException -> 0x0609, blocks: (B:244:0x0589, B:246:0x059b, B:248:0x05b0, B:251:0x05bc, B:252:0x05c0, B:253:0x05c4, B:247:0x05a9), top: B:299:0x0589, outer: #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:250:0x05bb  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0621 A[Catch: all -> 0x06b2, TryCatch #13 {all -> 0x06b2, blocks: (B:134:0x029d, B:136:0x02a3, B:138:0x02af, B:139:0x02b3, B:141:0x02b9, B:143:0x02cd, B:147:0x02d6, B:149:0x02dc, B:152:0x02f1, B:160:0x0309, B:162:0x0324, B:166:0x0333, B:168:0x0358, B:172:0x0392, B:174:0x0397, B:176:0x039f, B:177:0x03a2, B:179:0x03b3, B:181:0x03be, B:182:0x03c1, B:184:0x03cd, B:186:0x03d8, B:187:0x03db, B:189:0x03e6, B:190:0x03e9, B:192:0x03f5, B:194:0x0400, B:196:0x0409, B:197:0x040c, B:199:0x0418, B:201:0x0423, B:202:0x0426, B:204:0x0432, B:206:0x043d, B:208:0x044c, B:210:0x0456, B:215:0x0480, B:216:0x048b, B:218:0x0496, B:220:0x04a2, B:222:0x04ad, B:224:0x04b2, B:225:0x04b5, B:227:0x04c1, B:228:0x04d7, B:231:0x04ec, B:233:0x04fd, B:235:0x050f, B:237:0x0531, B:239:0x0542, B:244:0x0589, B:246:0x059b, B:248:0x05b0, B:252:0x05c0, B:253:0x05c4, B:247:0x05a9, B:255:0x0609, B:240:0x0577, B:241:0x057e, B:242:0x0581, B:120:0x026b, B:133:0x029a, B:259:0x0621, B:260:0x0624, B:261:0x0625, B:266:0x0666, B:282:0x0690, B:284:0x0696, B:286:0x06a1, B:270:0x066f, B:291:0x06ae, B:292:0x06b1, B:251:0x05bc), top: B:317:0x00da, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0696 A[Catch: all -> 0x06b2, TryCatch #13 {all -> 0x06b2, blocks: (B:134:0x029d, B:136:0x02a3, B:138:0x02af, B:139:0x02b3, B:141:0x02b9, B:143:0x02cd, B:147:0x02d6, B:149:0x02dc, B:152:0x02f1, B:160:0x0309, B:162:0x0324, B:166:0x0333, B:168:0x0358, B:172:0x0392, B:174:0x0397, B:176:0x039f, B:177:0x03a2, B:179:0x03b3, B:181:0x03be, B:182:0x03c1, B:184:0x03cd, B:186:0x03d8, B:187:0x03db, B:189:0x03e6, B:190:0x03e9, B:192:0x03f5, B:194:0x0400, B:196:0x0409, B:197:0x040c, B:199:0x0418, B:201:0x0423, B:202:0x0426, B:204:0x0432, B:206:0x043d, B:208:0x044c, B:210:0x0456, B:215:0x0480, B:216:0x048b, B:218:0x0496, B:220:0x04a2, B:222:0x04ad, B:224:0x04b2, B:225:0x04b5, B:227:0x04c1, B:228:0x04d7, B:231:0x04ec, B:233:0x04fd, B:235:0x050f, B:237:0x0531, B:239:0x0542, B:244:0x0589, B:246:0x059b, B:248:0x05b0, B:252:0x05c0, B:253:0x05c4, B:247:0x05a9, B:255:0x0609, B:240:0x0577, B:241:0x057e, B:242:0x0581, B:120:0x026b, B:133:0x029a, B:259:0x0621, B:260:0x0624, B:261:0x0625, B:266:0x0666, B:282:0x0690, B:284:0x0696, B:286:0x06a1, B:270:0x066f, B:291:0x06ae, B:292:0x06b1, B:251:0x05bc), top: B:317:0x00da, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:336:0x026b A[ADDED_TO_REGION, EDGE_INSN: B:336:0x026b->B:120:0x026b ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:348:0x04d7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0123 A[Catch: all -> 0x06b6, TryCatch #17 {all -> 0x06b6, blocks: (B:3:0x0010, B:5:0x0021, B:6:0x002b, B:10:0x0034, B:12:0x003a, B:13:0x0045, B:15:0x004d, B:16:0x0051, B:18:0x005c, B:19:0x0067, B:21:0x0072, B:22:0x0080, B:24:0x009f, B:26:0x00a5, B:27:0x00a8, B:29:0x00b4, B:30:0x00cb, B:32:0x00dc, B:34:0x00e2, B:38:0x00f7, B:51:0x011b, B:55:0x0123, B:56:0x0126, B:57:0x0127, B:61:0x014f, B:65:0x0157, B:70:0x0190, B:251:0x05bc), top: B:320:0x0010 }] */
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void v() {
        Throwable th;
        Throwable th2;
        Cursor cursor;
        Cursor cursor2;
        zzam zzamVar;
        long zzz;
        SQLiteException sQLiteException;
        Cursor cursor3;
        String str;
        Throwable th3;
        Cursor cursor4;
        long j2;
        SQLiteException sQLiteException2;
        Cursor cursor5;
        List emptyList;
        String str2;
        Object zza;
        String str3;
        boolean z;
        List list;
        boolean z2;
        zzlk zzlkVar;
        int g2;
        String str4;
        IOException iOException;
        zzey zzd;
        String str5;
        Object g3;
        zzln zzlnVar;
        ByteArrayInputStream byteArrayInputStream;
        GZIPInputStream gZIPInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th4;
        SQLiteException sQLiteException3;
        Cursor cursor6;
        zzey zzj;
        String str6;
        zzaz().zzg();
        b();
        int i2 = 1;
        this.zzv = true;
        boolean z3 = false;
        int i3 = 0;
        try {
            this.zzn.zzaw();
            Boolean p2 = this.zzn.zzt().p();
            if (p2 == null) {
                zzj = zzay().zzk();
                str6 = "Upload data called on the client side before use of service was decided";
            } else if (!p2.booleanValue()) {
                if (this.f7040a <= 0) {
                    zzaz().zzg();
                    if (this.zzy != null) {
                        zzj = zzay().zzj();
                        str6 = "Uploading requested multiple times";
                    } else {
                        zzfg zzfgVar = this.zzd;
                        zzak(zzfgVar);
                        if (zzfgVar.zza()) {
                            long currentTimeMillis = zzav().currentTimeMillis();
                            Cursor cursor7 = null;
                            int zze = zzg().zze(null, zzen.zzP);
                            zzg();
                            long zzz2 = currentTimeMillis - zzag.zzz();
                            for (int i4 = 0; i4 < zze && zzag(null, zzz2); i4++) {
                            }
                            long zza2 = this.zzk.zzc.zza();
                            if (zza2 != 0) {
                                zzay().zzc().zzb("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(currentTimeMillis - zza2)));
                            }
                            zzam zzamVar2 = this.zze;
                            zzak(zzamVar2);
                            String zzr = zzamVar2.zzr();
                            long j3 = -1;
                            try {
                                if (TextUtils.isEmpty(zzr)) {
                                    try {
                                        this.zzA = -1L;
                                        zzamVar = this.zze;
                                        zzak(zzamVar);
                                        zzg();
                                        zzz = currentTimeMillis - zzag.zzz();
                                        zzamVar.zzg();
                                        zzamVar.a();
                                    } catch (Throwable th5) {
                                        th2 = th5;
                                        cursor2 = cursor;
                                    }
                                    try {
                                        cursor3 = zzamVar.p().rawQuery("select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;", new String[]{String.valueOf(zzz)});
                                        try {
                                        } catch (SQLiteException e2) {
                                            sQLiteException = e2;
                                            zzamVar.f6809a.zzay().zzd().zzb("Error selecting expired configs", sQLiteException);
                                        }
                                    } catch (SQLiteException e3) {
                                        sQLiteException = e3;
                                        cursor3 = null;
                                    } catch (Throwable th6) {
                                        th2 = th6;
                                        cursor2 = null;
                                        if (cursor2 != null) {
                                            cursor2.close();
                                        }
                                        throw th2;
                                    }
                                    if (cursor3.moveToFirst()) {
                                        str = cursor3.getString(0);
                                        cursor3.close();
                                        if (!TextUtils.isEmpty(str)) {
                                        }
                                    } else {
                                        zzamVar.f6809a.zzay().zzj().zza("No expired configs for apps with pending events");
                                        cursor3.close();
                                        str = null;
                                        if (!TextUtils.isEmpty(str)) {
                                            zzam zzamVar3 = this.zze;
                                            zzak(zzamVar3);
                                            zzh zzj2 = zzamVar3.zzj(str);
                                            if (zzj2 != null) {
                                                c(zzj2);
                                            }
                                        }
                                    }
                                } else {
                                    if (this.zzA == -1) {
                                        zzam zzamVar4 = this.zze;
                                        zzak(zzamVar4);
                                        try {
                                            cursor6 = zzamVar4.p().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
                                            try {
                                                try {
                                                    if (cursor6.moveToFirst()) {
                                                        j3 = cursor6.getLong(0);
                                                    }
                                                } catch (SQLiteException e4) {
                                                    sQLiteException3 = e4;
                                                    zzamVar4.f6809a.zzay().zzd().zzb("Error querying raw events", sQLiteException3);
                                                }
                                            } catch (Throwable th7) {
                                                th4 = th7;
                                                cursor7 = cursor6;
                                                if (cursor7 != null) {
                                                    cursor7.close();
                                                }
                                                throw th4;
                                            }
                                        } catch (SQLiteException e5) {
                                            sQLiteException3 = e5;
                                            cursor6 = null;
                                        } catch (Throwable th8) {
                                            th4 = th8;
                                            if (cursor7 != null) {
                                            }
                                            throw th4;
                                        }
                                        cursor6.close();
                                        this.zzA = j3;
                                    }
                                    int zze2 = zzg().zze(zzr, zzen.zzf);
                                    int max = Math.max(0, zzg().zze(zzr, zzen.zzg));
                                    zzam zzamVar5 = this.zze;
                                    zzak(zzamVar5);
                                    zzamVar5.zzg();
                                    zzamVar5.a();
                                    Preconditions.checkArgument(zze2 > 0);
                                    Preconditions.checkArgument(max > 0);
                                    Preconditions.checkNotEmpty(zzr);
                                    try {
                                        cursor5 = zzamVar5.p().query("queue", new String[]{"rowid", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "retry_count"}, "app_id=?", new String[]{zzr}, null, null, "rowid", String.valueOf(zze2));
                                        try {
                                            try {
                                                if (cursor5.moveToFirst()) {
                                                    ArrayList arrayList = new ArrayList();
                                                    int i5 = 0;
                                                    while (true) {
                                                        long j4 = cursor5.getLong(i3);
                                                        try {
                                                            byte[] blob = cursor5.getBlob(i2);
                                                            zzlnVar = zzamVar5.f7018b.zzi;
                                                            zzak(zzlnVar);
                                                            try {
                                                                byteArrayInputStream = new ByteArrayInputStream(blob);
                                                                gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
                                                                byteArrayOutputStream = new ByteArrayOutputStream();
                                                                j2 = currentTimeMillis;
                                                            } catch (IOException e6) {
                                                                e = e6;
                                                                j2 = currentTimeMillis;
                                                            }
                                                        } catch (IOException e7) {
                                                            e = e7;
                                                            j2 = currentTimeMillis;
                                                        }
                                                        try {
                                                            try {
                                                                byte[] bArr = new byte[1024];
                                                                while (true) {
                                                                    int read = gZIPInputStream.read(bArr);
                                                                    if (read <= 0) {
                                                                        break;
                                                                    }
                                                                    ByteArrayInputStream byteArrayInputStream2 = byteArrayInputStream;
                                                                    byteArrayOutputStream.write(bArr, 0, read);
                                                                    byteArrayInputStream = byteArrayInputStream2;
                                                                }
                                                                gZIPInputStream.close();
                                                                byteArrayInputStream.close();
                                                                byte[] byteArray = byteArrayOutputStream.toByteArray();
                                                                if (!arrayList.isEmpty() && byteArray.length + i5 > max) {
                                                                    break;
                                                                }
                                                                try {
                                                                    com.google.android.gms.internal.measurement.zzgb zzgbVar = (com.google.android.gms.internal.measurement.zzgb) zzln.m(com.google.android.gms.internal.measurement.zzgc.zzu(), byteArray);
                                                                    if (!cursor5.isNull(2)) {
                                                                        zzgbVar.zzag(cursor5.getInt(2));
                                                                    }
                                                                    i5 += byteArray.length;
                                                                    arrayList.add(Pair.create((com.google.android.gms.internal.measurement.zzgc) zzgbVar.zzaE(), Long.valueOf(j4)));
                                                                } catch (IOException e8) {
                                                                    iOException = e8;
                                                                    zzd = zzamVar5.f6809a.zzay().zzd();
                                                                    str5 = "Failed to merge queued bundle. appId";
                                                                    g3 = zzfa.g(zzr);
                                                                    zzd.zzc(str5, g3, iOException);
                                                                    if (!cursor5.moveToNext()) {
                                                                        break;
                                                                    }
                                                                    currentTimeMillis = j2;
                                                                    i2 = 1;
                                                                    i3 = 0;
                                                                    cursor5.close();
                                                                    emptyList = arrayList;
                                                                    if (!emptyList.isEmpty()) {
                                                                    }
                                                                    this.zzv = false;
                                                                    zzad();
                                                                }
                                                                if (!cursor5.moveToNext() || i5 > max) {
                                                                    break;
                                                                    break;
                                                                }
                                                                currentTimeMillis = j2;
                                                                i2 = 1;
                                                                i3 = 0;
                                                            } catch (IOException e9) {
                                                                e = e9;
                                                                IOException iOException2 = e;
                                                                try {
                                                                    zzlnVar.f6809a.zzay().zzd().zzb("Failed to ungzip content", iOException2);
                                                                    throw iOException2;
                                                                    break;
                                                                } catch (IOException e10) {
                                                                    e = e10;
                                                                    iOException = e;
                                                                    zzd = zzamVar5.f6809a.zzay().zzd();
                                                                    str5 = "Failed to unzip queued bundle. appId";
                                                                    g3 = zzfa.g(zzr);
                                                                    zzd.zzc(str5, g3, iOException);
                                                                    if (!cursor5.moveToNext()) {
                                                                    }
                                                                    cursor5.close();
                                                                    emptyList = arrayList;
                                                                    if (!emptyList.isEmpty()) {
                                                                    }
                                                                    this.zzv = false;
                                                                    zzad();
                                                                }
                                                            }
                                                        } catch (SQLiteException e11) {
                                                            e = e11;
                                                            sQLiteException2 = e;
                                                            zzamVar5.f6809a.zzay().zzd().zzc("Error querying bundles. appId", zzfa.g(zzr), sQLiteException2);
                                                            emptyList = Collections.emptyList();
                                                            if (cursor5 != null) {
                                                            }
                                                            if (!emptyList.isEmpty()) {
                                                            }
                                                            this.zzv = false;
                                                            zzad();
                                                        }
                                                    }
                                                    cursor5.close();
                                                    emptyList = arrayList;
                                                } else {
                                                    try {
                                                        emptyList = Collections.emptyList();
                                                        cursor5.close();
                                                        j2 = currentTimeMillis;
                                                    } catch (SQLiteException e12) {
                                                        sQLiteException2 = e12;
                                                        j2 = currentTimeMillis;
                                                        zzamVar5.f6809a.zzay().zzd().zzc("Error querying bundles. appId", zzfa.g(zzr), sQLiteException2);
                                                        emptyList = Collections.emptyList();
                                                        if (cursor5 != null) {
                                                            cursor5.close();
                                                        }
                                                        if (!emptyList.isEmpty()) {
                                                        }
                                                        this.zzv = false;
                                                        zzad();
                                                    }
                                                }
                                            } catch (SQLiteException e13) {
                                                e = e13;
                                                j2 = currentTimeMillis;
                                            }
                                        } catch (Throwable th9) {
                                            th3 = th9;
                                            cursor4 = cursor5;
                                            if (cursor4 != null) {
                                                cursor4.close();
                                            }
                                            throw th3;
                                        }
                                    } catch (SQLiteException e14) {
                                        j2 = currentTimeMillis;
                                        sQLiteException2 = e14;
                                        cursor5 = null;
                                    } catch (Throwable th10) {
                                        th3 = th10;
                                        cursor4 = null;
                                        if (cursor4 != null) {
                                        }
                                        throw th3;
                                    }
                                    if (!emptyList.isEmpty()) {
                                        if (C(zzr).zzi(zzah.AD_STORAGE)) {
                                            Iterator it = emptyList.iterator();
                                            while (true) {
                                                if (!it.hasNext()) {
                                                    str4 = null;
                                                    break;
                                                }
                                                com.google.android.gms.internal.measurement.zzgc zzgcVar = (com.google.android.gms.internal.measurement.zzgc) ((Pair) it.next()).first;
                                                if (!zzgcVar.zzK().isEmpty()) {
                                                    str4 = zzgcVar.zzK();
                                                    break;
                                                }
                                            }
                                            if (str4 != null) {
                                                int i6 = 0;
                                                while (true) {
                                                    if (i6 >= emptyList.size()) {
                                                        break;
                                                    }
                                                    com.google.android.gms.internal.measurement.zzgc zzgcVar2 = (com.google.android.gms.internal.measurement.zzgc) ((Pair) emptyList.get(i6)).first;
                                                    if (!zzgcVar2.zzK().isEmpty() && !zzgcVar2.zzK().equals(str4)) {
                                                        break;
                                                    }
                                                    i6++;
                                                }
                                            }
                                        }
                                        com.google.android.gms.internal.measurement.zzfz zza3 = com.google.android.gms.internal.measurement.zzga.zza();
                                        int size = emptyList.size();
                                        ArrayList arrayList2 = new ArrayList(emptyList.size());
                                        boolean z4 = zzg().zzt(zzr) && C(zzr).zzi(zzah.AD_STORAGE);
                                        boolean zzi = C(zzr).zzi(zzah.AD_STORAGE);
                                        boolean zzi2 = C(zzr).zzi(zzah.ANALYTICS_STORAGE);
                                        zzps.zzc();
                                        boolean zzs = zzg().zzs(null, zzen.zzaH);
                                        int i7 = 0;
                                        while (i7 < size) {
                                            com.google.android.gms.internal.measurement.zzgb zzgbVar2 = (com.google.android.gms.internal.measurement.zzgb) ((com.google.android.gms.internal.measurement.zzgc) ((Pair) emptyList.get(i7)).first).zzbB();
                                            arrayList2.add((Long) ((Pair) emptyList.get(i7)).second);
                                            zzg().zzh();
                                            zzgbVar2.zzam(64000L);
                                            long j5 = j2;
                                            zzgbVar2.zzal(j5);
                                            this.zzn.zzaw();
                                            try {
                                                zzgbVar2.zzah(false);
                                                if (!z4) {
                                                    zzgbVar2.zzq();
                                                }
                                                if (!zzi) {
                                                    zzgbVar2.zzx();
                                                    zzgbVar2.zzt();
                                                }
                                                if (!zzi2) {
                                                    zzgbVar2.zzn();
                                                }
                                                zzpj.zzc();
                                                boolean z5 = z4;
                                                if (zzg().zzs(zzr, zzen.zzaC)) {
                                                    zzgb zzgbVar3 = this.zzc;
                                                    zzak(zzgbVar3);
                                                    Set k2 = zzgbVar3.k(zzr);
                                                    if (k2 != null) {
                                                        zzgbVar2.zzi(k2);
                                                    }
                                                }
                                                if (zzg().zzs(zzr, zzen.zzaE)) {
                                                    zzgb zzgbVar4 = this.zzc;
                                                    zzak(zzgbVar4);
                                                    if (zzgbVar4.u(zzr)) {
                                                        zzgbVar2.zzp();
                                                    }
                                                    zzgb zzgbVar5 = this.zzc;
                                                    zzak(zzgbVar5);
                                                    if (zzgbVar5.w(zzr)) {
                                                        zzgbVar2.zzu();
                                                    }
                                                }
                                                if (zzg().zzs(zzr, zzen.zzaF)) {
                                                    zzgb zzgbVar6 = this.zzc;
                                                    zzak(zzgbVar6);
                                                    if (zzgbVar6.x(zzr) && (g2 = zzln.g(zzgbVar2, "_id")) != -1) {
                                                        zzgbVar2.zzB(g2);
                                                    }
                                                }
                                                if (zzg().zzs(zzr, zzen.zzaG)) {
                                                    zzgb zzgbVar7 = this.zzc;
                                                    zzak(zzgbVar7);
                                                    if (zzgbVar7.v(zzr)) {
                                                        zzgbVar2.zzq();
                                                    }
                                                }
                                                if (zzg().zzs(zzr, zzen.zzaJ)) {
                                                    zzgb zzgbVar8 = this.zzc;
                                                    zzak(zzgbVar8);
                                                    if (zzgbVar8.t(zzr)) {
                                                        zzgbVar2.zzn();
                                                        if (zzg().zzs(zzr, zzen.zzaK)) {
                                                            zzlk zzlkVar2 = (zzlk) this.zzC.get(zzr);
                                                            if (zzlkVar2 != null) {
                                                                z = zzi;
                                                                list = emptyList;
                                                                z2 = zzi2;
                                                                if (zzlkVar2.f7039b + zzg().zzi(zzr, zzen.zzR) >= zzav().elapsedRealtime()) {
                                                                    zzlkVar = zzlkVar2;
                                                                    zzgbVar2.zzS(zzlkVar.f7038a);
                                                                    if (zzg().zzs(zzr, zzen.zzaL)) {
                                                                        zzgb zzgbVar9 = this.zzc;
                                                                        zzak(zzgbVar9);
                                                                        if (zzgbVar9.zzw(zzr)) {
                                                                            zzgbVar2.zzy();
                                                                        }
                                                                    }
                                                                    if (!zzs) {
                                                                        zzgbVar2.zzy();
                                                                    }
                                                                    if (zzg().zzs(zzr, zzen.zzU)) {
                                                                        byte[] zzby = ((com.google.android.gms.internal.measurement.zzgc) zzgbVar2.zzaE()).zzby();
                                                                        zzln zzlnVar2 = this.zzi;
                                                                        zzak(zzlnVar2);
                                                                        zzgbVar2.zzK(zzlnVar2.h(zzby));
                                                                    }
                                                                    zza3.zza(zzgbVar2);
                                                                    i7++;
                                                                    emptyList = list;
                                                                    z4 = z5;
                                                                    zzi = z;
                                                                    zzi2 = z2;
                                                                    j2 = j5;
                                                                }
                                                            } else {
                                                                z = zzi;
                                                                list = emptyList;
                                                                z2 = zzi2;
                                                            }
                                                            zzlkVar = new zzlk(this);
                                                            this.zzC.put(zzr, zzlkVar);
                                                            zzgbVar2.zzS(zzlkVar.f7038a);
                                                            if (zzg().zzs(zzr, zzen.zzaL)) {
                                                            }
                                                            if (!zzs) {
                                                            }
                                                            if (zzg().zzs(zzr, zzen.zzU)) {
                                                            }
                                                            zza3.zza(zzgbVar2);
                                                            i7++;
                                                            emptyList = list;
                                                            z4 = z5;
                                                            zzi = z;
                                                            zzi2 = z2;
                                                            j2 = j5;
                                                        }
                                                    }
                                                }
                                                z = zzi;
                                                list = emptyList;
                                                z2 = zzi2;
                                                if (zzg().zzs(zzr, zzen.zzaL)) {
                                                }
                                                if (!zzs) {
                                                }
                                                if (zzg().zzs(zzr, zzen.zzU)) {
                                                }
                                                zza3.zza(zzgbVar2);
                                                i7++;
                                                emptyList = list;
                                                z4 = z5;
                                                zzi = z;
                                                zzi2 = z2;
                                                j2 = j5;
                                            } catch (Throwable th11) {
                                                th = th11;
                                                z3 = false;
                                                this.zzv = z3;
                                                zzad();
                                                throw th;
                                            }
                                        }
                                        long j6 = j2;
                                        if (Log.isLoggable(zzay().zzq(), 2)) {
                                            zzln zzlnVar3 = this.zzi;
                                            zzak(zzlnVar3);
                                            str2 = zzlnVar3.n((com.google.android.gms.internal.measurement.zzga) zza3.zzaE());
                                        } else {
                                            str2 = null;
                                        }
                                        zzak(this.zzi);
                                        byte[] zzby2 = ((com.google.android.gms.internal.measurement.zzga) zza3.zzaE()).zzby();
                                        zzla zzlaVar = this.zzl;
                                        zzpj.zzc();
                                        try {
                                            if (zzlaVar.f6809a.zzf().zzs(zzr, zzen.zzaD)) {
                                                zzgb zzgbVar10 = zzlaVar.f7018b.zzc;
                                                zzak(zzgbVar10);
                                                String i8 = zzgbVar10.i(zzr);
                                                if (TextUtils.isEmpty(i8)) {
                                                    zza = zzen.zzp.zza(null);
                                                } else {
                                                    Uri parse = Uri.parse((String) zzen.zzp.zza(null));
                                                    Uri.Builder buildUpon = parse.buildUpon();
                                                    buildUpon.authority(i8 + "." + parse.getAuthority());
                                                    str3 = buildUpon.build().toString();
                                                    URL url2 = new URL(str3);
                                                    Preconditions.checkArgument(!arrayList2.isEmpty());
                                                    if (this.zzy == null) {
                                                        zzay().zzd().zza("Set uploading progress before finishing the previous upload");
                                                    } else {
                                                        this.zzy = new ArrayList(arrayList2);
                                                    }
                                                    this.zzk.zzd.zzb(j6);
                                                    zzay().zzj().zzd("Uploading data. app, uncompressed size, data", size > 0 ? zza3.zzb(0).zzy() : "?", Integer.valueOf(zzby2.length), str2);
                                                    this.zzu = true;
                                                    zzfg zzfgVar22 = this.zzd;
                                                    zzak(zzfgVar22);
                                                    zzlc zzlcVar2 = new zzlc(this, zzr);
                                                    zzfgVar22.zzg();
                                                    zzfgVar22.a();
                                                    Preconditions.checkNotNull(url2);
                                                    Preconditions.checkNotNull(zzby2);
                                                    Preconditions.checkNotNull(zzlcVar2);
                                                    zzfgVar22.f6809a.zzaz().zzo(new zzff(zzfgVar22, zzr, url2, zzby2, null, zzlcVar2));
                                                }
                                            } else {
                                                zza = zzen.zzp.zza(null);
                                            }
                                            URL url22 = new URL(str3);
                                            Preconditions.checkArgument(!arrayList2.isEmpty());
                                            if (this.zzy == null) {
                                            }
                                            this.zzk.zzd.zzb(j6);
                                            zzay().zzj().zzd("Uploading data. app, uncompressed size, data", size > 0 ? zza3.zzb(0).zzy() : "?", Integer.valueOf(zzby2.length), str2);
                                            this.zzu = true;
                                            zzfg zzfgVar222 = this.zzd;
                                            zzak(zzfgVar222);
                                            zzlc zzlcVar22 = new zzlc(this, zzr);
                                            zzfgVar222.zzg();
                                            zzfgVar222.a();
                                            Preconditions.checkNotNull(url22);
                                            Preconditions.checkNotNull(zzby2);
                                            Preconditions.checkNotNull(zzlcVar22);
                                            zzfgVar222.f6809a.zzaz().zzo(new zzff(zzfgVar222, zzr, url22, zzby2, null, zzlcVar22));
                                        } catch (MalformedURLException unused) {
                                            zzay().zzd().zzc("Failed to parse upload URL. Not uploading. appId", zzfa.g(zzr), str3);
                                        }
                                        str3 = (String) zza;
                                    }
                                }
                                this.zzv = false;
                                zzad();
                            } catch (Throwable th12) {
                                th = th12;
                                z3 = false;
                                this.zzv = z3;
                                zzad();
                                throw th;
                            }
                        }
                        zzay().zzj().zza("Network not connected, ignoring upload request");
                    }
                }
                zzaf();
                this.zzv = false;
                zzad();
            } else {
                zzj = zzay().zzd();
                str6 = "Upload called in the client side when service should be used";
            }
            zzj.zza(str6);
            this.zzv = false;
            zzad();
        } catch (Throwable th13) {
            th = th13;
        }
    }

    /*  JADX ERROR: IF instruction can be used only in fallback mode
        jadx.core.utils.exceptions.CodegenException: IF instruction can be used only in fallback mode
        	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:664)
        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:522)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:280)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
        	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:175)
        	at jadx.core.dex.regions.loops.LoopRegion.generate(LoopRegion.java:171)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
        	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
        	at jadx.core.codegen.RegionGen.makeCatchBlock(RegionGen.java:365)
        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:313)
        	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
        	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
        	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
        	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:296)
        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:275)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:377)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:306)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:272)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
        */
    /* JADX WARN: Can't wrap try/catch for region: R(18:294|(2:296|(1:298)(7:299|300|(1:302)|51|(0)(0)|54|(0)(0)))|303|304|305|306|307|308|309|310|311|312|300|(0)|51|(0)(0)|54|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:222:0x075e, code lost:
        if (r14.isEmpty() == false) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x029e, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x02a0, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x02a1, code lost:
        r33 = "metadata_fingerprint";
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x02a4, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x02a5, code lost:
        r33 = "metadata_fingerprint";
        r21 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x02ab, code lost:
        r11.f6809a.zzay().zzd().zzc("Error pruning currencies. appId", com.google.android.gms.measurement.internal.zzfa.g(r10), r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x032f  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0332 A[Catch: all -> 0x0aa5, TryCatch #3 {all -> 0x0aa5, blocks: (B:28:0x0124, B:30:0x0136, B:32:0x0142, B:33:0x014e, B:36:0x015c, B:38:0x0166, B:43:0x0172, B:99:0x031c, B:108:0x0352, B:110:0x0392, B:112:0x0398, B:113:0x03af, B:117:0x03c2, B:119:0x03d9, B:121:0x03df, B:122:0x03f6, B:127:0x0420, B:131:0x0441, B:132:0x0458, B:135:0x0469, B:138:0x0486, B:139:0x049a, B:141:0x04a4, B:143:0x04b3, B:145:0x04b9, B:146:0x04c2, B:147:0x04d0, B:149:0x04e5, B:151:0x04fa, B:163:0x0526, B:164:0x053b, B:166:0x0565, B:169:0x057d, B:172:0x05c0, B:174:0x05ec, B:176:0x062b, B:177:0x0630, B:179:0x0638, B:180:0x063d, B:182:0x0645, B:183:0x064a, B:185:0x0659, B:187:0x0661, B:188:0x0666, B:190:0x066f, B:191:0x0673, B:193:0x0680, B:194:0x0685, B:196:0x06ac, B:198:0x06b4, B:199:0x06b9, B:201:0x06c1, B:202:0x06c4, B:204:0x06dc, B:207:0x06e4, B:208:0x06fd, B:210:0x0703, B:212:0x0717, B:214:0x0723, B:216:0x0730, B:220:0x074a, B:221:0x075a, B:225:0x0763, B:226:0x0766, B:228:0x0784, B:230:0x0796, B:232:0x079a, B:234:0x07a5, B:235:0x07ae, B:237:0x07f1, B:238:0x07f6, B:240:0x07fe, B:242:0x0807, B:243:0x080a, B:245:0x0817, B:247:0x0837, B:248:0x0842, B:250:0x0875, B:251:0x087a, B:252:0x0887, B:254:0x088f, B:256:0x0899, B:257:0x08a6, B:259:0x08b0, B:260:0x08bd, B:261:0x08ca, B:263:0x08d0, B:265:0x0900, B:266:0x0946, B:267:0x0950, B:268:0x095c, B:270:0x0962, B:279:0x09b0, B:280:0x09fe, B:282:0x0a0e, B:296:0x0a72, B:285:0x0a26, B:287:0x0a2a, B:273:0x096e, B:275:0x099a, B:291:0x0a43, B:292:0x0a5a, B:295:0x0a5d, B:173:0x05de, B:160:0x050b, B:102:0x0332, B:103:0x0339, B:105:0x033f, B:107:0x034b, B:49:0x0186, B:52:0x0192, B:54:0x01a9, B:60:0x01c7, B:68:0x0207, B:70:0x020d, B:72:0x021b, B:74:0x022c, B:77:0x0233, B:95:0x02da, B:97:0x02e5, B:78:0x0261, B:79:0x027e, B:81:0x0285, B:83:0x0296, B:94:0x02be, B:93:0x02ab, B:63:0x01d5, B:67:0x01fd), top: B:310:0x0124, inners: #0, #1, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0392 A[Catch: all -> 0x0aa5, TryCatch #3 {all -> 0x0aa5, blocks: (B:28:0x0124, B:30:0x0136, B:32:0x0142, B:33:0x014e, B:36:0x015c, B:38:0x0166, B:43:0x0172, B:99:0x031c, B:108:0x0352, B:110:0x0392, B:112:0x0398, B:113:0x03af, B:117:0x03c2, B:119:0x03d9, B:121:0x03df, B:122:0x03f6, B:127:0x0420, B:131:0x0441, B:132:0x0458, B:135:0x0469, B:138:0x0486, B:139:0x049a, B:141:0x04a4, B:143:0x04b3, B:145:0x04b9, B:146:0x04c2, B:147:0x04d0, B:149:0x04e5, B:151:0x04fa, B:163:0x0526, B:164:0x053b, B:166:0x0565, B:169:0x057d, B:172:0x05c0, B:174:0x05ec, B:176:0x062b, B:177:0x0630, B:179:0x0638, B:180:0x063d, B:182:0x0645, B:183:0x064a, B:185:0x0659, B:187:0x0661, B:188:0x0666, B:190:0x066f, B:191:0x0673, B:193:0x0680, B:194:0x0685, B:196:0x06ac, B:198:0x06b4, B:199:0x06b9, B:201:0x06c1, B:202:0x06c4, B:204:0x06dc, B:207:0x06e4, B:208:0x06fd, B:210:0x0703, B:212:0x0717, B:214:0x0723, B:216:0x0730, B:220:0x074a, B:221:0x075a, B:225:0x0763, B:226:0x0766, B:228:0x0784, B:230:0x0796, B:232:0x079a, B:234:0x07a5, B:235:0x07ae, B:237:0x07f1, B:238:0x07f6, B:240:0x07fe, B:242:0x0807, B:243:0x080a, B:245:0x0817, B:247:0x0837, B:248:0x0842, B:250:0x0875, B:251:0x087a, B:252:0x0887, B:254:0x088f, B:256:0x0899, B:257:0x08a6, B:259:0x08b0, B:260:0x08bd, B:261:0x08ca, B:263:0x08d0, B:265:0x0900, B:266:0x0946, B:267:0x0950, B:268:0x095c, B:270:0x0962, B:279:0x09b0, B:280:0x09fe, B:282:0x0a0e, B:296:0x0a72, B:285:0x0a26, B:287:0x0a2a, B:273:0x096e, B:275:0x099a, B:291:0x0a43, B:292:0x0a5a, B:295:0x0a5d, B:173:0x05de, B:160:0x050b, B:102:0x0332, B:103:0x0339, B:105:0x033f, B:107:0x034b, B:49:0x0186, B:52:0x0192, B:54:0x01a9, B:60:0x01c7, B:68:0x0207, B:70:0x020d, B:72:0x021b, B:74:0x022c, B:77:0x0233, B:95:0x02da, B:97:0x02e5, B:78:0x0261, B:79:0x027e, B:81:0x0285, B:83:0x0296, B:94:0x02be, B:93:0x02ab, B:63:0x01d5, B:67:0x01fd), top: B:310:0x0124, inners: #0, #1, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x03c0  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0526 A[Catch: all -> 0x0aa5, TryCatch #3 {all -> 0x0aa5, blocks: (B:28:0x0124, B:30:0x0136, B:32:0x0142, B:33:0x014e, B:36:0x015c, B:38:0x0166, B:43:0x0172, B:99:0x031c, B:108:0x0352, B:110:0x0392, B:112:0x0398, B:113:0x03af, B:117:0x03c2, B:119:0x03d9, B:121:0x03df, B:122:0x03f6, B:127:0x0420, B:131:0x0441, B:132:0x0458, B:135:0x0469, B:138:0x0486, B:139:0x049a, B:141:0x04a4, B:143:0x04b3, B:145:0x04b9, B:146:0x04c2, B:147:0x04d0, B:149:0x04e5, B:151:0x04fa, B:163:0x0526, B:164:0x053b, B:166:0x0565, B:169:0x057d, B:172:0x05c0, B:174:0x05ec, B:176:0x062b, B:177:0x0630, B:179:0x0638, B:180:0x063d, B:182:0x0645, B:183:0x064a, B:185:0x0659, B:187:0x0661, B:188:0x0666, B:190:0x066f, B:191:0x0673, B:193:0x0680, B:194:0x0685, B:196:0x06ac, B:198:0x06b4, B:199:0x06b9, B:201:0x06c1, B:202:0x06c4, B:204:0x06dc, B:207:0x06e4, B:208:0x06fd, B:210:0x0703, B:212:0x0717, B:214:0x0723, B:216:0x0730, B:220:0x074a, B:221:0x075a, B:225:0x0763, B:226:0x0766, B:228:0x0784, B:230:0x0796, B:232:0x079a, B:234:0x07a5, B:235:0x07ae, B:237:0x07f1, B:238:0x07f6, B:240:0x07fe, B:242:0x0807, B:243:0x080a, B:245:0x0817, B:247:0x0837, B:248:0x0842, B:250:0x0875, B:251:0x087a, B:252:0x0887, B:254:0x088f, B:256:0x0899, B:257:0x08a6, B:259:0x08b0, B:260:0x08bd, B:261:0x08ca, B:263:0x08d0, B:265:0x0900, B:266:0x0946, B:267:0x0950, B:268:0x095c, B:270:0x0962, B:279:0x09b0, B:280:0x09fe, B:282:0x0a0e, B:296:0x0a72, B:285:0x0a26, B:287:0x0a2a, B:273:0x096e, B:275:0x099a, B:291:0x0a43, B:292:0x0a5a, B:295:0x0a5d, B:173:0x05de, B:160:0x050b, B:102:0x0332, B:103:0x0339, B:105:0x033f, B:107:0x034b, B:49:0x0186, B:52:0x0192, B:54:0x01a9, B:60:0x01c7, B:68:0x0207, B:70:0x020d, B:72:0x021b, B:74:0x022c, B:77:0x0233, B:95:0x02da, B:97:0x02e5, B:78:0x0261, B:79:0x027e, B:81:0x0285, B:83:0x0296, B:94:0x02be, B:93:0x02ab, B:63:0x01d5, B:67:0x01fd), top: B:310:0x0124, inners: #0, #1, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0565 A[Catch: all -> 0x0aa5, TryCatch #3 {all -> 0x0aa5, blocks: (B:28:0x0124, B:30:0x0136, B:32:0x0142, B:33:0x014e, B:36:0x015c, B:38:0x0166, B:43:0x0172, B:99:0x031c, B:108:0x0352, B:110:0x0392, B:112:0x0398, B:113:0x03af, B:117:0x03c2, B:119:0x03d9, B:121:0x03df, B:122:0x03f6, B:127:0x0420, B:131:0x0441, B:132:0x0458, B:135:0x0469, B:138:0x0486, B:139:0x049a, B:141:0x04a4, B:143:0x04b3, B:145:0x04b9, B:146:0x04c2, B:147:0x04d0, B:149:0x04e5, B:151:0x04fa, B:163:0x0526, B:164:0x053b, B:166:0x0565, B:169:0x057d, B:172:0x05c0, B:174:0x05ec, B:176:0x062b, B:177:0x0630, B:179:0x0638, B:180:0x063d, B:182:0x0645, B:183:0x064a, B:185:0x0659, B:187:0x0661, B:188:0x0666, B:190:0x066f, B:191:0x0673, B:193:0x0680, B:194:0x0685, B:196:0x06ac, B:198:0x06b4, B:199:0x06b9, B:201:0x06c1, B:202:0x06c4, B:204:0x06dc, B:207:0x06e4, B:208:0x06fd, B:210:0x0703, B:212:0x0717, B:214:0x0723, B:216:0x0730, B:220:0x074a, B:221:0x075a, B:225:0x0763, B:226:0x0766, B:228:0x0784, B:230:0x0796, B:232:0x079a, B:234:0x07a5, B:235:0x07ae, B:237:0x07f1, B:238:0x07f6, B:240:0x07fe, B:242:0x0807, B:243:0x080a, B:245:0x0817, B:247:0x0837, B:248:0x0842, B:250:0x0875, B:251:0x087a, B:252:0x0887, B:254:0x088f, B:256:0x0899, B:257:0x08a6, B:259:0x08b0, B:260:0x08bd, B:261:0x08ca, B:263:0x08d0, B:265:0x0900, B:266:0x0946, B:267:0x0950, B:268:0x095c, B:270:0x0962, B:279:0x09b0, B:280:0x09fe, B:282:0x0a0e, B:296:0x0a72, B:285:0x0a26, B:287:0x0a2a, B:273:0x096e, B:275:0x099a, B:291:0x0a43, B:292:0x0a5a, B:295:0x0a5d, B:173:0x05de, B:160:0x050b, B:102:0x0332, B:103:0x0339, B:105:0x033f, B:107:0x034b, B:49:0x0186, B:52:0x0192, B:54:0x01a9, B:60:0x01c7, B:68:0x0207, B:70:0x020d, B:72:0x021b, B:74:0x022c, B:77:0x0233, B:95:0x02da, B:97:0x02e5, B:78:0x0261, B:79:0x027e, B:81:0x0285, B:83:0x0296, B:94:0x02be, B:93:0x02ab, B:63:0x01d5, B:67:0x01fd), top: B:310:0x0124, inners: #0, #1, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:173:0x05de A[Catch: all -> 0x0aa5, TryCatch #3 {all -> 0x0aa5, blocks: (B:28:0x0124, B:30:0x0136, B:32:0x0142, B:33:0x014e, B:36:0x015c, B:38:0x0166, B:43:0x0172, B:99:0x031c, B:108:0x0352, B:110:0x0392, B:112:0x0398, B:113:0x03af, B:117:0x03c2, B:119:0x03d9, B:121:0x03df, B:122:0x03f6, B:127:0x0420, B:131:0x0441, B:132:0x0458, B:135:0x0469, B:138:0x0486, B:139:0x049a, B:141:0x04a4, B:143:0x04b3, B:145:0x04b9, B:146:0x04c2, B:147:0x04d0, B:149:0x04e5, B:151:0x04fa, B:163:0x0526, B:164:0x053b, B:166:0x0565, B:169:0x057d, B:172:0x05c0, B:174:0x05ec, B:176:0x062b, B:177:0x0630, B:179:0x0638, B:180:0x063d, B:182:0x0645, B:183:0x064a, B:185:0x0659, B:187:0x0661, B:188:0x0666, B:190:0x066f, B:191:0x0673, B:193:0x0680, B:194:0x0685, B:196:0x06ac, B:198:0x06b4, B:199:0x06b9, B:201:0x06c1, B:202:0x06c4, B:204:0x06dc, B:207:0x06e4, B:208:0x06fd, B:210:0x0703, B:212:0x0717, B:214:0x0723, B:216:0x0730, B:220:0x074a, B:221:0x075a, B:225:0x0763, B:226:0x0766, B:228:0x0784, B:230:0x0796, B:232:0x079a, B:234:0x07a5, B:235:0x07ae, B:237:0x07f1, B:238:0x07f6, B:240:0x07fe, B:242:0x0807, B:243:0x080a, B:245:0x0817, B:247:0x0837, B:248:0x0842, B:250:0x0875, B:251:0x087a, B:252:0x0887, B:254:0x088f, B:256:0x0899, B:257:0x08a6, B:259:0x08b0, B:260:0x08bd, B:261:0x08ca, B:263:0x08d0, B:265:0x0900, B:266:0x0946, B:267:0x0950, B:268:0x095c, B:270:0x0962, B:279:0x09b0, B:280:0x09fe, B:282:0x0a0e, B:296:0x0a72, B:285:0x0a26, B:287:0x0a2a, B:273:0x096e, B:275:0x099a, B:291:0x0a43, B:292:0x0a5a, B:295:0x0a5d, B:173:0x05de, B:160:0x050b, B:102:0x0332, B:103:0x0339, B:105:0x033f, B:107:0x034b, B:49:0x0186, B:52:0x0192, B:54:0x01a9, B:60:0x01c7, B:68:0x0207, B:70:0x020d, B:72:0x021b, B:74:0x022c, B:77:0x0233, B:95:0x02da, B:97:0x02e5, B:78:0x0261, B:79:0x027e, B:81:0x0285, B:83:0x0296, B:94:0x02be, B:93:0x02ab, B:63:0x01d5, B:67:0x01fd), top: B:310:0x0124, inners: #0, #1, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:176:0x062b A[Catch: all -> 0x0aa5, TryCatch #3 {all -> 0x0aa5, blocks: (B:28:0x0124, B:30:0x0136, B:32:0x0142, B:33:0x014e, B:36:0x015c, B:38:0x0166, B:43:0x0172, B:99:0x031c, B:108:0x0352, B:110:0x0392, B:112:0x0398, B:113:0x03af, B:117:0x03c2, B:119:0x03d9, B:121:0x03df, B:122:0x03f6, B:127:0x0420, B:131:0x0441, B:132:0x0458, B:135:0x0469, B:138:0x0486, B:139:0x049a, B:141:0x04a4, B:143:0x04b3, B:145:0x04b9, B:146:0x04c2, B:147:0x04d0, B:149:0x04e5, B:151:0x04fa, B:163:0x0526, B:164:0x053b, B:166:0x0565, B:169:0x057d, B:172:0x05c0, B:174:0x05ec, B:176:0x062b, B:177:0x0630, B:179:0x0638, B:180:0x063d, B:182:0x0645, B:183:0x064a, B:185:0x0659, B:187:0x0661, B:188:0x0666, B:190:0x066f, B:191:0x0673, B:193:0x0680, B:194:0x0685, B:196:0x06ac, B:198:0x06b4, B:199:0x06b9, B:201:0x06c1, B:202:0x06c4, B:204:0x06dc, B:207:0x06e4, B:208:0x06fd, B:210:0x0703, B:212:0x0717, B:214:0x0723, B:216:0x0730, B:220:0x074a, B:221:0x075a, B:225:0x0763, B:226:0x0766, B:228:0x0784, B:230:0x0796, B:232:0x079a, B:234:0x07a5, B:235:0x07ae, B:237:0x07f1, B:238:0x07f6, B:240:0x07fe, B:242:0x0807, B:243:0x080a, B:245:0x0817, B:247:0x0837, B:248:0x0842, B:250:0x0875, B:251:0x087a, B:252:0x0887, B:254:0x088f, B:256:0x0899, B:257:0x08a6, B:259:0x08b0, B:260:0x08bd, B:261:0x08ca, B:263:0x08d0, B:265:0x0900, B:266:0x0946, B:267:0x0950, B:268:0x095c, B:270:0x0962, B:279:0x09b0, B:280:0x09fe, B:282:0x0a0e, B:296:0x0a72, B:285:0x0a26, B:287:0x0a2a, B:273:0x096e, B:275:0x099a, B:291:0x0a43, B:292:0x0a5a, B:295:0x0a5d, B:173:0x05de, B:160:0x050b, B:102:0x0332, B:103:0x0339, B:105:0x033f, B:107:0x034b, B:49:0x0186, B:52:0x0192, B:54:0x01a9, B:60:0x01c7, B:68:0x0207, B:70:0x020d, B:72:0x021b, B:74:0x022c, B:77:0x0233, B:95:0x02da, B:97:0x02e5, B:78:0x0261, B:79:0x027e, B:81:0x0285, B:83:0x0296, B:94:0x02be, B:93:0x02ab, B:63:0x01d5, B:67:0x01fd), top: B:310:0x0124, inners: #0, #1, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0638 A[Catch: all -> 0x0aa5, TryCatch #3 {all -> 0x0aa5, blocks: (B:28:0x0124, B:30:0x0136, B:32:0x0142, B:33:0x014e, B:36:0x015c, B:38:0x0166, B:43:0x0172, B:99:0x031c, B:108:0x0352, B:110:0x0392, B:112:0x0398, B:113:0x03af, B:117:0x03c2, B:119:0x03d9, B:121:0x03df, B:122:0x03f6, B:127:0x0420, B:131:0x0441, B:132:0x0458, B:135:0x0469, B:138:0x0486, B:139:0x049a, B:141:0x04a4, B:143:0x04b3, B:145:0x04b9, B:146:0x04c2, B:147:0x04d0, B:149:0x04e5, B:151:0x04fa, B:163:0x0526, B:164:0x053b, B:166:0x0565, B:169:0x057d, B:172:0x05c0, B:174:0x05ec, B:176:0x062b, B:177:0x0630, B:179:0x0638, B:180:0x063d, B:182:0x0645, B:183:0x064a, B:185:0x0659, B:187:0x0661, B:188:0x0666, B:190:0x066f, B:191:0x0673, B:193:0x0680, B:194:0x0685, B:196:0x06ac, B:198:0x06b4, B:199:0x06b9, B:201:0x06c1, B:202:0x06c4, B:204:0x06dc, B:207:0x06e4, B:208:0x06fd, B:210:0x0703, B:212:0x0717, B:214:0x0723, B:216:0x0730, B:220:0x074a, B:221:0x075a, B:225:0x0763, B:226:0x0766, B:228:0x0784, B:230:0x0796, B:232:0x079a, B:234:0x07a5, B:235:0x07ae, B:237:0x07f1, B:238:0x07f6, B:240:0x07fe, B:242:0x0807, B:243:0x080a, B:245:0x0817, B:247:0x0837, B:248:0x0842, B:250:0x0875, B:251:0x087a, B:252:0x0887, B:254:0x088f, B:256:0x0899, B:257:0x08a6, B:259:0x08b0, B:260:0x08bd, B:261:0x08ca, B:263:0x08d0, B:265:0x0900, B:266:0x0946, B:267:0x0950, B:268:0x095c, B:270:0x0962, B:279:0x09b0, B:280:0x09fe, B:282:0x0a0e, B:296:0x0a72, B:285:0x0a26, B:287:0x0a2a, B:273:0x096e, B:275:0x099a, B:291:0x0a43, B:292:0x0a5a, B:295:0x0a5d, B:173:0x05de, B:160:0x050b, B:102:0x0332, B:103:0x0339, B:105:0x033f, B:107:0x034b, B:49:0x0186, B:52:0x0192, B:54:0x01a9, B:60:0x01c7, B:68:0x0207, B:70:0x020d, B:72:0x021b, B:74:0x022c, B:77:0x0233, B:95:0x02da, B:97:0x02e5, B:78:0x0261, B:79:0x027e, B:81:0x0285, B:83:0x0296, B:94:0x02be, B:93:0x02ab, B:63:0x01d5, B:67:0x01fd), top: B:310:0x0124, inners: #0, #1, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0645 A[Catch: all -> 0x0aa5, TryCatch #3 {all -> 0x0aa5, blocks: (B:28:0x0124, B:30:0x0136, B:32:0x0142, B:33:0x014e, B:36:0x015c, B:38:0x0166, B:43:0x0172, B:99:0x031c, B:108:0x0352, B:110:0x0392, B:112:0x0398, B:113:0x03af, B:117:0x03c2, B:119:0x03d9, B:121:0x03df, B:122:0x03f6, B:127:0x0420, B:131:0x0441, B:132:0x0458, B:135:0x0469, B:138:0x0486, B:139:0x049a, B:141:0x04a4, B:143:0x04b3, B:145:0x04b9, B:146:0x04c2, B:147:0x04d0, B:149:0x04e5, B:151:0x04fa, B:163:0x0526, B:164:0x053b, B:166:0x0565, B:169:0x057d, B:172:0x05c0, B:174:0x05ec, B:176:0x062b, B:177:0x0630, B:179:0x0638, B:180:0x063d, B:182:0x0645, B:183:0x064a, B:185:0x0659, B:187:0x0661, B:188:0x0666, B:190:0x066f, B:191:0x0673, B:193:0x0680, B:194:0x0685, B:196:0x06ac, B:198:0x06b4, B:199:0x06b9, B:201:0x06c1, B:202:0x06c4, B:204:0x06dc, B:207:0x06e4, B:208:0x06fd, B:210:0x0703, B:212:0x0717, B:214:0x0723, B:216:0x0730, B:220:0x074a, B:221:0x075a, B:225:0x0763, B:226:0x0766, B:228:0x0784, B:230:0x0796, B:232:0x079a, B:234:0x07a5, B:235:0x07ae, B:237:0x07f1, B:238:0x07f6, B:240:0x07fe, B:242:0x0807, B:243:0x080a, B:245:0x0817, B:247:0x0837, B:248:0x0842, B:250:0x0875, B:251:0x087a, B:252:0x0887, B:254:0x088f, B:256:0x0899, B:257:0x08a6, B:259:0x08b0, B:260:0x08bd, B:261:0x08ca, B:263:0x08d0, B:265:0x0900, B:266:0x0946, B:267:0x0950, B:268:0x095c, B:270:0x0962, B:279:0x09b0, B:280:0x09fe, B:282:0x0a0e, B:296:0x0a72, B:285:0x0a26, B:287:0x0a2a, B:273:0x096e, B:275:0x099a, B:291:0x0a43, B:292:0x0a5a, B:295:0x0a5d, B:173:0x05de, B:160:0x050b, B:102:0x0332, B:103:0x0339, B:105:0x033f, B:107:0x034b, B:49:0x0186, B:52:0x0192, B:54:0x01a9, B:60:0x01c7, B:68:0x0207, B:70:0x020d, B:72:0x021b, B:74:0x022c, B:77:0x0233, B:95:0x02da, B:97:0x02e5, B:78:0x0261, B:79:0x027e, B:81:0x0285, B:83:0x0296, B:94:0x02be, B:93:0x02ab, B:63:0x01d5, B:67:0x01fd), top: B:310:0x0124, inners: #0, #1, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:190:0x066f A[Catch: all -> 0x0aa5, TryCatch #3 {all -> 0x0aa5, blocks: (B:28:0x0124, B:30:0x0136, B:32:0x0142, B:33:0x014e, B:36:0x015c, B:38:0x0166, B:43:0x0172, B:99:0x031c, B:108:0x0352, B:110:0x0392, B:112:0x0398, B:113:0x03af, B:117:0x03c2, B:119:0x03d9, B:121:0x03df, B:122:0x03f6, B:127:0x0420, B:131:0x0441, B:132:0x0458, B:135:0x0469, B:138:0x0486, B:139:0x049a, B:141:0x04a4, B:143:0x04b3, B:145:0x04b9, B:146:0x04c2, B:147:0x04d0, B:149:0x04e5, B:151:0x04fa, B:163:0x0526, B:164:0x053b, B:166:0x0565, B:169:0x057d, B:172:0x05c0, B:174:0x05ec, B:176:0x062b, B:177:0x0630, B:179:0x0638, B:180:0x063d, B:182:0x0645, B:183:0x064a, B:185:0x0659, B:187:0x0661, B:188:0x0666, B:190:0x066f, B:191:0x0673, B:193:0x0680, B:194:0x0685, B:196:0x06ac, B:198:0x06b4, B:199:0x06b9, B:201:0x06c1, B:202:0x06c4, B:204:0x06dc, B:207:0x06e4, B:208:0x06fd, B:210:0x0703, B:212:0x0717, B:214:0x0723, B:216:0x0730, B:220:0x074a, B:221:0x075a, B:225:0x0763, B:226:0x0766, B:228:0x0784, B:230:0x0796, B:232:0x079a, B:234:0x07a5, B:235:0x07ae, B:237:0x07f1, B:238:0x07f6, B:240:0x07fe, B:242:0x0807, B:243:0x080a, B:245:0x0817, B:247:0x0837, B:248:0x0842, B:250:0x0875, B:251:0x087a, B:252:0x0887, B:254:0x088f, B:256:0x0899, B:257:0x08a6, B:259:0x08b0, B:260:0x08bd, B:261:0x08ca, B:263:0x08d0, B:265:0x0900, B:266:0x0946, B:267:0x0950, B:268:0x095c, B:270:0x0962, B:279:0x09b0, B:280:0x09fe, B:282:0x0a0e, B:296:0x0a72, B:285:0x0a26, B:287:0x0a2a, B:273:0x096e, B:275:0x099a, B:291:0x0a43, B:292:0x0a5a, B:295:0x0a5d, B:173:0x05de, B:160:0x050b, B:102:0x0332, B:103:0x0339, B:105:0x033f, B:107:0x034b, B:49:0x0186, B:52:0x0192, B:54:0x01a9, B:60:0x01c7, B:68:0x0207, B:70:0x020d, B:72:0x021b, B:74:0x022c, B:77:0x0233, B:95:0x02da, B:97:0x02e5, B:78:0x0261, B:79:0x027e, B:81:0x0285, B:83:0x0296, B:94:0x02be, B:93:0x02ab, B:63:0x01d5, B:67:0x01fd), top: B:310:0x0124, inners: #0, #1, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0680 A[Catch: all -> 0x0aa5, TryCatch #3 {all -> 0x0aa5, blocks: (B:28:0x0124, B:30:0x0136, B:32:0x0142, B:33:0x014e, B:36:0x015c, B:38:0x0166, B:43:0x0172, B:99:0x031c, B:108:0x0352, B:110:0x0392, B:112:0x0398, B:113:0x03af, B:117:0x03c2, B:119:0x03d9, B:121:0x03df, B:122:0x03f6, B:127:0x0420, B:131:0x0441, B:132:0x0458, B:135:0x0469, B:138:0x0486, B:139:0x049a, B:141:0x04a4, B:143:0x04b3, B:145:0x04b9, B:146:0x04c2, B:147:0x04d0, B:149:0x04e5, B:151:0x04fa, B:163:0x0526, B:164:0x053b, B:166:0x0565, B:169:0x057d, B:172:0x05c0, B:174:0x05ec, B:176:0x062b, B:177:0x0630, B:179:0x0638, B:180:0x063d, B:182:0x0645, B:183:0x064a, B:185:0x0659, B:187:0x0661, B:188:0x0666, B:190:0x066f, B:191:0x0673, B:193:0x0680, B:194:0x0685, B:196:0x06ac, B:198:0x06b4, B:199:0x06b9, B:201:0x06c1, B:202:0x06c4, B:204:0x06dc, B:207:0x06e4, B:208:0x06fd, B:210:0x0703, B:212:0x0717, B:214:0x0723, B:216:0x0730, B:220:0x074a, B:221:0x075a, B:225:0x0763, B:226:0x0766, B:228:0x0784, B:230:0x0796, B:232:0x079a, B:234:0x07a5, B:235:0x07ae, B:237:0x07f1, B:238:0x07f6, B:240:0x07fe, B:242:0x0807, B:243:0x080a, B:245:0x0817, B:247:0x0837, B:248:0x0842, B:250:0x0875, B:251:0x087a, B:252:0x0887, B:254:0x088f, B:256:0x0899, B:257:0x08a6, B:259:0x08b0, B:260:0x08bd, B:261:0x08ca, B:263:0x08d0, B:265:0x0900, B:266:0x0946, B:267:0x0950, B:268:0x095c, B:270:0x0962, B:279:0x09b0, B:280:0x09fe, B:282:0x0a0e, B:296:0x0a72, B:285:0x0a26, B:287:0x0a2a, B:273:0x096e, B:275:0x099a, B:291:0x0a43, B:292:0x0a5a, B:295:0x0a5d, B:173:0x05de, B:160:0x050b, B:102:0x0332, B:103:0x0339, B:105:0x033f, B:107:0x034b, B:49:0x0186, B:52:0x0192, B:54:0x01a9, B:60:0x01c7, B:68:0x0207, B:70:0x020d, B:72:0x021b, B:74:0x022c, B:77:0x0233, B:95:0x02da, B:97:0x02e5, B:78:0x0261, B:79:0x027e, B:81:0x0285, B:83:0x0296, B:94:0x02be, B:93:0x02ab, B:63:0x01d5, B:67:0x01fd), top: B:310:0x0124, inners: #0, #1, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:201:0x06c1 A[Catch: all -> 0x0aa5, TryCatch #3 {all -> 0x0aa5, blocks: (B:28:0x0124, B:30:0x0136, B:32:0x0142, B:33:0x014e, B:36:0x015c, B:38:0x0166, B:43:0x0172, B:99:0x031c, B:108:0x0352, B:110:0x0392, B:112:0x0398, B:113:0x03af, B:117:0x03c2, B:119:0x03d9, B:121:0x03df, B:122:0x03f6, B:127:0x0420, B:131:0x0441, B:132:0x0458, B:135:0x0469, B:138:0x0486, B:139:0x049a, B:141:0x04a4, B:143:0x04b3, B:145:0x04b9, B:146:0x04c2, B:147:0x04d0, B:149:0x04e5, B:151:0x04fa, B:163:0x0526, B:164:0x053b, B:166:0x0565, B:169:0x057d, B:172:0x05c0, B:174:0x05ec, B:176:0x062b, B:177:0x0630, B:179:0x0638, B:180:0x063d, B:182:0x0645, B:183:0x064a, B:185:0x0659, B:187:0x0661, B:188:0x0666, B:190:0x066f, B:191:0x0673, B:193:0x0680, B:194:0x0685, B:196:0x06ac, B:198:0x06b4, B:199:0x06b9, B:201:0x06c1, B:202:0x06c4, B:204:0x06dc, B:207:0x06e4, B:208:0x06fd, B:210:0x0703, B:212:0x0717, B:214:0x0723, B:216:0x0730, B:220:0x074a, B:221:0x075a, B:225:0x0763, B:226:0x0766, B:228:0x0784, B:230:0x0796, B:232:0x079a, B:234:0x07a5, B:235:0x07ae, B:237:0x07f1, B:238:0x07f6, B:240:0x07fe, B:242:0x0807, B:243:0x080a, B:245:0x0817, B:247:0x0837, B:248:0x0842, B:250:0x0875, B:251:0x087a, B:252:0x0887, B:254:0x088f, B:256:0x0899, B:257:0x08a6, B:259:0x08b0, B:260:0x08bd, B:261:0x08ca, B:263:0x08d0, B:265:0x0900, B:266:0x0946, B:267:0x0950, B:268:0x095c, B:270:0x0962, B:279:0x09b0, B:280:0x09fe, B:282:0x0a0e, B:296:0x0a72, B:285:0x0a26, B:287:0x0a2a, B:273:0x096e, B:275:0x099a, B:291:0x0a43, B:292:0x0a5a, B:295:0x0a5d, B:173:0x05de, B:160:0x050b, B:102:0x0332, B:103:0x0339, B:105:0x033f, B:107:0x034b, B:49:0x0186, B:52:0x0192, B:54:0x01a9, B:60:0x01c7, B:68:0x0207, B:70:0x020d, B:72:0x021b, B:74:0x022c, B:77:0x0233, B:95:0x02da, B:97:0x02e5, B:78:0x0261, B:79:0x027e, B:81:0x0285, B:83:0x0296, B:94:0x02be, B:93:0x02ab, B:63:0x01d5, B:67:0x01fd), top: B:310:0x0124, inners: #0, #1, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0703 A[Catch: all -> 0x0aa5, TRY_LEAVE, TryCatch #3 {all -> 0x0aa5, blocks: (B:28:0x0124, B:30:0x0136, B:32:0x0142, B:33:0x014e, B:36:0x015c, B:38:0x0166, B:43:0x0172, B:99:0x031c, B:108:0x0352, B:110:0x0392, B:112:0x0398, B:113:0x03af, B:117:0x03c2, B:119:0x03d9, B:121:0x03df, B:122:0x03f6, B:127:0x0420, B:131:0x0441, B:132:0x0458, B:135:0x0469, B:138:0x0486, B:139:0x049a, B:141:0x04a4, B:143:0x04b3, B:145:0x04b9, B:146:0x04c2, B:147:0x04d0, B:149:0x04e5, B:151:0x04fa, B:163:0x0526, B:164:0x053b, B:166:0x0565, B:169:0x057d, B:172:0x05c0, B:174:0x05ec, B:176:0x062b, B:177:0x0630, B:179:0x0638, B:180:0x063d, B:182:0x0645, B:183:0x064a, B:185:0x0659, B:187:0x0661, B:188:0x0666, B:190:0x066f, B:191:0x0673, B:193:0x0680, B:194:0x0685, B:196:0x06ac, B:198:0x06b4, B:199:0x06b9, B:201:0x06c1, B:202:0x06c4, B:204:0x06dc, B:207:0x06e4, B:208:0x06fd, B:210:0x0703, B:212:0x0717, B:214:0x0723, B:216:0x0730, B:220:0x074a, B:221:0x075a, B:225:0x0763, B:226:0x0766, B:228:0x0784, B:230:0x0796, B:232:0x079a, B:234:0x07a5, B:235:0x07ae, B:237:0x07f1, B:238:0x07f6, B:240:0x07fe, B:242:0x0807, B:243:0x080a, B:245:0x0817, B:247:0x0837, B:248:0x0842, B:250:0x0875, B:251:0x087a, B:252:0x0887, B:254:0x088f, B:256:0x0899, B:257:0x08a6, B:259:0x08b0, B:260:0x08bd, B:261:0x08ca, B:263:0x08d0, B:265:0x0900, B:266:0x0946, B:267:0x0950, B:268:0x095c, B:270:0x0962, B:279:0x09b0, B:280:0x09fe, B:282:0x0a0e, B:296:0x0a72, B:285:0x0a26, B:287:0x0a2a, B:273:0x096e, B:275:0x099a, B:291:0x0a43, B:292:0x0a5a, B:295:0x0a5d, B:173:0x05de, B:160:0x050b, B:102:0x0332, B:103:0x0339, B:105:0x033f, B:107:0x034b, B:49:0x0186, B:52:0x0192, B:54:0x01a9, B:60:0x01c7, B:68:0x0207, B:70:0x020d, B:72:0x021b, B:74:0x022c, B:77:0x0233, B:95:0x02da, B:97:0x02e5, B:78:0x0261, B:79:0x027e, B:81:0x0285, B:83:0x0296, B:94:0x02be, B:93:0x02ab, B:63:0x01d5, B:67:0x01fd), top: B:310:0x0124, inners: #0, #1, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0763 A[Catch: all -> 0x0aa5, TryCatch #3 {all -> 0x0aa5, blocks: (B:28:0x0124, B:30:0x0136, B:32:0x0142, B:33:0x014e, B:36:0x015c, B:38:0x0166, B:43:0x0172, B:99:0x031c, B:108:0x0352, B:110:0x0392, B:112:0x0398, B:113:0x03af, B:117:0x03c2, B:119:0x03d9, B:121:0x03df, B:122:0x03f6, B:127:0x0420, B:131:0x0441, B:132:0x0458, B:135:0x0469, B:138:0x0486, B:139:0x049a, B:141:0x04a4, B:143:0x04b3, B:145:0x04b9, B:146:0x04c2, B:147:0x04d0, B:149:0x04e5, B:151:0x04fa, B:163:0x0526, B:164:0x053b, B:166:0x0565, B:169:0x057d, B:172:0x05c0, B:174:0x05ec, B:176:0x062b, B:177:0x0630, B:179:0x0638, B:180:0x063d, B:182:0x0645, B:183:0x064a, B:185:0x0659, B:187:0x0661, B:188:0x0666, B:190:0x066f, B:191:0x0673, B:193:0x0680, B:194:0x0685, B:196:0x06ac, B:198:0x06b4, B:199:0x06b9, B:201:0x06c1, B:202:0x06c4, B:204:0x06dc, B:207:0x06e4, B:208:0x06fd, B:210:0x0703, B:212:0x0717, B:214:0x0723, B:216:0x0730, B:220:0x074a, B:221:0x075a, B:225:0x0763, B:226:0x0766, B:228:0x0784, B:230:0x0796, B:232:0x079a, B:234:0x07a5, B:235:0x07ae, B:237:0x07f1, B:238:0x07f6, B:240:0x07fe, B:242:0x0807, B:243:0x080a, B:245:0x0817, B:247:0x0837, B:248:0x0842, B:250:0x0875, B:251:0x087a, B:252:0x0887, B:254:0x088f, B:256:0x0899, B:257:0x08a6, B:259:0x08b0, B:260:0x08bd, B:261:0x08ca, B:263:0x08d0, B:265:0x0900, B:266:0x0946, B:267:0x0950, B:268:0x095c, B:270:0x0962, B:279:0x09b0, B:280:0x09fe, B:282:0x0a0e, B:296:0x0a72, B:285:0x0a26, B:287:0x0a2a, B:273:0x096e, B:275:0x099a, B:291:0x0a43, B:292:0x0a5a, B:295:0x0a5d, B:173:0x05de, B:160:0x050b, B:102:0x0332, B:103:0x0339, B:105:0x033f, B:107:0x034b, B:49:0x0186, B:52:0x0192, B:54:0x01a9, B:60:0x01c7, B:68:0x0207, B:70:0x020d, B:72:0x021b, B:74:0x022c, B:77:0x0233, B:95:0x02da, B:97:0x02e5, B:78:0x0261, B:79:0x027e, B:81:0x0285, B:83:0x0296, B:94:0x02be, B:93:0x02ab, B:63:0x01d5, B:67:0x01fd), top: B:310:0x0124, inners: #0, #1, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0784 A[Catch: all -> 0x0aa5, TryCatch #3 {all -> 0x0aa5, blocks: (B:28:0x0124, B:30:0x0136, B:32:0x0142, B:33:0x014e, B:36:0x015c, B:38:0x0166, B:43:0x0172, B:99:0x031c, B:108:0x0352, B:110:0x0392, B:112:0x0398, B:113:0x03af, B:117:0x03c2, B:119:0x03d9, B:121:0x03df, B:122:0x03f6, B:127:0x0420, B:131:0x0441, B:132:0x0458, B:135:0x0469, B:138:0x0486, B:139:0x049a, B:141:0x04a4, B:143:0x04b3, B:145:0x04b9, B:146:0x04c2, B:147:0x04d0, B:149:0x04e5, B:151:0x04fa, B:163:0x0526, B:164:0x053b, B:166:0x0565, B:169:0x057d, B:172:0x05c0, B:174:0x05ec, B:176:0x062b, B:177:0x0630, B:179:0x0638, B:180:0x063d, B:182:0x0645, B:183:0x064a, B:185:0x0659, B:187:0x0661, B:188:0x0666, B:190:0x066f, B:191:0x0673, B:193:0x0680, B:194:0x0685, B:196:0x06ac, B:198:0x06b4, B:199:0x06b9, B:201:0x06c1, B:202:0x06c4, B:204:0x06dc, B:207:0x06e4, B:208:0x06fd, B:210:0x0703, B:212:0x0717, B:214:0x0723, B:216:0x0730, B:220:0x074a, B:221:0x075a, B:225:0x0763, B:226:0x0766, B:228:0x0784, B:230:0x0796, B:232:0x079a, B:234:0x07a5, B:235:0x07ae, B:237:0x07f1, B:238:0x07f6, B:240:0x07fe, B:242:0x0807, B:243:0x080a, B:245:0x0817, B:247:0x0837, B:248:0x0842, B:250:0x0875, B:251:0x087a, B:252:0x0887, B:254:0x088f, B:256:0x0899, B:257:0x08a6, B:259:0x08b0, B:260:0x08bd, B:261:0x08ca, B:263:0x08d0, B:265:0x0900, B:266:0x0946, B:267:0x0950, B:268:0x095c, B:270:0x0962, B:279:0x09b0, B:280:0x09fe, B:282:0x0a0e, B:296:0x0a72, B:285:0x0a26, B:287:0x0a2a, B:273:0x096e, B:275:0x099a, B:291:0x0a43, B:292:0x0a5a, B:295:0x0a5d, B:173:0x05de, B:160:0x050b, B:102:0x0332, B:103:0x0339, B:105:0x033f, B:107:0x034b, B:49:0x0186, B:52:0x0192, B:54:0x01a9, B:60:0x01c7, B:68:0x0207, B:70:0x020d, B:72:0x021b, B:74:0x022c, B:77:0x0233, B:95:0x02da, B:97:0x02e5, B:78:0x0261, B:79:0x027e, B:81:0x0285, B:83:0x0296, B:94:0x02be, B:93:0x02ab, B:63:0x01d5, B:67:0x01fd), top: B:310:0x0124, inners: #0, #1, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:237:0x07f1 A[Catch: all -> 0x0aa5, TryCatch #3 {all -> 0x0aa5, blocks: (B:28:0x0124, B:30:0x0136, B:32:0x0142, B:33:0x014e, B:36:0x015c, B:38:0x0166, B:43:0x0172, B:99:0x031c, B:108:0x0352, B:110:0x0392, B:112:0x0398, B:113:0x03af, B:117:0x03c2, B:119:0x03d9, B:121:0x03df, B:122:0x03f6, B:127:0x0420, B:131:0x0441, B:132:0x0458, B:135:0x0469, B:138:0x0486, B:139:0x049a, B:141:0x04a4, B:143:0x04b3, B:145:0x04b9, B:146:0x04c2, B:147:0x04d0, B:149:0x04e5, B:151:0x04fa, B:163:0x0526, B:164:0x053b, B:166:0x0565, B:169:0x057d, B:172:0x05c0, B:174:0x05ec, B:176:0x062b, B:177:0x0630, B:179:0x0638, B:180:0x063d, B:182:0x0645, B:183:0x064a, B:185:0x0659, B:187:0x0661, B:188:0x0666, B:190:0x066f, B:191:0x0673, B:193:0x0680, B:194:0x0685, B:196:0x06ac, B:198:0x06b4, B:199:0x06b9, B:201:0x06c1, B:202:0x06c4, B:204:0x06dc, B:207:0x06e4, B:208:0x06fd, B:210:0x0703, B:212:0x0717, B:214:0x0723, B:216:0x0730, B:220:0x074a, B:221:0x075a, B:225:0x0763, B:226:0x0766, B:228:0x0784, B:230:0x0796, B:232:0x079a, B:234:0x07a5, B:235:0x07ae, B:237:0x07f1, B:238:0x07f6, B:240:0x07fe, B:242:0x0807, B:243:0x080a, B:245:0x0817, B:247:0x0837, B:248:0x0842, B:250:0x0875, B:251:0x087a, B:252:0x0887, B:254:0x088f, B:256:0x0899, B:257:0x08a6, B:259:0x08b0, B:260:0x08bd, B:261:0x08ca, B:263:0x08d0, B:265:0x0900, B:266:0x0946, B:267:0x0950, B:268:0x095c, B:270:0x0962, B:279:0x09b0, B:280:0x09fe, B:282:0x0a0e, B:296:0x0a72, B:285:0x0a26, B:287:0x0a2a, B:273:0x096e, B:275:0x099a, B:291:0x0a43, B:292:0x0a5a, B:295:0x0a5d, B:173:0x05de, B:160:0x050b, B:102:0x0332, B:103:0x0339, B:105:0x033f, B:107:0x034b, B:49:0x0186, B:52:0x0192, B:54:0x01a9, B:60:0x01c7, B:68:0x0207, B:70:0x020d, B:72:0x021b, B:74:0x022c, B:77:0x0233, B:95:0x02da, B:97:0x02e5, B:78:0x0261, B:79:0x027e, B:81:0x0285, B:83:0x0296, B:94:0x02be, B:93:0x02ab, B:63:0x01d5, B:67:0x01fd), top: B:310:0x0124, inners: #0, #1, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:240:0x07fe A[Catch: all -> 0x0aa5, TryCatch #3 {all -> 0x0aa5, blocks: (B:28:0x0124, B:30:0x0136, B:32:0x0142, B:33:0x014e, B:36:0x015c, B:38:0x0166, B:43:0x0172, B:99:0x031c, B:108:0x0352, B:110:0x0392, B:112:0x0398, B:113:0x03af, B:117:0x03c2, B:119:0x03d9, B:121:0x03df, B:122:0x03f6, B:127:0x0420, B:131:0x0441, B:132:0x0458, B:135:0x0469, B:138:0x0486, B:139:0x049a, B:141:0x04a4, B:143:0x04b3, B:145:0x04b9, B:146:0x04c2, B:147:0x04d0, B:149:0x04e5, B:151:0x04fa, B:163:0x0526, B:164:0x053b, B:166:0x0565, B:169:0x057d, B:172:0x05c0, B:174:0x05ec, B:176:0x062b, B:177:0x0630, B:179:0x0638, B:180:0x063d, B:182:0x0645, B:183:0x064a, B:185:0x0659, B:187:0x0661, B:188:0x0666, B:190:0x066f, B:191:0x0673, B:193:0x0680, B:194:0x0685, B:196:0x06ac, B:198:0x06b4, B:199:0x06b9, B:201:0x06c1, B:202:0x06c4, B:204:0x06dc, B:207:0x06e4, B:208:0x06fd, B:210:0x0703, B:212:0x0717, B:214:0x0723, B:216:0x0730, B:220:0x074a, B:221:0x075a, B:225:0x0763, B:226:0x0766, B:228:0x0784, B:230:0x0796, B:232:0x079a, B:234:0x07a5, B:235:0x07ae, B:237:0x07f1, B:238:0x07f6, B:240:0x07fe, B:242:0x0807, B:243:0x080a, B:245:0x0817, B:247:0x0837, B:248:0x0842, B:250:0x0875, B:251:0x087a, B:252:0x0887, B:254:0x088f, B:256:0x0899, B:257:0x08a6, B:259:0x08b0, B:260:0x08bd, B:261:0x08ca, B:263:0x08d0, B:265:0x0900, B:266:0x0946, B:267:0x0950, B:268:0x095c, B:270:0x0962, B:279:0x09b0, B:280:0x09fe, B:282:0x0a0e, B:296:0x0a72, B:285:0x0a26, B:287:0x0a2a, B:273:0x096e, B:275:0x099a, B:291:0x0a43, B:292:0x0a5a, B:295:0x0a5d, B:173:0x05de, B:160:0x050b, B:102:0x0332, B:103:0x0339, B:105:0x033f, B:107:0x034b, B:49:0x0186, B:52:0x0192, B:54:0x01a9, B:60:0x01c7, B:68:0x0207, B:70:0x020d, B:72:0x021b, B:74:0x022c, B:77:0x0233, B:95:0x02da, B:97:0x02e5, B:78:0x0261, B:79:0x027e, B:81:0x0285, B:83:0x0296, B:94:0x02be, B:93:0x02ab, B:63:0x01d5, B:67:0x01fd), top: B:310:0x0124, inners: #0, #1, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0817 A[Catch: all -> 0x0aa5, TryCatch #3 {all -> 0x0aa5, blocks: (B:28:0x0124, B:30:0x0136, B:32:0x0142, B:33:0x014e, B:36:0x015c, B:38:0x0166, B:43:0x0172, B:99:0x031c, B:108:0x0352, B:110:0x0392, B:112:0x0398, B:113:0x03af, B:117:0x03c2, B:119:0x03d9, B:121:0x03df, B:122:0x03f6, B:127:0x0420, B:131:0x0441, B:132:0x0458, B:135:0x0469, B:138:0x0486, B:139:0x049a, B:141:0x04a4, B:143:0x04b3, B:145:0x04b9, B:146:0x04c2, B:147:0x04d0, B:149:0x04e5, B:151:0x04fa, B:163:0x0526, B:164:0x053b, B:166:0x0565, B:169:0x057d, B:172:0x05c0, B:174:0x05ec, B:176:0x062b, B:177:0x0630, B:179:0x0638, B:180:0x063d, B:182:0x0645, B:183:0x064a, B:185:0x0659, B:187:0x0661, B:188:0x0666, B:190:0x066f, B:191:0x0673, B:193:0x0680, B:194:0x0685, B:196:0x06ac, B:198:0x06b4, B:199:0x06b9, B:201:0x06c1, B:202:0x06c4, B:204:0x06dc, B:207:0x06e4, B:208:0x06fd, B:210:0x0703, B:212:0x0717, B:214:0x0723, B:216:0x0730, B:220:0x074a, B:221:0x075a, B:225:0x0763, B:226:0x0766, B:228:0x0784, B:230:0x0796, B:232:0x079a, B:234:0x07a5, B:235:0x07ae, B:237:0x07f1, B:238:0x07f6, B:240:0x07fe, B:242:0x0807, B:243:0x080a, B:245:0x0817, B:247:0x0837, B:248:0x0842, B:250:0x0875, B:251:0x087a, B:252:0x0887, B:254:0x088f, B:256:0x0899, B:257:0x08a6, B:259:0x08b0, B:260:0x08bd, B:261:0x08ca, B:263:0x08d0, B:265:0x0900, B:266:0x0946, B:267:0x0950, B:268:0x095c, B:270:0x0962, B:279:0x09b0, B:280:0x09fe, B:282:0x0a0e, B:296:0x0a72, B:285:0x0a26, B:287:0x0a2a, B:273:0x096e, B:275:0x099a, B:291:0x0a43, B:292:0x0a5a, B:295:0x0a5d, B:173:0x05de, B:160:0x050b, B:102:0x0332, B:103:0x0339, B:105:0x033f, B:107:0x034b, B:49:0x0186, B:52:0x0192, B:54:0x01a9, B:60:0x01c7, B:68:0x0207, B:70:0x020d, B:72:0x021b, B:74:0x022c, B:77:0x0233, B:95:0x02da, B:97:0x02e5, B:78:0x0261, B:79:0x027e, B:81:0x0285, B:83:0x0296, B:94:0x02be, B:93:0x02ab, B:63:0x01d5, B:67:0x01fd), top: B:310:0x0124, inners: #0, #1, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:259:0x08b0 A[Catch: all -> 0x0aa5, TryCatch #3 {all -> 0x0aa5, blocks: (B:28:0x0124, B:30:0x0136, B:32:0x0142, B:33:0x014e, B:36:0x015c, B:38:0x0166, B:43:0x0172, B:99:0x031c, B:108:0x0352, B:110:0x0392, B:112:0x0398, B:113:0x03af, B:117:0x03c2, B:119:0x03d9, B:121:0x03df, B:122:0x03f6, B:127:0x0420, B:131:0x0441, B:132:0x0458, B:135:0x0469, B:138:0x0486, B:139:0x049a, B:141:0x04a4, B:143:0x04b3, B:145:0x04b9, B:146:0x04c2, B:147:0x04d0, B:149:0x04e5, B:151:0x04fa, B:163:0x0526, B:164:0x053b, B:166:0x0565, B:169:0x057d, B:172:0x05c0, B:174:0x05ec, B:176:0x062b, B:177:0x0630, B:179:0x0638, B:180:0x063d, B:182:0x0645, B:183:0x064a, B:185:0x0659, B:187:0x0661, B:188:0x0666, B:190:0x066f, B:191:0x0673, B:193:0x0680, B:194:0x0685, B:196:0x06ac, B:198:0x06b4, B:199:0x06b9, B:201:0x06c1, B:202:0x06c4, B:204:0x06dc, B:207:0x06e4, B:208:0x06fd, B:210:0x0703, B:212:0x0717, B:214:0x0723, B:216:0x0730, B:220:0x074a, B:221:0x075a, B:225:0x0763, B:226:0x0766, B:228:0x0784, B:230:0x0796, B:232:0x079a, B:234:0x07a5, B:235:0x07ae, B:237:0x07f1, B:238:0x07f6, B:240:0x07fe, B:242:0x0807, B:243:0x080a, B:245:0x0817, B:247:0x0837, B:248:0x0842, B:250:0x0875, B:251:0x087a, B:252:0x0887, B:254:0x088f, B:256:0x0899, B:257:0x08a6, B:259:0x08b0, B:260:0x08bd, B:261:0x08ca, B:263:0x08d0, B:265:0x0900, B:266:0x0946, B:267:0x0950, B:268:0x095c, B:270:0x0962, B:279:0x09b0, B:280:0x09fe, B:282:0x0a0e, B:296:0x0a72, B:285:0x0a26, B:287:0x0a2a, B:273:0x096e, B:275:0x099a, B:291:0x0a43, B:292:0x0a5a, B:295:0x0a5d, B:173:0x05de, B:160:0x050b, B:102:0x0332, B:103:0x0339, B:105:0x033f, B:107:0x034b, B:49:0x0186, B:52:0x0192, B:54:0x01a9, B:60:0x01c7, B:68:0x0207, B:70:0x020d, B:72:0x021b, B:74:0x022c, B:77:0x0233, B:95:0x02da, B:97:0x02e5, B:78:0x0261, B:79:0x027e, B:81:0x0285, B:83:0x0296, B:94:0x02be, B:93:0x02ab, B:63:0x01d5, B:67:0x01fd), top: B:310:0x0124, inners: #0, #1, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:263:0x08d0 A[Catch: all -> 0x0aa5, TRY_LEAVE, TryCatch #3 {all -> 0x0aa5, blocks: (B:28:0x0124, B:30:0x0136, B:32:0x0142, B:33:0x014e, B:36:0x015c, B:38:0x0166, B:43:0x0172, B:99:0x031c, B:108:0x0352, B:110:0x0392, B:112:0x0398, B:113:0x03af, B:117:0x03c2, B:119:0x03d9, B:121:0x03df, B:122:0x03f6, B:127:0x0420, B:131:0x0441, B:132:0x0458, B:135:0x0469, B:138:0x0486, B:139:0x049a, B:141:0x04a4, B:143:0x04b3, B:145:0x04b9, B:146:0x04c2, B:147:0x04d0, B:149:0x04e5, B:151:0x04fa, B:163:0x0526, B:164:0x053b, B:166:0x0565, B:169:0x057d, B:172:0x05c0, B:174:0x05ec, B:176:0x062b, B:177:0x0630, B:179:0x0638, B:180:0x063d, B:182:0x0645, B:183:0x064a, B:185:0x0659, B:187:0x0661, B:188:0x0666, B:190:0x066f, B:191:0x0673, B:193:0x0680, B:194:0x0685, B:196:0x06ac, B:198:0x06b4, B:199:0x06b9, B:201:0x06c1, B:202:0x06c4, B:204:0x06dc, B:207:0x06e4, B:208:0x06fd, B:210:0x0703, B:212:0x0717, B:214:0x0723, B:216:0x0730, B:220:0x074a, B:221:0x075a, B:225:0x0763, B:226:0x0766, B:228:0x0784, B:230:0x0796, B:232:0x079a, B:234:0x07a5, B:235:0x07ae, B:237:0x07f1, B:238:0x07f6, B:240:0x07fe, B:242:0x0807, B:243:0x080a, B:245:0x0817, B:247:0x0837, B:248:0x0842, B:250:0x0875, B:251:0x087a, B:252:0x0887, B:254:0x088f, B:256:0x0899, B:257:0x08a6, B:259:0x08b0, B:260:0x08bd, B:261:0x08ca, B:263:0x08d0, B:265:0x0900, B:266:0x0946, B:267:0x0950, B:268:0x095c, B:270:0x0962, B:279:0x09b0, B:280:0x09fe, B:282:0x0a0e, B:296:0x0a72, B:285:0x0a26, B:287:0x0a2a, B:273:0x096e, B:275:0x099a, B:291:0x0a43, B:292:0x0a5a, B:295:0x0a5d, B:173:0x05de, B:160:0x050b, B:102:0x0332, B:103:0x0339, B:105:0x033f, B:107:0x034b, B:49:0x0186, B:52:0x0192, B:54:0x01a9, B:60:0x01c7, B:68:0x0207, B:70:0x020d, B:72:0x021b, B:74:0x022c, B:77:0x0233, B:95:0x02da, B:97:0x02e5, B:78:0x0261, B:79:0x027e, B:81:0x0285, B:83:0x0296, B:94:0x02be, B:93:0x02ab, B:63:0x01d5, B:67:0x01fd), top: B:310:0x0124, inners: #0, #1, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0962 A[Catch: all -> 0x0aa5, TryCatch #3 {all -> 0x0aa5, blocks: (B:28:0x0124, B:30:0x0136, B:32:0x0142, B:33:0x014e, B:36:0x015c, B:38:0x0166, B:43:0x0172, B:99:0x031c, B:108:0x0352, B:110:0x0392, B:112:0x0398, B:113:0x03af, B:117:0x03c2, B:119:0x03d9, B:121:0x03df, B:122:0x03f6, B:127:0x0420, B:131:0x0441, B:132:0x0458, B:135:0x0469, B:138:0x0486, B:139:0x049a, B:141:0x04a4, B:143:0x04b3, B:145:0x04b9, B:146:0x04c2, B:147:0x04d0, B:149:0x04e5, B:151:0x04fa, B:163:0x0526, B:164:0x053b, B:166:0x0565, B:169:0x057d, B:172:0x05c0, B:174:0x05ec, B:176:0x062b, B:177:0x0630, B:179:0x0638, B:180:0x063d, B:182:0x0645, B:183:0x064a, B:185:0x0659, B:187:0x0661, B:188:0x0666, B:190:0x066f, B:191:0x0673, B:193:0x0680, B:194:0x0685, B:196:0x06ac, B:198:0x06b4, B:199:0x06b9, B:201:0x06c1, B:202:0x06c4, B:204:0x06dc, B:207:0x06e4, B:208:0x06fd, B:210:0x0703, B:212:0x0717, B:214:0x0723, B:216:0x0730, B:220:0x074a, B:221:0x075a, B:225:0x0763, B:226:0x0766, B:228:0x0784, B:230:0x0796, B:232:0x079a, B:234:0x07a5, B:235:0x07ae, B:237:0x07f1, B:238:0x07f6, B:240:0x07fe, B:242:0x0807, B:243:0x080a, B:245:0x0817, B:247:0x0837, B:248:0x0842, B:250:0x0875, B:251:0x087a, B:252:0x0887, B:254:0x088f, B:256:0x0899, B:257:0x08a6, B:259:0x08b0, B:260:0x08bd, B:261:0x08ca, B:263:0x08d0, B:265:0x0900, B:266:0x0946, B:267:0x0950, B:268:0x095c, B:270:0x0962, B:279:0x09b0, B:280:0x09fe, B:282:0x0a0e, B:296:0x0a72, B:285:0x0a26, B:287:0x0a2a, B:273:0x096e, B:275:0x099a, B:291:0x0a43, B:292:0x0a5a, B:295:0x0a5d, B:173:0x05de, B:160:0x050b, B:102:0x0332, B:103:0x0339, B:105:0x033f, B:107:0x034b, B:49:0x0186, B:52:0x0192, B:54:0x01a9, B:60:0x01c7, B:68:0x0207, B:70:0x020d, B:72:0x021b, B:74:0x022c, B:77:0x0233, B:95:0x02da, B:97:0x02e5, B:78:0x0261, B:79:0x027e, B:81:0x0285, B:83:0x0296, B:94:0x02be, B:93:0x02ab, B:63:0x01d5, B:67:0x01fd), top: B:310:0x0124, inners: #0, #1, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:282:0x0a0e A[Catch: SQLiteException -> 0x0a29, all -> 0x0aa5, TRY_LEAVE, TryCatch #0 {SQLiteException -> 0x0a29, blocks: (B:280:0x09fe, B:282:0x0a0e), top: B:304:0x09fe, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0a24  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x096e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0192 A[Catch: all -> 0x0aa5, TRY_ENTER, TryCatch #3 {all -> 0x0aa5, blocks: (B:28:0x0124, B:30:0x0136, B:32:0x0142, B:33:0x014e, B:36:0x015c, B:38:0x0166, B:43:0x0172, B:99:0x031c, B:108:0x0352, B:110:0x0392, B:112:0x0398, B:113:0x03af, B:117:0x03c2, B:119:0x03d9, B:121:0x03df, B:122:0x03f6, B:127:0x0420, B:131:0x0441, B:132:0x0458, B:135:0x0469, B:138:0x0486, B:139:0x049a, B:141:0x04a4, B:143:0x04b3, B:145:0x04b9, B:146:0x04c2, B:147:0x04d0, B:149:0x04e5, B:151:0x04fa, B:163:0x0526, B:164:0x053b, B:166:0x0565, B:169:0x057d, B:172:0x05c0, B:174:0x05ec, B:176:0x062b, B:177:0x0630, B:179:0x0638, B:180:0x063d, B:182:0x0645, B:183:0x064a, B:185:0x0659, B:187:0x0661, B:188:0x0666, B:190:0x066f, B:191:0x0673, B:193:0x0680, B:194:0x0685, B:196:0x06ac, B:198:0x06b4, B:199:0x06b9, B:201:0x06c1, B:202:0x06c4, B:204:0x06dc, B:207:0x06e4, B:208:0x06fd, B:210:0x0703, B:212:0x0717, B:214:0x0723, B:216:0x0730, B:220:0x074a, B:221:0x075a, B:225:0x0763, B:226:0x0766, B:228:0x0784, B:230:0x0796, B:232:0x079a, B:234:0x07a5, B:235:0x07ae, B:237:0x07f1, B:238:0x07f6, B:240:0x07fe, B:242:0x0807, B:243:0x080a, B:245:0x0817, B:247:0x0837, B:248:0x0842, B:250:0x0875, B:251:0x087a, B:252:0x0887, B:254:0x088f, B:256:0x0899, B:257:0x08a6, B:259:0x08b0, B:260:0x08bd, B:261:0x08ca, B:263:0x08d0, B:265:0x0900, B:266:0x0946, B:267:0x0950, B:268:0x095c, B:270:0x0962, B:279:0x09b0, B:280:0x09fe, B:282:0x0a0e, B:296:0x0a72, B:285:0x0a26, B:287:0x0a2a, B:273:0x096e, B:275:0x099a, B:291:0x0a43, B:292:0x0a5a, B:295:0x0a5d, B:173:0x05de, B:160:0x050b, B:102:0x0332, B:103:0x0339, B:105:0x033f, B:107:0x034b, B:49:0x0186, B:52:0x0192, B:54:0x01a9, B:60:0x01c7, B:68:0x0207, B:70:0x020d, B:72:0x021b, B:74:0x022c, B:77:0x0233, B:95:0x02da, B:97:0x02e5, B:78:0x0261, B:79:0x027e, B:81:0x0285, B:83:0x0296, B:94:0x02be, B:93:0x02ab, B:63:0x01d5, B:67:0x01fd), top: B:310:0x0124, inners: #0, #1, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x020d A[Catch: all -> 0x0aa5, TryCatch #3 {all -> 0x0aa5, blocks: (B:28:0x0124, B:30:0x0136, B:32:0x0142, B:33:0x014e, B:36:0x015c, B:38:0x0166, B:43:0x0172, B:99:0x031c, B:108:0x0352, B:110:0x0392, B:112:0x0398, B:113:0x03af, B:117:0x03c2, B:119:0x03d9, B:121:0x03df, B:122:0x03f6, B:127:0x0420, B:131:0x0441, B:132:0x0458, B:135:0x0469, B:138:0x0486, B:139:0x049a, B:141:0x04a4, B:143:0x04b3, B:145:0x04b9, B:146:0x04c2, B:147:0x04d0, B:149:0x04e5, B:151:0x04fa, B:163:0x0526, B:164:0x053b, B:166:0x0565, B:169:0x057d, B:172:0x05c0, B:174:0x05ec, B:176:0x062b, B:177:0x0630, B:179:0x0638, B:180:0x063d, B:182:0x0645, B:183:0x064a, B:185:0x0659, B:187:0x0661, B:188:0x0666, B:190:0x066f, B:191:0x0673, B:193:0x0680, B:194:0x0685, B:196:0x06ac, B:198:0x06b4, B:199:0x06b9, B:201:0x06c1, B:202:0x06c4, B:204:0x06dc, B:207:0x06e4, B:208:0x06fd, B:210:0x0703, B:212:0x0717, B:214:0x0723, B:216:0x0730, B:220:0x074a, B:221:0x075a, B:225:0x0763, B:226:0x0766, B:228:0x0784, B:230:0x0796, B:232:0x079a, B:234:0x07a5, B:235:0x07ae, B:237:0x07f1, B:238:0x07f6, B:240:0x07fe, B:242:0x0807, B:243:0x080a, B:245:0x0817, B:247:0x0837, B:248:0x0842, B:250:0x0875, B:251:0x087a, B:252:0x0887, B:254:0x088f, B:256:0x0899, B:257:0x08a6, B:259:0x08b0, B:260:0x08bd, B:261:0x08ca, B:263:0x08d0, B:265:0x0900, B:266:0x0946, B:267:0x0950, B:268:0x095c, B:270:0x0962, B:279:0x09b0, B:280:0x09fe, B:282:0x0a0e, B:296:0x0a72, B:285:0x0a26, B:287:0x0a2a, B:273:0x096e, B:275:0x099a, B:291:0x0a43, B:292:0x0a5a, B:295:0x0a5d, B:173:0x05de, B:160:0x050b, B:102:0x0332, B:103:0x0339, B:105:0x033f, B:107:0x034b, B:49:0x0186, B:52:0x0192, B:54:0x01a9, B:60:0x01c7, B:68:0x0207, B:70:0x020d, B:72:0x021b, B:74:0x022c, B:77:0x0233, B:95:0x02da, B:97:0x02e5, B:78:0x0261, B:79:0x027e, B:81:0x0285, B:83:0x0296, B:94:0x02be, B:93:0x02ab, B:63:0x01d5, B:67:0x01fd), top: B:310:0x0124, inners: #0, #1, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02e5 A[Catch: all -> 0x0aa5, TryCatch #3 {all -> 0x0aa5, blocks: (B:28:0x0124, B:30:0x0136, B:32:0x0142, B:33:0x014e, B:36:0x015c, B:38:0x0166, B:43:0x0172, B:99:0x031c, B:108:0x0352, B:110:0x0392, B:112:0x0398, B:113:0x03af, B:117:0x03c2, B:119:0x03d9, B:121:0x03df, B:122:0x03f6, B:127:0x0420, B:131:0x0441, B:132:0x0458, B:135:0x0469, B:138:0x0486, B:139:0x049a, B:141:0x04a4, B:143:0x04b3, B:145:0x04b9, B:146:0x04c2, B:147:0x04d0, B:149:0x04e5, B:151:0x04fa, B:163:0x0526, B:164:0x053b, B:166:0x0565, B:169:0x057d, B:172:0x05c0, B:174:0x05ec, B:176:0x062b, B:177:0x0630, B:179:0x0638, B:180:0x063d, B:182:0x0645, B:183:0x064a, B:185:0x0659, B:187:0x0661, B:188:0x0666, B:190:0x066f, B:191:0x0673, B:193:0x0680, B:194:0x0685, B:196:0x06ac, B:198:0x06b4, B:199:0x06b9, B:201:0x06c1, B:202:0x06c4, B:204:0x06dc, B:207:0x06e4, B:208:0x06fd, B:210:0x0703, B:212:0x0717, B:214:0x0723, B:216:0x0730, B:220:0x074a, B:221:0x075a, B:225:0x0763, B:226:0x0766, B:228:0x0784, B:230:0x0796, B:232:0x079a, B:234:0x07a5, B:235:0x07ae, B:237:0x07f1, B:238:0x07f6, B:240:0x07fe, B:242:0x0807, B:243:0x080a, B:245:0x0817, B:247:0x0837, B:248:0x0842, B:250:0x0875, B:251:0x087a, B:252:0x0887, B:254:0x088f, B:256:0x0899, B:257:0x08a6, B:259:0x08b0, B:260:0x08bd, B:261:0x08ca, B:263:0x08d0, B:265:0x0900, B:266:0x0946, B:267:0x0950, B:268:0x095c, B:270:0x0962, B:279:0x09b0, B:280:0x09fe, B:282:0x0a0e, B:296:0x0a72, B:285:0x0a26, B:287:0x0a2a, B:273:0x096e, B:275:0x099a, B:291:0x0a43, B:292:0x0a5a, B:295:0x0a5d, B:173:0x05de, B:160:0x050b, B:102:0x0332, B:103:0x0339, B:105:0x033f, B:107:0x034b, B:49:0x0186, B:52:0x0192, B:54:0x01a9, B:60:0x01c7, B:68:0x0207, B:70:0x020d, B:72:0x021b, B:74:0x022c, B:77:0x0233, B:95:0x02da, B:97:0x02e5, B:78:0x0261, B:79:0x027e, B:81:0x0285, B:83:0x0296, B:94:0x02be, B:93:0x02ab, B:63:0x01d5, B:67:0x01fd), top: B:310:0x0124, inners: #0, #1, #10 }] */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final void w(com.google.android.gms.measurement.internal.zzaw r35, com.google.android.gms.measurement.internal.zzq r36) {
        /*
            Method dump skipped, instructions count: 2740
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzll.w(com.google.android.gms.measurement.internal.zzaw, com.google.android.gms.measurement.internal.zzq):void");
    }

    @VisibleForTesting
    @WorkerThread
    final boolean x() {
        zzey zzk;
        String str;
        zzaz().zzg();
        FileLock fileLock = this.zzw;
        if (fileLock != null && fileLock.isValid()) {
            zzay().zzj().zza("Storage concurrent access okay");
            return true;
        }
        this.zze.f6809a.zzf();
        try {
            FileChannel channel = new RandomAccessFile(new File(this.zzn.zzau().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
            this.zzx = channel;
            FileLock tryLock = channel.tryLock();
            this.zzw = tryLock;
            if (tryLock != null) {
                zzay().zzj().zza("Storage concurrent access okay");
                return true;
            }
            zzay().zzd().zza("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e2) {
            e = e2;
            zzk = zzay().zzd();
            str = "Failed to acquire storage lock";
            zzk.zzb(str, e);
            return false;
        } catch (IOException e3) {
            e = e3;
            zzk = zzay().zzd();
            str = "Failed to access storage lock file";
            zzk.zzb(str, e);
            return false;
        } catch (OverlappingFileLockException e4) {
            e = e4;
            zzk = zzay().zzk();
            str = "Storage lock already acquired";
            zzk.zzb(str, e);
            return false;
        }
    }

    final long z() {
        long currentTimeMillis = zzav().currentTimeMillis();
        zzkg zzkgVar = this.zzk;
        zzkgVar.a();
        zzkgVar.zzg();
        long zza = zzkgVar.zze.zza();
        if (zza == 0) {
            zza = zzkgVar.f6809a.zzv().i().nextInt(86400000) + 1;
            zzkgVar.zze.zzb(zza);
        }
        return ((((currentTimeMillis + zza) / 1000) / 60) / 60) / 24;
    }

    @WorkerThread
    public final void zzQ(String str, zziw zziwVar) {
        zzaz().zzg();
        String str2 = this.zzE;
        if (str2 == null || str2.equals(str) || zziwVar != null) {
            this.zzE = str;
            this.zzD = zziwVar;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzhf
    public final Context zzau() {
        return this.zzn.zzau();
    }

    @Override // com.google.android.gms.measurement.internal.zzhf
    public final Clock zzav() {
        return ((zzgk) Preconditions.checkNotNull(this.zzn)).zzav();
    }

    @Override // com.google.android.gms.measurement.internal.zzhf
    public final zzab zzaw() {
        throw null;
    }

    @Override // com.google.android.gms.measurement.internal.zzhf
    public final zzfa zzay() {
        return ((zzgk) Preconditions.checkNotNull(this.zzn)).zzay();
    }

    @Override // com.google.android.gms.measurement.internal.zzhf
    public final zzgh zzaz() {
        return ((zzgk) Preconditions.checkNotNull(this.zzn)).zzaz();
    }

    public final zzaa zzf() {
        zzaa zzaaVar = this.zzh;
        zzak(zzaaVar);
        return zzaaVar;
    }

    public final zzag zzg() {
        return ((zzgk) Preconditions.checkNotNull(this.zzn)).zzf();
    }

    public final zzam zzi() {
        zzam zzamVar = this.zze;
        zzak(zzamVar);
        return zzamVar;
    }

    public final zzev zzj() {
        return this.zzn.zzj();
    }

    public final zzfg zzl() {
        zzfg zzfgVar = this.zzd;
        zzak(zzfgVar);
        return zzfgVar;
    }

    public final zzfi zzm() {
        zzfi zzfiVar = this.zzf;
        if (zzfiVar != null) {
            return zzfiVar;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    public final zzgb zzo() {
        zzgb zzgbVar = this.zzc;
        zzak(zzgbVar);
        return zzgbVar;
    }

    public final zziu zzr() {
        zziu zziuVar = this.zzj;
        zzak(zziuVar);
        return zziuVar;
    }

    public final zzkg zzs() {
        return this.zzk;
    }

    public final zzln zzu() {
        zzln zzlnVar = this.zzi;
        zzak(zzlnVar);
        return zzlnVar;
    }

    public final zzlt zzv() {
        return ((zzgk) Preconditions.checkNotNull(this.zzn)).zzv();
    }
}
