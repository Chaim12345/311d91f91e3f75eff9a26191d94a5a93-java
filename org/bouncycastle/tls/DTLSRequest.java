package org.bouncycastle.tls;
/* loaded from: classes4.dex */
public class DTLSRequest {
    private final ClientHello clientHello;
    private final byte[] message;
    private final long recordSeq;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DTLSRequest(long j2, byte[] bArr, ClientHello clientHello) {
        this.recordSeq = j2;
        this.message = bArr;
        this.clientHello = clientHello;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClientHello a() {
        return this.clientHello;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] b() {
        return this.message;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c() {
        return TlsUtils.readUint16(this.message, 4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long d() {
        return this.recordSeq;
    }
}
