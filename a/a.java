package a;

import android.content.Context;
import androidx.camera.camera2.Camera2Config;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import java.util.Set;
/* loaded from: classes.dex */
public final /* synthetic */ class a implements CameraDeviceSurfaceManager.Provider {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ a f5a = new a();

    private /* synthetic */ a() {
    }

    @Override // androidx.camera.core.impl.CameraDeviceSurfaceManager.Provider
    public final CameraDeviceSurfaceManager newInstance(Context context, Object obj, Set set) {
        CameraDeviceSurfaceManager lambda$defaultConfig$0;
        lambda$defaultConfig$0 = Camera2Config.lambda$defaultConfig$0(context, obj, set);
        return lambda$defaultConfig$0;
    }
}
