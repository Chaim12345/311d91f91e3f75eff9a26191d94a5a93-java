package org.bouncycastle.cms.jcajce;

import java.security.Key;
import java.security.Provider;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.KEKRecipient;
import org.bouncycastle.operator.OperatorException;
/* loaded from: classes3.dex */
public abstract class JceKEKRecipient implements KEKRecipient {

    /* renamed from: a  reason: collision with root package name */
    protected EnvelopedDataHelper f13191a;

    /* renamed from: b  reason: collision with root package name */
    protected EnvelopedDataHelper f13192b;

    /* renamed from: c  reason: collision with root package name */
    protected boolean f13193c;
    private SecretKey recipientKey;

    public JceKEKRecipient(SecretKey secretKey) {
        EnvelopedDataHelper envelopedDataHelper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
        this.f13191a = envelopedDataHelper;
        this.f13192b = envelopedDataHelper;
        this.f13193c = false;
        this.recipientKey = secretKey;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Key a(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr) {
        try {
            Key jceKey = this.f13191a.getJceKey(algorithmIdentifier2.getAlgorithm(), this.f13191a.createSymmetricUnwrapper(algorithmIdentifier, this.recipientKey).generateUnwrappedKey(algorithmIdentifier2, bArr));
            if (this.f13193c) {
                this.f13191a.keySizeCheck(algorithmIdentifier2, jceKey);
            }
            return jceKey;
        } catch (OperatorException e2) {
            throw new CMSException("exception unwrapping key: " + e2.getMessage(), e2);
        }
    }

    public JceKEKRecipient setContentProvider(String str) {
        this.f13192b = new EnvelopedDataHelper(new NamedJcaJceExtHelper(str));
        return this;
    }

    public JceKEKRecipient setContentProvider(Provider provider) {
        this.f13192b = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        return this;
    }

    public JceKEKRecipient setKeySizeValidation(boolean z) {
        this.f13193c = z;
        return this;
    }

    public JceKEKRecipient setProvider(String str) {
        EnvelopedDataHelper envelopedDataHelper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(str));
        this.f13191a = envelopedDataHelper;
        this.f13192b = envelopedDataHelper;
        return this;
    }

    public JceKEKRecipient setProvider(Provider provider) {
        EnvelopedDataHelper envelopedDataHelper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        this.f13191a = envelopedDataHelper;
        this.f13192b = envelopedDataHelper;
        return this;
    }
}
