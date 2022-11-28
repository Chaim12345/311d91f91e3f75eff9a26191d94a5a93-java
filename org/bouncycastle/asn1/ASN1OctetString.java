package org.bouncycastle.asn1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes3.dex */
public abstract class ASN1OctetString extends ASN1Primitive implements ASN1OctetStringParser {

    /* renamed from: b  reason: collision with root package name */
    static final ASN1UniversalType f12702b = new ASN1UniversalType(ASN1OctetString.class, 4) { // from class: org.bouncycastle.asn1.ASN1OctetString.1
        @Override // org.bouncycastle.asn1.ASN1UniversalType
        ASN1Primitive c(ASN1Sequence aSN1Sequence) {
            return aSN1Sequence.l();
        }

        @Override // org.bouncycastle.asn1.ASN1UniversalType
        ASN1Primitive d(DEROctetString dEROctetString) {
            return dEROctetString;
        }
    };

    /* renamed from: c  reason: collision with root package name */
    static final byte[] f12703c = new byte[0];

    /* renamed from: a  reason: collision with root package name */
    byte[] f12704a;

    public ASN1OctetString(byte[] bArr) {
        Objects.requireNonNull(bArr, "'string' cannot be null");
        this.f12704a = bArr;
    }

    public static ASN1OctetString getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1OctetString)) {
            return (ASN1OctetString) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1OctetString) {
                return (ASN1OctetString) aSN1Primitive;
            }
        } else if (obj instanceof byte[]) {
            try {
                return (ASN1OctetString) f12702b.b((byte[]) obj);
            } catch (IOException e2) {
                throw new IllegalArgumentException("failed to construct OCTET STRING from byte[]: " + e2.getMessage());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public static ASN1OctetString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return (ASN1OctetString) f12702b.e(aSN1TaggedObject, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1OctetString h(byte[] bArr) {
        return new DEROctetString(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean b(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1OctetString) {
            return Arrays.areEqual(this.f12704a, ((ASN1OctetString) aSN1Primitive).f12704a);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive f() {
        return new DEROctetString(this.f12704a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive g() {
        return new DEROctetString(this.f12704a);
    }

    @Override // org.bouncycastle.asn1.InMemoryRepresentable
    public ASN1Primitive getLoadedObject() {
        return toASN1Primitive();
    }

    @Override // org.bouncycastle.asn1.ASN1OctetStringParser
    public InputStream getOctetStream() {
        return new ByteArrayInputStream(this.f12704a);
    }

    public byte[] getOctets() {
        return this.f12704a;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        return Arrays.hashCode(getOctets());
    }

    public ASN1OctetStringParser parser() {
        return this;
    }

    public String toString() {
        return "#" + Strings.fromByteArray(Hex.encode(this.f12704a));
    }
}
