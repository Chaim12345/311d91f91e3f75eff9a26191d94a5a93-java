package org.bouncycastle.cert.cmp;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1Object;
/* loaded from: classes3.dex */
class CMPUtil {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(ASN1Object aSN1Object, OutputStream outputStream) {
        try {
            aSN1Object.encodeTo(outputStream, ASN1Encoding.DER);
            outputStream.close();
        } catch (IOException e2) {
            throw new CMPRuntimeException("unable to DER encode object: " + e2.getMessage(), e2);
        }
    }
}
