package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.IntRange;
import androidx.annotation.RecentlyNonNull;
import com.google.android.libraries.places.internal.zzha;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes2.dex */
public abstract class PhotoMetadata implements Parcelable {

    @AutoValue.Builder
    /* loaded from: classes2.dex */
    public static abstract class Builder {
        @RecentlyNonNull
        public PhotoMetadata build() {
            PhotoMetadata zzb = zzb();
            int width = zzb.getWidth();
            zzha.zzj(width >= 0, "Width must not be < 0, but was: %s.", width);
            int height = zzb.getHeight();
            zzha.zzj(height >= 0, "Height must not be < 0, but was: %s.", height);
            zzha.zzi(!TextUtils.isEmpty(zzb.zza()), "PhotoReference must not be null or empty.");
            return zzb;
        }

        @RecentlyNonNull
        public abstract String getAttributions();

        @IntRange(from = 0)
        public abstract int getHeight();

        @IntRange(from = 0)
        public abstract int getWidth();

        @RecentlyNonNull
        public abstract Builder setAttributions(@RecentlyNonNull String str);

        @RecentlyNonNull
        public abstract Builder setHeight(@IntRange(from = 0) int i2);

        @RecentlyNonNull
        public abstract Builder setWidth(@IntRange(from = 0) int i2);

        abstract PhotoMetadata zzb();
    }

    @RecentlyNonNull
    public static Builder builder(@RecentlyNonNull String str) {
        zzo zzoVar = new zzo();
        zzoVar.zza(str);
        zzoVar.setWidth(0);
        zzoVar.setHeight(0);
        zzoVar.setAttributions("");
        return zzoVar;
    }

    @RecentlyNonNull
    public abstract String getAttributions();

    @IntRange(from = 0)
    public abstract int getHeight();

    @IntRange(from = 0)
    public abstract int getWidth();

    @RecentlyNonNull
    public abstract String zza();
}
