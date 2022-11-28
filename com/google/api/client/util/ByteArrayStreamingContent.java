package com.google.api.client.util;

import java.io.OutputStream;
/* loaded from: classes2.dex */
public class ByteArrayStreamingContent implements StreamingContent {
    private final byte[] byteArray;
    private final int length;
    private final int offset;

    public ByteArrayStreamingContent(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public ByteArrayStreamingContent(byte[] bArr, int i2, int i3) {
        this.byteArray = (byte[]) Preconditions.checkNotNull(bArr);
        Preconditions.checkArgument(i2 >= 0 && i3 >= 0 && i2 + i3 <= bArr.length);
        this.offset = i2;
        this.length = i3;
    }

    @Override // com.google.api.client.util.StreamingContent
    public void writeTo(OutputStream outputStream) {
        outputStream.write(this.byteArray, this.offset, this.length);
        outputStream.flush();
    }
}
