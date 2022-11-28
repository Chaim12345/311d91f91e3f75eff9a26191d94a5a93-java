package org.bouncycastle.cms;

import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes3.dex */
public class CMSTypedStream {
    private static final int BUF_SIZ = 32768;
    private final ASN1ObjectIdentifier _oid;

    /* renamed from: a  reason: collision with root package name */
    protected InputStream f13149a;

    /* loaded from: classes3.dex */
    private static class FullReaderStream extends FilterInputStream {
        FullReaderStream(InputStream inputStream) {
            super(inputStream);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) {
            if (i3 == 0) {
                return 0;
            }
            int readFully = Streams.readFully(((FilterInputStream) this).in, bArr, i2, i3);
            if (readFully > 0) {
                return readFully;
            }
            return -1;
        }
    }

    public CMSTypedStream(InputStream inputStream) {
        this(PKCSObjectIdentifiers.data.getId(), inputStream, 32768);
    }

    public CMSTypedStream(String str, InputStream inputStream) {
        this(new ASN1ObjectIdentifier(str), inputStream, 32768);
    }

    public CMSTypedStream(String str, InputStream inputStream, int i2) {
        this(new ASN1ObjectIdentifier(str), inputStream, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CMSTypedStream(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this._oid = aSN1ObjectIdentifier;
    }

    public CMSTypedStream(ASN1ObjectIdentifier aSN1ObjectIdentifier, InputStream inputStream) {
        this(aSN1ObjectIdentifier, inputStream, 32768);
    }

    public CMSTypedStream(ASN1ObjectIdentifier aSN1ObjectIdentifier, InputStream inputStream, int i2) {
        this._oid = aSN1ObjectIdentifier;
        this.f13149a = new FullReaderStream(new BufferedInputStream(inputStream, i2));
    }

    public void drain() {
        Streams.drain(this.f13149a);
        this.f13149a.close();
    }

    public InputStream getContentStream() {
        return this.f13149a;
    }

    public ASN1ObjectIdentifier getContentType() {
        return this._oid;
    }
}
