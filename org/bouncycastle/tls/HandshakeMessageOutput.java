package org.bouncycastle.tls;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class HandshakeMessageOutput extends ByteArrayOutputStream {
    /* JADX INFO: Access modifiers changed from: package-private */
    public HandshakeMessageOutput(short s2) {
        this(s2, 60);
    }

    HandshakeMessageOutput(short s2, int i2) {
        super(a(i2));
        TlsUtils.checkUint8(s2);
        TlsUtils.writeUint8(s2, (OutputStream) this);
        ((ByteArrayOutputStream) this).count += 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(int i2) {
        return i2 + 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void d(TlsProtocol tlsProtocol, short s2, byte[] bArr) {
        HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput(s2, bArr.length);
        handshakeMessageOutput.write(bArr);
        handshakeMessageOutput.c(tlsProtocol);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(TlsHandshakeHash tlsHandshakeHash, int i2) {
        int i3 = (((ByteArrayOutputStream) this).count - 4) + i2;
        TlsUtils.checkUint24(i3);
        TlsUtils.writeUint24(i3, ((ByteArrayOutputStream) this).buf, 1);
        tlsHandshakeHash.update(((ByteArrayOutputStream) this).buf, 0, ((ByteArrayOutputStream) this).count);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(TlsProtocol tlsProtocol) {
        int i2 = ((ByteArrayOutputStream) this).count - 4;
        TlsUtils.checkUint24(i2);
        TlsUtils.writeUint24(i2, ((ByteArrayOutputStream) this).buf, 1);
        tlsProtocol.h0(((ByteArrayOutputStream) this).buf, 0, ((ByteArrayOutputStream) this).count);
        ((ByteArrayOutputStream) this).buf = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(TlsClientProtocol tlsClientProtocol, TlsHandshakeHash tlsHandshakeHash, int i2) {
        if (i2 > 0) {
            tlsHandshakeHash.update(((ByteArrayOutputStream) this).buf, ((ByteArrayOutputStream) this).count - i2, i2);
        }
        tlsClientProtocol.h0(((ByteArrayOutputStream) this).buf, 0, ((ByteArrayOutputStream) this).count);
        ((ByteArrayOutputStream) this).buf = null;
    }
}
