package com.github.mikephil.charting.utils;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.View;
/* loaded from: classes.dex */
public class ViewPortHandler {

    /* renamed from: a  reason: collision with root package name */
    protected final Matrix f5471a = new Matrix();

    /* renamed from: b  reason: collision with root package name */
    protected RectF f5472b = new RectF();

    /* renamed from: c  reason: collision with root package name */
    protected float f5473c = 0.0f;

    /* renamed from: d  reason: collision with root package name */
    protected float f5474d = 0.0f;
    private float mMinScaleY = 1.0f;
    private float mMaxScaleY = Float.MAX_VALUE;
    private float mMinScaleX = 1.0f;
    private float mMaxScaleX = Float.MAX_VALUE;
    private float mScaleX = 1.0f;
    private float mScaleY = 1.0f;
    private float mTransX = 0.0f;
    private float mTransY = 0.0f;
    private float mTransOffsetX = 0.0f;
    private float mTransOffsetY = 0.0f;

    /* renamed from: e  reason: collision with root package name */
    protected float[] f5475e = new float[9];

    /* renamed from: f  reason: collision with root package name */
    protected Matrix f5476f = new Matrix();

    /* renamed from: g  reason: collision with root package name */
    protected final float[] f5477g = new float[9];

    public boolean canZoomInMoreX() {
        return this.mScaleX < this.mMaxScaleX;
    }

    public boolean canZoomInMoreY() {
        return this.mScaleY < this.mMaxScaleY;
    }

    public boolean canZoomOutMoreX() {
        return this.mScaleX > this.mMinScaleX;
    }

    public boolean canZoomOutMoreY() {
        return this.mScaleY > this.mMinScaleY;
    }

    public void centerViewPort(float[] fArr, View view) {
        Matrix matrix = this.f5476f;
        matrix.reset();
        matrix.set(this.f5471a);
        matrix.postTranslate(-(fArr[0] - offsetLeft()), -(fArr[1] - offsetTop()));
        refresh(matrix, view, true);
    }

    public float contentBottom() {
        return this.f5472b.bottom;
    }

    public float contentHeight() {
        return this.f5472b.height();
    }

    public float contentLeft() {
        return this.f5472b.left;
    }

    public float contentRight() {
        return this.f5472b.right;
    }

    public float contentTop() {
        return this.f5472b.top;
    }

    public float contentWidth() {
        return this.f5472b.width();
    }

    public Matrix fitScreen() {
        Matrix matrix = new Matrix();
        fitScreen(matrix);
        return matrix;
    }

    public void fitScreen(Matrix matrix) {
        this.mMinScaleX = 1.0f;
        this.mMinScaleY = 1.0f;
        matrix.set(this.f5471a);
        float[] fArr = this.f5475e;
        for (int i2 = 0; i2 < 9; i2++) {
            fArr[i2] = 0.0f;
        }
        matrix.getValues(fArr);
        fArr[2] = 0.0f;
        fArr[5] = 0.0f;
        fArr[0] = 1.0f;
        fArr[4] = 1.0f;
        matrix.setValues(fArr);
    }

    public float getChartHeight() {
        return this.f5474d;
    }

    public float getChartWidth() {
        return this.f5473c;
    }

    public MPPointF getContentCenter() {
        return MPPointF.getInstance(this.f5472b.centerX(), this.f5472b.centerY());
    }

    public RectF getContentRect() {
        return this.f5472b;
    }

    public Matrix getMatrixTouch() {
        return this.f5471a;
    }

    public float getMaxScaleX() {
        return this.mMaxScaleX;
    }

    public float getMaxScaleY() {
        return this.mMaxScaleY;
    }

    public float getMinScaleX() {
        return this.mMinScaleX;
    }

    public float getMinScaleY() {
        return this.mMinScaleY;
    }

    public float getScaleX() {
        return this.mScaleX;
    }

    public float getScaleY() {
        return this.mScaleY;
    }

    public float getSmallestContentExtension() {
        return Math.min(this.f5472b.width(), this.f5472b.height());
    }

    public float getTransX() {
        return this.mTransX;
    }

    public float getTransY() {
        return this.mTransY;
    }

    public boolean hasChartDimens() {
        return this.f5474d > 0.0f && this.f5473c > 0.0f;
    }

    public boolean hasNoDragOffset() {
        return this.mTransOffsetX <= 0.0f && this.mTransOffsetY <= 0.0f;
    }

    public boolean isFullyZoomedOut() {
        return isFullyZoomedOutX() && isFullyZoomedOutY();
    }

    public boolean isFullyZoomedOutX() {
        float f2 = this.mScaleX;
        float f3 = this.mMinScaleX;
        return f2 <= f3 && f3 <= 1.0f;
    }

    public boolean isFullyZoomedOutY() {
        float f2 = this.mScaleY;
        float f3 = this.mMinScaleY;
        return f2 <= f3 && f3 <= 1.0f;
    }

    public boolean isInBounds(float f2, float f3) {
        return isInBoundsX(f2) && isInBoundsY(f3);
    }

    public boolean isInBoundsBottom(float f2) {
        return this.f5472b.bottom >= ((float) ((int) (f2 * 100.0f))) / 100.0f;
    }

    public boolean isInBoundsLeft(float f2) {
        return this.f5472b.left <= f2 + 1.0f;
    }

    public boolean isInBoundsRight(float f2) {
        return this.f5472b.right >= (((float) ((int) (f2 * 100.0f))) / 100.0f) - 1.0f;
    }

    public boolean isInBoundsTop(float f2) {
        return this.f5472b.top <= f2;
    }

    public boolean isInBoundsX(float f2) {
        return isInBoundsLeft(f2) && isInBoundsRight(f2);
    }

    public boolean isInBoundsY(float f2) {
        return isInBoundsTop(f2) && isInBoundsBottom(f2);
    }

    public void limitTransAndScale(Matrix matrix, RectF rectF) {
        float f2;
        matrix.getValues(this.f5477g);
        float[] fArr = this.f5477g;
        float f3 = fArr[2];
        float f4 = fArr[0];
        float f5 = fArr[5];
        float f6 = fArr[4];
        this.mScaleX = Math.min(Math.max(this.mMinScaleX, f4), this.mMaxScaleX);
        this.mScaleY = Math.min(Math.max(this.mMinScaleY, f6), this.mMaxScaleY);
        float f7 = 0.0f;
        if (rectF != null) {
            f7 = rectF.width();
            f2 = rectF.height();
        } else {
            f2 = 0.0f;
        }
        this.mTransX = Math.min(Math.max(f3, ((-f7) * (this.mScaleX - 1.0f)) - this.mTransOffsetX), this.mTransOffsetX);
        float max = Math.max(Math.min(f5, (f2 * (this.mScaleY - 1.0f)) + this.mTransOffsetY), -this.mTransOffsetY);
        this.mTransY = max;
        float[] fArr2 = this.f5477g;
        fArr2[2] = this.mTransX;
        fArr2[0] = this.mScaleX;
        fArr2[5] = max;
        fArr2[4] = this.mScaleY;
        matrix.setValues(fArr2);
    }

    public float offsetBottom() {
        return this.f5474d - this.f5472b.bottom;
    }

    public float offsetLeft() {
        return this.f5472b.left;
    }

    public float offsetRight() {
        return this.f5473c - this.f5472b.right;
    }

    public float offsetTop() {
        return this.f5472b.top;
    }

    public Matrix refresh(Matrix matrix, View view, boolean z) {
        this.f5471a.set(matrix);
        limitTransAndScale(this.f5471a, this.f5472b);
        if (z) {
            view.invalidate();
        }
        matrix.set(this.f5471a);
        return matrix;
    }

    public void resetZoom(Matrix matrix) {
        matrix.reset();
        matrix.set(this.f5471a);
        matrix.postScale(1.0f, 1.0f, 0.0f, 0.0f);
    }

    public void restrainViewPort(float f2, float f3, float f4, float f5) {
        this.f5472b.set(f2, f3, this.f5473c - f4, this.f5474d - f5);
    }

    public void setChartDimens(float f2, float f3) {
        float offsetLeft = offsetLeft();
        float offsetTop = offsetTop();
        float offsetRight = offsetRight();
        float offsetBottom = offsetBottom();
        this.f5474d = f3;
        this.f5473c = f2;
        restrainViewPort(offsetLeft, offsetTop, offsetRight, offsetBottom);
    }

    public void setDragOffsetX(float f2) {
        this.mTransOffsetX = Utils.convertDpToPixel(f2);
    }

    public void setDragOffsetY(float f2) {
        this.mTransOffsetY = Utils.convertDpToPixel(f2);
    }

    public void setMaximumScaleX(float f2) {
        if (f2 == 0.0f) {
            f2 = Float.MAX_VALUE;
        }
        this.mMaxScaleX = f2;
        limitTransAndScale(this.f5471a, this.f5472b);
    }

    public void setMaximumScaleY(float f2) {
        if (f2 == 0.0f) {
            f2 = Float.MAX_VALUE;
        }
        this.mMaxScaleY = f2;
        limitTransAndScale(this.f5471a, this.f5472b);
    }

    public void setMinMaxScaleX(float f2, float f3) {
        if (f2 < 1.0f) {
            f2 = 1.0f;
        }
        if (f3 == 0.0f) {
            f3 = Float.MAX_VALUE;
        }
        this.mMinScaleX = f2;
        this.mMaxScaleX = f3;
        limitTransAndScale(this.f5471a, this.f5472b);
    }

    public void setMinMaxScaleY(float f2, float f3) {
        if (f2 < 1.0f) {
            f2 = 1.0f;
        }
        if (f3 == 0.0f) {
            f3 = Float.MAX_VALUE;
        }
        this.mMinScaleY = f2;
        this.mMaxScaleY = f3;
        limitTransAndScale(this.f5471a, this.f5472b);
    }

    public void setMinimumScaleX(float f2) {
        if (f2 < 1.0f) {
            f2 = 1.0f;
        }
        this.mMinScaleX = f2;
        limitTransAndScale(this.f5471a, this.f5472b);
    }

    public void setMinimumScaleY(float f2) {
        if (f2 < 1.0f) {
            f2 = 1.0f;
        }
        this.mMinScaleY = f2;
        limitTransAndScale(this.f5471a, this.f5472b);
    }

    public Matrix setZoom(float f2, float f3) {
        Matrix matrix = new Matrix();
        setZoom(f2, f3, matrix);
        return matrix;
    }

    public Matrix setZoom(float f2, float f3, float f4, float f5) {
        Matrix matrix = new Matrix();
        matrix.set(this.f5471a);
        matrix.setScale(f2, f3, f4, f5);
        return matrix;
    }

    public void setZoom(float f2, float f3, Matrix matrix) {
        matrix.reset();
        matrix.set(this.f5471a);
        matrix.setScale(f2, f3);
    }

    public Matrix translate(float[] fArr) {
        Matrix matrix = new Matrix();
        translate(fArr, matrix);
        return matrix;
    }

    public void translate(float[] fArr, Matrix matrix) {
        matrix.reset();
        matrix.set(this.f5471a);
        matrix.postTranslate(-(fArr[0] - offsetLeft()), -(fArr[1] - offsetTop()));
    }

    public Matrix zoom(float f2, float f3) {
        Matrix matrix = new Matrix();
        zoom(f2, f3, matrix);
        return matrix;
    }

    public Matrix zoom(float f2, float f3, float f4, float f5) {
        Matrix matrix = new Matrix();
        zoom(f2, f3, f4, f5, matrix);
        return matrix;
    }

    public void zoom(float f2, float f3, float f4, float f5, Matrix matrix) {
        matrix.reset();
        matrix.set(this.f5471a);
        matrix.postScale(f2, f3, f4, f5);
    }

    public void zoom(float f2, float f3, Matrix matrix) {
        matrix.reset();
        matrix.set(this.f5471a);
        matrix.postScale(f2, f3);
    }

    public Matrix zoomIn(float f2, float f3) {
        Matrix matrix = new Matrix();
        zoomIn(f2, f3, matrix);
        return matrix;
    }

    public void zoomIn(float f2, float f3, Matrix matrix) {
        matrix.reset();
        matrix.set(this.f5471a);
        matrix.postScale(1.4f, 1.4f, f2, f3);
    }

    public Matrix zoomOut(float f2, float f3) {
        Matrix matrix = new Matrix();
        zoomOut(f2, f3, matrix);
        return matrix;
    }

    public void zoomOut(float f2, float f3, Matrix matrix) {
        matrix.reset();
        matrix.set(this.f5471a);
        matrix.postScale(0.7f, 0.7f, f2, f3);
    }
}
