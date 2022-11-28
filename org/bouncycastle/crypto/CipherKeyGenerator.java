package org.bouncycastle.crypto;

import java.security.SecureRandom;
/* loaded from: classes3.dex */
public class CipherKeyGenerator {

    /* renamed from: a  reason: collision with root package name */
    protected SecureRandom f13240a;

    /* renamed from: b  reason: collision with root package name */
    protected int f13241b;

    public byte[] generateKey() {
        byte[] bArr = new byte[this.f13241b];
        this.f13240a.nextBytes(bArr);
        return bArr;
    }

    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.f13240a = keyGenerationParameters.getRandom();
        this.f13241b = (keyGenerationParameters.getStrength() + 7) / 8;
    }
}
