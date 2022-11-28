package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes3.dex */
public class AsymmetricKeyParameter implements CipherParameters {

    /* renamed from: a  reason: collision with root package name */
    boolean f13464a;

    public AsymmetricKeyParameter(boolean z) {
        this.f13464a = z;
    }

    public boolean isPrivate() {
        return this.f13464a;
    }
}
