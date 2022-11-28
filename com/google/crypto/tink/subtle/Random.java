package com.google.crypto.tink.subtle;

import java.security.SecureRandom;
/* loaded from: classes2.dex */
public final class Random {
    private static final ThreadLocal<SecureRandom> localRandom = new ThreadLocal<SecureRandom>() { // from class: com.google.crypto.tink.subtle.Random.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public SecureRandom initialValue() {
            return Random.newDefaultSecureRandom();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static SecureRandom newDefaultSecureRandom() {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextLong();
        return secureRandom;
    }

    public static byte[] randBytes(int i2) {
        byte[] bArr = new byte[i2];
        localRandom.get().nextBytes(bArr);
        return bArr;
    }

    public static final int randInt() {
        return localRandom.get().nextInt();
    }

    public static final int randInt(int i2) {
        return localRandom.get().nextInt(i2);
    }
}
