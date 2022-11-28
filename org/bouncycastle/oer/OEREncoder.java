package org.bouncycastle.oer;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.oer.OERDefinition;
/* loaded from: classes4.dex */
public class OEREncoder {
    public static byte[] toByteArray(ASN1Encodable aSN1Encodable, OERDefinition.Element element) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new OEROutputStream(byteArrayOutputStream).write(aSN1Encodable, element);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e2) {
            throw new IllegalStateException(e2.getMessage(), e2);
        }
    }
}
