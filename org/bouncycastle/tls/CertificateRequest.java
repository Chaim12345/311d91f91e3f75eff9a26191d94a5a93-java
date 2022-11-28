package org.bouncycastle.tls;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class CertificateRequest {

    /* renamed from: a  reason: collision with root package name */
    protected final byte[] f14699a;

    /* renamed from: b  reason: collision with root package name */
    protected final short[] f14700b;

    /* renamed from: c  reason: collision with root package name */
    protected final Vector f14701c;

    /* renamed from: d  reason: collision with root package name */
    protected final Vector f14702d;

    /* renamed from: e  reason: collision with root package name */
    protected final Vector f14703e;

    public CertificateRequest(byte[] bArr, Vector vector, Vector vector2, Vector vector3) {
        this(bArr, null, checkSupportedSignatureAlgorithms(vector, (short) 80), vector2, vector3);
    }

    private CertificateRequest(byte[] bArr, short[] sArr, Vector vector, Vector vector2, Vector vector3) {
        if (bArr != null && !TlsUtils.isValidUint8(bArr.length)) {
            throw new IllegalArgumentException("'certificateRequestContext' cannot be longer than 255");
        }
        if (sArr != null && (sArr.length < 1 || !TlsUtils.isValidUint8(sArr.length))) {
            throw new IllegalArgumentException("'certificateTypes' should have length from 1 to 255");
        }
        this.f14699a = TlsUtils.clone(bArr);
        this.f14700b = sArr;
        this.f14701c = vector;
        this.f14702d = vector2;
        this.f14703e = vector3;
    }

    public CertificateRequest(short[] sArr, Vector vector, Vector vector2) {
        this(null, sArr, vector, null, vector2);
    }

    private static Vector checkSupportedSignatureAlgorithms(Vector vector, short s2) {
        if (vector != null) {
            return vector;
        }
        throw new TlsFatalAlert(s2, "'signature_algorithms' is required");
    }

    public static CertificateRequest parse(TlsContext tlsContext, InputStream inputStream) {
        ProtocolVersion serverVersion = tlsContext.getServerVersion();
        if (TlsUtils.isTLSv13(serverVersion)) {
            byte[] readOpaque8 = TlsUtils.readOpaque8(inputStream);
            Hashtable K = TlsProtocol.K(13, TlsUtils.readOpaque16(inputStream));
            return new CertificateRequest(readOpaque8, checkSupportedSignatureAlgorithms(TlsExtensionsUtils.getSignatureAlgorithmsExtension(K), AlertDescription.missing_extension), TlsExtensionsUtils.getSignatureAlgorithmsCertExtension(K), TlsExtensionsUtils.getCertificateAuthoritiesExtension(K));
        }
        boolean isTLSv12 = TlsUtils.isTLSv12(serverVersion);
        short[] readUint8ArrayWithUint8Length = TlsUtils.readUint8ArrayWithUint8Length(inputStream, 1);
        Vector vector = null;
        Vector parseSupportedSignatureAlgorithms = isTLSv12 ? TlsUtils.parseSupportedSignatureAlgorithms(inputStream) : null;
        byte[] readOpaque16 = TlsUtils.readOpaque16(inputStream);
        if (readOpaque16.length > 0) {
            Vector vector2 = new Vector();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(readOpaque16);
            do {
                byte[] readOpaque162 = TlsUtils.readOpaque16(byteArrayInputStream, 1);
                X500Name x500Name = X500Name.getInstance(TlsUtils.readASN1Object(readOpaque162));
                TlsUtils.requireDEREncoding(x500Name, readOpaque162);
                vector2.addElement(x500Name);
            } while (byteArrayInputStream.available() > 0);
            vector = vector2;
        }
        return new CertificateRequest(readUint8ArrayWithUint8Length, parseSupportedSignatureAlgorithms, vector);
    }

    public void encode(TlsContext tlsContext, OutputStream outputStream) {
        ProtocolVersion serverVersion = tlsContext.getServerVersion();
        boolean isTLSv12 = TlsUtils.isTLSv12(serverVersion);
        boolean isTLSv13 = TlsUtils.isTLSv13(serverVersion);
        byte[] bArr = this.f14699a;
        if (isTLSv13 == (bArr != null)) {
            short[] sArr = this.f14700b;
            if (isTLSv13 == (sArr == null)) {
                if (isTLSv12 == (this.f14701c != null) && (isTLSv13 || this.f14702d == null)) {
                    if (isTLSv13) {
                        TlsUtils.writeOpaque8(bArr, outputStream);
                        Hashtable hashtable = new Hashtable();
                        TlsExtensionsUtils.addSignatureAlgorithmsExtension(hashtable, this.f14701c);
                        Vector vector = this.f14702d;
                        if (vector != null) {
                            TlsExtensionsUtils.addSignatureAlgorithmsCertExtension(hashtable, vector);
                        }
                        Vector vector2 = this.f14703e;
                        if (vector2 != null) {
                            TlsExtensionsUtils.addCertificateAuthoritiesExtension(hashtable, vector2);
                        }
                        TlsUtils.writeOpaque16(TlsProtocol.f0(hashtable), outputStream);
                        return;
                    }
                    TlsUtils.writeUint8ArrayWithUint8Length(sArr, outputStream);
                    if (isTLSv12) {
                        TlsUtils.encodeSupportedSignatureAlgorithms(this.f14701c, outputStream);
                    }
                    Vector vector3 = this.f14703e;
                    if (vector3 == null || vector3.isEmpty()) {
                        TlsUtils.writeUint16(0, outputStream);
                        return;
                    }
                    Vector vector4 = new Vector(this.f14703e.size());
                    int i2 = 0;
                    for (int i3 = 0; i3 < this.f14703e.size(); i3++) {
                        byte[] encoded = ((X500Name) this.f14703e.elementAt(i3)).getEncoded(ASN1Encoding.DER);
                        vector4.addElement(encoded);
                        i2 += encoded.length + 2;
                    }
                    TlsUtils.checkUint16(i2);
                    TlsUtils.writeUint16(i2, outputStream);
                    for (int i4 = 0; i4 < vector4.size(); i4++) {
                        TlsUtils.writeOpaque16((byte[]) vector4.elementAt(i4), outputStream);
                    }
                    return;
                }
            }
        }
        throw new IllegalStateException();
    }

    public Vector getCertificateAuthorities() {
        return this.f14703e;
    }

    public byte[] getCertificateRequestContext() {
        return TlsUtils.clone(this.f14699a);
    }

    public short[] getCertificateTypes() {
        return this.f14700b;
    }

    public Vector getSupportedSignatureAlgorithms() {
        return this.f14701c;
    }

    public Vector getSupportedSignatureAlgorithmsCert() {
        return this.f14702d;
    }

    public boolean hasCertificateRequestContext(byte[] bArr) {
        return Arrays.areEqual(this.f14699a, bArr);
    }
}
