package com.google.crypto.tink.subtle;

import java.nio.ByteBuffer;
/* loaded from: classes2.dex */
public interface StreamSegmentDecrypter {
    void decryptSegment(ByteBuffer byteBuffer, int i2, boolean z, ByteBuffer byteBuffer2);

    void init(ByteBuffer byteBuffer, byte[] bArr);
}
