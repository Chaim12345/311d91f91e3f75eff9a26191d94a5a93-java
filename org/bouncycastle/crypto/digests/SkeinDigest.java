package org.bouncycastle.crypto.digests;

import org.apache.commons.cli.HelpFormatter;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.params.SkeinParameters;
import org.bouncycastle.util.Memoable;
/* loaded from: classes3.dex */
public class SkeinDigest implements ExtendedDigest, Memoable {
    public static final int SKEIN_1024 = 1024;
    public static final int SKEIN_256 = 256;
    public static final int SKEIN_512 = 512;
    private SkeinEngine engine;

    public SkeinDigest(int i2, int i3) {
        this.engine = new SkeinEngine(i2, i3);
        init(null);
    }

    public SkeinDigest(SkeinDigest skeinDigest) {
        this.engine = new SkeinEngine(skeinDigest.engine);
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new SkeinDigest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i2) {
        return this.engine.doFinal(bArr, i2);
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "Skein-" + (this.engine.getBlockSize() * 8) + HelpFormatter.DEFAULT_OPT_PREFIX + (this.engine.getOutputSize() * 8);
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return this.engine.getBlockSize();
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.engine.getOutputSize();
    }

    public void init(SkeinParameters skeinParameters) {
        this.engine.init(skeinParameters);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.engine.reset();
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        this.engine.reset(((SkeinDigest) memoable).engine);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b2) {
        this.engine.update(b2);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i2, int i3) {
        this.engine.update(bArr, i2, i3);
    }
}
