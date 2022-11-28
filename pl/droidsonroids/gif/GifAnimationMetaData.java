package pl.droidsonroids.gif;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import java.io.File;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Locale;
import kotlin.jvm.internal.CharCompanionObject;
import pl.droidsonroids.gif.annotations.Beta;
/* loaded from: classes4.dex */
public class GifAnimationMetaData implements Serializable, Parcelable {
    public static final Parcelable.Creator<GifAnimationMetaData> CREATOR = new Parcelable.Creator<GifAnimationMetaData>() { // from class: pl.droidsonroids.gif.GifAnimationMetaData.1
        @Override // android.os.Parcelable.Creator
        public GifAnimationMetaData createFromParcel(Parcel parcel) {
            return new GifAnimationMetaData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public GifAnimationMetaData[] newArray(int i2) {
            return new GifAnimationMetaData[i2];
        }
    };
    private static final long serialVersionUID = 5692363926580237325L;
    private final int mDuration;
    private final int mHeight;
    private final int mImageCount;
    private final int mLoopCount;
    private final long mMetadataBytesCount;
    private final long mPixelsBytesCount;
    private final int mWidth;

    public GifAnimationMetaData(@Nullable ContentResolver contentResolver, @NonNull Uri uri) {
        this(GifInfoHandle.w(contentResolver, uri));
    }

    public GifAnimationMetaData(@NonNull AssetFileDescriptor assetFileDescriptor) {
        this(new GifInfoHandle(assetFileDescriptor));
    }

    public GifAnimationMetaData(@NonNull AssetManager assetManager, @NonNull String str) {
        this(assetManager.openFd(str));
    }

    public GifAnimationMetaData(@NonNull Resources resources, @DrawableRes @RawRes int i2) {
        this(resources.openRawResourceFd(i2));
    }

    private GifAnimationMetaData(Parcel parcel) {
        this.mLoopCount = parcel.readInt();
        this.mDuration = parcel.readInt();
        this.mHeight = parcel.readInt();
        this.mWidth = parcel.readInt();
        this.mImageCount = parcel.readInt();
        this.mMetadataBytesCount = parcel.readLong();
        this.mPixelsBytesCount = parcel.readLong();
    }

    public GifAnimationMetaData(@NonNull File file) {
        this(file.getPath());
    }

    public GifAnimationMetaData(@NonNull FileDescriptor fileDescriptor) {
        this(new GifInfoHandle(fileDescriptor));
    }

    public GifAnimationMetaData(@NonNull InputStream inputStream) {
        this(new GifInfoHandle(inputStream));
    }

    public GifAnimationMetaData(@NonNull String str) {
        this(new GifInfoHandle(str));
    }

    public GifAnimationMetaData(@NonNull ByteBuffer byteBuffer) {
        this(new GifInfoHandle(byteBuffer));
    }

    private GifAnimationMetaData(GifInfoHandle gifInfoHandle) {
        this.mLoopCount = gifInfoHandle.j();
        this.mDuration = gifInfoHandle.g();
        this.mWidth = gifInfoHandle.p();
        this.mHeight = gifInfoHandle.i();
        this.mImageCount = gifInfoHandle.m();
        this.mMetadataBytesCount = gifInfoHandle.k();
        this.mPixelsBytesCount = gifInfoHandle.b();
        gifInfoHandle.y();
    }

    public GifAnimationMetaData(@NonNull byte[] bArr) {
        this(new GifInfoHandle(bArr));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long getAllocationByteCount() {
        return this.mPixelsBytesCount;
    }

    @Beta
    public long getDrawableAllocationByteCount(@Nullable GifDrawable gifDrawable, @IntRange(from = 1, to = 65535) int i2) {
        if (i2 >= 1 && i2 <= 65535) {
            int i3 = i2 * i2;
            return (this.mPixelsBytesCount / i3) + ((gifDrawable == null || gifDrawable.f15253e.isRecycled()) ? ((this.mWidth * this.mHeight) * 4) / i3 : Build.VERSION.SDK_INT >= 19 ? gifDrawable.f15253e.getAllocationByteCount() : gifDrawable.getFrameByteCount());
        }
        throw new IllegalStateException("Sample size " + i2 + " out of range <1, " + CharCompanionObject.MAX_VALUE + ">");
    }

    public int getDuration() {
        return this.mDuration;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getLoopCount() {
        return this.mLoopCount;
    }

    public long getMetadataAllocationByteCount() {
        return this.mMetadataBytesCount;
    }

    public int getNumberOfFrames() {
        return this.mImageCount;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public boolean isAnimated() {
        return this.mImageCount > 1 && this.mDuration > 0;
    }

    @NonNull
    public String toString() {
        int i2 = this.mLoopCount;
        String format = String.format(Locale.ENGLISH, "GIF: size: %dx%d, frames: %d, loops: %s, duration: %d", Integer.valueOf(this.mWidth), Integer.valueOf(this.mHeight), Integer.valueOf(this.mImageCount), i2 == 0 ? "Infinity" : Integer.toString(i2), Integer.valueOf(this.mDuration));
        if (isAnimated()) {
            return "Animated " + format;
        }
        return format;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.mLoopCount);
        parcel.writeInt(this.mDuration);
        parcel.writeInt(this.mHeight);
        parcel.writeInt(this.mWidth);
        parcel.writeInt(this.mImageCount);
        parcel.writeLong(this.mMetadataBytesCount);
        parcel.writeLong(this.mPixelsBytesCount);
    }
}
