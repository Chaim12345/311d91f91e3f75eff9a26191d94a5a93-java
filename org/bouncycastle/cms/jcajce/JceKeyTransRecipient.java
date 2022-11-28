package org.bouncycastle.cms.jcajce;

import java.security.Key;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.Gost2814789EncryptedKey;
import org.bouncycastle.asn1.cryptopro.GostR3410KeyTransport;
import org.bouncycastle.asn1.cryptopro.GostR3410TransportParameters;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.KeyTransRecipient;
import org.bouncycastle.jcajce.spec.GOST28147WrapParameterSpec;
import org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.jcajce.JceAsymmetricKeyUnwrapper;
import org.bouncycastle.util.Arrays;
/* loaded from: classes3.dex */
public abstract class JceKeyTransRecipient implements KeyTransRecipient {

    /* renamed from: a  reason: collision with root package name */
    protected EnvelopedDataHelper f13219a;

    /* renamed from: b  reason: collision with root package name */
    protected EnvelopedDataHelper f13220b;

    /* renamed from: c  reason: collision with root package name */
    protected Map f13221c;

    /* renamed from: d  reason: collision with root package name */
    protected boolean f13222d;

    /* renamed from: e  reason: collision with root package name */
    protected boolean f13223e;
    private PrivateKey recipientKey;

    public JceKeyTransRecipient(PrivateKey privateKey) {
        EnvelopedDataHelper envelopedDataHelper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
        this.f13219a = envelopedDataHelper;
        this.f13220b = envelopedDataHelper;
        this.f13221c = new HashMap();
        this.f13222d = false;
        this.recipientKey = CMSUtils.a(privateKey);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Key a(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr) {
        if (!CMSUtils.h(algorithmIdentifier.getAlgorithm())) {
            JceAsymmetricKeyUnwrapper mustProduceEncodableUnwrappedKey = this.f13219a.createAsymmetricUnwrapper(algorithmIdentifier, this.recipientKey).setMustProduceEncodableUnwrappedKey(this.f13223e);
            if (!this.f13221c.isEmpty()) {
                for (ASN1ObjectIdentifier aSN1ObjectIdentifier : this.f13221c.keySet()) {
                    mustProduceEncodableUnwrappedKey.setAlgorithmMapping(aSN1ObjectIdentifier, (String) this.f13221c.get(aSN1ObjectIdentifier));
                }
            }
            try {
                Key jceKey = this.f13219a.getJceKey(algorithmIdentifier2.getAlgorithm(), mustProduceEncodableUnwrappedKey.generateUnwrappedKey(algorithmIdentifier2, bArr));
                if (this.f13222d) {
                    this.f13219a.keySizeCheck(algorithmIdentifier2, jceKey);
                }
                return jceKey;
            } catch (OperatorException e2) {
                throw new CMSException("exception unwrapping key: " + e2.getMessage(), e2);
            }
        }
        try {
            GostR3410KeyTransport gostR3410KeyTransport = GostR3410KeyTransport.getInstance(bArr);
            GostR3410TransportParameters transportParameters = gostR3410KeyTransport.getTransportParameters();
            PublicKey generatePublic = this.f13219a.createKeyFactory(algorithmIdentifier.getAlgorithm()).generatePublic(new X509EncodedKeySpec(transportParameters.getEphemeralPublicKey().getEncoded()));
            KeyAgreement f2 = this.f13219a.f(algorithmIdentifier.getAlgorithm());
            f2.init(this.recipientKey, new UserKeyingMaterialSpec(transportParameters.getUkm()));
            f2.doPhase(generatePublic, true);
            ASN1ObjectIdentifier aSN1ObjectIdentifier2 = CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_KeyWrap;
            SecretKey generateSecret = f2.generateSecret(aSN1ObjectIdentifier2.getId());
            Cipher d2 = this.f13219a.d(aSN1ObjectIdentifier2);
            d2.init(4, generateSecret, new GOST28147WrapParameterSpec(transportParameters.getEncryptionParamSet(), transportParameters.getUkm()));
            Gost2814789EncryptedKey sessionEncryptedKey = gostR3410KeyTransport.getSessionEncryptedKey();
            return d2.unwrap(Arrays.concatenate(sessionEncryptedKey.getEncryptedKey(), sessionEncryptedKey.getMacKey()), this.f13219a.m(algorithmIdentifier2.getAlgorithm()), 3);
        } catch (Exception e3) {
            throw new CMSException("exception unwrapping key: " + e3.getMessage(), e3);
        }
    }

    public JceKeyTransRecipient setAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        this.f13221c.put(aSN1ObjectIdentifier, str);
        return this;
    }

    public JceKeyTransRecipient setContentProvider(String str) {
        this.f13220b = CMSUtils.b(str);
        return this;
    }

    public JceKeyTransRecipient setContentProvider(Provider provider) {
        this.f13220b = CMSUtils.c(provider);
        return this;
    }

    public JceKeyTransRecipient setKeySizeValidation(boolean z) {
        this.f13222d = z;
        return this;
    }

    public JceKeyTransRecipient setMustProduceEncodableUnwrappedKey(boolean z) {
        this.f13223e = z;
        return this;
    }

    public JceKeyTransRecipient setProvider(String str) {
        EnvelopedDataHelper envelopedDataHelper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(str));
        this.f13219a = envelopedDataHelper;
        this.f13220b = envelopedDataHelper;
        return this;
    }

    public JceKeyTransRecipient setProvider(Provider provider) {
        EnvelopedDataHelper envelopedDataHelper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        this.f13219a = envelopedDataHelper;
        this.f13220b = envelopedDataHelper;
        return this;
    }
}
