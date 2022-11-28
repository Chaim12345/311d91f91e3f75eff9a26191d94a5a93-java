package androidx.camera.core.internal.compat.quirk;

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
        if (IncompleteCameraListQuirk.a()) {
            arrayList.add(new IncompleteCameraListQuirk());
        }
        if (ImageCaptureRotationOptionQuirk.a()) {
            arrayList.add(new ImageCaptureRotationOptionQuirk());
        }
        return arrayList;
    }
}
