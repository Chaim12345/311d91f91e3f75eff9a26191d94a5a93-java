package org.bouncycastle.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes4.dex */
public class CertificateStatusRequestItemV2 {

    /* renamed from: a  reason: collision with root package name */
    protected short f14708a;

    /* renamed from: b  reason: collision with root package name */
    protected Object f14709b;

    public CertificateStatusRequestItemV2(short s2, Object obj) {
        if (!a(s2, obj)) {
            throw new IllegalArgumentException("'request' is not an instance of the correct type");
        }
        this.f14708a = s2;
        this.f14709b = obj;
    }

    protected static boolean a(short s2, Object obj) {
        if (s2 == 1 || s2 == 2) {
            return obj instanceof OCSPStatusRequest;
        }
        throw new IllegalArgumentException("'statusType' is an unsupported CertificateStatusType");
    }

    public static CertificateStatusRequestItemV2 parse(InputStream inputStream) {
        short readUint8 = TlsUtils.readUint8(inputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(TlsUtils.readOpaque16(inputStream));
        if (readUint8 == 1 || readUint8 == 2) {
            OCSPStatusRequest parse = OCSPStatusRequest.parse(byteArrayInputStream);
            TlsProtocol.b(byteArrayInputStream);
            return new CertificateStatusRequestItemV2(readUint8, parse);
        }
        throw new TlsFatalAlert((short) 50);
    }

    public void encode(OutputStream outputStream) {
        TlsUtils.writeUint8(this.f14708a, outputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        short s2 = this.f14708a;
        if (s2 != 1 && s2 != 2) {
            throw new TlsFatalAlert((short) 80);
        }
        ((OCSPStatusRequest) this.f14709b).encode(byteArrayOutputStream);
        TlsUtils.writeOpaque16(byteArrayOutputStream.toByteArray(), outputStream);
    }

    public OCSPStatusRequest getOCSPStatusRequest() {
        Object obj = this.f14709b;
        if (obj instanceof OCSPStatusRequest) {
            return (OCSPStatusRequest) obj;
        }
        throw new IllegalStateException("'request' is not an OCSPStatusRequest");
    }

    public Object getRequest() {
        return this.f14709b;
    }

    public short getStatusType() {
        return this.f14708a;
    }
}
