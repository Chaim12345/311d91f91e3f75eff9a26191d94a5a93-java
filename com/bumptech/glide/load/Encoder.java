package com.bumptech.glide.load;

import androidx.annotation.NonNull;
import java.io.File;
/* loaded from: classes.dex */
public interface Encoder<T> {
    boolean encode(@NonNull T t2, @NonNull File file, @NonNull Options options);
}
