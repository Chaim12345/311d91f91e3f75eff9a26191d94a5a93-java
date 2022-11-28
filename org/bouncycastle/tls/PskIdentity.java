package org.bouncycastle.tls;

import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class PskIdentity {

    /* renamed from: a  reason: collision with root package name */
    protected byte[] f14790a;

    /* renamed from: b  reason: collision with root package name */
    protected long f14791b;

    public PskIdentity(byte[] bArr, long j2) {
        if (bArr == null) {
            throw new IllegalArgumentException("'identity' cannot be null");
        }
        if (bArr.length < 1 || !TlsUtils.isValidUint16(bArr.length)) {
            throw new IllegalArgumentException("'identity' should have length from 1 to 65535");
        }
        if (!TlsUtils.isValidUint32(j2)) {
            throw new IllegalArgumentException("'obfuscatedTicketAge' should be a uint32");
        }
        this.f14790a = bArr;
        this.f14791b = j2;
    }

    public static PskIdentity parse(InputStream inputStream) {
        return new PskIdentity(TlsUtils.readOpaque16(inputStream, 1), TlsUtils.readUint32(inputStream));
    }

    public void encode(OutputStream outputStream) {
        TlsUtils.writeOpaque16(this.f14790a, outputStream);
        TlsUtils.writeUint32(this.f14791b, outputStream);
    }

    public boolean equals(Object obj) {
        if (obj instanceof PskIdentity) {
            PskIdentity pskIdentity = (PskIdentity) obj;
            return this.f14791b == pskIdentity.f14791b && Arrays.constantTimeAreEqual(this.f14790a, pskIdentity.f14790a);
        }
        return false;
    }

    public int getEncodedLength() {
        return this.f14790a.length + 6;
    }

    public byte[] getIdentity() {
        return this.f14790a;
    }

    public long getObfuscatedTicketAge() {
        return this.f14791b;
    }

    public int hashCode() {
        int hashCode = Arrays.hashCode(this.f14790a);
        long j2 = this.f14791b;
        return (hashCode ^ ((int) j2)) ^ ((int) (j2 >>> 32));
    }
}
