package pl.droidsonroids.gif;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import java.io.File;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
/* loaded from: classes4.dex */
public abstract class InputSource {

    /* loaded from: classes4.dex */
    public static class AssetFileDescriptorSource extends InputSource {
        private final AssetFileDescriptor mAssetFileDescriptor;

        public AssetFileDescriptorSource(@NonNull AssetFileDescriptor assetFileDescriptor) {
            super();
            this.mAssetFileDescriptor = assetFileDescriptor;
        }

        @Override // pl.droidsonroids.gif.InputSource
        GifInfoHandle c() {
            return new GifInfoHandle(this.mAssetFileDescriptor);
        }
    }

    /* loaded from: classes4.dex */
    public static final class AssetSource extends InputSource {
        private final AssetManager mAssetManager;
        private final String mAssetName;

        public AssetSource(@NonNull AssetManager assetManager, @NonNull String str) {
            super();
            this.mAssetManager = assetManager;
            this.mAssetName = str;
        }

        @Override // pl.droidsonroids.gif.InputSource
        GifInfoHandle c() {
            return new GifInfoHandle(this.mAssetManager.openFd(this.mAssetName));
        }
    }

    /* loaded from: classes4.dex */
    public static final class ByteArraySource extends InputSource {
        private final byte[] bytes;

        public ByteArraySource(@NonNull byte[] bArr) {
            super();
            this.bytes = bArr;
        }

        @Override // pl.droidsonroids.gif.InputSource
        GifInfoHandle c() {
            return new GifInfoHandle(this.bytes);
        }
    }

    /* loaded from: classes4.dex */
    public static final class DirectByteBufferSource extends InputSource {
        private final ByteBuffer byteBuffer;

        public DirectByteBufferSource(@NonNull ByteBuffer byteBuffer) {
            super();
            this.byteBuffer = byteBuffer;
        }

        @Override // pl.droidsonroids.gif.InputSource
        GifInfoHandle c() {
            return new GifInfoHandle(this.byteBuffer);
        }
    }

    /* loaded from: classes4.dex */
    public static final class FileDescriptorSource extends InputSource {
        private final FileDescriptor mFd;

        public FileDescriptorSource(@NonNull FileDescriptor fileDescriptor) {
            super();
            this.mFd = fileDescriptor;
        }

        @Override // pl.droidsonroids.gif.InputSource
        GifInfoHandle c() {
            return new GifInfoHandle(this.mFd);
        }
    }

    /* loaded from: classes4.dex */
    public static final class FileSource extends InputSource {
        private final String mPath;

        public FileSource(@NonNull File file) {
            super();
            this.mPath = file.getPath();
        }

        public FileSource(@NonNull String str) {
            super();
            this.mPath = str;
        }

        @Override // pl.droidsonroids.gif.InputSource
        GifInfoHandle c() {
            return new GifInfoHandle(this.mPath);
        }
    }

    /* loaded from: classes4.dex */
    public static final class InputStreamSource extends InputSource {
        private final InputStream inputStream;

        public InputStreamSource(@NonNull InputStream inputStream) {
            super();
            this.inputStream = inputStream;
        }

        @Override // pl.droidsonroids.gif.InputSource
        GifInfoHandle c() {
            return new GifInfoHandle(this.inputStream);
        }
    }

    /* loaded from: classes4.dex */
    public static class ResourcesSource extends InputSource {
        private final int mResourceId;
        private final Resources mResources;

        public ResourcesSource(@NonNull Resources resources, @DrawableRes @RawRes int i2) {
            super();
            this.mResources = resources;
            this.mResourceId = i2;
        }

        @Override // pl.droidsonroids.gif.InputSource
        GifInfoHandle c() {
            return new GifInfoHandle(this.mResources.openRawResourceFd(this.mResourceId));
        }
    }

    /* loaded from: classes4.dex */
    public static final class UriSource extends InputSource {
        private final ContentResolver mContentResolver;
        private final Uri mUri;

        public UriSource(@Nullable ContentResolver contentResolver, @NonNull Uri uri) {
            super();
            this.mContentResolver = contentResolver;
            this.mUri = uri;
        }

        @Override // pl.droidsonroids.gif.InputSource
        GifInfoHandle c() {
            return GifInfoHandle.w(this.mContentResolver, this.mUri);
        }
    }

    private InputSource() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final GifDrawable a(GifDrawable gifDrawable, ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, boolean z, GifOptions gifOptions) {
        return new GifDrawable(b(gifOptions), gifDrawable, scheduledThreadPoolExecutor, z);
    }

    final GifInfoHandle b(@NonNull GifOptions gifOptions) {
        GifInfoHandle c2 = c();
        c2.I(gifOptions.f15265a, gifOptions.f15266b);
        return c2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract GifInfoHandle c();
}
