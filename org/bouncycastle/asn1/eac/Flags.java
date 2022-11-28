package org.bouncycastle.asn1.eac;
/* loaded from: classes3.dex */
public class Flags {

    /* renamed from: a  reason: collision with root package name */
    int f12780a;

    /* loaded from: classes3.dex */
    private static class StringJoiner {

        /* renamed from: a  reason: collision with root package name */
        String f12781a;

        /* renamed from: b  reason: collision with root package name */
        boolean f12782b = true;

        /* renamed from: c  reason: collision with root package name */
        StringBuffer f12783c = new StringBuffer();

        public StringJoiner(String str) {
            this.f12781a = str;
        }

        public void add(String str) {
            if (this.f12782b) {
                this.f12782b = false;
            } else {
                this.f12783c.append(this.f12781a);
            }
            this.f12783c.append(str);
        }

        public String toString() {
            return this.f12783c.toString();
        }
    }

    public Flags() {
        this.f12780a = 0;
    }

    public Flags(int i2) {
        this.f12780a = 0;
        this.f12780a = i2;
    }

    public int getFlags() {
        return this.f12780a;
    }

    public boolean isSet(int i2) {
        return (i2 & this.f12780a) != 0;
    }

    public void set(int i2) {
        this.f12780a = i2 | this.f12780a;
    }
}
