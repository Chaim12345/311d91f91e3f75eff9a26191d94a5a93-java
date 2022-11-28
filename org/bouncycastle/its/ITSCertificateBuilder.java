package org.bouncycastle.its;

import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.oer.its.CrlSeries;
import org.bouncycastle.oer.its.HashedId;
import org.bouncycastle.oer.its.PsidGroupPermissions;
import org.bouncycastle.oer.its.PsidSsp;
import org.bouncycastle.oer.its.SequenceOfPsidGroupPermissions;
import org.bouncycastle.oer.its.SequenceOfPsidSsp;
import org.bouncycastle.oer.its.ToBeSignedCertificate;
/* loaded from: classes3.dex */
public class ITSCertificateBuilder {

    /* renamed from: a  reason: collision with root package name */
    protected final ToBeSignedCertificate.Builder f13601a;

    /* renamed from: b  reason: collision with root package name */
    protected final ITSCertificate f13602b;

    /* renamed from: c  reason: collision with root package name */
    protected ASN1Integer f13603c;

    /* renamed from: d  reason: collision with root package name */
    protected HashedId f13604d;

    /* renamed from: e  reason: collision with root package name */
    protected CrlSeries f13605e;

    public ITSCertificateBuilder(ITSCertificate iTSCertificate, ToBeSignedCertificate.Builder builder) {
        this.f13603c = new ASN1Integer(3L);
        this.f13604d = new HashedId.HashedId3(new byte[3]);
        this.f13605e = new CrlSeries(0);
        this.f13602b = iTSCertificate;
        this.f13601a = builder;
        builder.setCracaId(this.f13604d);
        builder.setCrlSeries(this.f13605e);
    }

    public ITSCertificateBuilder(ToBeSignedCertificate.Builder builder) {
        this(null, builder);
    }

    public ITSCertificate getIssuer() {
        return this.f13602b;
    }

    public ITSCertificateBuilder setAppPermissions(PsidSsp... psidSspArr) {
        SequenceOfPsidSsp.Builder builder = SequenceOfPsidSsp.builder();
        for (int i2 = 0; i2 != psidSspArr.length; i2++) {
            builder.setItem(psidSspArr[i2]);
        }
        this.f13601a.setAppPermissions(builder.createSequenceOfPsidSsp());
        return this;
    }

    public ITSCertificateBuilder setCertIssuePermissions(PsidGroupPermissions... psidGroupPermissionsArr) {
        this.f13601a.setCertIssuePermissions(SequenceOfPsidGroupPermissions.builder().addGroupPermission(psidGroupPermissionsArr).createSequenceOfPsidGroupPermissions());
        return this;
    }

    public ITSCertificateBuilder setCracaId(byte[] bArr) {
        HashedId.HashedId3 hashedId3 = new HashedId.HashedId3(bArr);
        this.f13604d = hashedId3;
        this.f13601a.setCracaId(hashedId3);
        return this;
    }

    public ITSCertificateBuilder setCrlSeries(int i2) {
        CrlSeries crlSeries = new CrlSeries(i2);
        this.f13605e = crlSeries;
        this.f13601a.setCrlSeries(crlSeries);
        return this;
    }

    public ITSCertificateBuilder setValidityPeriod(ITSValidityPeriod iTSValidityPeriod) {
        this.f13601a.setValidityPeriod(iTSValidityPeriod.toASN1Structure());
        return this;
    }

    public ITSCertificateBuilder setVersion(int i2) {
        this.f13603c = new ASN1Integer(i2);
        return this;
    }
}
