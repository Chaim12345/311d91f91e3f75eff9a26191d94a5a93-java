package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public class ContentLoadingProgressBar extends ProgressBar {
    private static final int MIN_DELAY = 500;
    private static final int MIN_SHOW_TIME = 500;

    /* renamed from: a  reason: collision with root package name */
    long f2736a;

    /* renamed from: b  reason: collision with root package name */
    boolean f2737b;

    /* renamed from: c  reason: collision with root package name */
    boolean f2738c;

    /* renamed from: d  reason: collision with root package name */
    boolean f2739d;
    private final Runnable mDelayedHide;
    private final Runnable mDelayedShow;

    public ContentLoadingProgressBar(@NonNull Context context) {
        this(context, null);
    }

    public ContentLoadingProgressBar(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f2736a = -1L;
        this.f2737b = false;
        this.f2738c = false;
        this.f2739d = false;
        this.mDelayedHide = new Runnable() { // from class: androidx.core.widget.ContentLoadingProgressBar.1
            @Override // java.lang.Runnable
            public void run() {
                ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
                contentLoadingProgressBar.f2737b = false;
                contentLoadingProgressBar.f2736a = -1L;
                contentLoadingProgressBar.setVisibility(8);
            }
        };
        this.mDelayedShow = new Runnable() { // from class: androidx.core.widget.ContentLoadingProgressBar.2
            @Override // java.lang.Runnable
            public void run() {
                ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
                contentLoadingProgressBar.f2738c = false;
                if (contentLoadingProgressBar.f2739d) {
                    return;
                }
                contentLoadingProgressBar.f2736a = System.currentTimeMillis();
                ContentLoadingProgressBar.this.setVisibility(0);
            }
        };
    }

    private void removeCallbacks() {
        removeCallbacks(this.mDelayedHide);
        removeCallbacks(this.mDelayedShow);
    }

    public synchronized void hide() {
        this.f2739d = true;
        removeCallbacks(this.mDelayedShow);
        this.f2738c = false;
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = this.f2736a;
        long j3 = currentTimeMillis - j2;
        if (j3 < 500 && j2 != -1) {
            if (!this.f2737b) {
                postDelayed(this.mDelayedHide, 500 - j3);
                this.f2737b = true;
            }
        }
        setVisibility(8);
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        removeCallbacks();
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks();
    }

    public synchronized void show() {
        this.f2736a = -1L;
        this.f2739d = false;
        removeCallbacks(this.mDelayedHide);
        this.f2737b = false;
        if (!this.f2738c) {
            postDelayed(this.mDelayedShow, 500L);
            this.f2738c = true;
        }
    }
}
