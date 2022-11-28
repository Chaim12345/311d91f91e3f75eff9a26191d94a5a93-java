package org.bouncycastle.asn1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;
import org.bouncycastle.util.Arrays;
/* loaded from: classes3.dex */
public abstract class ASN1TaggedObject extends ASN1Primitive implements ASN1TaggedObjectParser {
    private static final int DECLARED_EXPLICIT = 1;
    private static final int DECLARED_IMPLICIT = 2;
    private static final int PARSED_EXPLICIT = 3;
    private static final int PARSED_IMPLICIT = 4;

    /* renamed from: a  reason: collision with root package name */
    final int f12721a;

    /* renamed from: b  reason: collision with root package name */
    final int f12722b;

    /* renamed from: c  reason: collision with root package name */
    final int f12723c;

    /* renamed from: d  reason: collision with root package name */
    final ASN1Encodable f12724d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1TaggedObject(int i2, int i3, int i4, ASN1Encodable aSN1Encodable) {
        Objects.requireNonNull(aSN1Encodable, "'obj' cannot be null");
        if (i3 == 0 || (i3 & 192) != i3) {
            throw new IllegalArgumentException("invalid tag class: " + i3);
        }
        this.f12721a = aSN1Encodable instanceof ASN1Choice ? 1 : i2;
        this.f12722b = i3;
        this.f12723c = i4;
        this.f12724d = aSN1Encodable;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ASN1TaggedObject(boolean z, int i2, int i3, ASN1Encodable aSN1Encodable) {
        this(z ? 1 : 2, i2, i3, aSN1Encodable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ASN1TaggedObject(boolean z, int i2, ASN1Encodable aSN1Encodable) {
        this(z, 128, i2, aSN1Encodable);
    }

    private static ASN1TaggedObject checkedCast(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1TaggedObject) {
            return (ASN1TaggedObject) aSN1Primitive;
        }
        throw new IllegalStateException("unexpected object: " + aSN1Primitive.getClass().getName());
    }

    public static ASN1TaggedObject getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1TaggedObject)) {
            return (ASN1TaggedObject) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1TaggedObject) {
                return (ASN1TaggedObject) aSN1Primitive;
            }
        } else if (obj instanceof byte[]) {
            try {
                return checkedCast(ASN1Primitive.fromByteArray((byte[]) obj));
            } catch (IOException e2) {
                throw new IllegalArgumentException("failed to construct tagged object from byte[]: " + e2.getMessage());
            }
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
    }

    public static ASN1TaggedObject getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (128 == aSN1TaggedObject.getTagClass()) {
            if (z) {
                return aSN1TaggedObject.getExplicitBaseTagged();
            }
            throw new IllegalArgumentException("this method not valid for implicitly tagged tagged objects");
        }
        throw new IllegalStateException("this method only valid for CONTEXT_SPECIFIC tags");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1Primitive h(int i2, int i3, ASN1EncodableVector aSN1EncodableVector) {
        DLTaggedObject dLTaggedObject = aSN1EncodableVector.size() == 1 ? new DLTaggedObject(3, i2, i3, aSN1EncodableVector.get(0)) : new DLTaggedObject(4, i2, i3, DLFactory.a(aSN1EncodableVector));
        return i2 != 64 ? dLTaggedObject : new DLApplicationSpecific(dLTaggedObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1Primitive i(int i2, int i3, ASN1EncodableVector aSN1EncodableVector) {
        BERTaggedObject bERTaggedObject = aSN1EncodableVector.size() == 1 ? new BERTaggedObject(3, i2, i3, aSN1EncodableVector.get(0)) : new BERTaggedObject(4, i2, i3, BERFactory.a(aSN1EncodableVector));
        return i2 != 64 ? bERTaggedObject : new BERApplicationSpecific(bERTaggedObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1Primitive j(int i2, int i3, byte[] bArr) {
        DLTaggedObject dLTaggedObject = new DLTaggedObject(4, i2, i3, new DEROctetString(bArr));
        return i2 != 64 ? dLTaggedObject : new DLApplicationSpecific(dLTaggedObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean b(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1ApplicationSpecific) {
            return aSN1Primitive.equals((ASN1Primitive) this);
        }
        if (aSN1Primitive instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Primitive;
            if (this.f12723c == aSN1TaggedObject.f12723c && this.f12722b == aSN1TaggedObject.f12722b) {
                if (this.f12721a == aSN1TaggedObject.f12721a || isExplicit() == aSN1TaggedObject.isExplicit()) {
                    ASN1Primitive aSN1Primitive2 = this.f12724d.toASN1Primitive();
                    ASN1Primitive aSN1Primitive3 = aSN1TaggedObject.f12724d.toASN1Primitive();
                    if (aSN1Primitive2 == aSN1Primitive3) {
                        return true;
                    }
                    if (isExplicit()) {
                        return aSN1Primitive2.b(aSN1Primitive3);
                    }
                    try {
                        return Arrays.areEqual(getEncoded(), aSN1TaggedObject.getEncoded());
                    } catch (IOException unused) {
                        return false;
                    }
                }
                return false;
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive f() {
        return new DERTaggedObject(this.f12721a, this.f12722b, this.f12723c, this.f12724d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive g() {
        return new DLTaggedObject(this.f12721a, this.f12722b, this.f12723c, this.f12724d);
    }

    public ASN1Object getBaseObject() {
        ASN1Encodable aSN1Encodable = this.f12724d;
        return aSN1Encodable instanceof ASN1Object ? (ASN1Object) aSN1Encodable : aSN1Encodable.toASN1Primitive();
    }

    public ASN1Primitive getBaseUniversal(boolean z, int i2) {
        ASN1UniversalType a2 = ASN1UniversalTypes.a(i2);
        if (a2 != null) {
            return l(z, a2);
        }
        throw new IllegalArgumentException("unsupported UNIVERSAL tag number: " + i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getContents() {
        try {
            byte[] encoded = this.f12724d.toASN1Primitive().getEncoded(k());
            if (isExplicit()) {
                return encoded;
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(encoded);
            ASN1InputStream.h(byteArrayInputStream, byteArrayInputStream.read());
            int g2 = ASN1InputStream.g(byteArrayInputStream, byteArrayInputStream.available(), false);
            int available = byteArrayInputStream.available();
            int i2 = g2 < 0 ? available - 2 : available;
            if (i2 >= 0) {
                byte[] bArr = new byte[i2];
                System.arraycopy(encoded, encoded.length - available, bArr, 0, i2);
                return bArr;
            }
            throw new ASN1ParsingException("failed to get contents");
        } catch (IOException e2) {
            throw new ASN1ParsingException("failed to get contents", e2);
        }
    }

    public ASN1Object getExplicitBaseObject() {
        if (isExplicit()) {
            ASN1Encodable aSN1Encodable = this.f12724d;
            return aSN1Encodable instanceof ASN1Object ? (ASN1Object) aSN1Encodable : aSN1Encodable.toASN1Primitive();
        }
        throw new IllegalStateException("object implicit - explicit expected.");
    }

    public ASN1TaggedObject getExplicitBaseTagged() {
        if (isExplicit()) {
            return checkedCast(this.f12724d.toASN1Primitive());
        }
        throw new IllegalStateException("object implicit - explicit expected.");
    }

    public ASN1TaggedObject getImplicitBaseTagged(int i2, int i3) {
        if (i2 == 0 || (i2 & 192) != i2) {
            throw new IllegalArgumentException("invalid base tag class: " + i2);
        }
        int i4 = this.f12721a;
        if (i4 != 1) {
            return i4 != 2 ? o(i2, i3) : ASN1Util.a(checkedCast(this.f12724d.toASN1Primitive()), i2, i3);
        }
        throw new IllegalStateException("object explicit - implicit expected.");
    }

    @Override // org.bouncycastle.asn1.InMemoryRepresentable
    public final ASN1Primitive getLoadedObject() {
        return this;
    }

    public ASN1Primitive getObject() {
        if (128 == getTagClass()) {
            return this.f12724d.toASN1Primitive();
        }
        throw new IllegalStateException("this method only valid for CONTEXT_SPECIFIC tags");
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
        return this.f12722b;
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public int getTagNo() {
        return this.f12723c;
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public boolean hasContextTag(int i2) {
        return this.f12722b == 128 && this.f12723c == i2;
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public boolean hasTag(int i2, int i3) {
        return this.f12722b == i2 && this.f12723c == i3;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        return (((this.f12722b * 7919) ^ this.f12723c) ^ (isExplicit() ? 15 : 240)) ^ this.f12724d.toASN1Primitive().hashCode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isConstructed() {
        return d();
    }

    public boolean isExplicit() {
        int i2 = this.f12721a;
        return i2 == 1 || i2 == 3;
    }

    abstract String k();

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Primitive l(boolean z, ASN1UniversalType aSN1UniversalType) {
        if (z) {
            if (isExplicit()) {
                return aSN1UniversalType.a(this.f12724d.toASN1Primitive());
            }
            throw new IllegalStateException("object explicit - implicit expected.");
        } else if (1 != this.f12721a) {
            ASN1Primitive aSN1Primitive = this.f12724d.toASN1Primitive();
            int i2 = this.f12721a;
            return i2 != 3 ? i2 != 4 ? aSN1UniversalType.a(aSN1Primitive) : aSN1Primitive instanceof ASN1Sequence ? aSN1UniversalType.c((ASN1Sequence) aSN1Primitive) : aSN1UniversalType.d((DEROctetString) aSN1Primitive) : aSN1UniversalType.c(n(aSN1Primitive));
        } else {
            throw new IllegalStateException("object explicit - implicit expected.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean m() {
        int i2 = this.f12721a;
        return i2 == 3 || i2 == 4;
    }

    abstract ASN1Sequence n(ASN1Primitive aSN1Primitive);

    abstract ASN1TaggedObject o(int i2, int i3);

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1Encodable parseBaseUniversal(boolean z, int i2) {
        ASN1Primitive baseUniversal = getBaseUniversal(z, i2);
        return i2 != 3 ? i2 != 4 ? i2 != 16 ? i2 != 17 ? baseUniversal : ((ASN1Set) baseUniversal).parser() : ((ASN1Sequence) baseUniversal).parser() : ((ASN1OctetString) baseUniversal).parser() : ((ASN1BitString) baseUniversal).parser();
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1Encodable parseExplicitBaseObject() {
        return getExplicitBaseObject();
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1TaggedObjectParser parseExplicitBaseTagged() {
        return getExplicitBaseTagged();
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1TaggedObjectParser parseImplicitBaseTagged(int i2, int i3) {
        return getImplicitBaseTagged(i2, i3);
    }

    public String toString() {
        return ASN1Util.getTagText(this.f12722b, this.f12723c) + this.f12724d;
    }
}
