package com.google.crypto.tink.subtle;

import java.nio.ByteBuffer;
/* loaded from: classes2.dex */
public interface StreamSegmentEncrypter {
    void encryptSegment(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, boolean z, ByteBuffer byteBuffer3);

    void encryptSegment(ByteBuffer byteBuffer, boolean z, ByteBuffer byteBuffer2);

    ByteBuffer getHeader();
}
