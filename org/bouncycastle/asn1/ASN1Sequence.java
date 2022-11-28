package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okhttp3.HttpUrl;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Iterable;
/* loaded from: classes3.dex */
public abstract class ASN1Sequence extends ASN1Primitive implements Iterable<ASN1Encodable> {

    /* renamed from: b  reason: collision with root package name */
    static final ASN1UniversalType f12708b = new ASN1UniversalType(ASN1Sequence.class, 16) { // from class: org.bouncycastle.asn1.ASN1Sequence.1
        @Override // org.bouncycastle.asn1.ASN1UniversalType
        ASN1Primitive c(ASN1Sequence aSN1Sequence) {
            return aSN1Sequence;
        }
    };

    /* renamed from: a  reason: collision with root package name */
    ASN1Encodable[] f12709a;

    /* JADX INFO: Access modifiers changed from: protected */
    public ASN1Sequence() {
        this.f12709a = ASN1EncodableVector.f12679a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ASN1Sequence(ASN1Encodable aSN1Encodable) {
        Objects.requireNonNull(aSN1Encodable, "'element' cannot be null");
        this.f12709a = new ASN1Encodable[]{aSN1Encodable};
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ASN1Sequence(ASN1EncodableVector aSN1EncodableVector) {
        Objects.requireNonNull(aSN1EncodableVector, "'elementVector' cannot be null");
        this.f12709a = aSN1EncodableVector.c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ASN1Sequence(ASN1Encodable[] aSN1EncodableArr) {
        if (Arrays.isNullOrContainsNull(aSN1EncodableArr)) {
            throw new NullPointerException("'elements' cannot be null, or contain null");
        }
        this.f12709a = ASN1EncodableVector.a(aSN1EncodableArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Sequence(ASN1Encodable[] aSN1EncodableArr, boolean z) {
        this.f12709a = z ? ASN1EncodableVector.a(aSN1EncodableArr) : aSN1EncodableArr;
    }

    public static ASN1Sequence getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1Sequence)) {
            return (ASN1Sequence) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1Sequence) {
                return (ASN1Sequence) aSN1Primitive;
            }
        } else if (obj instanceof byte[]) {
            try {
                return (ASN1Sequence) f12708b.b((byte[]) obj);
            } catch (IOException e2) {
                throw new IllegalArgumentException("failed to construct sequence from byte[]: " + e2.getMessage());
            }
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
    }

    public static ASN1Sequence getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return (ASN1Sequence) f12708b.e(aSN1TaggedObject, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean b(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1Sequence) {
            ASN1Sequence aSN1Sequence = (ASN1Sequence) aSN1Primitive;
            int size = size();
            if (aSN1Sequence.size() != size) {
                return false;
            }
            for (int i2 = 0; i2 < size; i2++) {
                ASN1Primitive aSN1Primitive2 = this.f12709a[i2].toASN1Primitive();
                ASN1Primitive aSN1Primitive3 = aSN1Sequence.f12709a[i2].toASN1Primitive();
                if (aSN1Primitive2 != aSN1Primitive3 && !aSN1Primitive2.b(aSN1Primitive3)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean d() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive f() {
        return new DERSequence(this.f12709a, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive g() {
        return new DLSequence(this.f12709a, false);
    }

    public ASN1Encodable getObjectAt(int i2) {
        return this.f12709a[i2];
    }

    public Enumeration getObjects() {
        return new Enumeration() { // from class: org.bouncycastle.asn1.ASN1Sequence.2
            private int pos = 0;

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.pos < ASN1Sequence.this.f12709a.length;
            }

            @Override // java.util.Enumeration
            public Object nextElement() {
                int i2 = this.pos;
                ASN1Encodable[] aSN1EncodableArr = ASN1Sequence.this.f12709a;
                if (i2 < aSN1EncodableArr.length) {
                    this.pos = i2 + 1;
                    return aSN1EncodableArr[i2];
                }
                throw new NoSuchElementException();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1BitString[] h() {
        int size = size();
        ASN1BitString[] aSN1BitStringArr = new ASN1BitString[size];
        for (int i2 = 0; i2 < size; i2++) {
            aSN1BitStringArr[i2] = ASN1BitString.getInstance(this.f12709a[i2]);
        }
        return aSN1BitStringArr;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        int length = this.f12709a.length;
        int i2 = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i2;
            }
            i2 = (i2 * 257) ^ this.f12709a[length].toASN1Primitive().hashCode();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1OctetString[] i() {
        int size = size();
        ASN1OctetString[] aSN1OctetStringArr = new ASN1OctetString[size];
        for (int i2 = 0; i2 < size; i2++) {
            aSN1OctetStringArr[i2] = ASN1OctetString.getInstance(this.f12709a[i2]);
        }
        return aSN1OctetStringArr;
    }

    @Override // org.bouncycastle.util.Iterable, java.lang.Iterable
    public Iterator<ASN1Encodable> iterator() {
        return new Arrays.Iterator(this.f12709a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract ASN1BitString j();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract ASN1External k();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract ASN1OctetString l();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract ASN1Set m();

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Encodable[] n() {
        return this.f12709a;
    }

    public ASN1SequenceParser parser() {
        final int size = size();
        return new ASN1SequenceParser() { // from class: org.bouncycastle.asn1.ASN1Sequence.3
            private int pos = 0;

            @Override // org.bouncycastle.asn1.InMemoryRepresentable
            public ASN1Primitive getLoadedObject() {
                return ASN1Sequence.this;
            }

            @Override // org.bouncycastle.asn1.ASN1SequenceParser
            public ASN1Encodable readObject() {
                int i2 = size;
                int i3 = this.pos;
                if (i2 == i3) {
                    return null;
                }
                ASN1Encodable[] aSN1EncodableArr = ASN1Sequence.this.f12709a;
                this.pos = i3 + 1;
                ASN1Encodable aSN1Encodable = aSN1EncodableArr[i3];
                return aSN1Encodable instanceof ASN1Sequence ? ((ASN1Sequence) aSN1Encodable).parser() : aSN1Encodable instanceof ASN1Set ? ((ASN1Set) aSN1Encodable).parser() : aSN1Encodable;
            }

            @Override // org.bouncycastle.asn1.ASN1Encodable
            public ASN1Primitive toASN1Primitive() {
                return ASN1Sequence.this;
            }
        };
    }

    public int size() {
        return this.f12709a.length;
    }

    public ASN1Encodable[] toArray() {
        return ASN1EncodableVector.a(this.f12709a);
    }

    public String toString() {
        int size = size();
        if (size == 0) {
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(AbstractJsonLexerKt.BEGIN_LIST);
        int i2 = 0;
        while (true) {
            stringBuffer.append(this.f12709a[i2]);
            i2++;
            if (i2 >= size) {
                stringBuffer.append(AbstractJsonLexerKt.END_LIST);
                return stringBuffer.toString();
            }
            stringBuffer.append(", ");
        }
    }
}
