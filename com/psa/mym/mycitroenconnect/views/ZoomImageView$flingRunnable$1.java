package com.psa.mym.mycitroenconnect.views;

import android.widget.OverScroller;
import androidx.core.view.ViewCompat;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes3.dex */
public final class ZoomImageView$flingRunnable$1 implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ZoomImageView f10763a;
    private float lastX;
    private float lastY;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZoomImageView$flingRunnable$1(ZoomImageView zoomImageView) {
        this.f10763a = zoomImageView;
    }

    public final float getLastX() {
        return this.lastX;
    }

    public final float getLastY() {
        return this.lastY;
    }

    @Override // java.lang.Runnable
    public void run() {
        OverScroller overScroller;
        OverScroller overScroller2;
        OverScroller overScroller3;
        OverScroller overScroller4;
        overScroller = this.f10763a.scroller;
        OverScroller overScroller5 = null;
        if (overScroller == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scroller");
            overScroller = null;
        }
        if (overScroller.isFinished()) {
            return;
        }
        overScroller2 = this.f10763a.scroller;
        if (overScroller2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scroller");
            overScroller2 = null;
        }
        if (overScroller2.computeScrollOffset()) {
            overScroller3 = this.f10763a.scroller;
            if (overScroller3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("scroller");
                overScroller3 = null;
            }
            float currX = overScroller3.getCurrX();
            overScroller4 = this.f10763a.scroller;
            if (overScroller4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("scroller");
            } else {
                overScroller5 = overScroller4;
            }
            float currY = overScroller5.getCurrY();
            ZoomImageView.c(this.f10763a, currX - this.lastX, currY - this.lastY, false, 4, null);
            this.lastX = currX;
            this.lastY = currY;
            ViewCompat.postOnAnimation(this.f10763a, this);
        }
    }

    public final void setLastX(float f2) {
        this.lastX = f2;
    }

    public final void setLastY(float f2) {
        this.lastY = f2;
    }
}
