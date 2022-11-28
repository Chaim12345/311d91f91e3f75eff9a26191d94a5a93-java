package org.bouncycastle.tls;

import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes4.dex */
public class CertificateStatusRequest {

    /* renamed from: a  reason: collision with root package name */
    protected short f14706a;

    /* renamed from: b  reason: collision with root package name */
    protected Object f14707b;

    public CertificateStatusRequest(short s2, Object obj) {
        if (!a(s2, obj)) {
            throw new IllegalArgumentException("'request' is not an instance of the correct type");
        }
        this.f14706a = s2;
        this.f14707b = obj;
    }

    protected static boolean a(short s2, Object obj) {
        if (s2 == 1) {
            return obj instanceof OCSPStatusRequest;
        }
        throw new IllegalArgumentException("'statusType' is an unsupported CertificateStatusType");
    }

    public static CertificateStatusRequest parse(InputStream inputStream) {
        short readUint8 = TlsUtils.readUint8(inputStream);
        if (readUint8 == 1) {
            return new CertificateStatusRequest(readUint8, OCSPStatusRequest.parse(inputStream));
        }
        throw new TlsFatalAlert((short) 50);
    }

    public void encode(OutputStream outputStream) {
        TlsUtils.writeUint8(this.f14706a, outputStream);
        if (this.f14706a != 1) {
            throw new TlsFatalAlert((short) 80);
        }
        ((OCSPStatusRequest) this.f14707b).encode(outputStream);
    }

    public OCSPStatusRequest getOCSPStatusRequest() {
        if (a((short) 1, this.f14707b)) {
            return (OCSPStatusRequest) this.f14707b;
        }
        throw new IllegalStateException("'request' is not an OCSPStatusRequest");
    }

    public Object getRequest() {
        return this.f14707b;
    }

    public short getStatusType() {
        return this.f14706a;
    }
}
