package org.bouncycastle.crypto.params;

import java.security.SecureRandom;
/* loaded from: classes3.dex */
public class DSAParameterGenerationParameters {
    public static final int DIGITAL_SIGNATURE_USAGE = 1;
    public static final int KEY_ESTABLISHMENT_USAGE = 2;
    private final int certainty;

    /* renamed from: l  reason: collision with root package name */
    private final int f13475l;

    /* renamed from: n  reason: collision with root package name */
    private final int f13476n;
    private final SecureRandom random;
    private final int usageIndex;

    public DSAParameterGenerationParameters(int i2, int i3, int i4, SecureRandom secureRandom) {
        this(i2, i3, i4, secureRandom, -1);
    }

    public DSAParameterGenerationParameters(int i2, int i3, int i4, SecureRandom secureRandom, int i5) {
        this.f13475l = i2;
        this.f13476n = i3;
        this.certainty = i4;
        this.usageIndex = i5;
        this.random = secureRandom;
    }

    public int getCertainty() {
        return this.certainty;
    }

    public int getL() {
        return this.f13475l;
    }

    public int getN() {
        return this.f13476n;
    }

    public SecureRandom getRandom() {
        return this.random;
    }

    public int getUsageIndex() {
        return this.usageIndex;
    }
}
