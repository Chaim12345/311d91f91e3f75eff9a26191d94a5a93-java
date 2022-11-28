package org.bouncycastle.jce.provider;

import java.util.Date;
/* loaded from: classes3.dex */
class CertStatus {
    public static final int UNDETERMINED = 12;
    public static final int UNREVOKED = 11;

    /* renamed from: a  reason: collision with root package name */
    int f13812a = 11;

    /* renamed from: b  reason: collision with root package name */
    Date f13813b = null;

    public int getCertStatus() {
        return this.f13812a;
    }

    public Date getRevocationDate() {
        return this.f13813b;
    }

    public void setCertStatus(int i2) {
        this.f13812a = i2;
    }

    public void setRevocationDate(Date date) {
        this.f13813b = date;
    }
}
