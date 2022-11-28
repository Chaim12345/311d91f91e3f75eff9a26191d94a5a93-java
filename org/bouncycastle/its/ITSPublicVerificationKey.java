package org.bouncycastle.its;

import org.bouncycastle.oer.its.PublicVerificationKey;
/* loaded from: classes3.dex */
public class ITSPublicVerificationKey {

    /* renamed from: a  reason: collision with root package name */
    protected final PublicVerificationKey f13607a;

    public ITSPublicVerificationKey(PublicVerificationKey publicVerificationKey) {
        this.f13607a = publicVerificationKey;
    }

    public PublicVerificationKey toASN1Structure() {
        return this.f13607a;
    }
}
