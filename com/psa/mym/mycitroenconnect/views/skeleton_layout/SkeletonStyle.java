package com.psa.mym.mycitroenconnect.views.skeleton_layout;

import androidx.annotation.ColorInt;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.mask.SkeletonShimmerDirection;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface SkeletonStyle {
    @ColorInt
    int getMaskColor();

    float getMaskCornerRadius();

    int getShimmerAngle();

    @ColorInt
    int getShimmerColor();

    @NotNull
    SkeletonShimmerDirection getShimmerDirection();

    long getShimmerDurationInMillis();

    boolean getShowShimmer();

    void setMaskColor(int i2);

    void setMaskCornerRadius(float f2);

    void setShimmerAngle(int i2);

    void setShimmerColor(int i2);

    void setShimmerDirection(@NotNull SkeletonShimmerDirection skeletonShimmerDirection);

    void setShimmerDurationInMillis(long j2);

    void setShowShimmer(boolean z);
}
