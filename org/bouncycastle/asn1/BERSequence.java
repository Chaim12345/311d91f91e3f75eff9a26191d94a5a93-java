package org.bouncycastle.asn1;
/* loaded from: classes3.dex */
public class BERSequence extends ASN1Sequence {
    public BERSequence() {
    }

    public BERSequence(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable);
    }

    public BERSequence(ASN1EncodableVector aSN1EncodableVector) {
        super(aSN1EncodableVector);
    }

    public BERSequence(ASN1Encodable[] aSN1EncodableArr) {
        super(aSN1EncodableArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void c(ASN1OutputStream aSN1OutputStream, boolean z) {
        aSN1OutputStream.p(z, 48, this.f12709a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int e(boolean z) {
        int i2 = z ? 4 : 3;
        int length = this.f12709a.length;
        for (int i3 = 0; i3 < length; i3++) {
            i2 += this.f12709a[i3].toASN1Primitive().e(true);
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Sequence
    public ASN1BitString j() {
        return new BERBitString(h());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Sequence
    public ASN1External k() {
        return ((ASN1Sequence) g()).k();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Sequence
    public ASN1OctetString l() {
        return new BEROctetString(i());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Sequence
    public ASN1Set m() {
        return new BERSet(false, n());
    }
}
