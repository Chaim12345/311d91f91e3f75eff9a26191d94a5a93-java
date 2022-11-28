package androidx.car.app.constraints;

import android.os.RemoteException;
import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.car.app.CarContext;
import androidx.car.app.HostCall;
import androidx.car.app.HostDispatcher;
import androidx.car.app.R;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.managers.Manager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
@RequiresCarApi(2)
/* loaded from: classes.dex */
public class ConstraintManager implements Manager {
    public static final int CONTENT_LIMIT_TYPE_GRID = 1;
    public static final int CONTENT_LIMIT_TYPE_LIST = 0;
    public static final int CONTENT_LIMIT_TYPE_PANE = 4;
    public static final int CONTENT_LIMIT_TYPE_PLACE_LIST = 2;
    public static final int CONTENT_LIMIT_TYPE_ROUTE_LIST = 3;
    @NonNull
    private final CarContext mCarContext;
    @NonNull
    private final HostDispatcher mHostDispatcher;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface ContentLimitType {
    }

    private ConstraintManager(CarContext carContext, HostDispatcher hostDispatcher) {
        this.mCarContext = carContext;
        this.mHostDispatcher = hostDispatcher;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static ConstraintManager create(@NonNull CarContext carContext, @NonNull HostDispatcher hostDispatcher) {
        Objects.requireNonNull(carContext);
        Objects.requireNonNull(hostDispatcher);
        return new ConstraintManager(carContext, hostDispatcher);
    }

    @IntegerRes
    private int getResourceIdForContentType(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? R.integer.content_limit_list : R.integer.content_limit_pane : R.integer.content_limit_route_list : R.integer.content_limit_place_list : R.integer.content_limit_grid;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$getContentLimit$0(int i2, IConstraintHost iConstraintHost) {
        return Integer.valueOf(iConstraintHost.getContentLimit(i2));
    }

    public int getContentLimit(final int i2) {
        Integer num;
        try {
            num = (Integer) this.mHostDispatcher.dispatchForResult(CarContext.CONSTRAINT_SERVICE, "getContentLimit", new HostCall() { // from class: androidx.car.app.constraints.a
                @Override // androidx.car.app.HostCall
                public final Object dispatch(Object obj) {
                    Integer lambda$getContentLimit$0;
                    lambda$getContentLimit$0 = ConstraintManager.lambda$getContentLimit$0(i2, (IConstraintHost) obj);
                    return lambda$getContentLimit$0;
                }
            });
        } catch (RemoteException unused) {
            num = null;
        }
        return num != null ? num.intValue() : this.mCarContext.getResources().getInteger(getResourceIdForContentType(i2));
    }
}
