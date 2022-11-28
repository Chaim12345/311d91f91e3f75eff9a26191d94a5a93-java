package com.fasterxml.jackson.core.sym;
/* loaded from: classes.dex */
public final class Name3 extends Name {
    private final int q1;
    private final int q2;
    private final int q3;

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
        return this.q1 == i2 && this.q2 == i3 && this.q3 == i4;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int[] iArr, int i2) {
        return i2 == 3 && iArr[0] == this.q1 && iArr[1] == this.q2 && iArr[2] == this.q3;
    }
}
