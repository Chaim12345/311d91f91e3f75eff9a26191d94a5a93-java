package org.bouncycastle.cert.crmf.jcajce;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import org.bouncycastle.asn1.crmf.EncryptedValue;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.cert.crmf.EncryptedValueBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.operator.KeyWrapper;
import org.bouncycastle.operator.OutputEncryptor;
/* loaded from: classes3.dex */
public class JcaEncryptedValueBuilder extends EncryptedValueBuilder {
    public JcaEncryptedValueBuilder(KeyWrapper keyWrapper, OutputEncryptor outputEncryptor) {
        super(keyWrapper, outputEncryptor);
    }

    public EncryptedValue build(PrivateKey privateKey) {
        return build(PrivateKeyInfo.getInstance(privateKey.getEncoded()));
    }

    public EncryptedValue build(X509Certificate x509Certificate) {
        return build(new JcaX509CertificateHolder(x509Certificate));
    }
}
