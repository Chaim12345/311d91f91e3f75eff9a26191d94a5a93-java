package androidx.camera.core;

import androidx.annotation.NonNull;
import androidx.camera.core.impl.CaptureBundle;
import androidx.camera.core.impl.CaptureStage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
final class CaptureBundles {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class CaptureBundleImpl implements CaptureBundle {

        /* renamed from: a  reason: collision with root package name */
        final List f971a;

        CaptureBundleImpl(List list) {
            if (list == null || list.isEmpty()) {
                throw new IllegalArgumentException("Cannot set an empty CaptureStage list.");
            }
            this.f971a = Collections.unmodifiableList(new ArrayList(list));
        }

        @Override // androidx.camera.core.impl.CaptureBundle
        public List<CaptureStage> getCaptureStages() {
            return this.f971a;
        }
    }

    private CaptureBundles() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static CaptureBundle a(@NonNull List list) {
        return new CaptureBundleImpl(list);
    }

    @NonNull
    static CaptureBundle b(@NonNull CaptureStage... captureStageArr) {
        return new CaptureBundleImpl(Arrays.asList(captureStageArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static CaptureBundle c() {
        return b(new CaptureStage.DefaultCaptureStage());
    }
}
