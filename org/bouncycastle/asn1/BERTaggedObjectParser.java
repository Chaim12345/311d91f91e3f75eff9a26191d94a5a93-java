package org.bouncycastle.asn1;

import java.io.IOException;
/* loaded from: classes3.dex */
public class BERTaggedObjectParser implements ASN1TaggedObjectParser {

    /* renamed from: a  reason: collision with root package name */
    final int f12742a;

    /* renamed from: b  reason: collision with root package name */
    final int f12743b;

    /* renamed from: c  reason: collision with root package name */
    final ASN1StreamParser f12744c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BERTaggedObjectParser(int i2, int i3, ASN1StreamParser aSN1StreamParser) {
        this.f12742a = i2;
        this.f12743b = i3;
        this.f12744c = aSN1StreamParser;
    }

    @Override // org.bouncycastle.asn1.InMemoryRepresentable
    public ASN1Primitive getLoadedObject() {
        return this.f12744c.c(this.f12742a, this.f12743b);
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1Encodable getObjectParser(int i2, boolean z) {
        if (128 == getTagClass()) {
            return parseBaseUniversal(z, i2);
        }
        throw new ASN1Exception("this method only valid for CONTEXT_SPECIFIC tags");
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public int getTagClass() {
        return this.f12742a;
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public int getTagNo() {
        return this.f12743b;
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public boolean hasContextTag(int i2) {
        return this.f12742a == 128 && this.f12743b == i2;
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public boolean hasTag(int i2, int i3) {
        return this.f12742a == i2 && this.f12743b == i3;
    }

    public boolean isConstructed() {
        return true;
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1Encodable parseBaseUniversal(boolean z, int i2) {
        return z ? this.f12744c.h(i2) : this.f12744c.e(i2);
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1Encodable parseExplicitBaseObject() {
        return this.f12744c.readObject();
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1TaggedObjectParser parseExplicitBaseTagged() {
        return this.f12744c.i();
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1TaggedObjectParser parseImplicitBaseTagged(int i2, int i3) {
        return 64 == i2 ? new BERApplicationSpecificParser(i3, this.f12744c) : new BERTaggedObjectParser(i2, i3, this.f12744c);
    }

    @Override // org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        try {
            return getLoadedObject();
        } catch (IOException e2) {
            throw new ASN1ParsingException(e2.getMessage());
        }
    }
}
