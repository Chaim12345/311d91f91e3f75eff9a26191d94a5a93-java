package androidx.camera.camera2.internal.compat.quirk;

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
        if (ImageCapturePixelHDRPlusQuirk.a()) {
            arrayList.add(new ImageCapturePixelHDRPlusQuirk());
        }
        if (SamsungPreviewTargetAspectRatioQuirk.a()) {
            arrayList.add(new SamsungPreviewTargetAspectRatioQuirk());
        }
        if (Nexus4AndroidLTargetAspectRatioQuirk.a()) {
            arrayList.add(new Nexus4AndroidLTargetAspectRatioQuirk());
        }
        if (ExcludedSupportedSizesQuirk.a()) {
            arrayList.add(new ExcludedSupportedSizesQuirk());
        }
        if (CrashWhenTakingPhotoWithAutoFlashAEModeQuirk.a()) {
            arrayList.add(new CrashWhenTakingPhotoWithAutoFlashAEModeQuirk());
        }
        if (PreviewPixelHDRnetQuirk.a()) {
            arrayList.add(new PreviewPixelHDRnetQuirk());
        }
        if (StillCaptureFlashStopRepeatingQuirk.a()) {
            arrayList.add(new StillCaptureFlashStopRepeatingQuirk());
        }
        return arrayList;
    }
}
