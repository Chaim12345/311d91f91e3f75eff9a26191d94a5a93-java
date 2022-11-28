package org.bouncycastle.oer.its;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
/* loaded from: classes4.dex */
public class Uint8 extends ASN1Object {
    private final int value;

    public Uint8(int i2) {
        this.value = b(i2);
    }

    public Uint8(BigInteger bigInteger) {
        this.value = bigInteger.intValue();
    }

    public static Uint8 getInstance(Object obj) {
        return obj instanceof Uint8 ? (Uint8) obj : new Uint8(ASN1Integer.getInstance(obj).getValue());
    }

    protected int b(int i2) {
        if (i2 >= 0) {
            if (i2 <= 255) {
                return i2;
            }
            throw new IllegalArgumentException("Uint16 must be <= 0xFF");
        }
        throw new IllegalArgumentException("Uint16 must be >= 0");
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new ASN1Integer(this.value);
    }
}
