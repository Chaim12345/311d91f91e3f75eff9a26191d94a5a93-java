package org.bouncycastle.asn1;

import java.io.OutputStream;
/* loaded from: classes3.dex */
public class BERSequenceGenerator extends BERGenerator {
    public BERSequenceGenerator(OutputStream outputStream) {
        super(outputStream);
        b(48);
    }

    public BERSequenceGenerator(OutputStream outputStream, int i2, boolean z) {
        super(outputStream, i2, z);
        b(48);
    }

    public void addObject(ASN1Encodable aSN1Encodable) {
        aSN1Encodable.toASN1Primitive().encodeTo(this.f12691a);
    }

    public void addObject(ASN1Primitive aSN1Primitive) {
        aSN1Primitive.encodeTo(this.f12691a);
    }

    public void close() {
        a();
    }
}
