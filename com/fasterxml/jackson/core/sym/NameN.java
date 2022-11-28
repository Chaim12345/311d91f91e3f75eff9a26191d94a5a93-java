package com.fasterxml.jackson.core.sym;

import java.util.Arrays;
/* loaded from: classes.dex */
public final class NameN extends Name {

    /* renamed from: q  reason: collision with root package name */
    private final int[] f5223q;
    private final int q1;
    private final int q2;
    private final int q3;
    private final int q4;
    private final int qlen;

    NameN(String str, int i2, int i3, int i4, int i5, int i6, int[] iArr, int i7) {
        super(str, i2);
        this.q1 = i3;
        this.q2 = i4;
        this.q3 = i5;
        this.q4 = i6;
        this.f5223q = iArr;
        this.qlen = i7;
    }

    private final boolean _equals2(int[] iArr) {
        int i2 = this.qlen - 4;
        for (int i3 = 0; i3 < i2; i3++) {
            if (iArr[i3 + 4] != this.f5223q[i3]) {
                return false;
            }
        }
        return true;
    }

    public static NameN construct(String str, int i2, int[] iArr, int i3) {
        if (i3 >= 4) {
            return new NameN(str, i2, iArr[0], iArr[1], iArr[2], iArr[3], i3 + (-4) > 0 ? Arrays.copyOfRange(iArr, 4, i3) : null, i3);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int i2) {
        return false;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int i2, int i3) {
        return false;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int i2, int i3, int i4) {
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0040 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0054 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0055 A[RETURN] */
    @Override // com.fasterxml.jackson.core.sym.Name
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean equals(int[] iArr, int i2) {
        if (i2 == this.qlen && iArr[0] == this.q1 && iArr[1] == this.q2 && iArr[2] == this.q3 && iArr[3] == this.q4) {
            switch (i2) {
                case 4:
                    return true;
                case 5:
                    if (iArr[4] == this.f5223q[0]) {
                        return false;
                    }
                    break;
                case 6:
                    if (iArr[5] != this.f5223q[1]) {
                        return false;
                    }
                    if (iArr[4] == this.f5223q[0]) {
                    }
                    break;
                case 7:
                    if (iArr[6] != this.f5223q[2]) {
                        return false;
                    }
                    if (iArr[5] != this.f5223q[1]) {
                    }
                    if (iArr[4] == this.f5223q[0]) {
                    }
                    break;
                case 8:
                    if (iArr[7] != this.f5223q[3]) {
                        return false;
                    }
                    if (iArr[6] != this.f5223q[2]) {
                    }
                    if (iArr[5] != this.f5223q[1]) {
                    }
                    if (iArr[4] == this.f5223q[0]) {
                    }
                    break;
                default:
                    return _equals2(iArr);
            }
        } else {
            return false;
        }
    }
}
