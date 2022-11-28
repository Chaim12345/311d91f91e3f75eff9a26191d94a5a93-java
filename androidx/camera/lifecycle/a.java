package androidx.camera.lifecycle;

import androidx.arch.core.util.Function;
import androidx.camera.core.CameraX;
/* loaded from: classes.dex */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ a f1344a = new a();

    private /* synthetic */ a() {
    }

    @Override // androidx.arch.core.util.Function
    public final Object apply(Object obj) {
        ProcessCameraProvider lambda$getInstance$0;
        lambda$getInstance$0 = ProcessCameraProvider.lambda$getInstance$0((CameraX) obj);
        return lambda$getInstance$0;
    }
}
