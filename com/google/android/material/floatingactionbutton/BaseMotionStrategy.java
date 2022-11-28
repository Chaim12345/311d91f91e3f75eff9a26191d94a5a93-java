package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Property;
import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.MotionSpec;
import java.util.ArrayList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class BaseMotionStrategy implements MotionStrategy {
    private final Context context;
    @Nullable
    private MotionSpec defaultMotionSpec;
    @NonNull
    private final ExtendedFloatingActionButton fab;
    private final ArrayList<Animator.AnimatorListener> listeners = new ArrayList<>();
    @Nullable
    private MotionSpec motionSpec;
    private final AnimatorTracker tracker;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseMotionStrategy(@NonNull ExtendedFloatingActionButton extendedFloatingActionButton, AnimatorTracker animatorTracker) {
        this.fab = extendedFloatingActionButton;
        this.context = extendedFloatingActionButton.getContext();
        this.tracker = animatorTracker;
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public final void addAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        this.listeners.add(animatorListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public AnimatorSet b(@NonNull MotionSpec motionSpec) {
        ArrayList arrayList = new ArrayList();
        if (motionSpec.hasPropertyValues("opacity")) {
            arrayList.add(motionSpec.getAnimator("opacity", this.fab, View.ALPHA));
        }
        if (motionSpec.hasPropertyValues("scale")) {
            arrayList.add(motionSpec.getAnimator("scale", this.fab, View.SCALE_Y));
            arrayList.add(motionSpec.getAnimator("scale", this.fab, View.SCALE_X));
        }
        if (motionSpec.hasPropertyValues("width")) {
            arrayList.add(motionSpec.getAnimator("width", this.fab, ExtendedFloatingActionButton.f7301b));
        }
        if (motionSpec.hasPropertyValues("height")) {
            arrayList.add(motionSpec.getAnimator("height", this.fab, ExtendedFloatingActionButton.f7302c));
        }
        if (motionSpec.hasPropertyValues("paddingStart")) {
            arrayList.add(motionSpec.getAnimator("paddingStart", this.fab, ExtendedFloatingActionButton.f7303d));
        }
        if (motionSpec.hasPropertyValues("paddingEnd")) {
            arrayList.add(motionSpec.getAnimator("paddingEnd", this.fab, ExtendedFloatingActionButton.f7304e));
        }
        if (motionSpec.hasPropertyValues("labelOpacity")) {
            arrayList.add(motionSpec.getAnimator("labelOpacity", this.fab, new Property<ExtendedFloatingActionButton, Float>(Float.class, "LABEL_OPACITY_PROPERTY") { // from class: com.google.android.material.floatingactionbutton.BaseMotionStrategy.1
                @Override // android.util.Property
                public Float get(ExtendedFloatingActionButton extendedFloatingActionButton) {
                    return Float.valueOf(AnimationUtils.lerp(0.0f, 1.0f, (Color.alpha(extendedFloatingActionButton.getCurrentTextColor()) / 255.0f) / Color.alpha(extendedFloatingActionButton.f7305a.getColorForState(extendedFloatingActionButton.getDrawableState(), BaseMotionStrategy.this.fab.f7305a.getDefaultColor()))));
                }

                @Override // android.util.Property
                public void set(ExtendedFloatingActionButton extendedFloatingActionButton, Float f2) {
                    int colorForState = extendedFloatingActionButton.f7305a.getColorForState(extendedFloatingActionButton.getDrawableState(), BaseMotionStrategy.this.fab.f7305a.getDefaultColor());
                    ColorStateList valueOf = ColorStateList.valueOf(Color.argb((int) (AnimationUtils.lerp(0.0f, Color.alpha(colorForState) / 255.0f, f2.floatValue()) * 255.0f), Color.red(colorForState), Color.green(colorForState), Color.blue(colorForState)));
                    if (f2.floatValue() == 1.0f) {
                        extendedFloatingActionButton.n(extendedFloatingActionButton.f7305a);
                    } else {
                        extendedFloatingActionButton.n(valueOf);
                    }
                }
            }));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSetCompat.playTogether(animatorSet, arrayList);
        return animatorSet;
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public AnimatorSet createAnimator() {
        return b(getCurrentMotionSpec());
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public final MotionSpec getCurrentMotionSpec() {
        MotionSpec motionSpec = this.motionSpec;
        if (motionSpec != null) {
            return motionSpec;
        }
        if (this.defaultMotionSpec == null) {
            this.defaultMotionSpec = MotionSpec.createFromResource(this.context, getDefaultMotionSpecResource());
        }
        return (MotionSpec) Preconditions.checkNotNull(this.defaultMotionSpec);
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    @NonNull
    public final List<Animator.AnimatorListener> getListeners() {
        return this.listeners;
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    @Nullable
    public MotionSpec getMotionSpec() {
        return this.motionSpec;
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    @CallSuper
    public void onAnimationCancel() {
        this.tracker.clear();
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    @CallSuper
    public void onAnimationEnd() {
        this.tracker.clear();
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    @CallSuper
    public void onAnimationStart(Animator animator) {
        this.tracker.onNextAnimationStart(animator);
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public final void removeAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        this.listeners.remove(animatorListener);
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public final void setMotionSpec(@Nullable MotionSpec motionSpec) {
        this.motionSpec = motionSpec;
    }
}
