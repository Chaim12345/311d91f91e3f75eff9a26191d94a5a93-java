package com.fasterxml.jackson.core.sym;
/* loaded from: classes.dex */
public final class Name1 extends Name {
    private static final Name1 EMPTY = new Name1("", 0, 0);

    /* renamed from: q  reason: collision with root package name */
    private final int f5222q;

    Name1(String str, int i2, int i3) {
        super(str, i2);
        this.f5222q = i3;
    }

    public static Name1 getEmptyName() {
        return EMPTY;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int i2) {
        return i2 == this.f5222q;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int i2, int i3) {
        return i2 == this.f5222q && i3 == 0;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int i2, int i3, int i4) {
        return false;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int[] iArr, int i2) {
        return i2 == 1 && iArr[0] == this.f5222q;
    }
}
