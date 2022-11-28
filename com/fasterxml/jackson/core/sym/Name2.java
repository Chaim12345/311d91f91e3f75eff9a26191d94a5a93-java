package com.fasterxml.jackson.core.sym;
/* loaded from: classes.dex */
public final class Name2 extends Name {
    private final int q1;
    private final int q2;

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int i2) {
        return false;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int i2, int i3) {
        return i2 == this.q1 && i3 == this.q2;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int i2, int i3, int i4) {
        return false;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int[] iArr, int i2) {
        return i2 == 2 && iArr[0] == this.q1 && iArr[1] == this.q2;
    }
}
