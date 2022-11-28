package org.bouncycastle.oer;

import java.security.AccessController;
import java.security.PrivilegedAction;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
/* loaded from: classes4.dex */
public class OEROptional extends ASN1Object {
    public static final OEROptional ABSENT = new OEROptional(false, null);
    private final boolean defined;
    private final ASN1Encodable value;

    private OEROptional(boolean z, ASN1Encodable aSN1Encodable) {
        this.defined = z;
        this.value = aSN1Encodable;
    }

    public static OEROptional getInstance(Object obj) {
        return obj instanceof OEROptional ? (OEROptional) obj : obj instanceof ASN1Encodable ? new OEROptional(true, (ASN1Encodable) obj) : ABSENT;
    }

    public static <T> T getValue(Class<T> cls, Object obj) {
        OEROptional oEROptional = getInstance(obj);
        if (oEROptional.defined) {
            return (T) oEROptional.getObject(cls);
        }
        return null;
    }

    @Override // org.bouncycastle.asn1.ASN1Object
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass() && super.equals(obj)) {
            OEROptional oEROptional = (OEROptional) obj;
            if (this.defined != oEROptional.defined) {
                return false;
            }
            ASN1Encodable aSN1Encodable = this.value;
            ASN1Encodable aSN1Encodable2 = oEROptional.value;
            return aSN1Encodable != null ? aSN1Encodable.equals(aSN1Encodable2) : aSN1Encodable2 == null;
        }
        return false;
    }

    public ASN1Encodable get() {
        return !this.defined ? ABSENT : this.value;
    }

    public <T> T getObject(final Class<T> cls) {
        if (this.defined) {
            return this.value.getClass().isInstance(cls) ? cls.cast(this.value) : (T) AccessController.doPrivileged(new PrivilegedAction<T>() { // from class: org.bouncycastle.oer.OEROptional.1
                /* JADX WARN: Type inference failed for: r0v4, types: [T, java.lang.Object] */
                @Override // java.security.PrivilegedAction
                public T run() {
                    try {
                        return cls.cast(cls.getMethod("getInstance", Object.class).invoke(null, OEROptional.this.value));
                    } catch (Exception e2) {
                        throw new IllegalStateException("could not invoke getInstance on type " + e2.getMessage(), e2);
                    }
                }
            });
        }
        return null;
    }

    @Override // org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        int hashCode = ((super.hashCode() * 31) + (this.defined ? 1 : 0)) * 31;
        ASN1Encodable aSN1Encodable = this.value;
        return hashCode + (aSN1Encodable != null ? aSN1Encodable.hashCode() : 0);
    }

    public boolean isDefined() {
        return this.defined;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        if (this.defined) {
            return get().toASN1Primitive();
        }
        throw new RuntimeException("bang");
    }

    public String toString() {
        if (this.defined) {
            return "OPTIONAL(" + this.value + ")";
        }
        return "ABSENT";
    }
}
