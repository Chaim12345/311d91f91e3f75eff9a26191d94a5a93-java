package androidx.car.app.navigation;

import androidx.car.app.HostCall;
/* loaded from: classes.dex */
public final /* synthetic */ class c implements HostCall {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ c f1505a = new c();

    private /* synthetic */ c() {
    }

    @Override // androidx.car.app.HostCall
    public final Object dispatch(Object obj) {
        Object navigationEnded;
        navigationEnded = ((INavigationHost) obj).navigationEnded();
        return navigationEnded;
    }
}
