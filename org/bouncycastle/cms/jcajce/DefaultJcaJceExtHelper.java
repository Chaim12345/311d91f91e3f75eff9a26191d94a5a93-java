package org.bouncycastle.cms.jcajce;

import java.security.PrivateKey;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.operator.SymmetricKeyUnwrapper;
import org.bouncycastle.operator.jcajce.JceAsymmetricKeyUnwrapper;
import org.bouncycastle.operator.jcajce.JceKTSKeyUnwrapper;
import org.bouncycastle.operator.jcajce.JceSymmetricKeyUnwrapper;
/* loaded from: classes3.dex */
class DefaultJcaJceExtHelper extends DefaultJcaJceHelper implements JcaJceExtHelper {
    @Override // org.bouncycastle.cms.jcajce.JcaJceExtHelper
    public JceAsymmetricKeyUnwrapper createAsymmetricUnwrapper(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey) {
        return new JceAsymmetricKeyUnwrapper(algorithmIdentifier, CMSUtils.a(privateKey));
    }

    @Override // org.bouncycastle.cms.jcajce.JcaJceExtHelper
    public JceKTSKeyUnwrapper createAsymmetricUnwrapper(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey, byte[] bArr, byte[] bArr2) {
        return new JceKTSKeyUnwrapper(algorithmIdentifier, CMSUtils.a(privateKey), bArr, bArr2);
    }

    @Override // org.bouncycastle.cms.jcajce.JcaJceExtHelper
    public SymmetricKeyUnwrapper createSymmetricUnwrapper(AlgorithmIdentifier algorithmIdentifier, SecretKey secretKey) {
        return new JceSymmetricKeyUnwrapper(algorithmIdentifier, secretKey);
    }
}
