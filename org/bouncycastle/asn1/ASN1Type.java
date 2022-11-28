package org.bouncycastle.asn1;
/* loaded from: classes3.dex */
abstract class ASN1Type {

    /* renamed from: a  reason: collision with root package name */
    final Class f12725a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Type(Class cls) {
        this.f12725a = cls;
    }

    public final boolean equals(Object obj) {
        return this == obj;
    }

    public final int hashCode() {
        return super.hashCode();
    }
}
