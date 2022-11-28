package org.bouncycastle.asn1;

import com.google.common.base.Ascii;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import org.bouncycastle.util.Arrays;
/* loaded from: classes3.dex */
public abstract class ASN1BitString extends ASN1Primitive implements ASN1String, ASN1BitStringParser {

    /* renamed from: b  reason: collision with root package name */
    static final ASN1UniversalType f12676b = new ASN1UniversalType(ASN1BitString.class, 3) { // from class: org.bouncycastle.asn1.ASN1BitString.1
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.bouncycastle.asn1.ASN1UniversalType
        public ASN1Primitive c(ASN1Sequence aSN1Sequence) {
            return aSN1Sequence.j();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.bouncycastle.asn1.ASN1UniversalType
        public ASN1Primitive d(DEROctetString dEROctetString) {
            return ASN1BitString.h(dEROctetString.getOctets());
        }
    };
    private static final char[] table = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: a  reason: collision with root package name */
    final byte[] f12677a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1BitString(byte b2, int i2) {
        if (i2 > 7 || i2 < 0) {
            throw new IllegalArgumentException("pad bits cannot be greater than 7 or less than 0");
        }
        this.f12677a = new byte[]{(byte) i2, b2};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1BitString(byte[] bArr, int i2) {
        Objects.requireNonNull(bArr, "'data' cannot be null");
        if (bArr.length == 0 && i2 != 0) {
            throw new IllegalArgumentException("zero length data with non-zero pad bits");
        }
        if (i2 > 7 || i2 < 0) {
            throw new IllegalArgumentException("pad bits cannot be greater than 7 or less than 0");
        }
        this.f12677a = Arrays.prepend(bArr, (byte) i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1BitString(byte[] bArr, boolean z) {
        if (z) {
            Objects.requireNonNull(bArr, "'contents' cannot be null");
            if (bArr.length < 1) {
                throw new IllegalArgumentException("'contents' cannot be empty");
            }
            int i2 = bArr[0] & 255;
            if (i2 > 0) {
                if (bArr.length < 2) {
                    throw new IllegalArgumentException("zero length data with non-zero pad bits");
                }
                if (i2 > 7) {
                    throw new IllegalArgumentException("pad bits cannot be greater than 7 or less than 0");
                }
            }
        }
        this.f12677a = bArr;
    }

    public static ASN1BitString getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1BitString)) {
            return (ASN1BitString) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1BitString) {
                return (ASN1BitString) aSN1Primitive;
            }
        } else if (obj instanceof byte[]) {
            try {
                return (ASN1BitString) f12676b.b((byte[]) obj);
            } catch (IOException e2) {
                throw new IllegalArgumentException("failed to construct BIT STRING from byte[]: " + e2.getMessage());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public static ASN1BitString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return (ASN1BitString) f12676b.e(aSN1TaggedObject, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1BitString h(byte[] bArr) {
        int length = bArr.length;
        if (length >= 1) {
            int i2 = bArr[0] & 255;
            if (i2 > 0) {
                if (i2 > 7 || length < 2) {
                    throw new IllegalArgumentException("invalid pad bits detected");
                }
                byte b2 = bArr[length - 1];
                if (b2 != ((byte) ((255 << i2) & b2))) {
                    return new DLBitString(bArr, false);
                }
            }
            return new DERBitString(bArr, false);
        }
        throw new IllegalArgumentException("truncated BIT STRING detected");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static byte[] i(int i2) {
        if (i2 == 0) {
            return new byte[0];
        }
        int i3 = 4;
        for (int i4 = 3; i4 >= 1 && ((255 << (i4 * 8)) & i2) == 0; i4--) {
            i3--;
        }
        byte[] bArr = new byte[i3];
        for (int i5 = 0; i5 < i3; i5++) {
            bArr[i5] = (byte) ((i2 >> (i5 * 8)) & 255);
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int j(int i2) {
        int i3;
        int i4 = 3;
        while (true) {
            if (i4 < 0) {
                i3 = 0;
                break;
            } else if (i4 != 0) {
                int i5 = i2 >> (i4 * 8);
                if (i5 != 0) {
                    i3 = i5 & 255;
                    break;
                }
                i4--;
            } else if (i2 != 0) {
                i3 = i2 & 255;
                break;
            } else {
                i4--;
            }
        }
        if (i3 == 0) {
            return 0;
        }
        int i6 = 1;
        while (true) {
            i3 <<= 1;
            if ((i3 & 255) == 0) {
                return 8 - i6;
            }
            i6++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean b(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1BitString) {
            byte[] bArr = this.f12677a;
            byte[] bArr2 = ((ASN1BitString) aSN1Primitive).f12677a;
            int length = bArr.length;
            if (bArr2.length != length) {
                return false;
            }
            if (length == 1) {
                return true;
            }
            int i2 = length - 1;
            for (int i3 = 0; i3 < i2; i3++) {
                if (bArr[i3] != bArr2[i3]) {
                    return false;
                }
            }
            int i4 = 255 << (bArr[0] & 255);
            return ((byte) (bArr[i2] & i4)) == ((byte) (bArr2[i2] & i4));
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive f() {
        return new DERBitString(this.f12677a, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive g() {
        return new DLBitString(this.f12677a, false);
    }

    @Override // org.bouncycastle.asn1.ASN1BitStringParser
    public InputStream getBitStream() {
        byte[] bArr = this.f12677a;
        return new ByteArrayInputStream(bArr, 1, bArr.length - 1);
    }

    public byte[] getBytes() {
        byte[] bArr = this.f12677a;
        if (bArr.length == 1) {
            return ASN1OctetString.f12703c;
        }
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 1, bArr.length);
        int length = copyOfRange.length - 1;
        copyOfRange[length] = (byte) (((byte) (255 << (bArr[0] & 255))) & copyOfRange[length]);
        return copyOfRange;
    }

    @Override // org.bouncycastle.asn1.InMemoryRepresentable
    public ASN1Primitive getLoadedObject() {
        return toASN1Primitive();
    }

    @Override // org.bouncycastle.asn1.ASN1BitStringParser
    public InputStream getOctetStream() {
        int i2 = this.f12677a[0] & 255;
        if (i2 == 0) {
            return getBitStream();
        }
        throw new IOException("expected octet-aligned bitstring, but found padBits: " + i2);
    }

    public byte[] getOctets() {
        byte[] bArr = this.f12677a;
        if (bArr[0] == 0) {
            return Arrays.copyOfRange(bArr, 1, bArr.length);
        }
        throw new IllegalStateException("attempt to get non-octet aligned data from BIT STRING");
    }

    @Override // org.bouncycastle.asn1.ASN1BitStringParser
    public int getPadBits() {
        return this.f12677a[0] & 255;
    }

    @Override // org.bouncycastle.asn1.ASN1String
    public String getString() {
        try {
            byte[] encoded = getEncoded();
            StringBuffer stringBuffer = new StringBuffer((encoded.length * 2) + 1);
            stringBuffer.append('#');
            for (int i2 = 0; i2 != encoded.length; i2++) {
                byte b2 = encoded[i2];
                char[] cArr = table;
                stringBuffer.append(cArr[(b2 >>> 4) & 15]);
                stringBuffer.append(cArr[b2 & Ascii.SI]);
            }
            return stringBuffer.toString();
        } catch (IOException e2) {
            throw new ASN1ParsingException("Internal error encoding BitString: " + e2.getMessage(), e2);
        }
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        byte[] bArr = this.f12677a;
        if (bArr.length < 2) {
            return 1;
        }
        int length = bArr.length - 1;
        return (Arrays.hashCode(bArr, 0, length) * 257) ^ ((byte) (bArr[length] & (255 << (bArr[0] & 255))));
    }

    public int intValue() {
        int min = Math.min(5, this.f12677a.length - 1);
        int i2 = 0;
        for (int i3 = 1; i3 < min; i3++) {
            i2 |= (255 & this.f12677a[i3]) << ((i3 - 1) * 8);
        }
        if (1 > min || min >= 5) {
            return i2;
        }
        byte[] bArr = this.f12677a;
        return i2 | ((((byte) (bArr[min] & (255 << (bArr[0] & 255)))) & 255) << ((min - 1) * 8));
    }

    public ASN1BitStringParser parser() {
        return this;
    }

    public String toString() {
        return getString();
    }
}
