package org.bouncycastle.cms;

import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.cms.KEKIdentifier;
import org.bouncycastle.asn1.cms.KEKRecipientInfo;
import org.bouncycastle.asn1.cms.RecipientInfo;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.SymmetricKeyWrapper;
/* loaded from: classes3.dex */
public abstract class KEKRecipientInfoGenerator implements RecipientInfoGenerator {

    /* renamed from: a  reason: collision with root package name */
    protected final SymmetricKeyWrapper f13152a;
    private final KEKIdentifier kekIdentifier;

    /* JADX INFO: Access modifiers changed from: protected */
    public KEKRecipientInfoGenerator(KEKIdentifier kEKIdentifier, SymmetricKeyWrapper symmetricKeyWrapper) {
        this.kekIdentifier = kEKIdentifier;
        this.f13152a = symmetricKeyWrapper;
    }

    @Override // org.bouncycastle.cms.RecipientInfoGenerator
    public final RecipientInfo generate(GenericKey genericKey) {
        try {
            return new RecipientInfo(new KEKRecipientInfo(this.kekIdentifier, this.f13152a.getAlgorithmIdentifier(), new DEROctetString(this.f13152a.generateWrappedKey(genericKey))));
        } catch (OperatorException e2) {
            throw new CMSException("exception wrapping content key: " + e2.getMessage(), e2);
        }
    }
}
