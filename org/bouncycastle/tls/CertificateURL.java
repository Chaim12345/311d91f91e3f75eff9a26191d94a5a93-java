package org.bouncycastle.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
/* loaded from: classes4.dex */
public class CertificateURL {

    /* renamed from: a  reason: collision with root package name */
    protected short f14710a;

    /* renamed from: b  reason: collision with root package name */
    protected Vector f14711b;

    /* loaded from: classes4.dex */
    class ListBuffer16 extends ByteArrayOutputStream {
        ListBuffer16(CertificateURL certificateURL) {
            TlsUtils.writeUint16(0, this);
        }

        void a(OutputStream outputStream) {
            int i2 = ((ByteArrayOutputStream) this).count - 2;
            TlsUtils.checkUint16(i2);
            TlsUtils.writeUint16(i2, ((ByteArrayOutputStream) this).buf, 0);
            outputStream.write(((ByteArrayOutputStream) this).buf, 0, ((ByteArrayOutputStream) this).count);
            ((ByteArrayOutputStream) this).buf = null;
        }
    }

    public CertificateURL(short s2, Vector vector) {
        if (!CertChainType.isValid(s2)) {
            throw new IllegalArgumentException("'type' is not a valid CertChainType value");
        }
        if (vector == null || vector.isEmpty()) {
            throw new IllegalArgumentException("'urlAndHashList' must have length > 0");
        }
        if (s2 != 1 || vector.size() == 1) {
            this.f14710a = s2;
            this.f14711b = vector;
            return;
        }
        throw new IllegalArgumentException("'urlAndHashList' must contain exactly one entry when type is " + CertChainType.getText(s2));
    }

    public static CertificateURL parse(TlsContext tlsContext, InputStream inputStream) {
        short readUint8 = TlsUtils.readUint8(inputStream);
        if (CertChainType.isValid(readUint8)) {
            int readUint16 = TlsUtils.readUint16(inputStream);
            if (readUint16 >= 1) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(TlsUtils.readFully(readUint16, inputStream));
                Vector vector = new Vector();
                while (byteArrayInputStream.available() > 0) {
                    vector.addElement(URLAndHash.parse(tlsContext, byteArrayInputStream));
                }
                if (readUint8 != 1 || vector.size() == 1) {
                    return new CertificateURL(readUint8, vector);
                }
                throw new TlsFatalAlert((short) 50);
            }
            throw new TlsFatalAlert((short) 50);
        }
        throw new TlsFatalAlert((short) 50);
    }

    public void encode(OutputStream outputStream) {
        TlsUtils.writeUint8(this.f14710a, outputStream);
        ListBuffer16 listBuffer16 = new ListBuffer16(this);
        for (int i2 = 0; i2 < this.f14711b.size(); i2++) {
            ((URLAndHash) this.f14711b.elementAt(i2)).encode(listBuffer16);
        }
        listBuffer16.a(outputStream);
    }

    public short getType() {
        return this.f14710a;
    }

    public Vector getURLAndHashList() {
        return this.f14711b;
    }
}
