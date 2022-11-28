package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeySign;
import java.util.Arrays;
/* loaded from: classes2.dex */
public final class Ed25519Sign implements PublicKeySign {
    public static final int SECRET_KEY_LEN = 32;
    private final byte[] hashedPrivateKey;
    private final byte[] publicKey;

    /* loaded from: classes2.dex */
    public static final class KeyPair {
        private final byte[] privateKey;
        private final byte[] publicKey;

        private KeyPair(byte[] bArr, byte[] bArr2) {
            this.publicKey = bArr;
            this.privateKey = bArr2;
        }

        public static KeyPair newKeyPair() {
            byte[] randBytes = Random.randBytes(32);
            return new KeyPair(Ed25519.f(Ed25519.e(randBytes)), randBytes);
        }

        public byte[] getPrivateKey() {
            byte[] bArr = this.privateKey;
            return Arrays.copyOf(bArr, bArr.length);
        }

        public byte[] getPublicKey() {
            byte[] bArr = this.publicKey;
            return Arrays.copyOf(bArr, bArr.length);
        }
    }

    public Ed25519Sign(byte[] bArr) {
        if (bArr.length != 32) {
            throw new IllegalArgumentException(String.format("Given private key's length is not %s", 32));
        }
        byte[] e2 = Ed25519.e(bArr);
        this.hashedPrivateKey = e2;
        this.publicKey = Ed25519.f(e2);
    }

    @Override // com.google.crypto.tink.PublicKeySign
    public byte[] sign(byte[] bArr) {
        return Ed25519.g(bArr, this.publicKey, this.hashedPrivateKey);
    }
}
