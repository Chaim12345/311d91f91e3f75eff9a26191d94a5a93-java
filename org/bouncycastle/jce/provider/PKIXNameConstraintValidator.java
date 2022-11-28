package org.bouncycastle.jce.provider;

import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralSubtree;
import org.bouncycastle.asn1.x509.NameConstraintValidatorException;
/* loaded from: classes3.dex */
public class PKIXNameConstraintValidator {

    /* renamed from: a  reason: collision with root package name */
    org.bouncycastle.asn1.x509.PKIXNameConstraintValidator f13824a = new org.bouncycastle.asn1.x509.PKIXNameConstraintValidator();

    public void addExcludedSubtree(GeneralSubtree generalSubtree) {
        this.f13824a.addExcludedSubtree(generalSubtree);
    }

    public void checkExcluded(GeneralName generalName) {
        try {
            this.f13824a.checkExcluded(generalName);
        } catch (NameConstraintValidatorException e2) {
            throw new PKIXNameConstraintValidatorException(e2.getMessage(), e2);
        }
    }

    public void checkExcludedDN(ASN1Sequence aSN1Sequence) {
        try {
            this.f13824a.checkExcludedDN(X500Name.getInstance(aSN1Sequence));
        } catch (NameConstraintValidatorException e2) {
            throw new PKIXNameConstraintValidatorException(e2.getMessage(), e2);
        }
    }

    public void checkPermitted(GeneralName generalName) {
        try {
            this.f13824a.checkPermitted(generalName);
        } catch (NameConstraintValidatorException e2) {
            throw new PKIXNameConstraintValidatorException(e2.getMessage(), e2);
        }
    }

    public void checkPermittedDN(ASN1Sequence aSN1Sequence) {
        try {
            this.f13824a.checkPermittedDN(X500Name.getInstance(aSN1Sequence));
        } catch (NameConstraintValidatorException e2) {
            throw new PKIXNameConstraintValidatorException(e2.getMessage(), e2);
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof PKIXNameConstraintValidator) {
            return this.f13824a.equals(((PKIXNameConstraintValidator) obj).f13824a);
        }
        return false;
    }

    public int hashCode() {
        return this.f13824a.hashCode();
    }

    public void intersectEmptyPermittedSubtree(int i2) {
        this.f13824a.intersectEmptyPermittedSubtree(i2);
    }

    public void intersectPermittedSubtree(GeneralSubtree generalSubtree) {
        this.f13824a.intersectPermittedSubtree(generalSubtree);
    }

    public void intersectPermittedSubtree(GeneralSubtree[] generalSubtreeArr) {
        this.f13824a.intersectPermittedSubtree(generalSubtreeArr);
    }

    public String toString() {
        return this.f13824a.toString();
    }
}
