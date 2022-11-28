package com.google.android.gms.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.StatusCallback;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes2.dex */
public class ActivityRecognitionClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    public static final /* synthetic */ int zza = 0;

    public ActivityRecognitionClient(@NonNull Activity activity) {
        super(activity, LocationServices.API, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public ActivityRecognitionClient(@NonNull Context context) {
        super(context, LocationServices.API, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @NonNull
    @RequiresPermission(anyOf = {"android.permission.ACTIVITY_RECOGNITION", "com.google.android.gms.permission.ACTIVITY_RECOGNITION"})
    public Task<Void> removeActivityTransitionUpdates(@NonNull final PendingIntent pendingIntent) {
        return doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.location.zzd
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                PendingIntent pendingIntent2 = pendingIntent;
                zzj zzjVar = new zzj((TaskCompletionSource) obj2);
                Preconditions.checkNotNull(zzjVar, "ResultHolder not provided.");
                ((com.google.android.gms.internal.location.zzam) ((com.google.android.gms.internal.location.zzbe) obj).getService()).zzk(pendingIntent2, new StatusCallback(zzjVar));
            }
        }).setMethodKey(2406).build());
    }

    @NonNull
    @RequiresPermission(anyOf = {"android.permission.ACTIVITY_RECOGNITION", "com.google.android.gms.permission.ACTIVITY_RECOGNITION"})
    public Task<Void> removeActivityUpdates(@NonNull final PendingIntent pendingIntent) {
        return doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.location.zze
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                ((com.google.android.gms.internal.location.zzbe) obj).zzv(pendingIntent);
                ((TaskCompletionSource) obj2).setResult(null);
            }
        }).setMethodKey(2402).build());
    }

    @NonNull
    public Task<Void> removeSleepSegmentUpdates(@NonNull final PendingIntent pendingIntent) {
        return doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.location.zzf
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                PendingIntent pendingIntent2 = pendingIntent;
                zzj zzjVar = new zzj((TaskCompletionSource) obj2);
                Preconditions.checkNotNull(pendingIntent2, "PendingIntent must be specified.");
                Preconditions.checkNotNull(zzjVar, "ResultHolder not provided.");
                ((com.google.android.gms.internal.location.zzam) ((com.google.android.gms.internal.location.zzbe) obj).getService()).zzp(pendingIntent2, new StatusCallback(zzjVar));
            }
        }).setMethodKey(2411).build());
    }

    @NonNull
    @RequiresPermission(anyOf = {"android.permission.ACTIVITY_RECOGNITION", "com.google.android.gms.permission.ACTIVITY_RECOGNITION"})
    public Task<Void> requestActivityTransitionUpdates(@NonNull final ActivityTransitionRequest activityTransitionRequest, @NonNull final PendingIntent pendingIntent) {
        activityTransitionRequest.zza(b());
        return doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.location.zzc
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                ActivityTransitionRequest activityTransitionRequest2 = ActivityTransitionRequest.this;
                PendingIntent pendingIntent2 = pendingIntent;
                zzj zzjVar = new zzj((TaskCompletionSource) obj2);
                Preconditions.checkNotNull(activityTransitionRequest2, "activityTransitionRequest must be specified.");
                Preconditions.checkNotNull(pendingIntent2, "PendingIntent must be specified.");
                Preconditions.checkNotNull(zzjVar, "ResultHolder not provided.");
                ((com.google.android.gms.internal.location.zzam) ((com.google.android.gms.internal.location.zzbe) obj).getService()).zzq(activityTransitionRequest2, pendingIntent2, new StatusCallback(zzjVar));
            }
        }).setMethodKey(2405).build());
    }

    @NonNull
    @RequiresPermission(anyOf = {"android.permission.ACTIVITY_RECOGNITION", "com.google.android.gms.permission.ACTIVITY_RECOGNITION"})
    public Task<Void> requestActivityUpdates(long j2, @NonNull final PendingIntent pendingIntent) {
        zzk zzkVar = new zzk();
        zzkVar.zza(j2);
        final zzl zzb = zzkVar.zzb();
        zzb.zza(b());
        return doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.location.zzh
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                zzl zzlVar = zzl.this;
                PendingIntent pendingIntent2 = pendingIntent;
                zzj zzjVar = new zzj((TaskCompletionSource) obj2);
                Preconditions.checkNotNull(zzlVar, "ActivityRecognitionRequest can't be null.");
                Preconditions.checkNotNull(pendingIntent2, "PendingIntent must be specified.");
                Preconditions.checkNotNull(zzjVar, "ResultHolder not provided.");
                ((com.google.android.gms.internal.location.zzam) ((com.google.android.gms.internal.location.zzbe) obj).getService()).zzs(zzlVar, pendingIntent2, new StatusCallback(zzjVar));
            }
        }).setMethodKey(2401).build());
    }

    @NonNull
    @RequiresPermission(anyOf = {"android.permission.ACTIVITY_RECOGNITION", "com.google.android.gms.permission.ACTIVITY_RECOGNITION"})
    public Task<Void> requestSleepSegmentUpdates(@NonNull final PendingIntent pendingIntent, @NonNull final SleepSegmentRequest sleepSegmentRequest) {
        Preconditions.checkNotNull(pendingIntent, "PendingIntent must be specified.");
        return doRead(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.location.zzg
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                ActivityRecognitionClient activityRecognitionClient = ActivityRecognitionClient.this;
                ((com.google.android.gms.internal.location.zzam) ((com.google.android.gms.internal.location.zzbe) obj).getService()).zzt(pendingIntent, sleepSegmentRequest, new zzi(activityRecognitionClient, (TaskCompletionSource) obj2));
            }
        }).setFeatures(zzy.zzb).setMethodKey(2410).build());
    }
}
