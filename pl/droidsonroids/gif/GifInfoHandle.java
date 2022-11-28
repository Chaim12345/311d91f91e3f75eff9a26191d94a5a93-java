package pl.droidsonroids.gif;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.system.Os;
import android.view.Surface;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.RequiresApi;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import kotlin.text.Typography;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class GifInfoHandle {
    private volatile long gifInfoPtr;

    static {
        LibraryLoader.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GifInfoHandle() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GifInfoHandle(AssetFileDescriptor assetFileDescriptor) {
        try {
            this.gifInfoPtr = openFileDescriptor(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), false);
        } finally {
            try {
                assetFileDescriptor.close();
            } catch (IOException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GifInfoHandle(FileDescriptor fileDescriptor) {
        this.gifInfoPtr = openFileDescriptor(fileDescriptor, 0L, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GifInfoHandle(InputStream inputStream) {
        if (!inputStream.markSupported()) {
            throw new IllegalArgumentException("InputStream does not support marking");
        }
        this.gifInfoPtr = openStream(inputStream);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GifInfoHandle(String str) {
        this.gifInfoPtr = openFile(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GifInfoHandle(ByteBuffer byteBuffer) {
        this.gifInfoPtr = openDirectByteBuffer(byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GifInfoHandle(byte[] bArr) {
        this.gifInfoPtr = openByteArray(bArr);
    }

    private static native void bindSurface(long j2, Surface surface, long[] jArr);

    static native int createTempNativeFileDescriptor();

    static native int extractNativeFileDescriptor(FileDescriptor fileDescriptor, boolean z);

    private static native void free(long j2);

    private static native long getAllocationByteCount(long j2);

    private static native String getComment(long j2);

    private static native int getCurrentFrameIndex(long j2);

    private static native int getCurrentLoop(long j2);

    private static native int getCurrentPosition(long j2);

    private static native int getDuration(long j2);

    private static native int getFrameDuration(long j2, int i2);

    private static native int getHeight(long j2);

    private static native int getLoopCount(long j2);

    private static native long getMetadataByteCount(long j2);

    private static native int getNativeErrorCode(long j2);

    @RequiresApi(21)
    private static int getNativeFileDescriptor(FileDescriptor fileDescriptor, boolean z) {
        try {
            int createTempNativeFileDescriptor = createTempNativeFileDescriptor();
            Os.dup2(fileDescriptor, createTempNativeFileDescriptor);
            return createTempNativeFileDescriptor;
        } finally {
            if (z) {
                Os.close(fileDescriptor);
            }
        }
    }

    private static native int getNumberOfFrames(long j2);

    private static native long[] getSavedState(long j2);

    private static native long getSourceLength(long j2);

    private static native int getWidth(long j2);

    private static native void glTexImage2D(long j2, int i2, int i3);

    private static native void glTexSubImage2D(long j2, int i2, int i3);

    private static native void initTexImageDescriptor(long j2);

    private static native boolean isAnimationCompleted(long j2);

    private static native boolean isOpaque(long j2);

    static native long openByteArray(byte[] bArr);

    static native long openDirectByteBuffer(ByteBuffer byteBuffer);

    static native long openFile(String str);

    private static long openFileDescriptor(FileDescriptor fileDescriptor, long j2, boolean z) {
        int nativeFileDescriptor;
        if (Build.VERSION.SDK_INT > 27) {
            try {
                nativeFileDescriptor = getNativeFileDescriptor(fileDescriptor, z);
            } catch (Exception e2) {
                throw new GifIOException(GifError.OPEN_FAILED.f15264a, e2.getMessage());
            }
        } else {
            nativeFileDescriptor = extractNativeFileDescriptor(fileDescriptor, z);
        }
        return openNativeFileDescriptor(nativeFileDescriptor, j2);
    }

    static native long openNativeFileDescriptor(int i2, long j2);

    static native long openStream(InputStream inputStream);

    private static native void postUnbindSurface(long j2);

    private static native long renderFrame(long j2, Bitmap bitmap);

    private static native boolean reset(long j2);

    private static native long restoreRemainder(long j2);

    private static native int restoreSavedState(long j2, long[] jArr, Bitmap bitmap);

    private static native void saveRemainder(long j2);

    private static native void seekToFrame(long j2, int i2, Bitmap bitmap);

    private static native void seekToFrameGL(long j2, int i2);

    private static native void seekToTime(long j2, int i2, Bitmap bitmap);

    private static native void setLoopCount(long j2, char c2);

    private static native void setOptions(long j2, char c2, boolean z);

    private static native void setSpeedFactor(long j2, float f2);

    private static native void startDecoderThread(long j2);

    private static native void stopDecoderThread(long j2);

    private void throwIfFrameIndexOutOfBounds(@IntRange(from = 0) int i2) {
        int numberOfFrames = getNumberOfFrames(this.gifInfoPtr);
        if (i2 < 0 || i2 >= numberOfFrames) {
            throw new IndexOutOfBoundsException("Frame index is not in range <0;" + numberOfFrames + Typography.greater);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GifInfoHandle w(ContentResolver contentResolver, Uri uri) {
        if ("file".equals(uri.getScheme())) {
            return new GifInfoHandle(uri.getPath());
        }
        AssetFileDescriptor openAssetFileDescriptor = contentResolver.openAssetFileDescriptor(uri, "r");
        if (openAssetFileDescriptor != null) {
            return new GifInfoHandle(openAssetFileDescriptor);
        }
        throw new IOException("Could not open AssetFileDescriptor for " + uri);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean A() {
        return reset(this.gifInfoPtr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long B() {
        return restoreRemainder(this.gifInfoPtr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int C(long[] jArr, Bitmap bitmap) {
        return restoreSavedState(this.gifInfoPtr, jArr, bitmap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void D() {
        saveRemainder(this.gifInfoPtr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void E(@IntRange(from = 0, to = 2147483647L) int i2, Bitmap bitmap) {
        seekToFrame(this.gifInfoPtr, i2, bitmap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void F(@IntRange(from = 0) int i2) {
        throwIfFrameIndexOutOfBounds(i2);
        seekToFrameGL(this.gifInfoPtr, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void G(@IntRange(from = 0, to = 2147483647L) int i2, Bitmap bitmap) {
        seekToTime(this.gifInfoPtr, i2, bitmap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void H(@IntRange(from = 0, to = 65535) int i2) {
        if (i2 < 0 || i2 > 65535) {
            throw new IllegalArgumentException("Loop count of range <0, 65535>");
        }
        synchronized (this) {
            setLoopCount(this.gifInfoPtr, (char) i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void I(char c2, boolean z) {
        setOptions(this.gifInfoPtr, c2, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void J(@FloatRange(from = 0.0d, fromInclusive = false) float f2) {
        if (f2 <= 0.0f || Float.isNaN(f2)) {
            throw new IllegalArgumentException("Speed factor is not positive");
        }
        if (f2 < 4.656613E-10f) {
            f2 = 4.656613E-10f;
        }
        synchronized (this) {
            setSpeedFactor(this.gifInfoPtr, f2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void K() {
        startDecoderThread(this.gifInfoPtr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void L() {
        stopDecoderThread(this.gifInfoPtr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Surface surface, long[] jArr) {
        bindSurface(this.gifInfoPtr, surface, jArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long b() {
        return getAllocationByteCount(this.gifInfoPtr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized String c() {
        return getComment(this.gifInfoPtr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int d() {
        return getCurrentFrameIndex(this.gifInfoPtr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int e() {
        return getCurrentLoop(this.gifInfoPtr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int f() {
        return getCurrentPosition(this.gifInfoPtr);
    }

    protected void finalize() {
        try {
            y();
        } finally {
            super.finalize();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int g() {
        return getDuration(this.gifInfoPtr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int h(@IntRange(from = 0) int i2) {
        throwIfFrameIndexOutOfBounds(i2);
        return getFrameDuration(this.gifInfoPtr, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int i() {
        return getHeight(this.gifInfoPtr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int j() {
        return getLoopCount(this.gifInfoPtr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long k() {
        return getMetadataByteCount(this.gifInfoPtr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int l() {
        return getNativeErrorCode(this.gifInfoPtr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int m() {
        return getNumberOfFrames(this.gifInfoPtr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long[] n() {
        return getSavedState(this.gifInfoPtr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long o() {
        return getSourceLength(this.gifInfoPtr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int p() {
        return getWidth(this.gifInfoPtr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q(int i2, int i3) {
        glTexImage2D(this.gifInfoPtr, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r(int i2, int i3) {
        glTexSubImage2D(this.gifInfoPtr, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void s() {
        initTexImageDescriptor(this.gifInfoPtr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean t() {
        return isAnimationCompleted(this.gifInfoPtr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean u() {
        return isOpaque(this.gifInfoPtr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean v() {
        return this.gifInfoPtr == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void x() {
        postUnbindSurface(this.gifInfoPtr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void y() {
        free(this.gifInfoPtr);
        this.gifInfoPtr = 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long z(Bitmap bitmap) {
        return renderFrame(this.gifInfoPtr, bitmap);
    }
}
