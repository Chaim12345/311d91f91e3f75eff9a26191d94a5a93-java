package com.google.android.play.core.splitinstall;

import android.app.Activity;
import androidx.annotation.NonNull;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.tasks.Task;
import java.util.List;
import java.util.Locale;
import java.util.Set;
/* loaded from: classes2.dex */
public interface SplitInstallManager {
    @NonNull
    Task<Void> cancelInstall(int i2);

    @NonNull
    Task<Void> deferredInstall(List<String> list);

    @NonNull
    Task<Void> deferredLanguageInstall(List<Locale> list);

    @NonNull
    Task<Void> deferredLanguageUninstall(List<Locale> list);

    @NonNull
    Task<Void> deferredUninstall(List<String> list);

    @NonNull
    Set<String> getInstalledLanguages();

    @NonNull
    Set<String> getInstalledModules();

    @NonNull
    Task<SplitInstallSessionState> getSessionState(int i2);

    @NonNull
    Task<List<SplitInstallSessionState>> getSessionStates();

    void registerListener(@NonNull SplitInstallStateUpdatedListener splitInstallStateUpdatedListener);

    boolean startConfirmationDialogForResult(@NonNull SplitInstallSessionState splitInstallSessionState, @NonNull Activity activity, int i2);

    boolean startConfirmationDialogForResult(@NonNull SplitInstallSessionState splitInstallSessionState, @NonNull IntentSenderForResultStarter intentSenderForResultStarter, int i2);

    Task<Integer> startInstall(@NonNull SplitInstallRequest splitInstallRequest);

    void unregisterListener(@NonNull SplitInstallStateUpdatedListener splitInstallStateUpdatedListener);

    void zza(@NonNull SplitInstallStateUpdatedListener splitInstallStateUpdatedListener);

    void zzb(@NonNull SplitInstallStateUpdatedListener splitInstallStateUpdatedListener);
}
