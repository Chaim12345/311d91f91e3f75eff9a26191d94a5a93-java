package org.bouncycastle.pqc.crypto.newhope;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class NHPrivateKeyParameters extends AsymmetricKeyParameter {

    /* renamed from: b  reason: collision with root package name */
    final short[] f14556b;

    public NHPrivateKeyParameters(short[] sArr) {
        super(true);
        this.f14556b = Arrays.clone(sArr);
    }

    public short[] getSecData() {
        return Arrays.clone(this.f14556b);
    }
}
