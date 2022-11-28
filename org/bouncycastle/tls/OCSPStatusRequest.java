package org.bouncycastle.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ocsp.ResponderID;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes4.dex */
public class OCSPStatusRequest {

    /* renamed from: a  reason: collision with root package name */
    protected Vector f14768a;

    /* renamed from: b  reason: collision with root package name */
    protected Extensions f14769b;

    public OCSPStatusRequest(Vector vector, Extensions extensions) {
        this.f14768a = vector;
        this.f14769b = extensions;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static OCSPStatusRequest parse(InputStream inputStream) {
        byte[] readOpaque16;
        Vector vector = new Vector();
        byte[] readOpaque162 = TlsUtils.readOpaque16(inputStream);
        if (readOpaque162.length <= 0) {
            Extensions extensions = null;
            readOpaque16 = TlsUtils.readOpaque16(inputStream);
            if (readOpaque16.length > 0) {
            }
            return new OCSPStatusRequest(vector, extensions);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(readOpaque162);
        do {
            byte[] readOpaque163 = TlsUtils.readOpaque16(byteArrayInputStream, 1);
            ResponderID responderID = ResponderID.getInstance(TlsUtils.readASN1Object(readOpaque163));
            TlsUtils.requireDEREncoding(responderID, readOpaque163);
            vector.addElement(responderID);
        } while (byteArrayInputStream.available() > 0);
        Extensions extensions2 = null;
        readOpaque16 = TlsUtils.readOpaque16(inputStream);
        if (readOpaque16.length > 0) {
            extensions2 = Extensions.getInstance(TlsUtils.readASN1Object(readOpaque16));
            TlsUtils.requireDEREncoding(extensions2, readOpaque16);
        }
        return new OCSPStatusRequest(vector, extensions2);
    }

    public void encode(OutputStream outputStream) {
        Vector vector = this.f14768a;
        if (vector == null || vector.isEmpty()) {
            TlsUtils.writeUint16(0, outputStream);
        } else {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (int i2 = 0; i2 < this.f14768a.size(); i2++) {
                TlsUtils.writeOpaque16(((ResponderID) this.f14768a.elementAt(i2)).getEncoded(ASN1Encoding.DER), byteArrayOutputStream);
            }
            TlsUtils.checkUint16(byteArrayOutputStream.size());
            TlsUtils.writeUint16(byteArrayOutputStream.size(), outputStream);
            Streams.writeBufTo(byteArrayOutputStream, outputStream);
        }
        Extensions extensions = this.f14769b;
        if (extensions == null) {
            TlsUtils.writeUint16(0, outputStream);
            return;
        }
        byte[] encoded = extensions.getEncoded(ASN1Encoding.DER);
        TlsUtils.checkUint16(encoded.length);
        TlsUtils.writeUint16(encoded.length, outputStream);
        outputStream.write(encoded);
    }

    public Extensions getRequestExtensions() {
        return this.f14769b;
    }

    public Vector getResponderIDList() {
        return this.f14768a;
    }
}
