package org.bouncycastle.crypto.signers;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;
/* loaded from: classes3.dex */
public class StandardDSAEncoding implements DSAEncoding {
    public static final StandardDSAEncoding INSTANCE = new StandardDSAEncoding();

    protected BigInteger a(BigInteger bigInteger, BigInteger bigInteger2) {
        if (bigInteger2.signum() < 0 || (bigInteger != null && bigInteger2.compareTo(bigInteger) >= 0)) {
            throw new IllegalArgumentException("Value out of range");
        }
        return bigInteger2;
    }

    protected BigInteger b(BigInteger bigInteger, ASN1Sequence aSN1Sequence, int i2) {
        return a(bigInteger, ((ASN1Integer) aSN1Sequence.getObjectAt(i2)).getValue());
    }

    protected void c(BigInteger bigInteger, ASN1EncodableVector aSN1EncodableVector, BigInteger bigInteger2) {
        aSN1EncodableVector.add(new ASN1Integer(a(bigInteger, bigInteger2)));
    }

    @Override // org.bouncycastle.crypto.signers.DSAEncoding
    public BigInteger[] decode(BigInteger bigInteger, byte[] bArr) {
        ASN1Sequence aSN1Sequence = (ASN1Sequence) ASN1Primitive.fromByteArray(bArr);
        if (aSN1Sequence.size() == 2) {
            BigInteger b2 = b(bigInteger, aSN1Sequence, 0);
            BigInteger b3 = b(bigInteger, aSN1Sequence, 1);
            if (Arrays.areEqual(encode(bigInteger, b2, b3), bArr)) {
                return new BigInteger[]{b2, b3};
            }
        }
        throw new IllegalArgumentException("Malformed signature");
    }

    @Override // org.bouncycastle.crypto.signers.DSAEncoding
    public byte[] encode(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        c(bigInteger, aSN1EncodableVector, bigInteger2);
        c(bigInteger, aSN1EncodableVector, bigInteger3);
        return new DERSequence(aSN1EncodableVector).getEncoded(ASN1Encoding.DER);
    }
}
