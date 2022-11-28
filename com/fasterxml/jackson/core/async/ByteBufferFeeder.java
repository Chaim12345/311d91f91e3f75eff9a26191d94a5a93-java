package com.fasterxml.jackson.core.async;

import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public interface ByteBufferFeeder extends NonBlockingInputFeeder {
    void feedInput(ByteBuffer byteBuffer);
}
