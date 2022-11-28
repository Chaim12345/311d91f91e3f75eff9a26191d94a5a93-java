package com.psa.mym.mycitroenconnect.views.skeleton_layout.mask;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public enum SkeletonShimmerDirection {
    LEFT_TO_RIGHT(0),
    RIGHT_TO_LEFT(1);
    
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final int stableId;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final SkeletonShimmerDirection valueOf(int i2) {
            SkeletonShimmerDirection[] values;
            for (SkeletonShimmerDirection skeletonShimmerDirection : SkeletonShimmerDirection.values()) {
                if (skeletonShimmerDirection.stableId == i2) {
                    return skeletonShimmerDirection;
                }
            }
            return null;
        }
    }

    SkeletonShimmerDirection(int i2) {
        this.stableId = i2;
    }
}
