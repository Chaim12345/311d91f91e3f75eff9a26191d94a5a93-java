package org.bouncycastle.asn1.x9;
/* loaded from: classes3.dex */
public abstract class X9ECParametersHolder {
    private X9ECParameters params;

    protected abstract X9ECParameters a();

    public synchronized X9ECParameters getParameters() {
        if (this.params == null) {
            this.params = a();
        }
        return this.params;
    }
}
