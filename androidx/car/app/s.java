package androidx.car.app;
/* loaded from: classes.dex */
public final /* synthetic */ class s implements HostCall {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ s f1522a = new s();

    private /* synthetic */ s() {
    }

    @Override // androidx.car.app.HostCall
    public final Object dispatch(Object obj) {
        Object finish;
        finish = ((ICarHost) obj).finish();
        return finish;
    }
}
