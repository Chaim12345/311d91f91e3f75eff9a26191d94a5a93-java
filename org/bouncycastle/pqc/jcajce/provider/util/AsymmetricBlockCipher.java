package org.bouncycastle.pqc.jcajce.provider.util;

import java.io.ByteArrayOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
/* loaded from: classes4.dex */
public abstract class AsymmetricBlockCipher extends CipherSpiExt {

    /* renamed from: b  reason: collision with root package name */
    protected AlgorithmParameterSpec f14632b;

    /* renamed from: c  reason: collision with root package name */
    protected ByteArrayOutputStream f14633c = new ByteArrayOutputStream();

    /* renamed from: d  reason: collision with root package name */
    protected int f14634d;

    /* renamed from: e  reason: collision with root package name */
    protected int f14635e;

    @Override // org.bouncycastle.pqc.jcajce.provider.util.CipherSpiExt
    protected final void a(String str) {
    }

    @Override // org.bouncycastle.pqc.jcajce.provider.util.CipherSpiExt
    protected final void b(String str) {
    }

    protected void c(int i2) {
        int size = i2 + this.f14633c.size();
        int i3 = this.f14637a;
        if (i3 == 1) {
            if (size <= this.f14634d) {
                return;
            }
            throw new IllegalBlockSizeException("The length of the plaintext (" + size + " bytes) is not supported by the cipher (max. " + this.f14634d + " bytes).");
        } else if (i3 != 2 || size == this.f14635e) {
        } else {
            throw new IllegalBlockSizeException("Illegal ciphertext length (expected " + this.f14635e + " bytes, was " + size + " bytes).");
        }
    }

    protected abstract void d(Key key, AlgorithmParameterSpec algorithmParameterSpec);

    @Override // org.bouncycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final int doFinal(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        if (bArr2.length >= getOutputSize(i3)) {
            byte[] doFinal = doFinal(bArr, i2, i3);
            System.arraycopy(doFinal, 0, bArr2, i4, doFinal.length);
            return doFinal.length;
        }
        throw new ShortBufferException("Output buffer too short.");
    }

    @Override // org.bouncycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final byte[] doFinal(byte[] bArr, int i2, int i3) {
        c(i3);
        update(bArr, i2, i3);
        byte[] byteArray = this.f14633c.toByteArray();
        this.f14633c.reset();
        int i4 = this.f14637a;
        if (i4 != 1) {
            if (i4 != 2) {
                return null;
            }
            return f(byteArray);
        }
        return g(byteArray);
    }

    protected abstract void e(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom);

    protected abstract byte[] f(byte[] bArr);

    protected abstract byte[] g(byte[] bArr);

    @Override // org.bouncycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final int getBlockSize() {
        return this.f14637a == 1 ? this.f14634d : this.f14635e;
    }

    @Override // org.bouncycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final byte[] getIV() {
        return null;
    }

    @Override // org.bouncycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final int getOutputSize(int i2) {
        if (i2 + this.f14633c.size() > getBlockSize()) {
            return 0;
        }
        return this.f14637a == 1 ? this.f14635e : this.f14634d;
    }

    @Override // org.bouncycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final AlgorithmParameterSpec getParameters() {
        return this.f14632b;
    }

    public final void initDecrypt(Key key) {
        try {
            initDecrypt(key, null);
        } catch (InvalidAlgorithmParameterException unused) {
            throw new InvalidParameterException("This cipher needs algorithm parameters for initialization (cannot be null).");
        }
    }

    @Override // org.bouncycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final void initDecrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec) {
        this.f14637a = 2;
        d(key, algorithmParameterSpec);
    }

    public final void initEncrypt(Key key) {
        try {
            initEncrypt(key, null, CryptoServicesRegistrar.getSecureRandom());
        } catch (InvalidAlgorithmParameterException unused) {
            throw new InvalidParameterException("This cipher needs algorithm parameters for initialization (cannot be null).");
        }
    }

    public final void initEncrypt(Key key, SecureRandom secureRandom) {
        try {
            initEncrypt(key, null, secureRandom);
        } catch (InvalidAlgorithmParameterException unused) {
            throw new InvalidParameterException("This cipher needs algorithm parameters for initialization (cannot be null).");
        }
    }

    public final void initEncrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec) {
        initEncrypt(key, algorithmParameterSpec, CryptoServicesRegistrar.getSecureRandom());
    }

    @Override // org.bouncycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final void initEncrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        this.f14637a = 1;
        e(key, algorithmParameterSpec, secureRandom);
    }

    @Override // org.bouncycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final int update(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        update(bArr, i2, i3);
        return 0;
    }

    @Override // org.bouncycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final byte[] update(byte[] bArr, int i2, int i3) {
        if (i3 != 0) {
            this.f14633c.write(bArr, i2, i3);
        }
        return new byte[0];
    }
}
