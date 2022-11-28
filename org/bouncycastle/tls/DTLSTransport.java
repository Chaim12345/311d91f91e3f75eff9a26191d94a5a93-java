package org.bouncycastle.tls;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Objects;
/* loaded from: classes4.dex */
public class DTLSTransport implements DatagramTransport {
    private final DTLSRecordLayer recordLayer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DTLSTransport(DTLSRecordLayer dTLSRecordLayer) {
        this.recordLayer = dTLSRecordLayer;
    }

    @Override // org.bouncycastle.tls.TlsCloseable
    public void close() {
        this.recordLayer.close();
    }

    @Override // org.bouncycastle.tls.DatagramReceiver
    public int getReceiveLimit() {
        return this.recordLayer.getReceiveLimit();
    }

    @Override // org.bouncycastle.tls.DatagramSender
    public int getSendLimit() {
        return this.recordLayer.getSendLimit();
    }

    @Override // org.bouncycastle.tls.DatagramReceiver
    public int receive(byte[] bArr, int i2, int i3, int i4) {
        Objects.requireNonNull(bArr, "'buf' cannot be null");
        if (i2 < 0 || i2 >= bArr.length) {
            throw new IllegalArgumentException("'off' is an invalid offset: " + i2);
        } else if (i3 < 0 || i3 > bArr.length - i2) {
            throw new IllegalArgumentException("'len' is an invalid length: " + i3);
        } else if (i4 >= 0) {
            try {
                return this.recordLayer.receive(bArr, i2, i3, i4);
            } catch (InterruptedIOException e2) {
                throw e2;
            } catch (RuntimeException e3) {
                this.recordLayer.a((short) 80);
                throw new TlsFatalAlert((short) 80, (Throwable) e3);
            } catch (TlsFatalAlert e4) {
                this.recordLayer.a(e4.getAlertDescription());
                throw e4;
            } catch (IOException e5) {
                this.recordLayer.a((short) 80);
                throw e5;
            }
        } else {
            throw new IllegalArgumentException("'waitMillis' cannot be negative");
        }
    }

    @Override // org.bouncycastle.tls.DatagramSender
    public void send(byte[] bArr, int i2, int i3) {
        Objects.requireNonNull(bArr, "'buf' cannot be null");
        if (i2 < 0 || i2 >= bArr.length) {
            throw new IllegalArgumentException("'off' is an invalid offset: " + i2);
        } else if (i3 < 0 || i3 > bArr.length - i2) {
            throw new IllegalArgumentException("'len' is an invalid length: " + i3);
        } else {
            try {
                this.recordLayer.send(bArr, i2, i3);
            } catch (InterruptedIOException e2) {
                throw e2;
            } catch (IOException e3) {
                this.recordLayer.a((short) 80);
                throw e3;
            } catch (RuntimeException e4) {
                this.recordLayer.a((short) 80);
                throw new TlsFatalAlert((short) 80, (Throwable) e4);
            } catch (TlsFatalAlert e5) {
                this.recordLayer.a(e5.getAlertDescription());
                throw e5;
            }
        }
    }
}
