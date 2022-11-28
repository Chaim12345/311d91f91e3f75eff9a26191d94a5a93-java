package org.bouncycastle.x509;

import java.util.Date;
/* loaded from: classes4.dex */
class CertStatus {
    public static final int UNDETERMINED = 12;
    public static final int UNREVOKED = 11;

    /* renamed from: a  reason: collision with root package name */
    int f15136a;

    /* renamed from: b  reason: collision with root package name */
    Date f15137b;

    public int getCertStatus() {
        return this.f15136a;
    }

    public Date getRevocationDate() {
        return this.f15137b;
    }

    public void setCertStatus(int i2) {
        this.f15136a = i2;
    }

    public void setRevocationDate(Date date) {
        this.f15137b = date;
    }
}
