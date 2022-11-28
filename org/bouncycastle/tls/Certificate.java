package org.bouncycastle.tls;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.TlsCrypto;
/* loaded from: classes4.dex */
public class Certificate {
    private static final TlsCertificate[] EMPTY_CERTS;
    private static final CertificateEntry[] EMPTY_CERT_ENTRIES;
    public static final Certificate EMPTY_CHAIN;
    public static final Certificate EMPTY_CHAIN_TLS13;

    /* renamed from: a  reason: collision with root package name */
    protected final byte[] f14695a;

    /* renamed from: b  reason: collision with root package name */
    protected final CertificateEntry[] f14696b;

    /* loaded from: classes4.dex */
    public static class ParseOptions {
        private int maxChainLength = Integer.MAX_VALUE;

        public int getMaxChainLength() {
            return this.maxChainLength;
        }

        public ParseOptions setMaxChainLength(int i2) {
            this.maxChainLength = i2;
            return this;
        }
    }

    static {
        TlsCertificate[] tlsCertificateArr = new TlsCertificate[0];
        EMPTY_CERTS = tlsCertificateArr;
        CertificateEntry[] certificateEntryArr = new CertificateEntry[0];
        EMPTY_CERT_ENTRIES = certificateEntryArr;
        EMPTY_CHAIN = new Certificate(tlsCertificateArr);
        EMPTY_CHAIN_TLS13 = new Certificate(TlsUtils.EMPTY_BYTES, certificateEntryArr);
    }

    public Certificate(byte[] bArr, CertificateEntry[] certificateEntryArr) {
        if (bArr != null && !TlsUtils.isValidUint8(bArr.length)) {
            throw new IllegalArgumentException("'certificateRequestContext' cannot be longer than 255");
        }
        if (TlsUtils.isNullOrContainsNull(certificateEntryArr)) {
            throw new NullPointerException("'certificateEntryList' cannot be null or contain any nulls");
        }
        this.f14695a = TlsUtils.clone(bArr);
        this.f14696b = certificateEntryArr;
    }

    public Certificate(TlsCertificate[] tlsCertificateArr) {
        this(null, convert(tlsCertificateArr));
    }

    protected static void a(TlsContext tlsContext, TlsCertificate tlsCertificate, byte[] bArr, OutputStream outputStream) {
        byte[] g2 = TlsUtils.g(tlsContext, tlsCertificate, bArr);
        if (g2 == null || g2.length <= 0) {
            return;
        }
        outputStream.write(g2);
    }

    private static CertificateEntry[] convert(TlsCertificate[] tlsCertificateArr) {
        if (TlsUtils.isNullOrContainsNull(tlsCertificateArr)) {
            throw new NullPointerException("'certificateList' cannot be null or contain any nulls");
        }
        int length = tlsCertificateArr.length;
        CertificateEntry[] certificateEntryArr = new CertificateEntry[length];
        for (int i2 = 0; i2 < length; i2++) {
            certificateEntryArr[i2] = new CertificateEntry(tlsCertificateArr[i2], null);
        }
        return certificateEntryArr;
    }

    public static Certificate parse(ParseOptions parseOptions, TlsContext tlsContext, InputStream inputStream, OutputStream outputStream) {
        boolean isTLSv13 = TlsUtils.isTLSv13(tlsContext.getSecurityParameters().getNegotiatedVersion());
        byte[] readOpaque8 = isTLSv13 ? TlsUtils.readOpaque8(inputStream) : null;
        int readUint24 = TlsUtils.readUint24(inputStream);
        if (readUint24 == 0) {
            return !isTLSv13 ? EMPTY_CHAIN : readOpaque8.length < 1 ? EMPTY_CHAIN_TLS13 : new Certificate(readOpaque8, EMPTY_CERT_ENTRIES);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(TlsUtils.readFully(readUint24, inputStream));
        TlsCrypto crypto = tlsContext.getCrypto();
        int max = Math.max(1, parseOptions.getMaxChainLength());
        Vector vector = new Vector();
        while (byteArrayInputStream.available() > 0) {
            if (vector.size() >= max) {
                throw new TlsFatalAlert((short) 80, "Certificate chain longer than maximum (" + max + ")");
            }
            byte[] readOpaque24 = TlsUtils.readOpaque24(byteArrayInputStream, 1);
            TlsCertificate createCertificate = crypto.createCertificate(readOpaque24);
            if (vector.isEmpty() && outputStream != null) {
                a(tlsContext, createCertificate, readOpaque24, outputStream);
            }
            vector.addElement(new CertificateEntry(createCertificate, isTLSv13 ? TlsProtocol.K(11, TlsUtils.readOpaque16(byteArrayInputStream)) : null));
        }
        CertificateEntry[] certificateEntryArr = new CertificateEntry[vector.size()];
        for (int i2 = 0; i2 < vector.size(); i2++) {
            certificateEntryArr[i2] = (CertificateEntry) vector.elementAt(i2);
        }
        return new Certificate(readOpaque8, certificateEntryArr);
    }

    public static Certificate parse(TlsContext tlsContext, InputStream inputStream, OutputStream outputStream) {
        return parse(new ParseOptions(), tlsContext, inputStream, outputStream);
    }

    protected CertificateEntry[] b() {
        CertificateEntry[] certificateEntryArr = this.f14696b;
        int length = certificateEntryArr.length;
        if (length == 0) {
            return EMPTY_CERT_ENTRIES;
        }
        CertificateEntry[] certificateEntryArr2 = new CertificateEntry[length];
        System.arraycopy(certificateEntryArr, 0, certificateEntryArr2, 0, length);
        return certificateEntryArr2;
    }

    protected TlsCertificate[] c() {
        int length = this.f14696b.length;
        if (length == 0) {
            return EMPTY_CERTS;
        }
        TlsCertificate[] tlsCertificateArr = new TlsCertificate[length];
        for (int i2 = 0; i2 < length; i2++) {
            tlsCertificateArr[i2] = this.f14696b[i2].getCertificate();
        }
        return tlsCertificateArr;
    }

    public void encode(TlsContext tlsContext, OutputStream outputStream, OutputStream outputStream2) {
        byte[] f0;
        boolean isTLSv13 = TlsUtils.isTLSv13(tlsContext);
        byte[] bArr = this.f14695a;
        if ((bArr != null) != isTLSv13) {
            throw new IllegalStateException();
        }
        if (isTLSv13) {
            TlsUtils.writeOpaque8(bArr, outputStream);
        }
        int length = this.f14696b.length;
        Vector vector = new Vector(length);
        Vector vector2 = isTLSv13 ? new Vector(length) : null;
        long j2 = 0;
        for (int i2 = 0; i2 < length; i2++) {
            CertificateEntry certificateEntry = this.f14696b[i2];
            TlsCertificate certificate = certificateEntry.getCertificate();
            byte[] encoded = certificate.getEncoded();
            if (i2 == 0 && outputStream2 != null) {
                a(tlsContext, certificate, encoded, outputStream2);
            }
            vector.addElement(encoded);
            j2 = j2 + encoded.length + 3;
            if (isTLSv13) {
                Hashtable extensions = certificateEntry.getExtensions();
                vector2.addElement(extensions == null ? TlsUtils.EMPTY_BYTES : TlsProtocol.f0(extensions));
                j2 = j2 + f0.length + 2;
            }
        }
        TlsUtils.checkUint24(j2);
        TlsUtils.writeUint24((int) j2, outputStream);
        for (int i3 = 0; i3 < length; i3++) {
            TlsUtils.writeOpaque24((byte[]) vector.elementAt(i3), outputStream);
            if (isTLSv13) {
                TlsUtils.writeOpaque16((byte[]) vector2.elementAt(i3), outputStream);
            }
        }
    }

    public TlsCertificate getCertificateAt(int i2) {
        return this.f14696b[i2].getCertificate();
    }

    public CertificateEntry getCertificateEntryAt(int i2) {
        return this.f14696b[i2];
    }

    public CertificateEntry[] getCertificateEntryList() {
        return b();
    }

    public TlsCertificate[] getCertificateList() {
        return c();
    }

    public byte[] getCertificateRequestContext() {
        return TlsUtils.clone(this.f14695a);
    }

    public short getCertificateType() {
        return (short) 0;
    }

    public int getLength() {
        return this.f14696b.length;
    }

    public boolean isEmpty() {
        return this.f14696b.length == 0;
    }
}
