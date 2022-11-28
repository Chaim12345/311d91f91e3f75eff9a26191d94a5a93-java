package org.bouncycastle.pqc.crypto.lms;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.util.Encodable;
/* loaded from: classes4.dex */
public abstract class LMSKeyParameters extends AsymmetricKeyParameter implements Encodable {
    /* JADX INFO: Access modifiers changed from: protected */
    public LMSKeyParameters(boolean z) {
        super(z);
    }

    public abstract byte[] getEncoded();
}
