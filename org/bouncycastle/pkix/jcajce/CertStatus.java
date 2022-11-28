package org.bouncycastle.pkix.jcajce;

import java.util.Date;
/* loaded from: classes4.dex */
class CertStatus {
    public static final int UNDETERMINED = 12;
    public static final int UNREVOKED = 11;

    /* renamed from: a  reason: collision with root package name */
    int f14476a = 11;

    /* renamed from: b  reason: collision with root package name */
    Date f14477b = null;

    public int getCertStatus() {
        return this.f14476a;
    }

    public Date getRevocationDate() {
        return this.f14477b;
    }

    public void setCertStatus(int i2) {
        this.f14476a = i2;
    }

    public void setRevocationDate(Date date) {
        this.f14477b = date;
    }
}
