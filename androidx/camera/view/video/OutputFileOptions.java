package androidx.camera.view.video;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.camera.core.VideoCapture;
import androidx.camera.view.video.AutoValue_OutputFileOptions;
import androidx.core.util.Preconditions;
import com.google.auto.value.AutoValue;
import java.io.File;
@ExperimentalVideo
@AutoValue
/* loaded from: classes.dex */
public abstract class OutputFileOptions {
    private static final Metadata EMPTY_METADATA = Metadata.builder().build();

    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class Builder {
        abstract Builder a(@Nullable ContentResolver contentResolver);

        abstract Builder b(@Nullable ContentValues contentValues);

        @NonNull
        public abstract OutputFileOptions build();

        abstract Builder c(@Nullable File file);

        abstract Builder d(@Nullable ParcelFileDescriptor parcelFileDescriptor);

        abstract Builder e(@Nullable Uri uri);

        @NonNull
        public abstract Builder setMetadata(@NonNull Metadata metadata);
    }

    @NonNull
    public static Builder builder(@NonNull ContentResolver contentResolver, @NonNull Uri uri, @NonNull ContentValues contentValues) {
        return new AutoValue_OutputFileOptions.Builder().setMetadata(EMPTY_METADATA).a(contentResolver).e(uri).b(contentValues);
    }

    @NonNull
    public static Builder builder(@NonNull ParcelFileDescriptor parcelFileDescriptor) {
        Preconditions.checkArgument(Build.VERSION.SDK_INT >= 26, "Using a ParcelFileDescriptor to record a video is only supported for Android 8.0 or above.");
        return new AutoValue_OutputFileOptions.Builder().setMetadata(EMPTY_METADATA).d(parcelFileDescriptor);
    }

    @NonNull
    public static Builder builder(@NonNull File file) {
        return new AutoValue_OutputFileOptions.Builder().setMetadata(EMPTY_METADATA).c(file);
    }

    private boolean isSavingToFile() {
        return c() != null;
    }

    private boolean isSavingToFileDescriptor() {
        return d() != null;
    }

    private boolean isSavingToMediaStore() {
        return (e() == null || a() == null || b() == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract ContentResolver a();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract ContentValues b();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract File c();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract ParcelFileDescriptor d();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract Uri e();

    @NonNull
    public abstract Metadata getMetadata();

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public VideoCapture.OutputFileOptions toVideoCaptureOutputFileOptions() {
        VideoCapture.OutputFileOptions.Builder builder;
        if (isSavingToFile()) {
            builder = new VideoCapture.OutputFileOptions.Builder((File) Preconditions.checkNotNull(c()));
        } else if (isSavingToFileDescriptor()) {
            builder = new VideoCapture.OutputFileOptions.Builder(((ParcelFileDescriptor) Preconditions.checkNotNull(d())).getFileDescriptor());
        } else {
            Preconditions.checkState(isSavingToMediaStore());
            builder = new VideoCapture.OutputFileOptions.Builder((ContentResolver) Preconditions.checkNotNull(a()), (Uri) Preconditions.checkNotNull(e()), (ContentValues) Preconditions.checkNotNull(b()));
        }
        VideoCapture.Metadata metadata = new VideoCapture.Metadata();
        metadata.location = getMetadata().getLocation();
        builder.setMetadata(metadata);
        return builder.build();
    }
}
