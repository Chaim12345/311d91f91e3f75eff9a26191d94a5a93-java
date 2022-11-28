package com.yalantis.ucrop.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.yalantis.ucrop.callback.BitmapLoadCallback;
import com.yalantis.ucrop.model.ExifInfo;
import com.yalantis.ucrop.util.BitmapLoadUtils;
import com.yalantis.ucrop.util.FastBitmapDrawable;
import com.yalantis.ucrop.util.RectUtils;
/* loaded from: classes3.dex */
public class TransformImageView extends ImageView {
    private static final int MATRIX_VALUES_COUNT = 9;
    private static final int RECT_CENTER_POINT_COORDS = 2;
    private static final int RECT_CORNER_POINTS_COORDS = 8;
    private static final String TAG = "TransformImageView";

    /* renamed from: a  reason: collision with root package name */
    protected final float[] f10860a;

    /* renamed from: b  reason: collision with root package name */
    protected final float[] f10861b;

    /* renamed from: c  reason: collision with root package name */
    protected Matrix f10862c;

    /* renamed from: d  reason: collision with root package name */
    protected int f10863d;

    /* renamed from: e  reason: collision with root package name */
    protected int f10864e;

    /* renamed from: f  reason: collision with root package name */
    protected TransformImageListener f10865f;

    /* renamed from: g  reason: collision with root package name */
    protected boolean f10866g;

    /* renamed from: h  reason: collision with root package name */
    protected boolean f10867h;
    private ExifInfo mExifInfo;
    private String mImageInputPath;
    private String mImageOutputPath;
    private float[] mInitialImageCenter;
    private float[] mInitialImageCorners;
    private final float[] mMatrixValues;
    private int mMaxBitmapSize;

    /* loaded from: classes3.dex */
    public interface TransformImageListener {
        void onLoadComplete();

        void onLoadFailure(@NonNull Exception exc);

        void onRotate(float f2);

        void onScale(float f2);
    }

    public TransformImageView(Context context) {
        this(context, null);
    }

    public TransformImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TransformImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f10860a = new float[8];
        this.f10861b = new float[2];
        this.mMatrixValues = new float[9];
        this.f10862c = new Matrix();
        this.f10866g = false;
        this.f10867h = false;
        this.mMaxBitmapSize = 0;
        e();
    }

    private void updateCurrentImagePoints() {
        this.f10862c.mapPoints(this.f10860a, this.mInitialImageCorners);
        this.f10862c.mapPoints(this.f10861b, this.mInitialImageCenter);
    }

    protected float d(@NonNull Matrix matrix, @IntRange(from = 0, to = 9) int i2) {
        matrix.getValues(this.mMatrixValues);
        return this.mMatrixValues[i2];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e() {
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void f() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        float intrinsicWidth = drawable.getIntrinsicWidth();
        float intrinsicHeight = drawable.getIntrinsicHeight();
        String.format("Image size: [%d:%d]", Integer.valueOf((int) intrinsicWidth), Integer.valueOf((int) intrinsicHeight));
        RectF rectF = new RectF(0.0f, 0.0f, intrinsicWidth, intrinsicHeight);
        this.mInitialImageCorners = RectUtils.getCornersFromRect(rectF);
        this.mInitialImageCenter = RectUtils.getCenterFromRect(rectF);
        this.f10867h = true;
        TransformImageListener transformImageListener = this.f10865f;
        if (transformImageListener != null) {
            transformImageListener.onLoadComplete();
        }
    }

    public float getCurrentAngle() {
        return getMatrixAngle(this.f10862c);
    }

    public float getCurrentScale() {
        return getMatrixScale(this.f10862c);
    }

    public ExifInfo getExifInfo() {
        return this.mExifInfo;
    }

    public String getImageInputPath() {
        return this.mImageInputPath;
    }

    public String getImageOutputPath() {
        return this.mImageOutputPath;
    }

    public float getMatrixAngle(@NonNull Matrix matrix) {
        return (float) (-(Math.atan2(d(matrix, 1), d(matrix, 0)) * 57.29577951308232d));
    }

    public float getMatrixScale(@NonNull Matrix matrix) {
        return (float) Math.sqrt(Math.pow(d(matrix, 0), 2.0d) + Math.pow(d(matrix, 3), 2.0d));
    }

    public int getMaxBitmapSize() {
        if (this.mMaxBitmapSize <= 0) {
            this.mMaxBitmapSize = BitmapLoadUtils.calculateMaxBitmapSize(getContext());
        }
        return this.mMaxBitmapSize;
    }

    @Nullable
    public Bitmap getViewBitmap() {
        if (getDrawable() == null || !(getDrawable() instanceof FastBitmapDrawable)) {
            return null;
        }
        return ((FastBitmapDrawable) getDrawable()).getBitmap();
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (z || (this.f10866g && !this.f10867h)) {
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            this.f10863d = (getWidth() - getPaddingRight()) - paddingLeft;
            this.f10864e = (getHeight() - getPaddingBottom()) - paddingTop;
            f();
        }
    }

    public void postRotate(float f2, float f3, float f4) {
        if (f2 != 0.0f) {
            this.f10862c.postRotate(f2, f3, f4);
            setImageMatrix(this.f10862c);
            TransformImageListener transformImageListener = this.f10865f;
            if (transformImageListener != null) {
                transformImageListener.onRotate(getMatrixAngle(this.f10862c));
            }
        }
    }

    public void postScale(float f2, float f3, float f4) {
        if (f2 != 0.0f) {
            this.f10862c.postScale(f2, f2, f3, f4);
            setImageMatrix(this.f10862c);
            TransformImageListener transformImageListener = this.f10865f;
            if (transformImageListener != null) {
                transformImageListener.onScale(getMatrixScale(this.f10862c));
            }
        }
    }

    public void postTranslate(float f2, float f3) {
        if (f2 == 0.0f && f3 == 0.0f) {
            return;
        }
        this.f10862c.postTranslate(f2, f3);
        setImageMatrix(this.f10862c);
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        setImageDrawable(new FastBitmapDrawable(bitmap));
    }

    @Override // android.widget.ImageView
    public void setImageMatrix(Matrix matrix) {
        super.setImageMatrix(matrix);
        this.f10862c.set(matrix);
        updateCurrentImagePoints();
    }

    public void setImageUri(@NonNull Uri uri, @Nullable Uri uri2) {
        int maxBitmapSize = getMaxBitmapSize();
        BitmapLoadUtils.decodeBitmapInBackground(getContext(), uri, uri2, maxBitmapSize, maxBitmapSize, new BitmapLoadCallback() { // from class: com.yalantis.ucrop.view.TransformImageView.1
            @Override // com.yalantis.ucrop.callback.BitmapLoadCallback
            public void onBitmapLoaded(@NonNull Bitmap bitmap, @NonNull ExifInfo exifInfo, @NonNull String str, @Nullable String str2) {
                TransformImageView.this.mImageInputPath = str;
                TransformImageView.this.mImageOutputPath = str2;
                TransformImageView.this.mExifInfo = exifInfo;
                TransformImageView transformImageView = TransformImageView.this;
                transformImageView.f10866g = true;
                transformImageView.setImageBitmap(bitmap);
            }

            @Override // com.yalantis.ucrop.callback.BitmapLoadCallback
            public void onFailure(@NonNull Exception exc) {
                Log.e(TransformImageView.TAG, "onFailure: setImageUri", exc);
                TransformImageListener transformImageListener = TransformImageView.this.f10865f;
                if (transformImageListener != null) {
                    transformImageListener.onLoadFailure(exc);
                }
            }
        });
    }

    public void setMaxBitmapSize(int i2) {
        this.mMaxBitmapSize = i2;
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType == ImageView.ScaleType.MATRIX) {
            super.setScaleType(scaleType);
        }
    }

    public void setTransformImageListener(TransformImageListener transformImageListener) {
        this.f10865f = transformImageListener;
    }
}
