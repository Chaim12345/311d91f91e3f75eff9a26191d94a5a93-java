package com.rd.animation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes3.dex */
public class ValueAnimation {
    private ColorAnimation colorAnimation;
    private FillAnimation fillAnimation;
    private ScaleAnimation scaleAnimation;
    private SlideAnimation slideAnimation;
    private ThinWormAnimation thinWormAnimation;
    private UpdateListener updateListener;
    private WormAnimation wormAnimation;

    /* loaded from: classes3.dex */
    public interface UpdateListener {
        void onColorAnimationUpdated(int i2, int i3);

        void onFillAnimationUpdated(int i2, int i3, int i4, int i5, int i6, int i7);

        void onScaleAnimationUpdated(int i2, int i3, int i4, int i5);

        void onSlideAnimationUpdated(int i2);

        void onThinWormAnimationUpdated(int i2, int i3, int i4);

        void onWormAnimationUpdated(int i2, int i3);
    }

    public ValueAnimation(@Nullable UpdateListener updateListener) {
        this.updateListener = updateListener;
    }

    @NonNull
    public ColorAnimation color() {
        if (this.colorAnimation == null) {
            this.colorAnimation = new ColorAnimation(this.updateListener);
        }
        return this.colorAnimation;
    }

    @NonNull
    public FillAnimation fill() {
        if (this.fillAnimation == null) {
            this.fillAnimation = new FillAnimation(this.updateListener);
        }
        return this.fillAnimation;
    }

    @NonNull
    public ScaleAnimation scale() {
        if (this.scaleAnimation == null) {
            this.scaleAnimation = new ScaleAnimation(this.updateListener);
        }
        return this.scaleAnimation;
    }

    @NonNull
    public SlideAnimation slide() {
        if (this.slideAnimation == null) {
            this.slideAnimation = new SlideAnimation(this.updateListener);
        }
        return this.slideAnimation;
    }

    @NonNull
    public ThinWormAnimation thinWorm() {
        if (this.thinWormAnimation == null) {
            this.thinWormAnimation = new ThinWormAnimation(this.updateListener);
        }
        return this.thinWormAnimation;
    }

    @NonNull
    public WormAnimation worm() {
        if (this.wormAnimation == null) {
            this.wormAnimation = new WormAnimation(this.updateListener);
        }
        return this.wormAnimation;
    }
}
