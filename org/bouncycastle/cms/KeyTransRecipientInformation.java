package org.bouncycastle.cms;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.cms.KeyTransRecipientInfo;
import org.bouncycastle.asn1.cms.RecipientIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes3.dex */
public class KeyTransRecipientInformation extends RecipientInformation {
    private KeyTransRecipientInfo info;

    /* JADX INFO: Access modifiers changed from: package-private */
    public KeyTransRecipientInformation(KeyTransRecipientInfo keyTransRecipientInfo, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        super(keyTransRecipientInfo.getKeyEncryptionAlgorithm(), algorithmIdentifier, cMSSecureReadable, authAttributesProvider);
        KeyTransRecipientId keyTransRecipientId;
        this.info = keyTransRecipientInfo;
        RecipientIdentifier recipientIdentifier = keyTransRecipientInfo.getRecipientIdentifier();
        boolean isTagged = recipientIdentifier.isTagged();
        ASN1Encodable id = recipientIdentifier.getId();
        if (isTagged) {
            keyTransRecipientId = new KeyTransRecipientId(ASN1OctetString.getInstance(id).getOctets());
        } else {
            IssuerAndSerialNumber issuerAndSerialNumber = IssuerAndSerialNumber.getInstance(id);
            keyTransRecipientId = new KeyTransRecipientId(issuerAndSerialNumber.getName(), issuerAndSerialNumber.getSerialNumber().getValue());
        }
        this.f13158a = keyTransRecipientId;
    }

    @Override // org.bouncycastle.cms.RecipientInformation
    protected RecipientOperator a(Recipient recipient) {
        return ((KeyTransRecipient) recipient).getRecipientOperator(this.f13159b, this.f13160c, this.info.getEncryptedKey().getOctets());
    }
}
