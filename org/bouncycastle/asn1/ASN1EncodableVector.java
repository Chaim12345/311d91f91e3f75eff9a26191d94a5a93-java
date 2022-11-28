package org.bouncycastle.asn1;

import java.util.Objects;
/* loaded from: classes3.dex */
public class ASN1EncodableVector {
    private static final int DEFAULT_CAPACITY = 10;

    /* renamed from: a  reason: collision with root package name */
    static final ASN1Encodable[] f12679a = new ASN1Encodable[0];
    private boolean copyOnWrite;
    private int elementCount;
    private ASN1Encodable[] elements;

    public ASN1EncodableVector() {
        this(10);
    }

    public ASN1EncodableVector(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("'initialCapacity' must not be negative");
        }
        this.elements = i2 == 0 ? f12679a : new ASN1Encodable[i2];
        this.elementCount = 0;
        this.copyOnWrite = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1Encodable[] a(ASN1Encodable[] aSN1EncodableArr) {
        return aSN1EncodableArr.length < 1 ? f12679a : (ASN1Encodable[]) aSN1EncodableArr.clone();
    }

    private void doAddAll(ASN1Encodable[] aSN1EncodableArr, String str) {
        int length = aSN1EncodableArr.length;
        if (length < 1) {
            return;
        }
        int length2 = this.elements.length;
        int i2 = this.elementCount + length;
        int i3 = 0;
        if ((i2 > length2) | this.copyOnWrite) {
            reallocate(i2);
        }
        do {
            ASN1Encodable aSN1Encodable = aSN1EncodableArr[i3];
            Objects.requireNonNull(aSN1Encodable, str);
            this.elements[this.elementCount + i3] = aSN1Encodable;
            i3++;
        } while (i3 < length);
        this.elementCount = i2;
    }

    private void reallocate(int i2) {
        ASN1Encodable[] aSN1EncodableArr = new ASN1Encodable[Math.max(this.elements.length, i2 + (i2 >> 1))];
        System.arraycopy(this.elements, 0, aSN1EncodableArr, 0, this.elementCount);
        this.elements = aSN1EncodableArr;
        this.copyOnWrite = false;
    }

    public void add(ASN1Encodable aSN1Encodable) {
        Objects.requireNonNull(aSN1Encodable, "'element' cannot be null");
        int length = this.elements.length;
        int i2 = this.elementCount + 1;
        if (this.copyOnWrite | (i2 > length)) {
            reallocate(i2);
        }
        this.elements[this.elementCount] = aSN1Encodable;
        this.elementCount = i2;
    }

    public void addAll(ASN1EncodableVector aSN1EncodableVector) {
        Objects.requireNonNull(aSN1EncodableVector, "'other' cannot be null");
        doAddAll(aSN1EncodableVector.elements, "'other' elements cannot be null");
    }

    public void addAll(ASN1Encodable[] aSN1EncodableArr) {
        Objects.requireNonNull(aSN1EncodableArr, "'others' cannot be null");
        doAddAll(aSN1EncodableArr, "'others' elements cannot be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Encodable[] b() {
        int i2 = this.elementCount;
        if (i2 == 0) {
            return f12679a;
        }
        ASN1Encodable[] aSN1EncodableArr = new ASN1Encodable[i2];
        System.arraycopy(this.elements, 0, aSN1EncodableArr, 0, i2);
        return aSN1EncodableArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Encodable[] c() {
        int i2 = this.elementCount;
        if (i2 == 0) {
            return f12679a;
        }
        ASN1Encodable[] aSN1EncodableArr = this.elements;
        if (aSN1EncodableArr.length == i2) {
            this.copyOnWrite = true;
            return aSN1EncodableArr;
        }
        ASN1Encodable[] aSN1EncodableArr2 = new ASN1Encodable[i2];
        System.arraycopy(aSN1EncodableArr, 0, aSN1EncodableArr2, 0, i2);
        return aSN1EncodableArr2;
    }

    public ASN1Encodable get(int i2) {
        if (i2 < this.elementCount) {
            return this.elements[i2];
        }
        throw new ArrayIndexOutOfBoundsException(i2 + " >= " + this.elementCount);
    }

    public int size() {
        return this.elementCount;
    }
}
