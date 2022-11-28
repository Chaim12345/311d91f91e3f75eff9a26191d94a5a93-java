package com.fasterxml.jackson.core.sym;
/* loaded from: classes.dex */
public abstract class Name {

    /* renamed from: a  reason: collision with root package name */
    protected final String f5220a;

    /* renamed from: b  reason: collision with root package name */
    protected final int f5221b;

    /* JADX INFO: Access modifiers changed from: protected */
    public Name(String str, int i2) {
        this.f5220a = str;
        this.f5221b = i2;
    }

    public abstract boolean equals(int i2);

    public abstract boolean equals(int i2, int i3);

    public abstract boolean equals(int i2, int i3, int i4);

    public boolean equals(Object obj) {
        return obj == this;
    }

    public abstract boolean equals(int[] iArr, int i2);

    public String getName() {
        return this.f5220a;
    }

    public final int hashCode() {
        return this.f5221b;
    }

    public String toString() {
        return this.f5220a;
    }
}
