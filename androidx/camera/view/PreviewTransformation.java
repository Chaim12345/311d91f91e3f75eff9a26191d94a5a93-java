package androidx.camera.view;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Size;
import android.view.Display;
import android.view.TextureView;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.camera.core.Logger;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.view.PreviewView;
import androidx.camera.view.internal.compat.quirk.DeviceQuirks;
import androidx.camera.view.internal.compat.quirk.PreviewOneThirdWiderQuirk;
import androidx.camera.view.internal.compat.quirk.TextureViewRotationQuirk;
import androidx.core.util.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class PreviewTransformation {
    private static final PreviewView.ScaleType DEFAULT_SCALE_TYPE = PreviewView.ScaleType.FILL_CENTER;
    private static final String TAG = "PreviewTransform";
    private boolean mIsFrontCamera;
    private int mPreviewRotationDegrees;
    private Size mResolution;
    private PreviewView.ScaleType mScaleType = DEFAULT_SCALE_TYPE;
    private Rect mSurfaceCropRect;
    private int mTargetRotation;
    private Rect mViewportRect;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.camera.view.PreviewTransformation$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1373a;

        static {
            int[] iArr = new int[PreviewView.ScaleType.values().length];
            f1373a = iArr;
            try {
                iArr[PreviewView.ScaleType.FIT_CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1373a[PreviewView.ScaleType.FILL_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1373a[PreviewView.ScaleType.FIT_END.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1373a[PreviewView.ScaleType.FILL_END.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1373a[PreviewView.ScaleType.FIT_START.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1373a[PreviewView.ScaleType.FILL_START.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private static RectF flipHorizontally(RectF rectF, float f2) {
        float f3 = f2 + f2;
        return new RectF(f3 - rectF.right, rectF.top, f3 - rectF.left, rectF.bottom);
    }

    private Rect getCorrectedCropRect(Rect rect) {
        PreviewOneThirdWiderQuirk previewOneThirdWiderQuirk = (PreviewOneThirdWiderQuirk) DeviceQuirks.get(PreviewOneThirdWiderQuirk.class);
        if (previewOneThirdWiderQuirk != null) {
            RectF rectF = new RectF(rect);
            Matrix matrix = new Matrix();
            matrix.setScale(previewOneThirdWiderQuirk.getCropRectScaleX(), 1.0f, rect.centerX(), rect.centerY());
            matrix.mapRect(rectF);
            Rect rect2 = new Rect();
            rectF.round(rect2);
            return rect2;
        }
        return rect;
    }

    private Size getRotatedViewportSize() {
        return TransformUtils.is90or270(this.mPreviewRotationDegrees) ? new Size(this.mViewportRect.height(), this.mViewportRect.width()) : new Size(this.mViewportRect.width(), this.mViewportRect.height());
    }

    private RectF getTransformedSurfaceRect(Size size, int i2) {
        Preconditions.checkState(isTransformationInfoReady());
        Matrix f2 = f(size, i2);
        RectF rectF = new RectF(0.0f, 0.0f, this.mResolution.getWidth(), this.mResolution.getHeight());
        f2.mapRect(rectF);
        return rectF;
    }

    private boolean isTransformationInfoReady() {
        return (this.mSurfaceCropRect == null || this.mResolution == null) ? false : true;
    }

    private static void setMatrixRectToRect(Matrix matrix, RectF rectF, RectF rectF2, PreviewView.ScaleType scaleType) {
        Matrix.ScaleToFit scaleToFit;
        switch (AnonymousClass1.f1373a[scaleType.ordinal()]) {
            case 1:
            case 2:
                scaleToFit = Matrix.ScaleToFit.CENTER;
                break;
            case 3:
            case 4:
                scaleToFit = Matrix.ScaleToFit.END;
                break;
            case 5:
            case 6:
                scaleToFit = Matrix.ScaleToFit.START;
                break;
            default:
                Logger.e(TAG, "Unexpected crop rect: " + scaleType);
                scaleToFit = Matrix.ScaleToFit.FILL;
                break;
        }
        if (scaleType == PreviewView.ScaleType.FIT_CENTER || scaleType == PreviewView.ScaleType.FIT_START || scaleType == PreviewView.ScaleType.FIT_END) {
            matrix.setRectToRect(rectF, rectF2, scaleToFit);
            return;
        }
        matrix.setRectToRect(rectF2, rectF, scaleToFit);
        matrix.invert(matrix);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bitmap a(@NonNull Bitmap bitmap, Size size, int i2) {
        if (isTransformationInfoReady()) {
            Matrix g2 = g();
            RectF transformedSurfaceRect = getTransformedSurfaceRect(size, i2);
            Bitmap createBitmap = Bitmap.createBitmap(size.getWidth(), size.getHeight(), bitmap.getConfig());
            Canvas canvas = new Canvas(createBitmap);
            Matrix matrix = new Matrix();
            matrix.postConcat(g2);
            matrix.postScale(transformedSurfaceRect.width() / this.mResolution.getWidth(), transformedSurfaceRect.height() / this.mResolution.getHeight());
            matrix.postTranslate(transformedSurfaceRect.left, transformedSurfaceRect.top);
            canvas.drawBitmap(bitmap, matrix, new Paint(7));
            return createBitmap;
        }
        return bitmap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Matrix b(Size size, int i2) {
        if (isTransformationInfoReady()) {
            Matrix matrix = new Matrix();
            f(size, i2).invert(matrix);
            Matrix matrix2 = new Matrix();
            matrix2.setRectToRect(new RectF(0.0f, 0.0f, this.mResolution.getWidth(), this.mResolution.getHeight()), new RectF(0.0f, 0.0f, 1.0f, 1.0f), Matrix.ScaleToFit.FILL);
            matrix.postConcat(matrix2);
            return matrix;
        }
        return null;
    }

    RectF c(Size size, int i2) {
        RectF rectF = new RectF(0.0f, 0.0f, size.getWidth(), size.getHeight());
        Size rotatedViewportSize = getRotatedViewportSize();
        RectF rectF2 = new RectF(0.0f, 0.0f, rotatedViewportSize.getWidth(), rotatedViewportSize.getHeight());
        Matrix matrix = new Matrix();
        setMatrixRectToRect(matrix, rectF2, rectF, this.mScaleType);
        matrix.mapRect(rectF2);
        return i2 == 1 ? flipHorizontally(rectF2, size.getWidth() / 2.0f) : rectF2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PreviewView.ScaleType d() {
        return this.mScaleType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Rect e() {
        return this.mSurfaceCropRect;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Matrix f(Size size, int i2) {
        Preconditions.checkState(isTransformationInfoReady());
        Matrix rectToRect = TransformUtils.getRectToRect(new RectF(this.mSurfaceCropRect), h(size) ? new RectF(0.0f, 0.0f, size.getWidth(), size.getHeight()) : c(size, i2), this.mPreviewRotationDegrees);
        if (this.mIsFrontCamera) {
            if (TransformUtils.is90or270(this.mPreviewRotationDegrees)) {
                rectToRect.preScale(1.0f, -1.0f, this.mSurfaceCropRect.centerX(), this.mSurfaceCropRect.centerY());
            } else {
                rectToRect.preScale(-1.0f, 1.0f, this.mSurfaceCropRect.centerX(), this.mSurfaceCropRect.centerY());
            }
        }
        return rectToRect;
    }

    @VisibleForTesting
    Matrix g() {
        Preconditions.checkState(isTransformationInfoReady());
        RectF rectF = new RectF(0.0f, 0.0f, this.mResolution.getWidth(), this.mResolution.getHeight());
        int i2 = -TransformUtils.surfaceRotationToRotationDegrees(this.mTargetRotation);
        TextureViewRotationQuirk textureViewRotationQuirk = (TextureViewRotationQuirk) DeviceQuirks.get(TextureViewRotationQuirk.class);
        if (textureViewRotationQuirk != null) {
            i2 += textureViewRotationQuirk.getCorrectionRotation(this.mIsFrontCamera);
        }
        return TransformUtils.getRectToRect(rectF, rectF, i2);
    }

    @VisibleForTesting
    boolean h(Size size) {
        return TransformUtils.isAspectRatioMatchingWithRoundingError(size, true, getRotatedViewportSize(), false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(PreviewView.ScaleType scaleType) {
        this.mScaleType = scaleType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"UnsafeOptInUsageError"})
    public void j(@NonNull SurfaceRequest.TransformationInfo transformationInfo, Size size, boolean z) {
        Logger.d(TAG, "Transformation info set: " + transformationInfo + " " + size + " " + z);
        this.mSurfaceCropRect = getCorrectedCropRect(transformationInfo.getCropRect());
        this.mViewportRect = transformationInfo.getCropRect();
        this.mPreviewRotationDegrees = transformationInfo.getRotationDegrees();
        this.mTargetRotation = transformationInfo.getTargetRotation();
        this.mResolution = size;
        this.mIsFrontCamera = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k(Size size, int i2, @NonNull View view) {
        if (size.getHeight() == 0 || size.getWidth() == 0) {
            Logger.w(TAG, "Transform not applied due to PreviewView size: " + size);
        } else if (isTransformationInfoReady()) {
            if (view instanceof TextureView) {
                ((TextureView) view).setTransform(g());
            } else {
                Display display = view.getDisplay();
                if (display != null && display.getRotation() != this.mTargetRotation) {
                    Logger.e(TAG, "Non-display rotation not supported with SurfaceView / PERFORMANCE mode.");
                }
            }
            RectF transformedSurfaceRect = getTransformedSurfaceRect(size, i2);
            view.setPivotX(0.0f);
            view.setPivotY(0.0f);
            view.setScaleX(transformedSurfaceRect.width() / this.mResolution.getWidth());
            view.setScaleY(transformedSurfaceRect.height() / this.mResolution.getHeight());
            view.setTranslationX(transformedSurfaceRect.left - view.getLeft());
            view.setTranslationY(transformedSurfaceRect.top - view.getTop());
        }
    }
}
