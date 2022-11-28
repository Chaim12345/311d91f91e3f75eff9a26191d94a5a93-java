package com.google.mlkit.common.sdkinternal.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.LongSparseArray;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.mlkit_common.zzid;
import com.google.android.gms.internal.mlkit_common.zzij;
import com.google.android.gms.internal.mlkit_common.zzlc;
import com.google.android.gms.internal.mlkit_common.zzll;
import com.google.android.gms.internal.mlkit_common.zzlm;
import com.google.android.gms.internal.mlkit_common.zzln;
import com.google.android.gms.internal.mlkit_common.zzlo;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MlKitContext;
/* JADX INFO: Access modifiers changed from: package-private */
@WorkerThread
/* loaded from: classes2.dex */
public final class zzc extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ RemoteModelDownloadManager f10341a;
    private final long zzb;
    private final TaskCompletionSource zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzc(RemoteModelDownloadManager remoteModelDownloadManager, long j2, TaskCompletionSource taskCompletionSource, zzb zzbVar) {
        this.f10341a = remoteModelDownloadManager;
        this.zzb = j2;
        this.zzc = taskCompletionSource;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        GmsLogger gmsLogger;
        LongSparseArray longSparseArray;
        LongSparseArray longSparseArray2;
        zzll zzllVar;
        RemoteModel remoteModel;
        zzll zzllVar2;
        RemoteModel remoteModel2;
        RemoteModel remoteModel3;
        zzll zzllVar3;
        RemoteModel remoteModel4;
        MlKitException zzl;
        MlKitContext mlKitContext;
        long longExtra = intent.getLongExtra("extra_download_id", -1L);
        if (longExtra != this.zzb) {
            return;
        }
        Integer downloadingModelStatusCode = this.f10341a.getDownloadingModelStatusCode();
        synchronized (this.f10341a) {
            try {
                mlKitContext = this.f10341a.zze;
                mlKitContext.getApplicationContext().unregisterReceiver(this);
            } catch (IllegalArgumentException e2) {
                gmsLogger = RemoteModelDownloadManager.zza;
                gmsLogger.w("ModelDownloadManager", "Exception thrown while trying to unregister the broadcast receiver for the download", e2);
            }
            longSparseArray = this.f10341a.zzc;
            longSparseArray.remove(this.zzb);
            longSparseArray2 = this.f10341a.zzd;
            longSparseArray2.remove(this.zzb);
        }
        if (downloadingModelStatusCode != null) {
            if (downloadingModelStatusCode.intValue() == 16) {
                zzllVar3 = this.f10341a.zzi;
                zzlc zzg = zzlo.zzg();
                RemoteModelDownloadManager remoteModelDownloadManager = this.f10341a;
                remoteModel4 = remoteModelDownloadManager.zzg;
                Long valueOf = Long.valueOf(longExtra);
                zzllVar3.zzd(zzg, remoteModel4, false, remoteModelDownloadManager.getFailureReason(valueOf));
                TaskCompletionSource taskCompletionSource = this.zzc;
                zzl = this.f10341a.zzl(valueOf);
                taskCompletionSource.setException(zzl);
                return;
            } else if (downloadingModelStatusCode.intValue() == 8) {
                zzllVar2 = this.f10341a.zzi;
                zzlc zzg2 = zzlo.zzg();
                remoteModel2 = this.f10341a.zzg;
                zzlm zzh = zzln.zzh();
                zzh.zzb(zzid.NO_ERROR);
                zzh.zze(true);
                remoteModel3 = this.f10341a.zzg;
                zzh.zzd(remoteModel3.getModelType());
                zzh.zza(zzij.SUCCEEDED);
                zzllVar2.zzf(zzg2, remoteModel2, zzh.zzh());
                this.zzc.setResult(null);
                return;
            }
        }
        zzllVar = this.f10341a.zzi;
        zzlc zzg3 = zzlo.zzg();
        remoteModel = this.f10341a.zzg;
        zzllVar.zzd(zzg3, remoteModel, false, 0);
        this.zzc.setException(new MlKitException("Model downloading failed", 13));
    }
}
