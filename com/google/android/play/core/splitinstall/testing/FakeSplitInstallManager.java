package com.google.android.play.core.splitinstall.testing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.internal.zzaf;
import com.google.android.play.core.internal.zzcf;
import com.google.android.play.core.internal.zzcj;
import com.google.android.play.core.internal.zzco;
import com.google.android.play.core.splitinstall.SplitInstallException;
import com.google.android.play.core.splitinstall.SplitInstallManager;
import com.google.android.play.core.splitinstall.SplitInstallRequest;
import com.google.android.play.core.splitinstall.SplitInstallSessionState;
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener;
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes2.dex */
public class FakeSplitInstallManager implements SplitInstallManager {
    public static final /* synthetic */ int zza = 0;
    private static final long zzb = TimeUnit.SECONDS.toMillis(1);
    private final Handler zzc;
    private final Context zzd;
    private final com.google.android.play.core.splitinstall.zzs zze;
    private final zzco zzf;
    private final zzcf zzg;
    private final zzaf zzh;
    private final zzaf zzi;
    private final Executor zzj;
    private final com.google.android.play.core.splitinstall.zzg zzk;
    private final File zzl;
    private final AtomicReference zzm;
    private final Set zzn;
    private final Set zzo;
    private final AtomicBoolean zzp;
    private final zzd zzq;

    @Deprecated
    public FakeSplitInstallManager(Context context, File file) {
        this(context, file, new com.google.android.play.core.splitinstall.zzs(context, context.getPackageName()), zzj.zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FakeSplitInstallManager(Context context, @Nullable File file, com.google.android.play.core.splitinstall.zzs zzsVar, zzco zzcoVar) {
        Executor zza2 = com.google.android.play.core.splitcompat.zzd.zza();
        zzcf zzcfVar = new zzcf(context);
        zzd zzdVar = zzd.zza;
        this.zzc = new Handler(Looper.getMainLooper());
        this.zzm = new AtomicReference();
        this.zzn = Collections.synchronizedSet(new HashSet());
        this.zzo = Collections.synchronizedSet(new HashSet());
        this.zzp = new AtomicBoolean(false);
        this.zzd = context;
        this.zzl = file;
        this.zze = zzsVar;
        this.zzf = zzcoVar;
        this.zzj = zza2;
        this.zzg = zzcfVar;
        this.zzq = zzdVar;
        this.zzi = new zzaf();
        this.zzh = new zzaf();
        this.zzk = com.google.android.play.core.splitinstall.zzo.INSTANCE;
    }

    private final com.google.android.play.core.splitinstall.zzk zzk() {
        com.google.android.play.core.splitinstall.zzk zza2 = this.zze.zza();
        if (zza2 != null) {
            return zza2;
        }
        throw new IllegalStateException("Language information could not be found. Make sure you are using the target application context, not the tests context, and the app is built as a bundle.");
    }

    @Nullable
    private final SplitInstallSessionState zzl() {
        return (SplitInstallSessionState) this.zzm.get();
    }

    @Nullable
    private final synchronized SplitInstallSessionState zzm(zzp zzpVar) {
        SplitInstallSessionState zzl = zzl();
        SplitInstallSessionState zza2 = zzpVar.zza(zzl);
        if (this.zzm.compareAndSet(zzl, zza2)) {
            return zza2;
        }
        return null;
    }

    private final Task zzn(@SplitInstallErrorCode final int i2) {
        zzm(new zzp() { // from class: com.google.android.play.core.splitinstall.testing.zzg
            @Override // com.google.android.play.core.splitinstall.testing.zzp
            public final SplitInstallSessionState zza(SplitInstallSessionState splitInstallSessionState) {
                int i3 = i2;
                int i4 = FakeSplitInstallManager.zza;
                if (splitInstallSessionState == null) {
                    return null;
                }
                return SplitInstallSessionState.create(splitInstallSessionState.sessionId(), 6, i3, splitInstallSessionState.bytesDownloaded(), splitInstallSessionState.totalBytesToDownload(), splitInstallSessionState.moduleNames(), splitInstallSessionState.languages());
            }
        });
        return Tasks.zza(new SplitInstallException(i2));
    }

    private static String zzo(String str) {
        return str.split("\\.config\\.", 2)[0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzp(List list, List list2, List list3, long j2, boolean z) {
        this.zzk.zza().zzd(list, new zzo(this, list2, list3, j2, z, list));
    }

    private final void zzq(final SplitInstallSessionState splitInstallSessionState) {
        this.zzc.post(new Runnable() { // from class: com.google.android.play.core.splitinstall.testing.zzm
            @Override // java.lang.Runnable
            public final void run() {
                FakeSplitInstallManager.this.e(splitInstallSessionState);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzr(List list, List list2, long j2) {
        this.zzn.addAll(list);
        this.zzo.addAll(list2);
        Long valueOf = Long.valueOf(j2);
        zzs(5, 0, valueOf, valueOf, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zzs(final int i2, final int i3, @Nullable final Long l2, @Nullable final Long l3, @Nullable final List list, @Nullable final Integer num, @Nullable final List list2) {
        SplitInstallSessionState zzm = zzm(new zzp() { // from class: com.google.android.play.core.splitinstall.testing.zzi
            @Override // com.google.android.play.core.splitinstall.testing.zzp
            public final SplitInstallSessionState zza(SplitInstallSessionState splitInstallSessionState) {
                Integer num2 = num;
                int i4 = i2;
                int i5 = i3;
                Long l4 = l2;
                Long l5 = l3;
                List<String> list3 = list;
                List<String> list4 = list2;
                int i6 = FakeSplitInstallManager.zza;
                SplitInstallSessionState create = splitInstallSessionState == null ? SplitInstallSessionState.create(0, 0, 0, 0L, 0L, new ArrayList(), new ArrayList()) : splitInstallSessionState;
                return SplitInstallSessionState.create(num2 == null ? create.sessionId() : num2.intValue(), i4, i5, l4 == null ? create.bytesDownloaded() : l4.longValue(), l5 == null ? create.totalBytesToDownload() : l5.longValue(), list3 == null ? create.moduleNames() : list3, list4 == null ? create.languages() : list4);
            }
        });
        if (zzm != null) {
            zzq(zzm);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File a() {
        return this.zzl;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> cancelInstall(final int i2) {
        try {
            SplitInstallSessionState zzm = zzm(new zzp() { // from class: com.google.android.play.core.splitinstall.testing.zzf
                @Override // com.google.android.play.core.splitinstall.testing.zzp
                public final SplitInstallSessionState zza(SplitInstallSessionState splitInstallSessionState) {
                    int status;
                    int i3 = i2;
                    int i4 = FakeSplitInstallManager.zza;
                    if (splitInstallSessionState != null && i3 == splitInstallSessionState.sessionId() && ((status = splitInstallSessionState.status()) == 1 || status == 2 || status == 8 || status == 9 || status == 7)) {
                        return SplitInstallSessionState.create(i3, 7, splitInstallSessionState.errorCode(), splitInstallSessionState.bytesDownloaded(), splitInstallSessionState.totalBytesToDownload(), splitInstallSessionState.moduleNames(), splitInstallSessionState.languages());
                    }
                    throw new SplitInstallException(-3);
                }
            });
            if (zzm != null) {
                zzq(zzm);
            }
            return Tasks.zzb(null);
        } catch (SplitInstallException e2) {
            return Tasks.zza(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void d(final long j2, final List list, final List list2, final List list3) {
        long j3 = j2 / 3;
        long j4 = 0;
        for (int i2 = 0; i2 < 3; i2++) {
            j4 = Math.min(j2, j4 + j3);
            zzs(2, 0, Long.valueOf(j4), Long.valueOf(j2), null, null, null);
            SystemClock.sleep(zzb);
            SplitInstallSessionState zzl = zzl();
            if (zzl.status() == 9 || zzl.status() == 7 || zzl.status() == 6) {
                return;
            }
        }
        this.zzj.execute(new Runnable() { // from class: com.google.android.play.core.splitinstall.testing.zze
            @Override // java.lang.Runnable
            public final void run() {
                FakeSplitInstallManager.this.f(list, list2, list3, j2);
            }
        });
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredInstall(List<String> list) {
        return Tasks.zza(new SplitInstallException(-5));
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredLanguageInstall(List<Locale> list) {
        return Tasks.zza(new SplitInstallException(-5));
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredLanguageUninstall(List<Locale> list) {
        return Tasks.zza(new SplitInstallException(-5));
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredUninstall(List<String> list) {
        return Tasks.zza(new SplitInstallException(-5));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void e(SplitInstallSessionState splitInstallSessionState) {
        this.zzh.zzc(splitInstallSessionState);
        this.zzi.zzc(splitInstallSessionState);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void f(List list, List list2, List list3, long j2) {
        if (this.zzp.get()) {
            zzs(6, -6, null, null, null, null, null);
        } else if (this.zzk.zza() != null) {
            zzp(list, list2, list3, j2, false);
        } else {
            zzr(list2, list3, j2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void g(List list, final List list2) {
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            File file = (File) it.next();
            String zza2 = zzcj.zza(file);
            Uri fromFile = Uri.fromFile(file);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(fromFile, this.zzd.getContentResolver().getType(fromFile));
            intent.addFlags(1);
            intent.putExtra("module_name", zzo(zza2));
            intent.putExtra("split_id", zza2);
            arrayList.add(intent);
            arrayList2.add(zzo(zzcj.zza(file)));
        }
        SplitInstallSessionState zzl = zzl();
        if (zzl == null) {
            return;
        }
        final long j2 = zzl.totalBytesToDownload();
        this.zzj.execute(new Runnable() { // from class: com.google.android.play.core.splitinstall.testing.zzl
            @Override // java.lang.Runnable
            public final void run() {
                FakeSplitInstallManager.this.d(j2, arrayList, arrayList2, list2);
            }
        });
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Set<String> getInstalledLanguages() {
        HashSet hashSet = new HashSet();
        if (this.zze.zzd() != null) {
            hashSet.addAll(this.zze.zzd());
        }
        hashSet.addAll(this.zzo);
        return hashSet;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Set<String> getInstalledModules() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(this.zze.zzc());
        hashSet.addAll(this.zzn);
        return hashSet;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<SplitInstallSessionState> getSessionState(int i2) {
        SplitInstallSessionState zzl = zzl();
        return (zzl == null || zzl.sessionId() != i2) ? Tasks.zza(new SplitInstallException(-4)) : Tasks.zzb(zzl);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<List<SplitInstallSessionState>> getSessionStates() {
        SplitInstallSessionState zzl = zzl();
        return Tasks.zzb(zzl != null ? Collections.singletonList(zzl) : Collections.emptyList());
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void registerListener(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzi.zza(splitInstallStateUpdatedListener);
    }

    public void setShouldNetworkError(boolean z) {
        this.zzp.set(z);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final boolean startConfirmationDialogForResult(SplitInstallSessionState splitInstallSessionState, Activity activity, int i2) {
        return false;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final boolean startConfirmationDialogForResult(SplitInstallSessionState splitInstallSessionState, IntentSenderForResultStarter intentSenderForResultStarter, int i2) {
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x0127, code lost:
        if (r4.contains(r5) == false) goto L51;
     */
    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Task<Integer> startInstall(final SplitInstallRequest splitInstallRequest) {
        Integer zza2;
        File[] fileArr;
        int i2;
        Iterator it;
        try {
            SplitInstallSessionState zzm = zzm(new zzp() { // from class: com.google.android.play.core.splitinstall.testing.zzh
                @Override // com.google.android.play.core.splitinstall.testing.zzp
                public final SplitInstallSessionState zza(SplitInstallSessionState splitInstallSessionState) {
                    SplitInstallRequest splitInstallRequest2 = SplitInstallRequest.this;
                    int i3 = FakeSplitInstallManager.zza;
                    if (splitInstallSessionState == null || splitInstallSessionState.hasTerminalStatus()) {
                        return SplitInstallSessionState.create(splitInstallSessionState != null ? 1 + splitInstallSessionState.sessionId() : 1, 1, 0, 0L, 0L, splitInstallRequest2.getModuleNames(), new ArrayList());
                    }
                    throw new SplitInstallException(-1);
                }
            });
            if (zzm != null) {
                int sessionId = zzm.sessionId();
                final ArrayList arrayList = new ArrayList();
                for (Locale locale : splitInstallRequest.getLanguages()) {
                    arrayList.add(locale.getLanguage());
                }
                HashSet hashSet = new HashSet();
                final ArrayList arrayList2 = new ArrayList();
                File[] listFiles = this.zzl.listFiles(zzk.zza);
                if (listFiles == null) {
                    return zzn(-5);
                }
                int length = listFiles.length;
                int i3 = 0;
                long j2 = 0;
                while (i3 < length) {
                    File file = listFiles[i3];
                    String zza3 = zzcj.zza(file);
                    String zzo = zzo(zza3);
                    hashSet.add(zza3);
                    if (splitInstallRequest.getModuleNames().contains(zzo)) {
                        String zzo2 = zzo(zza3);
                        HashSet hashSet2 = new HashSet(this.zzg.zza());
                        Map zza4 = zzk().zza(Arrays.asList(zzo2));
                        HashSet hashSet3 = new HashSet();
                        for (Set set : zza4.values()) {
                            hashSet3.addAll(set);
                            listFiles = listFiles;
                        }
                        fileArr = listFiles;
                        HashSet hashSet4 = new HashSet();
                        Iterator it2 = hashSet2.iterator();
                        while (it2.hasNext()) {
                            String str = (String) it2.next();
                            int i4 = length;
                            if (str.contains("_")) {
                                it = it2;
                                str = str.split("_", -1)[0];
                            } else {
                                it = it2;
                            }
                            hashSet4.add(str);
                            it2 = it;
                            length = i4;
                        }
                        i2 = length;
                        hashSet4.addAll(this.zzo);
                        hashSet4.addAll(arrayList);
                        HashSet hashSet5 = new HashSet();
                        for (Map.Entry entry : zza4.entrySet()) {
                            if (hashSet4.contains(entry.getKey())) {
                                hashSet5.addAll((Collection) entry.getValue());
                            }
                        }
                        if (hashSet3.contains(zza3)) {
                        }
                        j2 += file.length();
                        arrayList2.add(file);
                        break;
                        i3++;
                        listFiles = fileArr;
                        length = i2;
                    } else {
                        fileArr = listFiles;
                        i2 = length;
                    }
                    List<Locale> languages = splitInstallRequest.getLanguages();
                    ArrayList arrayList3 = new ArrayList(this.zzn);
                    arrayList3.addAll(Arrays.asList("", "base"));
                    Map zza5 = zzk().zza(arrayList3);
                    for (Locale locale2 : languages) {
                        if (zza5.containsKey(locale2.getLanguage()) && ((Set) zza5.get(locale2.getLanguage())).contains(zza3)) {
                            j2 += file.length();
                            arrayList2.add(file);
                            break;
                        }
                    }
                    i3++;
                    listFiles = fileArr;
                    length = i2;
                }
                String obj = hashSet.toString();
                String valueOf = String.valueOf(splitInstallRequest.getModuleNames());
                StringBuilder sb = new StringBuilder(obj.length() + 22 + valueOf.length());
                sb.append("availableSplits ");
                sb.append(obj);
                sb.append(" want ");
                sb.append(valueOf);
                if (splitInstallRequest.getModuleNames().size() != 1 || (zza2 = (Integer) ((zzt) this.zzf.zza()).zzb().get(splitInstallRequest.getModuleNames().get(0))) == null) {
                    zza2 = ((zzt) this.zzf.zza()).zza();
                }
                if (zza2 != null) {
                    return zzn(zza2.intValue());
                }
                if (hashSet.containsAll(new HashSet(splitInstallRequest.getModuleNames()))) {
                    Long valueOf2 = Long.valueOf(j2);
                    List<String> moduleNames = splitInstallRequest.getModuleNames();
                    Integer valueOf3 = Integer.valueOf(sessionId);
                    zzs(1, 0, 0L, valueOf2, moduleNames, valueOf3, arrayList);
                    this.zzj.execute(new Runnable() { // from class: com.google.android.play.core.splitinstall.testing.zzn
                        @Override // java.lang.Runnable
                        public final void run() {
                            FakeSplitInstallManager.this.g(arrayList2, arrayList);
                        }
                    });
                    return Tasks.zzb(valueOf3);
                }
                return zzn(-2);
            }
            return zzn(-100);
        } catch (SplitInstallException e2) {
            return zzn(e2.getErrorCode());
        }
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void unregisterListener(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzi.zzb(splitInstallStateUpdatedListener);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void zza(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzh.zza(splitInstallStateUpdatedListener);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void zzb(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzh.zzb(splitInstallStateUpdatedListener);
    }
}
