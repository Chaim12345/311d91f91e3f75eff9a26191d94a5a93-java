package androidx.car.app;

import androidx.car.app.managers.Manager;
import androidx.car.app.managers.ManagerFactory;
import androidx.car.app.managers.ResultManager;
/* loaded from: classes.dex */
public final /* synthetic */ class y implements ManagerFactory {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ y f1569a = new y();

    private /* synthetic */ y() {
    }

    @Override // androidx.car.app.managers.ManagerFactory
    public final Manager create() {
        return ResultManager.create();
    }
}
