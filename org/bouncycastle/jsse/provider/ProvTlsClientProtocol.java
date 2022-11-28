package org.bouncycastle.jsse.provider;

import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.tls.TlsClientProtocol;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ProvTlsClientProtocol extends TlsClientProtocol {
    private static final boolean provAcceptRenegotiation = PropertyUtils.b("org.bouncycastle.jsse.client.acceptRenegotiation", false);
    private final Closeable closeable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvTlsClientProtocol(InputStream inputStream, OutputStream outputStream, Closeable closeable) {
        super(inputStream, outputStream);
        this.closeable = closeable;
    }

    @Override // org.bouncycastle.tls.TlsProtocol
    protected void g() {
        this.closeable.close();
    }

    @Override // org.bouncycastle.tls.TlsProtocol
    protected int p() {
        return provAcceptRenegotiation ? 2 : 0;
    }
}
