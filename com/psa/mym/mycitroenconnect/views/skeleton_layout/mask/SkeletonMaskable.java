package com.psa.mym.mycitroenconnect.views.skeleton_layout.mask;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface SkeletonMaskable {

    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static void invalidate(@NotNull SkeletonMaskable skeletonMaskable) {
        }

        public static void start(@NotNull SkeletonMaskable skeletonMaskable) {
        }

        public static void stop(@NotNull SkeletonMaskable skeletonMaskable) {
        }
    }

    void invalidate();

    void start();

    void stop();
}
