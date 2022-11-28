package org.bouncycastle.math.field;

import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
class GF2Polynomial implements Polynomial {

    /* renamed from: a  reason: collision with root package name */
    protected final int[] f14353a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GF2Polynomial(int[] iArr) {
        this.f14353a = Arrays.clone(iArr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GF2Polynomial) {
            return Arrays.areEqual(this.f14353a, ((GF2Polynomial) obj).f14353a);
        }
        return false;
    }

    @Override // org.bouncycastle.math.field.Polynomial
    public int getDegree() {
        int[] iArr = this.f14353a;
        return iArr[iArr.length - 1];
    }

    @Override // org.bouncycastle.math.field.Polynomial
    public int[] getExponentsPresent() {
        return Arrays.clone(this.f14353a);
    }

    public int hashCode() {
        return Arrays.hashCode(this.f14353a);
    }
}
