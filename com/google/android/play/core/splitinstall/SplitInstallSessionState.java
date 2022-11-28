package com.google.android.play.core.splitinstall;

import android.app.PendingIntent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode;
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public abstract class SplitInstallSessionState {
    public static SplitInstallSessionState create(int i2, @SplitInstallSessionStatus int i3, @SplitInstallErrorCode int i4, long j2, long j3, List<String> list, List<String> list2) {
        if (i3 != 8) {
            return new zza(i2, i3, i4, j2, j3, list, list2, null, null);
        }
        throw new IllegalArgumentException("REQUIRES_USER_CONFIRMATION state not supported.");
    }

    public static SplitInstallSessionState zzd(Bundle bundle) {
        return new zza(bundle.getInt("session_id"), bundle.getInt(NotificationCompat.CATEGORY_STATUS), bundle.getInt("error_code"), bundle.getLong("bytes_downloaded"), bundle.getLong("total_bytes_to_download"), bundle.getStringArrayList("module_names"), bundle.getStringArrayList("languages"), (PendingIntent) bundle.getParcelable("user_confirmation_intent"), bundle.getParcelableArrayList("split_file_intents"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract List a();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract List b();

    public abstract long bytesDownloaded();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract List c();

    @SplitInstallErrorCode
    public abstract int errorCode();

    public boolean hasTerminalStatus() {
        int status = status();
        return status == 0 || status == 5 || status == 6 || status == 7;
    }

    @NonNull
    public List<String> languages() {
        return a() != null ? new ArrayList(a()) : new ArrayList();
    }

    @NonNull
    public List<String> moduleNames() {
        return b() != null ? new ArrayList(b()) : new ArrayList();
    }

    @Nullable
    @Deprecated
    public abstract PendingIntent resolutionIntent();

    public abstract int sessionId();

    @SplitInstallSessionStatus
    public abstract int status();

    public abstract long totalBytesToDownload();
}
