package pl.droidsonroids.gif;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.widget.MediaController;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import pl.droidsonroids.gif.transforms.CornerRadiusTransform;
import pl.droidsonroids.gif.transforms.Transform;
/* loaded from: classes4.dex */
public class GifDrawable extends Drawable implements Animatable, MediaController.MediaPlayerControl {

    /* renamed from: a  reason: collision with root package name */
    final ScheduledThreadPoolExecutor f15249a;

    /* renamed from: b  reason: collision with root package name */
    volatile boolean f15250b;

    /* renamed from: c  reason: collision with root package name */
    long f15251c;

    /* renamed from: d  reason: collision with root package name */
    protected final Paint f15252d;

    /* renamed from: e  reason: collision with root package name */
    final Bitmap f15253e;

    /* renamed from: f  reason: collision with root package name */
    final GifInfoHandle f15254f;

    /* renamed from: g  reason: collision with root package name */
    final ConcurrentLinkedQueue f15255g;

    /* renamed from: h  reason: collision with root package name */
    final boolean f15256h;

    /* renamed from: i  reason: collision with root package name */
    final InvalidationHandler f15257i;

    /* renamed from: j  reason: collision with root package name */
    ScheduledFuture f15258j;
    private final Rect mDstRect;
    private final RenderTask mRenderTask;
    private int mScaledHeight;
    private int mScaledWidth;
    private final Rect mSrcRect;
    private ColorStateList mTint;
    private PorterDuffColorFilter mTintFilter;
    private PorterDuff.Mode mTintMode;
    private Transform mTransform;

    public GifDrawable(@Nullable ContentResolver contentResolver, @NonNull Uri uri) {
        this(GifInfoHandle.w(contentResolver, uri), null, null, true);
    }

    public GifDrawable(@NonNull AssetFileDescriptor assetFileDescriptor) {
        this(new GifInfoHandle(assetFileDescriptor), null, null, true);
    }

    public GifDrawable(@NonNull AssetManager assetManager, @NonNull String str) {
        this(assetManager.openFd(str));
    }

    public GifDrawable(@NonNull Resources resources, @DrawableRes @RawRes int i2) {
        this(resources.openRawResourceFd(i2));
        float b2 = GifViewUtils.b(resources, i2);
        this.mScaledHeight = (int) (this.f15254f.i() * b2);
        this.mScaledWidth = (int) (this.f15254f.p() * b2);
    }

    public GifDrawable(@NonNull File file) {
        this(file.getPath());
    }

    public GifDrawable(@NonNull FileDescriptor fileDescriptor) {
        this(new GifInfoHandle(fileDescriptor), null, null, true);
    }

    public GifDrawable(@NonNull InputStream inputStream) {
        this(new GifInfoHandle(inputStream), null, null, true);
    }

    public GifDrawable(@NonNull String str) {
        this(new GifInfoHandle(str), null, null, true);
    }

    public GifDrawable(@NonNull ByteBuffer byteBuffer) {
        this(new GifInfoHandle(byteBuffer), null, null, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GifDrawable(GifInfoHandle gifInfoHandle, GifDrawable gifDrawable, ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, boolean z) {
        this.f15250b = true;
        this.f15251c = Long.MIN_VALUE;
        this.mDstRect = new Rect();
        this.f15252d = new Paint(6);
        this.f15255g = new ConcurrentLinkedQueue();
        RenderTask renderTask = new RenderTask(this);
        this.mRenderTask = renderTask;
        this.f15256h = z;
        this.f15249a = scheduledThreadPoolExecutor == null ? GifRenderingExecutor.a() : scheduledThreadPoolExecutor;
        this.f15254f = gifInfoHandle;
        Bitmap bitmap = null;
        if (gifDrawable != null) {
            synchronized (gifDrawable.f15254f) {
                if (!gifDrawable.f15254f.v() && gifDrawable.f15254f.i() >= gifInfoHandle.i() && gifDrawable.f15254f.p() >= gifInfoHandle.p()) {
                    gifDrawable.shutdown();
                    Bitmap bitmap2 = gifDrawable.f15253e;
                    bitmap2.eraseColor(0);
                    bitmap = bitmap2;
                }
            }
        }
        if (bitmap == null) {
            this.f15253e = Bitmap.createBitmap(gifInfoHandle.p(), gifInfoHandle.i(), Bitmap.Config.ARGB_8888);
        } else {
            this.f15253e = bitmap;
        }
        this.f15253e.setHasAlpha(!gifInfoHandle.u());
        this.mSrcRect = new Rect(0, 0, gifInfoHandle.p(), gifInfoHandle.i());
        this.f15257i = new InvalidationHandler(this);
        renderTask.doWork();
        this.mScaledWidth = gifInfoHandle.p();
        this.mScaledHeight = gifInfoHandle.i();
    }

    public GifDrawable(@NonNull byte[] bArr) {
        this(new GifInfoHandle(bArr), null, null, true);
    }

    private void cancelPendingRenderTask() {
        ScheduledFuture scheduledFuture = this.f15258j;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.f15257i.removeMessages(-1);
    }

    @Nullable
    public static GifDrawable createFromResource(@NonNull Resources resources, @DrawableRes @RawRes int i2) {
        try {
            return new GifDrawable(resources, i2);
        } catch (IOException unused) {
            return null;
        }
    }

    private void scheduleNextRender() {
        if (this.f15256h && this.f15250b) {
            long j2 = this.f15251c;
            if (j2 != Long.MIN_VALUE) {
                long max = Math.max(0L, j2 - SystemClock.uptimeMillis());
                this.f15251c = Long.MIN_VALUE;
                this.f15249a.remove(this.mRenderTask);
                this.f15258j = this.f15249a.schedule(this.mRenderTask, max, TimeUnit.MILLISECONDS);
            }
        }
    }

    private void shutdown() {
        this.f15250b = false;
        this.f15257i.removeMessages(-1);
        this.f15254f.y();
    }

    private PorterDuffColorFilter updateTintFilter(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(long j2) {
        if (this.f15256h) {
            this.f15251c = 0L;
            this.f15257i.sendEmptyMessageAtTime(-1, 0L);
            return;
        }
        cancelPendingRenderTask();
        this.f15258j = this.f15249a.schedule(this.mRenderTask, Math.max(j2, 0L), TimeUnit.MILLISECONDS);
    }

    public void addAnimationListener(@NonNull AnimationListener animationListener) {
        this.f15255g.add(animationListener);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canPause() {
        return true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        return getNumberOfFrames() > 1;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        return getNumberOfFrames() > 1;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        boolean z;
        if (this.mTintFilter == null || this.f15252d.getColorFilter() != null) {
            z = false;
        } else {
            this.f15252d.setColorFilter(this.mTintFilter);
            z = true;
        }
        Transform transform = this.mTransform;
        if (transform == null) {
            canvas.drawBitmap(this.f15253e, this.mSrcRect, this.mDstRect, this.f15252d);
        } else {
            transform.onDraw(canvas, this.f15252d, this.f15253e);
        }
        if (z) {
            this.f15252d.setColorFilter(null);
        }
    }

    public long getAllocationByteCount() {
        return this.f15254f.b() + (Build.VERSION.SDK_INT >= 19 ? this.f15253e.getAllocationByteCount() : getFrameByteCount());
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f15252d.getAlpha();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        return 100;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.f15252d.getColorFilter();
    }

    @Nullable
    public String getComment() {
        return this.f15254f.c();
    }

    @FloatRange(from = 0.0d)
    public float getCornerRadius() {
        Transform transform = this.mTransform;
        if (transform instanceof CornerRadiusTransform) {
            return ((CornerRadiusTransform) transform).getCornerRadius();
        }
        return 0.0f;
    }

    public Bitmap getCurrentFrame() {
        Bitmap bitmap = this.f15253e;
        Bitmap copy = bitmap.copy(bitmap.getConfig(), this.f15253e.isMutable());
        copy.setHasAlpha(this.f15253e.hasAlpha());
        return copy;
    }

    public int getCurrentFrameIndex() {
        return this.f15254f.d();
    }

    public int getCurrentLoop() {
        int e2 = this.f15254f.e();
        return (e2 == 0 || e2 < this.f15254f.j()) ? e2 : e2 - 1;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        return this.f15254f.f();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        return this.f15254f.g();
    }

    @NonNull
    public GifError getError() {
        return GifError.a(this.f15254f.l());
    }

    public int getFrameByteCount() {
        return this.f15253e.getRowBytes() * this.f15253e.getHeight();
    }

    public int getFrameDuration(@IntRange(from = 0) int i2) {
        return this.f15254f.h(i2);
    }

    public long getInputSourceByteCount() {
        return this.f15254f.o();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mScaledHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mScaledWidth;
    }

    public int getLoopCount() {
        return this.f15254f.j();
    }

    public long getMetadataAllocationByteCount() {
        return this.f15254f.k();
    }

    public int getNumberOfFrames() {
        return this.f15254f.m();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return (!this.f15254f.u() || this.f15252d.getAlpha() < 255) ? -2 : -1;
    }

    @NonNull
    public final Paint getPaint() {
        return this.f15252d;
    }

    public int getPixel(@IntRange(from = 0) int i2, @IntRange(from = 0) int i3) {
        if (i2 < this.f15254f.p()) {
            if (i3 < this.f15254f.i()) {
                return this.f15253e.getPixel(i2, i3);
            }
            throw new IllegalArgumentException("y must be < height");
        }
        throw new IllegalArgumentException("x must be < width");
    }

    public void getPixels(@NonNull int[] iArr) {
        this.f15253e.getPixels(iArr, 0, this.f15254f.p(), 0, 0, this.f15254f.p(), this.f15254f.i());
    }

    @Nullable
    public Transform getTransform() {
        return this.mTransform;
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        super.invalidateSelf();
        scheduleNextRender();
    }

    public boolean isAnimationCompleted() {
        return this.f15254f.t();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        return this.f15250b;
    }

    public boolean isRecycled() {
        return this.f15254f.v();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.f15250b;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        return super.isStateful() || ((colorStateList = this.mTint) != null && colorStateList.isStateful());
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        this.mDstRect.set(rect);
        Transform transform = this.mTransform;
        if (transform != null) {
            transform.onBoundsChange(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        ColorStateList colorStateList = this.mTint;
        if (colorStateList == null || (mode = this.mTintMode) == null) {
            return false;
        }
        this.mTintFilter = updateTintFilter(colorStateList, mode);
        return true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void pause() {
        stop();
    }

    public void recycle() {
        shutdown();
        this.f15253e.recycle();
    }

    public boolean removeAnimationListener(AnimationListener animationListener) {
        return this.f15255g.remove(animationListener);
    }

    public void reset() {
        this.f15249a.execute(new SafeRunnable(this) { // from class: pl.droidsonroids.gif.GifDrawable.1
            @Override // pl.droidsonroids.gif.SafeRunnable
            public void doWork() {
                if (GifDrawable.this.f15254f.A()) {
                    GifDrawable.this.start();
                }
            }
        });
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void seekTo(@IntRange(from = 0, to = 2147483647L) final int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Position is not positive");
        }
        this.f15249a.execute(new SafeRunnable(this) { // from class: pl.droidsonroids.gif.GifDrawable.2
            @Override // pl.droidsonroids.gif.SafeRunnable
            public void doWork() {
                GifDrawable gifDrawable = GifDrawable.this;
                gifDrawable.f15254f.G(i2, gifDrawable.f15253e);
                this.f15278a.f15257i.sendEmptyMessageAtTime(-1, 0L);
            }
        });
    }

    public void seekToBlocking(@IntRange(from = 0, to = 2147483647L) int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Position is not positive");
        }
        synchronized (this.f15254f) {
            this.f15254f.G(i2, this.f15253e);
        }
        this.f15257i.sendEmptyMessageAtTime(-1, 0L);
    }

    public void seekToFrame(@IntRange(from = 0, to = 2147483647L) final int i2) {
        if (i2 < 0) {
            throw new IndexOutOfBoundsException("Frame index is not positive");
        }
        this.f15249a.execute(new SafeRunnable(this) { // from class: pl.droidsonroids.gif.GifDrawable.3
            @Override // pl.droidsonroids.gif.SafeRunnable
            public void doWork() {
                GifDrawable gifDrawable = GifDrawable.this;
                gifDrawable.f15254f.E(i2, gifDrawable.f15253e);
                GifDrawable.this.f15257i.sendEmptyMessageAtTime(-1, 0L);
            }
        });
    }

    public Bitmap seekToFrameAndGet(@IntRange(from = 0, to = 2147483647L) int i2) {
        Bitmap currentFrame;
        if (i2 >= 0) {
            synchronized (this.f15254f) {
                this.f15254f.E(i2, this.f15253e);
                currentFrame = getCurrentFrame();
            }
            this.f15257i.sendEmptyMessageAtTime(-1, 0L);
            return currentFrame;
        }
        throw new IndexOutOfBoundsException("Frame index is not positive");
    }

    public Bitmap seekToPositionAndGet(@IntRange(from = 0, to = 2147483647L) int i2) {
        Bitmap currentFrame;
        if (i2 >= 0) {
            synchronized (this.f15254f) {
                this.f15254f.G(i2, this.f15253e);
                currentFrame = getCurrentFrame();
            }
            this.f15257i.sendEmptyMessageAtTime(-1, 0L);
            return currentFrame;
        }
        throw new IllegalArgumentException("Position is not positive");
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(@IntRange(from = 0, to = 255) int i2) {
        this.f15252d.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.f15252d.setColorFilter(colorFilter);
    }

    public void setCornerRadius(@FloatRange(from = 0.0d) float f2) {
        CornerRadiusTransform cornerRadiusTransform = new CornerRadiusTransform(f2);
        this.mTransform = cornerRadiusTransform;
        cornerRadiusTransform.onBoundsChange(this.mDstRect);
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        this.f15252d.setDither(z);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        this.f15252d.setFilterBitmap(z);
        invalidateSelf();
    }

    public void setLoopCount(@IntRange(from = 0, to = 65535) int i2) {
        this.f15254f.H(i2);
    }

    public void setSpeed(@FloatRange(from = 0.0d, fromInclusive = false) float f2) {
        this.f15254f.J(f2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.mTint = colorStateList;
        this.mTintFilter = updateTintFilter(colorStateList, this.mTintMode);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(@Nullable PorterDuff.Mode mode) {
        this.mTintMode = mode;
        this.mTintFilter = updateTintFilter(this.mTint, mode);
        invalidateSelf();
    }

    public void setTransform(@Nullable Transform transform) {
        this.mTransform = transform;
        if (transform != null) {
            transform.onBoundsChange(this.mDstRect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (!this.f15256h) {
            if (z) {
                if (z2) {
                    reset();
                }
                if (visible) {
                    start();
                }
            } else if (visible) {
                stop();
            }
        }
        return visible;
    }

    @Override // android.graphics.drawable.Animatable, android.widget.MediaController.MediaPlayerControl
    public void start() {
        synchronized (this) {
            if (this.f15250b) {
                return;
            }
            this.f15250b = true;
            a(this.f15254f.B());
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        synchronized (this) {
            if (this.f15250b) {
                this.f15250b = false;
                cancelPendingRenderTask();
                this.f15254f.D();
            }
        }
    }

    @NonNull
    public String toString() {
        return String.format(Locale.ENGLISH, "GIF: size: %dx%d, frames: %d, error: %d", Integer.valueOf(this.f15254f.p()), Integer.valueOf(this.f15254f.i()), Integer.valueOf(this.f15254f.m()), Integer.valueOf(this.f15254f.l()));
    }
}
