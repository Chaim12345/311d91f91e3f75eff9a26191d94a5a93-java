package androidx.car.app.connection;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.car.app.utils.CommonUtils;
import androidx.lifecycle.LiveData;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;
/* loaded from: classes.dex */
public final class CarConnection {
    public static final String ACTION_CAR_CONNECTION_UPDATED = "androidx.car.app.connection.action.CAR_CONNECTION_UPDATED";
    public static final String CAR_CONNECTION_STATE = "CarConnectionState";
    public static final int CONNECTION_TYPE_NATIVE = 1;
    public static final int CONNECTION_TYPE_NOT_CONNECTED = 0;
    public static final int CONNECTION_TYPE_PROJECTION = 2;
    private final LiveData<Integer> mConnectionTypeLiveData;

    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface ConnectionType {
    }

    public CarConnection(@NonNull Context context) {
        Objects.requireNonNull(context);
        this.mConnectionTypeLiveData = CommonUtils.isAutomotiveOS(context) ? new AutomotiveCarConnectionTypeLiveData() : new CarConnectionTypeLiveData(context);
    }

    @NonNull
    public LiveData<Integer> getType() {
        return this.mConnectionTypeLiveData;
    }
}
