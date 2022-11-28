package org.bouncycastle.cms;

import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.asn1.cms.OriginatorInfo;
/* loaded from: classes3.dex */
public class CMSAuthEnvelopedGenerator extends CMSEnvelopedGenerator {

    /* renamed from: d  reason: collision with root package name */
    final List f13105d = new ArrayList();

    /* renamed from: e  reason: collision with root package name */
    protected CMSAttributeTableGenerator f13106e = null;

    /* renamed from: f  reason: collision with root package name */
    protected CMSAttributeTableGenerator f13107f = null;

    /* renamed from: g  reason: collision with root package name */
    protected OriginatorInfo f13108g;

    @Override // org.bouncycastle.cms.CMSEnvelopedGenerator
    public void addRecipientInfoGenerator(RecipientInfoGenerator recipientInfoGenerator) {
        this.f13105d.add(recipientInfoGenerator);
    }

    public void setAuthenticatedAttributeGenerator(CMSAttributeTableGenerator cMSAttributeTableGenerator) {
        this.f13106e = cMSAttributeTableGenerator;
    }

    @Override // org.bouncycastle.cms.CMSEnvelopedGenerator
    public void setOriginatorInfo(OriginatorInformation originatorInformation) {
        this.f13108g = originatorInformation.toASN1Structure();
    }

    public void setUnauthenticatedAttributeGenerator(CMSAttributeTableGenerator cMSAttributeTableGenerator) {
        this.f13107f = cMSAttributeTableGenerator;
    }
}
