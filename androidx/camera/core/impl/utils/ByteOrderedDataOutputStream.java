package androidx.camera.core.impl.utils;

import java.io.FilterOutputStream;
import java.io.OutputStream;
import java.nio.ByteOrder;
/* loaded from: classes.dex */
class ByteOrderedDataOutputStream extends FilterOutputStream {

    /* renamed from: a  reason: collision with root package name */
    final OutputStream f1180a;
    private ByteOrder mByteOrder;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteOrderedDataOutputStream(OutputStream outputStream, ByteOrder byteOrder) {
        super(outputStream);
        this.f1180a = outputStream;
        this.mByteOrder = byteOrder;
    }

    public void setByteOrder(ByteOrder byteOrder) {
        this.mByteOrder = byteOrder;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) {
        this.f1180a.write(bArr);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        this.f1180a.write(bArr, i2, i3);
    }

    public void writeByte(int i2) {
        this.f1180a.write(i2);
    }

    public void writeInt(int i2) {
        OutputStream outputStream;
        int i3;
        ByteOrder byteOrder = this.mByteOrder;
        if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
            this.f1180a.write((i2 >>> 0) & 255);
            this.f1180a.write((i2 >>> 8) & 255);
            this.f1180a.write((i2 >>> 16) & 255);
            outputStream = this.f1180a;
            i3 = i2 >>> 24;
        } else if (byteOrder != ByteOrder.BIG_ENDIAN) {
            return;
        } else {
            this.f1180a.write((i2 >>> 24) & 255);
            this.f1180a.write((i2 >>> 16) & 255);
            this.f1180a.write((i2 >>> 8) & 255);
            outputStream = this.f1180a;
            i3 = i2 >>> 0;
        }
        outputStream.write(i3 & 255);
    }

    public void writeShort(short s2) {
        OutputStream outputStream;
        int i2;
        ByteOrder byteOrder = this.mByteOrder;
        if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
            this.f1180a.write((s2 >>> 0) & 255);
            outputStream = this.f1180a;
            i2 = s2 >>> 8;
        } else if (byteOrder != ByteOrder.BIG_ENDIAN) {
            return;
        } else {
            this.f1180a.write((s2 >>> 8) & 255);
            outputStream = this.f1180a;
            i2 = s2 >>> 0;
        }
        outputStream.write(i2 & 255);
    }

    public void writeUnsignedInt(long j2) {
        writeInt((int) j2);
    }

    public void writeUnsignedShort(int i2) {
        writeShort((short) i2);
    }
}
