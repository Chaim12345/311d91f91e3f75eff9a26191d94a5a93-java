package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Aead;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import javax.crypto.AEADBadTagException;
/* loaded from: classes2.dex */
abstract class ChaCha20Poly1305Base implements Aead {
    private final ChaCha20Base chacha20;
    private final ChaCha20Base macKeyChaCha20;

    public ChaCha20Poly1305Base(byte[] bArr) {
        this.chacha20 = a(bArr, 1);
        this.macKeyChaCha20 = a(bArr, 0);
    }

    private byte[] decrypt(ByteBuffer byteBuffer, byte[] bArr) {
        if (byteBuffer.remaining() >= this.chacha20.e() + 16) {
            int position = byteBuffer.position();
            byte[] bArr2 = new byte[16];
            byteBuffer.position(byteBuffer.limit() - 16);
            byteBuffer.get(bArr2);
            byteBuffer.position(position);
            byteBuffer.limit(byteBuffer.limit() - 16);
            byte[] bArr3 = new byte[this.chacha20.e()];
            byteBuffer.get(bArr3);
            if (bArr == null) {
                bArr = new byte[0];
            }
            try {
                Poly1305.b(getMacKey(bArr3), macDataRfc8439(bArr, byteBuffer), bArr2);
                byteBuffer.position(position);
                return this.chacha20.c(byteBuffer);
            } catch (GeneralSecurityException e2) {
                throw new AEADBadTagException(e2.toString());
            }
        }
        throw new GeneralSecurityException("ciphertext too short");
    }

    private void encrypt(ByteBuffer byteBuffer, byte[] bArr, byte[] bArr2) {
        if (byteBuffer.remaining() < bArr.length + this.chacha20.e() + 16) {
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        int position = byteBuffer.position();
        this.chacha20.d(byteBuffer, bArr);
        byteBuffer.position(position);
        byte[] bArr3 = new byte[this.chacha20.e()];
        byteBuffer.get(bArr3);
        byteBuffer.limit(byteBuffer.limit() - 16);
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        byte[] a2 = Poly1305.a(getMacKey(bArr3), macDataRfc8439(bArr2, byteBuffer));
        byteBuffer.limit(byteBuffer.limit() + 16);
        byteBuffer.put(a2);
    }

    private byte[] getMacKey(byte[] bArr) {
        byte[] bArr2 = new byte[32];
        this.macKeyChaCha20.a(bArr, 0).get(bArr2);
        return bArr2;
    }

    private static byte[] macDataRfc8439(byte[] bArr, ByteBuffer byteBuffer) {
        int length = bArr.length % 16 == 0 ? bArr.length : (bArr.length + 16) - (bArr.length % 16);
        int remaining = byteBuffer.remaining();
        int i2 = remaining % 16;
        int i3 = (i2 == 0 ? remaining : (remaining + 16) - i2) + length;
        ByteBuffer order = ByteBuffer.allocate(i3 + 16).order(ByteOrder.LITTLE_ENDIAN);
        order.put(bArr);
        order.position(length);
        order.put(byteBuffer);
        order.position(i3);
        order.putLong(bArr.length);
        order.putLong(remaining);
        return order.array();
    }

    abstract ChaCha20Base a(byte[] bArr, int i2);

    @Override // com.google.crypto.tink.Aead
    public byte[] decrypt(byte[] bArr, byte[] bArr2) {
        return decrypt(ByteBuffer.wrap(bArr), bArr2);
    }

    @Override // com.google.crypto.tink.Aead
    public byte[] encrypt(byte[] bArr, byte[] bArr2) {
        if (bArr.length <= (Integer.MAX_VALUE - this.chacha20.e()) - 16) {
            ByteBuffer allocate = ByteBuffer.allocate(bArr.length + this.chacha20.e() + 16);
            encrypt(allocate, bArr, bArr2);
            return allocate.array();
        }
        throw new GeneralSecurityException("plaintext too long");
    }
}
