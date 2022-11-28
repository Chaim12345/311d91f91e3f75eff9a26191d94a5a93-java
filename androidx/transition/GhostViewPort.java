package androidx.transition;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class GhostViewPort extends ViewGroup implements GhostView {

    /* renamed from: a  reason: collision with root package name */
    ViewGroup f4097a;

    /* renamed from: b  reason: collision with root package name */
    View f4098b;

    /* renamed from: c  reason: collision with root package name */
    final View f4099c;

    /* renamed from: d  reason: collision with root package name */
    int f4100d;
    @Nullable
    private Matrix mMatrix;
    private final ViewTreeObserver.OnPreDrawListener mOnPreDrawListener;

    GhostViewPort(View view) {
        super(view.getContext());
        this.mOnPreDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: androidx.transition.GhostViewPort.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                View view2;
                ViewCompat.postInvalidateOnAnimation(GhostViewPort.this);
                GhostViewPort ghostViewPort = GhostViewPort.this;
                ViewGroup viewGroup = ghostViewPort.f4097a;
                if (viewGroup == null || (view2 = ghostViewPort.f4098b) == null) {
                    return true;
                }
                viewGroup.endViewTransition(view2);
                ViewCompat.postInvalidateOnAnimation(GhostViewPort.this.f4097a);
                GhostViewPort ghostViewPort2 = GhostViewPort.this;
                ghostViewPort2.f4097a = null;
                ghostViewPort2.f4098b = null;
                return true;
            }
        };
        this.f4099c = view;
        setWillNotDraw(false);
        setLayerType(2, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GhostViewPort a(View view, ViewGroup viewGroup, Matrix matrix) {
        GhostViewHolder ghostViewHolder;
        if (view.getParent() instanceof ViewGroup) {
            GhostViewHolder b2 = GhostViewHolder.b(viewGroup);
            GhostViewPort d2 = d(view);
            int i2 = 0;
            if (d2 != null && (ghostViewHolder = (GhostViewHolder) d2.getParent()) != b2) {
                i2 = d2.f4100d;
                ghostViewHolder.removeView(d2);
                d2 = null;
            }
            if (d2 == null) {
                if (matrix == null) {
                    matrix = new Matrix();
                    b(view, viewGroup, matrix);
                }
                d2 = new GhostViewPort(view);
                d2.g(matrix);
                if (b2 == null) {
                    b2 = new GhostViewHolder(viewGroup);
                } else {
                    b2.c();
                }
                c(viewGroup, b2);
                c(viewGroup, d2);
                b2.a(d2);
                d2.f4100d = i2;
            } else if (matrix != null) {
                d2.g(matrix);
            }
            d2.f4100d++;
            return d2;
        }
        throw new IllegalArgumentException("Ghosted views must be parented by a ViewGroup");
    }

    static void b(View view, ViewGroup viewGroup, Matrix matrix) {
        ViewGroup viewGroup2 = (ViewGroup) view.getParent();
        matrix.reset();
        ViewUtils.j(viewGroup2, matrix);
        matrix.preTranslate(-viewGroup2.getScrollX(), -viewGroup2.getScrollY());
        ViewUtils.k(viewGroup, matrix);
    }

    static void c(View view, View view2) {
        ViewUtils.g(view2, view2.getLeft(), view2.getTop(), view2.getLeft() + view.getWidth(), view2.getTop() + view.getHeight());
    }

    static GhostViewPort d(View view) {
        return (GhostViewPort) view.getTag(R.id.ghost_view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void e(View view) {
        GhostViewPort d2 = d(view);
        if (d2 != null) {
            int i2 = d2.f4100d - 1;
            d2.f4100d = i2;
            if (i2 <= 0) {
                ((GhostViewHolder) d2.getParent()).removeView(d2);
            }
        }
    }

    static void f(@NonNull View view, @Nullable GhostViewPort ghostViewPort) {
        view.setTag(R.id.ghost_view, ghostViewPort);
    }

    void g(@NonNull Matrix matrix) {
        this.mMatrix = matrix;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        f(this.f4099c, this);
        this.f4099c.getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        ViewUtils.i(this.f4099c, 4);
        if (this.f4099c.getParent() != null) {
            ((View) this.f4099c.getParent()).invalidate();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        this.f4099c.getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        ViewUtils.i(this.f4099c, 0);
        f(this.f4099c, null);
        if (this.f4099c.getParent() != null) {
            ((View) this.f4099c.getParent()).invalidate();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        CanvasUtils.a(canvas, true);
        canvas.setMatrix(this.mMatrix);
        ViewUtils.i(this.f4099c, 0);
        this.f4099c.invalidate();
        ViewUtils.i(this.f4099c, 4);
        drawChild(canvas, this.f4099c, getDrawingTime());
        CanvasUtils.a(canvas, false);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
    }

    @Override // androidx.transition.GhostView
    public void reserveEndViewTransition(ViewGroup viewGroup, View view) {
        this.f4097a = viewGroup;
        this.f4098b = view;
    }

    @Override // android.view.View, androidx.transition.GhostView
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        if (d(this.f4099c) == this) {
            ViewUtils.i(this.f4099c, i2 == 0 ? 4 : 0);
        }
    }
}
