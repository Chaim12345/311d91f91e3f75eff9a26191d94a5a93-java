package org.bouncycastle.dvcs;

import org.bouncycastle.asn1.dvcs.Data;
/* loaded from: classes3.dex */
public abstract class DVCSRequestData {

    /* renamed from: a  reason: collision with root package name */
    protected Data f13526a;

    /* JADX INFO: Access modifiers changed from: protected */
    public DVCSRequestData(Data data) {
        this.f13526a = data;
    }

    public Data toASN1Structure() {
        return this.f13526a;
    }
}
