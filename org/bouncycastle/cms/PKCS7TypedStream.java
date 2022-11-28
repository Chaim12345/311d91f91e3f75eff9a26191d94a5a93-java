package org.bouncycastle.cms;

import com.google.common.base.Ascii;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
/* loaded from: classes3.dex */
public class PKCS7TypedStream extends CMSTypedStream {
    private final ASN1Encodable content;

    public PKCS7TypedStream(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        super(aSN1ObjectIdentifier);
        this.content = aSN1Encodable;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private InputStream getContentStream(ASN1Encodable aSN1Encodable) {
        byte b2;
        int i2;
        byte[] encoded = aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER);
        int i3 = 1;
        if ((encoded[0] & Ascii.US) != 31) {
            int i4 = i3 + 1;
            b2 = encoded[i3];
            if ((b2 & 128) != 0) {
            }
            return new ByteArrayInputStream(encoded, i4, encoded.length - i4);
        }
        do {
            i2 = encoded[i3] & 128;
            i3++;
        } while (i2 != 0);
        int i42 = i3 + 1;
        b2 = encoded[i3];
        if ((b2 & 128) != 0) {
            i42 += b2 & Byte.MAX_VALUE;
        }
        return new ByteArrayInputStream(encoded, i42, encoded.length - i42);
    }

    @Override // org.bouncycastle.cms.CMSTypedStream
    public void drain() {
        this.content.toASN1Primitive();
    }

    public ASN1Encodable getContent() {
        return this.content;
    }

    @Override // org.bouncycastle.cms.CMSTypedStream
    public InputStream getContentStream() {
        try {
            return getContentStream(this.content);
        } catch (IOException e2) {
            throw new CMSRuntimeException("unable to convert content to stream: " + e2.getMessage(), e2);
        }
    }
}
