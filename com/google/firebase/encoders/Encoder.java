package com.google.firebase.encoders;

import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
interface Encoder<TValue, TContext> {
    void encode(@NonNull TValue tvalue, @NonNull TContext tcontext);
}
