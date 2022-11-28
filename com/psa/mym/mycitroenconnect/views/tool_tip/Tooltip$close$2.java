package com.psa.mym.mycitroenconnect.views.tool_tip;

import android.view.animation.Animation;
import android.widget.FrameLayout;
import com.psa.mym.mycitroenconnect.views.tool_tip.Tooltip;
import com.psa.mym.mycitroenconnect.views.tool_tip.Tooltip$close$2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class Tooltip$close$2 implements Animation.AnimationListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Tooltip f10801a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Tooltip$close$2(Tooltip tooltip) {
        this.f10801a = tooltip;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onAnimationEnd$lambda-0  reason: not valid java name */
    public static final void m182onAnimationEnd$lambda0(Tooltip this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.closeNow();
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(@Nullable Animation animation) {
        FrameLayout overlay$app_preprodQa = this.f10801a.getOverlay$app_preprodQa();
        final Tooltip tooltip = this.f10801a;
        overlay$app_preprodQa.post(new Runnable() { // from class: r.h
            @Override // java.lang.Runnable
            public final void run() {
                Tooltip$close$2.m182onAnimationEnd$lambda0(Tooltip.this);
            }
        });
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationRepeat(@Nullable Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationStart(@Nullable Animation animation) {
    }
}
