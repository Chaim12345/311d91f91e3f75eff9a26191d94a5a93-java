package org.bouncycastle.asn1.pkcs;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.util.Arrays;
/* loaded from: classes3.dex */
public class MacData extends ASN1Object {
    private static final BigInteger ONE = BigInteger.valueOf(1);

    /* renamed from: a  reason: collision with root package name */
    DigestInfo f12842a;

    /* renamed from: b  reason: collision with root package name */
    byte[] f12843b;

    /* renamed from: c  reason: collision with root package name */
    BigInteger f12844c;

    private MacData(ASN1Sequence aSN1Sequence) {
        this.f12842a = DigestInfo.getInstance(aSN1Sequence.getObjectAt(0));
        this.f12843b = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1)).getOctets());
        this.f12844c = aSN1Sequence.size() == 3 ? ASN1Integer.getInstance(aSN1Sequence.getObjectAt(2)).getValue() : ONE;
    }

    public MacData(DigestInfo digestInfo, byte[] bArr, int i2) {
        this.f12842a = digestInfo;
        this.f12843b = Arrays.clone(bArr);
        this.f12844c = BigInteger.valueOf(i2);
    }

    public static MacData getInstance(Object obj) {
        if (obj instanceof MacData) {
            return (MacData) obj;
        }
        if (obj != null) {
            return new MacData(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BigInteger getIterationCount() {
        return this.f12844c;
    }

    public DigestInfo getMac() {
        return this.f12842a;
    }

    public byte[] getSalt() {
        return Arrays.clone(this.f12843b);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(3);
        aSN1EncodableVector.add(this.f12842a);
        aSN1EncodableVector.add(new DEROctetString(this.f12843b));
        if (!this.f12844c.equals(ONE)) {
            aSN1EncodableVector.add(new ASN1Integer(this.f12844c));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
