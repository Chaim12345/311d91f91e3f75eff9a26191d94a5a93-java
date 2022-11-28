package org.bouncycastle.jsse.provider;

import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.tls.TlsServerProtocol;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ProvTlsServerProtocol extends TlsServerProtocol {
    private final Closeable closeable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvTlsServerProtocol(InputStream inputStream, OutputStream outputStream, Closeable closeable) {
        super(inputStream, outputStream);
        this.closeable = closeable;
    }

    @Override // org.bouncycastle.tls.TlsProtocol
    protected void g() {
        this.closeable.close();
    }
}
