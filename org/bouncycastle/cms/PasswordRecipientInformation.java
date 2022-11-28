package org.bouncycastle.cms;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cms.PasswordRecipientInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.util.Integers;
/* loaded from: classes3.dex */
public class PasswordRecipientInformation extends RecipientInformation {

    /* renamed from: e  reason: collision with root package name */
    static Map f13156e = new HashMap();

    /* renamed from: f  reason: collision with root package name */
    static Map f13157f;
    private PasswordRecipientInfo info;

    static {
        HashMap hashMap = new HashMap();
        f13157f = hashMap;
        ASN1ObjectIdentifier aSN1ObjectIdentifier = CMSAlgorithm.DES_EDE3_CBC;
        hashMap.put(aSN1ObjectIdentifier, Integers.valueOf(8));
        Map map = f13157f;
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = CMSAlgorithm.AES128_CBC;
        map.put(aSN1ObjectIdentifier2, Integers.valueOf(16));
        Map map2 = f13157f;
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = CMSAlgorithm.AES192_CBC;
        map2.put(aSN1ObjectIdentifier3, Integers.valueOf(16));
        Map map3 = f13157f;
        ASN1ObjectIdentifier aSN1ObjectIdentifier4 = CMSAlgorithm.AES256_CBC;
        map3.put(aSN1ObjectIdentifier4, Integers.valueOf(16));
        f13156e.put(aSN1ObjectIdentifier, Integers.valueOf(192));
        f13156e.put(aSN1ObjectIdentifier2, Integers.valueOf(128));
        f13156e.put(aSN1ObjectIdentifier3, Integers.valueOf(192));
        f13156e.put(aSN1ObjectIdentifier4, Integers.valueOf(256));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PasswordRecipientInformation(PasswordRecipientInfo passwordRecipientInfo, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        super(passwordRecipientInfo.getKeyEncryptionAlgorithm(), algorithmIdentifier, cMSSecureReadable, authAttributesProvider);
        this.info = passwordRecipientInfo;
        this.f13158a = new PasswordRecipientId();
    }

    @Override // org.bouncycastle.cms.RecipientInformation
    protected RecipientOperator a(Recipient recipient) {
        PasswordRecipient passwordRecipient = (PasswordRecipient) recipient;
        AlgorithmIdentifier algorithmIdentifier = AlgorithmIdentifier.getInstance(AlgorithmIdentifier.getInstance(this.info.getKeyEncryptionAlgorithm()).getParameters());
        return passwordRecipient.getRecipientOperator(algorithmIdentifier, this.f13160c, passwordRecipient.calculateDerivedKey(passwordRecipient.getPasswordConversionScheme(), getKeyDerivationAlgorithm(), ((Integer) f13156e.get(algorithmIdentifier.getAlgorithm())).intValue()), this.info.getEncryptedKey().getOctets());
    }

    public String getKeyDerivationAlgOID() {
        if (this.info.getKeyDerivationAlgorithm() != null) {
            return this.info.getKeyDerivationAlgorithm().getAlgorithm().getId();
        }
        return null;
    }

    public byte[] getKeyDerivationAlgParams() {
        ASN1Encodable parameters;
        try {
            if (this.info.getKeyDerivationAlgorithm() == null || (parameters = this.info.getKeyDerivationAlgorithm().getParameters()) == null) {
                return null;
            }
            return parameters.toASN1Primitive().getEncoded();
        } catch (Exception e2) {
            throw new RuntimeException("exception getting encryption parameters " + e2);
        }
    }

    public AlgorithmIdentifier getKeyDerivationAlgorithm() {
        return this.info.getKeyDerivationAlgorithm();
    }
}
