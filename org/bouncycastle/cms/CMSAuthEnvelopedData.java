package org.bouncycastle.cms;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.AuthEnvelopedData;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.EncryptedContentInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Encodable;
/* loaded from: classes3.dex */
public class CMSAuthEnvelopedData implements Encodable {

    /* renamed from: a  reason: collision with root package name */
    RecipientInformationStore f13100a;
    private ASN1Set authAttrs;
    private AlgorithmIdentifier authEncAlg;

    /* renamed from: b  reason: collision with root package name */
    ContentInfo f13101b;
    private byte[] mac;
    private OriginatorInformation originatorInfo;
    private ASN1Set unauthAttrs;

    public CMSAuthEnvelopedData(InputStream inputStream) {
        this(CMSUtils.q(inputStream));
    }

    public CMSAuthEnvelopedData(ContentInfo contentInfo) {
        this.f13101b = contentInfo;
        AuthEnvelopedData authEnvelopedData = AuthEnvelopedData.getInstance(contentInfo.getContent());
        if (authEnvelopedData.getOriginatorInfo() != null) {
            this.originatorInfo = new OriginatorInformation(authEnvelopedData.getOriginatorInfo());
        }
        ASN1Set recipientInfos = authEnvelopedData.getRecipientInfos();
        final EncryptedContentInfo authEncryptedContentInfo = authEnvelopedData.getAuthEncryptedContentInfo();
        this.authEncAlg = authEncryptedContentInfo.getContentEncryptionAlgorithm();
        this.mac = authEnvelopedData.getMac().getOctets();
        CMSSecureReadable cMSSecureReadable = new CMSSecureReadable() { // from class: org.bouncycastle.cms.CMSAuthEnvelopedData.1
            @Override // org.bouncycastle.cms.CMSSecureReadable
            public ASN1ObjectIdentifier getContentType() {
                return authEncryptedContentInfo.getContentType();
            }

            @Override // org.bouncycastle.cms.CMSSecureReadable
            public InputStream getInputStream() {
                return new ByteArrayInputStream(Arrays.concatenate(authEncryptedContentInfo.getEncryptedContent().getOctets(), CMSAuthEnvelopedData.this.mac));
            }
        };
        this.authAttrs = authEnvelopedData.getAuthAttrs();
        this.unauthAttrs = authEnvelopedData.getUnauthAttrs();
        this.f13100a = this.authAttrs != null ? CMSEnvelopedHelper.b(recipientInfos, this.authEncAlg, cMSSecureReadable, new AuthAttributesProvider() { // from class: org.bouncycastle.cms.CMSAuthEnvelopedData.2
            @Override // org.bouncycastle.cms.AuthAttributesProvider
            public ASN1Set getAuthAttributes() {
                return CMSAuthEnvelopedData.this.authAttrs;
            }

            @Override // org.bouncycastle.cms.AuthAttributesProvider
            public boolean isAead() {
                return true;
            }
        }) : CMSEnvelopedHelper.a(recipientInfos, this.authEncAlg, cMSSecureReadable);
    }

    public CMSAuthEnvelopedData(byte[] bArr) {
        this(CMSUtils.r(bArr));
    }

    public AttributeTable getAuthAttrs() {
        ASN1Set aSN1Set = this.authAttrs;
        if (aSN1Set == null) {
            return null;
        }
        return new AttributeTable(aSN1Set);
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() {
        return this.f13101b.getEncoded();
    }

    public byte[] getMac() {
        return Arrays.clone(this.mac);
    }

    public OriginatorInformation getOriginatorInfo() {
        return this.originatorInfo;
    }

    public RecipientInformationStore getRecipientInfos() {
        return this.f13100a;
    }

    public AttributeTable getUnauthAttrs() {
        ASN1Set aSN1Set = this.unauthAttrs;
        if (aSN1Set == null) {
            return null;
        }
        return new AttributeTable(aSN1Set);
    }

    public ContentInfo toASN1Structure() {
        return this.f13101b;
    }
}
