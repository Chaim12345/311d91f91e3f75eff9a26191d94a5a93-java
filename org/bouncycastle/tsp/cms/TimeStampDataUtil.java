package org.bouncycastle.tsp.cms;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.TimeStampAndCRL;
import org.bouncycastle.asn1.cms.TimeStampedData;
import org.bouncycastle.asn1.cms.TimeStampedDataParser;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.tsp.TSPException;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
class TimeStampDataUtil {
    private final MetaDataUtil metaDataUtil;
    private final TimeStampAndCRL[] timeStamps;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TimeStampDataUtil(TimeStampedData timeStampedData) {
        this.metaDataUtil = new MetaDataUtil(timeStampedData.getMetaData());
        this.timeStamps = timeStampedData.getTemporalEvidence().getTstEvidence().toTimeStampAndCRLArray();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TimeStampDataUtil(TimeStampedDataParser timeStampedDataParser) {
        this.metaDataUtil = new MetaDataUtil(timeStampedDataParser.getMetaData());
        this.timeStamps = timeStampedDataParser.getTemporalEvidence().getTstEvidence().toTimeStampAndCRLArray();
    }

    private void compareDigest(TimeStampToken timeStampToken, byte[] bArr) {
        if (!Arrays.areEqual(bArr, timeStampToken.getTimeStampInfo().getMessageImprintDigest())) {
            throw new ImprintDigestInvalidException("hash calculated is different from MessageImprintDigest found in TimeStampToken", timeStampToken);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] a(DigestCalculator digestCalculator) {
        TimeStampAndCRL[] timeStampAndCRLArr = this.timeStamps;
        TimeStampAndCRL timeStampAndCRL = timeStampAndCRLArr[timeStampAndCRLArr.length - 1];
        OutputStream outputStream = digestCalculator.getOutputStream();
        try {
            outputStream.write(timeStampAndCRL.getEncoded(ASN1Encoding.DER));
            outputStream.close();
            return digestCalculator.getDigest();
        } catch (IOException e2) {
            throw new CMSException("exception calculating hash: " + e2.getMessage(), e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String b() {
        return this.metaDataUtil.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String c() {
        return this.metaDataUtil.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DigestCalculator d(DigestCalculatorProvider digestCalculatorProvider) {
        try {
            DigestCalculator digestCalculator = digestCalculatorProvider.get(new AlgorithmIdentifier(f(this.timeStamps[0]).getTimeStampInfo().getMessageImprintAlgOID()));
            i(digestCalculator);
            return digestCalculator;
        } catch (CMSException e2) {
            throw new OperatorCreationException("unable to extract algorithm ID: " + e2.getMessage(), e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AttributeTable e() {
        return new AttributeTable(this.metaDataUtil.c());
    }

    TimeStampToken f(TimeStampAndCRL timeStampAndCRL) {
        try {
            return new TimeStampToken(timeStampAndCRL.getTimeStampToken());
        } catch (IOException e2) {
            throw new CMSException("unable to parse token data: " + e2.getMessage(), e2);
        } catch (IllegalArgumentException e3) {
            throw new CMSException("token data invalid: " + e3.getMessage(), e3);
        } catch (TSPException e4) {
            if (e4.getCause() instanceof CMSException) {
                throw ((CMSException) e4.getCause());
            }
            throw new CMSException("token data invalid: " + e4.getMessage(), e4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TimeStampToken[] g() {
        TimeStampToken[] timeStampTokenArr = new TimeStampToken[this.timeStamps.length];
        int i2 = 0;
        while (true) {
            TimeStampAndCRL[] timeStampAndCRLArr = this.timeStamps;
            if (i2 >= timeStampAndCRLArr.length) {
                return timeStampTokenArr;
            }
            timeStampTokenArr[i2] = f(timeStampAndCRLArr[i2]);
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TimeStampAndCRL[] h() {
        return this.timeStamps;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(DigestCalculator digestCalculator) {
        this.metaDataUtil.d(digestCalculator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(DigestCalculatorProvider digestCalculatorProvider, byte[] bArr) {
        int i2 = 0;
        while (true) {
            TimeStampAndCRL[] timeStampAndCRLArr = this.timeStamps;
            if (i2 >= timeStampAndCRLArr.length) {
                return;
            }
            try {
                TimeStampToken f2 = f(timeStampAndCRLArr[i2]);
                if (i2 > 0) {
                    DigestCalculator digestCalculator = digestCalculatorProvider.get(f2.getTimeStampInfo().getHashAlgorithm());
                    digestCalculator.getOutputStream().write(this.timeStamps[i2 - 1].getEncoded(ASN1Encoding.DER));
                    bArr = digestCalculator.getDigest();
                }
                compareDigest(f2, bArr);
                i2++;
            } catch (IOException e2) {
                throw new CMSException("exception calculating hash: " + e2.getMessage(), e2);
            } catch (OperatorCreationException e3) {
                throw new CMSException("cannot create digest: " + e3.getMessage(), e3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k(DigestCalculatorProvider digestCalculatorProvider, byte[] bArr, TimeStampToken timeStampToken) {
        try {
            byte[] encoded = timeStampToken.getEncoded();
            int i2 = 0;
            while (true) {
                TimeStampAndCRL[] timeStampAndCRLArr = this.timeStamps;
                if (i2 >= timeStampAndCRLArr.length) {
                    throw new ImprintDigestInvalidException("passed in token not associated with timestamps present", timeStampToken);
                }
                try {
                    TimeStampToken f2 = f(timeStampAndCRLArr[i2]);
                    if (i2 > 0) {
                        DigestCalculator digestCalculator = digestCalculatorProvider.get(f2.getTimeStampInfo().getHashAlgorithm());
                        digestCalculator.getOutputStream().write(this.timeStamps[i2 - 1].getEncoded(ASN1Encoding.DER));
                        bArr = digestCalculator.getDigest();
                    }
                    compareDigest(f2, bArr);
                    if (Arrays.areEqual(f2.getEncoded(), encoded)) {
                        return;
                    }
                    i2++;
                } catch (IOException e2) {
                    throw new CMSException("exception calculating hash: " + e2.getMessage(), e2);
                } catch (OperatorCreationException e3) {
                    throw new CMSException("cannot create digest: " + e3.getMessage(), e3);
                }
            }
        } catch (IOException e4) {
            throw new CMSException("exception encoding timeStampToken: " + e4.getMessage(), e4);
        }
    }
}
