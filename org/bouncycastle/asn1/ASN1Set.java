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
public abstract class ASN1Set extends ASN1Primitive implements Iterable<ASN1Encodable> {

    /* renamed from: c  reason: collision with root package name */
    static final ASN1UniversalType f12713c = new ASN1UniversalType(ASN1Set.class, 17) { // from class: org.bouncycastle.asn1.ASN1Set.1
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.bouncycastle.asn1.ASN1UniversalType
        public ASN1Primitive c(ASN1Sequence aSN1Sequence) {
            return aSN1Sequence.m();
        }
    };

    /* renamed from: a  reason: collision with root package name */
    protected final ASN1Encodable[] f12714a;

    /* renamed from: b  reason: collision with root package name */
    protected final boolean f12715b;

    /* JADX INFO: Access modifiers changed from: protected */
    public ASN1Set() {
        this.f12714a = ASN1EncodableVector.f12679a;
        this.f12715b = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ASN1Set(ASN1Encodable aSN1Encodable) {
        Objects.requireNonNull(aSN1Encodable, "'element' cannot be null");
        this.f12714a = new ASN1Encodable[]{aSN1Encodable};
        this.f12715b = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ASN1Set(ASN1EncodableVector aSN1EncodableVector, boolean z) {
        ASN1Encodable[] c2;
        Objects.requireNonNull(aSN1EncodableVector, "'elementVector' cannot be null");
        if (!z || aSN1EncodableVector.size() < 2) {
            c2 = aSN1EncodableVector.c();
        } else {
            c2 = aSN1EncodableVector.b();
            sort(c2);
        }
        this.f12714a = c2;
        this.f12715b = z || c2.length < 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Set(boolean z, ASN1Encodable[] aSN1EncodableArr) {
        this.f12714a = aSN1EncodableArr;
        this.f12715b = z || aSN1EncodableArr.length < 2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ASN1Set(ASN1Encodable[] aSN1EncodableArr, boolean z) {
        if (Arrays.isNullOrContainsNull(aSN1EncodableArr)) {
            throw new NullPointerException("'elements' cannot be null, or contain null");
        }
        ASN1Encodable[] a2 = ASN1EncodableVector.a(aSN1EncodableArr);
        if (z && a2.length >= 2) {
            sort(a2);
        }
        this.f12714a = a2;
        this.f12715b = z || a2.length < 2;
    }

    private static byte[] getDEREncoded(ASN1Encodable aSN1Encodable) {
        try {
            return aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER);
        } catch (IOException unused) {
            throw new IllegalArgumentException("cannot encode object added to SET");
        }
    }

    public static ASN1Set getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1Set)) {
            return (ASN1Set) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1Set) {
                return (ASN1Set) aSN1Primitive;
            }
        } else if (obj instanceof byte[]) {
            try {
                return (ASN1Set) f12713c.b((byte[]) obj);
            } catch (IOException e2) {
                throw new IllegalArgumentException("failed to construct set from byte[]: " + e2.getMessage());
            }
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
    }

    public static ASN1Set getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return (ASN1Set) f12713c.e(aSN1TaggedObject, z);
    }

    private static boolean lessThanOrEqual(byte[] bArr, byte[] bArr2) {
        int i2 = bArr[0] & (-33);
        int i3 = bArr2[0] & (-33);
        if (i2 != i3) {
            return i2 < i3;
        }
        int min = Math.min(bArr.length, bArr2.length) - 1;
        for (int i4 = 1; i4 < min; i4++) {
            if (bArr[i4] != bArr2[i4]) {
                return (bArr[i4] & 255) < (bArr2[i4] & 255);
            }
        }
        return (bArr[min] & 255) <= (bArr2[min] & 255);
    }

    private static void sort(ASN1Encodable[] aSN1EncodableArr) {
        int length = aSN1EncodableArr.length;
        if (length < 2) {
            return;
        }
        ASN1Encodable aSN1Encodable = aSN1EncodableArr[0];
        ASN1Encodable aSN1Encodable2 = aSN1EncodableArr[1];
        byte[] dEREncoded = getDEREncoded(aSN1Encodable);
        byte[] dEREncoded2 = getDEREncoded(aSN1Encodable2);
        if (lessThanOrEqual(dEREncoded2, dEREncoded)) {
            aSN1Encodable2 = aSN1Encodable;
            aSN1Encodable = aSN1Encodable2;
            dEREncoded2 = dEREncoded;
            dEREncoded = dEREncoded2;
        }
        for (int i2 = 2; i2 < length; i2++) {
            ASN1Encodable aSN1Encodable3 = aSN1EncodableArr[i2];
            byte[] dEREncoded3 = getDEREncoded(aSN1Encodable3);
            if (lessThanOrEqual(dEREncoded2, dEREncoded3)) {
                aSN1EncodableArr[i2 - 2] = aSN1Encodable;
                aSN1Encodable = aSN1Encodable2;
                dEREncoded = dEREncoded2;
                aSN1Encodable2 = aSN1Encodable3;
                dEREncoded2 = dEREncoded3;
            } else if (lessThanOrEqual(dEREncoded, dEREncoded3)) {
                aSN1EncodableArr[i2 - 2] = aSN1Encodable;
                aSN1Encodable = aSN1Encodable3;
                dEREncoded = dEREncoded3;
            } else {
                int i3 = i2 - 1;
                while (true) {
                    i3--;
                    if (i3 <= 0) {
                        break;
                    }
                    ASN1Encodable aSN1Encodable4 = aSN1EncodableArr[i3 - 1];
                    if (lessThanOrEqual(getDEREncoded(aSN1Encodable4), dEREncoded3)) {
                        break;
                    }
                    aSN1EncodableArr[i3] = aSN1Encodable4;
                }
                aSN1EncodableArr[i3] = aSN1Encodable3;
            }
        }
        aSN1EncodableArr[length - 2] = aSN1Encodable;
        aSN1EncodableArr[length - 1] = aSN1Encodable2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean b(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1Set) {
            ASN1Set aSN1Set = (ASN1Set) aSN1Primitive;
            int size = size();
            if (aSN1Set.size() != size) {
                return false;
            }
            DERSet dERSet = (DERSet) f();
            DERSet dERSet2 = (DERSet) aSN1Set.f();
            for (int i2 = 0; i2 < size; i2++) {
                ASN1Primitive aSN1Primitive2 = dERSet.f12714a[i2].toASN1Primitive();
                ASN1Primitive aSN1Primitive3 = dERSet2.f12714a[i2].toASN1Primitive();
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
        ASN1Encodable[] aSN1EncodableArr;
        if (this.f12715b) {
            aSN1EncodableArr = this.f12714a;
        } else {
            aSN1EncodableArr = (ASN1Encodable[]) this.f12714a.clone();
            sort(aSN1EncodableArr);
        }
        return new DERSet(true, aSN1EncodableArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive g() {
        return new DLSet(this.f12715b, this.f12714a);
    }

    public ASN1Encodable getObjectAt(int i2) {
        return this.f12714a[i2];
    }

    public Enumeration getObjects() {
        return new Enumeration() { // from class: org.bouncycastle.asn1.ASN1Set.2
            private int pos = 0;

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.pos < ASN1Set.this.f12714a.length;
            }

            @Override // java.util.Enumeration
            public Object nextElement() {
                int i2 = this.pos;
                ASN1Encodable[] aSN1EncodableArr = ASN1Set.this.f12714a;
                if (i2 < aSN1EncodableArr.length) {
                    this.pos = i2 + 1;
                    return aSN1EncodableArr[i2];
                }
                throw new NoSuchElementException();
            }
        };
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        int length = this.f12714a.length;
        int i2 = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i2;
            }
            i2 += this.f12714a[length].toASN1Primitive().hashCode();
        }
    }

    @Override // org.bouncycastle.util.Iterable, java.lang.Iterable
    public Iterator<ASN1Encodable> iterator() {
        return new Arrays.Iterator(toArray());
    }

    public ASN1SetParser parser() {
        final int size = size();
        return new ASN1SetParser() { // from class: org.bouncycastle.asn1.ASN1Set.3
            private int pos = 0;

            @Override // org.bouncycastle.asn1.InMemoryRepresentable
            public ASN1Primitive getLoadedObject() {
                return ASN1Set.this;
            }

            @Override // org.bouncycastle.asn1.ASN1SetParser
            public ASN1Encodable readObject() {
                int i2 = size;
                int i3 = this.pos;
                if (i2 == i3) {
                    return null;
                }
                ASN1Encodable[] aSN1EncodableArr = ASN1Set.this.f12714a;
                this.pos = i3 + 1;
                ASN1Encodable aSN1Encodable = aSN1EncodableArr[i3];
                return aSN1Encodable instanceof ASN1Sequence ? ((ASN1Sequence) aSN1Encodable).parser() : aSN1Encodable instanceof ASN1Set ? ((ASN1Set) aSN1Encodable).parser() : aSN1Encodable;
            }

            @Override // org.bouncycastle.asn1.ASN1Encodable
            public ASN1Primitive toASN1Primitive() {
                return ASN1Set.this;
            }
        };
    }

    public int size() {
        return this.f12714a.length;
    }

    public ASN1Encodable[] toArray() {
        return ASN1EncodableVector.a(this.f12714a);
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
            stringBuffer.append(this.f12714a[i2]);
            i2++;
            if (i2 >= size) {
                stringBuffer.append(AbstractJsonLexerKt.END_LIST);
                return stringBuffer.toString();
            }
            stringBuffer.append(", ");
        }
    }
}
