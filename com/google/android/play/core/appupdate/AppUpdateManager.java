package com.google.android.play.core.appupdate;

import android.app.Activity;
import androidx.annotation.NonNull;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.tasks.Task;
/* loaded from: classes2.dex */
public interface AppUpdateManager {
    @NonNull
    Task<Void> completeUpdate();

    @NonNull
    Task<AppUpdateInfo> getAppUpdateInfo();

    void registerListener(@NonNull InstallStateUpdatedListener installStateUpdatedListener);

    Task<Integer> startUpdateFlow(@NonNull AppUpdateInfo appUpdateInfo, @NonNull Activity activity, @NonNull AppUpdateOptions appUpdateOptions);

    boolean startUpdateFlowForResult(@NonNull AppUpdateInfo appUpdateInfo, @AppUpdateType int i2, @NonNull Activity activity, int i3);

    boolean startUpdateFlowForResult(@NonNull AppUpdateInfo appUpdateInfo, @AppUpdateType int i2, @NonNull IntentSenderForResultStarter intentSenderForResultStarter, int i3);

    boolean startUpdateFlowForResult(@NonNull AppUpdateInfo appUpdateInfo, @NonNull Activity activity, @NonNull AppUpdateOptions appUpdateOptions, int i2);

    boolean startUpdateFlowForResult(@NonNull AppUpdateInfo appUpdateInfo, @NonNull IntentSenderForResultStarter intentSenderForResultStarter, @NonNull AppUpdateOptions appUpdateOptions, int i2);

    void unregisterListener(@NonNull InstallStateUpdatedListener installStateUpdatedListener);
}
