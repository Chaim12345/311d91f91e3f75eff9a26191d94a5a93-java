package org.bouncycastle.tls;

import java.io.ByteArrayInputStream;
import org.bouncycastle.tls.crypto.TlsHash;
/* loaded from: classes4.dex */
public class HandshakeMessageInput extends ByteArrayInputStream {
    /* JADX INFO: Access modifiers changed from: package-private */
    public HandshakeMessageInput(byte[] bArr, int i2, int i3) {
        super(bArr, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(TlsHash tlsHash, int i2) {
        byte[] bArr = ((ByteArrayInputStream) this).buf;
        int i3 = ((ByteArrayInputStream) this).mark;
        tlsHash.update(bArr, i3, (((ByteArrayInputStream) this).count - i3) - i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(TlsHash tlsHash, int i2) {
        tlsHash.update(((ByteArrayInputStream) this).buf, ((ByteArrayInputStream) this).count - i2, i2);
    }

    @Override // java.io.ByteArrayInputStream, java.io.InputStream
    public void mark(int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.ByteArrayInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    public void updateHash(TlsHash tlsHash) {
        byte[] bArr = ((ByteArrayInputStream) this).buf;
        int i2 = ((ByteArrayInputStream) this).mark;
        tlsHash.update(bArr, i2, ((ByteArrayInputStream) this).count - i2);
    }
}
