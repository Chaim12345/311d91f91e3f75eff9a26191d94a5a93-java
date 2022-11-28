package org.bouncycastle.asn1;

import java.io.OutputStream;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class DLOutputStream extends ASN1OutputStream {
    /* JADX INFO: Access modifiers changed from: package-private */
    public DLOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    @Override // org.bouncycastle.asn1.ASN1OutputStream
    DLOutputStream c() {
        return this;
    }

    @Override // org.bouncycastle.asn1.ASN1OutputStream
    void j(ASN1Encodable[] aSN1EncodableArr) {
        for (ASN1Encodable aSN1Encodable : aSN1EncodableArr) {
            aSN1Encodable.toASN1Primitive().g().c(this, true);
        }
    }

    @Override // org.bouncycastle.asn1.ASN1OutputStream
    void s(ASN1Primitive aSN1Primitive, boolean z) {
        aSN1Primitive.g().c(this, z);
    }

    @Override // org.bouncycastle.asn1.ASN1OutputStream
    void t(ASN1Primitive[] aSN1PrimitiveArr) {
        for (ASN1Primitive aSN1Primitive : aSN1PrimitiveArr) {
            aSN1Primitive.g().c(this, true);
        }
    }
}
