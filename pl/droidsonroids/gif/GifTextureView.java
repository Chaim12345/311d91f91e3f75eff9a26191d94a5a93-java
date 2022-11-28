package pl.droidsonroids.gif;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Surface;
import android.view.TextureView;
import android.widget.ImageView;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.IOException;
import java.lang.ref.WeakReference;
import pl.droidsonroids.gif.GifViewUtils;
import pl.droidsonroids.gif.InputSource;
/* loaded from: classes4.dex */
public class GifTextureView extends TextureView {
    private static final ImageView.ScaleType[] sScaleTypeArray = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    private InputSource mInputSource;
    private RenderThread mRenderThread;
    private ImageView.ScaleType mScaleType;
    private float mSpeedFactor;
    private final Matrix mTransform;
    private GifViewUtils.GifViewAttributes viewAttributes;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: pl.droidsonroids.gif.GifTextureView$1  reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f15267a;

        static {
            int[] iArr = new int[ImageView.ScaleType.values().length];
            f15267a = iArr;
            try {
                iArr[ImageView.ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15267a[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15267a[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f15267a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f15267a[ImageView.ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f15267a[ImageView.ScaleType.FIT_START.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f15267a[ImageView.ScaleType.FIT_XY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f15267a[ImageView.ScaleType.MATRIX.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* loaded from: classes4.dex */
    public interface PlaceholderDrawListener {
        void onDrawPlaceholder(Canvas canvas);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class RenderThread extends Thread implements TextureView.SurfaceTextureListener {

        /* renamed from: a  reason: collision with root package name */
        final ConditionVariable f15268a;

        /* renamed from: b  reason: collision with root package name */
        long[] f15269b;
        private GifInfoHandle mGifInfoHandle;
        private final WeakReference<GifTextureView> mGifTextureViewReference;
        private IOException mIOException;

        RenderThread(GifTextureView gifTextureView) {
            super("GifRenderThread");
            this.f15268a = new ConditionVariable();
            this.mGifInfoHandle = new GifInfoHandle();
            this.mGifTextureViewReference = new WeakReference<>(gifTextureView);
        }

        void c(@NonNull GifTextureView gifTextureView, @Nullable PlaceholderDrawListener placeholderDrawListener) {
            this.f15268a.b();
            gifTextureView.setSuperSurfaceTextureListener(placeholderDrawListener != null ? new PlaceholderDrawingSurfaceTextureListener(placeholderDrawListener) : null);
            this.mGifInfoHandle.x();
            interrupt();
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
            GifTextureView gifTextureView = this.mGifTextureViewReference.get();
            if (gifTextureView != null) {
                gifTextureView.updateTextureViewSize(this.mGifInfoHandle);
            }
            this.f15268a.c();
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            this.f15268a.b();
            this.mGifInfoHandle.x();
            interrupt();
            return true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                GifTextureView gifTextureView = this.mGifTextureViewReference.get();
                if (gifTextureView == null) {
                    return;
                }
                GifInfoHandle c2 = gifTextureView.mInputSource.c();
                this.mGifInfoHandle = c2;
                c2.I((char) 1, gifTextureView.isOpaque());
                if (gifTextureView.viewAttributes.f15277b >= 0) {
                    this.mGifInfoHandle.H(gifTextureView.viewAttributes.f15277b);
                }
                final GifTextureView gifTextureView2 = this.mGifTextureViewReference.get();
                if (gifTextureView2 == null) {
                    this.mGifInfoHandle.y();
                    return;
                }
                gifTextureView2.setSuperSurfaceTextureListener(this);
                boolean isAvailable = gifTextureView2.isAvailable();
                this.f15268a.d(isAvailable);
                if (isAvailable) {
                    gifTextureView2.post(new Runnable() { // from class: pl.droidsonroids.gif.GifTextureView.RenderThread.1
                        @Override // java.lang.Runnable
                        public void run() {
                            gifTextureView2.updateTextureViewSize(RenderThread.this.mGifInfoHandle);
                        }
                    });
                }
                this.mGifInfoHandle.J(gifTextureView2.mSpeedFactor);
                while (!isInterrupted()) {
                    try {
                        this.f15268a.a();
                        GifTextureView gifTextureView3 = this.mGifTextureViewReference.get();
                        if (gifTextureView3 == null) {
                            break;
                        }
                        SurfaceTexture surfaceTexture = gifTextureView3.getSurfaceTexture();
                        if (surfaceTexture != null) {
                            Surface surface = new Surface(surfaceTexture);
                            try {
                                this.mGifInfoHandle.a(surface, this.f15269b);
                            } finally {
                                surface.release();
                            }
                        }
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
                this.mGifInfoHandle.y();
                this.mGifInfoHandle = new GifInfoHandle();
            } catch (IOException e2) {
                this.mIOException = e2;
            }
        }
    }

    public GifTextureView(Context context) {
        super(context);
        this.mScaleType = ImageView.ScaleType.FIT_CENTER;
        this.mTransform = new Matrix();
        this.mSpeedFactor = 1.0f;
        init(null, 0, 0);
    }

    public GifTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mScaleType = ImageView.ScaleType.FIT_CENTER;
        this.mTransform = new Matrix();
        this.mSpeedFactor = 1.0f;
        init(attributeSet, 0, 0);
    }

    public GifTextureView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mScaleType = ImageView.ScaleType.FIT_CENTER;
        this.mTransform = new Matrix();
        this.mSpeedFactor = 1.0f;
        init(attributeSet, i2, 0);
    }

    @RequiresApi(21)
    public GifTextureView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mScaleType = ImageView.ScaleType.FIT_CENTER;
        this.mTransform = new Matrix();
        this.mSpeedFactor = 1.0f;
        init(attributeSet, i2, i3);
    }

    private static InputSource findSource(TypedArray typedArray) {
        TypedValue typedValue = new TypedValue();
        if (typedArray.getValue(R.styleable.GifTextureView_gifSource, typedValue)) {
            if (typedValue.resourceId != 0) {
                String resourceTypeName = typedArray.getResources().getResourceTypeName(typedValue.resourceId);
                if (GifViewUtils.f15273a.contains(resourceTypeName)) {
                    return new InputSource.ResourcesSource(typedArray.getResources(), typedValue.resourceId);
                }
                if (!TypedValues.Custom.S_STRING.equals(resourceTypeName)) {
                    throw new IllegalArgumentException("Expected string, drawable, mipmap or raw resource type. '" + resourceTypeName + "' is not supported");
                }
            }
            return new InputSource.AssetSource(typedArray.getResources().getAssets(), typedValue.string.toString());
        }
        return null;
    }

    private void init(AttributeSet attributeSet, int i2, int i3) {
        if (attributeSet != null) {
            int attributeIntValue = attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "scaleType", -1);
            if (attributeIntValue >= 0) {
                ImageView.ScaleType[] scaleTypeArr = sScaleTypeArray;
                if (attributeIntValue < scaleTypeArr.length) {
                    this.mScaleType = scaleTypeArr[attributeIntValue];
                }
            }
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.GifTextureView, i2, i3);
            this.mInputSource = findSource(obtainStyledAttributes);
            super.setOpaque(obtainStyledAttributes.getBoolean(R.styleable.GifTextureView_isOpaque, false));
            obtainStyledAttributes.recycle();
            this.viewAttributes = new GifViewUtils.GifViewAttributes(this, attributeSet, i2, i3);
        } else {
            super.setOpaque(false);
            this.viewAttributes = new GifViewUtils.GifViewAttributes();
        }
        if (isInEditMode()) {
            return;
        }
        RenderThread renderThread = new RenderThread(this);
        this.mRenderThread = renderThread;
        if (this.mInputSource != null) {
            renderThread.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSuperSurfaceTextureListener(TextureView.SurfaceTextureListener surfaceTextureListener) {
        super.setSurfaceTextureListener(surfaceTextureListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTextureViewSize(GifInfoHandle gifInfoHandle) {
        Matrix.ScaleToFit scaleToFit;
        Matrix matrix = new Matrix();
        float width = getWidth();
        float height = getHeight();
        float p2 = gifInfoHandle.p() / width;
        float i2 = gifInfoHandle.i() / height;
        RectF rectF = new RectF(0.0f, 0.0f, gifInfoHandle.p(), gifInfoHandle.i());
        RectF rectF2 = new RectF(0.0f, 0.0f, width, height);
        float f2 = 1.0f;
        switch (AnonymousClass1.f15267a[this.mScaleType.ordinal()]) {
            case 1:
                matrix.setScale(p2, i2, width / 2.0f, height / 2.0f);
                break;
            case 2:
                f2 = 1.0f / Math.min(p2, i2);
                matrix.setScale(p2 * f2, f2 * i2, width / 2.0f, height / 2.0f);
                break;
            case 3:
                if (gifInfoHandle.p() > width || gifInfoHandle.i() > height) {
                    f2 = Math.min(1.0f / p2, 1.0f / i2);
                }
                matrix.setScale(p2 * f2, f2 * i2, width / 2.0f, height / 2.0f);
                break;
            case 4:
                scaleToFit = Matrix.ScaleToFit.CENTER;
                matrix.setRectToRect(rectF, rectF2, scaleToFit);
                matrix.preScale(p2, i2);
                break;
            case 5:
                scaleToFit = Matrix.ScaleToFit.END;
                matrix.setRectToRect(rectF, rectF2, scaleToFit);
                matrix.preScale(p2, i2);
                break;
            case 6:
                scaleToFit = Matrix.ScaleToFit.START;
                matrix.setRectToRect(rectF, rectF2, scaleToFit);
                matrix.preScale(p2, i2);
                break;
            case 7:
                return;
            case 8:
                matrix.set(this.mTransform);
                matrix.preScale(p2, i2);
                break;
        }
        super.setTransform(matrix);
    }

    @Nullable
    public IOException getIOException() {
        return this.mRenderThread.mIOException != null ? this.mRenderThread.mIOException : GifIOException.a(this.mRenderThread.mGifInfoHandle.l());
    }

    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    @Override // android.view.TextureView
    public TextureView.SurfaceTextureListener getSurfaceTextureListener() {
        return null;
    }

    @Override // android.view.TextureView
    public Matrix getTransform(Matrix matrix) {
        if (matrix == null) {
            matrix = new Matrix();
        }
        matrix.set(this.mTransform);
        return matrix;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        this.mRenderThread.c(this, null);
        super.onDetachedFromWindow();
        SurfaceTexture surfaceTexture = getSurfaceTexture();
        if (surfaceTexture != null) {
            surfaceTexture.release();
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof GifViewSavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        GifViewSavedState gifViewSavedState = (GifViewSavedState) parcelable;
        super.onRestoreInstanceState(gifViewSavedState.getSuperState());
        this.mRenderThread.f15269b = gifViewSavedState.f15272a[0];
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        RenderThread renderThread = this.mRenderThread;
        renderThread.f15269b = renderThread.mGifInfoHandle.n();
        return new GifViewSavedState(super.onSaveInstanceState(), this.viewAttributes.f15276a ? this.mRenderThread.f15269b : null);
    }

    public void setFreezesAnimation(boolean z) {
        this.viewAttributes.f15276a = z;
    }

    public void setImageMatrix(Matrix matrix) {
        setTransform(matrix);
    }

    public synchronized void setInputSource(@Nullable InputSource inputSource) {
        setInputSource(inputSource, null);
    }

    public synchronized void setInputSource(@Nullable InputSource inputSource, @Nullable PlaceholderDrawListener placeholderDrawListener) {
        this.mRenderThread.c(this, placeholderDrawListener);
        try {
            this.mRenderThread.join();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        this.mInputSource = inputSource;
        RenderThread renderThread = new RenderThread(this);
        this.mRenderThread = renderThread;
        if (inputSource != null) {
            renderThread.start();
        }
    }

    @Override // android.view.TextureView
    public void setOpaque(boolean z) {
        if (z != isOpaque()) {
            super.setOpaque(z);
            setInputSource(this.mInputSource);
        }
    }

    public void setScaleType(@NonNull ImageView.ScaleType scaleType) {
        this.mScaleType = scaleType;
        updateTextureViewSize(this.mRenderThread.mGifInfoHandle);
    }

    public void setSpeed(@FloatRange(from = 0.0d, fromInclusive = false) float f2) {
        this.mSpeedFactor = f2;
        this.mRenderThread.mGifInfoHandle.J(f2);
    }

    @Override // android.view.TextureView
    public void setSurfaceTexture(SurfaceTexture surfaceTexture) {
        throw new UnsupportedOperationException("Changing SurfaceTexture is not supported");
    }

    @Override // android.view.TextureView
    public void setSurfaceTextureListener(TextureView.SurfaceTextureListener surfaceTextureListener) {
        throw new UnsupportedOperationException("Changing SurfaceTextureListener is not supported");
    }

    @Override // android.view.TextureView
    public void setTransform(Matrix matrix) {
        this.mTransform.set(matrix);
        updateTextureViewSize(this.mRenderThread.mGifInfoHandle);
    }
}
