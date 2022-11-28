package com.google.crypto.tink.shaded.protobuf;

import java.nio.ByteBuffer;
/* loaded from: classes2.dex */
public abstract class ByteOutput {
    public abstract void write(byte b2);

    public abstract void write(ByteBuffer byteBuffer);

    public abstract void write(byte[] bArr, int i2, int i3);

    public abstract void writeLazy(ByteBuffer byteBuffer);

    public abstract void writeLazy(byte[] bArr, int i2, int i3);
}
