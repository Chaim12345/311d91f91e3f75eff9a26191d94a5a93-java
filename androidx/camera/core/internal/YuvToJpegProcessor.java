package androidx.camera.core.internal;

import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.Image;
import android.media.ImageWriter;
import android.util.Size;
import android.view.Surface;
import androidx.annotation.GuardedBy;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CaptureProcessor;
import androidx.camera.core.impl.ImageProxyBundle;
import androidx.camera.core.impl.utils.ExifData;
import androidx.camera.core.impl.utils.ExifOutputStream;
import androidx.camera.core.internal.compat.ImageWriterCompat;
import androidx.camera.core.internal.utils.ImageUtil;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.EOFException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
@RequiresApi(26)
/* loaded from: classes.dex */
public class YuvToJpegProcessor implements CaptureProcessor {
    private static final String TAG = "YuvToJpegProcessor";
    private static final Rect UNINITIALIZED_RECT = new Rect(0, 0, 0, 0);
    @GuardedBy("mLock")
    private ImageWriter mImageWriter;
    private final int mMaxImages;
    @IntRange(from = 0, to = 100)
    private final int mQuality;
    private final Object mLock = new Object();
    @GuardedBy("mLock")
    private boolean mClosed = false;
    @GuardedBy("mLock")
    private int mProcessingImages = 0;
    @GuardedBy("mLock")
    private Rect mImageRect = UNINITIALIZED_RECT;

    /* loaded from: classes.dex */
    private static final class ByteBufferOutputStream extends OutputStream {
        private final ByteBuffer mByteBuffer;

        ByteBufferOutputStream(@NonNull ByteBuffer byteBuffer) {
            this.mByteBuffer = byteBuffer;
        }

        @Override // java.io.OutputStream
        public void write(int i2) {
            if (!this.mByteBuffer.hasRemaining()) {
                throw new EOFException("Output ByteBuffer has no bytes remaining.");
            }
            this.mByteBuffer.put((byte) i2);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i2, int i3) {
            int i4;
            Objects.requireNonNull(bArr);
            if (i2 < 0 || i2 > bArr.length || i3 < 0 || (i4 = i2 + i3) > bArr.length || i4 < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (i3 == 0) {
                return;
            }
            if (this.mByteBuffer.remaining() < i3) {
                throw new EOFException("Output ByteBuffer has insufficient bytes remaining.");
            }
            this.mByteBuffer.put(bArr, i2, i3);
        }
    }

    public YuvToJpegProcessor(@IntRange(from = 0, to = 100) int i2, int i3) {
        this.mQuality = i2;
        this.mMaxImages = i3;
    }

    @NonNull
    private static ExifData getExifData(@NonNull ImageProxy imageProxy) {
        ExifData.Builder builderForDevice = ExifData.builderForDevice();
        imageProxy.getImageInfo().populateExifData(builderForDevice);
        return builderForDevice.setImageWidth(imageProxy.getWidth()).setImageHeight(imageProxy.getHeight()).build();
    }

    public void close() {
        synchronized (this.mLock) {
            if (!this.mClosed) {
                this.mClosed = true;
                if (this.mProcessingImages != 0 || this.mImageWriter == null) {
                    Logger.d(TAG, "close() called while processing. Will close after completion.");
                } else {
                    Logger.d(TAG, "No processing in progress. Closing immediately.");
                    this.mImageWriter.close();
                }
            }
        }
    }

    @Override // androidx.camera.core.impl.CaptureProcessor
    public void onOutputSurface(@NonNull Surface surface, int i2) {
        Preconditions.checkState(i2 == 256, "YuvToJpegProcessor only supports JPEG output format.");
        synchronized (this.mLock) {
            if (this.mClosed) {
                Logger.w(TAG, "Cannot set output surface. Processor is closed.");
            } else if (this.mImageWriter != null) {
                throw new IllegalStateException("Output surface already set.");
            } else {
                this.mImageWriter = ImageWriterCompat.newInstance(surface, this.mMaxImages, i2);
            }
        }
    }

    @Override // androidx.camera.core.impl.CaptureProcessor
    public void onResolutionUpdate(@NonNull Size size) {
        synchronized (this.mLock) {
            this.mImageRect = new Rect(0, 0, size.getWidth(), size.getHeight());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x013b A[Catch: all -> 0x015e, TRY_ENTER, TRY_LEAVE, TryCatch #18 {all -> 0x015e, blocks: (B:43:0x00e3, B:90:0x013b), top: B:151:0x0057 }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0162  */
    @Override // androidx.camera.core.impl.CaptureProcessor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void process(@NonNull ImageProxyBundle imageProxyBundle) {
        ImageWriter imageWriter;
        boolean z;
        Rect rect;
        Image image;
        ImageProxy imageProxy;
        ImageProxy imageProxy2;
        List<Integer> captureIds = imageProxyBundle.getCaptureIds();
        boolean z2 = false;
        Preconditions.checkArgument(captureIds.size() == 1, "Processing image bundle have single capture id, but found " + captureIds.size());
        ListenableFuture<ImageProxy> imageProxy3 = imageProxyBundle.getImageProxy(captureIds.get(0).intValue());
        Preconditions.checkArgument(imageProxy3.isDone());
        synchronized (this.mLock) {
            imageWriter = this.mImageWriter;
            z = !this.mClosed;
            rect = this.mImageRect;
            if (z) {
                this.mProcessingImages++;
            }
        }
        ImageProxy imageProxy4 = null;
        try {
            try {
                imageProxy = imageProxy3.get();
                try {
                } catch (InterruptedException e2) {
                    e = e2;
                    image = null;
                    imageProxy4 = imageProxy;
                    if (z) {
                    }
                    synchronized (this.mLock) {
                    }
                } catch (ExecutionException e3) {
                    e = e3;
                    image = null;
                    imageProxy4 = imageProxy;
                    if (z) {
                    }
                    synchronized (this.mLock) {
                    }
                } catch (Throwable th) {
                    th = th;
                    image = null;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (InterruptedException e4) {
            e = e4;
            image = null;
            if (z) {
            }
            synchronized (this.mLock) {
            }
        } catch (ExecutionException e5) {
            e = e5;
            image = null;
            if (z) {
            }
            synchronized (this.mLock) {
            }
        } catch (Throwable th3) {
            th = th3;
            image = null;
        }
        if (!z) {
            Logger.w(TAG, "Image enqueued for processing on closed processor.");
            imageProxy.close();
            synchronized (this.mLock) {
                if (z) {
                    try {
                        int i2 = this.mProcessingImages;
                        this.mProcessingImages = i2 - 1;
                        if (i2 == 0 && this.mClosed) {
                            z2 = true;
                        }
                    } finally {
                    }
                }
            }
            if (z2) {
                imageWriter.close();
                Logger.d(TAG, "Closed after completion of last image processed.");
                return;
            }
            return;
        }
        image = imageWriter.dequeueInputImage();
        try {
            imageProxy2 = imageProxy3.get();
        } catch (InterruptedException e6) {
            e = e6;
            imageProxy4 = imageProxy;
            if (z) {
                Logger.e(TAG, "Failed to process YUV -> JPEG", e);
                Image dequeueInputImage = imageWriter.dequeueInputImage();
                try {
                    ByteBuffer buffer = dequeueInputImage.getPlanes()[0].getBuffer();
                    buffer.rewind();
                    buffer.limit(0);
                    imageWriter.queueInputImage(dequeueInputImage);
                    image = dequeueInputImage;
                } catch (Throwable th4) {
                    th = th4;
                    image = dequeueInputImage;
                    synchronized (this.mLock) {
                        if (z) {
                            try {
                                int i3 = this.mProcessingImages;
                                this.mProcessingImages = i3 - 1;
                                if (i3 == 0 && this.mClosed) {
                                    z2 = true;
                                }
                            } finally {
                            }
                        }
                    }
                    if (image != null) {
                        image.close();
                    }
                    if (imageProxy4 != null) {
                        imageProxy4.close();
                    }
                    if (z2) {
                        imageWriter.close();
                        Logger.d(TAG, "Closed after completion of last image processed.");
                    }
                    throw th;
                }
            }
            synchronized (this.mLock) {
                if (z) {
                    try {
                        int i4 = this.mProcessingImages;
                        this.mProcessingImages = i4 - 1;
                        if (i4 == 0 && this.mClosed) {
                            z2 = true;
                        }
                    } finally {
                    }
                }
            }
            if (image != null) {
                image.close();
            }
            if (imageProxy4 != null) {
                imageProxy4.close();
            }
            if (!z2) {
                return;
            }
            imageWriter.close();
            Logger.d(TAG, "Closed after completion of last image processed.");
        } catch (ExecutionException e7) {
            e = e7;
            imageProxy4 = imageProxy;
            if (z) {
            }
            synchronized (this.mLock) {
            }
        } catch (Throwable th5) {
            th = th5;
            imageProxy4 = imageProxy;
            synchronized (this.mLock) {
            }
        }
        try {
            Preconditions.checkState(imageProxy2.getFormat() == 35, "Input image is not expected YUV_420_888 image format");
            YuvImage yuvImage = new YuvImage(ImageUtil.yuv_420_888toNv21(imageProxy2), 17, imageProxy2.getWidth(), imageProxy2.getHeight(), null);
            ByteBuffer buffer2 = image.getPlanes()[0].getBuffer();
            int position = buffer2.position();
            yuvImage.compressToJpeg(rect, this.mQuality, new ExifOutputStream(new ByteBufferOutputStream(buffer2), getExifData(imageProxy2)));
            imageProxy2.close();
            try {
                buffer2.limit(buffer2.position());
                buffer2.position(position);
                imageWriter.queueInputImage(image);
                synchronized (this.mLock) {
                    if (z) {
                        try {
                            int i5 = this.mProcessingImages;
                            this.mProcessingImages = i5 - 1;
                            if (i5 == 0 && this.mClosed) {
                                z2 = true;
                            }
                        } finally {
                        }
                    }
                }
                if (!z2) {
                    return;
                }
            } catch (InterruptedException e8) {
                e = e8;
                if (z) {
                }
                synchronized (this.mLock) {
                }
            } catch (ExecutionException e9) {
                e = e9;
                if (z) {
                }
                synchronized (this.mLock) {
                }
            }
        } catch (InterruptedException e10) {
            e = e10;
            imageProxy4 = imageProxy2;
            if (z) {
            }
            synchronized (this.mLock) {
            }
        } catch (ExecutionException e11) {
            e = e11;
            imageProxy4 = imageProxy2;
            if (z) {
            }
            synchronized (this.mLock) {
            }
        } catch (Throwable th6) {
            th = th6;
            imageProxy4 = imageProxy2;
            synchronized (this.mLock) {
            }
        }
        imageWriter.close();
        Logger.d(TAG, "Closed after completion of last image processed.");
    }
}
