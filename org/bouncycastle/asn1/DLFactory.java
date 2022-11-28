package org.bouncycastle.asn1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class DLFactory {

    /* renamed from: a  reason: collision with root package name */
    static final DLSequence f12746a = new DLSequence();

    /* renamed from: b  reason: collision with root package name */
    static final DLSet f12747b = new DLSet();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DLSequence a(ASN1EncodableVector aSN1EncodableVector) {
        return aSN1EncodableVector.size() < 1 ? f12746a : new DLSequence(aSN1EncodableVector);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DLSet b(ASN1EncodableVector aSN1EncodableVector) {
        return aSN1EncodableVector.size() < 1 ? f12747b : new DLSet(aSN1EncodableVector);
    }
}
