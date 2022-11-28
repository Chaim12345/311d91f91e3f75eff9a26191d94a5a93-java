package org.bouncycastle.tls;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes4.dex */
public class HeartbeatMessage {

    /* renamed from: a  reason: collision with root package name */
    protected short f14760a;

    /* renamed from: b  reason: collision with root package name */
    protected byte[] f14761b;

    /* renamed from: c  reason: collision with root package name */
    protected byte[] f14762c;

    /* loaded from: classes4.dex */
    static class PayloadBuffer extends ByteArrayOutputStream {
        PayloadBuffer() {
        }

        byte[] a(int i2) {
            return TlsUtils.copyOfRangeExact(((ByteArrayOutputStream) this).buf, i2, ((ByteArrayOutputStream) this).count);
        }

        byte[] b(int i2) {
            if (i2 > ((ByteArrayOutputStream) this).count - 16) {
                return null;
            }
            return Arrays.copyOf(((ByteArrayOutputStream) this).buf, i2);
        }
    }

    public HeartbeatMessage(short s2, byte[] bArr, byte[] bArr2) {
        if (!HeartbeatMessageType.isValid(s2)) {
            throw new IllegalArgumentException("'type' is not a valid HeartbeatMessageType value");
        }
        if (bArr == null || bArr.length >= 65536) {
            throw new IllegalArgumentException("'payload' must have length < 2^16");
        }
        if (bArr2 == null || bArr2.length < 16) {
            throw new IllegalArgumentException("'padding' must have length >= 16");
        }
        this.f14760a = s2;
        this.f14761b = bArr;
        this.f14762c = bArr2;
    }

    public static HeartbeatMessage create(TlsContext tlsContext, short s2, byte[] bArr) {
        return create(tlsContext, s2, bArr, 16);
    }

    public static HeartbeatMessage create(TlsContext tlsContext, short s2, byte[] bArr, int i2) {
        return new HeartbeatMessage(s2, bArr, tlsContext.getNonceGenerator().generateNonce(i2));
    }

    public static HeartbeatMessage parse(InputStream inputStream) {
        short readUint8 = TlsUtils.readUint8(inputStream);
        if (HeartbeatMessageType.isValid(readUint8)) {
            int readUint16 = TlsUtils.readUint16(inputStream);
            PayloadBuffer payloadBuffer = new PayloadBuffer();
            Streams.pipeAll(inputStream, payloadBuffer);
            byte[] b2 = payloadBuffer.b(readUint16);
            if (b2 == null) {
                return null;
            }
            return new HeartbeatMessage(readUint8, b2, payloadBuffer.a(readUint16));
        }
        throw new TlsFatalAlert((short) 47);
    }

    public void encode(OutputStream outputStream) {
        TlsUtils.writeUint8(this.f14760a, outputStream);
        TlsUtils.checkUint16(this.f14761b.length);
        TlsUtils.writeUint16(this.f14761b.length, outputStream);
        outputStream.write(this.f14761b);
        outputStream.write(this.f14762c);
    }

    public int getPaddingLength() {
        return this.f14762c.length;
    }

    public byte[] getPayload() {
        return this.f14761b;
    }

    public short getType() {
        return this.f14760a;
    }
}
