package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Aead;
/* loaded from: classes2.dex */
public interface EciesAeadHkdfDemHelper {
    Aead getAead(byte[] bArr);

    int getSymmetricKeySizeInBytes();
}
