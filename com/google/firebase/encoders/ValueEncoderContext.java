package com.google.firebase.encoders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public interface ValueEncoderContext {
    @NonNull
    ValueEncoderContext add(double d2);

    @NonNull
    ValueEncoderContext add(float f2);

    @NonNull
    ValueEncoderContext add(int i2);

    @NonNull
    ValueEncoderContext add(long j2);

    @NonNull
    ValueEncoderContext add(@Nullable String str);

    @NonNull
    ValueEncoderContext add(boolean z);

    @NonNull
    ValueEncoderContext add(@NonNull byte[] bArr);
}
