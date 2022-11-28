package com.psa.mym.mycitroenconnect.views.skeleton_layout.mask;

import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.annotation.ColorInt;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.BaseExtensionsKt;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SkeletonMaskShimmer extends SkeletonMask {
    @Nullable
    private Handler animation;
    @Nullable
    private Runnable animationTask;
    private final long durationInMillis;
    @NotNull
    private final Matrix matrix;
    @NotNull
    private final Lazy refreshIntervalInMillis$delegate;
    private final int shimmerAngle;
    private final int shimmerColor;
    @NotNull
    private final SkeletonShimmerDirection shimmerDirection;
    @NotNull
    private final Lazy shimmerGradient$delegate;
    private final float width;

    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SkeletonShimmerDirection.values().length];
            iArr[SkeletonShimmerDirection.LEFT_TO_RIGHT.ordinal()] = 1;
            iArr[SkeletonShimmerDirection.RIGHT_TO_LEFT.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkeletonMaskShimmer(@NotNull View parent, @ColorInt int i2, @ColorInt int i3, long j2, @NotNull SkeletonShimmerDirection shimmerDirection, int i4) {
        super(parent, i2);
        Lazy lazy;
        Lazy lazy2;
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(shimmerDirection, "shimmerDirection");
        this.shimmerColor = i3;
        this.durationInMillis = j2;
        this.shimmerDirection = shimmerDirection;
        this.shimmerAngle = i4;
        lazy = LazyKt__LazyJVMKt.lazy(new SkeletonMaskShimmer$refreshIntervalInMillis$2(parent));
        this.refreshIntervalInMillis$delegate = lazy;
        this.width = parent.getWidth();
        this.matrix = new Matrix();
        lazy2 = LazyKt__LazyJVMKt.lazy(new SkeletonMaskShimmer$shimmerGradient$2(this));
        this.shimmerGradient$delegate = lazy2;
    }

    private final float currentOffset() {
        float currentProgress;
        int i2 = WhenMappings.$EnumSwitchMapping$0[this.shimmerDirection.ordinal()];
        if (i2 == 1) {
            currentProgress = currentProgress();
        } else if (i2 != 2) {
            throw new NoWhenBranchMatchedException();
        } else {
            currentProgress = 1 - currentProgress();
        }
        float f2 = this.width;
        float f3 = 2 * f2;
        float f4 = -f3;
        return (currentProgress * ((f2 + f3) - f4)) + f4;
    }

    private final float currentProgress() {
        double currentTimeMillis = System.currentTimeMillis();
        double d2 = this.durationInMillis;
        double floor = Math.floor(currentTimeMillis / d2) * d2;
        return (float) ((currentTimeMillis - floor) / ((d2 + floor) - floor));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long getRefreshIntervalInMillis() {
        return ((Number) this.refreshIntervalInMillis$delegate.getValue()).longValue();
    }

    private final LinearGradient getShimmerGradient() {
        return (LinearGradient) this.shimmerGradient$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateShimmer() {
        this.matrix.setTranslate(currentOffset(), 0.0f);
        d().getShader().setLocalMatrix(this.matrix);
        e().invalidate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.mask.SkeletonMask
    @NotNull
    public Paint c() {
        Paint paint = new Paint();
        paint.setShader(getShimmerGradient());
        paint.setAntiAlias(true);
        return paint;
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.mask.SkeletonMask, com.psa.mym.mycitroenconnect.views.skeleton_layout.mask.SkeletonMaskable
    public void invalidate() {
        if (BaseExtensionsKt.isAttachedToWindowCompat(e()) && e().getVisibility() == 0) {
            start();
        } else {
            stop();
        }
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.mask.SkeletonMask, com.psa.mym.mycitroenconnect.views.skeleton_layout.mask.SkeletonMaskable
    public void start() {
        if (this.animation == null) {
            Looper myLooper = Looper.myLooper();
            if (myLooper == null) {
                myLooper = Looper.getMainLooper();
            }
            this.animation = new Handler(myLooper);
            Runnable runnable = new Runnable() { // from class: com.psa.mym.mycitroenconnect.views.skeleton_layout.mask.SkeletonMaskShimmer$start$1
                @Override // java.lang.Runnable
                public void run() {
                    Handler handler;
                    long refreshIntervalInMillis;
                    SkeletonMaskShimmer.this.updateShimmer();
                    handler = SkeletonMaskShimmer.this.animation;
                    if (handler != null) {
                        refreshIntervalInMillis = SkeletonMaskShimmer.this.getRefreshIntervalInMillis();
                        handler.postDelayed(this, refreshIntervalInMillis);
                    }
                }
            };
            this.animationTask = runnable;
            Handler handler = this.animation;
            if (handler != null) {
                handler.post(runnable);
            }
        }
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.mask.SkeletonMask, com.psa.mym.mycitroenconnect.views.skeleton_layout.mask.SkeletonMaskable
    public void stop() {
        Handler handler;
        Runnable runnable = this.animationTask;
        if (runnable != null && (handler = this.animation) != null) {
            handler.removeCallbacks(runnable);
        }
        this.animation = null;
    }
}
