package com.chuckerteam.chucker.internal.support;

import android.util.Log;
import com.google.firebase.messaging.Constants;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0007\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u001a\u0010\t\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007R\u0016\u0010\n\u001a\u00020\u00028\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/Logger;", "", "", AppConstants.ARG_MESSAGE, "", "info", "warn", "", "throwable", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "TAG", "Ljava/lang/String;", "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class Logger {
    public static final Logger INSTANCE = new Logger();
    private static final String TAG = "Chucker";

    private Logger() {
    }

    public static /* synthetic */ void error$default(Logger logger, String str, Throwable th, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            th = null;
        }
        logger.error(str, th);
    }

    public final void error(@NotNull String message, @Nullable Throwable th) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (th != null) {
            Log.e(TAG, message, th);
        } else {
            Log.e(TAG, message);
        }
    }

    public final void info(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
    }

    public final void warn(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
    }
}
