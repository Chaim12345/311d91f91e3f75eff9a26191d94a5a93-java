package org.bouncycastle.pqc.crypto.sphincsplus;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class SPHINCSPlusSigner implements MessageSigner {
    private SPHINCSPlusPrivateKeyParameters privKey;
    private SPHINCSPlusPublicKeyParameters pubKey;
    private SecureRandom random;

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        SPHINCSPlusEngine a2 = this.privKey.getParameters().a();
        byte[] bArr2 = new byte[a2.f14591b];
        SecureRandom secureRandom = this.random;
        if (secureRandom != null) {
            secureRandom.nextBytes(bArr2);
        }
        Fors fors = new Fors(a2);
        byte[] PRF_msg = a2.PRF_msg(this.privKey.f14604f.f14589b, bArr2, bArr);
        PK pk = this.privKey.f14605i;
        IndexedDigest a3 = a2.a(PRF_msg, pk.f14581a, pk.f14582b, bArr);
        byte[] bArr3 = a3.f14578c;
        long j2 = a3.f14576a;
        int i2 = a3.f14577b;
        ADRS adrs = new ADRS();
        adrs.setType(3);
        adrs.setTreeAddress(j2);
        adrs.setKeyPairAddress(i2);
        SPHINCSPlusPrivateKeyParameters sPHINCSPlusPrivateKeyParameters = this.privKey;
        SIG_FORS[] sign = fors.sign(bArr3, sPHINCSPlusPrivateKeyParameters.f14604f.f14588a, sPHINCSPlusPrivateKeyParameters.f14605i.f14581a, adrs);
        byte[] pkFromSig = fors.pkFromSig(sign, bArr3, this.privKey.f14605i.f14581a, adrs);
        new ADRS().setType(2);
        byte[] a4 = new HT(a2, this.privKey.getSeed(), this.privKey.getPublicSeed()).a(pkFromSig, j2, i2);
        int length = sign.length + 2;
        byte[][] bArr4 = new byte[length];
        int i3 = 0;
        bArr4[0] = PRF_msg;
        while (i3 != sign.length) {
            int i4 = i3 + 1;
            bArr4[i4] = Arrays.concatenate(sign[i3].f14585b, Arrays.concatenate(sign[i3].f14584a));
            i3 = i4;
        }
        bArr4[length - 1] = a4;
        return Arrays.concatenate(bArr4);
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!z) {
            this.pubKey = (SPHINCSPlusPublicKeyParameters) cipherParameters;
        } else if (!(cipherParameters instanceof ParametersWithRandom)) {
            this.privKey = (SPHINCSPlusPrivateKeyParameters) cipherParameters;
        } else {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.privKey = (SPHINCSPlusPrivateKeyParameters) parametersWithRandom.getParameters();
            this.random = parametersWithRandom.getRandom();
        }
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        SPHINCSPlusEngine a2 = this.pubKey.getParameters().a();
        ADRS adrs = new ADRS();
        SIG sig = new SIG(a2.f14591b, a2.f14599j, a2.f14598i, a2.f14597h, a2.f14601l, a2.f14594e, bArr2);
        byte[] r2 = sig.getR();
        SIG_FORS[] sig_fors = sig.getSIG_FORS();
        SIG_XMSS[] sig_ht = sig.getSIG_HT();
        IndexedDigest a3 = a2.a(r2, this.pubKey.getSeed(), this.pubKey.getRoot(), bArr);
        byte[] bArr3 = a3.f14578c;
        long j2 = a3.f14576a;
        int i2 = a3.f14577b;
        adrs.setLayerAddress(0);
        adrs.setTreeAddress(j2);
        adrs.setType(3);
        adrs.setKeyPairAddress(i2);
        byte[] pkFromSig = new Fors(a2).pkFromSig(sig_fors, bArr3, this.pubKey.getSeed(), adrs);
        adrs.setType(2);
        return new HT(a2, null, this.pubKey.getSeed()).verify(pkFromSig, sig_ht, this.pubKey.getSeed(), j2, i2, this.pubKey.getRoot());
    }
}
