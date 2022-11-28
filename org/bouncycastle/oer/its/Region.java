package org.bouncycastle.oer.its;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Integer;
/* loaded from: classes4.dex */
public class Region extends Uint16 {
    public Region(int i2) {
        super(i2);
    }

    public Region(BigInteger bigInteger) {
        super(bigInteger);
    }

    public static Region getInstance(Object obj) {
        return obj instanceof Region ? (Region) obj : new Region(ASN1Integer.getInstance(obj).getValue());
    }
}
