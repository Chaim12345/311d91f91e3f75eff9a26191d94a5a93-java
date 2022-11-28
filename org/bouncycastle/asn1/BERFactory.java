package org.bouncycastle.asn1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class BERFactory {

    /* renamed from: a  reason: collision with root package name */
    static final BERSequence f12736a = new BERSequence();

    static {
        new BERSet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BERSequence a(ASN1EncodableVector aSN1EncodableVector) {
        return aSN1EncodableVector.size() < 1 ? f12736a : new BERSequence(aSN1EncodableVector);
    }
}
