package org.bouncycastle.crypto;
/* loaded from: classes3.dex */
public class BufferedAsymmetricBlockCipher {

    /* renamed from: a  reason: collision with root package name */
    protected byte[] f13232a;

    /* renamed from: b  reason: collision with root package name */
    protected int f13233b;
    private final AsymmetricBlockCipher cipher;

    public BufferedAsymmetricBlockCipher(AsymmetricBlockCipher asymmetricBlockCipher) {
        this.cipher = asymmetricBlockCipher;
    }

    public byte[] doFinal() {
        byte[] processBlock = this.cipher.processBlock(this.f13232a, 0, this.f13233b);
        reset();
        return processBlock;
    }

    public int getBufferPosition() {
        return this.f13233b;
    }

    public int getInputBlockSize() {
        return this.cipher.getInputBlockSize();
    }

    public int getOutputBlockSize() {
        return this.cipher.getOutputBlockSize();
    }

    public AsymmetricBlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        reset();
        this.cipher.init(z, cipherParameters);
        this.f13232a = new byte[this.cipher.getInputBlockSize() + (z ? 1 : 0)];
        this.f13233b = 0;
    }

    public void processByte(byte b2) {
        int i2 = this.f13233b;
        byte[] bArr = this.f13232a;
        if (i2 >= bArr.length) {
            throw new DataLengthException("attempt to process message too long for cipher");
        }
        this.f13233b = i2 + 1;
        bArr[i2] = b2;
    }

    public void processBytes(byte[] bArr, int i2, int i3) {
        if (i3 == 0) {
            return;
        }
        if (i3 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int i4 = this.f13233b;
        int i5 = i4 + i3;
        byte[] bArr2 = this.f13232a;
        if (i5 > bArr2.length) {
            throw new DataLengthException("attempt to process message too long for cipher");
        }
        System.arraycopy(bArr, i2, bArr2, i4, i3);
        this.f13233b += i3;
    }

    public void reset() {
        if (this.f13232a != null) {
            int i2 = 0;
            while (true) {
                byte[] bArr = this.f13232a;
                if (i2 >= bArr.length) {
                    break;
                }
                bArr[i2] = 0;
                i2++;
            }
        }
        this.f13233b = 0;
    }
}
