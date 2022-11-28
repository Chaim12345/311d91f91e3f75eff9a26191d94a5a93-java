package org.bouncycastle.tls;

import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.util.Strings;
/* loaded from: classes4.dex */
public class URLAndHash {

    /* renamed from: a  reason: collision with root package name */
    protected String f14908a;

    /* renamed from: b  reason: collision with root package name */
    protected byte[] f14909b;

    public URLAndHash(String str, byte[] bArr) {
        if (TlsUtils.isNullOrEmpty(str) || str.length() >= 65536) {
            throw new IllegalArgumentException("'url' must have length from 1 to (2^16 - 1)");
        }
        if (bArr != null && bArr.length != 20) {
            throw new IllegalArgumentException("'sha1Hash' must have length == 20, if present");
        }
        this.f14908a = str;
        this.f14909b = bArr;
    }

    public static URLAndHash parse(TlsContext tlsContext, InputStream inputStream) {
        byte[] bArr;
        String fromByteArray = Strings.fromByteArray(TlsUtils.readOpaque16(inputStream, 1));
        short readUint8 = TlsUtils.readUint8(inputStream);
        if (readUint8 != 0) {
            if (readUint8 != 1) {
                throw new TlsFatalAlert((short) 47);
            }
            bArr = TlsUtils.readFully(20, inputStream);
        } else if (TlsUtils.isTLSv12(tlsContext)) {
            throw new TlsFatalAlert((short) 47);
        } else {
            bArr = null;
        }
        return new URLAndHash(fromByteArray, bArr);
    }

    public void encode(OutputStream outputStream) {
        TlsUtils.writeOpaque16(Strings.toByteArray(this.f14908a), outputStream);
        if (this.f14909b == null) {
            TlsUtils.writeUint8(0, outputStream);
            return;
        }
        TlsUtils.writeUint8(1, outputStream);
        outputStream.write(this.f14909b);
    }

    public byte[] getSHA1Hash() {
        return this.f14909b;
    }

    public String getURL() {
        return this.f14908a;
    }
}
