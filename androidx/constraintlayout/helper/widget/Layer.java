package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R;
/* loaded from: classes.dex */
public class Layer extends ConstraintHelper {
    private static final String TAG = "Layer";

    /* renamed from: i  reason: collision with root package name */
    ConstraintLayout f2016i;

    /* renamed from: j  reason: collision with root package name */
    protected float f2017j;

    /* renamed from: k  reason: collision with root package name */
    protected float f2018k;

    /* renamed from: l  reason: collision with root package name */
    protected float f2019l;

    /* renamed from: m  reason: collision with root package name */
    protected float f2020m;
    private boolean mApplyElevationOnAttach;
    private boolean mApplyVisibilityOnAttach;
    private float mGroupRotateAngle;
    private float mRotationCenterX;
    private float mRotationCenterY;
    private float mScaleX;
    private float mScaleY;
    private float mShiftX;
    private float mShiftY;

    /* renamed from: n  reason: collision with root package name */
    protected float f2021n;

    /* renamed from: o  reason: collision with root package name */
    protected float f2022o;

    /* renamed from: p  reason: collision with root package name */
    boolean f2023p;

    /* renamed from: q  reason: collision with root package name */
    View[] f2024q;

    public Layer(Context context) {
        super(context);
        this.mRotationCenterX = Float.NaN;
        this.mRotationCenterY = Float.NaN;
        this.mGroupRotateAngle = Float.NaN;
        this.mScaleX = 1.0f;
        this.mScaleY = 1.0f;
        this.f2017j = Float.NaN;
        this.f2018k = Float.NaN;
        this.f2019l = Float.NaN;
        this.f2020m = Float.NaN;
        this.f2021n = Float.NaN;
        this.f2022o = Float.NaN;
        this.f2023p = true;
        this.f2024q = null;
        this.mShiftX = 0.0f;
        this.mShiftY = 0.0f;
    }

    public Layer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRotationCenterX = Float.NaN;
        this.mRotationCenterY = Float.NaN;
        this.mGroupRotateAngle = Float.NaN;
        this.mScaleX = 1.0f;
        this.mScaleY = 1.0f;
        this.f2017j = Float.NaN;
        this.f2018k = Float.NaN;
        this.f2019l = Float.NaN;
        this.f2020m = Float.NaN;
        this.f2021n = Float.NaN;
        this.f2022o = Float.NaN;
        this.f2023p = true;
        this.f2024q = null;
        this.mShiftX = 0.0f;
        this.mShiftY = 0.0f;
    }

    public Layer(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mRotationCenterX = Float.NaN;
        this.mRotationCenterY = Float.NaN;
        this.mGroupRotateAngle = Float.NaN;
        this.mScaleX = 1.0f;
        this.mScaleY = 1.0f;
        this.f2017j = Float.NaN;
        this.f2018k = Float.NaN;
        this.f2019l = Float.NaN;
        this.f2020m = Float.NaN;
        this.f2021n = Float.NaN;
        this.f2022o = Float.NaN;
        this.f2023p = true;
        this.f2024q = null;
        this.mShiftX = 0.0f;
        this.mShiftY = 0.0f;
    }

    private void reCacheViews() {
        int i2;
        if (this.f2016i == null || (i2 = this.f2240b) == 0) {
            return;
        }
        View[] viewArr = this.f2024q;
        if (viewArr == null || viewArr.length != i2) {
            this.f2024q = new View[i2];
        }
        for (int i3 = 0; i3 < this.f2240b; i3++) {
            this.f2024q[i3] = this.f2016i.getViewById(this.f2239a[i3]);
        }
    }

    private void transform() {
        if (this.f2016i == null) {
            return;
        }
        if (this.f2024q == null) {
            reCacheViews();
        }
        f();
        double radians = Float.isNaN(this.mGroupRotateAngle) ? 0.0d : Math.toRadians(this.mGroupRotateAngle);
        float sin = (float) Math.sin(radians);
        float cos = (float) Math.cos(radians);
        float f2 = this.mScaleX;
        float f3 = f2 * cos;
        float f4 = this.mScaleY;
        float f5 = (-f4) * sin;
        float f6 = f2 * sin;
        float f7 = f4 * cos;
        for (int i2 = 0; i2 < this.f2240b; i2++) {
            View view = this.f2024q[i2];
            float left = ((view.getLeft() + view.getRight()) / 2) - this.f2017j;
            float top = ((view.getTop() + view.getBottom()) / 2) - this.f2018k;
            view.setTranslationX((((f3 * left) + (f5 * top)) - left) + this.mShiftX);
            view.setTranslationY((((left * f6) + (f7 * top)) - top) + this.mShiftY);
            view.setScaleY(this.mScaleY);
            view.setScaleX(this.mScaleX);
            if (!Float.isNaN(this.mGroupRotateAngle)) {
                view.setRotation(this.mGroupRotateAngle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void c(ConstraintLayout constraintLayout) {
        b(constraintLayout);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void e(AttributeSet attributeSet) {
        super.e(attributeSet);
        this.f2243e = false;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.ConstraintLayout_Layout_android_visibility) {
                    this.mApplyVisibilityOnAttach = true;
                } else if (index == R.styleable.ConstraintLayout_Layout_android_elevation) {
                    this.mApplyElevationOnAttach = true;
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    protected void f() {
        if (this.f2016i == null) {
            return;
        }
        if (this.f2023p || Float.isNaN(this.f2017j) || Float.isNaN(this.f2018k)) {
            if (!Float.isNaN(this.mRotationCenterX) && !Float.isNaN(this.mRotationCenterY)) {
                this.f2018k = this.mRotationCenterY;
                this.f2017j = this.mRotationCenterX;
                return;
            }
            View[] d2 = d(this.f2016i);
            int left = d2[0].getLeft();
            int top = d2[0].getTop();
            int right = d2[0].getRight();
            int bottom = d2[0].getBottom();
            for (int i2 = 0; i2 < this.f2240b; i2++) {
                View view = d2[i2];
                left = Math.min(left, view.getLeft());
                top = Math.min(top, view.getTop());
                right = Math.max(right, view.getRight());
                bottom = Math.max(bottom, view.getBottom());
            }
            this.f2019l = right;
            this.f2020m = bottom;
            this.f2021n = left;
            this.f2022o = top;
            this.f2017j = Float.isNaN(this.mRotationCenterX) ? (left + right) / 2 : this.mRotationCenterX;
            this.f2018k = Float.isNaN(this.mRotationCenterY) ? (top + bottom) / 2 : this.mRotationCenterY;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f2016i = (ConstraintLayout) getParent();
        if (this.mApplyVisibilityOnAttach || this.mApplyElevationOnAttach) {
            int visibility = getVisibility();
            float elevation = Build.VERSION.SDK_INT >= 21 ? getElevation() : 0.0f;
            for (int i2 = 0; i2 < this.f2240b; i2++) {
                View viewById = this.f2016i.getViewById(this.f2239a[i2]);
                if (viewById != null) {
                    if (this.mApplyVisibilityOnAttach) {
                        viewById.setVisibility(visibility);
                    }
                    if (this.mApplyElevationOnAttach && elevation > 0.0f && Build.VERSION.SDK_INT >= 21) {
                        viewById.setTranslationZ(viewById.getTranslationZ() + elevation);
                    }
                }
            }
        }
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        a();
    }

    @Override // android.view.View
    public void setPivotX(float f2) {
        this.mRotationCenterX = f2;
        transform();
    }

    @Override // android.view.View
    public void setPivotY(float f2) {
        this.mRotationCenterY = f2;
        transform();
    }

    @Override // android.view.View
    public void setRotation(float f2) {
        this.mGroupRotateAngle = f2;
        transform();
    }

    @Override // android.view.View
    public void setScaleX(float f2) {
        this.mScaleX = f2;
        transform();
    }

    @Override // android.view.View
    public void setScaleY(float f2) {
        this.mScaleY = f2;
        transform();
    }

    @Override // android.view.View
    public void setTranslationX(float f2) {
        this.mShiftX = f2;
        transform();
    }

    @Override // android.view.View
    public void setTranslationY(float f2) {
        this.mShiftY = f2;
        transform();
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        a();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void updatePostLayout(ConstraintLayout constraintLayout) {
        reCacheViews();
        this.f2017j = Float.NaN;
        this.f2018k = Float.NaN;
        ConstraintWidget constraintWidget = ((ConstraintLayout.LayoutParams) getLayoutParams()).getConstraintWidget();
        constraintWidget.setWidth(0);
        constraintWidget.setHeight(0);
        f();
        layout(((int) this.f2021n) - getPaddingLeft(), ((int) this.f2022o) - getPaddingTop(), ((int) this.f2019l) + getPaddingRight(), ((int) this.f2020m) + getPaddingBottom());
        transform();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void updatePreDraw(ConstraintLayout constraintLayout) {
        this.f2016i = constraintLayout;
        float rotation = getRotation();
        if (rotation == 0.0f && Float.isNaN(this.mGroupRotateAngle)) {
            return;
        }
        this.mGroupRotateAngle = rotation;
    }
}
