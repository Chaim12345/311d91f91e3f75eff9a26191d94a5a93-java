package org.bouncycastle.tls;

import java.io.IOException;
import org.bouncycastle.tls.crypto.TlsCrypto;
import org.bouncycastle.tls.crypto.TlsHMAC;
import org.bouncycastle.tls.crypto.TlsMAC;
import org.bouncycastle.tls.crypto.TlsMACOutputStream;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class DTLSVerifier {
    private final TlsMAC cookieMAC;
    private final TlsMACOutputStream cookieMACOutputStream;

    public DTLSVerifier(TlsCrypto tlsCrypto) {
        TlsMAC createCookieMAC = createCookieMAC(tlsCrypto);
        this.cookieMAC = createCookieMAC;
        this.cookieMACOutputStream = new TlsMACOutputStream(createCookieMAC);
    }

    private static TlsMAC createCookieMAC(TlsCrypto tlsCrypto) {
        TlsHMAC createHMAC = tlsCrypto.createHMAC(3);
        int macLength = createHMAC.getMacLength();
        byte[] bArr = new byte[macLength];
        tlsCrypto.getSecureRandom().nextBytes(bArr);
        createHMAC.setKey(bArr, 0, macLength);
        return createHMAC;
    }

    public synchronized DTLSRequest verifyRequest(byte[] bArr, byte[] bArr2, int i2, int i3, DatagramSender datagramSender) {
        TlsMAC tlsMAC;
        boolean z = false;
        try {
            this.cookieMAC.update(bArr, 0, bArr.length);
            DTLSRequest f2 = DTLSReliableHandshake.f(bArr2, i2, i3, this.cookieMACOutputStream);
            if (f2 != null) {
                byte[] calculateMAC = this.cookieMAC.calculateMAC();
                try {
                    if (Arrays.constantTimeAreEqual(calculateMAC, f2.a().getCookie())) {
                        return f2;
                    }
                    DTLSReliableHandshake.j(datagramSender, f2.d(), calculateMAC);
                } catch (IOException unused) {
                    if (z) {
                        tlsMAC = this.cookieMAC;
                        tlsMAC.reset();
                    }
                    return null;
                } catch (Throwable th) {
                    th = th;
                    if (z) {
                        this.cookieMAC.reset();
                    }
                    throw th;
                }
            } else {
                z = true;
            }
        } catch (IOException unused2) {
            z = true;
        } catch (Throwable th2) {
            th = th2;
            z = true;
        }
        if (z) {
            tlsMAC = this.cookieMAC;
            tlsMAC.reset();
        }
        return null;
    }
}
