package com.google.crypto.tink.subtle.prf;

import com.google.errorprone.annotations.Immutable;
import java.io.InputStream;
@Immutable
/* loaded from: classes2.dex */
public interface StreamingPrf {
    InputStream computePrf(byte[] bArr);
}
