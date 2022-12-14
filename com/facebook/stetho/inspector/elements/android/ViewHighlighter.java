package com.facebook.stetho.inspector.elements.android;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Util;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class ViewHighlighter {

    /* loaded from: classes.dex */
    private static final class NoopHighlighter extends ViewHighlighter {
        private NoopHighlighter() {
        }

        @Override // com.facebook.stetho.inspector.elements.android.ViewHighlighter
        public void clearHighlight() {
        }

        @Override // com.facebook.stetho.inspector.elements.android.ViewHighlighter
        public void setHighlightedView(View view, @Nullable Rect rect, int i2) {
        }
    }

    @TargetApi(18)
    /* loaded from: classes.dex */
    private static final class OverlayHighlighter extends ViewHighlighter {
        private View mHighlightedView;
        private final ViewHighlightOverlays mHighlightOverlays = ViewHighlightOverlays.newInstance();
        private final Rect mHighlightedBounds = new Rect();
        private final Rect mEmptyRect = new Rect();
        private AtomicReference<View> mViewToHighlight = new AtomicReference<>();
        private AtomicReference<Rect> mBoundsToHighlight = new AtomicReference<>();
        private AtomicInteger mContentColor = new AtomicInteger();
        private final Runnable mHighlightViewOnUiThreadRunnable = new Runnable() { // from class: com.facebook.stetho.inspector.elements.android.ViewHighlighter.OverlayHighlighter.1
            @Override // java.lang.Runnable
            public void run() {
                OverlayHighlighter.this.highlightViewOnUiThread();
            }
        };
        private final Handler mHandler = new Handler(Looper.getMainLooper());

        /* JADX INFO: Access modifiers changed from: private */
        public void highlightViewOnUiThread() {
            View andSet = this.mViewToHighlight.getAndSet(null);
            Rect andSet2 = this.mBoundsToHighlight.getAndSet(null);
            if (andSet2 == null) {
                andSet2 = this.mEmptyRect;
            }
            if (andSet == this.mHighlightedView && this.mHighlightedBounds.equals(andSet2)) {
                return;
            }
            View view = this.mHighlightedView;
            if (view != null) {
                this.mHighlightOverlays.removeHighlight(view);
            }
            if (andSet != null) {
                this.mHighlightOverlays.highlightView(andSet, andSet2, this.mContentColor.get());
            }
            this.mHighlightedView = andSet;
            Rect rect = this.mHighlightedBounds;
            if (andSet2 == null) {
                rect.setEmpty();
            } else {
                rect.set(andSet2);
            }
        }

        private void setHighlightedViewImpl(@Nullable View view, @Nullable Rect rect, int i2) {
            this.mHandler.removeCallbacks(this.mHighlightViewOnUiThreadRunnable);
            this.mViewToHighlight.set(view);
            this.mBoundsToHighlight.set(rect);
            this.mContentColor.set(i2);
            this.mHandler.postDelayed(this.mHighlightViewOnUiThreadRunnable, 100L);
        }

        @Override // com.facebook.stetho.inspector.elements.android.ViewHighlighter
        public void clearHighlight() {
            setHighlightedViewImpl(null, null, 0);
        }

        @Override // com.facebook.stetho.inspector.elements.android.ViewHighlighter
        public void setHighlightedView(View view, @Nullable Rect rect, int i2) {
            setHighlightedViewImpl((View) Util.throwIfNull(view), rect, i2);
        }
    }

    protected ViewHighlighter() {
    }

    public static ViewHighlighter newInstance() {
        if (Build.VERSION.SDK_INT >= 18) {
            return new OverlayHighlighter();
        }
        LogUtil.w("Running on pre-JBMR2: View highlighting is not supported");
        return new NoopHighlighter();
    }

    public abstract void clearHighlight();

    public abstract void setHighlightedView(View view, @Nullable Rect rect, int i2);
}
