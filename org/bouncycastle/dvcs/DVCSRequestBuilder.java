package org.bouncycastle.dvcs;

import java.io.IOException;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.dvcs.DVCSObjectIdentifiers;
import org.bouncycastle.asn1.dvcs.DVCSRequestInformationBuilder;
import org.bouncycastle.asn1.dvcs.Data;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.cms.CMSSignedDataGenerator;
/* loaded from: classes3.dex */
public abstract class DVCSRequestBuilder {

    /* renamed from: a  reason: collision with root package name */
    protected final DVCSRequestInformationBuilder f13525a;
    private final ExtensionsGenerator extGenerator = new ExtensionsGenerator();
    private final CMSSignedDataGenerator signedDataGen = new CMSSignedDataGenerator();

    /* JADX INFO: Access modifiers changed from: protected */
    public DVCSRequestBuilder(DVCSRequestInformationBuilder dVCSRequestInformationBuilder) {
        this.f13525a = dVCSRequestInformationBuilder;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DVCSRequest a(Data data) {
        if (!this.extGenerator.isEmpty()) {
            this.f13525a.setExtensions(this.extGenerator.generate());
        }
        return new DVCSRequest(new ContentInfo(DVCSObjectIdentifiers.id_ct_DVCSRequestData, new org.bouncycastle.asn1.dvcs.DVCSRequest(this.f13525a.build(), data)));
    }

    public void addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z, ASN1Encodable aSN1Encodable) {
        try {
            this.extGenerator.addExtension(aSN1ObjectIdentifier, z, aSN1Encodable);
        } catch (IOException e2) {
            throw new DVCSException("cannot encode extension: " + e2.getMessage(), e2);
        }
    }

    public void setDVCS(GeneralName generalName) {
        this.f13525a.setDVCS(generalName);
    }

    public void setDVCS(GeneralNames generalNames) {
        this.f13525a.setDVCS(generalNames);
    }

    public void setDataLocations(GeneralName generalName) {
        this.f13525a.setDataLocations(generalName);
    }

    public void setDataLocations(GeneralNames generalNames) {
        this.f13525a.setDataLocations(generalNames);
    }

    public void setNonce(BigInteger bigInteger) {
        this.f13525a.setNonce(bigInteger);
    }

    public void setRequester(GeneralName generalName) {
        this.f13525a.setRequester(generalName);
    }
}
