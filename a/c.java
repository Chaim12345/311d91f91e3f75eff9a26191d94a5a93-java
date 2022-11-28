package a;

import android.content.Context;
import androidx.camera.camera2.Camera2Config;
import androidx.camera.core.impl.UseCaseConfigFactory;
/* loaded from: classes.dex */
public final /* synthetic */ class c implements UseCaseConfigFactory.Provider {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ c f7a = new c();

    private /* synthetic */ c() {
    }

    @Override // androidx.camera.core.impl.UseCaseConfigFactory.Provider
    public final UseCaseConfigFactory newInstance(Context context) {
        UseCaseConfigFactory lambda$defaultConfig$1;
        lambda$defaultConfig$1 = Camera2Config.lambda$defaultConfig$1(context);
        return lambda$defaultConfig$1;
    }
}
