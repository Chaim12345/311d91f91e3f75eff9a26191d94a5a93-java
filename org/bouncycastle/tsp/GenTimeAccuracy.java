package org.bouncycastle.tsp;

import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.tsp.Accuracy;
/* loaded from: classes4.dex */
public class GenTimeAccuracy {
    private Accuracy accuracy;

    public GenTimeAccuracy(Accuracy accuracy) {
        this.accuracy = accuracy;
    }

    private String format(int i2) {
        StringBuilder sb;
        String str;
        if (i2 < 10) {
            sb = new StringBuilder();
            str = "00";
        } else if (i2 >= 100) {
            return Integer.toString(i2);
        } else {
            sb = new StringBuilder();
            str = "0";
        }
        sb.append(str);
        sb.append(i2);
        return sb.toString();
    }

    private int getTimeComponent(ASN1Integer aSN1Integer) {
        if (aSN1Integer != null) {
            return aSN1Integer.intValueExact();
        }
        return 0;
    }

    public int getMicros() {
        return getTimeComponent(this.accuracy.getMicros());
    }

    public int getMillis() {
        return getTimeComponent(this.accuracy.getMillis());
    }

    public int getSeconds() {
        return getTimeComponent(this.accuracy.getSeconds());
    }

    public String toString() {
        return getSeconds() + "." + format(getMillis()) + format(getMicros());
    }
}
