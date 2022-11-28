package org.bouncycastle.pqc.crypto.xmss;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
final class WOTSPlus {
    private final KeyedHashFunctions khf;
    private final WOTSPlusParameters params;
    private byte[] publicSeed;
    private byte[] secretKeySeed;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WOTSPlus(WOTSPlusParameters wOTSPlusParameters) {
        Objects.requireNonNull(wOTSPlusParameters, "params == null");
        this.params = wOTSPlusParameters;
        int d2 = wOTSPlusParameters.d();
        this.khf = new KeyedHashFunctions(wOTSPlusParameters.getTreeDigest(), d2);
        this.secretKeySeed = new byte[d2];
        this.publicSeed = new byte[d2];
    }

    private byte[] chain(byte[] bArr, int i2, int i3, OTSHashAddress oTSHashAddress) {
        int d2 = this.params.d();
        Objects.requireNonNull(bArr, "startHash == null");
        if (bArr.length != d2) {
            throw new IllegalArgumentException("startHash needs to be " + d2 + "bytes");
        }
        Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
        Objects.requireNonNull(oTSHashAddress.c(), "otsHashAddress byte array == null");
        int i4 = i2 + i3;
        if (i4 <= this.params.e() - 1) {
            if (i3 == 0) {
                return bArr;
            }
            byte[] chain = chain(bArr, i2, i3 - 1, oTSHashAddress);
            OTSHashAddress oTSHashAddress2 = (OTSHashAddress) ((OTSHashAddress.Builder) ((OTSHashAddress.Builder) ((OTSHashAddress.Builder) new OTSHashAddress.Builder().g(oTSHashAddress.a())).h(oTSHashAddress.b())).p(oTSHashAddress.f()).n(oTSHashAddress.d()).o(i4 - 1).f(0)).l();
            byte[] d3 = this.khf.d(this.publicSeed, oTSHashAddress2.c());
            byte[] d4 = this.khf.d(this.publicSeed, ((OTSHashAddress) ((OTSHashAddress.Builder) ((OTSHashAddress.Builder) ((OTSHashAddress.Builder) new OTSHashAddress.Builder().g(oTSHashAddress2.a())).h(oTSHashAddress2.b())).p(oTSHashAddress2.f()).n(oTSHashAddress2.d()).o(oTSHashAddress2.e()).f(1)).l()).c());
            byte[] bArr2 = new byte[d2];
            for (int i5 = 0; i5 < d2; i5++) {
                bArr2[i5] = (byte) (chain[i5] ^ d4[i5]);
            }
            return this.khf.a(d3, bArr2);
        }
        throw new IllegalArgumentException("max chain length must not be greater than w");
    }

    private List<Integer> convertToBaseW(byte[] bArr, int i2, int i3) {
        Objects.requireNonNull(bArr, "msg == null");
        if (i2 == 4 || i2 == 16) {
            int log2 = XMSSUtil.log2(i2);
            if (i3 <= (bArr.length * 8) / log2) {
                ArrayList arrayList = new ArrayList();
                for (int i4 : bArr) {
                    for (int i5 = 8 - log2; i5 >= 0; i5 -= log2) {
                        arrayList.add(Integer.valueOf((i4 >> i5) & (i2 - 1)));
                        if (arrayList.size() == i3) {
                            return arrayList;
                        }
                    }
                }
                return arrayList;
            }
            throw new IllegalArgumentException("outLength too big");
        }
        throw new IllegalArgumentException("w needs to be 4 or 16");
    }

    private byte[] expandSecretKeySeed(int i2) {
        if (i2 < 0 || i2 >= this.params.a()) {
            throw new IllegalArgumentException("index out of bounds");
        }
        return this.khf.d(this.secretKeySeed, XMSSUtil.toBytesBigEndian(i2, 32));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public KeyedHashFunctions a() {
        return this.khf;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public WOTSPlusParameters b() {
        return this.params;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WOTSPlusPublicKeyParameters c(OTSHashAddress oTSHashAddress) {
        Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
        byte[][] bArr = new byte[this.params.a()];
        for (int i2 = 0; i2 < this.params.a(); i2++) {
            oTSHashAddress = (OTSHashAddress) ((OTSHashAddress.Builder) ((OTSHashAddress.Builder) ((OTSHashAddress.Builder) new OTSHashAddress.Builder().g(oTSHashAddress.a())).h(oTSHashAddress.b())).p(oTSHashAddress.f()).n(i2).o(oTSHashAddress.e()).f(oTSHashAddress.getKeyAndMask())).l();
            bArr[i2] = chain(expandSecretKeySeed(i2), 0, this.params.e() - 1, oTSHashAddress);
        }
        return new WOTSPlusPublicKeyParameters(this.params, bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WOTSPlusPublicKeyParameters d(byte[] bArr, WOTSPlusSignature wOTSPlusSignature, OTSHashAddress oTSHashAddress) {
        Objects.requireNonNull(bArr, "messageDigest == null");
        if (bArr.length == this.params.d()) {
            Objects.requireNonNull(wOTSPlusSignature, "signature == null");
            Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
            List<Integer> convertToBaseW = convertToBaseW(bArr, this.params.e(), this.params.b());
            int i2 = 0;
            for (int i3 = 0; i3 < this.params.b(); i3++) {
                i2 += (this.params.e() - 1) - convertToBaseW.get(i3).intValue();
            }
            convertToBaseW.addAll(convertToBaseW(XMSSUtil.toBytesBigEndian(i2 << (8 - ((this.params.c() * XMSSUtil.log2(this.params.e())) % 8)), (int) Math.ceil((this.params.c() * XMSSUtil.log2(this.params.e())) / 8.0d)), this.params.e(), this.params.c()));
            byte[][] bArr2 = new byte[this.params.a()];
            for (int i4 = 0; i4 < this.params.a(); i4++) {
                oTSHashAddress = (OTSHashAddress) ((OTSHashAddress.Builder) ((OTSHashAddress.Builder) ((OTSHashAddress.Builder) new OTSHashAddress.Builder().g(oTSHashAddress.a())).h(oTSHashAddress.b())).p(oTSHashAddress.f()).n(i4).o(oTSHashAddress.e()).f(oTSHashAddress.getKeyAndMask())).l();
                bArr2[i4] = chain(wOTSPlusSignature.toByteArray()[i4], convertToBaseW.get(i4).intValue(), (this.params.e() - 1) - convertToBaseW.get(i4).intValue(), oTSHashAddress);
            }
            return new WOTSPlusPublicKeyParameters(this.params, bArr2);
        }
        throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] e() {
        return Arrays.clone(this.publicSeed);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] f(byte[] bArr, OTSHashAddress oTSHashAddress) {
        return this.khf.d(bArr, ((OTSHashAddress) ((OTSHashAddress.Builder) ((OTSHashAddress.Builder) new OTSHashAddress.Builder().g(oTSHashAddress.a())).h(oTSHashAddress.b())).p(oTSHashAddress.f()).l()).c());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(byte[] bArr, byte[] bArr2) {
        Objects.requireNonNull(bArr, "secretKeySeed == null");
        if (bArr.length != this.params.d()) {
            throw new IllegalArgumentException("size of secretKeySeed needs to be equal to size of digest");
        }
        Objects.requireNonNull(bArr2, "publicSeed == null");
        if (bArr2.length != this.params.d()) {
            throw new IllegalArgumentException("size of publicSeed needs to be equal to size of digest");
        }
        this.secretKeySeed = bArr;
        this.publicSeed = bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WOTSPlusSignature h(byte[] bArr, OTSHashAddress oTSHashAddress) {
        Objects.requireNonNull(bArr, "messageDigest == null");
        if (bArr.length == this.params.d()) {
            Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
            List<Integer> convertToBaseW = convertToBaseW(bArr, this.params.e(), this.params.b());
            int i2 = 0;
            for (int i3 = 0; i3 < this.params.b(); i3++) {
                i2 += (this.params.e() - 1) - convertToBaseW.get(i3).intValue();
            }
            convertToBaseW.addAll(convertToBaseW(XMSSUtil.toBytesBigEndian(i2 << (8 - ((this.params.c() * XMSSUtil.log2(this.params.e())) % 8)), (int) Math.ceil((this.params.c() * XMSSUtil.log2(this.params.e())) / 8.0d)), this.params.e(), this.params.c()));
            byte[][] bArr2 = new byte[this.params.a()];
            for (int i4 = 0; i4 < this.params.a(); i4++) {
                oTSHashAddress = (OTSHashAddress) ((OTSHashAddress.Builder) ((OTSHashAddress.Builder) ((OTSHashAddress.Builder) new OTSHashAddress.Builder().g(oTSHashAddress.a())).h(oTSHashAddress.b())).p(oTSHashAddress.f()).n(i4).o(oTSHashAddress.e()).f(oTSHashAddress.getKeyAndMask())).l();
                bArr2[i4] = chain(expandSecretKeySeed(i4), 0, convertToBaseW.get(i4).intValue(), oTSHashAddress);
            }
            return new WOTSPlusSignature(this.params, bArr2);
        }
        throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
    }
}
