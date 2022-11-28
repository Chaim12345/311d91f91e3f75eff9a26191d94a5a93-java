package org.bouncycastle.cms.jcajce;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.PasswordRecipient;
/* loaded from: classes3.dex */
public abstract class JcePasswordRecipient implements PasswordRecipient {
    private char[] password;
    private int schemeID = 1;

    /* renamed from: a  reason: collision with root package name */
    protected EnvelopedDataHelper f13229a = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());

    /* JADX INFO: Access modifiers changed from: package-private */
    public JcePasswordRecipient(char[] cArr) {
        this.password = cArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Key a(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr, byte[] bArr2) {
        Cipher i2 = this.f13229a.i(algorithmIdentifier.getAlgorithm());
        try {
            i2.init(4, new SecretKeySpec(bArr, i2.getAlgorithm()), new IvParameterSpec(ASN1OctetString.getInstance(algorithmIdentifier.getParameters()).getOctets()));
            return i2.unwrap(bArr2, algorithmIdentifier2.getAlgorithm().getId(), 3);
        } catch (GeneralSecurityException e2) {
            throw new CMSException("cannot process content encryption key: " + e2.getMessage(), e2);
        }
    }

    @Override // org.bouncycastle.cms.PasswordRecipient
    public byte[] calculateDerivedKey(int i2, AlgorithmIdentifier algorithmIdentifier, int i3) {
        return this.f13229a.a(i2, this.password, algorithmIdentifier, i3);
    }

    @Override // org.bouncycastle.cms.PasswordRecipient
    public char[] getPassword() {
        return this.password;
    }

    @Override // org.bouncycastle.cms.PasswordRecipient
    public int getPasswordConversionScheme() {
        return this.schemeID;
    }

    public JcePasswordRecipient setPasswordConversionScheme(int i2) {
        this.schemeID = i2;
        return this;
    }

    public JcePasswordRecipient setProvider(String str) {
        this.f13229a = new EnvelopedDataHelper(new NamedJcaJceExtHelper(str));
        return this;
    }

    public JcePasswordRecipient setProvider(Provider provider) {
        this.f13229a = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        return this;
    }
}
