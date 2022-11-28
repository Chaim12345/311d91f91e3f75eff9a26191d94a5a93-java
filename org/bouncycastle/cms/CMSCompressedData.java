package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.cms.CompressedData;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.operator.InputExpanderProvider;
import org.bouncycastle.util.Encodable;
/* loaded from: classes3.dex */
public class CMSCompressedData implements Encodable {

    /* renamed from: a  reason: collision with root package name */
    ContentInfo f13119a;

    /* renamed from: b  reason: collision with root package name */
    CompressedData f13120b;

    public CMSCompressedData(InputStream inputStream) {
        this(CMSUtils.q(inputStream));
    }

    public CMSCompressedData(ContentInfo contentInfo) {
        this.f13119a = contentInfo;
        try {
            this.f13120b = CompressedData.getInstance(contentInfo.getContent());
        } catch (ClassCastException e2) {
            throw new CMSException("Malformed content.", e2);
        } catch (IllegalArgumentException e3) {
            throw new CMSException("Malformed content.", e3);
        }
    }

    public CMSCompressedData(byte[] bArr) {
        this(CMSUtils.r(bArr));
    }

    public ASN1ObjectIdentifier getCompressedContentType() {
        return this.f13120b.getEncapContentInfo().getContentType();
    }

    public byte[] getContent(InputExpanderProvider inputExpanderProvider) {
        try {
            return CMSUtils.streamToByteArray(inputExpanderProvider.get(this.f13120b.getCompressionAlgorithmIdentifier()).getInputStream(((ASN1OctetString) this.f13120b.getEncapContentInfo().getContent()).getOctetStream()));
        } catch (IOException e2) {
            throw new CMSException("exception reading compressed stream.", e2);
        }
    }

    public CMSTypedStream getContentStream(InputExpanderProvider inputExpanderProvider) {
        ContentInfo encapContentInfo = this.f13120b.getEncapContentInfo();
        return new CMSTypedStream(encapContentInfo.getContentType(), inputExpanderProvider.get(this.f13120b.getCompressionAlgorithmIdentifier()).getInputStream(((ASN1OctetString) encapContentInfo.getContent()).getOctetStream()));
    }

    public ASN1ObjectIdentifier getContentType() {
        return this.f13119a.getContentType();
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() {
        return this.f13119a.getEncoded();
    }

    public ContentInfo toASN1Structure() {
        return this.f13119a;
    }
}
