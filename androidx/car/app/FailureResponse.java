package androidx.car.app;

import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.serialization.BundlerException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.InvalidParameterException;
import java.util.Objects;
/* loaded from: classes.dex */
public final class FailureResponse {
    public static final int BUNDLER_EXCEPTION = 1;
    public static final int ILLEGAL_STATE_EXCEPTION = 2;
    public static final int INVALID_PARAMETER_EXCEPTION = 3;
    public static final int REMOTE_EXCEPTION = 6;
    public static final int RUNTIME_EXCEPTION = 5;
    public static final int SECURITY_EXCEPTION = 4;
    public static final int UNKNOWN_ERROR = 0;
    @Keep
    private final int mErrorType;
    @Nullable
    @Keep
    private final String mStackTrace;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface ErrorType {
    }

    private FailureResponse() {
        this.mStackTrace = null;
        this.mErrorType = 0;
    }

    public FailureResponse(@NonNull Throwable th) {
        Objects.requireNonNull(th);
        this.mStackTrace = Log.getStackTraceString(th);
        this.mErrorType = th instanceof BundlerException ? 1 : th instanceof IllegalStateException ? 2 : th instanceof InvalidParameterException ? 3 : th instanceof SecurityException ? 4 : th instanceof RuntimeException ? 5 : th instanceof RemoteException ? 6 : 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof FailureResponse) {
            FailureResponse failureResponse = (FailureResponse) obj;
            return this.mErrorType == failureResponse.mErrorType && Objects.equals(this.mStackTrace, failureResponse.mStackTrace);
        }
        return false;
    }

    public int getErrorType() {
        return this.mErrorType;
    }

    @NonNull
    public String getStackTrace() {
        String str = this.mStackTrace;
        Objects.requireNonNull(str);
        return str;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mErrorType), this.mStackTrace);
    }
}
