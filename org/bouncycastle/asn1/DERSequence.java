package org.bouncycastle.asn1;
/* loaded from: classes3.dex */
public class DERSequence extends ASN1Sequence {
    private int contentsLength;

    public DERSequence() {
        this.contentsLength = -1;
    }

    public DERSequence(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable);
        this.contentsLength = -1;
    }

    public DERSequence(ASN1EncodableVector aSN1EncodableVector) {
        super(aSN1EncodableVector);
        this.contentsLength = -1;
    }

    public DERSequence(ASN1Encodable[] aSN1EncodableArr) {
        super(aSN1EncodableArr);
        this.contentsLength = -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DERSequence(ASN1Encodable[] aSN1EncodableArr, boolean z) {
        super(aSN1EncodableArr, z);
        this.contentsLength = -1;
    }

    public static DERSequence convert(ASN1Sequence aSN1Sequence) {
        return (DERSequence) aSN1Sequence.f();
    }

    private int getContentsLength() {
        if (this.contentsLength < 0) {
            int length = this.f12709a.length;
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                i2 += this.f12709a[i3].toASN1Primitive().f().e(true);
            }
            this.contentsLength = i2;
        }
        return this.contentsLength;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void c(ASN1OutputStream aSN1OutputStream, boolean z) {
        aSN1OutputStream.q(z, 48);
        DEROutputStream b2 = aSN1OutputStream.b();
        int length = this.f12709a.length;
        int i2 = 0;
        if (this.contentsLength >= 0 || length > 16) {
            aSN1OutputStream.i(getContentsLength());
            while (i2 < length) {
                this.f12709a[i2].toASN1Primitive().f().c(b2, true);
                i2++;
            }
            return;
        }
        ASN1Primitive[] aSN1PrimitiveArr = new ASN1Primitive[length];
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            ASN1Primitive f2 = this.f12709a[i4].toASN1Primitive().f();
            aSN1PrimitiveArr[i4] = f2;
            i3 += f2.e(true);
        }
        this.contentsLength = i3;
        aSN1OutputStream.i(i3);
        while (i2 < length) {
            aSN1PrimitiveArr[i2].c(b2, true);
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int e(boolean z) {
        return ASN1OutputStream.e(z, getContentsLength());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Sequence, org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive f() {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Sequence, org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive g() {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Sequence
    public ASN1BitString j() {
        return new DERBitString(BERBitString.k(h()), false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Sequence
    public ASN1External k() {
        return new DERExternal(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Sequence
    public ASN1OctetString l() {
        return new DEROctetString(BEROctetString.k(i()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Sequence
    public ASN1Set m() {
        return new DLSet(false, n());
    }
}
