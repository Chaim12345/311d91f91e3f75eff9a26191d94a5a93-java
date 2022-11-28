package org.bouncycastle.pqc.crypto.qtesla;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;
/* loaded from: classes4.dex */
public class QTESLAKeyGenerationParameters extends KeyGenerationParameters {
    private final int securityCategory;

    public QTESLAKeyGenerationParameters(int i2, SecureRandom secureRandom) {
        super(secureRandom, -1);
        QTESLASecurityCategory.a(i2);
        this.securityCategory = i2;
    }

    public int getSecurityCategory() {
        return this.securityCategory;
    }
}
