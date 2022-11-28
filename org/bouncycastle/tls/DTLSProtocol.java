package org.bouncycastle.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public abstract class DTLSProtocol {
    /* JADX INFO: Access modifiers changed from: protected */
    public static void a(DTLSRecordLayer dTLSRecordLayer, short s2) {
        if (s2 >= 0) {
            if (!MaxFragmentLength.isValid(s2)) {
                throw new TlsFatalAlert((short) 80);
            }
            dTLSRecordLayer.m(1 << (s2 + 8));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static short b(boolean z, Hashtable hashtable, Hashtable hashtable2, short s2) {
        short maxFragmentLengthExtension = TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable2);
        if (maxFragmentLengthExtension < 0 || (MaxFragmentLength.isValid(maxFragmentLengthExtension) && (z || maxFragmentLengthExtension == TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable)))) {
            return maxFragmentLengthExtension;
        }
        throw new TlsFatalAlert(s2);
    }

    protected static byte[] c(TlsContext tlsContext, Certificate certificate, OutputStream outputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        certificate.encode(tlsContext, byteArrayOutputStream, outputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static byte[] d(Vector vector) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        TlsProtocol.k0(byteArrayOutputStream, vector);
        return byteArrayOutputStream.toByteArray();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void f(TlsContext tlsContext, DTLSReliableHandshake dTLSReliableHandshake, Certificate certificate, OutputStream outputStream) {
        SecurityParameters securityParametersHandshake = tlsContext.getSecurityParametersHandshake();
        if (securityParametersHandshake.getLocalCertificate() != null) {
            throw new TlsFatalAlert((short) 80);
        }
        if (certificate == null) {
            certificate = Certificate.EMPTY_CHAIN;
        }
        dTLSReliableHandshake.k((short) 11, c(tlsContext, certificate, outputStream));
        securityParametersHandshake.Q = certificate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int g(int i2, short s2) {
        int encryptionAlgorithm = TlsUtils.getEncryptionAlgorithm(i2);
        if (encryptionAlgorithm == -1 || encryptionAlgorithm == 1 || encryptionAlgorithm == 2) {
            throw new TlsFatalAlert(s2);
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e(byte[] bArr, byte[] bArr2) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        byte[] readFully = TlsUtils.readFully(bArr2.length, byteArrayInputStream);
        TlsProtocol.b(byteArrayInputStream);
        if (!Arrays.constantTimeAreEqual(bArr2, readFully)) {
            throw new TlsFatalAlert((short) 40);
        }
    }
}
