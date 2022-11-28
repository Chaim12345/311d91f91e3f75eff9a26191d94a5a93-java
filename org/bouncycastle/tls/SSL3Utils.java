package org.bouncycastle.tls;

import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.tls.crypto.TlsHash;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.io.Streams;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class SSL3Utils {
    private static final byte[] SSL_CLIENT = {67, 76, 78, 84};
    private static final byte[] SSL_SERVER = {83, 82, 86, 82};
    private static final byte IPAD_BYTE = 54;
    private static final byte[] IPAD = genPad(IPAD_BYTE, 48);
    private static final byte OPAD_BYTE = 92;
    private static final byte[] OPAD = genPad(OPAD_BYTE, 48);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] a(TlsHandshakeHash tlsHandshakeHash, boolean z) {
        TlsHash forkPRFHash = tlsHandshakeHash.forkPRFHash();
        byte[] bArr = z ? SSL_SERVER : SSL_CLIENT;
        forkPRFHash.update(bArr, 0, bArr.length);
        return forkPRFHash.calculateHash();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(TlsContext tlsContext, TlsHash tlsHash, TlsHash tlsHash2) {
        byte[] extract = tlsContext.getCrypto().adoptSecret(tlsContext.getSecurityParametersHandshake().getMasterSecret()).extract();
        completeHash(extract, tlsHash, 48);
        completeHash(extract, tlsHash2, 40);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] c(InputStream inputStream) {
        return Streams.readAll(inputStream);
    }

    private static void completeHash(byte[] bArr, TlsHash tlsHash, int i2) {
        tlsHash.update(bArr, 0, bArr.length);
        tlsHash.update(IPAD, 0, i2);
        byte[] calculateHash = tlsHash.calculateHash();
        tlsHash.update(bArr, 0, bArr.length);
        tlsHash.update(OPAD, 0, i2);
        tlsHash.update(calculateHash, 0, calculateHash.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void d(byte[] bArr, OutputStream outputStream) {
        outputStream.write(bArr);
    }

    private static byte[] genPad(byte b2, int i2) {
        byte[] bArr = new byte[i2];
        Arrays.fill(bArr, b2);
        return bArr;
    }
}
