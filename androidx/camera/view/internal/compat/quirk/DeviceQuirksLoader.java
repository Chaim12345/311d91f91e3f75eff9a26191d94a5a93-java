package androidx.camera.view.internal.compat.quirk;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class DeviceQuirksLoader {
    private DeviceQuirksLoader() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static List a() {
        ArrayList arrayList = new ArrayList();
        if (PreviewOneThirdWiderQuirk.a()) {
            arrayList.add(new PreviewOneThirdWiderQuirk());
        }
        if (SurfaceViewStretchedQuirk.a()) {
            arrayList.add(new SurfaceViewStretchedQuirk());
        }
        if (TextureViewRotationQuirk.a()) {
            arrayList.add(new TextureViewRotationQuirk());
        }
        return arrayList;
    }
}
