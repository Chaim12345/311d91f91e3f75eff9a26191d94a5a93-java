package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
@Immutable
/* loaded from: classes2.dex */
final class MacHashFunction extends AbstractHashFunction {
    private final int bits;
    private final Key key;
    private final Mac prototype;
    private final boolean supportsClone;
    private final String toString;

    /* loaded from: classes2.dex */
    private static final class MacHasher extends AbstractByteHasher {
        private boolean done;
        private final Mac mac;

        private MacHasher(Mac mac) {
            this.mac = mac;
        }

        private void checkNotDone() {
            Preconditions.checkState(!this.done, "Cannot re-use a Hasher after calling hash() on it");
        }

        @Override // com.google.common.hash.AbstractByteHasher
        protected void a(ByteBuffer byteBuffer) {
            checkNotDone();
            Preconditions.checkNotNull(byteBuffer);
            this.mac.update(byteBuffer);
        }

        @Override // com.google.common.hash.AbstractByteHasher
        protected void b(byte[] bArr) {
            checkNotDone();
            this.mac.update(bArr);
        }

        @Override // com.google.common.hash.AbstractByteHasher
        protected void c(byte[] bArr, int i2, int i3) {
            checkNotDone();
            this.mac.update(bArr, i2, i3);
        }

        @Override // com.google.common.hash.Hasher
        public HashCode hash() {
            checkNotDone();
            this.done = true;
            return HashCode.b(this.mac.doFinal());
        }

        @Override // com.google.common.hash.AbstractByteHasher
        protected void update(byte b2) {
            checkNotDone();
            this.mac.update(b2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MacHashFunction(String str, Key key, String str2) {
        Mac mac = getMac(str, key);
        this.prototype = mac;
        this.key = (Key) Preconditions.checkNotNull(key);
        this.toString = (String) Preconditions.checkNotNull(str2);
        this.bits = mac.getMacLength() * 8;
        this.supportsClone = supportsClone(mac);
    }

    private static Mac getMac(String str, Key key) {
        try {
            Mac mac = Mac.getInstance(str);
            mac.init(key);
            return mac;
        } catch (InvalidKeyException e2) {
            throw new IllegalArgumentException(e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new IllegalStateException(e3);
        }
    }

    private static boolean supportsClone(Mac mac) {
        try {
            mac.clone();
            return true;
        } catch (CloneNotSupportedException unused) {
            return false;
        }
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return this.bits;
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        if (this.supportsClone) {
            try {
                return new MacHasher((Mac) this.prototype.clone());
            } catch (CloneNotSupportedException unused) {
            }
        }
        return new MacHasher(getMac(this.prototype.getAlgorithm(), this.key));
    }

    public String toString() {
        return this.toString;
    }
}
