package androidx.camera.camera2.internal;

import android.util.Size;
import java.util.Comparator;
/* loaded from: classes.dex */
public final /* synthetic */ class x0 implements Comparator {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ x0 f945a = new x0();

    private /* synthetic */ x0() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int lambda$getMinimumPreviewSize$0;
        lambda$getMinimumPreviewSize$0 = MeteringRepeatingSession.lambda$getMinimumPreviewSize$0((Size) obj, (Size) obj2);
        return lambda$getMinimumPreviewSize$0;
    }
}
