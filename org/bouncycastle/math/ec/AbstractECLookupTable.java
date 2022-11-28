package org.bouncycastle.math.ec;
/* loaded from: classes3.dex */
public abstract class AbstractECLookupTable implements ECLookupTable {
    @Override // org.bouncycastle.math.ec.ECLookupTable
    public ECPoint lookupVar(int i2) {
        return lookup(i2);
    }
}
