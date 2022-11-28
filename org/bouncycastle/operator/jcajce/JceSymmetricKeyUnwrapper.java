package org.bouncycastle.operator.jcajce;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.SymmetricKeyUnwrapper;
/* loaded from: classes4.dex */
public class JceSymmetricKeyUnwrapper extends SymmetricKeyUnwrapper {
    private OperatorHelper helper;
    private SecretKey secretKey;

    public JceSymmetricKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier, SecretKey secretKey) {
        super(algorithmIdentifier);
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.secretKey = secretKey;
    }

    @Override // org.bouncycastle.operator.KeyUnwrapper
    public GenericKey generateUnwrappedKey(AlgorithmIdentifier algorithmIdentifier, byte[] bArr) {
        try {
            Cipher i2 = this.helper.i(getAlgorithmIdentifier().getAlgorithm());
            i2.init(4, this.secretKey);
            return new JceGenericKey(algorithmIdentifier, i2.unwrap(bArr, this.helper.j(algorithmIdentifier.getAlgorithm()), 3));
        } catch (InvalidKeyException e2) {
            throw new OperatorException("key invalid in message.", e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new OperatorException("can't find algorithm.", e3);
        }
    }

    public JceSymmetricKeyUnwrapper setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        return this;
    }

    public JceSymmetricKeyUnwrapper setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }
}
