package org.bouncycastle.asn1;
/* loaded from: classes3.dex */
final class ASN1Tag {
    private final int tagClass;
    private final int tagNumber;

    private ASN1Tag(int i2, int i3) {
        this.tagClass = i2;
        this.tagNumber = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1Tag a(int i2, int i3) {
        return new ASN1Tag(i2, i3);
    }
}
