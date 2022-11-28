package org.bouncycastle.mozilla.jcajce;

import java.security.InvalidKeyException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.mozilla.SignedPublicKeyAndChallenge;
/* loaded from: classes4.dex */
public class JcaSignedPublicKeyAndChallenge extends SignedPublicKeyAndChallenge {

    /* renamed from: b  reason: collision with root package name */
    JcaJceHelper f14369b;

    private JcaSignedPublicKeyAndChallenge(org.bouncycastle.asn1.mozilla.SignedPublicKeyAndChallenge signedPublicKeyAndChallenge, JcaJceHelper jcaJceHelper) {
        super(signedPublicKeyAndChallenge);
        this.f14369b = new DefaultJcaJceHelper();
        this.f14369b = jcaJceHelper;
    }

    public JcaSignedPublicKeyAndChallenge(byte[] bArr) {
        super(bArr);
        this.f14369b = new DefaultJcaJceHelper();
    }

    public PublicKey getPublicKey() {
        try {
            SubjectPublicKeyInfo subjectPublicKeyInfo = this.f14368a.getPublicKeyAndChallenge().getSubjectPublicKeyInfo();
            return this.f14369b.createKeyFactory(subjectPublicKeyInfo.getAlgorithm().getAlgorithm().getId()).generatePublic(new X509EncodedKeySpec(subjectPublicKeyInfo.getEncoded()));
        } catch (Exception unused) {
            throw new InvalidKeyException("error encoding public key");
        }
    }

    public JcaSignedPublicKeyAndChallenge setProvider(String str) {
        return new JcaSignedPublicKeyAndChallenge(this.f14368a, new NamedJcaJceHelper(str));
    }

    public JcaSignedPublicKeyAndChallenge setProvider(Provider provider) {
        return new JcaSignedPublicKeyAndChallenge(this.f14368a, new ProviderJcaJceHelper(provider));
    }
}
