package org.bouncycastle.cms.jcajce;

import java.security.Key;
import java.security.PrivateKey;
import java.security.Provider;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.KeyTransRecipient;
import org.bouncycastle.cms.KeyTransRecipientId;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes3.dex */
public abstract class JceKTSKeyTransRecipient implements KeyTransRecipient {
    private static final byte[] ANONYMOUS_SENDER = Hex.decode("0c14416e6f6e796d6f75732053656e64657220202020");

    /* renamed from: a  reason: collision with root package name */
    protected EnvelopedDataHelper f13199a;

    /* renamed from: b  reason: collision with root package name */
    protected EnvelopedDataHelper f13200b;

    /* renamed from: c  reason: collision with root package name */
    protected Map f13201c;

    /* renamed from: d  reason: collision with root package name */
    protected boolean f13202d;
    private final byte[] partyVInfo;
    private PrivateKey recipientKey;

    public JceKTSKeyTransRecipient(PrivateKey privateKey, byte[] bArr) {
        EnvelopedDataHelper envelopedDataHelper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
        this.f13199a = envelopedDataHelper;
        this.f13200b = envelopedDataHelper;
        this.f13201c = new HashMap();
        this.f13202d = false;
        this.recipientKey = CMSUtils.a(privateKey);
        this.partyVInfo = bArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static byte[] b(KeyTransRecipientId keyTransRecipientId) {
        return keyTransRecipientId.getSerialNumber() != null ? new IssuerAndSerialNumber(keyTransRecipientId.getIssuer(), keyTransRecipientId.getSerialNumber()).getEncoded(ASN1Encoding.DER) : new DEROctetString(keyTransRecipientId.getSubjectKeyIdentifier()).getEncoded();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Key a(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr) {
        try {
            Key jceKey = this.f13199a.getJceKey(algorithmIdentifier2.getAlgorithm(), this.f13199a.createAsymmetricUnwrapper(algorithmIdentifier, this.recipientKey, ANONYMOUS_SENDER, this.partyVInfo).generateUnwrappedKey(algorithmIdentifier2, bArr));
            if (this.f13202d) {
                this.f13199a.keySizeCheck(algorithmIdentifier2, jceKey);
            }
            return jceKey;
        } catch (OperatorException e2) {
            throw new CMSException("exception unwrapping key: " + e2.getMessage(), e2);
        }
    }

    public JceKTSKeyTransRecipient setAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        this.f13201c.put(aSN1ObjectIdentifier, str);
        return this;
    }

    public JceKTSKeyTransRecipient setContentProvider(String str) {
        this.f13200b = CMSUtils.b(str);
        return this;
    }

    public JceKTSKeyTransRecipient setContentProvider(Provider provider) {
        this.f13200b = CMSUtils.c(provider);
        return this;
    }

    public JceKTSKeyTransRecipient setKeySizeValidation(boolean z) {
        this.f13202d = z;
        return this;
    }

    public JceKTSKeyTransRecipient setProvider(String str) {
        EnvelopedDataHelper envelopedDataHelper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(str));
        this.f13199a = envelopedDataHelper;
        this.f13200b = envelopedDataHelper;
        return this;
    }

    public JceKTSKeyTransRecipient setProvider(Provider provider) {
        EnvelopedDataHelper envelopedDataHelper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        this.f13199a = envelopedDataHelper;
        this.f13200b = envelopedDataHelper;
        return this;
    }
}
