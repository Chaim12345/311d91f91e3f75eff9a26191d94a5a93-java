package org.bouncycastle.asn1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class DERFactory {

    /* renamed from: a  reason: collision with root package name */
    static final DERSequence f12745a = new DERSequence();

    static {
        new DERSet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DERSequence a(ASN1EncodableVector aSN1EncodableVector) {
        return aSN1EncodableVector.size() < 1 ? f12745a : new DERSequence(aSN1EncodableVector);
    }
}
