package androidx.camera.view.video;

import android.location.Location;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.view.video.AutoValue_Metadata;
import com.google.auto.value.AutoValue;
@ExperimentalVideo
@AutoValue
/* loaded from: classes.dex */
public abstract class Metadata {

    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class Builder {
        @NonNull
        public abstract Metadata build();

        @NonNull
        public abstract Builder setLocation(@Nullable Location location);
    }

    @NonNull
    public static Builder builder() {
        return new AutoValue_Metadata.Builder();
    }

    @Nullable
    public abstract Location getLocation();
}
