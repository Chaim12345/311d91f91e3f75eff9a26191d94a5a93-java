package androidx.camera.view.video;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.view.video.OutputFileOptions;
import java.io.File;
import java.util.Objects;
/* loaded from: classes.dex */
final class AutoValue_OutputFileOptions extends OutputFileOptions {
    private final ContentResolver contentResolver;
    private final ContentValues contentValues;
    private final File file;
    private final ParcelFileDescriptor fileDescriptor;
    private final Metadata metadata;
    private final Uri saveCollection;

    /* loaded from: classes.dex */
    static final class Builder extends OutputFileOptions.Builder {
        private ContentResolver contentResolver;
        private ContentValues contentValues;
        private File file;
        private ParcelFileDescriptor fileDescriptor;
        private Metadata metadata;
        private Uri saveCollection;

        @Override // androidx.camera.view.video.OutputFileOptions.Builder
        OutputFileOptions.Builder a(@Nullable ContentResolver contentResolver) {
            this.contentResolver = contentResolver;
            return this;
        }

        @Override // androidx.camera.view.video.OutputFileOptions.Builder
        OutputFileOptions.Builder b(@Nullable ContentValues contentValues) {
            this.contentValues = contentValues;
            return this;
        }

        @Override // androidx.camera.view.video.OutputFileOptions.Builder
        public OutputFileOptions build() {
            String str = "";
            if (this.metadata == null) {
                str = " metadata";
            }
            if (str.isEmpty()) {
                return new AutoValue_OutputFileOptions(this.file, this.fileDescriptor, this.contentResolver, this.saveCollection, this.contentValues, this.metadata);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // androidx.camera.view.video.OutputFileOptions.Builder
        OutputFileOptions.Builder c(@Nullable File file) {
            this.file = file;
            return this;
        }

        @Override // androidx.camera.view.video.OutputFileOptions.Builder
        OutputFileOptions.Builder d(@Nullable ParcelFileDescriptor parcelFileDescriptor) {
            this.fileDescriptor = parcelFileDescriptor;
            return this;
        }

        @Override // androidx.camera.view.video.OutputFileOptions.Builder
        OutputFileOptions.Builder e(@Nullable Uri uri) {
            this.saveCollection = uri;
            return this;
        }

        @Override // androidx.camera.view.video.OutputFileOptions.Builder
        public OutputFileOptions.Builder setMetadata(Metadata metadata) {
            Objects.requireNonNull(metadata, "Null metadata");
            this.metadata = metadata;
            return this;
        }
    }

    private AutoValue_OutputFileOptions(@Nullable File file, @Nullable ParcelFileDescriptor parcelFileDescriptor, @Nullable ContentResolver contentResolver, @Nullable Uri uri, @Nullable ContentValues contentValues, Metadata metadata) {
        this.file = file;
        this.fileDescriptor = parcelFileDescriptor;
        this.contentResolver = contentResolver;
        this.saveCollection = uri;
        this.contentValues = contentValues;
        this.metadata = metadata;
    }

    @Override // androidx.camera.view.video.OutputFileOptions
    @Nullable
    ContentResolver a() {
        return this.contentResolver;
    }

    @Override // androidx.camera.view.video.OutputFileOptions
    @Nullable
    ContentValues b() {
        return this.contentValues;
    }

    @Override // androidx.camera.view.video.OutputFileOptions
    @Nullable
    File c() {
        return this.file;
    }

    @Override // androidx.camera.view.video.OutputFileOptions
    @Nullable
    ParcelFileDescriptor d() {
        return this.fileDescriptor;
    }

    @Override // androidx.camera.view.video.OutputFileOptions
    @Nullable
    Uri e() {
        return this.saveCollection;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof OutputFileOptions) {
            OutputFileOptions outputFileOptions = (OutputFileOptions) obj;
            File file = this.file;
            if (file != null ? file.equals(outputFileOptions.c()) : outputFileOptions.c() == null) {
                ParcelFileDescriptor parcelFileDescriptor = this.fileDescriptor;
                if (parcelFileDescriptor != null ? parcelFileDescriptor.equals(outputFileOptions.d()) : outputFileOptions.d() == null) {
                    ContentResolver contentResolver = this.contentResolver;
                    if (contentResolver != null ? contentResolver.equals(outputFileOptions.a()) : outputFileOptions.a() == null) {
                        Uri uri = this.saveCollection;
                        if (uri != null ? uri.equals(outputFileOptions.e()) : outputFileOptions.e() == null) {
                            ContentValues contentValues = this.contentValues;
                            if (contentValues != null ? contentValues.equals(outputFileOptions.b()) : outputFileOptions.b() == null) {
                                if (this.metadata.equals(outputFileOptions.getMetadata())) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }

    @Override // androidx.camera.view.video.OutputFileOptions
    @NonNull
    public Metadata getMetadata() {
        return this.metadata;
    }

    public int hashCode() {
        File file = this.file;
        int hashCode = ((file == null ? 0 : file.hashCode()) ^ 1000003) * 1000003;
        ParcelFileDescriptor parcelFileDescriptor = this.fileDescriptor;
        int hashCode2 = (hashCode ^ (parcelFileDescriptor == null ? 0 : parcelFileDescriptor.hashCode())) * 1000003;
        ContentResolver contentResolver = this.contentResolver;
        int hashCode3 = (hashCode2 ^ (contentResolver == null ? 0 : contentResolver.hashCode())) * 1000003;
        Uri uri = this.saveCollection;
        int hashCode4 = (hashCode3 ^ (uri == null ? 0 : uri.hashCode())) * 1000003;
        ContentValues contentValues = this.contentValues;
        return ((hashCode4 ^ (contentValues != null ? contentValues.hashCode() : 0)) * 1000003) ^ this.metadata.hashCode();
    }

    public String toString() {
        return "OutputFileOptions{file=" + this.file + ", fileDescriptor=" + this.fileDescriptor + ", contentResolver=" + this.contentResolver + ", saveCollection=" + this.saveCollection + ", contentValues=" + this.contentValues + ", metadata=" + this.metadata + "}";
    }
}
