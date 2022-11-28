package org.bouncycastle.cms;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSEnvelopedHelper;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes3.dex */
public abstract class RecipientInformation {

    /* renamed from: a  reason: collision with root package name */
    protected RecipientId f13158a;
    private AuthAttributesProvider additionalData;

    /* renamed from: b  reason: collision with root package name */
    protected AlgorithmIdentifier f13159b;

    /* renamed from: c  reason: collision with root package name */
    protected AlgorithmIdentifier f13160c;

    /* renamed from: d  reason: collision with root package name */
    protected CMSSecureReadable f13161d;
    private RecipientOperator operator;
    private byte[] resultMac;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecipientInformation(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        this.f13159b = algorithmIdentifier;
        this.f13160c = algorithmIdentifier2;
        this.f13161d = cMSSecureReadable;
        this.additionalData = authAttributesProvider;
    }

    private byte[] encodeObj(ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            return aSN1Encodable.toASN1Primitive().getEncoded();
        }
        return null;
    }

    protected abstract RecipientOperator a(Recipient recipient);

    public byte[] getContent(Recipient recipient) {
        try {
            return CMSUtils.streamToByteArray(getContentStream(recipient).getContentStream());
        } catch (IOException e2) {
            throw new CMSException("unable to parse internal stream: " + e2.getMessage(), e2);
        }
    }

    public byte[] getContentDigest() {
        CMSSecureReadable cMSSecureReadable = this.f13161d;
        if (cMSSecureReadable instanceof CMSEnvelopedHelper.CMSDigestAuthenticatedSecureReadable) {
            return ((CMSEnvelopedHelper.CMSDigestAuthenticatedSecureReadable) cMSSecureReadable).getDigest();
        }
        return null;
    }

    public CMSTypedStream getContentStream(Recipient recipient) {
        this.operator = a(recipient);
        AuthAttributesProvider authAttributesProvider = this.additionalData;
        if (authAttributesProvider != null) {
            if (authAttributesProvider.isAead()) {
                this.operator.getAADStream().write(this.additionalData.getAuthAttributes().getEncoded(ASN1Encoding.DER));
                return new CMSTypedStream(this.f13161d.getContentType(), this.operator.getInputStream(this.f13161d.getInputStream()));
            }
            return new CMSTypedStream(this.f13161d.getContentType(), this.f13161d.getInputStream());
        }
        return new CMSTypedStream(this.f13161d.getContentType(), this.operator.getInputStream(this.f13161d.getInputStream()));
    }

    public ASN1ObjectIdentifier getContentType() {
        return this.f13161d.getContentType();
    }

    public String getKeyEncryptionAlgOID() {
        return this.f13159b.getAlgorithm().getId();
    }

    public byte[] getKeyEncryptionAlgParams() {
        try {
            return encodeObj(this.f13159b.getParameters());
        } catch (Exception e2) {
            throw new RuntimeException("exception getting encryption parameters " + e2);
        }
    }

    public AlgorithmIdentifier getKeyEncryptionAlgorithm() {
        return this.f13159b;
    }

    public byte[] getMac() {
        if (this.resultMac == null && this.operator.isMacBased()) {
            if (this.additionalData != null) {
                try {
                    Streams.drain(this.operator.getInputStream(new ByteArrayInputStream(this.additionalData.getAuthAttributes().getEncoded(ASN1Encoding.DER))));
                } catch (IOException e2) {
                    throw new IllegalStateException("unable to drain input: " + e2.getMessage());
                }
            }
            this.resultMac = this.operator.getMac();
        }
        return this.resultMac;
    }

    public RecipientId getRID() {
        return this.f13158a;
    }
}
