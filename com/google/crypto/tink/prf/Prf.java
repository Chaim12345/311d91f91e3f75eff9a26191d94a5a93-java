package com.google.crypto.tink.prf;

import com.google.errorprone.annotations.Immutable;
@Immutable
/* loaded from: classes2.dex */
public interface Prf {
    byte[] compute(byte[] bArr, int i2);
}
