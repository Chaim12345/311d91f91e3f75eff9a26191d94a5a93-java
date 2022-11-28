package org.bouncycastle.math.field;

import java.math.BigInteger;
/* loaded from: classes4.dex */
class PrimeField implements FiniteField {

    /* renamed from: a  reason: collision with root package name */
    protected final BigInteger f14356a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PrimeField(BigInteger bigInteger) {
        this.f14356a = bigInteger;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PrimeField) {
            return this.f14356a.equals(((PrimeField) obj).f14356a);
        }
        return false;
    }

    @Override // org.bouncycastle.math.field.FiniteField
    public BigInteger getCharacteristic() {
        return this.f14356a;
    }

    @Override // org.bouncycastle.math.field.FiniteField
    public int getDimension() {
        return 1;
    }

    public int hashCode() {
        return this.f14356a.hashCode();
    }
}
