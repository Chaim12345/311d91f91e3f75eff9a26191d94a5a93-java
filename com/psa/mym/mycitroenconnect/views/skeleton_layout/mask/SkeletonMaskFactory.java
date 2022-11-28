package com.psa.mym.mycitroenconnect.views.skeleton_layout.mask;

import android.view.View;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonConfig;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class SkeletonMaskFactory {
    @NotNull
    public static final SkeletonMaskFactory INSTANCE = new SkeletonMaskFactory();

    private SkeletonMaskFactory() {
    }

    @NotNull
    public final SkeletonMask createMask(@NotNull View view, @NotNull SkeletonConfig config) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(config, "config");
        boolean showShimmer = config.getShowShimmer();
        if (showShimmer) {
            return new SkeletonMaskShimmer(view, config.getMaskColor(), config.getShimmerColor(), config.getShimmerDurationInMillis(), config.getShimmerDirection(), config.getShimmerAngle());
        }
        if (showShimmer) {
            throw new NoWhenBranchMatchedException();
        }
        return new SkeletonMaskSolid(view, config.getMaskColor());
    }
}
