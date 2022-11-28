package org.bouncycastle.asn1;
/* loaded from: classes3.dex */
public interface ASN1TaggedObjectParser extends ASN1Encodable, InMemoryRepresentable {
    ASN1Encodable getObjectParser(int i2, boolean z);

    int getTagClass();

    int getTagNo();

    boolean hasContextTag(int i2);

    boolean hasTag(int i2, int i3);

    ASN1Encodable parseBaseUniversal(boolean z, int i2);

    ASN1Encodable parseExplicitBaseObject();

    ASN1TaggedObjectParser parseExplicitBaseTagged();

    ASN1TaggedObjectParser parseImplicitBaseTagged(int i2, int i3);
}
