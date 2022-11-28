package com.google.android.gms.internal.mlkit_common;

import android.os.SystemClock;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
/* loaded from: classes.dex */
public final class zzlx {
    private static final GmsLogger zza = new GmsLogger("RemoteModelUtils", "");

    @WorkerThread
    public static zzil zza(RemoteModel remoteModel, SharedPrefManager sharedPrefManager, zzln zzlnVar) {
        ModelType zzb = zzlnVar.zzb();
        String modelHash = remoteModel.getModelHash();
        zzir zzirVar = new zzir();
        zzim zzimVar = new zzim();
        zzimVar.zzc(remoteModel.getModelNameForBackend());
        zzimVar.zzd(zzio.CLOUD);
        zzimVar.zza(zzac.zzb(modelHash));
        int ordinal = zzb.ordinal();
        zzimVar.zzb(ordinal != 2 ? ordinal != 4 ? ordinal != 5 ? zzin.TYPE_UNKNOWN : zzin.BASE_DIGITAL_INK : zzin.CUSTOM : zzin.BASE_TRANSLATE);
        zzirVar.zzb(zzimVar.zzg());
        zziu zzc = zzirVar.zzc();
        zzii zziiVar = new zzii();
        zziiVar.zzd(zzlnVar.zzc());
        zziiVar.zzc(zzlnVar.zzd());
        zziiVar.zzb(Long.valueOf(zzlnVar.zza()));
        zziiVar.zzf(zzc);
        if (zzlnVar.zzg()) {
            long modelDownloadBeginTimeMs = sharedPrefManager.getModelDownloadBeginTimeMs(remoteModel);
            if (modelDownloadBeginTimeMs == 0) {
                zza.w("RemoteModelUtils", "Model downloaded without its beginning time recorded.");
            } else {
                long modelFirstUseTimeMs = sharedPrefManager.getModelFirstUseTimeMs(remoteModel);
                if (modelFirstUseTimeMs == 0) {
                    modelFirstUseTimeMs = SystemClock.elapsedRealtime();
                    sharedPrefManager.setModelFirstUseTimeMs(remoteModel, modelFirstUseTimeMs);
                }
                zziiVar.zzg(Long.valueOf(modelFirstUseTimeMs - modelDownloadBeginTimeMs));
            }
        }
        if (zzlnVar.zzf()) {
            long modelDownloadBeginTimeMs2 = sharedPrefManager.getModelDownloadBeginTimeMs(remoteModel);
            if (modelDownloadBeginTimeMs2 == 0) {
                zza.w("RemoteModelUtils", "Model downloaded without its beginning time recorded.");
            } else {
                zziiVar.zze(Long.valueOf(SystemClock.elapsedRealtime() - modelDownloadBeginTimeMs2));
            }
        }
        return zziiVar.zzi();
    }
}
