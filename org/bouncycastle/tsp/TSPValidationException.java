package org.bouncycastle.tsp;
/* loaded from: classes4.dex */
public class TSPValidationException extends TSPException {
    private int failureCode;

    public TSPValidationException(String str) {
        super(str);
        this.failureCode = -1;
    }

    public TSPValidationException(String str, int i2) {
        super(str);
        this.failureCode = -1;
        this.failureCode = i2;
    }

    public int getFailureCode() {
        return this.failureCode;
    }
}
