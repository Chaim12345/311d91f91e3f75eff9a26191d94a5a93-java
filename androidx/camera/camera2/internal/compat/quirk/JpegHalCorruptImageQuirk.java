package androidx.camera.camera2.internal.compat.quirk;

import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.core.internal.compat.quirk.SoftwareJpegEncodingPreferredQuirk;
import androidx.core.util.Preconditions;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
/* loaded from: classes.dex */
public final class JpegHalCorruptImageQuirk implements SoftwareJpegEncodingPreferredQuirk {
    private static final Set<String> KNOWN_AFFECTED_DEVICES = new HashSet(Arrays.asList("heroqltevzw", "heroqltetmo"));
    private static final Set<Integer> SUPPORTED_HARDWARE_LEVELS;

    static {
        HashSet hashSet = new HashSet();
        SUPPORTED_HARDWARE_LEVELS = hashSet;
        hashSet.add(1);
        if (Build.VERSION.SDK_INT >= 24) {
            hashSet.add(3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(@NonNull CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        return KNOWN_AFFECTED_DEVICES.contains(Build.DEVICE.toLowerCase(Locale.US)) && SUPPORTED_HARDWARE_LEVELS.contains(Integer.valueOf(((Integer) Preconditions.checkNotNull((Integer) cameraCharacteristicsCompat.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL))).intValue()));
    }
}
