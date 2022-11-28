package org.bouncycastle.asn1;

import java.util.Enumeration;
import java.util.NoSuchElementException;
/* loaded from: classes3.dex */
public class BEROctetString extends ASN1OctetString {
    private static final int DEFAULT_SEGMENT_LIMIT = 1000;
    private final ASN1OctetString[] elements;
    private final int segmentLimit;

    public BEROctetString(byte[] bArr) {
        this(bArr, 1000);
    }

    public BEROctetString(byte[] bArr, int i2) {
        this(bArr, null, i2);
    }

    private BEROctetString(byte[] bArr, ASN1OctetString[] aSN1OctetStringArr, int i2) {
        super(bArr);
        this.elements = aSN1OctetStringArr;
        this.segmentLimit = i2;
    }

    public BEROctetString(ASN1OctetString[] aSN1OctetStringArr) {
        this(aSN1OctetStringArr, 1000);
    }

    public BEROctetString(ASN1OctetString[] aSN1OctetStringArr, int i2) {
        this(k(aSN1OctetStringArr), aSN1OctetStringArr, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] k(ASN1OctetString[] aSN1OctetStringArr) {
        int length = aSN1OctetStringArr.length;
        if (length != 0) {
            if (length != 1) {
                int i2 = 0;
                for (ASN1OctetString aSN1OctetString : aSN1OctetStringArr) {
                    i2 += aSN1OctetString.f12704a.length;
                }
                byte[] bArr = new byte[i2];
                int i3 = 0;
                for (ASN1OctetString aSN1OctetString2 : aSN1OctetStringArr) {
                    byte[] bArr2 = aSN1OctetString2.f12704a;
                    System.arraycopy(bArr2, 0, bArr, i3, bArr2.length);
                    i3 += bArr2.length;
                }
                return bArr;
            }
            return aSN1OctetStringArr[0].f12704a;
        }
        return ASN1OctetString.f12703c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void c(ASN1OutputStream aSN1OutputStream, boolean z) {
        if (!d()) {
            byte[] bArr = this.f12704a;
            DEROctetString.i(aSN1OutputStream, z, bArr, 0, bArr.length);
            return;
        }
        aSN1OutputStream.q(z, 36);
        aSN1OutputStream.g(128);
        ASN1OctetString[] aSN1OctetStringArr = this.elements;
        if (aSN1OctetStringArr == null) {
            int i2 = 0;
            while (true) {
                byte[] bArr2 = this.f12704a;
                if (i2 >= bArr2.length) {
                    break;
                }
                int min = Math.min(bArr2.length - i2, this.segmentLimit);
                DEROctetString.i(aSN1OutputStream, true, this.f12704a, i2, min);
                i2 += min;
            }
        } else {
            aSN1OutputStream.t(aSN1OctetStringArr);
        }
        aSN1OutputStream.g(0);
        aSN1OutputStream.g(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean d() {
        return this.elements != null || this.f12704a.length > this.segmentLimit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int e(boolean z) {
        if (!d()) {
            return DEROctetString.j(z, this.f12704a.length);
        }
        int i2 = z ? 4 : 3;
        if (this.elements == null) {
            int length = this.f12704a.length;
            int i3 = this.segmentLimit;
            int i4 = length / i3;
            int j2 = i2 + (DEROctetString.j(true, i3) * i4);
            int length2 = this.f12704a.length - (i4 * this.segmentLimit);
            return length2 > 0 ? j2 + DEROctetString.j(true, length2) : j2;
        }
        int i5 = 0;
        while (true) {
            ASN1OctetString[] aSN1OctetStringArr = this.elements;
            if (i5 >= aSN1OctetStringArr.length) {
                return i2;
            }
            i2 += aSN1OctetStringArr[i5].e(true);
            i5++;
        }
    }

    public Enumeration getObjects() {
        return this.elements == null ? new Enumeration() { // from class: org.bouncycastle.asn1.BEROctetString.1

            /* renamed from: a  reason: collision with root package name */
            int f12737a = 0;

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.f12737a < BEROctetString.this.f12704a.length;
            }

            @Override // java.util.Enumeration
            public Object nextElement() {
                int i2 = this.f12737a;
                BEROctetString bEROctetString = BEROctetString.this;
                byte[] bArr = bEROctetString.f12704a;
                if (i2 < bArr.length) {
                    int min = Math.min(bArr.length - i2, bEROctetString.segmentLimit);
                    byte[] bArr2 = new byte[min];
                    System.arraycopy(BEROctetString.this.f12704a, this.f12737a, bArr2, 0, min);
                    this.f12737a += min;
                    return new DEROctetString(bArr2);
                }
                throw new NoSuchElementException();
            }
        } : new Enumeration() { // from class: org.bouncycastle.asn1.BEROctetString.2

            /* renamed from: a  reason: collision with root package name */
            int f12739a = 0;

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.f12739a < BEROctetString.this.elements.length;
            }

            @Override // java.util.Enumeration
            public Object nextElement() {
                if (this.f12739a < BEROctetString.this.elements.length) {
                    ASN1OctetString[] aSN1OctetStringArr = BEROctetString.this.elements;
                    int i2 = this.f12739a;
                    this.f12739a = i2 + 1;
                    return aSN1OctetStringArr[i2];
                }
                throw new NoSuchElementException();
            }
        };
    }
}
