package androidx.car.app.navigation;

import androidx.car.app.HostCall;
/* loaded from: classes.dex */
public final /* synthetic */ class b implements HostCall {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ b f1504a = new b();

    private /* synthetic */ b() {
    }

    @Override // androidx.car.app.HostCall
    public final Object dispatch(Object obj) {
        Object navigationStarted;
        navigationStarted = ((INavigationHost) obj).navigationStarted();
        return navigationStarted;
    }
}
