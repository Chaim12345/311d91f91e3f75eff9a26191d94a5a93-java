package androidx.car.app;
/* loaded from: classes.dex */
public final /* synthetic */ class c implements HostCall {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ c f1457a = new c();

    private /* synthetic */ c() {
    }

    @Override // androidx.car.app.HostCall
    public final Object dispatch(Object obj) {
        Object invalidate;
        invalidate = ((IAppHost) obj).invalidate();
        return invalidate;
    }
}
