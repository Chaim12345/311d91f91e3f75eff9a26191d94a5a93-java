package org.bouncycastle.pqc.crypto.sphincsplus;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
/* loaded from: classes4.dex */
public class SPHINCSPlusKeyParameters extends AsymmetricKeyParameter {

    /* renamed from: b  reason: collision with root package name */
    final SPHINCSPlusParameters f14603b;

    /* JADX INFO: Access modifiers changed from: protected */
    public SPHINCSPlusKeyParameters(boolean z, SPHINCSPlusParameters sPHINCSPlusParameters) {
        super(z);
        this.f14603b = sPHINCSPlusParameters;
    }

    public SPHINCSPlusParameters getParameters() {
        return this.f14603b;
    }
}
