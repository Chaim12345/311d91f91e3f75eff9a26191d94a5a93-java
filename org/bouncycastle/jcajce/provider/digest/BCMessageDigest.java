package org.bouncycastle.jcajce.provider.digest;

import java.security.DigestException;
import java.security.MessageDigest;
import org.bouncycastle.crypto.Digest;
/* loaded from: classes3.dex */
public class BCMessageDigest extends MessageDigest {

    /* renamed from: a  reason: collision with root package name */
    protected Digest f13742a;

    /* renamed from: b  reason: collision with root package name */
    protected int f13743b;

    /* JADX INFO: Access modifiers changed from: protected */
    public BCMessageDigest(Digest digest) {
        super(digest.getAlgorithmName());
        this.f13742a = digest;
        this.f13743b = digest.getDigestSize();
    }

    @Override // java.security.MessageDigestSpi
    public int engineDigest(byte[] bArr, int i2, int i3) {
        int i4 = this.f13743b;
        if (i3 >= i4) {
            if (bArr.length - i2 >= i4) {
                this.f13742a.doFinal(bArr, i2);
                return this.f13743b;
            }
            throw new DigestException("insufficient space in the output buffer to store the digest");
        }
        throw new DigestException("partial digests not returned");
    }

    @Override // java.security.MessageDigestSpi
    public byte[] engineDigest() {
        byte[] bArr = new byte[this.f13743b];
        this.f13742a.doFinal(bArr, 0);
        return bArr;
    }

    @Override // java.security.MessageDigestSpi
    public int engineGetDigestLength() {
        return this.f13743b;
    }

    @Override // java.security.MessageDigestSpi
    public void engineReset() {
        this.f13742a.reset();
    }

    @Override // java.security.MessageDigestSpi
    public void engineUpdate(byte b2) {
        this.f13742a.update(b2);
    }

    @Override // java.security.MessageDigestSpi
    public void engineUpdate(byte[] bArr, int i2, int i3) {
        this.f13742a.update(bArr, i2, i3);
    }
}
