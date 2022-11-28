package org.bouncycastle.cert.ocsp;

import java.util.Date;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ocsp.RevokedInfo;
import org.bouncycastle.asn1.x509.CRLReason;
/* loaded from: classes3.dex */
public class RevokedStatus implements CertificateStatus {

    /* renamed from: a  reason: collision with root package name */
    RevokedInfo f13097a;

    public RevokedStatus(Date date, int i2) {
        this.f13097a = new RevokedInfo(new ASN1GeneralizedTime(date), CRLReason.lookup(i2));
    }

    public RevokedStatus(RevokedInfo revokedInfo) {
        this.f13097a = revokedInfo;
    }

    public int getRevocationReason() {
        if (this.f13097a.getRevocationReason() != null) {
            return this.f13097a.getRevocationReason().getValue().intValue();
        }
        throw new IllegalStateException("attempt to get a reason where none is available");
    }

    public Date getRevocationTime() {
        return OCSPUtils.a(this.f13097a.getRevocationTime());
    }

    public boolean hasRevocationReason() {
        return this.f13097a.getRevocationReason() != null;
    }
}
