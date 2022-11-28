package org.bouncycastle.tsp.cms;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.ContentInfoParser;
import org.bouncycastle.asn1.cms.TimeStampedDataParser;
import org.bouncycastle.cms.CMSContentInfoParser;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes4.dex */
public class CMSTimeStampedDataParser extends CMSContentInfoParser {
    private TimeStampedDataParser timeStampedData;
    private TimeStampDataUtil util;

    public CMSTimeStampedDataParser(InputStream inputStream) {
        super(inputStream);
        initialize(this.f13121a);
    }

    public CMSTimeStampedDataParser(byte[] bArr) {
        this(new ByteArrayInputStream(bArr));
    }

    private void initialize(ContentInfoParser contentInfoParser) {
        try {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = CMSObjectIdentifiers.timestampedData;
            if (aSN1ObjectIdentifier.equals((ASN1Primitive) contentInfoParser.getContentType())) {
                this.timeStampedData = TimeStampedDataParser.getInstance(contentInfoParser.getContent(16));
                return;
            }
            throw new IllegalArgumentException("Malformed content - type must be " + aSN1ObjectIdentifier.getId());
        } catch (IOException e2) {
            throw new CMSException("parsing exception: " + e2.getMessage(), e2);
        }
    }

    private void parseTimeStamps() {
        try {
            if (this.util == null) {
                InputStream content = getContent();
                if (content != null) {
                    Streams.drain(content);
                }
                this.util = new TimeStampDataUtil(this.timeStampedData);
            }
        } catch (IOException e2) {
            throw new CMSException("unable to parse evidence block: " + e2.getMessage(), e2);
        }
    }

    public byte[] calculateNextHash(DigestCalculator digestCalculator) {
        return this.util.a(digestCalculator);
    }

    public InputStream getContent() {
        if (this.timeStampedData.getContent() != null) {
            return this.timeStampedData.getContent().getOctetStream();
        }
        return null;
    }

    public URI getDataUri() {
        DERIA5String dataUri = this.timeStampedData.getDataUri();
        if (dataUri != null) {
            return new URI(dataUri.getString());
        }
        return null;
    }

    public String getFileName() {
        return this.util.b();
    }

    public String getMediaType() {
        return this.util.c();
    }

    public DigestCalculator getMessageImprintDigestCalculator(DigestCalculatorProvider digestCalculatorProvider) {
        try {
            parseTimeStamps();
            return this.util.d(digestCalculatorProvider);
        } catch (CMSException e2) {
            throw new OperatorCreationException("unable to extract algorithm ID: " + e2.getMessage(), e2);
        }
    }

    public AttributeTable getOtherMetaData() {
        return this.util.e();
    }

    public TimeStampToken[] getTimeStampTokens() {
        parseTimeStamps();
        return this.util.g();
    }

    public void initialiseMessageImprintDigestCalculator(DigestCalculator digestCalculator) {
        this.util.i(digestCalculator);
    }

    public void validate(DigestCalculatorProvider digestCalculatorProvider, byte[] bArr) {
        parseTimeStamps();
        this.util.j(digestCalculatorProvider, bArr);
    }

    public void validate(DigestCalculatorProvider digestCalculatorProvider, byte[] bArr, TimeStampToken timeStampToken) {
        parseTimeStamps();
        this.util.k(digestCalculatorProvider, bArr, timeStampToken);
    }
}
