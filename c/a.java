package c;

import androidx.car.app.HostCall;
import androidx.car.app.ICarHost;
import androidx.car.app.hardware.ICarHardwareHost;
import androidx.car.app.hardware.common.CarHardwareHostDispatcher;
/* loaded from: classes.dex */
public final /* synthetic */ class a implements HostCall {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ a f4318a = new a();

    private /* synthetic */ a() {
    }

    @Override // androidx.car.app.HostCall
    public final Object dispatch(Object obj) {
        ICarHardwareHost lambda$getHost$3;
        lambda$getHost$3 = CarHardwareHostDispatcher.lambda$getHost$3((ICarHost) obj);
        return lambda$getHost$3;
    }
}
