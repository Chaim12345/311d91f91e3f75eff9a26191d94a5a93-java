package com.google.firebase.encoders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public interface ObjectEncoderContext {
    @NonNull
    ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, double d2);

    @NonNull
    ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, float f2);

    @NonNull
    ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, int i2);

    @NonNull
    ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, long j2);

    @NonNull
    ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, @Nullable Object obj);

    @NonNull
    ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, boolean z);

    @NonNull
    @Deprecated
    ObjectEncoderContext add(@NonNull String str, double d2);

    @NonNull
    @Deprecated
    ObjectEncoderContext add(@NonNull String str, int i2);

    @NonNull
    @Deprecated
    ObjectEncoderContext add(@NonNull String str, long j2);

    @NonNull
    @Deprecated
    ObjectEncoderContext add(@NonNull String str, @Nullable Object obj);

    @NonNull
    @Deprecated
    ObjectEncoderContext add(@NonNull String str, boolean z);

    @NonNull
    ObjectEncoderContext inline(@Nullable Object obj);

    @NonNull
    ObjectEncoderContext nested(@NonNull FieldDescriptor fieldDescriptor);

    @NonNull
    ObjectEncoderContext nested(@NonNull String str);
}
