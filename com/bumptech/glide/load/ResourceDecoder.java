package com.bumptech.glide.load;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.engine.Resource;
/* loaded from: classes.dex */
public interface ResourceDecoder<T, Z> {
    @Nullable
    Resource<Z> decode(@NonNull T t2, int i2, int i3, @NonNull Options options);

    boolean handles(@NonNull T t2, @NonNull Options options);
}
