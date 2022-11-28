package org.bouncycastle.asn1.x509;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes3.dex */
public class BasicConstraints extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    ASN1Boolean f12933a;

    /* renamed from: b  reason: collision with root package name */
    ASN1Integer f12934b;

    public BasicConstraints(int i2) {
        this.f12933a = ASN1Boolean.getInstance(false);
        this.f12934b = null;
        this.f12933a = ASN1Boolean.getInstance(true);
        this.f12934b = new ASN1Integer(i2);
    }

    private BasicConstraints(ASN1Sequence aSN1Sequence) {
        this.f12933a = ASN1Boolean.getInstance(false);
        this.f12934b = null;
        if (aSN1Sequence.size() == 0) {
            this.f12933a = null;
            this.f12934b = null;
            return;
        }
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1Boolean) {
            this.f12933a = ASN1Boolean.getInstance(aSN1Sequence.getObjectAt(0));
        } else {
            this.f12933a = null;
            this.f12934b = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        }
        if (aSN1Sequence.size() > 1) {
            if (this.f12933a == null) {
                throw new IllegalArgumentException("wrong sequence in constructor");
            }
            this.f12934b = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1));
        }
    }

    public BasicConstraints(boolean z) {
        this.f12933a = ASN1Boolean.getInstance(false);
        this.f12934b = null;
        if (z) {
            this.f12933a = ASN1Boolean.getInstance(true);
        } else {
            this.f12933a = null;
        }
        this.f12934b = null;
    }

    public static BasicConstraints fromExtensions(Extensions extensions) {
        return getInstance(Extensions.getExtensionParsedValue(extensions, Extension.basicConstraints));
    }

    public static BasicConstraints getInstance(Object obj) {
        if (obj instanceof BasicConstraints) {
            return (BasicConstraints) obj;
        }
        if (obj instanceof X509Extension) {
            return getInstance(X509Extension.convertValueToObject((X509Extension) obj));
        }
        if (obj != null) {
            return new BasicConstraints(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static BasicConstraints getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public BigInteger getPathLenConstraint() {
        ASN1Integer aSN1Integer = this.f12934b;
        if (aSN1Integer != null) {
            return aSN1Integer.getValue();
        }
        return null;
    }

    public boolean isCA() {
        ASN1Boolean aSN1Boolean = this.f12933a;
        return aSN1Boolean != null && aSN1Boolean.isTrue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        ASN1Boolean aSN1Boolean = this.f12933a;
        if (aSN1Boolean != null) {
            aSN1EncodableVector.add(aSN1Boolean);
        }
        ASN1Integer aSN1Integer = this.f12934b;
        if (aSN1Integer != null) {
            aSN1EncodableVector.add(aSN1Integer);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        StringBuilder sb;
        if (this.f12934b == null) {
            sb = new StringBuilder();
            sb.append("BasicConstraints: isCa(");
            sb.append(isCA());
            sb.append(")");
        } else {
            sb = new StringBuilder();
            sb.append("BasicConstraints: isCa(");
            sb.append(isCA());
            sb.append("), pathLenConstraint = ");
            sb.append(this.f12934b.getValue());
        }
        return sb.toString();
    }
}
