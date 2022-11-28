package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewParent;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R;
/* loaded from: classes.dex */
public class MotionTelltales extends MockView {
    private static final String TAG = "MotionTelltales";

    /* renamed from: b  reason: collision with root package name */
    MotionLayout f2230b;

    /* renamed from: c  reason: collision with root package name */
    float[] f2231c;

    /* renamed from: d  reason: collision with root package name */
    Matrix f2232d;

    /* renamed from: e  reason: collision with root package name */
    int f2233e;

    /* renamed from: f  reason: collision with root package name */
    int f2234f;

    /* renamed from: g  reason: collision with root package name */
    float f2235g;
    private Paint mPaintTelltales;

    public MotionTelltales(Context context) {
        super(context);
        this.mPaintTelltales = new Paint();
        this.f2231c = new float[2];
        this.f2232d = new Matrix();
        this.f2233e = 0;
        this.f2234f = -65281;
        this.f2235g = 0.25f;
        init(context, null);
    }

    public MotionTelltales(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPaintTelltales = new Paint();
        this.f2231c = new float[2];
        this.f2232d = new Matrix();
        this.f2233e = 0;
        this.f2234f = -65281;
        this.f2235g = 0.25f;
        init(context, attributeSet);
    }

    public MotionTelltales(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mPaintTelltales = new Paint();
        this.f2231c = new float[2];
        this.f2232d = new Matrix();
        this.f2233e = 0;
        this.f2234f = -65281;
        this.f2235g = 0.25f;
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MotionTelltales);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.MotionTelltales_telltales_tailColor) {
                    this.f2234f = obtainStyledAttributes.getColor(index, this.f2234f);
                } else if (index == R.styleable.MotionTelltales_telltales_velocityMode) {
                    this.f2233e = obtainStyledAttributes.getInt(index, this.f2233e);
                } else if (index == R.styleable.MotionTelltales_telltales_tailScale) {
                    this.f2235g = obtainStyledAttributes.getFloat(index, this.f2235g);
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.mPaintTelltales.setColor(this.f2234f);
        this.mPaintTelltales.setStrokeWidth(5.0f);
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // androidx.constraintlayout.utils.widget.MockView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getMatrix().invert(this.f2232d);
        if (this.f2230b == null) {
            ViewParent parent = getParent();
            if (parent instanceof MotionLayout) {
                this.f2230b = (MotionLayout) parent;
                return;
            }
            return;
        }
        int width = getWidth();
        int height = getHeight();
        float[] fArr = {0.1f, 0.25f, 0.5f, 0.75f, 0.9f};
        for (int i2 = 0; i2 < 5; i2++) {
            float f2 = fArr[i2];
            for (int i3 = 0; i3 < 5; i3++) {
                float f3 = fArr[i3];
                this.f2230b.getViewVelocity(this, f3, f2, this.f2231c, this.f2233e);
                this.f2232d.mapVectors(this.f2231c);
                float f4 = width * f3;
                float f5 = height * f2;
                float[] fArr2 = this.f2231c;
                float f6 = fArr2[0];
                float f7 = this.f2235g;
                float f8 = f5 - (fArr2[1] * f7);
                this.f2232d.mapVectors(fArr2);
                canvas.drawLine(f4, f5, f4 - (f6 * f7), f8, this.mPaintTelltales);
            }
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        postInvalidate();
    }

    public void setText(CharSequence charSequence) {
        this.f2209a = charSequence.toString();
        requestLayout();
    }
}
